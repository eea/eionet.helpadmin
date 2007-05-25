<%@page contentType="text/html;charset=UTF-8" import="eionet.helpadm.*"%>

<%

request.setCharacterEncoding("UTF-8");

String errMsg = (String)request.getAttribute(Attrs.ERRMSG);
if (errMsg==null) errMsg = (String)session.getAttribute(Attrs.ERRMSG);
if (errMsg==null) errMsg = "Was error, but found no error message!";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <%@ include file="headerinfo.txt" %>
    <title>Help Admin Tool</title>
</head>
<body>
<div id="container">	
    <jsp:include page="location.jsp" flush='true'>
        <jsp:param name="name" value="Error"/>
    </jsp:include>
    <%@ include file="menu.jsp" %>
<div id="workarea">
    <%@ include file="operations.jsp" %>

                    <br/><br/><b><%=errMsg%></b>
</div> <!-- workarea -->
</div> <!-- container -->
<%@ include file="footer.jsp" %>
</body>
</html>
