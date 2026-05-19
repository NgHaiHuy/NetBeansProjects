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
        System.out.println("--- Thu nghiem ham size ---");
        System.out.println("Do dai cua mang la: " + a.size());

        // --- BỔ SUNG: Thử nghiệm hàm getFirst và getLast ---
        System.out.println("--- Thu nghiem ham getFirst va getLast ---");
        Node firstNode = a.getFirst();
        Node lastNode = a.getLast();
        System.out.println("Node dau tien (First Node) la: " + (firstNode != null ? firstNode.info : "null"));
        System.out.println("Node cuoi cung (Last Node) la: " + (lastNode != null ? lastNode.info : "null"));

        System.out.println("--- Thu nghiem ham pos ---");

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

        // Giả sử d là một Node đã được tìm thấy trước đó (Ví dụ: Node d = a.search(5);)
        System.out.println("--- Thu nghiem ham getPrev theo node truoc do ---");
        Node d = a.search(5);

        if (d != null) {
            Node pre = a.getPrev(d); // ĐÃ SỬA: Bỏ chữ "p:" đi, chỉ truyền vào biến d

            if (pre != null) {
                System.out.println("Node truoc cua " + d.info + " la: " + pre.info);
            } else {
                System.out.println("Khong co node truoc"); // ĐÃ SỬA: Bỏ chữ "x:" trong println
            }
        }

        // --- BỔ SUNG: Thử nghiệm hàm getNext ---
        System.out.println("--- Thu nghiem ham getNext theo node truoc do ---");
        if (d != null) {
            Node nextNode = a.getNext(d);
            if (nextNode != null) {
                System.out.println("Node sau cua " + d.info + " la: " + nextNode.info);
            } else {
                System.out.println("Khong co node sau (day la node cuoi cung)");
            }
        }

        System.out.println("--- Thu nghiem ham get theo index ---");
        int indexToFind = 2; // Ví dụ tìm phần tử tại vị trí số 2 (phần tử thứ 3)
        Node resultNode = a.get(indexToFind);

        if (resultNode != null) {
            System.out.println("Node tai index " + indexToFind + " co gia tri la: " + resultNode.info);
        } else {
            System.out.println("Vi tri index " + indexToFind + " khong hop le hoac vuot qua do dai danh sach.");
        }

        System.out.println("--- Thu nghiem ham insertAfter ---");
        int valueToFind = 3; // Tìm Node có giá trị bằng 3 để chèn đằng sau nó
        int newValue = 9;   // Giá trị mới muốn chèn vào danh sách

        Node targetNode = a.search(valueToFind);

        if (targetNode != null) {
            System.out.print("Danh sach truoc khi chen sau " + valueToFind + ": ");
            a.traverse();

            // Gọi hàm chèn giá trị newValue ra sau targetNode
            a.insertAfter(targetNode, newValue);

            System.out.print("Danh sach sau khi chen sau " + valueToFind + " : ");
            a.traverse();
        } else {
            System.out.println("Khong tim thay Node co gia tri " + valueToFind + " de chen!");
        }

        System.out.println("--- Thu nghiem ham insertBefore ---");
        int findVal = 6;     // Tìm Node có giá trị bằng 6 để chèn đằng trước nó
        int insertVal = 88;  // Giá trị mới muốn chèn vào danh sách

        Node targetBefore = a.search(findVal);

        if (targetBefore != null) {
            System.out.print("Danh sach truoc khi chen truoc " + findVal + ": ");
            a.traverse();

            // Gọi hàm chèn giá trị insertVal ra trước targetBefore
            a.insertBefore(targetBefore, insertVal);

            System.out.print("Danh sach sau khi chen truoc " + findVal + " : ");
            a.traverse();
        } else {
            System.out.println("Khong tim thay Node co gia tri " + findVal + " de chen!");
        }

        System.out.println("--- Thu nghiem ham insert theo index ---");
        int targetIndex = 2; // Vị trí index muốn chèn vào (ví dụ vị trí số 2)
        int insertValue = 55; // Giá trị mới muốn chèn vào

        System.out.print("Danh sach truoc khi chen vao index " + targetIndex + ": ");
        a.traverse();

        // Gọi hàm chèn giá trị vào vị trí index cụ thể
        a.insert(targetIndex, insertValue);

        System.out.print("Danh sach sau khi chen vao index " + targetIndex + " : ");
        a.traverse();

    }
}
