package Smartphone;

import javax.swing.*;
import java.awt.*;

public class weather extends JPanel {

    JPanel back = new JPanel();
    private display weatherScreen;
    protected Dimension dim	= new Dimension(400, 30);

    public weather(display weatherScreen) {
        this.weatherScreen = weatherScreen;
        ImageIcon i = new ImageIcon("src\\pictures\\weatherBackground.jpg");
        JLabel j = new JLabel(i);

        back.add(j);

        add(back);
    }
}
