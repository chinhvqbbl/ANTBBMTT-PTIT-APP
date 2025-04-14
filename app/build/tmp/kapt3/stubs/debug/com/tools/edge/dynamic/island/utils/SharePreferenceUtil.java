package com.tools.edge.dynamic.island.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\r\u0018\u0000 -2\u00020\u0001:\u0001-B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\fJ\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0012J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0015J\u001f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00190\u0018\"\u0006\b\u0000\u0010\u0019\u0018\u00012\u0006\u0010\u000e\u001a\u00020\u000fH\u0086\bJ\u0016\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u001bJ \u0010\u001c\u001a\u0004\u0018\u0001H\u0019\"\u0006\b\u0000\u0010\u0019\u0018\u00012\u0006\u0010\u000e\u001a\u00020\u000fH\u0086\b\u00a2\u0006\u0002\u0010\u001dJ\b\u0010\u001e\u001a\u0004\u0018\u00010\bJ\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u000e\u001a\u00020\u000fJ\u001a\u0010\u001f\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fJ\u000e\u0010 \u001a\u00020!2\u0006\u0010\u000e\u001a\u00020\u000fJ\'\u0010\"\u001a\u00020!\"\u0006\b\u0000\u0010\u0019\u0018\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010#\u001a\b\u0012\u0004\u0012\u0002H\u00190\u0018H\u0086\bJ&\u0010$\u001a\u00020!\"\u0006\b\u0000\u0010\u0019\u0018\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010%\u001a\u0002H\u0019H\u0086\b\u00a2\u0006\u0002\u0010&J\u0016\u0010\'\u001a\u00020!2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020\fJ\u0016\u0010)\u001a\u00020!2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020\u0012J\u0016\u0010*\u001a\u00020!2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020\u0015J\u0016\u0010+\u001a\u00020!2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010(\u001a\u00020\u001bJ\u0018\u0010,\u001a\u00020!2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010(\u001a\u0004\u0018\u00010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u00a8\u0006."}, d2 = {"Lcom/tools/edge/dynamic/island/utils/SharePreferenceUtil;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "mPref", "Landroid/content/SharedPreferences;", "getMPref", "()Landroid/content/SharedPreferences;", "clear", "", "getBooleanValue", "key", "", "default", "getFloatValue", "", "i", "getIntValue", "", "defaultValue", "getListFromSharePreference", "", "T", "getLongValue", "", "getObjectFromSharePreference", "(Ljava/lang/String;)Ljava/lang/Object;", "getSharedPreferences", "getStringValue", "remove", "", "saveListToSharePreference", "list", "saveObjectToSharePreference", "data", "(Ljava/lang/String;Ljava/lang/Object;)V", "setBooleanValue", "value", "setFloatValue", "setIntValue", "setLongValue", "setStringValue", "Companion", "Dynamic-Island_v1.0.2_v102_04.14.2025_debug"})
public final class SharePreferenceUtil {
    @org.jetbrains.annotations.NotNull
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String APP_SHARE_KEY = "com.tools.edge.dynamic.island";
    @android.annotation.SuppressLint(value = {"StaticFieldLeak"})
    @org.jetbrains.annotations.Nullable
    private static com.tools.edge.dynamic.island.utils.SharePreferenceUtil instance;
    @org.jetbrains.annotations.NotNull
    private final android.content.SharedPreferences mPref = null;
    @org.jetbrains.annotations.NotNull
    public static final com.tools.edge.dynamic.island.utils.SharePreferenceUtil.Companion Companion = null;
    
    public SharePreferenceUtil(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final android.content.SharedPreferences getSharedPreferences() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final android.content.SharedPreferences getMPref() {
        return null;
    }
    
    public final void setStringValue(@org.jetbrains.annotations.NotNull
    java.lang.String key, @org.jetbrains.annotations.Nullable
    java.lang.String value) {
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getStringValue(@org.jetbrains.annotations.NotNull
    java.lang.String key, @org.jetbrains.annotations.Nullable
    java.lang.String p1_772401952) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getStringValue(@org.jetbrains.annotations.NotNull
    java.lang.String key) {
        return null;
    }
    
    public final void setIntValue(@org.jetbrains.annotations.NotNull
    java.lang.String key, int value) {
    }
    
    public final int getIntValue(@org.jetbrains.annotations.NotNull
    java.lang.String key, int defaultValue) {
        return 0;
    }
    
    public final void setFloatValue(@org.jetbrains.annotations.NotNull
    java.lang.String key, float value) {
    }
    
    public final float getFloatValue(@org.jetbrains.annotations.NotNull
    java.lang.String key, float i) {
        return 0.0F;
    }
    
    public final void setLongValue(@org.jetbrains.annotations.NotNull
    java.lang.String key, long value) {
    }
    
    public final long getLongValue(@org.jetbrains.annotations.NotNull
    java.lang.String key, long i) {
        return 0L;
    }
    
    public final void setBooleanValue(@org.jetbrains.annotations.NotNull
    java.lang.String key, boolean value) {
    }
    
    public final boolean getBooleanValue(@org.jetbrains.annotations.NotNull
    java.lang.String key) {
        return false;
    }
    
    public final boolean getBooleanValue(@org.jetbrains.annotations.NotNull
    java.lang.String key, boolean p1_772401952) {
        return false;
    }
    
    public final void remove(@org.jetbrains.annotations.NotNull
    java.lang.String key) {
    }
    
    public final boolean clear() {
        return false;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\bJ\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u0083\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2 = {"Lcom/tools/edge/dynamic/island/utils/SharePreferenceUtil$Companion;", "", "()V", "APP_SHARE_KEY", "", "getAPP_SHARE_KEY", "()Ljava/lang/String;", "instance", "Lcom/tools/edge/dynamic/island/utils/SharePreferenceUtil;", "getInstance", "initializeInstance", "", "context", "Landroid/content/Context;", "Dynamic-Island_v1.0.2_v102_04.14.2025_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final java.lang.String getAPP_SHARE_KEY() {
            return null;
        }
        
        public final void initializeInstance(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
        }
        
        @org.jetbrains.annotations.NotNull
        public final com.tools.edge.dynamic.island.utils.SharePreferenceUtil getInstance() {
            return null;
        }
    }
}