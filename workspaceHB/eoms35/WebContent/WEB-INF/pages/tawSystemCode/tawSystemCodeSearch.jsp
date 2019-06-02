
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<html:form action="/codes.do?method=xquery" method="post" styleId="TawSystemCodeForm"> 
<table class="formTable" width="75%">
	<tr>
		<td colspan="4" align="center">
			<bean:message key="tawSystemCodeForm.chaxunTitle"/>
		</td>
	</tr>
	<tr>
	<td width="260"></td>
		<td class="label" align="right">
			<eoms:label styleClass="desc" key="tawSystemCodeForm.mingcheng"/>
		    <html:errors property="name"/>
		</td>
		<td>
			<html:text property="name" styleId="name" styleClass="text medium"
			alt="maxLength:128" />
		</td>
		<td width="260"></td>
	</tr>
	<tr>
	<td width="260"></td>
		<td class="label" align="right">
			<eoms:label styleClass="desc" key="tawSystemCodeForm.bianma"/>
		    <html:errors property="code"/>
		</td>
		<td>
			<html:text property="code" styleId="code" styleClass="text medium"
			alt="maxLength:128" />
		</td>
		<td width="260"></td>
	</tr>
	<tr>
		<td colspan="4" align="center">
		<html:submit styleClass="button" property="method.save" onclick="bCancel=false">
            <bean:message key="tawSystemCodeForm.chaxun"/>
        </html:submit>
         &nbsp;&nbsp;
      <html:reset styleClass="button" onclick="bCancel=true"> <fmt:message key="button.reset"/></html:reset>
		</td>
	</tr>
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>