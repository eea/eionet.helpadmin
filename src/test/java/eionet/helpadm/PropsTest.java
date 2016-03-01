package eionet.helpadm;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Søren Roug
 *
 */
public class PropsTest {

    @Test
    public void getApplicationsProperty() {
        assertEquals("DataDict", Props.getProperty(Props.APPS));
    }

    @Test
    public void getApplicationsSystemProperty() {
        String oldProp = System.setProperty(Props.APPS, "A,B,C");
        assertEquals("A,B,C", Props.getProperty(Props.APPS));
        if (oldProp == null) {
            System.clearProperty(Props.APPS);
        } else {
            System.setProperty(Props.APPS, oldProp);
        }
    }
}
