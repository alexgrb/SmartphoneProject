package tools;

import Smartphone.Contact;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class imageButton extends JButton {
    private Image mshi;

    //String rootPath = "src\\pictures\\";
    public imageButton(String text, String path) {
        loadImage(path);
        setSurfaceSize(text);
    }

    public imageButton(String text, int text2) {
        setText(text);
        setForeground(Color.BLACK);
        setBackground(Color.WHITE);
        Border ligne = new LineBorder(Color.BLACK);
        Border marge = new EmptyBorder(5, 15, 5, 15);
        Border autour = new CompoundBorder(ligne, marge);
        setBorder(autour);
        setBorder(new Contact.RoundedBorder(5)); //10 c'est le rayon
        setFont(Contact.fontBouton);
    }

    private void loadImage(String path) {
        mshi =  new ImageIcon("src\\pictures\\" + path+ ".png").getImage();
    }

    private void setSurfaceSize(String text) {
        Dimension d = new Dimension();
        d.width = mshi.getWidth(null);
        d.height = mshi.getHeight(null);
        setPreferredSize(d);
        setText(text);
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(mshi, 0, 0, null);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
}
