/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAn_QuanLyBanBanh.DTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ADMIN
 */
public class StatMonthSaleDTO {
    private List<MonthYearSale> salesByMonthAndYear; // List to store sales data by month and year

    public StatMonthSaleDTO(List<MonthYearSale> salesByMonthAndYear) {
        this.salesByMonthAndYear = salesByMonthAndYear;
    }

    public List<MonthYearSale> getSalesByMonthAndYear() {
        return salesByMonthAndYear;
    }

    // Add more methods as needed
}
