/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LECOO
 */
public class Node {
    int info;
    Node left;
    Node right;

    public Node() {
    }

    public Node(int x, Node left, Node right) {
        this.info = x;
        this.left = left;
        this.right = right;
    }

    public Node(int x) {
        this(x, null, null);
    }
}
