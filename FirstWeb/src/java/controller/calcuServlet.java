package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "calcuServlet", urlPatterns = {"/calcuServlet"})
public class calcuServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");
        String expr = request.getParameter("expression");
        
        try (PrintWriter out = response.getWriter()) {
            if (expr == null || expr.trim().isEmpty()) {
                out.print("");
                return;
            }
            
            char op = ' ';
            int opIndex = -1;
            
            // Find operator
            for(int i=0; i<expr.length(); i++) {
                char c = expr.charAt(i);
                if(c == '+' || c == '-' || c == 'x' || c == '/' || c == '%') {
                    if (i == 0 && c == '-') continue; // handle negative first number
                    op = c;
                    opIndex = i;
                    break;
                }
            }
            
            if(opIndex != -1) {
                double num1 = Double.parseDouble(expr.substring(0, opIndex));
                double num2 = Double.parseDouble(expr.substring(opIndex+1));
                double res = 0;
                
                switch(op) {
                    case '+': res = num1 + num2; break;
                    case '-': res = num1 - num2; break;
                    case 'x': res = num1 * num2; break;
                    case '/': 
                        if(num2 == 0) {
                            out.print("Error");
                            return;
                        }
                        res = num1 / num2; 
                        break;
                    case '%': 
                        if(num2 == 0) {
                            out.print("Error");
                            return;
                        }
                        res = num1 % num2; 
                        break;
                }
                
                if(res == (long) res) {
                    out.print((long)res);
                } else {
                    out.print(res);
                }
            } else {
                out.print(expr);
            }
        } catch (Exception e) {
            response.getWriter().print("Error");
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
        return "Calculator Servlet";
    }
}
