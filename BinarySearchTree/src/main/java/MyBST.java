/**
 * Lớp đại diện cho Cây Tìm kiếm Nhị phân (Binary Search Tree - BST).
 * Các nút trên cây được tổ chức sao cho:
 * - Tất cả các nút thuộc cây con bên trái của nút X đều có giá trị nhỏ hơn giá trị của X.
 * - Tất cả các nút thuộc cây con bên phải của nút X đều có giá trị lớn hơn hoặc bằng giá trị của X.
 */
public class MyBST {

    Node root; // Nút gốc của cây tìm kiếm nhị phân

    /**
     * Constructor khởi tạo cây nhị phân tìm kiếm rỗng.
     */
    public MyBST() {
        this.root = null; // Cây mới tạo chưa có nút nào, gốc là null
    }

    /**
     * Xóa toàn bộ cây bằng cách gán nút gốc về null.
     */
    public void clear() {
        this.root = null;
    }

    /**
     * Kiểm tra xem cây có đang rỗng hay không.
     * 
     * @return true nếu cây rỗng (root là null), ngược lại trả về false.
     */
    public boolean isEmpty() {
        return (root == null);
    }

    /**
     * Chèn một giá trị nguyên x vào cây tìm kiếm nhị phân.
     * 
     * @param x Giá trị cần chèn vào cây
     */
    public void insert(int x) {
        // Trường hợp 1: Cây đang rỗng, nút chèn vào sẽ trở thành nút gốc
        if (isEmpty()) {
            System.out.println("Insert root: " + x);
            Node newNode = new Node(x);
            root = newNode;
            return;
        }

        // Trường hợp 2: Cây không rỗng, tìm vị trí thích hợp để chèn nút mới
        Node curr;           // Con trỏ duyệt cây
        Node parentOfCurr;   // Nút cha của nút hiện tại (dùng để liên kết nút mới sau khi tìm được vị trí)
        curr = root;

        parentOfCurr = null;
        while (curr != null) {
            // Kiểm tra trùng lặp: BST không lưu trữ các giá trị trùng nhau
            if (curr.info == x) {
                System.out.println("The key " + x + " already exists, no insertion");
                return;
            }
            // Di chuyển con trỏ xuống cây con tương ứng
            parentOfCurr = curr; // Lưu lại nút cha hiện tại trước khi đi xuống tiếp
            if (x < curr.info) {
                curr = curr.left;  // Đi sang trái nếu giá trị chèn nhỏ hơn giá trị nút hiện tại
            } else {
                curr = curr.right; // Đi sang phải nếu giá trị chèn lớn hơn giá trị nút hiện tại
            }
        }

        // Tạo nút mới chứa giá trị x
        Node newNode = new Node(x);
        // Liên kết nút mới vào nút cha đã tìm được
        if (x < parentOfCurr.info) {
            parentOfCurr.left = newNode;  // Gắn làm con bên trái nếu x nhỏ hơn cha
        } else {
            parentOfCurr.right = newNode; // Gắn làm con bên phải nếu x lớn hơn cha
        }
    }

    /**
     * Chèn nhiều phần tử từ một mảng số nguyên vào cây.
     * 
     * @param a Mảng các số nguyên cần chèn
     */
    public void insertMany(int[] a) {
        for (int i = 0; i < a.length; i++) {
            insert(a[i]);
        }
    }

    /**
     * In ra màn hình giá trị của một nút.
     * 
     * @param p Nút cần in giá trị
     */
    public void visit(Node p) {
        System.out.print(p.info + " ");
    }

    /**
     * Duyệt cây theo chiều rộng (Breadth-first traversal / Level-order traversal).
     * Thuật toán sử dụng một hàng đợi (Queue) để duyệt từng tầng của cây từ trên xuống dưới, từ trái qua phải.
     */
    public void breadth() {
        // Nếu cây rỗng thì không cần duyệt
        if (isEmpty()) {
            return;
        }

        // Khởi tạo hàng đợi để hỗ trợ duyệt
        MyQueue q = new MyQueue();

        // Bước 1: Đưa nút gốc vào hàng đợi
        q.enqueue(root);
        Node visitNode = null;

        // Bước 2: Vòng lặp duyệt cho đến khi hàng đợi rỗng
        while (!q.isEmpty()) {
            // Lấy nút đầu tiên ra khỏi hàng đợi để thăm (visit)
            visitNode = q.dequeue();
            visit(visitNode);

            // Đưa nút con bên trái vào hàng đợi nếu nó tồn tại
            if (visitNode.left != null) {
                q.enqueue(visitNode.left);
            }
            // Đưa nút con bên phải vào hàng đợi nếu nó tồn tại (đã được sửa tách biệt)
            if (visitNode.right != null) {
                q.enqueue(visitNode.right);
            }
        }
    }
}
