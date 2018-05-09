
import com.barcodelib.barcode.QRCode;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Ingresodatos extends javax.swing.JFrame {

    int udm = 0, resol = 72, rot = 0;
    float mi = 0.000f, md = 0.000f, ms = 0.000f, min = 0.000f, tam = 5.00f;
    private String prenombre, senombre, preapeellido, seapellido, seregion, seuniverisda, roll, correo;
    String año, mes, dia, horas, minutos, segundos, fecha;
    long id, id2, cell;
    int selector, numeid;
    conexionBD con = new conexionBD();
    Connection c = con.getConnection();

    public Ingresodatos() {
        initComponents();
        setLocationRelativeTo(null);
        idcodigo.requestFocus();
        repuesta.setEnabled(false);
        repuesta.setEditable(false);
        zona.setSelectedItem(null);
        universidad.setSelectedItem(null);
        Croll.setSelectedItem(null);
        bmodificar.setEnabled(false);
        che.setState(false);

    }

    public void generarQR() {

        try {
            QRCode qr = new QRCode();
            String texto = idcodigo.getText();
            qr.setData(texto);
            qr.setDataMode(QRCode.MODE_NUMERIC);
            qr.setUOM(udm);
            qr.setLeftMargin(mi);
            qr.setRightMargin(md);
            qr.setTopMargin(ms);
            qr.setBottomMargin(min);
            qr.setResolution(resol);
            qr.setRotate(rot);
            qr.setModuleSize(tam);
            qr.renderBarcode(System.getProperty("user.dir") + "codiQR.png");

            //  File arch = new File("C:/Users/Aloia/Desktop/alexza/CODIGOQR/codiQR.png");
            // Desktop d = Desktop.getDesktop();
            // d.open(arch);
            repuesta.setText("QR GENERADO");
        } catch (Exception e) {
            System.out.println("Error " + e);
            repuesta.setText("UBICACION DEL ARCHIVO codiQR NO ENCONTRADO");
        }

        //Se guarde el archivo
        imagenqr.setLayout(new BorderLayout());
        JLabel label = new JLabel();
        imagenqr.add(label, BorderLayout.CENTER);
        ImageIcon imagen = new ImageIcon(System.getProperty("user.dir") + "codiQR.png");
        imagen.getImage().flush(); // ¡LÍNEA CLAVE!
        label.setIcon(imagen);

    }

    public void buscar() {

        try {
            Statement mod = c.createStatement();
            ResultSet md = mod.executeQuery("SELECT pr_nombre,se_nombre,pr_apellido,se_apellido,regional,universidad,roll,Fecha_registro,celular,email FROM delegados"
                    + " WHERE identificacion='" + id + "'");

            while (md.next()) {
                primernombre.setText(md.getString(1));
                segundonombre.setText(md.getString(2));
                primerapeellido.setText(md.getString(3));
                segundoapellido.setText(md.getString(4));
                zona.setSelectedItem(md.getString(5));
                universidad.setSelectedItem(md.getString(6));
                Croll.setSelectedItem(md.getString(7));
                re_fecha.setDate(md.getTimestamp(8));
                celular.setText(md.getString(9));
                email.setText(md.getString(10));

            }

            generarQR();
            repuesta.setText("USUARIO ENCONTRADO");

        } catch (SQLException e) {
            repuesta.setText("USUARIO NO ENCONTRADO");
            JOptionPane.showMessageDialog(null, "NO HAY CONEXION CON LA BASE DE DATOS");
        }
    }

    public void insertardatos() {
        guardardatos();
        generarQR();
        tomafecha();
        try {
            PreparedStatement Pps = (PreparedStatement) c.prepareStatement("INSERT INTO delegados"
                    + "(identificacion,pr_nombre,se_nombre,pr_apellido,se_apellido,regional,universidad,roll,Fecha_registro,celular,email)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?)");

            Pps.setLong(1, id);
            Pps.setString(2, prenombre);
            Pps.setString(3, senombre);
            Pps.setString(4, preapeellido);
            Pps.setString(5, seapellido);
            Pps.setString(6, seregion);
            Pps.setString(7, seuniverisda);
            Pps.setString(8, roll);
            Pps.setString(9, fecha);
            Pps.setLong(10, cell);
            Pps.setString(11, correo);

            Pps.executeUpdate();

            BREGISTRAR.setEnabled(true);
            repuesta.setText("SE GUARDO CON EXITO");
            JOptionPane.showMessageDialog(null, "SE GUARDO EL REGISTRO CON EXITO");

        } catch (MySQLIntegrityConstraintViolationException e) {
            buscar();
            repuesta.setText("USUARIO YA EXISTE");
        } catch (HeadlessException | SQLException e) {
            System.out.println(e);
            buscar();
            repuesta.setText("USUARIO YA EXISTE");
        }
    }

    public void guardardatos() {
        id = Long.parseLong(idcodigo.getText());
        prenombre = primernombre.getText();
        senombre = segundonombre.getText();
        preapeellido = primerapeellido.getText();
        seapellido = segundoapellido.getText();
        cell = Long.parseLong(celular.getText());
        correo = email.getText();
        seregion = (String) zona.getSelectedItem();
        seuniverisda = (String) universidad.getSelectedItem();
        roll = (String) Croll.getSelectedItem();
    }

    public void tomafecha() {
        año = Integer.toString(re_fecha.getCalendar().get(Calendar.YEAR));
        mes = Integer.toString(re_fecha.getCalendar().get(Calendar.MONTH));
        dia = Integer.toString(re_fecha.getCalendar().get(Calendar.DAY_OF_MONTH));
        horas = Integer.toString(re_fecha.getCalendar().get(Calendar.HOUR));
        minutos = Integer.toString(re_fecha.getCalendar().get(Calendar.MINUTE));
        segundos = Integer.toString(re_fecha.getCalendar().get(Calendar.SECOND));
        fecha = (año + "-" + mes + "-" + dia + " " + horas + ":" + minutos + ":" + segundos);
    }

    public void borrardatos() {
        idcodigo.setText("");
        primernombre.setText("");
        segundonombre.setText("");
        primerapeellido.setText("");
        segundoapellido.setText("");
        celular.setText("");
        email.setText("");
        zona.setSelectedItem(null);
        universidad.setSelectedItem(null);
        Croll.setSelectedItem(null);
        
    }

    public void modificardatos() {
        guardardatos();
        tomafecha();
        String sqlin = "UPDATE delegados SET  pr_nombre='" + prenombre + "', se_nombre='" + senombre + "',pr_apellido='" + preapeellido + "',se_apellido='" + seapellido + "',regional='" + seregion + "',universidad='" + seuniverisda + "',roll='" + roll + "',Fecha_registro='" + fecha + "'"
                + "WHERE identificacion =" + id + "";

        try {

            PreparedStatement moda = (PreparedStatement) c.prepareStatement(sqlin);
            moda.executeUpdate();

            repuesta.setText("ACTUALIZACION OK");
            JOptionPane.showMessageDialog(null, "USUARIO ACTUALIZADO");
        } catch (Exception e) {

            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR NO SE PUDO HACER LA MODIFICACION");
        }
    }

    public void modificarid() {

        id2 = Long.parseLong(idcodigo.getText());

        String sqlinid = "UPDATE delegados SET  identificacion=" + id2 + " WHERE identificacion =" + id + "";
        System.out.println(sqlinid);
        try {

            PreparedStatement moda = (PreparedStatement) c.prepareStatement(sqlinid);
            moda.executeUpdate();
            repuesta.setText("ACTUALIZACION OK");
            JOptionPane.showMessageDialog(null, "USUARIO ACTUALIZADO");
            che.setState(false);
        } catch (Exception e) {

            System.out.println(e);
            JOptionPane.showMessageDialog(null, "ERROR NO SE PUDO HACER LA MODIFICACION");
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        primernombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        segundonombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        primerapeellido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        segundoapellido = new javax.swing.JTextField();
        BMENUPRINCIPAL = new javax.swing.JButton();
        universiadad = new javax.swing.JLabel();
        universidad = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        zona = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        idcodigo = new javax.swing.JTextField();
        BREGISTRAR = new javax.swing.JButton();
        repuesta = new javax.swing.JTextField();
        BIMPRIMIR = new javax.swing.JButton();
        BBUSCAR = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        BMODIFICAR = new javax.swing.JButton();
        imagenqr = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        Croll = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        bmodificar = new javax.swing.JButton();
        re_fecha = new com.toedter.calendar.JDateChooser();
        che = new java.awt.Checkbox();
        jButton1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        celular = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("PRIMER NOMBRE:");

        primernombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                primernombreActionPerformed(evt);
            }
        });
        primernombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                primernombreKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("SEGUNDO NOMBRE:");

        segundonombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundonombreActionPerformed(evt);
            }
        });
        segundonombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundonombreKeyTyped(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("PRIMER APELLIDO:");

        primerapeellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                primerapeellidoActionPerformed(evt);
            }
        });
        primerapeellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                primerapeellidoKeyTyped(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("SEGUNDO APELLIDO:");

        segundoapellido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                segundoapellidoActionPerformed(evt);
            }
        });
        segundoapellido.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                segundoapellidoKeyTyped(evt);
            }
        });

        BMENUPRINCIPAL.setFont(new java.awt.Font("Cambria", 1, 11)); // NOI18N
        BMENUPRINCIPAL.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/gohome.gif"))); // NOI18N
        BMENUPRINCIPAL.setText("MENU PRINCIPAL");
        BMENUPRINCIPAL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BMENUPRINCIPALActionPerformed(evt);
            }
        });

        universiadad.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        universiadad.setText("UNIVERSIADA:");

        universidad.setFont(new java.awt.Font("Cambria", 1, 11)); // NOI18N
        universidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UNIVERSIDAD DEL CAUCA", "Item 2", "Item 3", "Item 4" }));
        universidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                universidadActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("REGION:");

        zona.setFont(new java.awt.Font("Cambria", 1, 11)); // NOI18N
        zona.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AMAZONIA", "ANDINA", "CARIBE", "PACIFICO", "ORINOQUIA" }));
        zona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zonaActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("IDENTIFICACION:");

        idcodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idcodigoActionPerformed(evt);
            }
        });

        BREGISTRAR.setFont(new java.awt.Font("Cambria", 1, 11)); // NOI18N
        BREGISTRAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/add.png"))); // NOI18N
        BREGISTRAR.setText("REGISTRAR");
        BREGISTRAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BREGISTRARActionPerformed(evt);
            }
        });

        repuesta.setBackground(new java.awt.Color(0, 153, 153));
        repuesta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        repuesta.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        repuesta.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        BIMPRIMIR.setFont(new java.awt.Font("Cambria", 1, 11)); // NOI18N
        BIMPRIMIR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/fileprint.gif"))); // NOI18N
        BIMPRIMIR.setText("IMPRIMIR");
        BIMPRIMIR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BIMPRIMIRActionPerformed(evt);
            }
        });

        BBUSCAR.setFont(new java.awt.Font("Cambria", 1, 11)); // NOI18N
        BBUSCAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/application_form_magnify.png"))); // NOI18N
        BBUSCAR.setText("BUSCAR");
        BBUSCAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBUSCARActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("CODIGO QR");

        BMODIFICAR.setFont(new java.awt.Font("Cambria", 1, 11)); // NOI18N
        BMODIFICAR.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/database_refresh.png"))); // NOI18N
        BMODIFICAR.setText("MODIFICAR");
        BMODIFICAR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BMODIFICARActionPerformed(evt);
            }
        });

        imagenqr.setBackground(new java.awt.Color(0, 0, 0));
        imagenqr.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout imagenqrLayout = new javax.swing.GroupLayout(imagenqr);
        imagenqr.setLayout(imagenqrLayout);
        imagenqrLayout.setHorizontalGroup(
            imagenqrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        imagenqrLayout.setVerticalGroup(
            imagenqrLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("ROLL:");

        Croll.setFont(new java.awt.Font("Cambria", 1, 11)); // NOI18N
        Croll.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DELEGADO", "DELEGADA" }));
        Croll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrollActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("FECHA DE REGISTRO:");

        bmodificar.setFont(new java.awt.Font("Cambria", 1, 11)); // NOI18N
        bmodificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/database_refresh.png"))); // NOI18N
        bmodificar.setText("MODIFICAR IDENTIFICACION");
        bmodificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bmodificarActionPerformed(evt);
            }
        });

        re_fecha.setDateFormatString("yyyy/MM/dd HH:mm:ss");

        che.setFont(new java.awt.Font("Cambria", 1, 12)); // NOI18N
        che.setLabel("confirmacion");
        che.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cheMouseClicked(evt);
            }
        });
        che.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cheKeyPressed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Cambria", 1, 11)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/editdelete.gif"))); // NOI18N
        jButton1.setText("BORRAR DATOS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("CELULAR");

        celular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celularActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("E-MAIL");

        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BREGISTRAR, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BIMPRIMIR, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BMODIFICAR, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(repuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BMENUPRINCIPAL, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(146, 146, 146)
                                .addComponent(jLabel8))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(116, 116, 116)
                                .addComponent(imagenqr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel9)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel7))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(idcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(zona, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(primerapeellido)
                                                    .addComponent(primernombre)
                                                    .addComponent(celular, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(Croll, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(BBUSCAR))))
                                .addComponent(jLabel6))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel10)
                                    .addGap(18, 18, 18)
                                    .addComponent(re_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel12))
                                        .addGap(22, 22, 22)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(bmodificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(segundoapellido)
                                            .addComponent(segundonombre)
                                            .addComponent(che, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(email)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(universiadad)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(universidad, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addContainerGap(37, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addGap(66, 66, 66)))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(bmodificar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(BBUSCAR)
                    .addComponent(che, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(primernombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(segundonombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(segundoapellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(primerapeellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(celular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(zona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(universiadad)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(Croll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel10))
                            .addComponent(jLabel9)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(universidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(re_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BREGISTRAR, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(repuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BIMPRIMIR, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BMODIFICAR, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BMENUPRINCIPAL, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(imagenqr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iconos/status_online.png"))); // NOI18N
        jLabel1.setText("DELEGADOS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BMODIFICARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BMODIFICARActionPerformed

        modificardatos();
    }//GEN-LAST:event_BMODIFICARActionPerformed

    private void BBUSCARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBUSCARActionPerformed
        id = Long.parseLong(idcodigo.getText());

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
                    repuesta.setText("USUARIO NO EXISTE");
                }

                che.setState(false);
            }

        } catch (MySQLIntegrityConstraintViolationException e) {

            repuesta.setText("INGRESE IDENTIFICACION PARA BUSCAR");
        } catch (SQLException ex) {
            repuesta.setText("INGRESE IDENTIFICACION PARA BUSCAR");

        }

    }//GEN-LAST:event_BBUSCARActionPerformed

    private void BIMPRIMIRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BIMPRIMIRActionPerformed
        guardardatos();
        /*
        List<String> mensajes = new ArrayList<String>();

        mensajes.add(prenombre);
        mensajes.add(senombre);
        mensajes.add(preapeellido);
        mensajes.add(seapellido);
        Imprimirescarapelas ime = new Imprimirescarapelas(mensajes);
        ime.toFront();
        ime.setVisible(true);
        this.setVisible(false);
         */
        Imprimir imp = new Imprimir();
        imp.toFront();
        imp.setVisible(true);

        Imprimir.recnombres.setText(prenombre + " " + senombre + " " + preapeellido + " " + seapellido);
        Imprimir.recregional.setText(seregion);
        Imprimir.recuniversiadad.setText(seuniverisda);

        Imprimir.recimagen.setLayout(new BorderLayout());
        JLabel label = new JLabel();
        Imprimir.recimagen.add(label, BorderLayout.CENTER);
        ImageIcon reimagen = new ImageIcon(System.getProperty("user.dir") + "codiQR.png");
        reimagen.getImage().flush(); // ¡LÍNEA CLAVE!
        label.setIcon(reimagen);

        Imprimir.redelegados.setText(roll);

        numeid = (int) id;
        String fid = String.valueOf(numeid);
        Imprimir.recid.setText(fid);


    }//GEN-LAST:event_BIMPRIMIRActionPerformed

    private void BREGISTRARActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BREGISTRARActionPerformed
        insertardatos();
        guardardatos();
      
    }//GEN-LAST:event_BREGISTRARActionPerformed

    private void idcodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idcodigoActionPerformed

    }//GEN-LAST:event_idcodigoActionPerformed

    private void zonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zonaActionPerformed
        seregion = (String) zona.getSelectedItem();
        universiadad.requestFocus();
    }//GEN-LAST:event_zonaActionPerformed

    private void universidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_universidadActionPerformed
        seuniverisda = (String) universidad.getSelectedItem();
        Croll.requestFocus();
    }//GEN-LAST:event_universidadActionPerformed

    private void BMENUPRINCIPALActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BMENUPRINCIPALActionPerformed
        MenuPrincipal MP = new MenuPrincipal();
        MP.toFront();
        MP.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_BMENUPRINCIPALActionPerformed

    private void segundoapellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundoapellidoKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }
    }//GEN-LAST:event_segundoapellidoKeyTyped

    private void segundoapellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundoapellidoActionPerformed
        seapellido = segundoapellido.getText();
        celular.requestFocus();
    }//GEN-LAST:event_segundoapellidoActionPerformed

    private void primerapeellidoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_primerapeellidoKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }
    }//GEN-LAST:event_primerapeellidoKeyTyped

    private void primerapeellidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_primerapeellidoActionPerformed
        preapeellido = primerapeellido.getText();
        segundoapellido.requestFocus();
    }//GEN-LAST:event_primerapeellidoActionPerformed

    private void segundonombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_segundonombreKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }
    }//GEN-LAST:event_segundonombreKeyTyped

    private void segundonombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_segundonombreActionPerformed
        senombre = segundonombre.getText();
        primerapeellido.requestFocus();
    }//GEN-LAST:event_segundonombreActionPerformed

    private void primernombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_primernombreKeyTyped
        char c = evt.getKeyChar();
        if (Character.isLowerCase(c)) {
            String cad = ("" + c).toUpperCase();
            c = cad.charAt(0);
            evt.setKeyChar(c);
        }
    }//GEN-LAST:event_primernombreKeyTyped

    private void primernombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_primernombreActionPerformed
        prenombre = primernombre.getText();
        segundonombre.requestFocus();
    }//GEN-LAST:event_primernombreActionPerformed

    private void cheKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cheKeyPressed
        bmodificar.setEnabled(true);
        JOptionPane.showMessageDialog(null, "CONFIRMRA QUE QUIERE CAMBIAR LA IDENTIFICACION");
    }//GEN-LAST:event_cheKeyPressed

    private void cheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cheMouseClicked
        bmodificar.setEnabled(true);
        JOptionPane.showMessageDialog(null, "CONFIRMRA QUE QUIERE CAMBIAR LA IDENTIFICACION");
    }//GEN-LAST:event_cheMouseClicked

    private void bmodificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bmodificarActionPerformed

        modificarid();
        generarQR();
        bmodificar.setEnabled(false);
        che.setState(false);
    }//GEN-LAST:event_bmodificarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        borrardatos();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void celularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celularActionPerformed
       email.requestFocus();
    }//GEN-LAST:event_celularActionPerformed

    private void CrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrollActionPerformed
        re_fecha.requestFocus();
    }//GEN-LAST:event_CrollActionPerformed

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        zona.requestFocus();
    }//GEN-LAST:event_emailActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BBUSCAR;
    private javax.swing.JButton BIMPRIMIR;
    private javax.swing.JButton BMENUPRINCIPAL;
    private javax.swing.JButton BMODIFICAR;
    private javax.swing.JButton BREGISTRAR;
    private javax.swing.JComboBox<String> Croll;
    private javax.swing.JButton bmodificar;
    private javax.swing.JTextField celular;
    private java.awt.Checkbox che;
    private javax.swing.JTextField email;
    private javax.swing.JTextField idcodigo;
    private javax.swing.JPanel imagenqr;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField primerapeellido;
    private javax.swing.JTextField primernombre;
    private com.toedter.calendar.JDateChooser re_fecha;
    private javax.swing.JTextField repuesta;
    private javax.swing.JTextField segundoapellido;
    private javax.swing.JTextField segundonombre;
    private javax.swing.JLabel universiadad;
    private javax.swing.JComboBox<String> universidad;
    private javax.swing.JComboBox<String> zona;
    // End of variables declaration//GEN-END:variables
}
