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
<td width="100%" colspan="14" bgcolor="#E5EDF8" align="center" height="25"><b>
<%
out.print("测试卡列表");
%>
    &nbsp;&nbsp;<b><bean:message key="label.list"/></b>

 </b> </td>
</tr>
<tr bgcolor="#FFFFFF">
    <td width="100%" colspan="14" height="25" bgcolor="#EEECED" align="center">
      <bean:write name="pagerHeader" scope="request" filter="false"/>
    <%! String key;%>
    </td>
</tr>
<tr bgcolor="#FFFFFF"><b>
       <td nowrap bgcolor="#E5EDF8" align="center" height="25">
          序列号
        </td>
        <td nowrap bgcolor="#E5EDF8" align="center" height="25">
           开通日期
        </td>
        <td nowrap bgcolor="#E5EDF8" align="center" height="25">
           注销日期
        </td>
        <td nowrap bgcolor="#E5EDF8" align="center" height="25">
          旧系统编号
        </td>
        <td nowrap bgcolor="#E5EDF8" align="center" height="25">
          开通业务
        </td>
        <td nowrap bgcolor="#E5EDF8" align="center" height="25">
          当前状态
        </td>
        <td nowrap bgcolor="#E5EDF8" align="center" height="25">
           卡类型
        </td>
        <td nowrap bgcolor="#E5EDF8" align="center" height="25">
           运营商
        </td>
        <td nowrap bgcolor="#E5EDF8" align="center" height="25">
          查看
        </td>
        <td nowrap bgcolor="#E5EDF8" align="center" height="25">
          修改
        </td>
        <td nowrap bgcolor="#E5EDF8" align="center" height="25">
          删除
        </td>
</b>
</tr>
   <logic:iterate id="tawTestcard" name="tawTestcard" type="com.boco.eoms.testcard.model.TawTestcard">

<tr bgcolor="#FFFFFF">
     <td nowrap bgcolor="#E5EDF8" align="center" height="30">
                    <bean:write name="tawTestcard" property="iccid" scope="page"/>
    </td>
    <td nowrap bgcolor="#E5EDF8" align="center" height="30">
                    <bean:write name="tawTestcard" property="begintime" scope="page"/>
    </td>
    <td nowrap bgcolor="#E5EDF8" align="center" height="30">
                    <bean:write name="tawTestcard" property="endtime" scope="page"/>
    </td>
    <td nowrap bgcolor="#E5EDF8" align="center" height="30">
                    <bean:write name="tawTestcard" property="oldNo" scope="page"/>
    </td>
     <td nowrap bgcolor="#E5EDF8" align="center" height="30">
                    <bean:write name="tawTestcard" property="operation" scope="page"/>
    </td>
     <td nowrap bgcolor="#E5EDF8" align="center" height="30">
         <bean:write name="tawTestcard" property="state" scope="page"/>

    </td>
     <td nowrap bgcolor="#E5EDF8" align="center" height="30">
         <bean:write name="tawTestcard" property="cardType" scope="page"/>
    </td>
    </td>
     <td nowrap bgcolor="#E5EDF8" align="center" height="30">
         <bean:write name="tawTestcard" property="fromOpe" scope="page"/>
    </td>

    <td nowrap bgcolor="#E5EDF8" align="center" height="30"> <html:link href="../TawTestcard/view.do" paramId="iccid" paramName="tawTestcard" paramProperty="iccid"><bean:message key="label.view"/></html:link></td>
    <td nowrap bgcolor="#E5EDF8" align="center" height="30"> <html:link href="../TawTestcard/edit.do" paramId="iccid" paramName="tawTestcard" paramProperty="iccid"><bean:message key="label.edit"/></html:link></td>
    <td nowrap bgcolor="#E5EDF8" align="center" height="30"> <html:link href="../TawTestcard/remove.do" paramId="iccid" paramName="tawTestcard" paramProperty="iccid"><bean:message key="label.remove"/></html:link></td>

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
