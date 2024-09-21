package DoAn_QuanLyBanBanh.DB;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nhu
 */
public class JDBCUtil {
     public static Connection getConnection(){
        Connection conn= null;
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            String url="jdbc:mysql://localhost:3306/cuahangbanbanh";
            String username = "root";
            String password ="";
            conn =DriverManager.getConnection(url, username, password);
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void closeConnection( Connection conn){
        try {
            if(conn!=null){
                conn.close();
                System.out.println("thanhcong");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void printInfo( Connection conn){
        try {
            if(conn!=null){
                DatabaseMetaData db = conn.getMetaData();
                System.out.println(db.getDatabaseProductName());
                System.out.println(db.getDatabaseProductVersion());
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Connection conn = JDBCUtil.getConnection();
        JDBCUtil.closeConnection(conn);
    }
    
}
