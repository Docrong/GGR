
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.ArrayList,java.util.List,com.boco.eoms.common.util.StaticMethod" %>
<%@ page import="com.boco.eoms.sparepart.model.*"%>
<html>
<head>
<title>
<bean:message key="storage.choose"/>
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<div align="center">
<form action="../../sparepart/storage/choosed.do" method="post" name="TawStorageForm" onsubmit="return checkdata()">
<table border="0" width="70%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
        <b><bean:message key="storage.choose"/></b>
      </td>
    </tr>
</table>
<br>
<table border="0" width="70%" cellspacing="1" cellpadding="1" class="table_show" align=center>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="storage.select"/>:</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                        <select name="id" size="1" style="FONT-SIZE: 8pt">
                           <option value=""><bean:message key="storage.select"/></option>
                           <logic:iterate id="storage" name="storage" type="com.boco.eoms.sparepart.model.TawStorage">
                           <option value="<bean:write name="storage" property="id" scope="page"/>"><bean:write name="storage" property="storagename" scope="page"/></option>
                           </logic:iterate>
                        </select>
                  </td>
    </tr>
</table>
<table border="0" width="70%" cellspacing="0">
    <tr>
      <td height="32" align="right">
          <input type="submit" value="<bean:message key="label.ok"/>" name="submit" class="clsbtn2">
          &nbsp;&nbsp;
      </td>
    </tr>
  </table>
</form>
</div>
</body>
<%@ include file="/common/footer_eoms.jsp"%>

</html>
<script language="javascript">
function checkdata() {
        if ( document.TawStorageForm.id.value == "" ) {
                alert('${eoms:a2u("请选择仓库！")}');
                document.TawStorageForm.id.focus();
                return false;
        }
       return true;
}
</script>
