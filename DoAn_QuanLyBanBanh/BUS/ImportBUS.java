/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAn_QuanLyBanBanh.BUS;

import DoAn_QuanLyBanBanh.DAO.ImportDAO;
import DoAn_QuanLyBanBanh.DB.JDBCUtil;
import DoAn_QuanLyBanBanh.DTO.ImportDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author nhu
 */
public class ImportBUS {
    ImportDAO imDAO = new ImportDAO();
    public ArrayList<ImportDTO> getImport(){
        return imDAO.getImport();
    }
    public ImportDTO nhaphang(ImportDTO im){
        if(imDAO.add(im)){
            return im;
        }
         return null;
    }
     public int getMaPN(){
         return imDAO.getMaHoaDonMoiNhat();
     }
     public ArrayList<ImportDTO> getImporttheoma(int ma){
        return imDAO.getImporttheoma(ma);
    }
    public ArrayList<ImportDTO> getImporttheongay(String tuNgay,String denNgay){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy");
            
            Date min = sdf.parse(tuNgay);
            Date max = sdf.parse(denNgay);
            if (max.before(min)) {
                JOptionPane.showMessageDialog(null, "Khoảng cách giữa các ngày chưa phù hợp (dd/MM/yyy)!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            ArrayList<ImportDTO> result = new ArrayList<>();
            for (ImportDTO pn : imDAO.getImport()) {
                if (pn.getNgayLap().after(min) && pn.getNgayLap().before(max)) {
                    result.add(pn);
                }
            }
            return result;
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, " ngày chưa phù hợp (dd/MM/yyy)!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }
     
}
