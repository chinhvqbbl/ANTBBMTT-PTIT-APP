package com.tools.edge.dynamic.island.ui.component.service;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\u0018\u0000 !2\u00020\u0001:\u0001!B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\"\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0019H\u0016J\b\u0010\u001d\u001a\u00020\u0017H\u0002J\b\u0010\u001e\u001a\u00020\u0017H\u0002J\u0006\u0010\u001f\u001a\u00020\u0017J\u0006\u0010 \u001a\u00020\u0017R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\rX\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011\u00a8\u0006\""}, d2 = {"Lcom/tools/edge/dynamic/island/ui/component/service/PermissionService;", "Landroid/app/Service;", "()V", "checkRunnableAccessibility", "Ljava/lang/Runnable;", "checkRunnableNotification", "handlerAccessibility", "Landroid/os/Handler;", "handlerNotification", "isRunningAccessibility", "", "isRunningNotification", "prefs", "Landroid/content/SharedPreferences;", "getPrefs", "()Landroid/content/SharedPreferences;", "setPrefs", "(Landroid/content/SharedPreferences;)V", "onBind", "Landroid/os/IBinder;", "p0", "Landroid/content/Intent;", "onDestroy", "", "onStartCommand", "", "intent", "flags", "startId", "startCheckingAccessibility", "startCheckingNotification", "stopCheckingAccessibility", "stopCheckingNotification", "Companion", "Dynamic-Island_v1.0.2_v102_04.21.2025_debug"})
public final class PermissionService extends android.app.Service {
    @org.jetbrains.annotations.Nullable()
    private android.os.Handler handlerNotification;
    private boolean isRunningNotification = false;
    @org.jetbrains.annotations.Nullable()
    private android.os.Handler handlerAccessibility;
    private boolean isRunningAccessibility = false;
    public android.content.SharedPreferences prefs;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.Runnable checkRunnableNotification = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.Runnable checkRunnableAccessibility = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.tools.edge.dynamic.island.ui.component.service.PermissionService.Companion Companion = null;
    
    public PermissionService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.SharedPreferences getPrefs() {
        return null;
    }
    
    public final void setPrefs(@org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences p0) {
    }
    
    private final void startCheckingNotification() {
    }
    
    private final void startCheckingAccessibility() {
    }
    
    public final void stopCheckingNotification() {
    }
    
    public final void stopCheckingAccessibility() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.os.IBinder onBind(@org.jetbrains.annotations.Nullable()
    android.content.Intent p0) {
        return null;
    }
    
    @java.lang.Override()
    public int onStartCommand(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/tools/edge/dynamic/island/ui/component/service/PermissionService$Companion;", "", "()V", "checkNotificationEnabled", "", "context", "Landroid/content/Context;", "Dynamic-Island_v1.0.2_v102_04.21.2025_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final boolean checkNotificationEnabled(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return false;
        }
    }
}