
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.ArrayList,java.util.List,com.boco.eoms.common.util.StaticMethod" %>
<%@ page import="com.boco.eoms.sparepart.model.*"%>
<%@ page import ="com.boco.eoms.sparepart.util.TawClassMsgTree"%>
<html>
<head>
<%
 String str =(String)request.getAttribute("StringTree");
%>
<title>
<bean:message key="remind.setpart"/>
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
var s=["s1","s2","s3"];
var opt0 = ['${eoms:a2u("所属专业")}','${eoms:a2u("网元类型")}','${eoms:a2u("备件类型")}'];
var dsy = new Dsy();
<%=str%>

function setup()
{
	for(i=0;i<s.length-1;i++)
		document.getElementById(s[i]).onchange=new Function("change(dsy,"+(i+1)+",s,opt0)");
	change(dsy,0,s,opt0);
}
</SCRIPT>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif" onload="setup()">
<div align="center">
<br>
<table border="0" width="95%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
        <b><bean:message key="remind.setpart"/></b>
      </td>
    </tr>
</table>
<br>
<form name="frm" action="../stock/overpart.do" method="post" onsubmit="return checkdata()">
<table border="0" width="75%" cellspacing="1" cellpadding="1" class="table_show" align=center>
  <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="storage.name"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="storageid" size="1" style="width: 6.8cm;">
                            <option value=""><bean:message key="label.choose"/></option>
            <logic:iterate id="storage" name="storage" type="com.boco.eoms.sparepart.model.TawStorage">
                           <option value="<bean:write name="storage" property="id" scope="page"/>"><bean:write name="storage" property="storagename" scope="page"/></option>
            </logic:iterate>
                        </select>
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
                   <select name="objectname" id="s3" style="width: 6.8cm;"></select>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25"><bean:message key="remind.upperlimit"/>${eoms:a2u('：')}</td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" name="upperlimit"size="35" value=""  maxlength="255" ><font color="#FF0000">**</font>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25"><bean:message key="remind.lowerlimit"/>${eoms:a2u('：')}</td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" name="lowerlimit"size="35" value=""  maxlength="255" ><font color="#FF0000">**</font>
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
function IsDigit(cCheck)
	{
	return (('0'<=cCheck) && (cCheck<='9'));
	}

function IsNumber(str)
{
	for (var nIndex=0; nIndex<str.length; nIndex++)
		{
		cCheck = str.charAt(nIndex);
		if (!(IsDigit(cCheck) || cCheck=='.'))
			{
			return false;
			}
		}
   return true;
}
function checkdata() {
        if ( document.frm.storageid.value == "" ) {
                alert('${eoms:a2u("请选择仓库！")}');
                document.frm.storageid.focus();
                return false;
        }
        if ( document.frm.department.value == '${eoms:a2u("所属专业")}' ) {
                alert('${eoms:a2u("请选择备件所属专业！")}');
                document.frm.department.focus();
                return false;
        }
        if ( document.frm.necode.value == '${eoms:a2u("网元类型")}' ) {
                alert('${eoms:a2u("请选择备件网元类型！")}');
                document.frm.necode.focus();
                return false;
        }

        if ( document.frm.objectname.value == '${eoms:a2u("备件类型")}' ) {
                alert('${eoms:a2u("备件类型！")}');
                document.frm.objectname.focus();
                return false;
        }
       if ( document.frm.upperlimit.value == "" ) {
                alert('${eoms:a2u("请输入库存备件上限！")}');
                document.frm.upperlimit.focus();
                return false;
        }else{
           if(!IsNumber(document.frm.upperlimit.value))
           {
              alert('${eoms:a2u("库存备件上限必须为数字!")}');
              document.frm.upperlimit.focus();
              return false;
           }
        }
       if ( document.frm.lowerlimit.value == "" ) {
                alert('${eoms:a2u("请输入库存备件下限！")}');
                document.frm.lowerlimit.focus();
                return false;
        }
        else{
           if(!IsNumber(document.frm.lowerlimit.value))
           {
              alert('${eoms:a2u("库存备件下限必须为数字!")}');
              document.frm.lowerlimit.focus();
              return false;
           }
        }

       return true;
}
</script>



