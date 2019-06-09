package Smartphone;

import tools.imageLabel;

import javax.swing.*;
import java.awt.*;

/**
 * Elle initialise la JFrame et va lire le fichier txt qui contient les contacts.
 * @author Piranavan Thambirajah & Alex Gharbi
 */
public class Main {
    // static final String dir = System.getProperty("user.dir");
    static String dir = "C:\\Users\\piran\\IdeaProjects\\SmartphoneProject";

    public static void main(String[] args) {

        //POUR EXéCUTER SANS LE JAR -
        //METTEZ LE CHEMIN ABSOLU VERS LES SRC DANS LA VARIABLE dir !!!!

        dir = args[0]; //On passe l'argument comme étant le chemin vers les src

        ViewContactList.LectureContact();
        display screen = null;
        screen = new display();


        screen.setSize(480, 800);
               screen.setVisible(true); //Affiche la fenêtre
               Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
               screen.setLocation(dim.width/2-screen.getSize().width/2, dim.height/2-screen.getSize().height/2);

    }
}

