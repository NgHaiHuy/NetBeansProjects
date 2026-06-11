package dsa;

import entity.Student;
import java.io.*;

// Danh sach lien ket don chua cac sinh vien (Student)
public class StudentList extends MyLinkedList<Student> {

    // Khoi tao danh sach rong
    public StudentList() {
        super();
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

    // Doc du lieu sinh vien tu file.
    // Tra ve so luong sinh vien load duoc, hoac -1 neu file khong ton tai.
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
                if (parts.length >= 3) {
                    try {
                        String scode = parts[0].trim();
                        String name = parts[1].trim();
                        int byear = Integer.parseInt(parts[2].trim());
                        if (scode.isEmpty() || name.isEmpty()) {
                            System.err.println("Warning: Skipping line with empty fields: " + line);
                            continue;
                        }
                        addLast(new Student(scode, name, byear));
                        count++;
                    } catch (NumberFormatException e) {
                        System.err.println("Warning: Skipping malformed line (invalid birth year): " + line);
                    }
                } else {
                    System.err.println("Warning: Skipping line with insufficient fields: " + line);
                }
            }
        }
        return count;
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
