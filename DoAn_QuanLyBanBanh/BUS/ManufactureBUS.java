/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAn_QuanLyBanBanh.BUS;

import DoAn_QuanLyBanBanh.DAO.ManufactureDAO;
import DoAn_QuanLyBanBanh.DTO.ManufactureDTO;
import java.util.ArrayList;

/**
 *
 * @author nhu
 */
public class ManufactureBUS {
    ManufactureDAO nccDAO= new ManufactureDAO();
    public ArrayList<ManufactureDTO> getManufacture(){
        return nccDAO.getManufacture();
    }
    public String add(ManufactureDTO m){
        if(nccDAO.addManufacture(m)){
            return "Thêm thành công !!!^-^";
        }
        return "Thêm thất bại !!";
    }
    public String delete(ManufactureDTO m){
        if(nccDAO.deleteManufacture(m)){
            return "Bạn có chắc muốn xóa?";
        }
        return "Xóa thất bại";
    }
    public String update(ManufactureDTO m){
        if(nccDAO.updateManufacture(m)){
            return "Sửa thành công !!!^-^";
        }
        return "Sửa thất bại !!";
    }
    public ArrayList<ManufactureDTO> Find(int id,String name){
        return nccDAO.search(id, name);
    }
}
