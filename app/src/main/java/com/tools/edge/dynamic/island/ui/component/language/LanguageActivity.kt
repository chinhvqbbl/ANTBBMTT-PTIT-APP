package com.tools.edge.dynamic.island.ui.component.language

import android.content.res.Configuration
import android.view.View

import com.tools.edge.dynamic.island.R
import com.tools.edge.dynamic.island.ads.AdsManager
import com.tools.edge.dynamic.island.ads.AdsManager.nativeAdLanguage
import com.tools.edge.dynamic.island.ads.AdsManager.setPreLoadNativeCallback
import com.tools.edge.dynamic.island.ads.PreLoadNativeListener
import com.tools.edge.dynamic.island.app.AppConstants
import com.tools.edge.dynamic.island.app.GlobalApp
import com.tools.edge.dynamic.island.databinding.ActivityLanguageBinding
import com.tools.edge.dynamic.island.ui.bases.BaseActivity
import com.tools.edge.dynamic.island.ui.bases.ext.goneView
import com.tools.edge.dynamic.island.ui.bases.ext.showToastById
import com.tools.edge.dynamic.island.ui.bases.ext.visibleView
import com.tools.edge.dynamic.island.ui.component.service.ITGAccessibilityService
import com.tools.edge.dynamic.island.ui.component.setting.SettingActivity
import com.tools.edge.dynamic.island.ui.component.utils.Utils
import com.tools.edge.dynamic.island.utils.EasyPreferences.get
import com.tools.edge.dynamic.island.utils.EasyPreferences.set
import com.tools.edge.dynamic.island.utils.Routes
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import java.util.Locale

@AndroidEntryPoint
class LanguageActivity : BaseActivity<ActivityLanguageBinding>(),
    PreLoadNativeListener {
    private var adapter: LanguageAdapter? = null
    private var model: LanguageModel = LanguageModel()
    private var populateNativeAdView = false
    private var isFromSetting = false

    override fun getLayoutActivity() = R.layout.activity_language

    override fun initViews() {
        super.initViews()
        GlobalApp.isback = true
        if (intent.getStringExtra(AppConstants.KEY_TRACKING_SCREEN_FROM) != null) {
            if (intent.getStringExtra(AppConstants.KEY_TRACKING_SCREEN_FROM) == SettingActivity::class.java.simpleName) {
                isFromSetting = true
                mBinding.relayAds.removeAllViews()
                mBinding.ivDone.visibleView()
            }
        }

        adapter = LanguageAdapter(this, onClickItemLanguage = {
            adapter?.setSelectLanguage(it)
            model = it
            mBinding.ivDone.visibleView()
        })
        mBinding.rclLanguage.adapter = adapter

        setLanguageDefault()
        AdsManager.loadNativePermission(this)
        AdsManager.loadNativeHome(this)
        setPreLoadNativeCallback(this)
        showNativeLanguage()

        if (prefs.getBoolean(AppConstants.keyCheckNotification, false) && prefs.getBoolean(AppConstants.keyCheckAccessibility, false)) {
            if (Utils.isServiceRunning(
                    this, ITGAccessibilityService::class.java.name
                )
            ) {
                ITGAccessibilityService.getInstance().statusBarParentView.visibility = View.VISIBLE
                ITGAccessibilityService.getInstance().onReloadDynamic()
            }
        }
    }

    override fun onClickViews() {
        super.onClickViews()
        mBinding.ivDone.setOnClickListener {
            if (model.languageName != "") {
                prefs[AppConstants.KEY_LANGUAGE] = model.isoLanguage
                setLocale()

                if (isFromSetting) {
                    gotoActivity()
                } else {
                    Routes.startOnBoardingActivity(this)
                }
                finishAffinity()

            } else {
                showToastById(R.string.please_select_language)
            }
        }
    }

    private fun gotoActivity() {
        Routes.startMainActivity(this)
    }

    private fun setLocale() {
        val language = prefs.getString(AppConstants.KEY_LANGUAGE, "en")

        if (language == "") {
            val config = Configuration()
            val locale = Locale.getDefault()
            Locale.setDefault(locale)
            config.locale = locale
            resources.updateConfiguration(config, resources.displayMetrics)
        } else {
            if (language.equals("", ignoreCase = true)) return
            val locale = Locale(language)
            Locale.setDefault(locale)
            val config = Configuration()
            config.locale = locale
            resources.updateConfiguration(config, resources.displayMetrics)
        }

    }

    private fun setLanguageDefault() {
        val listLanguages: ArrayList<LanguageModel> = ArrayList()

        val key: String? = prefs[AppConstants.KEY_LANGUAGE, "en"]

        listLanguages.add(LanguageModel("English", "en", false, R.drawable.ic_english))
        listLanguages.add(LanguageModel("Hindi", "hi", false, R.drawable.ic_hindi))
        listLanguages.add(LanguageModel("Spanish", "es", false, R.drawable.ic_spanish))
        listLanguages.add(LanguageModel("French", "fr", false, R.drawable.ic_france))
        listLanguages.add(LanguageModel("Portuguese", "pt", false, R.drawable.ic_portugal))
        listLanguages.add(LanguageModel("Korean", "ko", false, R.drawable.ic_korean))

        if (GlobalApp.instance.getLanguage() != null && !listLanguages.contains(GlobalApp.instance.getLanguage())) {
            listLanguages.remove(listLanguages[listLanguages.size - 1])
            val modelLanguage = GlobalApp.instance.getLanguage()
            if (modelLanguage != null) {
                listLanguages.add(0, modelLanguage)
            }

        }

        for (i in listLanguages.indices) {
            if (key == listLanguages[i].isoLanguage) {
                val data = listLanguages[i]
                data.isCheck = false
                listLanguages.remove(listLanguages[i])
                listLanguages.add(0, data)
//                model = data
                break
            }
        }

        adapter?.submitData(listLanguages)
    }

    private fun showNativeLanguage() {
        if (nativeAdLanguage != null) {
            Timber.e("initAdmob: $nativeAdLanguage")
            populateNativeAdView = true

        } else {
            mBinding.frAds.goneView()
            Timber.d(
                "LanguageActivity initAds nativeAdViewLanguage = $nativeAdLanguage - nativeAdLanguage = $nativeAdLanguage"
            )
        }
    }

    override fun onLoadNativeSuccess() {
        showNativeLanguage()
    }

    override fun onLoadNativeFail() {
        if (nativeAdLanguage != null) {
            mBinding.frAds.visibleView()
        } else {
            mBinding.frAds.goneView()
        }
    }
}