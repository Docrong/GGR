<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<html>

<body>
<center>
<br>

<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align=center>
  <tr class="td_label">
    <td width="20%" height="25"><bean:message key="tawSystemUserForm.userid"/></td>
    <td width="20%" height="25"><bean:message key="tawSystemUserForm.username"/></td>
    <td width="20%" height="25"><bean:message key="tawSystemUserForm.pwd"/></td>
    
    <td width="10%" height="25"><bean:message key="tawSystemUserForm.cptroomname"/></td>
    <td width="10%" height="25"><bean:message key="tawSystemUserForm.deptid"/></td>
    <td width="10%" height="25"><bean:message key="tawSystemUserForm.remark"/></td>
    
  </tr>
	<logic:iterate id="tawUser" name="tawSystemUserList" type="com.boco.eoms.commons.system.user.model.TawSystemUser">
  <tr class="tr_show">
    <td width="20%" height="25"><bean:write name="tawUser" property="userid" scope="page"/></td>
    <td width="20%" height="25"><bean:write name="tawUser" property="username" scope="page"/></td>
    <td width="20%" height="25"><bean:write name="tawUser" property="password" scope="page"/></td>
    <td width="20%" height="25"><bean:write name="tawUser" property="cptroomname" scope="page"/></td>
    <td width="20%" height="25"><bean:write name="tawUser" property="deptid" scope="page"/></td>
    <td width="20%" height="25"><bean:write name="tawUser" property="remark" scope="page"/></td>
<% if(tawUser.getDeleted().equals("1")){%>
    <td width="10%" height="25">
	<html:link page="/editTawSystemUser.do?method=comebackdel" paramId="id"
      paramName="tawUser" paramProperty="id"><img src="<%=request.getContextPath()%>/images/pic_5_3.gif"
      border="0" alt="<bean:message key="button.comebackdel"/><bean:write name="tawUser" property="id" scope="page"/> "></html:link></td>
    <td width="10%" height="25">
	<html:link page="/editTawSystemUser.do?method=delete" paramId="id"
      paramName="tawUser" paramProperty="id"><img src="<%=request.getContextPath()%>/images/dept.gif"
      border="0" alt="<bean:message key="button.comebackdel"/><bean:write name="tawUser" property="id" scope="page"/> "></html:link></td>
  <%} %>
  </tr>
	</logic:iterate>
</table>

  </center>
</div>

</body>

</html>