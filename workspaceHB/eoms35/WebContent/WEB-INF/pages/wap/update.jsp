<%@page pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<%@ include file="/wap/common/taglibs.jsp"%>
	<%@ page import="com.boco.eoms.base.Constants;"%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<link href="${app}/wap/css/style.css" rel="stylesheet" type="text/css" />
		<title><bean:message key="wap.common.rember.password.title" /></title>
	</head>
	<%String userid =(String) request.getAttribute("userid");%>
	 <%
	 String exceptionEoms = request
					.getAttribute(Constants.EOMS_SECURITY_EXCEPTION_KEY) == null ? ""
					: (String) request
							.getAttribute(Constants.EOMS_SECURITY_EXCEPTION_KEY);
	 %>
	<body>
	 
		
			 <form method="post" name="commonForm" action="${app}/wap/forget/update.do" >
			 	<table border="0" cellspacing="0" cellpadding="0" class="add_table_bg">
			 <% if (exceptionEoms.equals("PasswordError")) {

			           %>  
								<font color="#FF0000">
								<bean:message key="errors.password.error" /></font>
								<br />
								<%}   %>					
				<tr class="fb_xx_head_tr">
					<td class="tt_head_bg">
						<bean:message key="wap.common.rember.password" />
						<input type="text" name="password" /><bean:message key="wap.common.rember.password.tishi" />
						<input type="hidden" name="name" value="<%=userid%>"/>
					</td>

				</tr>
				
					<tr>
						<td class="btn_bg">
								<table>
									 <tr>
										 <td align="center"><input type="submit" class="btn_02" value="<bean:message key="wap.common.rember.password.submit"/>" />
										 </td>
									</tr>
									</table>
							</td>
					</tr>
													 
				</table>
			</form>
		
	</body>
</html>