/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LECOO
 */
public class MyList {

    Node head, tail;

    public MyList() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void clear() {
        head = tail = null;
    }

    // 1. Thêm một phần tử vào cuối danh sách
    public void addLast(int x) {
        Node q = new Node(x);
        if (isEmpty()) {
            head = tail = q;
        } else {
            tail.next = q;
            tail = q;
        }
    }

    // 2. Thêm một phần tử vào đầu danh sách
    public void addFirst(int x) {
        Node q = new Node(x);
        if (isEmpty()) {
            head = tail = q;
        } else {
            q.next = head;
            head = q;
        }
    }

    // 3. Thêm một mảng vào cuối danh sách
    public void addMany(int a[]) {
        for (int i = 0; i < a.length; i++) {
            addLast(a[i]);
        }
    }

    // 4. Thêm một mảng vào đầu danh sách (giữ nguyên thứ tự mảng)
    public void addManyFirst(int a[]) {
        for (int i = a.length - 1; i >= 0; i--) {
            addFirst(a[i]);
        }
    }

    // 5. Duyệt và in danh sách
    public void traverse() {
        Node p = head;
        while (p != null) {
            System.out.print(p.info + " ");
            p = p.next;
        }
        System.out.println();
    }

    // Phương thức xóa phần tử ở đầu danh sách liên kết
    public void removeFirst() {
        // Trường hợp 1: Danh sách rỗng, không có gì để xóa
        if (isEmpty()) {
            System.out.println("Danh sách rỗng, không thể xóa!");
            return; // Kết thúc phương thức
        }

        // Trường hợp 2: Danh sách chỉ có duy nhất 1 phần tử
        if (head == tail) {
            head = tail = null; // Xóa xong thì danh sách trở thành rỗng
        } // Trường hợp 3: Danh sách có từ 2 phần tử trở lên
        else {
            head = head.next; // Dịch chuyển head sang nút tiếp theo để bỏ qua nút đầu cũ
        }
    }

    // Phương thức xóa phần tử ở cuối danh sách liên kết
    public void removeLast() {
        // Trường hợp 1: Danh sách rỗng, không có gì để xóa
        if (isEmpty()) {
            System.out.println("Danh sách rỗng, không thể xóa!");
            return; // Kết thúc phương thức
        }

        // Trường hợp 2: Danh sách chỉ có duy nhất 1 phần tử
        if (head == tail) {
            head = tail = null; // Xóa xong thì danh sách trở thành rỗng
        } // Trường hợp 3: Danh sách có từ 2 phần tử trở lên
        else {
            Node p = head;
            // Dùng vòng lặp để tìm nút nằm ngay trước nút cuối cùng (nút áp chót)
            // Điều kiện dừng là p.next phải chính là tail
            while (p.next != tail) {
                p = p.next; // Dịch chuyển con trỏ p tiến về phía trước
            }

            // Sau vòng lặp, p đang đứng ở nút áp chót
            p.next = null; // Ngắt kết nối từ nút áp chót đến nút cuối cũ
            tail = p;      // Cập nhật lại con trỏ tail chính là nút p (nút áp chót cũ)
        }
    }

    // Hàm trả về đối tượng Node đầu tiên của danh sách
    public Node getFirst() {
        // Nếu danh sách rỗng, không có Node nào để trả về
        if (isEmpty()) {
            return null;
        }
        // Trả về đối tượng Node mà con trỏ head đang quản lý
        return head;
    }

    // Hàm trả về đối tượng Node cuối cùng của danh sách
    public Node getLast() {
        // Nếu danh sách rỗng, không có Node nào để trả về
        if (isEmpty()) {
            return null;
        }
        // Trả về đối tượng Node mà con trỏ tail đang quản lý
        return tail;
    }

    // Hàm tìm kiếm và trả về Node đầu tiên có thuộc tính info bằng với x
    public Node search(int x) {
        // Tạo một con trỏ p bắt đầu từ đầu danh sách (head) để đi duyệt
        Node p = head;

        // Duyệt qua từng Node trong danh sách cho đến khi hết (p == null)
        while (p != null) {
            // Nếu tìm thấy Node có phần dữ liệu (info) trùng với giá trị x cần tìm
            if (p.info == x) {
                return p; // Trả về ngay Node đó và kết thúc hàm
            }
            p = p.next; // Di chuyển con trỏ p sang Node tiếp theo
        }

        // Nếu đã duyệt hết danh sách mà không tìm thấy Node nào thỏa mãn
        return null;
    }

    // Hàm đếm và trả về số lượng Node hiện có trong danh sách
    public int size() {
        int count = 0;      // Khởi tạo biến đếm bằng 0
        Node p = head;      // Bắt đầu duyệt từ đầu danh sách (head)

        // Vòng lặp chạy đến khi nào đi qua hết Node cuối cùng (p thành null)
        while (p != null) {
            count++;        // Tăng biến đếm lên 1 với mỗi Node tìm thấy
            p = p.next;     // Di chuyển sang Node kế tiếp
        }

        return count;       // Trả về tổng số lượng Node đã đếm được
    }

