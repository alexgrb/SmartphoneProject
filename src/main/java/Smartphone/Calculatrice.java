package Smartphone;

import net.miginfocom.swing.MigLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * Calculatrice reprise de openclassroom et mis en forme à nos gôuts.
 */

public class Calculatrice extends JPanel {
    //Tableau stockant les éléments à afficher dans la calculatrice
    String[] tab_string = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", ".", "=", "C", "+", "-", "*", "/"};
    //Un bouton par élément à afficher
    JButton[] tab_button = new JButton[tab_string.length];
    private JLabel ecran = new JLabel();
    private Dimension dim = new Dimension(80, 80);
    private Dimension dim2 = new Dimension(80, 80);
    private double chiffre1;
    private boolean clicOperateur = false, update = false;
    private String operateur = "";


    /**
     * Constructeur faisant appel à la méthode d'initialisation
     * @see Calculatrice#initComposant()
     */
    public Calculatrice(){
        this.setSize(240, 260);

        //On initialise le conteneur avec tous les composants
        initComposant();
        //On ajoute le conteneur

        this.setVisible(true);
    }

    /**
     * Méthode initialisant le conteneur avec tous les composants
     */
    private void initComposant(){
        //On définit la police d'écriture à utiliser
        Font police = new Font("Arial", Font.BOLD, 20);
        ecran = new JLabel("0");
        ecran.setFont(police);
        //On aligne les informations à droite dans le JLabel
        ecran.setHorizontalAlignment(JLabel.RIGHT);
        ecran.setPreferredSize(new Dimension(400, 50));
        JPanel operateur = new JPanel();
        operateur.setLayout(new MigLayout("wrap"));
       // operateur.setPreferredSize(new Dimension(55, 225));
        JPanel chiffre = new JPanel();
        chiffre.setLayout(new MigLayout("wrap 3"));
       // chiffre.setPreferredSize(new Dimension(165, 225));
        JPanel panEcran = new JPanel();
       // panEcran.setPreferredSize(new Dimension(220, 30));

        setLayout(new MigLayout("wrap 2"));
        setBackground(Color.white);
        //On parcourt le tableau initialisé
        //afin de créer nos boutons
        for(int i = 0; i < tab_string.length; i++){
            tab_button[i] = new JButton(tab_string[i]);
            tab_button[i].setPreferredSize(dim);
            tab_button[i].setFont(new Font("Helvetica", Font.PLAIN, 25));
            switch(i){
                //Pour chaque élément situé à la fin du tableau
                //et qui n'est pas un chiffre
                //on définit le comportement à avoir grâce à un listener
                case 11 :
                    tab_button[i].addActionListener(new EgalListener());
                    chiffre.add(tab_button[i]);
                    break;
                case 12 :
                    tab_button[i].setForeground(Color.red);
                    tab_button[i].addActionListener(new ResetListener());
                    //operateur.add(tab_button[i]);
                    break;
                case 13 :
                    tab_button[i].addActionListener(new PlusListener());
                    tab_button[i].setPreferredSize(dim2);
                    operateur.add(tab_button[i]);
                    break;
                case 14 :
                    tab_button[i].addActionListener(new MoinsListener());
                    tab_button[i].setPreferredSize(dim2);
                    operateur.add(tab_button[i]);
                    break;
                case 15 :
                    tab_button[i].addActionListener(new MultiListener());
                    tab_button[i].setPreferredSize(dim2);
                    operateur.add(tab_button[i]);
                    break;
                case 16 :
                    tab_button[i].addActionListener(new DivListener());
                    tab_button[i].setPreferredSize(dim2);
                    operateur.add(tab_button[i]);
                    break;
                default :
                    //Par défaut, ce sont les premiers éléments du tableau
                    //donc des chiffres, on affecte alors le bon listener
                    chiffre.add(tab_button[i]);
                    tab_button[i].addActionListener(new ChiffreListener());
                    break;
            }
        }
        panEcran.add(ecran);
        panEcran.setBorder(BorderFactory.createLineBorder(Color.black));
        add(panEcran, "span, gaptop 50, gapbottom 40");
        add(chiffre);
        add(operateur);
        add(tab_button[12],"gapleft 8");
    }

    /**
     * Méthode permettant d'effectuer un calcul selon l'opérateur sélectionné
     */
    private void calcul(){
        if(operateur.equals("+")){
            chiffre1 = chiffre1 +
                    Double.valueOf(ecran.getText()).doubleValue();
            ecran.setText(String.valueOf(chiffre1));
        }
        if(operateur.equals("-")){
            chiffre1 = chiffre1 -
                    Double.valueOf(ecran.getText()).doubleValue();
            ecran.setText(String.valueOf(chiffre1));
        }
        if(operateur.equals("*")){
            chiffre1 = chiffre1 *
                    Double.valueOf(ecran.getText()).doubleValue();
            ecran.setText(String.valueOf(chiffre1));
        }
        if(operateur.equals("/")){
            try{
                chiffre1 = chiffre1 /
                        Double.valueOf(ecran.getText()).doubleValue();
                ecran.setText(String.valueOf(chiffre1));
            } catch(ArithmeticException e) {
                ecran.setText("0");
System.out.println("lol");
            }
        }
    }



    /**
     * Class ActionDelete qui executera les actions effectuer lors de son appel
     * @see ChiffreListener#actionPerformed(ActionEvent)
     *
     */
    class ChiffreListener implements ActionListener {
        /**
         * Listener utilisé pour les chiffres
         * Permet de stocker les chiffres et de les afficher
         * @param e
         */
        public void actionPerformed(ActionEvent e){
            //On affiche le chiffre additionnel dans le label
            String str = ((JButton)e.getSource()).getText();
            if(update){
                update = false;
            }
            else{
                if(!ecran.getText().equals("0"))
                    str = ecran.getText() + str;
            }
            ecran.setText(str);
        }
    }

    /**
     * Listener affecté au bouton =
     * @see Calculatrice#calcul()
     */
    class EgalListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
            calcul();
            update = true;
            clicOperateur = false;
        }
    }

    /**
     * Listener affecté au bouton +
     * @see Calculatrice#calcul()
     */
    class PlusListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
            if(clicOperateur){
                calcul();
                ecran.setText(String.valueOf(chiffre1));
            }
            else{
                chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
                clicOperateur = true;
            }
            operateur = "+";
            update = true;
        }
    }

    /**
     * Listener affecté au bouton -
     * @see Calculatrice#calcul()
     */
    class MoinsListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
            if(clicOperateur){
                calcul();
                ecran.setText(String.valueOf(chiffre1));
            }
            else{
                chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
                clicOperateur = true;
            }
            operateur = "-";
            update = true;
        }
    }

    /**
     * Listener affecté au bouton *
     * @see Calculatrice#calcul()
     */
    class MultiListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
            if(clicOperateur){
                calcul();
                ecran.setText(String.valueOf(chiffre1));
            }
            else{
                chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
                clicOperateur = true;
            }
            operateur = "*";
            update = true;
        }
    }

    /**
     * Listener affecté au bouton /
     * @see Calculatrice#calcul()
     */
    class DivListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
            if(clicOperateur){
                calcul();
                ecran.setText(String.valueOf(chiffre1));
            }
            else{
                chiffre1 = Double.valueOf(ecran.getText()).doubleValue();
                clicOperateur = true;
            }
            operateur = "/";
            update = true;
        }
    }

    /**
     * Listener affecté au bouton de remise à zero
     * @see Calculatrice#calcul()
     */
    class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0){
            clicOperateur = false;
            update = true;
            chiffre1 = 0;
            operateur = "";
            ecran.setText("");
        }
    }
}