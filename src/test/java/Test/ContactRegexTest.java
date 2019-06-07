package Test;


import Smartphone.ContactRegex;
import org.junit.Test;

import static org.junit.Assert.*;


public class ContactRegexTest {


    @Test
    public void validerEmail() {
        ContactRegex reg = new ContactRegex();
        assertTrue(reg.validerEmail("vcaibis@bluewin.ch"));
        assertFalse(reg.validerEmail("vcaibisbluewin.ch"));
    }

    @Test
    public void validerNumTel() {
        ContactRegex reg = new ContactRegex();
        assertTrue(reg.validerNumTel("0799249044"));
        assertFalse(reg.validerNumTel("07992497044"));
    }

    @Test
    public void validerNPA() {
        ContactRegex reg = new ContactRegex();
        assertTrue(reg.validerNPA("9009"));
        assertFalse(reg.validerNPA("109"));

    }
}

