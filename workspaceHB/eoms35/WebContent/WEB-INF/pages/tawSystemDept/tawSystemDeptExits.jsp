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
    <td width="20%" height="25"><bean:message key="tawSystemDeptForm.deptName"/></td>
    <td width="20%" height="25"><bean:message key="tawSystemDeptForm.deptmanager"/></td>
    <td width="20%" height="25"><bean:message key="tawSystemDeptForm.parentDeptid"/></td>
    
    <td width="10%" height="25"><bean:message key="tawSystemDeptForm.operuserid"/></td>
    <td width="10%" height="25"><bean:message key="tawSystemDeptForm.regionflag"/></td>
    <td width="10%" height="25"><bean:message key="tawSystemDeptForm.tmporaryManager"/></td>
    
    <td width="10%" height="25"><bean:message key="tawSystemDeptForm.remark"/></td>
  </tr>
	<logic:iterate id="tawDept" name="tawSystemDeptList" type="com.boco.eoms.commons.system.dept.model.TawSystemDept">
  <tr class="tr_show">
    <td width="20%" height="25"><bean:write name="tawDept" property="deptName" scope="page"/></td>
    <td width="20%" height="25"><bean:write name="tawDept" property="deptmanager" scope="page"/></td>
    <td width="20%" height="25"><bean:write name="tawDept" property="parentDeptid" scope="page"/></td>
    <td width="20%" height="25"><bean:write name="tawDept" property="operuserid" scope="page"/></td>
    <td width="20%" height="25"><bean:write name="tawDept" property="regionflag" scope="page"/></td>
    <td width="20%" height="25"><bean:write name="tawDept" property="tmporaryManager" scope="page"/></td>
    <td width="20%" height="25"><bean:write name="tawDept" property="remark" scope="page"/></td>
<% if(tawDept.getDeleted().equals("1")){%>
    <td width="10%" height="25">
	<html:link page="/editTawSystemDept.do?method=comebackdel" paramId="id"
      paramName="tawDept" paramProperty="id"><img src="<%=request.getContextPath()%>/images/pic_5_3.gif"
      border="0" alt="<bean:message key="button.comebackdel"/><bean:write name="tawDept" property="id" scope="page"/> "></html:link></td>
  <%} %>
  </tr>
	</logic:iterate>
</table>

  </center>
</div>

</body>

</html>