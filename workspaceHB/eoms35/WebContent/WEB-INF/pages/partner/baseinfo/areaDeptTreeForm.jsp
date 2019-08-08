<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<script type="text/javascript">
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'areaDeptTreeForm'});
    });
</script>

<html:form action="/areaDeptTrees.do?method=save" styleId="areaDeptTreeForm" method="post">

    <fmt:bundle basename="config/applicationResources-partner-baseinfo">

        <table class="formTable">
            <caption>
                <div class="header center"><fmt:message key="areaDeptTree.form.heading"/></div>
            </caption>

            <tr>
                <td class="label">
                    <fmt:message key="areaDeptTree.nodeName"/>
                </td>
                <td class="content">
                    <html:text property="nodeName" styleId="nodeName"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${areaDeptTreeForm.nodeName}"/>
                </td>
            </tr>

        </table>

    </fmt:bundle>

    <table>
        <tr>
            <td>
                <input type="submit" class="btn" value="<fmt:message key="button.save"/>"/>
                <c:if test="${not empty areaDeptTreeForm.id}">
                    <input type="button" class="btn" value="<fmt:message key="button.delete"/>"
                           onclick="javascript:if(confirm('确定删除?')){
                                   var url='${app}/areaDeptTree/areaDeptTrees.do?method=remove&nodeId=${areaDeptTreeForm.nodeId}';
                                   location.href=url}"
                    />
                </c:if>
            </td>
        </tr>
    </table>
    <html:hidden property="id" value="${areaDeptTreeForm.id}"/>
    <html:hidden property="nodeId" value="${areaDeptTreeForm.nodeId}"/>
    <html:hidden property="parentNodeId" value="${areaDeptTreeForm.parentNodeId}"/>
    <html:hidden property="leaf" value="${areaDeptTreeForm.leaf}"/>
</html:form>

<%@ include file="/common/footer_eoms.jsp" %>