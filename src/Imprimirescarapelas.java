
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Imprimirescarapelas extends JFrame {

    String Nombre;
    Ingresodatos idd = new Ingresodatos();

    DrawingCanvas canvas = new DrawingCanvas();
    JButton setUpButton = new JButton("Setup"),
            printButton = new JButton("IMPRIMIR"),
            cancelButton = new JButton("CANCELAR");

    PrinterJob imprimirtrabajo = PrinterJob.getPrinterJob();
    PageFormat formatopagina = imprimirtrabajo.defaultPage();
    PrintableCanvas PrintableCanvas;
    public static List<String> mensajes;

    public Imprimirescarapelas(List<String> mensajes) {
        this.mensajes = mensajes;
        
        System.out.println(Nombre);

        getContentPane().add(canvas);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));

        setUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                setup();
            }
        });
        panel.add(setUpButton);
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                imprimir();

            }
        });
        panel.add(printButton);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent a) {
                cancel();
            }
        });
        panel.add(cancelButton);
        getContentPane().add(BorderLayout.SOUTH, panel);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 700);
        setVisible(true);
    }

    private void setup() {
        formatopagina = imprimirtrabajo.pageDialog(formatopagina);
        imprimirtrabajo.validatePage(formatopagina);

    }

    private void imprimir() {
        PrintableCanvas = new PrintableCanvas(formatopagina);
        imprimirtrabajo.setPrintable(PrintableCanvas);
        boolean ok = imprimirtrabajo.printDialog();
        if (ok) {
            try {
                imprimirtrabajo.print();
                idd.toFront();
                idd.setVisible(true);
            } catch (Exception pe) {
                System.out.println("Ocurrio una Exception de Impresion ");
                pe.printStackTrace();
            }
        }
    }

    private void cancel() {
        imprimirtrabajo.cancel();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

   
}

class DrawingCanvas extends JPanel {

    Font font = new Font("Arial", Font.BOLD, 14);
    FontMetrics fontMetrics;
    int w = 400, h = 275;

    DrawingCanvas() {
        //COLOR DE FONDO EN EL JPANEL
        setBackground(Color.white);

        //TAMAÑO DE LA VENTA EN EL JPANEL
        setSize(400, 275);

        fontMetrics = getFontMetrics(font);

    }

    public void pintarComponentes(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        pintarContenido(g2D, w, h);
    }

    public void pintarContenido(Graphics2D g2D, int w, int h) {
        g2D.setFont(font);
        g2D.drawString(Imprimirescarapelas.mensajes.get(0)+" "+Imprimirescarapelas.mensajes.get(1),
                (float) (0.5 * (w - fontMetrics.stringWidth(Imprimirescarapelas.mensajes.get(0)))),
                (float) (0.5 * h - 1.25 * fontMetrics.getHeight()));
    }

}

class PrintableCanvas implements Printable {

    DrawingCanvas canvas;
    PageFormat formatopagina;

    public PrintableCanvas(PageFormat pf) {
        formatopagina = pf;
    }

    public int print(Graphics g, PageFormat formatopagina, int pageIndex)
            throws PrinterException {
        if (pageIndex >= 1) {
            return Printable.NO_SUCH_PAGE;
        }
        Graphics2D g2D = (Graphics2D) g;
        canvas = new DrawingCanvas();
        canvas.pintarContenido(g2D, (int) formatopagina.getImageableWidth(),
                (int) formatopagina.getImageableHeight());
        return Printable.PAGE_EXISTS;

    }
}
