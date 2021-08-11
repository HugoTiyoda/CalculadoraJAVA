package br.com.hugotiyoda.calc.inteface;

import javax.swing.*;
import java.awt.*;

public class Botoes extends JButton {


    public Botoes(String funcao, Color cor) {
        setText(funcao);
        setOpaque(true);
        setBackground(cor);
        setFont(new Font("Arial",Font.PLAIN,18));
        setForeground(Color.white);
        setBorder(BorderFactory.createLineBorder(Color.black));



    }

}
