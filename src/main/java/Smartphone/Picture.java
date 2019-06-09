package Smartphone;

import tools.ImageResizer;
import tools.imageLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import static Smartphone.display.picDirectory;

public class Picture extends JPanel {

    private JButton backBTN = new JButton("");
    private JButton deleteBTN = new JButton("");
    private String path;
    private Gallery gallery;


    /**
     * Constucteur Picture. Permet d'afficher en grand une image.
     * Il va utiliser une autre classe pour redimensionner l'image.
     * @see ImageResizer
     * @param path
     *      Nom de l'image à afficher
     * @param gallery
     *      Objet galerie afin qu'on puisse revenir dessus.
     */
    public Picture(String path, Gallery gallery){
        this.gallery=gallery;
        this.path=path;
        backBTN.setIcon(new ImageIcon(picDirectory+"iconBack.png"));
        backBTN.setOpaque(false);
        backBTN.setBackground(new Color(0,true));
        backBTN.setBorder(null);
        backBTN.addActionListener(new backBTNListener());
        setLayout(null);
        backBTN.setBounds(10,10,40,40);
        add(backBTN);

        deleteBTN.setIcon(new ImageIcon(picDirectory+"iconDelete.png"));
        deleteBTN.setOpaque(false);
        deleteBTN.setBackground(new Color(0,true));
        deleteBTN.setBorder(null);
        deleteBTN.addActionListener(new deleteBTNListener());
        setLayout(null);
        deleteBTN.setBounds(420,10,40,40);
        add(deleteBTN);
        setOpaque(false);

       try {
            ImageResizer.resize(picDirectory+"gallery\\" + path, picDirectory+"big\\" + path, 470, 470);

        } catch (IOException e) {
            e.printStackTrace();
        }

        imageLabel picture = new imageLabel(path,2);

        picture.setBounds(1,50,470,470);
        add(picture);
    }

    /**
     * Enlève tout du panel et raffiche la gallerie
     */

    class backBTNListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            removeAll();
            add(Gallery.finalPanel);
            add(Gallery.nbPhoto);
            add(Gallery.jbAdd);
            revalidate();
            repaint();
        }
    }

    /**
     * Lancer une méthode qui permet de supprimer l'image.
     * @see Gallery#deletePhoto(String)
     */

    class deleteBTNListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            gallery.deletePhoto(path);
        }
    }
 }



