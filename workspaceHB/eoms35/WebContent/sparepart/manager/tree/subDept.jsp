
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<html>
<head>
<title>
${eoms:a2u('小专业类型')}
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<div align="center">
<table border="0" width="75%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
         <b>${eoms:a2u('小专业类型')}</b>
      </td>
    </tr>
</table>
<form name="tawTree" action="../tree/view.do" method="post" onsubmit="return checkdata()">
<table border="0" width="75%" cellspacing="1" cellpadding="1" class="table_show" align=center>
  <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('大专业类型')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <select name="id" size="1" style="width: 6.8cm;">
                            <option value=""><bean:message key="label.choose"/></option>
                            <logic:iterate id="tawTree" name="tawTree" type="com.boco.eoms.sparepart.model.TawTree">
                            <option value="<bean:write name="tawTree" property="id" scope="page"/>"><bean:write name="tawTree" property="cname" scope="page"/></option>
                            </logic:iterate>
                    </select>
                 </td>
    </tr>
<!--
     <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">网 元</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <input name="bb" type="text"size="35">
                  </td>
    </tr>
-->
</table>
<table border="0" width="75%" cellspacing="0">
    <tr>
       <td height="32" align="right">
          <input type="submit" value="<bean:message key="label.query"/>" name="reset"  class="clsbtn2">
          &nbsp;&nbsp;
      </td>
    </tr>
  </table>
</form>
</div>
</body>
</html>
<script language="javascript">
function checkdata() {
        if ( document.tawTree.id.value == "" ) {
                alert('${eoms:a2u("请选择大专业类型！")}');
                document.tawTree.id.focus();
                return false;
        }
       return true;
}
</script>
