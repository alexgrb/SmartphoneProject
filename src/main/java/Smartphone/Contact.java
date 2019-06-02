package Smartphone;

/*
 * @author Piranavan Thambirajah & Alex Gharbi
 * Createad: 03.04.2019 - Last Update: 30.04.2019
 * Main frame for the smartphone
 */

import net.miginfocom.swing.MigLayout;
import tools.imageButton;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import static Smartphone.display.picDirectory;

public class Contact extends JPanel {
    static JPanel contactImg = new Gallery();
    private Contact contact;
    static ContactRegex regex = new ContactRegex();

    protected String path = "";

    public static Font fontBouton = new Font("Dialog",Font.BOLD ,15);
    protected Font fontlabels = new Font("Arial",Font.BOLD ,14);
    protected Font fontJtextfields = new Font("Arial",Font.PLAIN ,14);
    protected Font fontJList = new Font("Arial",Font.PLAIN ,20);


    protected Dimension dim = new Dimension(200, 30);
    protected Dimension dimSmall = new Dimension(100, 30);
    protected Dimension dimJlist = new Dimension(450, 700);


    // Les labels
    private JLabel lbNom = new JLabel("Nom");
    private JLabel lbPrenom = new JLabel("Prenom");
    private JLabel lbNumTel = new JLabel("Numéro de téléphone");
    private JLabel lbEmail = new JLabel("E-mail");
    private JLabel lbAdresse = new JLabel("Adresse");
    private JLabel lbNpa = new JLabel("NPA");
    private JLabel lbDateNaissance = new JLabel("Date de naissance");
    private JLabel lbimg = new JLabel();
    private JLabel lbempty = new JLabel(" ");


    //TextFields
    private static JTextField jtNom = new JTextField();
    private static JTextField jtPrenom = new JTextField();
    private static JTextField jtNumTel = new JTextField();
    private static JTextField jtEmail = new JTextField();
    private static JTextField jtAdresse = new JTextField();
    private static JTextField jtNpa = new JTextField();
    private static JTextField jtDateNaissance = new JTextField();
    protected static JTextField jtpathImg = new JTextField();

    //Bouton
    protected static JButton jbAdd = new JButton ();
    private static JButton jbValiderEdit = new JButton ();
    private static JButton jbValiderAdd = new JButton ();
    protected static JButton jbEdit = new JButton ();
    protected static JButton jbDelete = new JButton ();
    protected static JButton jbRetour = new JButton ();


    //Liste des contacts

   // protected static String[] chaine;
    private static JList jlistContact = new JList();


    protected JPanel listPanel = new JPanel();
    protected JPanel subButtonsPanel = new JPanel();
    protected JPanel mainButtonsPanel = new JPanel();
    protected static JPanel formPanel = new JPanel();


    protected static boolean valModifSupp = false;

    public static String pathFiletxt = ".\\contact.txt";

    // Tableau après lecture du fichier
    protected static ContactData[] tabContactData;


