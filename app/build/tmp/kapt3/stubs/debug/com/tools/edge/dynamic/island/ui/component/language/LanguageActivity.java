package com.tools.edge.dynamic.island.ui.component.language;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\rH\u0016J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\b\u0010\u0011\u001a\u00020\u000fH\u0016J\b\u0010\u0012\u001a\u00020\u000fH\u0016J\b\u0010\u0013\u001a\u00020\u000fH\u0016J\b\u0010\u0014\u001a\u00020\u000fH\u0002J\b\u0010\u0015\u001a\u00020\u000fH\u0002J\b\u0010\u0016\u001a\u00020\u000fH\u0002R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/tools/edge/dynamic/island/ui/component/language/LanguageActivity;", "Lcom/tools/edge/dynamic/island/ui/bases/BaseActivity;", "Lcom/tools/edge/dynamic/island/databinding/ActivityLanguageBinding;", "Lcom/tools/edge/dynamic/island/ads/PreLoadNativeListener;", "()V", "adapter", "Lcom/tools/edge/dynamic/island/ui/component/language/LanguageAdapter;", "isFromSetting", "", "model", "Lcom/tools/edge/dynamic/island/ui/component/language/LanguageModel;", "populateNativeAdView", "getLayoutActivity", "", "gotoActivity", "", "initViews", "onClickViews", "onLoadNativeFail", "onLoadNativeSuccess", "setLanguageDefault", "setLocale", "showNativeLanguage", "Dynamic-Island_v1.0.2_v102_05.03.2025_debug"})
public final class LanguageActivity extends com.tools.edge.dynamic.island.ui.bases.BaseActivity<com.tools.edge.dynamic.island.databinding.ActivityLanguageBinding> implements com.tools.edge.dynamic.island.ads.PreLoadNativeListener {
    @org.jetbrains.annotations.Nullable()
    private com.tools.edge.dynamic.island.ui.component.language.LanguageAdapter adapter;
    @org.jetbrains.annotations.NotNull()
    private com.tools.edge.dynamic.island.ui.component.language.LanguageModel model;
    private boolean populateNativeAdView = false;
    private boolean isFromSetting = false;
    
    public LanguageActivity() {
        super();
    }
    
    @java.lang.Override()
    public int getLayoutActivity() {
        return 0;
    }
    
    @java.lang.Override()
    public void initViews() {
    }
    
    @java.lang.Override()
    public void onClickViews() {
    }
    
    private final void gotoActivity() {
    }
    
    private final void setLocale() {
    }
    
    private final void setLanguageDefault() {
    }
    
    private final void showNativeLanguage() {
    }
    
    @java.lang.Override()
    public void onLoadNativeSuccess() {
    }
    
    @java.lang.Override()
    public void onLoadNativeFail() {
    }
}