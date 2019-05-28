package tools;

import javax.swing.*;
import java.awt.*;

public class imageLabel extends JLabel {
    private Image mshi;

    //String rootPath = "src\\pictures\\";
    public imageLabel(String path) {
        loadImage(path);
        setSurfaceSize();
    }
    public imageLabel(String path, int i) { //i Nous permet de faire une autre méthode quasi identique
        loadImage("gallery\\"+path);
        setSurfaceSize();
    }

    public void loadImage(String path) {
        mshi =  new ImageIcon("src\\main\\java\\pictures\\" + path+ ".png").getImage();
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
