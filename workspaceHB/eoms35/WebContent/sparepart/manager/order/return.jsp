
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<html>
<head>
<title>
<bean:message key="sparepart.return"/>
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<div align="center">
      <center>
	  <br>
<table border="0" width="95%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
         <b><bean:message key="sparepart.return"/></b>
      </td>
    </tr>
</table>
<form action="../part/returnover.do" method="post" name="TawSparepartForm">
<table border="0" width="95%" cellspacing="1" cellpadding="1" align=center>
    <tr class="tr_show_left">
      <td>
        ${eoms:a2u('您当前操作的仓库是：')}<font color="red"><bean:write name="storage_dept_name" scope="session"/></font>&nbsp;&nbsp;的<b>&nbsp;&nbsp;<font color="blue"><bean:write name="storage" scope="session"/></font></b>
      </td>
    </tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align="center">
        <tr class="td_label">
                      <td align="center">${eoms:a2u('归还')}</td>
                      <td align="center"><bean:message key="sparepart.mangle"/></td>
                      <td align="center">${eoms:a2u('原序列号')}</td>
                      <td align="center">${eoms:a2u('新序列号')}</td>
                      <td align="center">${eoms:a2u('备件名称')}</td>
                      <td align="center">${eoms:a2u('供货厂商')}</td>
                      <td align="center">${eoms:a2u('借用人')}</td>
                      <td align="center">${eoms:a2u('借用部门')}</td>
                      <td align="center">${eoms:a2u('借出时间')}</td>
                      <td align="center">${eoms:a2u('归还时间')}</td>
        </tr>
<%int i=1;%>
    <logic:iterate id="tawOrderDetail" name="tawOrderDetail" type="com.boco.eoms.sparepart.model.TawOrderDetail">
        <tr class="tr_show">
                      <td><p align="center">
                      <INPUT TYPE="checkbox" name="return"  id="<%="a"+i%>" label="<%="b"+i%>" onClick="checkOnly(this);"
                        value="<bean:write name="tawOrderDetail" property="sparepartId" scope="page"/>,<bean:write name="tawOrderDetail" property="orgSerialNo" scope="page"/>,<bean:write name="tawOrderDetail" property="serialno" scope="page"/>,<bean:write name="tawOrderDetail" property="orderId" scope="page"/>,<bean:write name="tawOrderDetail" property="operater" scope="page"/>">
                      </td>
                      <td><p align="center">
                      <INPUT TYPE="checkbox" name="mangle"  id="<%="b"+i%>" label="<%="a"+i%>" onClick="checkOnly(this);"
                        value="<bean:write name="tawOrderDetail" property="sparepartId" scope="page"/>,<bean:write name="tawOrderDetail" property="orgSerialNo" scope="page"/>,<bean:write name="tawOrderDetail" property="serialno" scope="page"/>,<bean:write name="tawOrderDetail" property="orderId" scope="page"/>,<bean:write name="tawOrderDetail" property="operater" scope="page"/>">
                      </td>
                      <td align="center"><bean:write name="tawOrderDetail" property="orgSerialNo" scope="page"/></td>
                      <td align="center"><bean:write name="tawOrderDetail" property="serialno" scope="page"/></td>
                      <td align="center"><bean:write name="tawOrderDetail" property="objectname" scope="page"/></td>
                      <td align="center"><bean:write name="tawOrderDetail" property="supplier" scope="page"/></td>

                      <td align="center"><bean:write name="tawOrderDetail" property="proposer" scope="page"/></td>
                      <td align="center"><bean:write name="tawOrderDetail" property="propDept" scope="page"/></td>
                      <td align="center"><bean:write name="tawOrderDetail" property="startdate" scope="page"/></td>
                      <td align="center"><eoms:SelectDate name="outtime" formName="TawSparepartForm"/></td>
                      	
        </tr>
<%i++;%>
    </logic:iterate>
</table>
<SCRIPT LANGUAGE="JavaScript">
<!--
function checkOnly(el){
	document.getElementById(el.label).checked=false;
}
//-->
</SCRIPT>
<table border="0" width="95%" cellspacing="0">
   <tr>
     <td height="32" align="right">
          <input type="submit" value="<bean:message key="label.submit"/>" name="submit" class="clsbtn2">
          &nbsp;&nbsp;
          <input type="reset" value="<bean:message key="label.reset"/>" name="reset" class="clsbtn2">
          &nbsp;&nbsp;
      </td>
    </tr>
</table>
</form>

</center>
</div>
</body>
<%@ include file="/common/footer_eoms.jsp"%>

</html>