    public Contact() {

        setBackground(new Color(255,255,255));
        this.contact = this;

        //---------- Boutons ---------//
       /* jbAdd = new imageButton();
        jbAdd.setIcon(new ImageIcon(picDirectory+"iconAdd.png"));

        /*
        jbRetour = new imageButton();
        jbRetour.setIcon(new ImageIcon(picDirectory+"iconBack.png"));
*/
        jbEdit = new imageButton("Editer", 2);
        jbEdit.setBackground(Color.LIGHT_GRAY);
        jbValiderEdit = new imageButton("Valider modification(s)", 2);
        jbValiderEdit.setBackground(Color.LIGHT_GRAY);
        jbValiderAdd = new imageButton("Valider ", 2);
        jbValiderAdd.setBackground(Color.LIGHT_GRAY);
        jbDelete = new imageButton("Supprimer", 2);
        jbDelete.setBackground(Color.LIGHT_GRAY);

        //---------- END ---------//

        //----------- Les boutons -----//
//
        //jbValiderEdit.addActionListener(new ValiderEditAdd());
       // jbValiderAdd.addActionListener(new ValiderAdd());
      //  jbAdd.addActionListener(new ActionAdd());
        jbEdit.addActionListener(new ActionEdit());
        jbDelete.addActionListener(new ActionDelete());
        lbimg.addMouseListener(new mouseListener());

        jbAdd.setFont(fontBouton);
        jbRetour.setFont(fontBouton);

        //Fonts et dim sur les labels

        lbNom.setFont(fontlabels);
        lbPrenom.setFont(fontlabels);
        lbNumTel.setFont(fontlabels);
        lbEmail.setFont(fontlabels);
        lbAdresse.setFont(fontlabels);
        lbNpa.setFont(fontlabels);
        lbDateNaissance.setFont(fontlabels);

        //Fonts et dim des JtextField

        jtNom.setFont (fontJtextfields);
        jtNom.setFont (fontJtextfields);
        jtPrenom.setFont (fontJtextfields);
        jtNumTel.setFont (fontJtextfields);
        jtEmail.setFont (fontJtextfields);
        jtAdresse.setFont (fontJtextfields);
        jtNpa.setFont (fontJtextfields);
        jtDateNaissance.setFont (fontJtextfields);
        jtpathImg.setFont (fontJtextfields);


        jtNom.setPreferredSize(dimSmall);
        jtPrenom.setPreferredSize(dimSmall);
        jtNumTel.setPreferredSize(dim);
        jtEmail.setPreferredSize(dim);
        jtAdresse.setPreferredSize(dim);
        jtNpa.setPreferredSize(dimSmall);
        jtDateNaissance.setPreferredSize(dimSmall);
        jtpathImg.setPreferredSize(dimSmall);

        //-------------------- LAYOUTS ----------------//

        mainButtonsPanel.setLayout(new MigLayout("wrap 2","[] 300 []"));
        mainButtonsPanel.add(jbRetour);
        mainButtonsPanel.add(jbAdd);

        subButtonsPanel.add(jbValiderEdit);
        subButtonsPanel.add(jbValiderAdd);
        subButtonsPanel.add(jbEdit);
        subButtonsPanel.add(jbDelete);


        formPanel.setLayout(new MigLayout());
        formPanel.add(lbempty, "align left");
        formPanel.add(lbimg, "wrap");
        formPanel.add(lbNom, "wrap");
        formPanel.add(jtNom, "wrap");
        formPanel.add(lbPrenom, "wrap");
        formPanel.add(jtPrenom, "wrap");
        formPanel.add(lbNumTel, "wrap");
        formPanel.add(jtNumTel, "wrap");
        formPanel.add(lbEmail, "wrap");
        formPanel.add(jtEmail, "wrap");
        formPanel.add(lbAdresse, "wrap");
        formPanel.add(jtAdresse, "wrap");
        formPanel.add(lbNpa, "align left");
        formPanel.add(lbDateNaissance, "wrap");
        formPanel.add(jtNpa, "align left");
        formPanel.add(jtDateNaissance, "wrap");


        //formPanel.add(jtpathImg, "wrap");

        mainButtonsPanel.setBackground(new Color(255,255,255));
        listPanel.setBackground(new Color(255,255,255));
        formPanel.setBackground(new Color(255,255,255));
        subButtonsPanel.setBackground(new Color(255,255,255));

        //-------------------- END LAYOUTS ----------------//

        //Bottom

        mainButtonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        subButtonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        add(mainButtonsPanel);
        add(listPanel);
        add(formPanel);
        add(subButtonsPanel);

        formPanel.setVisible(false);

    }

    // ------------------ LIST + ACTION LISTENER ---------------------- //
    public static int find(ContactData[] a, String target) {
        String s = "";

        for (int i = 0; i < a.length; i++) {

            s = a[i].getNom().substring(0, 5);

            if (target.equals(s))
                return i;
        }
        return -1;
    }

    public void setContactPanel(int i){
        listPanel.setVisible(false);
        formPanel.setVisible(true);
        setEditable(false);
        if(valModifSupp == false){
            jbRetour.setVisible(true);
            jbEdit.setVisible(true);
            jbDelete.setVisible(true);
            jbAdd.setVisible(false);
        }

        if(i != -1){
            jtNom.setText(tabContactData[i].getNom());
            jtPrenom.setText(tabContactData[i].getPrenom());
            jtNumTel.setText(tabContactData[i].getNumTel());
            jtEmail.setText(tabContactData[i].getEmail());
            jtAdresse.setText(tabContactData[i].getAdresse());
            jtNpa.setText(tabContactData[i].getNPAloc());
            jtDateNaissance.setText(tabContactData[i].getDateNaissance());
            jtpathImg.setText(tabContactData[i].getPathImg());

            path = tabContactData[i].getPathImg();

            ImageIcon pira = new ImageIcon(picDirectory+"min\\" + path);
            lbimg.setIcon(pira);

        }
    }




