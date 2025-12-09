package Expense_Servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import ExpenseBean.ExpenseBean;
import ExpenseDAOs.ExpenseDAO;

@WebServlet("/expenses")
public class GetExpensesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
    	
    	HttpSession session = request.getSession(false);

    	if (session == null || session.getAttribute("userId") == null) {
    	    response.setStatus(401);
    	    return;
    	}

    	int userId = (int) session.getAttribute("userId");


        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        int employeeId = userId;

        ExpenseDAO dao = new ExpenseDAO();
        List<ExpenseBean> expenses = dao.getExpensesByEmployee(employeeId);

        Gson gson = new Gson();
        out.print(gson.toJson(expenses));
        out.flush();
    }
}
