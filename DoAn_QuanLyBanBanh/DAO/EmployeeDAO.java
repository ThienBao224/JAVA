/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAn_QuanLyBanBanh.DAO;

import DoAn_QuanLyBanBanh.DB.JDBCUtil;
import DoAn_QuanLyBanBanh.DTO.EmployeeDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nhu
 */
public class EmployeeDAO {
    public ArrayList<EmployeeDTO> getEmployee(){
        ArrayList<EmployeeDTO> arr = new ArrayList<>();
        Connection conn = JDBCUtil.getConnection();
        try {
            Statement st = conn.createStatement();
            String sql="SELECT * FROM employee";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                EmployeeDTO em = new EmployeeDTO();
                em.setId(rs.getInt(1));
                em.setLastname(rs.getString(2));
                em.setName(rs.getString(3));
                em.setGender(rs.getString(4));
                em.setPosition(rs.getString(5));
                arr.add(em);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return arr;
    }
    public boolean addEmployee(EmployeeDTO em){
        boolean kt = false;
        Connection conn = null;
        PreparedStatement st = null;
        
        try {
            conn= JDBCUtil.getConnection();
            String sql= "INSERT INTO employee(last_name,first_name,gender,position) VALUES(?,?,?,?)";
            st =conn.prepareStatement(sql);
            st.setString(1, em.getLastname());
            st.setString(2, em.getName());
            st.setString(3, em.getGender());
            st.setString(4, em.getPosition());
            if(st.executeUpdate()>=1){
                kt=true;
            }
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return kt;
    }
    public boolean deleteEmployee(EmployeeDTO em){
        boolean kt = false;
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn= JDBCUtil.getConnection();
            String sql = "DELETE FROM employee WHERE id=?";
            st = conn.prepareStatement(sql);
            st.setInt(1, em.getId());
            if(st.executeUpdate()>=1){
                kt=true;
            }
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return kt;
    } 
    public boolean updateEmployee(EmployeeDTO em){
        boolean kt = false;
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn= JDBCUtil.getConnection();
            String sql = "UPDATE employee SET last_name=?,first_name=?,gender=?,position=? WHERE id=? ";
            st = conn.prepareStatement(sql);
            st.setString(1, em.getLastname());
            st.setString(2, em.getName());
            st.setString(3, em.getGender());
            st.setString(4, em.getPosition());
            st.setInt(5, em.getId());
            if(st.executeUpdate()>=1){
                kt=true;
            }  
        } catch (Exception e) {
        } finally {
             JDBCUtil.closeConnection(conn);
        }
        return kt;
    }
     public EmployeeDTO find(String id) {
        PreparedStatement stmt = null;
        ResultSet rs = null; 
        Connection conn = null;
        try {
             conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM employee WHERE id = ?");
            stmt.setString(1, id);

            rs = stmt.executeQuery();

            while (rs.next()) {
                EmployeeDTO e = new EmployeeDTO();
                e.setName(rs.getString("name"));
                return e;
            }

        } catch (SQLException e) {
            return null;
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return null;
    }
     public List<EmployeeDTO> findByName(String name) {
        List<EmployeeDTO> employees = new ArrayList();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            // Prepare the SQL statement with a name filter
            stmt = conn.prepareStatement("SELECT * FROM employee WHERE first_name LIKE ?");
            // Set the name parameter in the query, adding wildcards for partial matching
            stmt.setString(1, "%" + name + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                EmployeeDTO e = new EmployeeDTO();
                e.setId(rs.getInt("id"));
                e.setName(rs.getString("first_name"));
                e.setLastname(rs.getString("last_name"));
                e.setGender(rs.getString("gender"));
                e.setPosition(rs.getString("position"));
                employees.add(e);
            }
        } catch (SQLException e) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return employees;
    }
}
