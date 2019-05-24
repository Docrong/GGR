<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<%
	String formId=StaticMethod.nullObject2String(request.getAttribute("formId"));
%>
<body background="<%=request.getContextPath()%>/images/img/bg001.gif">

<div align="center">
 <center>
<script language="javascript">
function onSubmit(){
       	var ret="";
        var ids= document.getElementsByName("id");
        for(var i=0;i<ids.length;i++){
          var selected=ids[i].checked;
          var id=ids[i].value;
          if(selected){
            ret=ret+","+id;
          }
        }
        if(ret==""){
          	alert('请选择要申请的电话号码！');
          	return false;
        }else{
       		retsub=ret.substring(1,ret.length);
        	window.location.href='<%=request.getContextPath()%>/testcard/TawTestcardApply/submitcards.do?ids='+retsub+'&formId='+<%=formId%>;
        }

}
</script>
<table border="0" width="95%" cellspacing="1" class="listTable">
<tr>
	<td width="100%" colspan="14" bgcolor="#E5EDF8" align="center" height="25">
		<b>
			测试卡查询结果 列表&nbsp; 
 		</b> 
	</td>
</tr>
<tr bgcolor="#FFFFFF">
    <td width="100%" colspan="14" height="25" bgcolor="#EEECED" align="center">
      <bean:write name="pagerHeader" scope="request" filter="false"/>
    <%! String key;%>
    </td>
</tr>
<tr bgcolor="#FFFFFF">
       <td nowrap class="label"  align="center" height="25">
          选择
        </td>
       <td nowrap class="label"  align="center" height="25">
          存放公司
        </td>
        <td nowrap class="label"  align="center" height="25">
          手机号码
        </td>
</tr>
   <logic:iterate id="tawTestcard" name="tawTestcard" type="com.boco.eoms.testcard.model.TawTestcard">
<tr bgcolor="#FFFFFF">
     <td nowrap>
     <input name="id" type="checkbox" value="<bean:write name="tawTestcard" property="id" scope="page"/>"/>
     <td nowrap bgcolor="#E5EDF8" align="center">
                    <bean:write name="tawTestcard" property="leave" scope="page"/>
    </td>
    <td nowrap bgcolor="#E5EDF8" align="center" >
                    <bean:write name="tawTestcard" property="phoneNumber" scope="page"/>
    </td>

</tr>
    </logic:iterate>

<tr>


</tr>
	<TR>
		<TD align="left" colspan=3>
      		<input type="button" value="选择提交" onclick="onSubmit();"/>&nbsp;
		</TD>
	</TR>
</table>
</center>
</div>
</body>

