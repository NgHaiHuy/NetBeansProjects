/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LECOO
 */
public class Main {

    public static void decToBin(int k) {
        MyStack s = new MyStack();
        System.out.print(k + " in binary system is: ");
        while (k > 0) {
            s.push(new Integer(k % 2));
            k = k / 2;
        }
        while (!s.isEmpty()) {
            System.out.print(s.pop());
        }
        System.out.println();
    }

    public static void main(String[] args) {
        decToBin(12);
        System.out.println();
    }
}
