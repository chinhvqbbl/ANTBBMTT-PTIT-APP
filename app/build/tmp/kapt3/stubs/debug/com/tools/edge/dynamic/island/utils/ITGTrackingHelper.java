package com.tools.edge.dynamic.island.utils;

/**
 * Required implementation
 * implementation 'com.google.firebase:firebase-analytics-ktx'
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001%B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0018\u001a\u00020\u0004J\u0016\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0004J\u0018\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u00042\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fJ \u0010 \u001a\u00020\u00002\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u00042\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fJ\u0016\u0010\"\u001a\u00020\u00002\u0006\u0010#\u001a\u00020\u00042\u0006\u0010$\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/tools/edge/dynamic/island/utils/ITGTrackingHelper;", "", "()V", "AGREE_CONSENT_1", "", "AGREE_CONSENT_2", "AGREE_CONSENT_3", "CONSENT_ERROR_1", "CONSENT_ERROR_2", "DISPLAY_CONSENT_1", "DISPLAY_CONSENT_2", "DISPLAY_CONSENT_3", "LOAD_CONSENT_1", "LOAD_CONSENT_2", "LOAD_CONSENT_3", "NOT_REQUIRE_DISPLAY_CONSENT_1", "NOT_USING_DISPLAY_CONSENT_1", "NOT_USING_DISPLAY_CONSENT_2", "REFUSE_CONSENT_1", "REFUSE_CONSENT_2", "REFUSE_CONSENT_3", "mFirebaseAnalytics", "Lcom/google/firebase/analytics/FirebaseAnalytics;", "addScreenTrack", "screenName", "fromScreenToScreen", "fromScreen", "toScreen", "logEvent", "eventName", "params", "Landroid/os/Bundle;", "logEventClick", "activityName", "userProperty", "name", "value", "Params", "Dynamic-Island_v1.0.2_v102_04.14.2025_debug"})
public final class ITGTrackingHelper {
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String CONSENT_ERROR_1 = "consent_error_1";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String CONSENT_ERROR_2 = "consent_error_2";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String LOAD_CONSENT_1 = "new_load_consent_1";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String DISPLAY_CONSENT_1 = "new_display_consent_1";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String NOT_REQUIRE_DISPLAY_CONSENT_1 = "new_not_require_consent_1";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String NOT_USING_DISPLAY_CONSENT_1 = "new_not_using_display_consent_1";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String AGREE_CONSENT_1 = "new_agree_consent_1";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String REFUSE_CONSENT_1 = "new_refuse_consent_1";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String LOAD_CONSENT_2 = "load_consent_2";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String DISPLAY_CONSENT_2 = "display_consent_2";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String NOT_USING_DISPLAY_CONSENT_2 = "not_using_display_consent_2";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String AGREE_CONSENT_2 = "agree_consent_2";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String REFUSE_CONSENT_2 = "refuse_consent_2";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String LOAD_CONSENT_3 = "load_consent_3";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String DISPLAY_CONSENT_3 = "display_consent_3";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String AGREE_CONSENT_3 = "agree_consent_3";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String REFUSE_CONSENT_3 = "refuse_consent_3";
    @org.jetbrains.annotations.NotNull
    private static com.google.firebase.analytics.FirebaseAnalytics mFirebaseAnalytics;
    @org.jetbrains.annotations.NotNull
    public static final com.tools.edge.dynamic.island.utils.ITGTrackingHelper INSTANCE = null;
    
    private ITGTrackingHelper() {
        super();
    }
    
    /**
     * Khi gắn cái này cần trao đổi với PM, PO, BA
     */
    @org.jetbrains.annotations.NotNull
    public final com.tools.edge.dynamic.island.utils.ITGTrackingHelper userProperty(@org.jetbrains.annotations.NotNull
    java.lang.String name, @org.jetbrains.annotations.NotNull
    java.lang.String value) {
        return null;
    }
    
    /**
     * Tracking theo sự kiên được lên kịch bản.
     * Gắn theo kịch bản của PO, PM, BA.
     */
    @org.jetbrains.annotations.NotNull
    public final com.tools.edge.dynamic.island.utils.ITGTrackingHelper logEvent(@org.jetbrains.annotations.NotNull
    java.lang.String eventName, @org.jetbrains.annotations.Nullable
    android.os.Bundle params) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.tools.edge.dynamic.island.utils.ITGTrackingHelper logEventClick(@org.jetbrains.annotations.NotNull
    java.lang.String activityName, @org.jetbrains.annotations.NotNull
    java.lang.String eventName, @org.jetbrains.annotations.Nullable
    android.os.Bundle params) {
        return null;
    }
    
    /**
     * Chỉ gắn cho tracking theo màn hình theo yêu cầu của PM, PO, BA
     */
    @org.jetbrains.annotations.NotNull
    public final com.tools.edge.dynamic.island.utils.ITGTrackingHelper addScreenTrack(@org.jetbrains.annotations.NotNull
    java.lang.String screenName) {
        return null;
    }
    
    /**
     * Chỉ gắn cho tracking theo màn hình theo yêu cầu của PM, PO, BA
     */
    @org.jetbrains.annotations.NotNull
    public final com.tools.edge.dynamic.island.utils.ITGTrackingHelper fromScreenToScreen(@org.jetbrains.annotations.NotNull
    java.lang.String fromScreen, @org.jetbrains.annotations.NotNull
    java.lang.String toScreen) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lcom/tools/edge/dynamic/island/utils/ITGTrackingHelper$Params;", "", "()V", "Dynamic-Island_v1.0.2_v102_04.14.2025_debug"})
    public static final class Params {
        @org.jetbrains.annotations.NotNull
        public static final com.tools.edge.dynamic.island.utils.ITGTrackingHelper.Params INSTANCE = null;
        
        private Params() {
            super();
        }
    }
}