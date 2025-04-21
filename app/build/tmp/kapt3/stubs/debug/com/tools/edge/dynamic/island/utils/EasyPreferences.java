package com.tools.edge.dynamic.island.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J!\u0010\n\u001a\u00020\u000b*\u00020\u00042\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000e0\rH\u0082\bJ4\u0010\u000f\u001a\u0004\u0018\u0001H\u0010\"\n\b\u0000\u0010\u0010\u0018\u0001*\u00020\u0001*\u00020\u00042\u0006\u0010\u0011\u001a\u00020\b2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u0001H\u0010H\u0086\n\u00a2\u0006\u0002\u0010\u0013J\u001f\u0010\u0014\u001a\u00020\u000b*\u00020\u00042\u0006\u0010\u0011\u001a\u00020\b2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u0086\u0002\u00a8\u0006\u0016"}, d2 = {"Lcom/tools/edge/dynamic/island/utils/EasyPreferences;", "", "()V", "customPrefs", "Landroid/content/SharedPreferences;", "context", "Landroid/content/Context;", "name", "", "defaultPrefs", "edit", "", "operation", "Lkotlin/Function1;", "Landroid/content/SharedPreferences$Editor;", "get", "T", "key", "defaultValue", "(Landroid/content/SharedPreferences;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "set", "value", "Dynamic-Island_v1.0.2_v102_04.21.2025_debug"})
public final class EasyPreferences {
    @org.jetbrains.annotations.NotNull()
    public static final com.tools.edge.dynamic.island.utils.EasyPreferences INSTANCE = null;
    
    private EasyPreferences() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.SharedPreferences defaultPrefs(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.SharedPreferences customPrefs(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String name) {
        return null;
    }
    
    private final void edit(android.content.SharedPreferences $this$edit, kotlin.jvm.functions.Function1<? super android.content.SharedPreferences.Editor, ? extends android.content.SharedPreferences.Editor> operation) {
    }
    
    /**
     * puts a key value pair in shared prefs if doesn't exists, otherwise updates value on given [key]
     */
    public final void set(@org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences $this$set, @org.jetbrains.annotations.NotNull()
    java.lang.String key, @org.jetbrains.annotations.Nullable()
    java.lang.Object value) {
    }
}