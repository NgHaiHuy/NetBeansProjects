package entity;

// Lop dai dien cho 1 sinh vien
public class Student {

    private String scode;  // Ma sinh vien (duy nhat)
    private String name;   // Ten sinh vien
    private int byear;     // Nam sinh (phai du 18 tuoi)

    public Student() {
    }

    public Student(String scode, String name, int byear) {
        this.scode = scode;
        this.name = name;
        this.byear = byear;
    }

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

    @Override
    public String toString() {
        return String.format("%-10s | %-20s | %-6d", scode, name, byear);
    }
}
