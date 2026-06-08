package dsa;

import entity.Room;
import java.io.*;

// Danh sach lien ket don chua cac phong (Room)
public class RoomList {
    private Node<Room> head; // Node dau danh sach
    private Node<Room> tail; // Node cuoi danh sach

    // Khoi tao danh sach rong
    public RoomList() {
        head = tail = null;
    }

    // Kiem tra danh sach rong
    public boolean isEmpty() {
        return head == null;
    }

    // Xoa toan bo danh sach
    public void clear() {
        head = tail = null;
    }

    // Them phong vao cuoi danh sach
    public void addLast(Room room) {
        Node<Room> newNode = new Node<>(room);
        if (isEmpty()) {
            head = tail = newNode;  // Danh sach rong -> node moi la ca head va tail
        } else {
            tail.next = newNode;    // Noi node moi vao sau tail
            tail = newNode;         // Cap nhat tail
        }
    }

    // Them phong vao dau danh sach
    public void addFirst(Room room) {
        Node<Room> newNode = new Node<>(room);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;    // Node moi tro toi head cu
            head = newNode;         // Cap nhat head
        }
    }

    // Dem so luong phan tu trong danh sach
    public int size() {
        int count = 0;
        Node<Room> current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // Them phong truoc vi tri k (k tinh tu 0)
    public boolean addBefore(Room room, int k) {
        int n = size();
        if (k < 0 || k > n) return false;     // Vi tri khong hop le
        if (k == 0) { addFirst(room); return true; }  // Them vao dau
        if (k == n) { addLast(room); return true; }   // Them vao cuoi

        // Di chuyen den vi tri k-1
        Node<Room> newNode = new Node<>(room);
        Node<Room> current = head;
        for (int i = 0; i < k - 1; i++) {
            current = current.next;
        }
        // Chen node moi vao giua
        newNode.next = current.next;
        current.next = newNode;
        return true;
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

    // Xoa phong tai vi tri k (k tinh tu 0)
    public boolean deleteAt(int k) {
        int n = size();
        if (k < 0 || k >= n) return false;

        // Xoa node dau
        if (k == 0) {
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
            }
            return true;
        }

        // Di chuyen den vi tri k-1
        Node<Room> current = head;
        for (int i = 0; i < k - 1; i++) {
            current = current.next;
        }
        // Xoa node tai vi tri k
        if (current.next == tail) {
            tail = current;
            tail.next = null;
        } else {
            current.next = current.next.next;
        }
        return true;
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

    // Sap xep danh sach tang dan theo rcode (Selection Sort)
    public void sortByRcode() {
        if (isEmpty() || head.next == null) return;

        // Vong lap ngoai: chon tung vi tri
        for (Node<Room> pi = head; pi.next != null; pi = pi.next) {
            Node<Room> minNode = pi;
            // Vong lap trong: tim phan tu nho nhat con lai
            for (Node<Room> pj = pi.next; pj != null; pj = pj.next) {
                if (pj.info.getRcode().compareToIgnoreCase(minNode.info.getRcode()) < 0) {
                    minNode = pj;
                }
            }
            // Doi cho du lieu giua 2 node
            if (minNode != pi) {
                Room temp = pi.info;
                pi.info = minNode.info;
                minNode.info = temp;
            }
        }
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

    // Lay node dau danh sach
    public Node<Room> getHead() {
        return head;
    }

    // Doc du lieu phong tu file
    public void loadFromFile(String filename) throws IOException {
        clear();
        File file = new File(filename);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue; // Bo dong trong va dong comment

                // Tach cac truong bang dau "|"
                String[] parts = line.split("\\s*\\|\\s*");
                if (parts.length >= 8) {
                    String rcode = parts[0].trim();
                    String name = parts[1].trim();
                    String dom = parts[2].trim();
                    String floor = parts[3].trim();
                    String type = parts[4].trim();
                    int beds = Integer.parseInt(parts[5].trim());
                    int booked = Integer.parseInt(parts[6].trim());
                    double price = Double.parseDouble(parts[7].trim());
                    addLast(new Room(rcode, name, dom, floor, type, beds, booked, price));
                }
            }
        }
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
