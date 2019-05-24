<%@ include file="/portal/common/taglibs-portal.jsp"%>
<html>
	<head>		
	    <%@ include file="/portal/common/meta-portal.jsp"%>    	    
	    <light:personalAccount/>
	    <script language="JavaScript" src="<%= request.getContextPath() %>/portal/light/scripts/prototype.js"></script>
	    <script language="JavaScript" src="<%= request.getContextPath() %>/portal/light/scripts/rico.js"></script>
	    <script language="JavaScript" src="<%= request.getContextPath() %>/portal/light/scripts/lightPortal.js"></script>		      	    	 	    		    				
	    <script language="JavaScript" src="<%= request.getContextPath() %>/portal/light/scripts/lightPortlet.js"></script>		      	    	 	    		    					      	    	 	    		    				
	    <script language="JavaScript" src="<%= request.getContextPath() %>/portal/light/scripts/locale/lightResourceBundle<%= (session.getAttribute("currentLocale") != null) ? "_"+(String)session.getAttribute("currentLocale") : "" %>.js"></script>		      	    	 	    		    								          
	    <LINK href="<%= request.getContextPath() %>/portal/light/<%= (session.getAttribute("theme") != null) ? (String)session.getAttribute("theme") : "theme/master.css"%>" rel="stylesheet" type="text/css">           
        <title>Light Portal</title>
	</head>
	<body bgcolor="#FFFFFF"  leftmargin="0" topmargin="0"
			 rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0">
			 <table id="mainLayout" class="mainpage" border="0" cellpadding="0" cellspacing="0">
			 	<thead>
					<tr><th class="mainHeader" colspan="2" scope="colgroup">
						<tiles:insert attribute="header" flush="false"/>
					</th></tr>
				</thead>

				<tfoot>
					<tr><td class="footerPanel" colspan="2">
						<tiles:insert attribute="footer" flush="false"/>
					</td></tr>
				</tfoot>
				
				<tbody>
					<tr>
					<td class="menu"><span id="leftMenu">
						<tiles:insert attribute="menu" flush="false"/>
					</span></td>
					
					<td class="context"><span id="pageContext">								
						<img src="<%= request.getContextPath() %>/portal/light/images/spacer.gif" style="border: 0px" width="100" height="20" />				
						<tiles:insert attribute="body" flush="false"/>
					</span></td>
					</tr>
				</tbody>
			</table>
	</body>	
</html>  