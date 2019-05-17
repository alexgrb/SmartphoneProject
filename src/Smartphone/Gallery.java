package Smartphone;

import tools.imageLabel;

import javax.swing.*;
import java.awt.*;

public class Gallery extends JPanel {

    JPanel imgPanel = new JPanel();
    private int nbPhotos = 11;

    public Gallery (){

        setLayout(new BorderLayout());
        imgPanel.setLayout(new GridLayout(0, 2));

        JLabel label[] = new JLabel[11];
        for (int j = 1; j<nbPhotos; j++){
            label[j] = new imageLabel(String.valueOf(j));
            imgPanel.add(label[j]);
        }

        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(imgPanel);
        add(new JScrollPane(imgPanel));

    }

}
