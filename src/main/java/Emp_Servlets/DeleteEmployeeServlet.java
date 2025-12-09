package Emp_Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EmployeeDAOs.EmployeeDao;

@WebServlet("/employee/delete")
public class DeleteEmployeeServlet extends HttpServlet {

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        int empId = Integer.parseInt(request.getParameter("id"));

        EmployeeDao dao = new EmployeeDao();
        boolean deleted = dao.deleteEmployee(empId);

        if (deleted) {
            out.print("{\"status\":\"success\",\"message\":\"Employee deleted successfully\"}");
        } else {
            out.print("{\"status\":\"error\",\"message\":\"Failed to delete employee\"}");
        }
        out.flush();
    }
}
