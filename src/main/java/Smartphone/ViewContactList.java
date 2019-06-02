package Smartphone;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;

public class ViewContactList extends JPanel {

    protected static JList jlistContact = new JList();
    private static String[] listAffichageJList;
    private static String[] listSortedAffichageJList;
    private static ContactData[] tabContactData;
    public static String pathFiletxt = ".\\contact.txt";
    private static String[] chaine;
    private ViewContactList viewContactList;
    protected JScrollPane scrollPane;

    /*


     */

    public ViewContactList() {
       // updateList();
        viewContactList = this;

        jlistContact.setBackground(Color.LIGHT_GRAY);
        jlistContact.addListSelectionListener(new EcouteurList());
        //jlistContact.setFont(fontJList);
       // jlistContact.setPreferredSize(dimJlist);
        scrollPane = new JScrollPane(jlistContact);
        add(scrollPane);
    }

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

            jlistContact.setListData(listAffichageJList);
            sortList(jlistContact);
         //   statutBtnInitial();
        //    formPanel.setVisible(false);
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
            int z = jlistContact.getSelectedIndex();
            String i = (String) jlistContact.getSelectedValue();
            // String search = i.substring(0,5);
            System.out.println(i);
            // setContactPanel(search);

            JPanel viewContact = new ViewContact(tabContactData[z], viewContactList);
            jlistContact.removeAll();
            removeAll();
            jlistContact.setVisible(false);
            viewContact.setBounds(0,0,480,800);
            add(viewContact);

        }
    }

}
