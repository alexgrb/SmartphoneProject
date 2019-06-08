package Test;


import com.sun.org.apache.xerces.internal.util.PropertyState;
import org.junit.Test;
import tools.imageLabel;

import java.io.File;

public class imageLabelTest {

    File f = new File(".png");

    @Test(expected = NullPointerException.class)
    public void loadImageTest() throws NullPointerException {

        imageLabel img = new imageLabel("10",1);

    }

}
