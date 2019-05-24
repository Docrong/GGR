<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<html>
<link href="<%=request.getContextPath()%>/css/table_style.css" rel="stylesheet" type="text/css">


<%
//String dealStatus = StaticMethod.null2String(request.getParameter("deal"),"1");
String condition="";//request.getAttribute(condition);
%>
<body background="<%=request.getContextPath()%>/images/img/bg001.gif">

<div align="center">
  <center>

<table border="0" width="95%" cellspacing="1" bgcolor="#709ED5" style="border-right-style: solid; border-right-color: #d3d3d3; border-bottom-style: solid; border-bottom-color: #d3d3d3">


<tr>
<td width="100%" colspan="14" bgcolor="#E5EDF8" align="center" height="25">
<b>
<%
out.print("测试卡借用记录列表");
%>
    &nbsp;&nbsp;<b><bean:message key="label.list"/></b>

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
       <td nowrap bgcolor="#E5EDF8" align="center" height="25">
          卡号(iccid)
        </td>
        <td nowrap bgcolor="#E5EDF8" align="center" height="25">
           电话号码
        </td>
        <td nowrap bgcolor="#E5EDF8" align="center" height="25">
          存放分公司
        </td>
        <td nowrap bgcolor="#E5EDF8" align="center" height="25">
          经手人
        </td>
        <td nowrap bgcolor="#E5EDF8" align="center" height="25">
          借用部门
        </td>
        <td nowrap bgcolor="#E5EDF8" align="center" height="25">
          借用人
        </td>
        <td nowrap bgcolor="#E5EDF8" align="center" height="25">
          借用时间
        </td>
        <td nowrap bgcolor="#E5EDF8" align="center" height="25">
          应归还时间
        </td>
        <td nowrap bgcolor="#E5EDF8" align="center" height="25">
           归还时间
        </td>
        <td nowrap bgcolor="#E5EDF8" align="center" height="25">
           状态
        </td>
        <td nowrap bgcolor="#E5EDF8" align="center" height="25">
           用途
        </td>
        <td nowrap bgcolor="#E5EDF8" align="center" height="25">
          查看
        </td>
 <!--       <td nowrap bgcolor="#E5EDF8" align="center" height="25">
          修改
        </td>
        <td nowrap bgcolor="#E5EDF8" align="center" height="25">
          删除
        </td>-->
</b>
</tr>
<logic:iterate id="tawTestcardManager" name="tawTestcardManager" type="com.boco.eoms.testcard.model.TawTestcardManager">
<tr bgcolor="#FFFFFF">
     <td nowrap bgcolor="#E5EDF8" align="center" >
                    <bean:write name="tawTestcardManager" property="cardid" scope="page"/>
    </td>
    <td nowrap bgcolor="#E5EDF8" align="center" >
                    <bean:write name="tawTestcardManager" property="telnum" scope="page"/>
    </td>
    <td nowrap bgcolor="#E5EDF8" align="center" >
                    <bean:write name="tawTestcardManager" property="leave" scope="page"/>
    </td>
    <td nowrap bgcolor="#E5EDF8" align="center" >
                    <bean:write name="tawTestcardManager" property="dealer" scope="page"/>
    </td>
    <td nowrap bgcolor="#E5EDF8" align="center" >
                    <bean:write name="tawTestcardManager" property="lenddept" scope="page"/>
    </td>
    <td nowrap bgcolor="#E5EDF8" align="center" >
                    <bean:write name="tawTestcardManager" property="lender" scope="page"/>
    </td>
     <td nowrap bgcolor="#E5EDF8" align="center" >
                    <bean:write name="tawTestcardManager" property="leantime" scope="page"/>
    </td>
     <td nowrap bgcolor="#E5EDF8" align="center" >
         <bean:write name="tawTestcardManager" property="belongtime" scope="page"/>

    </td>
     <td nowrap bgcolor="#E5EDF8" align="center" >
         <bean:write name="tawTestcardManager" property="returntime" scope="page"/>
    </td>
    <td nowrap bgcolor="#E5EDF8" align="center" >
         <bean:write name="tawTestcardManager" property="state" scope="page"/>
    </td>
     <td nowrap bgcolor="#E5EDF8" align="center" >
         <bean:write name="tawTestcardManager" property="reason" scope="page"/>
    </td>

    <td nowrap bgcolor="#E5EDF8" align="center" height="30"> <html:link href="../TawTestcardManager/useview.do" paramId="id" paramName="tawTestcardManager" paramProperty="id"><bean:message key="label.view"/></html:link></td>
 <!--   <td nowrap bgcolor="#E5EDF8" align="center" height="30"> <html:link href="../TawTestcardManager/edit.do" paramId="id" paramName="tawTestcardManager" paramProperty="id"><bean:message key="label.edit"/></html:link></td>
    <td nowrap bgcolor="#E5EDF8" align="center" height="30"> <html:link href="../TawTestcardManager/remove.do" paramId="id" paramName="tawTestcardManager" paramProperty="id"><bean:message key="label.remove"/></html:link></td>
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
