/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAn_QuanLyBanBanh.BUS;

import DoAn_QuanLyBanBanh.DAO.EmployeeDAO;
import DoAn_QuanLyBanBanh.DTO.EmployeeDTO;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author nhu
 */
public class EmployeeBUS {
    EmployeeDAO emDAO = new EmployeeDAO();
     public ArrayList<EmployeeDTO> getEmployee(){
         return emDAO.getEmployee();
     }
      public EmployeeDTO getEmployee(String id) {
        EmployeeDTO e = emDAO.find(id);
        return e;
    }
      public List<EmployeeDTO> findByName(String name) {
        List<EmployeeDTO> employees = emDAO.findByName(name);
        return employees;
    }
     public boolean addEmployee(String lastname, String name, String gender,String position){
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
         if(position.trim().equals("Chọn chức vụ")){
            JOptionPane.showMessageDialog(null, "Hãy chọn chức vụ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
         }
         EmployeeDTO em = new EmployeeDTO();
         em.setLastname(lastname);
         em.setName(name);
         em.setGender(gender);
         em.setPosition(position);
         boolean flag = emDAO.addEmployee(em);
         if(flag){
             JOptionPane.showMessageDialog(null, "Thêm thành công!!!");
         }else{
             JOptionPane.showMessageDialog(null, "Thêm thất bại!!!");
         }
         return flag;
         
     }
     public String delet(EmployeeDTO em){
         if(emDAO.deleteEmployee(em)){
             return "Bạn có chắc muốn xóa?";
         }
         return "Xóa thất bại!!!";
     }
     
     public boolean updateEmployee(int id,String lastname, String name, String gender,String position){
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
         if(position.trim().equals("Chọn chức vụ")){
            JOptionPane.showMessageDialog(null, "Hãy chọn chức vụ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
         }
         EmployeeDTO em = new EmployeeDTO();
         em.setId(id);
         em.setLastname(lastname);
         em.setName(name);
         em.setGender(gender);
         em.setPosition(position);
         boolean flag = emDAO.updateEmployee(em);
         if(flag){
             JOptionPane.showMessageDialog(null, "Sửa thành công!!!");
         }else{
             JOptionPane.showMessageDialog(null, "Sửa thất bại!!!");
         }
         return flag;
         
     }
}
