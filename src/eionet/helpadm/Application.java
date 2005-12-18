package eionet.helpadm;

import java.util.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.*;

import javax.servlet.http.*;
import com.tee.uit.client.*;
import com.tee.util.Util;

public class Application {
	
	public static final String RPC_SERVICE_NAME = "HelpService";
	
	private String name = null;
	private String url = null;
	private String host = null;
	
	private String usr = null;
	private String psw = null;
	
	private ServiceClientIF client = null;
	
	/**
	 * 
	 */
	public Application(String name, String url){
		this.name = name;
		this.url  = url;
		extractHost();
	}
	
	/**
	 * 
	 */
	public String getHost(){
		return host;
	}

	/**
	 * 
	 */
	public String getName(){
		return name;
	}

	/**
	 * 
	 */
	public String getUrl(){
		return url;
	}
	
	/**
	 * 
	 */
	public void login(String usr, String psw) throws Exception {
		this.usr = usr;
		this.psw = psw;
		client = null;
		loadClient();
	}
	
	/**
	 * 
	 */
	private void loadClient() throws Exception {
		
		client = ServiceClients.getServiceClient(RPC_SERVICE_NAME, url);
		if (!Util.nullString(usr) && !Util.nullString(psw))
			client.setCredentials(usr,psw);
	}
	
	/**
	 * 
	 */
	public void reload() throws Exception {
		
		if (client==null) loadClient();		
		Vector params = new Vector();
		client.getValue("reset", params);
	}
	
	/**
	 * 
	 */
	private void listScreens(HttpServletRequest req) throws Exception{
		
		if (client==null) loadClient();
		
		Vector params = new Vector();
		Object o = client.getValue("getScreens", params);
		req.setAttribute(Attrs.SCREENS, o);
	}

	/**
	 * 
	 */
	private void listAreas(HttpServletRequest req) throws Exception{
		
		String screenID = req.getParameter(Params.SCREEN_ID);
		if (screenID==null) throwMissingParam(Params.SCREEN_ID);
		
		if (client==null) loadClient();
		
		Vector params = new Vector();
		params.add(screenID);
		req.setAttribute(Attrs.AREAS, client.getValue("getAreas", params));
	}

	/**
	 * 
	 */
	private void listHtmls(HttpServletRequest req) throws Exception{
		
		String screenID = req.getParameter(Params.SCREEN_ID);
		if (screenID==null) throwMissingParam(Params.SCREEN_ID);
		
		String areaID = req.getParameter(Params.AREA_ID);
		if (areaID==null) throwMissingParam(Params.AREA_ID);
		
		if (client==null) loadClient();
		
		Vector params = new Vector();
		params.add(screenID);
		params.add(areaID);
		
		req.setAttribute(Attrs.HTMLS, client.getValue("getHtmls", params));
	}
	
	/**
	 * 
	 */
	private void testMe(HttpServletRequest req) throws Exception{
		if (client==null) loadClient();
		
		Vector params = new Vector();
		params.add("test_screen");
		params.add("test_area");
		Object value = client.getValue("getHtmls", params);

		req.setAttribute(Attrs.TEST, value);
	}
	
	/**
	 * 
	 */
	private void createScreen(HttpServletRequest req) throws Exception{
		
		String id = req.getParameter(Params.SCREEN_ID);
		if (Util.nullString(id)) throwMissingParam(Params.SCREEN_ID);
		String descr = req.getParameter(Params.SCREEN_DESCR);
		if (descr==null) descr = "";
		
		if (client==null) loadClient();
		
		Vector params = new Vector();
		params.add(id);
		params.add(descr.trim());
		client.getValue("createScreen", params);
	}

	/**
	 * 
	 */
	private void createArea(HttpServletRequest req) throws Exception{

		String screenID = req.getParameter(Params.SCREEN_ID);
		if (Util.nullString(screenID)) throwMissingParam(Params.SCREEN_ID);
		String areaID = req.getParameter(Params.AREA_ID);
		if (Util.nullString(areaID)) throwMissingParam(Params.AREA_ID);		
		String descr = req.getParameter(Params.AREA_DESCR);
		if (descr==null) descr = "";
		
		if (client==null) loadClient();
		
		Vector params = new Vector();
		params.add(screenID);
		params.add(areaID);
		params.add(descr.trim());
		client.getValue("createArea", params);
	}

