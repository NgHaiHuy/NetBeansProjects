import service.DormitoryManager;
import entity.Room;
import entity.Student;
import entity.Booking;
import entity.BookingState;
import dsa.RoomList;
import dsa.StudentList;
import dsa.BookingList;
import dsa.Node;
import util.Validation;

// Lop chinh chua menu, giao dien nguoi dung (UI) va dieu khien chuong trinh (Controller Layer)
public class Main {
    public static void main(String[] args) {
        DormitoryManager manager = new DormitoryManager();
        System.out.println("Initializing Dormitory Management System...");

        // Vong lap hien thi menu cho den khi nguoi dung chon thoat
        while (true) {
            printMenu();
            String choice = Validation.getString("Choose an option: ", "Choice cannot be empty.");
            System.out.println();

            switch (choice) {
                // --- Quan ly phong ---
                case "1.1":  loadRooms(manager);             break;
                case "1.2":  addRoomEnd(manager);            break;
                case "1.3":  displayRooms(manager);          break;
                case "1.4":  saveRooms(manager);             break;
                case "1.5":  searchRoomByRcode(manager);     break;
                case "1.6":  deleteRoomByRcode(manager);     break;
                case "1.7":  sortRoomsByRcode(manager);      break;
                case "1.8":  addRoomStart(manager);          break;
                case "1.9":  addRoomBeforePos(manager);      break;
                case "1.10": deleteRoomAt(manager);          break;
                case "1.11": searchRoomByName(manager);      break;
                case "1.12": searchBookedRoomsByRcode(manager); break;

                // --- Quan ly sinh vien ---
                case "2.1":  loadStudents(manager);          break;
                case "2.2":  addStudentEnd(manager);         break;
                case "2.3":  displayStudents(manager);       break;
                case "2.4":  saveStudents(manager);          break;
                case "2.5":  searchStudentByScode(manager);  break;
                case "2.6":  deleteStudentByScode(manager);  break;
                case "2.7":  searchStudentByName(manager);   break;
                case "2.8":  searchBookingsByScode(manager); break;

                // --- Quan ly dat phong ---
                case "3.1":  loadBookings(manager);          break;
                case "3.2":  bookRoom(manager);              break;
                case "3.3":  displayBookings(manager);       break;
                case "3.4":  saveBookings(manager);          break;
                case "3.5":  sortBookings(manager);          break;
                case "3.6":  leaveRoom(manager);             break;

                // Thoat chuong trinh
                case "0":
                    System.out.println("Thank you for using Dormitory Management System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please choose from 1.1 to 3.6, or 0 to exit.");
            }
            System.out.println();
        }
    }

    // In menu chinh ra man hinh
    private static void printMenu() {
        System.out.println("=========================================================");
        System.out.println("           DORMITORY MANAGEMENT SYSTEM                   ");
        System.out.println("=========================================================");
        System.out.println(" ROOM LIST:");
        System.out.println("   1.1.  Load room list from rooms.txt");
        System.out.println("   1.2.  Input & add room to the end");
        System.out.println("   1.3.  Display room list");
        System.out.println("   1.4.  Save room list to rooms.txt");
        System.out.println("   1.5.  Search room by rcode");
        System.out.println("   1.6.  Delete room by rcode");
        System.out.println("   1.7.  Sort rooms by rcode");
        System.out.println("   1.8.  Input & add room to beginning");
        System.out.println("   1.9.  Add room before position k");
        System.out.println("   1.10. Delete room at position k");
        System.out.println("   1.11. Search rooms by name");
        System.out.println("   1.12. Search booked room by rcode (and view occupants)");
        System.out.println();
        System.out.println(" STUDENT LIST:");
        System.out.println("   2.1.  Load student list from students.txt");
        System.out.println("   2.2.  Input & add student to the end");
        System.out.println("   2.3.  Display student list");
        System.out.println("   2.4.  Save student list to students.txt");
        System.out.println("   2.5.  Search student by scode");
        System.out.println("   2.6.  Delete student by scode");
        System.out.println("   2.7.  Search students by name");
        System.out.println("   2.8.  Search bookings by scode (rooms booked)");
        System.out.println();
        System.out.println(" BOOKING LIST:");
        System.out.println("   3.1.  Load booking list from bookings.txt");
        System.out.println("   3.2.  Book a room");
        System.out.println("   3.3.  Display booking list");
        System.out.println("   3.4.  Save booking list to bookings.txt");
        System.out.println("   3.5.  Sort bookings by rcode + scode descending");
        System.out.println("   3.6.  Leave room by rcode + scode");
        System.out.println();
        System.out.println("   0.    Exit program");
        System.out.println("=========================================================");
    }

