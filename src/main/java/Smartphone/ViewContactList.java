package Smartphone;

import net.miginfocom.swing.MigLayout;
import tools.imageButton;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

import static Smartphone.display.picDirectory;

public class ViewContactList extends JPanel {

    protected static JList jlistContact = new JList();
    private static String[] listAffichageJList;
    private static String[] listSortedAffichageJList;
    protected static ContactData[] tabContactData;
    public static String pathFiletxt = ".\\contact.txt";
    protected static String[] chaine;
    private ViewContactList viewContactList;
    protected JScrollPane scrollPane;
    protected static JButton jbAdd = new JButton ();
    protected JPanel show = new JPanel();


    public ViewContactList() {
        viewContactList = this;

        show.setBackground(Color.white);
        show.setLayout(new MigLayout());
        show.setPreferredSize(new Dimension(480,800));
        show.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 10));

        jbAdd = new imageButton();
        jbAdd.setIcon(new ImageIcon(picDirectory+"iconAdd.png"));
        jbAdd.addActionListener(new ActionAdd());

        jlistContact.setBackground(Color.white);
        jlistContact.addListSelectionListener(new EcouteurList());
        jlistContact.setFont(new Font("Helvetica", Font.BOLD, 25));

        scrollPane = new JScrollPane(jlistContact);
        scrollPane.setPreferredSize(new Dimension(400,740));
        scrollPane.setBorder(null);

        show.add(scrollPane);
        show.add(jbAdd, "top");
        add(show);


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
        listSortedAffichageJList = new String[chaine.length];
        tabContactData = new ContactData[chaine.length];

        String[] tempo = new String[6];
        try {
            for (int i = 0; i<chaine.length; i++){

                if(chaine[i]!= null){
                    // On découpe chaque ligne du fichier en 6 partie distinctement séparées
                    tempo = chaine[i].split(" - ", 8);
                    // On crée un tableau de contact qui contiendra un objet contact avec les infos
                    tabContactData[i] = new ContactData(tempo[0], tempo[1], tempo[2], tempo[3], tempo[4], tempo[5], tempo[6], tempo[7]);
                    // On crée le text d'affichage de la JList

                    listAffichageJList[i] = tempo[0] + " " + tempo[1];

                    if(chaine[i].contains("#deleted")){
                        listAffichageJList[i] = null;
                    }
                }
            }
            System.out.println("Je passe dans le try aussi");
            jlistContact.setListData(listAffichageJList);
            sortList(jlistContact);
        }catch (Exception e){
            System.out.println("Erreur à la mise a jour des informations");
            System.out.println(e.toString());
        }
    }
    public static void sortList(JList jlist) {

        ListModel model = jlist.getModel();

        int n = chaine.length;
        String[]data = new String[n];
        for (int i=0; i<n; i++){
            data[i] = (String) model.getElementAt(i);
        }
        Arrays.sort(data);
        jlist.setListData(data);
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
            ViewContactList.updateList();
            jlistContact.setEnabled(true);
        }
        catch (Exception e){
            System.out.println("Problème lecture fichier");
            System.out.println(e.toString());
        }
    }
    class EcouteurList implements ListSelectionListener {
        /**
         *
         * Va rechercher dans le tableau de contact, le contact séléctionné et l'affecter dans les champs JTextField
         */
        public void valueChanged(ListSelectionEvent evt){

            String i = (String) jlistContact.getSelectedValue();
            String search = i.substring(0,5);
            System.out.println(i);
           int  z = find(tabContactData,search);
            JPanel viewContact = new ViewContact(tabContactData[z], viewContactList);

            show.removeAll();
            viewContact.setBounds(0,0,480,800);
            show.add(viewContact);
            show.repaint();
            show.revalidate();
        }
    }
    class ActionAdd implements ActionListener {

        /**
         * Appeler la méthode "resetChamp" et va mettre les champs JTextField vide et afficher les boutons correspondant
         * Rendre les JTextField editable afin de pouvoir ajouter des données
         *
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            JPanel viewContact = new ViewContact(viewContactList);
            show.removeAll();

            viewContact.setBounds(0,0,480,800);
            show.add(viewContact);
            show.repaint();
            show.revalidate();

        }

    }

}