package Smartphone;

import tools.imageLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Gallery extends JPanel {

    public static JPanel imgPanel = new JPanel();
    private int nbPhotos = 11;
    JScrollPane scroll = new JScrollPane();

    private static JButton jbRetour = new JButton ("Retour");
    private static JButton jbSuppr = new JButton ("Suppr");

    public Gallery (){

        setLayout(new BorderLayout());
        imgPanel.setLayout(new GridLayout(0, 2));

        JLabel label[] = new JLabel[11];
        for (int j = 1; j<nbPhotos; j++){
            String path = String.valueOf(j);
            label[j] = new imageLabel(path);
            imgPanel.add(label[j]);
            label[j].addMouseListener (new MouseAdapter() {

                public void mousePressed(MouseEvent me) {
                    imgPanel.removeAll();
                    JPanel imgzoomPanel = new Picture(path); //photo
                    imgPanel.setLayout(null);
                    imgzoomPanel.setBounds(0,0,480,745);
                    imgPanel.add(imgzoomPanel);

                    imgPanel.validate();
                    imgPanel.repaint();

                    //Bouton
                   jbRetour.addActionListener(new display.homeListener(4));

                    //jbRetour.setPreferredSize(new Dimension(150, 30));
                    //imgzoomPanel.add(jbRetour);
                    setVisible(true);
                }
            });
        }


        scroll.setViewportView(imgPanel);
        add(new JScrollPane(imgPanel));

    }

    /*class homeListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            //Via cette instruction, on passe au conteneur correspondant au nom fourni en paramètre
            cardLayout.show(content, access[i]);
        }
    }
*/



}



