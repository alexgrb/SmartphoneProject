package Smartphone;

import net.miginfocom.swing.MigLayout;
import tools.imageButton;
import tools.imageLabel;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Smartphone.display.picDirectory;

public class ViewContact extends JPanel {

    private ContactData contact;
    private JLabel name = new JLabel();
    private JLabel firstName = new JLabel();
    private JLabel phoneNumber = new JLabel();
    private JLabel mail = new JLabel();
    private JLabel address = new JLabel();
    private JLabel NPA = new JLabel();
    private JLabel dateOfBirth = new JLabel();
    private JLabel picture = new JLabel();
    protected Dimension dim = new Dimension(200, 30);
    protected Dimension dimSmall = new Dimension(100, 30);
    protected Dimension dimJlist = new Dimension(450, 700);
    private ViewContactList viewContactList;
    private JButton backButton = new JButton();



    public ViewContact(ContactData contact, ViewContactList viewContactList) {
        setLayout(new MigLayout("wrap 2"));
        this.contact = contact;
        this.viewContactList = viewContactList;

        backButton.setIcon(new ImageIcon(picDirectory+"iconBack.png"));
        backButton.addActionListener(new backListener());
        setContactPanel();
        setLabelsSize();
        addToPanel();
    }

    public ViewContact(ViewContactList viewContactList) {
        this.viewContactList = viewContactList;
        viewContactList.jlistContact.getModel().getElementAt(0);
        backButton.setIcon(new ImageIcon(picDirectory+"iconBack.png"));
        backButton.addActionListener(new backListener());
        resetChamp();
      //  setLabelsSize();
        addToPanel();
    }

    public void setContactPanel() {
        name.setText(this.contact.getNom());
        firstName.setText(this.contact.getPrenom());
        phoneNumber.setText(this.contact.getNumTel());
        mail.setText(this.contact.getEmail());
        address.setText(this.contact.getAdresse());
        NPA.setText(this.contact.getNPAloc());
        dateOfBirth.setText(this.contact.getDateNaissance());
        picture = new imageLabel(this.contact.getPathImg());
    }


    public void setLabelsSize(){
        name.setPreferredSize(dimSmall);
        firstName.setPreferredSize(dimSmall);
        phoneNumber.setPreferredSize(dim);
        mail.setPreferredSize(dim);
        address.setPreferredSize(dim);
        NPA.setPreferredSize(dimSmall);
        dateOfBirth.setPreferredSize(dimSmall);
        picture.setPreferredSize(dimSmall);
    }

        public void addToPanel(){
            add(name);
            add(firstName);
            add(phoneNumber);
            add(mail);
            add(address);
            add(NPA);
            add(dateOfBirth);
            add(picture);
            add(backButton);
            System.out.println(name.getText());
        }

    class backListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
                removeAll();
                viewContactList.updateList();
                add(viewContactList.scrollPane);
                add(viewContactList.jbAdd);
                viewContactList.jlistContact.setVisible(true);
        }
    }
    /**
     * Méthode qui va simplement reset les champs JTextField null afin de pouvoir inscrire des nouveaux contact
     */

    public void resetChamp(){
        name.setText(null);
        firstName.setText(null);
        phoneNumber.setText(null);
        mail.setText(null);
        address.setText(null);
        NPA.setText(null);
        dateOfBirth.setText(null);
    }
}

