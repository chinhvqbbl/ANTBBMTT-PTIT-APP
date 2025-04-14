package com.tools.edge.dynamic.island.ui.component.main.configs.dialog;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B0\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012!\u0010\u0005\u001a\u001d\u0012\u0013\u0012\u00110\u0007\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006\u00a2\u0006\u0002\u0010\fJ\b\u0010\u0014\u001a\u00020\u0007H\u0016J\b\u0010\u0015\u001a\u00020\u000bH\u0017R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R,\u0010\u0005\u001a\u001d\u0012\u0013\u0012\u00110\u0007\u00a2\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u000b0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/tools/edge/dynamic/island/ui/component/main/configs/dialog/VisibilityDialog;", "Lcom/tools/edge/dynamic/island/ui/bases/BaseDialog;", "Lcom/tools/edge/dynamic/island/databinding/DialogKeepNotchVisibleBinding;", "context", "Landroid/content/Context;", "callback", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "id", "", "(Landroid/content/Context;Lkotlin/jvm/functions/Function1;)V", "adapter", "Lcom/tools/edge/dynamic/island/ui/component/main/configs/dialog/VisibilityAdapter;", "getCallback", "()Lkotlin/jvm/functions/Function1;", "listVisibilityModel", "", "Lcom/tools/edge/dynamic/island/ui/component/entity/VisibilityModel;", "getLayoutDialog", "initViews", "Dynamic-Island_v1.0.2_v102_04.14.2025_debug"})
public final class VisibilityDialog extends com.tools.edge.dynamic.island.ui.bases.BaseDialog<com.tools.edge.dynamic.island.databinding.DialogKeepNotchVisibleBinding> {
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> callback = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.tools.edge.dynamic.island.ui.component.entity.VisibilityModel> listVisibilityModel = null;
    @org.jetbrains.annotations.Nullable
    private com.tools.edge.dynamic.island.ui.component.main.configs.dialog.VisibilityAdapter adapter;
    
    public VisibilityDialog(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> callback) {
        super(null, 0);
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> getCallback() {
        return null;
    }
    
    @java.lang.Override
    public int getLayoutDialog() {
        return 0;
    }
    
    @java.lang.Override
    @android.annotation.SuppressLint(value = {"NotifyDataSetChanged"})
    public void initViews() {
    }
}