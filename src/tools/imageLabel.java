package tools;

import javax.swing.*;
import java.awt.*;

public class imageLabel extends JLabel {
    private Image mshi;

    //String rootPath = "src\\pictures\\";
    public imageLabel(String path) {
      //  mshi = new ImageIcon("src\\pictures\\" + path.toString()+ ".png");
        //Filereader
        //this get weight et get height
        loadImage(path);
        setSurfaceSize();
        //set pathname après au lieu de recréer un JLAbel
    }

    private void loadImage(String path) {

        mshi =  new ImageIcon("src\\pictures\\" + path+ ".png").getImage();
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