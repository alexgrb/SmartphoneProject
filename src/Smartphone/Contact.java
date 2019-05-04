package Smartphone;

/*
 * @author Piranavan Thambirajah & Alex Gharbi
 * Createad: 03.04.2019 - Last Update: 30.04.2019
 * Main frame for the smartphone
 */

import javafx.scene.control.TextFormatter;

import javax.swing.*;
import javax.swing.border.Border;
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
    private JLabel lbTitre = new JLabel("Contact");


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
     JTextField jtPrenom = new JTextField();
    private JTextField jtEmail = new JTextField();
    private JTextField jtAdresse = new JTextField();
    //Bouton
    private JButton bOK = new JButton ("OK");

    private JPanel top = new JPanel();
    private JPanel center = new JPanel();
    private JPanel bottom = new JPanel();


    public Contact() {



        bOK.addActionListener(new BoutonListener());



        //Nouvelle police
        Font police = new Font("Arial", Font.BOLD, 14);
        jtfNPA.setFont(police);


        //Taille des JtextField

        lbTitre.setPreferredSize(new Dimension(150, 30));
        jtNom.setPreferredSize(new Dimension(150, 30));
        jtPrenom.setPreferredSize(new Dimension(150, 30));
        jtfNumTel.setPreferredSize(new Dimension(150, 30));
        jtEmail.setPreferredSize(new Dimension(150, 30));
        jtAdresse.setPreferredSize(new Dimension(150, 30));
        jtfNPA.setPreferredSize(new Dimension(150, 30));
        jtfDateNaissance.setPreferredSize(new Dimension(150, 30));
        bOK.setPreferredSize(new Dimension(150, 30));

        //On ajoute nos TextFields et JLabel en alternance

            //Top
        top.add(lbTitre);
        top.setLayout(new GridLayout(1,1));

            //Center
        center.add(lbNom);
        center.add(jtNom);
        center.add(lbPrenom);
        center.add(jtPrenom);
        center.add(lbNumTel);
        center.add(jtfNumTel);
        center.add(lbEmail);
        center.add(jtEmail);
        center.add(lbAdresse);
        center.add(jtAdresse);
        center.add(lbNpaLoc);
        center.add(jtfNPA);
        center.add(lbDateNaissance);
        center.add(jtfDateNaissance);

        center.setLayout(new GridLayout(14,1));
            //Bottom
        bottom.add(bOK);

        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);


    }

    class BoutonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Nom : " + jtNom.getText());
            System.out.println("Prenom : " + jtPrenom.getText());
            System.out.println("NumTel : " + jtfNumTel.getText());
            System.out.println("E-mail : " + jtEmail.getText());
            System.out.println("Adresse : " + jtAdresse.getText());
            System.out.println("NPA : " + jtfNPA.getText());
            System.out.println("Date de naissance : " + jtfDateNaissance.getText());

        }

}
}
