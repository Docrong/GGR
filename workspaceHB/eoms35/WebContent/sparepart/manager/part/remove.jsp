
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.List,java.util.ArrayList,com.boco.eoms.sparepart.model.*,com.boco.eoms.sparepart.model.TawStorage,com.boco.eoms.common.util.StaticMethod"%>
<%@ page import="java.lang.*"%>
<html>
<head>
<title>${eoms:a2u('备件转库')}</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<table border="0" width="95%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
      </td>
    </tr>
</table>
<form action="../part/remove.do" method="post" name="tawSparepartForm">
<table border="0" width="95%" cellspacing="1" cellpadding="1" align=center>
    <tr class="tr_show_left">
      <td>
        ${eoms:a2u('您当前操作的仓库是：')}<font color="red"><bean:write name="storage_dept_name" scope="session"/></font>&nbsp;&nbsp;${eoms:a2u('的')}<b>&nbsp;&nbsp;<font color="blue"><bean:write name="storage" scope="session"/></font></b>
      </td>
    </tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align="center">
       <tr class="tr_show">
         <td width="106%" colspan="10" align="right"><bean:write name="pagerHeader" scope="request" filter="false"/>
         <%! String key;%></td>
       </tr>
       <tr class="td_label" align="center">
        <td><bean:message key="label.select"/></td>
        <td><bean:message key="sparepart.department"/></td>
        <td><bean:message key="sparepart.necode"/></td>
        <td><bean:message key="sparepart.objectname"/></td>
        <td><bean:message key="sparepart.supplier"/></td>
        <td><bean:message key="sparepart.state"/></td>
        <td><bean:message key="sparepart.operator"/></td>
        <td><bean:message key="sparepart.managecode"/></td>
        <td><bean:message key="sparepart.serialno"/></td>
      </tr>
      <logic:iterate id="sparepart" name="sparepart" type="com.boco.eoms.sparepart.model.TawPart">
      <tr class="tr_show" align="center">
       <td>
         <INPUT TYPE="checkbox" name="id" value="<bean:write name="sparepart" property="id" scope="page"/>"></td>
       <td><bean:write name="sparepart" property="nettype" scope="page"/></td>
       <td><bean:write name="sparepart" property="necode" scope="page"/></td>
       <td><bean:write name="sparepart" property="objecttype" scope="page"/></td>
       <td><bean:write name="sparepart" property="supplier" scope="page"/></td>
       <td><bean:write name="sparepart" property="state" scope="page"/></td>
       <td><bean:write name="sparepart" property="operator" scope="page"/></td>
       <td><bean:write name="sparepart" property="managecode" scope="page"/></td>
       <td><bean:write name="sparepart" property="serialno" scope="page"/></td>
      </tr>
      </logic:iterate>
</table>
<table border="0" width="95%" cellspacing="0">
   <tr>
      <td height="32" align="right">
          <input type="submit" value="<bean:message key="label.ok"/>" name="pp" class="clsbtn2">
          &nbsp;&nbsp;
      </td>
    </tr>
</table>
</form>
<logic:present name="choosed">
<table border="0" width="95%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
           <b><bean:message key="view.choosed"/></b>
      </td>
    </tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align="center">
    <tr class="td_label">
      <td><bean:message key="sparepart.department"/></td>
	  <td>${eoms:a2u('小专业')}</td>
      <td><bean:message key="sparepart.necode"/></td>
      <td><bean:message key="sparepart.objectname"/></td>
      <td><bean:message key="sparepart.supplier"/></td>
      <td><bean:message key="sparepart.state"/></td>
      <td><bean:message key="sparepart.operator"/></td>
      <td><bean:message key="sparepart.managecode"/></td>
      <td><bean:message key="sparepart.serialno"/></td>
    </tr>
<logic:iterate id="choosed" name="choosed" type="com.boco.eoms.sparepart.model.TawPart">
        <tr class="tr_show" align="center">
          <td><bean:write name="choosed" property="nettype" scope="page"/></td>
		  <td><bean:write name="choosed" property="subdept" scope="page"/></td>
          <td><bean:write name="choosed" property="necode" scope="page"/></td>
          <td><bean:write name="choosed" property="objecttype" scope="page"/></td>
          <td><bean:write name="choosed" property="supplier" scope="page"/></td>
          <td><bean:write name="choosed" property="state" scope="page"/></td>
          <td><bean:write name="choosed" property="operator" scope="page"/></td>
          <td><bean:write name="choosed" property="managecode" scope="page"/></td>
          <td><bean:write name="choosed" property="serialno" scope="page"/></td>
        </tr>
</logic:iterate>
</table>

<table border="0" width="95%" cellspacing="1" cellpadding="1" align=center>
    <tr class="tr_show_left">
      <td>
	   ${eoms:a2u('您想调转的仓库是：')}<select name="storage_id" size="1" style="FONT-SIZE: 8pt">
                           <option value=""><bean:message key="storage.select"/></option>
                            <logic:iterate id="storage" name="storage" type="com.boco.eoms.sparepart.model.TawStorage">
                          	<option value="<bean:write name="storage" property="id" scope="page"/>"><bean:write name="storage" property="storagename" scope="page"/> </option>
                            </logic:iterate>
                        </select>
      </td>
    </tr>
</table>

<form action="../part/removeover.do" method="post" name="TawSparepartForm">
<html:hidden property="sumid" name="tawSparepartForm" />
<input type="hidden" id="storageId" name="storageId" value="">
<table border="0" width="95%" cellspacing="0">
    <tr>
      <td height="32" align="right">
          <input type="button" value="<bean:message key="label.submit"/>" name="pp" onclick="if(checkdata()) document.forms[1].submit();" class="clsbtn2">
          &nbsp;&nbsp;
      </td>
    </tr>
</table>
<script language="javascript">
function checkdata() {
        if ( document.all.storage_id.value == "" ) {
                alert('${eoms:a2u("请选择仓库！")}');
                document.all.storage_id.focus();
                return false;
        }
	document.all.storageId.value=document.all.storage_id.value;
	return true;
}
</script>
</form>
</logic:present>
</body>
<%@ include file="/common/footer_eoms.jsp"%>

</html>
