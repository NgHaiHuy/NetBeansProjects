/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LECOO
 */
import java.util.LinkedList;

public class MyQueue { // sử dụng lại LinkedList
    LinkedList<Node> queue;

    public MyQueue() {
        this.queue = new LinkedList<Node>();
    }
    
    public void clear() {
        this.queue.clear();
    }
    
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }
    
    public void enqueue(Node x) {
        this.queue.addLast(x);
    }
    
    public Node dequeue() {
        if (isEmpty()) return null;
        Node p = this.queue.removeFirst();
        return p;
    }
    
    public Node front() {
        if (isEmpty()) return null;
        return this.queue.getFirst();
    }
}
