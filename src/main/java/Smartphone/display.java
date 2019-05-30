package Smartphone;

/*
 * @author Piranavan Thambirajah & Alex Gharbi
 * Createad: 30.04.2019 - Last Update: 30.04.2019
 * Main frame for the smartphone
 */

import tools.imageButton;
import tools.imageLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class display extends JFrame {

    //Main setting wich allows us to switch between panels
    protected static CardLayout cardLayout = new CardLayout();
    protected static int maxApp = 5;
    //Panels
    protected static JPanel content = new JPanel();
    protected static JPanel bottom = new JPanel();

    //Array to other apps
    protected static String[] access = {"Weather", "Contacts", "Home", "Calcul", "Gallery", "Image"};

    private static JButton[] appButton= new JButton[maxApp];

    public display() {

        ///PARAMETRE DE LA FENETRE///
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Ferme le programme lorsqu'on ferme la fenêtre
        setResizable(false); //Empêche le redimensionnement
        setUndecorated(true);

        ///BOUTONS///
        for (int i=0 ; i<appButton.length;i++){
            appButton[i] = new JButton();
            appButton[i] = new imageButton(); //Cela va rendre le fond transparent
            appButton[i].addActionListener(new homeListener(i)); //Ajoute l'action listener afin de pouvoir naviguer entre les appli
        }
        appButton[0].setIcon(new ImageIcon("src\\main\\java\\pictures\\iconWeather.png"));
        appButton[1].setIcon(new ImageIcon("src\\main\\java\\pictures\\iconContact.png"));
        appButton[2].setIcon(new ImageIcon("src\\main\\java\\pictures\\iconeHome.png"));
        appButton[3].setIcon(new ImageIcon("src\\main\\java\\pictures\\iconCalculette.png"));
        appButton[4].setIcon(new ImageIcon("src\\main\\java\\pictures\\iconGallery.png"));

       bottom.setLayout(new FlowLayout());

         //Background Image

        //Panel
        JPanel homePanel = new Home(); //Homescreen

        JPanel calculPanel = new Calculatrice();
        JPanel contactlPanel = new Contact();
        try {
        JPanel statusPanel = new StatusBar();
        statusPanel.setBounds(0,0,480,40);
            add(statusPanel);
             } catch (InterruptedException e) {
                e.printStackTrace();
            }
        JPanel weatherPanel = new weather();
        JPanel galleryPanel = new Gallery();

        bottom.setOpaque(false);
        bottom.setBackground(new Color(0,true));
        bottom.setBorder(null);


bottom.add(appButton[2]);
        for(int z = 0; z< maxApp; z++){
            bottom.add(appButton[z]);
        }

        ///CONFIGURATION DES LAYOUT///
        content.setLayout(cardLayout);
        content.add(homePanel, access[2]);
        content.add(weatherPanel, access[0]);
        content.add(contactlPanel, access[1]);
        content.add(calculPanel, access[3]);
        content.add(galleryPanel, access[4]);

        setLayout(null);
        content.setBounds(0,35,480,765);
        add(content);
        bottom.setBounds(0,740,480,60);
        add(bottom);
        bottom.setVisible(false);

        getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.gray));

    }

     static class homeListener implements ActionListener{
        int i = 0; // Variable to represent the choosen menu item id
        public homeListener(int appId){
            i = appId;
        }
        public void actionPerformed(ActionEvent e) {
            //Via cette instruction, on passe au conteneur correspondant au nom fourni en paramètre
            if(i==2) {
                cardLayout.show(content, access[2]);
                bottom.setVisible(false);
            }
            else {
                cardLayout.show(content, access[i]);
                bottom.setVisible(true);
            }
        }
    }
}

