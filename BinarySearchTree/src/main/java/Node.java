/**
 * Lớp đại diện cho một nút (Node) trong Cây Tìm kiếm Nhị phân (Binary Search Tree - BST).
 * Mỗi nút chứa một giá trị dữ liệu và tham chiếu đến hai nút con (trái và phải).
 */
public class Node {
    int info;    // Giá trị/dữ liệu được lưu trữ tại nút này
    Node left;   // Tham chiếu đến nút con bên trái (chứa các giá trị nhỏ hơn nút hiện tại)
    Node right;  // Tham chiếu đến nút con bên phải (chứa các giá trị lớn hơn hoặc bằng nút hiện tại)

    /**
     * Khởi tạo một nút rỗng không có giá trị và không có liên kết.
     */
    public Node() {
    }

    /**
     * Khởi tạo một nút mới với giá trị cụ thể và chỉ định các nút con trái/phải.
     * 
     * @param x     Giá trị gán cho nút
     * @param left  Nút con bên trái
     * @param right Nút con bên phải
     */
    public Node(int x, Node left, Node right) {
        this.info = x;
        this.left = left;
        this.right = right;
    }

    /**
     * Khởi tạo một nút mới chỉ với giá trị, mặc định hai con trái và phải là null.
     * 
     * @param x Giá trị gán cho nút
     */
    public Node(int x) {
        this(x, null, null); // Gọi constructor 3 tham số phía trên
    }
}
