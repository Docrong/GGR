<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<html:form action="/kmExpertBasics.do?method=save" styleId="kmExpertBasicForm" method="post">
	<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager"><br>
		<table class="formTable">
			<tr>
				<td class="label">
					<fmt:message key="kmExpertBasic.userId" />
				</td>
				<td class="content">
					<eoms:id2nameDB id="${kmExpertBasicForm.userId}" beanId="tawSystemUserDao" />
				</td>
				<td class="label">
					<fmt:message key="kmExpertBasic.deptId" />
				</td>
				<td class="content">
					<eoms:id2nameDB id="${kmExpertBasicForm.deptId}" beanId="tawSystemDeptDao" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="kmExpertBasic.phone" />
				</td>
				<td class="content">
					${kmExpertBasicForm.phone}
				</td>
				<td class="label">
					<fmt:message key="kmExpertBasic.position" />
				</td>
				<td class="content">
					${kmExpertBasicForm.position}
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="kmExpertBasic.joinJobDate" />
				</td>
				<td class="content">
					${kmExpertBasicForm.joinJobDate}
				</td>
				<td class="label">
					<fmt:message key="kmExpertBasic.education" />
				</td>
				<td class="content">
					<eoms:id2nameDB id="${kmExpertBasicForm.education}" beanId="ItawSystemDictTypeDao" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="kmExpertBasic.privateJobDate" />
				</td>
				<td class="content">
					${kmExpertBasicForm.privateJobDate}
				</td>
				<td class="label">
					<fmt:message key="kmExpertBasic.polity" />
				</td>
				<td class="content">
					<eoms:id2nameDB id="${kmExpertBasicForm.polity}" beanId="ItawSystemDictTypeDao" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="kmExpertBasic.company" />
				</td>
				<td colspan="3" class="content">
					${kmExpertBasicForm.company}
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="kmExpertBasic.expertClass" />
				</td>
				<td class="content">
					<eoms:id2nameDB id="${kmExpertBasicForm.expertClass}" beanId="ItawSystemDictTypeDao" />
				</td>			
				<td class="label">
					<fmt:message key="kmExpertBasic.expertLevel" />
				</td>
				<td class="content">
					<eoms:id2nameDB id="${kmExpertBasicForm.expertLevel}" beanId="ItawSystemDictTypeDao" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="kmExpertBasic.specialty" />
				</td>
				<td class="content">
					<eoms:id2nameDB id="${kmExpertBasicForm.specialty}" beanId="ItawSystemDictTypeDao" />
				</td>			
				<td class="label">
					<fmt:message key="kmExpertBasic.area" />
				</td>
				<td class="content">
					<eoms:id2nameDB id="${kmExpertBasicForm.area}" beanId="tawSystemAreaDao" />
				</td>
			</tr>

			<tr>
				<td class="label">
					<fmt:message key="kmExpertBasic.intro" />
				</td>
				<td colspan="3" class="content">
					<textarea name="intro" cols="50" id="intro" class="textarea max" readonly="readonly" alt="allowBlank:false,vtext:'请输入专家特长...'">${kmExpertBasicForm.intro}</textarea>
				</td>
			</tr>
		</table>
	</fmt:bundle>
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>