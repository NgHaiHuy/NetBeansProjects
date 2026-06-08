import service.DormitoryManager;
import util.Validation;

// Lop chinh chua menu va dieu khien chuong trinh
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
                case "1.1":  manager.loadRooms();             break;
                case "1.2":  manager.addRoomEnd();            break;
                case "1.3":  manager.displayRooms();          break;
                case "1.4":  manager.saveRooms();             break;
                case "1.5":  manager.searchRoomByRcode();     break;
                case "1.6":  manager.deleteRoomByRcode();     break;
                case "1.7":  manager.sortRoomsByRcode();      break;
                case "1.8":  manager.addRoomStart();          break;
                case "1.9":  manager.addRoomBeforePos();      break;
                case "1.10": manager.deleteRoomAt();          break;
                case "1.11": manager.searchRoomByName();      break;
                case "1.12": manager.searchBookedRoomsByRcode(); break;

                // --- Quan ly sinh vien ---
                case "2.1":  manager.loadStudents();          break;
                case "2.2":  manager.addStudentEnd();         break;
                case "2.3":  manager.displayStudents();       break;
                case "2.4":  manager.saveStudents();          break;
                case "2.5":  manager.searchStudentByScode();  break;
                case "2.6":  manager.deleteStudentByScode();  break;
                case "2.7":  manager.searchStudentByName();   break;
                case "2.8":  manager.searchBookingsByScode(); break;

                // --- Quan ly dat phong ---
                case "3.1":  manager.loadBookings();          break;
                case "3.2":  manager.bookRoom();              break;
                case "3.3":  manager.displayBookings();       break;
                case "3.4":  manager.saveBookings();          break;
                case "3.5":  manager.sortBookings();          break;
                case "3.6":  manager.leaveRoom();             break;

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
}
