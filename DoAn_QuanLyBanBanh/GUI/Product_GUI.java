/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package DoAn_QuanLyBanBanh.GUI;
import DoAn_QuanLyBanBanh.BUS.CategoryBUS;
import DoAn_QuanLyBanBanh.BUS.ProductsBUS;
import DoAn_QuanLyBanBanh.DAO.ProductsDAO;
import DoAn_QuanLyBanBanh.DTO.CategoryDTO;
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
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author nhu
 */
public class Product_GUI extends javax.swing.JPanel {
    CategoryBUS caBUS = new CategoryBUS();
    DefaultTableModel model;
    ProductsBUS prBUS = new ProductsBUS();
    /**
     * Creates new form Product_GUI
     */
    public Product_GUI() {
        initComponents();
         CmbCategory();
        showTable();
        showCategoryGUI();
        jTableProducts.getTableHeader().setBackground(new Color(255,153,0));
    }
    public void CmbCategory(){
        ArrayList<CategoryDTO> arr = caBUS.getCategory();
        cmbCategory.addItem("0 - Chọn loại");
        for (CategoryDTO categoryDTO : arr) {
            cmbCategory.addItem(categoryDTO.getMaLoai()+" - "+categoryDTO.getTenLoai());
        }
        cmbCategory.addItem("Khác");
        
        
    }
    private void showCategoryGUI(){
        cmbCategory.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int loai = cmbCategory.getSelectedIndex();
                if(loai==cmbCategory.getItemCount()-1){
                    CategoryGUI loaiGUI = new CategoryGUI();
                    loaiGUI.setVisible(true);
                    CmbCategory();
                }
            }
            
        });
    }
    private void loadAnh(String anh) {
        
        jLabelImg.setIcon(getAnhSP(anh));
    }
    public void showTable(){
         model = new DefaultTableModel();
        model.addColumn("Mã sản phẩm");
        model.addColumn("Tên sản phẩm");
        model.addColumn("Loại");
        model.addColumn("Giá");
        model.addColumn("Số lượng");
        model.addColumn("Ảnh");
        jTableProducts.setModel(model);
        ArrayList<ProductsDTO> arr = prBUS.getProducts();
        
        for (ProductsDTO productsDTO : arr) {
            String tenLoai = caBUS.getTenLoai(productsDTO.getMaLoai());
            Object[] row={productsDTO.getMaSP(),productsDTO.getTenSP(),tenLoai,productsDTO.getGia(),productsDTO.getSoLuong(),productsDTO.getHinh()};
            model.addRow(row);
        }
        jTableProducts.addMouseListener(new MouseAdapter(){ 
             public void mouseClicked(MouseEvent e) {
        // Lấy chỉ mục của dòng được chọn
        int selectedRow = jTableProducts.getSelectedRow();

        // Truy xuất dữ liệu từ dòng được chọn
        int id = (int) jTableProducts.getValueAt(selectedRow, 0);
        String name = (String) jTableProducts.getValueAt(selectedRow, 1);
        String tenloai = (String) jTableProducts.getValueAt(selectedRow, 2);
        int gia = (int) jTableProducts.getValueAt(selectedRow, 3);
        int sluong= (int) jTableProducts.getValueAt(selectedRow, 4);
        String img = (String)jTableProducts.getValueAt(selectedRow, 5);

        // Đặt dữ liệu lên các TextField
        jLabelIdProduct.setText(String.valueOf(id));
        jTextFieldProductName.setText(name);
        cmbCategory.setSelectedItem(tenloai);
        jTextFieldPrice.setText(String.valueOf(gia));
        lbsoluong.setText(String.valueOf(sluong));
        loadAnh(img);
        
        
        
    }
        });
     
        
    }
    File file;
    private void luuFileAnh() {
        BufferedImage bImage = null;
        try {
            File initialImage = new File(file.getPath());
            bImage = ImageIO.read(initialImage);

            ImageIO.write(bImage, "png", new File("src/main/resources/DoAn_QuanLyBanBanh/IMG/products/" + file.getName()));

        } catch (IOException e) {
            System.out.println("Exception occured :" + e.getMessage());
        }
    }
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabelIdProduct = new javax.swing.JLabel();
        cmbCategory = new javax.swing.JComboBox<>();
        jTextFieldPrice = new javax.swing.JTextField();
        jTextFieldProductName = new javax.swing.JTextField();
        lbsoluong = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabelImg = new javax.swing.JLabel();
        jButtonChon = new javax.swing.JButton();
        jButtonAdd = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonUpdate = new javax.swing.JButton();
        jTextFieldSearch = new javax.swing.JTextField();
        jButtonSearch = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableProducts = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Quản lý sản phẩm");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Mã sản phẩm ");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Tên sản phẩm ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Loại sản phẩm ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Giá sản phẩm ");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Số lượng");

        jLabelIdProduct.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5))
                .addGap(51, 51, 51)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldProductName, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                    .addComponent(jTextFieldPrice, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbsoluong, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabelIdProduct, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                            .addComponent(cmbCategory, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(83, 83, 83))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabelIdProduct))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextFieldProductName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbsoluong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(43, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabelImg.setBackground(new java.awt.Color(204, 204, 255));
        jLabelImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn_QuanLyBanBanh/IMG/products/Default.png"))); // NOI18N
        jLabelImg.setPreferredSize(new java.awt.Dimension(200, 200));

        jButtonChon.setBackground(new java.awt.Color(255, 204, 204));
        jButtonChon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonChon.setText("Chọn");
        jButtonChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonChonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jButtonChon, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabelImg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelImg, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jButtonChon)
                .addGap(38, 38, 38))
        );

        jButtonAdd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn_QuanLyBanBanh/IMG/hinh/them.png"))); // NOI18N
        jButtonAdd.setText("Thêm");
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jButtonDelete.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn_QuanLyBanBanh/IMG/hinh/xoa.png"))); // NOI18N
        jButtonDelete.setText("Xóa");
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });

        jButtonUpdate.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn_QuanLyBanBanh/IMG/hinh/sua.png"))); // NOI18N
        jButtonUpdate.setText("Sửa");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jTextFieldSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchActionPerformed(evt);
            }
        });

        jButtonSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/DoAn_QuanLyBanBanh/IMG/hinh/tk.png"))); // NOI18N
        jButtonSearch.setText("Search");
        jButtonSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSearchActionPerformed(evt);
            }
        });

        jTableProducts.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(jTableProducts);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButtonAdd)
                                        .addGap(81, 81, 81)
                                        .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(105, 105, 105)
                                        .addComponent(jButtonUpdate))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(20, 20, 20)
                                        .addComponent(jButtonSearch))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(383, 383, 383)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAdd)
                    .addComponent(jButtonDelete)
                    .addComponent(jButtonUpdate))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonSearch))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(81, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonChonActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser("src/main/resources/DoAn_QuanLyBanBanh/IMG/products/");
        //fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Tệp hình ảnh", "jpg","png","jpeg");
        fileChooser.setFileFilter(filter);
        int returnVl = fileChooser.showOpenDialog(null);
        if(returnVl==JFileChooser.APPROVE_OPTION){
            file = fileChooser.getSelectedFile();
            jLabelImg.setIcon(getAnhSP(file.getPath()));
            //

        }
    }//GEN-LAST:event_jButtonChonActionPerformed

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        // TODO add your handling code here:
        String anh = file.getName();
        String loai =(String) cmbCategory.getSelectedItem();
        String[] loaiTmp =loai.split(" - ");
        int maLoai = Integer.parseInt(loaiTmp[0]);
        ProductsDTO pr = new ProductsDTO();
        pr.setTenSP(jTextFieldProductName.getText());
        pr.setMaLoai(maLoai);
        pr.setSoLuong(0);
        pr.setGia(Integer.parseInt(jTextFieldPrice.getText()));
        pr.setHinh(anh);
        JOptionPane.showMessageDialog(null, prBUS.add(pr));
        showTable();
        luuFileAnh();
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        // TODO add your handling code here:
        ProductsDTO pr = new ProductsDTO();
        pr.setMaSP(Integer.parseInt(jLabelIdProduct.getText()));
        JOptionPane.showMessageDialog(null, prBUS.delete(pr));
        showTable();
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        // TODO add your handling code here:
        String anh = file.getName();
        String loai =cmbCategory.getSelectedItem()+"";
        String[] loaiTmp =loai.split(" - ");
        int maLoai = Integer.parseInt(loaiTmp[0]);
        ProductsDTO pr = new ProductsDTO();
        pr.setMaSP(Integer.parseInt(jLabelIdProduct.getText()));
        pr.setTenSP(jTextFieldProductName.getText());
        pr.setMaLoai(maLoai);
        pr.setSoLuong(0);
        pr.setGia(Integer.parseInt(jTextFieldPrice.getText()));
        pr.setHinh(anh);
        JOptionPane.showMessageDialog(null, prBUS.update(pr));
        showTable();
        luuFileAnh();

    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jTextFieldSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearchActionPerformed
     private void loadData(String name) {
        ProductsDAO dao = new ProductsDAO();
        List<ProductsDTO> products =dao.findByName(name);
        updateTable(products);    
    }

    private void updateTable(List<ProductsDTO> products) {
        model.setRowCount(0);
        // Populate the table model
        for (ProductsDTO product : products) {
            Object[] row = new Object[]{product.getMaSP(),product.getTenSP(), product.getMaLoai(), product.getGia(), product.getHinh(), product.getSoLuong()};
            model.addRow(row);
        }    
    }
    private void jButtonSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSearchActionPerformed
        // TODO add your handling code here:

        String name = jTextFieldSearch.getText().trim();
        loadData(name);

    }//GEN-LAST:event_jButtonSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbCategory;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonChon;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonSearch;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelIdProduct;
    private javax.swing.JLabel jLabelImg;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableProducts;
    private javax.swing.JTextField jTextFieldPrice;
    private javax.swing.JTextField jTextFieldProductName;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JLabel lbsoluong;
    // End of variables declaration//GEN-END:variables
}
