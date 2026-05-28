<%-- 
    Document   : calcu
    Created on : May 20, 2026, 8:51:46 AM
    Author     : LECOO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Calculator JSP Page</title>
        <link href="https://googleapis.com" rel="stylesheet">
        <style>
            * {
                box-sizing: border-box;
                margin: 0;
                padding: 0;
                font-family: 'Inter', sans-serif;
            }

            body {
                background: linear-gradient(135deg, #e0f2fe 0%, #bae6fd 50%, #f0f9ff 100%);
                min-height: 100vh;
                display: flex;
                align-items: center;
                justify-content: center;
            }

            .main-container {
                background: rgba(255, 255, 255, 0.98);
                padding: 40px;
                border-radius: 24px;
                box-shadow: 0 10px 40px rgba(0, 0, 0, 0.05);
                width: 100%;
                max-width: 400px;
                text-align: center;
            }

            h2 {
                font-size: 24px;
                font-weight: 600;
                color: #111827;
                margin-bottom: 24px;
            }

            /* Cấu trúc các ô nhập liệu */
            .input-group {
                text-align: left;
                margin-bottom: 16px;
            }

            .input-group label {
                display: block;
                font-size: 14px;
                font-weight: 500;
                color: #4b5563;
                margin-bottom: 6px;
            }

            .form-control {
                width: 100%;
                padding: 12px 16px;
                font-size: 16px;
                border: 1px solid #e5e7eb;
                border-radius: 12px;
                background-color: #f9fafb;
                color: #1f2937;
                outline: none;
                transition: all 0.2s;
            }

            .form-control:focus {
                border-color: #bae6fd;
                box-shadow: 0 0 0 3px rgba(186, 230, 253, 0.5);
            }

            /* Khu vực nút bấm */
            .btn-group {
                display: flex;
                justify-content: center;
                gap: 12px;
                margin-top: 24px;
                margin-bottom: 24px;
            }

            .btn {
                padding: 14px 28px;
                font-size: 16px;
                font-weight: 500;
                border: none;
                border-radius: 12px;
                cursor: pointer;
                transition: background-color 0.2s;
            }

            .btn-calc {
                background-color: #1f2937;
                color: white;
            }

            .btn-calc:hover {
                background-color: #111827;
            }

            .btn-reset {
                background-color: #ef4444;
                color: white;
                text-decoration: none;
                display: flex;
                align-items: center;
                justify-content: center;
            }

            .btn-reset:hover {
                background-color: #dc2626;
            }

            /* Box hiển thị đáp án */
            .result-container {
                text-align: left;
                margin-top: 20px;
                padding: 16px;
                border-radius: 12px;
                background-color: #f3f4f6;
                border: 1px solid #e5e7eb;
            }

            .result-title {
                font-size: 12px;
                font-weight: 600;
                color: #6b7280;
                text-transform: uppercase;
                letter-spacing: 0.05em;
                margin-bottom: 4px;
            }

            .result-value {
                font-size: 20px;
                font-weight: 600;
                color: #111827;
                word-break: break-all;
            }

            .btn-home {
                margin-top: 20px;
                display: block;
                text-decoration: none;
                color: #6b7280;
                font-size: 14px;
            }

            .btn-home:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        
        <%
            // Khởi tạo các biến lưu trữ giá trị mặc định
            String txtX = "";
            String txtY = "";
            String selectedOp = "+";
            String resultStr = "";

            // Nhận diện hành động gửi dữ liệu (Khi bấm nút Calculator)
            if ("POST".equalsIgnoreCase(request.getMethod())) {
                txtX = request.getParameter("numX");
                txtY = request.getParameter("numY");
                selectedOp = request.getParameter("operator");

                if (txtX != null && txtY != null && !txtX.trim().isEmpty() && !txtY.trim().isEmpty()) {
                    try {
                        double x = Double.parseDouble(txtX);
                        double y = Double.parseDouble(txtY);
                        double res = 0;
                        boolean isSuccess = true;
                        
                        // Xử lý nhánh switch-case chuẩn xác
                        switch (selectedOp) {
                            case "+": res = x + y; break;
                            case "-": res = x - y; break;
                            case "x": res = x * y; break;
                            case "/": 
                                if (y != 0) {
                                    res = x / y;
                                } else {
                                    isSuccess = false;
                                    resultStr = "Error! Can division 0";
                                }
                                break;
                            default: isSuccess = false; break;
                        }

                        if (isSuccess) {
                            // Định dạng hiển thị kết quả gọn đẹp (loại bỏ đuôi .0 nếu là số nguyên)
                            if (res == (long) res) {
                                resultStr = String.valueOf((long) res);
                            } else {
                                resultStr = String.valueOf(res);
                            }
                        }
                    } catch (NumberFormatException e) {
                        resultStr = "Error: Invalid number format";
                    }
                } else {
                    resultStr = "Error! Please input x or y";
                }
            }
        %>

        <div class="main-container">
            <h2>Calculator</h2>

            <form action="" method="post">
                <!-- Ô nhập số x -->
                <div class="input-group">
                    <label for="numX">Input X</label>
                    <input type="number" step="any" id="numX" name="numX" class="form-control" required value="<%= txtX %>">
                </div>

                <!-- Ô nhập số y -->
                <div class="input-group">
                    <label for="numY">Input Y</label>
                    <input type="number" step="any" id="numY" name="numY" class="form-control" required value="<%= txtY %>">
                </div>

                <div class="input-group">
                    <label for="operator">Choose the operation</label>
                    <select id="operator" name="operator" class="form-control">
                        <option value="+" <%= "+".equals(selectedOp) ? "selected" : "" %> >(+)</option>
                        <option value="-" <%= "-".equals(selectedOp) ? "selected" : "" %> >(-)</option>
                        <option value="x" <%= "x".equals(selectedOp) ? "selected" : "" %> >(x)</option>
                        <option value="/" <%= "/".equals(selectedOp) ? "selected" : "" %> >(/)</option>
                    </select>
                </div>
                    
                <div class="btn-group">
                    <button type="submit" class="btn btn-calc">Calculator</button>
                </div>
            </form>

            <!-- Box hiển thị đáp án ở dưới cùng -->
            <div class="result-container">
                <div class="result-title">Result</div>
                <div class="result-value"><%= resultStr %></div>
            </div>

            <a href="index.html" class="btn-home">Back to Home</a>
        </div>
    </body>
</html>
