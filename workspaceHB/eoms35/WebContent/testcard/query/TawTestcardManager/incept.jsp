
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<script language="javascript">
function onReturn111()
{
  document.form.action="../TawTestcardManager/accede.do";
  document.form.submit();
}
function selectall(){
  var selobj = document.forms[0].id;
  for(i=0;i<selobj.length;i++){
    if(selobj[i].checked==true)
      selobj[i].checked = false;
    else
      selobj[i].checked = true;
  }
  //增加了只有一项执行内容的情况
  if(selobj.length==null){
    if(selobj.checked==true)
      selobj.checked = false;
    else
      selobj.checked = true;
  }
}
</script>
<html>
<body>
<form name="form" method="post" >
<table border="0" width="95%" cellspacing="1"  class="formTable">
	<tr>
		<td width="100%" colspan="13" bgcolor="#E5EDF8" align="center" height="25">
 		<b align="center" class="table_title">${eoms:a2u("请选择要接收的测试卡")}&nbsp;&nbsp;<b>${eoms:a2u("列表")}
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
    		<td width="100%" colspan="14" height="25" bgcolor="#EEECED" align="center">
      			<bean:write name="pagerHeader" scope="request" filter="false"/>
    		</td>
        </tr>
	<tr bgcolor="#FFFFFF"><b>
       		<td class="label" align="center" height="25">${eoms:a2u("卡号(iccid)")}</td>
       		<td class="label" align="center" height="25">${eoms:a2u("msisdn")}</td>
            <td class="label" align="center" height="25">${eoms:a2u("经手人")}</td>
            <td class="label" align="center" height="25">${eoms:a2u("归属省份")}</td>
        	<td class="label" align="center" height="25">${eoms:a2u("归属地市")}</td>
        	<td class="label" align="center" height="25">${eoms:a2u("借用人")}</td>
       		<td class="label" align="center" height="25">${eoms:a2u("借用时间")}</td>
        	<td class="label" align="center" height="25">${eoms:a2u("应归还时间")}</td>
            <td class="label" align="center" height="25">${eoms:a2u("状态")}</td>
        	<td class="label" align="center" height="25">${eoms:a2u("借用原因")}</td>
        	<td class="label" align="center" height="25"><a onclick="selectall()">${eoms:a2u("全选")}</a></td>
	</tr>
   	<logic:iterate id="tawTestcardManager" name="tawTestcardManager" type="com.boco.eoms.testcard.model.TawTestcardManager">
		<tr bgcolor="#FFFFFF">
     	<td align="center" ><bean:write name="tawTestcardManager" property="cardid" scope="page"/></td>
     	<td  align="center" ><bean:write name="tawTestcardManager" property="msisdn" scope="page"/></td>
     	<td  align="center" ><bean:write name="tawTestcardManager" property="dealer" scope="page"/></td>
    	<td  align="center" ><bean:write name="tawTestcardManager" property="fromcrit" scope="page"/></td>
    	<td  align="center" ><bean:write name="tawTestcardManager" property="fromcity" scope="page"/></td>
    	<td  align="center" ><bean:write name="tawTestcardManager" property="lender" scope="page"/></td>
     	<td align="center" ><bean:write name="tawTestcardManager" property="leantime" scope="page"/></td>
     	<td align="center" ><bean:write name="tawTestcardManager" property="belongtime" scope="page"/></td>
        <td align="center" ><bean:write name="tawTestcardManager" property="stateName" scope="page"/></td> 
     	<td align="center" ><bean:write name="tawTestcardManager" property="reason" scope="page"/></td>
        <td  align="center" ><INPUT TYPE="checkbox" name="id" value="<bean:write name="tawTestcardManager" property="id" scope="page"/>"></td>
        </tr>
    	</logic:iterate>
 <tr>
  <td width="100%" colspan="14" bgcolor="#E5EDF8" align="right">
   <input type="button" value=${eoms:a2u("接收")} name="gh" class="button" onclick="onReturn111();">&nbsp;&nbsp;
  </td>
</tr>
</table>
</form>
</body>
</html>