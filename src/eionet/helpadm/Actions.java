
package eionet.helpadm;

public interface Actions {
	
	public static final String APPLOGIN  = "applogin";
	public static final String APPLOGOUT = "applogout";
	public static final String APPRELOAD = "appreload";
	
	public static final String LIST_APPS = "list_apps";
	public static final String LIST_SCREENS = "list_screens";
	public static final String LIST_AREAS = "list_areas";
	public static final String LIST_HTMLS = "list_htmls";
	
	public static final String CREATE_SCREEN = "create_screen";
	public static final String EDIT_SCREEN = "edit_screen";
	public static final String REMOVE_SCREENS = "remove_screens";
	
	public static final String CREATE_AREA = "create_area";
	public static final String EDIT_AREA = "edit_area";
	public static final String REMOVE_AREAS = "remove_areas";
	
	public static final String EDIT_HTML = "edit_html";
	
	public static final String TEST = "test";
	public static final String DEFAULT = LIST_APPS;
}
