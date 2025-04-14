package com.tools.edge.dynamic.island.app;

@dagger.hilt.android.HiltAndroidApp
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u001c\u0010\n\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010\t0\u000bj\n\u0012\u0006\u0012\u0004\u0018\u00010\t`\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\b\u0010\u000f\u001a\u00020\u000eH\u0016J\u000e\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0004R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007\u00a8\u0006\u0013"}, d2 = {"Lcom/tools/edge/dynamic/island/app/GlobalApp;", "Lcom/bbl/module_ads/application/AdsMultiDexApplication;", "()V", "isAccessibilityServiceRunning", "", "()Z", "setAccessibilityServiceRunning", "(Z)V", "getLanguage", "Lcom/tools/edge/dynamic/island/ui/component/language/LanguageModel;", "getListLanguageApp", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "initAds", "", "onCreate", "setAccessibilityActive", "isActive", "Companion", "Dynamic-Island_v1.0.2_v102_04.14.2025_debug"})
public final class GlobalApp extends com.bbl.module_ads.application.AdsMultiDexApplication {
    private boolean isAccessibilityServiceRunning = false;
    @android.annotation.SuppressLint(value = {"StaticFieldLeak"})
    public static com.tools.edge.dynamic.island.app.GlobalApp instance;
    private static boolean isShowAds = false;
    private static boolean isBackPermission = false;
    private static boolean isNextLanguage = true;
    @org.jetbrains.annotations.Nullable
    private static java.lang.Boolean isback = true;
    @org.jetbrains.annotations.NotNull
    public static final com.tools.edge.dynamic.island.app.GlobalApp.Companion Companion = null;
    
    public GlobalApp() {
        super();
    }
    
    public final boolean isAccessibilityServiceRunning() {
        return false;
    }
    
    public final void setAccessibilityServiceRunning(boolean p0) {
    }
    
    public final void setAccessibilityActive(boolean isActive) {
    }
    
    @java.lang.Override
    public void onCreate() {
    }
    
    private final void initAds() {
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.tools.edge.dynamic.island.ui.component.language.LanguageModel getLanguage() {
        return null;
    }
    
    private final java.util.ArrayList<com.tools.edge.dynamic.island.ui.component.language.LanguageModel> getListLanguageApp() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000e\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001a\u0010\u0010\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR\u001e\u0010\u0012\u001a\u0004\u0018\u00010\nX\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016\u00a8\u0006\u0018"}, d2 = {"Lcom/tools/edge/dynamic/island/app/GlobalApp$Companion;", "", "()V", "instance", "Lcom/tools/edge/dynamic/island/app/GlobalApp;", "getInstance", "()Lcom/tools/edge/dynamic/island/app/GlobalApp;", "setInstance", "(Lcom/tools/edge/dynamic/island/app/GlobalApp;)V", "isBackPermission", "", "()Z", "setBackPermission", "(Z)V", "isNextLanguage", "setNextLanguage", "isShowAds", "setShowAds", "isback", "getIsback", "()Ljava/lang/Boolean;", "setIsback", "(Ljava/lang/Boolean;)V", "Ljava/lang/Boolean;", "Dynamic-Island_v1.0.2_v102_04.14.2025_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.tools.edge.dynamic.island.app.GlobalApp getInstance() {
            return null;
        }
        
        public final void setInstance(@org.jetbrains.annotations.NotNull
        com.tools.edge.dynamic.island.app.GlobalApp p0) {
        }
        
        public final boolean isShowAds() {
            return false;
        }
        
        public final void setShowAds(boolean p0) {
        }
        
        public final boolean isBackPermission() {
            return false;
        }
        
        public final void setBackPermission(boolean p0) {
        }
        
        public final boolean isNextLanguage() {
            return false;
        }
        
        public final void setNextLanguage(boolean p0) {
        }
        
        @org.jetbrains.annotations.Nullable
        public final java.lang.Boolean getIsback() {
            return null;
        }
        
        public final void setIsback(@org.jetbrains.annotations.Nullable
        java.lang.Boolean p0) {
        }
    }
}