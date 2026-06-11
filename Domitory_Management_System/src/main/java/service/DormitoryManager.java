package service;

import entity.Booking;
import entity.BookingState;
import entity.Room;
import entity.Student;
import dsa.*;
import java.io.IOException;

// Lop xu ly nghiep vu chinh: quan ly phong, sinh vien, va dat phong (Business Logic Layer)
public class DormitoryManager {
    private final RoomList roomList;       // Danh sach phong
    private final StudentList studentList; // Danh sach sinh vien
    private final BookingList bookingList; // Danh sach dat phong

    // Khoi tao 3 danh sach rong
    public DormitoryManager() {
        this.roomList = new RoomList();
        this.studentList = new StudentList();
        this.bookingList = new BookingList();
    }

    public RoomList getRoomList() {
        return roomList;
    }

    public StudentList getStudentList() {
        return studentList;
    }

    public BookingList getBookingList() {
        return bookingList;
    }

    // ==========================================
    //          CAC CHUC NANG VE PHONG
    // ==========================================

    // 1.1. Doc du lieu phong tu file rooms.txt
    public int loadRooms() throws IOException {
        return roomList.loadFromFile("rooms.txt");
    }

    // 1.2. Them phong vao cuoi danh sach
    public boolean addRoomEnd(Room r) {
        if (roomList.searchByRcode(r.getRcode()) != null) {
            return false; // Code phai duy nhat
        }
        roomList.addLast(r);
        return true;
    }

    // 1.4. Luu danh sach phong ra file rooms.txt
    public void saveRooms() throws IOException {
        roomList.saveToFile("rooms.txt");
    }

    // 1.5. Tim phong theo ma phong (rcode)
    public Room searchRoomByRcode(String rcode) {
        Node<Room> node = roomList.searchByRcode(rcode);
        return node != null ? node.info : null;
    }

    // 1.6. Xoa phong theo ma phong
    // 0 = thanh cong, 1 = khong tim thay, 2 = dang co nguoi o
    public int deleteRoomByRcode(String rcode) {
        Node<Room> node = roomList.searchByRcode(rcode);
        if (node == null) {
            return 1;
        }
        if (node.info.getBooked() > 0) {
            return 2;
        }
        // Xoa booking lien quan truoc
        bookingList.deleteBookingsByRcode(rcode);
        // Xoa phong
        roomList.deleteByRcode(rcode);
        return 0;
    }

    // 1.7. Sap xep phong tang dan theo rcode
    public void sortRoomsByRcode() {
        roomList.sortByRcode();
    }

    // 1.8. Them phong vao dau danh sach
    public boolean addRoomStart(Room r) {
        if (roomList.searchByRcode(r.getRcode()) != null) {
            return false;
        }
        roomList.addFirst(r);
        return true;
    }

    // 1.9. Them phong truoc vi tri k
    public boolean addRoomBeforePos(Room r, int k) {
        if (roomList.searchByRcode(r.getRcode()) != null) {
            return false;
        }
        return roomList.addBefore(r, k);
    }

    // 1.10. Xoa phong tai vi tri k
    // 0 = thanh cong, 1 = vi tri khong hop le, 2 = dang co nguoi o
    public int deleteRoomAt(int k) {
        int n = roomList.size();
        if (k < 0 || k >= n) {
            return 1;
        }
        // Tim phong tai vi tri k
        Node<Room> current = roomList.getHead();
        for (int i = 0; i < k; i++) {
            current = current.next;
        }
        Room room = current.info;

        if (room.getBooked() > 0) {
            return 2;
        }
        // Xoa booking lien quan truoc, roi xoa phong
        bookingList.deleteBookingsByRcode(room.getRcode());
        roomList.deleteAt(k);
        return 0;
    }

    // 1.11. Tim phong theo ten
    public RoomList searchRoomByName(String name) {
        return roomList.searchByName(name);
    }

    // 1.12. Liet ke sinh vien dang o trong phong do
    public StudentList getOccupants(String rcode) {
        StudentList occupants = new StudentList();
        Node<Booking> currentB = bookingList.getHead();
        while (currentB != null) {
            Booking b = currentB.info;
            if (b.getRcode().equalsIgnoreCase(rcode.trim()) && b.getState() == BookingState.ACTIVE) {
                Node<Student> studNode = studentList.searchByScode(b.getScode());
                if (studNode != null) {
                    occupants.addLast(studNode.info);
                }
            }
            currentB = currentB.next;
        }
        return occupants;
    }

    // ==========================================
    //       CAC CHUC NANG VE SINH VIEN
    // ==========================================

