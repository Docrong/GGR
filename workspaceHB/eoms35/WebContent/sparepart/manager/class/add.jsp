<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<%@ page import="java.util.ArrayList,java.util.List,com.boco.eoms.common.util.StaticMethod" %>
<%@ page import="com.boco.eoms.sparepart.model.*"%>
<html>
<head>
<title>
<bean:message key="class.create"/>
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<div align="center">
<table border="0" width="75%" cellspacing="0">
    <tr>
      <td class="table_title" align="center"><b><bean:message key="class.create"/></b>
      </td>
    </tr>
</table>
<form method="post" action="../storage/insertclass.do"  name="tawClassMsgForm" onsubmit="return checkdata()">
<html:hidden property="parentId" name="tawClassMsgForm" />
<table border="0" width="75%" cellspacing="1" cellpadding="1" class="table_show" align=center>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="class.name"/>：</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text"size="35" value=""  maxlength="50" name="cname">
                    <font color="#FF0000">**</font>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25"><bean:message key="class.note"/>：</td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" name="note" size="35"   maxlength="255" >
                  </td>
    </tr>
</table>
<table border="0" width="75%" cellspacing="0">
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
</div>
</body>
<%@ include file="/common/footer_eoms.jsp"%>
</html>
<script language="javascript">
function checkdata() {
        if ( document.tawClassMsgForm.cname.value == "" ) {
                alert('${eoms:a2u("请选择备件所属名称！")}');
                document.tawClassMsgForm.cname.focus();
                return false;
        }
        var vl=document.tawClassMsgForm.cname.value;
        if(vl.length>20){
                alert('${eoms:a2u("备件所属名称不能超过20个字符！")}');
                document.tawClassMsgForm.cname.focus();
                return false;
}
   return true;
}
</script>
