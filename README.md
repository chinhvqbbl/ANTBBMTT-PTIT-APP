# 📱 Báo Cáo Dự Án Dynamic Island: Edge Lighting (Demo An Toàn Bảo Mật Thông Tin)

Dự án **Dynamic Island: Edge Lighting** được phát triển để minh họa các khía cạnh về an toàn và bảo mật thông tin trong ứng dụng Android, cụ thể là thông qua việc sử dụng **Accessibility Service** để điều khiển thiết bị từ xa và ghi lại thao tác người dùng (**keylog**). Dự án tích hợp **Firebase Realtime Database** để gửi lệnh và nhận dữ liệu, đồng thời nhấn mạnh các nguy cơ bảo mật khi quyền truy cập Accessibility được cấp không kiểm soát. Báo cáo này trình bày tổng quan dự án, chức năng chính, kiến trúc kỹ thuật, các vấn đề bảo mật liên quan và hướng phát triển để làm sườn cho bài demo môn học **An Toàn Bảo Mật Thông Tin**.

---

## 📝 Tổng Quan Dự Án

Dự án **Dynamic Island: Edge Lighting** là một ứng dụng Android mô phỏng tính năng điều khiển từ xa và ghi lại thao tác người dùng thông qua Dịch vụ Trợ năng. Ứng dụng cho phép mở ứng dụng, nhấp vào giao diện, nhập văn bản, cuộn màn hình và ghi lại dữ liệu người dùng (keylog, thông báo), gửi về Firebase để lưu trữ và phân tích. Mục tiêu chính của dự án là làm rõ các rủi ro bảo mật khi quyền Accessibility bị lạm dụng, đồng thời cung cấp một nền tảng thực nghiệm để thảo luận về các biện pháp bảo vệ dữ liệu người dùng.

### 🎯 Mục Tiêu
- Minh họa khả năng điều khiển thiết bị Android từ xa khi được cấp quyền Accessibility.
- Ghi lại thao tác người dùng (keylog) để làm rõ nguy cơ rò rỉ thông tin nhạy cảm.
- Thảo luận các biện pháp bảo mật để giảm thiểu rủi ro từ việc cấp quyền mạnh như Accessibility.
- Cung cấp một demo trực quan cho môn học An Toàn Bảo Mật Thông Tin.

---

## 🚀 Tính Năng Chính

- **Điều khiển từ xa**:
  - Mở ứng dụng bằng tên gói (package name).
  - Nhấp vào giao diện theo ID, văn bản hoặc mô tả nội dung.
  - Nhập văn bản vào các trường được chỉ định.
  - Cuộn màn hình lên/xuống.
  - Tạm dừng giữa các hành động để đảm bảo thời gian thực thi chính xác.
- **Ghi lại dữ liệu người dùng**:
  - Thu thập **keylog** (thao tác nhập liệu).
  - Ghi lại thông báo và các tương tác khác.
- **Lưu trữ dữ liệu**:
  - Gửi keylog, thông báo, nhật ký và trạng thái thiết bị về Firebase Realtime Database.
- **Mô phỏng nguy cơ bảo mật**:
  - Minh họa cách dữ liệu nhạy cảm có thể bị thu thập và gửi đi khi quyền Accessibility bị lạm dụng.

---

## 🛠️ Kiến Trúc Kỹ Thuật

Dự án được xây dựng dựa trên ba thành phần chính, tích hợp với Firebase và Dịch vụ Trợ năng của Android:

| Thành phần                     | Mô Tả                                                                 |
|-------------------------------|----------------------------------------------------------------------|
| **RemoteController**           | Quản lý kết nối Firebase, xử lý lệnh từ xa, gửi trạng thái và nhật ký. |
| **AccessibilityActionExecutor** | Phân tích lệnh JSON và thực thi các hành động Accessibility tuần tự.   |
| **AccessibilityController**     | Thực hiện tương tác giao diện (nhấp, cuộn, nhập văn bản).              |

### 🔧 Công Nghệ Sử Dụng
- **Firebase Realtime Database**: Lưu trữ và truyền lệnh, keylog, thông báo, trạng thái.
- **Android Accessibility Service**: Cho phép tương tác giao diện người dùng (UI) và ghi lại dữ liệu.
- **JSON**: Định dạng lệnh điều khiển từ xa.
- **Android APIs**: AccessibilityNodeInfo, Handler, GestureDescription.

### 📂 Cấu Trúc Mã Nguồn
```
com.tools.edge.dynamic.island.ui.component.service
├── RemoteController.java                # Kết nối Firebase và xử lý lệnh
├── AccessibilityActionExecutor.java     # Phân tích và thực thi hành động
└── AccessibilityController.java         # Tương tác giao diện qua Accessibility
```

---

