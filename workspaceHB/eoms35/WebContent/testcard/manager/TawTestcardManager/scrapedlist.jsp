<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import = "java.util.*,java.lang.*"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<html>
 


<%
//String dealStatus = StaticMethod.null2String(request.getParameter("deal"),"1");
String condition="";//request.getAttribute(condition);
%>
<body>

<div align="center">
  <center>

<table border="0" width="95%" cellspacing="1" class="formTable">


<tr>
<td width="100%" colspan="14" bgcolor="#E5EDF8" align="center" height="25">
<b>
 
${eoms:a2u("测试卡借用记录列表")}
    &nbsp;&nbsp;<b>${eoms:a2u("记录列表")}</b>

 </b>
</td>
</tr>
<tr bgcolor="#FFFFFF">
    <td width="100%" colspan="14" height="25" bgcolor="#EEECED" align="center">
      <bean:write name="pagerHeader" scope="request" filter="false"/>
    <%! String key;%>
    </td>
</tr>
<tr bgcolor="#FFFFFF"><b>
       <td class="label" align="center" height="25">
          ${eoms:a2u("卡号(iccid)")}
        </td>
        <td class="label" align="center" height="25">
           ${eoms:a2u("MSISDN")}
        </td>
        <td class="label" align="center" height="25">
          ${eoms:a2u("报废部门")}
        </td>
        <td class="label" align="center" height="25">
          ${eoms:a2u("经手人")}
        </td>
        <td class="label" align="center" height="25">
          ${eoms:a2u("报废时间")}
        </td>
        <td class="label" align="center" height="25">
           ${eoms:a2u("备注")}
        </td>
        <td class="label" align="center" height="25">
          ${eoms:a2u("查看")}
        </td>
 <!--       <td class="label" align="center" height="25">
          修改
        </td>
        <td class="label" align="center" height="25">
          删除
        </td>-->
</b>
</tr>
<logic:iterate id="tawTestcardManager" name="tawTestcardManager" type="com.boco.eoms.testcard.model.TawTestcardManager">
<tr bgcolor="#FFFFFF">
     <td  align="center" >
                    <bean:write name="tawTestcardManager" property="cardid" scope="page"/>
    </td>
    <td  align="center" >
                    <bean:write name="tawTestcardManager" property="msisdn" scope="page"/>
    </td>
    <td  align="center" >
                    <bean:write name="tawTestcardManager" property="leave" scope="page"/>
    </td>
    <td  align="center" >
                    <bean:write name="tawTestcardManager" property="dealer" scope="page"/>
    </td>
     <td  align="center" >
                    <bean:write name="tawTestcardManager" property="leantime" scope="page"/>
    </td>
     <td  align="center" >
         <bean:write name="tawTestcardManager" property="reason" scope="page"/>
    </td>

    <td class="label" align="center" height="30"> <html:link href="../TawTestcardManager/scrapview.do" paramId="id" paramName="tawTestcardManager" paramProperty="id"> ${eoms:a2u("查看")}</html:link></td>
 <!--   <td class="label" align="center" height="30"> <html:link href="../TawTestcardManager/edit.do" paramId="id" paramName="tawTestcardManager" paramProperty="id"><bean:message key="label.edit"/></html:link></td>
    <td class="label" align="center" height="30"> <html:link href="../TawTestcardManager/remove.do" paramId="id" paramName="tawTestcardManager" paramProperty="id"><bean:message key="label.remove"/></html:link></td>
-->
</tr>
</logic:iterate>

<tr>

<%
//System.out.println(condition);
%>
<!--<input type="submit" name="excel" value="导出EXCEL" class="clsbtn2">-->

</tr>
</table>
</div>
</center>
</body>
</div>
