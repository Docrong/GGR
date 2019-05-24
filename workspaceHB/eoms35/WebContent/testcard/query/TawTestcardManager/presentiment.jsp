<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import = "java.util.*,java.lang.*"%>
 
<%
//String dealStatus = StaticMethod.null2String(request.getParameter("deal"),"1");
String condition="";//request.getAttribute(condition);
%>
<body background="<%=request.getContextPath()%>/images/img/bg001.gif">

<div align="center">
  <center>

<table border="0" width="95%" cellspacing="1" class="formTable">


<tr>
<td width="100%" colspan="14" bgcolor="#E5EDF8" align="center" height="25">
<b>

${eoms:a2u("测试卡预警列表")}

    &nbsp;&nbsp;<b> </b>

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
          ${eoms:a2u("卡号")}
        </td>
        <td class="label" align="center" height="25">
           ${eoms:a2u("msisdn")}
        </td>
        <td class="label" align="center" height="25">
          ${eoms:a2u("借用部门")}
        </td>
        <td class="label" align="center" height="25">
          ${eoms:a2u("借用人")}
        </td>
        <td class="label" align="center" height="25">
          ${eoms:a2u("借用时间")}
        </td>
        <td class="label" align="center" height="25">
          ${eoms:a2u("应归还时间")}
        </td>
        <td class="label" align="center" height="25">
           ${eoms:a2u("用途")}
        </td>
        <td class="label" align="center" height="25">
          ${eoms:a2u("查看")}
        </td>
</b>
</tr>
<logic:iterate id="tawTestcardManager" name="tawTestcardManager" type="com.boco.eoms.testcard.model.TawTestcardManager">
<tr bgcolor="#FFFFFF">
     <td   align="center" >
                    <bean:write name="tawTestcardManager" property="cardid" scope="page"/>
    </td>
    <td   align="center" >
                    <bean:write name="tawTestcardManager" property="msisdn" scope="page"/>
    </td>
    <td   align="center" >
                    <bean:write name="tawTestcardManager" property="lenddept" scope="page"/>
    </td>
    <td   align="center" >
                    <bean:write name="tawTestcardManager" property="lender" scope="page"/>
    </td>
     <td   align="center" >
                    <bean:write name="tawTestcardManager" property="leantime" scope="page"/>
    </td>
     <td   align="center" >
         <bean:write name="tawTestcardManager" property="belongtime" scope="page"/>
    </td>
     <td   align="center" >
         <bean:write name="tawTestcardManager" property="reason" scope="page"/>
    </td>

    <td   align="center" height="30"> <html:link href="../TawTestcardManager/view.do" paramId="id" paramName="tawTestcardManager" paramProperty="id">${eoms:a2u("查看")}</html:link></td>
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
