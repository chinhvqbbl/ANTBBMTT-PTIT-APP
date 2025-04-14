package com.tools.edge.dynamic.island.ui.bases;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0017\u001a\u00020\u0018H&J\b\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u001aH\u0002J\b\u0010\u001c\u001a\u00020\u001aH\u0016J\b\u0010\u001d\u001a\u00020\u001aH\u0016J\b\u0010\u001e\u001a\u00020\u001aH\u0016J\u0010\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020!H\u0016J\u0012\u0010\"\u001a\u00020\u001a2\b\u0010#\u001a\u0004\u0018\u00010$H\u0014J\b\u0010%\u001a\u00020\u001aH\u0016J\b\u0010&\u001a\u00020\u001aH\u0014J\b\u0010\'\u001a\u00020\u001aH\u0016J\b\u0010(\u001a\u00020\u001aH\u0002J\b\u0010)\u001a\u00020\u001aH\u0016J\u0006\u0010*\u001a\u00020\u001aR\u001c\u0010\u0005\u001a\u00028\u0000X\u0086.\u00a2\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u000b\u001a\u00020\fX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016\u00a8\u0006+"}, d2 = {"Lcom/tools/edge/dynamic/island/ui/bases/BaseActivity;", "VB", "Landroidx/databinding/ViewDataBinding;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "mBinding", "getMBinding", "()Landroidx/databinding/ViewDataBinding;", "setMBinding", "(Landroidx/databinding/ViewDataBinding;)V", "Landroidx/databinding/ViewDataBinding;", "prefs", "Landroid/content/SharedPreferences;", "getPrefs", "()Landroid/content/SharedPreferences;", "setPrefs", "(Landroid/content/SharedPreferences;)V", "rateDialog", "Lcom/tools/edge/dynamic/island/ui/component/dialog/RateAppDialog;", "getRateDialog", "()Lcom/tools/edge/dynamic/island/ui/component/dialog/RateAppDialog;", "setRateDialog", "(Lcom/tools/edge/dynamic/island/ui/component/dialog/RateAppDialog;)V", "getLayoutActivity", "", "hideNavigationBar", "", "hideSystemUIBeloR", "initViews", "observerData", "onClickViews", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResizeViews", "onResume", "requestWindow", "setLocal", "setUpViews", "shareApp", "Dynamic-Island_v1.0.2_v102_04.14.2025_debug"})
public abstract class BaseActivity<VB extends androidx.databinding.ViewDataBinding> extends androidx.appcompat.app.AppCompatActivity {
    public VB mBinding;
    public android.content.SharedPreferences prefs;
    @org.jetbrains.annotations.Nullable
    private com.tools.edge.dynamic.island.ui.component.dialog.RateAppDialog rateDialog;
    
    public BaseActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final VB getMBinding() {
        return null;
    }
    
    public final void setMBinding(@org.jetbrains.annotations.NotNull
    VB p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.content.SharedPreferences getPrefs() {
        return null;
    }
    
    public final void setPrefs(@org.jetbrains.annotations.NotNull
    android.content.SharedPreferences p0) {
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    public void setUpViews() {
    }
    
    public abstract int getLayoutActivity();
    
    public void requestWindow() {
    }
    
    public void initViews() {
    }
    
    public void onResizeViews() {
    }
    
    public void onClickViews() {
    }
    
    public void observerData() {
    }
    
    private final void setLocal() {
    }
    
    @java.lang.Override
    protected void onResume() {
    }
    
    @java.lang.Override
    public void onConfigurationChanged(@org.jetbrains.annotations.NotNull
    android.content.res.Configuration newConfig) {
    }
    
    private final void hideNavigationBar() {
    }
    
    private final void hideSystemUIBeloR() {
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.tools.edge.dynamic.island.ui.component.dialog.RateAppDialog getRateDialog() {
        return null;
    }
    
    public final void setRateDialog(@org.jetbrains.annotations.Nullable
    com.tools.edge.dynamic.island.ui.component.dialog.RateAppDialog p0) {
    }
    
    public final void shareApp() {
    }
}