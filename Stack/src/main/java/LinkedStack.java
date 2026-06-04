
import java.util.EmptyStackException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LECOO
 */
public class LinkedStack {

    protected Node head;

    public LinkedStack() {
        head = null;
    }

    public boolean isEmpty() {
        return (head == null);
    }

    public void push(Object x) {
        head = new Node(x, head);
    }

    Object top() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return (head.info);
    }

    public Object pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        Object x = head.info;
        head = head.next;
        return (x);
    }
}
