/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 * Lớp triển khai cấu trúc dữ liệu danh sách liên kết đơn
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

    // 1. Thêm vào cuối danh sách
    public void addLast(Student x) {
        Node q = new Node(x);
        if (isEmpty()) {
            head = tail = q;
        } else {
            tail.next = q;
            tail = q;
        }
    }

    // 2. Thêm vào đầu danh sách
    public void addFirst(Student x) {
        Node q = new Node(x);
        if (isEmpty()) {
            head = tail = q;
        } else {
            q.next = head;
            head = q;
        }
    }

    // 3. Thêm nhiều phần tử vào cuối danh sách
    public void addMany(Student a[]) {
        for (Student x : a) {
            addLast(x);
        }
    }

    // 4. Thêm nhiều phần tử vào đầu danh sách (giữ nguyên thứ tự mảng)
    public void addManyFirst(Student a[]) {
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

    // 6. Xóa phần tử ở đầu danh sách
    public void removeFirst() {
        if (isEmpty()) {
            return;
        }
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
        }
    }

    // 7. Xóa phần tử ở cuối danh sách
    public void removeLast() {
        if (isEmpty()) {
            return;
        }
        if (head == tail) {
            head = tail = null;
        } else {
            Node pre = getPrev(tail);
            if (pre != null) {
                pre.next = null;
                tail = pre;
            }
        }
    }

    // 8. Lấy Node đầu tiên
    public Node getFirst() {
        return head;
    }

    // 9. Lấy Node cuối cùng
    public Node getLast() {
        return tail;
    }

    // 10. Tìm Node đầu tiên có giá trị bằng x
    public Node search(Student x) {
        Node p = head;
        while (p != null) {
            if (p.info == x) {
                return p;
            }
            p = p.next;
        }
        return null;
    }

    // 11. Đếm và trả về số lượng Node
    public int size() {
        int count = 0;
        Node p = head;
        while (p != null) {
            count++;
            p = p.next;
        }
        return count;
    }

    // 12. Lấy Node đứng sau Node p
    public Node getNext(Node p) {
        return (p == null) ? null : p.next;
    }

    // 13. Lấy vị trí (index) của Node p
    public int pos(Node p) {
        if (p == null) {
            return -1;
        }
        int index = 0;
        Node current = head;
        while (current != null) {
            if (current == p) {
                return index;
            }
            index++;
            current = current.next;
        }
        return -1;
    }

    // 14. Tìm Node đứng ngay trước Node p
    public Node getPrev(Node p) {
        if (isEmpty() || p == null || p == head) {
            return null;
        }
        Node current = head;
        while (current != null) {
            if (current.next == p) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // 15. Lấy Node tại vị trí index i
    public Node get(int i) {
        if (i < 0 || isEmpty()) {
            return null;
        }
        int count = 0;
        Node current = head;
        while (current != null) {
            if (count == i) {
                return current;
            }
            count++;
            current = current.next;
        }
        return null;
    }

    // 16. Chèn giá trị x vào ngay sau Node p
    public void insertAfter(Node p, Student x) {
        if (p == null) {
            return;
        }
        Node q = new Node(x);
        q.next = p.next;
        p.next = q;
        if (p == tail) {
            tail = q;
        }
    }

    // 17. Chèn giá trị x vào ngay trước Node p
    public void insertBefore(Node p, Student x) {
        if (p == null) {
            return;
        }
        if (p == head) {
            addFirst(x);
        } else {
            Node pre = getPrev(p);
            if (pre != null) {
                insertAfter(pre, x);
            }
        }
    }

    // 18. Chèn giá trị x vào vị trí index cụ thể
    public void insert(int index, Student x) {
        if (index < 0) {
            return;
        }
        if (index == 0) {
            addFirst(x);
        } else {
            Node pre = get(index - 1);
            if (pre != null) {
                insertAfter(pre, x);
            }
        }
    }

    // 19. Xóa Node p khỏi danh sách
    public void remove(Node p) {
        if (isEmpty() || p == null) {
            return;
        }
        if (p == head) {
            removeFirst();
        } else {
            Node pre = getPrev(p);
            if (pre != null) {
                removeAfter(pre);
            }
        }
    }

    // 20. Xóa phần tử tại vị trí index
    public void removeIndex(int index) {
        if (isEmpty() || index < 0) {
            return;
        }
        if (index == 0) {
            removeFirst();
        } else {
            Node pre = get(index - 1);
            if (pre != null) {
                removeAfter(pre);
            }
        }
    }

    // 21. Xóa phần tử đứng sau Node p
    public void removeAfter(Node p) {
        if (p == null || p == tail || p.next == null) {
            return;
        }
        Node q = p.next;
        p.next = q.next;
        if (q == tail) {
            tail = p;
        }
    }

    // 22. Xóa phần tử đứng trước Node p
    public void removeBefore(Node p) {
        if (isEmpty() || p == null || p == head) {
            return;
        }
        if (head.next == p) {
            removeFirst();
            return;
        }
        Node pre = getPrev(p);
        if (pre != null) {
            Node prePre = getPrev(pre);
            if (prePre != null) {
                removeAfter(prePre);
            }
        }
    }

    // 23. Cập nhật giá trị của Node p thành x
    public void set(Node p, Student x) {
        if (p != null) {
            p.info = x;
        }
    }

    // 24. Tìm Node có giá trị lớn nhất
    public Node findMaxByMark() {
        if (isEmpty()) {
            return null;
        }
        Node max = head;
        Node cur = head.next;
        while (cur != null) {
            if (cur.info.mark > max.info.mark) {
                max = cur;
            }
            cur = cur.next;
        }
        return max;
    }

    // 25. Tìm Node có giá trị nhỏ nhất
    public Node findMinByMark() {
        if (isEmpty()) {
            return null;
        }
        Node min = head;
        Node cur = head.next;
        while (cur != null) {
            if (cur.info.mark < min.info.mark) {
                min = cur;
            }
            cur = cur.next;
        }
        return min;
    }

    // 26. Hoán đổi giá trị của hai Node p và q (swap info)
    public void swap(Node p, Node q) {
        if (p == null || q == null) {
            return;
        }
        Student temp = p.info;
        p.info = q.info;
        q.info = temp;
    }/*

    // 27. Sắp xếp danh sách từ node st đến node end (inclusive) bằng thuật toán selection sort
    public void sort(Node st, Node end) {
        if (st == null || end == null) return;
        Node i = st;
        while (i != null && i != end) {
            Node min = i;
            Node j = i.next;
            while (j != null && j != end.next) {
                if (j.info < min.info) {
                    min = j;
                }
                j = j.next;
            }
            if (min != i) {
                swap(i, min);
            }
            i = i.next;
        }
    }
     */

    public void sortByMark() {
        if (isEmpty()) {
            return;
        }
        for (Node i = head; i != null; i = i.next) {
            Node min = i;
            for (Node j = i.next; j != null; j = j.next) {
                if (j.info.sid.compareTo(min.info.sid) < 0) {
                    min = j;
                }
            }
        }
    }

    public void sortBySid() {
        if (isEmpty()) {
            return;
        }
        for (Node i = head; i != null; i = i.next) {
            Node min = i;
            for (Node j = i.next; j != null; j = j.next) {
                if (j.info.sid.compareTo(min.info.sid) < 0) {
                    min = j;
                }
            }
            if (min != i) {
                swap(i, min);
            }
        }
    }
}
