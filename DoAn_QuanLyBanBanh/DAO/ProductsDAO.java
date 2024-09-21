/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAn_QuanLyBanBanh.DAO;

import DoAn_QuanLyBanBanh.DB.JDBCUtil;
import DoAn_QuanLyBanBanh.DTO.ProductsDTO;
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
public class ProductsDAO {
    public ArrayList<ProductsDTO> getProducts(){
        ArrayList<ProductsDTO> arr = new ArrayList<>();
        Connection conn = null;
        try {
            conn= JDBCUtil.getConnection();
            Statement st = conn.createStatement();
            String sql = "SELECT * FROM product";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                ProductsDTO pr = new ProductsDTO();
                pr.setMaSP(rs.getInt(1));
                pr.setTenSP(rs.getString(2));
                pr.setMaLoai(rs.getInt(3));
                pr.setGia(rs.getInt(4));
                pr.setHinh(rs.getString(5));
                pr.setSoLuong(rs.getInt(6));
                arr.add(pr);
            }
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return arr;
    }
    public ArrayList<ProductsDTO> getSanPhamTheoLoai(int loai){
        
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn= JDBCUtil.getConnection();
            
            String sql = "SELECT * FROM product WHERE id=?";
            st= conn.prepareStatement(sql);
            st.setInt(1, loai);
            ResultSet rs = st.executeQuery();
            ArrayList<ProductsDTO> arr = new ArrayList<>();
            while (rs.next()) {
                ProductsDTO pr = new ProductsDTO();
                pr.setMaSP(rs.getInt(1));
                pr.setTenSP(rs.getString(2));
                pr.setMaLoai(rs.getInt(3));
                pr.setGia(rs.getInt(4));
                pr.setHinh(rs.getString(5));
                pr.setSoLuong(rs.getInt(6));
                arr.add(pr);
            }
            return arr;
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return null;
    }
    
    public boolean addPrducts(ProductsDTO pr){
        boolean kt = false;
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql ="INSERT INTO product(name,type_id,price,img,quantity) VALUES(?,?,?,?,?)";
            st=conn.prepareStatement(sql);
            st.setString(1, pr.getTenSP());
            st.setInt(2, pr.getMaLoai());
            st.setInt(3, pr.getGia());
            st.setString(4, pr.getHinh());
            st.setInt(5, pr.getSoLuong());
            if(st.executeUpdate()>=1){
                kt=true;
            }
            
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return kt;
        
    }
    public boolean deleteProducts(ProductsDTO pr){
        boolean kt =  false;
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "DELETE FROM product WHERE id=?";
            st= conn.prepareStatement(sql);
            st.setInt(1, pr.getMaSP());
            if(st.executeUpdate()>=1){
                kt= true;
            }
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return kt;
    }
    public ProductsDTO getSanPhamLoai(int loai){
        
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn= JDBCUtil.getConnection();
            
            String sql = "SELECT * FROM product WHERE id=?";
            st= conn.prepareStatement(sql);
            st.setInt(1, loai);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ProductsDTO pr = new ProductsDTO();
                pr.setMaSP(rs.getInt(1));
                pr.setTenSP(rs.getString(2));
                pr.setMaLoai(rs.getInt(3));
                pr.setGia(rs.getInt(4));
                pr.setHinh(rs.getString(5));
                pr.setSoLuong(rs.getInt(6));
                return pr;
            }
            
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return null;
    }
    public void capNhatSoLuongSP(int ma, int soLuongMat) {
        Connection conn = null;
        ProductsDTO sp = getSanPhamLoai(ma);
        int soLuong = sp.getSoLuong();
        sp.setSoLuong(soLuong + soLuongMat);
        try {
            conn =JDBCUtil.getConnection();
            String sql = "UPDATE product SET quantity=? WHERE id=" + ma;
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, sp.getSoLuong());
            pre.executeUpdate();
        } catch (SQLException e) {
        }

    }
    public boolean updateProducts(ProductsDTO pr){
        boolean kt =  false;
        Connection conn = null;
        PreparedStatement st = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "UPDATE product SET name=?,type_id=?,price=?,img=?,quantity=? WHERE id=?";
            st= conn.prepareStatement(sql);
            st.setString(1, pr.getTenSP());
            st.setInt(2, pr.getMaLoai());
            st.setInt(3, pr.getGia());
            st.setString(4, pr.getHinh());
            st.setInt(5, pr.getSoLuong());
            st.setInt(6, pr.getMaSP());
            if(st.executeUpdate()>=1){
                kt= true;
            }
        } catch (Exception e) {
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return kt;
    }
    public List<ProductsDTO> findByName(String name) {
        List<ProductsDTO> products = new ArrayList();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM product WHERE name LIKE ?");
            stmt.setString(1, "%" + name + "%");
            rs = stmt.executeQuery();

            while (rs.next()) {
                ProductsDTO pr = new ProductsDTO();
                pr.setMaSP(rs.getInt(1));
                pr.setTenSP(rs.getString(2));
                pr.setMaLoai(rs.getInt(3));
                pr.setGia(rs.getInt(4));
                pr.setHinh(rs.getString(5));
                pr.setSoLuong(rs.getInt(6));
                products.add(pr);
            }
        } catch (SQLException e) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return products;
    }  
}
