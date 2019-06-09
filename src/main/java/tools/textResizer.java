package tools;

import javax.swing.*;
import java.awt.*;

public class textResizer {
    private JLabel text;

    /**
     * Modifie la taille du texte en fonction de la place disponible
     * @param givenLabel
     *         Label contenant le texte à modifier
     */
    public textResizer(JLabel givenLabel) {
        text = givenLabel;

        Font labelFont = text.getFont();
        String labelText = text.getText();

        int stringWidth = text.getFontMetrics(labelFont).stringWidth(labelText);
        int componentWidth = text.getWidth();

        // Find out how much the font can grow in width.
        double widthRatio = (double)componentWidth / (double)stringWidth;

        int newFontSize = (int)(labelFont.getSize() * widthRatio);
        int componentHeight = text.getHeight();

        // Pick a new font size so it will not be larger than the height of label.
        int fontSizeToUse = Math.min(newFontSize, componentHeight);

        // Set the label's font size to the newly determined size.
        text.setFont(new Font(labelFont.getName(), Font.PLAIN, (fontSizeToUse)));
    }

    /**
     * Modifie la taille du texte par rapport à la taille donnée
     * @param givenLabel
     *         Label contenant le texte à modifier
     * @param fontSizeToUse
     *          Taille de texte voulu
     */
    public textResizer(JLabel givenLabel, int fontSizeToUse) {
        text = givenLabel;

        Font labelFont = text.getFont();

        text.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
    }

    /**
     * Modifie la taille du texte par rapport à la taille donné et passe aussi en gras.
     * @param givenLabel
     *          Label content le texte à modifier
     * @param fontSizeToUse
     *          Taille de texte voulu
     * @param i
     *          Si un nombre est donnée, il passe le texte en gras.
     */
    public textResizer(JLabel givenLabel, int fontSizeToUse, int i) {
        text = givenLabel;
        Font labelFont = text.getFont();
        text.setFont(new Font(labelFont.getName(), Font.BOLD, fontSizeToUse));
    }
}

