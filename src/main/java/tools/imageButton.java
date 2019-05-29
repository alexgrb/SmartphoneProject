package tools;

import javax.swing.*;
import java.awt.*;

public class imageButton extends JButton {
    private Image mshi;

    public imageButton(String text, String path) {
        loadImage(path);
        setSurfaceSize(text);
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
