package ExpenseDAOs;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DataBase_operations.DBConnect;
import ExpenseBean.ExpenseBean;

public class ExpenseDAO {

    Connection con = DBConnect.connect();

    // 1️st Insert Expense
    public boolean addExpense(ExpenseBean bean) {
        try {
            String query = "INSERT INTO expenses (employee_id, amount, category, description, month, year) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, bean.getEmployeeId());
            ps.setDouble(2, bean.getAmount());
            ps.setString(3, bean.getCategory());
            ps.setString(4, bean.getDescription());
            ps.setString(5, bean.getMonth());
            ps.setString(6, bean.getYear());

            int result = ps.executeUpdate();
            return result > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2️nd Get expenses for an employee
    public List<ExpenseBean> getExpensesByEmployee(int employeeId) {
        List<ExpenseBean> list = new ArrayList<>();

        try {
            String query = "SELECT * FROM expenses WHERE employee_id = ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, employeeId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ExpenseBean bean = new ExpenseBean();
                bean.setId(rs.getInt("id"));
                bean.setEmployeeId(rs.getInt("employee_id"));
                bean.setAmount(rs.getDouble("amount"));
                bean.setCategory(rs.getString("category"));
                bean.setDescription(rs.getString("description"));
                bean.setMonth(rs.getString("month"));
                bean.setYear(rs.getString("year"));

                list.add(bean);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
