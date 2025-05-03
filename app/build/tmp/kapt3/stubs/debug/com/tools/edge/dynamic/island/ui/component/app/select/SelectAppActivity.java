package com.tools.edge.dynamic.island.ui.component.app.select;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0003J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u000eH\u0002J\b\u0010\u0012\u001a\u00020\u000eH\u0016J\b\u0010\u0013\u001a\u00020\u000eH\u0003J\b\u0010\u0014\u001a\u00020\u000eH\u0016J\b\u0010\u0015\u001a\u00020\u000eH\u0016R$\u0010\u0004\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\b0\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/tools/edge/dynamic/island/ui/component/app/select/SelectAppActivity;", "Lcom/tools/edge/dynamic/island/ui/bases/BaseActivity;", "Lcom/tools/edge/dynamic/island/databinding/ActivitySelectAppBinding;", "()V", "listApps", "Landroidx/lifecycle/MutableLiveData;", "Ljava/util/HashSet;", "Lcom/tools/edge/dynamic/island/ui/component/entity/AppDetail;", "Lkotlin/collections/HashSet;", "prefManager", "Lcom/tools/edge/dynamic/island/ui/component/background/PrefManager;", "selectAppAdapter", "Lcom/tools/edge/dynamic/island/ui/component/app/select/adapter/SelectAppAdapter;", "checkSelectAll", "", "getLayoutActivity", "", "initAdapter", "initViews", "loadData", "observerData", "onClickViews", "Dynamic-Island_v1.0.2_v102_05.03.2025_debug"})
public final class SelectAppActivity extends com.tools.edge.dynamic.island.ui.bases.BaseActivity<com.tools.edge.dynamic.island.databinding.ActivitySelectAppBinding> {
    @org.jetbrains.annotations.Nullable()
    private com.tools.edge.dynamic.island.ui.component.app.select.adapter.SelectAppAdapter selectAppAdapter;
    @org.jetbrains.annotations.NotNull()
    private androidx.lifecycle.MutableLiveData<java.util.HashSet<com.tools.edge.dynamic.island.ui.component.entity.AppDetail>> listApps;
    @org.jetbrains.annotations.Nullable()
    private com.tools.edge.dynamic.island.ui.component.background.PrefManager prefManager;
    
    public SelectAppActivity() {
        super();
    }
    
    @java.lang.Override()
    public int getLayoutActivity() {
        return 0;
    }
    
    @java.lang.Override()
    public void initViews() {
    }
    
    private final void checkSelectAll() {
    }
    
    @android.annotation.SuppressLint(value = {"SuspiciousIndentation"})
    private final void loadData() {
    }
    
    private final void initAdapter() {
    }
    
    @java.lang.Override()
    public void observerData() {
    }
    
    @java.lang.Override()
    public void onClickViews() {
    }
}