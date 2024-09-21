/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAn_QuanLyBanBanh.DAO;

import DoAn_QuanLyBanBanh.DB.JDBCUtil;
import DoAn_QuanLyBanBanh.DTO.MonthYearSale;
import DoAn_QuanLyBanBanh.DTO.StatDTO;
import DoAn_QuanLyBanBanh.DTO.StatMonthSaleDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
public class StatDAO {

    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public StatDTO getStats() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        StatDTO statDTO = new StatDTO();

        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT "
                    + "(SELECT COUNT(*) FROM product) AS total_products, "
                    + "(SELECT SUM(total_price) FROM `order`) AS total_sales, "
                    + "(SELECT COUNT(*) FROM customer) AS total_customers, "
                    + "(SELECT COUNT(*) FROM employee) AS total_employees";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            // Set the statistics in StatDTO object
            if (rs.next()) {
                statDTO.setTotalProducts(rs.getInt("total_products"));
                statDTO.setTotalSales(rs.getDouble("total_sales"));
                statDTO.setTotalCustomers(rs.getInt("total_customers"));
                statDTO.setTotalEmployees(rs.getInt("total_employees"));
            }

        } catch (SQLException e) {
            // Handle any SQL exceptions here
            e.printStackTrace();
        } finally {
            // Close resources
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return statDTO;
    }

    public StatMonthSaleDTO getSalesByMonthAndYear() throws SQLException {
        List<MonthYearSale> monthYearSales = new ArrayList<>();

        // Execute query to retrieve sales data for each month and year
        String sql = "SELECT YEAR(created_at) AS year, MONTH(created_at) AS month, SUM(total_price) AS total_sales "
                + "FROM `order` "
                + "GROUP BY YEAR(created_at), MONTH(created_at)";
        try {
            Connection conn = JDBCUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Process the result set to populate monthYearSales list
            while (rs.next()) {
                int year = rs.getInt("year");
                int month = rs.getInt("month");
                double totalSales = rs.getDouble("total_sales");
                monthYearSales.add(new MonthYearSale(year, month, totalSales));
            }
        } catch (SQLException e) {
            Logger.getLogger(ImportDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            JDBCUtil.closeConnection(conn);
        }

        return new StatMonthSaleDTO(monthYearSales);
    }
}
