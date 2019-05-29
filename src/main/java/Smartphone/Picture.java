package Smartphone;

import tools.ImageResizer;
import tools.imageLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Picture extends JPanel {

    private JButton backBTN = new JButton(""); //Génère une erreur c'est normal

    public Picture(String path){
        backBTN.setIcon(new ImageIcon("src\\main\\java\\pictures\\iconBack.png"));
        backBTN.setOpaque(false);
        backBTN.setBackground(new Color(0,true));
        backBTN.setBorder(null);
        backBTN.addActionListener(new backBTNListener());
        setLayout(null);
        backBTN.setBounds(10,10,40,40);
        add(backBTN);


       try {
            ImageResizer.resize("src\\main\\java\\pictures\\gallery\\" + path+ ".png", "src\\main\\java\\pictures\\gallery\\" + path+ "Bis.png", 470, 470);
        } catch (IOException e) {
           // e.printStackTrace();
        }

        imageLabel picture = new imageLabel(path+"Bis",2);

        picture.setBounds(1,50,470,470);
        add(picture);
    }

     class backBTNListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            removeAll();
            add(Gallery.finalPanel);
            add(Gallery.jbAdd);
            revalidate();
            repaint();
        }
    }

}
