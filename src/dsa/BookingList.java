package dsa;

import entity.Booking;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDate;
import util.Validation;

/**
 * Lớp BookingList đại diện cho Danh sách liên kết đơn tự phát triển dành riêng cho đối tượng Booking.
 */
public class BookingList {
    private Node<Booking> head; // Node đầu tiên của danh sách đặt phòng
    private Node<Booking> tail; // Node cuối cùng của danh sách đặt phòng

    // Khởi tạo danh sách trống
    public BookingList() {
        head = tail = null;
    }

    // Kiểm tra xem danh sách đặt phòng có trống hay không
    public boolean isEmpty() {
        return head == null;
    }

    // Xóa sạch toàn bộ phần tử trong danh sách
    public void clear() {
        head = tail = null;
    }

    // Lấy node đầu tiên (head)
    public Node<Booking> getHead() {
        return head;
    }

    /**
     * Thêm một bản ghi đặt phòng vào đầu danh sách (yêu cầu nghiệp vụ Book room 3.2).
     */
    public void addFirst(Booking booking) {
        Node<Booking> newNode = new Node<>(booking);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    /**
     * Thêm một bản ghi đặt phòng vào cuối danh sách (hỗ trợ đọc file).
     */
    public void addLast(Booking booking) {
        Node<Booking> newNode = new Node<>(booking);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    /**
     * Tính số lượng bản ghi đặt phòng trong danh sách.
     */
    public int size() {
        int count = 0;
        Node<Booking> curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    /**
     * Sắp xếp danh sách đặt phòng giảm dần theo rcode, nếu trùng rcode thì sắp xếp giảm dần theo scode.
     * Sử dụng thuật toán Bubble Sort tráo đổi thuộc tính info.
     */
    public void sortByRcodeAndScode() {
        if (head == null || head.next == null) return;
        boolean swapped;
        do {
            swapped = false;
            Node<Booking> curr = head;
            while (curr.next != null) {
                int rcodeCompare = curr.info.getRcode().compareToIgnoreCase(curr.next.info.getRcode());
                boolean needSwap = false;
                
                if (rcodeCompare < 0) {
                    // Sắp xếp giảm dần theo rcode (nếu rcode của node hiện tại bé hơn node tiếp theo -> đổi chỗ)
                    needSwap = true;
                } else if (rcodeCompare == 0) {
                    // Nếu rcode bằng nhau, xét tiếp scode giảm dần
                    if (curr.info.getScode().compareToIgnoreCase(curr.next.info.getScode()) < 0) {
                        needSwap = true;
                    }
                }

                if (needSwap) {
                    Booking temp = curr.info;
                    curr.info = curr.next.info;
                    curr.next.info = temp;
                    swapped = true;
                }
                curr = curr.next;
            }
        } while (swapped);
    }

    /**
     * Nạp danh sách đặt phòng từ file bookings.txt.
     * Cú pháp dòng trong file: rcode|scode|bdate|ldate|state
     */
    public void loadFromFile(String filename) {
        clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {
                    String rcode = parts[0].trim();
                    String scode = parts[1].trim();
                    LocalDate bdate = Validation.getNullableDate(parts[2].trim(), "");
                    LocalDate ldate = Validation.getNullableDate(parts[3].trim(), "");
                    int state = Integer.parseInt(parts[4].trim());
                    Booking booking = new Booking(rcode, scode, bdate, ldate, state);
                    addLast(booking);
                }
            }
            System.out.println("Tải danh sách đặt phòng thành công từ " + filename + " (" + size() + " lượt đặt)");
        } catch (Exception e) {
            System.out.println("Lỗi khi tải dữ liệu từ file " + filename + ": " + e.getMessage());
        }
    }

    /**
     * Lưu danh sách đặt phòng hiện tại xuống file bookings.txt.
     */
    public void saveToFile(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            Node<Booking> curr = head;
            while (curr != null) {
                Booking b = curr.info;
                pw.printf("%s|%s|%s|%s|%d\n", 
                    b.getRcode(), 
                    b.getScode(), 
                    Validation.formatDate(b.getBdate()), 
                    Validation.formatDate(b.getLdate()), 
                    b.getState());
                curr = curr.next;
            }
            System.out.println("Đã lưu danh sách đặt phòng vào file " + filename);
        } catch (Exception e) {
            System.out.println("Lỗi khi lưu dữ liệu vào file " + filename + ": " + e.getMessage());
        }
    }

    /**
     * Hiển thị toàn bộ danh sách đặt phòng dưới dạng bảng trực quan.
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("Danh sách đặt phòng hiện đang trống.");
            return;
        }
        System.out.println("+----------+----------+------------+------------+-------+");
        System.out.println("| Rcode    | Scode    | Bdate      | Ldate      | State |");
        System.out.println("+----------+----------+------------+------------+-------+");
        Node<Booking> curr = head;
        while (curr != null) {
            System.out.println(curr.info.toString());
            curr = curr.next;
        }
        System.out.println("+----------+----------+------------+------------+-------+");
    }

    /**
     * Xóa toàn bộ lượt đặt phòng liên quan đến mã phòng rcode (xóa dây chuyền / cascading delete).
     */
    public void deleteBookingsByRcode(String rcode) {
        if (isEmpty()) return;

        // Xử lý các node bị xóa ở ngay đầu danh sách
        while (head != null && head.info.getRcode().equalsIgnoreCase(rcode)) {
            head = head.next;
        }

        if (head == null) {
            tail = null;
            return;
        }

        // Xử lý các node bị xóa ở giữa và cuối danh sách
        Node<Booking> curr = head;
        while (curr.next != null) {
            if (curr.next.info.getRcode().equalsIgnoreCase(rcode)) {
                if (curr.next == tail) {
                    tail = curr;
                }
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
    }

    /**
     * Xóa toàn bộ lượt đặt phòng liên quan đến mã sinh viên scode (xóa dây chuyền / cascading delete).
     */
    public void deleteBookingsByScode(String scode) {
        if (isEmpty()) return;

        // Xử lý các node bị xóa ở ngay đầu danh sách
        while (head != null && head.info.getScode().equalsIgnoreCase(scode)) {
            head = head.next;
        }

        if (head == null) {
            tail = null;
            return;
        }

        // Xử lý các node bị xóa ở giữa và cuối danh sách
        Node<Booking> curr = head;
        while (curr.next != null) {
            if (curr.next.info.getScode().equalsIgnoreCase(scode)) {
                if (curr.next == tail) {
                    tail = curr;
                }
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }
    }
}
