package eionet.helpadm;

import java.util.*;

public class Props {

	public static final String PROP_FILE = "helpadmin";
	public static final String APPS = "applications";
	public static final String ROUTER_URL = ".router.url";
	public static final String SUPERUSERS = "superusers";
	public static final String DBG = "dbg";
	
	private static ResourceBundle props = null;
	private static Hashtable defaults = null;

	/**
	 * get String property 
	 * @param name
	 * @return
	 */
	public static synchronized String getProperty(String name){
		
		if (props==null)
			init();
		
		String prop = null;
		if (props!=null){
			try{
				prop = props.getString(name);
			}
			catch (MissingResourceException mre){}
		}
		
		if (prop==null){
			prop = (String)defaults.get(name);
			if (prop!=null)
				System.out.println("Property value for key " +
									name + " not found. Using default.");
		}
		
		return prop;
	}
	
	/**
	 * Get int property. Throws an exception if Integer.parseInt() failed.
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public static synchronized int getIntProperty(String name) throws Exception{
		String sProp = Props.getProperty(name);
		try{
			return Integer.parseInt(sProp);
		}
		catch (NumberFormatException nfe){
			String dflt = (String)defaults.get(name);
			if (dflt!=null){
				System.out.println("Invalid property value for key " +
									name + ". Using default.");
				return Integer.parseInt(dflt);
			}
			else
				throw new Exception("Invalid property value for key " + name);
		}
	}
	
	/**
	 * initializes the properties through ResourceBundle, sets the defaults too
	 */
	private static synchronized void init(){
		try {
			props = ResourceBundle.getBundle(PROP_FILE);
		} catch (MissingResourceException mre) {
			System.out.println("Properties file " + PROP_FILE +
								".properties not found. Using defaults.");
		}
		setDefaults();
	}

	/**
	 * sets the default properties
	 *
	 */	
	private static synchronized void setDefaults(){
		
		defaults = new Hashtable();
	}
	
	public static void main(String[] args){
		String s = Props.getProperty("testprop");
		System.out.println(s);
	}
}
