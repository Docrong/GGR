
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="com.boco.eoms.sparepart.util.TawClassMsgTree"%>
<%@ page import="java.util.List,com.boco.eoms.sparepart.model.*,com.boco.eoms.common.util.StaticMethod"%>
<html>
<%
  List sparepart=(List)request.getAttribute("sparepart");
  TawPart part=(TawPart)sparepart.get(0);
%>
<head>
<title>
${eoms:a2u('替换备件')}
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
<style>
body,select
{
	font-size:9pt;
	font-family:Verdana;
}
select {background-color:#F0F0F0;}
</style>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif" onload="setup()">
<div align="center">
<br>
<form method="post" action="../part/serialnoend.do" name="frm" onsubmit="return checkdata()">
<table border="0" width="80%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
        <b>${eoms:a2u('替换备件')}</b>
      </td>
    </tr>
</table>
<table border="0" width="80%" cellspacing="1" cellpadding="1" class="table_show" align=center>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('已损坏备件序列号：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text"size="35" value=""  maxlength="50" name="badserialno">
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('被替换备件序列号：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" size="35" value="<%=StaticMethod.nullObject2String(part.getSerialno())%>"  maxlength="50" name="serialno">
                      <a href="../../sparepart/charge/updateterm.do" class="clsbtn2">&nbsp;&nbsp;&nbsp;&nbsp${eoms:a2u('查询')}
                    </a>
                  </td>
    </tr>
</table>
<table border="0" width="80%" cellspacing="0">
    <tr>
      <td height="32" align="right">
          <input type="submit" value="<bean:message key="label.submit"/>" name="submit" onclick="frm.differ.value=1" class="clsbtn2">
          &nbsp;&nbsp;
      </td>
    </tr>
</table>

<script language="javascript">
function checkdata() {
        if ( document.frm.serialno.value == "" ) {
                alert('${eoms:a2u("请输入被替换备件序列号！")}');
                document.frm.serialno.focus();
                return false;
        }
       if ( document.frm.badserialno.value == "" ) {
                alert('${eoms:a2u("请输入已损坏备件序列号！")}');
                document.frm.badserialno.focus();
                return false;
        }
      return true;
}
</script>
</form>
</div>
</body>
<%@ include file="/common/footer_eoms.jsp"%>

</html>
