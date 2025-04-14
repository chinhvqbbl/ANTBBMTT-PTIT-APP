package com.tools.edge.dynamic.island.ui.component.moduldetails

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.preference.PreferenceManager
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.os.bundleOf


import com.tools.edge.dynamic.island.R
import com.tools.edge.dynamic.island.ads.AdsManager
import com.tools.edge.dynamic.island.ads.PreLoadNativeListener
import com.tools.edge.dynamic.island.databinding.ActivityModulDetailsBinding
import com.tools.edge.dynamic.island.ui.bases.BaseActivity
import com.tools.edge.dynamic.island.ui.bases.ext.goneView
import com.tools.edge.dynamic.island.ui.bases.ext.isNetwork
import com.tools.edge.dynamic.island.ui.bases.ext.visibleView
import com.tools.edge.dynamic.island.ui.component.background.AppConst
import com.tools.edge.dynamic.island.ui.component.background.PrefManager
import com.tools.edge.dynamic.island.ui.component.model.ModulesType
import com.tools.edge.dynamic.island.ui.component.utils.Constants
import com.tools.edge.dynamic.island.utils.Routes
import com.tools.edge.dynamic.island.utils.Utils

class ModulDetailsActivity : BaseActivity<ActivityModulDetailsBinding>() {
    private var populateNative: Boolean = false
    private val listLayoutModulesType: MutableList<View> = mutableListOf()
    private var prefManager: PrefManager? = null
    private var preferences: SharedPreferences? = null
    private var launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        if (it.resultCode == Activity.RESULT_OK) {

        }
    }

    val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {

            }
        }

    override fun getLayoutActivity(): Int = R.layout.activity_modul_details

    override fun onResume() {
        super.onResume()
        val isBluetoothEnabled = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            ActivityCompat.checkSelfPermission(
                this, Manifest.permission.BLUETOOTH_CONNECT
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
        if (!isBluetoothEnabled) {
            mBinding.switchDisplayHaedset.isChecked = false
        } else {
            mBinding.switchDisplayHaedset.isChecked = prefManager?.showNotificationHeadset ?: false
        }


        val isBatteryEnable = Utils.isIgnoringBatteryOptimizations(this)

        if (isBatteryEnable) {
            mBinding.switchDisplayBatteryChange.isChecked =
                prefManager?.showNotificationBatteryChange ?: false
        } else {
            mBinding.switchDisplayBatteryChange.isChecked = false

        }
    }


    override fun initViews() {
        super.initViews()
        initOnBackPress()
        prefManager = PrefManager(this)
        preferences = PreferenceManager.getDefaultSharedPreferences(this)

        listLayoutModulesType.add(mBinding.layoutMusic)
        listLayoutModulesType.add(mBinding.layoutNotification)
        listLayoutModulesType.add(mBinding.layoutCall)
        listLayoutModulesType.add(mBinding.layoutNavigation)
        listLayoutModulesType.add(mBinding.layoutHaedset)
        listLayoutModulesType.add(mBinding.layoutBatteryChange)
        listLayoutModulesType.add(mBinding.layoutRingingState)

        listLayoutModulesType.forEach { it.goneView() }

        val bundle = intent.extras

        val nameCategory = bundle?.getString(Constants.KEY_MODULES)

        when (nameCategory) {
            ModulesType.MUSIC.name -> {
                mBinding.tvNameModule.setText(R.string.txt_music)
                initModuleMusic()
            }

            ModulesType.NOTIFICATION.name -> {
                mBinding.tvNameModule.setText(R.string.txt_notification)
                initModuleNotification()
            }

            ModulesType.CALLS.name -> {
                mBinding.tvNameModule.setText(R.string.txt_calls)
                initModuleCall()
            }

            ModulesType.NAVIGATION.name -> {
                mBinding.tvNameModule.setText(R.string.txt_navigation)
                initModuleNavigation()
            }

            ModulesType.HEADSET.name -> {
                mBinding.tvNameModule.setText(R.string.txt_headset)
                initModuleHeadset()
            }

            ModulesType.BATTERY_CHANGE.name -> {
                mBinding.tvNameModule.setText(R.string.txt_battery_charge)
                initModuleBatteryChange()
            }

            ModulesType.RINGING_STATE.name -> {
                mBinding.tvNameModule.setText(R.string.txt_ringing_state)
                initModuleRingingState()
            }

        }
        initSharePreferencesListener()

        showNativeModules()
        if (AdsManager.nativeModules == null) {
            AdsManager.setPreLoadNativeCallback(object : PreLoadNativeListener {
                override fun onLoadNativeSuccess() {
                    showNativeModules()
                }

                override fun onLoadNativeFail() {
                    showNativeModules()
                }

            })

            AdsManager.loadNativeModules(this)

        }
        AdsManager.loadInterModulesBack(this)
        mBinding.frAds.goneView()
    }

    private fun showNativeModules() {
        if (isNetwork()) {
            if (AdsManager.nativeModules != null && !populateNative) {
                mBinding.frAds.visibleView()
                populateNative = true

            } else {
                mBinding.frAds.goneView()
            }
        } else {
            mBinding.frAds.goneView()
        }
    }

    private fun initModuleNotification() {
        mBinding.layoutNotification.visibleView()

        mBinding.btnAppNotifications.setOnClickListener {
            Routes.startSelectAppsActivity(this, bundleOf(), launcher)
        }

        val isShowingNotificationOnLockScreen = prefManager?.showOnLock
        mBinding.switchHideOnLockScreen.isChecked = isShowingNotificationOnLockScreen ?: true
        mBinding.switchHideOnLockScreen.setOnCheckedChangeListener { _, isShow ->
            prefManager?.showOnLock = isShow
        }


        val isShowingNotificationAction = prefManager?.showAction
        mBinding.switchShowAction.isChecked = isShowingNotificationAction ?: true
        mBinding.switchShowAction.setOnCheckedChangeListener { _, isShow ->
            prefManager?.showAction = isShow
        }


        val isShowingNotification = prefManager?.showNotification
        mBinding.switchDisplayNotification.isChecked = isShowingNotification ?: true

        mBinding.switchDisplayNotification.setOnCheckedChangeListener { _, isShow ->
            prefManager?.showNotification = isShow
        }

        val isShowingQuickDisplay = prefManager?.showQuickDisplay
        mBinding.switchQuickDisplay.isChecked = isShowingQuickDisplay ?: true
        mBinding.switchQuickDisplay.setOnCheckedChangeListener { _, isShow ->
            prefManager?.showQuickDisplay = isShow
        }


    }

    private fun initModuleCall() {
        mBinding.layoutCall.visibleView()
        val isShowCallDefault = prefManager?.getShowCall()
        mBinding.switchDisplayCall.isChecked = isShowCallDefault ?: false

        mBinding.switchDisplayCall.setOnCheckedChangeListener { _, isChecked ->
            prefManager?.showCall = isChecked
        }

        val isShowTimeCall = prefManager?.showTimeCall ?: false
        mBinding.switchDisplayCallTimer.isChecked = isShowTimeCall

        mBinding.switchDisplayCallTimer.setOnCheckedChangeListener { _, isChecked ->
            prefManager?.showTimeCall = isChecked
        }
    }

    private fun initModuleNavigation() {
        mBinding.layoutNavigation.visibleView()
        val isShow = prefManager?.showNotificationNavigation
        mBinding.switchDisplayNavigation.isChecked = isShow ?: true
        mBinding.switchDisplayNavigation.setOnCheckedChangeListener { _, isChecked ->
            prefManager?.showNotificationNavigation = isChecked
        }
    }


    private fun initModuleHeadset() {
        mBinding.layoutHaedset.visibleView()
        val isBluetoothEnabled = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            ActivityCompat.checkSelfPermission(
                this, Manifest.permission.BLUETOOTH_CONNECT
            ) == PackageManager.PERMISSION_GRANTED
        } else {
            true
        }
        val isShow = prefManager?.showNotificationHeadset
        if (isShow == true && isBluetoothEnabled) {
            mBinding.switchDisplayHaedset.isChecked = true
        } else {
            mBinding.switchDisplayHaedset.isChecked = false
        }


        mBinding.switchDisplayHaedset.setOnCheckedChangeListener { _, isChecked ->
            val isBluetoothEnabledFromPermissionActivity =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    ActivityCompat.checkSelfPermission(
                        this, Manifest.permission.BLUETOOTH_CONNECT
                    ) == PackageManager.PERMISSION_GRANTED
                } else {
                    true
                }
            if (isChecked) {
                if (isBluetoothEnabledFromPermissionActivity) {
                    prefManager?.showNotificationHeadset = true
                    mBinding.switchDisplayHaedset.isChecked = true
                } else {
                    Log.d("chinh_dep_trai_kk_4", "initViews() called with: activity =")
                    Routes.startPermissionActivity(this, resultLauncher)
                }
            } else {
                prefManager?.showNotificationHeadset = false
            }
        }
    }

    private fun initModuleRingingState() {
        mBinding.layoutRingingState.visibleView()
        val isShow = prefManager?.showNotificationRingMode
        mBinding.switchDisplayRingingState.isChecked = isShow ?: true

        mBinding.switchDisplayRingingState.setOnCheckedChangeListener { _, isChecked ->
            prefManager?.showNotificationRingMode = isChecked
        }
    }

    private fun initModuleBatteryChange() {

        mBinding.layoutBatteryChange.visibleView()
        val isShow = prefManager?.showNotificationBatteryChange
        mBinding.switchDisplayBatteryChange.isChecked = isShow ?: true

        mBinding.switchDisplayBatteryChange.setOnCheckedChangeListener { _, isChecked ->

            if (isChecked) {
                if (Utils.isIgnoringBatteryOptimizations(this)) {
                    prefManager?.showNotificationBatteryChange = true
                    mBinding.switchDisplayBatteryChange.isChecked = true
                } else {
                    initIntentBattery()
                }

            } else {
                prefManager?.showNotificationBatteryChange = false
            }
        }

    }

    private fun initIntentBattery() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val intent = Intent()
            val packageName: String = this.packageName
            val pm = this.getSystemService(POWER_SERVICE) as PowerManager
            if (pm.isIgnoringBatteryOptimizations(packageName)) intent.setAction(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS) else {
                intent.setAction(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS)
                intent.setData(Uri.parse("package:$packageName"))
            }
            this.startActivity(intent)
        }
    }

    private fun initSharePreferencesListener() {
//        val listener =
//            SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
//                val intent = Intent(packageName + Constants.UPDATE_MODUL)
//                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
//            }
//        preferences?.registerOnSharedPreferenceChangeListener(listener)
    }

    private fun initModuleMusic() {
        mBinding.layoutMusic.visibleView()
        mBinding.switchDisplayMusic.isChecked = prefManager?.showNotificationMusic ?: true
        if (mBinding.switchDisplayMusic.isChecked && prefManager?.showNotificationMusicRotate == true) {
            mBinding.switchRotateMusic.isChecked = true
        } else {
            mBinding.switchRotateMusic.isChecked = false
        }
        mBinding.switchRotateMusic.setOnCheckedChangeListener { _, isChecked ->
            if (mBinding.switchDisplayMusic.isChecked) {
                if (isChecked) {
                    if (prefManager?.showNotificationMusic == true) {
                        mBinding.switchRotateMusic.isChecked = true
                        prefManager?.showNotificationMusicRotate = true
                    }
                } else {
                    prefManager?.showNotificationMusicRotate = false
                }
            } else {
                mBinding.switchRotateMusic.isChecked = false
                prefManager?.showNotificationMusicRotate = false
            }
        }

        mBinding.switchDisplayMusic.setOnCheckedChangeListener { _, isChecked ->
            prefManager?.showNotificationMusic = isChecked
            if (!isChecked) {
                mBinding.switchRotateMusic.isChecked = false
            } else {
                val intent = Intent(getPackageName() + AppConst.INTENT_LONG_CLICK)
                sendBroadcast(intent)
            }
        }
    }

    override fun onClickViews() {
        super.onClickViews()
        mBinding.imvBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun initOnBackPress() {
        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (AdsManager.mInterModulesBack != null && AdsManager.mInterModulesBack?.isReady == true) {

                } else {
                    setResult(RESULT_OK)
                    finish()
                }
            }
        })
    }


}