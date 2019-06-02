<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_innerpage.jsp"%>
<script type="text/javascript">
	//刷新父框架中的整颗树
	parent.AppFrameTree.refreshTree();
	//刷新父框架中当前选中的节点
	//parent.AppFrameTree.reloadNode();
</script>

<div class="successPage">
	<h1 class="header">
		${eoms:a2u('操作成功！')}
	</h1>
	<div class="content">
		${eoms:a2u('您的操作已成功执行。')}
		<br />
	</div>
</div>

<html:form action="/codes.do?method=xquery"
	method="post" styleId="TawSystemCodeForm">
	<table>
		<tr>
			<td>
				&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td>
				<html:submit styleClass="button" property="method.back"
					onclick="bCancel=false">
					<fmt:message key="button.back" />
				</html:submit>
			</td>
		</tr>
	</table>
</html:form>

<%@ include file="/common/footer_eoms_innerpage.jsp"%>
