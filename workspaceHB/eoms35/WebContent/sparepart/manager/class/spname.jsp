
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.ArrayList,java.util.List,com.boco.eoms.common.util.StaticMethod" %>
<%@ page import="com.boco.eoms.sparepart.model.*"%>
<%@ page import ="com.boco.eoms.sparepart.util.TawClassMsgTree"%>
<html>
<head>
<%
/*
String abc="";
String tag=(String)request.getAttribute("tag");
if(!tag.equals("tag")){
String a=request.getAttribute("a").toString();
a=StaticMethod.dbNull2String(a);
String b=request.getAttribute("b").toString();
b=StaticMethod.dbNull2String(b);
abc="['"+a+"','"+b+"']";
}
*/
TawClassMsgTree wk_tree = new TawClassMsgTree();
String str = wk_tree.dong("2");
%>
<title>
${eoms:a2u('备件名称维护')}
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<style>
body,select
{
	font-size:9pt;
	font-family:Verdana;
}
select {background-color:#F0F0F0;}
</style>
<script language="JavaScript" src="<%=request.getContextPath()%>/css/area.js"></script>
<SCRIPT LANGUAGE = JavaScript>
var s=["s1","s2"];
var opt0 = ['${eoms:a2u("所属专业")}','${eoms:a2u("网元类型")}'];

var dsy = new Dsy();
<%=str%>
function setup()
{
	for(i=0;i<s.length-1;i++)
		document.getElementById(s[i]).onchange=new Function("change("+(i+1)+")");
	change(0);
}
</SCRIPT>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif" onload="setup()">
<div align="center">
<table border="0" width="75%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
        <b>${eoms:a2u('备件名称维护')}</b>
      </td>
    </tr>
</table>
<br>
<form name="frm" action="../storage/nameview.do" method="post" onsubmit="return checkdata()">
<table border="0" width="75%" cellspacing="1" cellpadding="1" class="table_show" align=center>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.department"/>：</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                  <select name="department" id="s1" style="width: 6.8cm;"></select>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.necode"/>：</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="necode" id="s2" style="width: 6.8cm;"></select>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.objectname"/>：</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <input name="objectname" type="text"size="35">
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.note"/>：</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <input name="note" type="text"size="35"> ${eoms:a2u('(备件名称编码)')}
                  </td>
    </tr>
</table>
<INPUT TYPE="hidden" name="differ" >
<table border="0" width="75%" cellspacing="0">
    <tr>
       <td height="32" align="right">
          <input type="submit" value="<bean:message key="label.query"/>" name="reset" onclick="frm.differ.value=2" class="clsbtn2">
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
        if ( document.frm.department.value == '${eoms:a2u("所属专业")}' ) {
                alert( '${eoms:a2u("请选择备件所属专业！")}');
                document.frm.department.focus();
                return false;
        }
        if ( document.frm.necode.value == '${eoms:a2u("网元类型")}' ) {
                alert('${eoms:a2u("请选择备件网元类型！")}');
                document.frm.necode.focus();
                return false;
        }
       return true;
}
</script>



