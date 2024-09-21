/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAn_QuanLyBanBanh.BUS;

import DoAn_QuanLyBanBanh.DAO.OrderDAO;
import DoAn_QuanLyBanBanh.DTO.OrderDTO;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author nhu
 */
public class OrderBUS {
    OrderDAO hdDAO =new OrderDAO();
    public ArrayList<OrderDTO> getOrder(){
        return hdDAO.getOrder();
    }
    public OrderDTO addOrder(OrderDTO hd){
        if(hdDAO.addOrder(hd)){
            return hd;
        }
        return null;
    }
    public int getMaHD(){
        return hdDAO.getMaHoaDonMoiNhat();
    }
    public ArrayList<OrderDTO> getOrdertheoma(int ma){
        return hdDAO.getOrdertheoma(ma);
    }
    public ArrayList<OrderDTO> getOrdertheongay(String tuNgay,String denNgay){
         try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
            
            Date min = sdf.parse(tuNgay);
            Date max = sdf.parse(denNgay);
            if (max.before(min)) {
                JOptionPane.showMessageDialog(null, "Khoảng cách giữa các ngày chưa phù hợp (dd/MM/yyy)!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            ArrayList<OrderDTO> result = new ArrayList<>();
            for (OrderDTO hd : hdDAO.getOrder()) {
                if (hd.getNgayTaoHD().after(min) &&hd.getNgayTaoHD().before(max)) {
                    result.add(hd);
                }
            }
            return result;
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, " ngày chưa phù hợp (dd/MM/yyy)!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
}
