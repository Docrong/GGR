<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<table width="700" class="listTable">
	<caption>
		部省接口列表显示
	</caption>
	<thead>
		<tr>
			<td>
			时间
			</td>
			<td>
			专业名称
			</td>
			<td>
			日志类型
			</td>
			<td>
			结果
			</td>
			<td>
			详细
			</td>
		</tr>
	</thead>
	<tbody>
		<logic:iterate id="showList" name="listAll">
			<tr>
				<td>
					<bean:write name="showList" property="createTime" />
				</td>
				<td>
					<bean:write name="showList" property="model" />
				</td>
				<td>
					<bean:write name="showList" property="logType" />
				</td>
				<td>
					<bean:write name="showList" property="resultState" />
				</td>
				<td>
					<a href="<%=request.getContextPath()%>/workplan/logShow/showOne.do?id=<bean:write name='showList' property='id' />">详细</a>
				</td>
			</tr>
		</logic:iterate>
		<tr>
			<td colspan='9'>
				<center>
					<input type="button" value="返回" onClick="javascript:window.history.back();" class="button">
				</center>
			</td>
		</tr>
	</tbody>
</table>
