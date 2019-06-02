package Smartphone;

import net.miginfocom.swing.MigLayout;
import tools.imageButton;
import tools.imageLabel;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static Smartphone.Contact.regex;
import static Smartphone.ViewContactList.tabContactData;
import static Smartphone.ViewContactList.updateList;
import static Smartphone.display.picDirectory;

public class ViewContact extends JPanel {

    private ContactData contact;
    static JPanel contactImg = new Gallery();
    private ViewContact viewContact;
    private JLabel name = new JLabel("Nom");
    private JLabel firstName = new JLabel("Prénom");
    private JLabel phoneNumber = new JLabel("Numéro de téléphone");
    private JLabel mail = new JLabel("Mail");
    private JLabel address = new JLabel("Adresse");
    private JLabel NPA = new JLabel("NPA");
    private JLabel dateOfBirth = new JLabel("Date de naissance");
    protected JLabel picture = new imageLabel("iconAdd");

    private static JTextField nameJT = new JTextField();
    private static JTextField firstNameJT = new JTextField();
    private static JTextField phoneNumberJT = new JTextField();
    private static JTextField mailJT = new JTextField();
    private static JTextField addressJT = new JTextField();
    private static JTextField NPAJT = new JTextField();
    private static JTextField dateOfBirthJT = new JTextField();
    private static JTextField pictureJT = new JTextField();

    protected Dimension dim = new Dimension(200, 30);
    protected Dimension dimSmall = new Dimension(100, 30);
    private static ViewContactList viewContactList;

    private JButton backButton = new JButton();
    private static JButton jbValiderAdd = new JButton ("Valider l'ajout");
    private static JButton editButton = new JButton ("Editer");
    private static JButton jbValiderEdit = new JButton ("Valider les modifications");



    public ViewContact(ContactData contact, ViewContactList viewContactList) {
        setLayout(new MigLayout("wrap"));
        this.contact = contact;
        this.viewContactList = viewContactList;
        viewContact = this;

        setBackground(Color.white);
        backButton.setIcon(new ImageIcon(picDirectory+"iconBack.png"));
        backButton.addActionListener(new backListener());

        editButton.addActionListener(new ActionEdit());
        setContactPanelJT();
        setLabelsSizeJT();
        addToPanelJT();
        add(editButton);


        jbValiderEdit.addActionListener(new ValiderEditAdd());
        add(jbValiderEdit);
        jbValiderEdit.setVisible(false);
        jbValiderAdd.setVisible(false);
        setEditable(false);
    }

    public ViewContact(ViewContactList viewContactList) {
        this.viewContactList = viewContactList;
        viewContact = this;
        setLayout(new MigLayout("wrap"));
        setBackground(Color.white);
        viewContactList.jlistContact.getModel().getElementAt(0);
        backButton.setIcon(new ImageIcon(picDirectory+"iconBack.png"));
        backButton.addActionListener(new backListener());
        jbValiderAdd = new imageButton("Valider", 2);
        jbValiderAdd.setBackground(Color.white);
        jbValiderAdd.addActionListener(new ValiderAdd(this));

        setEditable(true);
        setLabelsSizeJT();
        resetChamp();
        addToPanelJT();
    }

    public void setContactPanelJT() {
        nameJT.setText(this.contact.getNom());
        firstNameJT.setText(this.contact.getPrenom());
        phoneNumberJT.setText(this.contact.getNumTel());
        mailJT.setText(this.contact.getEmail());
        addressJT.setText(this.contact.getAdresse());
        NPAJT.setText(this.contact.getNPAloc());
        dateOfBirthJT.setText(this.contact.getDateNaissance());
    }

    public void setLabelsSizeJT(){
        nameJT.setPreferredSize(dimSmall);
        firstNameJT.setPreferredSize(dimSmall);
        phoneNumberJT.setPreferredSize(dim);
        mailJT.setPreferredSize(dim);
        addressJT.setPreferredSize(dim);
        NPAJT.setPreferredSize(dimSmall);
        dateOfBirthJT.setPreferredSize(dimSmall);
        pictureJT.setPreferredSize(dimSmall);
    }

