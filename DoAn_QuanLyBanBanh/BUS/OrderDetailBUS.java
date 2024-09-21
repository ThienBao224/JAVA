/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAn_QuanLyBanBanh.BUS;

import DoAn_QuanLyBanBanh.DAO.OrderDetailDAO;
import DoAn_QuanLyBanBanh.DTO.OrderDetailDTO;
import java.util.ArrayList;

/**
 *
 * @author nhu
 */
public class OrderDetailBUS {
    OrderDetailDAO cthdDAO = new OrderDetailDAO();
    public ArrayList<OrderDetailDTO> getOrderDetail(){
        return cthdDAO.getOrderDetail();
    }
    public OrderDetailDTO add(OrderDetailDTO cthd){
        if(cthdDAO.add(cthd)){
            return cthd;
        }
        return null;
    }
    public ArrayList<OrderDetailDTO> getOrderDetailtheomaHD(int ma){
        return cthdDAO.getOrderDetailtheomaHD(ma);
    }
}
