========================================================================================================================
                                     HỌC VIỆN CÔNG NGHỆ BƯU CHÍNH VIỄN THÔNG (PTIT)
                                           LẬP TRÌNH HƯỚNG ĐỐI TƯỢNG (INT1332)
========================================================================================================================
ĐỀ TÀI: HỆ THỐNG QUẢN LÝ NHÂN SỰ & BẢNG LƯƠNG DOANH NGHIỆP (HR PAYROLL SYSTEM)
NHÓM THỰC HIỆN: NHÓM 08
========================================================================================================================

I. DANH MỤC CÁC TỆP BẢN VẼ TRONG THƯ MỤC UML/
------------------------------------------------------------------------------------------------------------------------
STT | Tên Tệp Tin                            | Cụm Kiến Trúc Phân Tích & Nội Dung Mô Tả Chi Tiết
----+----------------------------------------+--------------------------------------------------------------------------
00  | 00_SO_DO_TONG_THE_HE_THONG.txt         | Bản vẽ kiến trúc tổng thể toàn hệ thống, liên kết 6 Package, bảng tra cứu
    |                                        | toàn diện 24 thực thể, luồng hoạt động xuyên tầng (Startup, Create, Calc)
----+----------------------------------------+--------------------------------------------------------------------------
01  | 01_CUM_DOMAIN_MODEL_VA_THUE_TNCN.txt   | Cụm Thực thể miền (Domain Model), cây kế thừa 2 tầng (Employee -> FT
    |                                        | -> Manager), PartTime, Intern, Interface Taxable & Bộ tính thuế 4 bậc.
----+----------------------------------------+--------------------------------------------------------------------------
02  | 02_CUM_REPOSITORY_TRUY_XUAT_DU_LIEU.txt| Tầng Truy xuất dữ liệu (Repository Pattern), Interface EmployeeRepository
    |                                        | (15 hàm CRUD), lưu trữ kép Dual-Level RAM (LinkedHashMap) + File CSV.
----+----------------------------------------+--------------------------------------------------------------------------
03  | 03_CUM_SERVICE_TINH_LUONG_NGHIEP_VU.txt| Tầng Nghiệp vụ tài chính (Service Layer), PayrollService độc lập với
    |                                        | Swing UI, Constructor Dependency Injection & thuật toán tài chính.
----+----------------------------------------+--------------------------------------------------------------------------
04  | 04_CUM_GIAO_DIEN_NGUOI_DUNG_SWING_UI.tx| Tầng Trình diễn đồ họa (Swing UI), BaseWindow (Template Method), 
    |                                        | MainWindow (điều phối 2 Tab), EmployeeFormDialog (CardLayout) & Bảng.
----+----------------------------------------+--------------------------------------------------------------------------
05  | 05_CUM_TIEN_ICH_UTIL_VA_BO_TRO.txt     | Cụm Tiện ích dùng chung, EmployeeComparator (Strategy, tiếng Việt chuẩn
    |                                        | bằng Collator), ChartHelper (JFreeChart đồ họa), Currency & Exporter.
========================================================================================================================

========================================================================================================================
II. BẢNG QUY CHUẨN KÝ HIỆU THEO CHUẨN QUỐC TẾ OMG UML 2.5
------------------------------------------------------------------------------------------------------------------------

1. CẤU TRÚC 3 NGĂN CỦA MỘT LỚP (3 COMPARTMENTS):
   ┌──────────────────────────────────────────────────────────────────────────────────────────────────────────────────┐
   │ Ngăn 1: Stereotype & Tên Lớp (Class Name)                                                                        │
   │         <<abstract>> TênLớp / <<interface>> TênInterface / <<utility>> TênLớp                                    │
   ├──────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤
   │ Ngăn 2: Thuộc tính (Attributes)                                                                                  │
   │         [Visibility] [TênThuộcTính]: [KiểuDữLiệu] [= GiáTrịKhởiTạo] [{ĐặcTính}]                                  │
   ├──────────────────────────────────────────────────────────────────────────────────────────────────────────────────┤
   │ Ngăn 3: Phương thức / Hành vi (Operations / Methods)                                                             │
   │         [Visibility] [TênPhươngThức]([ThamSố: Kiểu, ...]): [KiểuTrảVề] [{ĐặcTính}]                               │
   └──────────────────────────────────────────────────────────────────────────────────────────────────────────────────┘