    public void setEditable(boolean val){
        nameJT.setEditable(val);
        firstNameJT.setEditable(val);
        phoneNumberJT.setEditable(val);
        mailJT.setEditable(val);
        addressJT.setEditable(val);
        NPAJT.setEditable(val);
        dateOfBirthJT.setEditable(val);
        pictureJT.setEditable(val);

    }

    public void addToPanelJT(){
        add(backButton);
        add(picture, "right");
        add(name);
        add(nameJT);
        add(firstName);
        add(firstNameJT);
        add(phoneNumber);
        add(phoneNumberJT);
        add(mail);
        add(mailJT);
        add(address);
        add(addressJT);
        add(NPA);
        add(NPAJT);
        add(dateOfBirth);
        add(dateOfBirthJT);
        add(jbValiderAdd);

        picture.addMouseListener(new mouseListener());

    }
    /**
     * Méthode qui va modifier un tableau de chaine (liste de contact) selon les paramètres reçus à la ligne séléctionnée.
     * va lire le tableau au complet jusqu'a une valeur null et se positionner à la ligne du contact que on désire modifier
     * va ecraser la valeur à la position séléctionnée par un nouveau String définit
     */

    public void ModifChaine(String nom, String prenom, String num, String mail, String adresse , String npaLoc, String date, String pathImg, int numJList) {
        for(int i = 0; i<ViewContactList.chaine.length; i++){
            if(ViewContactList.chaine[i] != null){
                if(i == numJList){
                    ViewContactList.chaine[i] = nom + " - " + prenom + " - " + num + " - " + mail + " - " + adresse + " - " + npaLoc +" - " + date + " - " + pathImg;
                }
            }
        }
        updateList();
        Contact.writeContact();
        setEditable(false);
    }
    public static int find(ContactData[] a, String target) {
        String s = "";

        for (int i = 0; i < a.length; i++) {
            s = a[i].getNom().substring(0, 5);
            if (target.equals(s))
                return i;
        }
        return -1;
    }

