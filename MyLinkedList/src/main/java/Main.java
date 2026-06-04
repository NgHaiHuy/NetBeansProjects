/*
 * Demo program for MyList with Student objects.
 */

/**
 * Lớp chạy thử nghiệm danh sách liên kết MyList
 *
 * @author LECOO
 */
public class Main {

    public static void main(String[] args) {
        /*MyList a = new MyList();

        System.out.println("=== THU NGHIEM KHOI TAO DANH SACH ===");
        System.out.println("Danh sach rong? " + a.isEmpty());

        // 1. Tạo danh sách ban đầu
        int[] b = {9, 5, 3, 6, 1, 8};
        a.addMany(b); // Thêm mảng vào cuối
        a.addFirst(7); // Thêm 7 vào đầu

        System.out.print("Danh sach ban dau: ");
        a.traverse();
        System.out.println("Do dai danh sach: " + a.size());
        System.out.println("----------------------------------------\n");

        // 2. Thử nghiệm tìm kiếm và truy cập phần tử
        System.out.println("=== THU NGHIEM TIM KIEM & TRUY CAP ===");
        Node node5 = a.search(5);
        System.out.println("- Tim kiem node co gia tri 5: " + (node5 != null ? "Tim thay (info = " + node5.info + ")" : "Khong tim thay"));
        System.out.println("- Vi tri index cua node 5: " + a.pos(node5));

        Node firstNode = a.getFirst();
        Node lastNode = a.getLast();
        System.out.println("- Node dau tien: " + (firstNode != null ? firstNode.info : "null"));
        System.out.println("- Node cuoi cung: " + (lastNode != null ? lastNode.info : "null"));

        Node prevOf5 = a.getPrev(node5);
        Node nextOf5 = a.getNext(node5);
        System.out.println("- Node dung truoc 5: " + (prevOf5 != null ? prevOf5.info : "null"));
        System.out.println("- Node dung sau 5: " + (nextOf5 != null ? nextOf5.info : "null"));

        Node nodeAtIndex2 = a.get(2);
        System.out.println("- Node tai vi tri index 2: " + (nodeAtIndex2 != null ? nodeAtIndex2.info : "null"));
        System.out.println("----------------------------------------\n");

        // 3. Thử nghiệm chèn phần tử
        System.out.println("=== THU NGHIEM CHEN PHAN TU ===");
        System.out.print("Danh sach truoc khi chen: ");
        a.traverse();

        // insertAfter: Chèn sau node 5
        System.out.println("- Chen 99 vao sau node 5:");
        a.insertAfter(node5, 99);
        System.out.print(" -> Ket qua: ");
        a.traverse();

        // insertBefore: Chèn trước node 5
        System.out.println("- Chen 88 vao truoc node 5:");
        a.insertBefore(node5, 88);
        System.out.print(" -> Ket qua: ");
        a.traverse();

        // insert tai index i
        System.out.println("- Chen 55 vao vi tri index 2:");
        a.insert(2, 55);
        System.out.print(" -> Ket qua: ");
        a.traverse();
        System.out.println("----------------------------------------\n");

        // 4. Thử nghiệm xóa phần tử
        System.out.println("=== THU NGHIEM XOA PHAN TU ===");
        System.out.print("Danh sach truoc khi xoa: ");
        a.traverse();

        // removeFirst: Xóa phần tử đầu tiên
        System.out.println("- Xoa phan tu dau tien (removeFirst):");
        a.removeFirst();
        System.out.print(" -> Ket qua: ");
        a.traverse();

        // removeLast: Xóa phần tử cuối cùng
        System.out.println("- Xoa phan tu cuoi cung (removeLast):");
        a.removeLast();
        System.out.print(" -> Ket qua: ");
        a.traverse();

        // remove(Node p): Xóa node cụ thể (xóa node 5)
        System.out.println("- Xoa truc tiep node 5 (remove node p):");
        a.remove(node5);
        System.out.print(" -> Ket qua: ");
        a.traverse();

        // removeIndex: Xóa theo index
        System.out.println("- Xoa phan tu tai index 3 (removeIndex):");
        a.removeIndex(3);
        System.out.print(" -> Ket qua: ");
        a.traverse();

        // removeAfter: Xóa phần tử sau node 88
        Node node88 = a.search(88);
        System.out.println("- Xoa phan tu dung sau node 88 (removeAfter):");
        a.removeAfter(node88);
        System.out.print(" -> Ket qua: ");
        a.traverse();

        // removeBefore: Xóa phần tử trước node 6
        Node node6 = a.search(6);
        System.out.println("- Xoa phan tu dung truoc node 6 (removeBefore):");
        a.removeBefore(node6);
        System.out.print(" -> Ket qua: ");
        a.traverse();
        System.out.println("----------------------------------------\n");

        // 5. Thử nghiệm cập nhật giá trị (set)
        System.out.println("=== THU NGHIEM CAP NHAT PHAN TU (SET) ===");
        System.out.print("Danh sach truoc khi set: ");
        a.traverse();
        Node nodeToSet = a.search(6);
        System.out.println("- Cap nhat node co gia tri 6 thanh 66:");
        a.set(nodeToSet, 66);
        System.out.print(" -> Ket qua: ");
        a.traverse();
        System.out.println("----------------------------------------\n");

        // 6. Thử nghiệm tìm giá trị lớn nhất và nhỏ nhất
        System.out.println("=== THU NGHIEM TIM MAX & MIN ===");
        Node maxNode = a.findMax();
        Node minNode = a.findMin();
        System.out.println("- Node co gia tri lon nhat: " + (maxNode != null ? maxNode.info : "null"));
        System.out.println("- Node co gia tri nho nhat: " + (minNode != null ? minNode.info : "null"));
        System.out.println("----------------------------------------");
        // 7. Thử nghiệm hoán đổi (swap) và sắp xếp (sort)
        System.out.println("=== THU NGHIEM SWAP & SORT ===");
        // swap nodes with values 7 and 1 (if they exist)
        Node node7 = a.search(7);
        Node node1 = a.search(1);
        if (node7 != null && node1 != null) {
            System.out.println("- Trước khi swap:");
            a.traverse();
            a.swap(node7, node1);
            System.out.println("- Sau khi swap (7 <-> 1):");
            a.traverse();
        }
        // sort entire list
        a.sort(a.getFirst(), a.getLast());
        System.out.println("- Sau khi sort toàn danh sách:");
        a.traverse();
        System.out.println("----------------------------------------");
    }*/

        MyList a = new MyList();
        System.out.println("Is list empty? " + a.isEmpty());
        System.out.println();

        // Add some students
        a.addLast(new Student("he180001", "An", 9.5));
        a.traverse();
        System.out.println("**********************************");

        a.addLast(new Student("he190056", "Binh", 5.5));
        Student[] b = {
            new Student("he180078", "An", 4.5),
            new Student("he18123", "Hoang", 9.5),
            new Student("he180341", "Xuan", 3.5),
            new Student("he180541", "Thuy", 6.5)
        };
        a.addMany(b);
        a.traverse();
        System.out.println("**********************************");

        a.addFirst(new Student("he180079", "Ha", 4.5));

        System.out.println("--- Initial list ---");
        a.traverse();
        System.out.println();

        System.out.println("Max by mark: " + a.findMaxByMark().info);
        System.out.println("Min by mark: " + a.findMinByMark().info);
        System.out.println();

        System.out.println("--- Sorted by mark ---");
        a.sortByMark();
        a.traverse();
        System.out.println();

        System.out.println("--- Sorted by SID ---");
        a.sortBySid();
        a.traverse();
        System.out.println();

        System.out.println("--- Reverse range 0-3 ---");
        Node st = a.get(0);
        Node end = a.get(3);
        a.reverse(st, end);
        a.traverse();
    }
}
