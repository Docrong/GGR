<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<html:html>
<head>
<title>查看维护人员资料</title>
<html:base/>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>

<form name="viewmaintainer">
<body>
<br>
  <table border="0" width="100%" cellspacing="0" align="center">
	<tr>
      <td width="100%" align="center" class="table_title">
        <b>
          <bean:message key="label.view"/>&nbsp;<bean:message key="TawInfMaintainer.Name"/>
        </b>
      </td>
    </tr>
  </table>
  <table border="0" width="100%" cellspacing="1" cellpadding="1" class="table_show" align="center">
    <tr class="tr_show">
      <td width="30%" height="25" class="clsfth">&nbsp;
        &nbsp<bean:message key="TawInfMaintainer.Maintainer_Name"/>
	  </td>
	  <td width="70%" height="25">
		&nbsp<bean:write name="tawInfMaintainerForm" property="maintainerName" scope="request"/>
	  </td>
	</tr>
    <tr class="tr_show">
      <td width="30%" height="25" class="clsfth">&nbsp;
        &nbsp<bean:message key="TawInfMaintainer.Maintainer_Sex"/>
	  </td>
	  <td width="70%" height="25">
		&nbsp<bean:write name="tawInfMaintainerForm" property="maintainerSex" scope="request"/>
	  </td>
	</tr>
	<tr class="tr_show">
      <td width="30%" height="25" class="clsfth">&nbsp;
        &nbsp<bean:message key="TawInfMaintainer.DeptName"/>
	  </td>
	  <td width="70%" height="25">
		&nbsp<bean:write name="tawInfMaintainerForm" property="deptName" scope="request"/>
	  </td>
	</tr>
	<tr class="tr_show">
      <td width="30%" height="25" class="clsfth">&nbsp;
        &nbsp<bean:message key="TawInfMaintainer.Tele"/>
	  </td>
	  <td width="70%" height="25">
		&nbsp<bean:write name="tawInfMaintainerForm" property="tele" scope="request"/>
	  </td>
	</tr>
	<tr class="tr_show">
      <td width="30%" height="25" class="clsfth">&nbsp;
        &nbsp<bean:message key="TawInfMaintainer.Tele_Mobile"/>
	  </td>
	  <td width="70%" height="25">
		&nbsp<bean:write name="tawInfMaintainerForm" property="teleMobile" scope="request"/>
	  </td>
	</tr>
	<tr class="tr_show">
      <td width="30%" height="25" class="clsfth">&nbsp;
        &nbsp<bean:message key="TawInfMaintainer.Tele_Home"/>
	  </td>
	  <td width="70%" height="25">
		&nbsp<bean:write name="tawInfMaintainerForm" property="teleHome" scope="request"/>
	  </td>
	</tr>
	<tr class="tr_show">
      <td width="30%" height="25" class="clsfth">&nbsp;
        &nbsp<bean:message key="TawInfMaintainer.Email"/>
	  </td>
	  <td width="70%" height="25">
		&nbsp<bean:write name="tawInfMaintainerForm" property="email" scope="request"/>
	  </td>
	</tr>
	<tr class="tr_show">
      <td width="30%" height="25" class="clsfth">&nbsp;
        &nbsp<bean:message key="TawInfMaintainer.Special"/>
	  </td>
	  <td width="70%" height="25">
		&nbsp<bean:write name="tawInfMaintainerForm" property="special" scope="request"/>
	  </td>
	</tr>
  </table>
  <table border="0" width="100%" cellspacing="1" align="center">
     <tr>
	    <td width="100%" height="32" align="right">
          <input type="button" value="<bean:message key="label.cancel"/>" onclick="history.back()" class="clsbtn2"/>
		</td>
    </tr>
  </table>
</body>
</form>

</html:html>
