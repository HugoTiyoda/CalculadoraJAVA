package br.com.hugotiyoda.calc.inteface;

import br.com.hugotiyoda.calc.memory.Memory;
import br.com.hugotiyoda.calc.memory.MemoryListener;

import javax.swing.*;
import java.awt.*;


public class Display extends JPanel implements MemoryListener {

    private final JLabel label;

    public Display(){
        Memory.getInstance().addListener(this);
        setBackground(Color.darkGray);
        label = new JLabel(Memory.getInstance().getTextoAtual());
        label.setForeground(Color.white);
        label.setFont(new Font("Arial",Font.PLAIN,30));
        setLayout(new FlowLayout(FlowLayout.RIGHT,10,20));
        add(label);
    }

    @Override
    public void valorAlterado(String novoValor) {
        label.setText(novoValor);
    }
}
