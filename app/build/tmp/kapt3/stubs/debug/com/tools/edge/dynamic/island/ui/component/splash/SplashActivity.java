package com.tools.edge.dynamic.island.ui.component.splash;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u0000 \'2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u00032\u00020\u0004:\u0001\'B\u0005\u00a2\u0006\u0002\u0010\u0005J\b\u0010\f\u001a\u00020\rH\u0002J\b\u0010\u000e\u001a\u00020\rH\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\b\u0010\u0014\u001a\u00020\rH\u0016J\b\u0010\u0015\u001a\u00020\u0007H\u0016J\b\u0010\u0016\u001a\u00020\u0007H\u0016J\b\u0010\u0017\u001a\u00020\rH\u0016J\b\u0010\u0018\u001a\u00020\rH\u0002J\b\u0010\u0019\u001a\u00020\rH\u0002J\b\u0010\u001a\u001a\u00020\rH\u0016J\u0010\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u0012H\u0016J\u0010\u0010 \u001a\u00020\r2\u0006\u0010!\u001a\u00020\u0007H\u0016J\b\u0010\"\u001a\u00020\rH\u0014J\b\u0010#\u001a\u00020\rH\u0016J\b\u0010$\u001a\u00020\rH\u0014J\b\u0010%\u001a\u00020\rH\u0016J\b\u0010&\u001a\u00020\rH\u0014R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/tools/edge/dynamic/island/ui/component/splash/SplashActivity;", "Lcom/tools/edge/dynamic/island/ui/bases/BaseActivity;", "Lcom/tools/edge/dynamic/island/databinding/ActivitySplashBinding;", "Lcom/tools/edge/dynamic/island/ads/RemoteConfigUtils$Listener;", "Lcom/itg/iaumodule/IAdConsentCallBack;", "()V", "canPersonalized", "", "getConfigSuccess", "isRemoteLoading", "selectLanguage", "selectOnBoarding", "checkNeedToLoadConsent", "", "checkRemoteConfigResult", "getCurrentActivity", "Landroid/app/Activity;", "getLayoutActivity", "", "handleClickConsent", "initViews", "isDebug", "isUnderAgeAd", "loadSuccess", "loadingRemoteConfig", "moveActivity", "onBackPressed", "onConsentError", "formError", "Lcom/google/android/ump/FormError;", "onConsentStatus", "consentStatus", "onConsentSuccess", "b", "onDestroy", "onNotUsingAdConsent", "onPause", "onRequestShowDialog", "onResume", "Companion", "Dynamic-Island_v1.0.2_v102_04.21.2025_debug"})
public final class SplashActivity extends com.tools.edge.dynamic.island.ui.bases.BaseActivity<com.tools.edge.dynamic.island.databinding.ActivitySplashBinding> implements com.tools.edge.dynamic.island.ads.RemoteConfigUtils.Listener, com.itg.iaumodule.IAdConsentCallBack {
    private boolean getConfigSuccess = false;
    private boolean selectLanguage = false;
    private boolean selectOnBoarding = false;
    private boolean canPersonalized = true;
    private boolean isRemoteLoading = false;
    private static boolean checkMoveToLanguages = false;
    @org.jetbrains.annotations.NotNull()
    public static final com.tools.edge.dynamic.island.ui.component.splash.SplashActivity.Companion Companion = null;
    
    public SplashActivity() {
        super();
    }
    
    @java.lang.Override()
    public int getLayoutActivity() {
        return 0;
    }
    
    @java.lang.Override()
    public void initViews() {
    }
    
    private final void checkNeedToLoadConsent() {
    }
    
    private final void loadingRemoteConfig() {
    }
    
    private final void checkRemoteConfigResult() {
    }
    
    private final void moveActivity() {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    public void loadSuccess() {
    }
    
    @java.lang.Override()
    public void onBackPressed() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.app.Activity getCurrentActivity() {
        return null;
    }
    
    @java.lang.Override()
    public boolean isDebug() {
        return false;
    }
    
    @java.lang.Override()
    public boolean isUnderAgeAd() {
        return false;
    }
    
    @java.lang.Override()
    public void onConsentError(@org.jetbrains.annotations.NotNull()
    com.google.android.ump.FormError formError) {
    }
    
    @java.lang.Override()
    public void onConsentStatus(int consentStatus) {
    }
    
    @java.lang.Override()
    public void onConsentSuccess(boolean b) {
    }
    
    @java.lang.Override()
    public void onNotUsingAdConsent() {
    }
    
    @java.lang.Override()
    public void onRequestShowDialog() {
    }
    
    private final void handleClickConsent(boolean canPersonalized) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    @java.lang.Override()
    protected void onPause() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String testDeviceID() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/tools/edge/dynamic/island/ui/component/splash/SplashActivity$Companion;", "", "()V", "checkMoveToLanguages", "", "getCheckMoveToLanguages", "()Z", "setCheckMoveToLanguages", "(Z)V", "Dynamic-Island_v1.0.2_v102_04.21.2025_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final boolean getCheckMoveToLanguages() {
            return false;
        }
        
        public final void setCheckMoveToLanguages(boolean p0) {
        }
    }
}