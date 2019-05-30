package tools;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class imageLabel extends JLabel {
    private Image mshi;

    public imageLabel(String path) {
        loadImage(path);
        setSurfaceSize();
    }
    public imageLabel(String path, int i) { //i Nous permet de faire une autre méthode quasi identique
        if(i==1) {
            String imageToResize = "src\\main\\java\\pictures\\gallery\\" + path;
            String resizedImagePath = "src\\main\\java\\pictures\\min\\" + path;
            try {
                ImageResizer.resize(imageToResize, resizedImagePath, 100, 100);
            } catch (IOException e) {
                 e.printStackTrace();
            }
            loadImageGallery(resizedImagePath);
        }
        else {
        loadImageBig("big\\" + path);
        }
        setSurfaceSize();

    }

    public void loadImage(String path) {
        mshi =  new ImageIcon("src\\main\\java\\pictures\\" + path+".png").getImage();
    }
    public void loadImageBig(String path) {
        mshi =  new ImageIcon("src\\main\\java\\pictures\\" + path).getImage();
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
}
