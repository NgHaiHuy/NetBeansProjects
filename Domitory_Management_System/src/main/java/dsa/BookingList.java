package dsa;

import entity.Booking;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Danh sach lien ket don chua cac booking (dat phong)
public class BookingList {
    private Node<Booking> head; // Node dau danh sach
    private Node<Booking> tail; // Node cuoi danh sach
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    // Khoi tao danh sach rong
    public BookingList() {
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

    // Them booking vao dau danh sach (dung khi dat phong moi)
    public void addFirst(Booking booking) {
        Node<Booking> newNode = new Node<>(booking);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    // Them booking vao cuoi danh sach (dung khi doc tu file)
    public void addLast(Booking booking) {
        Node<Booking> newNode = new Node<>(booking);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    // Dem so luong booking
    public int size() {
        int count = 0;
        Node<Booking> current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // Tim booking dang hoat dong (state=1) theo rcode va scode
    public Booking searchActiveBooking(String rcode, String scode) {
        Node<Booking> current = head;
        while (current != null) {
            Booking b = current.info;
            if (b.getRcode().equalsIgnoreCase(rcode.trim()) &&
                b.getScode().equalsIgnoreCase(scode.trim()) &&
                b.getState() == 1) {
                return b;  // Tim thay booking dang hoat dong
            }
            current = current.next;
        }
        return null;  // Khong tim thay
    }

    // Kiem tra sinh vien co dang o phong nao khong (co booking state=1)
    public boolean hasActiveBooking(String scode) {
        Node<Booking> current = head;
        while (current != null) {
            Booking b = current.info;
            if (b.getScode().equalsIgnoreCase(scode.trim()) && b.getState() == 1) {
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

    // Sap xep giam dan theo rcode, neu rcode bang nhau thi giam dan theo scode
    // Su dung Selection Sort
    public void sortByRcodeAndScodeDesc() {
        if (isEmpty() || head.next == null) return;

        for (Node<Booking> pi = head; pi.next != null; pi = pi.next) {
            Node<Booking> maxNode = pi;
            for (Node<Booking> pj = pi.next; pj != null; pj = pj.next) {
                // So sanh rcode giam dan
                int rcodeComp = pj.info.getRcode().compareToIgnoreCase(maxNode.info.getRcode());
                if (rcodeComp > 0) {
                    maxNode = pj;
                } else if (rcodeComp == 0) {
                    // Neu rcode bang nhau, so sanh scode giam dan
                    if (pj.info.getScode().compareToIgnoreCase(maxNode.info.getScode()) > 0) {
                        maxNode = pj;
                    }
                }
            }
            // Doi cho du lieu
            if (maxNode != pi) {
                Booking temp = pi.info;
                pi.info = maxNode.info;
                maxNode.info = temp;
            }
        }
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

    // Lay node dau danh sach
    public Node<Booking> getHead() {
        return head;
    }

    // Doc du lieu booking tu file
    public void loadFromFile(String filename) throws IOException {
        clear();
        File file = new File(filename);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#")) continue;

                String[] parts = line.split("\\s*\\|\\s*");
                if (parts.length >= 5) {
                    String rcode = parts[0].trim();
                    String scode = parts[1].trim();

                    // Doc ngay dat phong
                    Date bdate = null;
                    try { bdate = sdf.parse(parts[2].trim()); }
                    catch (ParseException e) { /* bo qua loi */ }

                    // Doc ngay tra phong (co the la "null")
                    Date ldate = null;
                    String ldateStr = parts[3].trim();
                    if (!ldateStr.equalsIgnoreCase("null") && !ldateStr.isEmpty()) {
                        try { ldate = sdf.parse(ldateStr); }
                        catch (ParseException e) { /* bo qua loi */ }
                    }

                    int state = Integer.parseInt(parts[4].trim());
                    addLast(new Booking(rcode, scode, bdate, ldate, state));
                }
            }
        }
    }

    // Ghi du lieu booking ra file
    public void saveToFile(String filename) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            Node<Booking> current = head;
            while (current != null) {
                Booking b = current.info;
                String bdateStr = b.getBdate() != null ? sdf.format(b.getBdate()) : "null";
                String ldateStr = b.getLdate() != null ? sdf.format(b.getLdate()) : "null";
                pw.println(String.format("%s | %s | %s | %s | %d",
                        b.getRcode(), b.getScode(), bdateStr, ldateStr, b.getState()));
                current = current.next;
            }
        }
    }
}