    // Hàm trả về Node đứng ngay sau Node p cho trước
    public Node getNext(Node p) {
        // Nếu Node truyền vào bị rỗng (null), không thể lấy phần tử kế tiếp thì return Null
        if (p == null) {
            return null;
        }
        // Trả về liên kết đến Node tiếp theo của p
        return p.next;
    }

    // Hàm trả về vị trí (index) của Node p trong danh sách (bắt đầu từ 0)
// Nếu không tìm thấy Node p trong danh sách, hàm trả về -1
    public int pos(Node p) {
        // Nếu Node truyền vào bị rỗng, trả về -1 ngay lập tức
        if (p == null) {
            return -1;
        }

        int index = 0;      // Biến lưu vị trí hiện tại, bắt đầu từ 0
        Node current = head; // Dùng con trỏ current bắt đầu duyệt từ đầu danh sách (head)

        // Duyệt qua từng Node cho đến hết danh sách
        while (current != null) {
            // So sánh địa chỉ vùng nhớ (current == p) để xem có đúng là Node cần tìm không
            if (current == p) {
                return index; // Tìm thấy, trả về vị trí index ngay lập tức
            }

            index++;             // Tăng vị trí lên 1 để chuẩn bị kiểm tra phần tử tiếp theo
            current = current.next; // Di chuyển con trỏ sang Node kế tiếp
        }

        // Nếu đã duyệt hết danh sách mà vẫn không tìm thấy Node p
        return -1;
    }

    // Hàm tìm và trả về Node đứng ngay trước Node p
    public Node getPrev(Node p) {
        // Nếu danh sách rỗng, hoặc Node p rỗng, hoặc p chính là nút đầu (không có nút trước)
        if (isEmpty() || p == null || p == head) {
            return null;
        }

        Node current = head;
        // Duyệt danh sách để tìm xem nút nào có current.next chính là p
        while (current != null) {
            if (current.next == p) {
                return current; // Tìm thấy nút đứng trước, trả về ngay
            }
            current = current.next; // Di chuyển sang Node kế tiếp
        }

        return null; // Không tìm thấy Node p trong danh sách
    }

    // Hàm trả về Node tại vị trí index i (bắt đầu từ 0)
// Trả về null nếu vị trí i không hợp lệ (âm hoặc vượt quá số phần tử)
    public Node get(int i) {
        // Kiểm tra tính hợp lệ của index i
        if (i < 0 || isEmpty()) {
            return null;
        }

        int count = 0;      // Biến theo dõi vị trí hiện tại
        Node current = head; // Bắt đầu duyệt từ đầu danh sách

        // Duyệt danh sách đến khi hết hoặc đến đúng vị trí i cần tìm
        while (current != null) {
            if (count == i) {
                return current; // Tìm thấy vị trí i, trả về Node này
            }
            count++;            // Tăng chỉ số vị trí
            current = current.next; // Di chuyển sang Node kế tiếp
        }

        return null; // Trả về null nếu index i lớn hơn hoặc bằng size của list
    }

    // Hàm chèn một giá trị x vào ngay sau một Node p cho trước
    public void insertAfter(Node p, int x) {
        // Nếu Node p bị rỗng (null), không thể chèn đằng sau nó
        if (p == null) {
            System.out.println("Node p khong ton tai, khong the chen!");
            return;
        }

        // Tạo một Node mới chứa giá trị x cần chèn
        Node q = new Node(x);

        // Bước 1: Cho Node mới trỏ tới phần tử đứng sau p hiện tại
        q.next = p.next;

        // Bước 2: Cập nhật lại Node p trỏ tới Node mới q
        p.next = q;

        // Trường hợp đặc biệt: Nếu p ban đầu là nút cuối cùng (tail), 
        // thì bây giờ Node mới q sẽ trở thành nút cuối cùng mới.
        if (p == tail) {
            tail = q;
        }
    }

    // Hàm chèn một giá trị x vào ngay trước Node p cho trước
    public void insertBefore(Node p, int x) {
        // Trường hợp 1: Nếu Node p bị rỗng hoặc danh sách rỗng, không thể chèn
        if (p == null || isEmpty()) {
            System.out.println("Node p khong ton tai hoac danh sach rong, khong the chen!");
            return;
        }

        // Trường hợp 2: Nếu p chính là nút đầu tiên (head)
        // Việc chèn vào trước head bản chất chính là hàm addFirst
        if (p == head) {
            addFirst(x);
            return;
        }

        // Trường hợp 3: p nằm ở giữa hoặc cuối danh sách
        // Đi tìm Node đứng ngay trước Node p
        Node pre = getPrev(p); // Tái sử dụng hàm getPrev đã viết trước đó

        // Nếu tìm thấy Node đứng trước
        if (pre != null) {
            Node q = new Node(x); // Tạo Node mới chứa giá trị x
            q.next = p;           // Bước 1: Cho Node mới trỏ đến p
            pre.next = q;         // Bước 2: Cho Node đứng trước trỏ đến Node mới
        } else {
            System.out.println("Node p khong thuoc danh sach nay!");
        }
    }

}
