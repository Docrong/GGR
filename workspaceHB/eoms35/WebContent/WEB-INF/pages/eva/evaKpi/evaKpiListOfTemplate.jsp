<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<style type="text/css">
  	body{background-image:none;}
</style>
<table class="listTable" id="list-table">
	<caption>
		<div class="header center">考核指标列表</div>
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
	</tr>
	</thead>
	<tbody>
	<logic:iterate id="kpiIt" name="kpiIt" indexId="index">
	<tr>
		<td>
			${index + 1}
		</td>
		<td>
			<bean:write name="kpiIt" property="kpiName"/>
		</td>
		<td>
			<bean:write name="kpiIt" property="creator"/>
		</td>
		<td>
			<bean:write name="kpiIt" property="createTime"/>
		</td>
	</tr>
	</logic:iterate>
	</tbody>
</table>
<script src="${app}/scripts/util/iframe.js" type="text/javascript"/></script>
<%@ include file="/common/footer_eoms.jsp"%>