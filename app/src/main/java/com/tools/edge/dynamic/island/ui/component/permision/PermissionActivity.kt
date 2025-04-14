package com.tools.edge.dynamic.island.ui.component.permision

import android.Manifest
import android.R.attr
import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.PowerManager
import android.provider.Settings
import android.provider.Settings.Global
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.tools.edge.dynamic.island.R
import com.tools.edge.dynamic.island.ads.AdsManager.nativeAdLanguage
import com.tools.edge.dynamic.island.ads.AdsManager.nativeAdPermission
import com.tools.edge.dynamic.island.ads.AdsManager.setPreLoadNativeCallback
import com.tools.edge.dynamic.island.ads.PreLoadNativeListener
import com.tools.edge.dynamic.island.app.AppConstants.keyCheckAccessibility
import com.tools.edge.dynamic.island.app.AppConstants.keyCheckBattery
import com.tools.edge.dynamic.island.app.AppConstants.keyCheckBluetooth
import com.tools.edge.dynamic.island.app.AppConstants.keyCheckNotification
import com.tools.edge.dynamic.island.app.GlobalApp
import com.tools.edge.dynamic.island.databinding.ActivityPermissionBinding
import com.tools.edge.dynamic.island.ui.bases.BaseActivity
import com.tools.edge.dynamic.island.ui.bases.ext.click
import com.tools.edge.dynamic.island.ui.bases.ext.goneView
import com.tools.edge.dynamic.island.ui.bases.ext.visibleView
import com.tools.edge.dynamic.island.ui.component.background.AppConst
import com.tools.edge.dynamic.island.ui.component.background.PrefManager
import com.tools.edge.dynamic.island.ui.component.moduldetails.PermissionRequiredDialog
import com.tools.edge.dynamic.island.ui.component.service.ITGAccessibilityService
import com.tools.edge.dynamic.island.ui.component.service.NotificationService
import com.tools.edge.dynamic.island.ui.component.service.PermissionService
import com.tools.edge.dynamic.island.ui.component.utils.Constants
import com.tools.edge.dynamic.island.ui.component.utils.Utils
import com.tools.edge.dynamic.island.utils.EasyPreferences.set
import com.tools.edge.dynamic.island.utils.Routes
import com.tools.edge.dynamic.island.utils.Utils.isIgnoringBatteryOptimizations
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.annotations.AfterPermissionGranted
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.annotations.MustBeInvokedByOverriders
import timber.log.Timber


class PermissionActivity : BaseActivity<ActivityPermissionBinding>(), PreLoadNativeListener {

    private var prefManager: PrefManager? = null

    private var populateNativeAdView = false
    private var intentService: Intent? = null
    private val requestBluetoothPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { permission ->

            setKeyCheckCheckBluetooth(permission)
            mBinding.imvCheckBluetooth.setImageResource(if (permission) R.drawable.ic_select_app else R.drawable.ic_un_select_app)

        }

    private val LAUNCH_BATTERRY = 1001
    private var conutRunner: Int = 0

