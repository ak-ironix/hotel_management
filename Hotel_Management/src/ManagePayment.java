/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author akcha
 */
import javax.swing.*;
import java.sql.*;
import javax.swing.table.*;
public class ManagePayment extends javax.swing.JFrame {
    public int roomcost=0;
    /**
     * Creates new form ManagePayment
     */
    public ManagePayment() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    public void payment(String s){
        try{
        int bid = Integer.parseInt(tf1.getText());
        int exp =0,pid=0;
        String met = "";
        Class.forName("java.sql.Driver");
        Connection con =DriverManager.getConnection("jdbc:mysql://localhost/hotelmanagement","root","root");
        PreparedStatement stmt = con.prepareStatement("insert into payment values (?,?,?,?,null,?,?)");
        PreparedStatement idstmt = con.prepareStatement("select max(p_id) from payment");
        PreparedStatement expstmt= con.prepareStatement("select price from expense where e_id = ?");
        PreparedStatement procedure= con.prepareStatement("call tot_exp(?,@a,@b,@c);");
        
        ResultSet idrs = idstmt.executeQuery();
        while(idrs.next()){
                pid = idrs.getInt("max(p_id)");
            }
        pid++;
        if(cb1.isSelected()){
            int x =0;
            expstmt.setInt(1,1);
            ResultSet exprs = expstmt.executeQuery();
            while(exprs.next()){
                x = exprs.getInt("price");
            }
            exp = exp + x;
        }
        if(cb2.isSelected()){
            int x =0;
            expstmt.setInt(1,2);
            ResultSet exprs = expstmt.executeQuery();
            while(exprs.next()){
                x = exprs.getInt("price");
            }
            exp = exp + x;
        }
        if(cb3.isSelected()){
            int x =0;
            expstmt.setInt(1,3);
            ResultSet exprs = expstmt.executeQuery();
            while(exprs.next()){
                x = exprs.getInt("price");
            }
            exp = exp + x;
        }
        if(cb4.isSelected()){
            int x =0;
            expstmt.setInt(1,4);
            ResultSet exprs = expstmt.executeQuery();
            while(exprs.next()){
                x = exprs.getInt("price");
            }
            exp = exp + x;
        }
        if(rb1.isSelected()){
            met = "cash";
        }
        if(rb2.isSelected()){
            met = "card";
        }
        if(rb3.isSelected()){
            met = "upi";
        }
        stmt.setInt(1,pid);
        stmt.setInt(2,bid);
        stmt.setInt(3,roomcost);
        stmt.setInt(4,exp);
        stmt.setString(5,met);
        stmt.setString(6,s);
        boolean y = stmt.execute();
        if(y==false){
           System.out.println("Payment table updated");
           procedure.setInt(1,pid);
           procedure.execute();
           displaypayment(pid);
        }
        }
        catch(Exception e){
            System.out.println("error");
        }
    }
    
    public void displaypayment(int pid){
        try{
        DefaultTableModel model= new DefaultTableModel();
        Class.forName("java.sql.Driver");
        Connection con =DriverManager.getConnection("jdbc:mysql://localhost/hotelmanagement","root","root");
        PreparedStatement stmt= con.prepareStatement("select * from payment where p_id = " + pid);
        ResultSet rs = stmt.executeQuery();
        
        model.addColumn("PaymentID");
        model.addColumn("BookingID");
        model.addColumn("Room Cost");
        model.addColumn("Expense Cost");
        model.addColumn("Total Cost");
        model.addColumn("Payment Method");
        model.addColumn("Payment Status");
        while(rs.next()){
            pid = rs.getInt("p_id");
            int bid = rs.getInt("b_id");
            int rc = rs.getInt("room_cost");
            int ec = rs.getInt("expense_cost");
            int tc = rs.getInt("total_cost");
            String pm = rs.getString("payment_type");
            String ps = rs.getString("payment_status");
            model.addRow(new Object[]{pid,bid,rc,ec,tc,pm,ps});
        }
        if(model.getRowCount()==0){
            model.setRowCount(0);
            System.out.println("Invalid ID");
            payment.setModel(model);
            payment.setEnabled(false);
            return;
        }
        payment.setModel(model);
        payment.setEnabled(false);
    }
catch(Exception e){
        System.out.println("error");
}
    }
    
