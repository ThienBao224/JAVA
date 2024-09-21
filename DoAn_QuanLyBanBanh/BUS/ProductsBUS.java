/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAn_QuanLyBanBanh.BUS;

import DoAn_QuanLyBanBanh.DAO.ProductsDAO;
import DoAn_QuanLyBanBanh.DTO.ProductsDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author nhu
 */
public class ProductsBUS {
    
    ProductsDAO prDAO = new ProductsDAO();
    public ArrayList<ProductsDTO> getProducts(){
        return prDAO.getProducts();
    }
    public String add(ProductsDTO pr){
        if(prDAO.addPrducts(pr)){
            return "Thêm thành công!!";
           
        }
        return "Thêm thất bại!!";
    }
    public String delete(ProductsDTO pr){
        if(prDAO.deleteProducts(pr)){
            return "Bạn có muốn xóa?";
           
        }
        return "Xóa thất bại!!";
    }
    public String update(ProductsDTO pr){
        if(prDAO.updateProducts(pr)){
            return "Sửa thành công!!";
           
        }
        return "Sửa thất bại!!";
    }
    public void capNhatSoLuongSP(int ma, int soLuongMat) {
        prDAO.capNhatSoLuongSP(ma, soLuongMat);
    }
     public ArrayList<ProductsDTO> getSanPhamTheoLoai(String ma) {
        if (!ma.trim().equals("")) {
            ArrayList<ProductsDTO> dssp = new ArrayList<>();
            try {
                int maLoai = Integer.parseInt(ma);
                for (ProductsDTO sp : prDAO.getProducts()) {
                    if (sp.getMaLoai() == maLoai) {
                        dssp.add(sp);
                    }
                }
                return dssp;
            } catch (Exception e) {
            }
        }
        return null;
    }
      public List<ProductsDTO> findByName(String name) {
        List<ProductsDTO> employees = prDAO.findByName(name);
        return employees;
    }
      public  ArrayList<ProductsDTO> getSPtheoma(int ma){
          return prDAO.getSanPhamTheoLoai(ma);
      }
    
}
