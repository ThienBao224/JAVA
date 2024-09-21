/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DoAn_QuanLyBanBanh.DTO;

/**
 *
 * @author ADMIN
 */
public class StatDTO {

    private int totalProducts;
    private double totalSales;
    private int totalCustomers;
    private int totalEmployees;

    // Constructor
    public StatDTO() {

    }

    public StatDTO(int totalProducts, double totalSales, int totalCustomers, int totalEmployees) {
        this.totalProducts = totalProducts;
        this.totalSales = totalSales;
        this.totalCustomers = totalCustomers;
        this.totalEmployees = totalEmployees;
    }

    // Getters
    public int getTotalProducts() {
        return totalProducts;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public int getTotalCustomers() {
        return totalCustomers;
    }

    public int getTotalEmployees() {
        return totalEmployees;
    }

    // Setters
    public void setTotalProducts(int totalProducts) {
        this.totalProducts = totalProducts;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

    public void setTotalCustomers(int totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    public void setTotalEmployees(int totalEmployees) {
        this.totalEmployees = totalEmployees;
    }
}