    // ==========================================
    //    CẨN THẬN ĐIỀU KHIỂN & NHẬP LIỆU PHÒNG
    // ==========================================

    private static void loadRooms(DormitoryManager manager) {
        try {
            int count = manager.loadRooms();
            if (count == -1) {
                System.out.println("Error: rooms.txt does not exist.");
            } else {
                System.out.printf("Room data loaded successfully from rooms.txt. Loaded %d room(s).%n", count);
            }
        } catch (Exception e) {
            System.out.println("Error loading room data: " + e.getMessage());
        }
    }

    private static void addRoomEnd(DormitoryManager manager) {
        Room r = inputRoom(manager, true);
        if (r != null) {
            if (manager.addRoomEnd(r)) {
                System.out.println("Room added to the end of the list.");
            } else {
                System.out.println("Error: Room Code must be unique. This code already exists.");
            }
        }
    }

    private static void displayRooms(DormitoryManager manager) {
        manager.getRoomList().display();
    }

    private static void saveRooms(DormitoryManager manager) {
        try {
            manager.saveRooms();
            System.out.println("Room data saved successfully to rooms.txt.");
        } catch (Exception e) {
            System.out.println("Error saving room data: " + e.getMessage());
        }
    }

    private static void searchRoomByRcode(DormitoryManager manager) {
        String rcode = Validation.getString("Enter Room Code to search: ", "Room Code cannot be empty.");
        Room r = manager.searchRoomByRcode(rcode);
        if (r != null) {
            System.out.println("Room found:");
            System.out.println(r.toString());
        } else {
            System.out.println("Room not found.");
        }
    }

    private static void deleteRoomByRcode(DormitoryManager manager) {
        String rcode = Validation.getString("Enter Room Code to delete: ", "Room Code cannot be empty.");
        int result = manager.deleteRoomByRcode(rcode);
        if (result == 0) {
            System.out.println("Room and all its booking history deleted successfully.");
        } else if (result == 1) {
            System.out.println("Room not found.");
        } else if (result == 2) {
            System.out.printf("Cannot delete room %s: It currently has active bookings.%n", rcode);
        }
    }

    private static void sortRoomsByRcode(DormitoryManager manager) {
        manager.sortRoomsByRcode();
        System.out.println("Rooms sorted by rcode. Current list:");
        manager.getRoomList().display();
    }

    private static void addRoomStart(DormitoryManager manager) {
        Room r = inputRoom(manager, true);
        if (r != null) {
            if (manager.addRoomStart(r)) {
                System.out.println("Room added to the beginning of the list.");
            } else {
                System.out.println("Error: Room Code must be unique. This code already exists.");
            }
        }
    }

    private static void addRoomBeforePos(DormitoryManager manager) {
        int k = Validation.getInt("Enter position k (0-based): ", "Position must be an integer.");
        int n = manager.getRoomList().size();
        if (k < 0 || k > n) {
            System.out.printf("Invalid position. Position must be between 0 and %d.%n", n);
            return;
        }
        Room r = inputRoom(manager, true);
        if (r != null) {
            if (manager.addRoomBeforePos(r, k)) {
                System.out.printf("Room added before position %d.%n", k);
            } else {
                System.out.println("Error adding room (Room Code must be unique).");
            }
        }
    }

    private static void deleteRoomAt(DormitoryManager manager) {
        int k = Validation.getInt("Enter position k to delete (0-based): ", "Position must be an integer.");
        int result = manager.deleteRoomAt(k);
        if (result == 0) {
            System.out.printf("Room at position %d deleted successfully.%n", k);
        } else if (result == 1) {
            System.out.println("Invalid position.");
        } else if (result == 2) {
            System.out.println("Cannot delete room: It currently has active bookings.");
        }
    }

