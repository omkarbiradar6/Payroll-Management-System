package servlets;

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

import Beans.UserBean;
import UserDAOs.UserDao;

@WebServlet("/auth/login")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // Read JSON from request body
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        UserBean inputUser = gson.fromJson(reader, UserBean.class);

        // Check login from DB
        UserDao dao = new UserDao();
        UserBean user = dao.login(inputUser.getEmail(), inputUser.getPassword());

        if (user != null) {

            // Create Session ONLY after successful login
            HttpSession session = request.getSession();
            session.setAttribute("userId", user.getId());
            session.setAttribute("role", user.getRole());
            session.setAttribute("employeeId", user.getId()); 
            String json = gson.toJson(user);
            out.print("{\"status\":\"success\",\"user\":" + json + "}");
        } else {
            out.print("{\"status\":\"error\",\"message\":\"Invalid credentials\"}");
        }
        out.flush();
    }
}
