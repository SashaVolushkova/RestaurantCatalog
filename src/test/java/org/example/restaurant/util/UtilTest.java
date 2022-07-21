package org.example.restaurant.util;

import com.google.i18n.phonenumbers.NumberParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilTest {

    @Test
    void reformatRuTelephoneRemoveWhiteSpaces() throws NumberParseException {
        String removeWhiteSpaces = Util.reformatRuTelephone("+7(999)222-33-11");
        assertEquals("+79992223311", removeWhiteSpaces);
        removeWhiteSpaces = Util.reformatRuTelephone("+7-999-222-33-11");
        assertEquals("+79992223311", removeWhiteSpaces);
        removeWhiteSpaces = Util.reformatRuTelephone("+7(999)222-33-11");
        assertEquals("+79992223311", removeWhiteSpaces);
        removeWhiteSpaces = Util.reformatRuTelephone("+7(999)    222 - 33  -  11");
        assertEquals("+79992223311", removeWhiteSpaces);
        removeWhiteSpaces = Util.reformatRuTelephone("+78008888888");
        assertEquals("+78008888888", removeWhiteSpaces);
    }

    @Test
    void reformatRuTelephoneRemoveNotRu() {
        Assertions.assertThrows(Exception.class, () -> Util.reformatRuTelephone("+9(999)222-33-11"));
        Assertions.assertThrows(Exception.class, () -> Util.reformatRuTelephone("+7(899)222-33-11"));
    }

    @Test
    void reformatRuTelephoneRemoveNotTelephone() {
        Assertions.assertThrows(Exception.class, () -> Util.reformatRuTelephone("+cdvbgfcdxdsaxd"));
        Assertions.assertThrows(Exception.class, () -> Util.reformatRuTelephone("+7(ddddd899)222-33-11"));
        Assertions.assertThrows(Exception.class, () -> Util.reformatRuTelephone("+7000000899)2-0-----0999922-33-11"));
        Assertions.assertThrows(Exception.class, () -> Util.reformatRuTelephone("+70000008900000000000000000000"));
    }
}