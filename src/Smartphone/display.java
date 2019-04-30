package Smartphone;

/*
 * @author Piranavan Thambirajah & Alex Gharbi
 * Createad: 30.04.2019 - Last Update: 30.04.2019
 * Main frame for the smartphone
 */

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class display extends JFrame {

    //Main setting wich allows us to switch between panels
    protected CardLayout cardLayout = new CardLayout();


    //Panels
    protected JPanel content = new JPanel();
    protected JPanel statusBar = new JPanel(); //On the top will be displayed time and battery status
    protected JPanel homePanel = new JPanel(); //Homescreen

    //Array to other apps
    protected String[] access = {"Menu", "Contacts", "Weather"};
    private JLabel labelFR = new JLabel("Bonjour le monde");

    private JPanel panel = new JPanel();

    private JButton button = new JButton("Voter pour Liam");

    public display(){

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //Ferme le programme lorsqu'on ferme la fenêtre

        setTitle("Yes");

        ImageIcon i = new ImageIcon("src\\pictures\\homeBackground.jpg");

        JLabel j = new JLabel(i);


        homePanel.setLayout(null);
        homePanel.setBackground(Color.ORANGE);

        statusBar.setLayout(null);
        statusBar.setBackground(Color.BLACK);

        content.setLayout(cardLayout);
        content.add(j);
        content.add(homePanel, access[0]);


        add(content);


    }
}

