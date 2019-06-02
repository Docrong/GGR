
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.ArrayList,java.util.List,com.boco.eoms.common.util.StaticMethod" %>
<%@ page import="com.boco.eoms.sparepart.model.*"%>
<%@ page import="com.boco.eoms.commons.system.dept.model.TawSystemDept"%>
<html>
<%
	List dept = (List)request.getAttribute("DEPT");
%>
<head>
<title>
<bean:message key="storage.update"/>
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<div align="center">
<br>
<form action="../storage/update.do" method="post" name="TawStorageForm">
<html:hidden property="id" name="tawStorageForm"/>
<table border="0" width="75%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
        <b><bean:message key="storage.update"/></b>
      </td>
    </tr>
</table>
<table border="0" width="75%" cellspacing="1" cellpadding="1" class="table_show" align=center>
    <tr class="tr_show">
      <td class="clsfth" colspan="3" height="25" align="left">
        <font color="#CC0000">${eoms:a2u('注意：带有 **号的栏目是必须填写的，其他的栏目可以不填。')}</font>
      </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="storage.name"/>:</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text"size="35" value="<bean:write name="storage" property="storagename" scope="request"/>"  maxlength="50" name="storagename">
                   <font color="#FF0000">**</font></td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25"><bean:message key="storage.note"/>:</td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" name="note" size="35" value="<bean:write name="storage" property="note" scope="request"/>"  maxlength="255" >
                  </td>
    </tr>
</table>
<table border="0" width="75%" cellspacing="0">
	<tr class="tr_show">
		<td class= "clsfth" width="30%">${eoms:a2u('分公司')}</td>
		<td width="70%" height="25" colspan="2">
			<bean:define id="deptId" name="storage" property="deptid" type="java.lang.String"/>
			<select name="deptId" style="width: 3.6cm;">
	                <%
	                	for(int i = 0; i < dept.size(); i++) {
	                		TawSystemDept tawDept = (TawSystemDept)dept.get(i);
	                		if(deptId == tawDept.getDeptId()) {
	                %>
	                		<option value = <%=tawDept.getDeptId()%> selected><%=tawDept.getDeptName()%></option>
	                <%
	                		} else {
	                %>		
	                		<option value = <%=tawDept.getDeptId()%>><%=tawDept.getDeptName()%></option>
	                <%
	                		}
	                	}
	                %>
	        <select>
		</td>
	</tr>
</table>
<table border="0" width="75%" cellspacing="0">
    <tr>
      <td height="32" align="right">
          <input type="submit" value="<bean:message key="label.save"/>" name="submit" class="clsbtn2">
          &nbsp;&nbsp;
          <input type="reset" value="<bean:message key="label.reset"/>" name="reset" class="clsbtn2">
          &nbsp;&nbsp;
      </td>
    </tr>
  </table>
</form>
</div>
</body>
<%@ include file="/common/footer_eoms.jsp"%>

</html>
