/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAn_QuanLyBanBanh.DAO;

import DoAn_QuanLyBanBanh.DB.JDBCUtil;
import DoAn_QuanLyBanBanh.DTO.CustomerDTO;
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
public class CustomerDAO {
    public ArrayList<CustomerDTO> getCustomer(){
        ArrayList<CustomerDTO> arr = new ArrayList<>();
        Connection conn = null;
        try {
            conn= JDBCUtil.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM customer";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                CustomerDTO kh = new CustomerDTO();
                kh.setId(rs.getInt(1));
                kh.setLastname(rs.getString(2));
                kh.setName(rs.getString(3));
                kh.setSdt(rs.getInt(4));
                kh.setGender(rs.getString(5));
                arr.add(kh);
            }
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return arr;
    }
    public boolean addCustomer(CustomerDTO kh){
        boolean kt = false;
        Connection conn = null;
        PreparedStatement st = null;
        
        try {
            conn= JDBCUtil.getConnection();
            String sql= "INSERT INTO customer(last_name,first_name,phone,gender) VALUES(?,?,?,?)";
            st =conn.prepareStatement(sql);
            st.setString(1, kh.getLastname());
            st.setString(2, kh.getName());
            st.setInt(3, kh.getSdt());
            st.setString(4, kh.getGender()); 
            if(st.executeUpdate()>=1){
                kt=true;
            }
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return kt;
    }
    public boolean deletCustomer(CustomerDTO kh){
        boolean kt = false;
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn= JDBCUtil.getConnection();
            String sql = "DELETE FROM customer WHERE id=?";
            st = conn.prepareStatement(sql);
            st.setInt(1, kh.getId());
            if(st.executeUpdate()>=1){
                kt=true;
            }
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return kt;
    } 
    public boolean updateCustomer(CustomerDTO kh){
        boolean kt = false;
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn= JDBCUtil.getConnection();
            String sql = "UPDATE customer SET last_name=?,first_name=?,phone=?,gender=? WHERE id=? ";
            st = conn.prepareStatement(sql);
            st.setString(1, kh.getLastname());
            st.setString(2, kh.getName());
            st.setString(3, kh.getGender());
            st.setInt(4, kh.getSdt());
            st.setInt(5, kh.getId());
            if(st.executeUpdate()>=1){
                kt=true;
            }  
        } catch (Exception e) {
        } finally {
             JDBCUtil.closeConnection(conn);
        }
        return kt;
    }
    public List<CustomerDTO> findByName(String name) {
        List<CustomerDTO> customers = new ArrayList();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM customer WHERE first_name = ?");
            stmt.setString(1, "%" + name + "%");
            rs = stmt.executeQuery();
            while (rs.next()) {
                CustomerDTO c = new CustomerDTO();
                c.setId(rs.getInt(1));
                c.setLastname(rs.getString(2));
                c.setName(rs.getString(3));
                c.setSdt(rs.getInt(4));
                c.setGender(rs.getString(5));
                customers.add(c);
            }
        } catch (SQLException e) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return customers;
    }

    
}
