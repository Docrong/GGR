<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import ="com.boco.eoms.common.util.StaticMethod"%>
<html>
  <head>
    <title> ${eoms:a2u("卡号(测试卡报废页面")}</title>
     
  </head>

<%
String condition="";
%>
<script language="javascript">
    function submitAudit(){
       	var ret="";
        var ids= document.getElementsByName("iccid");
        for(var i=0;i<ids.length;i++){
          var selected=ids[i].checked;
          var id=ids[i].value;
          if(selected){
            ret=ret+","+id;
          }
        }
        if(ret != ""){
  			document.forms[0].action="../TawTestcard/batchtrash.do";
  			document.forms[0].submit(); 
        }else{
        	alert('${eoms:a2u("请选择要删除的测试卡")}');
        	return false;
        }
  }
 </script>
<body background="<%=request.getContextPath()%>/images/img/bg001.gif">
<form action="../TawTestcard/batchtrash.do" method="post" >
  <table width="95%" cellspacing="1" class="formTable">
<tr>
<td width="100%" colspan="14" class="label" align="center" height="25"><b>
 ${eoms:a2u("测 试 卡 列 表")}
 </b> </td>
</tr>
<tr bgcolor="#FFFFFF">
    <td width="100%" colspan="14" height="25" class="label" align="center">
      <bean:write name="pagerHeader" scope="request" filter="false"/>
    <%! String key;%>
    </td>
</tr>
<tr bgcolor="#FFFFFF">
<td nowrap class="label" align="center" height="25">
        ${eoms:a2u("选择")}
        </td>
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
          ${eoms:a2u("msisdn")}
        </td>
        <td nowrap class="label" align="center" height="25">
          ${eoms:a2u("imsi")}
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
           ${eoms:a2u("修改")}
        </td>
         <td nowrap class="label" align="center" height="25">
           ${eoms:a2u("删除")}
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
<td nowrap   align="center" height="25">
        <INPUT TYPE="checkbox" name="iccid" value="<bean:write name="tawTestcard" property="iccid" scope="page"/>"></td>
    </td>
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
    <td id="td0" nowrap   align="center">
                    <bean:write name="tawTestcard" property="msisdn" scope="page"/>
    </td>
    <td nowrap   align="center">
                    <bean:write name="tawTestcard" property="imsi" scope="page"/>
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
    <td nowrap   align="center" > <html:link href="../TawTestcard/view.do" paramId="iccid" paramName="tawTestcard" paramProperty="iccid"> ${eoms:a2u("查看")}</html:link></td>
    <td nowrap   align="center" height="30"> <html:link href="../TawTestcardManager/toedit.do" paramId="iccid" paramName="tawTestcard" paramProperty="iccid"> ${eoms:a2u("修改")}</html:link></td>
<td nowrap   align="center" height="30"> <html:link href="../TawTestcard/edittrash.do" paramId="iccid" paramName="tawTestcard" paramProperty="iccid"> ${eoms:a2u("删除")}</html:link></td>
</tr>
    </logic:iterate>
    <tr>
<td width="100%" colspan="14" class="label" align="right" height="25">

     <input type="button" class="btn" style="margin-right: 5px"
		onclick="submitAudit()"
		value=${eoms:a2u("删除")} />
</td>
</tr>
</table>
</form>
</form>
</body>
</div>
</html>
