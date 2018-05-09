
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

public class Imprimir extends javax.swing.JFrame implements Printable {

    PrinterJob job = PrinterJob.getPrinterJob();
    PageFormat pf = job.defaultPage();

    public Imprimir() {
        initComponents();
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cuerpo = new javax.swing.JPanel();
        recnombres = new javax.swing.JTextField();
        recregional = new javax.swing.JTextField();
        recuniversiadad = new javax.swing.JTextField();
        redelegados = new javax.swing.JTextField();
        recimagen = new javax.swing.JPanel();
        recid = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        cuerpo.setBackground(new java.awt.Color(255, 255, 255));
        cuerpo.setPreferredSize(new java.awt.Dimension(200, 350));

        recnombres.setEditable(false);
        recnombres.setBackground(new java.awt.Color(255, 255, 255));
        recnombres.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        recnombres.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        recnombres.setBorder(null);

        recregional.setEditable(false);
        recregional.setBackground(new java.awt.Color(255, 255, 255));
        recregional.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        recregional.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        recregional.setBorder(null);

        recuniversiadad.setEditable(false);
        recuniversiadad.setBackground(new java.awt.Color(255, 255, 255));
        recuniversiadad.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        recuniversiadad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        recuniversiadad.setBorder(null);

        redelegados.setEditable(false);
        redelegados.setBackground(new java.awt.Color(255, 255, 255));
        redelegados.setFont(new java.awt.Font("Cambria", 1, 14)); // NOI18N
        redelegados.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        redelegados.setBorder(null);

        recimagen.setBackground(new java.awt.Color(255, 255, 255));
        recimagen.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout recimagenLayout = new javax.swing.GroupLayout(recimagen);
        recimagen.setLayout(recimagenLayout);
        recimagenLayout.setHorizontalGroup(
            recimagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 126, Short.MAX_VALUE)
        );
        recimagenLayout.setVerticalGroup(
            recimagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 130, Short.MAX_VALUE)
        );

        recid.setEditable(false);
        recid.setBackground(new java.awt.Color(255, 255, 255));
        recid.setFont(new java.awt.Font("Cambria", 1, 11)); // NOI18N
        recid.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        recid.setBorder(null);
        recid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recidActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout cuerpoLayout = new javax.swing.GroupLayout(cuerpo);
        cuerpo.setLayout(cuerpoLayout);
        cuerpoLayout.setHorizontalGroup(
            cuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(redelegados, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(recuniversiadad, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(recregional, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(recnombres)
            .addComponent(recid, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cuerpoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(recimagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145))
        );
        cuerpoLayout.setVerticalGroup(
            cuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cuerpoLayout.createSequentialGroup()
                .addGap(192, 192, 192)
                .addComponent(recnombres, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(recregional, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(recuniversiadad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(redelegados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(recimagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(recid, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );

        jButton1.setText("IMPRIMIR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("ATRAS");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("SETUP");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, 420, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(67, 67, 67)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cuerpo, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void recidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recidActionPerformed

    }//GEN-LAST:event_recidActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {

            job.setPrintable(this);
            job.printDialog();
            job.print();
            dispose();
        } catch (Exception e) {
            System.out.println(e);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        pf=job.pageDialog(pf);
        job.validatePage(pf);
    }//GEN-LAST:event_jButton3ActionPerformed
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Imprimir dialog = new Imprimir();
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cuerpo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    public static javax.swing.JTextField recid;
    public static javax.swing.JPanel recimagen;
    public static javax.swing.JTextField recnombres;
    public static javax.swing.JTextField recregional;
    public static javax.swing.JTextField recuniversiadad;
    public static javax.swing.JTextField redelegados;
    // End of variables declaration//GEN-END:variables
 public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }
        Graphics2D g2d = (Graphics2D) graphics;
        //Punto donde empezará a imprimir dentro la pagina (100, 50)
        g2d.translate(pageFormat.getImageableX() + 50,
                pageFormat.getImageableY() + 50);
        g2d.scale(0.75, 0.75); //Reducción de la impresión al 50%
        cuerpo.printAll(graphics);
        return PAGE_EXISTS;
    }
}
