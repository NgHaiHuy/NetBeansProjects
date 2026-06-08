package entity;

// Lop dai dien cho 1 phong trong ky tuc xa
public class Room {

    private String rcode;   // Ma phong (duy nhat)
    private String name;    // Ten phong
    private String dom;     // Ten ky tuc xa
    private String floor;   // Tang
    private String type;    // Loai phong: "double" hoac "triple"
    private int beds;       // So giuong con trong (4 cho double, 6 cho triple)
    private int booked;     // So giuong da dat
    private double price;   // Gia 1 giuong

    public Room() {
    }

    public Room(String rcode, String name, String dom, String floor,
            String type, int beds, int booked, double price) {
        this.rcode = rcode;
        this.name = name;
        this.dom = dom;
        this.floor = floor;
        this.type = type.toLowerCase();
        this.beds = beds;
        this.booked = booked;
        this.price = price;
    }

    public String getRcode() {
        return rcode;
    }

    public void setRcode(String rcode) {
        this.rcode = rcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDom() {
        return dom;
    }

    public void setDom(String dom) {
        this.dom = dom;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBeds() {
        return beds;
    }

    public void setBeds(int beds) {
        this.beds = beds;
    }

    public int getBooked() {
        return booked;
    }

    public void setBooked(int booked) {
        this.booked = booked;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%-10s | %-15s | %-10s | %-8s | %-8s | %-4d | %-6d | %-8.2f",
                rcode, name, dom, floor, type, beds, booked, price);
    }
}
