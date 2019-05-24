<%@ page contentType="text/html; charset=GB2312" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/jstl-core.tld" prefix="c" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="app" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="eoms" %>
<%@ page import="java.util.List,com.boco.eoms.sparepart.model.TawClassMsg,com.boco.eoms.common.util.StaticMethod"%>
<%@ page import ="com.boco.eoms.sparepart.util.TawClassMsgTree"%>
<html>
<head>
<%
 String str =(String)request.getAttribute("StringTree");
%>
<title>
<bean:message key="storage.stat"/>
</title>
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
var s=["s1","s2","s3","s4"];
var opt0 = ['${eoms:a2u("全选")}','${eoms:a2u("全选")}','${eoms:a2u("全选")}','${eoms:a2u("全选")}'];
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
<script language="javascript" src="<%=request.getContextPath()%>/css/table_calendar.js"></script>
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif" onload="setup()">
<div align="center">
<br>
<table border="0" width="75%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
        <b><bean:message key="storage.stat"/></b>
      </td>
    </tr>
</table>
<form action="../query/statview.do" method="post" name="frm" onsubmit="checkdata()">
	<SCRIPT language=javascript>
<!--
//以下四行用于日历显示
var outObject;
var old_dd = null;
writeCalender();
bltSetDay(bltTheYear,bltTheMonth);
//-->
</SCRIPT>
<table border="0" width="75%" cellspacing="1" cellpadding="1" class="table_show" align=center>
  <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.department"/></p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                  <select name="nettype" id="s1" style="width: 4.4cm;"></select>
                  </td>
    </tr>
	  <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('小专业')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                  <select name="subdept" id="s2" style="width: 4.4cm;"></select>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.necode"/></p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="necode" id="s3" style="width: 4.4cm;"></select>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.objectname"/></p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                     <select name="objectname" id="s4" style="width: 4.4cm;"></select>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="storage.name"/></p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="storage" size="1"  style="width: 4.4cm;">
                            <option value=""><bean:message key="label.selall"/></option>
            <logic:iterate id="storage" name="storage" type="com.boco.eoms.sparepart.model.TawStorage">
                           <option value="<bean:write name="storage" property="id" scope="page"/>"><bean:write name="storage" property="storagename" scope="page"/></option>
            </logic:iterate>
                        </select>
                     </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.state"/></p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="state" size="1"  style="width: 4.4cm;">
                            <option value=""><bean:message key="label.selall"/></option>
            <logic:iterate id="state" name="state" type="com.boco.eoms.sparepart.model.TawClassMsg">
                           <option value="<bean:write name="state" property="id" scope="page"/>"><bean:write name="state" property="cname" scope="page"/></option>
            </logic:iterate>
                        </select>
                     </td>
    </tr>
	 <tr class="tr_show">
      <td class="clsfth" width="30%" height="25">
        <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('入库开始时间')}</p>
      </td>
      <td width="70%" height="25" colspan="2">        
		  <html:text property="startintime" size="20" value="" onfocus="setday(this)" readonly="true"/>
      </td>
    </tr>
    <tr class="tr_show">
      <td class="clsfth" width="30%" height="25">
        <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('入库结束时间')}</p>
      </td>
      <td width="70%" height="25" colspan="2">
      	<html:text property="endintime" size="20" value="" onfocus="setday(this)" readonly="true"/>        
      </td>
    </tr>
    <!--
    <tr class="tr_show">
      <td class="clsfth" width="30%" height="25">
        <p style="margin-top: 0; margin-bottom: 0">出库开始时间</p>
      </td>
      <td width="70%" height="25" colspan="2">       
        <html:text property="startouttime" size="20" value="" onfocus="setday(this)" readonly="true"/> 
      </td>
    </tr>
    <tr class="tr_show">
      <td class="clsfth" width="30%" height="25">
        <p style="margin-top: 0; margin-bottom: 0">出库结束时间</p>
      </td>
      <td width="70%" height="25" colspan="2">
      	<html:text property="endouttime" size="20" value="" onfocus="setday(this)" readonly="true"/>        
      </td>
    </tr>
    -->
    <tr class="tr_show">
      <td class="clsfth" width="30%" height="25">
        <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('设备厂商')}</p>
      </td>
      <td width="70%" height="25" colspan="2">
        <input type="text"size="22"  maxlength="50" name="fixe">
      </td>
    </tr>
    <tr class="tr_show">
      <td class="clsfth" width="30%" height="25">
        <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('所属公司')}</p>
      </td>
      <td width="70%" height="25" colspan="2">
        <input type="text"size="22"  maxlength="50" name="company">
      </td>
    </tr>
<!--     <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.timein"/></p>
                  </td>
                   <td width="70%" height="25" colspan="2">
                    <app:SelectDate name="timein" formName="frm" value="<%=StaticMethod.getLocalString() %>"/>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.timeend"/></p>
                  </td>
                   <td width="70%" height="25" colspan="2">
                    <app:SelectDate name="timeend" formName="frm" value="<%=StaticMethod.getLocalString() %>"/>
                  </td>
    </tr>-->
</table>
<script language="javascript">
function checkdata() {

        if ( document.frm.nettype.value == '${eoms:a2u("全选")}' ) {
                document.frm.nettype.value="";
        }
		 if ( document.frm.subdept.value == '${eoms:a2u("全选")}' ) {
                 document.frm.subdept.value="";
        }
        if ( document.frm.necode.value == '${eoms:a2u("全选")}' ) {
                 document.frm.necode.value="";
        }

        if ( document.frm.objectname.value == '${eoms:a2u("全选")}' ) {
                  document.frm.objectname.value="";
        }
}
</script>
<table border="0" width="75%" cellspacing="0">
    <tr>
      <td height="32" align="right">
          <input type="submit" value="<bean:message key="label.query"/>" name="statterm" class="clsbtn2">
          &nbsp;&nbsp;
      </td>
    </tr>
</table>
</form>
</div>
</body>
</html>
