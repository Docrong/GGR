
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import ="com.boco.eoms.sparepart.util.TawClassMsgTree"%>
<%@ page import ="com.boco.eoms.db.util.ConnectionPool"%>
<%@ page import ="com.boco.eoms.sparepart.bo.TawStorageBO"%>
<%@ page import ="com.boco.eoms.sparepart.controller.TawSparepartForm"%>
<%@ page import ="javax.servlet.http.HttpServletRequest"%>
<%@ page import="java.util.List,com.boco.eoms.sparepart.model.TawClassMsg,com.boco.eoms.common.util.StaticMethod"%>
<html>
<head>
<%
 TawSparepartForm sparepartForm=(TawSparepartForm)request.getAttribute("sparepart");
 String str =(String)request.getAttribute("StringTree");
 List templist =(List) request.getAttribute("supplier");
 TawClassMsg tawClassMsg = null;
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
var s=["s1","s2","s3","s4"];
var opt0 = ["<%=StaticMethod.null2String(sparepartForm.getDepartment())%>","<%=StaticMethod.null2String(sparepartForm.getNecode())%>","<%=StaticMethod.null2String(sparepartForm.getObjecttype())%>"];
var dsy = new Dsy();
<%=str%>
function setup()
{
	for(i=0;i<s.length-1;i++)
		document.getElementById(s[i]).onchange=new Function("change(dsy,"+(i+1)+",s,opt0)");
	change(dsy,0,s,opt0);
}
function changeType(v){
  document.getElementById("sheettype").style.display = (v==11 || v==12) ? "none" : "block";
  document.getElementById("accessorytype").style.display = (v==11 || v==12) ? "none" : "block";
}
function setState(){
  <%
  int tempState = StaticMethod.null2int(sparepartForm.getState());
  switch (tempState)
  {
    case 0:
    case 11:
    %>
    document.getElementById("radio0").checked="checked";
    <%
    break;
    case 12:
    %>
    document.getElementById("radio1").checked="checked";
    <%
    break;
    case 19:
    %>
    document.getElementById("radio2").checked="checked";
    changeType(19);
    <%
  }
    %>
}
</SCRIPT>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif" onload="setup(),setState()">
<div align="center">
<br>
<form method="post" action="../part/add.do" name="frm" enctype="multipart/form-data">
<table border="0" width="80%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
        <b>${eoms:a2u('填写入库备件信息')}</b>
      </td>
    </tr>
