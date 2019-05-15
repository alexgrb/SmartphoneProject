package Smartphone;

/*
 * @author Piranavan Thambirajah & Alex Gharbi
 * Createad: 03.04.2019 - Last Update: 30.04.2019
 * Main frame for the smartphone
 */

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class Contact extends JPanel {


    ContactRegex regex = new ContactRegex();

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
    private JButton bOK = new JButton("OK");

    //Liste des contacts
    private static JList jlistContact = new JList();

    protected JPanel topPanel = new JPanel(); // Stores the two top panels
    protected JPanel leftPanel = new JPanel(); // Scroll Pane
    protected JPanel rightPanel = new JPanel(); // Right Panel with buttons + form
    protected JPanel rightTopPanel = new JPanel(); // Buttons
    protected JPanel rightBottomPanel = new JPanel(); // Form
    protected JPanel bottomPanel = new JPanel(); // Stores the three bottom panels

    private JPanel top = new JPanel();
    private JPanel center = new JPanel();
    private JPanel bottom = new JPanel();
    private JPanel east = new JPanel();

    private static String week[] = {"Monday", "Tuesday", "Wednesday",
            "Thursday", "Friday", "Saturday", "Sunday"};
    // Tableau apr�s lecture du fichier
    protected static ContactData[] tabContactData;


    public Contact() {

        //Nouvelle police
        Font police = new Font("Arial", Font.BOLD, 14);
        jtNpa.setFont(police);


        // Setting up the scroll pane
        JScrollPane scrollPane = new JScrollPane(jlistContact);
        Border emptyBorder = BorderFactory.createEmptyBorder();
        scrollPane.setBorder(emptyBorder);
        leftPanel.setBorder(emptyBorder);
        jlistContact.setBorder(emptyBorder);
        scrollPane.setBackground(Color.gray);
        scrollPane.setBounds(0, 0, 500, 500);
        jlistContact.setBackground(Color.LIGHT_GRAY);
        jlistContact.setFont(police);
        east.add(jlistContact);
        east.add(scrollPane);


        bOK.addActionListener(new BoutonListener());


        // Liste



        /*
        DefaultListModel listModel = new DefaultListModel();


        listModel.addElement("Jane Doe");
        listModel.addElement("John Smith");
        listModel.addElement("Kathy Green");
        */


        //Implémente le String[] dans la Jlist
        jlistContact = new JList(week);

        //Lorsque l'on clique sur un éléments de la liste, il s'affiche sur la console
        jlistContact.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    final List selectedValuesList = jlistContact.getSelectedValuesList();
                    System.out.println(selectedValuesList);
                }
            }
        });

        //Ajoute un scrollpane à la Jlist
        add(new JScrollPane(jlistContact));

        //Ajoute la liste dans le Jpanel top (qui est instancié plus bas)
        top.add(jlistContact);

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
        top.setLayout(new GridLayout(1, 1));

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

        center.setLayout(new GridLayout(14, 1));

        center.add(bOK);

        //Bottom

        jlistContact.addListSelectionListener(new EcouteurList());

        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);
        add(east, BorderLayout.EAST);


    }


    // ------------------ LIST + ACTION LISTENER ---------------------- //

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


            ContactData alex = new ContactData("Alex", "Gharbi", "08292982873", "alex.gharbi@hotmail.com", "Wow", "1009", "01.06.1996");

            try {
                LectureContact();
                Serialisation(alex);
                Deserialisation();
                WriteData();
                WriteData();

            } catch (Exception ex) {
                ex.printStackTrace();
            }


            //String[] nomprenom = {jtPrenom.getText(), jtNom.getText()};
            //updateList(nomprenom);

        }

    }
    // Action effectuée lors de la séléction d'un élément dans la JList

    class EcouteurList implements ListSelectionListener {
        /**
         * M�thode effectu�e lors de l'action du click sur un �l�ment de la JList et va r�cup�rer la ligne s�l�ctionn�e
         * Va afficher le formulaire en bas afin d'afficher les informations du contact s�l�ctionn� et le rendre non-editable
         * Va rechercher dans le tableau de contact, le contact s�l�ctionner et l'affecter dans les champs JTextField
         */
        public void valueChanged(ListSelectionEvent evt){
            int i = jlistContact.getSelectedIndex();
           /*
            panelDroitBas.setVisible(true);
            setEditable(false);
            if(valModifSupp == false){
                jbEdit.setVisible(true);
                jbDelete.setVisible(true);
            } */
            if(i != -1){
                jtNom.setText(tabContactData[i].getNom());
                jtPrenom.setText(tabContactData[i].getPrenom());
                jtNumTel.setText(tabContactData[i].getNumTel());
                jtEmail.setText(tabContactData[i].getEmail());
                jtAdresse.setText(tabContactData[i].getAdresse());
                jtNpa.setText(tabContactData[i].getNPAloc());
                jtDateNaissance.setText(tabContactData[i].getDateNaissance());
            }
        }
    }


    //------------------------------- METHODES -----------------------------//



    private static String[] listAffichageJList;


    public void updateList(String[] nomprenom) {
        String contactonlist = "";
        listAffichageJList = new String[1];
        try {
            for (int i = 0; i < nomprenom.length; i++) {
                if (nomprenom[i] != null) {
                    //listAffichageJList[i] = nomprenom[i];
                    listAffichageJList[i] = nomprenom[0] + " " + nomprenom[1];
                    if (nomprenom[i].contains("#deleted")) {

                        //listAffichageJList[i] = null;
                    }
                }
            }

            jlistContact.setListData(listAffichageJList);
            //statutBtnInitial();
            //panelDroitBas.setVisible(false);
        } catch (Exception e) {
            System.out.println("Erreur à la mise a jour des informations");
            System.out.println(e.toString());
        }
    }



    public static String pathFiletxt = ".\\contact.txt";

    //Méthode qui permet de sérialiser et inscrire le conetnu d'un objet ContactData préparé à l'avance, dans le fichier .txt

    public static void Serialisation(ContactData contactData) throws FileNotFoundException {

        try {
            FileOutputStream fos = new FileOutputStream(pathFiletxt);
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(contactData);
            System.out.println("Objet sérialisé");
            os.close();
            System.out.println("Fermeture");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    //Méthode qui permet de lire le fichier .txt (illisible pour nous) et le traduire en charactères lisibles dans la console
    public void Deserialisation() throws FileNotFoundException {

        try {


            FileInputStream fis = new FileInputStream(pathFiletxt);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ContactData cd = (ContactData) ois.readObject();
            System.out.println("Mon objet " + cd);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //Méthode qui permet de parcourir notre String [] et de l'afficher en toute lettres dans notre fichier .txt
    public void WriteData() {

        try {
            PrintWriter fichierSortie = new PrintWriter(new BufferedWriter(new FileWriter(pathFiletxt, false)));
            for (int i = 0; i<week.length; i++){
                if(week[i]!= null){
                    if(!week[i].contains("#delete")){
                        fichierSortie.println(week[i]);
                        System.out.println("Il se passe un truc!!");

                    }
                }
            }
            //fermeture fichier
            fichierSortie.close();
        } catch (IOException e) {
            System.out.println("erreur inscription dans fichier");
            e.printStackTrace();
        }

    }

    /**
     * Méthode de lecture du fichier afin de créer un tableau de contact et une chaine à partir des données contenue dans le fichier.
     * le premier try va calculer le nombre de ligne contenue dans le fichier afin de créer les tableaux nécessaires
     * le deuxième try va s'occuper de rajouter chaque ligne dans le fichier sur le tableau définit afin d'être utilisé dans le programme
     * @see BufferedReader
     * @see Contact
     * @exception 2 gestion d'exception en cas de problème lors de la lecture du fichier ou de l'execution des lignes
     */

    public void LectureContact() {
        String ligne;
        try{
            BufferedReader br = new BufferedReader( new InputStreamReader( new FileInputStream(pathFiletxt)));
            int cptLengthChaine = 0;
            while ((br.readLine())!= null){
                cptLengthChaine++;
            }
            week = new String[cptLengthChaine];
            listAffichageJList = new String[week.length];
            tabContactData = new ContactData[week.length];
            br.close();

        }catch (Exception e){
            System.out.println("Lecture fichier");
            System.out.println(e.toString());
        }

        try{
            int cpt = 0;
            BufferedReader br=new BufferedReader( new InputStreamReader( new FileInputStream(pathFiletxt)));

            while ((ligne=br.readLine())!=null ){
                week[cpt] = ligne ;
                cpt++;
            }
            br.close();
            updateList();
            jlistContact.setEnabled(true);
        }
        catch (Exception e){
            System.out.println("problème Lecture fichier");
            System.out.println(e.toString());
        }
    }

    /**
     * M�thode de mise � jour de la liste des contacts dans la Jlist
     * La méthode va créer un tableau temporaire afin de placer à chaque position, et dans l'ordre, les informations
     * de nom, prenom etc... du contact
     * On va ensuite créer un tableau de contact TabContactData contenant des contacts placer dans le tableau temporaire
     * Il va aussi omettre d'y inclure les lignes contenant un #delete
     * @see Contact
     */
    @SuppressWarnings("unchecked")
    public static void updateList(){
        listAffichageJList = new String[week.length];
        tabContactData = new ContactData[week.length];

        String[] tempo = new String[6];
        try {
            for (int i = 0; i<week.length; i++){
                if(week[i]!= null){
                    // On découpe chaque ligne du fichier en 6 partie distinctement séparées
                    tempo = week[i].split(" - ", 7);
                    // On crée un tableau de contact qui contiendra un objet contact avec les infos
                    tabContactData[i] = new ContactData(tempo[0], tempo[1], tempo[2], tempo[3], tempo[4], tempo[5], tempo[6]);
                    // On crée le text d'affichage de la JList
                    listAffichageJList[i] = tempo[0] + " " + tempo[1];
                    if(week[i].contains("#deleted")){
                        listAffichageJList[i] = null;
                    }
                }
            }

            jlistContact.setListData(listAffichageJList);
            //statutBtnInitial();
            //panelDroitBas.setVisible(false);
        }catch (Exception e){
            System.out.println("Erreur à la mise a jour des informations");
            System.out.println(e.toString());
        }
    }



}
