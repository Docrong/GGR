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
	<body>
	 <%
	 String exceptionEoms = request
					.getAttribute(Constants.EOMS_SECURITY_EXCEPTION_KEY) == null ? ""
					: (String) request
							.getAttribute(Constants.EOMS_SECURITY_EXCEPTION_KEY);
	 %>
		<table border="0" cellspacing="0" cellpadding="0"  class="sub_table_bg">
		     <form method="post" name="commonForm" action="${app}/wap/forget/checkuser.do" >
			
								<% if (exceptionEoms.equals("NameIsNull")) {

			           %>  
								<font color="#FF0000">
								<bean:message key="errors.password.nameisnull" /></font>
								<br />
								<%} else if (exceptionEoms.equals("UserNotFound")) {

			%>					<font color="#FF0000">
								<bean:message key="errors.password.usernotfound" />
								</font>
								<br />
								<%} else if (exceptionEoms.equals("NotUpdatePassword")) {

			%>					<font color="#FF0000">
								<bean:message key="errors.password.notupdat" />
								</font>
								<br />
								<%}

			%>			
				
				<tr>   
			   <td class="list_text_03">
						<div class="div_all">
							<table  width="100%" border="0" cellpadding="1" cellspacing="1" class="add_table_bg">
									<tr class="fb_xx_head_tr">
										<td class="btn_bg">
											<bean:message key="wap.common.rember.password.user" />
											<input type="text" name="name" />
											<input type="hidden" name="tel" value="-1"/>
										</td>
			
									</tr>
									<tr>										
										<td class="btn_bg">
											<input type="submit" class="btn_02"
												value="<bean:message key="wap.common.rember.password.submit"/>" />
										</td>
					  			</tr>
							</table>
						</div>	
					</td>
				 </tr> 
			</form>
		</table>
	</body>
</html>