    private static void searchRoomByName(DormitoryManager manager) {
        String name = Validation.getString("Enter Room Name to search: ", "Room Name cannot be empty.");
        RoomList matches = manager.searchRoomByName(name);
        if (!matches.isEmpty()) {
            System.out.println("Matching rooms:");
            matches.display();
        } else {
            System.out.println("No matching rooms found.");
        }
    }

    private static void searchBookedRoomsByRcode(DormitoryManager manager) {
        String rcode = Validation.getString("Enter Room Code: ", "Room Code cannot be empty.");
        Room r = manager.searchRoomByRcode(rcode);
        if (r == null) {
            System.out.println("Room not found.");
            return;
        }
        System.out.println("Room Details:");
        System.out.println(r.toString());

        StudentList occupants = manager.getOccupants(rcode);
        System.out.println("Students currently living in this room:");
        if (!occupants.isEmpty()) {
            occupants.display();
        } else {
            System.out.println("No students are currently living in this room.");
        }
    }

    // ==========================================
    //    ĐIỀU KHIỂN & NHẬP LIỆU SINH VIÊN
    // ==========================================

    private static void loadStudents(DormitoryManager manager) {
        try {
            int count = manager.loadStudents();
            if (count == -1) {
                System.out.println("Error: students.txt does not exist.");
            } else {
                System.out.printf("Student data loaded successfully from students.txt. Loaded %d student(s).%n", count);
            }
        } catch (Exception e) {
            System.out.println("Error loading student data: " + e.getMessage());
        }
    }

    private static void addStudentEnd(DormitoryManager manager) {
        Student s = inputStudent(manager, true);
        if (s != null) {
            if (manager.addStudentEnd(s)) {
                System.out.println("Student added to the end of the list.");
            } else {
                System.out.println("Error: Student Code must be unique. This code already exists.");
            }
        }
    }

    private static void displayStudents(DormitoryManager manager) {
        manager.getStudentList().display();
    }

    private static void saveStudents(DormitoryManager manager) {
        try {
            manager.saveStudents();
            System.out.println("Student data saved successfully to students.txt.");
        } catch (Exception e) {
            System.out.println("Error saving student data: " + e.getMessage());
        }
    }

    private static void searchStudentByScode(DormitoryManager manager) {
        String scode = Validation.getString("Enter Student Code to search: ", "Student Code cannot be empty.");
        Student s = manager.searchStudentByScode(scode);
        if (s != null) {
            System.out.println("Student found:");
            System.out.println(s.toString());
        } else {
            System.out.println("Student not found.");
        }
    }

    private static void deleteStudentByScode(DormitoryManager manager) {
        String scode = Validation.getString("Enter Student Code to delete: ", "Student Code cannot be empty.");
        int result = manager.deleteStudentByScode(scode);
        if (result == 0) {
            System.out.println("Student and all their booking history deleted successfully.");
        } else if (result == 1) {
            System.out.println("Student not found.");
        } else if (result == 2) {
            System.out.println("Cannot delete student: The student is currently living in a room.");
        }
    }

    private static void searchStudentByName(DormitoryManager manager) {
        String name = Validation.getString("Enter Student Name to search: ", "Student Name cannot be empty.");
        StudentList matches = manager.searchStudentByName(name);
        if (!matches.isEmpty()) {
            System.out.println("Matching students:");
            matches.display();
        } else {
            System.out.println("No matching students found.");
        }
    }

    private static void searchBookingsByScode(DormitoryManager manager) {
        String scode = Validation.getString("Enter Student Code: ", "Student Code cannot be empty.");
        Student s = manager.searchStudentByScode(scode);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.println("Student Details:");
        System.out.println(s.toString());

        BookingList bookings = manager.getBookingsByScode(scode);
        System.out.println("Rooms booked by this student:");
        if (!bookings.isEmpty()) {
            Node<Booking> current = bookings.getHead();
            while (current != null) {
                Booking b = current.info;
                Room room = manager.searchRoomByRcode(b.getRcode());
                if (room != null) {
                    System.out.printf("%s (Status: %s)%n", room.toString(), b.getState().getDisplayName());
                }
                current = current.next;
            }
        } else {
            System.out.println("No rooms have been booked by this student.");
        }
    }

    // ==========================================
    //      ĐIỀU KHIỂN & NHẬP LIỆU ĐẶT PHÒNG
    // ==========================================

