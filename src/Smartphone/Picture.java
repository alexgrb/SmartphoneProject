package Smartphone;

import tools.imageLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Picture extends JPanel {

    JPanel imgPanel = new JPanel();
    private int nbPhotos = 11;

    public Picture (String path){

        setLayout(new BorderLayout());

        imageLabel picture = new imageLabel(path);

        imgPanel.add(picture, BorderLayout.CENTER);

        JButton back = new JButton("back");
        back.addActionListener(new display.homeListener(12));
        imgPanel.add(back, BorderLayout.NORTH);

        add(imgPanel);
    }

}
