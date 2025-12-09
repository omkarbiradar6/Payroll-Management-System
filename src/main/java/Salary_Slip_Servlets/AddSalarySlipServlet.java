package Salary_Slip_Servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Beans.SalarySlipBean;
import DataBase_operations.DBConnect;
import SalarySlipDAOs.SalarySlipDAO;

@WebServlet("/AddSalarySlip")
public class AddSalarySlipServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        Gson gson = new Gson();

    
        // SESSION CHECK
     
        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            res.setStatus(401);
            out.print("{\"error\":\"Unauthorized - Please login\"}");
            return;
        }

        
        // ADMIN CHECK
       
        String role = (String) session.getAttribute("role");
        if (!"admin".equals(role)) {
            res.setStatus(403);
            out.print("{\"error\":\"Forbidden - Only admin can add salary slips\"}");
            return;
        }

       
        // READ JSON FROM REQUEST BODY
       
        BufferedReader reader = req.getReader();
        SalarySlipBean slip = gson.fromJson(reader, SalarySlipBean.class);

        // Validate JSON fields (optional)
        if (slip == null || slip.getEmployeeId() == 0) {
            res.setStatus(400);
            out.print("{\"error\":\"Invalid JSON input\"}");
            return;
        }

        
        // DATABASE INSERT
        
        SalarySlipDAO dao = new SalarySlipDAO(DBConnect.connect());
        boolean success = dao.addSalarySlip(slip);

        // SEND JSON RESPONSE
        if (success) {
            out.print("{\"status\":\"success\",\"message\":\"Salary Slip Added Successfully\"}");
        } else {
            res.setStatus(500);
            out.print("{\"status\":\"error\",\"message\":\"Failed to Add Salary Slip\"}");
        }

        out.flush();
    }
}
