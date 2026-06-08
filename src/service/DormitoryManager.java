package service;

import dsa.BookingList;
import dsa.Node;
import dsa.RoomList;
import dsa.StudentList;
import entity.Booking;
import entity.Room;
import entity.Student;
import java.time.LocalDate;
import util.Validation;

/**
 * Lớp DormitoryManager đóng vai trò là tầng Service, phối hợp hoạt động giữa
 * RoomList, StudentList và BookingList để thực hiện các chức năng nghiệp vụ phức tạp
 * liên quan đến tính nhất quán dữ liệu giữa các danh sách liên kết.
 */
public class DormitoryManager {
    private final RoomList rooms;       // Danh sách các phòng
    private final StudentList students;   // Danh sách các sinh viên
    private final BookingList bookings;   // Danh sách các giao dịch đặt phòng

    public DormitoryManager(RoomList rooms, StudentList students, BookingList bookings) {
        this.rooms = rooms;
        this.students = students;
        this.bookings = bookings;
    }

    /**
     * Nghiệp vụ 3.2: Đặt giường trong phòng.
     * Kiểm tra phòng và sinh viên có tồn tại hay không.
     * Kiểm tra phòng còn giường trống không (beds > 0).
     * Kiểm tra xem sinh viên có đang ở phòng nào khác không (không được ở song song 2 phòng).
     * Nếu hợp lệ: Thêm bản ghi đặt phòng vào ĐẦU danh sách đặt phòng, giảm 1 giường trống của phòng và tăng 1 giường đã đặt.
     */
    public void bookRoom() {
        System.out.println("--- Đặt Phòng Ký Túc Xá ---");
        
        // Nhập mã phòng và tìm kiếm trong danh sách phòng
        String rcode = Validation.getString("Nhập mã phòng (rcode): ", "Mã phòng không được để trống.");
        Room room = rooms.searchByRcode(rcode);
        if (room == null) {
            System.out.println("Lỗi: Phòng có mã " + rcode + " không tồn tại.");
            return;
        }

        // Nhập mã sinh viên và tìm kiếm trong danh sách sinh viên
        String scode = Validation.getString("Nhập mã sinh viên (scode): ", "Mã sinh viên không được để trống.");
        Student student = students.searchByScode(scode);
        if (student == null) {
            System.out.println("Lỗi: Sinh viên có mã " + scode + " không tồn tại.");
            return;
        }

        // Kiểm tra số lượng giường khả dụng của phòng
        if (room.getBeds() <= 0) {
            System.out.println("Lỗi: Phòng " + rcode + " đã hết giường trống.");
            return;
        }

        // Kiểm tra xem sinh viên có đang cư trú tại một phòng nào khác hay không (trạng thái booking = 1)
        Node<Booking> curr = bookings.getHead();
        while (curr != null) {
            if (curr.info.getScode().equalsIgnoreCase(scode) && curr.info.getState() == 1) {
                System.out.println("Lỗi: Sinh viên " + scode + " hiện đang ở tại phòng " + curr.info.getRcode() + ", không thể đặt thêm.");
                return;
            }
            curr = curr.next;
        }

        // Tạo đối tượng Đặt phòng với ngày thuê là Hôm nay, ngày rời là null, trạng thái là 1 (đang thuê)
        Booking booking = new Booking(rcode, scode, LocalDate.now(), null, 1);
        
        // Thêm bản ghi vào đầu danh sách đặt phòng
        bookings.addFirst(booking);

        // Cập nhật lại số giường của phòng: giường trống giảm 1, giường đã đặt tăng 1
        room.setBeds(room.getBeds() - 1);
        room.setBooked(room.getBooked() + 1);

        System.out.println("Đặt phòng thành công! Sinh viên " + scode + " đã nhận giường tại phòng " + rcode + ".");
    }

