package Test;


import Smartphone.ContactRegex;
import org.junit.Before;
import org.junit.Test;
import tools.imageLabel;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class imageLabelTest {


    File f = new File(".png");

    @Test (expected = FileNotFoundException.class)
    public void loadImage() {
        imageLabel img = new imageLabel("lol");
        //img.loadImage(f.toString());

    }
}
