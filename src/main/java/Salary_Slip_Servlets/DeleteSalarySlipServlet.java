package Salary_Slip_Servlets;


import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DataBase_operations.DBConnect;
import SalarySlipDAOs.SalarySlipDAO;

@WebServlet("/DeleteSalarySlip")
public class DeleteSalarySlipServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException 
    {
    	
    	HttpSession session = req.getSession(false);
    	if (session == null || session.getAttribute("userId") == null) {
    	    res.setStatus(401);
    	    return;
    	}

    	String role = (String) session.getAttribute("role");
    	if (!"admin".equals(role)) {
    	    res.setStatus(403);
    	    return;
    	}


        int id = Integer.parseInt(req.getParameter("id"));

        SalarySlipDAO dao = new SalarySlipDAO(DBConnect.connect());

        boolean success = dao.deleteSalarySlip(id);

        if (success) {
            res.getWriter().write("Salary Slip Deleted Successfully");
        } else {
            res.getWriter().write("Failed to Delete Salary Slip");
        }
    }
}
