
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<html:form action="/codes.do?method=cancel" method="post"
	styleId="TawSystemCodeForm">
	<ul>
		<html:hidden property="id" />
		<table class="formTable middle" align="center">

			<tr>
				<td colspan="2" align="center" class="label">
					<h2>
						${eoms:a2u('模块基础信息管理查看')}
					</h2>
				</td>
			</tr>
			<tr>
				<td width="40%" height="20" class="label">
					<bean:message key="tawSystemCodeForm.mingcheng" />
				</td>
				<td>
					<bean:write property="name" name="tawSystemCodeForm" />
				</td>
			</tr>
			<tr>
				<td width="40%" height="20" class="label">
					<bean:message key="tawSystemCodeForm.bianma" />
				</td>
				<td>
					<bean:write property="code" name="tawSystemCodeForm" />
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<html:submit styleClass="button" property="method.save"
						onclick="window.close();">
						<bean:message key="tawSystemCodeForm.close" />
					</html:submit>
				</td>
			</tr>
		</table>
	</ul>

</html:form>
<%@ include file="/common/footer_eoms.jsp"%>
