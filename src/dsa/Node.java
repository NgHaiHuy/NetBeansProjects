package dsa;

/**
 * Lớp Node đại diện cho một phần tử (node) trong cấu trúc dữ liệu Danh sách liên kết đơn.
 * @param <T> Loại thực thể dữ liệu được lưu trữ trong node (Ví dụ: Room, Student, hoặc Booking)
 */
public class Node<T> {
    public T info;        // Dữ liệu của node
    public Node<T> next;  // Tham chiếu (con trỏ) đến node tiếp theo trong danh sách

    // Constructor mặc định không tham số
    public Node() {
    }

    // Constructor tạo node mới với thông tin dữ liệu (next sẽ trỏ tới null)
    public Node(T info) {
        this.info = info;
        this.next = null;
    }

    // Constructor tạo node mới với thông tin dữ liệu và tham chiếu đến node tiếp theo
    public Node(T info, Node<T> next) {
        this.info = info;
        this.next = next;
    }
}
