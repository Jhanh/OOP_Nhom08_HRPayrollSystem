====================================================================================================
               HỌC VIỆN CÔNG NGHỆ BƯU CHÍNH VIỄN THÔNG (PTIT)
                   KHOA CÔNG NGHỆ THÔNG TIN - BỘ MÔN OOP
               ĐỒ ÁN MÔN HỌC: LẬP TRÌNH HƯỚNG ĐỐI TƯỢNG (INT1332)
====================================================================================================
ĐỀ TÀI: HỆ THỐNG QUẢN LÝ NHÂN SỰ & BẢNG LƯƠNG DOANH NGHIỆP (HR PAYROLL SYSTEM)
NHÓM THỰC HIỆN: NHÓM 08
THƯ MỤC: TÀI LIỆU THIẾT KẾ SƠ ĐỒ LỚP UML CHUẨN QUỐC TẾ (OMG UML 2.5 SPECIFICATION)
====================================================================================================

I. MỤC ĐÍCH & Ý NGHĨA CỦA THƯ MỤC NÀY
----------------------------------------------------------------------------------------------------
Trong kỹ nghệ phần mềm và đặc biệt là môn học Lập trình hướng đối tượng (OOP), Sơ đồ lớp (UML Class 
Diagram) là bản thiết kế kiến trúc quan trọng nhất. Nó phản ánh tư duy trừu tượng hóa thế giới thực,
cách quy hoạch module, phân bổ trách nhiệm giữa các thực thể và mối quan hệ ràng buộc giữa các lớp.

Thay vì chỉ vẽ một sơ đồ chung chung sơ sài, thư mục 'UML/' này được xây dựng công phu nhằm:
1. Mô phỏng chi tiết 100% tất cả 24 thực thể (Class, Abstract Class, Interface, Enum, Record/Static Class) 
   trong mã nguồn dự án theo đúng chuẩn mực quốc tế OMG UML 2.5.
2. Chia nhỏ thành từng cụm kiến trúc chuyên biệt (Domain Model, Repository, Service, UI, Util) để người đọc,
   sinh viên và giảng viên chấm thi có thể tra cứu từng chi tiết kỹ thuật nhỏ nhất.
3. Cung cấp song song cả 2 hình thức biểu diễn:
   - Bản vẽ trực quan bằng ký tự văn bản chuẩn (ASCII Art / Unicode Box-Drawing) xem được trực tiếp trên 
     mọi trình soạn thảo, GitHub và terminal.
   - Khối mã nguồn PlantUML (@startuml ... @enduml) đạt chuẩn quốc tế, có thể copy-paste để kết xuất 
     ngay lập tức ra file ảnh vector SVG/PNG chất lượng cao.

====================================================================================================
II. DANH MỤC CÁC TỆP BẢN VẼ TRONG THƯ MỤC UML/
----------------------------------------------------------------------------------------------------
STT | Tên tệp tin                            | Nội dung kiến trúc mô tả
----+---------------------------------------+-------------------------------------------------------
00  | 00_SO_DO_TONG_THE_HE_THONG.txt        | Bản vẽ kiến trúc tổng thể toàn bộ hệ thống (High-Level
    |                                       | Architecture & Package Diagram), liên kết 6 Package,
    |                                       | luồng tương tác xuyên tầng và bảng tra cứu 24 thực thể.
----+---------------------------------------+-------------------------------------------------------
01  | 01_CUM_DOMAIN_MODEL_VA_THUE_TNCN.txt  | Cụm Thực thể miền (Domain Model), cây kế thừa 2 tầng
    |                                       | (Employee -> FullTimeEmployee -> Manager), PartTime,
    |                                       | Intern, Interface Taxable, TaxCalculator & Exception.
----+---------------------------------------+-------------------------------------------------------
02  | 02_CUM_REPOSITORY_TRUY_XUAT_DU_LIEU.txt| Cụm Truy xuất & Lưu trữ dữ liệu (Repository Pattern),
    |                                       | Interface EmployeeRepository (15 hàm CRUD), cơ chế lưu 
    |                                       | trữ kép RAM (LinkedHashMap) + File CSV & Bộ sao lưu.
