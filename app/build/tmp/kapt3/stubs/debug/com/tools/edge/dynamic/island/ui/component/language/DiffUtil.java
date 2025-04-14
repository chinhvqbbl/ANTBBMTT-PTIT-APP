package com.tools.edge.dynamic.island.ui.component.language;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0018\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\b\u0010\u0013\u001a\u00020\u0010H\u0016J\b\u0010\u0014\u001a\u00020\u0010H\u0016R \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR \u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\n\u00a8\u0006\u0015"}, d2 = {"Lcom/tools/edge/dynamic/island/ui/component/language/DiffUtil;", "Landroidx/recyclerview/widget/DiffUtil$Callback;", "oldListLanguage", "", "Lcom/tools/edge/dynamic/island/ui/component/language/LanguageModel;", "newListLanguage", "(Ljava/util/List;Ljava/util/List;)V", "getNewListLanguage", "()Ljava/util/List;", "setNewListLanguage", "(Ljava/util/List;)V", "getOldListLanguage", "setOldListLanguage", "areContentsTheSame", "", "oldItemPosition", "", "newItemPosition", "areItemsTheSame", "getNewListSize", "getOldListSize", "Dynamic-Island_v1.0.2_v102_04.14.2025_debug"})
public final class DiffUtil extends androidx.recyclerview.widget.DiffUtil.Callback {
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.tools.edge.dynamic.island.ui.component.language.LanguageModel> oldListLanguage;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.tools.edge.dynamic.island.ui.component.language.LanguageModel> newListLanguage;
    
    public DiffUtil(@org.jetbrains.annotations.NotNull
    java.util.List<com.tools.edge.dynamic.island.ui.component.language.LanguageModel> oldListLanguage, @org.jetbrains.annotations.NotNull
    java.util.List<com.tools.edge.dynamic.island.ui.component.language.LanguageModel> newListLanguage) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.tools.edge.dynamic.island.ui.component.language.LanguageModel> getOldListLanguage() {
        return null;
    }
    
    public final void setOldListLanguage(@org.jetbrains.annotations.NotNull
    java.util.List<com.tools.edge.dynamic.island.ui.component.language.LanguageModel> p0) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.tools.edge.dynamic.island.ui.component.language.LanguageModel> getNewListLanguage() {
        return null;
    }
    
    public final void setNewListLanguage(@org.jetbrains.annotations.NotNull
    java.util.List<com.tools.edge.dynamic.island.ui.component.language.LanguageModel> p0) {
    }
    
    @java.lang.Override
    public int getOldListSize() {
        return 0;
    }
    
    @java.lang.Override
    public int getNewListSize() {
        return 0;
    }
    
    @java.lang.Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }
    
    @java.lang.Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }
}