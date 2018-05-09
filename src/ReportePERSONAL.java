
import com.mysql.jdbc.Connection;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class ReportePERSONAL extends javax.swing.JFrame {

    conexionBD con = new conexionBD();
    Connection c = con.getConnection();
    TableRowSorter trs;
    String[] tablapersonal;
    DefaultTableModel dmtpersonal = new DefaultTableModel();

    int id_filtro, id_ordenar;
    int id_radio;

    JTable tabla = new JTable(dmtpersonal);

    public ReportePERSONAL() {
        initComponents();
        setLocationRelativeTo(null);
        mostrardatos();
        tfiltro.setEnabled(false);
        selfiltro.setSelectedItem(null);
        selfiltro.requestFocus();
        tabla.setModel(dmtpersonal);
        tabla.setRowSorter(trs);

    }

    public void mostrardatos() {

        dmtpersonal.addColumn("IDENTIFICACION");
        dmtpersonal.addColumn("PRIMER NOMBRE");
        dmtpersonal.addColumn("SEGUNDO NOMBRE");
        dmtpersonal.addColumn("PRIMER APELLIDO");
        dmtpersonal.addColumn("SEGUNDO APELLIDO");
        dmtpersonal.addColumn("CELULAR");
        dmtpersonal.addColumn("EMAIL");
        dmtpersonal.addColumn("REGIONAL");
        dmtpersonal.addColumn("UNIVERSIDAD");
        dmtpersonal.addColumn("ROLL");
        dmtpersonal.addColumn("FECHA DE REGISTRO");
        tdpersonal.setModel(dmtpersonal);

         tablapersonal= new String[11];
        try {
            Statement stp = c.createStatement();
            ResultSet rs = stp.executeQuery("SELECT * FROM delegados");
            while (rs.next()) {
                tablapersonal[0] = rs.getString(1);
                tablapersonal[1] = rs.getString(2);
                tablapersonal[2] = rs.getString(3);
                tablapersonal[3] = rs.getString(4);
                tablapersonal[4] = rs.getString(5);
                tablapersonal[5] = rs.getString(10);
                tablapersonal[6] = rs.getString(11);
                tablapersonal[7] = rs.getString(6);
                tablapersonal[8] = rs.getString(7);
                tablapersonal[9] = rs.getString(8);
                tablapersonal[10] = rs.getString(9);
                dmtpersonal.addRow(tablapersonal);
              

            }
        } catch (Exception e) {

            System.out.println(e);
        }

    }

    public void ordenardatos() {
        
        //BORRA LOS DATOS DE LAS FILAS Y LAS COLUMNAS
        dmtpersonal.setColumnCount(0);
        dmtpersonal.setRowCount(0);
        
        //SELECCIONA SI ES ASCENDEMTE O DESCENDENTE
        id_ordenar=o_select.getSelectedIndex();
        String tipo=Gordenar.getSelection().getActionCommand();
        String seleccion="";
        //SELECCIONA EL INTEM PARA ORGANIZAR LOS DATOS 
        id_radio=o_select.getSelectedIndex();
        if(id_radio==0){
             seleccion="identificacion";
        }else if(id_radio==1){
             seleccion="pr_nombre";
        }else if(id_radio==2){
             seleccion="se_nombre";
        }else if(id_radio==3){
             seleccion="pr_apellido";
        }else if(id_radio==4){
             seleccion="se_apellido";
        }else if(id_radio==5){
             seleccion="celular";
        }else if(id_radio==6){
             seleccion="email";
        }else if(id_radio==7){
             seleccion="regional";
        }else if(id_radio==8){
             seleccion="universidad";
        }else if(id_radio==9){
             seleccion="roll";
        }else if(id_radio==10){
             seleccion="Fecha_registro";
        }
        
        //ADICIONA LAS COLUMNAS PRINCIPAL DE LOS TITULOS DE LA TABLA
        dmtpersonal.addColumn("IDENTIFICACION");
        dmtpersonal.addColumn("PRIMER NOMBRE");
        dmtpersonal.addColumn("SEGUNDO NOMBRE");
        dmtpersonal.addColumn("PRIMER APELLIDO");
        dmtpersonal.addColumn("SEGUNDO APELLIDO");
        dmtpersonal.addColumn("CELULAR");
        dmtpersonal.addColumn("EMAIL");
        dmtpersonal.addColumn("REGIONAL");
        dmtpersonal.addColumn("UNIVERSIDAD");
        dmtpersonal.addColumn("ROLL");
        dmtpersonal.addColumn("FECHA DE REGISTRO");
        tdpersonal.setModel(dmtpersonal);
        
        String o_datos="SELECT * FROM delegados ORDER BY "+seleccion+" "+tipo;
       
      
        //LLENAR LA TABLA CON LOS DATOS QUE SE ENCUENTRA EN LA BASE DE DATOS
        String[] tablapersonal = new String[11];
        try {
            Statement stp = c.createStatement();
            ResultSet rs = stp.executeQuery(o_datos);
            while (rs.next()) {
                tablapersonal[0] = rs.getString(1);
                tablapersonal[1] = rs.getString(2);
                tablapersonal[2] = rs.getString(3);
                tablapersonal[3] = rs.getString(4);
                tablapersonal[4] = rs.getString(5);
                tablapersonal[5] = rs.getString(10);
                tablapersonal[6] = rs.getString(11);
                tablapersonal[7] = rs.getString(6);
                tablapersonal[8] = rs.getString(7);
                tablapersonal[9] = rs.getString(8);
                tablapersonal[10] = rs.getString(9);
                
                dmtpersonal.addRow(tablapersonal);
               

            }
        } catch (Exception e) {

            System.out.println(e);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        Gordenar = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tdpersonal = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tfiltro = new javax.swing.JTextField();
        selfiltro = new javax.swing.JComboBox<>();
        ordenar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        o_select = new javax.swing.JComboBox<>();
        ASC = new javax.swing.JRadioButton();
        DESC = new javax.swing.JRadioButton();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        tdpersonal.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tdpersonal);

        jLabel2.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("DATOS PERSONALES DELEGADOS ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/report_go.png"))); // NOI18N
        jButton1.setText("REPORTES");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/gohome.gif"))); // NOI18N
        jButton2.setText("MENU PRINCIPAL");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("FILTRO:");

        tfiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfiltroKeyTyped(evt);
            }
        });

        selfiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IDENTIFICACION", "PRIMER NOMBRE", "SEGUNDO NOMBRE", "PRIMER APELLIDO", "SEGUNDO APELIIDO", "CELULAR", "EMAIL", "REGIONAL", "UNIVERSIDAD", "ROLL", "" }));
        selfiltro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selfiltroActionPerformed(evt);
            }
        });

        ordenar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/spellcheck.png"))); // NOI18N
        ordenar.setText("ORDENAR");
        ordenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ordenarActionPerformed(evt);
            }
        });

        jLabel3.setText("ORDENAR:");

        o_select.setMaximumRowCount(9);
        o_select.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IDENTIFICACION", "PRIMER NOMBRE", "SEGUNDO NOMBRE", "PRIMER APELLIDO", "SEGUNDO APELLIDO", "CELULAR", "EMAIL", "REGIONAL", "UNIVERSIDAD", "ROLL", " " }));

        Gordenar.add(ASC);
        ASC.setText("ASCENDETE");
        ASC.setActionCommand("ASC");

        Gordenar.add(DESC);
        DESC.setText("DESCENDETE");
        DESC.setActionCommand("DESC");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(selfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(o_select, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ASC)
                        .addGap(18, 18, 18)
                        .addComponent(DESC)
                        .addGap(18, 18, 18)
                        .addComponent(ordenar)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jLabel1)
                    .addComponent(selfiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfiltro)
                    .addComponent(jButton2)
                    .addComponent(ordenar)
                    .addComponent(jLabel3)
                    .addComponent(o_select, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ASC)
                    .addComponent(DESC))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfiltroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfiltroKeyTyped
        tfiltro.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent ke) {
                trs.setRowFilter(RowFilter.regexFilter("(?i)" + tfiltro.getText(), id_filtro));
            }

        });
        trs = new TableRowSorter(dmtpersonal);
        tdpersonal.setRowSorter(trs);
    }//GEN-LAST:event_tfiltroKeyTyped

    private void selfiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selfiltroActionPerformed

        id_filtro = selfiltro.getSelectedIndex();
        tfiltro.setEnabled(true);
        tfiltro.requestFocus();
    }//GEN-LAST:event_selfiltroActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        MenuPrincipal MP = new MenuPrincipal();
        MP.toFront();
        MP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Reporteasamblea ra = new Reporteasamblea();
        ra.toFront();
        ra.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ordenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ordenarActionPerformed
        ordenardatos();
    }//GEN-LAST:event_ordenarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton ASC;
    private javax.swing.JRadioButton DESC;
    private javax.swing.ButtonGroup Gordenar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> o_select;
    private javax.swing.JButton ordenar;
    private javax.swing.JComboBox<String> selfiltro;
    private javax.swing.JTable tdpersonal;
    private javax.swing.JTextField tfiltro;
    // End of variables declaration//GEN-END:variables

}
