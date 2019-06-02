<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.boco.eoms.commons.statistic.base.util.StatManagerMethod,com.boco.eoms.commons.statistic.base.mgr.impl.StatConfigManagerImpl"%>
<!-- 删除统计工具产生的垃圾文件-->
<%
	String StatDeleteValue = request.getParameter("StatDeleteValue");
	if("StatDeleteExcel".equalsIgnoreCase(StatDeleteValue))
	{
		StatManagerMethod.deleteFileExcel();
	}
	else if("StatDeleteXml".equalsIgnoreCase(StatDeleteValue))
	{
		StatManagerMethod.deleteFileXml();
	}
%>

	<html>
	  <head>
	    <title>My JSP 'StatDelete.jsp' starting page</title>
	  </head>
	  
	  <body>
	  
	  <table align="center" valign="center">
	 	<tr align="center" valign="center">
	 		<td align="center" valign="center">
	 			 Execute Config succeed !
	 		</td>
	 	</tr>
	   </table>
	  		
	  </body>
	</html>
