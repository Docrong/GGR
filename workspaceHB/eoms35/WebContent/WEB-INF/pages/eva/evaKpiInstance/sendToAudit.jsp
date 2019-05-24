<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function(){
	v = new eoms.form.Validation({form:'evaKpiInstanceForm'});
	
	// 人员树
	var	userTreeAction='${app}/xtree.do?method=userFromDept';
	userViewer = new Ext.JsonView("user-list",
		'<div id="user-{id}" class="viewlistitem-{nodeType}">{name}</div>',
		{ 
			multiSelect: true,
			emptyText : '<div>未选择审核人</div>'
		}
	);
	var userStr = '[]';
	userViewer.jsonData = eoms.JSONDecode(userStr);
	userViewer.refresh();
	
	userTree = new xbox({
		btnId:'userTreeBtn',dlgId:'dlg-user',
		treeDataUrl:userTreeAction,treeRootId:'-1',treeRootText:'人员',treeChkMode:'',treeChkType:'user',
		viewer:userViewer,saveChkFldId:'recieverOrgId', returnJSON:'true'
	});
})
</script>

<html:form action="/evaKpiInstances.do?method=sendToAudit" styleId="evaKpiInstanceForm" method="post"> 
<table class="formTable" id="form">
	<caption>
		<div class="header center">考核任务送审</div>
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
	<tr>
		<td class="label">
			备注
		</td>
		<td class="content" colspan="3">
			${template.remark}
		</td>
	</tr>
	<tr>
		<td class="label">
			审核人
		</td>
		<td class="content" colspan="3">
			<div id="user-list" class="viewer-box"></div>
			<input type="button" value="选择审核人" id="userTreeBtn" class="btn"/>
			<html:hidden property="recieverOrgId" styleId="recieverOrgId"/>
		</td>
	</tr>
	<tr>
		<td class="content" colspan="4">
			<input type="submit" class="btn" value="送审" />
		</td>
	</tr>
</table>
<html:hidden property="orgId" value="${org.id}" />
<html:hidden property="templateId" value="${template.id}" />
</html:form>
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