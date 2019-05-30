package Smartphone;

import java.io.*;


public class ContactData implements Serializable {

    private String nom;
    private String prenom;
    private String numTel;
    private String email;
    private String adresse;
    private String NPAloc;
    private String dateNaissance;
    private String pathImg;


    public ContactData(String nom, String prenom, String numTel, String email, String adresse, String NPAloc, String dateNaissance, String pathImg) {
        this.nom = nom;
        this.prenom = prenom;
        this.numTel = numTel;
        this.email = email;
        this.adresse = adresse;
        this.NPAloc = NPAloc;
        this.dateNaissance = dateNaissance;
        this.pathImg = pathImg;
    }

    //Getters
    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNumTel() {
        return numTel;
    }

    public String getEmail() {
        return email;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getNPAloc() {
        return NPAloc;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public String getPathImg() {
        return pathImg;
    }


    //Setters
    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setNPAloc(String NPAloc) {
        this.NPAloc = NPAloc;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }


}
