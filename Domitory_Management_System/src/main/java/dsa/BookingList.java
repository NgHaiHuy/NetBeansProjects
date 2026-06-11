package dsa;

import entity.Booking;
import entity.BookingState;
import java.io.*;

// Danh sach lien ket don chua cac booking (dat phong)
public class BookingList extends MyLinkedList<Booking> {

    // Khoi tao danh sach rong
    public BookingList() {
        super();
    }

    // Tim booking dang hoat dong (state=ACTIVE) theo rcode va scode
    public Booking searchActiveBooking(String rcode, String scode) {
        Node<Booking> current = head;
        while (current != null) {
            Booking b = current.info;
            if (b.getRcode().equalsIgnoreCase(rcode.trim()) &&
                b.getScode().equalsIgnoreCase(scode.trim()) &&
                b.getState() == BookingState.ACTIVE) {
                return b;  // Tim thay booking dang hoat dong
            }
            current = current.next;
        }
        return null;  // Khong tim thay
    }

    // Kiem tra sinh vien co dang o phong nao khong (co booking state=ACTIVE)
    public boolean hasActiveBooking(String scode) {
        Node<Booking> current = head;
        while (current != null) {
            Booking b = current.info;
            if (b.getScode().equalsIgnoreCase(scode.trim()) && b.getState() == BookingState.ACTIVE) {
                return true;  // Sinh vien dang o 1 phong
            }
            current = current.next;
        }
        return false;  // Sinh vien khong o phong nao
    }

