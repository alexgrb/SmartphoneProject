package Smartphone;

/*
 * @author Piranavan Thambirajah & Alex Gharbi
 * Createad: 03.04.2019 - Last Update: 30.04.2019
 * Main frame for the smartphone
 */

import tools.RoundedBorder;
import tools.imageButton;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class Contact extends JPanel {



    ContactRegex regex = new ContactRegex();

    // private imageLabel photoDuContact = new imageLabel("10");
    // Fonts


    public static Font fontBouton = new Font("Arial",Font.PLAIN ,14);
    protected Font fontlabels = new Font("Arial",Font.BOLD ,14);
    protected Font fontJtextfields = new Font("Arial",Font.PLAIN ,14);
    protected Font fontJList = new Font("Arial",Font.PLAIN ,20);





    protected Dimension dim = new Dimension(200, 30);
    protected Dimension dimJlist = new Dimension(400, 100);


    // Les labels
    private JLabel lbNom = new JLabel("Nom");
    private JLabel lbPrenom = new JLabel("Prenom");
    private JLabel lbNumTel = new JLabel("Numéro de téléphone");
    private JLabel lbEmail = new JLabel("E-mail");
    private JLabel lbAdresse = new JLabel("Adresse");
    private JLabel lbNpa = new JLabel("NPA");
    private JLabel lbDateNaissance = new JLabel("Date de naissance");
    private JCheckBox checkFav = new JCheckBox("Favori");



    //TextFields
    private JTextField jtNom = new JTextField();
    private JTextField jtPrenom = new JTextField();
    private JTextField jtNumTel = new JTextField();
    private JTextField jtEmail = new JTextField();
    private JTextField jtAdresse = new JTextField();
    private JTextField jtNpa = new JTextField();
    private JTextField jtDateNaissance = new JTextField();


    //Bouton
    protected static JButton jbAdd = new JButton ("");
    private static JButton jbValiderEdit = new JButton ("Valider modification(s)");
    private static JButton jbValiderAdd = new JButton ("Valider ajout");
    protected static JButton jbEdit = new JButton ("Modifier");
    protected static JButton jbDelete = new JButton ("Supprimer");
    private static JButton jbRetour = new JButton ("Retour");


    //Liste des contacts

    private static String chaine[];
    private static JList jlistContact = new JList();


    private JPanel topPanel = new JPanel();
    private JPanel centerPanel = new JPanel();
    private static JPanel bottomPanel = new JPanel();


    private static boolean valModifSupp = false;

    public static String pathFiletxt = ".\\contact.txt";

    // Tableau après lecture du fichier
    protected static ContactData[] tabContactData;


    public Contact() {


        //---------- Boutons ---------//

        jbAdd.setIcon(new ImageIcon("src\\pictures\\addresized.png"));
        jbAdd.setOpaque(false);
        jbAdd.setBackground(new Color(0,true));
        jbAdd.setBorder(null);


        jbRetour.setBorderPainted(false);
        jbRetour.setFocusPainted(false);
        jbRetour.setContentAreaFilled(false);


        jbValiderEdit.setBorder(new RoundedBorder(10)); //10 is the radius
        jbValiderEdit.setForeground(Color.BLUE);


        //Nouvelle police
        Font police = new Font("Arial", Font.BOLD, 16);
        jtNpa.setFont(police);



        Border line = new LineBorder(Color.BLACK);
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
      /*  jbEdit.setForeground(Color.BLACK);
        jbEdit.setBackground(Color.WHITE);
        jbEdit.setBorder(compound);
        jbEdit.setBorder(new RoundedBorder(5)); //10 is the radius

       */
        jbEdit = new imageButton("edit", 2);


        /*public class layoutBtn (JButton jbutton){
            jbEdit.setForeground(Color.BLACK);
            jbEdit.setBackground(Color.WHITE);
            Border ligne = new LineBorder(Color.BLACK);
            Border marge = new EmptyBorder(5, 15, 5, 15);
            Border autour = new CompoundBorder(ligne, marge);
            jbEdit.setBorder(autour);
            jbEdit.setBorder(new RoundedBorder(5)); //10 c'est le rayon
        }

         */



        //---------- END ---------//


        //----------- Les boutons -----//

        jbValiderEdit.addActionListener(new ValiderEditAdd());
        jbValiderAdd.addActionListener(new ValiderAdd());
        jbAdd.addActionListener(new ActionAdd());
        jbEdit.addActionListener(new ActionEdit());
        jbDelete.addActionListener(new ActionDelete());
        jbRetour.addActionListener(new ActionRetour());
        jlistContact.addListSelectionListener(new EcouteurList());


        jbAdd.setFont(fontBouton);
        jbValiderEdit.setFont(fontBouton);
        jbValiderAdd.setFont(fontBouton);
        //jbEdit.setFont(fontBouton);
        jbDelete.setFont(fontBouton);
        jbRetour.setFont(fontBouton);

        centerPanel.add(jbAdd);
        centerPanel.add(jbValiderEdit);
        centerPanel.add(jbValiderAdd);
        centerPanel.add(jbEdit);
        centerPanel.add(jbDelete);
        centerPanel.add(jbRetour);


        //---------------------------//


        JScrollPane scrollPane = new JScrollPane(jlistContact);
        topPanel.add(scrollPane);


        /*
        JScrollPane scrollPane = new JScrollPane();
        jlistContact.add(scrollPane);
        topPanel.add(scrollPane);
        */

        /*
        Border emptyBorder = BorderFactory.createEmptyBorder();
        scrollPane.setBorder(emptyBorder);
        jlistContact.setBorder(emptyBorder);
        */

        // -------------- END   -----------//


        //------------- JList --------------//

        jlistContact.setBackground(Color.LIGHT_GRAY);
        jlistContact.setFont(fontJList);
        jlistContact.setPreferredSize(dimJlist);
        jlistContact.setBorder(new EmptyBorder(15,10, 15, 10));


        //-------------- END   -----------//


        //Fonts et dim sur les labels

        lbNom.setFont(fontlabels);
        lbPrenom.setFont(fontlabels);
        lbNumTel.setFont(fontlabels);
        lbEmail.setFont(fontlabels);
        lbAdresse.setFont(fontlabels);
        lbNpa.setFont(fontlabels);
        lbDateNaissance.setFont(fontlabels);


        lbNom.setPreferredSize(dim);
        lbPrenom.setPreferredSize(dim);
        lbNumTel.setPreferredSize(dim);
        lbEmail.setPreferredSize(dim);
        lbAdresse.setPreferredSize(dim);
        lbNpa.setPreferredSize(dim);
        lbDateNaissance.setPreferredSize(dim);

        //Fonts et dim des JtextField

        jtNom.setFont (fontJtextfields);
        jtNom.setFont (fontJtextfields);
        jtPrenom.setFont (fontJtextfields);
        jtNumTel.setFont (fontJtextfields);
        jtEmail.setFont (fontJtextfields);
        jtAdresse.setFont (fontJtextfields);
        jtNpa.setFont (fontJtextfields);
        jtDateNaissance.setFont (fontJtextfields);

        jtNom.setPreferredSize(dim);
        jtNom.setPreferredSize(dim);
        jtPrenom.setPreferredSize(dim);
        jtNumTel.setPreferredSize(dim);
        jtEmail.setPreferredSize(dim);
        jtAdresse.setPreferredSize(dim);
        jtNpa.setPreferredSize(dim);
        jtDateNaissance.setPreferredSize(dim);




        bottomPanel.add(lbNom);
        bottomPanel.add(jtNom);
        bottomPanel.add(lbPrenom);
        bottomPanel.add(jtPrenom);
        bottomPanel.add(lbNumTel);
        bottomPanel.add(jtNumTel);
        bottomPanel.add(lbEmail);
        bottomPanel.add(jtEmail);
        bottomPanel.add(lbAdresse);
        bottomPanel.add(jtAdresse);
        bottomPanel.add(lbNpa);
        bottomPanel.add(jtNpa);
        bottomPanel.add(lbDateNaissance);
        bottomPanel.add(jtDateNaissance);
        bottomPanel.add(checkFav);
        bottomPanel.setLayout(new GridLayout(14, 1));


        //Bottom



        centerPanel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 10));

        //Layouts
        add(topPanel);
        add(centerPanel);
        add(bottomPanel);

        bottomPanel.setVisible(false);


    }

    // ------------------ LIST + ACTION LISTENER ---------------------- //



    class EcouteurList implements ListSelectionListener {
        /**
         *
         * Va rechercher dans le tableau de contact, le contact séléctionné et l'affecter dans les champs JTextField
         */
        public void valueChanged(ListSelectionEvent evt){
            int i = jlistContact.getSelectedIndex();

            topPanel.setVisible(false);
            bottomPanel.setVisible(true);
            setEditable(false);
            if(valModifSupp == false){
                //jbAdd.setVisible(false);
                jbRetour.setVisible(true);
                jbEdit.setVisible(true);
                jbDelete.setVisible(true);
            }


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



    class ValiderEditAdd implements ActionListener{
        /**
         * Va appeler les methodes de validation (validEmain, validPhone, validBirthday) qui si elles retournent toutes une valeur vrai(true)
         * va appeler la méthode ModifChaine afin d'effectuer la modification
         * Si une des 3 méthodes de validation retournent false, va afficher le text faut en rouge afin d'effectuer les modifications nécessaires
         *
         */
        public void actionPerformed(ActionEvent e){
            try {
                int numJList = jlistContact.getSelectedIndex();
                // Test les champs
                if(validPhone(jtNumTel.getText())) {
                    jtNumTel.setForeground(Color.BLACK);
                    if (validEmail(jtEmail.getText())){
                        jtEmail.setForeground(Color.BLACK);
                        if(validNPA(jtNpa.getText())) {
                            jtNpa.setForeground(Color.BLACK);
                            // On recupère tous les champs, et on réecrit la ligne avec les nouvelles données
                            //checkFav.isSelected()
                            ModifChaine(jtNom.getText(), jtPrenom.getText(), jtNumTel.getText(), jtEmail.getText(), jtAdresse.getText(), jtNpa.getText(), jtDateNaissance.getText(), numJList);
                        } else {
                            jtNpa.setForeground(Color.RED);
                        }
                    } else {
                        jtEmail.setForeground(Color.RED);
                    }
                } else {
                    jtNumTel.setForeground(Color.RED);
                }
            } catch (Exception f) {
                System.out.println("Erreur à la modification des contacts");
                System.out.println(f.toString());
            }
        }
    }




    class ActionAdd implements ActionListener{

        /**
         * Appeler la méthode "resetChamp" et va mettre les champs JTextField vide et afficher les boutons correspondant
         * Rendre les JTextField editable afin de pouvoir ajouter des données
         *
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            topPanel.setVisible(false);
            resetChamp();
            bottomPanel.setVisible(true);
            jbAdd.setVisible(false);
            jbEdit.setVisible(false);
            jbDelete.setVisible(false);
            jbValiderEdit.setVisible(false);
            jbValiderAdd.setVisible(true);
            jbRetour.setVisible(true);
            setEditable(true);
        }

    }

    class ValiderAdd implements ActionListener{

        /**
         * Va appeler les methodes de validation (validEmain, validPhone, validBirthday) qui si elles retournent toutes une valeur vrai(true)
         *      * Si une des 3 méthodes de validation retournent false, va afficher le text faut en rouge
         * Si c'est tout bon : va appeler la méthode addInChaine qui va rajouter une ligne à la chaine de contact
         *
         * */
        @Override
        public void actionPerformed(ActionEvent e) {
            int valJList = jlistContact.getSelectedIndex();
            if(validPhone(jtNumTel.getText())) {
                jtNumTel.setForeground(Color.BLACK);
                if (validEmail(jtEmail.getText())){
                    jtEmail.setForeground(Color.BLACK);
                    if(validNPA(jtNpa.getText())) {
                        jtNpa.setForeground(Color.BLACK);
                        addInChaine(valJList);
                        resetChamp();
                        statutBtnInitial();
                    } else {
                        jtNpa.setForeground(Color.RED);
                    }
                } else {
                    jtEmail.setForeground(Color.RED);
                }
            } else {
                jtNumTel.setForeground(Color.RED);
            }
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
            bottomPanel.setVisible(true);
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

    class ActionRetour implements ActionListener{

        /**
         * Va appeler la méthode "resetChamp()" qui va réinitialiser la valeur des JTextFields (tous vides)
         * Remettre les boutons à leur état initial
         * Enlever la selection de la JList

         */

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                resetChamp();
                jlistContact.clearSelection();
                bottomPanel.setVisible(false);
                topPanel.setVisible(true);
                statutBtnInitial();

            } catch (Exception f){
                System.out.println("Erreur a l'annulation");
                System.out.println(f.toString());
            }
        }
    }



    //---------------------------- Validators ------------------------//





    /**
     * Méthodes qui vont être appelées afin de valider les champs spéciaux. Via les classes REGEX
     */


    public boolean validPhone(String tel){
        boolean response;
        response = regex.validerNumTel(tel);
        return response;
    }


    public boolean validEmail(String mail){
        boolean response;
        response = regex.validerEmail(mail);
        return response;
    }



    public boolean validNPA(String npa){
        boolean response;
        response = regex.validerNPA(npa);
        return response;
    }










    //------------------------------- METHODES -----------------------------//



    private static String[] listAffichageJList;


    /**
     * Méthode qui va ajouter une ligne au tableau de chaine.
     * Elle va créer un tableau temporaire de la longueur du tableau de chaine + 1 pour la nouvelle ligne et y affecter les valeur du tableau de chaine
     * Puis va rechercher les valeur dans les JTextField et ajouter la valeur à la dernière ligne du tableau temporaire
     * Finallement affectera le tableau temporaire à un nouveau tableau de chaine avec la nouvelle valeur
     *
     */

    public void addInChaine(int numJList){
        String temp [] = new String[chaine.length+1];

        for(int i=0; i<chaine.length; i++){
            temp[i] = chaine[i];

        }
        // Creation du tableau temporaire avec les valeur à inscrire
        temp[chaine.length] = jtNom.getText() + " - " + jtPrenom.getText() + " - " + jtNumTel.getText() + " - "+ jtEmail.getText() + " - " + jtAdresse.getText() + " - " + jtNpa.getText() + " - " +  jtDateNaissance.getText();
        chaine = new String [temp.length];
        chaine = temp;
        writeContact();
        updateList();

    }



    /**
     *
     * Méthode qui permet de sérialiser et inscrire le conetnu d'un objet ContactData préparé à l'avance, dans le fichier .txt
     *
     */

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

    /**
     Méthode qui permet de lire le fichier .txt (illisible pour nous) et le traduire en charactères lisibles dans la console
     */
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

    /**
     *     Méthode qui permet de parcourir notre String [] et de l'afficher en toute lettres dans notre fichier .txt
     */


    public void WriteData() {

        try {
            PrintWriter fichierSortie = new PrintWriter(new BufferedWriter(new FileWriter(pathFiletxt, false)));
            for (int i = 0; i<chaine.length; i++){
                if(chaine[i]!= null){
                    if(!chaine[i].contains("#delete")){
                        fichierSortie.println(chaine[i]);
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
     * Méthode de lecture du fichier pour créer un tableau de contact et une chaine à partir des données contenue dans le fichier.
     * le premier try va calculer le nombre de ligne contenue dans le fichier afin de créer les tableaux nécessaires
     * le deuxième try va s'occuper de rajouter chaque ligne dans le fichier sur le tableau définit afin d'être utilisé dans le programme
     *
     */

    public static void LectureContact() {
        String ligne;
        try{
            BufferedReader br = new BufferedReader( new InputStreamReader( new FileInputStream(pathFiletxt)));
            int cptLengthChaine = 0;
            while ((br.readLine())!= null){
                cptLengthChaine++;
            }
            chaine = new String[cptLengthChaine];
            listAffichageJList = new String[chaine.length];
            tabContactData = new ContactData[chaine.length];
            br.close();

        }catch (Exception e){
            System.out.println("Lecture fichier");
            System.out.println(e.toString());
        }

        try{
            int cpt = 0;
            BufferedReader br=new BufferedReader( new InputStreamReader( new FileInputStream(pathFiletxt)));

            while ((ligne=br.readLine())!=null ){
                chaine[cpt] = ligne ;
                cpt++;
            }
            br.close();
            updateList();
            jlistContact.setEnabled(true);
        }
        catch (Exception e){
            System.out.println("Problème lecture fichier");
            System.out.println(e.toString());
        }
    }

    /**
     * Méthode de mise à jour de la liste des contacts dans la Jlist
     * La méthode va créer un tableau temporaire afin de placer à chaque position, et dans l'ordre, les informations
     * de nom, prenom etc... du contact
     * On va ensuite créer un tableau de contact TabContactData contenant des contacts placer dans le tableau temporaire
     * Il va aussi omettre d'y inclure les lignes contenant un #delete
     *
     */


    public static void updateList(){
        listAffichageJList = new String[chaine.length];
        tabContactData = new ContactData[chaine.length];

        String[] tempo = new String[6];
        try {
            for (int i = 0; i<chaine.length; i++){
                if(chaine[i]!= null){
                    // On découpe chaque ligne du fichier en 6 partie distinctement séparées
                    tempo = chaine[i].split(" - ", 7);
                    // On crée un tableau de contact qui contiendra un objet contact avec les infos
                    tabContactData[i] = new ContactData(tempo[0], tempo[1], tempo[2], tempo[3], tempo[4], tempo[5], tempo[6]);
                    // On crée le text d'affichage de la JList
                    listAffichageJList[i] = tempo[0] + " " + tempo[1];
                    if(chaine[i].contains("#deleted")){
                        listAffichageJList[i] = null;
                    }
                }
            }

            jlistContact.setListData(listAffichageJList);
            statutBtnInitial();
            bottomPanel.setVisible(false);
        }catch (Exception e){
            System.out.println("Erreur à la mise a jour des informations");
            System.out.println(e.toString());
        }
    }

    /**
     * Méthode qui va modifier un tableau de chaine (liste de contact) selon les paramètres reçus à la ligne séléctionnée.
     * va lire le tableau au complet jusqu'a une valeur null et se positionner à la ligne du contact que on désire modifier
     * va ecraser la valeur à la position séléctionnée par un nouveau String définit
     */

    public void ModifChaine(String nom, String prenom, String num, String mail, String adresse , String npaLoc, String date, int numJList) {
        for(int i = 0; i<chaine.length; i++){
            if(chaine[i] != null){
                if(i == numJList){
                    chaine[i] = nom + " - " + prenom + " - " + num + " - " + mail + " - " + adresse + " - " + npaLoc +" - " + date;
                }
            }
        }
        updateList();
        writeContact();
        jlistContact.setEnabled(true);
        //statutBtnInitial();
    }

    /**
     * Méthode qui va simplement reset les champs JTextField null afin de pouvoir inscrire des nouveaux contact
     */

    public void resetChamp(){
        jtNom.setText(null);
        jtPrenom.setText(null);
        jtNumTel.setText(null);
        jtEmail.setText(null);
        jtAdresse.setText(null);
        jtNpa.setText(null);
        jtDateNaissance.setText(null);
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
            ModifChaine("#deleted", "#deleted", "#deleted", "#deleted", "#deleted", "#deleted", "#deleted", numJList);
        }

    }

    /**
     *
     * Méthode pour ajouter le data d'un contact à l'aide d'un printwriter
     * Check s'il n'y a pas de valeurs nulles ou deleted avant l'écriture
     *
     */

    public void writeContact(){
        try {
            try {
                PrintWriter pw = new PrintWriter(pathFiletxt);
                // Check s'il n'y a pas de valeurs nulles ou #deleted avant l'écriture
                for (int i = 0; i<chaine.length; i++){
                    if(chaine[i]!= null){
                        if(!chaine[i].contains("#delete")){
                            pw.println(chaine[i]);
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



    public static class RoundedBorder implements Border {

        private int radius;


        public RoundedBorder(int radius) {
            this.radius = radius;
        }


        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }


        public boolean isBorderOpaque() {
            return true;
        }


        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }
}
