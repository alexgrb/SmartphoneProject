package Smartphone;

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
      /*  switch (str){
            case "weather" : //La valeur entrée nous permet de connaitre le fond à mettre
               path =  "src\\pictures\\iconWeather.png";
               break;
            case "home" :
                path = "src\\pictures\\iconHome.png";
                break;
            default:
                path = "src\\pictures\\iconHome.png";
        }
        try {
            img = ImageIO.read(new File(path.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    /*public void paintComponent(Graphics g){ //JSP ce que c'est j'ai pris sur internet
        Graphics2D g2d = (Graphics2D)g;
        GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, 20, Color.cyan, true);
        g2d.setPaint(gp);
        g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        g2d.setColor(Color.black);
        g2d.drawString(this.name, this.getWidth() / 2 - (this.getWidth() / 2 /4), (this.getHeight() / 2) + 5);
    }*/
}