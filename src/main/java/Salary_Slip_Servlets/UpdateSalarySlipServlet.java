package Salary_Slip_Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import Beans.SalarySlipBean;
import DataBase_operations.DBConnect;
import SalarySlipDAOs.SalarySlipDAO;

@WebServlet("/UpdateSalarySlip")
public class UpdateSalarySlipServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        
        // Auth checks
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            response.setStatus(401);
            out.print("{\"status\":\"error\",\"message\":\"Unauthorized\"}");
            out.flush();
            return;
        }

        String role = (String) session.getAttribute("role");
        if (!"admin".equals(role)) {
            response.setStatus(403);
            out.print("{\"status\":\"error\",\"message\":\"Admin access required\"}");
            out.flush();
            return;
        }

        // Read JSON exactly like SignupServlet
        Gson gson = new Gson();
        SalarySlipBean slip = gson.fromJson(request.getReader(), SalarySlipBean.class);

        SalarySlipDAO dao = new SalarySlipDAO(DBConnect.connect());
        double gross = slip.getBasicSalary() + slip.getAllowances() + slip.getHra();
        double net = gross-slip.getDeductions();
        slip.setNetSalary(net);
        boolean success = dao.updateSalarySlip(slip);

        if (success) {
            out.print("{\"status\":\"success\",\"message\":\"Salary Slip Updated Successfully\"}");
        } else {
            out.print("{\"status\":\"error\",\"message\":\"Failed to Update Salary Slip\"}");
        }
        out.flush();
    }
}
