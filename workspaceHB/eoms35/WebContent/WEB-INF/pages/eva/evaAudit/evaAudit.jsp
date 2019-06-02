<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<%
	String templateId = StaticMethod.nullObject2String(request.getAttribute("templateId"));
%>
<script language="javascript">
function check(flag){
   if(flag == 0){
     if(evaAuditForm.auditInfo.value == ""){
       alert("请填写审批意见！");
       return false;
     }
     evaAuditForm.auditFlag.value = "0";
     evaAuditForm.submit();
     return true;
   }
   else{
     if(evaAuditForm.auditInfo.value == ""){
       alert("请填写审批意见！");
       return false;
     }
     evaAuditForm.auditFlag.value = "1";
     evaAuditForm.submit();
     return true;
   }
}
</script>
<div class="list-title">
	审批
</div>

<html:form action="/evaAudit.do?method=audit" styleId="evaAuditForm" method="post"> 
<table>
<input type="hidden" name="auditFlag">
	<tr height="30">
		<td width="30%">
			审批意见
		</td>
		<td width="70%">
			<html:textarea property="auditInfo" styleId="auditInfo"
						styleClass="text medium"  value="${evaAuditForm.auditInfo}" />
		</td>
	</tr>
	<tr height="30">
		<td colspan="2">
            <input type="button" value="审批通过" onClick="return check(0);">
            <input type="button" value="审批不通过" onClick="return check(1);">
		</td>
	</tr>
</table>

<html:hidden property="id" value="<%=templateId%>" />
</html:form>
<!-- 模板信息和考核结果 -->
<table class="formTable" id="form">
	<caption>
		<div class="header center">考核任务</div>
	</caption>
	<tr>
		<td class="label">
			模板名称
		</td>
		<td class="content">
			${template.templateName}
		</td>
		<td class="label">
			考核周期
		</td>
		<td class="content">
			<eoms:dict key="dict-eva" dictId="cycle" itemId="${template.cycle}" beanId="id2nameXML" />
		</td>
	</tr>
	<tr>
		<td class="label">
			周期起始日期
		</td>
		<td class="content">
			${template.startTime}
		</td>
		<td class="label">
			周期结束日期
		</td>
		<td class="content">
			${template.endTime}
		</td>
	</tr>
	<tr>
		<td class="label">
			模板下发时间
		</td>
		<td class="content">
			${org.operateTime}
		</td>
		<td class="label">
			模板接收单位
		</td>
		<td class="content">
			${org.orgName}
		</td>
	</tr>
	<c:if test="${activeOrg.actionType == 'template_reported'}">
	<tr>
		<td class="label">
			模板上报时间
		</td>
		<td class="content">
			${activeOrg.operateTime}
		</td>
		<td class="label">
			审核意见
		</td>
		<td class="content">
			<a href="${app}/eva/evaAudit.do?method=auditInfoList&nodeId=${org.id}"
				 target="_blank">
				点击查看审核意见
			</a>
		</td>
	</tr>
	</c:if>
	<tr>
		<td class="label">
			备注
		</td>
		<td class="content" colspan="3">
			${template.remark}
		</td>
	</tr>
</table>
<br/>
<table class="listTable" id="list">
	<caption>
		<div class="header center">指标列表</div>
	</caption>
	<thead>	
	<tr>
		<td>
			编号
		</td>
		<td>
			指标名称
		</td>
		<td>
			创建人
		</td>
		<td>
			创建时间
		</td>
		<td>
			分数
		</td>
	</tr>
	</thead>
	<tbody>
	<logic:iterate id="kpiList" name="kpiList" indexId="index">
	<tr>
		<td>
			${index + 1}
		</td>
		<td>
			<bean:write name="kpiList" property="kpiName"/>
		</td>
		<td>
			<bean:write name="kpiList" property="creator"/>
		</td>
		<td>
			<bean:write name="kpiList" property="createTime"/>
		</td>
		<td>
			<bean:write name="kpiList" property="evaScore"/>
		</td>
	</tr>
	</logic:iterate>
	</tbody>
</table>
<%@ include file="/common/footer_eoms.jsp"%>