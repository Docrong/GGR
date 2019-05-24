<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
function validate() {
	var total = 0;
	Ext.select("input",false,"list").each(function(input){
		total += parseFloat(input.dom.value);
	});
	if (Ext.get("totalScore").dom.value > 100) {
		alert("总分不能大于100，请重新填写！");
	} else if (Ext.get("totalScore").dom.value != total) {
		alert("总分与指标分数之和不相等，请调整后提交！");
	} else {
		document.forms[0].submit();
	}
}

function computeTotalScore() {
	var total = 0;
	Ext.select("input",false,"list").each(function(input){
		total += parseFloat(input.dom.value);
	});
	Ext.get("totalScore").dom.value = total;
}
</script>

<table class="formTable" id="form">
	<caption>
		<div class="header center">考核结果填写</div>
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
			总分
		</td>
		<td class="content" colspan="3">
			<input type="text" class="text" id="totalScore" name="totalScore" value="${template.totalScore}" />
			<input type="button" class="btn" value="计算总分" onclick="computeTotalScore();" />
		</td>
	</tr>
</table>
<br/>
<html:form action="/evaKpiInstances.do?method=saveInstance" styleId="evaKpiInstanceForm" method="post"> 
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
			分数
		</td>
		<td>
			创建人
		</td>
		<td>
			创建时间
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
			<input type="text" class="text small" id="${kpiList.id}" name="${kpiList.id}"
				value="${kpiList.evaScore}" />
		</td>
		<td>
			<bean:write name="kpiList" property="creator"/>
		</td>
		<td>
			<bean:write name="kpiList" property="createTime"/>
		</td>
	</tr>
	</logic:iterate>
	</tbody>
</table>
<table id="submit-btn" align="right">
	<tr>
		<td>
			<input type="button" class="btn" value="保存" onclick="validate();" />
		</td>
	</tr>
</table>
<html:hidden property="id" value="${template.id}" />
<html:hidden property="orgId" value="${org.id}" />
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>