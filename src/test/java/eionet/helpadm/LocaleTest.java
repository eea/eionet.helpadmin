package eionet.helpadm;

import static junit.framework.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Søren Roug
 *
 */
public class LocaleTest {


    @Test
    public void javac_utf8() {
        // Verify that javac can handle "Elektra" in the Greek alphabet
        char elektra[] = {
                '\u0397', '\u03bb', '\u03ad', '\u03ba', '\u03c4', '\u03c1', '\u03b1'
        };
        String Elektra = new String(elektra);
        assertEquals("Dude, set your locale to UTF-8", "Ηλέκτρα", Elektra);
    }

}
