<%@ page language="java" pageEncoding="UTF-8"%>
<jsp:directive.page import="com.boco.eoms.eva.util.EvaConstants" />
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function(){
	v = new eoms.form.Validation({form:'evaKpiInstanceForm'});
})
</script>

<html:form action="/evaTasks.do?method=saveTaskDetail"
	styleId="evaKpiInstanceForm" method="post">
	<table class="listTable" id="list-table">
		<caption>
			<div class="header center">
				任务(${requestScope.taskName})-合作伙伴(${requestScope.partner})-周期(${requestScope.timeType})-时间(${requestScope.time}) 考核报表
				考核报表
			</div>
		</caption>
		<thead>
			<tr>
				<td colspan="${requestScope.maxLevel}" align="left">
					指标名称
				</td>
				<td align="left">
					算法描述
				</td>
				<td align="left">
					标准分(总${requestScope.totalWeight}分)
				</td>
				<td align="left">
					实际得分(总${requestScope.totalScore}分)
				</td>
				<td align="left">
					增扣分原因
				</td>
				<td align="left">
					备注
				</td>
			</tr>
		</thead>
		<tbody>
			<logic:iterate id="rowList" name="tableList" type="java.util.List"
				indexId="index">
				<tr>
					<logic:iterate id="taskDetail" name="rowList">
						<bean:define id="leaf" name="taskDetail" property="leaf" />
						<td rowspan="${taskDetail.rowspan}"
							colspan="${taskDetail.colspan}" class="label"
							style="vertical-align:middle;text-align:left">
							<eoms:id2nameDB id="${taskDetail.kpiId}" beanId="evaKpiDao" />
						</td>
						<%
						if (EvaConstants.NODE_LEAF.equals(leaf)) {
						%>
						<td align="left">
							<bean:write name="taskDetail" property="algorithm" />
						</td>
						<td align="left">
							${taskDetail.weight}
						</td>
						<td align="left">
							<bean:write name="taskDetail" property="realScore" />
						</td>
						<td align="left">
							<bean:write name="taskDetail" property="reduceReason" />
						</td>
						<td align="left">
							<bean:write name="taskDetail" property="remark" />
						</td>
						<%
						}
						%>
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
      document.forms[0].action="evaReportInfos.do?method=queryInitSingle";
    };
</script>
