<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%
String condition="";
%>
<script language="javascript">
function onReturn()
{
  document.form.action="../TawTestcardManager/return.do";
  document.form.submit();
}

function onRenew()
{
  document.form.action="../TawTestcardManager/renew.do";
  document.form.submit();
}
</script>
<html>
<body background="<%=request.getContextPath()%>/images/img/bg001.gif">
<form name="form" method="post" >
<table border="0" width="95%" cellspacing="1" class="formTable">
	<tr>
		<td width="100%" colspan="13"   align="center" height="25">
 		<b align="center" class="table_title">${eoms:a2u("请选择要归还的测试卡列表")}&nbsp;&nbsp; 
		</td>
	</tr>
	<tr bgcolor="#FFFFFF">
    		<td width="100%" colspan="14" height="25" bgcolor="#EEECED" align="center">
      			<bean:write name="pagerHeader" scope="request" filter="false"/>
    		</td>
        </tr>
	<tr bgcolor="#FFFFFF"><b>
       		<td nowrap class="label" align="center" height="25">${eoms:a2u("卡号(iccid)")}</td>
       		<td nowrap class="label" align="center" height="25">${eoms:a2u("msisdn")}</td>
            <td nowrap class="label" align="center" height="25">${eoms:a2u("经手人")}</td>
            <td nowrap class="label" align="center" height="25">${eoms:a2u("归属省份")}</td>
        	<td nowrap class="label" align="center" height="25">${eoms:a2u("归属地市")}</td>
        	<td nowrap class="label" align="center" height="25">${eoms:a2u("借用人")}</td>
       		<td nowrap class="label" align="center" height="25">${eoms:a2u("借用时间")}</td>
        	<td nowrap class="label" align="center" height="25">${eoms:a2u("应归还时间")}</td>
<!--        <td nowrap class="label" align="center" height="25">${eoms:a2u("归还时间")}</td>-->
            <td nowrap class="label" align="center" height="25">${eoms:a2u("状态")}</td>
        	<td nowrap class="label" align="center" height="25">${eoms:a2u("借用原因")}</td>
            <!--<td nowrap class="label" align="center" height="25">${eoms:a2u("归还")}</td>
        	<td nowrap class="label" align="center" height="25">${eoms:a2u("续借")}</td>-->
        	<td nowrap class="label" align="center" height="25">${eoms:a2u("选择")}	</td>
	</tr>
   	<logic:iterate id="tawTestcardManager" name="tawTestcardManager" type="com.boco.eoms.testcard.model.TawTestcardManager">
		<tr bgcolor="#FFFFFF">
     	<td nowrap   align="center" ><bean:write name="tawTestcardManager" property="cardid" scope="page"/></td>
     	<td nowrap   align="center" ><bean:write name="tawTestcardManager" property="msisdn" scope="page"/></td>
     	<td nowrap   align="center" ><bean:write name="tawTestcardManager" property="dealer" scope="page"/></td>
     	
    	<td nowrap   align="center" ><bean:write name="tawTestcardManager" property="fromcrit" scope="page"/></td>
    	<td nowrap   align="center" ><bean:write name="tawTestcardManager" property="fromcity" scope="page"/></td>
    	
    	<td nowrap   align="center" ><bean:write name="tawTestcardManager" property="lender" scope="page"/></td>
     	<td nowrap   align="center" ><bean:write name="tawTestcardManager" property="leantime" scope="page"/></td>
     	<td nowrap   align="center" ><bean:write name="tawTestcardManager" property="belongtime" scope="page"/></td>
<!--    <td nowrap   align="center" ><bean:write name="tawTestcardManager" property="returntime" scope="page"/></td>-->
        <td nowrap   align="center" ><bean:write name="tawTestcardManager" property="stateName" scope="page"/></td>
     	<td nowrap   align="center" ><bean:write name="tawTestcardManager" property="reason" scope="page"/></td>
        <td nowrap   align="center" ><INPUT TYPE="checkbox" name="id" value="<bean:write name="tawTestcardManager" property="id" scope="page"/>"></td>
        <%--<!--<td nowrap   align="center" > <html:link href="../TawTestcardManager/return.do" paramId="id" paramName="tawTestcardManager" paramProperty="id">归还</html:link></td>
        <td nowrap   align="center" >
        <%if(!("使用").equals(tawTestcardManager.getState())){%>
        <html:link href="../TawTestcardManager/renew.do" paramId="id" paramName="tawTestcardManager" paramProperty="id">${eoms:a2u("续借</html:link>
          <%}%>
        </td>-->
        --%></tr>
    	</logic:iterate>
 <tr>
  <td width="100%" colspan="14"   align="right">
   <input type="button" value=${eoms:a2u("归还")} name="gh" class="button" onclick="onReturn();">&nbsp;&nbsp;
   <input type="button" value=${eoms:a2u("续借")} name="xj" class="button" onclick="onRenew();">
  </td>
</tr>
</table>
</form>
</body>
</html>