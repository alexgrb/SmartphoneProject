package Smartphone;

import java.io.*;

/**
 *
 * Classe ContactData qui contient les valeur d'un contact.
 * @author Piranavan Thambirajah & Alex Gharbi
 * Créé en mai 2019
 */

public class ContactData implements Serializable {

    private String nom;
    private String prenom;
    private String numTel;
    private String email;
    private String adresse;
    private String NPAloc;
    private String dateNaissance;
    private String pathImg;

    /**
     * Permet d'instancier la classe avec les valeur envoyée en parametre.
     * @param nom contenu du prenom
     * @param prenom contenu du prenom
     * @param numTel contenu du numéro de téléphone
     * @param email contenu de l'email
     * @param adresse contenu de l'adresse
     * @param NPAloc contenu du NPA
     * @param dateNaissance contenu de la date de naissance
     * @param pathImg contenu du nom du fichier .jpg de l'image
     */


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

    /**
     * Retourne le nom.
     *
     * @return Le nom correspondant, sous forme d'une chaine de
     *         caractères.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne le prenom.
     *
     * @return Le prenom correspondant, sous forme d'une chaine de
     *         caractères.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Retourne le numéro de téléphone.
     *
     * @return Le numéro de téléphone correspondant, sous forme d'une chaine de
     *         caractères.
     */
    public String getNumTel() {
        return numTel;
    }

    /**
     * Retourne l'email.
     *
     * @return L'email correspondant, sous forme d'une chaine de
     *         caractères.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Retourne l'adresse.
     *
     * @return L'adresse correspondant, sous forme d'une chaine de
     *         caractères.
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Retourne le NPA.
     *
     * @return Le NPA correspondant, sous forme d'une chaine de
     *         caractères.
     */
    public String getNPAloc() {
        return NPAloc;
    }

    /**
     * Retourne la date de naissance.
     *
     * @return la date de naissance correspondant, sous forme d'une chaine de
     *         caractères.
     */
    public String getDateNaissance() {
        return dateNaissance;
    }

    /**
     * Retourne le nom de l'image.
     *
     * @return le nom de l'image, sous forme d'une chaine de
     *         caractères.
     */
    public String getPathImg() {
        return pathImg;

    }
}