    // 2.1. Doc du lieu sinh vien tu file students.txt
    public int loadStudents() throws IOException {
        return studentList.loadFromFile("students.txt");
    }

    // 2.2. Them sinh vien
    public boolean addStudentEnd(Student s) {
        if (studentList.searchByScode(s.getScode()) != null) {
            return false;
        }
        studentList.addLast(s);
        return true;
    }

    // 2.4. Luu danh sach sinh vien
    public void saveStudents() throws IOException {
        studentList.saveToFile("students.txt");
    }

    // 2.5. Tim sinh vien theo ma sinh vien
    public Student searchStudentByScode(String scode) {
        Node<Student> node = studentList.searchByScode(scode);
        return node != null ? node.info : null;
    }

    // 2.6. Xoa sinh vien theo scode
    // 0 = thanh cong, 1 = khong tim thay, 2 = dang o ky tuc xa
    public int deleteStudentByScode(String scode) {
        Node<Student> node = studentList.searchByScode(scode);
        if (node == null) {
            return 1;
        }
        if (bookingList.hasActiveBooking(scode)) {
            return 2;
        }
        // Xoa booking lien quan truoc
        bookingList.deleteBookingsByScode(scode);
        studentList.deleteByScode(scode);
        return 0;
    }

    // 2.7. Tim sinh vien theo ten
    public StudentList searchStudentByName(String name) {
        return studentList.searchByName(name);
    }

    // 2.8. Liet ke cac booking cua sinh vien nay
    public BookingList getBookingsByScode(String scode) {
        BookingList studentBookings = new BookingList();
        Node<Booking> currentB = bookingList.getHead();
        while (currentB != null) {
            Booking b = currentB.info;
            if (b.getScode().equalsIgnoreCase(scode.trim())) {
                studentBookings.addLast(b);
            }
            currentB = currentB.next;
        }
        return studentBookings;
    }

    // ==========================================
    //       CAC CHUC NANG VE DAT PHONG
    // ==========================================

    // 3.1. Doc du lieu booking tu file bookings.txt
    public int loadBookings() throws IOException {
        return bookingList.loadFromFile("bookings.txt");
    }

    // 3.2. Dat phong cho sinh vien
    // 0 = thanh cong, 1 = phong ko ton tai, 2 = sv ko ton tai, 3 = het giuong, 4 = sv dang o phong khac
    public int bookRoom(String rcode, String scode) {
        Node<Room> roomNode = roomList.searchByRcode(rcode);
        if (roomNode == null) {
            return 1;
        }
        Node<Student> studNode = studentList.searchByScode(scode);
        if (studNode == null) {
            return 2;
        }
        // Kiem tra phong con giuong trong khong
        if (roomNode.info.getBeds() <= 0) {
            return 3;
        }
        // Kiem tra sinh vien da dang o phong nao chua
        if (bookingList.hasActiveBooking(scode)) {
            return 4;
        }

        // Tao booking moi: ngay dat = hom nay, ngay tra = null, trang thai = ACTIVE
        String todayStr = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Booking booking = new Booking(rcode, scode, todayStr, "null", BookingState.ACTIVE);
        bookingList.addFirst(booking);

        // Cap nhat so giuong cua phong: giam giuong trong, tang giuong da dat
        Room r = roomNode.info;
        r.setBeds(r.getBeds() - 1);
        r.setBooked(r.getBooked() + 1);
        return 0;
    }

    // 3.4. Luu danh sach booking ra file bookings.txt
    public void saveBookings() throws IOException {
        bookingList.saveToFile("bookings.txt");
    }

    // 3.5. Sap xep booking giam dan theo rcode + scode
    public void sortBookings() {
        bookingList.sortByRcodeAndScodeDesc();
    }

    // 3.6. Tra phong: tim booking theo rcode + scode
    // 0 = thanh cong, 1 = ko tim thay booking hoat dong
    public int leaveRoom(String rcode, String scode) {
        Booking activeBooking = bookingList.searchActiveBooking(rcode, scode);
        if (activeBooking == null) {
            return 1;
        }

        // Cap nhat booking: da tra phong
        activeBooking.setState(BookingState.LEFT);
        String todayStr = java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        activeBooking.setLdate(todayStr);

        // Cap nhat so giuong cua phong: tang giuong trong, giam giuong da dat
        Node<Room> roomNode = roomList.searchByRcode(rcode);
        if (roomNode != null) {
            Room r = roomNode.info;
            r.setBeds(r.getBeds() + 1);
            r.setBooked(r.getBooked() - 1);
        }
        return 0;
    }
}