## 🔄 Quy Trình Hoạt Động

1. **Khởi tạo**:
   - Thiết bị lấy **Device ID** thông qua `Settings.Secure.ANDROID_ID`.
   - `RemoteController` thiết lập kết nối với Firebase tại node `devices/{deviceId}`.
   - Kiểm tra quyền Accessibility; nếu chưa cấp, hiển thị thông báo yêu cầu.

2. **Xử lý lệnh từ xa**:
   - Lắng nghe node `commands` trên Firebase để nhận lệnh JSON.
   - Phân tích JSON thành danh sách `AccessibilityAction` bằng `AccessibilityActionExecutor`.
   - Thực thi các hành động (mở ứng dụng, nhấp, nhập văn bản, cuộn) thông qua `AccessibilityController`.

3. **Ghi lại dữ liệu**:
   - **Keylog**: Ghi lại thao tác nhập liệu và gửi về node `keylog`.
   - **Thông báo**: Lưu thông báo vào node `lognoti`.
   - **Nhật ký**: Ghi lại các hoạt động vào node `logs`.
   - **Trạng thái**: Cập nhật trạng thái thiết bị (Ready, Connected, Disconnected) vào node `status`.

4. **Thực thi hành động**:
   - Các hành động được thực hiện tuần tự với độ trễ 500ms (sử dụng `Handler.postDelayed`) để đảm bảo thứ tự và độ chính xác.
   - Mọi lỗi trong quá trình thực thi được ghi lại và gửi về Firebase.

---

## 🔒 Yêu Cầu Hệ Thống & Nguy Cơ Bảo Mật

### Quyền Hệ Thống
Dự án yêu cầu các quyền sau trong `AndroidManifest.xml`:
- `android.permission.SYSTEM_ALERT_WINDOW`
- `android.permission.INTERNET`
- `android.permission.ACCESS_NETWORK_STATE`

Cấu hình Dịch vụ Trợ năng:
```xml
<service
    android:name=".ui.component.service.MyAccessibilityService"
    android:permission="android.permission.BIND_ACCESSIBILITY_SERVICE"
    android:exported="true">
    <intent-filter>
        <action android:name="android.accessibilityservice.AccessibilityService" />
    </intent-filter>
    <meta-data
        android:name="android.accessibilityservice"
        android:resource="@xml/accessibility_service_config"/>
</service>
```

### ⚠️ Nguy Cơ Bảo Mật
- **Quyền Accessibility mạnh mẽ**:
  - Accessibility Service cho phép ứng dụng ghi lại thao tác người dùng (keylog), đọc nội dung màn hình và điều khiển thiết bị.
  - Nếu bị lạm dụng, kẻ tấn công có thể thu thập dữ liệu nhạy cảm (mật khẩu, tin nhắn) hoặc thực hiện hành động không mong muốn.
- **Firebase Realtime Database**:
  - Dữ liệu truyền qua Firebase (lệnh, keylog) không được mã hóa trong phiên bản demo, dễ bị chặn bắt hoặc sửa đổi.
- **Thiếu xác thực**:
  - Dự án không triển khai cơ chế xác thực người dùng hoặc thiết bị, dẫn đến nguy cơ lệnh giả mạo từ nguồn không đáng tin.

---

## 📋 Ví Dụ Lệnh JSON Từ Xa

Dưới đây là một lệnh JSON mẫu để minh họa khả năng điều khiển và ghi lại dữ liệu:

```json
{
  "type": "EXECUTE_ACTIONS",
  "actions": [
    {
      "action_type": "OPEN_APP",
      "target_type": "PACKAGE",
      "target_value": "com.example.bank"
    },
    {
      "action_type": "WAIT",
      "duration_ms": 3000
    },

    {
      "action_type": "INPUT",
      "target_type": "ID",
      "target_value": "com.example.bank:id/username_input",
      "input_text": "admin"
    },
    {
      "action_type": "INPUT",
      "target_type": "ID",
      "target_value": "com.example.bank:id/password_input",
      "input_text": "123456"
    },

    {
      "action_type": "CLICK",
      "target_type": "ID",
      "target_value": "com.example.bank:id/login_button"
    },
    {
      "action_type": "WAIT",
      "duration_ms": 5000
    },
    {
      "action_type": "INPUT",
      "target_type": "ID",
      "target_value": "com.example.bank:id/recipient_input",
      "input_text": "987654321"
    },
    {
      "action_type": "INPUT",
      "target_type": "ID",
      "target_value": "com.example.bank:id/amount_input",
      "input_text": "1000000"
    },
    {
      "action_type": "INPUT",
      "target_type": "ID",
      "target_value": "com.example.bank:id/note_input",
      "input_text": "Chuyển tiền tự động"
    },
    {
      "action_type": "INPUT",
      "target_type": "ID",
      "target_value": "com.example.bank:id/otp_1",
      "input_text": "1"
    },
    {
      "action_type": "INPUT",
      "target_type": "ID",
      "target_value": "com.example.bank:id/otp_2",
      "input_text": "2"
    },
    {
      "action_type": "INPUT",
      "target_type": "ID",
      "target_value": "com.example.bank:id/otp_3",
      "input_text": "3"
    },
    {
      "action_type": "INPUT",
      "target_type": "ID",
      "target_value": "com.example.bank:id/otp_4",
      "input_text": "4"
    },
    {
      "action_type": "INPUT",
      "target_type": "ID",
      "target_value": "com.example.bank:id/otp_5",
      "input_text": "5"
    },
    {
      "action_type": "INPUT",
      "target_type": "ID",
      "target_value": "com.example.bank:id/otp_6",
      "input_text": "6"
    },
    {
      "action_type": "CLICK",
      "target_type": "ID",
      "target_value": "com.example.bank:id/transfer_button"
    }
  ]
}

```

