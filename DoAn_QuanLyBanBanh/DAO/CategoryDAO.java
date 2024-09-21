/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAn_QuanLyBanBanh.DAO;

import DoAn_QuanLyBanBanh.DB.JDBCUtil;
import DoAn_QuanLyBanBanh.DTO.CategoryDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author nhu
 */
public class CategoryDAO {
    public ArrayList<CategoryDTO> getCategory(){
        Connection conn = null;
        ArrayList<CategoryDTO> arr = new ArrayList<>();
        try {
            conn = JDBCUtil.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM category";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                CategoryDTO loai = new CategoryDTO();
                loai.setMaLoai(rs.getInt(1));
                loai.setTenLoai(rs.getString(2));
                arr.add(loai);
            }
            
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return arr;
    }
    public boolean addCategory(CategoryDTO loai){
        boolean kt = false;
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql ="INSERT INTO category(name) VALUES(?)";
            st = conn.prepareStatement(sql);
            st.setString(1, loai.getTenLoai());
            if(st.executeUpdate()>=1){
                kt = true;
            }
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return kt;
    }
    public boolean deleteCategory(CategoryDTO loai){
        boolean kt = false;
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql ="DELETE FROM category WHERE id=?";
            st = conn.prepareStatement(sql);
            st.setInt(1, loai.getMaLoai());
            if(st.executeUpdate()>=1){
                kt = true;
            }
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return kt;
    }
    public boolean updateCategory(CategoryDTO loai){
        boolean kt = false;
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql ="UPDATE category SET name=? WHERE id=?";
            st = conn.prepareStatement(sql);
            st.setString(1, loai.getTenLoai());
            st.setInt(2, loai.getMaLoai());
            if(st.executeUpdate()>=1){
                kt = true;
            }
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return kt;
    }
}
