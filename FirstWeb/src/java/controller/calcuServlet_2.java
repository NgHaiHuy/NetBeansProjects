package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "calcuServlet_2", urlPatterns = {"/calcuServlet_2", "/calcuservlet_2", "/calcusevlet_2"})
public class calcuServlet_2 extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String txtX = request.getParameter("numX");
        String txtY = request.getParameter("numY");
        String op = request.getParameter("operator");
        
        String resultStr = "";
        boolean isSuccess = true;
        
        if (txtX != null && txtY != null && !txtX.trim().isEmpty() && !txtY.trim().isEmpty()) {
            try {
                double x = Double.parseDouble(txtX);
                double y = Double.parseDouble(txtY);
                double res = 0;

                switch (op) {
                    case "+": res = x + y; break;
                    case "-": res = x - y; break;
                    case "x": res = x * y; break;
                    case "/": 
                        if (y != 0) {
                            res = x / y;
                        } else {
                            isSuccess = false;
                            resultStr = "Error! Cannot divide by 0";
                        }
                        break;
                    default: 
                        isSuccess = false; 
                        resultStr = "Error! Invalid operator";
                        break;
                }

                if (isSuccess) {
                    if (res == (long) res) {
                        resultStr = String.valueOf((long) res);
                    } else {
                        resultStr = String.valueOf(res);
                    }
                }
            } catch (NumberFormatException e) {
                isSuccess = false;
                resultStr = "Error! Invalid number format";
            }
        } else {
            isSuccess = false;
            resultStr = "Error! Please input both X and Y";
        }
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("    <meta charset='UTF-8'>");
            out.println("    <title>Calculation Result</title>");
            out.println("    <link href='https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600&display=swap' rel='stylesheet'>");
            out.println("    <style>");
            out.println("        * { box-sizing: border-box; margin: 0; padding: 0; font-family: 'Inter', sans-serif; }");
            out.println("        body { background: linear-gradient(135deg, #e0f2fe 0%, #bae6fd 50%, #f0f9ff 100%); min-height: 100vh; display: flex; align-items: center; justify-content: center; }");
            out.println("        .main-container { background: rgba(255, 255, 255, 0.98); padding: 40px; border-radius: 24px; box-shadow: 0 10px 40px rgba(0, 0, 0, 0.05); width: 100%; max-width: 400px; text-align: center; }");
            out.println("        h2 { font-size: 24px; font-weight: 600; color: #111827; margin-bottom: 24px; }");
            out.println("        .detail-row { display: flex; justify-content: space-between; padding: 12px 0; border-bottom: 1px solid #f3f4f6; font-size: 15px; color: #4b5563; }");
            out.println("        .detail-row:last-of-type { border-bottom: none; }");
            out.println("        .label { font-weight: 500; }");
            out.println("        .value { font-weight: 600; color: #1f2937; }");
            out.println("        .result-box { margin-top: 24px; padding: 16px; border-radius: 12px; background-color: #f0fdf4; border: 1px solid #bbf7d0; text-align: left; }");
            out.println("        .result-box.error { background-color: #fef2f2; border-color: #fecaca; }");
            out.println("        .result-title { font-size: 12px; font-weight: 600; color: #166534; text-transform: uppercase; letter-spacing: 0.05em; margin-bottom: 4px; }");
            out.println("        .result-box.error .result-title { color: #991b1b; }");
            out.println("        .result-value { font-size: 24px; font-weight: 600; color: #15803d; word-break: break-all; }");
            out.println("        .result-box.error .result-value { color: #b91c1c; }");
            out.println("        .btn-back { margin-top: 24px; display: inline-block; width: 100%; padding: 14px; background-color: #1f2937; color: white; text-decoration: none; border-radius: 12px; font-size: 16px; font-weight: 500; transition: background-color 0.2s; text-align: center; }");
            out.println("        .btn-back:hover { background-color: #111827; }");
            out.println("    </style>");
            out.println("</head>");
            out.println("<body>");
            out.println("    <div class='main-container'>");
            out.println("        <h2>JSP Result</h2>");
            out.println("        <div class='detail-row'><span class='label'>Number X:</span><span class='value'>" + (txtX != null ? txtX : "") + "</span></div>");
            out.println("        <div class='detail-row'><span class='label'>Operator:</span><span class='value'>" + (op != null ? op : "") + "</span></div>");
            out.println("        <div class='detail-row'><span class='label'>Number Y:</span><span class='value'>" + (txtY != null ? txtY : "") + "</span></div>");
            
            if (isSuccess) {
                out.println("        <div class='result-box'>");
            } else {
                out.println("        <div class='result-box error'>");
            }
            
            out.println("            <div class='result-title'>Result</div>");
            out.println("            <div class='result-value'>" + resultStr + "</div>");
            out.println("        </div>");
            out.println("        <a href='calcu.jsp' class='btn-back'>Calculate Again</a>");
            out.println("    </div>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "calcuServlet_2 for JSP";
    }
}
