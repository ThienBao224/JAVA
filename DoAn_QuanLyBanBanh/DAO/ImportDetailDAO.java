/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAn_QuanLyBanBanh.DAO;

import DoAn_QuanLyBanBanh.DB.JDBCUtil;
import DoAn_QuanLyBanBanh.DTO.ImportDetailDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author nhu
 */
public class ImportDetailDAO {
    public  ArrayList<ImportDetailDTO> getImportDetail(){
        ArrayList<ImportDetailDTO> arr = new ArrayList<>();
        Connection conn = null;
        try {
            conn = JDBCUtil.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM import_detail";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                ImportDetailDTO im = new ImportDetailDTO();
                im.setMaPN(rs.getInt(1));
                im.setMaSP(rs.getInt(2));
                im.setsLuong(rs.getInt(3));
                im.setPrice(rs.getInt(4));
                im.setThanhTien(rs.getInt(5));
                arr.add(im);
            }
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return arr;
    }
    public  ArrayList<ImportDetailDTO> getImportDetailtheoma(int ma){
        ArrayList<ImportDetailDTO> arr = new ArrayList<>();
        Connection conn = null;
        PreparedStatement st= null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM import_detail WHERE import_id=?";
            st=conn.prepareStatement(sql);
            st.setInt(1, ma);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                ImportDetailDTO im = new ImportDetailDTO();
                im.setMaPN(rs.getInt(1));
                im.setMaSP(rs.getInt(2));
                im.setsLuong(rs.getInt(3));
                im.setPrice(rs.getInt(4));
                im.setThanhTien(rs.getInt(5));
                arr.add(im);
            }
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return arr;
    }
    public boolean add(ImportDetailDTO imdet){
        boolean kt = false;
        Connection conn = null;
        PreparedStatement st2 = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql2= "INSERT INTO import_detail VALUES(?,?,?,?,?)";
            st2 = conn.prepareStatement(sql2);
            st2.setInt(1, imdet.getMaPN());
            st2.setInt(2, imdet.getMaSP());
            st2.setInt(3, imdet.getsLuong());
            st2.setInt(4, imdet.getPrice());
            st2.setInt(5, imdet.getThanhTien());
            if(st2.executeUpdate()>=1){
                kt=true;
            }
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return kt;
    }
}
