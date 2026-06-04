/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LECOO
 */
public class MyBST {

    Node root;

    public MyBST() {
        this.root = root;
    }

    public void clear() {
        this.root = null;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public void insert(int x) {
        if (isEmpty()) {
            System.out.println("Insert root: " + x);
            Node newNode = new Node(x);
            root = newNode;
            return;
        }

        // insert when BSTree is not empty
        Node curr;
        Node parentOfCurr;
        curr = root;

        parentOfCurr = null;
        while (curr != null) {
            // check if node x exist
            if (curr.info == x) {
                System.out.println("The key " + x + "already exists, no insertion");
                return;
            }
            // find position to insert: foundNode == null; parentOfCurr is parent
            parentOfCurr = curr;
            if (x < curr.info) {
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        // insert x to be child of parentOfCurr node
        Node newNode = new Node(x);
        if (x < parentOfCurr.info) {
            // System.out.println("Insert left of " + parentOfCurr.info + ".left: "+ x);
            parentOfCurr.left = newNode;
        } else {
            // System.out.println("Insert right of " + parentOfCurr.info + ".right: " + x) ;
            parentOfCurr.right = newNode;

        }
    }

    public void insertMany(int[] a) {
        for (int i = 0; i < a.length; i++) {
            insert(a[i]);
        }
    }

    public void visit(Node p) {
        System.out.print(p.info + " ");
    }

    public void breadth() {
        // check if BSTree is empty
        if (isEmpty()) {
            return;
        }

        // implement breadth-first traversal using queue
        MyQueue q = new MyQueue();

        q.enqueue(root);
        Node visitNode = null;

        while (!q.isEmpty()) {
            visitNode = q.dequeue();
            visit(visitNode);

            if (visitNode.left != null) {
                q.enqueue(visitNode.left);
            }
            if (visitNode.right != null) {
                q.enqueue(visitNode.right);
            }
        }
    }
}
