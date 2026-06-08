package service;

import entity.Booking;
import entity.Room;
import entity.Student;
import dsa.*;
import util.Validation;
import java.io.IOException;
import java.util.Date;

// Lop xu ly nghiep vu chinh: quan ly phong, sinh vien, va dat phong
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

    // ==========================================
    //          CAC CHUC NANG VE PHONG
    // ==========================================

    // 1.1. Doc du lieu phong tu file rooms.txt
    public void loadRooms() {
        try {
            roomList.loadFromFile("rooms.txt");
            System.out.println("Room data loaded successfully from rooms.txt.");
        } catch (IOException e) {
            System.out.println("Error loading room data: " + e.getMessage());
        }
    }

    // 1.2. Nhap va them phong vao cuoi danh sach
    public void addRoomEnd() {
        Room r = inputRoom(true);
        if (r != null) {
            roomList.addLast(r);
            System.out.println("Room added to the end of the list.");
        }
    }

    // 1.3. Hien thi tat ca phong
    public void displayRooms() {
        roomList.display();
    }

    // 1.4. Luu danh sach phong ra file rooms.txt
    public void saveRooms() {
        try {
            roomList.saveToFile("rooms.txt");
            System.out.println("Room data saved successfully to rooms.txt.");
        } catch (IOException e) {
            System.out.println("Error saving room data: " + e.getMessage());
        }
    }

    // 1.5. Tim phong theo ma phong (rcode)
    public void searchRoomByRcode() {
        String rcode = Validation.getString("Enter Room Code to search: ", "Room Code cannot be empty.");
        Node<Room> node = roomList.searchByRcode(rcode);
        if (node != null) {
            System.out.println("Room found:");
            System.out.println(node.info.toString());
        } else {
            System.out.println("Room not found.");
        }
    }

    // 1.6. Xoa phong theo ma phong
    // Chi xoa duoc khi phong khong co ai dang o (booked == 0)
    // Xoa cac booking lien quan truoc
    public void deleteRoomByRcode() {
        String rcode = Validation.getString("Enter Room Code to delete: ", "Room Code cannot be empty.");
        Node<Room> node = roomList.searchByRcode(rcode);
        if (node == null) {
            System.out.println("Room not found.");
            return;
        }
        if (node.info.getBooked() > 0) {
            System.out.printf("Cannot delete room %s: It currently has %d booked bed(s).%n",
                    rcode, node.info.getBooked());
            return;
        }
        // Xoa booking lien quan truoc
        bookingList.deleteBookingsByRcode(rcode);
        // Xoa phong
        if (roomList.deleteByRcode(rcode)) {
            System.out.println("Room and all its booking history deleted successfully.");
        } else {
            System.out.println("Error deleting room.");
        }
    }

    // 1.7. Sap xep phong tang dan theo rcode
    public void sortRoomsByRcode() {
        roomList.sortByRcode();
        System.out.println("Rooms sorted by rcode. Current list:");
        roomList.display();
    }

    // 1.8. Nhap va them phong vao dau danh sach
    public void addRoomStart() {
        Room r = inputRoom(true);
        if (r != null) {
            roomList.addFirst(r);
            System.out.println("Room added to the beginning of the list.");
        }
    }

    // 1.9. Them phong truoc vi tri k
    public void addRoomBeforePos() {
        int k = Validation.getInt("Enter position k (0-based): ", "Position must be an integer.");
        int n = roomList.size();
        if (k < 0 || k > n) {
            System.out.printf("Invalid position. Position must be between 0 and %d.%n", n);
            return;
        }
        Room r = inputRoom(true);
        if (r != null) {
            if (roomList.addBefore(r, k)) {
                System.out.printf("Room added before position %d.%n", k);
            } else {
                System.out.println("Error adding room at position.");
            }
        }
    }

    // 1.10. Xoa phong tai vi tri k
    public void deleteRoomAt() {
        int k = Validation.getInt("Enter position k to delete (0-based): ", "Position must be an integer.");
        int n = roomList.size();
        if (k < 0 || k >= n) {
            System.out.printf("Invalid position. Position must be between 0 and %d.%n", n - 1);
            return;
        }
        // Tim phong tai vi tri k
        Node<Room> current = roomList.getHead();
        for (int i = 0; i < k; i++) {
            current = current.next;
        }
        Room room = current.info;

        // Kiem tra phong co nguoi dang o khong
        if (room.getBooked() > 0) {
            System.out.printf("Cannot delete room %s: It currently has %d booked bed(s).%n",
                    room.getRcode(), room.getBooked());
            return;
        }
        // Xoa booking lien quan truoc, roi xoa phong
        bookingList.deleteBookingsByRcode(room.getRcode());
        if (roomList.deleteAt(k)) {
            System.out.printf("Room at position %d deleted successfully.%n", k);
        } else {
            System.out.println("Error deleting room.");
        }
    }

    // 1.11. Tim phong theo ten
    public void searchRoomByName() {
        String name = Validation.getString("Enter Room Name to search: ", "Room Name cannot be empty.");
        RoomList matches = roomList.searchByName(name);
        if (!matches.isEmpty()) {
            System.out.println("Matching rooms:");
            matches.display();
        } else {
            System.out.println("No matching rooms found.");
        }
    }

    // 1.12. Tim phong theo rcode va liet ke sinh vien dang o trong phong do
    public void searchBookedRoomsByRcode() {
        String rcode = Validation.getString("Enter Room Code: ", "Room Code cannot be empty.");
        Node<Room> roomNode = roomList.searchByRcode(rcode);
        if (roomNode == null) {
            System.out.println("Room not found.");
            return;
        }
        System.out.println("Room Details:");
        System.out.println(roomNode.info.toString());

        // Duyet booking de tim sinh vien dang o phong nay (state == 1)
        System.out.println("Students currently living in this room:");
        boolean found = false;
        Node<Booking> currentB = bookingList.getHead();
        while (currentB != null) {
            Booking b = currentB.info;
            if (b.getRcode().equalsIgnoreCase(rcode.trim()) && b.getState() == 1) {
                Node<Student> studNode = studentList.searchByScode(b.getScode());
                if (studNode != null) {
                    System.out.println(studNode.info.toString());
                    found = true;
                }
            }
            currentB = currentB.next;
        }
        if (!found) {
            System.out.println("No students are currently living in this room.");
        }
    }

    // ==========================================
    //       CAC CHUC NANG VE SINH VIEN
    // ==========================================

    // 2.1. Doc du lieu sinh vien tu file students.txt
    public void loadStudents() {
        try {
            studentList.loadFromFile("students.txt");
            System.out.println("Student data loaded successfully from students.txt.");
        } catch (IOException e) {
            System.out.println("Error loading student data: " + e.getMessage());
        }
    }

    // 2.2. Nhap va them sinh vien vao cuoi danh sach
    public void addStudentEnd() {
        Student s = inputStudent(true);
        if (s != null) {
            studentList.addLast(s);
            System.out.println("Student added to the end of the list.");
        }
    }

    // 2.3. Hien thi tat ca sinh vien
    public void displayStudents() {
        studentList.display();
    }

    // 2.4. Luu danh sach sinh vien ra file students.txt
    public void saveStudents() {
        try {
            studentList.saveToFile("students.txt");
            System.out.println("Student data saved successfully to students.txt.");
        } catch (IOException e) {
            System.out.println("Error saving student data: " + e.getMessage());
        }
    }

    // 2.5. Tim sinh vien theo ma sinh vien (scode)
    public void searchStudentByScode() {
        String scode = Validation.getString("Enter Student Code to search: ", "Student Code cannot be empty.");
        Node<Student> node = studentList.searchByScode(scode);
        if (node != null) {
            System.out.println("Student found:");
            System.out.println(node.info.toString());
        } else {
            System.out.println("Student not found.");
        }
    }

    // 2.6. Xoa sinh vien theo scode
    // Chi xoa duoc khi sinh vien da tra het tat ca phong (khong co booking state=1)
    // Xoa cac booking lien quan truoc
    public void deleteStudentByScode() {
        String scode = Validation.getString("Enter Student Code to delete: ", "Student Code cannot be empty.");
        Node<Student> node = studentList.searchByScode(scode);
        if (node == null) {
            System.out.println("Student not found.");
            return;
        }
        // Kiem tra sinh vien con dang o phong nao khong
        if (bookingList.hasActiveBooking(scode)) {
            System.out.println("Cannot delete student: The student is currently living in a room.");
            return;
        }
        // Xoa booking lien quan truoc
        bookingList.deleteBookingsByScode(scode);
        if (studentList.deleteByScode(scode)) {
            System.out.println("Student and all their booking history deleted successfully.");
        } else {
            System.out.println("Error deleting student.");
        }
    }

    // 2.7. Tim sinh vien theo ten
    public void searchStudentByName() {
        String name = Validation.getString("Enter Student Name to search: ", "Student Name cannot be empty.");
        StudentList matches = studentList.searchByName(name);
        if (!matches.isEmpty()) {
            System.out.println("Matching students:");
            matches.display();
        } else {
            System.out.println("No matching students found.");
        }
    }

    // 2.8. Tim sinh vien theo scode va liet ke tat ca phong da dat
    public void searchBookingsByScode() {
        String scode = Validation.getString("Enter Student Code: ", "Student Code cannot be empty.");
        Node<Student> studNode = studentList.searchByScode(scode);
        if (studNode == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.println("Student Details:");
        System.out.println(studNode.info.toString());

        // Duyet booking de tim phong da dat boi sinh vien nay
        System.out.println("Rooms booked by this student:");
        boolean found = false;
        Node<Booking> currentB = bookingList.getHead();
        while (currentB != null) {
            Booking b = currentB.info;
            if (b.getScode().equalsIgnoreCase(scode.trim())) {
                Node<Room> roomNode = roomList.searchByRcode(b.getRcode());
                if (roomNode != null) {
                    String status = b.getState() == 1 ? "STILL LIVING" : "LEFT";
                    System.out.printf("%s (Status: %s)%n", roomNode.info.toString(), status);
                    found = true;
                }
            }
            currentB = currentB.next;
        }
        if (!found) {
            System.out.println("No rooms have been booked by this student.");
        }
    }

    // ==========================================
    //       CAC CHUC NANG VE DAT PHONG
    // ==========================================

    // 3.1. Doc du lieu booking tu file bookings.txt
    public void loadBookings() {
        try {
            bookingList.loadFromFile("bookings.txt");
            System.out.println("Booking data loaded successfully from bookings.txt.");
        } catch (IOException e) {
            System.out.println("Error loading booking data: " + e.getMessage());
        }
    }

    // 3.2. Dat phong cho sinh vien
    // Kiem tra: phong ton tai, sinh vien ton tai, phong con giuong, sinh vien chua o phong nao
    public void bookRoom() {
        String rcode = Validation.getString("Enter Room Code: ", "Room Code cannot be empty.");
        Node<Room> roomNode = roomList.searchByRcode(rcode);
        if (roomNode == null) {
            System.out.println("Error: Room does not exist.");
            return;
        }
        String scode = Validation.getString("Enter Student Code: ", "Student Code cannot be empty.");
        Node<Student> studNode = studentList.searchByScode(scode);
        if (studNode == null) {
            System.out.println("Error: Student does not exist.");
            return;
        }
        // Kiem tra phong con giuong trong khong
        if (roomNode.info.getBeds() <= 0) {
            System.out.println("Error: Room is full (no available beds).");
            return;
        }
        // Kiem tra sinh vien da dang o phong nao chua
        if (bookingList.hasActiveBooking(scode)) {
            System.out.println("Error: Student is currently living in another room.");
            return;
        }

        // Tao booking moi: ngay dat = hom nay, ngay tra = null, trang thai = 1
        Date today = new Date();
        Booking booking = new Booking(rcode, scode, today, null, 1);
        bookingList.addFirst(booking); // Them vao dau danh sach

        // Cap nhat so giuong cua phong: giam giuong trong, tang giuong da dat
        Room r = roomNode.info;
        r.setBeds(r.getBeds() - 1);
        r.setBooked(r.getBooked() + 1);

        System.out.println("Room booked successfully!");
        System.out.println("Booking details: " + booking);
    }

    // 3.3. Hien thi tat ca booking
    public void displayBookings() {
        bookingList.display();
    }

    // 3.4. Luu danh sach booking ra file bookings.txt
    public void saveBookings() {
        try {
            bookingList.saveToFile("bookings.txt");
            System.out.println("Booking data saved successfully to bookings.txt.");
        } catch (IOException e) {
            System.out.println("Error saving booking data: " + e.getMessage());
        }
    }

    // 3.5. Sap xep booking giam dan theo rcode + scode
    public void sortBookings() {
        bookingList.sortByRcodeAndScodeDesc();
        System.out.println("Bookings sorted by rcode + scode descending. Current list:");
        bookingList.display();
    }

    // 3.6. Tra phong: tim booking theo rcode + scode
    // Neu tim thay va sinh vien dang o (state=1) -> dat state=0, ldate = hom nay
    public void leaveRoom() {
        String rcode = Validation.getString("Enter Room Code: ", "Room Code cannot be empty.");
        String scode = Validation.getString("Enter Student Code: ", "Student Code cannot be empty.");

        Booking activeBooking = bookingList.searchActiveBooking(rcode, scode);
        if (activeBooking == null) {
            System.out.println("Error: No active booking found for this student and room.");
            return;
        }

        // Cap nhat booking: da tra phong
        activeBooking.setState(0);
        activeBooking.setLdate(new Date());

        // Cap nhat so giuong cua phong: tang giuong trong, giam giuong da dat
        Node<Room> roomNode = roomList.searchByRcode(rcode);
        if (roomNode != null) {
            Room r = roomNode.info;
            r.setBeds(r.getBeds() + 1);
            r.setBooked(r.getBooked() - 1);
        }

        System.out.println("Student has successfully left the room.");
        System.out.println("Updated booking: " + activeBooking);
    }

    // ==========================================
    //       CAC HAM HO TRO NHAP DU LIEU
    // ==========================================

    // Nhap thong tin phong tu ban phim
    // checkUnique = true: kiem tra ma phong khong trung
    private Room inputRoom(boolean checkUnique) {
        String rcode;
        while (true) {
            rcode = Validation.getString("Enter Room Code: ", "Room Code cannot be empty.");
            if (!checkUnique) break;
            if (roomList.searchByRcode(rcode) == null) break; // Ma chua ton tai -> hop le
            System.out.println("Error: Room Code must be unique. This code already exists.");
        }
        String name = Validation.getString("Enter Room Name: ", "Room Name cannot be empty.");
        String dom = Validation.getString("Enter Dormitory: ", "Dormitory cannot be empty.");
        String floor = Validation.getString("Enter Floor: ", "Floor cannot be empty.");
        String type = Validation.getRoomType("Enter Room Type (double/triple): ",
                "Room Type must be 'double' or 'triple'.");
        int beds = Validation.getBedsForType(type,
                "Enter Beds (4 for double, 6 for triple): ", "Beds must be an integer.");
        int booked = Validation.getBookedBeds(beds,
                "Enter Booked Beds: ", "Booked beds must be an integer.");
        double price = Validation.getDouble("Enter Price per Bed: ", "Price must be a number.");
        while (price < 0) {
            System.out.println("Error: Price cannot be negative.");
            price = Validation.getDouble("Enter Price per Bed: ", "Price must be a number.");
        }

        // Tao phong moi: beds luu so giuong con trong = tong - da dat
        return new Room(rcode, name, dom, floor, type, beds - booked, booked, price);
    }

    // Nhap thong tin sinh vien tu ban phim
    // checkUnique = true: kiem tra ma sinh vien khong trung
    private Student inputStudent(boolean checkUnique) {
        String scode;
        while (true) {
            scode = Validation.getString("Enter Student Code: ", "Student Code cannot be empty.");
            if (!checkUnique) break;
            if (studentList.searchByScode(scode) == null) break;
            System.out.println("Error: Student Code must be unique. This code already exists.");
        }
        String name = Validation.getString("Enter Student Name: ", "Student Name cannot be empty.");
        int byear = Validation.getBirthYear("Enter Birth Year: ", "Birth Year must be an integer.");

        return new Student(scode, name, byear);
    }
}
