package Smartphone;

import tools.imageLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

public class Picture extends JPanel {

    private static JPanel picPanel = new JPanel();
    private int nbPhotos = 11;

    public Picture(String path){

        setLayout(null);

        imageLabel picture = new imageLabel(path);

        picture.setBounds(30,30,350,500);
        add(picture);
    }

}
