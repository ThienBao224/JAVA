/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAn_QuanLyBanBanh.BUS;

import DoAn_QuanLyBanBanh.DAO.CategoryDAO;
import DoAn_QuanLyBanBanh.DTO.CategoryDTO;
import java.util.ArrayList;

/**
 *
 * @author nhu
 */
public class CategoryBUS {
    CategoryDAO cate = new CategoryDAO();
     public ArrayList<CategoryDTO> getCategory(){
         return cate.getCategory();
     }
     public String getTenLoai(int ma) {
        for (CategoryDTO loai : cate.getCategory()) {
            if (loai.getMaLoai() == ma) {
                return loai.getMaLoai() + " - " + loai.getTenLoai();
            }
        }
        return "";
    }
     public String add(CategoryDTO loai){
        if(cate.addCategory(loai)){
            return "Thêm thành công!!";
        }
        return "Thêm thất bại";
     }
     public String dele(CategoryDTO loai){
        if(cate.deleteCategory(loai)){
            return "Xóa thành công!!";
        }
        return "Xóa thất bại";
     }
     public String update(CategoryDTO loai){
        if(cate.updateCategory(loai)){
            return "Sửa thành công!!";
        }
        return "Sửa thất bại";
     }
}
