package Smartphone;


import tools.imageButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * C'est la seule et unique frame du programme.
 * Toutes les applis sont des JPanels qui s'affichent dessus.
 * @author Piranavan Thambirajah & Alex Gharbi
 * Crée mai 2019
 */

public class display extends JFrame {

    //Main setting wich allows us to switch between panels
    protected static CardLayout cardLayout = new CardLayout();
    protected static int maxApp = 5;

    //Panels
    protected static JPanel content = new JPanel();
    protected static JPanel bottom = new JPanel();
    protected static JPanel statusPanel;
    protected static JPanel galleryPanel;
    //Array to other apps
    protected static String[] access = {"Home","Weather", "Contacts", "Calcul", "Gallery", "Image"};

    private static JButton[] appButton= new JButton[maxApp];
    protected static String picDirectory = Main.dir+"\\src\\main\\java\\pictures\\";

    public static String getPicDirectory() {
        return picDirectory;
    }

    /**
     * Constructeur de la seule et unique frame.
     * Gère le cardLayout vers toutes les applications.
     */
    public display() {

        System.out.println("Le rep: "+ Main.dir);
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
        appButton[0].setIcon(new ImageIcon(picDirectory+"iconeHome.png"));
        appButton[1].setIcon(new ImageIcon(picDirectory+"iconWeather.png"));
        appButton[2].setIcon(new ImageIcon(picDirectory+"iconContact.png"));
        appButton[3].setIcon(new ImageIcon(picDirectory+"iconCalculette.png"));
        appButton[4].setIcon(new ImageIcon(picDirectory+"iconGallery.png"));

        bottom.setLayout(new FlowLayout());

        ///PANEL///
        JPanel homePanel = new Home(); //Homescreen
        JPanel calculPanel = new Calculatrice();
        JPanel contactlPanel = new ViewContactList();
        JPanel weatherPanel = new weather();


        try {
            statusPanel = new StatusBar();
            statusPanel.setBounds(0,0,480,40);
            add(statusPanel);
            statusPanel.setVisible(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bottom.setBackground(new Color(255,255,255));

        bottom.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));


        bottom.add(appButton[0]);
        for(int z = 0; z< maxApp; z++){
            bottom.add(appButton[z]);
        }

        ///CONFIGURATION DES LAYOUT///
        content.setLayout(cardLayout);
        content.add(homePanel, access[0]);
        content.add(weatherPanel, access[1]);
        content.add(contactlPanel, access[2]);
        content.add(calculPanel, access[3]);

        setLayout(null);
        content.setBounds(0,0,480,800);
        add(content);
        bottom.setBounds(0,740,480,60);
        add(bottom);
        bottom.setVisible(false);

        //Bordure autour de la JFrame
        getRootPane().setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.gray));

    }

    /**
     * Class homeListener qui executera les actions effectuer lors de son appel
     * @see homeListener#actionPerformed(ActionEvent)
     *
     */
    static class homeListener implements ActionListener{
        /**
         * Variable i pour représenter l'id du bouton du menu choisi.
         * @param appId
         *
         */
        int i = 0; // Variable to represent the choosen menu item id
        public homeListener(int appId){
            i = appId;
        }

        /**
         * Affiche le contenu du panel correspondant à l'id  fourni en paramètre.
         * ID 0 affiche le homePanel en plein écran sans dock ni status Bar
         */
        public void actionPerformed(ActionEvent e) {
            //Via cette instruction, on passe au conteneur correspondant au nom fourni en paramètre
            if(i==0) {
                content.setBounds(0,0,480,800);
                cardLayout.show(content, access[0]);
                bottom.setVisible(false);
                statusPanel.setVisible(false);
            }
            else if(i==4){
                galleryPanel = new Gallery();
                content.add(galleryPanel,access[4]);
                content.setBounds(0,35,480,705);
                cardLayout.show(content, access[4]);
                bottom.setVisible(true);
                statusPanel.setVisible(true);
                content.revalidate();
                content.revalidate();
            }
            else {
                content.setBounds(0,35,480,705);
                cardLayout.show(content, access[i]);
                bottom.setVisible(true);
                statusPanel.setVisible(true);
            }
        }

    }
}