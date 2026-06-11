package dsa;

// Lop LinkedList tong quat chua cac thuoc tinh va phuong thuc chung
public class MyLinkedList<T> {
    protected Node<T> head; // Node dau danh sach
    protected Node<T> tail; // Node cuoi danh sach

    // Khoi tao danh sach rong
    public MyLinkedList() {
        head = tail = null;
    }

    // Kiem tra danh sach rong
    public boolean isEmpty() {
        return head == null;
    }

    // Xoa toan bo danh sach
    public void clear() {
        head = tail = null;
    }

    // Them phan tu vao cuoi danh sach
    public void addLast(T info) {
        Node<T> newNode = new Node<>(info);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    // Them phan tu vao dau danh sach
    public void addFirst(T info) {
        Node<T> newNode = new Node<>(info);
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    // Dem so luong phan tu
    public int size() {
        int count = 0;
        Node<T> current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    // Them phan tu truoc vi tri k (k tinh tu 0)
    public boolean addBefore(T info, int k) {
        int n = size();
        if (k < 0 || k > n) return false;     // Vi tri khong hop le
        if (k == 0) { addFirst(info); return true; }  // Them vao dau
        if (k == n) { addLast(info); return true; }   // Them vao cuoi

        // Di chuyen den vi tri k-1
        Node<T> newNode = new Node<>(info);
        Node<T> current = head;
        for (int i = 0; i < k - 1; i++) {
            current = current.next;
        }
        // Chen node moi vao giua
        newNode.next = current.next;
        current.next = newNode;
        return true;
    }

    // Xoa phan tu tai vi tri k (k tinh tu 0)
    public boolean deleteAt(int k) {
        int n = size();
        if (k < 0 || k >= n) return false;

        // Xoa node dau
        if (k == 0) {
            if (head == tail) {
                head = tail = null;
            } else {
                head = head.next;
            }
            return true;
        }

        // Di chuyen den vi tri k-1
        Node<T> current = head;
        for (int i = 0; i < k - 1; i++) {
            current = current.next;
        }
        // Xoa node tai vi tri k
        if (current.next == tail) {
            tail = current;
            tail.next = null;
        } else {
            current.next = current.next.next;
        }
        return true;
    }

    // Lay node dau danh sach
    public Node<T> getHead() {
        return head;
    }

    // Lay node cuoi danh sach
    public Node<T> getTail() {
        return tail;
    }
}
