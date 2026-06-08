package entity;

import java.time.LocalDate;
import util.Validation;

/**
 * Lớp Booking đại diện cho giao dịch đặt phòng của sinh viên tại ký túc xá.
 * Lưu trữ mã phòng được thuê, mã sinh viên thuê phòng, ngày bắt đầu thuê (bdate),
 * ngày trả phòng (ldate) và trạng thái thuê (state).
 */
public class Booking {
    private String rcode;      // Mã phòng được đặt
    private String scode;      // Mã sinh viên đặt phòng
    private LocalDate bdate;   // Ngày bắt đầu thuê phòng (bdate)
    private LocalDate ldate;   // Ngày rời phòng / trả phòng (ldate, có thể là null)
    private int state;         // Trạng thái: 1: vẫn đang thuê (ldate phải null), 0: đã rời phòng (ldate không null)

    // Constructor mặc định
    public Booking() {
    }

    // Constructor có tham số đầy đủ
    public Booking(String rcode, String scode, LocalDate bdate, LocalDate ldate, int state) {
        this.rcode = rcode;
        this.scode = scode;
        this.bdate = bdate;
        this.ldate = ldate;
        this.state = state;
    }

    // --- Các phương thức Getter và Setter cho Booking ---

    public String getRcode() {
        return rcode;
    }

    public void setRcode(String rcode) {
        this.rcode = rcode;
    }

    public String getScode() {
        return scode;
    }

    public void setScode(String scode) {
        this.scode = scode;
    }

    public LocalDate getBdate() {
        return bdate;
    }

    public void setBdate(LocalDate bdate) {
        this.bdate = bdate;
    }

    public LocalDate getLdate() {
        return ldate;
    }

    public void setLdate(LocalDate ldate) {
        this.ldate = ldate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    /**
     * Phương thức toString() chuyển đổi thông tin đặt phòng sang dòng dữ liệu hiển thị.
     */
    @Override
    public String toString() {
        return String.format("| %-8s | %-8s | %-10s | %-10s | %-5d |", 
                rcode, scode, Validation.formatDate(bdate), Validation.formatDate(ldate), state);
    }
}
