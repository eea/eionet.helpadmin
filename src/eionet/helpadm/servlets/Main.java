
package eionet.helpadm.servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;
import java.io.*;

import eionet.helpadm.*;
import com.tee.uit.client.*;
import eionet.directory.DirectoryService;
import java.security.acl.NotOwnerException;
import java.lang.reflect.InvocationTargetException;
import com.tee.uit.security.SignOnException;
import com.tee.util.Util;

/**
 * 
 */
public class Main extends Base {
	
	public void service(HttpServletRequest req, HttpServletResponse res)
										throws ServletException, IOException {  

		String action = req.getParameter(Params.ACTION);
		if (Util.nullString(action)) action = Actions.DEFAULT;
		
		try{
			//guard(req, action);
			act(req, action);
		}
		catch (Exception e){
			handleError(action, req, res, e);
			return;
		}
		
		dispatch(action,req,res);
	}
	
	private void act(HttpServletRequest req, String action) throws Exception{

		if (action.equals(Actions.LIST_APPS))
			doApps(req);
		else if (action.equals(Actions.APPLOGIN))
			appLogin(req);
		else if (action.equals(Actions.APPLOGOUT))
			appLogout(req.getSession());
		else if (action.equals(Actions.APPRELOAD))
			appReload(req);
		else
			doTheData(req, action);
	}
	
	private void doApps(HttpServletRequest req) throws Exception{
		
		HttpSession session = req.getSession();
		if (session.getAttribute(Attrs.APPS)==null)
			initApps(session);
	}
	
	private void doTheData(HttpServletRequest req, String action) throws Exception{
		
		HttpSession session = req.getSession();
		Application app = (Application)session.getAttribute(Attrs.APP);
		if (app!=null)
			app.doTheData(req, action);
		else
			throw new Exception("No application in session, session might be expired!");
	}
  
    /**
    * redirect to the right JSP
    */
    private void dispatch(String action, HttpServletRequest req, HttpServletResponse res)
    								throws ServletException, IOException  {
		
		String fwd = JSPs.APPS;
		
		if (action.equals(Actions.APPLOGIN)){
			closeAppLogin(res, "main?" + Params.ACTION + "=" + Actions.LIST_SCREENS);
			return;
		}
		else if (action.equals(Actions.LIST_SCREENS))
			fwd = JSPs.SCREENS;
		else if (action.equals(Actions.LIST_AREAS))
			fwd = JSPs.AREAS;
		else if (action.equals(Actions.LIST_HTMLS))
			fwd = JSPs.HTMLS;
		else if (action.equals(Actions.CREATE_SCREEN))
			fwd = "main?" + Params.ACTION + "=" + Actions.LIST_SCREENS;
		else if (action.equals(Actions.CREATE_AREA))
			fwd = "main?" + Params.ACTION + "=" + Actions.LIST_AREAS;
		else if (action.equals(Actions.REMOVE_SCREENS))
			fwd = "main?" + Params.ACTION + "=" + Actions.LIST_SCREENS;
		else if (action.equals(Actions.REMOVE_AREAS))
			fwd = "main?" + Params.ACTION + "=" + Actions.LIST_AREAS;
		else if (action.equals(Actions.EDIT_HTML))
			fwd = "main?" + Params.ACTION + "=" + Actions.LIST_HTMLS;
		else if (action.equals(Actions.EDIT_AREA))
			fwd = "main?" + Params.ACTION + "=" + Actions.LIST_HTMLS;
		else if (action.equals(Actions.APPRELOAD))
			fwd = "main?" + Params.ACTION + "=" + Actions.LIST_SCREENS;
		else if (action.equals(Actions.TEST))
			fwd = JSPs.TEST;
		
		req.getRequestDispatcher(fwd).forward(req,res);      
    }
}