</table>
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
        <font color="#CC0000">${eoms:a2u('注意：带有 ** 号的栏目是必须填写的，其他的栏目可以不填。')}</font>
      </td>
    </tr>
   <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('备件所属于大专业类型：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                  <select name="department" id="s1" style="width: 6.8cm;"></select>
                  <font color="#FF0000">**</font>
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('备件所属小专业类型：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="subDept" id="s2" style="width: 6.8cm;"></select>
                   <font color="#FF0000">**</font>
                  </td>
    </tr>
     <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('网元类型：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="necode" id="s3" style="width: 6.8cm;"></select>
                   <font color="#FF0000">**</font>
                  </td>
    </tr>
	  <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('名称：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   <select name="objecttype" id="s4" style="width: 6.8cm;"></select>
                   <font color="#FF0000">**</font>
                  </td>
    </tr>
   <%-- <tr class="tr_show" style="displan:none">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">实物名称：</p>
                    
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text"size="35"  maxlength="40"  value="<%=StaticMethod.null2String(sparepartForm.getEname())%>"  name="ename">
                    <font color="#FF0000">**</font>
                  </td>
    </tr>--%>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('设备型号：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" size="35" value="<%=StaticMethod.null2String(sparepartForm.getObjtype())%>" name="objtype" >
                  </td>
    </tr>	 	 
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('资产条形码：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text"size="35" value="<%=StaticMethod.null2String(sparepartForm.getManagecode())%>" name="managecode">
                    <font color="#FF0000">**</font></td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">${eoms:a2u('版本号：')}</td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" value="<%=StaticMethod.null2String(sparepartForm.getVersion())%>" name="version"size="35"  maxlength="40">
                    
                  </td>
    </tr>	 
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">${eoms:a2u('序列号：')}</td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" value="<%=StaticMethod.null2String(sparepartForm.getSerialno())%>" name="serialno"size="35"  maxlength="40">
                     
                  </td>
    </tr>


   <tr class="tr_show" style="display:none">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('入库状态：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                  <input id="radio0" type="radio" value="11" name="state" onclick="changeType(this.value);" />${eoms:a2u('新件')}
                  <input id="radio1" type="radio" value="12" name="state" onclick="changeType(this.value);" />${eoms:a2u('故障')}
                  <input id="radio2" type="radio" value="19" name="state" onclick="changeType(this.value);" />${eoms:a2u('调拨')}
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('性能状态：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   		<select name="proform" size="1" style="FONT-SIZE: 8pt" style="width: 6.8cm;">
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
                   		<select name="warrantyFlag" size="1" style="FONT-SIZE: 8pt" style="width: 6.8cm;">
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
                    <input type="text" size="35" value="<%=StaticMethod.null2String(sparepartForm.getRepair_endtime())%>" name="repair_endtime" >
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('保修时间：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" size="35" value="<%=StaticMethod.null2String(sparepartForm.getRepairtime())%>" name="repairtime" >
                  </td>
    </tr>	 
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('是否停产：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                   		<select name="stopproductFlag" size="1" style="FONT-SIZE: 8pt" style="width: 6.8cm;">
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
						  <select name="company" size="1" style="width: 6.8cm;">
                     
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
                    <input type="text" size="35" value="<%=StaticMethod.null2String(sparepartForm.getContract())%>" name="contract"  maxlength="40">
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('订购金额：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" size="35" value="<%=StaticMethod.null2String(sparepartForm.getMoney())%>" name="money"  maxlength="40">
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('单位：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" size="35" value="<%=StaticMethod.null2String(sparepartForm.getUnits())%>" name="units"  maxlength="40">
                  </td>
    </tr>	 

    <tr class="tr_show" id="sheettype"  style="display:none">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('工单号：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <%--
                    <input type="text" size="35" value="<%=StaticMethod.null2String(sparepartForm.getSheetid())%>" name="sheetid" readonly="readonly" />
										<input type="submit" name="toquery" value="查询" onclick=document.frm.action="../../newworksheet/part/accessory.do" />
										--%>
                    <input type="text" size="35" value="<%=StaticMethod.null2String(sparepartForm.getSheetid())%>" name="sheetid"  maxlength="40"/>
                  </td>
    </tr>
    <tr class="tr_show" id="accessorytype">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('附件：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="file" size="35" value="<%=StaticMethod.null2String(sparepartForm.getAccessory())%>" name="theFile">
                  </td>
    </tr>
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('物资存放位置：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <input type="text" size="35" value="<%=StaticMethod.null2String(sparepartForm.getPosition())%>" name="position" >
						  <font color="#FF0000">**</font>
                  </td>
    </tr>




    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.supplier"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                        <select name="supplier" size="1" style="FONT-SIZE: 8pt" style="width: 6.8cm;">
                          <%
                             for (int i = 0; i < templist.size(); i++) {
 								                 tawClassMsg = (TawClassMsg)templist.get(i);
                            if(tawClassMsg.getId() == StaticMethod.null2int(sparepartForm.getSupplier())) {
                          %>
                          	<option value="<%=tawClassMsg.getId()%>" selected="selected"><%=tawClassMsg.getCname()%></option>
                            <%} else {%>
                            <option value="<%=tawClassMsg.getId()%>"><%=tawClassMsg.getCname()%></option>
                            <%
                            }
                            }
                            %>
                        </select>
                        <font color="#FF0000">**</font>
                  </td>
    </tr>    
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0">${eoms:a2u('生产厂商：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                    <%-- <input type="text" size="35" value="<%=StaticMethod.null2String(sparepartForm.getFixe())%>" name="fixe" >--%>
						  <select name="fixe" size="1" style="width: 6.8cm;">
                     
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
                  <input type="text" size="35" value=""  maxlength="60"  name="describe">
						
                  </td>
    </tr>	  
    <tr class="tr_show">
                  <td class="clsfth" width="30%" height="25">
                    <p style="margin-top: 0; margin-bottom: 0"><bean:message key="sparepart.note"/>${eoms:a2u('：')}</p>
                  </td>
                  <td width="70%" height="25" colspan="2">
                  <input type="text" size="35" value="<%=StaticMethod.null2String(sparepartForm.getNote())%>"  maxlength="60"  name="note">
						
                  </td>
    </tr>
</table>
<table border="0" width="80%" cellspacing="0">
    <tr>
      <td height="32" align="right">
          <input type="submit" value="${eoms:a2u('提交审核')}" name="submit" onclick="return checkdata()" class="clsbtn2">
          &nbsp;&nbsp;
  <!--        <input type="submit" value="<bean:message key="label.resubmit"/>" name="reset" onclick="frm.differ.value=2" class="clsbtn2"> -->

      </td>
    </tr>
</table>

<script language="javascript">
changeType(11);
function checkdata() {
        if ( document.frm.department.value == "" ) {
                alert('${eoms:a2u("请选择备件所属大专业！")}');
                document.frm.department.focus();
                return false;
        }
        if ( document.frm.subDept.value == "" ) {
                alert('${eoms:a2u("请选择备件所属小专业！")}');
                document.frm.subDept.focus();
                return false;
        }
        
        if ( document.frm.necode.value == "" ) {
                alert('${eoms:a2u("请选择备件网元类型！")}');
                document.frm.necode.focus();
                return false;
        }
        if ( document.frm.objecttype.value == "" ) {
                alert('${eoms:a2u("请选择备件类型！")}');
                document.frm.objecttype.focus();
                return false;
        }
         if ( document.frm.ename.value == "" ) {
                alert('${eoms:a2u("请输入实物名称！")}');
                document.frm.ename.focus();
                return false;
        }
        if ( document.frm.supplier.value == "" ) {
                alert('${eoms:a2u("请选择供货商！")}');
                document.frm.supplier.focus();
                return false;
        }
        if ( document.frm.managecode.value == "" ) {
                alert('${eoms:a2u("请输入备件资产条形码：！")}');
                document.frm.managecode.focus();
                return false;
        }
        if ( document.frm.position.value == "" ) {
                alert('${eoms:a2u("请输入存放位置！")}');
                document.frm.position.focus();
                return false;
        }
        if ( document.frm.position.value == "" ) {
                alert('${eoms:a2u("请输入存放位置！")}');
                document.frm.position.focus();
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


