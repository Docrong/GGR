<%@page pageEncoding="UTF-8"%>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
	<%@ include file="/wap/common/taglibs.jsp"%>
	<%@ page import="com.boco.eoms.base.Constants;"%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><bean:message key="wap.common.rember.password.title" /></title>
	</head>
	<body>
	 
		<table border="0" cellspacing="0" cellpadding="0">
		     
				<tr>
					<td align="center">
						<font color="red">系统超时.请返回</font>
					</td>

				</tr>
				<tr>
					<td align="center">
					 <a href="${app}/wap/login.jsp" >返回</a>
					</td>
				</tr>
		 
		</table>
	</body>
</html>
