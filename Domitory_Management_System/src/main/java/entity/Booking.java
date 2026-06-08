package entity;

import java.text.SimpleDateFormat;
import java.util.Date;

// Lop dai dien cho 1 lan dat phong (booking)
public class Booking {

    private String rcode;  // Ma phong duoc dat
    private String scode;  // Ma sinh vien dat phong
    private Date bdate;    // Ngay dat phong
    private Date ldate;    // Ngay tra phong (null neu chua tra)
    private int state;     // Trang thai: 1 = dang o, 0 = da tra

    // Dinh dang ngay thang: ngay/thang/nam
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Booking() {
    }

    public Booking(String rcode, String scode, Date bdate, Date ldate, int state) {
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

    public Date getBdate() {
        return bdate;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    public Date getLdate() {
        return ldate;
    }

    public void setLdate(Date ldate) {
        this.ldate = ldate;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Override
    public String toString() {
        String bdateStr = bdate != null ? sdf.format(bdate) : "null";
        String ldateStr = ldate != null ? sdf.format(ldate) : "null";
        return String.format("%-10s | %-10s | %-12s | %-12s | %-5d",
                rcode, scode, bdateStr, ldateStr, state);
    }
}
