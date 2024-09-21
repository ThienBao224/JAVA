/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAn_QuanLyBanBanh.DAO;

import DoAn_QuanLyBanBanh.DB.JDBCUtil;
import DoAn_QuanLyBanBanh.DTO.OrderDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author nhu
 */
public class OrderDAO {
    public ArrayList<OrderDTO> getOrder(){
        ArrayList<OrderDTO> arr = new ArrayList<>();
        Connection conn = null;
        try {
            conn = JDBCUtil.getConnection();
            Statement st = conn.createStatement();
            String sql= "SELECT * FROM `order` ";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                OrderDTO hd = new OrderDTO();
                hd.setMaHD(rs.getInt(1));
                hd.setMaKH(rs.getInt(2));
                hd.setMaNV(rs.getInt(3));
                hd.setTongTien(rs.getInt(4));
                hd.setNgayTaoHD(rs.getDate(5));
                arr.add(hd);
            }
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return arr;
        
    }
    public ArrayList<OrderDTO> getOrdertheoma(int ma){
        ArrayList<OrderDTO> arr = new ArrayList<>();
        Connection conn = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql= "SELECT * FROM `order` WHERE id=?";
            PreparedStatement st =conn.prepareStatement(sql);
            st.setInt(1, ma);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                OrderDTO hd = new OrderDTO();
                hd.setMaHD(rs.getInt(1));
                hd.setMaKH(rs.getInt(2));
                hd.setMaNV(rs.getInt(3));
                hd.setTongTien(rs.getInt(4));
                hd.setNgayTaoHD(rs.getDate(5));
                arr.add(hd);
            }
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return arr;
        
    }
    public  boolean addOrder(OrderDTO hd){
        boolean kt = false;
        PreparedStatement st = null;
        Connection conn = null;
        try {
            conn= JDBCUtil.getConnection();
            String sql = "INSERT INTO `order`(`customer_id`,`employee_id`,`total_price`,`created_at`) VALUES(?,?,?,?) ";
            st = conn.prepareStatement(sql);
            st.setInt(1, hd.getMaKH());
            st.setInt(2, hd.getMaNV());
            st.setInt(3, hd.getTongTien());
            st.setTimestamp(4,  new java.sql.Timestamp(new java.util.Date().getTime()));
            if(st.executeUpdate()>=1){
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
            String sql = "SELECT MAX(id) FROM `order`";
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
