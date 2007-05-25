<%@page import="java.util.*"%>

<div id="toolribbon">
	<div id="lefttools">
      <a id="eealink" href="http://www.eea.europa.eu/">EEA</a>
      <a id="ewlink" href="http://www.ewindows.eu.org/">EnviroWindows</a>
    </div>
    <div id="righttools">
		<a id="printlink" title="Print this page" href="javascript:this.print();"><span>Print</span></a>
        <a id="fullscreenlink" href="javascript:toggleFullScreenMode()" title="Switch to/from full screen mode"><span>Switch to/from full screen mode</span></a>
        <a id="acronymlink" href="http://www.eionet.europa.eu/acronyms" title="Look up acronyms"><span>Acronyms</span></a>
        <form action="http://search.eionet.europa.eu/search.jsp" method="get"><div id="freesrchform"><label for="freesrchfld">Search</label>
        <input type="text" id="freesrchfld" name="q"/>
        <input id="freesrchbtn" type="image" src="images/button_go.gif" alt="Go"/></div></form>
    </div>
</div> <!-- toolribbon -->

<div id="pagehead">
        <a href="/"><img src="images/eealogo.gif" alt="Logo" id="logo" /></a>
        <div id="networktitle">Eionet</div>
        <div id="sitetitle">Administration of help texts of other sites</div>
        <div id="sitetagline">You change the online help text in Reportnet applications</div>
</div> <!-- pagehead -->


<div id="menuribbon">
	<%@ include file="dropdownmenus.txt" %>
</div>
<div class="breadcrumbtrail">
	<div class="breadcrumbhead">You are here:</div>
	<div class="breadcrumbitem eionetaccronym"><a href="http://www.eionet.europa.eu">Eionet</a></div>
	<div class="breadcrumbitem">HelpAdmin</div>
	<%
	String lastItemName = request.getParameter("name");
	if (lastItemName!=null && lastItemName.trim().length()>0){
		%>
		<div class="breadcrumbitemlast"><%=lastItemName%></div><%
	}
	%>
<div class="breadcrumbtail">
</div>
</div>
