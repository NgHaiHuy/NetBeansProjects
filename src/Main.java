import dsa.Node;
import dsa.RoomList;
import dsa.StudentList;
import dsa.BookingList;
import entity.Room;
import entity.Student;
import service.DormitoryManager;
import util.Validation;

/**
 * Lớp Main chứa luồng chạy chính của ứng dụng Quản lý Ký túc xá (Dormitory Management System - DMS).
 * Đảm nhận nhiệm vụ hiển thị menu điều khiển và chuyển tiếp đến các chức năng nghiệp vụ tương ứng.
 */
public class Main {
    public static void main(String[] args) {
        // Khởi tạo các cấu trúc danh sách liên kết tự xây dựng
        RoomList rooms = new RoomList();
        StudentList students = new StudentList();
        BookingList bookings = new BookingList();
        
        // Khởi tạo tầng Service xử lý nghiệp vụ chung
        DormitoryManager manager = new DormitoryManager(rooms, students, bookings);

        // Tự động tải dữ liệu từ các file text khi khởi động chương trình nếu file tồn tại
        java.io.File rFile = new java.io.File("rooms.txt");
        java.io.File sFile = new java.io.File("students.txt");
        java.io.File bFile = new java.io.File("bookings.txt");
        
        if (rFile.exists()) rooms.loadFromFile("rooms.txt");
        if (sFile.exists()) students.loadFromFile("students.txt");
        if (bFile.exists()) bookings.loadFromFile("bookings.txt");

        // Vòng lặp chạy menu chính
        while (true) {
            printMenu();
            String choice = Validation.getString("Nhập lựa chọn của bạn (ví dụ: 1.2 hoặc 0 để thoát): ", "Lựa chọn không được rỗng.");
            System.out.println();
            
            switch (choice) {
                // --- NHÓM CHỨC NĂNG PHÒNG (Room List) ---
                case "1.1":
                    rooms.loadFromFile("rooms.txt");
                    break;
                case "1.2":
                    addRoomToEnd(rooms);
                    break;
                case "1.3":
                    System.out.println("--- Danh Sách Phòng ---");
                    rooms.display();
                    break;
                case "1.4":
                    rooms.saveToFile("rooms.txt");
                    break;
                case "1.5":
                    searchRoomByRcode(rooms);
                    break;
                case "1.6":
                    manager.deleteRoomWithCheck();
                    break;
                case "1.7":
                    System.out.println("Đang sắp xếp danh sách phòng theo rcode...");
                    rooms.sortByRcode();
                    rooms.display();
                    break;
                case "1.8":
                    addRoomToBeginning(rooms);
                    break;
                case "1.9":
                    addRoomBeforePositionK(rooms);
                    break;
                case "1.10":
                    deleteRoomAtPositionK(rooms);
                    break;
                case "1.11":
                    searchRoomByName(rooms);
                    break;
                case "1.12":
                    manager.searchBookedByRcode();
                    break;

                // --- NHÓM CHỨC NĂNG SINH VIÊN (Student List) ---
                case "2.1":
                    students.loadFromFile("students.txt");
                    break;
                case "2.2":
                    addStudentToEnd(students);
                    break;
                case "2.3":
                    System.out.println("--- Danh Sách Sinh Viên ---");
                    students.display();
                    break;
                case "2.4":
                    students.saveToFile("students.txt");
                    break;
                case "2.5":
                    searchStudentByScode(students);
                    break;
                case "2.6":
                    manager.deleteStudentWithCheck();
                    break;
                case "2.7":
                    searchStudentByName(students);
                    break;
                case "2.8":
                    manager.searchLendingRoomsByScode();
                    break;

                // --- NHÓM CHỨC NĂNG ĐẶT PHÒNG (Booking List) ---
                case "3.1":
                    bookings.loadFromFile("bookings.txt");
                    break;
                case "3.2":
                    manager.bookRoom();
                    break;
                case "3.3":
                    System.out.println("--- Danh Sách Giao Dịch Đặt Phòng ---");
                    bookings.display();
                    break;
                case "3.4":
                    bookings.saveToFile("bookings.txt");
                    break;
                case "3.5":
                    System.out.println("Đang sắp xếp các giao dịch giảm dần theo rcode + scode...");
                    bookings.sortByRcodeAndScode();
                    bookings.display();
                    break;
                case "3.6":
                    manager.leaveRoom();
                    break;

                // --- THOÁT ---
                case "0":
                    System.out.println("Cảm ơn bạn đã sử dụng chương trình DMS. Tạm biệt!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập đúng mã menu (ví dụ: 1.3).");
            }
            System.out.println();
        }
    }

