package Smartphone;

import tools.imageLabel;

import javax.swing.*;
import java.awt.*;

/**
 * Elle initialise la JFrame et va lire le fichier txt qui contient les contacts.
 * @author Piranavan Thambirajah & Alex Gharbi
 */
public class Main {
    public static void main(String[] args) {

        ViewContactList.LectureContact();
        display screen = null;
        screen = new display();


        screen.setSize(480, 800);
               screen.setVisible(true); //Affiche la fenêtre
               Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
               screen.setLocation(dim.width/2-screen.getSize().width/2, dim.height/2-screen.getSize().height/2);

    }
}

