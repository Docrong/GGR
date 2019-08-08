<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page language="java" pageEncoding="UTF-8" %>

<script type="text/javascript">
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'kmScoreSetForm'});
    });
</script>

<html:form action="/kmScoreSets.do?method=save&nodeId=${nodeId}" styleId="kmScoreSetForm" method="post">

    <fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

        <table class="formTable">

            <input type="hidden" name="scoreWeightId" value="${kmScoreSetForm.scoreWeightId}"/>
            <input type="hidden" name="isDeleted" value="${kmScoreSetForm.isDeleted}"/>
            <tr>
                <td class="label">
                    <fmt:message key="kmScoreSet.powerName"/>
                </td>
                <td class="content">
                        ${kmScoreSetForm.powerName}
                    <input type="hidden" name="powerName" value="${kmScoreSetForm.powerName}"/>
                </td>
                <td class="label">
                    <fmt:message key="kmScoreWeight.powerWeight"/>
                </td>
                <td class="content">
                        ${powerWeight}
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="kmScoreSet.actionName"/>
                </td>
                <td class="content">
                        ${kmScoreSetForm.actionName}
                    <input type="hidden" name="actionName" value="${kmScoreSetForm.actionName}"/>
                </td>
                <td class="label">
                    <fmt:message key="kmScoreWeight.actionWeight"/>
                </td>
                <td class="content">
                        ${actionWeight}
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="kmScoreSet.userLevel"/>
                </td>
                <td class="content">
                    <eoms:dict key="dict-kmmanager" dictId="userLevel" isQuery="false"
                               alt="allowBlank:false,vtext:'请选择是否开放(字典)...'"
                               defaultId="${kmScoreSetForm.userLevel}" selectId="userLevel" beanId="selectXML"/>
                </td>
            </tr>
            <tr>
                <td class="label">
                    <fmt:message key="kmScoreSet.isCount"/>
                </td>
                <td class="content">
                    <eoms:dict key="dict-kmmanager" dictId="isCount" isQuery="false"
                               alt="allowBlank:false,vtext:'请选择是否开放(字典)...'"
                               defaultId="${kmScoreSetForm.isCount}" selectId="isCount" beanId="selectXML"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="kmScoreSet.score"/>
                </td>
                <td class="content">
                    <html:text property="score" styleId="score"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${kmScoreSetForm.score}"/>
                </td>
            </tr>

            <tr>
                <td class="label">
                    <fmt:message key="kmScoreSet.remark"/>
                </td>
                <td class="content">
                    <html:textarea property="remark" styleId="remark" cols="37" rows="8"
                                   alt="maxLength:1000,vtext:''" value="${kmScoreSetForm.remark}"/>
                </td>
            </tr>


        </table>

    </fmt:bundle>

    <table>
        <tr>
            <td>
                <input type="submit" class="btn" value="<fmt:message key="button.save"/>"/>
                <c:if test="${not empty kmScoreSetForm.id}">
                    <input type="button" class="btn" value="<fmt:message key="button.delete"/>"
                           onclick="javascript:if(confirm('你确定删除?')){
                                   var url='${app}/kmmanager/kmScoreSets.do?method=remove&id=${kmScoreSetForm.id}&nodeId=${nodeId}';
                                   location.href=url}"/>
                </c:if>
            </td>
        </tr>
    </table>
    <html:hidden property="id" value="${kmScoreSetForm.id}"/>
</html:form>

<%@ include file="/common/footer_eoms.jsp" %>