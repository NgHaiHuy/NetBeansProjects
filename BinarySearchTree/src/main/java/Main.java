/**
 * Lớp chính (Main) chứa hàm main để khởi chạy và kiểm thử chương trình.
 * Chương trình thực hiện tạo một Cây Tìm kiếm Nhị phân (BST), chèn các phần tử 
 * và tiến hành duyệt cây theo chiều rộng (Breadth-First Traversal).
 */
public class Main {
    public static void main(String[] args) {
        // Khởi tạo đối tượng cây nhị phân tìm kiếm
        MyBST bstree = new MyBST();
        
        // Chèn các phần tử đơn lẻ đầu tiên để dựng cấu trúc gốc
        bstree.insert(10); // 10 sẽ là nút gốc (root)
        bstree.insert(6);  // 6 < 10 nên nằm bên trái nút gốc 10
        bstree.insert(15); // 15 > 10 nên nằm bên phải nút gốc 10
        
        // Khai báo mảng chứa danh sách nhiều giá trị cần chèn thêm vào cây
        int[] a = {4, 8, 12, 19, 2, 5, 7, 9, 11, 14, 17, 20, 1, 3, 13, 16, 18, 21, 0, 22};
        
        // Gọi phương thức chèn hàng loạt các phần tử trong mảng 'a' vào cây
        bstree.insertMany(a);

        // Thực hiện duyệt cây theo chiều rộng (Breadth-first BSTree)
        // Kết quả sẽ in ra giá trị các nút theo từng tầng từ trên xuống dưới, từ trái sang phải.
        System.out.println("Breadth first BSTree:");
        bstree.breadth();
        System.out.println(); // In dòng trống xuống hàng
    }
}

