package dsa;

import entity.Student;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 * Lớp StudentList đại diện cho Danh sách liên kết đơn tự phát triển dành cho sinh viên.
 */
public class StudentList {
    private Node<Student> head; // Node đầu của danh sách sinh viên
    private Node<Student> tail; // Node cuối của danh sách sinh viên

    // Khởi tạo danh sách trống
    public StudentList() {
        head = tail = null;
    }

    // Kiểm tra xem danh sách sinh viên có trống hay không
    public boolean isEmpty() {
        return head == null;
    }

    // Xóa tất cả các sinh viên khỏi danh sách liên kết
    public void clear() {
        head = tail = null;
    }

    // Lấy node đầu tiên (head)
    public Node<Student> getHead() {
        return head;
    }

    /**
     * Thêm sinh viên vào cuối danh sách liên kết.
     */
    public void addLast(Student student) {
        Node<Student> newNode = new Node<>(student);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    /**
     * Trả về tổng số lượng sinh viên trong danh sách.
     */
    public int size() {
        int count = 0;
        Node<Student> curr = head;
        while (curr != null) {
            count++;
            curr = curr.next;
        }
        return count;
    }

    /**
     * Tìm kiếm sinh viên theo mã sinh viên (scode).
     * @return Đối tượng Student nếu tìm thấy, ngược lại trả về null
     */
    public Student searchByScode(String scode) {
        Node<Student> curr = head;
        while (curr != null) {
            if (curr.info.getScode().equalsIgnoreCase(scode)) {
                return curr.info;
            }
            curr = curr.next;
        }
        return null;
    }

    /**
     * Tìm kiếm sinh viên theo tên (tìm kiếm chứa chuỗi, không phân biệt chữ hoa thường).
     * @return Danh sách StudentList mới chứa kết quả tìm kiếm
     */
    public StudentList searchByName(String name) {
        StudentList result = new StudentList();
        Node<Student> curr = head;
        while (curr != null) {
            if (curr.info.getName().toLowerCase().contains(name.toLowerCase())) {
                result.addLast(curr.info);
            }
            curr = curr.next;
        }
        return result;
    }

    /**
     * Xóa sinh viên ra khỏi danh sách dựa trên mã sinh viên scode.
     */
    public void deleteByScode(String scode) {
        if (isEmpty()) return;

        // Nếu sinh viên cần xóa nằm ngay ở node head
        if (head.info.getScode().equalsIgnoreCase(scode)) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
            return;
        }

        // Tìm sinh viên trong danh sách để cắt liên kết
        Node<Student> curr = head;
        while (curr.next != null) {
            if (curr.next.info.getScode().equalsIgnoreCase(scode)) {
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
     * Nạp dữ liệu danh sách sinh viên từ file students.txt.
     * Cú pháp dòng trong file: scode|name|byear
     */
    public void loadFromFile(String filename) {
        clear();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length >= 3) {
                    String scode = parts[0].trim();
                    String name = parts[1].trim();
                    int byear = Integer.parseInt(parts[2].trim());
                    Student s = new Student(scode, name, byear);
                    addLast(s);
                }
            }
            System.out.println("Tải danh sách sinh viên thành công từ " + filename + " (" + size() + " sinh viên)");
        } catch (Exception e) {
            System.out.println("Lỗi khi tải dữ liệu từ file " + filename + ": " + e.getMessage());
        }
    }

    /**
     * Lưu danh sách sinh viên hiện tại vào file students.txt.
     */
    public void saveToFile(String filename) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            Node<Student> curr = head;
            while (curr != null) {
                Student s = curr.info;
                pw.printf("%s|%s|%d\n", s.getScode(), s.getName(), s.getByear());
                curr = curr.next;
            }
            System.out.println("Đã lưu danh sách sinh viên vào file " + filename);
        } catch (Exception e) {
            System.out.println("Lỗi khi lưu dữ liệu vào file " + filename + ": " + e.getMessage());
        }
    }

    /**
     * Hiển thị danh sách sinh viên dưới dạng bảng.
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("Danh sách sinh viên hiện đang trống.");
            return;
        }
        System.out.println("+----------+----------------------+-------+");
        System.out.println("| Scode    | Name                 | Byear |");
        System.out.println("+----------+----------------------+-------+");
        Node<Student> curr = head;
        while (curr != null) {
            System.out.println(curr.info.toString());
            curr = curr.next;
        }
        System.out.println("+----------+----------------------+-------+");
    }
}
