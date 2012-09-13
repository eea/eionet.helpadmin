
package eionet.helpadm.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import eionet.helpadm.Actions;
import eionet.helpadm.Application;
import eionet.helpadm.Attrs;
import eionet.helpadm.JSPs;
import eionet.helpadm.Params;
import eionet.helpadm.util.Util;

/**
 *
 */
public class Main extends Base {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String action = req.getParameter(Params.ACTION);
        if (Util.nullString(action)) {
            action = Actions.DEFAULT;
        }

        try{
            //guard(req, action);
            act(req, action);

        }
        catch (Throwable t){
            t.printStackTrace();
            handleError(action, req, res, t);
            return;
        }

        dispatch(action,req,res);
    }

    private void act(HttpServletRequest req, String action) throws Exception{

        if (action.equals(Actions.LIST_APPS)) {
            doApps(req);
        } else if (action.equals(Actions.APPLOGIN)) {
            appLogin(req);
        } else if (action.equals(Actions.APPLOGOUT)) {
            appLogout(req.getSession());
        } else if (action.equals(Actions.APPRELOAD)) {
            appReload(req);
        } else {
            doTheData(req, action);
        }
    }

    private void doApps(HttpServletRequest req) throws Exception{

        HttpSession session = req.getSession();
        if (session.getAttribute(Attrs.APPS)==null) {
            initApps(session);
        }
    }

    private void doTheData(HttpServletRequest req, String action) throws Exception{

        HttpSession session = req.getSession();
        Application app = (Application)session.getAttribute(Attrs.APP);
        if (app!=null) {
            app.doTheData(req, action);
        } else {
            throw new Exception("No application in session, session might be expired!");
        }
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
        else if (action.equals(Actions.LIST_SCREENS)) {
            fwd = JSPs.SCREENS;
        } else if (action.equals(Actions.LIST_AREAS)) {
            fwd = JSPs.AREAS;
        } else if (action.equals(Actions.LIST_HTMLS)) {
            fwd = JSPs.HTMLS;
        } else if (action.equals(Actions.CREATE_SCREEN)) {
            fwd = "main?" + Params.ACTION + "=" + Actions.LIST_SCREENS;
        } else if (action.equals(Actions.CREATE_AREA)) {
            fwd = "main?" + Params.ACTION + "=" + Actions.LIST_AREAS;
        } else if (action.equals(Actions.REMOVE_SCREENS)) {
            fwd = "main?" + Params.ACTION + "=" + Actions.LIST_SCREENS;
        } else if (action.equals(Actions.REMOVE_AREAS)) {
            fwd = "main?" + Params.ACTION + "=" + Actions.LIST_AREAS;
        } else if (action.equals(Actions.EDIT_HTML)) {
            fwd = "main?" + Params.ACTION + "=" + Actions.LIST_HTMLS;
        } else if (action.equals(Actions.EDIT_AREA)) {
            fwd = "main?" + Params.ACTION + "=" + Actions.LIST_HTMLS;
        } else if (action.equals(Actions.APPRELOAD)) {
            fwd = "main?" + Params.ACTION + "=" + Actions.LIST_SCREENS;
        } else if (action.equals(Actions.TEST)) {
            fwd = JSPs.TEST;
        }

        req.getRequestDispatcher(fwd).forward(req,res);
    }
}