/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAn_QuanLyBanBanh.DAO;

import DoAn_QuanLyBanBanh.DB.JDBCUtil;
import DoAn_QuanLyBanBanh.DTO.AccountDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author nhu
 */
public class AccountDAO {
    public ArrayList<AccountDTO> getAccount(){
        ArrayList<AccountDTO> arr = new ArrayList<>();
        Connection conn = JDBCUtil.getConnection();
        try {
            Statement st = conn.createStatement();
            String sql="SELECT * FROM account";
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                AccountDTO acc = new AccountDTO();
                acc.setUsername(rs.getString(1));
                acc.setPass(rs.getInt(2));
                arr.add(acc);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeConnection(conn);
        }
        return arr;
    }
}