    class backListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            removeAll();
            viewContact.setVisible(false);
            viewContactList.setPreferredSize(new Dimension(400,700));
            viewContactList.show.add(viewContactList.scrollPane);
            viewContactList.show.add(viewContactList.jbAdd, "top");
            viewContactList.show.repaint();
            viewContactList.show.revalidate();
        }
    }

    class ActionEdit implements ActionListener{
        /**
         *  Va récupérer la ligne séléctionnée dans la JList (si aucune séléctionnée, la méthode est quittée)
         *  Va afficher les boutons correspondant à l'action d'edition
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            setEditable(true);
            jbValiderEdit.setVisible(true);
        }
    }


    /**
     * Méthode qui va simplement reset les champs JTextField null afin de pouvoir inscrire des nouveaux contact
     */

    public void resetChamp(){
        nameJT.setText(null);
        firstNameJT.setText(null);
        phoneNumberJT.setText(null);
        mailJT.setText(null);
        addressJT.setText(null);
        NPAJT.setText(null);
        dateOfBirthJT.setText(null);
    }
    class ValiderAdd implements ActionListener{
        ViewContact viewContact;
        public ValiderAdd(ViewContact viewContact) {
            this.viewContact = viewContact;
        }

        /**
         * Va appeler les methodes de validation (validEmain, validPhone, validBirthday) qui si elles retournent toutes une valeur vrai(true)
         *      * Si une des 3 méthodes de validation retournent false, va afficher le text faut en rouge
         * Si c'est tout bon : va appeler la méthode addInChaine qui va rajouter une ligne à la chaine de contact
         *
         * */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(validPhone(phoneNumberJT.getText())) {
                phoneNumberJT.setForeground(Color.BLACK);
                if (validEmail(mailJT.getText())){
                    mailJT.setForeground(Color.BLACK);
                    if(validNPA(NPAJT.getText())) {
                        NPAJT.setForeground(Color.BLACK);
                          addInChaine();
                    } else {
                        NPAJT.setForeground(Color.RED);
                    }
                } else {
                    mailJT.setForeground(Color.RED);
                }
            } else {
                phoneNumberJT.setForeground(Color.RED);
            }
            resetChamp();
            removeAll();
            viewContactList.show.add(viewContactList.scrollPane);
            viewContactList.show.add(viewContactList.jbAdd, "top");
            viewContactList.show.repaint();
            viewContactList.show.revalidate();

        }

    }

    /**
     * Méthodes qui vont être appelées afin de valider les champs spéciaux. Via les classes REGEX
     */

    public static boolean validPhone(String tel){
        boolean response;
        response = regex.validerNumTel(tel);
        return response;
    }


    public static boolean validEmail(String mail){
        boolean response;
        response = regex.validerEmail(mail);
        return response;
    }

    public static boolean validNPA(String npa){
        boolean response;
        response = regex.validerNPA(npa);
        return response;
    }

    /**
     * Méthode qui va ajouter une ligne au tableau de chaine.
     * Elle va créer un tableau temporaire de la longueur du tableau de chaine + 1 pour la nouvelle ligne et y affecter les valeur du tableau de chaine
     * Puis va rechercher les valeur dans les JTextField et ajouter la valeur à la dernière ligne du tableau temporaire
     * Finallement affectera le tableau temporaire à un nouveau tableau de chaine avec la nouvelle valeur
     *
     */


    public static void addInChaine(){
        String temp [] = new String[viewContactList.chaine.length+1];

        for(int i=0; i<viewContactList.chaine.length; i++){
            temp[i] = viewContactList.chaine[i];
        }
        // Creation du tableau temporaire avec les valeur à inscrire
        temp[viewContactList.chaine.length] = nameJT.getText() + " - " + firstNameJT.getText() + " - " + phoneNumberJT.getText() + " - "+ mailJT.getText() + " - " + addressJT.getText() + " - " + NPAJT.getText() + " - " +  dateOfBirthJT.getText() + " - " +  pictureJT.getText();
        viewContactList.chaine = new String [temp.length];
        viewContactList.chaine = temp;
        Contact.writeContact();
        updateList();
    }

    public void imageUpdate(){
        String i = nameJT.getText();
        String search = i.substring(0,5);
        System.out.println(i);
        System.out.println("ici la table "+tabContactData.toString());
        int  numJList = find(tabContactData,search);
        try {
            // Test les champs
            if(validPhone(phoneNumberJT.getText())) {
                phoneNumberJT.setForeground(Color.BLACK);
                if (validEmail(mailJT.getText())){
                    mailJT.setForeground(Color.BLACK);
                    if(validNPA(NPAJT.getText())) {
                        NPAJT.setForeground(Color.BLACK);
                        // On recupère tous les champs, et on réecrit la ligne avec les nouvelles données
                        ModifChaine(nameJT.getText(), firstNameJT.getText(), phoneNumberJT.getText(), mailJT.getText(), addressJT.getText(), NPAJT.getText(), dateOfBirthJT.getText(), pictureJT.getText(), numJList);
                     } else {
                        NPAJT.setForeground(Color.RED);
                    }
                } else {
                    mailJT.setForeground(Color.RED);
                }
            } else {
                phoneNumberJT.setForeground(Color.RED);
            }
        } catch (Exception f) {
            System.out.println("Erreur à la modification des contacts");
            System.out.println(f.toString());
        }
    }

    class ValiderEditAdd implements ActionListener{
        /**
         * Va appeler les methodes de validation (validEmain, validPhone, validBirthday) qui si elles retournent toutes une valeur vrai(true)
         * va appeler la méthode ModifChaine afin d'effectuer la modification
         * Si une des 3 méthodes de validation retournent false, va afficher le text faut en rouge afin d'effectuer les modifications nécessaires
         *
         */
        public void actionPerformed(ActionEvent e){
             imageUpdate();
             System.out.println("Contact modifié");
        }
    }

    class mouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent me) {
            String i = nameJT.getText();
            String search = i.substring(0,5);
            int  z = find(tabContactData,search);

            if (jbValiderEdit.isVisible()){
                removeAll();
                setLayout(new BorderLayout());
                contactImg = new Gallery(z, viewContactList);
                add(contactImg, BorderLayout.CENTER);
                revalidate();
                repaint();}

            else {
                System.out.println("Il faut être en mode edit pour changer l'image");
            }

        }
    }

}

