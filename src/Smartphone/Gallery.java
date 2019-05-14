package Smartphone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Image;
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
        ImageIcon image12 = new ImageIcon("src\\pictures\\goals.jpg");
        JLabel label12 = new JLabel(image12);

        imgPanel.add(label12);

        label12.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                Image scaleImage = image12.getImage().getScaledInstance(280, 280,Image.SCALE_DEFAULT);                System.out.println("wooooow mdr");
            }
        });

*/






        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(imgPanel);
        add(new JScrollPane(imgPanel));


    }

}
