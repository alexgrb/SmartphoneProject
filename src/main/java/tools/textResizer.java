package tools;

import javax.swing.*;
import java.awt.*;

public class textResizer {
    private JLabel text;

    public textResizer(JLabel givenLabel) {
        text = givenLabel;

        Font labelFont = text.getFont();
        String labelText = text.getText();

        int stringWidth = text.getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = text.getWidth();
        System.out.println("Width : "+ text.getWidth());
        System.out.println(" w2 : "+ text.getFontMetrics(labelFont).stringWidth(labelText));

        // Find out how much the font can grow in width.
        double widthRatio = (double)componentWidth / (double)stringWidth;

        int newFontSize = (int)(labelFont.getSize() * widthRatio);
        int componentHeight = text.getHeight();

        // Pick a new font size so it will not be larger than the height of label.
        int fontSizeToUse = Math.min(newFontSize, componentHeight);

        // Set the label's font size to the newly determined size.
        text.setFont(new Font(labelFont.getName(), Font.PLAIN, (fontSizeToUse)));
    }
    public textResizer(JLabel givenLabel, int fontSizeToUse) {
        text = givenLabel;

        Font labelFont = text.getFont();

        text.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
    }
    public textResizer(JLabel givenLabel, int fontSizeToUse, int i) {
        text = givenLabel;

        Font labelFont = text.getFont();

        text.setFont(new Font(labelFont.getName(), Font.BOLD, fontSizeToUse));
    }

}
