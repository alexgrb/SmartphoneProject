package Smartphone;

/*
 * @author Piranavan Thambirajah & Alex Gharbi
 * Createad: 03.04.2019 - Last Update: 30.04.2019
 * Main frame for the smartphone
 */

import javafx.scene.control.TextFormatter;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.swing.border.Border;
import javax.xml.transform.Result;
import javax.xml.transform.Source;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;

public class Contact extends JPanel{



    Regex regex = new Regex();

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


    //TextFields
    private JTextField jtNom = new JTextField();
    private JTextField jtPrenom = new JTextField();
    private JTextField jtNumTel = new JTextField();
    private JTextField jtEmail = new JTextField();
    private JTextField jtAdresse = new JTextField();
    private JTextField jtNpa = new JTextField();
    private JTextField jtDateNaissance = new JTextField();


    //Bouton
    private JCheckBox checkFav = new JCheckBox("Favori");
    private JButton bOK = new JButton ("OK");

    private JPanel top = new JPanel();
    private JPanel center = new JPanel();
    private JPanel bottom = new JPanel();


    public Contact() {



        bOK.addActionListener(new BoutonListener());



        //Nouvelle police
        Font police = new Font("Arial", Font.BOLD, 14);
        jtNpa.setFont(police);


        //Taille des JtextField

        lbTitre.setPreferredSize(new Dimension(150, 30));
        jtNom.setPreferredSize(new Dimension(150, 30));
        jtPrenom.setPreferredSize(new Dimension(150, 30));
        jtNumTel.setPreferredSize(new Dimension(150, 30));
        jtEmail.setPreferredSize(new Dimension(150, 30));
        jtAdresse.setPreferredSize(new Dimension(150, 30));
        jtNpa.setPreferredSize(new Dimension(150, 30));
        jtDateNaissance.setPreferredSize(new Dimension(150, 30));
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
        center.add(jtNumTel);
        center.add(lbEmail);
        center.add(jtEmail);
        center.add(lbAdresse);
        center.add(jtAdresse);
        center.add(lbNpaLoc);
        center.add(jtNpa);
        center.add(lbDateNaissance);
        center.add(jtDateNaissance);
        center.add(checkFav);

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
            System.out.println("NumTel : " + jtNumTel.getText());
            System.out.println("E-mail : " + jtEmail.getText());
            System.out.println("Adresse : " + jtAdresse.getText());
            System.out.println("NPA : " + jtNpa.getText());
            System.out.println("Date de naissance : " + jtDateNaissance.getText());
            System.out.println("Favoris - état : " + checkFav.isSelected());


            if (!regex.validerEmail(jtEmail.getText()))
                System.out.println("L'email est faux.");
            else
                System.out.println("L'email est juste.");

            if (!regex.validerNPA(jtNpa.getText()))
                System.out.println("Le NPA est faux.");
            else
                System.out.println("Le NPA est juste.");


            if (!regex.validerNumTel(jtNumTel.getText()))
                System.out.println("Le NumTel est faux");
            else
                System.out.println("Le NumTel est juste.");
        }

    }

}