    val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                GlobalApp.isback = true
                Routes.startMainActivity(this)
                finish()
            }
            if (conutRunner == 0) {
                conutRunner++
                if (Utils.isServiceRunning(
                        this, NotificationService::class.java.name
                    )
                ) {
                    NotificationService.getInstance().initNotification()
                }
            }
        }

    private var dialogPermissionRequiredBluetooth: PermissionRequiredDialog? = null

    override fun getLayoutActivity(): Int = R.layout.activity_permission

    private var isShowDetailsAppNotification = false

    @SuppressLint("ClickableViewAccessibility")
    override fun initViews() {
        super.initViews()
        prefManager = PrefManager(this)

        dialogPermissionRequiredBluetooth = PermissionRequiredDialog(context = this) {
            startAppInfo()
        }


        dialogPermissionRequiredBluetooth?.setOnDismissListener {
            when {
                ContextCompat.checkSelfPermission(
                    this, Manifest.permission.BLUETOOTH_CONNECT
                ) == PackageManager.PERMISSION_GRANTED -> {
                    mBinding.imvCheckBluetooth.setImageResource(R.drawable.ic_select_app)
                }

                shouldShowRequestPermissionRationale(Manifest.permission.BLUETOOTH_CONNECT) -> {
//                    isShowDetailsAppNotification = true
                    mBinding.imvCheckBluetooth.setImageResource(R.drawable.ic_un_select_app)
                }

                else -> {
                    //  mBinding.switchBluetooth.isChecked = false
                    // You can directly ask for the permission.

                    mBinding.imvCheckBluetooth.setImageResource(R.drawable.ic_un_select_app)
                }
            }
        }


        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val intent = Intent()
                GlobalScope.launch(Dispatchers.IO){
                    delay(2000)
                    GlobalApp.isback = true
                }
                setResult(RESULT_OK, intent)
                finish()
            }

        })
        intentService = Intent(this, PermissionService::class.java)


        mBinding.imvCheckAccessibility.setImageResource(if (Constants.checkAccessibilityEnabled(this) && getKeyCheckAccessibility()) R.drawable.ic_select_app else R.drawable.ic_un_select_app)

        mBinding.imvCheckNotification.setImageResource(if (checkNotificationEnabled(this) && getKeyCheckNotification()) R.drawable.ic_select_app else R.drawable.ic_un_select_app)

        mBinding.imvCheckBattery.setImageResource(if (isIgnoringBatteryOptimizations(this) && getKeyCheckBattery()) R.drawable.ic_select_app else R.drawable.ic_un_select_app)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            mBinding.layoutBluetooth.visibleView()
            mBinding.imvCheckBluetooth.setImageResource(if (checkSelfPermission(Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED && getKeyCheckBluetooth()) R.drawable.ic_select_app else R.drawable.ic_un_select_app)

        }else {
            mBinding.layoutBluetooth.goneView()
        }
        setPreLoadNativeCallback(this)
        showNativePermission()
    }


    fun checkEnabled() {
        if (getKeyCheckAccessibility() && getKeyCheckNotification()) {
            if (Utils.isServiceRunning(
                    this@PermissionActivity, ITGAccessibilityService::class.java.name
                )
            ) {
                ITGAccessibilityService.getInstance().statusBarParentView.visibility = View.VISIBLE
                ITGAccessibilityService.getInstance().onReloadDynamic()
            }
        } else {
            if (Utils.isServiceRunning(
                    this@PermissionActivity, ITGAccessibilityService::class.java.name
                )
            ) {
                ITGAccessibilityService.getInstance().statusBarParentView.visibility = View.GONE
            }
        }
    }

    private fun initIntentBattery() {
        val intent = Intent()
        val packageName: String = this.packageName
        val pm = this.getSystemService(POWER_SERVICE) as PowerManager
        if (pm.isIgnoringBatteryOptimizations(packageName)) {
            intent.setAction(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS)
        } else {
            intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
            intent.setData(Uri.parse("package:$packageName"))
        }
        resultLauncher.launch(intent)
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            Timber.tag("chinh_dep_trai").d("onActivityResult() called with: requestCode ")
            Handler().postDelayed({

                if (isIgnoringBatteryOptimizations(this)) {
                    prefManager?.showNotificationBatteryChange = true
                    setKeyCheckBattery(true)
                    mBinding.imvCheckBattery.setImageResource(R.drawable.ic_select_app)
                }
            }, 1000)

        }


    private fun initNotification() {
        if (!PermissionService.checkNotificationEnabled(this)) {
            try {
                val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
                val bundle = Bundle()
                val str: String =
                    this.packageName + "/" + com.tools.edge.dynamic.island.ui.component.service.NotificationService::class.java.getName()
                bundle.putString(":settings:fragment_args_key", str)
                intent.putExtra(":settings:fragment_args_key", str)
                intent.putExtra(":settings:show_fragment_args", bundle)
                val permissionsActivity = this
                permissionsActivity.startActivity(
                    intent, ActivityOptions.makeCustomAnimation(
                        this, R.anim.fade_in, R.anim.fade_out
                    ).toBundle()
                )
            } catch (unused: java.lang.Exception) {
                Toast.makeText(
                    this,
                    "Notification service activity not found.\nPlease grant permission manually",
                    Toast.LENGTH_LONG
                ).show()
            }
            Constants.setNotif(this, true)
        } else {

        }


    }

    private fun requestAccessibilityPermission() {
        if (!Constants.checkAccessibilityEnabled(this)) {
            com.tools.edge.dynamic.island.utils.Utils.showIntentAccessibilityService(this)
        }

    }

    private fun showNativePermission() {
        if (nativeAdPermission != null) {
            Timber.e("initAdmob: ${nativeAdPermission}")
            populateNativeAdView = true

        } else {
            mBinding.frAds.removeAllViews()
            Timber.d(
                "LanguageActivity initAds nativeAdViewLanguage = ${nativeAdLanguage} - nativeAdLanguage = ${nativeAdLanguage}"
            )
        }
    }

    private fun getKeyCheckBattery(): Boolean = prefs.getBoolean(keyCheckBattery, false)
    private fun setKeyCheckBattery(value: Boolean) = prefs.set(keyCheckBattery, value)
    private fun getKeyCheckNotification(): Boolean = prefs.getBoolean(keyCheckNotification, false)
    private fun setKeyCheckNotification(value: Boolean) {
        prefs[keyCheckNotification] = value
    }

    private fun getKeyCheckAccessibility(): Boolean = prefs.getBoolean(keyCheckAccessibility, false)
    private fun setKeyCheckAccessibility(value: Boolean) {
        prefs[keyCheckAccessibility] = value
    }

    private fun getKeyCheckBluetooth(): Boolean = prefs.getBoolean(keyCheckBluetooth, false)

    private fun setKeyCheckCheckBluetooth(value: Boolean) {
        prefs[keyCheckBluetooth] = value
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onClickViews() {
        super.onClickViews()


        // permission Accessibility
        mBinding.switchAccessibility.click {

            if (Constants.checkAccessibilityEnabled(this)) {
                setKeyCheckAccessibility(!getKeyCheckAccessibility())
                mBinding.imvCheckAccessibility.setImageResource(if (getKeyCheckAccessibility()) R.drawable.ic_select_app else R.drawable.ic_un_select_app)

                checkEnabled()
            } else {
                startService(intentService)
                requestAccessibilityPermission()
            }
        }

        mBinding.switchBattery.click {
            if (isIgnoringBatteryOptimizations(this)) {
                val boolean = getKeyCheckBattery()
                setKeyCheckBattery(!boolean)
                mBinding.imvCheckBattery.setImageResource(if (!boolean) R.drawable.ic_select_app else R.drawable.ic_un_select_app)
            } else {
                initIntentBattery()
            }

        }

        // permission notifi
        mBinding.switchNotification.click {
            val boolean = getKeyCheckNotification()
            val isNotificationEnable = checkNotificationEnabled(this)
            if (isNotificationEnable) {
                setKeyCheckNotification(!boolean)
                mBinding.imvCheckNotification.setImageResource(if (!boolean) R.drawable.ic_select_app else R.drawable.ic_un_select_app)
//                mBinding.switchNotification.isChecked = !boolean
                checkEnabled()
            } else {
                startService(intentService)
                initNotification()
            }


        }


        // permission Bluetooth
        mBinding.switchBluetooth.click {
            when {
                ContextCompat.checkSelfPermission(
                    this, Manifest.permission.BLUETOOTH_CONNECT
                ) == PackageManager.PERMISSION_GRANTED -> {
                    val boolean = getKeyCheckBluetooth()
//                    mBinding.switchBluetooth.isChecked = !boolean
                    mBinding.imvCheckBluetooth.setImageResource(if (!boolean) R.drawable.ic_select_app else R.drawable.ic_un_select_app)
                    setKeyCheckCheckBluetooth(!boolean)
                }

                shouldShowRequestPermissionRationale(Manifest.permission.BLUETOOTH_CONNECT) -> {
                    val boolean = getKeyCheckBluetooth()
                    isShowDetailsAppNotification = true
                    dialogPermissionRequiredBluetooth?.show()
                    setKeyCheckCheckBluetooth(!boolean)
                }

                else -> {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                        requestBluetoothPermission.launch(Manifest.permission.BLUETOOTH_CONNECT)
                        val boolean = getKeyCheckBluetooth()
                        mBinding.imvCheckBluetooth.setImageResource(if (!boolean) R.drawable.ic_select_app else R.drawable.ic_un_select_app)
                        setKeyCheckCheckBluetooth(!boolean)
                    }
                    mBinding.imvCheckBluetooth.setImageResource(R.drawable.ic_un_select_app)
                    setKeyCheckCheckBluetooth(false)
                }
            }


        }


        mBinding.imgBack.click {
            onBackPressedDispatcher.onBackPressed()
        }


    }


    companion object {
        const val BLUETOOTH_PERMISSION = 6868
    }


    private fun checkNotificationEnabled(context: Context): Boolean {
        return try {
            Settings.Secure.getString(context.contentResolver, "enabled_notification_listeners")
                .contains(context.packageName)
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    override fun onResume() {
        super.onResume()

        val notificationEnable = checkNotificationEnabled(this)
        if (notificationEnable && getKeyCheckNotification()) {
            mBinding.imgNumber2.setImageResource(R.drawable.ic_permission_success)
            mBinding.imvCheckNotification.setImageResource(R.drawable.ic_select_app)
            Constants.setNotif(this, true)

        } else {
            mBinding.imgNumber2.setImageResource(R.drawable.ic_number_2)
            mBinding.imvCheckNotification.setImageResource(R.drawable.ic_un_select_app)
            Constants.setNotif(this, false)
        }

        val checkAccessibilityEnabled = Constants.checkAccessibilityEnabled(this)
        Log.e("TAG", "onResume checkAccessibilityEnabled: $checkAccessibilityEnabled")
        if (notificationEnable && checkAccessibilityEnabled && getKeyCheckNotification() && getKeyCheckAccessibility()) {
            val intentReload = Intent(packageName + AppConst.RELOAD_DYNAMIC)
            sendBroadcast(intentReload)
        }

        if (checkAccessibilityEnabled && getKeyCheckAccessibility()) {
            Constants.setControlEnabled(this, true)
            mBinding.imvCheckAccessibility.setImageResource(R.drawable.ic_select_app)
            mBinding.imgNumber.setImageResource(R.drawable.ic_permission_success)
        } else {
            mBinding.imgNumber.setImageResource(R.drawable.ic_number_1)
            Constants.setControlEnabled(this, false)
            mBinding.imvCheckAccessibility.setImageResource(R.drawable.ic_un_select_app)
        }

        val isBatteryEnable = isIgnoringBatteryOptimizations(this)

        if (isBatteryEnable && getKeyCheckBattery()) {
            mBinding.imvCheckBattery.setImageResource(R.drawable.ic_select_app)
            mBinding.imgNumber3.setImageResource(R.drawable.ic_permission_success)

        } else {
            mBinding.imgNumber3.setImageResource(R.drawable.ic_number_3)
            mBinding.imvCheckBattery.setImageResource(R.drawable.ic_un_select_app)
        }

        val isBluetoothEnabled = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            ActivityCompat.checkSelfPermission(
                this, Manifest.permission.BLUETOOTH_CONNECT
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }


        if (isBluetoothEnabled && getKeyCheckBluetooth()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                mBinding.switchBluetooth.visibleView()
                val checkBluetoothPermissions = ActivityCompat.checkSelfPermission(
                    this, Manifest.permission.BLUETOOTH_CONNECT
                ) == PackageManager.PERMISSION_GRANTED
                mBinding.imgNumber4.setImageResource(
                    if (checkBluetoothPermissions && getKeyCheckBluetooth())

                        R.drawable.ic_permission_success
                    else R.drawable.ic_number_4
                )
                mBinding.imvCheckBluetooth.setImageResource(if (checkBluetoothPermissions && getKeyCheckBluetooth()) R.drawable.ic_select_app else R.drawable.ic_un_select_app)
            } else {
                mBinding.layoutBluetooth.goneView()
            }

        } else {
            mBinding.imgNumber4.setImageResource(R.drawable.ic_number_4)
//            mBinding.switchBluetooth.isChecked = false
            mBinding.imvCheckBluetooth.setImageResource(R.drawable.ic_un_select_app)
        }

        if (isBatteryEnable && checkAccessibilityEnabled && notificationEnable && isBluetoothEnabled) {


//            when {
//
//            }
//            //Manifest.permission.READ_CONTACTS
//            requestReadPhoneState.launch(
//                arrayOf(
//                    Manifest.permission.READ_PHONE_STATE,
//                )
//            )


            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_PHONE_STATE
                ) == PackageManager.PERMISSION_GRANTED -> {
                    // You can use the API that requires the permission.
                }
                ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.READ_PHONE_STATE) -> {
                    // In an educational UI, explain to the user why your app requires this
                    // permission for a specific feature to behave as expected, and what
                    // features are disabled if it's declined. In this UI, include a
                    // "cancel" or "no thanks" button that lets the user continue
                    // using your app without granting the permission.
//                    showInContextUI(...)
                }
                else -> {
                    // You can directly ask for the permission.
                    // The registered ActivityResultCallback gets the result of this request.
                    requestPermissionLauncher.launch(
                        Manifest.permission.READ_PHONE_STATE)
                }
            }
        }


        if (dialogPermissionRequiredBluetooth?.isShowing == true) {
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.BLUETOOTH_CONNECT
                ) == PackageManager.PERMISSION_GRANTED

            ) {
                dialogPermissionRequiredBluetooth?.dismiss()
            }
        }

    }


    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onLoadNativeSuccess() {
        showNativePermission()
    }

    private fun startAppInfo() {
        try {
            //Open the specific App Info page:
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            intent.setData(Uri.parse("package:$packageName"))
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            //e.printStackTrace();

            //Open the generic Apps page:
            val intent = Intent(Settings.ACTION_MANAGE_APPLICATIONS_SETTINGS)
            startActivity(intent)
        }
    }


    override fun onLoadNativeFail() {
        if (nativeAdPermission != null) {
            mBinding.frAds.visibleView()
        } else {
            mBinding.frAds.goneView()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dialogPermissionRequiredBluetooth?.dismiss()
    }
}