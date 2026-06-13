package entity;

public class Booking {

    private String rcode;       // Ma phong duoc dat
    private String scode;       // Ma sinh vien dat phong
    private String bdate;       // Ngay dat phong (dd/MM/yyyy)
    private String ldate;       // Ngay tra phong (dd/MM/yyyy hoac "null")
    private BookingState state; // Trang thai: ACTIVE (dang o), LEFT (da tra)

    public Booking() {
    }

    public Booking(String rcode, String scode, String bdate, String ldate, BookingState state) {
        this.rcode = rcode;
        this.scode = scode;
        this.bdate = bdate;
        this.ldate = ldate;
        this.state = state;
    }

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

    public String getBdate() {
        return bdate;
    }

    public void setBdate(String bdate) {
        this.bdate = bdate;
    }

    public String getLdate() {
        return ldate;
    }

    public void setLdate(String ldate) {
        this.ldate = ldate;
    }

    public BookingState getState() {
        return state;
    }

    public void setState(BookingState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-10s | %-12s | %-12s | %-5d",
                rcode, scode, bdate, ldate, state.getCode());
    }
}
