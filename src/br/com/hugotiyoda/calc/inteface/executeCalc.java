package br.com.hugotiyoda.calc.inteface;

import javax.swing.*;
import java.awt.*;

public class executeCalc extends JFrame {

    public executeCalc() {
        umLayout();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(250, 375);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void umLayout() {
        Display display = new Display();
        Teclado teclado = new Teclado();

        this.setLayout(new BorderLayout());
        this.add(display, BorderLayout.NORTH);
        display.setPreferredSize(new Dimension(250, 60));
        this.add(teclado, BorderLayout.CENTER);
    }


    public static void main(String[] args) {

        new executeCalc();
    }

}
