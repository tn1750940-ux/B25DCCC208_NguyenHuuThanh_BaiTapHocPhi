# Bài tập Lập trình hướng đối tượng – Quản lý học phí

- **Họ tên:** <HỌ TÊN>
- **Mã sinh viên:** <MÃ SV>
- **Lớp:** <LỚP>

## Cấu trúc thư mục

| Thư mục | Nội dung | Kỹ thuật OOP chính |
|---|---|---|
| `Cau1/Main.java` | Chuẩn hóa thông tin sinh viên | Lớp `SinhVien`, chuẩn hóa họ tên / ngày sinh trong constructor |
| `Cau2/Main.java` | Tính học phí theo hệ đào tạo | Lớp trừu tượng `SinhVien` với `hocPhi()`; lớp con `SinhVienChinhQuy`, `SinhVienChatLuongCao`, `SinhVienLienThong` ghi đè |
| `Cau3/Main.java` | Quản lý danh sách học phí | Ngoại lệ tự định nghĩa `DuLieuKhongHopLeException`, lớp `QuanLyHocPhi` dùng `ArrayList`, sắp xếp theo học phí giảm dần / mã tăng dần |
| `Cau4/Main.java` | Miễn giảm học phí & thống kê theo hệ | Interface `MienGiam` (`long tinhGiam(long hocPhi)`), các lớp `ConChinhSach`, `HoNgheo`, `HocBong`; `SinhVien` có thuộc tính kiểu `MienGiam` (quan hệ kết hợp) |

Mỗi câu là một file `Main.java` độc lập, chỉ có một lớp `public Main`, không khai báo `package`.
Mỗi thư mục có sẵn `input.txt` là dữ liệu ví dụ trong đề.

## Bảng tính học phí (đơn giá 450.000 đ/tín chỉ)

| Mã hệ | Công thức |
|---|---|
| CQ | số tín chỉ × 450.000 |
| CLC | số tín chỉ × 450.000 × 1,5 |
| LT | số tín chỉ × 450.000 + 2.000.000 |

| Mã miễn giảm | Số tiền giảm |
|---|---|
| CS | 50% học phí |
| HN | 70% học phí |
| HB | 3.000.000 (không vượt quá học phí) |

## Hướng dẫn chạy

Yêu cầu: JDK 8 trở lên.

```bash
cd Cau1            # hoặc Cau2, Cau3, Cau4
javac -encoding UTF-8 Main.java
java Main < input.txt
```

Trên Windows PowerShell (không hỗ trợ `<`), dùng:

```powershell
cmd /c "java Main < input.txt"
```

Hoặc chạy `java Main` rồi nhập dữ liệu trực tiếp từ bàn phím.
