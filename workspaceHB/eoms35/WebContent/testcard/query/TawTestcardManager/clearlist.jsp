<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<script language="JavaScript">
//全选/反选开关
function selectall(){
  var selobj = document.forms[0].iccid;
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
<head>
<title></title>

</head>
<%
//String dealStatus = StaticMethod.null2String(request.getParameter("deal"),"1");
String condition="";//request.getAttribute(condition);
%>
<body background="<%=request.getContextPath()%>/images/img/bg001.gif">

<div align="center">
<form action="../TawTestcardManager/clearadd.do" method="post">
<table border="0" width="95%" cellspacing="1"  class="formTable">


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
       <td class="label" align="center" height="25">
          ${eoms:a2u("序号")}
        </td>
       <td class="label" align="center" height="25">
          ${eoms:a2u("存放公司")}
        </td>
        <td class="label" align="center" height="25">
          ${eoms:a2u("运营商")}
        </td>
        <td class="label" align="center" height="25">
          ${eoms:a2u("卡号(iccid)")}
        </td>
        <td class="label" align="center" height="25">
        ${eoms:a2u(" msisdn")}
        </td>
        <td class="label" align="center" height="25">
          ${eoms:a2u("套餐")}
        </td>
        <td class="label" align="center" height="25">
          ${eoms:a2u(" 状态")}
        </td>
        <td class="label" align="center" height="25">
           ${eoms:a2u("卡类型")}
        </td>
        <td class="label" align="center" height="25">
          ${eoms:a2u("查看")}
        </td>
        <td class="label" align="center" height="25">
          ${eoms:a2u("备注")}
        </td>
        <td class="label" align="center" height="25">
         <a onclick="selectall()">${eoms:a2u("全选")}</a>
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
  <td  align="center"><%=i%></td>
    <td  align="center">
                    <bean:write name="tawTestcard" property="leave" scope="page"/>
    </td>
    <td  align="center" >
                    <bean:write name="tawTestcard" property="fromOpe" scope="page"/>
    </td>
    <td  align="center">
                    <bean:write name="tawTestcard" property="iccid" scope="page"/>
    </td>
    <td  align="center">
                    <bean:write name="tawTestcard" property="msisdn" scope="page"/>
    </td>
     <td  align="center" >
         <bean:write name="tawTestcard" property="cardpackage" scope="page"/>

    </td>
     <td align="center">
         <bean:write name="tawTestcard" property="state" scope="page"/>
         <html:hidden name="tawTestcard" property="state"/>
    </td>
     <td  align="center" >
         <bean:write name="tawTestcard" property="cardType" scope="page"/>
    </td>
    <td  align="center" > <html:link href="../TawTestcard/view.do" paramId="iccid" paramName="tawTestcard" paramProperty="iccid">${eoms:a2u("显示")}</html:link></td>
    <td  align="center" height="30">
      
    </td>
    
    <td  align="center" height="25">
        <INPUT TYPE="checkbox" name="iccid" value="<bean:write name="tawTestcard" property="state" scope="page"/>,<bean:write name="tawTestcard" property="iccid" scope="page"/>,<bean:write name="tawTestcard" property="msisdn" scope="page"/>"></td>
</tr>
    </logic:iterate>
<tr>
<td width="100%" colspan="10" bgcolor="#E5EDF8" align="right" height="25">
<select name="newstate" style="width: 4.0cm;"><option value="0" selected="selected">${eoms:a2u("正常")}</option>
<option value="1">${eoms:a2u("停机")}</option>
<option value="2">${eoms:a2u("遗失")}</option>
<option value="6">${eoms:a2u("SIM卡注册失败")}</option></select>
<input type="text" name="clearresan" />
</td>
<td width="100%" colspan="4" bgcolor="#E5EDF8" align="right" height="25">
<input type="submit" value=${eoms:a2u("清查")} name="pp" class="button">
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
