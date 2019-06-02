<%@ include file="../../../common/taglibs.jsp"%>
<%@ include file="../../../common/header_eoms_form.jsp"%>
<%@ page import="java.util.*,java.lang.*"%>
<html:html>

<body>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" background="<%=request.getContextPath()%>/template/img/bg001.gif">
   <tr>
      <td width="100%" height="100%" align="center"><table width="743" height="440" background="<%=request.getContextPath()%>/images/bg001.jpg">
			<tr>
				<td align="center">
				  <font style="font-size:14px;color:#CC0000;"><strong><%
String strEndTimeDefined = String.valueOf(request.getAttribute("strEndTimeDefined"));
String maxerrortime = String.valueOf(request.getAttribute("maxerrortime"));
%>
        <bean:message key="TawRmRecord.notexchangtime1"/>
        
        <bean:message key="TawRmRecord.notexchangtime2"/><%=strEndTimeDefined%><bean:message key="TawRmRecord.notexchangtime3"/><%=maxerrortime%><bean:message key="TawRmRecord.notexchangtime4"/></strong></font>
				</td>
			</tr>
		</table></td>
    </tr>
</table>
</body>
</html:html>