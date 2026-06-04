
import java.util.LinkedList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LECOO
 */
public class MyStack {

    LinkedList h;

    MyStack() {
        h = new LinkedList();
    }

    boolean isEmpty() {
        return (h.isEmpty());
    }

    void push(Object x) {
        h.add(x);
    }

    Object pop() {
        if (isEmpty()) {
            return (null);
        }
        return (h.removeLast());
    }
}
