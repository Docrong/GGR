<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<table class="formTable" id="form">
	<caption>
		<div class="header center">考核任务查看</div>
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