<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
	String startDate = com.boco.eoms.base.util.StaticMethod.getLocalString(-1);
	String endDate = com.boco.eoms.base.util.StaticMethod.getCurrentDateTime();;
 %>

<script type="text/javascript"><!--
 
</script>

	<table width="50%" cellpadding="0" cellspacing="0" class="table">
	<caption>督办看板一级视图</caption>
		<thead>
			<tr>
				<th class="label">任务状态</th>
				<th class="label">任务总数量</th>
			</tr>
		</thead>
		<tbody>
			<tr>
			<td class="label">即将进入本级督办序列</td>
			<td><a href="../supervisetask/supervisetask.do?method=supervisetaskBoardDetail2&status=0&userid=${userid }">${count1 }</a></td>
			</tr>
			<tr>
			<td class="label">已进入本级督办序列</td>
			<td><a href="../supervisetask/supervisetask.do?method=supervisetaskBoardDetail2&status=1&userid=${userid }">${count2 }</a></td>
			</tr>
			<tr>
			<td class="label">已进入上级督办序列</td>
			<td><a href="../supervisetask/supervisetask.do?method=supervisetaskBoardDetail2&status=2&userid=${userid }">${count3 }</a></td>
			</tr>
		</tbody>
	</table>
</div>




<%@ include file="/common/footer_eoms.jsp"%>
