/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package DoAn_QuanLyBanBanh.GUI;
import DoAn_QuanLyBanBanh.BUS.CategoryBUS;
import DoAn_QuanLyBanBanh.BUS.EmployeeBUS;
import DoAn_QuanLyBanBanh.BUS.ImportBUS;
import DoAn_QuanLyBanBanh.BUS.ImportDetailBUS;
import DoAn_QuanLyBanBanh.BUS.ManufactureBUS;
import DoAn_QuanLyBanBanh.BUS.ProductsBUS;
import DoAn_QuanLyBanBanh.DTO.CategoryDTO;
import DoAn_QuanLyBanBanh.DTO.EmployeeDTO;
import DoAn_QuanLyBanBanh.DTO.ImportDTO;
import DoAn_QuanLyBanBanh.DTO.ImportDetailDTO;
import DoAn_QuanLyBanBanh.DTO.ManufactureDTO;
import DoAn_QuanLyBanBanh.DTO.ProductsDTO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author nhu
 */
public class Purchasing_GUI extends javax.swing.JPanel {
    DefaultTableModel model1,model2,model3,model;
    ProductsBUS prBUS = new ProductsBUS();
    ManufactureBUS maBUS = new ManufactureBUS();
    ImportBUS imBUS = new ImportBUS();
    ImportDetailBUS imdeBUS = new ImportDetailBUS();
    CategoryBUS caBUS = new CategoryBUS();
    EmployeeBUS emBUS = new EmployeeBUS();
    ArrayList<EmployeeDTO> arrNV= emBUS.getEmployee();
    ArrayList<ManufactureDTO> arrNCC = maBUS.getManufacture();
    ArrayList<ProductsDTO> arrSP= prBUS.getProducts();
    /**
     * Creates new form Purchasing_GUI
     */
    public Purchasing_GUI() {
        initComponents();
        showTableKhohang();
        loadCmbNCC();
        loadcmbMaSP();
        loadMNV();
        loadTablePN();
        loadTableSPN();
        xulisukien();
        loadngay();
    }
    public void showTableKhohang(){
        model1 = new DefaultTableModel();
        model1.addColumn("Mã sản phẩm ");
        model1.addColumn("Tên sản phẩm");
        model1.addColumn("Số lượng còn");
        model1.addColumn("Giá bán");
        tableKhohang.setModel(model1);
        model= new DefaultTableModel();
        model.addColumn("Mã sản phẩm ");
        model.addColumn("Tên sản phẩm");
        model.addColumn("Số lượng nhập");
        model.addColumn("Giá nhập");
        model.addColumn("Thành tiền");
        tbSPnhap.setModel(model);
    }