----+---------------------------------------+-------------------------------------------------------
03  | 03_CUM_SERVICE_TINH_LUONG_NGHIEP_VU.txt| Cụm Nghiệp vụ tính toán tài chính (Service Layer),
    |                                       | PayrollService, Dependency Injection, thuật toán tính 
    |                                       | lương, thuế TNCN, phân bổ phòng ban bằng Java 8 Stream.
----+---------------------------------------+-------------------------------------------------------
04  | 04_CUM_GIAO_DIEN_NGUOI_DUNG_SWING_UI.txt| Cụm Trình diễn đồ họa (Presentation Layer), BaseWindow
    |                                       | (Template Method), MainWindow, EmployeeFormDialog 
    |                                       | (CardLayout), ChartWindow, TableModels & CellRenderer.
----+---------------------------------------+-------------------------------------------------------
05  | 05_CUM_TIEN_ICH_UTIL_VA_BO_TRO.txt    | Cụm Tiện ích bổ trợ (Cross-cutting Concerns), 
    |                                       | EmployeeComparator (Strategy Pattern, Tiếng Việt chuẩn),
    |                                       | ChartHelper (JFreeChart), CurrencyFormatUtil, Exporter.
====================================================================================================

====================================================================================================
III. BẢNG QUY CHUẨN KÝ HIỆU THEO CHUẨN QUỐC TẾ OMG UML 2.5
----------------------------------------------------------------------------------------------------

1. CẤU TRÚC 3 NGĂN CỦA MỘT LỚP (3 COMPARTMENTS):
   ┌─────────────────────────────────────────────────────────┐
   │ Ngăn 1: Stereotype & Tên Lớp (Class Name)               │
   │         <<abstract>> TênLớp / <<interface>> TênInterface │
   ├─────────────────────────────────────────────────────────┤
   │ Ngăn 2: Thuộc tính (Attributes)                         │
   │         [Visibility] [Tên]: [KiểuDữLiệu] [= GiáTrị]     │
   ├─────────────────────────────────────────────────────────┤
   │ Ngăn 3: Phương thức / Hành vi (Operations / Methods)    │
   │         [Visibility] [Tên]([ThamSố]): [KiểuTrảVề]       │
   └─────────────────────────────────────────────────────────┘

