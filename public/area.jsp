<%@page contentType="text/html;charset=UTF-8" import="java.util.*,eionet.helpadm.*,com.tee.uit.help.*"%>

<%

request.setCharacterEncoding("UTF-8");

String screenID = request.getParameter(Params.SCREEN_ID);
if (screenID==null){
	request.setAttribute(Attrs.ERRMSG, "Parameter Params.SCREEN_ID is missing!");
	request.getRequestDispatcher(JSPs.ERROR).forward(request,response);
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <%@ include file="headerinfo.txt" %>
    <title>Help Admin Tool</title>
</head>
<body>
    <jsp:include page="location.jsp" flush='true'>
        <jsp:param name="name" value="Area"/>
    </jsp:include>
    <%@ include file="menu.jsp" %>
<div id="workarea">
    <%@ include file="operations.jsp" %>
    <h1>Area</h1>

					<form name="area" method="post" action="main">
					<table width="600" cellspacing="0" cellpadding="3">
					
						<tr>
							<td>&#160;</td>
							<td>Create new area</td>
						</tr>
						<tr height="10"><td colspan="2"></td></tr>						
						<tr>
							<td><b>ID:</b></td>
							<td><input type="text" size="20" name="<%=Params.AREA_ID%>"/></td>
						</tr>
						<tr>
							<td><b>Description:</b></td>
							<td>
								<textarea cols="40" rows="5" name="<%=Params.AREA_DESCR%>"></textarea>
							</td>
						</tr>
						<tr>
							<td>&#160;</td>
							<td><input type="submit" value="Create"/></td>
						</tr>
						
					</table>
					
					<input type="hidden" name="<%=Params.ACTION%>" value="<%=Actions.CREATE_AREA%>"/>
					<input type="hidden" name="<%=Params.SCREEN_ID%>" value="<%=screenID%>"/>
					
					</form>
				</div>
<%@ include file="footer.jsp" %>
</body>
</html>
