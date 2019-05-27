package Smartphone;

import tools.imageLabel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Gallery extends JPanel {

    JPanel imgzoomPanel = new Picture("");
    public static JPanel imgPanel = new JPanel();
    private int nbPhotos = 11;
    JScrollPane scroll = new JScrollPane();
    private static JButton jbRetour = new JButton ("Retour");
    private static JButton jbSuppr = new JButton ("Suppr");

    public Gallery (){

        setLayout(new BorderLayout());
        imgPanel.setLayout(new GridLayout(0, 2));
        Border blackline = BorderFactory.createLineBorder(Color.black);
        JLabel label[] = new JLabel[11];
        for (int j = 1; j<nbPhotos; j++){ //rempalcer 6 par nbPhotos
            String path = String.valueOf(j);
            label[j] = new imageLabel(path);
            imgPanel.add(label[j]);
            label[j].addMouseListener(new MouseAdapter() {
                                           public void mousePressed(MouseEvent me) {
                                               if(imgzoomPanel.isVisible()) {
                                                   imgzoomPanel.setVisible(false);
                                               }
                                               else {
                                                   //remove(imgzoomPanel);
                                                   imgzoomPanel = new Picture(path);
                                                   add(imgzoomPanel);
                                                   imgzoomPanel.setVisible(true);
                                               }
                                           }
                                       }
            );
            label[j].setBorder(blackline);
        }
        scroll.setViewportView(imgPanel);
        add(new JScrollPane(imgPanel));
    }
}



