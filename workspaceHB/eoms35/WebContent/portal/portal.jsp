<%@ include file="/portal/common/taglibs-portal.jsp"%>
<%@ page import = "org.light.portal.user.entity.UserEntity" %>
<html>
	<head>		
	    <%@ include file="/portal/common/meta-portal.jsp"%>  
	    
	    <light:personalAccount/>
	    <script language="JavaScript">
	    /*-----------------20070409 zhoucz add start---------------------*/
	    <%
	    	UserEntity user = (UserEntity)session.getAttribute("userEntity");
	    	String userNames = null;
	    	String userIds = null;
	    	if(null != user){
	    	 userNames = (String)(user.getLastName()+user.getFirstName());
	    	 userIds = (String)user.getUserId();
	    	}
	    %>
	    var userName='<%=userNames%>';//zhoucz add 20070409
	    var userIds='<%=userIds%>';//zhoucz add 20070403 for CAS
	    /*-----------------20070409 zhoucz add end---------------------*/
	    </script>
	    <script language="JavaScript" src="<%= request.getContextPath() %>/portal/light/scripts/prototype.js"></script>
	    <script language="JavaScript" src="<%= request.getContextPath() %>/portal/light/scripts/rico.js"></script>
	    <script language="JavaScript" src="<%= request.getContextPath() %>/portal/light/scripts/lightPortal.js"></script>		      	    	 	    		    				
	    <script language="JavaScript" src="<%= request.getContextPath() %>/portal/light/scripts/lightPortlet.js"></script>		      	    	 	    		    				
	    <script language="JavaScript" src="<%= request.getContextPath() %>/portal/light/scripts/lightPortletChat.js"></script>		      	    	 	    		    				
	    <script language="JavaScript" src="<%= request.getContextPath() %>/portal/light/scripts/locale/lightResourceBundle<%= (session.getAttribute("currentLocale") != null) ? "_"+(String)session.getAttribute("currentLocale") : "" %>.js"></script>		      	    	 	    		    								          
	    <LINK href="<%= request.getContextPath() %>/portal/light/<%= (session.getAttribute("theme") != null) ? (String)session.getAttribute("theme") : "theme/master.css"%>" rel="stylesheet" type="text/css">           
        <title>Light Portal</title>
	</head>
	<body bgcolor="#FFFFFF"  leftmargin="0" topmargin="0"
			 rightmargin="0" bottommargin="0" marginwidth="0" marginheight="0">
	</body>
</html>