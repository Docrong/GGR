<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<html>
<head>
<title></title>
 
</head>
<%
//String dealStatus = StaticMethod.null2String(request.getParameter("deal"),"1");
String condition="";//request.getAttribute(condition);
%>
<body>

<div align="center">
<form action="../TawTestcardManager/clearadd.do" method="post">
<table border="0" width="95%" cellspacing="1" class="formTable">


<tr>
<td width="100%" colspan="14" bgcolor="#E5EDF8" align="center" height="25">
<b align="center" class="table_title"><font size="+1">
  ${eoms:a2u("测试卡清查列表")}
</font></b></td>
</tr>
<tr bgcolor="#FFFFFF">
    <td width="100%" colspan="14" height="25" bgcolor="#EEECED" align="center">
      <bean:write name="pagerHeader" scope="request" filter="false"/>
    <%! String key;%>
    </td>
</tr>
<tr bgcolor="#FFFFFF"><b>
       <td nowrap class="label" align="center" height="25">
            ${eoms:a2u("序号")}
        </td>
       <td nowrap class="label" align="center" height="25">
            ${eoms:a2u("存放公司")}
        </td>
        <td nowrap class="label" align="center" height="25">
            ${eoms:a2u("运营商")}
        </td>
        <td nowrap class="label" align="center" height="25">
            ${eoms:a2u("卡号(iccid)")}
        </td>
        <td nowrap class="label" align="center" height="25">
           ${eoms:a2u("msisdn")}
        </td>
        <td nowrap class="label" align="center" height="25">
            ${eoms:a2u("套餐")}
        </td>
        <td nowrap class="label" align="center" height="25">
             ${eoms:a2u("状态")}
        </td>
        <td nowrap class="label" align="center" height="25">
             ${eoms:a2u("卡类型")}
        </td>
        <td nowrap class="label" align="center" height="25">
            ${eoms:a2u("清查历史记录")}
        </td>
</tr>
 <%
    int i=0;
    %>
   <logic:iterate id="tawTestcard" name="tawTestcard" type="com.boco.eoms.testcard.model.TawTestcard">
<%
i=i+1;
%>
<tr bgcolor="#FFFFFF">
  <td nowrap bgcolor="#E5EDF8" align="center"><%=i%></td>
    <td nowrap bgcolor="#E5EDF8" align="center">
                    <bean:write name="tawTestcard" property="leave" scope="page"/>
    </td>
    <td nowrap bgcolor="#E5EDF8" align="center" >
                    <bean:write name="tawTestcard" property="fromOpe" scope="page"/>
    </td>
    <td nowrap bgcolor="#E5EDF8" align="center">
                    <bean:write name="tawTestcard" property="iccid" scope="page"/>
    </td>
    <td nowrap bgcolor="#E5EDF8" align="center">
                    <bean:write name="tawTestcard" property="msisdn" scope="page"/>
    </td>
     <td nowrap bgcolor="#E5EDF8" align="center" >
         <bean:write name="tawTestcard" property="cardpackage" scope="page"/>

    </td>
     <td nowrap bgcolor="#E5EDF8" align="center">
         <bean:write name="tawTestcard" property="state" scope="page"/>
         <html:hidden name="tawTestcard" property="state"/>
    </td>
     <td nowrap bgcolor="#E5EDF8" align="center" >
         <bean:write name="tawTestcard" property="cardType" scope="page"/>
    </td>
    <td nowrap bgcolor="#E5EDF8" align="center" > <html:link href="../TawTestcardManager/clearhistory.do" paramId="iccid" paramName="tawTestcard" paramProperty="iccid">  ${eoms:a2u("查看")}</html:link></td>
</tr>
    </logic:iterate>
</table>
</form>
</div>
</body>
