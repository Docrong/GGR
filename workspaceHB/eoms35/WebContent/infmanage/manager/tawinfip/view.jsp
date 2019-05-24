<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html>
<head>
<title>查看用户IP地址资料</title>
<html:base/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>

<form name="viewip">
<body>
<br>
  <table border="0" width="80%" cellspacing="0" align="center">
	<tr>
      <td width="100%" align="center" class="table_title">
        <b>
          <bean:message key="label.view"/>&nbsp;<bean:message key="TawInfIp.Name"/>
        </b>
      </td>
    </tr>
  </table>
  <table border="0" width="80%" cellspacing="1" cellpadding="1" class="table_show" align="center">
    <tr class="tr_show">
      <td width="30%" height="25" class="clsfth">&nbsp;
        &nbsp<bean:message key="TawInfIp.User_id"/>
	  </td>
	  <td width="70%" height="25">
		&nbsp<bean:write name="tawInfIpForm" property="userId" scope="request"/>
	  </td>
	</tr>
    <tr class="tr_show">
      <td width="30%" height="25" class="clsfth">&nbsp;
        &nbsp<bean:message key="TawInfIp.User_name"/>
	  </td>
	  <td width="70%" height="25">
		&nbsp<bean:write name="tawInfIpForm" property="userName" scope="request"/>
	  </td>
	</tr>
	<tr class="tr_show">
      <td width="30%" height="25" class="clsfth">&nbsp;
        &nbsp<bean:message key="TawInfIp.Dept_name"/>
	  </td>
	  <td width="70%" height="25">
		&nbsp<bean:write name="tawInfIpForm" property="deptName" scope="request"/>
	  </td>
	</tr>
	<tr class="tr_show">
      <td width="30%" height="25" class="clsfth">&nbsp;
        &nbsp<bean:message key="TawInfIp.User_address"/>
	  </td>
	  <td width="70%" height="25">
		&nbsp<bean:write name="tawInfIpForm" property="userAddress" scope="request"/>
	  </td>
	</tr>
	<tr class="tr_show">
      <td width="30%" height="25" class="clsfth">&nbsp;
        &nbsp<bean:message key="TawInfIp.User_tel"/>
	  </td>
	  <td width="70%" height="25">
		&nbsp<bean:write name="tawInfIpForm" property="userTel" scope="request"/>
	  </td>
	</tr>
	<tr class="tr_show">
      <td width="30%" height="25" class="clsfth">&nbsp;
        &nbsp<bean:message key="TawInfIp.User_type"/>
	  </td>
	  <td width="70%" height="25">
		&nbsp<bean:write name="tawInfIpForm" property="userType" scope="request"/>
	  </td>
	</tr>
	<tr class="tr_show">
      <td width="30%" height="25" class="clsfth">&nbsp;
        &nbsp<bean:message key="TawInfIp.Dev_port"/>
	  </td>
	  <td width="70%" height="25">
		&nbsp<bean:write name="tawInfIpForm" property="devPort" scope="request"/>
	  </td>
	</tr>
	<tr class="tr_show">
      <td width="30%" height="25" class="clsfth">&nbsp;
        &nbsp<bean:message key="TawInfIp.Dev_id"/>
	  </td>
	  <td width="70%" height="25">
		&nbsp<bean:write name="tawInfIpForm" property="devId" scope="request"/>
	  </td>
	</tr>
	<tr class="tr_show">
      <td width="30%" height="25" class="clsfth">&nbsp;
        &nbsp<bean:message key="TawInfIp.User_logic"/>
	  </td>
	  <td width="70%" height="25">
		&nbsp<bean:write name="tawInfIpForm" property="userLogic" scope="request"/>
	  </td>
	</tr>
	<tr class="tr_show">
      <td width="30%" height="25" class="clsfth">&nbsp;
        &nbsp<bean:message key="TawInfIp.Logicport"/>
	  </td>
	  <td width="70%" height="25">
		&nbsp<bean:write name="tawInfIpForm" property="logicPort" scope="request"/>
	  </td>
	</tr>
	<tr class="tr_show">
      <td width="30%" height="25" class="clsfth">&nbsp;
        &nbsp<bean:message key="TawInfIp.Remark"/>
	  </td>
	  <td width="70%" height="25">
		&nbsp<bean:write name="tawInfIpForm" property="remark" scope="request"/>
	  </td>
	</tr>
  </table>
  <table border="0" width="80%" cellspacing="1" align="center">
     <tr>
	    <td width="100%" height="32" align="right">
          <input type="button" value="<bean:message key="label.cancel"/>" onclick="history.back()" class="clsbtn2"/>
		</td>
    </tr>
  </table>
</body>
</form>

</html:html>