    // Xoa tat ca booking cua 1 phong (dung khi xoa phong)
    public void deleteBookingsByRcode(String rcode) {
        if (isEmpty()) return;

        // Xoa cac node dau co rcode trung
        while (head != null && head.info.getRcode().equalsIgnoreCase(rcode.trim())) {
            head = head.next;
        }
        if (head == null) { tail = null; return; }

        // Xoa cac node con lai co rcode trung
        Node<Booking> current = head;
        while (current.next != null) {
            if (current.next.info.getRcode().equalsIgnoreCase(rcode.trim())) {
                if (current.next == tail) tail = current;
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
    }

    // Xoa tat ca booking cua 1 sinh vien (dung khi xoa sinh vien)
    public void deleteBookingsByScode(String scode) {
        if (isEmpty()) return;

        // Xoa cac node dau co scode trung
        while (head != null && head.info.getScode().equalsIgnoreCase(scode.trim())) {
            head = head.next;
        }
        if (head == null) { tail = null; return; }

        // Xoa cac node con lai co scode trung
        Node<Booking> current = head;
        while (current.next != null) {
            if (current.next.info.getScode().equalsIgnoreCase(scode.trim())) {
                if (current.next == tail) tail = current;
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
    }

    // =========================================================================
    // THUẬT TOÁN SẮP XẾP MERGE SORT O(N LOG N) TRÊN SINGLY LINKED LIST (BOOKINGS)
    // Sắp xếp giảm dần theo rcode, nếu rcode trùng nhau thì giảm dần theo scode
    // =========================================================================

    // Hàm gọi chính để sắp xếp danh sách booking
    public void sortByRcodeAndScodeDesc() {
        if (isEmpty() || head.next == null) return;
        
        // Gọi đệ quy để sắp xếp và cập nhật head mới
        head = mergeSort(head);

        // Cập nhật lại con trỏ tail của danh sách sau khi sắp xếp
        Node<Booking> current = head;
        while (current != null && current.next != null) {
            current = current.next;
        }
        tail = current;
    }

    // Hàm đệ quy chia để trị
    private Node<Booking> mergeSort(Node<Booking> h) {
        // Trường hợp cơ sở: danh sách rỗng hoặc chỉ có 1 phần tử
        if (h == null || h.next == null) {
            return h;
        }

        // Bước 1: Tìm Node ở giữa danh sách
        Node<Booking> middle = getMiddle(h);
        Node<Booking> nextOfMiddle = middle.next;

        // Bước 2: Ngắt kết nối để chia danh sách làm hai nửa độc lập
        middle.next = null;

        // Bước 3: Đệ quy sắp xếp từng nửa
        Node<Booking> left = mergeSort(h);
        Node<Booking> right = mergeSort(nextOfMiddle);

        // Bước 4: Trộn hai nửa đã sắp xếp theo thứ tự giảm dần
        return sortedMerge(left, right);
    }

    // Hàm trộn hai nửa đã sắp xếp giảm dần theo tiêu chí: rcode giảm dần, scode giảm dần
    private Node<Booking> sortedMerge(Node<Booking> a, Node<Booking> b) {
        if (a == null) return b;
        if (b == null) return a;

        Node<Booking> result;
        
        // So sánh mã phòng (rcode) của a và b
        int rcodeComp = a.info.getRcode().compareToIgnoreCase(b.info.getRcode());
        boolean aGreater = false;
        
        if (rcodeComp > 0) {
            // Nếu rcode của a lớn hơn b (thứ tự bảng chữ cái đứng sau), a đi trước (giảm dần)
            aGreater = true;
        } else if (rcodeComp == 0) {
            // Nếu mã phòng trùng nhau, tiếp tục so sánh mã sinh viên (scode)
            int scodeComp = a.info.getScode().compareToIgnoreCase(b.info.getScode());
            if (scodeComp >= 0) {
                // Nếu scode của a lớn hơn hoặc bằng b, a đi trước (giảm dần)
                aGreater = true;
            }
        }

        // Thực hiện trộn đệ quy dựa trên kết quả so sánh
        if (aGreater) {
            result = a;
            result.next = sortedMerge(a.next, b);
        } else {
            result = b;
            result.next = sortedMerge(a, b.next);
        }
        return result;
    }

    // Hàm tìm Node chính giữa bằng kỹ thuật hai con trỏ Runner
    private Node<Booking> getMiddle(Node<Booking> h) {
        if (h == null) return h;
        Node<Booking> fast = h.next; // Con trỏ nhanh chạy 2 bước
        Node<Booking> slow = h;      // Con trỏ chậm chạy 1 bước

        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }
        }
        // Con trỏ chậm dừng ở Node chính giữa
        return slow;
    }

    // Hien thi toan bo danh sach booking
    public void display() {
        if (isEmpty()) {
            System.out.println("Booking list is empty.");
            return;
        }
        System.out.println(String.format("%-10s | %-10s | %-12s | %-12s | %-5s",
                "Rcode", "Scode", "Book Date", "Leave Date", "State"));
        System.out.println("-------------------------------------------------------------");
        Node<Booking> current = head;
        while (current != null) {
            System.out.println(current.info.toString());
            current = current.next;
        }
    }

    // Doc du lieu booking tu file.
    // Tra ve so luong booking load duoc, hoac -1 neu file khong ton tai.
    public int loadFromFile(String filename) throws IOException {
        clear();
        File file = new File(filename);
        if (!file.exists()) return -1;

        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;

                String[] parts = line.split("\\s*\\|\\s*");
                if (parts.length >= 5) {
                    try {
                        String rcode = parts[0].trim();
                        String scode = parts[1].trim();

                        if (rcode.isEmpty() || scode.isEmpty()) {
                            System.err.println("Warning: Skipping line with empty codes: " + line);
                            continue;
                        }

                        // Doc ngay dat phong
                        String bdate = parts[2].trim();
                        if (!bdate.matches("\\d{2}/\\d{2}/\\d{4}")) {
                            System.err.println("Warning: Skipping malformed line (invalid booking date format, expected dd/MM/yyyy): " + line);
                            continue;
                        }

                        // Doc ngay tra phong (co the la "null")
                        String ldate = parts[3].trim();
                        if (!ldate.equalsIgnoreCase("null") && !ldate.isEmpty()) {
                            if (!ldate.matches("\\d{2}/\\d{2}/\\d{4}")) {
                                System.err.println("Warning: Skipping malformed line (invalid leave date format, expected dd/MM/yyyy): " + line);
                                continue;
                            }
                        } else {
                            ldate = "null";
                        }

                        int stateCode = Integer.parseInt(parts[4].trim());
                        BookingState state = BookingState.fromCode(stateCode);
                        addLast(new Booking(rcode, scode, bdate, ldate, state));
                        count++;
                    } catch (NumberFormatException e) {
                        System.err.println("Warning: Skipping malformed line (invalid state value): " + line);
                    }
                } else {
                    System.err.println("Warning: Skipping line with insufficient fields: " + line);
                }
            }
        }
        return count;
    }

    // Ghi du lieu booking ra file
    public void saveToFile(String filename) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            Node<Booking> current = head;
            while (current != null) {
                Booking b = current.info;
                pw.println(String.format("%s | %s | %s | %s | %d",
                        b.getRcode(), b.getScode(), b.getBdate(), b.getLdate(), b.getState().getCode()));
                current = current.next;
            }
        }
    }
}
