package com.tools.edge.dynamic.island.ui.component.example;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tools.edge.dynamic.island.R;
import com.tools.edge.dynamic.island.ui.component.model.AccessibilityAction;
import com.tools.edge.dynamic.island.ui.component.service.AccessibilityActionExecutor;
import com.tools.edge.dynamic.island.ui.component.service.AccessibilityController;
import com.tools.edge.dynamic.island.ui.component.service.ITGAccessibilityService;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Lớp ví dụ về cách sử dụng các hành động accessibility từ file JSON
 */
public class AccessibilityExample {
    private static final String TAG = "AccessibilityExample";

    /**
     * Ví dụ về cách sử dụng các hành động accessibility từ file JSON
     * @param service AccessibilityService
     */
    public static void exampleUsage(ITGAccessibilityService service) {
        if (service == null) {
            Log.e(TAG, "AccessibilityService is null");
            return;
        }

        // Tạo AccessibilityController
        AccessibilityController controller = new AccessibilityController(service);
        
        // Tạo AccessibilityActionExecutor
        AccessibilityActionExecutor executor = new AccessibilityActionExecutor(service, controller);
        
        // Thực thi các hành động từ file JSON
        executor.executeActionsFromJson("accessibility_actions.json");
    }


    /**
     * Xử lý dữ liệu JSON từ server
     * @param service AccessibilityService
     * @param jsonData Dữ liệu JSON từ server
     */
    public static void processServerData(ITGAccessibilityService service, String jsonData) {
        if (service == null || jsonData == null || jsonData.isEmpty()) {
            Log.e(TAG, "Invalid parameters");
            return;
        }

//        // Tạo AccessibilityController
//        AccessibilityController controller = new AccessibilityController(service);
//
//        // Tạo AccessibilityActionExecutor
//        AccessibilityActionExecutor executor = new AccessibilityActionExecutor(service, controller);
//
//        // Thực thi các hành động từ chuỗi JSON
//        executor.executeActionsFromJsonString(jsonData);
    }

    /**
     * Thêm nút để thực thi các hành động accessibility
     * @param activity Activity hiện tại
     * @param layoutId ID của layout để thêm nút
     */
    public static void addAccessibilityButton(Activity activity, int layoutId) {
        if (activity == null) {
            return;
        }

        // Kiểm tra xem AccessibilityService có đang chạy không
        ITGAccessibilityService service = ITGAccessibilityService.getInstance();
        if (service == null) {
            Toast.makeText(activity, "Vui lòng bật Accessibility Service", Toast.LENGTH_SHORT).show();
            return;
        }

        // Thêm nút vào layout
        View layout = activity.findViewById(layoutId);
        if (layout != null) {
            Button button = new Button(activity);
            button.setText("Thực thi hành động");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exampleUsage(service);
                    Toast.makeText(activity, "Đã thực thi các hành động", Toast.LENGTH_SHORT).show();
                }
            });
//            layout.addView(button);
        }
    }

    /**
     * Thêm nút vào một view cụ thể
     * @param context Context
     * @param parentView View cha để thêm nút
     */
    public static void addAccessibilityButtonToView(Context context, View parentView) {
        if (context == null || parentView == null) {
            return;
        }

        // Kiểm tra xem AccessibilityService có đang chạy không
        ITGAccessibilityService service = ITGAccessibilityService.getInstance();
        if (service == null) {
            Toast.makeText(context, "Vui lòng bật Accessibility Service", Toast.LENGTH_SHORT).show();
            return;
        }

        // Thêm nút vào view
        if (parentView instanceof android.view.ViewGroup) {
            android.view.ViewGroup viewGroup = (android.view.ViewGroup) parentView;
            Button button = new Button(context);
            button.setText("Thực thi hành động");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    exampleUsage(service);
                    Toast.makeText(context, "Đã thực thi các hành động", Toast.LENGTH_SHORT).show();
                }
            });
            viewGroup.addView(button);
        }
    }
} 