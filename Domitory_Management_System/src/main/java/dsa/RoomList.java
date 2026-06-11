package dsa;

import entity.Room;
import java.io.*;

// Danh sach lien ket don chua cac phong (Room)
public class RoomList extends MyLinkedList<Room> {

    // Khoi tao danh sach rong
    public RoomList() {
        super();
    }

    // Tim phong theo ma phong (rcode)
    public Node<Room> searchByRcode(String rcode) {
        Node<Room> current = head;
        while (current != null) {
            if (current.info.getRcode().equalsIgnoreCase(rcode.trim())) {
                return current;  // Tim thay
            }
            current = current.next;
        }
        return null;  // Khong tim thay
    }

    // Xoa phong theo ma phong (rcode)
    public boolean deleteByRcode(String rcode) {
        if (isEmpty()) return false;

        // Neu node dau la node can xoa
        if (head.info.getRcode().equalsIgnoreCase(rcode.trim())) {
            if (head == tail) {
                head = tail = null;  // Chi co 1 phan tu
            } else {
                head = head.next;    // Bo node dau
            }
            return true;
        }

        // Tim node truoc node can xoa
        Node<Room> current = head;
        while (current.next != null &&
               !current.next.info.getRcode().equalsIgnoreCase(rcode.trim())) {
            current = current.next;
        }

        // Xoa node tim duoc
        if (current.next != null) {
            if (current.next == tail) {
                tail = current;      // Neu xoa node cuoi thi cap nhat tail
                tail.next = null;
            } else {
                current.next = current.next.next;  // Bo qua node can xoa
            }
            return true;
        }
        return false;  // Khong tim thay
    }

    // Tim cac phong co ten chua chuoi 'name'
    public RoomList searchByName(String name) {
        RoomList result = new RoomList();
        Node<Room> current = head;
        while (current != null) {
            // So sanh khong phan biet hoa thuong
            if (current.info.getName().toLowerCase().contains(name.toLowerCase().trim())) {
                result.addLast(current.info);
            }
            current = current.next;
        }
        return result;
    }

    // =========================================================================
    // THUẬT TOÁN SẮP XẾP MERGE SORT O(N LOG N) TRÊN SINGLY LINKED LIST (ROOMS)
    // =========================================================================

    // Hàm gọi chính để sắp xếp danh sách phòng tăng dần theo rcode
    public void sortByRcode() {
        if (isEmpty() || head.next == null) return;
        
        // Gọi đệ quy Merge Sort để sắp xếp lại chuỗi Node và cập nhật head mới
        head = mergeSort(head);

        // Duyệt tìm Node cuối cùng sau khi đã sắp xếp để cập nhật lại con trỏ tail
        Node<Room> current = head;
        while (current != null && current.next != null) {
            current = current.next;
        }
        tail = current;
    }

    // Hàm đệ quy thực hiện thuật toán chia để trị (Divide and Conquer)
    private Node<Room> mergeSort(Node<Room> h) {
        // Trường hợp cơ sở: Danh sách rỗng hoặc chỉ có 1 phần tử thì không cần sắp xếp
        if (h == null || h.next == null) {
            return h;
        }

        // Bước 1: Tìm Node ở chính giữa danh sách để chia đôi
        Node<Room> middle = getMiddle(h);
        Node<Room> nextOfMiddle = middle.next;

        // Bước 2: Tách đôi danh sách bằng cách ngắt kết nối tại vị trí giữa
        middle.next = null;

        // Bước 3: Đệ quy sắp xếp nửa bên trái và nửa bên phải
        Node<Room> left = mergeSort(h);
        Node<Room> right = mergeSort(nextOfMiddle);

        // Bước 4: Trộn (Merge) hai nửa đã được sắp xếp lại với nhau theo thứ tự tăng dần
        return sortedMerge(left, right);
    }

    // Hàm trộn hai danh sách liên kết đơn đã được sắp xếp thành một danh sách duy nhất
    private Node<Room> sortedMerge(Node<Room> a, Node<Room> b) {
        Node<Room> result;
        
        // Nếu một trong hai nửa rỗng, trả về nửa còn lại
        if (a == null) return b;
        if (b == null) return a;

        // So sánh mã phòng (rcode) không phân biệt chữ hoa/thường để sắp xếp tăng dần
        if (a.info.getRcode().compareToIgnoreCase(b.info.getRcode()) <= 0) {
            result = a;
            // Đệ quy ghép phần còn lại của danh sách a với danh sách b
            result.next = sortedMerge(a.next, b);
        } else {
            result = b;
            // Đệ quy ghép danh sách a với phần còn lại của danh sách b
            result.next = sortedMerge(a, b.next);
        }
        return result;
    }

    // Hàm tìm Node ở giữa bằng kỹ thuật "con trỏ nhanh và chậm" (Runner Technique)
    private Node<Room> getMiddle(Node<Room> h) {
        if (h == null) return h;
        Node<Room> fast = h.next; // Con trỏ nhanh di chuyển 2 bước mỗi lần
        Node<Room> slow = h;      // Con trỏ chậm di chuyển 1 bước mỗi lần

        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }
        }
        // Khi con trỏ nhanh đi hết danh sách, con trỏ chậm sẽ dừng đúng ở giữa
        return slow;
    }

    // Hien thi toan bo danh sach phong
    public void display() {
        if (isEmpty()) {
            System.out.println("Room list is empty.");
            return;
        }
        // In tieu de bang
        System.out.println(String.format("%-10s | %-15s | %-10s | %-8s | %-8s | %-4s | %-6s | %-8s",
                "Rcode", "Room Name", "Dormitory", "Floor", "Type", "Beds", "Booked", "Price"));
        System.out.println("---------------------------------------------------------------------------------------");
        // In tung phong
        Node<Room> current = head;
        while (current != null) {
            System.out.println(current.info.toString());
            current = current.next;
        }
    }

    // Doc du lieu phong tu file.
    // Tra ve so luong phong load duoc, hoac -1 neu file khong ton tai.
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

                // Tach cac truong bang dau "|"
                String[] parts = line.split("\\s*\\|\\s*");
                if (parts.length >= 8) {
                    try {
                        String rcode = parts[0].trim();
                        String name = parts[1].trim();
                        String dom = parts[2].trim();
                        String floor = parts[3].trim();
                        String type = parts[4].trim();
                        int beds = Integer.parseInt(parts[5].trim());
                        int booked = Integer.parseInt(parts[6].trim());
                        double price = Double.parseDouble(parts[7].trim());

                        if (rcode.isEmpty() || name.isEmpty() || dom.isEmpty() || floor.isEmpty() || type.isEmpty()) {
                            System.err.println("Warning: Skipping line with empty fields: " + line);
                            continue;
                        }

                        addLast(new Room(rcode, name, dom, floor, type, beds, booked, price));
                        count++;
                    } catch (NumberFormatException e) {
                        System.err.println("Warning: Skipping malformed line (invalid numeric format): " + line);
                    }
                } else {
                    System.err.println("Warning: Skipping line with insufficient fields: " + line);
                }
            }
        }
        return count;
    }

    // Ghi du lieu phong ra file
    public void saveToFile(String filename) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            Node<Room> current = head;
            while (current != null) {
                Room r = current.info;
                pw.println(String.format("%s | %s | %s | %s | %s | %d | %d | %.2f",
                        r.getRcode(), r.getName(), r.getDom(), r.getFloor(),
                        r.getType(), r.getBeds(), r.getBooked(), r.getPrice()));
                current = current.next;
            }
        }
    }
}
