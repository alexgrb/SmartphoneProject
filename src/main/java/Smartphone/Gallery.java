package Smartphone;

import net.miginfocom.swing.MigLayout;
import tools.imageLabel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class Gallery extends JPanel {

    static JPanel imgzoomPanel = new Picture("");
    static JScrollPane finalPanel;
    public static JPanel imgPanel = new JPanel();

    private static int nbPhotos = (new File("src\\main\\java\\pictures\\gallery\\").list().length)/2; //Divided by two because there is always 2 version
    JScrollPane scroll = new JScrollPane();
    public static JButton jbAdd = new JButton ("");

    public Gallery (){
        jbAdd.setIcon(new ImageIcon("src\\main\\java\\pictures\\iconAdd.png"));
        jbAdd.setOpaque(false);
        jbAdd.setBackground(new Color(0,true));
        jbAdd.setBorder(null);
        loadImages();
    }

    class addButton implements ActionListener{

        public void actionPerformed(ActionEvent e) {

            File file = new File("C:\\Users\\piran\\IdeaProjects\\SmartphoneProject\\src\\main\\java\\picturesToAdd");
            JFileChooser chooser = new JFileChooser(file);

            int returnValue = chooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                System.out.println(selectedFile.getAbsolutePath());
                addImage(selectedFile);
            }
            revalidate();
            reloadNbPhotos();
            loadImages();
        }
    }

    public void addImage(File path){

        File dest = new File("src\\main\\java\\pictures\\gallery\\" + (nbPhotos+1)+ ".png");

        try {
            copyFileUsingStream(path.getPath(), dest.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private  void copyFileUsingStream(String source, String dest) throws IOException {
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
    }

     public void loadImages(){

         removeAll();
         imgPanel.removeAll();
         JLabel label[] = new JLabel[nbPhotos+1];
         setLayout(null);

         imgPanel.setLayout(new MigLayout("wrap 4"));
         for (int j = 1; j<(nbPhotos+1); j++){
            String path = String.valueOf(j);
            label[j] = new imageLabel(path,1);
            imgPanel.add(label[j]);
            label[j].addMouseListener(new mouseListener(path));
        }
         jbAdd.addActionListener(new addButton());
         jbAdd.setBounds(430,0,40,40);
         add(jbAdd);
         scroll.setViewportView(imgPanel);
         finalPanel = new JScrollPane(imgPanel);
         finalPanel.setBounds(0,40,480,700);
         add(finalPanel);

    }

    class mouseListener extends MouseAdapter {
        String path;

        public  mouseListener(String path) {
            this.path = path;
        }
        @Override
        public void mousePressed(MouseEvent me) {
            /*if(imgzoomPanel.isVisible()) {
                imgzoomPanel.setVisible(false);
            }
            else {

             */
            removeAll();
            setLayout(null);
            imgzoomPanel = new Picture(path);
            imgzoomPanel.setBounds(0,40,480,700);
            add(imgzoomPanel);
            revalidate();
            repaint();
            }
        }

    public static void reloadNbPhotos() {
        Gallery.nbPhotos = (new File("src\\main\\java\\pictures\\gallery\\").list().length);
        System.out.println("Actuellement "+ nbPhotos + " photos dans le dossier");
    }
}