    class ActionEdit implements ActionListener{
        /**
         *  Va récupérer la ligne séléctionnée dans la JList (si aucune séléctionnée, la méthode est quittée)
         *  Va afficher les boutons correspondant à l'action d'edition
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            int numJList = jlistContact.getSelectedIndex();

            // Si aucune ligne est séléctionnée, la méthode est quittée
            if(numJList == -1){
                return;
            }
            jlistContact.setEnabled(false);
            jbAdd.setVisible(false);
            jbEdit.setVisible(false);
            jbDelete.setVisible(false);
            jbValiderAdd.setVisible(false);
            jbValiderEdit.setVisible(true);
            jbRetour.setVisible(true);
            setEditable(true);
            formPanel.setVisible(true);
        }
    }

    class ActionDelete implements ActionListener{
        /**
         * Va appeler la méthode "ConfirmSupp()" qui elle va enclencher une JOptionPane de confirmation de supression
         *
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            ConfirmSupp();
        }
    }

    class mouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent me) {
            System.out.println("Je clique sur l'image numéro " + path);

            if (jbValiderEdit.isVisible()){
            removeAll();
            setLayout(null);
            contactImg = new Gallery(jlistContact.getSelectedIndex(), contact);
            contactImg.setBounds(0, 40, 480, 700);
            add(contactImg);
            revalidate();
            repaint();}

            else {
                System.out.println("Il faut être en mode edit pour changer l'image");
            }

        }
    }

    //---------------------------- Validators ------------------------//



    //------------------------------- METHODES -----------------------------//



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
       // updateList();
        writeContact();
        jlistContact.setEnabled(true);
    }


    /**
     * Méthode qui va simplement réinitialiser les bouton comme si venaient d'arriver sur la page (statut initial)
     */

    public static void statutBtnInitial(){
        jbAdd.setVisible(true);
        jbEdit.setVisible(false);
        jbDelete.setVisible(false);
        jbValiderEdit.setVisible(false);
        jbValiderAdd.setVisible(false);
        jbRetour.setVisible(false);
        jlistContact.setEnabled(true);

    }

    /**
     * Méthode qui va permettre de définir que tous les champs soient éditables ou non
     */

    public void setEditable(boolean val){
        jtNom.setEditable(val);
        jtPrenom.setEditable(val);
        jtNumTel.setEditable(val);
        jtEmail.setEditable(val);
        jtAdresse.setEditable(val);
        jtNpa.setEditable(val);
        jtDateNaissance.setEditable(val);
        jtpathImg.setEditable(val);

    }

    /**
     *  Méthode qui va afficher une JOptionPane afin de demander à l'utilisateur de valider la suppresion d'un contact
     * 	Si la réponse est fausse, on ne fait rien.
     * 	Si rep == false vrai -->  ModifChaine(), remplacer le .txt par des #delete
     */

    public void ConfirmSupp() {
        int reponse = JOptionPane.showConfirmDialog(this,
                "??? Etes-vous sûr de vouloir supprimer ce contact ???",
                "ACHTUNG",
                JOptionPane.YES_NO_OPTION);
        if (reponse == JOptionPane.YES_OPTION) {
            int numJList = jlistContact.getSelectedIndex();
            // va remplacer toutes la ligne que on désire supprimer par des #deleted
            ModifChaine("#deleted", "#deleted", "#deleted", "#deleted", "#deleted", "#deleted", "#deleted","#deleted", numJList);
        }

        listPanel.setVisible(true);
    }

    /**
     *
     * Méthode pour ajouter le data d'un contact à l'aide d'un printwriter
     * Check s'il n'y a pas de valeurs nulles ou deleted avant l'écriture
     *
     */

    public static void writeContact(){
        try {
            try {
                PrintWriter pw = new PrintWriter(pathFiletxt);
                // Check s'il n'y a pas de valeurs nulles ou #deleted avant l'écriture
                for (int i = 0; i<ViewContactList.chaine.length; i++){
                    if(ViewContactList.chaine[i]!= null){
                        if(!ViewContactList.chaine[i].contains("#delete")){
                            pw.println(ViewContactList.chaine[i]);
                        }
                    }
                }
                pw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            System.out.println("Erreur lors de l'écriture dans le fichier contact");
            System.out.println(e.toString());
        }
    }
}