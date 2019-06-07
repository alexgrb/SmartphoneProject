package tools;

import Smartphone.Contact;
import Smartphone.display;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * @author Piranavan Thambirajah & Alex Gharbi
 * Créé en mai 2019
 * Classe ImageButton, fait partie du package Tools, cette classe permet
 * d'alléger les implémentations et uniformiser les boutons avec ou sans icônes.
 */

public class imageButton extends JButton {
    private Image mshi;
    private ImageIcon pic;
    private String picDirectory = display.getPicDirectory();

    /**
     * Constructeur qui permet de construire un bouton de manière uniforme ?????????????
     */

    public imageButton() {
        setOpaque(false);
        setBackground(new Color(0, true));
        setBorder(null);
    }

    /**
     * Constructeur qui permet de construire un bouton avec une icône, sans fond ni bordure
     * @param path nom du fichier .jpg
     * @see imageButton#loadImage(String)
     */

    public imageButton(String path) {

        setOpaque(false);
        setBackground(new Color(0, true));
        setBorder(null);
        loadImage(path);
    }

    /**
     * Constructeur qui permet de construire un bouton de type texte
     * @param text le texte que l'on souhaitera afficher dans notre bouton
     * @param i paramètre qui permet de différencier ce constructeur du  précédent
     *
     */
    public imageButton(String text, int i) {
        setText(text);
        setForeground(Color.BLACK);
        setBackground(Color.LIGHT_GRAY);
        Border ligne = new LineBorder(Color.BLACK);
        Border marge = new EmptyBorder(10, 15, 10, 15);
        Border autour = new CompoundBorder(ligne, marge);
        setBorder(autour);
        //setBorder(new Contact.RoundedBorder(10));
        setFont(Contact.fontBouton);
    }

    /**
     * Méthode qui permet de créer une nouvelle ImageIcon à partir d'un nom de fichier
     * @param path nom du fichier .jpg
     */
    private void loadImage(String path) {
       // pic = new ImageIcon(getClass().getClassLoader().getResource(path+".png"));
        pic = new ImageIcon(picDirectory+path+".png");
        mshi = pic.getImage();
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
