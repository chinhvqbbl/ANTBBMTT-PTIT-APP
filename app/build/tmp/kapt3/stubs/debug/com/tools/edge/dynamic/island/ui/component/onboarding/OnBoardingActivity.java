package com.tools.edge.dynamic.island.ui.component.onboarding;

@dagger.hilt.android.AndroidEntryPoint
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0002J\b\u0010\r\u001a\u00020\bH\u0016J\b\u0010\u000e\u001a\u00020\fH\u0002J\b\u0010\u000f\u001a\u00020\fH\u0016J\b\u0010\u0010\u001a\u00020\fH\u0016J\b\u0010\u0011\u001a\u00020\fH\u0016J\b\u0010\u0012\u001a\u00020\fH\u0016J\b\u0010\u0013\u001a\u00020\fH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/tools/edge/dynamic/island/ui/component/onboarding/OnBoardingActivity;", "Lcom/tools/edge/dynamic/island/ui/bases/BaseActivity;", "Lcom/tools/edge/dynamic/island/databinding/ActivityOnboardingBinding;", "Lcom/tools/edge/dynamic/island/ads/PreLoadNativeListener;", "()V", "populateNativeAdView", "", "posViewPager", "", "tutorialAdapter", "Lcom/tools/edge/dynamic/island/ui/component/onboarding/adapter/OnBoardingAdapter;", "getData", "", "getLayoutActivity", "gotoNextScreen", "initViews", "onClickViews", "onLoadNativeFail", "onLoadNativeSuccess", "showNativeOnBoardingAds", "Dynamic-Island_v1.0.2_v102_04.14.2025_debug"})
public final class OnBoardingActivity extends com.tools.edge.dynamic.island.ui.bases.BaseActivity<com.tools.edge.dynamic.island.databinding.ActivityOnboardingBinding> implements com.tools.edge.dynamic.island.ads.PreLoadNativeListener {
    @org.jetbrains.annotations.Nullable
    private com.tools.edge.dynamic.island.ui.component.onboarding.adapter.OnBoardingAdapter tutorialAdapter;
    private int posViewPager = 0;
    private boolean populateNativeAdView = false;
    
    public OnBoardingActivity() {
        super();
    }
    
    @java.lang.Override
    public int getLayoutActivity() {
        return 0;
    }
    
    @java.lang.Override
    public void initViews() {
    }
    
    @java.lang.Override
    public void onClickViews() {
    }
    
    private final void showNativeOnBoardingAds() {
    }
    
    private final void gotoNextScreen() {
    }
    
    private final void getData() {
    }
    
    @java.lang.Override
    public void onLoadNativeSuccess() {
    }
    
    @java.lang.Override
    public void onLoadNativeFail() {
    }
}