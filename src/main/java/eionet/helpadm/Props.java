package eionet.helpadm;

import java.util.Properties;
import java.util.Hashtable;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
//import java.util.*;

public class Props {

    private static final String ENV_VAR = "HELPADMIN_CONF";
    public static final String PROP_FILE = "helpadmin";
    public static final String APPS = "applications";
    public static final String ROUTER_URL = ".router.url";
    public static final String SUPERUSERS = "superusers";
    public static final String DBG = "dbg";

    private static Properties props = null;
    private static Hashtable defaults = null;

    /**
     * get String property
     * @param name
     * @return
     */
    public static synchronized String getProperty(String name) {

        if (props == null)
            init();

        String prop = null;
        if (props != null) {
            prop = props.getProperty(name);
        }

        if (prop == null) {
            prop = (String)defaults.get(name);
            if (prop != null)
                System.out.println("Property value for key " + name + " not found. Using default.");
        }
        return prop;
    }

    /**
     * Get int property. Throws an exception if Integer.parseInt() failed.
     * @param name
     * @return
     * @throws Exception
     */
    public static synchronized int getIntProperty(String name) throws Exception {
        String sProp = Props.getProperty(name);
        try {
            return Integer.parseInt(sProp);
        } catch (NumberFormatException nfe) {
            String dflt = (String)defaults.get(name);
            if (dflt != null) {
                System.out.println("Invalid property value for key " + name + ". Using default.");
                return Integer.parseInt(dflt);
            } else {
                throw new Exception("Invalid property value for key " + name);
            }
        }
    }

    /**
     * Initializes the properties from file. Called if the props variable is null.
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
        setDefaults();
    }

    /**
     * sets the default properties
     *
     */
    private static synchronized void setDefaults() {
        defaults = new Hashtable();
    }
}
