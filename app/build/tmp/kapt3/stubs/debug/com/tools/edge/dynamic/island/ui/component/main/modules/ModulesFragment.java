package com.tools.edge.dynamic.island.ui.component.main.modules;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \'2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\'B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020\"H\u0016J\b\u0010$\u001a\u00020\"H\u0016J\b\u0010%\u001a\u00020\"H\u0016J\b\u0010&\u001a\u00020\"H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001f\u0010\f\u001a\u0010\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u000e0\u000e0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0017\u001a\u00020\u00188BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u001e0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/tools/edge/dynamic/island/ui/component/main/modules/ModulesFragment;", "Lcom/tools/edge/dynamic/island/ui/bases/BaseFragment;", "Lcom/tools/edge/dynamic/island/databinding/FragmentModuleBinding;", "()V", "TAG", "", "dialogPermissionRequiredCall", "Lcom/tools/edge/dynamic/island/ui/component/moduldetails/PermissionRequiredDialog;", "getDialogPermissionRequiredCall", "()Lcom/tools/edge/dynamic/island/ui/component/moduldetails/PermissionRequiredDialog;", "setDialogPermissionRequiredCall", "(Lcom/tools/edge/dynamic/island/ui/component/moduldetails/PermissionRequiredDialog;)V", "launcher", "Landroidx/activity/result/ActivityResultLauncher;", "Landroid/content/Intent;", "kotlin.jvm.PlatformType", "getLauncher", "()Landroidx/activity/result/ActivityResultLauncher;", "listModules", "", "Lcom/tools/edge/dynamic/island/ui/component/model/ModulesModel;", "modulesAdapter", "Lcom/tools/edge/dynamic/island/ui/component/main/modules/adapter/ModulesAdapter;", "modulesViewModel", "Lcom/tools/edge/dynamic/island/ui/component/main/modules/viewmodel/ModulesViewModel;", "getModulesViewModel", "()Lcom/tools/edge/dynamic/island/ui/component/main/modules/viewmodel/ModulesViewModel;", "modulesViewModel$delegate", "Lkotlin/Lazy;", "requestReadPhoneState", "", "getLayoutFragment", "", "initRcv", "", "initViews", "onClickViews", "onResume", "startAppInfo", "Companion", "Dynamic-Island_v1.0.2_v102_05.03.2025_debug"})
public final class ModulesFragment extends com.tools.edge.dynamic.island.ui.bases.BaseFragment<com.tools.edge.dynamic.island.databinding.FragmentModuleBinding> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy modulesViewModel$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private com.tools.edge.dynamic.island.ui.component.main.modules.adapter.ModulesAdapter modulesAdapter;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String TAG = "ModulesFragment";
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.tools.edge.dynamic.island.ui.component.model.ModulesModel> listModules;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<android.content.Intent> launcher = null;
    @org.jetbrains.annotations.Nullable()
    private com.tools.edge.dynamic.island.ui.component.moduldetails.PermissionRequiredDialog dialogPermissionRequiredCall;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String[]> requestReadPhoneState = null;
    private static int checkEnabledAds = 0;
    @org.jetbrains.annotations.NotNull()
    public static final com.tools.edge.dynamic.island.ui.component.main.modules.ModulesFragment.Companion Companion = null;
    
    public ModulesFragment() {
        super();
    }
    
    private final com.tools.edge.dynamic.island.ui.component.main.modules.viewmodel.ModulesViewModel getModulesViewModel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.activity.result.ActivityResultLauncher<android.content.Intent> getLauncher() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.tools.edge.dynamic.island.ui.component.moduldetails.PermissionRequiredDialog getDialogPermissionRequiredCall() {
        return null;
    }
    
    public final void setDialogPermissionRequiredCall(@org.jetbrains.annotations.Nullable()
    com.tools.edge.dynamic.island.ui.component.moduldetails.PermissionRequiredDialog p0) {
    }
    
    @java.lang.Override()
    public void onResume() {
    }
    
    private final void startAppInfo() {
    }
    
    @java.lang.Override()
    public int getLayoutFragment() {
        return 0;
    }
    
    @java.lang.Override()
    public void initViews() {
    }
    
    private final void initRcv() {
    }
    
    @java.lang.Override()
    public void onClickViews() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\t"}, d2 = {"Lcom/tools/edge/dynamic/island/ui/component/main/modules/ModulesFragment$Companion;", "", "()V", "checkEnabledAds", "", "getCheckEnabledAds", "()I", "setCheckEnabledAds", "(I)V", "Dynamic-Island_v1.0.2_v102_05.03.2025_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final int getCheckEnabledAds() {
            return 0;
        }
        
        public final void setCheckEnabledAds(int p0) {
        }
    }
}