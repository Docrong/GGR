<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%
//String dealStatus = StaticMethod.null2String(request.getParameter("deal"),"1");
String condition="";//request.getAttribute(condition);
%>
<body background="<%=request.getContextPath()%>/images/img/bg001.gif">

<div align="center">
  <center>

<table border="0" width="95%" cellspacing="1" class="listTable">


<tr>
<td width="100%" colspan="14" bgcolor="#E5EDF8" align="center" height="25"><b>
 
${eoms:a2u("测试卡查询结果 列表")}
    &nbsp; 

 </b> </td>
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
       <td nowrap class="label"  align="center" height="25">
          ${eoms:a2u("存放公司")}
        </td>
        <td nowrap class="label"  align="center" height="25">
          ${eoms:a2u("运营商")}
        </td>
        <td nowrap class="label"  align="center" height="25">
          ${eoms:a2u("手机号码")}
        </td>
        <td nowrap class="label"  align="center" height="25">
          ${eoms:a2u("卡号(iccid)")}
        </td>
        <td nowrap class="label"  align="center" height="25">
         ${eoms:a2u("MSISDN")}
        </td>
        <td nowrap class="label"  align="center" height="25">
          ${eoms:a2u("套餐")}
        </td>
        <td nowrap class="label"  align="center" height="25">
           ${eoms:a2u("状态")}
        </td>
        <td nowrap class="label"  align="center" height="25">
           ${eoms:a2u("激活状态")}
        </td>
        <td nowrap class="label"  align="center" height="25">
           ${eoms:a2u("卡类型")}
        </td>
        <td nowrap class="label"  align="center" height="25">
         ${eoms:a2u(" 查看")}
        </td>
<!--        <td nowrap bgcolor="#E5EDF8" align="center" height="25">
          ${eoms:a2u(" 修改")}
        </td>
        <td nowrap bgcolor="#E5EDF8" align="center" height="25">
         
          ${eoms:a2u("  删除")}
        </td> -->
</b>
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
    <td nowrap bgcolor="#E5EDF8" align="center" >
                    <bean:write name="tawTestcard" property="phoneNumber" scope="page"/>
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
    </td>
     <td nowrap bgcolor="#E5EDF8" align="center">
         <bean:write name="tawTestcard" property="isAlive" scope="page"/>
    </td>
    </td>
     <td nowrap bgcolor="#E5EDF8" align="center" >
         <bean:write name="tawTestcard" property="cardType" scope="page"/>
    </td>

    <td nowrap bgcolor="#E5EDF8" align="center" > <html:link href="../TawTestcard/view.do" paramId="iccid" paramName="tawTestcard" paramProperty="iccid">${eoms:a2u(" 查看")}</html:link></td>
<!--    <td nowrap bgcolor="#E5EDF8" align="center" > <html:link href="../TawTestcard/edit.do" paramId="iccid" paramName="tawTestcard" paramProperty="iccid">${eoms:a2u(" 修改")}</html:link></td>
    <td nowrap bgcolor="#E5EDF8" align="center" > <html:link href="../TawTestcard/remove.do" paramId="iccid" paramName="tawTestcard" paramProperty="iccid"> ${eoms:a2u("删除")}</html:link></td>-->

</tr>
    </logic:iterate>

<tr>


</tr>
</table>
</div>
</center>
</body>
</div>
