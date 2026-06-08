package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

// Lop chua cac ham kiem tra va nhap du lieu tu ban phim
public class Validation {
    private static final Scanner scanner = new Scanner(System.in);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    static {
        sdf.setLenient(false); // Tat che do tu dong sua loi ngay thang
    }

    // Nhap chuoi, bat buoc khong duoc rong
    public static String getString(String prompt, String errorMsg) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println(errorMsg);
        }
    }

    // Nhap so nguyen, nhap lai neu sai
    public static int getInt(String prompt, String errorMsg) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            }
        }
    }

    // Nhap so thuc, nhap lai neu sai
    public static double getDouble(String prompt, String errorMsg) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println(errorMsg);
            }
        }
    }

    // Nhap loai phong, chi chap nhan "double" hoac "triple"
    public static String getRoomType(String prompt, String errorMsg) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("double") || input.equals("triple")) return input;
            System.out.println(errorMsg);
        }
    }

    // Nhap so giuong phu hop voi loai phong (4 cho double, 6 cho triple)
    public static int getBedsForType(String type, String prompt, String errorMsg) {
        int expected = type.equalsIgnoreCase("double") ? 4 : 6;
        while (true) {
            int beds = getInt(prompt, errorMsg);
            if (beds == expected) return beds;
            System.out.printf("Error: A %s room must have exactly %d beds.%n", type, expected);
        }
    }

    // Nhap so giuong da dat, phai tu 0 den maxBeds
    public static int getBookedBeds(int maxBeds, String prompt, String errorMsg) {
        while (true) {
            int booked = getInt(prompt, errorMsg);
            if (booked >= 0 && booked <= maxBeds) return booked;
            System.out.printf("Error: Booked beds must be between 0 and %d.%n", maxBeds);
        }
    }

    // Nhap nam sinh, kiem tra phai du 18 tuoi
    public static int getBirthYear(String prompt, String errorMsg) {
        int currentYear = LocalDate.now().getYear();
        while (true) {
            int byear = getInt(prompt, errorMsg);
            if (currentYear - byear >= 18) return byear;
            System.out.printf("Error: Student must be at least 18 years old (born on or before %d).%n",
                    currentYear - 18);
        }
    }

    // Nhap ngay thang theo dinh dang dd/MM/yyyy
    public static Date getDate(String prompt, String errorMsg) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return sdf.parse(input);
            } catch (ParseException e) {
                System.out.println(errorMsg);
            }
        }
    }
}
