/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAn_QuanLyBanBanh.DAO;

import DoAn_QuanLyBanBanh.DB.JDBCUtil;
import DoAn_QuanLyBanBanh.DTO.OrderDetailDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author nhu
 */
public class OrderDetailDAO {
    public ArrayList<OrderDetailDTO> getOrderDetail(){
        ArrayList<OrderDetailDTO> arr = new ArrayList<>();
        Connection conn = null;
        try {
            conn = JDBCUtil.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM order_detail";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                OrderDetailDTO cthd = new OrderDetailDTO();
                cthd.setMaHD(rs.getInt(1));
                cthd.setMaSP(rs.getInt(2));
                cthd.setSoLuong(rs.getInt(3));
                cthd.setGiaSP(rs.getInt(4));
                cthd.setThanhTien(rs.getInt(5));
                arr.add(cthd);
            }
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return arr;
    }
     public ArrayList<OrderDetailDTO> getOrderDetailtheomaHD(int ma){
        
        Connection conn = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "SELECT * FROM order_detail WHERE order_id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, ma);
            ResultSet rs = st.executeQuery();
            ArrayList<OrderDetailDTO> arr = new ArrayList<>();
            while(rs.next()){
                OrderDetailDTO cthd = new OrderDetailDTO();
                cthd.setMaHD(rs.getInt(1));
                cthd.setMaSP(rs.getInt(2));
                cthd.setSoLuong(rs.getInt(3));
                cthd.setGiaSP(rs.getInt(4));
                cthd.setThanhTien(rs.getInt(5));
                arr.add(cthd);
            }
            return arr;
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return null;
    }
    public boolean add(OrderDetailDTO cthd){
        boolean kt = false;
        Connection conn= null;
        PreparedStatement st = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "INSERT INTO order_detail VALUES(?,?,?,?,?)";
            st=conn.prepareStatement(sql);
            st.setInt(1, cthd.getMaHD());
            st.setInt(2, cthd.getMaSP());
            st.setInt(3, cthd.getSoLuong());
            st.setInt(4, cthd.getGiaSP());
            st.setInt(5, cthd.getThanhTien());
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
