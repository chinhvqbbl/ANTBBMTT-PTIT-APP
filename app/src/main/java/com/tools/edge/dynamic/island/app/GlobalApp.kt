package com.tools.edge.dynamic.island.app

import android.annotation.SuppressLint
import android.content.res.Resources
import android.os.Build
import com.bbl.module_ads.admob.Admob
import com.bbl.module_ads.admob.AppOpenManager
import com.bbl.module_ads.ads.BBLAd
import com.bbl.module_ads.application.AdsMultiDexApplication
import com.bbl.module_ads.applovin.AppLovin
import com.bbl.module_ads.applovin.AppOpenMax
import com.bbl.module_ads.config.AdjustConfig
import com.bbl.module_ads.config.BBLAdConfig
import com.microsoft.clarity.Clarity
import com.microsoft.clarity.ClarityConfig

import com.tools.edge.dynamic.island.BuildConfig
import com.tools.edge.dynamic.island.R
import com.tools.edge.dynamic.island.ui.component.language.LanguageActivity
import com.tools.edge.dynamic.island.ui.component.language.LanguageModel
import com.tools.edge.dynamic.island.ui.component.onboarding.OnBoardingActivity
import com.tools.edge.dynamic.island.ui.component.permision.PermissionActivity
import com.tools.edge.dynamic.island.ui.component.setting.SettingActivity
import com.tools.edge.dynamic.island.ui.component.splash.SplashActivity
import com.tools.edge.dynamic.island.utils.SharePreferenceUtil
import com.tools.edge.dynamic.island.utils.SystemUtil
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class GlobalApp : AdsMultiDexApplication() {
    var isAccessibilityServiceRunning: Boolean = false

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: GlobalApp

        var isShowAds = false
        var isBackPermission = false
        var isNextLanguage = true
        var isback: Boolean? = true
    }

    fun setAccessibilityActive(isActive: Boolean) {
        isAccessibilityServiceRunning = isActive
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        val config = ClarityConfig("pojq6jjuw5")
        Clarity.initialize(applicationContext, config)
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        initAds()
        SharePreferenceUtil.initializeInstance(this)
    }

    private fun initAds() {
        val environment =
            if (BuildConfig.DEBUG) BBLAdConfig.ENVIRONMENT_DEVELOP else BBLAdConfig.ENVIRONMENT_PRODUCTION
        bblAdConfig = BBLAdConfig(this, BBLAdConfig.PROVIDER_ADMOB, environment)

        // Optional: setup Adjust event
        val adjustConfig = AdjustConfig(true, resources.getString(R.string.adjust_token))
        bblAdConfig.adjustConfig = adjustConfig
        bblAdConfig.facebookClientToken = resources.getString(R.string.facebook_client_token)

        // Optional: setup Appsflyer event
//        AppsflyerConfig appsflyerConfig = new AppsflyerConfig(true,APPSFLYER_TOKEN);
//        aperoAdConfig.setAppsflyerConfig(appsflyerConfig);

        // Optional: enable ads resume
        bblAdConfig.idAdResume = BuildConfig.admob_open_resume
        bblAdConfig.intervalInterstitialAd = 35
        BBLAd.getInstance().init(this, bblAdConfig, false)

        // Auto disable ad resume after user click ads and back to app
        Admob.getInstance().setDisableAdResumeWhenClickAds(true)
        AppLovin.getInstance().setDisableAdResumeWhenClickAds(true)
        // If true -> onNextAction() is called right after Ad Interstitial showed
        Admob.getInstance().setOpenActivityAfterShowInterAds(true)



    }

    fun getLanguage(): LanguageModel? {
        var languageModel: LanguageModel? = null
        val lang = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Resources.getSystem().configuration.locales[0].language
        } else {
            Resources.getSystem().configuration.locale.language
        }
        val key = if (!SystemUtil.languageApp.contains(lang)) {
            ""
        } else {
            lang
        }
        for (model in getListLanguageApp()) {
            if (key == model?.isoLanguage) {
                languageModel = model
                break
            }
        }
        return languageModel
    }

    private fun getListLanguageApp(): ArrayList<LanguageModel?> {
        val lists: ArrayList<LanguageModel?> = arrayListOf()
        lists.add(LanguageModel("Hindi", "hi", false, R.drawable.ic_hindi))
        lists.add(LanguageModel("Spanish", "es", false, R.drawable.ic_spanish))
        lists.add(LanguageModel("Croatian", "hr", false, R.drawable.ic_croatia))
        lists.add(LanguageModel("Czech", "cs", false, R.drawable.ic_czech_republic))
        lists.add(LanguageModel("Dutch", "nl", false, R.drawable.ic_dutch))
        lists.add(LanguageModel("English", "en", false, R.drawable.ic_english))
        lists.add(LanguageModel("Filipino", "fil", false, R.drawable.ic_filipino))
        lists.add(LanguageModel("French", "fr", false, R.drawable.ic_france))
        lists.add(LanguageModel("German", "de", false, R.drawable.ic_german))
        lists.add(LanguageModel("Indonesian", "in", false, R.drawable.ic_indonesian))
        lists.add(LanguageModel("Italian", "it", false, R.drawable.ic_italian))
        lists.add(LanguageModel("Japanese", "ja", false, R.drawable.ic_japanese))
        lists.add(LanguageModel("Korean", "ko", false, R.drawable.ic_korean))
        lists.add(LanguageModel("Malay", "ms", false, R.drawable.ic_malay))
        lists.add(LanguageModel("Polish", "pl", false, R.drawable.ic_polish))
        lists.add(LanguageModel("Portuguese", "pt", false, R.drawable.ic_portugal))
        lists.add(LanguageModel("Russian", "ru", false, R.drawable.ic_russian))
        lists.add(LanguageModel("Serbian", "sr", false, R.drawable.ic_serbian))
        lists.add(LanguageModel("Swedish", "sv", false, R.drawable.ic_swedish))
        lists.add(LanguageModel("Turkish", "tr", false, R.drawable.ic_turkish))
        lists.add(LanguageModel("Vietnamese", "vi", false, R.drawable.ic_vietnamese))
        lists.add(LanguageModel("China", "zh", false, R.drawable.ic_china))
        return lists
    }
}