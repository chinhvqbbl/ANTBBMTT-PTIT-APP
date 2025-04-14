package com.tools.edge.dynamic.island.ui.bases.ext;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\u000b"}, d2 = {"Lcom/tools/edge/dynamic/island/ui/bases/ext/OnCustomTouchListener;", "", "onCustomTouchDown", "", "view", "Landroid/view/View;", "event", "Landroid/view/MotionEvent;", "onCustomTouchMoveOut", "onCustomTouchOther", "onCustomTouchUp", "Dynamic-Island_v1.0.2_v102_04.14.2025_debug"})
public abstract interface OnCustomTouchListener {
    
    public abstract void onCustomTouchDown(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.NotNull
    android.view.MotionEvent event);
    
    public abstract void onCustomTouchMoveOut(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.NotNull
    android.view.MotionEvent event);
    
    public abstract void onCustomTouchUp(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.NotNull
    android.view.MotionEvent event);
    
    public abstract void onCustomTouchOther(@org.jetbrains.annotations.NotNull
    android.view.View view, @org.jetbrains.annotations.NotNull
    android.view.MotionEvent event);
}