    /**
     * Hiển thị bảng Menu các chức năng theo yêu cầu đề bài.
     */
    private static void printMenu() {
        System.out.println("=================================================================");
        System.out.println("                HỆ THỐNG QUẢN LÝ KÝ TÚC XÁ (DMS)                 ");
        System.out.println("=================================================================");
        System.out.println(" Room list (Quản lý phòng):                                      ");
        System.out.println("   1.1. Load data from file (Đọc từ file rooms.txt)             ");
        System.out.println("   1.2. Input & add to the end (Thêm phòng vào cuối danh sách)   ");
        System.out.println("   1.3. Display data (Hiển thị danh sách phòng)                  ");
        System.out.println("   1.4. Save room list to file (Lưu phòng vào rooms.txt)         ");
        System.out.println("   1.5. Search by rcode (Tìm kiếm phòng theo rcode)              ");
        System.out.println("   1.6. Delete by rcode (Xóa phòng có kiểm tra điều kiện)        ");
        System.out.println("   1.7. Sort by rcode (Sắp xếp tăng dần theo rcode)              ");
        System.out.println("   1.8. Input & add to beginning (Thêm phòng vào đầu danh sách)  ");
        System.out.println("   1.9. Add before position k (Chèn trước vị trí k)              ");
        System.out.println("   1.10. Delete position k (Xóa phòng tại vị trí k)              ");
        System.out.println("   1.11. Search by name (Tìm phòng theo tên)                     ");
        System.out.println("   1.12. Search booked by rcode (Xem phòng và sinh viên đang ở)  ");
        System.out.println("-----------------------------------------------------------------");
        System.out.println(" Student list (Quản lý sinh viên):                               ");
        System.out.println("   2.1. Load data from file (Đọc từ file students.txt)          ");
        System.out.println("   2.2. Input & add to the end (Thêm sinh viên vào cuối)         ");
        System.out.println("   2.3. Display data (Hiển thị danh sách sinh viên)              ");
        System.out.println("   2.4. Save student list to file (Lưu vào file students.txt)    ");
        System.out.println("   2.5. Search by scode (Tìm sinh viên theo scode)               ");
        System.out.println("   2.6. Delete by scode (Xóa sinh viên có kiểm tra điều kiện)    ");
        System.out.println("   2.7. Search by name (Tìm sinh viên theo tên)                  ");
        System.out.println("   2.8. Search lending books by scode (Xem phòng đã thuê của SV) ");
        System.out.println("-----------------------------------------------------------------");
        System.out.println(" Booking list (Quản lý giao dịch thuê):                          ");
        System.out.println("   3.1. Load data from file (Đọc từ file bookings.txt)          ");
        System.out.println("   3.2. Book the room (Đăng ký phòng cho sinh viên)              ");
        System.out.println("   3.3. Display data (Hiển thị danh sách giao dịch)              ");
        System.out.println("   3.4. Save booking list to file (Lưu vào file bookings.txt)    ");
        System.out.println("   3.5. Sort by rcode + scode (Sắp xếp giảm dần)                 ");
        System.out.println("   3.6. Leave the room by rcode + scode (Trả giường ký túc)       ");
        System.out.println("=================================================================");
        System.out.println("   0. Thoát chương trình                                         ");
        System.out.println("=================================================================");
    }

    // --- CÁC HÀM TRỢ GIÚP CHO DANH SÁCH PHÒNG (Room List Helpers) ---

