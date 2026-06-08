package dsa;

import entity.Room;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Lớp RoomList biểu diễn Danh sách liên kết đơn tự phát triển dành riêng cho các đối tượng Room.
 */
public class RoomList {
    private Node<Room> head; // Node đầu tiên của danh sách
    private Node<Room> tail; // Node cuối cùng của danh sách

    // Khởi tạo danh sách trống
    public RoomList() {
        head = tail = null;
    }

    // Kiểm tra danh sách có trống hay không
    public boolean isEmpty() {
        return head == null;
    }

    // Xóa sạch toàn bộ phần tử trong danh sách
    public void clear() {
        head = tail = null;
    }

    // Lấy node đầu tiên (head)
    public Node<Room> getHead() {
        return head;
    }

    /**
     * Thêm một phòng vào cuối danh sách liên kết.
     */
    public void addLast(Room room) {
        Node<Room> newNode = new Node<>(room);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    /**
     * Thêm một phòng vào đầu danh sách liên kết.
     */
    public void addFirst(Room room) {
        Node<Room> newNode = new Node<>(room);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    /**
     * Tính kích thước (số lượng phòng) hiện tại trong danh sách.
     */
    public int size() {
        int count = 0;
        Node<Room> curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    /**
     * Tìm kiếm phòng theo mã phòng (rcode).
     * Trả về đối tượng Room nếu tìm thấy, ngược lại trả về null.
     */
    public Room searchByRcode(String rcode) {
        Node<Room> curr = head;
        while (curr != null) {
            if (curr.info.getRcode().equalsIgnoreCase(rcode)) {
                return curr.info;
            }
            curr = curr.next;
        }
        return null;
    }

    /**
     * Tìm kiếm vị trí (chỉ số 0-based) của phòng theo rcode.
     */
    public int searchIndexByRcode(String rcode) {
        Node<Room> curr = head;
        int index = 0;
        while (curr != null) {
            if (curr.info.getRcode().equalsIgnoreCase(rcode)) {
                return index;
            }
            curr = curr.next;
            index++;
        }
        return -1;
    }

    /**
     * Tìm danh sách các phòng mà tên có chứa chuỗi tìm kiếm (không phân biệt chữ hoa thường).
     * @return một danh sách RoomList mới chứa kết quả tìm kiếm
     */
    public RoomList searchByName(String name) {
        RoomList result = new RoomList();
        Node<Room> curr = head;
        while (curr != null) {
            if (curr.info.getName().toLowerCase().contains(name.toLowerCase())) {
                result.addLast(curr.info);
            }
            curr = curr.next;
        }
        return result;
    }

    /**
     * Thêm một phòng vào trước vị trí thứ k (k bắt đầu từ 1).
     * Tương đương thêm phòng vào vị trí chỉ số k-1 (0-based).
     */
    public void addBefore(Room room, int position) {
        int n = size();
        // Nếu vị trí k nhỏ hơn hoặc bằng 1, thêm vào đầu
        if (position <= 1) {
            addFirst(room);
            return;
        }
        // Nếu vị trí vượt quá số phần tử hiện tại + 1, chèn vào cuối
        if (position > n + 1) {
            System.out.println("Vị trí vượt quá kích thước danh sách. Tự động thêm vào cuối.");
            addLast(room);
            return;
        }
        // Nếu k bằng n + 1, tương đương thêm cuối
        if (position == n + 1) {
            addLast(room);
            return;
        }

        // Tìm node ở vị trí k-2 (để chèn vào trước k-1)
        Node<Room> curr = head;
        for (int i = 1; i < position - 1; i++) {
            curr = curr.next;
        }
        // Tạo node mới và liên kết
        Node<Room> newNode = new Node<>(room, curr.next);
        curr.next = newNode;
    }

    /**
     * Xóa phần tử tại vị trí thứ k (1-indexed).
     */
    public void deletePosition(int position) {
        int n = size();
        if (position < 1 || position > n) {
            System.out.println("Vị trí xóa không hợp lệ!");
            return;
        }
        // Nếu xóa phần tử đầu tiên
        if (position == 1) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
            return;
        }
        
        // Tìm node ở trước node cần xóa
        Node<Room> curr = head;
        for (int i = 1; i < position - 1; i++) {
            curr = curr.next;
        }
        Node<Room> target = curr.next;
        curr.next = target.next;
        // Nếu xóa node cuối cùng, cập nhật lại tail
        if (target == tail) {
            tail = curr;
        }
    }

    /**
     * Xóa một phòng ra khỏi danh sách dựa vào rcode.
     */
    public void deleteByRcode(String rcode) {
        if (isEmpty()) return;

        // Nếu phòng cần xóa nằm ngay ở head
        if (head.info.getRcode().equalsIgnoreCase(rcode)) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
            return;
        }

        // Duyệt danh sách để tìm phòng có rcode khớp và xóa liên kết
        Node<Room> curr = head;
        while (curr.next != null) {
            if (curr.next.info.getRcode().equalsIgnoreCase(rcode)) {
                if (curr.next == tail) {
                    tail = curr;
                }
                curr.next = curr.next.next;
                return;
            }
            curr = curr.next;
        }
    }

    /**
     * Sắp xếp danh sách các phòng theo mã phòng rcode tăng dần (Thứ tự từ điển).
     * Sử dụng thuật toán Bubble Sort tráo đổi giá trị info của các node.
     */
    public void sortByRcode() {
        if (head == null || head.next == null) return;
        boolean swapped;
        do {
            swapped = false;
            Node<Room> curr = head;
            while (curr.next != null) {
                if (curr.info.getRcode().compareToIgnoreCase(curr.next.info.getRcode()) > 0) {
                    // Tráo đổi dữ liệu info của 2 node liền kề
                    Room temp = curr.info;
                    curr.info = curr.next.info;
                    curr.next.info = temp;
                    swapped = true;
                }
                curr = curr.next;
            }
        } while (swapped);
    }

    /**
     * Nạp danh sách phòng từ file rooms.txt.
     * Cú pháp dòng trong file: rcode|name|dom|floor|type|beds|booked|price
     */
    public void loadFromFile(String filename) {
        clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length >= 8) {
                    String rcode = parts[0].trim();
                    String name = parts[1].trim();
                    String dom = parts[2].trim();
                    String floor = parts[3].trim();
                    String type = parts[4].trim();
                    int beds = Integer.parseInt(parts[5].trim());
                    int booked = Integer.parseInt(parts[6].trim());
                    double price = Double.parseDouble(parts[7].trim());
                    Room room = new Room(rcode, name, dom, floor, type, beds, booked, price);
                    addLast(room);
                }
            }
            System.out.println("Tải dữ liệu thành công từ " + filename + " (" + size() + " phòng)");
        } catch (Exception e) {
            System.out.println("Lỗi khi tải dữ liệu từ file " + filename + ": " + e.getMessage());
        }
    }

    /**
     * Lưu danh sách phòng hiện tại xuống file rooms.txt.
     */
    public void saveToFile(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            Node<Room> curr = head;
            while (curr != null) {
                Room r = curr.info;
                pw.printf("%s|%s|%s|%s|%s|%d|%d|%.2f\n", 
                    r.getRcode(), r.getName(), r.getDom(), r.getFloor(), r.getType(), r.getBeds(), r.getBooked(), r.getPrice());
                curr = curr.next;
            }
            System.out.println("Đã lưu danh sách phòng vào file " + filename);
        } catch (Exception e) {
            System.out.println("Lỗi khi lưu dữ liệu vào file " + filename + ": " + e.getMessage());
        }
    }

    /**
     * Hiển thị toàn bộ danh sách phòng dưới dạng bảng trực quan.
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("Danh sách phòng hiện đang trống.");
            return;
        }
        System.out.println("+----------+--------------+----------+--------+----------+------+--------+----------+");
        System.out.println("| Rcode    | Name         | Dom      | Floor  | Type     | Beds | Booked | Price    |");
        System.out.println("+----------+--------------+----------+--------+----------+------+--------+----------+");
        Node<Room> curr = head;
        while (curr != null) {
            System.out.println(curr.info.toString());
            curr = curr.next;
        }
        System.out.println("+----------+--------------+----------+--------+----------+------+--------+----------+");
    }
}
