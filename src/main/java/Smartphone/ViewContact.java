package Smartphone;

/**
 * @author Piranavan Thambirajah & Alex Gharbi
 * Créé en mai 2019
 * Classe ViewContact qui va permettre l'affichage et la modification d'un Contact
 */

import net.miginfocom.swing.MigLayout;
import tools.imageButton;
import javax.swing.*;
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
    static ViewContact viewContact;

    //---------- JLabels ---------//
    private JLabel name = new JLabel("Nom");
    private JLabel firstName = new JLabel("Prénom");
    private JLabel phoneNumber = new JLabel("Numéro de téléphone");
    private JLabel mail = new JLabel("Mail");
    private JLabel address = new JLabel("Adresse");
    private JLabel NPA = new JLabel("NPA");
    private JLabel dateOfBirth = new JLabel("Date de naissance");
    private JLabel emptyLabel = new JLabel("");
    protected static JLabel image = new JLabel();
    //protected JLabel picture = new imageLabel("iconAdd");


    //---------- JTextfields ---------//
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
    protected static ViewContactList viewContactList;

    //---------- Boutons ---------//
    private JButton jbBack = new imageButton();
    private static JButton jbValiderAdd = new imageButton ("Valider l'ajout", 1);
    private static JButton editButton = new imageButton ("Editer", 1);
    private static JButton jbValiderEdit = new imageButton ("Valider les modifications", 1);



    //---------- Constructeurs ---------//

    /**
     * Constructeur qui affiche les informations d'un contact sélectionné directement depuis notre jListContact.
     * @param contact constructeur de la classe Contact
     * @param viewContactList constructeur de la classe viewContactList
     * @see ViewContact#setContactPanelJT()
     * @see ViewContact#setLabelsSizeJT()
     * @see ViewContact#addToPanelJT()
     */

    public ViewContact(ContactData contact, ViewContactList viewContactList) {
        setLayout(new MigLayout());
        this.contact = contact;
        this.viewContactList = viewContactList;
        viewContact = this;

        setBackground(Color.white);

        //jb

        jbBack.setIcon(new ImageIcon(picDirectory+"iconBack.png"));
        jbBack.addActionListener(new backListener());

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

    /**
     * Constructeur qui affiche un formulaire vide pour la création d'un nouveau contact.
     * On devra spécifier notre classe ViewContactList pour qu'il sache ou assigner le nouveau contact
     * @param viewContactList constructeur de la classe viewContactList
     * @see ViewContact#setEditable(boolean)
     * @see ViewContact#setLabelsSizeJT()
     * @see ViewContact#resetChamp()
     * @see ViewContact#addToPanelJT()
     */

    public ViewContact(ViewContactList viewContactList) {
        this.viewContactList = viewContactList;
        viewContact = this;
        setLayout(new MigLayout());
        setBackground(Color.white);
        viewContactList.jlistContact.getModel().getElementAt(0);

        jbBack.setIcon(new ImageIcon(picDirectory+"iconBack.png"));
        jbBack.addActionListener(new backListener());

        jbValiderAdd.setBackground(Color.white);
        jbValiderAdd.addActionListener(new ValiderAdd(this));

        setEditable(true);
        setLabelsSizeJT();
        resetChamp();
        addToPanelJT();
        add(jbValiderAdd);
    }



    //---------- END Constructeurs ---------//

    //---------- Méthodes ---------//

    /**
     * Méthode qui rempli les JTextfields avec les différentes informations du contact. (dont l'image)
     */
    public void setContactPanelJT() {
        nameJT.setText(this.contact.getNom());
        firstNameJT.setText(this.contact.getPrenom());
        phoneNumberJT.setText(this.contact.getNumTel());
        mailJT.setText(this.contact.getEmail());
        addressJT.setText(this.contact.getAdresse());
        NPAJT.setText(this.contact.getNPAloc());
        dateOfBirthJT.setText(this.contact.getDateNaissance());
        ImageIcon imgicon = new ImageIcon(picDirectory+"min\\" + this.contact.getPathImg());
        image.setIcon(imgicon);
    }

    /**
     *  Méthode qui définit la tailles des différents JTextfields
     */
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

    /**
     * Méthode qui permet de rendre tous les JTFields editable ou non, selon ce que l'on entre en valeur booleane
     * @param val true ou false
     */
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

    /**
     * Méthode qui permet d'ajouter tous les JTextfields et les Jlabel dans un panel.
     * On créer aussi le MouseListener de l'image.
     */
    public void addToPanelJT(){
        add(jbBack, "wrap");
        add(emptyLabel, "align left");
        add(image, "wrap");
        add(name,"align left, wrap");
        add(nameJT,"wrap");
        add(firstName,"wrap");
        add(firstNameJT,"wrap");
        add(phoneNumber,"wrap");
        add(phoneNumberJT,"wrap");
        add(mail,"wrap");
        add(mailJT,"wrap");
        add(address,"wrap");
        add(addressJT,"wrap");
        add(NPA,"align left");
        add(dateOfBirth,"wrap");
        add(NPAJT,"align left");
        add(dateOfBirthJT, "wrap");
        //add(pictureJT, "align left");
        //add(emptyLabel, "wrap");
        add(jbValiderAdd,"wrap");



        image.addMouseListener(new mouseListener());

    }

    /**
     * Méthode qui va modifier un tableau de chaine (liste de contact) selon les paramètres reçus à la ligne séléctionnée.
     * va lire le tableau au complet jusqu'a une valeur null et se positionner à la ligne du contact que on désire modifier
     * va ecraser la valeur à la position séléctionnée par un nouveau String définit
     * @param nom
     * @param prenom
     * @param num
     * @param mail
     * @param adresse
     * @param npaLoc
     * @param date
     * @param pathImg
     * @param numJList
     * @see ViewContactList#updateList()
     * @see Contact#writeContact()
     * @see ViewContact#setEditable(boolean)
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

    /**
     * Méthode qui va permettre de récupérer l'index de la personne qui a les memes 5 premiers caractères que la string envoyé.
     * C'est comme cela que nous retrouvons les personnes, car la JList est trier et les index ne correspondent plus.
     * @param a
     * @param target
     * @return int, l'indedx qui correspond à la string envoyé
     */
    public static int find(ContactData[] a, String target) {
        String s = "";

        for (int i = 0; i < a.length; i++) {
            s = a[i].getNom().substring(0, 5);
            if (target.equals(s))
                return i;
        }
        return -1;
    }


    /**
     * Méthode qui permet de rendre les champs vides.
     */
    public void resetChamp(){
        nameJT.setText(null);
        firstNameJT.setText(null);
        phoneNumberJT.setText(null);
        mailJT.setText(null);
        addressJT.setText(null);
        NPAJT.setText(null);
        dateOfBirthJT.setText(null);
        pictureJT.setText(null);
    }



    /**
     * Méthode qui va être appelée afin de valider les champs spéciaux d'un numéro de téléphone,
     * Via les classes REGEX
     * @param tel numéro de téléphone en String
     * @return si la le paramètre passe les tests regex elle retournera true, dans le cas contraire false
     */
    public static boolean validPhone(String tel){
        boolean response;
        response = regex.validerNumTel(tel);
        return response;
    }

    /**
     * Méthode qui va être appelée afin de valider les champs spéciaux d'un email,
     * Via les classes REGEX
     * @param mail email en String
     * @return si la le paramètre passe les tests regex elle retournera true, dans le cas contraire false
     */
    public static boolean validEmail(String mail){
        boolean response;
        response = regex.validerEmail(mail);
        return response;
    }

    /**
     * Méthode qui va être appelée afin de valider les champs spéciaux d'un numéro de téléphone,
     * Via les classes REGEX
     * @param npa npa en String
     * @return si la le paramètre passe les tests regex elle retournera true, dans le cas contraire false
     */
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
     * @see ViewContactList#updateList()
     * @see Contact#writeContact()
     */

    public static void addInChaine(){
        String temp [] = new String[viewContactList.chaine.length+1];

        for(int i=0; i<viewContactList.chaine.length; i++){
            temp[i] = viewContactList.chaine[i];
        }
        // Creation du tableau temporaire avec les valeur à inscrire
        temp[viewContactList.chaine.length] = nameJT.getText() + " - " + firstNameJT.getText() + " - " + phoneNumberJT.getText() + " - "+ mailJT.getText() + " - " + addressJT.getText() + " - " + NPAJT.getText() + " - " +  dateOfBirthJT.getText() + " - 10.png";
        viewContactList.chaine = new String [temp.length];
        viewContactList.chaine = temp;
        Contact.writeContact();
        updateList();
    }

    /**
     * Méthode appelée lors de la modification d'un contact
     * Va appeler les methodes de validation (validEmain, validPhone, validBirthday) qui si elles retournent toutes une valeur vrai(true)
     * va appeler la méthode ModifChaine afin d'effectuer la modification
     * Si une des 3 méthodes de validation retournent false, va afficher le text faut en rouge afin d'effectuer les modifications nécessaires
     * @see ViewContact#ModifChaine(String, String, String, String, String, String, String, String, int)
     * @see ViewContact#find(ContactData[], String)
     *
     */
    public void contactUpdate(){
        String i = nameJT.getText();
        String search = i.substring(0,5);
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

    /**
     * Setter du nouveau path de l'image lors de sa modification
     * @param path nom du fichier .jpg
     */
    public static void setPictureJT(String path) {
        ViewContact.pictureJT.setText(path);
    }

    //---------- END Méthodes ---------//

    //---------- Action Listeners ---------//

    /**
     * Class ValiderEditAdd qui executera les actions effectuer lors de son appel
     * @see ValiderEditAdd#actionPerformed(ActionEvent)
     *
     */

    class ValiderEditAdd implements ActionListener{
        /**
         * Va appeler la methodes de modification d'un contact.
         * @see ViewContact#contactUpdate()
         *
         */
        public void actionPerformed(ActionEvent e){
            contactUpdate();
            System.out.println("Contact modifié");
        }
    }

    /**
     * Class mouseListener qui executera les actions effectuer lors de son appel
     * @see mouseListener#mousePressed(MouseEvent)
     *
     */
    class mouseListener extends MouseAdapter {
        /**
         * Class mouseListener qui permettera de cliquer sur l'image correspondant à un contact pour ensuite la modifier.
         * Vérifier si on est bien en mode éditer (si on a cliqué sur le bouton edit : le bouton ValiderEdit sera donc visible)
         * Si on a passé la première boucle, lorsque l'on clique sur l'imgae on va enlever le panel actuel pour le remplacer par un constructeur d'une Gallery
         * @see ViewContact#find(ContactData[], String)
         *
         */
        @Override
        public void mousePressed(MouseEvent me) {
            String i = nameJT.getText();
            String search = i.substring(0,5);
            int  z = find(tabContactData,search);

            if (jbValiderEdit.isVisible()){
                removeAll();
                setLayout(new BorderLayout());
                contactImg = new Gallery(z, viewContactList);
                add(contactImg);
                revalidate();
                repaint();
            }

            else
                System.out.println("Il faut être en mode edit pour changer l'image");


        }
    }

    /**
     * Class ValiderAdd qui executera les actions effectuer lors de son appel
     * @see ValiderAdd#actionPerformed(ActionEvent)
     *
     */
    class ValiderAdd implements ActionListener{
        ViewContact viewContact;

        /**
         * ????????????????????????????????????????
         * @param viewContact
         */
        public ValiderAdd(ViewContact viewContact) {
            this.viewContact = viewContact;
        }

        /**
         * Va appeler les methodes de validation (validEmain, validPhone, validBirthday) qui si elles retournent toutes une valeur vrai(true)
         * Si une des 3 méthodes de validation retournent false, va afficher le text faut en rouge
         * Si c'est tout bon : va appeler la méthode addInChaine qui va rajouter une ligne à la chaine de contact,
         * reset les champs, supprimer le panel actuel pour nous renvoyer à la Jlist
         * @see ViewContact#addInChaine()
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(validPhone(phoneNumberJT.getText())) {
                phoneNumberJT.setForeground(Color.BLACK);
                if (validEmail(mailJT.getText())){
                    mailJT.setForeground(Color.BLACK);
                    if(validNPA(NPAJT.getText())) {
                        NPAJT.setForeground(Color.BLACK);
                        addInChaine();
                        resetChamp();
                        removeAll();
                        viewContactList.show.add(viewContactList.scrollPane);
                        viewContactList.show.add(viewContactList.jbAdd, "top");
                        viewContactList.show.repaint();
                        viewContactList.show.revalidate();


                    } else {
                        NPAJT.setForeground(Color.RED);
                    }
                } else {
                    mailJT.setForeground(Color.RED);
                }
            } else {
                phoneNumberJT.setForeground(Color.RED);
            }

        }

    }
    /**
     * Class backListener qui executera les actions effectuer lors de son appel
     * @see backListener#actionPerformed(ActionEvent)
     *
     */
    class backListener implements ActionListener {
        /**
         * Bouton qui servira simplement à retourner sur la jContatlist en faisant appel à la classe ViewContactList
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            removeAll();
            viewContact.setVisible(false);
            viewContactList.setPreferredSize(new Dimension(480,800));
            viewContactList.show.add(viewContactList.scrollPane);
            viewContactList.show.setBorder(null);
            viewContactList.show.add(viewContactList.jbAdd, "top");
            viewContactList.show.repaint();
            viewContactList.show.revalidate();

        }
    }

    /**
     * Class ActionEdit qui executera les actions effectuer lors de son appel
     * @see ActionEdit#actionPerformed(ActionEvent)
     *
     */
    class ActionEdit implements ActionListener{
        /**
         * Va rendre les Jtextfields editables, via la méthode setEditable
         * @see ViewContact#setEditable(boolean)
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            setEditable(true);
            jbValiderEdit.setVisible(true);
        }
    }

    //---------- END Action Listeners ---------//

    //mdr


}