    public void loadCmbNCC(){
        cmbNCC.addItem("Chọn nhà cung cấp");
        for (ManufactureDTO manufactureDTO : arrNCC) {
            cmbNCC.addItem(manufactureDTO.getMaNCC()+" - " +manufactureDTO.getTenNCC());
        }
    }
    public void loadcmbMaSP(){
        
        ArrayList<CategoryDTO> arr = caBUS.getCategory();
        cmbCategory.addItem("0 - Chọn mã loại");
        for (CategoryDTO loai :arr) {
                cmbCategory.addItem(loai.getMaLoai()+" - "+loai.getTenLoai());  
        }
    }
     private void  loadngay(){
          String ngay = loadDateTime();
          lbngay.setText(ngay);
      }
     public static String loadDateTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);
        return formattedDateTime;
    }
     private void xulisukien(){
         cmbCategory.addActionListener(new ActionListener(){
             @Override
             public void actionPerformed(ActionEvent e) {
                 loadDSSP();
             }
             
         });
     }
   private void loadDSSP(){
       model1.setRowCount(0);
        ArrayList<ProductsDTO> dssp = null;

        if (cmbCategory.getItemCount() > 0) {
            String loai = cmbCategory.getSelectedItem() + "";
            String loaiArr[] = loai.split("-");
            String loaiSP = loaiArr[0].trim();
            if (loaiSP.equals("0")) {
                dssp = prBUS.getProducts();
            } else {
                dssp = prBUS.getSanPhamTheoLoai(loaiSP);
            }
        }
        
        for (ProductsDTO productsDTO : dssp) {
            
                Object[] row= {productsDTO.getMaSP(),productsDTO.getTenSP(),productsDTO.getSoLuong(),productsDTO.getGia()};
            model1.addRow(row);
        }
        tableKhohang.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
        // Lấy chỉ mục của dòng được chọn
        int selectedRow = tableKhohang.getSelectedRow();

        // Truy xuất dữ liệu từ dòng được chọn
        int id = (int) tableKhohang.getValueAt(selectedRow, 0);
        String name = (String) tableKhohang.getValueAt(selectedRow, 1);
        int sluong= (int)tableKhohang.getValueAt(selectedRow, 2);
        int gia = (int) tableKhohang.getValueAt(selectedRow, 3);
        
        // Đặt dữ liệu lên các TextField
        lbelMasp.setText(String.valueOf(id));
        labeltenSP.setText(name);
        lbGiaBan.setText(String.valueOf(gia));
            }
        });
   }
    
    public void loadMNV(){
            
            lbNV.setText(String.valueOf(8));
        
    }
        public static String ngayToString(Date ngay) {
        // Tạo một đối tượng định dạng ngày tháng
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Chuyển đổi kiểu ngày thành chuỗi
        String ngayChuoi = sdf.format(ngay);

        return ngayChuoi;
    }
    public void loadTablePN(){
        model2 = new DefaultTableModel();
        model2.addColumn("Mã phiếu nhập");
        model2.addColumn("Ngày lập phiếu");
        model2.addColumn("Tổng tiền");
        tbdsPN.setModel(model2);
        ArrayList<ImportDTO> arr = imBUS.getImport();
        for (ImportDTO importDTO : arr) {
            Object[] row ={importDTO.getMaPN(),importDTO.getNgayLap(),importDTO.getTongTien()};
            model2.addRow(row);
        }
        tbdsPN.addMouseListener(new MouseAdapter(){
             @Override
            public void mouseClicked(MouseEvent e) {
        int selectedRow = tbdsPN.getSelectedRow();
         int id = (int) tbdsPN.getValueAt(selectedRow, 0);
        int total = (int)tbdsPN.getValueAt(selectedRow, 2);
        lbmapn.setText(String.valueOf(id));
        lbtongtien.setText(String.valueOf(total));  
        ArrayList<ImportDTO> ds = imBUS.getImporttheoma(id);
                 for (ImportDTO importDTO : ds) {
                     lbmnv.setText(String.valueOf(importDTO.getMaNV()));
                     lbmancc.setText(String.valueOf(importDTO.getMaNCC()));
                     lbngaynhap.setText(ngayToString(importDTO.getNgayLap()));
                 }
        model3 =new DefaultTableModel();
        model3.addColumn("Mã phiếu nhập");
        model3.addColumn("Mã sản phẩm");
        model3.addColumn("Số lượng nhập");
        model3.addColumn("Đơn giá");
        model3.addColumn("Thành tiền");
        tbctpn.setModel(model3);
        ArrayList<ImportDetailDTO> ctpn = imdeBUS.getImportDetailtheoma(id);
        for (ImportDetailDTO importDetailDTO : ctpn) {
            Object[] row ={importDetailDTO.getMaPN(),importDetailDTO.getMaSP(),importDetailDTO.getsLuong(),importDetailDTO.getPrice(),importDetailDTO.getThanhTien()};
            model3.addRow(row);
        }
            }
        
        });
        
        
    }
    public void loadTableSPN(){
        model3 =new DefaultTableModel();
        model3.addColumn("Mã phiếu nhập");
        model3.addColumn("Mã sản phẩm");
        model3.addColumn("Số lượng nhập");
        model3.addColumn("Đơn giá");
        model3.addColumn("Thành tiền");
        tbctpn.setModel(model3);
        ArrayList<ImportDetailDTO> arr = imdeBUS.getImportDetail();
        for (ImportDetailDTO importDetailDTO : arr) {
            Object[] row ={importDetailDTO.getMaPN(),importDetailDTO.getMaSP(),importDetailDTO.getsLuong(),importDetailDTO.getPrice(),importDetailDTO.getThanhTien()};
            model3.addRow(row);
        }
        tbctpn.addMouseListener(new MouseAdapter(){
              @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tbctpn.getSelectedRow();
                int masp = (int) tbctpn.getValueAt(selectedRow, 1);
                int sl = (int) tbctpn.getValueAt(selectedRow, 2);
                int gia= (int) tbctpn.getValueAt(selectedRow, 3);
                int tt = (int) tbctpn.getValueAt(selectedRow, 4);
                lbgianhap.setText(String.valueOf(gia));
                lbthanhtien.setText(String.valueOf(tt));
                lbslnhap.setText(String.valueOf(sl));
                ArrayList<ProductsDTO> dssp = prBUS.getSPtheoma(masp);
                  for (ProductsDTO productsDTO : dssp) {
                      lbtensp.setText(productsDTO.getTenSP());
                  }
            }
        });
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jTextFieldSLnhap = new javax.swing.JTextField();
        btNhaphang = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        TextFieldGiaNhap = new javax.swing.JTextField();
        cmbNCC = new javax.swing.JComboBox<>();
        lbngay = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        cmbCategory = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        lbelMasp = new javax.swing.JLabel();
        labeltenSP = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        lbGiaBan = new javax.swing.JLabel();
        lbNV = new javax.swing.JLabel();
        btthem = new javax.swing.JButton();
        btXOA = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableKhohang = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbSPnhap = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lbmapn = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lbmnv = new javax.swing.JLabel();
        lbngaynhap = new javax.swing.JLabel();
        lbmancc = new javax.swing.JLabel();
        lbtongtien = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbdsPN = new javax.swing.JTable();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbctpn = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lbgianhap = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lbtensp = new javax.swing.JLabel();
        lbthanhtien = new javax.swing.JLabel();
        lbslnhap = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        txttungay = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtdenngay = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Nhập hàng");
        jLabel1.setToolTipText("");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        btNhaphang.setText("Nhập hàng");
        btNhaphang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNhaphangActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel29.setText("Giá bán");

        jLabel30.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel30.setText("Giá nhập");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Mã nhà cung cấp");

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setText("Mã nhân viên");

        TextFieldGiaNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextFieldGiaNhapActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel32.setText("Loại sản phẩm");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel33.setText("Tên sản phẩm");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel34.setText("Mã sản phẩm");

        lbelMasp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        labeltenSP.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel35.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel35.setText("Số lượng nhập");

        lbGiaBan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lbNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btthem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn_QuanLyBanBanh/IMG/icon/them.png"))); // NOI18N
        btthem.setText("Thêm");
        btthem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btthemActionPerformed(evt);
            }
        });

        btXOA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn_QuanLyBanBanh/IMG/icon/xoa.png"))); // NOI18N
        btXOA.setText("Xóa");
        btXOA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btXOAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbngay, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel30)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(TextFieldGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel12)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                    .addComponent(jLabel34)
                                    .addGap(78, 78, 78)))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cmbNCC, 0, 187, Short.MAX_VALUE)
                                .addComponent(lbelMasp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(labeltenSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel31)
                                .addComponent(jLabel29))
                            .addGap(72, 78, Short.MAX_VALUE)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lbGiaBan, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lbNV, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel32)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btthem)
                                .addComponent(jLabel35))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldSLnhap, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel5Layout.createSequentialGroup()
                                    .addGap(46, 46, 46)
                                    .addComponent(btXOA)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btNhaphang))))))
                .addGap(0, 37, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lbngay, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel32))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel34)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(lbelMasp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel33)
                            .addComponent(labeltenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(TextFieldGiaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel30)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(lbNV, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbGiaBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jTextFieldSLnhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btNhaphang)
                    .addComponent(btthem)
                    .addComponent(btXOA))
                .addGap(23, 23, 23))
        );

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tableKhohang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tableKhohang);

        jScrollPane1.setViewportView(jScrollPane2);

        tbSPnhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tbSPnhap);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Kho hàng");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Danh sách nhập hàng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(174, 174, 174))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addGap(168, 168, 168)
                                    .addComponent(jLabel3))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(98, 98, 98)
                                .addComponent(jLabel4)))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jLabel4)
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(327, 327, 327))))
        );

        jTabbedPane1.addTab("Nhập hàng", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Mã phiếu nhập");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Ngày nhập");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Mã nhân viên");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Mã nhà cung cấp");

        lbmapn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Tổng tiền");

        lbmnv.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lbngaynhap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lbmancc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lbtongtien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(47, 47, 47))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(33, 33, 33)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbtongtien, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                    .addComponent(lbngaynhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbmancc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbmnv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbmapn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lbmapn, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbmnv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbmancc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbngaynhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lbtongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Phiếu nhập hàng");

        tbdsPN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(tbdsPN);

        jScrollPane4.setViewportView(jScrollPane5);

        tbctpn.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(tbctpn);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Thông tin phiếu nhập");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Số lượng nhập");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Tên sản phẩm");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Giá nhập");

        lbgianhap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Thành tiền");

        lbtensp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lbthanhtien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lbslnhap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                        .addComponent(lbthanhtien, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel11)
                            .addComponent(jLabel14))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbtensp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbgianhap, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(lbslnhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbtensp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbslnhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbgianhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(lbthanhtien, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Từ ngày:");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("đến ngày:");

        txtdenngay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdenngayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttungay, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtdenngay, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(txttungay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(txtdenngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addGap(174, 174, 174))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(jLabel18)))))
                .addGap(96, 96, 96)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                .addContainerGap(361, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Phiếu nhập hàng", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btNhaphangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNhaphangActionPerformed
        // TODO add your handling code here:
        String idNCC = cmbNCC.getSelectedItem()+"";
        String[] idTmp =idNCC.split(" - ");
        int maNCC = Integer.parseInt(idTmp[0]);
        int maNV = Integer.parseInt(lbNV.getText());
        ImportDTO pn = new ImportDTO();
        pn.setMaNCC(maNCC);
        pn.setMaNV(maNV);
        int total=0;
        ArrayList<Vector> dsNhapHang= new ArrayList<>();
        for(int i =0; i<tbSPnhap.getRowCount();i++){
            Vector spnhap=new Vector();
            spnhap.add(tbSPnhap.getValueAt(i, 0));
            spnhap.add(tbSPnhap.getValueAt(i, 1));
            spnhap.add(tbSPnhap.getValueAt(i, 2));
            spnhap.add(tbSPnhap.getValueAt(i, 3));
            spnhap.add(tbSPnhap.getValueAt(i, 4));
            total+=Integer.parseInt(tbSPnhap.getValueAt(i, 4)+"");
            dsNhapHang.add(spnhap);
        }
        pn.setTongTien(total);
        imBUS.nhaphang(pn);
        int maPN = imBUS.getMaPN();
        ImportDetailDTO ctpn = new ImportDetailDTO();
        ctpn.setMaPN(maPN);
        for (Vector spnhap : dsNhapHang) {
            int maSP = Integer.parseInt(spnhap.get(0)+"");
            int sl = Integer.parseInt(spnhap.get(2)+"");
            int dongia = Integer.parseInt(spnhap.get(3)+"");
            int thanhtien =Integer.parseInt(spnhap.get(4)+"");
            ctpn.setMaSP(maSP);
            ctpn.setsLuong(sl);
            ctpn.setPrice(dongia);
            ctpn.setThanhTien(thanhtien);
            imdeBUS.add(ctpn);
        }
        JOptionPane.showMessageDialog(null, "Nhập hàng thành công");

    }//GEN-LAST:event_btNhaphangActionPerformed

    private void TextFieldGiaNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextFieldGiaNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextFieldGiaNhapActionPerformed

    private void btthemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btthemActionPerformed
        // TODO add your handling code here:
        String id = lbelMasp.getText();
        String name = labeltenSP.getText();
        String price= TextFieldGiaNhap.getText();
        String slNhapText = jTextFieldSLnhap.getText();
        if (id.trim().isEmpty() || price.trim().isEmpty() || slNhapText.trim().isEmpty()) {
            // Nếu có, không thực hiện các thao tác tiếp theo và thoát khỏi phương thức
            return;
        }
        int slnhap = Integer.parseInt(slNhapText);
        lbelMasp.setText("");
        labeltenSP.setText("");
        jTextFieldSLnhap.setText("");
        TextFieldGiaNhap.setText("");
        lbGiaBan.setText("");
        

        if (id.trim().equalsIgnoreCase(""))
        return;
        int mSP = Integer.parseInt(id);
        for (int i = 0; i < tbSPnhap.getRowCount(); i++) {
            int maTbl = Integer.parseInt(tbSPnhap.getValueAt(i, 0) + "");
            if (maTbl == mSP) {
                int soLuongAdd = Integer.parseInt(tbSPnhap.getValueAt(i, 2) + "");
                soLuongAdd += slnhap;
                int donGiaSP = Integer.parseInt(price);
                tbSPnhap.setValueAt(soLuongAdd, i, 2);
                tbSPnhap.setValueAt((soLuongAdd * donGiaSP), i, 4);
                prBUS.capNhatSoLuongSP(mSP, +slnhap);
                prBUS.getProducts();
                loadDSSP();
                return;
            }
        }

        int dg =Integer.parseInt(price);
        int total= dg*slnhap;
        Object[] row={id,name,slnhap,price,total};
        prBUS.capNhatSoLuongSP(mSP,+slnhap);
        prBUS.getProducts();
        loadDSSP();
        model.addRow(row);
    }//GEN-LAST:event_btthemActionPerformed

    private void btXOAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btXOAActionPerformed
        // TODO add your handling code here:
        int selectRow = tbSPnhap.getSelectedRow();
        if(selectRow!=-1){
            int mSP= Integer.parseInt(tbSPnhap.getValueAt(selectRow, 0)+"") ;
            int sl = Integer.parseInt(tbSPnhap.getValueAt(selectRow, 2)+"");
            prBUS.capNhatSoLuongSP(mSP, -sl);
            loadDSSP();
            model.removeRow(selectRow);
        }
    }//GEN-LAST:event_btXOAActionPerformed

    private void txtdenngayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdenngayActionPerformed
        // TODO add your handling code here:
        timkiemPNtheongay(txttungay.getText(), txtdenngay.getText());
    }//GEN-LAST:event_txtdenngayActionPerformed
    private void timkiemPNtheongay(String tuNgay,String denNgay){
        ArrayList<ImportDTO> arr = imBUS.getImporttheongay(tuNgay, denNgay);
        model2 = new DefaultTableModel();
        model2.addColumn("Mã phiếu nhập");
        model2.addColumn("Ngày lập phiếu");
        model2.addColumn("Tổng tiền");
        tbdsPN.setModel(model2);
        for (ImportDTO importDTO : arr) {
            Object[] row ={importDTO.getMaPN(),importDTO.getNgayLap(),importDTO.getTongTien()};
            model2.addRow(row);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField TextFieldGiaNhap;
    private javax.swing.JButton btNhaphang;
    private javax.swing.JButton btXOA;
    private javax.swing.JButton btthem;
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JComboBox<String> cmbNCC;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextFieldSLnhap;
    private javax.swing.JLabel labeltenSP;
    private javax.swing.JLabel lbGiaBan;
    private javax.swing.JLabel lbNV;
    private javax.swing.JLabel lbelMasp;
    private javax.swing.JLabel lbgianhap;
    private javax.swing.JLabel lbmancc;
    private javax.swing.JLabel lbmapn;
    private javax.swing.JLabel lbmnv;
    private javax.swing.JLabel lbngay;
    private javax.swing.JLabel lbngaynhap;
    private javax.swing.JLabel lbslnhap;
    private javax.swing.JLabel lbtensp;
    private javax.swing.JLabel lbthanhtien;
    private javax.swing.JLabel lbtongtien;
    private javax.swing.JTable tableKhohang;
    private javax.swing.JTable tbSPnhap;
    private javax.swing.JTable tbctpn;
    private javax.swing.JTable tbdsPN;
    private javax.swing.JTextField txtdenngay;
    private javax.swing.JTextField txttungay;
    // End of variables declaration//GEN-END:variables
}