    /**
     * Nhập thông tin phòng mới từ bàn phím và thực hiện kiểm tra tính hợp lệ dữ liệu.
     */
    private static Room inputRoom(RoomList rooms, boolean isUpdate) {
        String rcode;
        while (true) {
            rcode = Validation.getPattern("Nhập mã phòng (chỉ chữ và số): ", "^[a-zA-Z0-9]+$", "Mã phòng không hợp lệ. Chỉ chấp nhận chữ và số.");
            // Đảm bảo mã phòng nhập mới không bị trùng lặp
            if (rooms.searchByRcode(rcode) == null) {
                break;
            }
            System.out.println("Lỗi: Mã phòng đã tồn tại. Vui lòng nhập mã phòng khác.");
        }

        String name = Validation.getString("Nhập tên phòng: ", "Tên phòng không được để trống.");
        String dom = Validation.getString("Nhập tên tòa ký túc xá (dom): ", "Tên tòa ký túc xá không được để trống.");
        String floor = Validation.getPattern("Nhập tầng: ", "^[0-9]+$", "Tầng phải là một số.");
        String type = Validation.getRoomType("Nhập loại phòng (double hoặc triple): ", "Loại phòng không hợp lệ. Chỉ nhận 'double' hoặc 'triple'.");
        
        // Theo yêu cầu: phòng double có 4 giường, phòng triple có 6 giường
        int beds = type.equals("double") ? 4 : 6;
        int booked = 0; // Khi tạo phòng mới thì số giường đã đặt mặc định là 0
        
        double price = Validation.getDouble("Nhập giá tiền của 1 giường: ", 0.0, Double.MAX_VALUE, "Giá giường phải lớn hơn hoặc bằng 0.");

        return new Room(rcode, name, dom, floor, type, beds, booked, price);
    }

    private static void addRoomToEnd(RoomList rooms) {
        System.out.println("--- Thêm Phòng vào Cuối Danh Sách ---");
        Room r = inputRoom(rooms, false);
        rooms.addLast(r);
        System.out.println("Thêm phòng thành công!");
    }

    private static void addRoomToBeginning(RoomList rooms) {
        System.out.println("--- Thêm Phòng vào Đầu Danh Sách ---");
        Room r = inputRoom(rooms, false);
        rooms.addFirst(r);
        System.out.println("Thêm phòng thành công!");
    }

    private static void addRoomBeforePositionK(RoomList rooms) {
        System.out.println("--- Chèn Phòng Trước Vị Trí k ---");
        int size = rooms.size();
        System.out.println("Kích thước danh sách hiện tại: " + size);
        int k = Validation.getInt("Nhập vị trí k (bắt đầu từ 1): ", 1, size + 1, "Vị trí phải từ 1 đến " + (size + 1));
        Room r = inputRoom(rooms, false);
        rooms.addBefore(r, k);
        System.out.println("Chèn phòng trước vị trí " + k + " thành công!");
    }

    private static void deleteRoomAtPositionK(RoomList rooms) {
        System.out.println("--- Xóa Phòng Tại Vị Trí k ---");
        int size = rooms.size();
        if (size == 0) {
            System.out.println("Danh sách trống. Không thể xóa.");
            return;
        }
        System.out.println("Kích thước danh sách hiện tại: " + size);
        int k = Validation.getInt("Nhập vị trí k (1 đến " + size + "): ", 1, size, "Vị trí không hợp lệ.");
        
        // Tìm phòng tại vị trí k để kiểm tra xem có ai đang ở hay không
        Node<Room> curr = rooms.getHead();
        for (int i = 1; i < k; i++) {
            curr = curr.next;
        }
        Room r = curr.info;
        if (r.getBooked() > 0) {
            System.out.println("Lỗi: Không thể xóa phòng " + r.getRcode() + " tại vị trí k = " + k + " vì đang có sinh viên ở.");
            return;
        }
        
        rooms.deletePosition(k);
        System.out.println("Xóa thành công phòng ở vị trí " + k + ".");
    }

