<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<title><fmt:message key="tawWorkbenchContactDetail.title" /></title>
<div class="list">
	<html:form action="tawWorkbenchDeptContacts" method="post"
		styleId="tawWorkbenchDeptContactForm">
		<ul>
			<table class="formTable">
				<tr height="30">
					<td nowrap="nowrap" width="20%" class="label">
						<eoms:label styleClass="desc"
							key="tawWorkbenchContactForm.contactName" />
					</td>
					<td>
						<bean:write name="tawWorkbenchDeptContactForm" property="contactName" />
					</td>
				</tr>
				<tr height="30">
					<td nowrap="nowrap" width="20%" class="label">
						<eoms:label styleClass="desc"
							key="tawWorkbenchContactForm.address" />
					</td>
					<td>
						<bean:write name="tawWorkbenchDeptContactForm" property="address" />
					</td>
				</tr>
				<%--
		 
		 <tr height="30" class="header">
			<td nowrap="nowrap" width="20%">
			<eoms:label styleClass="desc" key="tawWorkbenchContactForm.deptId" />
			</td>
			<td>
			<bean:write name="tawWorkbenchDeptContactForm" property="deptId" />
			</td>
		</tr>
	 
		 --%>
				<tr height="30">
					<td nowrap="nowrap" width="20%" class="label">
						<eoms:label styleClass="desc"
							key="tawWorkbenchContactForm.deptName" />
					</td>
					<td>
						<bean:write name="tawWorkbenchDeptContactForm" property="deptName" />
					</td>
				</tr>

				<tr height="30">
					<td nowrap="nowrap" width="20%" class="label">
						<eoms:label styleClass="desc" key="tawWorkbenchContactForm.email" />
					</td>
					<td>
						<a href="${app}/workbench/contact/tawWorkbenchContacts.do?"> <bean:write
								name="tawWorkbenchDeptContactForm" property="email" /> </a>
					</td>
				</tr>

				<tr height="30">
					<td nowrap="nowrap" width="20%" class="label">
						<eoms:label styleClass="desc"
							key="tawWorkbenchContactForm.position" />
					</td>
					<td>
						<bean:write name="tawWorkbenchDeptContactForm" property="position" />
					</td>
				</tr>
				<tr height="30">
					<td nowrap="nowrap" width="20%" class="label">
						<eoms:label styleClass="desc" key="tawWorkbenchContactForm.tele" />
					</td>
					<td>
						<bean:write name="tawWorkbenchDeptContactForm" property="tele" />
					</td>
				</tr>

			</table>
		</ul>


	</html:form>
</div>
<%@ include file="/common/footer_eoms.jsp"%>
