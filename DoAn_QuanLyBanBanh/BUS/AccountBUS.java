/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAn_QuanLyBanBanh.BUS;

import DoAn_QuanLyBanBanh.DAO.AccountDAO;
import DoAn_QuanLyBanBanh.DTO.AccountDTO;
import java.util.ArrayList;

/**
 *
 * @author nhu
 */
public class AccountBUS {
    AccountDAO accDAO = new AccountDAO();
    public ArrayList<AccountDTO> getAccount(){
        return accDAO.getAccount();
    }
}
