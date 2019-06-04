package Smartphone;

import net.miginfocom.swing.MigLayout;
import tools.imageButton;
import tools.imageLabel;
import tools.textResizer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

import static Smartphone.ViewContact.viewContact;
import static Smartphone.ViewContact.viewContactList;
import static Smartphone.display.picDirectory;

/**
         * C'est un JPanel qui va afficher toutes les images contenu dans le dossier picture.
         */

public class Gallery extends JPanel {

    static JPanel imgzoomPanel = new Picture("",null);
    static JScrollPane finalPanel;
    public static JPanel imgPanel = new JPanel();
    private Gallery gallery;
    static String galleryDirectory = picDirectory+"gallery\\";
    static final File dir = new File(galleryDirectory);
    private int i;
    private ViewContactList contact;

    private static int nbPhotos = (new File(galleryDirectory).list().length);
    JScrollPane scroll = new JScrollPane();
    public static JButton jbAdd = new JButton("");
    public static JLabel nbPhoto = new JLabel("");
    File[] images = dir.listFiles();

    /**
     *  Mets à jour le nombre de photos en se référant au dossier.
     */
    public void setImages() {
        this.images = dir.listFiles();;
    }

    /**
     * Constructeur général qui charge toutes les images.
     *
   //  * @see loadImages
     */
    public Gallery() {
        this.gallery = this;

        setBackground(new Color(255,255,255));
        jbAdd = new imageButton("iconAdd");
        loadImages();
    }

    /**
     *
     * @param i
     *          Index du contact afin qu'on puisse l'identifier
     * @param contact
     *          Object contact utilisé pour appeler le constructeur.
     *          Ainsi on peut revenir dessus après.
     *
     */
    public Gallery(int i, ViewContactList contact) {
        this.gallery = this;
        this.i=i;
        this.contact=contact;

        setLayout(new MigLayout("wrap 4"));
        System.out.println("Je suis dans la gallerie");
        setBackground(new Color(255,255,255));
        jbAdd = new imageButton("iconAdd");
        chooseImage();
    }


    public void setNbPhoto(int n) {
        this.nbPhoto.setText(n +" photos");
        nbPhoto.setBounds(20,0,100,40);
        new textResizer(nbPhoto);
        nbPhoto.setOpaque(false);
    }

    public void addImage(File path) {
        String finalPath = path.getPath().substring(74);
        System.out.println(finalPath);
        File dest = new File(galleryDirectory+finalPath) ;
        try {
            copyFileUsingStream(path.getPath(), dest.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        reloadNbPhotos();
    }

    private static void copyFileUsingStream(String source, String dest) throws IOException {
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
    public void chooseImage() {
        System.out.println("Jai réussi à choose Image.");
        removeAll();
        imgPanel.removeAll();
        JLabel[] label = new JLabel[nbPhotos + 1];

        imgPanel.setLayout(new MigLayout("wrap 3"));
        imgPanel.setBounds(0,40,480,400);
        imgPanel.setBackground(new Color(255,255,255));
        setImages();
        for (int j = 0; j < (images.length); j++) {
            String path = String.valueOf(images[j]).substring(31);

            label[j] = new imageLabel(path, 1);
            imgPanel.add(label[j]);
            label[j].addMouseListener(new chooseListener(path,i));
        }

        setNbPhoto(nbPhotos);

        add(nbPhoto, "span");
        jbAdd.addActionListener(new addButton());
        jbAdd.setBounds(430, 0, 40, 40);
        add(jbAdd);
        scroll.setViewportView(imgPanel);
        finalPanel = new JScrollPane(imgPanel);
        finalPanel.setBounds(0, 40, 480, 600);
        add(finalPanel);
        revalidate();
        repaint();
    }

    /**
     * Méthode qui enlève tout sur le JPanel.
     * Ensuite charge les photos du dossier pictures.
     * Le MigLayout est utilisé.
     */

    public void loadImages() {
        removeAll();
        imgPanel.removeAll();
        JLabel[] label = new JLabel[nbPhotos + 1];
        setLayout(null);
        imgPanel.setLayout(new MigLayout("wrap 4"));
        imgPanel.setBackground(new Color(255,255,255));
        setImages();
        for (int j = 0; j < (images.length); j++) {
            String path = String.valueOf(images[j]).substring(31);
            label[j] = new imageLabel(path, 1);
            imgPanel.add(label[j]);
            label[j].addMouseListener(new mouseListener(path));
        }

        setNbPhoto(nbPhotos);

        add(nbPhoto);
        jbAdd.addActionListener(new addButton());
        jbAdd.setBounds(430, 0, 40, 40);
        add(jbAdd);
        scroll.setViewportView(imgPanel);
        finalPanel = new JScrollPane(imgPanel);
        finalPanel.setBounds(0, 40, 480, 600);
        add(finalPanel);
        revalidate();
        repaint();
    }
    public void deletePhoto (String path) {

        String source = galleryDirectory + path;
        String dest = "src\\main\\java\\bin\\" + path;

        try {
            copyFileUsingStream(source, dest);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        File file = new File(source);
        File fileBis = new File(picDirectory+"big\\" + path);
        File fileMin = new File(picDirectory+"min\\" + path);

        file.delete();
        fileBis.delete();
        fileMin.delete();

        reloadNbPhotos();
        loadImages();
        revalidate();
        repaint();
    }
    public static void reloadNbPhotos() {
        Gallery.nbPhotos = (new File(galleryDirectory).list().length);
    }
    class addButton implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            File file = new File("src\\main\\java\\picturesToAdd");
            JFileChooser chooser = new JFileChooser(file);

            int returnValue = chooser.showOpenDialog(null);
            if (returnValue == JFileChooser.CANCEL_OPTION)
                return;
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                addImage(selectedFile);
            }
            revalidate();
            reloadNbPhotos();
            loadImages();
        }
    }

    class mouseListener extends MouseAdapter {
        String path;
        public mouseListener(String path) {
            this.path = path;
        }

        @Override
        public void mousePressed(MouseEvent me) {
            removeAll();
            setLayout(null);
            imgzoomPanel = new Picture(path, gallery);
            imgzoomPanel.setBounds(0, 40, 480, 700);
            add(imgzoomPanel);
            System.out.println("Dans le dernier Mouse Listener, path de la nouvelle image : " + path);
            revalidate();
            repaint();
        }
    }
    class chooseListener extends MouseAdapter {
        String path;
        public chooseListener(String path,int i) {
            this.path = path;
            //System.out.println("Dans le dernier Mouse Listener, path de la nouvelle image : " + path);
        }

        @Override
        public void mousePressed(MouseEvent me) {


            removeAll();
            viewContact.setVisible(false);
            viewContact.setPictureJT(path);
            viewContact.imageUpdate();
            viewContactList.setPreferredSize(new Dimension(480,800));
            viewContactList.show.add(viewContactList.scrollPane);
            viewContactList.show.setBorder(null);
            viewContactList.show.add(viewContactList.jbAdd, "top");
            viewContactList.show.repaint();
            viewContactList.show.revalidate();


        }
    }

}