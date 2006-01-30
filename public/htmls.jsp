<%@page contentType="text/html;charset=UTF-8" import="java.util.*,eionet.helpadm.*,com.tee.uit.help.*,eionet.helpadm.util.*"%>

<%

request.setCharacterEncoding("UTF-8");

String screenID = (String)request.getParameter(Params.SCREEN_ID);
String areaID   = (String)request.getParameter(Params.AREA_ID);

Hashtable htmls = (Hashtable)request.getAttribute(Attrs.HTMLS);

String htmlText = null;
if (htmls!=null){
	htmlText = (String)htmls.get(Area.DEFAULT_LANG);
	if (htmlText == null)
		htmlText = (String)htmls.get("");
}

if (htmlText==null)
	htmlText = "";

String popupWidth  = (String)request.getParameter(Params.POPUP_WIDTH);
if (popupWidth==null) popupWidth = "";
String popupLength = (String)request.getParameter(Params.POPUP_LENGTH);
if (popupLength==null) popupLength = "";

%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <title>Help Admin Tool</title>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
    <link rel="stylesheet" type="text/css" href="layout-print.css" media="print" />
    <link rel="stylesheet" type="text/css" href="layout-handheld.css" media="handheld" />
    <link rel="stylesheet" type="text/css" href="layout-screen.css" media="screen" title="EIONET style" />
    <script type="text/javascript">
    
    	function gotoAreas(){
	    	document.forms["htmls"].elements["<%=Params.ACTION%>"].value = "<%=Actions.LIST_AREAS%>";
	    	document.forms["htmls"].submit();
    	}
    	
    </script>
</head>
<body>
    <jsp:include page="location.jsp" flush='true'>
        <jsp:param name="name" value="Html text"/>
    </jsp:include>
    <%@ include file="menu.jsp" %>
<div id="workarea">
    <div id="operations">
    <ul>
        <li><a href="javascript:gotoAreas()">&lt; back to areas</a></li>
    </ul>
    </div>

    <h1>Html text</h1>


					<form name="htmls" method="post" action="main">
					
						<table width="auto" cellspacing="0" cellpadding="0">
							<tr>
								<td width="80%">Properties of <b><%=areaID%></b> area:</b></td>
								<td align="right" width="20%"><input type="submit" value="Save"/></td>
							</tr>
							<tr height="10"><td colspan="2"></td></tr>
							<tr><td colspan="2">HTML text:</td></tr>
							<tr>
								<td colspan="2">
									<%
									String doubleEscapedHtmlText = eionet.helpadm.util.Util.escapeEscapedHTML(htmlText);
									%>
									<textarea cols="80" rows="20" name="<%=Params.HTML_TEXT%>"><%=doubleEscapedHtmlText%></textarea>
								</td>
							</tr>
						</table>
						
						<table width="650" cellspacing="0" cellpadding="0">
							<tr>
								<td width="15%">Popup width:</td>
								<td width="85%" colspan="2">Popup length:</td>
							</tr>
							<tr>
								<td width="15%">
									<input type="text" size="10" name="<%=Params.POPUP_WIDTH%>" value="<%=popupWidth%>" />
								</td>
								<td width="60%">
									<input type="text" size="10" name="<%=Params.POPUP_LENGTH%>" value="<%=popupLength%>" />
								</td>
								<td width="25%" align="right">
								</td>
							</tr>
						</table>
						
						<input type="hidden" name="<%=Params.ACTION%>" value="<%=Actions.EDIT_AREA%>"/>
						<input type="hidden" name="<%=Params.SCREEN_ID%>" value="<%=screenID%>"/>
						<input type="hidden" name="<%=Params.AREA_ID%>" value="<%=areaID%>"/>
					
					</form>
				</div>
<%@ include file="footer.jsp" %>
</body>
</html>
