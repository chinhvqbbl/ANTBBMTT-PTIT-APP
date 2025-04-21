package com.tools.edge.dynamic.island.ui.component.service;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.accessibility.AccessibilityNodeInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.tools.edge.dynamic.island.ui.component.model.AccessibilityAction;

/**
 * Lớp thực thi các hành động accessibility từ file JSON
 */
public class AccessibilityActionExecutor {
    private static final String TAG = "AccessibilityActionExecutor";
    private final Context context;
    private final AccessibilityController controller;
    private final Handler handler;

    public AccessibilityActionExecutor(Context context, AccessibilityController controller) {
        this.context = context;
        this.controller = controller;
        this.handler = new Handler(Looper.getMainLooper());
    }

    /**
     * Thực thi các hành động từ file JSON
     *
     * @param jsonFileName Tên file JSON trong thư mục assets
     */
    public void executeActionsFromJson(String jsonFileName) {
        try {
            String jsonString = loadJsonFromAssets(jsonFileName);
            List<AccessibilityAction> actions = parseJsonToActions(jsonString);
            executeActions(actions);
        } catch (Exception e) {
            Log.e(TAG, "Error executing actions from JSON: " + e.getMessage());
        }
    }

    /**
     * Thực thi các hành động từ chuỗi JSON
     *
     * @param jsonString Chuỗi JSON chứa các hành động
     */
    public void executeActionsFromJsonString(String jsonString) {
        try {
            List<AccessibilityAction> actions = parseJsonToActions(jsonString);
            executeActions(actions);
        } catch (Exception e) {
            Log.e(TAG, "Error executing actions from JSON string: " + e.getMessage());
        }
    }

    /**
     * Thực thi danh sách các hành động
     *
     * @param actions Danh sách các hành động cần thực thi
     */
    public void executeActions(List<AccessibilityAction> actions) {
        if (actions == null || actions.isEmpty()) {
            Log.w(TAG, "No actions to execute");
            return;
        }

        // Thực thi từng hành động với độ trễ
        for (int i = 0; i < actions.size(); i++) {
            final AccessibilityAction action = actions.get(i);
            final int index = i;

            // Thêm độ trễ giữa các hành động
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    executeAction(action);

                    // Nếu đây là hành động cuối cùng, thông báo hoàn thành
                    if (index == actions.size() - 1) {
                        Log.d(TAG, "All actions executed successfully");
                    }
                }
            }, i * 500); // Độ trễ 500ms giữa các hành động
        }
    }

    /**
     * Thực thi một hành động
     *
     * @param action Hành động cần thực thi
     */
    public void executeAction(AccessibilityAction action) {
        if (action == null) {
            return;
        }

        String actionType = action.getActionType();
        String targetType = action.getTargetType();
        String targetValue = action.getTargetValue();
        String inputText = action.getInputText();
        long durationMs = action.getDurationMs();

        // Xử lý hành động chờ
        if (AccessibilityAction.ACTION_WAIT.equals(actionType)) {
            try {
                Thread.sleep(durationMs);
                Log.d(TAG, "Waited for " + durationMs + "ms");
            } catch (InterruptedException e) {
                Log.e(TAG, "Error waiting: " + e.getMessage());
            }
            return;
        }

        // Xử lý các hành động khác
        switch (actionType) {
            case AccessibilityAction.ACTION_OPEN_APP:
                if (AccessibilityAction.TARGET_PACKAGE.equals(targetType)) {
                    boolean success = controller.openApp(targetValue);
                    Log.d(TAG, "Opening app " + targetValue + ": " + (success ? "success" : "failed"));
                }
                break;

            case AccessibilityAction.ACTION_CLICK:
                if (AccessibilityAction.TARGET_ID.equals(targetType)) {
                    controller.clickViewById(targetValue);
                    Log.d(TAG, "Clicked view with ID: " + targetValue);
                } else if (AccessibilityAction.TARGET_TEXT.equals(targetType)) {
                    controller.selectViewByText(targetValue);
                    Log.d(TAG, "Clicked view with text: " + targetValue);
                } else if (AccessibilityAction.TARGET_DESCRIPTION.equals(targetType)) {
                    controller.clickViewByDescription(targetValue);
                    Log.d(TAG, "Clicked view with description: " + targetValue);
                }
                break;

            case AccessibilityAction.ACTION_INPUT:
                if ("FOCUSED".equals(targetType)) {
                    controller.inputText(inputText);
                    Log.d(TAG, "Input text '" + inputText + "' into focused view");
                } else if (AccessibilityAction.TARGET_ID.equals(targetType)) {
                    controller.inputTextById(targetValue, inputText);
                    Log.d(TAG, "Input text '" + inputText + "' into view with ID: " + targetValue);
                } else if ("DESCRIPTION".equals(targetType)) {
                    controller.inputTextByDescription(targetValue, inputText);
                    Log.d(TAG, "Input text '" + inputText + "' into view with description: " + targetValue);
                }
                break;


            case AccessibilityAction.ACTION_SCROLL:
                if (AccessibilityAction.SCROLL_DOWN.equals(action.getScrollDirection())) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        controller.scrollInDirection(AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD, action.getScrollDistance());
                        Log.d(TAG, "Scrolled down by " + action.getScrollDistance());
                    }
                } else if (AccessibilityAction.SCROLL_UP.equals(action.getScrollDirection())) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        controller.scrollInDirection(AccessibilityNodeInfo.ACTION_SCROLL_FORWARD, action.getScrollDistance());
                        Log.d(TAG, "Scrolled up by " + action.getScrollDistance());
                    }
                }
                break;

            default:
                Log.w(TAG, "Unknown action type: " + actionType);
                break;
        }
    }





    /**
     * Đọc file JSON từ thư mục assets
     *
     * @param fileName Tên file JSON
     * @return Chuỗi JSON
     * @throws IOException Nếu không thể đọc file
     */
    private String loadJsonFromAssets(String fileName) throws IOException {
        InputStream is = context.getAssets().open(fileName);
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        return new String(buffer, StandardCharsets.UTF_8);
    }

    /**
     * Phân tích chuỗi JSON thành danh sách các hành động
     *
     * @param jsonString Chuỗi JSON
     * @return Danh sách các hành động
     * @throws JSONException Nếu chuỗi JSON không hợp lệ
     */
    private List<AccessibilityAction> parseJsonToActions(String jsonString) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray actionsArray = jsonObject.getJSONArray("actions");

        List<AccessibilityAction> actions = new ArrayList<>();
        for (int i = 0; i < actionsArray.length(); i++) {
            JSONObject actionJson = actionsArray.getJSONObject(i);
            AccessibilityAction action = new AccessibilityAction();
            action.setActionType(actionJson.getString("action_type"));
            action.setTargetType(actionJson.optString("target_type", null));
            action.setTargetValue(actionJson.optString("target_value", null));
            action.setInputText(actionJson.optString("input_text", null));
            action.setDurationMs(actionJson.optInt("duration_ms", 0));
            actions.add(action);
        }

        return actions;
    }

}
