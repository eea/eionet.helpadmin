<%@page import="java.util.*"%>

<div id="operations">
<ul>
	<%
    String back_button=request.getParameter("back");
    if (back_button!=null){
    	String back_url=(String)session.getAttribute("backUrl");
    	if (back_url!=null){
			String back = back_url.length()<1 ? "javascript:history.back(-1)":back_url;	
			%>
        	<li><a href="<%=back%>">&lt;back</a></li> <%
		}
	}
       			
    // up link
    /*String up = request.getParameter("up");
    if (up!=null){
    	String upUrl=(String)session.getAttribute("upUrl");
    	if (upUrl!=null){ %>
        	<li><a href="<%=upUrl%>"><img border="0" src="images/up.gif"/></a></li>
        <%
		}
	}*/
	%>
	
</ul>
</div>


<%-- for debugging, remove the two dashes from the <%-- below, put them back later when you're done --%>
<%--
Enumeration oNames;
String oName=null;
%>
<P><B><U>Init parameters (app):</U></B><%
oNames=application.getInitParameterNames();
oName=null;
while (oNames.hasMoreElements()) {
  oName=(String)oNames.nextElement();%>
  <LI><%=oName%> = <%=application.getInitParameter(oName)%></LI><%
  }%>
<P><B><U>Init parameters (sess):</U></B><%
oNames=config.getInitParameterNames();
oName=null;
while (oNames.hasMoreElements()) {
  oName=(String)oNames.nextElement();%>
  <LI><%=oName%> = <%=config.getInitParameter(oName)%></LI><%
  }%>
<P><U><B>Session attributes:</U></B><%
oNames=session.getAttributeNames();
oName=null;
while (oNames.hasMoreElements()) {
  oName=(String)oNames.nextElement();%>
  <LI><%=oName%> = <%=session.getAttribute(oName)%></LI><%
  }%>
<P><B><U>Request:</U></B>
<P><U>parameters:</U><%
oNames=request.getParameterNames();
oName=null;
while (oNames.hasMoreElements()) {
  oName=(String)oNames.nextElement();%>
  <LI><%=oName%> = <%=request.getParameter(oName)%></LI><%
  }%>
<P><U>attributes:</U><%
oNames=request.getAttributeNames();
oName=null;
while (oNames.hasMoreElements()) {
  oName=(String)oNames.nextElement();%>
  <LI><%=oName%> = <%=request.getAttribute(oName)%></LI><%
  }%>
<P><U>headers:</U><%
oNames=request.getHeaderNames();
oName=null;
while (oNames.hasMoreElements()) {
  oName=(String)oNames.nextElement();%>
  <LI><%=oName%> = <%=request.getHeader(oName)%></LI><%
  }
%>
<%-- --%>
