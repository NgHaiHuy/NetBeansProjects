/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LECOO
 */
public class Main {
    public static void main(String[] args) {
        // Khởi tạo đối tượng cây
        MyBST bstree = new MyBST();
        
        // Chèn các phần tử đầu tiên
        bstree.insert(10);
        bstree.insert(6);
        bstree.insert(15);
        
        // Khai báo mảng và chèn nhiều phần tử
        int[] a = {4, 8, 12, 19, 2, 5, 7, 9, 11, 14, 17, 20, 1, 3, 13, 16, 18, 21, 0, 22};
        bstree.insertMany(a);

        // Duyệt cây theo chiều rộng
        System.out.println("Breadth first BSTree:");
        bstree.breadth();
        System.out.println();
    }
}

