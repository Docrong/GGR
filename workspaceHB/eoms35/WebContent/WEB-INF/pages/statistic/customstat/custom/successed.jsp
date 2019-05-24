<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_innerpage.jsp"%>
<script type="text/javascript">
	//刷新父框架中的整颗树
	parent.AppFrameTree.refreshTree();
	//刷新父框架中当前选中的节点
	//parent.AppFrameTree.reloadNode();
</script>

<div class="successPage">
	<h1 class="header">${eoms:a2u('定制失败！')}</h1>
	<div class="content">
	${eoms:a2u('您的定制已经执行过，请检查。')}<br/>
	</div>
</div>

<html:form action="stSubscriptions.do?method=search" method="post" styleId="StSubscriptionForm">
<table>
	<tr>
		<td>
			&nbsp;&nbsp;&nbsp;&nbsp;
		</td>
		<td>
			 <html:submit styleClass="button" onclick="bCancel=true">
			${eoms:a2u('返回')}
			</html:submit>
		</td>
	</tr>
</table>
</html:form>

<%@ include file="/common/footer_eoms_innerpage.jsp"%>