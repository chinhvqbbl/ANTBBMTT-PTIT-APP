package com.tools.edge.dynamic.island.ui.component.example;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.tools.edge.dynamic.island.R;
import com.tools.edge.dynamic.island.ui.component.service.ITGAccessibilityService;

/**
 * Activity ví dụ về cách sử dụng AccessibilityAction
 */
public class AccessibilityExampleActivity extends AppCompatActivity {

    private LinearLayout mainLayout;
    private Button executeButton;
    private Button addCustomButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessibility_example);

        // Khởi tạo các view
        mainLayout = findViewById(R.id.main_layout);
        executeButton = findViewById(R.id.execute_button);
        addCustomButton = findViewById(R.id.add_custom_button);

        // Thiết lập sự kiện cho nút thực thi
        executeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeAccessibilityActions();
            }
        });

        // Thiết lập sự kiện cho nút thêm nút tùy chỉnh
        addCustomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCustomAccessibilityButton();
            }
        });
    }

    /**
     * Thực thi các hành động accessibility
     */
    private void executeAccessibilityActions() {
        // Kiểm tra xem service có đang chạy không
        ITGAccessibilityService service = ITGAccessibilityService.getInstance();
        if (service == null) {
            Toast.makeText(this, "Accessibility Service chưa được kích hoạt", Toast.LENGTH_SHORT).show();
            return;
        }

        // Thực thi các hành động
        AccessibilityExample.exampleUsage(service);
        Toast.makeText(this, "Đã thực thi các hành động", Toast.LENGTH_SHORT).show();
    }

    /**
     * Thêm nút tùy chỉnh vào layout
     */
    private void addCustomAccessibilityButton() {
        // Tạo nút mới
        Button button = new Button(this);
        button.setText("Nút tùy chỉnh");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra xem service có đang chạy không
                ITGAccessibilityService service = ITGAccessibilityService.getInstance();
                if (service == null) {
                    Toast.makeText(AccessibilityExampleActivity.this, 
                            "Accessibility Service chưa được kích hoạt", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Thực thi các hành động
                AccessibilityExample.exampleUsage(service);
                Toast.makeText(AccessibilityExampleActivity.this, 
                        "Đã thực thi các hành động từ nút tùy chỉnh", Toast.LENGTH_SHORT).show();
            }
        });

        // Thêm nút vào layout
        mainLayout.addView(button);
    }
} 