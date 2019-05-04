package Smartphone;

/*
 * @author Piranavan Thambirajah & Alex Gharbi
 * Createad: 03.04.2019 - Last Update: 30.04.2019
 * Main frame for the smartphone
 */

import javafx.scene.control.TextFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;

public class Contact extends JPanel{


    //Jpanel
    private JPanel container = new JPanel();

    // Les labels
    private JLabel lbNom = new JLabel("Nom");
    private JLabel lbPrenom = new JLabel("Prenom");
    private JLabel lbNumTel = new JLabel("Numéro de téléphone");
    private JLabel lbEmail = new JLabel("E-mail");
    private JLabel lbAdresse = new JLabel("Adresse");
    private JLabel lbNpaLoc = new JLabel("NPA / Localité");
    private JLabel lbDateNaissance = new JLabel("Date de naissance");

    // FormattedTextFields
    private JFormattedTextField jtfNPA = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField jtfNumTel = new JFormattedTextField(NumberFormat.getIntegerInstance());
    private JFormattedTextField jtfDateNaissance = new JFormattedTextField(DateFormat.getDateInstance());

    //TextFields classiques
    private JTextField jtNom = new JTextField();
    private JTextField jtPrenom = new JTextField();
    private JTextField jtEmail = new JTextField();
    private JTextField jtAdresse = new JTextField();
    //Bouton
    private JButton bOK = new JButton ("OK");


    public Contact() {


        //Panel pan = second plan

        //Définir la couleur de fond avec le Panel de fond
        container.setBackground(Color.ORANGE);
        container.setLayout(new BorderLayout());

        // On créer un nouveau Jpanel qui sera notre ContentPane

        JPanel top = new JPanel();


        //Nouvelle police
        Font police = new Font("Arial", Font.BOLD, 14);
        jtfNPA.setPreferredSize(new Dimension(150, 30));
        jtfNPA.setFont(police);
        jtfNumTel.setPreferredSize(new Dimension(150, 30));



        //On ajoute nos TextFields, JLabel en alternance
        //top.add(lbNom);
        //top.add();



        top.add(lbNpaLoc);
        top.add(jtfNPA);
        top.add(lbNumTel);
        top.add(jtfNumTel);


        bOK.addActionListener(new BoutonListener());
        top.add(bOK);

        add(top);

    }

    class BoutonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("NPA : " + jtfNPA.getText());
            System.out.println("NumTel : " + jtfNumTel.getText());
        }

}
}
