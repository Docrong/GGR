<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<html>
 


<%
//String dealStatus = StaticMethod.null2String(request.getParameter("deal"),"1");
String condition="";//request.getAttribute(condition);
%>
<body background="<%=request.getContextPath()%>/images/img/bg001.gif">

<div align="center">
<form action="../TawTestcardManager/add.do" method="post">
<table border="0" width="95%" cellspacing="1" class="formTable">


<tr>
<td width="100%" colspan="14" class="label" align="center" height="25">
<b align="center" class="table_title"><font size="+1">
${eoms:a2u("可供借出测试卡  列表")}
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
       <%--<td nowrap class="label" align="center" height="25">
          存放公司
        </td>
        <td nowrap class="label" align="center" height="25">
          运营商
        </td>
        --%><td nowrap class="label" align="center" height="25">
          ${eoms:a2u("卡号(iccid)")}
        </td>
        <td nowrap class="label" align="center" height="25">
        ${eoms:a2u(" msisdn")}
        </td>
        <td nowrap class="label" align="center" height="25">
         ${eoms:a2u("归属省份")}
        </td>
        <td nowrap class="label" align="center" height="25">
         ${eoms:a2u("归属地市")}
        </td>
        <td nowrap class="label" align="center" height="25">
          ${eoms:a2u("套餐")}
        </td>
        <td nowrap class="label" align="center" height="25">
          ${eoms:a2u(" 状态")}
        </td>
        <td nowrap class="label" align="center" height="25">
           ${eoms:a2u("卡类型")}
        </td>
        <td nowrap class="label" align="center" height="25">
          ${eoms:a2u("查看")}
        </td>
        <td nowrap class="label" align="center" height="25">
          ${eoms:a2u("可借")}
        </td>
        <td nowrap class="label" align="center" height="25">
        ${eoms:a2u("选择")}
        </td>
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
  <td nowrap   align="center"><%=i%></td>
    <%--<td nowrap class="label" align="center">
                    <bean:write name="tawTestcard" property="leave" scope="page"/>
    </td>
    <td nowrap class="label" align="center" >
                    <bean:write name="tawTestcard" property="fromOpe" scope="page"/>
    </td>
    --%><td  align="center">
                    <bean:write name="tawTestcard" property="iccid" scope="page"/>
    </td>
    <td nowrap  align="center">
                    <bean:write name="tawTestcard" property="msisdn" scope="page"/>
    </td>
    <td nowrap  align="center">
                    <bean:write name="tawTestcard" property="fromCrit" scope="page"/>
    </td>
    <td nowrap  align="center">
                    <bean:write name="tawTestcard" property="fromCity" scope="page"/>
    </td>
     <td nowrap   align="center" >
         <bean:write name="tawTestcard" property="cardpackage" scope="page"/>

    </td>
     <td nowrap   align="center">
         <bean:write name="tawTestcard" property="state" scope="page"/>
    </td>
     <td nowrap   align="center" >
         <bean:write name="tawTestcard" property="cardType" scope="page"/>
    </td>
    <td nowrap   align="center" > <html:link href="../TawTestcard/view.do" paramId="iccid" paramName="tawTestcard" paramProperty="iccid">${eoms:a2u("查看")}</html:link></td>
    <td nowrap   align="center" height="30"> <html:link href="../TawTestcardManager/add.do" paramId="iccid" paramName="tawTestcard" paramProperty="iccid">${eoms:a2u("可借")}</html:link></td>
    <td nowrap   align="center" height="25">
        <INPUT TYPE="checkbox" name="iccid" value="<bean:write name="tawTestcard" property="iccid" scope="page"/>"></td>
    </td>

</tr>
    </logic:iterate>
<tr>
<td width="100%" colspan="14" class="label" align="right" height="25">
<input type="submit" value=${eoms:a2u("借出")} name="pp" class="button">
</td>
</tr>
<tr>

<%
//System.out.println(condition);
%>
<!--<input type="submit" name="excel" value="导出EXCEL" class="clsbtn2">-->

</tr>
</table>
</form>
</div>
</body>
