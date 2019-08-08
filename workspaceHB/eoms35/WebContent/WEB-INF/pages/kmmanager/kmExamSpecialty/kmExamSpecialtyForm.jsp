<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<script type="text/javascript">
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'kmExamSpecialtyForm'});
    });
</script>

<html:form action="/kmExamSpecialtys.do?method=save" styleId="kmExamSpecialtyForm" method="post">

    <fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
        <font color='red'>*</font>号为必填内容
        <table class="formTable">
            <caption>
                <div class="header center"><fmt:message key="kmExamSpecialty.form.heading"/></div>
            </caption>

            <tr>
                <td class="label">
                    <fmt:message key="kmExamSpecialty.specialtyName"/>&nbsp;<font color='red'>*</font>
                </td>
                <td class="content">
                    <html:text property="specialtyName" styleId="specialtyName"
                               styleClass="text medium" maxlength="20"
                               alt="allowBlank:false,vtext:''" value="${kmExamSpecialtyForm.specialtyName}"/>
                </td>
            </tr>

            <!-- tr>
		<td  class="label">
			<fmt:message key="kmExamSpecialty.isDeleted" />
		</td>
		<td class="content">
			<html:text property="isDeleted" styleId="isDeleted"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${kmExamSpecialtyForm.isDeleted}" />
		</td>
	</tr> -->
            <html:hidden property="isDeleted" value="0"/>
        </table>

    </fmt:bundle>

    <table>
        <tr>
            <td>
                <input type="submit" class="btn" value="<fmt:message key="button.save"/>"/>
                <c:if test="${not empty kmExamSpecialtyForm.specialtyID}">
                    <input type="button" class="btn" value="<fmt:message key="button.delete"/>"
                           onclick="javascript:if(confirm('confirm?')){
                                   var url='${app}/kmmanager/kmExamSpecialtys.do?method=remove&nodeId=${kmExamSpecialtyForm.nodeId}';
                                   location.href=url}"
                    />
                </c:if>
            </td>
        </tr>
    </table>
    <html:hidden property="specialtyID" value="${kmExamSpecialtyForm.specialtyID}"/>
    <html:hidden property="nodeId" value="${kmExamSpecialtyForm.nodeId}"/>
    <html:hidden property="parentNodeId" value="${kmExamSpecialtyForm.parentNodeId}"/>
    <html:hidden property="leaf" value="${kmExamSpecialtyForm.leaf}"/>
</html:form>

<%@ include file="/common/footer_eoms.jsp" %>