package com.tools.edge.dynamic.island.ui.component.main

import android.app.Activity
import android.os.Handler
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2


import com.bbl.module_ads.admob.AppOpenManager
import com.bbl.module_ads.funtion.AdCallback
import com.bbl.module_ads.util.AppConstant


import com.google.android.ump.FormError
import com.itg.iaumodule.IAdConsentCallBack
import com.itg.iaumodule.ITGAdConsent
import com.tools.edge.dynamic.island.BuildConfig
import com.tools.edge.dynamic.island.R
import com.tools.edge.dynamic.island.ads.AdsManager
import com.tools.edge.dynamic.island.ads.PreLoadNativeListener
import com.tools.edge.dynamic.island.ads.RemoteConfigUtils
import com.tools.edge.dynamic.island.app.AppConstants
import com.tools.edge.dynamic.island.app.AppConstants.IS_FIRST_RATED
import com.tools.edge.dynamic.island.app.AppConstants.KEY_SET_SHOW_DIALOD_RATE
import com.tools.edge.dynamic.island.app.GlobalApp
import com.tools.edge.dynamic.island.databinding.ActivityMainBinding
import com.tools.edge.dynamic.island.ui.bases.BaseActivity
import com.tools.edge.dynamic.island.ui.bases.ext.click
import com.tools.edge.dynamic.island.ui.bases.ext.goneView
import com.tools.edge.dynamic.island.ui.bases.ext.isNetwork
import com.tools.edge.dynamic.island.ui.bases.ext.replaceFragment
import com.tools.edge.dynamic.island.ui.bases.ext.showRateDialog
import com.tools.edge.dynamic.island.ui.bases.ext.visibleView
import com.tools.edge.dynamic.island.ui.component.background.AppConst
import com.tools.edge.dynamic.island.ui.component.dialog.ExitDialog
import com.tools.edge.dynamic.island.ui.component.main.adpter.MainAdapter
import com.tools.edge.dynamic.island.ui.component.main.configs.ConfigsFragment
import com.tools.edge.dynamic.island.ui.component.main.home.HomeFragment
import com.tools.edge.dynamic.island.ui.component.main.modules.ModulesFragment
import com.tools.edge.dynamic.island.ui.component.onboarding.OnBoardingActivity
import com.tools.edge.dynamic.island.ui.component.service.ITGAccessibilityService
import com.tools.edge.dynamic.island.ui.component.service.NotificationService
import com.tools.edge.dynamic.island.ui.component.utils.Constants
import com.tools.edge.dynamic.island.ui.component.utils.Utils
import com.tools.edge.dynamic.island.utils.ConnectionLiveData
import com.tools.edge.dynamic.island.utils.EasyPreferences.get
import com.tools.edge.dynamic.island.utils.EasyPreferences.set
import com.tools.edge.dynamic.island.utils.ITGTrackingHelper
import com.tools.edge.dynamic.island.utils.ITGTrackingHelper.logEvent
import com.tools.edge.dynamic.island.utils.Routes
import com.tools.edge.dynamic.island.utils.SharePreferenceUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutActivity(): Int = R.layout.activity_main
    private var populateNative: Boolean = false
    val isFirstStart = false
    var dialogExists: ExitDialog? = null
    var lastPosition = 0
    var mainAdapter: MainAdapter? = null
    val homeFragment = HomeFragment()
    val modulesFragment = ModulesFragment()

    override fun onStart() {
        super.onStart()
        val isFromPermissionService = intent.extras!!.getBoolean(AppConst.IS_FROM_SERVICE, false)
        if (isFromPermissionService) {

            intent.putExtra(AppConst.IS_FROM_SERVICE, false)
            Log.d("chinh_dep_trai_kk_1", "initViews() called with: activity ")
            Routes.startPermissionActivity(this, resultLauncher)

        } else {
            AppOpenManager.getInstance().enableAppResumeWithActivity(MainActivity::class.java)
        }
        Log.e("TAG", "onStart isFromPermissionService: $isFromPermissionService")
    }


    fun showNativeAds(isShowAds: Boolean) {
        showNativeMain()
    }

    override fun onResume() {
        super.onResume()
        ConnectionLiveData(this).observe(this) { isNetwork ->
            if (isNetwork) {
                Timber.d("network on")
            } else {
                Timber.d("network off")
            }
        }

        if (Constants.getNotif(this) && Utils.isServiceRunning(
                this, NotificationService::class.java.name
            ) && Utils.isServiceRunning(
                this, ITGAccessibilityService::class.java.name
            ) && prefs.getBoolean(AppConstants.keyCheckNotification, false) && prefs.getBoolean(
                AppConstants.keyCheckAccessibility, false
            )
        ) {
            ITGAccessibilityService.getInstance().scrollToMusic()
        } else {
            mBinding.switchDisplayNotchTutor.isChecked = false
        }
    }

    override fun initViews() {
        super.initViews()
        dialogExists = ExitDialog(this, onClickExit = {
            if (prefs[KEY_SET_SHOW_DIALOD_RATE, false] == false && prefs[IS_FIRST_RATED, true] == true) {
                if (RemoteConfigUtils.getIsShowRate()) {
                    showRateDialog(this@MainActivity, true)
                    prefs[IS_FIRST_RATED] = false
                }
            } else {
                finish()
            }
        })
        prefs[AppConstants.KEY_FIRST_ON_BOARDING] = true
        prefs[AppConstants.KEY_SELECT_LANGUAGE] = true

        if (prefs[AppConstants.KEY_CONFIRM_CONSENT, false] == false && prefs[AppConstants.KEY_IS_USER_GLOBAL, false] == false) {
            delayShowConsentDialog()
        }
//        HomeFragment().apply {
//            replaceFragment(this@MainActivity, R.id.fragment_container)
//        }
        val firstDemo = SharePreferenceUtil(this).getBooleanValue(
            AppConst.FIRST_SHOW_DEMO_DISPLAY_NOTCH, true
        )
        if (firstDemo) {
            SharePreferenceUtil(this).setBooleanValue(
                AppConst.FIRST_SHOW_DEMO_DISPLAY_NOTCH, false
            )
            hideDemo(true)
        } else {
            hideDemo(false)
        }
        initOnBackPressed()
        loadBannerMain()
        AdsManager.loadInterMain(this)
        AdsManager.loadNativeModules(this)
        AdsManager.loadNativeExit(this)
        mBinding.frAds.goneView()
        mainAdapter = MainAdapter(this)
        mainAdapter?.addFragment(homeFragment)
        mainAdapter?.addFragment(modulesFragment)
        mainAdapter?.addFragment(ConfigsFragment())

        mBinding.vpgMain.apply {
            setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
            adapter = mainAdapter
            setUserInputEnabled(false)
        }
//        mBinding.vpgMain.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
//            override fun onPageSelected(position: Int) {
//                super.onPageSelected(position)
//
//                when(position){
//                    0 -> {}
//                    1 -> {}
//                    2 -> {}
//
//                }
//            }
//        })


//        showNativeMain()
    }

    private fun showNativeMain() {
        if (isNetwork()) {
            if (AdsManager.nativeHome != null) {
                mBinding.frAds.visibleView()

            } else {
                mBinding.frAds.goneView()
            }
        } else {
            mBinding.frAds.goneView()
        }

    }


    private fun loadBannerMain() {

    }


    fun openHome() {
        if (lastPosition == 0) return
        lastPosition = 0

        showNativeMain()
        mBinding.imgHome.setImageResource(R.drawable.ic_home_active)
        mBinding.imgModule.setImageResource(R.drawable.ic_module_inactive)
        mBinding.imgConfigs.setImageResource(R.drawable.ic_configs_inactive)
        mBinding.tvHome.setTextColor(ContextCompat.getColor(this, R.color.black))
        mBinding.tvModule.setTextColor(ContextCompat.getColor(this, R.color.color_777777))
        mBinding.tvConfigs.setTextColor(ContextCompat.getColor(this, R.color.color_777777))
//        HomeFragment().apply {
//            replaceFragment(this@MainActivity, R.id.fragment_container)
//        }
        mBinding.vpgMain.setCurrentItem(0, true)
    }


    fun openModule() {
        if (lastPosition == 1) return
        lastPosition = 1
        showNativeMain()
        mBinding.imgHome.setImageResource(R.drawable.ic_home_inactive)
        mBinding.imgModule.setImageResource(R.drawable.ic_module_active)
        mBinding.imgConfigs.setImageResource(R.drawable.ic_configs_inactive)
        mBinding.tvHome.setTextColor(ContextCompat.getColor(this, R.color.color_777777))
        mBinding.tvModule.setTextColor(ContextCompat.getColor(this, R.color.black))
        mBinding.tvConfigs.setTextColor(ContextCompat.getColor(this, R.color.color_777777))
//        ModulesFragment().apply {
//            replaceFragment(this@MainActivity, R.id.fragment_container)
//        }
        mBinding.vpgMain.setCurrentItem(1, true)

    }

    fun openConfigs() {
        if (lastPosition == 2) return
        lastPosition = 2
        showNativeMain()
        mBinding.imgHome.setImageResource(R.drawable.ic_home_inactive)
        mBinding.imgModule.setImageResource(R.drawable.ic_module_inactive)
        mBinding.imgConfigs.setImageResource(R.drawable.ic_configs_active)
        mBinding.tvHome.setTextColor(ContextCompat.getColor(this, R.color.color_777777))
        mBinding.tvModule.setTextColor(ContextCompat.getColor(this, R.color.color_777777))
        mBinding.tvConfigs.setTextColor(ContextCompat.getColor(this, R.color.black))
        mBinding.vpgMain.setCurrentItem(2, true)

    }

    override fun onClickViews() {
        super.onClickViews()

        mBinding.linearHome.click {
            if (AdsManager.mInterMain != null && AdsManager.mInterMain?.isReady == true) {

            } else {
                openHome()
            }


        }
        mBinding.linearModule.click {
            if (AdsManager.mInterMain != null && AdsManager.mInterMain?.isReady == true) {

            } else {
                openModule()
            }
        }
        mBinding.linearConfigs.click {
            if (AdsManager.mInterMain != null && AdsManager.mInterMain?.isReady == true) {

            } else {
                openConfigs()
            }
        }
    }


    val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                showNativeAds(true)
                AppOpenManager.getInstance().enableAppResumeWithActivity(MainActivity::class.java)
                lifecycleScope.launch(Dispatchers.IO) {
                    delay(2000)
                    withContext(Dispatchers.Main) {
                        GlobalApp.isback = true
                    }
                }
                homeFragment.checkAccessibilityServiceActive()

            }
        }

    fun hideDemo(isFirst: Boolean) {
        mBinding.constraintTutor.isVisible = true
        mBinding.switchDisplayNotchTutor.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Log.d("chinh_dep_trai_kk_2", "initViews() called with: ")
                Routes.startPermissionActivity(this, resultLauncher)
            }
        }
        mBinding.constraintTutor.click {
            mBinding.constraintTutor.isVisible = false
        }
        if (!isFirst) {
            mBinding.constraintTutor.isVisible = false
        }
    }


    private fun initOnBackPressed() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                dialogExists?.setAds()
                dialogExists?.show()
            }
        })
    }

    private fun delayShowConsentDialog() {
        if (!com.tools.edge.dynamic.island.ads.RemoteConfigUtils.getOnShowDialogConsent()) {
            return
        }
        /**
         * Có đang check thiếu điều kiện không. A thấy nếu user click consent ở màn splash rồi thì vẫn vào đây thì phải
         * Và code hieejnt ại đang load fail -> 5s sau lại load lại đúng ko nhỉ
         */
        Handler().postDelayed({
            ITGTrackingHelper.logEvent(ITGTrackingHelper.LOAD_CONSENT_2, null)
            ITGAdConsent.loadAndShowConsent(true, object : IAdConsentCallBack {
                override fun getCurrentActivity(): Activity {
                    return this@MainActivity
                }

                override fun isDebug(): Boolean {
                    return BuildConfig.DEBUG
                }

                override fun isUnderAgeAd(): Boolean {
                    return false
                }

                override fun onNotUsingAdConsent() {
                    Log.v("Ynsuper", "onNotUsingAdConsent: ")
                    ITGTrackingHelper.logEvent(ITGTrackingHelper.NOT_USING_DISPLAY_CONSENT_2, null)
                    /**
                     * Lưu biến khi user ko nằm trong khu vực EU
                     */


                    prefs[AppConstants.KEY_IS_USER_GLOBAL] = true
                }

                override fun onConsentSuccess(canPersonalized: Boolean) {
                    Log.v("Ynsuper", "onConsentSuccess: $canPersonalized")
                    if (canPersonalized) {
                        /**
                         * Cần lưu biến khi user ấn agree vào SharedPreferenUtils
                         */
                        /**
                         * Cần lưu biến khi user ấn agree vào SharedPreferenUtils
                         */
                        /**
                         * Cần lưu biến khi user ấn agree vào SharedPreferenUtils
                         */
                        /**
                         * Cần lưu biến khi user ấn agree vào SharedPreferenUtils
                         */
                        logEvent(ITGTrackingHelper.AGREE_CONSENT_2, null)
                        prefs[AppConstants.KEY_CONFIRM_CONSENT] = true
                        Routes.startSplashActivity(this@MainActivity)
                    } else {
                        logEvent(ITGTrackingHelper.REFUSE_CONSENT_2, null)
                        ITGAdConsent.resetConsentDialog()
                    }
                }

                override fun onConsentError(formError: FormError) {
                    logEvent(ITGTrackingHelper.CONSENT_ERROR_2, null)
                    Log.v("Ynsuper", "onConsentError: " + formError.message)
                }

                override fun onConsentStatus(consentStatus: Int) {
                    Log.v("Ynsuper", "onConsentStatus: $consentStatus")/*if (consentStatus == 2) {
                                TdAdConsent.INSTANCE.showDialogConsent(this);
                                Log.v(TAG, "onConsentStatus: " + consentStatus);
                            }*/
                }

                override fun onRequestShowDialog() {
                    Log.v("Ynsuper", "onRequestShowDialog: ")
                    logEvent(ITGTrackingHelper.DISPLAY_CONSENT_2, null)
                }

                override fun testDeviceID(): String {
                    return "ED3576D8FCF2F8C52AD8E98B4CFA4005"
                }
            })
        }, 5000L)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("chinh_dep_trai_kk_des", "onDestroy: ")
    }
}
