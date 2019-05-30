package Smartphone;

import net.miginfocom.swing.MigLayout;
import tools.imageButton;
import tools.imageLabel;

import javax.swing.*;

import static Smartphone.display.maxApp;

public class Home extends JPanel {

    JButton[] appButton = new JButton[maxApp];
    public Home() {

        setLayout(new MigLayout("wrap 2"));
        JLabel j = new imageLabel("homeBackground");
        add(j);
        for (int i=0 ; i<appButton.length;i++){
            appButton[i] = new JButton();
            appButton[i] = new imageButton(); //Cela va rendre le fond transparent
            appButton[i].addActionListener(new display.homeListener(i)); //Ajoute l'action listener afin de pouvoir naviguer entre les appli
        }
        appButton[0].setIcon(new ImageIcon("src\\main\\java\\pictures\\iconWeather.png"));
        appButton[1].setIcon(new ImageIcon("src\\main\\java\\pictures\\iconContact.png"));
        appButton[2].setIcon(new ImageIcon("src\\main\\java\\pictures\\iconeHome.png"));
        appButton[3].setIcon(new ImageIcon("src\\main\\java\\pictures\\iconCalculette.png"));
        appButton[4].setIcon(new ImageIcon("src\\main\\java\\pictures\\iconGallery.png"));

      /*  int x=10; //Position X
        int y=200; //Position Y
        int width=90;
        int height=35;
        int cpt=0; //Compteur pour changer de ligne qu'on arrive à 4
*/
        for(int z = 0; z< maxApp; z++){
           // appButton[z].setBounds(x,y,width,height);
            add(appButton[z]);
         /*   x+=95; //On se déplace sur la droite de 105 pixel
            cpt++;
            if(cpt==4) { //Si on a déjà 4 app sur la ligne
                y += 50; //On décale vers le bas
                x = 10; //On revient tout à gauche

            }
        */}

    }
}
