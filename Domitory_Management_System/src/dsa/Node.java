package dsa;

// Lop Node tong quat dung cho danh sach lien ket
// T la kieu du lieu bat ky (Room, Student, Booking)
public class Node<T> {
    public T info;        // Du lieu cua node
    public Node<T> next;  // Con tro toi node tiep theo

    // Constructor khong tham so
    public Node() {
    }

    // Constructor voi du lieu, next = null (node cuoi)
    public Node(T info) {
        this.info = info;
        this.next = null;
    }

    // Constructor voi du lieu va con tro next
    public Node(T info, Node<T> next) {
        this.info = info;
        this.next = next;
    }
}
