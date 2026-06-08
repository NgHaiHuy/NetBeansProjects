package dsa;

import entity.Student;
import java.io.*;

// Danh sach lien ket don chua cac sinh vien (Student)
public class StudentList {
    private Node<Student> head; // Node dau danh sach
    private Node<Student> tail; // Node cuoi danh sach

    // Khoi tao danh sach rong
    public StudentList() {
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

    // Them sinh vien vao cuoi danh sach
    public void addLast(Student student) {
        Node<Student> newNode = new Node<>(student);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    // Dem so luong phan tu
    public int size() {
        int count = 0;
        Node<Student> current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // Tim sinh vien theo ma sinh vien (scode)
    public Node<Student> searchByScode(String scode) {
        Node<Student> current = head;
        while (current != null) {
            if (current.info.getScode().equalsIgnoreCase(scode.trim())) {
                return current;  // Tim thay
            }
            current = current.next;
        }
        return null;  // Khong tim thay
    }

    // Xoa sinh vien theo ma sinh vien (scode)
    public boolean deleteByScode(String scode) {
        if (isEmpty()) return false;

        // Xoa node dau
        if (head.info.getScode().equalsIgnoreCase(scode.trim())) {
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
            }
            return true;
        }

        // Tim node truoc node can xoa
        Node<Student> current = head;
        while (current.next != null &&
               !current.next.info.getScode().equalsIgnoreCase(scode.trim())) {
            current = current.next;
        }

        // Xoa node
        if (current.next != null) {
            if (current.next == tail) {
                tail = current;
                tail.next = null;
            } else {
                current.next = current.next.next;
            }
            return true;
        }
        return false;
    }

    // Tim cac sinh vien co ten chua chuoi 'name'
    public StudentList searchByName(String name) {
        StudentList result = new StudentList();
        Node<Student> current = head;
        while (current != null) {
            if (current.info.getName().toLowerCase().contains(name.toLowerCase().trim())) {
                result.addLast(current.info);
            }
            current = current.next;
        }
        return result;
    }

    // Hien thi toan bo danh sach sinh vien
    public void display() {
        if (isEmpty()) {
            System.out.println("Student list is empty.");
            return;
        }
        System.out.println(String.format("%-10s | %-20s | %-6s", "Scode", "Student Name", "Byear"));
        System.out.println("----------------------------------------");
        Node<Student> current = head;
        while (current != null) {
            System.out.println(current.info.toString());
            current = current.next;
        }
    }

    // Lay node dau danh sach
    public Node<Student> getHead() {
        return head;
    }

    // Doc du lieu sinh vien tu file
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
                if (parts.length >= 3) {
                    String scode = parts[0].trim();
                    String name = parts[1].trim();
                    int byear = Integer.parseInt(parts[2].trim());
                    addLast(new Student(scode, name, byear));
                }
            }
        }
    }

    // Ghi du lieu sinh vien ra file
    public void saveToFile(String filename) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            Node<Student> current = head;
            while (current != null) {
                Student s = current.info;
                pw.println(String.format("%s | %s | %d",
                        s.getScode(), s.getName(), s.getByear()));
                current = current.next;
            }
        }
    }
}
