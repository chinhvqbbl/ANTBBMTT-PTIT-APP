package com.tools.edge.dynamic.island.ui.bases.ext;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000*\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\n\u0010\u0004\u001a\u00020\u0005*\u00020\u0006\u001a\n\u0010\u0007\u001a\u00020\u0001*\u00020\u0006\u001a\u0012\u0010\b\u001a\u00020\t*\u00020\u00062\u0006\u0010\n\u001a\u00020\u0001\u001a\n\u0010\u000b\u001a\u00020\u0005*\u00020\u0006\u001a\u0012\u0010\f\u001a\u00020\r*\u00020\u00062\u0006\u0010\n\u001a\u00020\u0001\u001a\u0012\u0010\u000e\u001a\u00020\r*\u00020\u00062\u0006\u0010\u000f\u001a\u00020\t\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"CHECK_TIME_MULTI_CLICK", "", "mLastClickTime", "", "canTouch", "", "Landroid/content/Context;", "getCurrentSdkVersion", "getStringById", "", "id", "isNetwork", "showToastById", "", "showToastByString", "message", "Dynamic-Island_v1.0.2_v102_05.03.2025_debug"})
public final class ContextExtKt {
    public static final int CHECK_TIME_MULTI_CLICK = 500;
    private static long mLastClickTime = 0L;
    
    public static final boolean canTouch(@org.jetbrains.annotations.NotNull()
    android.content.Context $this$canTouch) {
        return false;
    }
    
    public static final void showToastByString(@org.jetbrains.annotations.NotNull()
    android.content.Context $this$showToastByString, @org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    public static final void showToastById(@org.jetbrains.annotations.NotNull()
    android.content.Context $this$showToastById, int id) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String getStringById(@org.jetbrains.annotations.NotNull()
    android.content.Context $this$getStringById, int id) {
        return null;
    }
    
    public static final int getCurrentSdkVersion(@org.jetbrains.annotations.NotNull()
    android.content.Context $this$getCurrentSdkVersion) {
        return 0;
    }
    
    public static final boolean isNetwork(@org.jetbrains.annotations.NotNull()
    android.content.Context $this$isNetwork) {
        return false;
    }
}