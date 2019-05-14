package Smartphone;

import tools.imageLabel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

               display wow = new display();
               wow.setSize(480, 800);
               wow.setVisible(true); //Affiche la fenêtre
               Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
               wow.setLocation(dim.width/2-wow.getSize().width/2, dim.height/2-wow.getSize().height/2);

        //imageLabel test= new imageLabel();
    }

}
