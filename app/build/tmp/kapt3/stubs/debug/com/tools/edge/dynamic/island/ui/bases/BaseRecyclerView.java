package com.tools.edge.dynamic.island.ui.bases;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u0012\u0012\u000e\u0012\f0\u0003R\b\u0012\u0004\u0012\u0002H\u00010\u00000\u0002:\u0001&B\u0005\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0010H&J\"\u0010\u0012\u001a\u00020\u00132\u0010\u0010\u0014\u001a\f0\u0003R\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0015\u001a\u00020\u0010H\u0016J%\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\u0010H\u0016\u00a2\u0006\u0002\u0010\u001bJ\"\u0010\u001c\u001a\f0\u0003R\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0010H\u0016J\u0010\u0010 \u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J%\u0010!\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\"\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\u0010H&\u00a2\u0006\u0002\u0010\u001bJ\u0016\u0010#\u001a\u00020\u00132\f\u0010$\u001a\b\u0012\u0004\u0012\u00028\u00000%H&R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\'"}, d2 = {"Lcom/tools/edge/dynamic/island/ui/bases/BaseRecyclerView;", "T", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/tools/edge/dynamic/island/ui/bases/BaseRecyclerView$ViewHolder;", "()V", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "list", "", "getList", "()Ljava/util/List;", "getItemCount", "", "getItemLayout", "onBindViewHolder", "", "holder", "position", "onClickViews", "binding", "Landroidx/databinding/ViewDataBinding;", "obj", "layoutPosition", "(Landroidx/databinding/ViewDataBinding;Ljava/lang/Object;I)V", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "onResizeViews", "setData", "item", "submitData", "newData", "", "ViewHolder", "Dynamic-Island_v1.0.2_v102_05.03.2025_debug"})
public abstract class BaseRecyclerView<T extends java.lang.Object> extends androidx.recyclerview.widget.RecyclerView.Adapter<com.tools.edge.dynamic.island.ui.bases.BaseRecyclerView<T>.ViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<T> list = null;
    @org.jetbrains.annotations.Nullable()
    private android.content.Context context;
    
    public BaseRecyclerView() {
        super();
    }
    
    public abstract int getItemLayout();
    
    public abstract void submitData(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends T> newData);
    
    public abstract void setData(@org.jetbrains.annotations.NotNull()
    androidx.databinding.ViewDataBinding binding, T item, int layoutPosition);
    
    public void onResizeViews(@org.jetbrains.annotations.NotNull()
    androidx.databinding.ViewDataBinding binding) {
    }
    
    public void onClickViews(@org.jetbrains.annotations.NotNull()
    androidx.databinding.ViewDataBinding binding, T obj, int layoutPosition) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<T> getList() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.content.Context getContext() {
        return null;
    }
    
    public final void setContext(@org.jetbrains.annotations.Nullable()
    android.content.Context p0) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.tools.edge.dynamic.island.ui.bases.BaseRecyclerView<T>.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.tools.edge.dynamic.island.ui.bases.BaseRecyclerView<T>.ViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010\fJ\u0015\u0010\r\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00028\u0000H\u0016\u00a2\u0006\u0002\u0010\fJ\b\u0010\u000e\u001a\u00020\nH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\u00a8\u0006\u000f"}, d2 = {"Lcom/tools/edge/dynamic/island/ui/bases/BaseRecyclerView$ViewHolder;", "Lcom/tools/edge/dynamic/island/ui/bases/BaseViewHolder;", "binding", "Landroidx/databinding/ViewDataBinding;", "(Lcom/tools/edge/dynamic/island/ui/bases/BaseRecyclerView;Landroidx/databinding/ViewDataBinding;)V", "getBinding", "()Landroidx/databinding/ViewDataBinding;", "setBinding", "(Landroidx/databinding/ViewDataBinding;)V", "bindData", "", "obj", "(Ljava/lang/Object;)V", "onClickViews", "onResizeViews", "Dynamic-Island_v1.0.2_v102_05.03.2025_debug"})
    public final class ViewHolder extends com.tools.edge.dynamic.island.ui.bases.BaseViewHolder<T> {
        @org.jetbrains.annotations.NotNull()
        private androidx.databinding.ViewDataBinding binding;
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        androidx.databinding.ViewDataBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.databinding.ViewDataBinding getBinding() {
            return null;
        }
        
        public final void setBinding(@org.jetbrains.annotations.NotNull()
        androidx.databinding.ViewDataBinding p0) {
        }
        
        @java.lang.Override()
        public void bindData(T obj) {
        }
        
        @java.lang.Override()
        public void onResizeViews() {
        }
        
        @java.lang.Override()
        public void onClickViews(T obj) {
        }
    }
}