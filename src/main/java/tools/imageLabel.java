package tools;


import Smartphone.display;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class imageLabel extends JLabel {
    private Image mshi;

    private String picDirectory = display.getPicDirectory();

    public imageLabel(String path) {
        try {
            loadImage(path);
            setSurfaceSize();}
        catch(Exception ex){
            System.out.println("Le path de l'imgage choisi n'existe pas!!");
            System.out.println(ex.toString());
        }
    }
    public imageLabel(String path, int i) { //i Nous permet de faire une autre méthode quasi identique pour
        if(i==1) {
            String imageToResize = picDirectory+"gallery\\" + path;
            String resizedImagePath = picDirectory+"min\\" + path;
            try {
                ImageResizer.resize(imageToResize, resizedImagePath, 100, 100);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Pas rtouvé");
            }
            loadImageGallery(resizedImagePath);
        }
        else {
            loadImageBig("big\\" + path);
        }
        setSurfaceSize();

    }

    /**
     * Méthode permettant d'uploader une ImageIcon depuis notre gallerie
     * @param path le nom de l'image
     */

    public void loadImage(String path) {

        File f = new File(picDirectory + path+".png");
        mshi =  new ImageIcon(picDirectory + path+".png").getImage();
        FileReader toReturn = null;
        try
        {
            if (!f.exists()) {
                System.out.println("Je suis la "+ picDirectory+ path+".png");
                throw new FileNotFoundException();
            }
            else {
                System.out.println("Sisi pas d'erreur");
            }
        }
        catch(FileNotFoundException e)
        {
            //System.out.println("Path de l'image : "+ path + " is " + f.exists());
            System.out.println("Cette image n'existe pas");
            System.out.println(e.toString());
        }
        catch(IOException e){
            throw e;
        }

    }

    /**
     * Méthode permettant d'uploader une "big" ImageIcon depuis notre gallerie
     * @param path le nom de l'image
     */
    public void loadImageBig(String path) {
        mshi =  new ImageIcon(picDirectory+ path).getImage();
    }
    public void loadImageGallery(String path) {
        mshi =  new ImageIcon(path).getImage();
    }

    private void setSurfaceSize() {
        Dimension d = new Dimension();
        d.width = mshi.getWidth(null);
        d.height = mshi.getHeight(null);
        setPreferredSize(d);
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
}        //mdr
