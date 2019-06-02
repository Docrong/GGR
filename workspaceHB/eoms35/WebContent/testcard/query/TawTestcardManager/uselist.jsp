<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<html>
  <head>
    <title>${eoms:a2u("测试卡测试页面")}</title>
 
  </head>
<%
//String dealStatus = StaticMethod.null2String(request.getParameter("deal"),"1");
String condition="";//request.getAttribute(condition);
%>
<body background="<%=request.getContextPath()%>/images/img/bg001.gif">
  <table width="95%" cellspacing="1" class="formTable">
<tr>
<td width="100%" colspan="14"   align="center" height="25"><b>
${eoms:a2u("可 供 测 试 的 测 试 卡 列 表")}
 </b> </td>
</tr>
<tr bgcolor="#FFFFFF">
    <td width="100%" colspan="14" height="25" bgcolor="#EEECED" align="center">
      <bean:write name="pagerHeader" scope="request" filter="false"/>
    <%! String key;%>
    </td>
</tr>
<tr bgcolor="#FFFFFF">
       <td nowrap class="label" align="center" height="25">
         ${eoms:a2u(" 序号")}
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
         ${eoms:a2u("电话号码")}
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
          ${eoms:a2u("查看")}
        </td>
        <td nowrap class="label" align="center" height="25">
          ${eoms:a2u("测试")}
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
  <td nowrap   align="center"><%=i%></td>
    <td nowrap   align="center">
                    <bean:write name="tawTestcard" property="leave" scope="page"/>
    </td>
    <td nowrap   align="center" >
                    <bean:write name="tawTestcard" property="fromOpe" scope="page"/>
    </td>
    <td nowrap   align="center">
                    <bean:write name="tawTestcard" property="iccid" scope="page"/>
    </td>
    <td nowrap   align="center">
                    <bean:write name="tawTestcard" property="telnum" scope="page"/>
    </td>
     <td nowrap   align="center" >
         <bean:write name="tawTestcard" property="cardpackage" scope="page"/>

    </td>
     <td nowrap   align="center">
         <bean:write name="tawTestcard" property="state" scope="page"/>
    </td>
    </td>
     <td nowrap   align="center" >
         <bean:write name="tawTestcard" property="cardType" scope="page"/>
    </td>
    <td nowrap   align="center" > <html:link href="../TawTestcard/view.do" paramId="iccid" paramName="tawTestcard" paramProperty="iccid"> ${eoms:a2u("显示")}</html:link></td>
    <td nowrap   align="center" height="30"> <html:link href="../TawTestcardManager/touse.do" paramId="iccid" paramName="tawTestcard" paramProperty="iccid"> ${eoms:a2u("测试")}</html:link></td>
</tr>
    </logic:iterate>
</table>
</form>
</body>
</div>
</html>
