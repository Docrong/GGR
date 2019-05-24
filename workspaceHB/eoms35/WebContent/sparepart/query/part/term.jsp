<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.List,com.boco.eoms.sparepart.model.TawClassMsg,com.boco.eoms.common.util.StaticMethod"%>
<%@ page import ="com.boco.eoms.sparepart.util.TawClassMsgTree"%>

<%
 String partTypeStr =(String)request.getAttribute("StringTree");
 String storageStr =(String)request.getAttribute("StorageTree");
%>
<title>
${eoms:a2u('备件查询')}
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

var t=["t1","t2"];
var opt1=['${eoms:a2u("全选")}','${eoms:a2u("全选")}'];
var dsyStorageTree = new Dsy();

<%=partTypeStr%>
<%=storageStr%>
function setup()
{
	for(i=0;i<s.length-1;i++)
		document.getElementById(s[i]).onchange=new Function("change(dsy,"+(i+1)+",s,opt0)");	
	change(dsy,0,s,opt0);
	for(i=0;i<t.length-1;i++)
		document.getElementById(t[i]).onchange=new Function("change1(dsyStorageTree,"+(i+1)+",t,opt1)");
	change1(dsyStorageTree,0,t,opt1);
}
</SCRIPT>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif" onload="setup()">
<div align="center">
<br>
<table border="0" width="75%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
        <b>${eoms:a2u('备件查询')}</b>
      </td>
    </tr>
</table>
<form action="../query/view.do" method="post" name="frm" onsubmit="checkdata()">
<table border="0" width="75%" cellspacing="1" cellpadding="1" class="table_show" align=center>
  	<tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('所属大专业：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                  <select name="nettype" id="s1" style="width: 4.4cm;"></select>
                  </td>
    </tr>
	<tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('所属小专业：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                  <select name="subdept" id="s2" style="width: 4.4cm;"></select>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.necode"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="necode" id="s3" style="width: 4.4cm;"></select>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><%--<bean:message key="sparepart.objecttype"/>--%>${eoms:a2u('名称：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="objecttype" id="s4" style="width: 4.4cm;"></select>
                  </td>
    </tr> 
   
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">${eoms:a2u('分公司名称：')}</td>
                  <td width="70%" height="25" colspan="2">
                  <select name="deptName" id="t1" style="width: 4.4cm;"></select>
                   </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('仓库名称：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                  <select name="storagename" id="t2" style="width:4.4cm;"></select>
                  </td>
    </tr>

    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.state"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="state" size="1" style="width: 4.4cm;">
                            <option value=""><bean:message key="label.selall"/></option>
            <logic:iterate id="state" name="state" type="com.boco.eoms.sparepart.model.TawClassMsg">
                           <option value="<bean:write name="state" property="id" scope="page"/>"><bean:write name="state" property="cname" scope="page"/></option>
            </logic:iterate>
                        </select>
                     </td>
    </tr>
 <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.supplier"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="supplier" size="1" style="width: 4.4cm;">
                            <option value=""><bean:message key="label.selall"/></option>
<logic:iterate id="supplier" name="supplier" type="com.boco.eoms.sparepart.model.TawClassMsg">
                           <option value="<bean:write name="supplier" property="id" scope="page"/>"><bean:write name="supplier" property="cname" scope="page"/></option>
</logic:iterate>
                        </select>
                     </td>
    </tr>
    <tr class="tr_show" style="display:none">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.objectname"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text"size="22"  maxlength="50" name="objectname">
                    </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.serialno"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text"size="22"  maxlength="50" name="serialno">
                    </td>
    </tr>
    <tr class="tr_show">
      <td class="clsfth" width="30%" height="25">
        <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('所属工程：')}</p>
      </td>
      <td width="70%" height="25" colspan="2">
        <input type="text" size="22" value=""  maxlength="1024" name="contract">
      </td>
    </tr>
    <tr class="tr_show">
      <td class="clsfth" width="30%" height="25">
        <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('入库开始时间：')}</p>
      </td>
      <td width="70%" height="25" colspan="2">
        <eoms:SelectDate name="startintime" value="2001-01-01" formName="frm"/>
      </td>
    </tr>
    <tr class="tr_show">
      <td class="clsfth" width="30%" height="25">
        <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('入库结束时间：')}</p>
      </td>
      <td width="70%" height="25" colspan="2">
        <eoms:SelectDate name="endintime" formName="frm"/>
      </td>
    </tr>

</table>
<script language="javascript">
function checkdata() {

        if ( document.frm.nettype.value == '${eoms:a2u("全选")}' ) {
                document.frm.nettype.value="";
        }
        if ( document.frm.necode.value == '${eoms:a2u("全选")}' ) {
                 document.frm.necode.value="";
        }
		if ( document.frm.subdept.value == '${eoms:a2u("全选")}' ) {
                 document.frm.subdept.value="";
        }

        if ( document.frm.objectname.value == '${eoms:a2u("全选")}' ) {
                  document.frm.objectname.value="";
        }
}
</script>
<table border="0" width="75%" cellspacing="0">
    <tr>
      <td height="32" align="right">
          <input type="submit" value="<bean:message key="label.query"/>" name="submit" class="clsbtn2">
          &nbsp;&nbsp;
          &nbsp;&nbsp;
      </td>
    </tr>
</table>
</form>
</div>
</body>
</html>
