package com.tools.edge.dynamic.island.ui.component.service;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.GestureDescription;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.graphics.Point;
import android.os.Build;
import androidx.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.List;
import java.util.ArrayList;

public class AccessibilityController {
    private final AccessibilityService service;
    private static final String TAG = "AccessibilityController";

    public AccessibilityController(AccessibilityService service) {
        this.service = service;
    }

    /**
     * Mở ứng dụng theo package name
     * @param packageName Package name của ứng dụng cần mở
     * @return true nếu mở thành công, false nếu thất bại
     */
    public boolean openApp(String packageName) {
        if (service == null || packageName == null || packageName.isEmpty()) {
            Log.e(TAG, "Invalid parameters for openApp");
            return false;
        }

        try {
            Intent intent = service.getPackageManager().getLaunchIntentForPackage(packageName);
            if (intent != null) {
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                service.startActivity(intent);
                Log.d(TAG, "Opened app: " + packageName);
                return true;
            } else {
                Log.e(TAG, "Could not find launch intent for package: " + packageName);
                return false;
            }
        } catch (Exception e) {
            Log.e(TAG, "Error opening app: " + packageName, e);
            return false;
        }
    }


    public void inputTextByDescription(String description, String text) {
        if (service == null || description == null || description.isEmpty() || text == null || text.isEmpty()) {
            return;
        }

        AccessibilityNodeInfo rootNode = service.getRootInActiveWindow();
        if (rootNode != null) {
            List<AccessibilityNodeInfo> nodes = new ArrayList<>();
            findNodesByDescription(rootNode, description, nodes);

            if (!nodes.isEmpty()) {
                inputTextIntoView(nodes.get(0), text);

                // Dọn bộ nhớ
                for (AccessibilityNodeInfo node : nodes) {
                    if (node != null) node.recycle();
                }
            }
            rootNode.recycle();
        }
    }


    /**
     * Nhập text vào trường input đang focus
     */
    public void inputText(String text) {
        if (text == null || text.isEmpty() || service == null) {
            return;
        }
        
        Bundle arguments = new Bundle();
        arguments.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, text);
        
