package Smartphone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class Gallery extends JPanel {

    JPanel imgPanel = new JPanel();

    public Gallery (){

        setLayout(new BorderLayout());

        imgPanel.setLayout(new GridLayout(0, 2));



        String [] tabFileName = {"src\\pictures\\1.jpg",
                "src\\pictures\\2.jpg",
                "src\\pictures\\3.jpg",
                "src\\pictures\\4.jpg",
                "src\\pictures\\5.jpg",
                "src\\pictures\\6.jpg",
                "src\\pictures\\7.jpg",
                "src\\pictures\\8.jpg",
                "src\\pictures\\9.jpg",
                "src\\pictures\\10.PNG",
                "src\\pictures\\11.jpg",
                "src\\pictures\\goals.jpg"};

        JLabel label[] = new JLabel[12];
        ImageIcon img [] = new ImageIcon[12];

        for (int i = 0 ; i < tabFileName.length ; i++){
            img [i] = new ImageIcon(tabFileName[i]);
            label[i] = new JLabel(img [i]);
            imgPanel.add(label[i]);
        }



        /*
        //Ajout de chaque image dans un label
        ImageIcon image1 = new ImageIcon("src\\pictures\\1.jpg");
        JLabel label1 = new JLabel(image1);


        ImageIcon image2 = new ImageIcon("src\\pictures\\2.jpg");
        JLabel label2 = new JLabel(image2);


        ImageIcon image3 = new ImageIcon("src\\pictures\\3.jpg");
        JLabel label3 = new JLabel(image3);
        //label3.setPreferredSize(new Dimension(160, 500));

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

        ImageIcon image10 = new ImageIcon("src\\pictures\\10.PNG");
        JLabel label10 = new JLabel(image10);

        ImageIcon image11 = new ImageIcon("src\\pictures\\11.jpg");
        JLabel label11 = new JLabel(image11);

        ImageIcon image12 = new ImageIcon("src\\pictures\\goals.jpg");
        JLabel label12 = new JLabel(image12);

*/


        /*
        ImageIcon image12 = new ImageIcon("src\\pictures\\goals.jpg");
        JLabel label12 = new JLabel(image12);
        label12.addMouseListener(new MouseListener());
        imgPanel.add(label12);
*/





        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(imgPanel);
        add(new JScrollPane(imgPanel));


    }

}
