package org.example;
import org.example.panel.ControlPanel;
import org.example.panel.DesignPanel;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public ControlPanel controlPanel;
    public DesignPanel designPanel;

    public MainFrame() {
        super("Dynamic Swing Designer");
        init();
        setVisible(true);
    }
/*
* creez cate un obiect din fiecare tip si cu ajutorul functiei add(...) o pozitionez pe fereastra setandu-i zona ferestrei
* controlPanel in partea de nord a ferestrei , iar designPanel in partea centrala a ferestrei
*/
    private void init() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        controlPanel = new ControlPanel(this);
        designPanel = new DesignPanel(this);
        add(controlPanel, BorderLayout.NORTH);
        add(designPanel, BorderLayout.CENTER);
        pack();
    }

}