2. KÝ HIỆU PHẠM VI TRUY CẬP (VISIBILITY MODIFIERS):
   - Dấu cộng (+): public           -> Mọi lớp bên ngoài đều có quyền truy cập.
   - Dấu trừ  (-): private          -> Chỉ nội bộ lớp mới truy cập được (Đóng gói dữ liệu).
   - Dấu thoi (#): protected        -> Chỉ lớp cha và các lớp con kế thừa mới truy cập được.
   - Dấu ngã  (~): package-private  -> Chỉ các lớp trong cùng một package mới truy cập được.

3. KÝ HIỆU ĐẶC TÍNH (PROPERTY MODIFIERS):
   - {abstract} / chữ in nghiêng    : Phương thức hoặc lớp thuần ảo, bắt buộc lớp con ghi đè.
   - {static}   / gạch chân nét liền: Thuộc tính hoặc phương thức tĩnh, thuộc về lớp thay vì đối tượng.
   - {final}    / {readOnly}        : Hằng số hoặc phương thức không cho phép ghi đè.

4. HỆ THỐNG MŨI TÊN VÀ MỐI QUAN HỆ KIẾN TRÚC (RELATIONSHIPS):
   ┌────────────────────────┬───────────────────┬─────────────────────────────────────────────────┐
   │ Tên Mối Quan Hệ (UML)  │ Ký Hiệu Vẽ        │ Ý Nghĩa Học Thuật & Thể Hiện Trong Code Java    │
   ├────────────────────────┼───────────────────┼─────────────────────────────────────────────────┤
   │ Kế thừa                │ ────▷ (Nét liền,  │ Quan hệ huyết thống 'IS-A'.                     │
   │ (Generalization)       │ tam giác rỗng)    │ Từ khóa: 'extends'                              │
   │                        │                   │ Ví dụ: FullTimeEmployee extends Employee        │
   ├────────────────────────┼───────────────────┼─────────────────────────────────────────────────┤
   │ Hiện thực hóa          │ ┄┄┄┄▷ (Nét đứt,   │ Cài đặt giao diện hợp đồng.                     │
   │ (Realization)          │ tam giác rỗng)    │ Từ khóa: 'implements'                           │
   │                        │                   │ Ví dụ: InMemoryRepo implements EmployeeRepo     │
   ├────────────────────────┼───────────────────┼─────────────────────────────────────────────────┤
   │ Cấu thành chặt chẽ     │ ◆──── (Hình thoi  │ Quan hệ sở hữu mạnh 'PART-OF'.                  │
   │ (Composition)          │ đặc màu đen)      │ Đối tượng con gắn liền sinh tử với đối tượng cha│
   │                        │                   │ Ví dụ: MainWindow *--> EmployeeTableModel       │
   ├────────────────────────┼───────────────────┼─────────────────────────────────────────────────┤
   │ Tập hợp lỏng lẻo       │ ◇──── (Hình thoi  │ Quan hệ chứa 'HAS-A'.                           │
   │ (Aggregation)          │ rỗng màu trắng)   │ Đối tượng con tồn tại độc lập với đối tượng cha.│
   │                        │                   │ Ví dụ: PayrollService o--> EmployeeRepository   │
   ├────────────────────────┼───────────────────┼─────────────────────────────────────────────────┤
   │ Phụ thuộc              │ ┄┄┄┄> (Nét đứt,   │ Sử dụng tạm thời (lời gọi hàm, tham số cục bộ). │
   │ (Dependency)           │ mũi tên nhọn)     │ Stereotype: <<use>>, <<create>>, <<call>>       │
   │                        │                   │ Ví dụ: MainWindow ..> EmployeeFormDialog        │
   ├────────────────────────┼───────────────────┼─────────────────────────────────────────────────┤
   │ Kết hợp định hướng     │ ────> (Nét liền,  │ Giữ tham chiếu lâu dài tới đối tượng khác.      │
   │ (Directed Association) │ mũi tên nhọn)     │ Ví dụ: BaseWindow ──> JMenuBar                  │
   └────────────────────────┴───────────────────┴─────────────────────────────────────────────────┘

5. BỘI SỐ QUAN HỆ (MULTIPLICITY):
   - 1      : Đúng chính xác 1 đối tượng.
   - 0..1   : Không có hoặc có tối đa 1 đối tượng (Optional).
   - * / 0..*: Không hoặc nhiều đối tượng (Danh sách List/Set/Map rỗng hoặc có phần tử).
   - 1..*   : Bắt buộc có từ 1 đối tượng trở lên.

====================================================================================================
IV. HƯỚNG DẪN KẾT XUẤT ĐỒ HỌA TỪ KHỐI MÃ PLANTUML
----------------------------------------------------------------------------------------------------
Trong mỗi tệp .txt đều chứa khối mã nguồn PlantUML hoàn chỉnh (bắt đầu bằng @startuml và kết thúc 
bằng @enduml). Để render thành hình ảnh PNG hoặc vector SVG, bạn có thể chọn 1 trong các cách sau:

Cách 1: Sử dụng công cụ trực tuyến miễn phí (Nhanh nhất - Không cần cài đặt):
  1. Truy cập trang web: https://www.planttext.com hoặc https://plantuml.com/plantuml
  2. Mở file .txt tương ứng trong thư mục này, copy toàn bộ nội dung từ dòng '@startuml' đến '@enduml'.
  3. Paste vào khung soạn thảo của trang web.
  4. Bấm nút 'Submit' hoặc 'Refresh' -> Hệ thống sẽ vẽ ngay sơ đồ trực quan cực đẹp.
  5. Chọn 'Export as PNG' hoặc 'Export as SVG' để tải ảnh về máy.

Cách 2: Sử dụng Extension trong Visual Studio Code:
  1. Cài Extension 'PlantUML' (của tác giả jebbs).
  2. Cài đặt thêm Graphviz trên máy: 'winget install graphviz'.
  3. Mở file .txt có chứa khối @startuml trong VS Code, nhấn tổ hợp phím 'Alt + D' để mở màn hình xem trước.
  4. Nhấp chuột phải chọn 'Export Current Diagram' để lưu ảnh chất lượng cao.

Cách 3: Sử dụng IntelliJ IDEA:
  1. Vào Settings -> Plugins -> Cài đặt plugin 'PlantUML Integration'.
  2. Mở file mã nguồn, IntelliJ sẽ tự động mở panel 'PlantUML' bên tay phải và hiển thị đồ họa trực tiếp.

====================================================================================================
HẾT TỆP HƯỚNG DẪN TỔNG QUAN
====================================================================================================

