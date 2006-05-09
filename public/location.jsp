<%@page import="java.util.*"%>
<div id="pagehead">
 <div id="identification">
  <a href="/"><img src="images/logo.png" alt="Logo" id="logo" border="0" /></a>
  <div class="sitetitle">Help Admin Tool (HAT)</div>
  <div class="sitetagline">Administration of help texts of other sites</div>
 </div>
<div class="breadcrumbtrail">
 <div class="breadcrumbhead">You are here:</div>
 <div class="breadcrumbitem"><a href="http://www.eionet.europa.eu">EIONET</a></div>

<%
   String oHName=request.getParameter("name");
   if (oHName==null) {  %>
 <div class="breadcrumbitemlast">Help Admin Tool</div>
<% } %>
<%  if (oHName!=null) { %>
 <div class="breadcrumbitem"><a href='main'>Help Admin Tool</a></div>
 <div class="breadcrumbitemlast"><%=oHName%></div>
<% } %>
 <div class="breadcrumbtail"></div>
</div>
</div> <!-- pagehead -->

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
