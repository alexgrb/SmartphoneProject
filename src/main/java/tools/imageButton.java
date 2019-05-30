package tools;

import javax.swing.*;
import java.awt.*;

public class imageButton extends JButton {
    private Image mshi;

    public imageButton() {
        setOpaque(false);
        setBackground(new Color(0, true));
        setBorder(null);
    }
    public imageButton(String path) {
        loadImage(path);
        setOpaque(false);
        setBackground(new Color(0, true));
        setBorder(null);
    }

    private void loadImage(String path) {
        mshi =  new ImageIcon("src\\main\\java\\pictures\\" + path+ ".png").getImage();
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
