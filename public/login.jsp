<%@ page import="eionet.helpadm.*"%>

<%

String appName = request.getParameter(Params.APP_NAME);
%>

<html>
<head>
<title>User Authentication</title>
<link href="eionet.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript">

	function setFocus(){
		
		if (document.all){
			document.all('<%=Params.APP_USR%>').focus();	
		}
		else{
			document.forms["LOGIN"].elements["<%=Params.APP_USR%>"].focus();
		}
	}

	function resetForm(){
		if (document.all){
			document.all('LOGIN').reset();
		}
		else{
			document.forms["LOGIN"].reset();
		}
	}

	function submitForm(){
		if (document.all){
			document.all('LOGIN').submit();
		}
		else{
			document.forms["LOGIN"].submit();
		}
	}

	function keyDown(){
		if (event.keyCode == 13){
			submitForm();
		}
	}
	
</script>
</head>

<body class="popup" onload="setFocus()">

<form name="LOGIN" method="post" action="main">

<input type="hidden" name="<%=Params.ACTION%>" value="<%=Actions.APPLOGIN%>"/>
<input type="hidden" name="<%=Params.APP_NAME%>" value="<%=appName%>"/>

<table width="100%" cellspacing="0" cellpadding="0">

	<tr>
		<td colspan="2">Log into <b><%=appName%></b>:</td>
	</tr>
	
	<tr height="5"><td colspan="2"></td></tr>
	
	<tr>
		<th align="right">Username:</th>
		<script language="JavaScript">
			if (navigator.appName.substring(0,5) == "Netsc")
				document.write("<th align='left' style='padding-left:10'><input size='14' type='text' class='smalltext' name='<%=Params.APP_USR%>'/></th>");
			else
				document.write("<th align='left' style='padding-left:10'><input size='25' type='text' class='smalltext' name='<%=Params.APP_USR%>'/></th>");
		</script>
	</tr>
	<tr>
		<th align="right">Password:</th>
		<script language="JavaScript">
			if (navigator.appName.substring(0,5) == "Netsc")
				document.write("<th align='left' style='padding-left:10'><input size='14' type='password' class='smalltext' name='<%=Params.APP_PSW%>' onkeydown='javascript:keyDown()'/></th>");
			else
				document.write("<th align='left' style='padding-left:10'><input size='25' type='password' class='smalltext' name='<%=Params.APP_PSW%>' onkeydown='javascript:keyDown()'/></th>");
		</script>
	</tr>
	<tr height="10"><td colspan="2"></td></tr>
	<tr><td align="right" colspan="2">
		<input name="SUBMIT" type="button" class="mediumbuttonb" value="Login" onclick="submitForm()" onkeypress="submitForm()"></input></td>
	</tr>
	<tr height="5"><td colspan="2"></td></tr>
	<tr><td align="right" colspan="2"><input name="RESET" type="button" class="mediumbuttonb" value="Clear Fields" onclick="resetForm()"></input></td></tr>
</table>
</form>
</body>
</html>
