
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="com.boco.eoms.sparepart.util.TawClassMsgTree"%>
<%@ page import="java.util.List,com.boco.eoms.sparepart.model.TawClassMsg,com.boco.eoms.common.util.StaticMethod"%>
<html>
<head>
<%
 String str =(String)request.getAttribute("StringTree");
%>
<title>
<bean:message key="sparepart.addnew"/>
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
<script language="JavaScript" src="<%=request.getContextPath()%>/css/area.js"></script>
<SCRIPT LANGUAGE = JavaScript>
var s=["s1","s2","s3"];
var opt0 = ['${eoms:a2u("备件所属专业")}','${eoms:a2u("网元类型")}','${eoms:a2u("备件类型")}'];
var dsy = new Dsy();
<%=str%>
function setup()
{
	for(i=0;i<s.length-1;i++)
		document.getElementById(s[i]).onchange=new Function("change(dsy,"+(i+1)+",s,opt0)");
	change(dsy,0,s,opt0);
}
</SCRIPT>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif" onload="setup()">
<div align="center">
<br>
<table border="0" width="80%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
        <b><bean:message key="sparepart.addnew"/></b>
      </td>
    </tr>
</table>
<form method="post" action="../part/add.do" name="frm" onsubmit="return checkdata()">
<table border="0" width="80%" cellspacing="1" cellpadding="1" align=center>
    <tr class="tr_show_left">
      <td>
        ${eoms:a2u('您当前操作的仓库是：')}<b>&nbsp;&nbsp;<bean:write name="storage" scope="session"/></b>
      </td>
    </tr>
</table>
<table border="0" width="80%" cellspacing="1" cellpadding="1" class="table_show" align=center>
    <tr class="tr_show">
      <td class="clsfth" colspan="3" height="25" align="left">
        <font color="#CC0000">${eoms:a2u('注意：带有 ** 号的栏目是必须填写的，其他的栏目可以不填。')}</font>
      </td>
    </tr>
   <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.department"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                  <select name="department" id="s1" style="width: 6.8cm;"></select>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.necode"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="necode" id="s2" style="width: 6.8cm;"></select>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('备件类型：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                     <select name="objecttype" id="s3" style="width: 6.8cm;"></select>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.objectname"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text"size="35" value="<bean:write name='ename' scope='request' filter='false'/>"  maxlength="50" name="ename">
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.supplier"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                        <select name="supplier" size="1" style="width: 6.8cm;">
                            <option value=""><bean:message key="supplier.choose"/></option>
  	                   <logic:iterate id="supplier" name="supplier" type="com.boco.eoms.sparepart.model.TawClassMsg">
                           <option value="<bean:write name="supplier" property="id" scope="page"/>"><bean:write name="supplier" property="cname" scope="page"/></option>
  	                   </logic:iterate>
                        </select>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25"><bean:message key="sparepart.productcode"/>${eoms:a2u('：')}</td>
                  <td width="70%" height="25" colspan="2">
                    <input value="<bean:write name='productcode' scope='request' filter='false'/>"  type="text" name="productcode" size="35"   maxlength="255" >
                   <font color="#FF0000">**</font>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.managecode"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text"size="35" value="<bean:write name='managecode' scope='request' filter='false'/>"  maxlength="50" name="managecode">
                   <font color="#FF0000">**</font></td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25"><bean:message key="sparepart.serialno"/>${eoms:a2u('：')}</td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" value="<bean:write name='serialno' scope='request' filter='false'/>" name="serialno" size="35"   maxlength="255" >
                  <font color="#FF0000">**</font>
                   </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25"><bean:message key="sparepart.operator"/>${eoms:a2u('：')}</td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" value="<bean:write name='operator' scope='request' filter='false'/>" name="operator" size="35"   maxlength="255" >
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('入库状态：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="state" size="1" style="FONT-SIZE: 8pt" style="width: 6.8cm;">
                            <option value="0">${eoms:a2u('选择状态')}</option>
                            <option value="11">${eoms:a2u('新件')}</option>
                            <option value="12">${eoms:a2u('故障')}</option>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('备件性能：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="proform" size="1" style="FONT-SIZE: 8pt" style="width: 6.8cm;">
                            <option value="0">${eoms:a2u('选择状态')}</option>
                            <option value="17">${eoms:a2u('正常')}</option>
                            <option value="18">${eoms:a2u('不正常')}</option>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('所属工程/合同：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" size="35" value=""  maxlength="1024" name="contract">
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('订购金额：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" size="35" value=""  maxlength="1024" name="money">
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('位置：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" size="35" value=""  maxlength="1024" name="position">
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.note"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text"size="35" value="<bean:write name='note' scope='request' filter='false'/>"  maxlength="50" name="note">
                  </td>
    </tr>
</table>
<INPUT TYPE="hidden" name="differ" >
<table border="0" width="80%" cellspacing="0">
    <tr>
      <td height="32" align="right">
          <input type="submit" value="<bean:message key="label.submit"/>" name="submit" onclick="frm.differ.value=1" class="clsbtn2">
          &nbsp;&nbsp;
          <input type="submit" value="<bean:message key="label.resubmit"/>" name="reset" onclick="frm.differ.value=2" class="clsbtn2">
          &nbsp;&nbsp;
      </td>
    </tr>
</table>
<script language="javascript">
function checkdata() {
      if ( document.frm.department.value == '${eoms:a2u("备件所属专业")}' ) {
                alert('${eoms:a2u("请选择备件所属专业！")}');
                document.frm.department.focus();
                return false;
        }
        if ( document.frm.necode.value == '${eoms:a2u("网元类型")}' ) {
                alert('${eoms:a2u("请选择备件网元类型！")}');
                document.frm.necode.focus();
                return false;
        }

        if ( document.frm.objecttype.value == '${eoms:a2u("备件类型")}' ) {
                alert('${eoms:a2u("请选择备件类型！")}');
                document.frm.objecttype.focus();
                return false;
        }
        if ( document.frm.supplier.value == "" ) {
                alert('${eoms:a2u("请选择供货商！")}');
                document.frm.supplier.focus();
                return false;
        }
        if ( document.frm.productcode.value == "" ) {
                alert('${eoms:a2u("请输入版本号！")}');
                document.frm.productcode.focus();
                return false;
        }
        if ( document.frm.managecode.value == "" ) {
                alert('${eoms:a2u("请输入备件管理信息编码！")}');
                document.frm.managecode.focus();
                return false;
        }
        if ( document.frm.managecode.value == "" ) {
                alert('${eoms:a2u("请输入备件管理信息编码！")}');
                document.frm.managecode.focus();
                return false;
        }
       if ( document.frm.serialno.value == "" ) {
                alert('${eoms:a2u("请输入备件序列号！")}');
                document.frm.serialno.focus();
                return false;
        }
       if ( document.frm.state.value == "0" ) {
                alert('${eoms:a2u("请选择备件状态！")}');
                document.frm.state.focus();
                return false;
        }
       if ( document.frm.perform.value == "0" ) {
                alert('${eoms:a2u("请选择备件性能状态！")}');
                document.frm.state.focus();
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


