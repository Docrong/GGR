<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<html:form action="/evaKpiInstances.do?method=genInstance" styleId="evaKpiInstanceForm" method="post"> 
<table class="formTable" id="form">
	<caption>
		<div class="header center">考核任务生成</div>
	</caption>
	<tr>
		<td class="label">
			模板名称
		</td>
		<td class="content">
			${template.templateName}
		</td>
		<td class="label">
			考核周期
		</td>
		<td class="content">
			<eoms:dict key="dict-eva" dictId="cycle" itemId="${template.cycle}" beanId="id2nameXML" />
		</td>
	</tr>
	<tr>
		<td class="label">
			周期起始日期
		</td>
		<td class="content">
			${template.startTime}
		</td>
		<td class="label">
			周期结束日期
		</td>
		<td class="content">
			${template.endTime}
		</td>
	</tr>
	<tr>
		<td class="label">
			模板下发时间
		</td>
		<td class="content">
			${org.operateTime}
		</td>
		<td class="label">
			模板接收单位
		</td>
		<td class="content">
			${org.orgName}
		</td>
	</tr>
	<tr>
		<td class="label">
			备注
		</td>
		<td class="content" colspan="3">
			${template.remark}
		</td>
	</tr>
	<tr height="30">
		<td colspan="4">
			<ul>
				<c:if test="${requestScope.errorInfo != null}">
					<li class="error">
					<img src="<c:url value="/images/icons/warning.gif"/>" alt="<fmt:message key="icon.warning"/>" class="icon" />
						${requestScope.errorInfo}
					</li>
				</c:if>
			</ul>
			<input type="submit" class="btn" value="生成考核任务" />
		</td>
	</tr>
</table>

<html:hidden property="id" value="${template.id}" />
<html:hidden property="orgId" value="${org.id}" />
</html:form>
<c:if test="${template.id != null}">
<iframe id="fileList" name="fileList" frameborder="0" width="100%" height="100%" 
	src="${app}/eva/evaKpis.do?method=listKpiOfTemplate&templateId=${template.id}">
</iframe>
</c:if>
<%@ include file="/common/footer_eoms.jsp"%>