    /**
     * Nghiệp vụ 3.6: Trả phòng / Rời phòng ký túc xá.
     * Tìm kiếm lượt đặt phòng đang hoạt động (state = 1) khớp rcode và scode.
     * Cập nhật trạng thái thành 0 (đã trả), ngày rời phòng ldate thành Hôm nay.
     * Cộng trả lại 1 giường trống và giảm 1 giường đã đặt ở phòng tương ứng.
     */
    public void leaveRoom() {
        System.out.println("--- Trả Phòng Ký Túc Xá ---");
        String rcode = Validation.getString("Nhập mã phòng (rcode): ", "Mã phòng không được để trống.");
        String scode = Validation.getString("Nhập mã sinh viên (scode): ", "Mã sinh viên không được để trống.");

        // Tìm kiếm lượt đặt phòng khớp mã phòng, mã sinh viên và đang có trạng thái hoạt động (state = 1)
        Node<Booking> curr = bookings.getHead();
        Booking foundBooking = null;
        while (curr != null) {
            if (curr.info.getRcode().equalsIgnoreCase(rcode) 
                    && curr.info.getScode().equalsIgnoreCase(scode) 
                    && curr.info.getState() == 1) {
                foundBooking = curr.info;
                break;
            }
            curr = curr.next;
        }

        if (foundBooking == null) {
            System.out.println("Lỗi: Không tìm thấy giao dịch thuê phòng đang hoạt động của Sinh viên " + scode + " tại Phòng " + rcode + ".");
            return;
        }

        // Cập nhật trạng thái giao dịch
        foundBooking.setState(0);
        foundBooking.setLdate(LocalDate.now());

        // Trả lại giường trống cho phòng tương ứng
        Room room = rooms.searchByRcode(rcode);
        if (room != null) {
            room.setBeds(room.getBeds() + 1);
            room.setBooked(room.getBooked() - 1);
        }

        System.out.println("Trả phòng thành công! Sinh viên " + scode + " đã rời khỏi phòng " + rcode + ".");
    }

    /**
     * Nghiệp vụ 1.6: Xóa phòng theo rcode.
     * Chỉ cho phép xóa nếu số giường đang đặt (booked) của phòng bằng 0 (không có sinh viên đang ở).
     * Phải xóa tất cả lịch sử đặt phòng (bookings) liên quan đến phòng này trước rồi mới xóa phòng khỏi danh sách phòng.
     */
    public void deleteRoomWithCheck() {
        System.out.println("--- Xóa Phòng ---");
        String rcode = Validation.getString("Nhập mã phòng cần xóa: ", "Mã phòng không được để trống.");
        Room room = rooms.searchByRcode(rcode);
        if (room == null) {
            System.out.println("Lỗi: Không tìm thấy phòng.");
            return;
        }

        // Điều kiện xóa: booked phải bằng 0 (Không ai đang ở)
        if (room.getBooked() > 0) {
            System.out.println("Lỗi: Không thể xóa phòng " + rcode + " vì hiện đang có " + room.getBooked() + " sinh viên đang cư trú.");
            return;
        }

        // Bước 1: Xóa toàn bộ lượt đặt phòng liên quan tới phòng này trong danh sách đặt phòng
        bookings.deleteBookingsByRcode(rcode);

        // Bước 2: Xóa phòng khỏi danh sách liên kết phòng
        rooms.deleteByRcode(rcode);
        System.out.println("Đã xóa thành công phòng " + rcode + " cùng với toàn bộ lịch sử giao dịch liên quan.");
    }

    /**
     * Nghiệp vụ 2.6: Xóa sinh viên theo scode.
     * Chỉ cho phép xóa sinh viên đã rời tất cả các phòng (không có phòng nào đang ở - state = 1).
     * Phải xóa lịch sử đặt phòng liên quan tới sinh viên này trước, sau đó xóa sinh viên khỏi danh sách.
     */
    public void deleteStudentWithCheck() {
        System.out.println("--- Xóa Sinh Viên ---");
        String scode = Validation.getString("Nhập mã sinh viên cần xóa: ", "Mã sinh viên không được để trống.");
        Student student = students.searchByScode(scode);
        if (student == null) {
            System.out.println("Lỗi: Không tìm thấy sinh viên.");
            return;
        }

        // Kiểm tra xem sinh viên có đang thuê bất kỳ phòng nào không
        Node<Booking> curr = bookings.getHead();
        while (curr != null) {
            if (curr.info.getScode().equalsIgnoreCase(scode) && curr.info.getState() == 1) {
                System.out.println("Lỗi: Không thể xóa sinh viên " + scode + " vì họ đang ở tại phòng " + curr.info.getRcode() + ".");
                return;
            }
            curr = curr.next;
        }

        // Bước 1: Xóa lịch sử đặt phòng liên quan tới sinh viên này
        bookings.deleteBookingsByScode(scode);

        // Bước 2: Xóa sinh viên khỏi danh sách liên kết sinh viên
        students.deleteByScode(scode);
        System.out.println("Đã xóa thành công sinh viên " + scode + " cùng với toàn bộ lịch sử thuê phòng liên quan.");
    }