2. KÝ HIỆU PHẠM VI TRUY CẬP (VISIBILITY MODIFIERS):
   - Dấu cộng (+): public           -> Truy cập công khai từ bên ngoài.
   - Dấu trừ  (-): private          -> Thuộc tính nội bộ, ẩn giấu thông tin (Tính Đóng gói).
   - Dấu thoi (#): protected        -> Chỉ lớp cha và các lớp con kế thừa mới được truy cập (Tính Kế thừa).
   - Dấu ngã  (~): package-private  -> Chỉ các lớp trong cùng package mới được truy cập.

3. KÝ HIỆU ĐẶC TÍNH (PROPERTY MODIFIERS):
   - {abstract} / chữ in nghiêng    : Phương thức hoặc lớp thuần ảo, bắt buộc các lớp con phải ghi đè (Override).
   - {static}   / gạch chân nét liền: Thuộc tính hoặc phương thức tĩnh, thuộc về lớp thay vì thuộc về từng đối tượng.
   - {final}    / {readOnly}        : Hằng số không thể thay đổi giá trị hoặc phương thức không thể ghi đè.

4. HỆ THỐNG MŨI TÊN VÀ MỐI QUAN HỆ KIẾN TRÚC (RELATIONSHIPS):
   ┌────────────────────────┬───────────────────┬─────────────────────────────────────────────────────────────────────┐
   │ Tên Mối Quan Hệ (UML)  │ Ký Hiệu Vẽ        │ Ý Nghĩa Học Thuật & Thể Hiện Trong Mã Nguồn Java                    │
   ├────────────────────────┼───────────────────┼─────────────────────────────────────────────────────────────────────┤
   │ Kế thừa                │ ────▷ (Nét liền,  │ Quan hệ huyết thống 'IS-A' (Tổng quát hóa / Chuyên biệt hóa).       │
   │ (Generalization)       │ tam giác rỗng)    │ Từ khóa: 'extends' (Ví dụ: FullTimeEmployee extends Employee)       │
   ├────────────────────────┼───────────────────┼─────────────────────────────────────────────────────────────────────┤
   │ Hiện thực hóa          │ ┄┄┄┄▷ (Nét đứt,   │ Cài đặt giao diện hợp đồng năng lực hoặc hành vi.                   │
   │ (Realization)          │ tam giác rỗng)    │ Từ khóa: 'implements' (Ví dụ: InMemoryRepo implements EmployeeRepo) │
   ├────────────────────────┼───────────────────┼─────────────────────────────────────────────────────────────────────┤
   │ Cấu thành chặt chẽ     │ ◆──── (Hình thoi  │ Quan hệ sở hữu mạnh 'PART-OF' (Ràng buộc sinh tử với lớp sở hữu).   │
   │ (Composition)          │ đặc màu đen)      │ Đối tượng con hủy theo cha (Ví dụ: MainWindow *-- TableModel)       │
   ├────────────────────────┼───────────────────┼─────────────────────────────────────────────────────────────────────┤
   │ Tập hợp lỏng lẻo       │ ◇──── (Hình thoi  │ Quan hệ chứa 'HAS-A' (Đối tượng con tồn tại độc lập với lớp chứa).  │
   │ (Aggregation)          │ rỗng màu trắng)   │ Truyền qua Dependency Injection (Ví dụ: PayrollService o-- Repo)    │
   ├────────────────────────┼───────────────────┼─────────────────────────────────────────────────────────────────────┤
   │ Phụ thuộc              │ ┄┄┄┄> (Nét đứt,   │ Sử dụng tạm thời thông qua tham số cục bộ hoặc lời gọi tĩnh.        │
   │ (Dependency)           │ mũi tên nhọn)     │ Stereotypes: <<use>>, <<call>>, <<open>>, <<throws>>                │
   ├────────────────────────┼───────────────────┼─────────────────────────────────────────────────────────────────────┤
   │ Kết hợp định hướng     │ ────> (Nét liền,  │ Giữ tham chiếu lâu dài tới đối tượng khác trong toàn bộ vòng đời.   │
   │ (Directed Association) │ mũi tên nhọn)     │ Ví dụ: BaseWindow ──> JMenuBar, MainWindow ──> JTabbedPane          │
   └────────────────────────┴───────────────────┴─────────────────────────────────────────────────────────────────────┘

5. BỘI SỐ QUAN HỆ (MULTIPLICITY):
   - 1      : Đúng chính xác 1 đối tượng duy nhất.
   - 0..1   : Tùy chọn (Optional), có thể không có hoặc có tối đa 1 đối tượng.
   - * / 0..*: Tập hợp danh sách (List / Set / Map) chứa từ 0 đến nhiều phần tử.
   - 1..*   : Bắt buộc phải có ít nhất từ 1 đối tượng trở lên.
