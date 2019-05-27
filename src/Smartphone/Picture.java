package Smartphone;

import tools.ImageResizer;
import tools.imageLabel;

import javax.swing.*;
import java.io.IOException;

public class Picture extends JPanel {

    public Picture(String path){

        setLayout(null);
     /*   try {
            ImageResizer.resize("src\\pictures\\" + path+ ".png", "src\\pictures\\" + path+ "Bis.png", 350, 500);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        imageLabel picture = new imageLabel(path);

        picture.setBounds(30,30,350,500);
        add(picture);
    }

}