    /**
     * Nghiệp vụ 1.12: Tìm phòng theo rcode và hiển thị chi tiết phòng cùng danh sách các sinh viên đang ở phòng đó.
     */
    public void searchBookedByRcode() {
        System.out.println("--- Tra Cứu Phòng Đã Đặt ---");
        String rcode = Validation.getString("Nhập mã phòng: ", "Mã phòng không được để trống.");
        Room room = rooms.searchByRcode(rcode);
        if (room == null) {
            System.out.println("Phòng có mã " + rcode + " không tồn tại.");
            return;
        }

        // Hiển thị thông tin phòng
        System.out.println("\nThông tin phòng:");
        System.out.println("+----------+--------------+----------+--------+----------+------+--------+----------+");
        System.out.println("| Rcode    | Name         | Dom      | Floor  | Type     | Beds | Booked | Price    |");
        System.out.println("+----------+--------------+----------+--------+----------+------+--------+----------+");
        System.out.println(room.toString());
        System.out.println("+----------+--------------+----------+--------+----------+------+--------+----------+");

        // Tìm danh sách các sinh viên đang cư trú trong phòng
        System.out.println("\nDanh sách sinh viên đang ở phòng này:");
        boolean hasStudents = false;
        Node<Booking> curr = bookings.getHead();
        while (curr != null) {
            if (curr.info.getRcode().equalsIgnoreCase(rcode) && curr.info.getState() == 1) {
                if (!hasStudents) {
                    System.out.println("+----------+----------------------+-------+");
                    System.out.println("| Scode    | Name                 | Byear |");
                    System.out.println("+----------+----------------------+-------+");
                    hasStudents = true;
                }
                Student s = students.searchByScode(curr.info.getScode());
                if (s != null) {
                    System.out.println(s.toString());
                } else {
                    System.out.printf("| %-8s | %-20s | %-5s |\n", curr.info.getScode(), "Sinh viên không rõ", "N/A");
                }
            }
            curr = curr.next;
        }
        if (hasStudents) {
            System.out.println("+----------+----------------------+-------+");
        } else {
            System.out.println("Không có sinh viên nào hiện đang ở phòng này.");
        }
    }

    /**
     * Nghiệp vụ 2.8: Tìm sinh viên theo scode và hiển thị toàn bộ lịch sử các phòng mà sinh viên này từng đặt.
     */
    public void searchLendingRoomsByScode() {
        System.out.println("--- Tra Cứu Phòng Đã Đặt Theo Sinh Viên ---");
        String scode = Validation.getString("Nhập mã sinh viên: ", "Mã sinh viên không được để trống.");
        Student student = students.searchByScode(scode);
        if (student == null) {
            System.out.println("Sinh viên có mã " + scode + " không tồn tại.");
            return;
        }

        // Hiển thị thông tin sinh viên
        System.out.println("\nThông tin sinh viên:");
        System.out.println("+----------+----------------------+-------+");
        System.out.println("| Scode    | Name                 | Byear |");
        System.out.println("+----------+----------------------+-------+");
        System.out.println(student.toString());
        System.out.println("+----------+----------------------+-------+");

        // Hiển thị danh sách phòng đã và đang thuê
        System.out.println("\nLịch sử đặt phòng của sinh viên này:");
        boolean hasBookings = false;
        Node<Booking> curr = bookings.getHead();
        while (curr != null) {
            if (curr.info.getScode().equalsIgnoreCase(scode)) {
                if (!hasBookings) {
                    System.out.println("+----------+------------+------------+----------------------+");
                    System.out.println("| Mã Phòng | Ngày Thuê  | Ngày Trả   | Trạng thái           |");
                    System.out.println("+----------+------------+------------+----------------------+");
                    hasBookings = true;
                }
                String status = curr.info.getState() == 1 ? "Đang cư trú" : "Đã trả giường";
                System.out.printf("| %-8s | %-10s | %-10s | %-20s |\n", 
                        curr.info.getRcode(), 
                        Validation.formatDate(curr.info.getBdate()), 
                        Validation.formatDate(curr.info.getLdate()), 
                        status);
            }
            curr = curr.next;
        }
        if (hasBookings) {
            System.out.println("+----------+------------+------------+----------------------+");
        } else {
            System.out.println("Sinh viên này chưa từng thực hiện giao dịch thuê phòng nào.");
        }
    }
}
