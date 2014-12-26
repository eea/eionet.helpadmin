<%@page contentType="text/html;charset=UTF-8" import="java.util.*,eionet.helpadm.*,eionet.help.*"%>

<%
request.setCharacterEncoding("UTF-8");
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
        <jsp:param name="name" value="Screen"/>
    </jsp:include>
    <%@ include file="menu.jsp" %>
<div id="workarea">
    <%@ include file="operations.jsp" %>
    <h1>Screen</h1>

					<form id="screen" method="post" action="main" accept-charset="UTF-8">
					<table width="600" cellspacing="0" cellpadding="3">
					
						<tr>
							<td>&nbsp;</td>
							<td>Create new screen</td>
						</tr>
						<tr><td colspan="2">&nbsp;</td></tr>						
						<tr>
							<td><strong>ID:</strong></td>
							<td><input type="text" size="20" name="<%=Params.SCREEN_ID%>"/></td>
						</tr>
						<tr>
							<td><strong>Description:</strong></td>
							<td>
								<textarea cols="40" rows="5" name="<%=Params.SCREEN_DESCR%>"></textarea>
							</td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td><input type="submit" value="Create"/></td>
						</tr>
						
					</table>
					<fieldset style="display:none">
						<input type="hidden" name="<%=Params.ACTION%>" value="<%=Actions.CREATE_SCREEN%>"/>
					</fieldset>
					</form>
				</div> <!-- workarea -->
				</div> <!-- container -->
<%@ include file="footer.jsp" %>
</body>
</html>
