package entity;

/**
 * Lớp Student đại diện cho thực thể Sinh viên trong hệ thống ký túc xá.
 * Gồm các thuộc tính mã sinh viên, tên sinh viên và năm sinh sinh viên.
 */
public class Student {
    private String scode; // Mã sinh viên (độc nhất)
    private String name;  // Tên sinh viên
    private int byear;    // Năm sinh (phải đảm bảo >= 18 tuổi)

    // Constructor mặc định
    public Student() {
    }

    // Constructor có đầy đủ tham số
    public Student(String scode, String name, int byear) {
        this.scode = scode;
        this.name = name;
        this.byear = byear;
    }

    // --- Các phương thức Getter và Setter cho Student ---

    public String getScode() {
        return scode;
    }

    public void setScode(String scode) {
        this.scode = scode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getByear() {
        return byear;
    }

    public void setByear(int byear) {
        this.byear = byear;
    }

    /**
     * Định dạng in sinh viên dưới dạng hàng của bảng.
     */
    @Override
    public String toString() {
        return String.format("| %-8s | %-20s | %-5d |", scode, name, byear);
    }
}
