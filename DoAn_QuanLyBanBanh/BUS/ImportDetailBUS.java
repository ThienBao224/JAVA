/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAn_QuanLyBanBanh.BUS;

import DoAn_QuanLyBanBanh.DAO.ImportDetailDAO;
import DoAn_QuanLyBanBanh.DTO.ImportDetailDTO;
import java.util.ArrayList;

/**
 *
 * @author nhu
 */
public class ImportDetailBUS {
    ImportDetailDAO imdeDAO = new ImportDetailDAO();
    public ArrayList<ImportDetailDTO> getImportDetail(){
        return imdeDAO.getImportDetail();
    }
 
    public ArrayList<ImportDetailDTO> getImportDetailtheoma(int ma){
        return imdeDAO.getImportDetailtheoma(ma);
    }
    public ImportDetailDTO add(ImportDetailDTO im){
        if(imdeDAO.add(im)){
           return im; 
        }
        return null;
        
    }
   
}
