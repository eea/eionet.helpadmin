<%@page contentType="text/html;charset=UTF-8" import="java.util.*,eionet.helpadm.*,com.tee.uit.security.*"%>

<%

request.setCharacterEncoding("UTF-8");

Hashtable appsHash = (Hashtable)session.getAttribute(Attrs.APPS);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <title>Help Admin Tool: Applications</title>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
    <link rel="stylesheet" type="text/css" href="layout-print.css" media="print" />
    <link rel="stylesheet" type="text/css" href="layout-handheld.css" media="handheld" />
    <link rel="stylesheet" type="text/css" href="layout-screen.css" media="screen" title="EIONET style" />
    <script type="text/javascript">
    
    	function login(appName) {
			var url = "login.jsp";
			if (appName!=null && appName.length>0){
				url = url + "?app=" + appName;
			}
			window.open(url,"login","height=200,width=430,status=no,toolbar=no,scrollbars=no,resizable=no,menubar=no,location=no");
		}
		
		function logout(){
			document.forms["apps"].elements["<%=Params.ACTION%>"].value = "<%=Actions.APPLOGOUT%>";
			document.forms["apps"].submit();
		}
		
		function gotoScreens(){
			document.forms["apps"].submit();
		}
		
    </script>
</head>
<body>
    <jsp:include page="location.jsp" flush='true'>
        <jsp:param name="name" value="Applications"/>
    </jsp:include>
    <%@ include file="menu.jsp" %>
<div id="workarea">
    <%@ include file="operations.jsp" %>
    <h1>Applications</h1>
                                <form name="apps" action="main" method="post">
				<%
				
				if (appsHash==null || appsHash.size()==0){ %>
					<b>No applications found!</b><%
				}
				else{ %>
					<table width="600" cellspacing="0" cellpadding="3">
					
						<%
						Application sessionApp = (Application)session.getAttribute(Attrs.APP);
						if (sessionApp!=null){ %>
							<tr>
								<td colspan="2" align="right">
									[ <a href="javascript:logout()">Logout of <%=sessionApp.getName()%></a> ]
								</td>
							</tr><%
						}
						%>
					
						<tr>
							<th width="100" align="left">Application</th>
							<th width="500" align="left">Host</th>
						</tr>
						
						<%
						for (Enumeration apps = appsHash.elements(); apps.hasMoreElements();){
							Application app = (Application)apps.nextElement();
							String href = "javascript:login('" + app.getName() + "')";
							if (sessionApp!=null && sessionApp.getName().equals(app.getName()))
								href = "javascript:gotoScreens()";
							%>
							<tr>
								<td class="head0"><a href="<%=href%>"><%=app.getName()%></a></td>
								<td class="head0"><%=app.getHost()%></td>
							</tr>
							<%
						}
						%>
						
					</table><%
				}
				
				%>
				
				<input type="hidden" name="<%=Params.ACTION%>" value="<%=Actions.LIST_SCREENS%>"/>
				</form>
				</div>
<%@ include file="footer.jsp" %>
</body>
</html>
