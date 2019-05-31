package SortedList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Arrays;
import java.util.Random;

public class MaFrame extends JFrame {

    final JList list = new JList();

    public MaFrame(){

        getContentPane().setLayout(new BorderLayout());

        final DefaultListModel listModel = new DefaultListModel();

        Random r = new Random();

       // new String [] yo

        for (int i=0; i<50; i++){
            listModel.addElement("Spacecraft" + (Math.abs(r.nextInt()%100)));
        }

        //Instantier la Jlist


        //Gui
        getContentPane().add(BorderLayout.CENTER, new JScrollPane(list));
        JButton sortButton = new JButton("Sort");
        sortButton.addActionListener(new ActionsortList());

        getContentPane().add(BorderLayout.SOUTH, sortButton);





    }

    class ActionsortList implements ActionListener{

        /**
         * Appeler la méthode "resetChamp" et va mettre les champs JTextField vide et afficher les boutons correspondant
         * Rendre les JTextField editable afin de pouvoir ajouter des données
         *
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("je sorte la liste");
            sortList(list);
        }

    }

    public void sortList (JList jlist) {
        ListModel model = jlist.getModel();

        int n = model.getSize();
        String[]data = new String[n];

        for (int i=0; i<n; i++){
            data[i] = (String) model.getElementAt(i);
        }

        Arrays.sort(data);
        jlist.setListData(data);
    }

    public static void main (String[] args) {
        (new MaFrame()).setVisible(true);
    }



}
