<%@ page import="eionet.helpadm.*" %>

<%
	Object o = request.getAttribute(Attrs.TEST);
	System.out.println("===> test.jsp");
	System.out.println(o);
%>

<html>
	<head></head>
	<body>
		<b><%=o%></b>
	</body>
</html>