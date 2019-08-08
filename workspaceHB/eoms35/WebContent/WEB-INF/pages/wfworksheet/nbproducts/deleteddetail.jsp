<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="java.util.List" %>
<% String deleted = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("deleted")); %>
<script type="text/javascript">
    function query() {
        window.open("./nbproductss.do?method=relationSheet&procode=${nbproductsForm.procode}", null, "left=300,top=150,height=400,width=500,scrollbars=yes,resizable=yes");
    }
</script>
<title><fmt:message key="nbproductsDetail.title"/></title>
<content tag="heading">${eoms:a2u('产品详细信息')}</content>

<html:form action="nbproductss.do?method=xsave" method="post" styleId="detail">
    <br>
    <br>
    <table class="formTable">
        <tr>
            <td class="label">
                <bean:message bundle="nbproducts" key="nbproducts.procode"/>
            </td>
            <td class="content">
                <html:errors property="procode"/>
                <bean:write name="nbproductsForm" property="procode" scope="request"/>
            </td>
            <td class="label">
                <bean:message bundle="nbproducts" key="nbproducts.nbPName"/>
            </td>
            <td class="content">
                <html:errors property="nbPName"/>
                <bean:write name="nbproductsForm" property="nbPName" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message bundle="nbproducts" key="nbproducts.businessDept"/>
            </td>
            <td class="content">
                <eoms:id2nameDB id="${nbproductsForm.businessDept}" beanId="ItawSystemDictTypeDao"/>
            </td>
            <td class="label">

                <bean:message bundle="nbproducts" key="nbproducts.keyword"/>
            </td>
            <td class="content">
                <html:errors property="keyword"/>
                <bean:write name="nbproductsForm" property="keyword" scope="request"/>

            </td>
        </tr>

        <tr>
            <td class="label">
                <bean:message bundle="nbproducts" key="nbproducts.businessSort"/>
            </td>
            <td class="content">
                <html:errors property="businessSort"/>
                <eoms:id2nameDB id="${nbproductsForm.businessSort}" beanId="ItawSystemDictTypeDao"/>
            </td>
            <td class="label">
                <bean:message bundle="nbproducts" key="nbproducts.businessSortTwo"/>
            </td>
            <td class="content">
                <html:errors property="businessSort"/>
                <eoms:id2nameDB id="${nbproductsForm.businessSortTwo}" beanId="ItawSystemDictTypeDao"/>
            </td>
        </tr>


        <tr>
            <td class="label">
                <bean:message bundle="nbproducts" key="nbproducts.recorder"/>
            </td>
            <td class="content">
                <html:errors property="recorder"/>
                <eoms:id2nameDB id="${nbproductsForm.recorder}" beanId="tawSystemUserDao"/>
            </td>
            <td class="label">
                <bean:message bundle="nbproducts" key="nbproducts.recordTime"/>
            </td>
            <td class="content">
                <html:errors property="recordTime"/>
                <bean:write name="nbproductsForm" property="recordTime" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message bundle="nbproducts" key="nbproducts.recorderDept"/>
            </td>
            <td class="content">
                <html:errors property="recorderDept"/>
                <bean:write name="nbproductsForm" property="recorderDept" scope="request"/>
            </td>
            <td class="label">
                <bean:message bundle="nbproducts" key="nbproducts.recorderContact"/>
            </td>
            <td class="content">
                <html:errors property="recorderContact"/>
                <bean:write name="nbproductsForm" property="recorderContact" scope="request"/>
            </td>

        <tr>
            <td class="label">
                <bean:message bundle="nbproducts" key="nbproducts.nbPdescription"/>
            </td>
            <td class="content" colspan="3">
                <html:errors property="nbPdescription"/>
                <bean:write name="nbproductsForm" property="nbPdescription" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message bundle="nbproducts" key="nbproducts.remarks"/>
            </td>
            <td class="content" colspan="3">
                <html:errors property="remarks"/>
                <bean:write name="nbproductsForm" property="remarks" scope="request"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="nbproducts" key="nbproducts.node"/></td>
            <td colspan='3'>
                <eoms:attachment name="nbproductsForm" property="node"
                                 scope="request" idField="node" appCode="nbproducts"
                                 viewFlag="Y"/>
            </td>
        </tr>
    </table>
    <table>
        <tr>
            <td>
                <input type="button" value="${eoms:a2u('相关工单')}" onclick="query();">
                <logic:empty name="type">
                <input type="button" style="margin-right: 5px"
                       onclick="location.href='<c:url
                               value="/sheet/nBProducts/nbproductss.do?method=xqueryDeleted&ifReference=${ifReference}"/>'"
                       value="${eoms:a2u('返回')}"/>
                </logic:empty>
        </tr>
    </table>
</html:form>

<!-- footer_eoms.jsp end-->