
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<html>
<head>
<title>
<bean:message key="order.view"/>
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
<script language="javascript">
String.prototype.trim = function()
{
	return this.replace(/(^\s*)|(\s*$)/g,"");
}
function handleid(){
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
	//var frm = document.forms[0];
  var arrServiceIns = document.getElementsByTagName('input');
  for(var i=0;i<arrServiceIns.length;i++){
    if(arrServiceIns[i].name=="servicein"){
 			if(arrServiceIns[i].checked==true){
         var idTemp = arrServiceIns[i].id;
         var serialNoObj = document.getElementById(idTemp+"_serialno");
         var chargeObj = document.getElementById(idTemp+"_charge");
         var chargeValue = chargeObj.value;
         chargeValue = chargeValue.trim();
         var serialNoValue = serialNoObj.value;
         if(chargeValue=="") chargeValue = 0;
         if(!IsNumber(chargeValue))
         {
        		alert('${eoms:a2u("维修费用必须为数字!")}');
            //document.frm.upperlimit.focus();
            return false;
         }else if(chargeValue.length>10){
         		alert('${eoms:a2u("维修费用数字不能超出12位!")}');
           	return false;
         }else{
         		arrServiceIns[i].value = arrServiceIns[i].value + "," + serialNoValue+","+chargeValue;
          }
//    alert(hiddenObj.id+"="+hiddenObj.value);
    	}
  	}
  }

  return true;
}
</script>
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<div align="center">
      <center>
	  <br>
<table border="0" width="95%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
         <b><bean:message key="order.view"/></b>
      </td>
    </tr>
</table>
<br>
<table border="0" width="95%" cellspacing="1" cellpadding="1" align=center>
    <tr class="tr_show_left">
      <td>
        ${eoms:a2u('您当前操作的仓库是：')}<font color="red"><bean:write name="storage_dept_name" scope="session"/></font>&nbsp;&nbsp;${eoms:a2u('的')}<b>&nbsp;&nbsp;<font color="blue"><bean:write name="storage" scope="session"/></font></b>
      </td>
    </tr>
</table>
<form action="../part/serviceover.do" method="post" name="TawSparepartForm" onsubmit="return handleid();">
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align="center">
        <tr class="td_label">
                      <td width="8%">${eoms:a2u('入库')}</td>
                      <td>${eoms:a2u('序列号')}</td>
                      <td >${eoms:a2u('维修费用(元)')}</td>
                      <td ><bean:message key="sparepart.objectname"/></td>
                      <td ><bean:message key="sparepart.supplier"/></td>
                      <td>${eoms:a2u('申请人')}</td>
                      <td>${eoms:a2u('申请部门')}</td>
                      <td>${eoms:a2u('出库时间')}</td>
        </tr>
<%int i=1;%>
    <logic:iterate id="tawOrderDetail" name="tawOrderDetail" type="com.boco.eoms.sparepart.model.TawOrderDetail">
        <tr class="tr_show" align="center">
                      <td>
                      <INPUT TYPE="checkbox" name="servicein" id="<%="a"+i%>" label="<%="b"+i%>"
                        value="<bean:write name="tawOrderDetail" property="sparepartId" scope="page"/>,<bean:write name="tawOrderDetail" property="orderId" scope="page"/>,<bean:write name="tawOrderDetail" property="orgSerialNo" scope="page"/>">
                      </td>
                      <td width="10%">
                      <input type="text" value="<bean:write name="tawOrderDetail" property="serialno" scope="page"/>" id="<%="a"+i%>_serialno" name="serialno" size="10"   maxlength="255" >
                      </td>
                      <td width="10%">
                      <input type="text" id="<%="a"+i%>_charge"  value="0.00" name="charge" size="10"   maxlength="255" >
                      </td>
                      <td ><bean:write name="tawOrderDetail" property="objectname" scope="page"/></td>
                      <td ><bean:write name="tawOrderDetail" property="supplier" scope="page"/></td>
                      <td ><bean:write name="tawOrderDetail" property="proposer" scope="page"/></td>
                      <td ><bean:write name="tawOrderDetail" property="propDept" scope="page"/></td>
                      <td ><bean:write name="tawOrderDetail" property="startdate" scope="page"/></td>
        </tr>
<%i++;%>
      </logic:iterate>
</table>
<SCRIPT LANGUAGE="JavaScript">
<!--
function checkOnly(el){
	document.getElementById(el.label).checked=false;
}
//-->
</SCRIPT>
<table border="0" width="95%" cellspacing="0">
   <tr>
     <td height="32" align="right">
          <input type="submit" value="<bean:message key="label.ok"/>" name="submit" class="clsbtn2">
          &nbsp;&nbsp;
      </td>
    </tr>
</table>
</form>
</center>
</div>
</body>
<%@ include file="/common/footer_eoms.jsp"%>

</html>
