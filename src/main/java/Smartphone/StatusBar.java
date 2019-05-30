package Smartphone;

import tools.imageButton;
import tools.textResizer;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static Smartphone.display.picDirectory;


public class StatusBar extends JPanel {

    public StatusBar() throws InterruptedException {
        setLayout(null);

        setBorder(new BevelBorder(BevelBorder.RAISED));
        setPreferredSize(new Dimension(this.getWidth(), 30));

        final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        int interval = 1000; // 1000 ms
        Calendar now = Calendar.getInstance();
        final JLabel currentTime = new JLabel(dateFormat.format(now.getTime()));


        currentTime.setBounds(10,0,200,30);
        new textResizer(currentTime,18,1); //Le 1 nous permet de déclencher le texte en gras
        add(currentTime);

        JButton closeButton = new imageButton();
        closeButton.setIcon(new ImageIcon(picDirectory+"iconOff.png"));
        closeButton.setBounds(440,2,30,30);

        closeButton.setForeground(Color.red);
        closeButton.addMouseListener(new MouseEvent());
        add(closeButton);

        new Timer(interval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Calendar now = Calendar.getInstance();
                currentTime.setText(dateFormat.format(now.getTime()));
            }
        }).start();

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
}