package Smartphone;

/*
 * @author Piranavan Thambirajah & Alex Gharbi
 * Createad: 30.04.2019 - Last Update: 30.04.2019
 * Main frame for the smartphone
 */

import javax.swing.*;
import javax.swing.border.BevelBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class display extends JFrame {

    //Main setting wich allows us to switch between panels
    protected CardLayout cardLayout = new CardLayout();

    //Panels
    protected JPanel content = new JPanel();

    //Array to other apps
    protected String[] access = {"Weather", "Contacts", "Home", "Calcul", "Gallery"};

    private JButton weatherButton = new button("weatherData"); //Les boutons sont crées via une classe "button"
    private JButton homeButton = new button("home");
    private JButton calculButton = new button("calculette");
    private JButton contactButton = new button("Contact");
    private JButton galleryButton = new button("galerie");
    private JPanel dockPanel = new JPanel();


    public display(){

        ///PARAMETRE DE LA FENETRE///
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Ferme le programme lorsqu'on ferme la fenêtre
        setResizable(false); //Empêche le redimensionnement
        setUndecorated(true);

        ///                     ///
        ///PATH VERS LES FONDS  ///
        ImageIcon i = new ImageIcon("src\\pictures\\homeBackground.jpg");
        JLabel j = new JLabel(i);

        ImageIcon h = new ImageIcon("src\\pictures\\weatherBackground.jpg");


        ///ACTION LISTENER SUR LES BOUTONS///
        weatherButton.addActionListener(new homeListener (0));
        contactButton.addActionListener(new homeListener(1));
        homeButton.addActionListener(new homeListener(2));
        calculButton.addActionListener(new homeListener(3));
        galleryButton.addActionListener(new homeListener(4));



        ///Panel


        JPanel homePanel = new JPanel(); //Homescreen
        homePanel.add(j);
        JPanel calculPanel = new Calculatrice();
        JPanel contactlPanel = new Contact();
        JPanel statusPanel = new JPanel();
        JPanel weatherPanel = new weather();
        JPanel galleryPanel = new Gallery();


        statusPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
        add(statusPanel, BorderLayout.NORTH);
        statusPanel.setPreferredSize(new Dimension(this.getWidth(), 30));
        statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.X_AXIS));
        JLabel statusLabel = new JLabel("status");
        JButton closeButton = new JButton("X");
        closeButton.setBackground(Color.RED);
        closeButton.addActionListener(new closeListener());
        statusLabel.setHorizontalAlignment(SwingConstants.LEFT);
        statusPanel.add(statusLabel);
        statusPanel.add(closeButton);

        dockPanel.add(weatherButton);
        dockPanel.add(homeButton);
        dockPanel.add(calculButton);
        dockPanel.add(contactButton);
        dockPanel.add(galleryButton);

        ///CONFIGURATION DES LAYOUT///

        content.setLayout(cardLayout);

        content.add(weatherPanel, access[0]);
        content.add(contactlPanel, access[1]);
        content.add(homePanel, access[2]);
        content.add(calculPanel, access[3]);
        content.add(galleryPanel, access[4]);

        add(content, BorderLayout.CENTER);
        add(dockPanel, BorderLayout.SOUTH);
    }

    class homeListener implements ActionListener{
        int i = 0; // Variable to represent the choosen menu item id
        public homeListener(int appId){
            i = appId;
        }

        public void actionPerformed(ActionEvent e) {
            //Via cette instruction, on passe au conteneur correspondant au nom fourni en paramètre
            cardLayout.show(content, access[i]);
        }
    }
    class closeListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            System.exit(0);
        }
    }

}

