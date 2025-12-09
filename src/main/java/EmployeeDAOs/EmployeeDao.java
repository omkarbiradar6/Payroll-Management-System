package EmployeeDAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Beans.EmployeeBean;
import DataBase_operations.DBConnect;

public class EmployeeDao {

    // ADD EMPLOYEE
    public boolean addEmployee(EmployeeBean emp) {
        String sql = "INSERT INTO payroll_employees "
                   + "(first_name, last_name, email, phone, dob, department, position, salary, join_date) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnect.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, emp.getFirstName());
            ps.setString(2, emp.getLastName());
            ps.setString(3, emp.getEmail());
            ps.setString(4, emp.getPhone());
            ps.setDate(5, emp.getDob());
            ps.setString(6, emp.getDepartment());
            ps.setString(7, emp.getPosition());
            ps.setDouble(8, emp.getSalary());
            ps.setDate(9, emp.getJoinDate());

            int result = ps.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // GET ALL EMPLOYEES
    public List<EmployeeBean> getAllEmployees() {
        List<EmployeeBean> list = new ArrayList<>();

        String sql = "SELECT * FROM payroll_employees ORDER BY id";

        try (Connection con = DBConnect.connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                EmployeeBean emp = new EmployeeBean();

                emp.setId(rs.getInt("id"));
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                emp.setEmail(rs.getString("email"));
                emp.setPhone(rs.getString("phone"));
                emp.setDob(rs.getDate("dob"));
                emp.setDepartment(rs.getString("department"));
                emp.setPosition(rs.getString("position"));
                emp.setSalary(rs.getDouble("salary"));
                emp.setJoinDate(rs.getDate("join_date"));

                list.add(emp);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // UPDATE EMPLOYEE
    public boolean updateEmployee(EmployeeBean emp) {
        String sql = "UPDATE payroll_employees SET "
                   + "first_name=?, last_name=?, email=?, phone=?, dob=?, "
                   + "department=?, position=?, salary=?, join_date=? "
                   + "WHERE id=?";

        try (Connection con = DBConnect.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, emp.getFirstName());
            ps.setString(2, emp.getLastName());
            ps.setString(3, emp.getEmail());
            ps.setString(4, emp.getPhone());
            ps.setDate(5, emp.getDob());
            ps.setString(6, emp.getDepartment());
            ps.setString(7, emp.getPosition());
            ps.setDouble(8, emp.getSalary());
            ps.setDate(9, emp.getJoinDate());
            ps.setInt(10, emp.getId());

            int result = ps.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // DELETE EMPLOYEE
    public boolean deleteEmployee(int empId) {
        String sql = "DELETE FROM payroll_employees WHERE id=?";

        try (Connection con = DBConnect.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, empId);

            int result = ps.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
