<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="java.lang.String"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod,com.boco.eoms.datum.vo.impl.TawBsc1860VO"%>

<html>

<head>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>

<title><bean:message key="label.edit"/>BSC1860投诉登记表</title>

<html:html>

<html:form method="post" action="/TawBsc1860/save2"  >
<%
 TawBsc1860VO vo = new TawBsc1860VO() ;
 vo = (TawBsc1860VO)request.getAttribute("VO") ;
%>
<body>

<div align="center">
  <center>
<br>
<table border="0" width="95%" cellspacing="0">
  <tr class="tr_show">
    <td width="100%" class="table_title" align="center">
        <bean:message key="label.edit"/>BSC1860投诉登记表</td>
  </tr>
</table>

<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
<logic:present name="VO" scope="request">
<html:hidden  name="VO" property="id"/>

   <tr class="tr_show">
		<td width="15%" class="clsfth">单号</td>
		<td width="200"><html:text property="sheetId"  size="20" name="VO" /></td>
</tr>
   <tr class="tr_show">
		<td width="15%" class="clsfth">收单人</td>
		<td width="200"><html:text property="receiver"  size="20" name="VO" /></td>
</tr>
   <tr class="tr_show">
		<td width="15%" class="clsfth">收单时间</td>
		<td width="200"><html:text property="receTime"  size="20" name="VO" /></td>
</tr>
   <tr class="tr_show">
		<td width="15%" class="clsfth">状态</td>
		<td width="200"><html:text property="status"  size="20" name="VO" /></td>
</tr>

</logic:present>
</table>
<table border="0" width="95%" cellspacing="0">
  <tr>
    <td width="100%" height="32" align="right">
      <html:submit  styleClass="clsbtn2">
        <bean:message key="label.save"/>
      </html:submit>
&nbsp;
      <html:reset styleClass="clsbtn2">
         <bean:message key="label.reset"/>
      </html:reset>&nbsp;&nbsp;</td>
  </tr>
</table>
  </center>
</div>

</html:form>

<logic:messagesPresent>
                  <html:messages id="error">
	<script type="text/javascript">
		<!--
                    alert("<bean:write name="error"/>");
		-->
	</script>
                  </html:messages>
</logic:messagesPresent>

</html:html>
</body>

</html>
