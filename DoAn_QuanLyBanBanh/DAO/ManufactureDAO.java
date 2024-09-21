/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAn_QuanLyBanBanh.DAO;

import DoAn_QuanLyBanBanh.DB.JDBCUtil;
import DoAn_QuanLyBanBanh.DTO.ManufactureDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author nhu
 */
public class ManufactureDAO {
    public ArrayList<ManufactureDTO> getManufacture(){
        ArrayList<ManufactureDTO> arr = new ArrayList<>();
        Connection conn = null;
        try {
            conn = JDBCUtil.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM manufacture";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                ManufactureDTO m = new ManufactureDTO();
                m.setMaNCC(rs.getInt(1));
                m.setTenNCC(rs.getString(2));
                m.setSdt(rs.getInt(3));
                m.setDiaChi(rs.getString(4));
                arr.add(m);
            }
            
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return arr;
    }
    public boolean addManufacture(ManufactureDTO m){
        boolean kt = false;
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn= JDBCUtil.getConnection();
            String sql= "INSERT INTO manufacture(name,phone,address) VALUES(?,?,?)";
            st = conn.prepareStatement(sql);
            st.setString(1, m.getTenNCC());
            st.setInt(2, m.getSdt());
            st.setString(3, m.getDiaChi());
            if(st.executeUpdate()>=1){
                kt= true;
            }
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return kt;
    }
    public boolean deleteManufacture(ManufactureDTO m){
        boolean kt = false;
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn= JDBCUtil.getConnection();
            String sql= "DELETE FROM manufacture WHERE id=?";
            st = conn.prepareStatement(sql);
            st.setInt(1, m.getMaNCC());
            if(st.executeUpdate()>=1){
                kt= true;
            }
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return kt;
    }
    public boolean updateManufacture(ManufactureDTO m){
        boolean kt = false;
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn= JDBCUtil.getConnection();
            String sql= "UPDATE manufacture SET name=?,phone=?,address=? WHERE id=?";
            st = conn.prepareStatement(sql);
            st.setString(1, m.getTenNCC());
            st.setInt(2, m.getSdt());
            st.setString(3, m.getDiaChi());
            st.setInt(4, m.getMaNCC());
            if(st.executeUpdate()>=1){
                kt= true;
            }
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return kt;
    }
     public ArrayList<ManufactureDTO> search(int id,String name){
        ManufactureDAO supplierDAO = new ManufactureDAO();
        ArrayList <ManufactureDTO> List = supplierDAO.getManufacture();
        ArrayList <ManufactureDTO> ListSearch = new ArrayList<>();
        for (ManufactureDTO supplier : List){
            if( supplier.getMaNCC()==id || name.equalsIgnoreCase(supplier.getTenNCC())){
                ListSearch.add(supplier);
            }
        }
        return ListSearch;
    }
}
