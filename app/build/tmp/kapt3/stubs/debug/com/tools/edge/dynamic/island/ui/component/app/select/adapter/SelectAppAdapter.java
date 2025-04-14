package com.tools.edge.dynamic.island.ui.component.app.select.adapter;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\'\u0012\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u0012\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\tJ\u001a\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\bJ\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0016H\u0016J \u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u0016H\u0016J\u0016\u0010\u001d\u001a\u00020\u00062\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00020\u001fH\u0016R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR#\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006 "}, d2 = {"Lcom/tools/edge/dynamic/island/ui/component/app/select/adapter/SelectAppAdapter;", "Lcom/tools/edge/dynamic/island/ui/bases/BaseRecyclerView;", "Lcom/tools/edge/dynamic/island/ui/component/entity/AppDetail;", "onClickItem", "Lkotlin/Function2;", "", "", "dataString", "", "(Lkotlin/jvm/functions/Function2;Ljava/lang/String;)V", "getDataString", "()Ljava/lang/String;", "getOnClickItem", "()Lkotlin/jvm/functions/Function2;", "getApplicationIcon", "Landroid/graphics/drawable/Drawable;", "context", "Landroid/content/Context;", "packageName", "getItemId", "", "position", "", "getItemLayout", "setData", "binding", "Landroidx/databinding/ViewDataBinding;", "item", "layoutPosition", "submitData", "newData", "", "Dynamic-Island_v1.0.2_v102_04.14.2025_debug"})
public final class SelectAppAdapter extends com.tools.edge.dynamic.island.ui.bases.BaseRecyclerView<com.tools.edge.dynamic.island.ui.component.entity.AppDetail> {
    @org.jetbrains.annotations.NotNull
    private final kotlin.jvm.functions.Function2<com.tools.edge.dynamic.island.ui.component.entity.AppDetail, java.lang.Boolean, kotlin.Unit> onClickItem = null;
    @org.jetbrains.annotations.NotNull
    private final java.lang.String dataString = null;
    
    public SelectAppAdapter(@org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function2<? super com.tools.edge.dynamic.island.ui.component.entity.AppDetail, ? super java.lang.Boolean, kotlin.Unit> onClickItem, @org.jetbrains.annotations.NotNull
    java.lang.String dataString) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlin.jvm.functions.Function2<com.tools.edge.dynamic.island.ui.component.entity.AppDetail, java.lang.Boolean, kotlin.Unit> getOnClickItem() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String getDataString() {
        return null;
    }
    
    @java.lang.Override
    public int getItemLayout() {
        return 0;
    }
    
    @java.lang.Override
    public long getItemId(int position) {
        return 0L;
    }
    
    @java.lang.Override
    public void setData(@org.jetbrains.annotations.NotNull
    androidx.databinding.ViewDataBinding binding, @org.jetbrains.annotations.NotNull
    com.tools.edge.dynamic.island.ui.component.entity.AppDetail item, int layoutPosition) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.graphics.drawable.Drawable getApplicationIcon(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.Nullable
    java.lang.String packageName) {
        return null;
    }
    
    @java.lang.Override
    public void submitData(@org.jetbrains.annotations.NotNull
    java.util.List<? extends com.tools.edge.dynamic.island.ui.component.entity.AppDetail> newData) {
    }
}