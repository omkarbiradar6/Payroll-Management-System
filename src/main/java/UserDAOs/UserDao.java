package UserDAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Beans.UserBean;
import DataBase_operations.DBConnect;

public class UserDao {

    // Check login credentials
    public UserBean login(String email, String password) {
        UserBean user = null;
        try {
            Connection conn = DBConnect.connect();
            String sql = "SELECT * FROM payroll_users WHERE email=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                user = new UserBean();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setRole(rs.getString("role"));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    // Register new user
    public boolean signup(UserBean user) {
        boolean status = false;
        try {
            Connection conn = DBConnect.connect();
            String sql = "INSERT INTO payroll_users (email, password, role) VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getRole());
            int rows = ps.executeUpdate();
            if(rows > 0) status = true;

        } catch(Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
