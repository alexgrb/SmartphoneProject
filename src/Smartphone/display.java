package Smartphone;

/*
 * @author Piranavan Thambirajah & Alex Gharbi
 * Createad: 30.04.2019 - Last Update: 30.04.2019
 * Main frame for the smartphone
 */

import tools.imageLabel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class display extends JFrame {

    //Main setting wich allows us to switch between panels
    protected CardLayout cardLayout = new CardLayout();
    protected int maxApp = 5;
    //Panels
    protected JPanel content = new JPanel();
    protected JPanel bottom = new JPanel();

    //Array to other apps
    protected String[] access = {"Weather", "Contacts", "Home", "Calcul", "Gallery"};
    private JButton[] appButton= new JButton[maxApp];

    public display(){

        ///PARAMETRE DE LA FENETRE///
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Ferme le programme lorsqu'on ferme la fenêtre
        setResizable(false); //Empêche le redimensionnement
        setUndecorated(true);

        ///BOUTONS///
        for (int i=0 ; i<appButton.length;i++){
            appButton[i] = new JButton();
            appButton[i].addActionListener(new homeListener(i));
        }
        appButton[0].setText("weather");
        appButton[1].setText("contact");
        appButton[2].setText("home");
        appButton[3].setText("calculette");
        appButton[4].setText("galerie");
        int x=10; //Position X
        int y=700; //Position Y
        int width=90;
        int height=35;
        int cpt=0; //Compteur pour changer de ligne qu'on arrive à 4
        for(int z = 0; z< maxApp; z++){
            appButton[z].setBounds(x,y,width,height);
            bottom.add(appButton[z]);
            x+=95; //On se déplace sur la droite de 105 pixel
            cpt++;
            if(cpt==4) { //Si on a déjà 4 app sur la ligne
                y += 50; //On décale vers le bas
                x = 10; //On revient tout à gauche
            }
        }
        /////////////////////////////////
        JLabel j = new imageLabel("homeBackground"); //Background Image

        //Panel
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

        ///CONFIGURATION DES LAYOUT///
        content.setLayout(cardLayout);
        content.add(homePanel, access[2]);
        content.add(weatherPanel, access[0]);
        content.add(contactlPanel, access[1]);
        content.add(calculPanel, access[3]);
        content.add(galleryPanel, access[4]);
        add(content, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
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

