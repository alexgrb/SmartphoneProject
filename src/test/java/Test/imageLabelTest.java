package Test;

import org.junit.Test;
import tools.imageLabel;

import java.io.File;

/**
 * @author Piranavan Thambirajah & Alex Gharbi
 * Créé en juin 2019
 * La classe permet de tester si une exception est bien lancée.
 */

public class imageLabelTest {

    File f = new File(".png");

    @Test(expected = NullPointerException.class)
    public void loadImageTest() throws NullPointerException {

        imageLabel img = new imageLabel("10",1);

    }

}
