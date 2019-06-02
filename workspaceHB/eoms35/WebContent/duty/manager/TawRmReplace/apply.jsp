<%@ page language="java" import="java.util.*,java.lang.*, org.apache.struts.util.*,com.boco.eoms.common.util.StaticMethod"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<script type="text/javascript">
Ext.onReady(function(){
	var	userTreeAction='${app}/xtree.do?method=userFromDept';
			new xbox({
				btnId:'receiverName',dlgId:'dlg-audit',dlgTitle:'${eoms:a2u('请选择人员')}',
				treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'${eoms:a2u('所有人员')}',treeChkMode:'single',treeChkType:'user',
				showChkFldId:'receiverName',saveChkFldId:'receiver'
			}); 
})
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawRmReplaceForm'});
});
 
</script>
<%
	  String roomId = request.getParameter("roomId");
	   
      GregorianCalendar cal_start = new GregorianCalendar();
      cal_start.add(cal_start.DATE,1);
      String str_start = StaticMethod.Cal2String(cal_start);
      str_start = String.valueOf(StaticMethod.getVector(str_start," ").elementAt(0));
      String isExit = (String)request.getAttribute("isexit");
     
      if(isExit==null||isExit.equals("")){
      	isExit="0";
      } 
%>
<head>
 
</head>
<body leftmargin="0" topmargin="0" class="clssclbar">
<br>
<html:form action="tawRmReplace" method="post" styleId="tawRmReplaceForm"> 
<input type = "hidden" name="roomId" value="<%=roomId%>">
<table  width="600" align="left">
<tr>
<td>
<table cellpadding="0" cellspacing="0" border="0" width="700"  >
<tr>
<td align="center" class="table_title">
<%if(isExit=="1"){
 %>
 <font color="red">${eoms:a2u('对方在这个时间段存在值班计划')}</font>
<%
  } else if(isExit=="2"){
%>
	<font color="red">${eoms:a2u('您在这个时间段内不存在值班计划')}</font>
<%
  }
%>
    &nbsp;${eoms:a2u('替班管理')}
  </td>
</tr>
</table>
<table cellpadding="0" cellspacing="0" border="0" width="600">
<tr>
<td>
<table border="0" width="80%" cellspacing="1" cellpadding="1" class="listTable" align=left width="600">
<tr class="tr_show">
<td class="label">
${eoms:a2u('选择替班人')}
</td>
<td>		 
			<input type="hidden" name="receiver"
						value="${tawRmReplaceForm.receiver}">
			<html:text property="receiverName" styleId="receiverName"
				styleClass="text medium" readonly="true" alt="allowBlank:false,vtext:'${eoms:a2u('请输入替班人')}'" /> 
			<html:hidden property="receiver" />
</td>
</tr>
<tr>
<td class="label">
${eoms:a2u('选择开始时间')}
</td>
 <td >
<eoms:SelectDate name="time_from" formName="tawRmReplaceForm" flag = "1" value = "<%=str_start%>"/>
</td>
</tr>
<tr>

<td class="label">
${eoms:a2u('选择结束时间')}
</td>
 <td>
<eoms:SelectDate name="time_to" formName="tawRmReplaceForm" flag = "1" value = "<%=str_start%>"/>
</td>
</tr>
<tr class="tr_show">
</tr>
</table>
</td>
</tr>
<tr>
<td>
        <html:submit styleClass="button" property="method.selectInfo" onclick="bCancel=false" >
           ${eoms:a2u('申请')} 
        </html:submit>
        &nbsp;&nbsp;&nbsp;&nbsp;<font color = "red">${eoms:a2u('* 开始时间和结束时间为申请人希望替班人替班的时间段')} </font>
</td>
</tr>
</table>
 
</html:form>
</body>

<%@ include file="/common/footer_eoms.jsp"%>