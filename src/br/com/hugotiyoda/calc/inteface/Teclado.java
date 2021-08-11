package br.com.hugotiyoda.calc.inteface;

import br.com.hugotiyoda.calc.inteface.Botoes;
import br.com.hugotiyoda.calc.memory.Memory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Teclado extends JPanel implements ActionListener {

    private Color DARK_GRAY = new Color(67, 67, 67);
    private Color GRAY = new Color(99, 99, 99);
    private Color RED = new Color(255, 0, 0);


    public void addBotao(String funcao, Color cor, GridBagConstraints c, int x, int y) {
        c.gridy = y;
        c.gridx = x;
        Botoes botao = new Botoes(funcao, cor);
        add(botao, c);
        botao.addActionListener(this);
    }

    public Teclado() {

        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        setLayout(layout);
        c.weightx = 1;
        c.weighty = 1;
        c.fill = GridBagConstraints.BOTH;

        addBotao("AC", DARK_GRAY, c, 0, 0);
        addBotao("±", DARK_GRAY, c, 1, 0);
        addBotao("%", DARK_GRAY, c, 2, 0);
        addBotao("/", RED, c, 3, 0);

        addBotao("7", GRAY, c, 0, 1);
        addBotao("8", GRAY, c, 1, 1);
        addBotao("9", GRAY, c, 2, 1);
        addBotao("*", RED, c, 3, 1);

        addBotao("4", GRAY, c, 0, 2);
        addBotao("5", GRAY, c, 1, 2);
        addBotao("6", GRAY, c, 2, 2);
        addBotao("-", RED, c, 3, 2);

        addBotao("1", GRAY, c, 0, 3);
        addBotao("2", GRAY, c, 1, 3);
        addBotao("3", GRAY, c, 2, 3);
        addBotao("+", RED, c, 3, 3);

        c.gridwidth = 2;
        addBotao("0", GRAY, c, 0, 4);
        c.gridwidth = 1;
        addBotao(",", GRAY, c, 2, 4);
        addBotao("=", RED, c, 3, 4);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if (e.getSource() instanceof JButton){
          JButton botao = (JButton) e.getSource();
          Memory.getInstance().comando(botao.getText());

      }
    }
}