    public void check_paid(int bid, String s){
        try{
        String st="";
        Class.forName("java.sql.Driver");
        Connection con =DriverManager.getConnection("jdbc:mysql://localhost/hotelmanagement","root","root");
        PreparedStatement stmt= con.prepareStatement("select payment_status from payment where b_id="+bid);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()){
            st = rs.getString("payment_status");
        }
        if(st.equals("paid")){
            System.out.println("Already paid");
            return;
        }
        if(st.equals("not paid")){
            PreparedStatement up= con.prepareStatement("update payment set payment_status =?  where b_id ="+bid);
            up.setString(1,s);
            up.execute();
            System.out.println("Booking exists in payment table. Payment status: "+s);
            return;
        }
        payment(s);
        }
        catch(Exception e){
        System.out.println("error");
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tf1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        found = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        cb1 = new javax.swing.JCheckBox();
        cb2 = new javax.swing.JCheckBox();
        cb3 = new javax.swing.JCheckBox();
        cb4 = new javax.swing.JCheckBox();
        rb1 = new javax.swing.JRadioButton();
        rb2 = new javax.swing.JRadioButton();
        rb3 = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        payment = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Payment");

        jLabel2.setText("Enter Booking Id");

        jButton1.setText("Find");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        found.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(found);

        jLabel3.setText("Add Expense");

        cb1.setText("Extra Bed");
        cb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb1ActionPerformed(evt);
            }
        });

        cb2.setText("Breakfast");

        cb3.setText("Lunch");

        cb4.setText("Dinner");

        buttonGroup1.add(rb1);
        rb1.setText("Cash");

        buttonGroup1.add(rb2);
        rb2.setText("Card");

        buttonGroup1.add(rb3);
        rb3.setText("Upi");

        jLabel4.setText("Payment Method");

        jButton2.setText("Pay Now");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Pay Later");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        payment.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(payment);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(125, 125, 125)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cb1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(cb2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(cb3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(cb4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rb1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(rb2, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(rb3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(34, 34, 34)
                        .addComponent(tf1, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tf1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb1)
                    .addComponent(cb2)
                    .addComponent(cb3)
                    .addComponent(cb4))
                .addGap(27, 27, 27)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb1)
                    .addComponent(rb2)
                    .addComponent(rb3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb1ActionPerformed

    }//GEN-LAST:event_cb1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       int id = Integer.parseInt(tf1.getText());
        check_paid(id,"paid");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
        DefaultTableModel model= new DefaultTableModel();
        Class.forName("java.sql.Driver");
        int id = Integer.parseInt(tf1.getText());
        Connection con =DriverManager.getConnection("jdbc:mysql://localhost/hotelmanagement","root","root");
        PreparedStatement stmt= con.prepareStatement("select c_id,b_id,booking.r_id,room_type from booking join room on room.r_id = booking.r_id where b_id = ?;");
        stmt.setInt(1,id);
        ResultSet rs = stmt.executeQuery();
        
        model.addColumn("CustomerID");
        model.addColumn("BookingID");
        model.addColumn("Room Number");
        model.addColumn("Room Type");
        while(rs.next()){
            int cid = rs.getInt("c_id");
            int bid = rs.getInt("b_id");
            int rid = rs.getInt("r_id");
            String rt = rs.getString("room_type");
            switch(rt){
                case "1 bed": roomcost = 5500;
                              break;
                case "2 beds": roomcost = 7000;
                              break;
                case "3 beds": roomcost = 9000;
                              break;
                default: System.out.println("ERROR");
            }
            model.addRow(new Object[]{cid,bid,rid,rt});
        }
        if(model.getRowCount()==0){
            model.setRowCount(0);
            System.out.println("Invalid ID");
            found.setModel(model);
            found.setEnabled(false);
            return;
        }
        found.setModel(model);
        found.setEnabled(false);
    }
catch(Exception e){
        System.out.println("error");
}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       int id = Integer.parseInt(tf1.getText());
       check_paid(id,"not paid");
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ManagePayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManagePayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManagePayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManagePayment.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManagePayment().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cb1;
    private javax.swing.JCheckBox cb2;
    private javax.swing.JCheckBox cb3;
    private javax.swing.JCheckBox cb4;
    private javax.swing.JTable found;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable payment;
    private javax.swing.JRadioButton rb1;
    private javax.swing.JRadioButton rb2;
    private javax.swing.JRadioButton rb3;
    private javax.swing.JTextField tf1;
    // End of variables declaration//GEN-END:variables
}
