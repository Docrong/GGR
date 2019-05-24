<%@page import="com.boco.eoms.wap.util.WapUtil"%>
<%@page import="com.boco.eoms.wap.util.WAPConstants"%>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<%@page import="java.util.List,java.util.ArrayList,com.boco.eoms.wap.model.*" pageEncoding="UTF-8"%>
	<%@ include file="/wap/common/taglibs.jsp"%>
	<%@ page import="com.boco.eoms.base.Constants;"%>
	<head>
		<link href="${app}/wap/css/style.css" rel="stylesheet" type="text/css" />
		<title><bean:message key="wap.title" /></title>
	</head>
	<%response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			String exceptionEoms = request
					.getAttribute(Constants.EOMS_SECURITY_EXCEPTION_KEY) == null ? ""
					: (String) request
							.getAttribute(Constants.EOMS_SECURITY_EXCEPTION_KEY);
	          //out.println(request.ServerVariables("HTTP_X_UP_CALLING_LINE_ID "));    
	          //out.println(request.getHeaders("x-up-calling-line-id"));
	         
			%>
	<body>
		<center>
		<form name="form2" method="post" action="${app}/wapLogin.do?method=wapSaveSession">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="login_bg">

				<tr>
					<td align="center"><img src="${app}/wap/images/logo.gif" /></td></tr>
						<!--<bean:message key="wap.login" />-->
						<tr><td><table class="login_table">
							 
								<%if (request.getParameter("error") != null) {

			%>
								<font color="#FF0000">
								<bean:message key="wap.error" /></font>
								<br />
								<%} else if (exceptionEoms.equals("BadCredentialsException")) {

			%>
								<font color="#FF0000">
								<fmt:message key="errors.password.mismatch" /></font>
								<br />
								<%} else if (exceptionEoms.equals("LockedException")) {

			%>					<font color="#FF0000">
								<fmt:message key="errors.account.locked" />
								</font>
								<br />
								<%} else if (exceptionEoms.equals("DisabledException")) {

			%>					<font color="#FF0000">
								<fmt:message key="errors.account.expired" />
								</font>
								<br />
								<%}

			%>
							<tr>
								<td class="user_text">
									<bean:message key="wap.username" />
									<bean:message key="wap.maohao" />
								</td>

								<td>
									<input name="j_username" type="text" value="<%=WapUtil.getValue4Cookie(WAPConstants.WAP_COOKIE_LOGIN_NAME,request.getCookies()) %>" size="10" maxlength="10" />
								</td>

							</tr>
							<tr>
								<td class="user_text">
									<bean:message key="wap.password" />
									<bean:message key="wap.maohao" />
								</td>
								<td>
									<input name="j_password" type="password" size="10" maxlength="10" />
								</td>
							</tr>
							<tr>
					<td colspan="2" class="table_td">
						<input name="Submit" type="submit" value='<bean:message key="wap.loginSubmit" />' class="btn" /> &nbsp
						<input name="Button" type="button" value='<bean:message key="button.remberPassword" />' class="btn" 
							onclick="javascript:{var url='${app}/wap/forget/index.do';location.href=url}"/>	
					</td>
			
							<input name="wapLogin" type="hidden" value="wap" />
						</table></td>
				</tr>					
			</table>
		</form>
		</center>
	</body>

</html>
