package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Lớp Validation cung cấp các phương thức để nhập liệu và kiểm tra tính hợp lệ của dữ liệu
 * đầu vào từ bàn phím (Console) cũng như hỗ trợ chuyển đổi dữ liệu khi đọc từ file.
 */
public class Validation {
    // Scanner để đọc dữ liệu từ bàn phím
    private static final Scanner scanner = new Scanner(System.in);
    
    // Định dạng ngày tháng mặc định là dd/MM/yyyy
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Nhập chuỗi ký tự không được để trống.
     * @param prompt Gợi ý nhập liệu
     * @param error Thông báo lỗi khi nhập sai
     * @return Chuỗi ký tự hợp lệ đã trim khoảng trắng
     */
    public static String getString(String prompt, String error) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println(error);
        }
    }

    /**
     * Nhập chuỗi ký tự khớp với một định dạng Regex cho trước.
     * @param prompt Gợi ý nhập liệu
     * @param regex Biểu thức chính quy (Regex) để kiểm tra định dạng
     * @param error Thông báo lỗi khi không khớp định dạng
     * @return Chuỗi ký tự khớp regex
     */
    public static String getPattern(String prompt, String regex, String error) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.matches(regex)) {
                return input;
            }
            System.out.println(error);
        }
    }

    /**
     * Nhập số nguyên nằm trong một khoảng [min, max] xác định.
     * @param prompt Gợi ý nhập liệu
     * @param min Giá trị nhỏ nhất được chấp nhận
     * @param max Giá trị lớn nhất được chấp nhận
     * @param error Thông báo lỗi khi nhập sai hoặc ngoài khoảng
     * @return Số nguyên hợp lệ
     */
    public static int getInt(String prompt, int min, int max, String error) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException ignored) {}
            System.out.println(error);
        }
    }

    /**
     * Nhập số thực nằm trong một khoảng [min, max] xác định.
     * @param prompt Gợi ý nhập liệu
     * @param min Giá trị thực nhỏ nhất được chấp nhận
     * @param max Giá trị thực lớn nhất được chấp nhận
     * @param error Thông báo lỗi khi nhập sai hoặc ngoài khoảng
     * @return Số thực hợp lệ
     */
    public static double getDouble(String prompt, double min, double max, String error) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());
                if (value >= min && value <= max) {
                    return value;
                }
            } catch (NumberFormatException ignored) {}
            System.out.println(error);
        }
    }

    /**
     * Nhập ngày tháng theo định dạng dd/MM/yyyy (bắt buộc phải có ngày).
     * @param prompt Gợi ý nhập liệu
     * @param error Thông báo lỗi khi sai định dạng ngày
     * @return Đối tượng LocalDate tương ứng
     */
    public static LocalDate getDate(String prompt, String error) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return LocalDate.parse(input, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println(error);
            }
        }
    }

    /**
     * Nhập ngày tháng (có thể rỗng hoặc "null" đối với ngày rời phòng ldate).
     * @param prompt Gợi ý nhập
     * @param error Báo lỗi định dạng ngày
     * @return LocalDate hoặc null nếu để trống
     */
    public static LocalDate getNullableDate(String prompt, String error) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty() || input.equalsIgnoreCase("null")) {
                return null;
            }
            try {
                return LocalDate.parse(input, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println(error);
            }
        }
    }

    /**
     * Nhập loại phòng (chỉ chấp nhận "double" hoặc "triple").
     * @param prompt Gợi ý nhập
     * @param error Báo lỗi
     * @return Loại phòng dạng chữ thường
     */
    public static String getRoomType(String prompt, String error) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("double") || input.equals("triple")) {
                return input;
            }
            System.out.println(error);
        }
    }

    /**
     * Nhập năm sinh sinh viên và kiểm tra xem sinh viên có đủ 18 tuổi hay không.
     * @param prompt Gợi ý nhập
     * @param error Báo lỗi định dạng năm sinh
     * @return Năm sinh hợp lệ của sinh viên đủ 18 tuổi
     */
    public static int getStudentBirthYear(String prompt, String error) {
        int currentYear = LocalDate.now().getYear();
        while (true) {
            int year = getInt(prompt, 1900, currentYear, error);
            if (currentYear - year >= 18) {
                return year;
            }
            System.out.println("Lỗi: Sinh viên phải từ 18 tuổi trở lên. Năm hiện tại: " + currentYear);
        }
    }

    /**
     * Định dạng đối tượng LocalDate thành chuỗi dạng dd/MM/yyyy.
     * @param date Đối tượng LocalDate
     * @return Chuỗi ngày tháng hoặc "null" nếu đối tượng là null
     */
    public static String formatDate(LocalDate date) {
        if (date == null) return "null";
        return date.format(DATE_FORMATTER);
    }
}
