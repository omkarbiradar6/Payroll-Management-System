package Expense_Servlets;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import ExpenseBean.ExpenseBean;
import ExpenseDAOs.ExpenseDAO;

@WebServlet("/expense")
public class AddExpenseServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
    	
    	HttpSession session = request.getSession(false);

    	if (session == null || session.getAttribute("userId") == null) {
    	    response.setStatus(401); 
    	    return;
    	}

    	int userId = (int) session.getAttribute("userId");


        response.setContentType("application/json");

        Gson gson = new Gson();
        BufferedReader reader = request.getReader();
        ExpenseBean expense = gson.fromJson(reader, ExpenseBean.class);

        ExpenseDAO dao = new ExpenseDAO();
        boolean inserted = dao.addExpense(expense);

        PrintWriter out = response.getWriter();
        if (inserted) {
            out.print("{\"status\":\"success\",\"message\":\"Expense added successfully\"}");
        } else {
            out.print("{\"status\":\"error\",\"message\":\"Failed to add expense\"}");
        }
        out.flush();
    }
}