	/**
	 * 
	 */
	private void removeScreens(HttpServletRequest req) throws Exception{
		
		String[] _rmvids = req.getParameterValues(Params.RMV_ID);
		if (_rmvids==null || _rmvids.length==0) throwMissingParam(Params.RMV_ID);

		if (client==null) loadClient();

		Vector rmvids = new Vector();
		for (int i=0; i<_rmvids.length; i++)
			rmvids.add(_rmvids[i]);
			
		Vector params = new Vector();
		params.add(rmvids);
		client.getValue("removeScreens", params);
	}

	/**
	 * 
	 */
	private void removeAreas(HttpServletRequest req) throws Exception{
		
		String screenID = req.getParameter(Params.SCREEN_ID);
		if (Util.nullString(screenID)) throwMissingParam(Params.SCREEN_ID);
		
		String[] _rmvids = req.getParameterValues(Params.RMV_ID);
		if (_rmvids==null || _rmvids.length==0) throwMissingParam(Params.RMV_ID);

		if (client==null) loadClient();

		Vector rmvids = new Vector();
		for (int i=0; i<_rmvids.length; i++)
			rmvids.add(_rmvids[i]);
			
		Vector params = new Vector();
		params.add(screenID);
		params.add(rmvids);
		client.getValue("removeAreas", params);
	}

	/**
	 * 
	 */
	private void editHtml(HttpServletRequest req) throws Exception{
		
		String screenID = req.getParameter(Params.SCREEN_ID);
		if (Util.nullString(screenID)) throwMissingParam(Params.SCREEN_ID);
		String areaID = req.getParameter(Params.AREA_ID);
		if (Util.nullString(areaID)) throwMissingParam(Params.AREA_ID);		
		String htmlText = req.getParameter(Params.HTML_TEXT);
		if (htmlText==null) htmlText = "";
		
		if (client==null) loadClient();

		Vector params = new Vector();
		params.add(screenID);
		params.add(areaID);
		params.add(htmlText.trim());
		client.getValue("editHtml", params);
	}

	/**
	 * 
	 */
	private void editArea(HttpServletRequest req) throws Exception{
		
		String screenID = req.getParameter(Params.SCREEN_ID);
		if (Util.nullString(screenID)) throwMissingParam(Params.SCREEN_ID);
		
		String areaID = req.getParameter(Params.AREA_ID);
		if (Util.nullString(areaID)) throwMissingParam(Params.AREA_ID);
				
		String htmlText = req.getParameter(Params.HTML_TEXT);
		if (htmlText==null) htmlText = "";
		
		String popupWidth = req.getParameter(Params.POPUP_WIDTH);
		if (popupWidth==null) popupWidth = "";
		
		String popupLength = req.getParameter(Params.POPUP_LENGTH);
		if (popupLength==null) popupLength = "";
		
		if (client==null) loadClient();

		Vector params = new Vector();
		params.add(screenID);
		params.add(areaID);
		params.add(htmlText.trim());
		params.add(popupWidth.trim());
		params.add(popupLength.trim());
		client.getValue("editArea", params);
	}

	/**
	 * 
	 */	
	public void doTheData(HttpServletRequest req, String action) throws Exception{
		
		if (client==null) loadClient();
		
		if (action.equals(Actions.LIST_SCREENS))
			listScreens(req);
		else if (action.equals(Actions.LIST_AREAS))
			listAreas(req);
		else if (action.equals(Actions.LIST_HTMLS))
			listHtmls(req);
		else if (action.equals(Actions.CREATE_SCREEN))
			createScreen(req);
		else if (action.equals(Actions.CREATE_AREA))
			createArea(req);
		else if (action.equals(Actions.REMOVE_SCREENS))
			removeScreens(req);
		else if (action.equals(Actions.REMOVE_AREAS))
			removeAreas(req);
		else if (action.equals(Actions.EDIT_HTML))
			editHtml(req);
		else if (action.equals(Actions.EDIT_AREA))
			editArea(req);
		else if (action.equals(Actions.TEST))
			testMe(req);
	}
	
	/**
	 * 
	 */
	private void extractHost(){
		try{
			URL _url = new URL(url);
			host = _url.getHost();
		}
		catch (MalformedURLException e){
			host = "Malformed url: " + url;
		}		
	}
	
	/**
	 * 
	 */
	private void throwMissingParam(String paramName) throws Exception{
		throw new Exception("Paramater " + paramName + " is missing!");
	}
}
