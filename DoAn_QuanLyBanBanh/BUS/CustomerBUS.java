/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAn_QuanLyBanBanh.BUS;

import DoAn_QuanLyBanBanh.DAO.CustomerDAO;
import DoAn_QuanLyBanBanh.DTO.CustomerDTO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author nhu
 */
public class CustomerBUS {
  CustomerDAO khDAO = new CustomerDAO();
  public ArrayList<CustomerDTO> getCustomer(){
      return khDAO.getCustomer();
  }
  public boolean addCustomer(String lastname, String name, String gender,int phone){
         if(lastname.trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Không được để trống họ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
         }
         if(name.trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Không được để trống tên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
         }
         if(gender.trim().equals("Chọn giới tính")){
            JOptionPane.showMessageDialog(null, "Hãy chọn giới tính!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
         }
        
         CustomerDTO em = new CustomerDTO();
         em.setLastname(lastname);
         em.setName(name);
         em.setGender(gender);
         em.setSdt(phone);
         boolean flag = khDAO.addCustomer(em);
         if(flag){
             JOptionPane.showMessageDialog(null, "Thêm thành công!!!");
         }else{
             JOptionPane.showMessageDialog(null, "Thêm thất bại!!!");
         }
         return flag;
         
     }
     public String delet(CustomerDTO em){
         if(khDAO.deletCustomer(em)){
             return "Bạn có chắc muốn xóa?";
         }
         return "Xóa thất bại!!!";
     }
     
     
     public boolean updateCustomer(int id,String lastname, String name, String gender,int phone){
         if(lastname.trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Không được để trống họ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
         }
         if(name.trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Không được để trống tên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
         }
         if(gender.trim().equals("Chọn giới tính")){
            JOptionPane.showMessageDialog(null, "Hãy chọn giới tính!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
         }
         CustomerDTO em = new CustomerDTO();
         em.setId(id);
         em.setLastname(lastname);
         em.setName(name);
         em.setGender(gender);
         em.setSdt(phone);
         boolean flag = khDAO.updateCustomer(em);
         if(flag){
             JOptionPane.showMessageDialog(null, "Sửa thành công!!!");
         }else{
             JOptionPane.showMessageDialog(null, "Sửa thất bại!!!");
         }
         return flag;
         
     }
     
    }
