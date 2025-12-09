package SalarySlipDAOs;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Beans.SalarySlipBean;

public class SalarySlipDAO {

    private Connection con;

    public SalarySlipDAO(Connection con) {
        this.con = con;
    }

    // 1️st Insert Salary Slip
    public boolean addSalarySlip(SalarySlipBean slip) {
        String sql = "INSERT INTO salary_slips(employee_id, basic_salary, hra, allowances, deductions, net_salary, month, year) VALUES (?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, slip.getEmployeeId());
            ps.setDouble(2, slip.getBasicSalary());
            ps.setDouble(3, slip.getHra());
            ps.setDouble(4, slip.getAllowances());
            ps.setDouble(5, slip.getDeductions());
            ps.setDouble(6, slip.getNetSalary());
            ps.setString(7, slip.getMonth());
            ps.setString(8, slip.getYear());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2️nd Get ALL Salary Slips
    public List<SalarySlipBean> getAllSalarySlips() {
        List<SalarySlipBean> list = new ArrayList<>();
        String sql = "SELECT * FROM salary_slips ORDER BY id DESC";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SalarySlipBean slip = new SalarySlipBean();

                slip.setId(rs.getInt("id"));
                slip.setEmployeeId(rs.getInt("employee_id"));
                slip.setBasicSalary(rs.getDouble("basic_salary"));
                slip.setHra(rs.getDouble("hra"));
                slip.setAllowances(rs.getDouble("allowances"));
                slip.setDeductions(rs.getDouble("deductions"));
                slip.setNetSalary(rs.getDouble("net_salary"));
                slip.setMonth(rs.getString("month"));
                slip.setYear(rs.getString("year"));

                list.add(slip);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // 3️rd Update Salary Slip
    public boolean updateSalarySlip(SalarySlipBean slip) {
        String sql = "UPDATE salary_slips " +
                     "SET basic_salary=?, hra=?, allowances=?, deductions=?, net_salary=?, month=?, year=? " +
                     "WHERE id=?";

        try {
            System.out.println("=== UPDATE DEBUG START ===");
            System.out.println("id=" + slip.getId());
            System.out.println("employeeId=" + slip.getEmployeeId());
            System.out.println("basicSalary=" + slip.getBasicSalary());
            System.out.println("hra=" + slip.getHra());
            System.out.println("allowances=" + slip.getAllowances());
            System.out.println("deductions=" + slip.getDeductions());
            System.out.println("netSalary=" + slip.getNetSalary());
            System.out.println("month=" + slip.getMonth());
            System.out.println("year=" + slip.getYear());

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, slip.getBasicSalary());
            ps.setDouble(2, slip.getHra());
            ps.setDouble(3, slip.getAllowances());
            ps.setDouble(4, slip.getDeductions());
            ps.setDouble(5, slip.getNetSalary());
            ps.setString(6, slip.getMonth());
            ps.setString(7, slip.getYear());
            ps.setInt(8, slip.getId());

            int rows = ps.executeUpdate();
            System.out.println("Rows updated = " + rows);
            System.out.println("=== UPDATE DEBUG END ===");

            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    // 4️th Delete Salary Slip
    public boolean deleteSalarySlip(int id) {
        String sql = "DELETE FROM salary_slips WHERE id=?";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    
    
    
    
 // 5️th Get Salary Slips Only For One Employee (Employee Portal)
    public List<SalarySlipBean> getSlipsByUserId(int userId) {

        List<SalarySlipBean> list = new ArrayList<>();
        String sql = "SELECT * FROM salary_slips WHERE employee_id = ? ORDER BY id DESC";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();
            System.out.println("Querying salary_slips for employee_id = " + userId);


            while (rs.next()) {
                SalarySlipBean slip = new SalarySlipBean();

                slip.setId(rs.getInt("id"));
                slip.setEmployeeId(rs.getInt("employee_id"));
                slip.setBasicSalary(rs.getDouble("basic_salary"));
                slip.setHra(rs.getDouble("hra"));
                slip.setAllowances(rs.getDouble("allowances"));
                slip.setDeductions(rs.getDouble("deductions"));
                slip.setNetSalary(rs.getDouble("net_salary"));
                slip.setMonth(rs.getString("month"));
                slip.setYear(rs.getString("year"));

                list.add(slip);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
