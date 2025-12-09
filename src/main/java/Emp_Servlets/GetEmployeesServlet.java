package Emp_Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Beans.EmployeeBean;
import EmployeeDAOs.EmployeeDao;

@WebServlet("/employee/all")
public class GetEmployeesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();

        EmployeeDao dao = new EmployeeDao();
        List<EmployeeBean> employees = dao.getAllEmployees();

        out.print(gson.toJson(employees));
        out.flush();
    }
}
