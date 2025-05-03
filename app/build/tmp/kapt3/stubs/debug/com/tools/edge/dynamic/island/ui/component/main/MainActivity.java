package com.tools.edge.dynamic.island.ui.component.main;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0011\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010(\u001a\u00020)H\u0002J\b\u0010*\u001a\u00020\u0012H\u0016J\u000e\u0010+\u001a\u00020)2\u0006\u0010,\u001a\u00020\u000fJ\b\u0010-\u001a\u00020)H\u0002J\b\u0010.\u001a\u00020)H\u0016J\b\u0010/\u001a\u00020)H\u0002J\b\u00100\u001a\u00020)H\u0016J\b\u00101\u001a\u00020)H\u0014J\b\u00102\u001a\u00020)H\u0014J\b\u00103\u001a\u00020)H\u0014J\u0006\u00104\u001a\u00020)J\u0006\u00105\u001a\u00020)J\u0006\u00106\u001a\u00020)J\u000e\u00107\u001a\u00020)2\u0006\u00108\u001a\u00020\u000fJ\b\u00109\u001a\u00020)H\u0002R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000fX\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u000e\u0010!\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001f\u0010\"\u001a\u0010\u0012\f\u0012\n %*\u0004\u0018\u00010$0$0#\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'\u00a8\u0006:"}, d2 = {"Lcom/tools/edge/dynamic/island/ui/component/main/MainActivity;", "Lcom/tools/edge/dynamic/island/ui/bases/BaseActivity;", "Lcom/tools/edge/dynamic/island/databinding/ActivityMainBinding;", "()V", "dialogExists", "Lcom/tools/edge/dynamic/island/ui/component/dialog/ExitDialog;", "getDialogExists", "()Lcom/tools/edge/dynamic/island/ui/component/dialog/ExitDialog;", "setDialogExists", "(Lcom/tools/edge/dynamic/island/ui/component/dialog/ExitDialog;)V", "homeFragment", "Lcom/tools/edge/dynamic/island/ui/component/main/home/HomeFragment;", "getHomeFragment", "()Lcom/tools/edge/dynamic/island/ui/component/main/home/HomeFragment;", "isFirstStart", "", "()Z", "lastPosition", "", "getLastPosition", "()I", "setLastPosition", "(I)V", "mainAdapter", "Lcom/tools/edge/dynamic/island/ui/component/main/adpter/MainAdapter;", "getMainAdapter", "()Lcom/tools/edge/dynamic/island/ui/component/main/adpter/MainAdapter;", "setMainAdapter", "(Lcom/tools/edge/dynamic/island/ui/component/main/adpter/MainAdapter;)V", "modulesFragment", "Lcom/tools/edge/dynamic/island/ui/component/main/modules/ModulesFragment;", "getModulesFragment", "()Lcom/tools/edge/dynamic/island/ui/component/main/modules/ModulesFragment;", "populateNative", "resultLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "getResultLauncher", "()Landroidx/activity/result/ActivityResultLauncher;", "delayShowConsentDialog", "", "getLayoutActivity", "hideDemo", "isFirst", "initOnBackPressed", "initViews", "loadBannerMain", "onClickViews", "onDestroy", "onResume", "onStart", "openConfigs", "openHome", "openModule", "showNativeAds", "isShowAds", "showNativeMain", "Dynamic-Island_v1.0.2_v102_05.03.2025_debug"})
public final class MainActivity extends com.tools.edge.dynamic.island.ui.bases.BaseActivity<com.tools.edge.dynamic.island.databinding.ActivityMainBinding> {
    private boolean populateNative = false;
    private final boolean isFirstStart = false;
    @org.jetbrains.annotations.Nullable()
    private com.tools.edge.dynamic.island.ui.component.dialog.ExitDialog dialogExists;
    private int lastPosition = 0;
    @org.jetbrains.annotations.Nullable()
    private com.tools.edge.dynamic.island.ui.component.main.adpter.MainAdapter mainAdapter;
    @org.jetbrains.annotations.NotNull()
    private final com.tools.edge.dynamic.island.ui.component.main.home.HomeFragment homeFragment = null;
    @org.jetbrains.annotations.NotNull()
    private final com.tools.edge.dynamic.island.ui.component.main.modules.ModulesFragment modulesFragment = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> resultLauncher = null;
    
    public MainActivity() {
        super();
    }
    
    @java.lang.Override()
    public int getLayoutActivity() {
        return 0;
    }
    
    public final boolean isFirstStart() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.tools.edge.dynamic.island.ui.component.dialog.ExitDialog getDialogExists() {
        return null;
    }
    
    public final void setDialogExists(@org.jetbrains.annotations.Nullable()
    com.tools.edge.dynamic.island.ui.component.dialog.ExitDialog p0) {
    }
    
    public final int getLastPosition() {
        return 0;
    }
    
    public final void setLastPosition(int p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.tools.edge.dynamic.island.ui.component.main.adpter.MainAdapter getMainAdapter() {
        return null;
    }
    
    public final void setMainAdapter(@org.jetbrains.annotations.Nullable()
    com.tools.edge.dynamic.island.ui.component.main.adpter.MainAdapter p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.tools.edge.dynamic.island.ui.component.main.home.HomeFragment getHomeFragment() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.tools.edge.dynamic.island.ui.component.main.modules.ModulesFragment getModulesFragment() {
        return null;
    }
    
    @java.lang.Override()
    protected void onStart() {
    }
    
    public final void showNativeAds(boolean isShowAds) {
    }
    
    @java.lang.Override()
    protected void onResume() {
    }
    
    @java.lang.Override()
    public void initViews() {
    }
    
    private final void showNativeMain() {
    }
    
    private final void loadBannerMain() {
    }
    
    public final void openHome() {
    }
    
    public final void openModule() {
    }
    
    public final void openConfigs() {
    }
    
    @java.lang.Override()
    public void onClickViews() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.activity.result.ActivityResultLauncher<android.content.Intent> getResultLauncher() {
        return null;
    }
    
    public final void hideDemo(boolean isFirst) {
    }
    
    private final void initOnBackPressed() {
    }
    
    private final void delayShowConsentDialog() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
}