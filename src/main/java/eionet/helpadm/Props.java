package eionet.helpadm;

import java.util.Properties;
import java.util.Hashtable;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

public class Props {

    private static final String ENV_VAR = "HELPADMIN_CONF";
    public static final String PROP_FILE = "helpadmin";
    public static final String APPS = "applications";
    public static final String ROUTER_URL = ".router.url";
    public static final String SUPERUSERS = "superusers";
    public static final String DBG = "dbg";

    private static Properties props = null;

    /**
     * Get String property. First tries system properties.
     * Then the property loaded from properties file.
     * @param name - Name of the property.
     * @return
     */
    public static synchronized String getProperty(String name) {
        if (props == null) {
            init();
        }

        String prop = null;
        prop = System.getProperty(name);
        if (prop != null) {
            return prop;
        }
        if (props != null) {
            prop = props.getProperty(name);
        }
        if (prop == null) {
            System.out.println("Default Property value for key " + name + " not found.");
        }
        return prop;
    }


    /**
     * Get int property. Throws an exception if Integer.parseInt() failed.
     * @param name - Name of the property.
     * @return
     * @throws Exception
     */
    public static synchronized int getIntProperty(String name) throws Exception {
        String sProp = getProperty(name);
        try {
            return Integer.parseInt(sProp);
        } catch (NumberFormatException nfe) {
            throw new Exception("Bad integer conversion of " + sProp);
        }
    }

    /**
     * Initializes the properties from file. These are fallback values.
     *
     * Called if the props variable is null.
     */
    private static synchronized void init() {
        props = new Properties();
        try {
            InputStream inStream = null;
            String filePath = System.getenv(ENV_VAR);
            if (filePath != null) {
                try {
                    inStream = new FileInputStream(filePath);
                } catch (Exception e) {
                    //throw new Exception("Properties file not found at " + filePath);
                    System.out.println("Properties file not found at " + filePath);
                    inStream = null;
                }
            } else {
                inStream = Props.class.getResourceAsStream("/" + PROP_FILE + ".properties");
                if (inStream == null) {
                    //throw new Exception("Properties file is not found in the classpath");
                    System.out.println("Properties file is not found in the classpath");
                    inStream = null;
                }
            }
            if (inStream != null) {
                props.load(inStream);
                inStream.close();
            }
        } catch (IOException mre) {
            //throw new Exception("Properties file is not readable");
            System.out.println("Properties file is not readable");
        }
    }
}
