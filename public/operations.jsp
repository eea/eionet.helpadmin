<div id="operations">

	<%
    String back_button=request.getParameter("back");
    if (back_button!=null){
    	String back_url=(String)session.getAttribute("backUrl");
    	if (back_url!=null){
			String back = back_url.length()<1 ? "javascript:history.back(-1)":back_url;	
			%>
			<ul>
        		<li><a href="<%=back%>">&lt;back</a></li>
        	</ul><%
		}
	}
	%>	
</div>
