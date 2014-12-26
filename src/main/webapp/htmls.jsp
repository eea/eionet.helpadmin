<%@page contentType="text/html;charset=UTF-8" import="java.util.*,eionet.helpadm.*,eionet.help.*,eionet.helpadm.util.*"%>

<%

request.setCharacterEncoding("UTF-8");

String screenID = (String)request.getParameter(Params.SCREEN_ID);
String areaID   = (String)request.getParameter(Params.AREA_ID);
String areaDescr   = (String)request.getParameter(Params.AREA_DESCR);
if (areaDescr==null)
	areaDescr = "";

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

Boolean isUserSuperuser = Application.isUserSuperuser(session);
String strThemeAdvancedDisable = isUserSuperuser!=null && isUserSuperuser.booleanValue()==true ?
									"fontselect,fontsizeselect,styleselect,anchor,image,hr,visualaid" :
									"fontselect,fontsizeselect,styleselect,anchor,image,cleanup,code,hr,visualaid";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <%@ include file="headerinfo.txt" %>
    <title>Help Admin Tool</title>
    <script type="text/javascript" src="tinymce/jscripts/tiny_mce/tiny_mce.js"></script>
    <script type="text/javascript">
// <![CDATA[

		tinyMCE.init({
			mode : "exact",
			elements : "helpTextArea1",
			theme : "advanced",
			theme_advanced_disable : "<%=strThemeAdvancedDisable%>"
		});

    	function gotoAreas(){
	    	document.forms["htmls"].elements["<%=Params.ACTION%>"].value = "<%=Actions.LIST_AREAS%>";
	    	document.forms["htmls"].submit();
    	}
// ]]>
    </script>
</head>
<body>
<div id="container">
    <jsp:include page="location.jsp" flush='true'>
        <jsp:param name="name" value="Area"/>
    </jsp:include>
    <%@ include file="menu.jsp" %>
<div id="workarea">
    <div id="operations">
    <ul>
        <li><a href="javascript:gotoAreas()">&lt; Back to areas</a></li>
    </ul>
    </div>

    <h1>Properties of <em><%=areaID%></em> area</h1>

	<form id="htmls" method="post" action="main">
	
		<table width="100%" cellspacing="0" cellpadding="0">
			<tr>
				<td><label for="fld_descr" class="question">Title</label></td>
				<td><input type="text" size="60" id="fld_descr" name="<%=Params.AREA_DESCR%>" value="<%=areaDescr%>"/></td>
			</tr>
			<tr>
				<td colspan="2"><label class="question">HTML text</label></td>
			</tr>
			<tr>
				<td colspan="2">
					<%
					String doubleEscapedHtmlText = eionet.helpadm.util.Util.escapeEscapedHTML(htmlText);
					%>
					<textarea id="helpTextArea1" style="width:100%" cols="80" rows="20" name="<%=Params.HTML_TEXT%>"><%=doubleEscapedHtmlText%></textarea>
				</td>
			</tr>
		</table>
		
		<table width="650" cellspacing="0" cellpadding="0">
		  <col style="width: 15%"/>
		  <col style="width: 60%"/>
		  <col style="width: 25%"/>
			<tr>
				<td><label class="question">Popup width</label></td>
				<td colspan="2"><label class="question">Popup length</label></td>
			</tr>
			<tr>
				<td>
					<input type="text" size="10" name="<%=Params.POPUP_WIDTH%>" value="<%=popupWidth%>" />
				</td>
				<td>
					<input type="text" size="10" name="<%=Params.POPUP_LENGTH%>" value="<%=popupLength%>" />
				</td>
				<td align="right">
				</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<input type="submit" value="Save"/>
				</td>
			</tr>
		</table>
		
		<div style="display:none">
			<input type="hidden" name="<%=Params.ACTION%>" value="<%=Actions.EDIT_AREA%>"/>
			<input type="hidden" name="<%=Params.SCREEN_ID%>" value="<%=screenID%>"/>
			<input type="hidden" name="<%=Params.AREA_ID%>" value="<%=areaID%>"/>
		</div>
		
	</form>
</div> <!-- workarea -->
</div> <!-- container -->
<%@ include file="footer.jsp" %>
</body>
</html>
