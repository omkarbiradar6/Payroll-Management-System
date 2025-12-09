package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import Beans.UserBean;
import UserDAOs.UserDao;

@WebServlet("/auth/signup")
public class SignupServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        BufferedReader reader = request.getReader();
        Gson gson = new Gson();
        UserBean newUser = gson.fromJson(reader, UserBean.class);

        UserDao dao = new UserDao();
        boolean created = dao.signup(newUser);

        if(created) {
            out.print("{\"status\":\"success\",\"message\":\"User registered successfully\"}");
        } else {
            out.print("{\"status\":\"error\",\"message\":\"Email already exists or error\"}");
        }
        out.flush();
    }
}
