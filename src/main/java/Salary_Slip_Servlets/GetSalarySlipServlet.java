package Salary_Slip_Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

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


@WebServlet("/GetSalarySlips")
public class GetSalarySlipServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);

        // Employee must be logged-in
        if (session == null || session.getAttribute("userId") == null) {
            out.write("{\"status\":\"error\", \"message\":\"Unauthorized\"}");
            return;
        }

        int employeeId = (int) session.getAttribute("userId");

        Connection con = DBConnect.connect();
        SalarySlipDAO dao = new SalarySlipDAO(con);

        try {
            // Fetch slips of ONLY this employee
            List<SalarySlipBean> slips = dao.getSlipsByUserId(employeeId);

            Gson gson = new Gson();
            String json = gson.toJson(slips);
            out.write(json);

        } catch (Exception e) {
            e.printStackTrace();
            out.write("{\"status\":\"error\", \"message\":\"Server error\"}");
        }
    }
}
