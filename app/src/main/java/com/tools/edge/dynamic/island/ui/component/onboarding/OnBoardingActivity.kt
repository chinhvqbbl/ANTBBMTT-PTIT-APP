package com.tools.edge.dynamic.island.ui.component.onboarding

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.bbl.module_ads.billing.AppPurchase


import com.tools.edge.dynamic.island.R
import com.tools.edge.dynamic.island.ads.AdsManager.nativeAdOnBoarding
import com.tools.edge.dynamic.island.ads.AdsManager.setPreLoadNativeCallback
import com.tools.edge.dynamic.island.ads.PreLoadNativeListener
import com.tools.edge.dynamic.island.app.AppConstants
import com.tools.edge.dynamic.island.databinding.ActivityOnboardingBinding
import com.tools.edge.dynamic.island.models.GuideModel
import com.tools.edge.dynamic.island.ui.bases.BaseActivity
import com.tools.edge.dynamic.island.ui.bases.ext.click
import com.tools.edge.dynamic.island.ui.bases.ext.goneView
import com.tools.edge.dynamic.island.ui.bases.ext.visibleView
import com.tools.edge.dynamic.island.ui.component.onboarding.adapter.OnBoardingAdapter
import com.tools.edge.dynamic.island.utils.EasyPreferences.set
import com.tools.edge.dynamic.island.utils.Routes
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import kotlin.math.abs

@AndroidEntryPoint
class OnBoardingActivity : BaseActivity<ActivityOnboardingBinding>(), PreLoadNativeListener {
    private var tutorialAdapter: OnBoardingAdapter? = null
    private var posViewPager = 0
    private var populateNativeAdView = false

    override fun getLayoutActivity(): Int = R.layout.activity_onboarding

    override fun initViews() {

        mBinding.tvGetStart.text = getString(R.string.next)
        tutorialAdapter = OnBoardingAdapter()
        mBinding.viewPager2.adapter = tutorialAdapter
        mBinding.viewPager2.clipToPadding = false
        mBinding.viewPager2.clipChildren = false
        mBinding.viewPager2.offscreenPageLimit = 4
        mBinding.viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_ALWAYS
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(100))
        compositePageTransformer.addTransformer { view, position ->
            val r = 1 - abs(position)
            view.scaleY = 0.8f + r * 0.2f
            val absPosition = abs(position)
            view.alpha = 1.0f - (1.0f - 0.3f) * absPosition
        }

        mBinding.viewPager2.setPageTransformer(compositePageTransformer)
        mBinding.viewPager2.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            @SuppressLint("InvalidAnalyticsName", "UseCompatLoadingForDrawables")
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> {
                        posViewPager = 0
                        mBinding.tvGetStart.text = getString(R.string.next)
                        mBinding.tvSkip.visibleView()
                        mBinding.view1.setImageResource(R.drawable.ic_view_select)
                        mBinding.view2.setImageResource(R.drawable.ic_view_un_select)
                        mBinding.view3.setImageResource(R.drawable.ic_view_un_select)

                        if (nativeAdOnBoarding != null) {
                            mBinding.frAds.visibleView()
                        } else {
                            mBinding.frAds.goneView()
                        }
                    }

                    1 -> {
                        posViewPager = 1
                        mBinding.tvGetStart.text = getString(R.string.next)
                        mBinding.tvSkip.visibleView()
                        mBinding.view1.setImageResource(R.drawable.ic_view_un_select)
                        mBinding.view2.setImageResource(R.drawable.ic_view_select)
                        mBinding.view3.setImageResource(R.drawable.ic_view_un_select)
                        mBinding.view4.setImageResource(R.drawable.ic_view_un_select)
                        mBinding.frAds.goneView()

                    }

                    2 -> {
                        posViewPager = 2
                        mBinding.tvGetStart.text = getString(R.string.next)
                        mBinding.tvSkip.visibleView()
                        mBinding.view1.setImageResource(R.drawable.ic_view_un_select)
                        mBinding.view2.setImageResource(R.drawable.ic_view_un_select)
                        mBinding.view3.setImageResource(R.drawable.ic_view_select)
                        mBinding.view4.setImageResource(R.drawable.ic_view_un_select)
                        mBinding.frAds.goneView()

                    }

                    3 -> {
                        posViewPager = 3
                        mBinding.tvGetStart.text = getString(R.string.start)
                        mBinding.tvSkip.goneView()
                        mBinding.view1.setImageResource(R.drawable.ic_view_un_select)
                        mBinding.view2.setImageResource(R.drawable.ic_view_un_select)
                        mBinding.view3.setImageResource(R.drawable.ic_view_un_select)
                        mBinding.view4.setImageResource(R.drawable.ic_view_select)

                        if (nativeAdOnBoarding != null) {
                            mBinding.frAds.visibleView()
                        } else {
                            mBinding.frAds.goneView()
                        }
                    }
                }
            }
        })

        getData()
        setPreLoadNativeCallback(this)
        showNativeOnBoardingAds()

    }

    override fun onClickViews() {
        mBinding.tvSkip.click {
            gotoNextScreen()
        }

        mBinding.tvGetStart.click {
            when (posViewPager) {

                3 -> {
                    gotoNextScreen()
                }

                else -> {
                    mBinding.viewPager2.currentItem = posViewPager + 1
                }
            }
        }
    }

    private fun showNativeOnBoardingAds() {
        if (nativeAdOnBoarding != null && !populateNativeAdView && !AppPurchase.getInstance().isPurchased) {
            Timber.e("initAdmob: ${nativeAdOnBoarding}")
            mBinding.frAds.visibleView()
            populateNativeAdView = true

        } else {
            mBinding.frAds.goneView()
            Timber.d(
                "LanguageActivity initAds nativeAdOnBoarding = ${nativeAdOnBoarding} - nativeAdLanguage = ${nativeAdOnBoarding}"
            )
        }

    }

    private fun gotoNextScreen() {

        prefs[AppConstants.KEY_FIRST_ON_BOARDING] = false
        Routes.startMainActivity(this)
        finish()

    }

    private fun getData() {
        val mHelpGuide: ArrayList<GuideModel> = ArrayList()
        mHelpGuide.add(
            GuideModel(
                R.drawable.ic_onboarding_1,
                R.string.app_name,
                R.string.txt_decorate_your_front_camera_support_you_with_useful_functions,
            )
        )
        mHelpGuide.add(
            GuideModel(
                R.drawable.ic_onboarding_2,
                R.string.txt_better_notifications,
                R.string.txt_real_time_updates_provide_the_latest_notification,
            )
        )
        mHelpGuide.add(
            GuideModel(
                R.drawable.ic_onboarding_3,
                R.string.txt_quick_ations,
                R.string.txt_easy_access_to_quick_action_provide_better_experience_for_your_phone,
            )
        )

        mHelpGuide.add(
            GuideModel(
                R.drawable.ic_onboarding_4,
                R.string.txt_easy_use,
                R.string.txt_easy_to_see_the_notifications_and_do_the_actions_on_dynamic_island_view,
            )
        )
        tutorialAdapter?.submitData(mHelpGuide as List<GuideModel>)
    }

    override fun onLoadNativeSuccess() {
        showNativeOnBoardingAds()
    }

    override fun onLoadNativeFail() {
        if (nativeAdOnBoarding != null) {
            mBinding.frAds.visibleView()
        } else {
            mBinding.frAds.goneView()
        }
    }

}