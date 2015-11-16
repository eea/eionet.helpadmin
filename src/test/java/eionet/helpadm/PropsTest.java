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
        assertEquals("DataDict", Props.getProperty("applications"));
    }

}