    private static void searchRoomByRcode(RoomList rooms) {
        System.out.println("--- Tìm Kiếm Phòng Theo Mã Phòng ---");
        String rcode = Validation.getString("Nhập mã phòng cần tìm: ", "Mã phòng không được để trống.");
        Room r = rooms.searchByRcode(rcode);
        if (r != null) {
            System.out.println("\nĐã tìm thấy phòng:");
            System.out.println("+----------+--------------+----------+--------+----------+------+--------+----------+");
            System.out.println("| Rcode    | Name         | Dom      | Floor  | Type     | Beds | Booked | Price    |");
            System.out.println("+----------+--------------+----------+--------+----------+------+--------+----------+");
            System.out.println(r.toString());
            System.out.println("+----------+--------------+----------+--------+----------+------+--------+----------+");
        } else {
            System.out.println("Không tìm thấy phòng nào có mã " + rcode + ".");
        }
    }

    private static void searchRoomByName(RoomList rooms) {
        System.out.println("--- Tìm Kiếm Phòng Theo Tên ---");
        String name = Validation.getString("Nhập từ khóa tìm kiếm tên phòng: ", "Từ khóa tìm kiếm không được để trống.");
        RoomList results = rooms.searchByName(name);
        if (!results.isEmpty()) {
            System.out.println("\nKết quả tìm kiếm:");
            results.display();
        } else {
            System.out.println("Không tìm thấy phòng nào có tên chứa \"" + name + "\".");
        }
    }

    // --- CÁC HÀM TRỢ GIÚP CHO DANH SÁCH SINH VIÊN (Student List Helpers) ---

    /**
     * Nhập thông tin sinh viên mới và kiểm tra ràng buộc duy nhất của mã sinh viên.
     */
    private static Student inputStudent(StudentList students) {
        String scode;
        while (true) {
            scode = Validation.getPattern("Nhập mã sinh viên (chỉ chữ và số): ", "^[a-zA-Z0-9]+$", "Mã sinh viên không hợp lệ. Chỉ chấp nhận chữ và số.");
            // Đảm bảo không trùng mã sinh viên
            if (students.searchByScode(scode) == null) {
                break;
            }
            System.out.println("Lỗi: Mã sinh viên đã tồn tại trong hệ thống. Vui lòng nhập mã sinh viên khác.");
        }

        String name = Validation.getString("Nhập tên sinh viên: ", "Tên sinh viên không được rỗng.");
        int byear = Validation.getStudentBirthYear("Nhập năm sinh sinh viên: ", "Năm sinh phải là một số nguyên hợp lệ.");

        return new Student(scode, name, byear);
    }

    private static void addStudentToEnd(StudentList students) {
        System.out.println("--- Thêm Sinh Viên Vào Cuối Danh Sách ---");
        Student s = inputStudent(students);
        students.addLast(s);
        System.out.println("Thêm sinh viên thành công!");
    }

    private static void searchStudentByScode(StudentList students) {
        System.out.println("--- Tìm Kiếm Sinh Viên Theo Mã ---");
        String scode = Validation.getString("Nhập mã sinh viên cần tìm: ", "Mã sinh viên không được rỗng.");
        Student s = students.searchByScode(scode);
        if (s != null) {
            System.out.println("\nĐã tìm thấy sinh viên:");
            System.out.println("+----------+----------------------+-------+");
            System.out.println("| Scode    | Name                 | Byear |");
            System.out.println("+----------+----------------------+-------+");
            System.out.println(s.toString());
            System.out.println("+----------+----------------------+-------+");
        } else {
            System.out.println("Không tìm thấy sinh viên nào có mã " + scode + ".");
        }
    }

    private static void searchStudentByName(StudentList students) {
        System.out.println("--- Tìm Kiếm Sinh Viên Theo Tên ---");
        String name = Validation.getString("Nhập từ khóa tìm kiếm tên sinh viên: ", "Từ khóa tìm kiếm không được rỗng.");
        StudentList results = students.searchByName(name);
        if (!results.isEmpty()) {
            System.out.println("\nKết quả tìm kiếm sinh viên:");
            results.display();
        } else {
            System.out.println("Không tìm thấy sinh viên nào có tên chứa \"" + name + "\".");
        }
    }
}