        AccessibilityNodeInfo focusedNode = service.getRootInActiveWindow();
        if (focusedNode != null) {
            focusedNode.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
            focusedNode.recycle();
        }
    }

    /**
     * Click vào view được chỉ định
     */
    public void clickOnView(AccessibilityNodeInfo node) {
        if (node == null || service == null) {
            return;
        }

        if (node.isClickable()) {
            node.performAction(AccessibilityNodeInfo.ACTION_CLICK);
        } else {
            // Tìm parent view có thể click
            AccessibilityNodeInfo clickableParent = node;
            while (clickableParent != null && !clickableParent.isClickable()) {
                AccessibilityNodeInfo parent = clickableParent.getParent();
                clickableParent.recycle();
                clickableParent = parent;
            }
            
            if (clickableParent != null) {
                clickableParent.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                clickableParent.recycle();
            }
        }
    }




    /**
     * Tìm và click vào view chứa text
     */
    public void selectViewByText(String text) {
        if (text == null || text.isEmpty() || service == null) {
            return;
        }

        AccessibilityNodeInfo rootNode = service.getRootInActiveWindow();
        if (rootNode != null) {
            List<AccessibilityNodeInfo> nodes = rootNode.findAccessibilityNodeInfosByText(text);

            if (nodes != null && !nodes.isEmpty()) {
                for (AccessibilityNodeInfo node : nodes) {
                    AccessibilityNodeInfo clickableNode = findClickableParent(node);
                    if (clickableNode != null) {
                        clickableNode.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        clickableNode.recycle();
                        break; // chỉ click node đầu tiên hợp lệ
                    }
                }

//                // Dọn bộ nhớ
//                for (AccessibilityNodeInfo node : nodes) {
//                    if (node != null) node.recycle();
//                }
            }
            rootNode.recycle();
        }
    }

    private AccessibilityNodeInfo findClickableParent(AccessibilityNodeInfo node) {
        AccessibilityNodeInfo current = node;
        while (current != null && !current.isClickable()) {
            AccessibilityNodeInfo parent = current.getParent();
            if (current != node) current.recycle();
            current = parent;
        }
        return current;
    }


    /**
     * Click tại tọa độ x,y trên màn hình
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void clickAtPosition(Point position) {
        if (service == null || position == null) {
            return;
        }

        Path clickPath = new Path();
        clickPath.moveTo(position.x, position.y);
        
        GestureDescription.Builder builder = new GestureDescription.Builder();
        builder.addStroke(new GestureDescription.StrokeDescription(clickPath, 0, 100));
        
        service.dispatchGesture(builder.build(), null, null);
    }

    /**
     * Scroll màn hình lên xuống
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void scrollInDirection(int direction, float distance) {
        if (service == null) {
            return;
        }

        DisplayMetrics metrics = service.getResources().getDisplayMetrics();
        Point start = new Point(metrics.widthPixels / 2, metrics.heightPixels / 2);
        Point end = new Point(start.x, start.y);

        switch (direction) {
            case AccessibilityNodeInfo.ACTION_SCROLL_FORWARD:
                end.y -= (int)(distance * metrics.density); // Lên
                break;
            case AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD:
                end.y += (int)(distance * metrics.density); // Xuống
                break;
        }

        scroll(start, end);
    }

    /**
     * Scroll màn hình
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void scroll(Point start, Point end) {
        if (service == null || start == null || end == null) {
            return;
        }

        Path scrollPath = new Path();
        scrollPath.moveTo(start.x, start.y);
        scrollPath.lineTo(end.x, end.y);

        GestureDescription.Builder builder = new GestureDescription.Builder();
        builder.addStroke(new GestureDescription.StrokeDescription(scrollPath, 0, 500));

        service.dispatchGesture(builder.build(), null, null);
    }

    /**
     * Long press tại vị trí
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void longPress(Point position) {
        if (service == null || position == null) {
            return;
        }

        Path clickPath = new Path();
        clickPath.moveTo(position.x, position.y);

        GestureDescription.Builder builder = new GestureDescription.Builder();
        builder.addStroke(new GestureDescription.StrokeDescription(clickPath, 0, 1000)); // 1 giây

        service.dispatchGesture(builder.build(), null, null);
    }

    /**
     * Focus vào view
     */
    public void focusView(AccessibilityNodeInfo node) {
        if (node != null) {
            node.performAction(AccessibilityNodeInfo.ACTION_FOCUS);
        }
    }

    /**
     * Copy text đã select
     */
    public void copySelectedText(AccessibilityNodeInfo node) {
        if (node != null) {
            node.performAction(AccessibilityNodeInfo.ACTION_COPY);
        }
    }

    /**
     * Paste text vào view đang focus
     */
    public void pasteText(AccessibilityNodeInfo node) {
        if (node != null) {
            node.performAction(AccessibilityNodeInfo.ACTION_PASTE);
        }
    }

    /**
     * Click vào view với ID resource
     */
    public void clickViewById(String viewId) {
        if (service == null || viewId == null || viewId.isEmpty()) {
            return;
        }

        AccessibilityNodeInfo rootNode = service.getRootInActiveWindow();
        if (rootNode != null) {
            List<AccessibilityNodeInfo> nodes = rootNode.findAccessibilityNodeInfosByViewId(viewId);
            
            if (nodes != null && !nodes.isEmpty()) {
                clickOnView(nodes.get(0));
                
                // Clean up
                for (AccessibilityNodeInfo node : nodes) {
                    if (node != null) {
                        node.recycle();
                    }
                }
            }
            rootNode.recycle();
        }
    }

    /**
     * Click vào view với content description
     */
    public void clickViewByDescription(String description) {
        if (service == null || description == null || description.isEmpty()) {
            return;
        }

        AccessibilityNodeInfo rootNode = service.getRootInActiveWindow();
        if (rootNode != null) {
            List<AccessibilityNodeInfo> nodes = new ArrayList<>();
            findNodesByDescription(rootNode, description, nodes);
            
            if (!nodes.isEmpty()) {
                clickOnView(nodes.get(0));
                
                // Clean up
                for (AccessibilityNodeInfo node : nodes) {
                    if (node != null) {
                        node.recycle();
                    }
                }
            }
            rootNode.recycle();
        }
    }

    private void findNodesByDescription(AccessibilityNodeInfo node, String description, List<AccessibilityNodeInfo> results) {
        if (node == null) return;

        if (description.equals(node.getContentDescription())) {
            results.add(node);
        }

        for (int i = 0; i < node.getChildCount(); i++) {
            findNodesByDescription(node.getChild(i), description, results);
        }
    }

    /**
     * Input text vào view được chỉ định
     */
    public void inputTextIntoView(AccessibilityNodeInfo node, String text) {
        if (node == null || text == null || text.isEmpty() || service == null) {
            return;
        }

        // Focus vào view trước
        focusView(node);
        
        // Clear text hiện tại nếu có
        Bundle clearArguments = new Bundle();
        clearArguments.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, "");
        node.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, clearArguments);
        
        // Input text mới
        Bundle arguments = new Bundle();
        arguments.putCharSequence(AccessibilityNodeInfo.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE, text);
        node.performAction(AccessibilityNodeInfo.ACTION_SET_TEXT, arguments);
    }

    /**
     * Input text vào view với ID resource
     */
    public void inputTextById(String viewId, String text) {
        if (service == null || viewId == null || viewId.isEmpty() || text == null || text.isEmpty()) {
            return;
        }

        AccessibilityNodeInfo rootNode = service.getRootInActiveWindow();
        if (rootNode != null) {
            List<AccessibilityNodeInfo> nodes = rootNode.findAccessibilityNodeInfosByViewId(viewId);
            
            if (nodes != null && !nodes.isEmpty()) {
                inputTextIntoView(nodes.get(0), text);
                
                // Clean up
                for (AccessibilityNodeInfo node : nodes) {
                    if (node != null) {
                        node.recycle();
                    }
                }
            }
            rootNode.recycle();
        }
    }
} 