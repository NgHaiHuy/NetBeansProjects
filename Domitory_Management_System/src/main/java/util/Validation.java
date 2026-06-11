package util;

import java.util.Scanner;

// Lop chua cac ham kiem tra va nhap du lieu tu ban phim
public class Validation {
    private static final Scanner scanner = new Scanner(System.in);

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

    // Nhap nam sinh, kiem tra phai du 18 tuoi va tu nam 1900 tro di
    public static int getBirthYear(String prompt, String errorMsg) {
        int currentYear = java.time.Year.now().getValue();
        while (true) {
            int byear = getInt(prompt, errorMsg);
            if (byear >= 1900 && currentYear - byear >= 18) {
                return byear;
            }
            if (byear < 1900) {
                System.out.println("Error: Birth year must be 1900 or later.");
            } else {
                System.out.printf("Error: Student must be at least 18 years old (born on or before %d).%n",
                        currentYear - 18);
            }
        }
    }

    // Kiem tra chuoi ngay thang hop le dd/MM/yyyy
    public static boolean isValidDateString(String dateStr) {
        if (!dateStr.matches("\\d{2}/\\d{2}/\\d{4}")) return false;
        String[] parts = dateStr.split("/");
        try {
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            if (month < 1 || month > 12) return false;
            if (year < 1900 || year > 2100) return false;

            int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {
                daysInMonth[1] = 29;
            }

            return day >= 1 && day <= daysInMonth[month - 1];
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Nhap ngay thang theo dinh dang dd/MM/yyyy
    public static String getDate(String prompt, String errorMsg) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (isValidDateString(input)) {
                return input;
            }
            System.out.println(errorMsg);
        }
    }
}