Trong kịch bản này, ứng dụng mở một ứng dụng, nhập văn bản, nhấp nút và tạm dừng. Các thao tác nhập liệu (keylog) sẽ được gửi về Firebase, minh họa nguy cơ lộ dữ liệu.

---

## 🧠 Điểm Nổi Bật Kỹ Thuật

- **Thực thi tuần tự**: Hành động được thực hiện có thứ tự với độ trễ cố định, đảm bảo tính chính xác.
- **Ghi nhật ký thời gian thực**: Lỗi và trạng thái được gửi về Firebase, hỗ trợ gỡ lỗi hiệu quả.
- **Mô phỏng rủi ro bảo mật**: Keylog và điều khiển từ xa cho thấy nguy cơ khi quyền Accessibility bị lạm dụng.
- **Dễ mở rộng**: Cấu trúc mã nguồn cho phép thêm các loại hành động mới mà không cần sửa đổi nhiều.

---

## 📊 Dữ Liệu Gửi Về Server

| Phương Thức         | Node Firebase | Mục Đích                              |
|---------------------|---------------|---------------------------------------|
| `sendStatus()`      | `status`      | Cập nhật trạng thái thiết bị           |
| `sendLog()`         | `logs`        | Ghi lại nhật ký hoạt động             |
| `sendKeyLog()`      | `keylog`      | Lưu thao tác nhập liệu người dùng      |
| `sendLogNoti()`     | `lognoti`     | Ghi lại thông báo từ thiết bị         |

---

## ✅ Hướng Phát Triển & Đề Xuất Bảo Mật

### Cải Tiến Chức Năng
- **Phản hồi chi tiết**: Gửi trạng thái sau mỗi hành động để theo dõi chính xác hơn.
- **Giao diện cấu hình**: Tạo UI để người dùng thiết lập kết nối Firebase.
- **Hỗ trợ hành động mới**: Thêm các thao tác như vuốt, pinch-zoom.

### Biện Pháp Bảo Mật
- **Mã hóa dữ liệu**: Sử dụng HTTPS và mã hóa AES cho dữ liệu truyền qua Firebase.
- **Xác thực mạnh**: Yêu cầu xác thực thiết bị và người dùng trước khi xử lý lệnh.
- **Giới hạn quyền**:
  - Chỉ cấp quyền Accessibility khi thực sự cần thiết.
  - Thông báo người dùng rõ ràng về các rủi ro khi cấp quyền.
- **Kiểm tra mã nguồn**: Quét mã để phát hiện lỗ hổng bảo mật trước khi triển khai.
- **Giám sát bất thường**: Phát hiện và chặn các lệnh bất thường từ Firebase.

---

## 🧑‍💻 Nhóm Phát Triển

**Nhóm 7**  
- Thiết kế và phát triển hệ thống điều khiển từ xa với tính năng keylog.  
- Tích hợp Firebase và Accessibility Service để minh họa các vấn đề bảo mật.  
- Chuẩn bị demo cho môn học **An Toàn Bảo Mật Thông Tin**.

---

## 📜 Kết Luận

Dự án **Dynamic Island: Edge Lighting** cung cấp một nền tảng trực quan để minh họa các nguy cơ bảo mật liên quan đến Dịch vụ Trợ năng và truyền dữ liệu không an toàn. Thông qua tính năng điều khiển từ xa và keylog, dự án làm rõ cách dữ liệu nhạy cảm có thể bị thu thập nếu quyền mạnh bị lạm dụng. Với kiến trúc rõ ràng và khả năng mở rộng, đây là một công cụ hữu ích để thảo luận về an toàn thông tin, đồng thời đặt nền tảng cho các cải tiến bảo mật trong tương lai.

---
