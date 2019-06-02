
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="com.boco.eoms.sparepart.util.TawClassMsgTree"%>
<%@ page import="java.util.List,com.boco.eoms.sparepart.model.*,com.boco.eoms.common.util.StaticMethod"%>
<html>
<%
  List sparepart=(List)request.getAttribute("sparepart");
  TawPart part=(TawPart)sparepart.get(0);
  
  String a=part.getNettype();
  String b=part.getSubdept();
  String c=part.getNecode();
  String d=part.getObjecttype();
  String abc="['"+a+"','"+b+"','"+c+"','"+d+"']";
 
  String str =(String)request.getAttribute("StringTree");
%>
<head>
<title>
<bean:message key="sparepart.update"/>
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

var s=["s1","s2","s3","s4"];
var opt0 =<%=abc%>;

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
<table border="0" width="80%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
        <b><bean:message key="sparepart.update"/></b>
      </td>
    </tr>
</table>
<form action="../part/updateover.do" method="post" name="frm" onsubmit="return checkdata()">
<INPUT TYPE="hidden" name="id" value="<%=part.getId()%>">
<table border="0" width="80%" cellspacing="1" cellpadding="1" align=center>
    <tr class="tr_show_left">
      <td>
        ${eoms:a2u('您当前操作的仓库是：')}<font color="red"><bean:write name="storage_dept_name" scope="session"/></font>&nbsp;&nbsp;${eoms:a2u('的')}<b>&nbsp;&nbsp;<font color="blue"><bean:write name="storage" scope="session"/></font></b>
      </td>
    </tr>
</table>
<table border="0" width="80%" cellspacing="1" cellpadding="1" class="table_show" align=center>
    <tr class="tr_show">
      <td class="clsfth" colspan="3" height="25" align="left">
        <font color="#CC0000">${eoms:a2u('注意：带有 **不可修改。')}</font>
      </td>
    </tr>
<tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('备件所属于大专业类型：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <select name="department" id="s1" style="width: 6.8cm;"></select>
                  </td>
    </tr>
	 <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('备件所属小专业类型：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="subDept" id="s2" style="width: 6.8cm;"></select>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('网元类型：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <select name="necode" id="s3" style="width: 6.8cm;"></select>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('名称：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="objectname" id="s4" style="width: 6.8cm;"></select>
                  </td>
    </tr>
	 <!--
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.objectname"/>：</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" name="ename"size="35" value="<%=part.getObjectname()%>"  maxlength="50" readonly>
                   <font color="#FF0000">**</font></td>
    </tr>
	 -->
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('设备型号：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" size="35" value="<%=part.getObjtype()%>" name="objtype" >
                  </td>
    </tr>	 	 
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('资产条形码：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text"size="35" value="<%=part.getManagecode()%>" name="managecode">
                    <font color="#FF0000">**</font></td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">${eoms:a2u('版本号：')}</td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" value="<%=part.getVersion()%>" name="version" size="35"  maxlength="40">
                    
                  </td>
    </tr>	 
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">${eoms:a2u('序列号：')}</td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" value="<%=part.getSerialno()%>" name="serialno"size="35"  maxlength="40">
                     
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('性能状态：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   		<select name="proform" size="1" value="<%=part.getProformFlag()%>" style="FONT-SIZE: 8pt" style="width: 6.8cm;">
                            <option value="1">${eoms:a2u('完好')}</option>
                            <option value="2">${eoms:a2u('修复可用')}</option>
							<option value="3">${eoms:a2u('拟报废')}</option>
                      </select>
                  </td>
    </tr> 
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('是否保修：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   		<select name="warrantyFlag" size="1" value="<%=part.getWarrantyFlag()%>" style="FONT-SIZE: 8pt" style="width: 6.8cm;">
                            <option value="1">${eoms:a2u('保修内')}</option>
                            <option value="2">${eoms:a2u('保修外')}</option>
                      </select>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('保修到期时间：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" size="35" value="<%=part.getRepair_endtime()%>" name="repair_endtime" >
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('保修时间：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" size="35" value="<%=part.getRepairtime()%>" name="repairtime" >
                  </td>
    </tr>	 
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('是否停产：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   		<select name="stopproductFlag" size="1" value="<%=part.getStopproductFlag()%>" style="FONT-SIZE: 8pt" style="width: 6.8cm;">
                            <option value="1">${eoms:a2u('未停产')}</option>
                            <option value="2">${eoms:a2u('已停产')}</option>
                      </select>
                  </td>
    </tr>	 
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('物资所属公司：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <%--  <input type="text" size="35" value="<%=StaticMethod.null2String(sparepartForm.getCompany())%>" name="company" >--%>
						  <select name="company" size="1" value="<%=part.getCompanyid()%>" style="width: 6.8cm;">
                     
                      <logic:iterate id="company" name="company" type="com.boco.eoms.sparepart.model.TawClassMsg">
                       <option value="<bean:write name="company" property="id" scope="page"/>"><bean:write name="company" property="cname" scope="page"/></option>
                      </logic:iterate>
                    </select>
                  </td>
    </tr>	 
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('所属工程/合同：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" size="35" value="<%=part.getContract()%>" name="contract"  maxlength="40">
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('订购金额：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" size="35" value="<%=part.getMoney()%>" name="money"  maxlength="40">
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('单位：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" size="35" value="<%=part.getUnits()%>" name="units"  maxlength="40">
                  </td>
    </tr>	
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('物资存放位置：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" size="35" value="<%=part.getPosition()%>" name="position" >
						  <font color="#FF0000">**</font>
                  </td>
    </tr>




    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.supplier"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                        <select name="supplier" size="1"  value="<%=part.getSupplierid()%>" style="FONT-SIZE: 8pt" style="width: 6.8cm;">
								
                     <option value="<%=part.getSupplierid()%>"><%=part.getSupplier()%></option>
                      <logic:iterate id="supplier" name="supplier" type="com.boco.eoms.sparepart.model.TawClassMsg">
                       <option value="<bean:write name="supplier" property="id" scope="page"/>"><bean:write name="supplier" property="cname" scope="page"/></option>
                      </logic:iterate>
                        </select>
                        <font color="#FF0000">**</font>
                  </td>
    </tr>    
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('生产厂商：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <%-- <input type="text" size="35" value="<%=part.getFixe()%>" name="fixe" >--%>
						  <select name="fixe" size="1" style="width: 6.8cm;">
                     <option value="<%=part.getFixeid()%>"><%=part.getFixe()%></option>
                      <logic:iterate id="fixe" name="fixe" type="com.boco.eoms.sparepart.model.TawClassMsg">
                       <option value="<bean:write name="fixe" property="id" scope="page"/>"><bean:write name="fixe" property="cname" scope="page"/></option>
                      </logic:iterate>
                    </select>						  
                  </td>
    </tr>
    <tr class="tr_show" style="display:none">
                  <td class="clsfth" width="30%" height="25"><bean:message key="sparepart.operator"/>${eoms:a2u('：')}</td>
                  <td width="70%" height="25" colspan="2">
                   <%-- <input size="35" readonly="readonly" value="<%=StaticMethod.null2String(sparepartForm.getOperator())%>" name="operator"  maxlength="40" />--%>
						 <input size="35"  value="" name="operator"  maxlength="40" />
                  </td>
    </tr>		
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('主要功能描述：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                  <input type="text" size="35" value="<%=part.getDescribe()%>"  maxlength="60"  name="describe">
						
                  </td>
    </tr>	  
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.note"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                  <input type="text" size="35" value="<%=part.getNote()%>"  maxlength="60"  name="note">
						
                  </td>
    </tr>	 	 

</table>
<table border="0" width="80%" cellspacing="0">
    <tr>
     <td height="32" align="right">
          <input type="submit" value="<bean:message key="label.submit"/>" name="submit" class="clsbtn2">
          &nbsp;&nbsp;
      </td>
    </tr>
  </table>
<script language="javascript">
function checkdata() {
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
                alert('${eoms:a2u("请选择备件类型！")}');
                document.frm.objectname.focus();
                return false;
        }
       if ( document.frm.productcode.value == "" ) {
                alert('${eoms:a2u("请输入产品代码！")}');
                document.frm.productcode.focus();
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

        if ( document.frm.supplier.value == "" ) {
                alert('${eoms:a2u("请选择供货商！")}');
                document.frm.supplier.focus();
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
