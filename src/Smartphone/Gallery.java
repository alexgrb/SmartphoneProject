package Smartphone;

import javax.swing.*;
import java.awt.*;

public class Gallery extends JPanel {

    JPanel imgPanel = new JPanel();

    public Gallery (){

        setLayout(new BorderLayout());

        //Ajout d'un panel global
        //JPanel pane = new JPanel();
        imgPanel.setLayout(new GridLayout(0, 3));
       // pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));





        //Ajout de chaque image dans un label
        ImageIcon image1 = new ImageIcon("src\\pictures\\1.jpg");
        JLabel label1 = new JLabel(image1);
        label1.setPreferredSize(new Dimension(160, 500));

        ImageIcon image2 = new ImageIcon("src\\pictures\\2.jpg");
        JLabel label2 = new JLabel(image2);
        label2.setPreferredSize(new Dimension(160, 500));

        ImageIcon image3 = new ImageIcon("src\\pictures\\3.jpg");
        JLabel label3 = new JLabel(image3);
        label3.setPreferredSize(new Dimension(160, 500));

        ImageIcon image4 = new ImageIcon("src\\pictures\\4.jpg");
        JLabel label4 = new JLabel(image4);

        ImageIcon image5 = new ImageIcon("src\\pictures\\5.jpg");
        JLabel label5 = new JLabel(image5);

        ImageIcon image6 = new ImageIcon("src\\pictures\\6.jpg");
        JLabel label6 = new JLabel(image6);

        ImageIcon image7 = new ImageIcon("src\\pictures\\7.jpg");
        JLabel label7 = new JLabel(image7);

        ImageIcon image8 = new ImageIcon("src\\pictures\\8.jpg");
        JLabel label8 = new JLabel(image8);

        ImageIcon image9 = new ImageIcon("src\\pictures\\9.jpg");
        JLabel label9 = new JLabel(image9);

        ImageIcon image10 = new ImageIcon("src\\pictures\\9.PNG");
        JLabel label10 = new JLabel(image10);

        ImageIcon image11 = new ImageIcon("src\\pictures\\9.jpg");
        JLabel label11 = new JLabel(image11);

        ImageIcon image12 = new ImageIcon("src\\pictures\\9.jpg");
        JLabel label12 = new JLabel(image12);



        /*
        // Setting up the scroll pane
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setViewportView(label1);
        scrollPane.setBounds(0, 0, 500, 500);
        frame.add(scrollPane);
*/


        //Ajout des images au panel respectif
        imgPanel.add(label1);
        imgPanel.add(label2);
        imgPanel.add(label3);
        imgPanel.add(label4);
        imgPanel.add(label5);
        imgPanel.add(label6);
        imgPanel.add(label7);
        imgPanel.add(label8);
        imgPanel.add(label9);
        imgPanel.add(label10);
        imgPanel.add(label11);
        imgPanel.add(label12);

        //add(pane, new GridLayout(3,3));
       // add(pane, BorderLayout.NORTH);
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(imgPanel);
        add(new JScrollPane(imgPanel));

        //Jpanel.setViewport


        //add(panel);

    }

}
