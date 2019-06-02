<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<html>
<head>
<title>
<bean:message key="order.view"/>
</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/table_style.css" type="text/css">
<script language="javascript">
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
  var arrCharges = document.getElementsByTagName('input');
  for(var i=0;i<arrCharges.length;i++){
    if(arrCharges[i].name=="charge"){
        if(!IsNumber(arrCharges[i].value))
           {
              alert('${eoms:a2u("维修费用必须为数字!")}');
              //document.frm.upperlimit.focus();
              return false;
           }else{
              var idTemp = arrCharges[i].id;
              var hiddenObj = document.getElementById(idTemp+"_");
              hiddenObj.value = idTemp + "_" + arrCharges[i].value;
            }
         var aa=arrCharges[i].value;
         if(aa.length>10){
           alert('${eoms:a2u(维修费用数字不能超出12位!)}');
           return false;
          }
//    alert(hiddenObj.id+"="+hiddenObj.value);
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
         <b>${eoms:a2u('确定需要退库的备件')}</b>
      </td>
    </tr>
</table>
<form action="../part/backover.do?order=<bean:write name="order" scope="request" filter="false"/>" method="post" name="TawSparepartForm" onsubmit="return handleid();">
<table border="0" width="95%" cellspacing="1" cellpadding="1" align=center>
    <tr class="tr_show_left">
      <td>
        ${eoms:a2u('您当前操作的仓库是：')}<b>&nbsp;&nbsp;<bean:write name="storage" scope="session"/></b>
      </td>
    </tr>
</table>
<table border="0" width="95%" cellspacing="1" cellpadding="1" class="table_show" align="center">
        <tr class="td_label">
                      <td width="10%"><bean:message key="sparepart.department"/></td>
                      <td width="8%"><bean:message key="sparepart.necode"/></td>
                      <td ><bean:message key="sparepart.objectname"/></td>
                      <td ><bean:message key="sparepart.supplier"/></td>
                      <td><bean:message key="sparepart.objectname"/></td>
                      <td ><bean:message key="sparepart.serialno"/></td>
                      <td width="8%">${eoms:a2u('退库')}</td>
        </tr>
<%int i=1;%>
    <logic:iterate id="sparepart" name="sparepart" type="com.boco.eoms.sparepart.model.TawPart">
        <tr class="tr_show" align="center">
                      <td ><bean:write name="sparepart" property="nettype" scope="page"/></td>
                      <td ><bean:write name="sparepart" property="necode" scope="page"/></td>
                      <td ><bean:write name="sparepart" property="objecttype" scope="page"/></td>
                      <td ><bean:write name="sparepart" property="supplier" scope="page"/></td>
                      <td ><bean:write name="sparepart" property="objectname" scope="page"/></td>
                      <td ><bean:write name="sparepart" property="serialno" scope="page"/></td>
                      <td>
                      <INPUT TYPE="checkbox" name="back" id="<%="a"+i%>" label="<%="b"+i%>" onClick="checkOnly(this);" value="<bean:write name="sparepart" property="id" scope="page"/>"></td>
                      </td>
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
<table class="listTable" border="0" width="95%" cellspacing="0">
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
