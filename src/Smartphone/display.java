package Smartphone;

/*
 * @author Piranavan Thambirajah & Alex Gharbi
 * Createad: 30.04.2019 - Last Update: 30.04.2019
 * Main frame for the smartphone
 */

import javax.swing.*;
import javax.swing.border.Border;
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
    protected String[] access = {"Weather", "Contacts", "Home", "Calcul"};

    private JButton weatherButton = new button("weather"); //Les boutons sont crées via une classe "button"
    private JButton homeButton = new button("home");
    private JButton calculButton = new button("calculette");
    private JButton contactButton = new button("Contact");
    private JPanel dockPanel = new JPanel();


    public display(){

        ///PARAMETRE DE LA FENETRE///
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Ferme le programme lorsqu'on ferme la fenêtre
        setResizable(false); //Empêche le redimensionnement
        setUndecorated(false); //Retire toutes options autour de la fenêtre
        setTitle("SMARTPHONE");
        ///                     ///
        ///PATH VERS LES FONDS  ///
        ImageIcon i = new ImageIcon("src\\pictures\\homeBackground.jpg");
        JLabel j = new JLabel(i);

        ImageIcon h = new ImageIcon("src\\pictures\\weatherBackground.jpg");
        JLabel x = new JLabel(h);

        ///ACTION LISTENER SUR LES BOUTONS///
        weatherButton.addActionListener(new homeListener (0));
        contactButton.addActionListener(new homeListener(1));
        homeButton.addActionListener(new homeListener(2));
        calculButton.addActionListener(new homeListener(3));


        ///Panel
        JPanel weatherPanel = new JPanel();
        weatherPanel.add(x);
        JPanel homePanel = new JPanel(); //Homescreen
        homePanel.add(j);
        JPanel calculPanel = new Calculatrice();
        JPanel contactlPanel = new Contact();

        dockPanel.add(weatherButton);
        dockPanel.add(homeButton);
        dockPanel.add(calculButton);
        dockPanel.add(contactButton);

        ///CONFIGURATION DES LAYOUT///

        content.setLayout(cardLayout);

        content.add(weatherPanel, access[0]);
        content.add(contactlPanel, access[1]);
        content.add(homePanel, access[2]);
        content.add(calculPanel, access[3]);

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

}

