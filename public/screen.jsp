<%@page contentType="text/html;charset=UTF-8" import="java.util.*,eionet.helpadm.*,com.tee.uit.help.*"%>

<%
request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <%@ include file="headerinfo.txt" %>
    <title>Help Admin Tool</title>
</head>
<body>
    <jsp:include page="location.jsp" flush='true'>
        <jsp:param name="name" value="Screen"/>
    </jsp:include>
    <%@ include file="menu.jsp" %>
<div id="workarea">
    <%@ include file="operations.jsp" %>
    <h1>Screen</h1>

					<form name="screen" method="post" action="main" acceptcharset="UTF-8">
					<table width="600" cellspacing="0" cellpadding="3">
					
						<tr>
							<td>&#160;</td>
							<td>Create new screen</td>
						</tr>
						<tr height="10"><td colspan="2"></td></tr>						
						<tr>
							<td><b>ID:</b></td>
							<td><input type="text" size="20" name="<%=Params.SCREEN_ID%>"/></td>
						</tr>
						<tr>
							<td><b>Description:</b></td>
							<td>
								<textarea cols="40" rows="5" name="<%=Params.SCREEN_DESCR%>"></textarea>
							</td>
						</tr>
						<tr>
							<td>&#160;</td>
							<td><input type="submit" value="Create"/></td>
						</tr>
						
					</table>
					
					<input type="hidden" name="<%=Params.ACTION%>" value="<%=Actions.CREATE_SCREEN%>"/>
					
					</form>
				</div>
<%@ include file="footer.jsp" %>
</body>
</html>
