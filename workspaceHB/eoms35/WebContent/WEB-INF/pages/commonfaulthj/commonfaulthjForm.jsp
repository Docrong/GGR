<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>


<script type="text/javascript">
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'commonfaulthjForm'});
    });
</script>

<html:form action="/commonfaulthjs.do?method=save" styleId="commonfaulthjForm" method="post">

    <fmt:bundle basename="config/applicationResource-commonfaulthj">

        <table class="formTable">
            <caption>
                <div class="header center">工单统计自动核减</div>
            </caption>

            <tr>
                <td class="label">
                    工单号*
                </td>
                <td class="content">
                    <html:text property="sheetid" styleId="sheetid"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${commonfaulthjForm.sheetid}"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    创建人*
                </td>
                <td class="content">
                    <html:text property="creater" styleId="creater"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${commonfaulthjForm.creater}"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    派单年份*
                </td>
                <td class="content">
                    <html:text property="sendyear" styleId="sendyear"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${commonfaulthjForm.sendyear}"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    派单月份*
                </td>
                <td class="content">
                    <html:text property="sendmonth" styleId="sendmonth"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${commonfaulthjForm.sendmonth}"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    派单日*
                </td>
                <td class="content">
                    <html:text property="sendday" styleId="sendday"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${commonfaulthjForm.sendday}"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    核减理由*
                </td>
                <td class="content">
                    <html:text property="remark" styleId="remark"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${commonfaulthjForm.remark}"/>
                </td>
            </tr>
            <tr>
                <td class="label">
                    核减类型*
                </td>
                <td class="content">
                    <eoms:comboBox name="${sheetPageName}subtractType" id="${sheetPageName}subtractType"
                                   initDicId="1010320" defaultValue="${commonfaulthjForm.subtractType}"
                                   alt="allowBlank:false"/>
                </td>
                <!-- 	<td class="content">
			<html:text property="subtractType" styleId="subtractType"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${commonfaulthjForm.subtractType}" />
		</td> -->

            </tr>


        </table>

    </fmt:bundle>

    <table>
        <tr>
            <td>
                <input type="submit" class="btn" value="<fmt:message key="button.save"/>"/>
                <c:if test="${not empty commonfaulthjForm.id}">
                    <input type="button" class="btn" value="<fmt:message key="button.delete"/>"
                           onclick="javascript:if(confirm('确定要删除？')){
                                   var url='${app}/commonfaulthj/commonfaulthjs.do?method=remove&id=${commonfaulthjForm.id}';
                                   location.href=url}"/>
                </c:if>
            </td>
        </tr>
    </table>
    <html:hidden property="id" value="${commonfaulthjForm.id}"/>
    <html:hidden property="savetime" value="${commonfaulthjForm.savetime}"/>
</html:form>

<%@ include file="/common/footer_eoms.jsp" %>