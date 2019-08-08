<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ page import="java.util.List" %>
<script type="text/javascript">
    <!--验证-->
    function initPage() {
        v = new eoms.form.Validation({form: 'nbproductsForm'});
    }

    Ext.onReady(
        function () {
            initPage();
        }
    );

    function query() {
        var formObj = document.forms[0];
        formObj.action = "nbproductss.do?method=relationSheet";
        formObj.submit();
    }

</script>
<%String flag = (String) request.getAttribute("flag");%>
<% String ifReference = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("ifReference"));
    if (ifReference.equals(""))
        ifReference = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifReference"));
    request.setAttribute("ifReference", ifReference);
    System.out.println("form ifReference=" + ifReference);
%>
<title><fmt:message key="nbproductsDetail.title"/></title>
<content tag="heading">新业务产品信息</content>
<html:form action="nbproductss.do?method=xsave&ifReference=${ifReference}" method="post" styleId="nbproductsForm">
    <br>
    <table class="formTable">
        <input type="hidden" id="id" name="id" value="${nbproductsForm.id}"/>
        <input type="hidden" id="procode" name="procode" value="${nbproductsForm.procode}"/>
        <input type="hidden" id="code" name="code" value="${nbproductsForm.code}"/>
        <tr>
            <td class="label">
                <bean:message bundle="nbproducts" key="nbproducts.nbPName"/>*
            </td>
            <td class="content">
                <html:errors property="nbPName"/>
                <html:text property="nbPName" styleId="nbPName" styleClass="text medium" alt="allowBlank:false"/>
            </td>
            <td class="label">
                <bean:message bundle="nbproducts" key="nbproducts.businessDept"/>*
            </td>
            <td class="content">
                <html:errors property="businessDept"/>
                <eoms:comboBox name="businessDept" id="${nbproductsForm.businessDept}"
                               defaultValue="${nbproductsForm.businessDept}" initDicId="1010504"
                               sub="${sheetPageName}mainNetSort3" size="1" alt="allowBlank:false"
                               styleClass="select-class"/>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message bundle="nbproducts" key="nbproducts.businessSort"/>*
            </td>
            <td class="content">
                <html:errors property="businessSort"/>
                <eoms:comboBox name="businessSort" id="businessSort" defaultValue="${nbproductsForm.businessSort}"
                               initDicId="1010515"
                               sub="businessSortTwo" alt="allowBlank:false" styleClass="select-class"/>
            </td>
            <td class="label">
                <bean:message bundle="nbproducts" key="nbproducts.businessSortTwo"/>*
            </td>
            <td class="content">
                <html:errors property="businessSortTwo"/>
                <eoms:comboBox name="businessSortTwo" id="businessSortTwo"
                               defaultValue="${nbproductsForm.businessSortTwo}"
                               initDicId="${nbproductsForm.businessSort}" alt="allowBlank:false"
                               styleClass="select-class"/>
            </td>
        </tr>

        <tr>
            <td class="label">
                <bean:message bundle="nbproducts" key="nbproducts.keyword"/>*
            </td>
            <td class="content" colspan="3">
                <html:errors property="keyword"/>
                <html:text property="keyword" styleId="keyword" styleClass="text medium" alt="allowBlank:false"/>
            </td>
        </tr>

        <tr>
            <td class="label">
                <bean:message bundle="nbproducts" key="nbproducts.nbPdescription"/>*
            </td>
            <td class="content" colspan="3">
                <textarea name="nbPdescription" id="nbPdescription" class="textarea max"
                          alt="allowBlank:false">${nbproductsForm.nbPdescription}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message bundle="nbproducts" key="nbproducts.remarks"/>
            </td>
            <td class="content" colspan="3">
                <textarea name="remarks" id="remarks" class="textarea max">${nbproductsForm.remarks}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message bundle="nbproducts" key="nbproducts.node"/>
            </td>
            <td class="content" colspan="3">
                <html:errors property="node"/>
                <eoms:attachment name="nbproductsForm" property="node"
                                 scope="request" idField="node" appCode="nbproducts"/>
            </td>
        </tr>
    </table>
    <br/>

    <input type="submit" styleClass="submit" property="method.xsave"
           onclick="return;location.href='<c:url
                   value="/sheet/nBProducts/nbproductss.do?method=xquery&ifReference=${ifReference}"/>'" value="保存"/>
    <input type="button" styleClass="button"
           onclick="location.href='<c:url
                   value="/sheet/nBProducts/nbproductss.do?method=xquery&ifReference=${ifReference}"/>'"
           value="返回"/>
</html:form>

<!-- footer_eoms.jsp end-->
