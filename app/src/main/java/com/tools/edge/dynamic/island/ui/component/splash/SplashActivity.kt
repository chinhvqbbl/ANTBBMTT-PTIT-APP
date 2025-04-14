package com.tools.edge.dynamic.island.ui.component.splash

import android.app.Activity
import android.os.CountDownTimer
import android.util.Log



import com.google.android.gms.ads.MobileAds
import com.google.android.ump.ConsentInformation
import com.google.android.ump.FormError
import com.itg.iaumodule.IAdConsentCallBack
import com.itg.iaumodule.ITGAdConsent
import com.itg.iaumodule.ITGAdConsent.resetConsentDialog
import com.tools.edge.dynamic.island.BuildConfig
import com.tools.edge.dynamic.island.R
import com.tools.edge.dynamic.island.ads.AdsManager
import com.tools.edge.dynamic.island.ads.AdsManager.loadNativeLanguage
import com.tools.edge.dynamic.island.ads.AdsManager.loadNativeOnBoarding
import com.tools.edge.dynamic.island.ads.RemoteConfigUtils
import com.tools.edge.dynamic.island.app.AppConstants
import com.tools.edge.dynamic.island.app.AppConstants.KEY_CONFIRM_CONSENT
import com.tools.edge.dynamic.island.app.AppConstants.KEY_FIRST_ON_BOARDING
import com.tools.edge.dynamic.island.app.AppConstants.KEY_IS_USER_GLOBAL
import com.tools.edge.dynamic.island.app.AppConstants.KEY_SELECT_LANGUAGE
import com.tools.edge.dynamic.island.app.GlobalApp.Companion.isNextLanguage
import com.tools.edge.dynamic.island.databinding.ActivitySplashBinding
import com.tools.edge.dynamic.island.ui.bases.BaseActivity
import com.tools.edge.dynamic.island.ui.bases.ext.isNetwork
import com.tools.edge.dynamic.island.utils.EasyPreferences.get
import com.tools.edge.dynamic.island.utils.EasyPreferences.set
import com.tools.edge.dynamic.island.utils.ITGTrackingHelper
import com.tools.edge.dynamic.island.utils.ITGTrackingHelper.logEvent
import com.tools.edge.dynamic.island.utils.Routes
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivitySplashBinding>(), RemoteConfigUtils.Listener,
    IAdConsentCallBack {
    private var getConfigSuccess = false
    private var selectLanguage = false
    private var selectOnBoarding = false
    private var canPersonalized = true
    private var isRemoteLoading = false

    companion object {
        var checkMoveToLanguages: Boolean = false
    }

    override fun getLayoutActivity() = R.layout.activity_splash

    override fun initViews() {
        super.initViews()
        MobileAds.initialize(this) {}
        selectLanguage = prefs[KEY_SELECT_LANGUAGE, false] == true
        selectOnBoarding = prefs[KEY_FIRST_ON_BOARDING, false] == true
        RemoteConfigUtils.init(this)
        checkMoveToLanguages = false

        if (prefs[KEY_CONFIRM_CONSENT, false] == false && prefs[KEY_IS_USER_GLOBAL, false] == false && isNetwork(
                this
            )
        ) {
            checkNeedToLoadConsent();
        } else {
            loadingRemoteConfig();
        }
    }

    private fun checkNeedToLoadConsent() {
        logEvent(ITGTrackingHelper.LOAD_CONSENT_1, null)
        ITGAdConsent.loadAndShowConsent(true, this)
    }

    private fun loadingRemoteConfig() {
        if (isRemoteLoading) return
        isRemoteLoading = true

        object : CountDownTimer(AppConstants.DEFAULT_TIME_SPLASH, 100) {
            override fun onTick(millisUntilFinished: Long) {
                Log.e("SplashActivity", " loadingRemoteConfig onTick == $millisUntilFinished")
                if (getConfigSuccess && millisUntilFinished < AppConstants.DEFAULT_LIMIT_TIME_SPLASH) {
                    checkRemoteConfigResult()
                    cancel()
                }
            }

            override fun onFinish() {
                if (!getConfigSuccess) {
                    checkRemoteConfigResult()
                }
            }
        }.start()
    }


    private fun checkRemoteConfigResult() {
        // check if not select language => show language screen
        if (!selectLanguage) {
            loadNativeLanguage(this, true)

        } else {
            loadNativeLanguage(this, false)
        }

        // check if not select onBoarding => show onBoarding screen
        if (!selectOnBoarding) {
            AdsManager.loadNativeOnBoarding(this, true)
        } else {
            AdsManager.loadNativeOnBoarding(this, false)
        }



            moveActivity()
        
    }


    private fun moveActivity() {
        if (!checkMoveToLanguages) {
            checkMoveToLanguages = true
            Routes.startLanguageActivity(this, null)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
       
    }


    override fun loadSuccess() {
        getConfigSuccess = true
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

    override fun getCurrentActivity(): Activity {
        return this@SplashActivity
    }

    override fun isDebug(): Boolean {
        Log.e("Ynsuper", "isDebug: " + BuildConfig.DEBUG)
        return BuildConfig.DEBUG
    }

    override fun isUnderAgeAd(): Boolean {
        return false
    }

    override fun onConsentError(formError: FormError) {
        canPersonalized = true
        logEvent(ITGTrackingHelper.CONSENT_ERROR_1, null)
        loadingRemoteConfig()
        Log.v("Ynsuper", "onConsentError: " + formError.message)
    }

    override fun onConsentStatus(consentStatus: Int) {
        canPersonalized = consentStatus != ConsentInformation.ConsentStatus.REQUIRED
        Log.v("Ynsuper", "onConsentStatus: $consentStatus canPersonalized $canPersonalized")
        /*if (consentStatus == ConsentInformation.ConsentStatus.REQUIRED) {
            ITGTrackingHelper.INSTANCE.logEvent(ITGTrackingHelper.DISPLAY_CONSENT_1, null);
        } else {
            ITGTrackingHelper.INSTANCE.logEvent(ITGTrackingHelper.NOT_REQUIRE_DISPLAY_CONSENT_1, null);
        }*/
    }

    override fun onConsentSuccess(b: Boolean) {
        canPersonalized = b
        handleClickConsent(canPersonalized)
        Log.v("Ynsuper", "onConsentSuccess: $canPersonalized")
    }

    override fun onNotUsingAdConsent() {
        Log.v("Ynsuper", "onNotUsingAdConsent:")
        logEvent(ITGTrackingHelper.NOT_USING_DISPLAY_CONSENT_1, null)
        /**
         * Lưu biến khi user ko nằm trong khu vực EU
         */
        prefs[KEY_IS_USER_GLOBAL] = true

        canPersonalized = true
        loadingRemoteConfig()
    }

    override fun onRequestShowDialog() {
        logEvent(ITGTrackingHelper.DISPLAY_CONSENT_1, null)
        Log.v("Ynsuper", "onRequestShowDialog:")
    }

    private fun handleClickConsent(canPersonalized: Boolean) {
        if (canPersonalized) {
            logEvent(ITGTrackingHelper.AGREE_CONSENT_1, null)
            prefs[KEY_CONFIRM_CONSENT] = true
        } else {
            ITGAdConsent.resetConsentDialog()
            logEvent(ITGTrackingHelper.REFUSE_CONSENT_1, null)
        }
        loadingRemoteConfig()
    }

    override fun onDestroy() {
        super.onDestroy()
        mBinding.videoLayout.onDestroyVideoLayout()

    }

    override fun onPause() {
        super.onPause()
        mBinding.videoLayout.onPauseVideoLayout()
    }
}