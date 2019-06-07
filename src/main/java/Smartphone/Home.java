package Smartphone;

import net.miginfocom.swing.MigLayout;

import tools.imageButton;
import tools.imageLabel;
import tools.textResizer;

import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static Smartphone.display.maxApp;
import static Smartphone.display.picDirectory;

public class Home extends JPanel {

    JButton[] appButton = new JButton[maxApp-1];
    private JPanel homeDisplay = new JPanel();


    public Home() {

        // setOpaque(false);
        JLabel j = new imageLabel("homeBackground");
        j.setBounds(0,0,480,800);

        JButton closeButton = new imageButton();
        closeButton.setIcon(new ImageIcon(picDirectory+"iconOff.png"));
        closeButton.setBounds(404,10,64,64);

        setLayout(null);
        homeDisplay.setLayout(new MigLayout("wrap 2"));

        homeDisplay.setOpaque(true);

        for (int i=0 ; i<appButton.length;i++){
            //  appButton[i] = new JButton();
            appButton[i] = new imageButton(); //Cela va rendre le fond transparent
            appButton[i].addActionListener(new display.homeListener((i+1))); //Ajoute l'action listener afin de pouvoir naviguer entre les appli
        }
        appButton[0].setIcon(new ImageIcon(picDirectory+"iconWeatherBis.png"));
        appButton[1].setIcon(new ImageIcon(picDirectory+"iconContactBis.png"));
        appButton[2].setIcon(new ImageIcon(picDirectory+"iconCalculetteBis.png"));
        appButton[3].setIcon(new ImageIcon(picDirectory+"iconGalleryBis.png"));


        setBorder(new BevelBorder(BevelBorder.RAISED));
        setPreferredSize(new Dimension(this.getWidth(), 30));

        final DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        int interval = 1000; // 1000 ms
        Calendar now = Calendar.getInstance();
        final JLabel currentTime = new JLabel(dateFormat.format(now.getTime()));


        currentTime.setBounds(10,0,200,30);
        new textResizer(currentTime,80,1); //Le 1 nous permet de déclencher le texte en gras



        homeDisplay.add(currentTime, "span 2 2, gapleft 30" ); // Wrap to next row
        homeDisplay.add(appButton[0]);
        homeDisplay.add(appButton[1]); // The component will span 2x2 cells.

        homeDisplay.add(appButton[2]);
        homeDisplay.add(appButton[3]); // Note that it "jumps over" the occupied cells.


        homeDisplay.setBounds(0,35,480,765);
        homeDisplay.setOpaque(false);




        closeButton.addMouseListener(new MouseEvent());
        // homeDisplay.add(closeButton);

        new Timer(interval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar now = Calendar.getInstance();
                currentTime.setText(dateFormat.format(now.getTime()));
            }
        }).start();

        add(homeDisplay);
        add(closeButton);
        add(j);


    }
    public class MouseEvent implements MouseListener {
        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            System.exit(0);
        }
        @Override
        public void mousePressed(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseReleased(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseEntered(java.awt.event.MouseEvent e) {}
        @Override
        public void mouseExited(java.awt.event.MouseEvent e) {}
    }
    //mdr

}