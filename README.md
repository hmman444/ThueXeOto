# LTDD

## 📚 Đồ Án Lập Trình Android - Kỳ 2 (2024-2025)

### 🌐 Giới thiệu
Đây là đồ án môn học **Lập Trình Di Động**, phát triển một ứng dụng **thuê xe hơi** trên nền tảng **Android**. Dự án sử dụng công nghệ **Java** cho Android và **Spring Boot** để xây dựng API backend.

---

## 🔧 Công nghệ sử dụng
### 💻 Backend:
- **Ngôn ngữ:** Java (Spring Boot)
- **Framework:** Spring Boot
- **Database:** MySQL
- **ORM:** Spring Data JPA (Hibernate)
- **API:** RESTful API
- **Bảo mật:** Spring Security (JWT nếu cần)
- **Test API:** Postman

### 🎮 Frontend (Android App):
- **Ngôn ngữ:** Java
- **Framework:** Android SDK
- **Networking:** Retrofit2
- **UI:** XML + View Binding
- **Quản lý dữ liệu:** Room Database / SharedPreferences
- **Kiến trúc:** MVVM

---

## 📝 Tiến độ thực hiện
### 📅 Tuần 1-2: Khởi tạo dự án
- [x] Xây dựng tài liệu yêu cầu
- [x] Tạo repo Git & cấu hình `.gitignore`
- [x] Cài đặt môi trường phát triển
- [x] Thiết kế database MySQL

### 📅 Tuần 3-4: Xây dựng Backend
- [x] Khởi tạo dự án Spring Boot
- [x] Cấu hình MySQL & JPA
- [ ] Xây dựng API cho thuê xe hơi (đang làm)
- [ ] Viết tài liệu API (Swagger/Postman)

### 📅 Tuần 5-6: Xây dựng Android App
- [ ] Thiết kế giao diện (XML)
- [ ] Kết nối API bằng Retrofit2
- [ ] Xây dựng chức năng đăng nhập & quản lý tài khoản
- [ ] Tích hợp bản đồ & định vị

### 📅 Tuần 7-8: Hoàn thiện & Deploy
- [ ] Kiểm thử ứng dụng
- [ ] Tối ưu hiệu suất
- [ ] Đưa API lên hosting (Google Cloud Run)
- [ ] Xuất file APK & demo ứng dụng

---

## ⚙️ Hướng dẫn cài đặt
### 1️⃣ Cài đặt Backend
```bash
# Clone repo
git clone https://github.com/yourusername/LTDD.git
cd LTDD/backend

# Cấu hình database (MySQL)
# Sửa file application.properties với thông tin database của bạn

# Chạy ứng dụng Spring Boot
mvn spring-boot:run
