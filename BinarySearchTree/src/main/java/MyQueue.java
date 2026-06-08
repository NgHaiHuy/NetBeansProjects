import java.util.LinkedList;

/**
 * Lớp đại diện cho một Hàng đợi (Queue) tự định nghĩa.
 * Hàng đợi hoạt động theo cơ chế FIFO (First In, First Out - Vào trước, Ra trước).
 * Ở đây, chúng ta sử dụng lại (reuse) lớp LinkedList có sẵn của Java để triển khai hàng đợi.
 * Hàng đợi này được sử dụng để hỗ trợ duyệt cây theo chiều rộng (Breadth-first traversal).
 */
public class MyQueue {
    // Sử dụng LinkedList để lưu trữ các nút (Node) của cây trong hàng đợi
    LinkedList<Node> queue;

    /**
     * Constructor khởi tạo một hàng đợi mới (rỗng).
     */
    public MyQueue() {
        this.queue = new LinkedList<Node>();
    }
    
    /**
     * Xóa sạch toàn bộ các phần tử trong hàng đợi.
     */
    public void clear() {
        this.queue.clear();
    }
    
    /**
     * Kiểm tra xem hàng đợi có đang rỗng hay không.
     * 
     * @return true nếu hàng đợi rỗng, ngược lại trả về false.
     */
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }
    
    /**
     * Thêm một nút mới vào cuối hàng đợi (Enqueue).
     * 
     * @param x Nút cần thêm vào hàng đợi
     */
    public void enqueue(Node x) {
        this.queue.addLast(x);
    }
    
    /**
     * Lấy ra và xóa nút ở đầu hàng đợi (Dequeue).
     * 
     * @return Nút ở đầu hàng đợi, hoặc null nếu hàng đợi rỗng.
     */
    public Node dequeue() {
        if (isEmpty()) return null; // Trả về null nếu hàng đợi không có phần tử nào
        Node p = this.queue.removeFirst(); // Lấy ra và xóa phần tử đầu tiên
        return p;
    }
    
    /**
     * Xem nút ở đầu hàng đợi mà không xóa nó khỏi hàng đợi (Front/Peek).
     * 
     * @return Nút ở đầu hàng đợi, hoặc null nếu hàng đợi rỗng.
     */
    public Node front() {
        if (isEmpty()) return null; // Trả về null nếu hàng đợi rỗng
        return this.queue.getFirst(); // Trả về phần tử đầu tiên nhưng giữ lại trong hàng đợi
    }
}
