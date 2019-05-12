package Smartphone;

import javax.swing.*;
import java.awt.*;

public class Gallery extends JPanel {



    public Gallery (){
       // JPanel panel = new JPanel(new GridLayout(3, 3, 2,2 ));

       // JFrame frame = new JFrame();

        //frame.add(panel);
      //  add(panel);

        setLayout(new GridLayout(3, 3, 2,2 ));
        //Ajout de chaque image dans un label
        ImageIcon image1 = new ImageIcon("src\\pictures\\1.jpg");
        JLabel label1 = new JLabel(image1);
        label1.setPreferredSize(new Dimension(500, 500));

        ImageIcon image2 = new ImageIcon("src\\pictures\\2.jpg");
        JLabel label2 = new JLabel(image2);
        label2.setPreferredSize(new Dimension(500, 500));

        ImageIcon image3 = new ImageIcon("src\\pictures\\3.jpg");
        JLabel label3 = new JLabel(image3);
        label3.setPreferredSize(new Dimension(500, 500));

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

        /*ImageIcon image10 = new ImageIcon("src\\pictures\\10.PNG");
        JLabel label10 = new JLabel(image10);

        ImageIcon image11 = new ImageIcon("src\\pictures\\11.jpg");
        JLabel label11 = new JLabel(image11);

        ImageIcon image12 = new ImageIcon("src\\pictures\\goals.jpg");
        JLabel label12 = new JLabel(image12);

        */

        /*
        // Setting up the scroll pane
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setViewportView(label1);
        scrollPane.setBounds(0, 0, 500, 500);
        frame.add(scrollPane);
*/
        // Ajout des label dans le panel
       /* panel.add(label1);
        //add(scrollPane);

        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(label6);
        panel.add(label7);
        panel.add(label8);
        panel.add(label9);

        */
        /*panel.add(label10);
        panel.add(label11);
        panel.add(label12);
*/
        add(label1);
        //add(scrollPane);

        add(label2);
        add(label3);
        add(label4);
        add(label5);
        add(label6);
        add(label7);
        add(label8);
        add(label9);
        //add(panel);

    }

}
