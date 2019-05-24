
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<html>
<head>
<title>
<bean:message key="order.view"/>
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
         <b><bean:message key="order.view"/></b>
      </td>
    </tr>
</table>
<br>
<form action="../part/returnover.do?order=<bean:write name="order" scope="request" filter="false"/>" method="post" name="TawSparepartForm">
<%
    String storageName=(String)request.getSession().getAttribute("storage");
%>
<table border="0" width="95%" cellspacing="1" cellpadding="1" align=center>
    <tr class="tr_show_left">
      <td>
        ${eoms:a2u('您当前操作的仓库是：')}<b>&nbsp;&nbsp;<%=storageName%></b>
      </td>
    </tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align="center">
        <tr class="td_label">
                      <td align="center"><bean:message key="sparepart.department"/></td>
                      <td align="center"><bean:message key="sparepart.necode"/></td>
                      <td align="center"><bean:message key="sparepart.objectname"/></td>
                      <td align="center"><bean:message key="sparepart.supplier"/></td>
                      <td align="center"><bean:message key="sparepart.objectname"/></td>
                      <td align="center"><bean:message key="sparepart.state"/></td>
                      <td align="center"><bean:message key="sparepart.serialno"/></td>
                      <td align="center"><bean:message key="sparepart.return"/></td>
                      <td align="center"><bean:message key="sparepart.mangle"/></td>
        </tr>
<%int i=1;%>
    <logic:iterate id="sparepart" name="sparepart" type="com.boco.eoms.sparepart.model.TawPart">
        <tr class="tr_show">
                      <td align="center"><bean:write name="sparepart" property="nettype" scope="page"/></td>
                      <td align="center"><bean:write name="sparepart" property="necode" scope="page"/></td>
                      <td align="center"><bean:write name="sparepart" property="objecttype" scope="page"/></td>
                      <td align="center"><bean:write name="sparepart" property="supplier" scope="page"/></td>
                      <td align="center"><bean:write name="sparepart" property="objectname" scope="page"/></td>
                      <td align="center"><bean:write name="sparepart" property="state" scope="page"/></td>
                      <td align="center"><bean:write name="sparepart" property="serialno" scope="page"/></td>
                      <td><p align="center">
                      <INPUT TYPE="checkbox" name="return"  id="<%="a"+i%>" label="<%="b"+i%>" onClick="checkOnly(this);" value="<bean:write name="sparepart" property="id" scope="page"/>"></td>
                      </td>
                      <td><p align="center">
                      <INPUT TYPE="checkbox" name="mangle"  id="<%="b"+i%>" label="<%="a"+i%>" onClick="checkOnly(this);" value="<bean:write name="sparepart" property="id" scope="page"/>"></td>
                      </td>
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
