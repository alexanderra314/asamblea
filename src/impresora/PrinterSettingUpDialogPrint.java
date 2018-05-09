
package impresora;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PrinterSettingUpDialogPrint extends JFrame {

    DrawingCanvas canvas = new DrawingCanvas();
    JButton setUpButton = new JButton("Page Setup"), 
            printButton = new JButton("Print"), 
            cancelButton = new JButton("Cancel");

    PrinterJob printJob = PrinterJob.getPrinterJob();
    PageFormat pageFormat = printJob.defaultPage();
    PrintableCanvas printableCanvas;

    public PrinterSettingUpDialogPrint() {
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
                print();
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
        setSize(400, 275);
        setVisible(true);
    }

    private void setup() {
        pageFormat = printJob.pageDialog(pageFormat);
        printJob.validatePage(pageFormat);
    }

    private void print() {
        printableCanvas = new PrintableCanvas(pageFormat);
        printJob.setPrintable(printableCanvas);

        boolean ok = printJob.printDialog();
        if (ok) {
            try {
                printJob.print();
            } catch (Exception pe) {
                System.out.println("Printing Exception Occured!");
                pe.printStackTrace();
            }
        }

    }

    private void cancel() {
        printJob.cancel();

    }

    public static void main(String arg[]) {
        new PrinterSettingUpDialogPrint();
    }
}

class DrawingCanvas extends JPanel {

    Font font = new Font("Dialog", Font.BOLD, 50);
    FontMetrics fontMetrics;
    int w = 400, h = 275;

    DrawingCanvas() {
        setBackground(Color.white);
        setSize(400, 275);
        fontMetrics = getFontMetrics(font);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        paintContent(g2D, w, h);
    }

    public void paintContent(Graphics2D g2D, int w, int h) {
        g2D.setFont(font);

        g2D.drawString("Java 2D", 
                (float) (0.5 * (w - fontMetrics.stringWidth("Java 2D"))),
                (float) (0.5 * h - 1.25 * fontMetrics.getHeight()));
        
        g2D.setFont(new Font("Arial", Font.PLAIN, 24));
        g2D.drawString("Hola mundo", 200, 350);
    }
}

class PrintableCanvas implements Printable {

    DrawingCanvas canvas;
    PageFormat pageFormat;

    public PrintableCanvas(PageFormat pf) {
        pageFormat = pf;
    }

    public int print(Graphics g, PageFormat pageFormat, int pageIndex)
            throws PrinterException {
        if (pageIndex >= 1) {
            return Printable.NO_SUCH_PAGE;
        }
        Graphics2D g2D = (Graphics2D) g;
        canvas = new DrawingCanvas();
        canvas.paintContent(g2D, (int) pageFormat.getImageableWidth(),
                (int) pageFormat.getImageableHeight());
        return Printable.PAGE_EXISTS;
    }
}
