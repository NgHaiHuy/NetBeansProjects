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
        MyList a = new MyList();

        // Kiểm tra danh sách rỗng
        System.out.println("Is empty: " + a.isEmpty());

        // Thêm vào cuối
        a.addLast(9);
        a.traverse();

        a.addLast(5);
        a.traverse();

        // Thêm nhiều phần tử vào cuối
        int b[] = {3, 6, 1, 8};
        a.addMany(b);
        a.traverse();

        // Thêm vào đầu
        a.addFirst(7);
        a.traverse();

        // Thêm nhiều phần tử vào đầu
        int c[] = {2, 6};
        a.addManyFirst(c);
        a.traverse();

        System.out.println("--- Thu nghiem ham xoa ---");
        // Giả sử danh sách đang có dữ liệu, tiến hành xóa đầu
        System.out.print("Truoc khi xoa dau: ");
        a.traverse();
        a.removeFirst();
        System.out.print("Sau khi xoa dau: ");
        a.traverse();

        // Tiến hành xóa cuối
        a.removeLast();
        System.out.print("Sau khi xoa cuoi: ");
        a.traverse();

        int x = 5;
        Node n = a.search(x);

        if (n != null) {
            // Sử dụng dấu + để nối chuỗi thay vì dấu hai chấm :
            System.out.println("Tim thay x: " + n.info);
        } else {
            System.out.println("Khong tim thay " + x + " trong danh sach.");
        }
        // In ra độ dài mảng
        System.out.println("Do dai cua mang la: " + a.size());

        System.out.println("--- Thử nghiệm hàm pos ---");

        int y = 5;
        // 1. Tìm Node có giá trị bằng 5
        n = a.search(y);

        if (n != null) {
            // 2. Tìm vị trí của Node n vừa tìm được
            int viTri = a.pos(n);
            System.out.println("Node co gia tri " + y + " nam o vi tri index: " + viTri);
        } else {
            System.out.println("Khong tim thay gia tri " + y + " trong danh sach.");
        }

    }
}
