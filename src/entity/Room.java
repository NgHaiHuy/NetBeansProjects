package entity;

/**
 * Lớp Room đại diện cho thực thể Phòng trong hệ thống quản lý ký túc xá.
 * Chứa các thông tin cơ bản về phòng bao gồm mã phòng, tên phòng, tòa ký túc, 
 * tầng, loại phòng, tổng số giường, số giường đã đặt và giá tiền của một giường.
 */
public class Room {
    private String rcode;   // Mã phòng (độc nhất)
    private String name;    // Tên phòng
    private String dom;     // Tên tòa nhà ký túc xá (ví dụ: Dom A, Dom B)
    private String floor;   // Tầng của phòng
    private String type;    // Loại phòng: chỉ nhận 2 giá trị "double" hoặc "triple"
    private int beds;       // Số giường khả dụng/chưa đặt trong phòng (double: 4 giường, triple: 6 giường ban đầu)
    private int booked;     // Số giường đã được đặt (điều kiện: booked <= tổng giường ban đầu)
    private double price;   // Giá thuê của 1 giường

    // Constructor mặc định không tham số
    public Room() {
    }

    // Constructor có tham số đầy đủ để khởi tạo một đối tượng Room
    public Room(String rcode, String name, String dom, String floor, String type, int beds, int booked, double price) {
        this.rcode = rcode;
        this.name = name;
        this.dom = dom;
        this.floor = floor;
        this.type = type.toLowerCase();
        this.beds = beds;
        this.booked = booked;
        this.price = price;
    }

    // --- Các phương thức Getter và Setter cho các thuộc tính ---

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
        this.type = type.toLowerCase();
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

    /**
     * Phương thức toString() hỗ trợ in dữ liệu phòng theo định dạng hàng trong bảng.
     */
    @Override
    public String toString() {
        return String.format("| %-8s | %-12s | %-8s | %-6s | %-8s | %-4d | %-6d | %-8.2f |", 
                rcode, name, dom, floor, type, beds, booked, price);
    }
}
