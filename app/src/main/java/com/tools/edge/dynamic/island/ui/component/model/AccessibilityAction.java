package com.tools.edge.dynamic.island.ui.component.model;

import com.google.gson.annotations.SerializedName;

/**
 * Class định nghĩa cấu trúc dữ liệu cho các hành động accessibility
 */
public class AccessibilityAction {
    
    @SerializedName("action_type")
    private String actionType; // Loại hành động: CLICK, INPUT, SCROLL, OPEN_APP, WAIT
    
    @SerializedName("target_type")
    private String targetType; // Loại target: TEXT, ID, DESCRIPTION, PACKAGE
    
    @SerializedName("target_value")
    private String targetValue; // Giá trị target (text, id, description, package name)
    
    @SerializedName("input_text")
    private String inputText; // Text cần input (chỉ dùng cho action INPUT)
    
    @SerializedName("scroll_direction")
    private String scrollDirection; // Hướng scroll: UP, DOWN (chỉ dùng cho action SCROLL)
    
    @SerializedName("scroll_distance")
    private float scrollDistance; // Khoảng cách scroll (chỉ dùng cho action SCROLL)
    
    @SerializedName("duration_ms")
    private long durationMs; // Thời gian chờ (chỉ dùng cho action WAIT)
    
    // Các hằng số cho action_type
    public static final String ACTION_CLICK = "CLICK";
    public static final String ACTION_INPUT = "INPUT";
    public static final String ACTION_SCROLL = "SCROLL";
    public static final String ACTION_OPEN_APP = "OPEN_APP";
    public static final String ACTION_WAIT = "WAIT";
    
    // Các hằng số cho target_type
    public static final String TARGET_TEXT = "TEXT";
    public static final String TARGET_ID = "ID";
    public static final String TARGET_DESCRIPTION = "DESCRIPTION";
    public static final String TARGET_PACKAGE = "PACKAGE";
    
    // Các hằng số cho scroll_direction
    public static final String SCROLL_UP = "UP";
    public static final String SCROLL_DOWN = "DOWN";
    
    // Constructor mặc định
    public AccessibilityAction() {
        this.inputText = "";
        this.scrollDirection = SCROLL_DOWN;
        this.scrollDistance = 200f;
        this.durationMs = 0L;
    }
    
    // Constructor cho hành động CLICK và OPEN_APP
    public AccessibilityAction(String actionType, String targetType, String targetValue) {
        this();
        this.actionType = actionType;
        this.targetType = targetType;
        this.targetValue = targetValue;
    }
    
    // Constructor cho hành động INPUT
    public AccessibilityAction(String actionType, String targetType, String targetValue, String inputText) {
        this(actionType, targetType, targetValue);
        this.inputText = inputText;
    }
    
    // Constructor cho hành động SCROLL
    public AccessibilityAction(String actionType, String targetType, String targetValue, float scrollDistance) {
        this(actionType, targetType, targetValue);
        this.scrollDistance = scrollDistance;
    }
    
    // Constructor cho hành động WAIT
    public AccessibilityAction(String actionType, String targetType, String targetValue, long durationMs) {
        this(actionType, targetType, targetValue);
        this.durationMs = durationMs;
    }
    
    // Constructor đầy đủ
    public AccessibilityAction(String actionType, String targetType, String targetValue, 
                             String inputText, long durationMs) {
        this();
        this.actionType = actionType;
        this.targetType = targetType;
        this.targetValue = targetValue;
        this.inputText = inputText;
        this.durationMs = durationMs;
    }
    
    // Getters và Setters
    public String getActionType() {
        return actionType;
    }
    
    public void setActionType(String actionType) {
        this.actionType = actionType;
    }
    
    public String getTargetType() {
        return targetType;
    }
    
    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }
    
    public String getTargetValue() {
        return targetValue;
    }
    
    public void setTargetValue(String targetValue) {
        this.targetValue = targetValue;
    }
    
    public String getInputText() {
        return inputText;
    }
    
    public void setInputText(String inputText) {
        this.inputText = inputText;
    }
    
    public String getScrollDirection() {
        return scrollDirection;
    }
    
    public void setScrollDirection(String scrollDirection) {
        this.scrollDirection = scrollDirection;
    }
    
    public float getScrollDistance() {
        return scrollDistance;
    }
    
    public void setScrollDistance(float scrollDistance) {
        this.scrollDistance = scrollDistance;
    }
    
    public long getDurationMs() {
        return durationMs;
    }
    
    public void setDurationMs(long durationMs) {
        this.durationMs = durationMs;
    }
} 