<%@page contentType="text/html;charset=UTF-8" import="java.util.*,eionet.helpadm.*,eionet.helpadm.util.*,eionet.help.*"%>

<%

request.setCharacterEncoding("UTF-8");

Vector screens = (Vector)request.getAttribute(Attrs.SCREENS);
Application app = (Application)session.getAttribute(Attrs.APP);

if (screens!=null)
	Collections.sort(screens, new CompHashtable(Screen.ID));
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <%@ include file="headerinfo.txt" %>
    <title>Help Admin Tool</title>
    <script type="text/javascript">
// <![CDATA[
    	function gotoScreen(screenID){
	    	document.forms["screens"].elements["<%=Params.SCREEN_ID%>"].value = screenID;
	    	document.forms["screens"].submit();
    	}
    	
    	function createNew(){
	    	document.location.assign('<%=JSPs.SCREEN%>');
    	}
    	
    	function reload(){
	    	document.forms["screens"].elements["<%=Params.ACTION%>"].value = "<%=Actions.APPRELOAD%>";
	    	document.forms["screens"].submit();
    	}
    	
    	function remove(){
	    	document.forms["screens"].elements["<%=Params.ACTION%>"].value = "<%=Actions.REMOVE_SCREENS%>";
	    	document.forms["screens"].submit();
    	}
// ]]>
    </script>
</head>
<body>
<div id="container">
    <jsp:include page="location.jsp" flush='true'>
        <jsp:param name="name" value="Screens"/>
    </jsp:include>
    <%@ include file="menu.jsp" %>
<div id="workarea">
    <div id="operations">
    <ul>
        <li><a href="main">&lt; applications</a></li>
    </ul>
    </div>
    <h1>Screens of <b><%=app.getName()%></b> application</h1>
	<br/>
	<br/>
				<form id="screens" method="post" action="main">
				<%
				
				if (screens==null || screens.size()==0){ %>
					<b>No screens found!</b><br/><br/>
					<input type="button" class="smallbutton" value="Create new" onclick="createNew()"/><%
				}
				else{ %>
					<table width="600" cellspacing="0" cellpadding="3">
					
						<tr>
							<td colspan="3">
								<input type="button" class="smallbutton" value="Create new" onclick="createNew()"/>
								<input type="button" class="smallbutton" value="Remove selected" onclick="remove()"/>
								<input type="button" class="smallbutton" value="Reload" onclick="reload()"/>
							</td>
						</tr>
						<tr>
							<td colspan="3">&nbsp;</td>
						</tr>
						<tr>
							<th style="width:20">&nbsp;</th>
							<th style="width:100" align="left">Screen</th>
							<th style="width:480" align="left">Description</th>
						</tr>
						
						<%
						for (int i=0; i<screens.size(); i++){
							Hashtable screen = (Hashtable)screens.get(i);
							String id = (String)screen.get(Screen.ID);
							String descr = (String)screen.get(Screen.DESCR);
							%>
							<tr>
								<td align="right">
									<input type="checkbox" name="<%=Params.RMV_ID%>" value="<%=id%>"/>
								</td>
								<td>
									<a href="javascript:gotoScreen('<%=id%>')"><%=id%></a>
								</td>
								<td>
									<%=descr%>
								</td>
							</tr>
							<%
						}
						%>
						
					</table>
					
					<%
				}
				
				%>
					<fieldset style="display:none">
						<input type="hidden" name="<%=Params.ACTION%>" value="<%=Actions.LIST_AREAS%>"/>
						<input type="hidden" name="<%=Params.SCREEN_ID%>" value=""/>
					</fieldset>
				</form>
				</div> <!-- workarea -->
				</div> <!-- container -->
<%@ include file="footer.jsp" %>
</body>
</html>
