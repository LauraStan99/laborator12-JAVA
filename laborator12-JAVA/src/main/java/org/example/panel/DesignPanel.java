package org.example.panel;
import org.example.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DesignPanel extends JPanel {
    public static final int W = 800, H = 600;
    final MainFrame frame;
/*
* cu functia setPreferredSize setam dimensiunea panoului desingPanel (avand dimensiunile predefinite)
* iar prin apelarea metodei setLayout(null) panoul va folosi pozitia absoluta a componentelor */
    public DesignPanel(MainFrame frame) {
        this.frame = frame;
        setPreferredSize(new Dimension(W, H));
        setLayout(null);
    }
/*
* aceasta metoda seteaza o pozitie random pe panoul designPanel unde urmeaza a fi pusa componenta de adaugat
* variabilele x si y iau valori numere intregi alese in mod random(reprezentand coordonatele coltului din stanga sus al componentei)
* iar in variabilele w si h sunt setate latimea si inaltimea componentei care urmeaza a se adauga
* prin apelarea metodei setBounds(x,y,w,h) se seteaza dimenziua noii componente
* iar cu ajutorul metodei add , aceasta este adaugata pe ecran   */
    public void setPositionOnPanel(JComponent newComponent) {
        Random random = new Random();
        int x = random.nextInt(W);
        int y = random.nextInt(H);
        int w = newComponent.getPreferredSize().width;
        int h = newComponent.getPreferredSize().height;
        newComponent.setBounds(x, y, w, h);
        add(newComponent);
        frame.repaint();
    }
}