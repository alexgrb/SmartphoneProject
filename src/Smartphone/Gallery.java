package Smartphone;

import javafx.stage.FileChooser;
import tools.imageLabel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class Gallery extends JPanel {

    static JPanel imgzoomPanel = new Picture("");
    public static JPanel imgPanel = new JPanel();
    private static int nbPhotos = (new File("src\\pictures\\gallery\\").list().length);
    JScrollPane scroll = new JScrollPane();
    private static JButton jbRetour = new JButton ("Ajouter");
    private static JLabel label[] = new JLabel[nbPhotos+1];

    public Gallery (){

        System.out.println(nbPhotos);

        setLayout(new BorderLayout());
        imgPanel.setLayout(new GridLayout(0, 2));

        jbRetour.addActionListener(new addButton());
        add(jbRetour, BorderLayout.SOUTH);



        loadImages();

        scroll.setViewportView(imgPanel);
        add(new JScrollPane(imgPanel), BorderLayout.CENTER);
    }

    class addButton implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            File file = new File("C:\\Users\\Public");
            JFileChooser chooser = new JFileChooser(file);

            int returnValue = chooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                System.out.println(selectedFile.getAbsolutePath());
                addImage(selectedFile);
            }
        }
    }

    public void addImage(File path){

        File dest = new File("src\\pictures\\gallery\\" + (nbPhotos+1)+ ".png");
        try {
            copyFileUsingStream(path, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copyFileUsingStream(File source, File dest) throws IOException {
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(source);
            os = new FileOutputStream(dest);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
        loadImages();
    }

     public static void loadImages(){

        for (int j = 1; j<(nbPhotos+1); j++){ //rempalcer 6 par nbPhotos
            String path = String.valueOf(j);
            label[j] = new imageLabel(path,1);
            imgPanel.add(label[j]);
            //label[j].addMouseListener(new mouseListener());

            Border blackline = BorderFactory.createLineBorder(Color.black);
            label[j].setBorder(blackline);
        }

    }

    class mouseListener extends MouseAdapter {
        String path;
        public void mouseListener(String path) {
            this.path = path;
        }
        @Override
        public void mousePressed(MouseEvent me) {
            if(imgzoomPanel.isVisible()) {
                imgzoomPanel.setVisible(false);
            }
            else {
                imgzoomPanel = new Picture(path);
                add(imgzoomPanel);
                imgzoomPanel.setVisible(true);

            }
        }

        }
    }





