package com.tools.edge.dynamic.island.ads

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.bbl.module_ads.ads.wrapper.ApAdError
import com.bbl.module_ads.ads.wrapper.ApInterstitialAd
import com.bbl.module_ads.ads.wrapper.ApNativeAd

import com.tools.edge.dynamic.island.BuildConfig
import com.tools.edge.dynamic.island.R
import com.tools.edge.dynamic.island.ui.bases.ext.isNetwork

object AdsManager {

    @SuppressLint("StaticFieldLeak")
    var nativeAdLanguage: ApNativeAd? = null

    @SuppressLint("StaticFieldLeak")
    var nativeAdOnBoarding: ApNativeAd? = null

    @SuppressLint("StaticFieldLeak")
    var nativeAdPermission: ApNativeAd? = null

    @SuppressLint("StaticFieldLeak")
    var nativeHome: ApNativeAd? = null

    @SuppressLint("StaticFieldLeak")
    var nativeModules: ApNativeAd? = null


    @SuppressLint("StaticFieldLeak")
    var nativeExit: ApNativeAd? = null


    var preLoadNativeListener: PreLoadNativeListener? = null
    var mInterMain: ApInterstitialAd? = null
    var mInterModulesBack: ApInterstitialAd? = null
    fun setPreLoadNativeCallback(listener: PreLoadNativeListener) {
        preLoadNativeListener = listener
    }

    fun loadNativeLanguage(activity: Activity, isNewID: Boolean) {
        if (nativeAdLanguage == null) {

        }
    }


    fun loadNativeExit(activity: Activity) {
        if (nativeExit == null) {

        }
    }

    fun loadInterMain(context: Activity) {
        if (RemoteConfigUtils.getOnInterMain() && mInterMain == null && isNetwork(context)) {

        }
    }


    fun loadInterModulesBack(context: Activity) {
        if (RemoteConfigUtils.getOnInterModulesBack() && AdsManager.mInterModulesBack == null && isNetwork(
                context
            )
        ) {

        }
    }

    fun loadNativeModules(activity: Activity) {
//        if (nativeModules == null) {
//            if (RemoteConfigUtils.getOnNativeModules()) {
//                TdAd.getInstance().loadNativeAdResultCallback(activity,
//                    BuildConfig.native_modules,
//                    R.layout.layout_native_language,
//                    object : TdAdCallback() {
//                        override fun onNativeAdLoaded(nativeAd: ApNativeAd) {
//                            super.onNativeAdLoaded(nativeAd)
//                            nativeModules = nativeAd
//                            if (preLoadNativeListener != null) {
//                                preLoadNativeListener?.onLoadNativeSuccess()
//                            }
//                        }
//
//                        override fun onAdFailedToLoad(adError: ApAdError?) {
//                            super.onAdFailedToLoad(adError)
//                            if (preLoadNativeListener != null) {
//                                preLoadNativeListener?.onLoadNativeFail()
//                            }
//                        }
//                    })
//            }
//        }
    }


    fun loadNativeHome(activity: Activity) {
        if (nativeHome == null) {

        }
    }

    fun loadNativeOnBoarding(activity: Activity, isNewID: Boolean) {
        if (nativeAdOnBoarding == null) {
            if (RemoteConfigUtils.getOnNativeOnBoarding() && activity.isNetwork()) {

            }
        }
    }

    fun loadNativePermission(activity: Activity) {
        if (nativeAdPermission == null) {
            if (RemoteConfigUtils.getOnNativePermission() && activity.isNetwork()) {

            }
        }
    }

    fun loadBanner(activity: AppCompatActivity, id: String, frAds: FrameLayout, bool: Boolean) {
        if (isNetwork(activity) && bool) {

        } else {
            frAds.removeAllViews()
        }
    }

}
