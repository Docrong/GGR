<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod" %>
<html>
<head>
<title>
${eoms:a2u('备件类型')}
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
<script language="JavaScript" src="<%=request.getContextPath()%>/css/area.js"></script>
</head>
<%
String str =StaticMethod.nullObject2String(request.getAttribute("StringTree"));
%>
<style>
body,select
{
	font-size:9pt;
	font-family:Verdana;
}
select {background-color:#F0F0F0;}
</style>

<SCRIPT LANGUAGE = JavaScript>
<!--
//** Power by Fason(2004-3-11)
//** Email:fason_pfx@hotmail.com

var s=["s1","s2","s3"];
var opt0 = ['${eoms:a2u("大专业类型")}','${eoms:a2u("小专业类型")}','${eoms:a2u("网元业类型")}'];

var dsy = new Dsy();
<%=str%>

function setup()
{
	for(i=0;i<s.length-1;i++)
		document.getElementById(s[i]).onchange=new Function("change(dsy,"+(i+1)+",s,opt0)");
	change(dsy,0,s,opt0);
}
//-->
</SCRIPT>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif" onload="setup()">
<div align="center">
<table border="0" width="75%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
         <b>${eoms:a2u('备件类型')}</b>
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
                    <select name="dept" id="s1" style="width: 6.8cm;"></select>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('小专业类型')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <select name="subType" id="s2" style="width: 6.8cm;"></select>
                  </td>
     </tr>
	 <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('网元类型')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <select name="root" id="s3" style="width: 6.8cm;"></select>
                  </td>
     </tr>
     <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('设备业类型')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <input name="type" type="text"size="35">
                  </td>
    </tr>
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
        if ( document.tawTree.dept.value == '${eoms:a2u("所属专业")}' ) {
                alert('${eoms:a2u("请选择专业！")}');
                document.tawTree.dept.focus();
                return false;
        }
        if ( document.tawTree.root.value == '${eoms:a2u("网元类型")}' ) {
                alert('${eoms:a2u("请选择网元类型！")}');
                document.tawTree.root.focus();
                return false;
        }
       return true;
}
</script>
