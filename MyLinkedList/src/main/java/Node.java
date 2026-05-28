
public class Node {

    Student info;
    Node next;

    public Node(Student x, Node p) {
        info = x;
        next = p;
    }

    public Node(Student x) {
        this(x, null);
    }
}