    private static void loadBookings(DormitoryManager manager) {
        try {
            int count = manager.loadBookings();
            if (count == -1) {
                System.out.println("Error: bookings.txt does not exist.");
            } else {
                System.out.printf("Booking data loaded successfully from bookings.txt. Loaded %d booking(s).%n", count);
            }
        } catch (Exception e) {
            System.out.println("Error loading booking data: " + e.getMessage());
        }
    }

    private static void bookRoom(DormitoryManager manager) {
        String rcode = Validation.getString("Enter Room Code: ", "Room Code cannot be empty.");
        String scode = Validation.getString("Enter Student Code: ", "Student Code cannot be empty.");
        int result = manager.bookRoom(rcode, scode);
        if (result == 0) {
            System.out.println("Room booked successfully!");
        } else if (result == 1) {
            System.out.println("Error: Room does not exist.");
        } else if (result == 2) {
            System.out.println("Error: Student does not exist.");
        } else if (result == 3) {
            System.out.println("Error: Room is full (no available beds).");
        } else if (result == 4) {
            System.out.println("Error: Student is currently living in another room.");
        }
    }

    private static void displayBookings(DormitoryManager manager) {
        manager.getBookingList().display();
    }

    private static void saveBookings(DormitoryManager manager) {
        try {
            manager.saveBookings();
            System.out.println("Booking data saved successfully to bookings.txt.");
        } catch (Exception e) {
            System.out.println("Error saving booking data: " + e.getMessage());
        }
    }

    private static void sortBookings(DormitoryManager manager) {
        manager.sortBookings();
        System.out.println("Bookings sorted by rcode + scode descending. Current list:");
        manager.getBookingList().display();
    }

    private static void leaveRoom(DormitoryManager manager) {
        String rcode = Validation.getString("Enter Room Code: ", "Room Code cannot be empty.");
        String scode = Validation.getString("Enter Student Code: ", "Student Code cannot be empty.");
        int result = manager.leaveRoom(rcode, scode);
        if (result == 0) {
            System.out.println("Student has successfully left the room.");
        } else if (result == 1) {
            System.out.println("Error: No active booking found for this student and room.");
        }
    }

    // ==========================================
    //          HÀM HỖ TRỢ NHẬP LIỆU
    // ==========================================

    private static Room inputRoom(DormitoryManager manager, boolean checkUnique) {
        String rcode;
        while (true) {
            rcode = Validation.getString("Enter Room Code: ", "Room Code cannot be empty.");
            if (!checkUnique) break;
            if (manager.searchRoomByRcode(rcode) == null) break;
            System.out.println("Error: Room Code must be unique. This code already exists.");
        }
        String name = Validation.getString("Enter Room Name: ", "Room Name cannot be empty.");
        String dom = Validation.getString("Enter Dormitory: ", "Dormitory cannot be empty.");
        String floor = Validation.getString("Enter Floor: ", "Floor cannot be empty.");
        String type = Validation.getRoomType("Enter Room Type (double/triple): ",
                "Room Type must be 'double' or 'triple'.");

        // Tự động tính sức chứa (Capacity) theo loại phòng
        int capacity = type.equalsIgnoreCase("double") ? 4 : 6;
        System.out.printf("Room type '%s' automatically assigned capacity of %d bed(s).%n", type, capacity);

        int booked = Validation.getBookedBeds(capacity,
                "Enter Booked Beds: ", "Booked beds must be an integer.");
        double price = Validation.getDouble("Enter Price per Bed: ", "Price must be a number.");
        while (price < 0) {
            System.out.println("Error: Price cannot be negative.");
            price = Validation.getDouble("Enter Price per Bed: ", "Price must be a number.");
        }

        // Available beds = capacity - booked
        return new Room(rcode, name, dom, floor, type, capacity - booked, booked, price);
    }

    private static Student inputStudent(DormitoryManager manager, boolean checkUnique) {
        String scode;
        while (true) {
            scode = Validation.getString("Enter Student Code: ", "Student Code cannot be empty.");
            if (!checkUnique) break;
            if (manager.searchStudentByScode(scode) == null) break;
            System.out.println("Error: Student Code must be unique. This code already exists.");
        }
        String name = Validation.getString("Enter Student Name: ", "Student Name cannot be empty.");
        int byear = Validation.getBirthYear("Enter Birth Year: ", "Birth Year must be an integer.");

        return new Student(scode, name, byear);
    }
}
