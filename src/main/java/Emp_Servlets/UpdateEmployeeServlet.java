package Emp_Servlets;


import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Beans.EmployeeBean;
import EmployeeDAOs.EmployeeDao;

@WebServlet("/employee/update")
public class UpdateEmployeeServlet extends HttpServlet {

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        Gson gson = new Gson();
        PrintWriter out = response.getWriter();

        EmployeeBean emp = gson.fromJson(request.getReader(), EmployeeBean.class);

        EmployeeDao dao = new EmployeeDao();
        boolean updated = dao.updateEmployee(emp);

        if (updated) {
            out.print("{\"status\":\"success\",\"message\":\"Employee updated successfully\"}");
        } else {
            out.print("{\"status\":\"error\",\"message\":\"Failed to update employee\"}");
        }
        out.flush();
    }
}
