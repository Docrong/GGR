<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<script type="text/javascript">
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'papersForm'});
    });
</script>

<html:form action="/papers.do?method=save" styleId="papersForm" method="post">

    <fmt:bundle basename="config/ApplicationResources-duty">

        <table class="formTable">
            <caption>
                <div class="header center"><fmt:message key="papers.form.heading"/></div>
            </caption>

            <tr>
                <td class="label">
                    <fmt:message key="papers.title"/>
                </td>
                <td class="content">
                    <html:text property="title" styleId="title"
                               styleClass="text medium"
                               alt="allowBlank:false,vtext:''" value="${papersForm.title}"/>
                </td>
            </tr>

                <%--<tr>
                    <td class="label">
                        <fmt:message key="papers.insertTime" />
                    </td>
                    <td class="content">
                        <html:text property="insertTime" styleId="insertTime"
                                    styleClass="text medium"
                                    alt="allowBlank:false,vtext:''" value="${papersForm.insertTime}" />
                    </td>
                </tr>

                <tr>
                    <td class="label">
                        <fmt:message key="papers.insertUserId" />
                    </td>
                    <td class="content">
                        <html:text property="insertUserId" styleId="insertUserId"
                                    styleClass="text medium"
                                    alt="allowBlank:false,vtext:''" value="${papersForm.insertUserId}" />
                    </td>
                </tr>--%>


            <tr>
                <td class="label">
                    <fmt:message key="papers.accessories"/>
                </td>
                <td class="content">
                    <c:choose>
                        <c:when test="${ not empty papersForm.id }">
                            <eoms:attachment name="papersForm" property="accessoriesId"
                                             scope="request" idField="accessoriesId" appCode="papers"/>
                        </c:when>
                        <c:otherwise>
                            <eoms:attachment idList="" idField="accessoriesId" appCode="papers"/>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>

        </table>

    </fmt:bundle>

    <table>
        <tr>
            <td>
                <input type="submit" class="btn" value="<fmt:message key="button.save"/>"/>
                <c:if test="${not empty papersForm.id}">
                    <input type="button" class="btn" value="<fmt:message key="button.delete"/>"
                           onclick="javascript:if(confirm('是否确认删除?')){
                                   var url='${app}/duty/papers/papers.do?method=remove&id=${papersForm.id}';
                                   location.href=url}"/>
                </c:if>
            </td>
        </tr>
    </table>
    <html:hidden property="id" value="${papersForm.id}"/>
</html:form>

<%@ include file="/common/footer_eoms.jsp" %>