<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<table class="listTable" id="list-table">
	<caption>
		<div class="header center">考核任务列表</div>
	</caption>
	<thead>
	<tr>
		<td>
			编号
		</td>
		<td>
			模板名称
		</td>
		<td>
			接收单位
		</td>
		<td>
			下发时间
		</td>
		<td>
			查看详细
		</td>
	</tr>
	</thead>
	<tbody>
	<logic:iterate id="orgIter" name="orgIter" indexId="index">
	<tr>
		<td>
			${index + 1}
		</td>
		<td>
			<bean:write name="orgIter" property="templateName"/>
		</td>
		<td>
			<bean:write name="orgIter" property="orgName"/>
		</td>
		<td>
			<bean:write name="orgIter" property="operateTime"/>
		</td>
		<td>
			<a href="${app}/eva/evaKpiInstances.do?method=viewInstance&nodeId=<bean:write name="orgIter" property="id"/>"
				 target="_blank">
				查看详细
			</a>
		</td>
	</tr>
	</logic:iterate>
	</tbody>
</table>
<%@ include file="/common/footer_eoms.jsp"%>