<%@ page import="eionet.helpadm.*"%>

<%
String appName = request.getParameter(Params.APP_NAME);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html>
<head>
    <%@ include file="headerinfo.txt" %>
    <title>User Authentication</title>
</head>

<body class="popup" onload="document.forms['LOGIN'].elements['<%=Params.APP_USR%>'].focus();">

<form id="LOGIN" method="post" action="main">
	<fieldset style="display:none">
		<input type="hidden" name="<%=Params.ACTION%>" value="<%=Actions.APPLOGIN%>"/>
		<input type="hidden" name="<%=Params.APP_NAME%>" value="<%=appName%>"/>
	</fieldset>
		
	<table width="100%" cellspacing="0" cellpadding="1">
	
		<tr>
			<td>&nbsp;</td>
			<td>Log into <strong><%=appName%></strong>:</td>
		</tr>		
		<tr>
			<td colspan="2">&nbsp;</td>
		</tr>
		<tr>
			<td align="right"><strong>Username:</strong>&nbsp;</td>
	        <td>
	        	<input size="25" type="text" class="smalltext" name="<%=Params.APP_USR%>"/>
	        </td>
		</tr>
		<tr>
			<td align="right"><strong>Password:</strong>&nbsp;</td>
	        <td>
	        	<input size="25" type="password" class="smalltext" name="<%=Params.APP_PSW%>" onkeydown="if (event.keyCode==13){document.forms['LOGIN'].submit();}"/>
	        </td>
		</tr>
		<tr>
			<td colspan="2">&nbsp;</td>
		</tr>
		<tr>
			<td>&nbsp;</td>
			<td>
				<input type="submit" class="mediumbuttonb" value="Login" onkeypress="document.forms['LOGIN'].submit();"/>&nbsp;<input type="reset" class="mediumbuttonb" value="Clear fields" />
			</td>
		</tr>
		
	</table>
</form>
</body>
</html>
