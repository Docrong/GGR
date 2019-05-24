<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:directive.page import="com.boco.eoms.eva.util.EvaConstants"/>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function(){
	v = new eoms.form.Validation({form:'evaKpiInstanceForm'});
})
</script>

<html:form action="/evaTasks.do?method=saveTaskDetail" styleId="evaKpiInstanceForm" method="post">
<table class="listTable" id="list-table">
	<caption>
		<div class="header center">
			任务(${requestScope.taskName})-合作伙伴(${requestScope.partner})-周期(${requestScope.timeType})-${requestScope.year}年(${requestScope.month1}月~${requestScope.month2}月) 考核报表
		</div>
	</caption>
	<thead>
	<tr>
		<td colspan="${requestScope.maxLevel}" align="left">
			指标名称
		</td>
		<td align="left">
			算法
		</td>
		<td align="left">
			标准总分：${requestScope.totalWeight}
		</td>
		<logic:iterate id="monthTotalIns" name="monthTotal" >
			<td align="left">
			<bean:write name="monthTotalIns" property="key"/>
			月总得分:
			<bean:write name="monthTotalIns" property="value"/> 
			</td>
		</logic:iterate>
	</tr>
	</thead>
	<tbody>
	<input type="hidden" id="taskId" name="taskId" value="${requestScope.taskId}"/>
	<input type="hidden" id="partner" name="partner" value="${requestScope.partner}"/>
	<input type="hidden" name="timeType" value="${requestScope.timeType}"/>
	<input type="hidden" name="time" value="${requestScope.time}"/>
	
	<logic:iterate id="rowList" name="tableList" type="java.util.List" indexId="index">
	<tr>
		<logic:iterate id="taskDetail" name="rowList">
		<bean:define id="leaf" name="taskDetail" property="leaf" />
		<td rowspan="${taskDetail.rowspan}" colspan="${taskDetail.colspan}" class="label" style="vertical-align:middle;text-align:left">
			<eoms:id2nameDB id="${taskDetail.kpiId}" beanId="evaKpiDao" />
		</td>
		<%if (EvaConstants.NODE_LEAF.equals(leaf)) { %>
		<td align="left">
			${taskDetail.algorithm}
		</td>
		<td align="left">
			${taskDetail.weight}
		</td>		
			<logic:iterate id="subMultiScore" name="taskDetail" property="multiScore">
			<td align="left">
				<bean:write name="subMultiScore"/>
			</td>
			</logic:iterate>	
		<%}%>
		</logic:iterate>
	</tr>
	</logic:iterate>
	</tbody>
</table>
<table>
	<tr>
		<td>	
			<input type="submit" class="btn" value="返回" onclick="queryBack()" />
		</td>
	</tr>
</table>
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>
<script type="text/javascript">
function  queryBack()
    {
      document.forms[0].action="evaReportInfos.do?method=queryInitMultiMonth";
    };
</script>