package eionet.helpadm.servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;

import eionet.rpcclient.*;

import eionet.helpadm.*;

/**
 * @author jaanus
 *
 */
public class Test {

    public static void main(String[] args) {
        
        try{
            String serviceName = "HelpService";
            String srvUrl = "http://localhost:8080/datadict/public/rpcrouter";
            ServiceClientIF client =
                    ServiceClients.getServiceClient(serviceName, srvUrl);
            client.setCredentials("test", "xxx");
            
            Vector params = new Vector();
            params.add("edit");
            params.add("abstract");
            Object value = client.getValue("getHtmls", params);
            System.out.println(value);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }
    }
}
