
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.List,com.boco.eoms.sparepart.model.TawClassMsg,com.boco.eoms.common.util.StaticMethod"%>
<%@ page import ="com.boco.eoms.sparepart.util.TawClassMsgTree"%>
<html>
<head> 
<%
 String str =(String)request.getAttribute("StringTree");
%>
<title>
<bean:message key="order.term"/>
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
var s=["s1","s2","s3","s4"];
var opt0 = ["","","",""];

var dsy = new Dsy();
<%=str%>

function setup()
{
	for(i=0;i<s.length-1;i++)
		document.getElementById(s[i]).onchange=new Function("change(dsy,"+(i+1)+",s,opt0)");
	change(dsy,0,s,opt0);
}
</SCRIPT>
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif" onload="setup()">
<div align="center">
<br>
<table border="0" width="75%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
        <b><bean:message key="order.term"/></b>
      </td>
    </tr>
</table>
<form method="post" action="../part/view.do"  name="frm" onsubmit="checkdata()">
<html:hidden property="orderType" name="tawOrderForm" />
<table border="0" width="75%" cellspacing="1" cellpadding="1" align=center>
    <tr class="tr_show_left">
      <td>
        ${eoms:a2u('您当前操作的仓库是：')}<font color="red"><bean:write name="storage_dept_name" scope="session"/></font>&nbsp;&nbsp;${eoms:a2u('的')}<b>&nbsp;&nbsp;<font color="blue"><bean:write name="storage" scope="session"/></font></b>
      </td>
    </tr>
</table>
<table border="0" width="75%" cellspacing="1" cellpadding="1" class="table_show" align=center>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.department"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                  <select name="nettype" id="s1" style="width: 6.8cm;"></select>
                  </td>
    </tr>
	  <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('备件所属小专业：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="subdept" id="s2" style="width: 6.8cm;"></select>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.necode"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="necode" id="s3" style="width: 6.8cm;"></select>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('备件类型：')}</p>
                  </td>
                 <td width="70%" height="25" colspan="2">
                   <select name="objecttype" id="s4" style="width: 6.8cm;"></select>
                 </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.supplier"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="supplier" size="1" style="width: 6.8cm;">
                           <option value=""><bean:message key="label.selall"/></option>
                           <logic:iterate id="supplier" name="supplier" type="com.boco.eoms.sparepart.model.TawClassMsg">
                           <option value="<bean:write name="supplier" property="id" scope="page"/>"><bean:write name="supplier" property="cname" scope="page"/></option>
                           </logic:iterate>
                    </select>
                  </td>
    </tr>
  
     <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.state"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="state" size="1" style="width: 4.4cm;">
                           <option value=""><bean:message key="label.selall"/></option>
                           <option value="11">${eoms:a2u('新件入库')}</option>          
                           <option value="12">${eoms:a2u('维护入库')}</option>
                           <option value="19">${eoms:a2u('其他入库')}</option>
                           <option value="14">${eoms:a2u('维护出库')}</option>
                           <option value="13">${eoms:a2u('维修出库')}</option>
                           <option value="16">${eoms:a2u('扩容出库')}</option>            
                           <option value="15">${eoms:a2u('报废出库')}</option>           
                           <option value="17">${eoms:a2u('其他出库')}</option>
                        </select>
                     </td>
    </tr>
    
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.objectname"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text"size="35"  maxlength="50" name="objectname">
                    </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.serialno"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text"size="35"  maxlength="50" name="serialno">
                    </td>
    </tr>
<!--    <tr class="tr_show">state
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.managecode"/>：</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text"size="35" value=""  maxlength="50" name="managecode">
                 </td>
    </tr>-->
    <%--
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25"><bean:message key="sparepart.operator"/>：</td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" name="operator"size="35"  maxlength="255" >
                  </td>
    </tr>
    --%>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('所属工程/合同：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" size="35" value=""  maxlength="1024" name="contract">
                  </td>
    </tr>
<!--    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.timein"/>：</p>
                  </td>
                   <td width="70%" height="25" colspan="2">
                    <app:SelectDate name="timein" formName="frm" value="<%=StaticMethod.getLocalString() %>"/>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.timeend"/>：</p>
                  </td>
                   <td width="70%" height="25" colspan="2">
                    <app:SelectDate name="timeend" formName="frm" value="<%=StaticMethod.getLocalString() %>"/>
                  </td>
    </tr>  -->
</table>
<script language="javascript">
function checkdata() {

      
}
</script>
<table border="0" width="75%" cellspacing="0">
    <tr>
     <td height="32" align="right">
          <input type="submit" value="<bean:message key="label.query"/>" name="submit" class="clsbtn2">
          &nbsp;&nbsp;
      </td>
    </tr>
</table>
</form>
</div>
</body>
<%@ include file="/common/footer_eoms.jsp"%>

</html>
