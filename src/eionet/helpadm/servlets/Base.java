package eionet.helpadm.servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;
import java.io.*;

import com.tee.uit.client.*;
import com.tee.uit.security.*;
import eionet.directory.*;
import eionet.helpadm.*;
import com.tee.util.Util;

public abstract class Base extends HttpServlet {
	
	private HashMap acls = null;

	/**
	 * logs into selected application
	 */
	public void appLogin(HttpServletRequest req) throws Exception{
		
		HttpSession session = req.getSession();
		appLogout(session);
		
		Hashtable apps = (Hashtable)session.getAttribute(Attrs.APPS);
		if (apps==null)
			throw new Exception("No applications in session, session might be expired!");
		
		String appName = req.getParameter(Params.APP_NAME);
		if (Util.nullString(appName)) throw new Exception("Application name missing!");
		
		Application app = (Application)apps.get(appName);
		if (app!=null){
			try{
				app.login(req.getParameter(Params.APP_USR),
						  req.getParameter(Params.APP_PSW));
			}
			catch (Exception e){
				throw new Exception("Application login error: " + e.toString());
			}
			
			session.setAttribute(Attrs.APP, app);
		}
		else
			throw new Exception("No application in session, session might be expired!"); 
	}

	/**
	 * removes the application under Attrs.APP in session
	 */
	public void appLogout(HttpSession session){
		session.removeAttribute(Attrs.APP);
	}
		
	/**
	 * 
	 */
	public void appReload(HttpServletRequest req) throws Exception{
		
		Application app = (Application)req.getSession().getAttribute(Attrs.APP);
		if (app==null)
			throw new Exception("Application is missing from session!");
		
		app.reload();
	}
	
	/**
	 * Initializes the applications hashtable
	 */
	protected void initApps(HttpSession session) throws Exception{
		
		String appsProp = Props.getProperty(Props.APPS);
		if (Util.nullString(appsProp))
			throw new Exception("Missing property: " + Props.APPS);
			
		Hashtable apps = new Hashtable();
		StringTokenizer st = new StringTokenizer(appsProp, ",");
		while (st.hasMoreTokens()) {
			String appName = st.nextToken().trim();
			String appUrl = Props.getProperty(appName + Props.ROUTER_URL);
			if (Util.nullString(appUrl))
				throw new Exception("Missing property: " + Props.APPS);
			
			Application app = new Application(appName, appUrl);
			apps.put(appName, app);
		}
		
		session.setAttribute(Attrs.APPS, apps);
	}
	
	/**
	 * Checks if the given username is allowed to work with Help Admin Tool
	 */
	protected boolean allowed(AppUser user){
		
		try {
			if (acls==null)
				acls = AccessController.getAcls();   

			AccessControlListIF hlpadmAcl = (AccessControlListIF)acls.get("/");
			if (hlpadmAcl == null) throw new Exception("Root acl was not found");
			return hlpadmAcl.checkPermission(user.getUserName(), "v");
		}
		catch (Exception e){
			return false;
		}
	}

	/**
	 * Handles the given exception by redirecting to the correct page
	 */
	protected void handleError(String action, HttpServletRequest req, HttpServletResponse res,
							   Exception e) throws ServletException,IOException {
		
		// if signon exception, remove application from session, so the user is not
		// directed to screens list again (getting the exception again and infinitely)
		if (e.toString().indexOf("com.tee.uit.security.SignOnException") != -1 )			
			req.getSession().removeAttribute(Attrs.APP);
		
		handleError(action, req, res, e.toString());
	}

	/**
	 * Handles the given error message by redirecting to the correct page
	 */
	protected void handleError(String action, HttpServletRequest req, HttpServletResponse res,
							   String errMsg) throws ServletException,IOException {
		
		req.setAttribute(Attrs.ERRMSG, errMsg);
		req.getSession().setAttribute(Attrs.ERRMSG, errMsg);
		
		if (action.equals(Actions.APPLOGIN))
			closeAppLogin(res, JSPs.ERROR);
		else			
			req.getRequestDispatcher(JSPs.ERROR).forward(req,res);
	}

	protected void closeAppLogin(HttpServletResponse res, String redirUrl) throws IOException{
		
		StringBuffer buf = new StringBuffer().
		append("<html><script>window.opener.location = '").
		append(redirUrl).append("'; ").append("window.close()</script></html>");
		
		try{
			PrintWriter out = res.getWriter();
			out.print(buf.toString());
			out.close();
		}
		catch (IOException ioe){
			System.out.println("closeAppLogin() failed: " + ioe.toString());
		}
	}
}