package Emp_Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Beans.EmployeeBean;
import EmployeeDAOs.EmployeeDao;

@WebServlet("/employee/add")
public class AddEmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("HIT /employee/add SERVLET");

        response.setContentType("application/json");
      
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        PrintWriter out = response.getWriter();

        EmployeeBean emp = gson.fromJson(request.getReader(), EmployeeBean.class);

        EmployeeDao dao = new EmployeeDao();
        boolean added = dao.addEmployee(emp);

        if (added) {
            out.print("{\"status\":\"success\",\"message\":\"Employee added successfully\"}");
        } else {
            out.print("{\"status\":\"error\",\"message\":\"Failed to add employee\"}");
        }
        out.flush();
    }
}
