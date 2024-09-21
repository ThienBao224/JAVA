/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAn_QuanLyBanBanh.DAO;

import DoAn_QuanLyBanBanh.DB.JDBCUtil;
import DoAn_QuanLyBanBanh.DTO.ImportDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author nhu
 */
public class ImportDAO {
    public  ArrayList<ImportDTO> getImport(){
        ArrayList<ImportDTO> arr = new ArrayList<>();
        Connection conn= null;
        try {
            conn = JDBCUtil.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM import";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                ImportDTO pn = new ImportDTO();
                pn.setMaPN(rs.getInt(1));
                pn.setMaNV(rs.getInt(2));
                pn.setMaNCC(rs.getInt(3));
                pn.setNgayLap(rs.getDate(4));
                pn.setTongTien(rs.getInt(5));
                arr.add(pn);
            }
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return arr;
    } 
    public  ArrayList<ImportDTO> getImporttheoma(int ma){
        ArrayList<ImportDTO> arr = new ArrayList<>();
        Connection conn= null;
        PreparedStatement st =null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM import WHERE id=?";
            st = conn.prepareStatement(sql);
            st.setInt(1, ma);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                ImportDTO pn = new ImportDTO();
                pn.setMaPN(rs.getInt(1));
                pn.setMaNV(rs.getInt(2));
                pn.setMaNCC(rs.getInt(3));
                pn.setNgayLap(rs.getDate(4));
                pn.setTongTien(rs.getInt(5));
                arr.add(pn);
            }
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return arr;
    } 
    public boolean update1(ImportDTO im){
        boolean kt = false;
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn=JDBCUtil.getConnection();
            String sql = "UPDATE import SET total=? WHERE id=?";
            st = conn.prepareStatement(sql);
            st.setInt(1, im.getTongTien());
            st.setInt(2, im.getMaPN());
            if(st.executeUpdate()>=1){
                kt = true;
            }
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return kt;
    }
    public boolean add(ImportDTO im) {
    boolean kt = false;
    Connection conn = null;
    PreparedStatement st = null;
    try {
        conn = JDBCUtil.getConnection();
           String sql = "INSERT INTO import (employee_id,manufacture_id,date,total) VALUES (?, ?,?,?)";
            st = conn.prepareStatement(sql);
            st.setInt(1, im.getMaNV());
            st.setInt(2, im.getMaNCC());
            st.setTimestamp(3, new java.sql.Timestamp(new java.util.Date().getTime()));
            st.setInt(4, im.getTongTien());
            if (st.executeUpdate() >= 1) {
                kt = true;
            }
        
    } catch (Exception e) {
    } finally {
        JDBCUtil.closeConnection(conn);
    }
    return kt;
}
    public int getMaHoaDonMoiNhat() {
        Connection conn = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT MAX(id) FROM import";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs.next())
                return rs.getInt(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
}
