
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Calendar;
import javax.swing.JOptionPane;

public class registroParticipantes extends javax.swing.JFrame {

    float id;
    int selector;
    java.util.Date objectotiempo = new java.util.Date();
    Timestamp timestamp = new Timestamp(objectotiempo.getTime());
    String año, mes, dia, horas, minutos, segundos, fecha;

    conexionBD con = new conexionBD();
    Connection c = con.getConnection();

    public registroParticipantes() {
        initComponents();
        setLocationRelativeTo(null);
        nombreregistro.setEditable(false);
        apellidosregistro.setEditable(false);
        repuestas.setEditable(false);

    }
    public void infecha() {
        año = Integer.toString(in.getCalendar().get(Calendar.YEAR));
        mes = Integer.toString(in.getCalendar().get(Calendar.MONTH));
        dia = Integer.toString(in.getCalendar().get(Calendar.DAY_OF_MONTH));
        horas = Integer.toString(in.getCalendar().get(Calendar.HOUR));
        minutos = Integer.toString(in.getCalendar().get(Calendar.MINUTE));
        segundos = Integer.toString(in.getCalendar().get(Calendar.SECOND));
        fecha = (año + "-" + mes + "-" + dia + " " + horas + ":" + minutos + ":" + segundos);

    }
    public void buscar() {
        try {

            id = Float.parseFloat(IDregistro.getText());
            
            Statement reg = c.createStatement();
            ResultSet se = reg.executeQuery("SELECT pr_nombre,se_nombre,pr_apellido,se_apellido FROM delegados"
                    + " WHERE identificacion='" + id + "'");
            while (se.next()) {
                nombreregistro.setText(se.getString(1) + " " + se.getString(2));
                apellidosregistro.setText(se.getString(3) + " " + se.getString(4));
                repuestas.setText("USUARIO ENCONTRADO");
            }

        } catch (SQLException ex) {

            System.out.println(ex);
        }

    }
    public void borrardatos() {
        nombreregistro.setText("");
        apellidosregistro.setText("");
        IDregistro.setText("");
        in.setDateFormatString("");

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        IDregistro = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        nombreregistro = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        apellidosregistro = new javax.swing.JTextField();
        in = new com.toedter.calendar.JDateChooser();
        guardar = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        menuprincipal = new javax.swing.JButton();
        BBUSCAR = new javax.swing.JButton();
        repuestas = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setText("IDENTIDICACION:");

        jLabel3.setText("NOMBRES:");

        nombreregistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreregistroActionPerformed(evt);
            }
        });

        jLabel4.setText("APELLIDOS:");

        in.setDateFormatString("yyyy/MM/dd HH:mm:ss");

        guardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/filesave.gif"))); // NOI18N
        guardar.setText("GUARDAR");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

        borrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/remove.gif"))); // NOI18N
        borrar.setText("BORRAR");
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });

        menuprincipal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/gohome.gif"))); // NOI18N
        menuprincipal.setText("MENU PRINCIPAL");
        menuprincipal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuprincipalActionPerformed(evt);
            }
        });

        BBUSCAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/filefind.gif"))); // NOI18N
        BBUSCAR.setText("BUSCAR");
        BBUSCAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBUSCARActionPerformed(evt);
            }
        });

        repuestas.setEditable(false);
        repuestas.setBackground(new java.awt.Color(0, 102, 102));
        repuestas.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\Users\\Aloia\\Desktop\\alexza\\iconos\\iconos-16x16-gif-transparente-11\\iconos-16x16-gif-transparente-1\\list.gif")); // NOI18N
        jLabel2.setText("TOMA DE ASISTENCIA ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 11, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );

        jLabel6.setText("INGRESO:");

        jPanel4.setBackground(new java.awt.Color(0, 0, 0));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("REGISTRO:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(181, 181, 181))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel5)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(guardar)
                        .addGap(62, 62, 62)
                        .addComponent(borrar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addComponent(menuprincipal))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(BBUSCAR, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(repuestas, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(IDregistro, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nombreregistro, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(apellidosregistro, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(in, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(IDregistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(repuestas, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BBUSCAR))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nombreregistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(apellidosregistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(in, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardar)
                    .addComponent(borrar)
                    .addComponent(menuprincipal))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuprincipalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuprincipalActionPerformed
        MenuPrincipal MP = new MenuPrincipal();
        MP.toFront();
        MP.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_menuprincipalActionPerformed

    private void BBUSCARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBUSCARActionPerformed
        id=Float.parseFloat(IDregistro.getText());
        try {
            Statement mod = c.createStatement();
            ResultSet md = mod.executeQuery("SELECT COUNT(*) FROM delegados"
                    + " WHERE identificacion='" + id + "'");
            while (md.next()) {
                selector = md.getInt(1);
                
                if (selector == 1) {
                    buscar();
                } else {
                    borrardatos();
                    repuestas.setText("USUARIO NO EXISTE");
                }
            }
        } catch (MySQLIntegrityConstraintViolationException e) {

            repuestas.setText("INGRESE IDENTIFICACION PARA BUSCAR");
        } catch (SQLException ex) {
            repuestas.setText("INGRESE IDENTIFICACION PARA BUSCAR");

        }

    }//GEN-LAST:event_BBUSCARActionPerformed

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarActionPerformed
        infecha();
        try {
            PreparedStatement Pps = (PreparedStatement) c.prepareStatement("INSERT INTO fecha(In_fecha,id_delegados)VALUES(?,?)");
            Pps.setString(1,fecha);
            Pps.setFloat(2,id);
            Pps.executeUpdate();
            JOptionPane.showMessageDialog(null,"SE INGRESO LA FECHA CORRECTAMENTE");
            
            System.out.println("ingreso de fecha ok");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        borrardatos();
        
    }//GEN-LAST:event_guardarActionPerformed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        borrardatos();
    }//GEN-LAST:event_borrarActionPerformed

    private void nombreregistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreregistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreregistroActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BBUSCAR;
    private javax.swing.JTextField IDregistro;
    private javax.swing.JTextField apellidosregistro;
    private javax.swing.JButton borrar;
    private javax.swing.JButton guardar;
    private com.toedter.calendar.JDateChooser in;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton menuprincipal;
    private javax.swing.JTextField nombreregistro;
    private javax.swing.JTextField repuestas;
    // End of variables declaration//GEN-END:variables

    private PreparedStatement PreparedStatement() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
