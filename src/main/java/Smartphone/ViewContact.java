package Smartphone;

import net.miginfocom.swing.MigLayout;
import tools.imageLabel;

import javax.swing.*;

import java.awt.*;

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
    private JLabel picture;
    protected Dimension dim = new Dimension(200, 30);
    protected Dimension dimSmall = new Dimension(100, 30);
    protected Dimension dimJlist = new Dimension(450, 700);

    public ViewContact(ContactData contact) {
        setLayout(new MigLayout("wrap 2"));
        this.contact = contact;
        setContactPanel();
        addToPanel();
       // setBackground(Color.blue);

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
            System.out.println(name.getText());
        }
    }

