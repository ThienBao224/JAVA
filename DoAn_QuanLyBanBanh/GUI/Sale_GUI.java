/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package DoAn_QuanLyBanBanh.GUI;
import DoAn_QuanLyBanBanh.BUS.CategoryBUS;
import DoAn_QuanLyBanBanh.BUS.CustomerBUS;
import DoAn_QuanLyBanBanh.BUS.EmployeeBUS;
import DoAn_QuanLyBanBanh.BUS.OrderBUS;
import DoAn_QuanLyBanBanh.BUS.OrderDetailBUS;
import DoAn_QuanLyBanBanh.BUS.ProductsBUS;
import DoAn_QuanLyBanBanh.DTO.CategoryDTO;
import DoAn_QuanLyBanBanh.DTO.CustomerDTO;
import DoAn_QuanLyBanBanh.DTO.EmployeeDTO;
import DoAn_QuanLyBanBanh.DTO.OrderDTO;
import DoAn_QuanLyBanBanh.DTO.OrderDetailDTO;
import DoAn_QuanLyBanBanh.DTO.ProductsDTO;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
/**
 *
 * @author nhu
 */
public class Sale_GUI extends javax.swing.JPanel {
CategoryBUS caBUS = new CategoryBUS();
ProductsBUS prBUS = new ProductsBUS();
DefaultTableModel model1,model2,model3,model4;
CustomerBUS khBUS = new CustomerBUS();
EmployeeBUS nvBUS = new EmployeeBUS();
OrderBUS orBUS = new OrderBUS();
OrderDetailBUS orDeBus = new OrderDetailBUS();
    /**
     * Creates new form Sale_GUI
     */
    public Sale_GUI() {
        initComponents();
        tbCTHD.getTableHeader().setBackground(new Color(255,153,0));
        tbHD.getTableHeader().setBackground(new Color(255,153,0));
        tbDSSP.getTableHeader().setBackground(new Color(255,153,0));
        tbSPmua.getTableHeader().setBackground(new Color(255,153,0));
        CmbCategory();
        nutLoai();
        loadngay();
        showTableHD();
        showTablelCTHD();
        model1 = new DefaultTableModel();
        model1.addColumn("Mã sản phẩm ");
        model1.addColumn("Tên sản phẩm");
        model1.addColumn("Số lượng còn");
        model1.addColumn("Đơn giá");
        model1.addColumn("Hình ảnh");
        tbDSSP.setModel(model1);
       model2 = new DefaultTableModel();
        model2.addColumn("Mã sản phẩm");
        model2.addColumn("Tên sản phẩm");
        model2.addColumn("Số lượng");
        model2.addColumn("Đơn giá");
        model2.addColumn("Thành tiền");
        tbSPmua.setModel(model2);
    }
    private void CmbCategory(){
        ArrayList<CategoryDTO> arrLoai = caBUS.getCategory();
        cmbCategory.addItem("0 - Chọn loại");
        for (CategoryDTO categoryDTO : arrLoai) {
            cmbCategory.addItem(categoryDTO.getMaLoai()+" - "+categoryDTO.getTenLoai());
        }
        ArrayList<CustomerDTO> arr =khBUS.getCustomer();
          cmbMaKH.addItem("0 - Chọn Khách hàng");
          for (CustomerDTO customerDTO : arr) {
              cmbMaKH.addItem(customerDTO.getId()+" - "+customerDTO.getName());
          }
        ArrayList<EmployeeDTO> arrNV = nvBUS.getEmployee();
        cmbMaNV.addItem("0 - Chọn nhân viên");
          for (EmployeeDTO employeeDTO : arrNV) {
              cmbMaNV.addItem(employeeDTO.getId() +" - "+employeeDTO.getName());
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
    
         private void loadDataTableSanPhamBan() {
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
            if(productsDTO.getSoLuong()>0){
                Object[] row= {productsDTO.getMaSP(),productsDTO.getTenSP(),productsDTO.getSoLuong(),productsDTO.getGia(),productsDTO.getHinh()};
            model1.addRow(row);
            }
        }
        tbDSSP.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
        // Lấy chỉ mục của dòng được chọn
        int selectedRow = tbDSSP.getSelectedRow();

        // Truy xuất dữ liệu từ dòng được chọn
        int id = (int) tbDSSP.getValueAt(selectedRow, 0);
        String name = (String) tbDSSP.getValueAt(selectedRow, 1);
        int sluong= (int) tbDSSP.getValueAt(selectedRow, 2);
        int gia = (int) tbDSSP.getValueAt(selectedRow, 3);
        String img = (String)tbDSSP.getValueAt(selectedRow, 4);
        SpinnerNumberModel modeSpinner = new SpinnerNumberModel(1, 1, sluong, 1);
        SpinnerSLmua.setModel(modeSpinner);
        JFormattedTextField txtSpinner = ((JSpinner.NumberEditor) SpinnerSLmua.getEditor()).getTextField();
            ((NumberFormatter) txtSpinner.getFormatter()).setAllowsInvalid(false);
            txtSpinner.setEditable(false);
            txtSpinner.setHorizontalAlignment(JTextField.LEFT);
        // Đặt dữ liệu lên các TextField
        lbMaSP.setText(String.valueOf(id));
        lbTenSP.setText(name);
        lbDongia.setText(String.valueOf(gia));
        loadAnh(img);
            }
        });
        
    }
        public static String ngayToString(Date ngay) {
        // Tạo một đối tượng định dạng ngày tháng
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Chuyển đổi kiểu ngày thành chuỗi
        String ngayChuoi = sdf.format(ngay);

        return ngayChuoi;
    }
        public void showTableHD(){
            model3 = new DefaultTableModel();
            model3.addColumn("Mã hóa đơn");
            model3.addColumn("Ngày lập");
            model3.addColumn("Tổng tiền");
             tbHD.setModel(model3);
             ArrayList<OrderDTO> arr = orBUS.getOrder();
             for (OrderDTO orderDTO : arr) {
                 Object[] row = {orderDTO.getMaHD(),orderDTO.getNgayTaoHD(),orderDTO.getTongTien()};
                model3.addRow(row);
             }
             tbHD.addMouseListener(new MouseAdapter(){
                  @Override
            public void mouseClicked(MouseEvent e) {
                int selectedRow = tbHD.getSelectedRow();
//                for(int i=0;i<tbHD.getRowCount();i++){
//                    
//                }
                int id = (int) tbHD.getValueAt(selectedRow, 0);
                Date date = (Date) tbHD.getValueAt(selectedRow, 1);
                int total = (int) tbHD.getValueAt(selectedRow, 2);
                LabelMaHD.setText(String.valueOf(id));
                lbNgay.setText(ngayToString(date));
                lbTongTien.setText(String.valueOf(total));
                ArrayList<OrderDTO> arr= orBUS.getOrdertheoma(id);
                      for (OrderDTO orderDTO : arr) {
                          int mkh = orderDTO.getMaKH();
                          int mnv=orderDTO.getMaNV();
                          lbMaKH.setText(String.valueOf(mkh));
                          lbMaNV.setText(String.valueOf(mnv));
                          
                      }
        model4 = new DefaultTableModel();
        model4.addColumn("Mã hóa đơn");
        model4.addColumn("Mã sản phẩm");
        model4.addColumn("Số lượng");
        model4.addColumn("Đơn giá");
        model4.addColumn("Thành tiền");
        tbCTHD.setModel(model4);
        //int ma = Integer.parseInt(LabelMaHD.getText());
        ArrayList<OrderDetailDTO> dstheoma= orDeBus.getOrderDetailtheomaHD(id);
        for (OrderDetailDTO orderDetailDTO : dstheoma) {
            Object[]row ={orderDetailDTO.getMaHD(),orderDetailDTO.getMaSP(),orderDetailDTO.getSoLuong(),orderDetailDTO.getGiaSP(),orderDetailDTO.getThanhTien()};
            model4.addRow(row);
        }
            }
             });
            
             
         }
         private  void nutLoai(){
             cmbCategory.addActionListener(new ActionListener(){
                 @Override
                 public void actionPerformed(ActionEvent e) {
                     loadDataTableSanPhamBan();
                 }
                 
             });
         }
         private void loadAnh(String anh) {
        
        jLabelImg.setIcon(getAnhSP(anh));
    }
         File file;
          private ImageIcon getAnhSP(String src) {
        BufferedImage img = null;
        File fileImg = new File(src);

        if (!fileImg.exists()) {
            fileImg = new File("src/main/resources/DoAn_QuanLyBanBanh/IMG/products/" + src);
        }

        try {
            img = ImageIO.read(fileImg);
            file = new File(src);
        } catch (IOException e) {
        }

        if (img != null) {
            Image dimg = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            return new ImageIcon(dimg);
        }

        return null;
    }
    public void showTablelCTHD(){
        model4 = new DefaultTableModel();
        model4.addColumn("Mã hóa đơn");
        model4.addColumn("Mã sản phẩm");
        model4.addColumn("Số lượng");
        model4.addColumn("Đơn giá");
        model4.addColumn("Thành tiền");
        tbCTHD.setModel(model4);
        ArrayList<OrderDetailDTO> ds= orDeBus.getOrderDetail();
        for (OrderDetailDTO orderDetailDTO : ds) {
            Object[]row ={orderDetailDTO.getMaHD(),orderDetailDTO.getMaSP(),orderDetailDTO.getSoLuong(),orderDetailDTO.getGiaSP(),orderDetailDTO.getThanhTien()};
            model4.addRow(row);
        }
        tbCTHD.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                int rowSelected=tbCTHD.getSelectedRow();
                int maHD = (int) tbCTHD.getValueAt(rowSelected, 0);
                int maSP = (int) tbCTHD.getValueAt(rowSelected, 1);
                int sl = (int) tbCTHD.getValueAt(rowSelected,2);
                int dgia = (int) tbCTHD.getValueAt(rowSelected,3);
                int thanhtien = (int) tbCTHD.getValueAt(rowSelected,4);
                lbSluong.setText(String.valueOf(sl));
                lbTongtien.setText(String.valueOf(thanhtien));
                lbDgia.setText(String.valueOf(dgia));
                ArrayList<ProductsDTO> dssptheoma= prBUS.getSPtheoma(maSP);
                for (ProductsDTO productsDTO : dssptheoma) {
                    lbSp.setText(productsDTO.getTenSP());
                    
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

        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        cmbCategory = new javax.swing.JComboBox<>();
        btADD = new javax.swing.JButton();
        btThanhtoan = new javax.swing.JButton();
        jLabelImg = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lbMaSP = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbTenSP = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        SpinnerSLmua = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbMaNV = new javax.swing.JComboBox<>();
        cmbMaKH = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        lbDongia = new javax.swing.JLabel();
        lbngay = new javax.swing.JLabel();
        btDelete = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbDSSP = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbSPmua = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        LabelMaHD = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        lbMaKH = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lbNgay = new javax.swing.JLabel();
        lbTongTien = new javax.swing.JLabel();
        lbMaNV = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbHD = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbSp = new javax.swing.JLabel();
        lbDgia = new javax.swing.JLabel();
        lbTongtien = new javax.swing.JLabel();
        lbSluong = new javax.swing.JLabel();
        btCTHD = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbCTHD = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtTungay = new javax.swing.JTextField();
        txtdenngay = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        btADD.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn_QuanLyBanBanh/IMG/icon/them.png"))); // NOI18N
        btADD.setText("Thêm");
        btADD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btADDActionPerformed(evt);
            }
        });

        btThanhtoan.setText("Thanh toán");
        btThanhtoan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btThanhtoanActionPerformed(evt);
            }
        });

        jLabelImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn_QuanLyBanBanh/IMG/products/Default.png"))); // NOI18N
        jLabelImg.setText("jLabel1");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Loại sản phẩm");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Mã sản phẩm");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Tên sản phẩm");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Số lượng");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Mã khách hàng");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Mã nhân viên");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Đơn giá");

        lbngay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn_QuanLyBanBanh/IMG/icon/xoa.png"))); // NOI18N
        btDelete.setText("Xóa ");
        btDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDeleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(btADD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(btDelete)
                .addGap(41, 41, 41)
                .addComponent(btThanhtoan)
                .addGap(34, 34, 34))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabelImg, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbCategory, 0, 168, Short.MAX_VALUE)
                                    .addComponent(lbTenSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(SpinnerSLmua)
                                    .addComponent(lbMaSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lbDongia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbMaNV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbMaKH, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(lbngay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(91, 91, 91))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbngay, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cmbMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbMaSP)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lbTenSP))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(SpinnerSLmua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lbDongia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jLabelImg, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btADD)
                    .addComponent(btThanhtoan)
                    .addComponent(btDelete))
                .addGap(30, 30, 30))
        );

        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tbDSSP.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbDSSP);

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane3.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tbSPmua.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tbSPmua);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setText("Menu");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setText("Danh sách mua hàng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(183, 183, 183)
                        .addComponent(jLabel15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(jLabel16)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );

        jTabbedPane1.addTab("Bán hàng", jPanel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Hóa đơn");

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Mã NV");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Mã hóa đơn");

        LabelMaHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Mã KH");

        lbMaKH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel20.setText("Tổng tiền");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Ngày lập");

        lbNgay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lbTongTien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lbMaNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel14)
                                .addComponent(jLabel18)
                                .addComponent(jLabel20))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(34, 34, 34)))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(LabelMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbMaKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(24, 24, 24)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(lbMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(lbMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(lbTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        tbHD.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbHD);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel8.setText("Thông tin hóa đơn");

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Tên sản phẩm");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Đơn giá");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Số lượng");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Thành tiền");

        lbSp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lbDgia.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lbTongtien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        lbSluong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel9)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(33, 33, 33)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbSluong, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbSp, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbDgia, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(lbSp, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(lbSluong, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(lbDgia, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(lbTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        btCTHD.setText("Xem tất cả");
        btCTHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCTHDActionPerformed(evt);
            }
        });

        tbCTHD.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tbCTHD);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Từ ngày:");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel22.setText("đến ngày:");

        txtdenngay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdenngayActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("Tìm kiếm");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTungay, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtdenngay, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(jLabel23)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel22)
                    .addComponent(txtTungay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtdenngay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 442, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addComponent(btCTHD)
                                .addGap(187, 187, 187))))))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(113, 113, 113))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(btCTHD)
                        .addGap(23, 23, 23)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(106, 106, 106))
        );

        jTabbedPane1.addTab("Hóa đơn", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btADDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btADDActionPerformed
        String id = lbMaSP.getText();
        String name = lbTenSP.getText();
        String price= lbDongia.getText();
        int quatityBuy = Integer.parseInt(SpinnerSLmua.getValue()+"");
        // int slcon = (int) tbDSSP.getValueAt(tbDSSP.getSelectedRow(), 2);
        lbMaSP.setText("");
        lbTenSP.setText("");
        lbDongia.setText("");
        SpinnerSLmua.setValue(0);

        if (id.trim().equalsIgnoreCase(""))
        return;
        int mSP = Integer.parseInt(id);
        for (int i = 0; i < tbSPmua.getRowCount(); i++) {
            int maTbl = Integer.parseInt(tbSPmua.getValueAt(i, 0) + "");
            if (maTbl == mSP) {
                int soLuongAdd = Integer.parseInt(tbSPmua.getValueAt(i, 2) + "");
                soLuongAdd += quatityBuy;
                int donGiaSP = Integer.parseInt(price);
                tbSPmua.setValueAt(soLuongAdd, i, 2);
                tbSPmua.setValueAt((soLuongAdd * donGiaSP), i, 4);
                prBUS.capNhatSoLuongSP(mSP, -quatityBuy);
                prBUS.getProducts();
                loadDataTableSanPhamBan();
                return;
            }
        }

        int dg =Integer.parseInt(price);
        int total= dg*quatityBuy;
        Object[] row={id,name,quatityBuy,price,total};
        prBUS.capNhatSoLuongSP(mSP, - quatityBuy);
        prBUS.getProducts();
        loadDataTableSanPhamBan();
        model2.addRow(row);
    }//GEN-LAST:event_btADDActionPerformed

    private void btThanhtoanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btThanhtoanActionPerformed
        // TODO add your handling code here:

        String idkh = cmbMaKH.getSelectedItem()+"";
        String[] idTmp =idkh.split(" - ");
        int maKH = Integer.parseInt(idTmp[0]);
        String idnv =(String) cmbMaNV.getSelectedItem();
        String[] idNVTmp = idnv.split(" - ");
        int maNV =Integer.parseInt(idNVTmp[0]);
        OrderDTO hd = new OrderDTO();
        hd.setMaKH(maKH);
        hd.setMaNV(maNV);
        int total=0;
        ArrayList<Vector> dsMuaHang= new ArrayList<>();
        for(int i =0; i<tbSPmua.getRowCount();i++){
            Vector spmua=new Vector();
            spmua.add(tbSPmua.getValueAt(i, 0));
            spmua.add(tbSPmua.getValueAt(i, 1));
            spmua.add(tbSPmua.getValueAt(i, 2));
            spmua.add(tbSPmua.getValueAt(i, 3));
            spmua.add(tbSPmua.getValueAt(i, 4));
            total+=Integer.parseInt(tbSPmua.getValueAt(i, 4)+"");
            dsMuaHang.add(spmua);
        }

        hd.setTongTien(total);
        orBUS.addOrder(hd);
        int maHD = orBUS.getMaHD();
        OrderDetailDTO cthd = new OrderDetailDTO();
        cthd.setMaHD(maHD);
        for (Vector spmua : dsMuaHang) {
            int maSP = Integer.parseInt(spmua.get(0)+"");
            int sl = Integer.parseInt(spmua.get(2)+"");
            int dongia = Integer.parseInt(spmua.get(3)+"");
            int thanhtien =Integer.parseInt(spmua.get(4)+"");
            cthd.setMaSP(maSP);
            cthd.setSoLuong(sl);
            cthd.setGiaSP(dongia);
            cthd.setThanhTien(thanhtien);
            orDeBus.add(cthd);
        }

    }//GEN-LAST:event_btThanhtoanActionPerformed

    private void btDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDeleteActionPerformed
        // TODO add your handling code here:
        int selectRow = tbSPmua.getSelectedRow();
        if(selectRow!=-1){
            int mSP= Integer.parseInt(tbSPmua.getValueAt(selectRow, 0)+"") ;
            int sl = Integer.parseInt(tbSPmua.getValueAt(selectRow, 2)+"");
            prBUS.capNhatSoLuongSP(mSP, sl);
            loadDataTableSanPhamBan();
            model2.removeRow(selectRow);
        }
    }//GEN-LAST:event_btDeleteActionPerformed

    private void btCTHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCTHDActionPerformed
        // TODO add your handling code here:
        showTablelCTHD();

    }//GEN-LAST:event_btCTHDActionPerformed

    private void txtdenngayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdenngayActionPerformed
        // TODO add your handling code here:
        timkiemtheongay(txtTungay.getText(), txtdenngay.getText());
    }//GEN-LAST:event_txtdenngayActionPerformed
    private void timkiemtheongay(String tuNgay,String denNgay){
        ArrayList<OrderDTO> hd = orBUS.getOrdertheongay(tuNgay, denNgay);
        model3 = new DefaultTableModel();
            model3.addColumn("Mã hóa đơn");
            model3.addColumn("Ngày lập");
            model3.addColumn("Tổng tiền");
             tbHD.setModel(model3);
             for (OrderDTO orderDTO : hd) {
                 Object[] row = {orderDTO.getMaHD(),orderDTO.getNgayTaoHD(),orderDTO.getTongTien()};
                model3.addRow(row);
             }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel LabelMaHD;
    private javax.swing.JSpinner SpinnerSLmua;
    private javax.swing.JButton btADD;
    private javax.swing.JButton btCTHD;
    private javax.swing.JButton btDelete;
    private javax.swing.JButton btThanhtoan;
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JComboBox<String> cmbMaKH;
    private javax.swing.JComboBox<String> cmbMaNV;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelImg;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lbDgia;
    private javax.swing.JLabel lbDongia;
    private javax.swing.JLabel lbMaKH;
    private javax.swing.JLabel lbMaNV;
    private javax.swing.JLabel lbMaSP;
    private javax.swing.JLabel lbNgay;
    private javax.swing.JLabel lbSluong;
    private javax.swing.JLabel lbSp;
    private javax.swing.JLabel lbTenSP;
    private javax.swing.JLabel lbTongTien;
    private javax.swing.JLabel lbTongtien;
    private javax.swing.JLabel lbngay;
    private javax.swing.JTable tbCTHD;
    private javax.swing.JTable tbDSSP;
    private javax.swing.JTable tbHD;
    private javax.swing.JTable tbSPmua;
    private javax.swing.JTextField txtTungay;
    private javax.swing.JTextField txtdenngay;
    // End of variables declaration//GEN-END:variables
}
