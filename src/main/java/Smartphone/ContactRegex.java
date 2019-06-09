package Smartphone;


import java.util.regex.*;


/**
 * Classe qui permet de vérifier les champs dans la classe Contact
 * @author Piranavan Thambirajah & Alex Gharbi
 * Creé mai 2019
 */

public class ContactRegex {

    public ContactRegex() {
    }

    /**
     * Méthode qui permet de vérfier si l'adresse e-mail est bel et bien correcte. Via les patterns regex.
     * @param mail contenu de l'email
     * @return boolean true ou false
     */

    public boolean validerEmail (String mail){

        Pattern pattern =  Pattern.compile( "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher matcher = pattern.matcher(mail.toUpperCase());
        return matcher.matches();
    }

    /**
     * Méthode qui permet de vérfier si l'adresse e-mail est bel et bien correcte. Via les patterns regex.
     * @param tel contenu du numéro de téléphone
     * @return boolean true ou false
     */
    //Méthode qui contrôle le numéro de tel. Contient-il bien 10 caractères? (ex : 0799249044)

    public boolean validerNumTel (String tel) {

        Pattern pattern = Pattern.compile("0([0-9]{9})");
        Matcher matcher = pattern.matcher(tel);
        return matcher.matches();
    }

    /**
     * Méthode qui permet de vérfier si l'adresse e-mail est bel et bien correcte. Via les patterns regex.
     * @param npa contenu du NPA
     * @return boolean true ou false
     */

    //Méthode qui contrôle le numéro de NPA. Contient-il bien 4 caractères? (ex : 1007)

    public boolean validerNPA (String npa) {

        Pattern pattern = Pattern.compile("[1-9]([0-9]{3})");
        Matcher matcher = pattern.matcher(npa);
        return matcher.matches();
    }
}
