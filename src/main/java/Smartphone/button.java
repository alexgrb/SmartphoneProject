package main.java.Smartphone;

import java.awt.*;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JButton;

public class button extends JButton {
    private String name;
    private Image img;
    private String path;

    public button(String str){ //Lors de l'instanciation, une valeur est entrée
        super(str);
        this.setBackground(Color.WHITE);
        this.name = str;
    }
}