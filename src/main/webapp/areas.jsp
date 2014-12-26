<%@page contentType="text/html;charset=UTF-8" import="java.util.*,eionet.helpadm.*,eionet.helpadm.util.*,eionet.help.*"%>

<%

request.setCharacterEncoding("UTF-8");

Vector areas = (Vector)request.getAttribute(Attrs.AREAS);
String screenID = request.getParameter(Params.SCREEN_ID);

if (areas!=null)
	Collections.sort(areas, new CompHashtable(Area.ID));
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <%@ include file="headerinfo.txt" %>
    <title>Help Admin Tool</title>
    <script type="text/javascript">
// <![CDATA[
    	function gotoHtmls(areaID, popupWidth, popupLength, descr){
	    	document.forms["areas"].elements["<%=Params.SCREEN_ID%>"].value = "<%=screenID%>";
	    	document.forms["areas"].elements["<%=Params.AREA_ID%>"].value = areaID;
	    	document.forms["areas"].elements["<%=Params.POPUP_WIDTH%>"].value = popupWidth;
	    	document.forms["areas"].elements["<%=Params.POPUP_LENGTH%>"].value = popupLength;
	    	document.forms["areas"].elements["<%=Params.AREA_DESCR%>"].value = descr;
	    	document.forms["areas"].submit();
    	}
    	
    	function createNew(){
	    	document.forms["create"].submit();
    	}
    	
    	function remove(){
	    	document.forms["areas"].elements["<%=Params.ACTION%>"].value = "<%=Actions.REMOVE_AREAS%>";
	    	document.forms["areas"].submit();
    	}
    	
    	function gotoScreens(){
	    	document.forms["areas"].elements["<%=Params.ACTION%>"].value = "<%=Actions.LIST_SCREENS%>";
	    	document.forms["areas"].submit();
    	}
// ]]>
    </script>
</head>
<body>
<div id="container">
    <jsp:include page="location.jsp" flush='true'>
        <jsp:param name="name" value="Areas"/>
    </jsp:include>
    <%@ include file="menu.jsp" %>
<div id="workarea">
    <div id="operations">
    <ul>
        <li><a href="javascript:gotoScreens()">&lt; back to screens</a></li>
    </ul>
    </div>

    <h1>Areas of <em><%=screenID%></em> screen</h1>					
	<br/>
	<br/>
				<form id="areas" method="post" action="main">					
				<%
				
				if (areas==null || areas.size()==0){ %>
					<strong>No areas found!</strong><br/><br/>
					<input type="button" class="smallbutton" value="Create new" onclick="createNew()"/><%
				}
				else{ %>				
					<table width="600" cellspacing="0" cellpadding="3">
					
						<tr>
							<td colspan="3">
								<input type="button" class="smallbutton" value="Create new" onclick="createNew()"/>
								<input type="button" class="smallbutton" value="Remove selected" onclick="remove()"/>
							</td>
						</tr>
						<tr>
							<td colspan="3">&nbsp;</td>
						</tr>
						<tr>
							<th style="width:20">&nbsp;</th>
							<th style="width:100" align="left">Area</th>
							<th style="width:480" align="left">Description</th>
						</tr>
						
						<%
						for (int i=0; i<areas.size(); i++){
							Hashtable area = (Hashtable)areas.get(i);
							String id = (String)area.get(Area.ID);
							String descr = (String)area.get(Area.DESCR);
							String popupWidth =  (String)area.get(Area.POPUP_WIDTH);
							String popupLength = (String)area.get(Area.POPUP_LENGTH);
							%>
							<tr>								
								<td align="right">
									<input type="checkbox" name="<%=Params.RMV_ID%>" value="<%=id%>"/>
								</td>
								<td>
									<a href="javascript:gotoHtmls('<%=id%>', '<%=popupWidth%>', '<%=popupLength%>', '<%=descr%>')"><%=id%></a>
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
						<input type="hidden" name="<%=Params.ACTION%>" value="<%=Actions.LIST_HTMLS%>"/>
						<input type="hidden" name="<%=Params.SCREEN_ID%>" value="<%=screenID%>"/>
						<input type="hidden" name="<%=Params.AREA_ID%>" value=""/>
						<input type="hidden" name="<%=Params.POPUP_WIDTH%>" value=""/>
						<input type="hidden" name="<%=Params.POPUP_LENGTH%>" value=""/>
						<input type="hidden" name="<%=Params.AREA_DESCR%>" value=""/>
					</fieldset>
				</form>
				
				<form id="create" action="<%=JSPs.AREA%>" method="post">
					<fieldset style="display:none">
						<input type="hidden" name="<%=Params.SCREEN_ID%>" value="<%=screenID%>"/>
					</fieldset>
				</form>
				
				</div> <!-- workarea -->
				</div> <!-- container -->
<%@ include file="footer.jsp" %>
</body>
</html>
