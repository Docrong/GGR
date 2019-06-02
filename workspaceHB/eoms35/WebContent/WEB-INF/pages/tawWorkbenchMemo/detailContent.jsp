<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import="com.boco.eoms.workbench.memo.model.TawWorkbenchMemo" %>
<%@ page import="com.boco.eoms.base.util.StaticMethod" %>
<title><fmt:message key="tawWorkbenchMemoDetail.title" /></title>
<content tag="heading">
<fmt:message key="tawWorkbenchMemoDetail.heading" />
</content>
<%
	TawWorkbenchMemo tawWorkbenchMemo=(TawWorkbenchMemo)request.getAttribute("tawWorkbenchMemo");
%>
<html:form action="/tawWorkbenchMemo.do?method=saveSend" method="post" styleId="tawWorkbenchMemoForm">
	<ul>
		<html:hidden property="id" />

		<table class="formTable">

			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawWorkbenchMemoForm.title" />
				</td>
				<td width="500" colspan="5">
					<%=StaticMethod.null2String(tawWorkbenchMemo.getTitle()) %>
				</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawWorkbenchMemoForm.content" />
				</td>
				<td width="500" colspan="5">
					<%=StaticMethod.null2String(tawWorkbenchMemo.getContent()) %>
				</td>
			</tr>

			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawWorkbenchMemoForm.level" />
				</td>
				<td width="500" colspan="5">
					<eoms:dict key="memo-dict" dictId="level" itemId="<%=tawWorkbenchMemo.getLevel() %>" beanId="id2nameXML" />
				</td>
			</tr>
			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawWorkbenchMemoForm.reciever" />
				</td>
				<td width="500" colspan="5">
					<%=StaticMethod.null2String(tawWorkbenchMemo.getReciever()) %>
				</td>
			</tr>

			<tr>
				<td width="100" class="label">
					<eoms:label styleClass="desc" key="tawWorkbenchMemoForm.sendmanner" />
				</td>
				<td width="500" colspan="5">
					<eoms:dict key="memo-dict" dictId="sendmanner" itemId="<%=tawWorkbenchMemo.getSendmanner() %>" beanId="id2nameXML" />
				</td>
			</tr>
		</table>
		<br>
		<table>
			<tr>
				<td>
					<html:submit styleClass="button" property="method.search">
		           		<fmt:message key="button.back"/>
		       		</html:submit>
				</td>
			<tr>
		</table>
	</ul>
</html:form>


<%@ include file="/common/footer_eoms.jsp"%>
