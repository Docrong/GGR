<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="java.util.List" %>
<script type="text/javascript">

    Ext.onReady(function () {
        var deptTreeCfg = {
            btnId: 'btn',
            dlgId: 'hello-dlg2',
            treeDataUrl: '${app}/xtree.do?method=dept',
            treeRootId: '-1',
            treeRootText: 'dept',
            treeChkMode: 'single',
            treeChkType: 'dept',
            showChkFldId: 'deptName',
            saveChkFldId: 'deptId'
        }
        var userTree = new xbox(deptTreeCfg);
    });

    function filter(value) {
        location.href = '<c:url value="/sheet/sheetLimit/sheetLimit.do?method=getSpecialty&moduleId="/>' + value;
        //alert(obj);
    }

    function fz(obj) {

        alert(obj);
    }
</script>
<fmt:setBundle basename="config/ApplicationResources-sheet-sheetLimit" var="sheetLimit"/>

<title><fmt:message bundle="${sheetLimit}" key="sheetLimit.title"/></title>
<content tag="heading"><fmt:message bundle="${sheetLimit}" key="sheetLimit.title"/></content>
<html:form action="saveSheetLimit" method="post" styleId="sheetLimitForm">
    <ul>
        <html:hidden property="id"/>

        <li>
            <eoms:label styleClass="desc" bundle="sheetLimit" key="sheetLimit.moudleId"/>
            <html:errors property="moudleId"/>
            <!-- <html:text property="moudleId" styleId="moudleId" styleClass="text medium"/>-->

            <c:set var="mid" value="${param.moduleId}"/>
            <c:if test="${!empty sheetLimitForm.moudleId}">
                <c:set var="mid" value="${sheetLimitForm.moudleId}"/>
            </c:if>

            <html:select property="moudleId" value="${mid}" onchange="filter(this.value)">
                <html:option value="1">please select sheet</html:option>
                <html:option value="2">${eoms:a2u("应急与事件管理流程")}</html:option>
                <html:option value="3">${eoms:a2u("网络优化流程")}</html:option>
                <html:option value="4">${eoms:a2u("供应商管理流程")}</html:option>
                <html:option value="5">${eoms:a2u("紧急故障管理流程")}</html:option>
            </html:select>
        </li>

        <li>
            <eoms:label styleClass="desc" bundle="sheetLimit" key="sheetLimit.deptId"/>
            <html:errors property="deptId"/>

            <input type="text" id="deptName" readonly="readonly" name="deptName" class="text"
                   value="<eoms:id2nameDB id="${sheetLimitForm.deptId}" beanId="tawSystemDeptDao" />"/>
            <input type="hidden" id="deptId" name="deptId" value="${sheetLimitForm.deptId}"/>

            <input type="button" id="btn" value="<fmt:message key="button.select"/>">

        </li>

        <logic:present name="specialtyList" scope="request">
            <logic:iterate id="roleFilter" name="specialtyList" type="com.boco.eoms.commons.system.role.util.RoleFilter"
                           length="4" offset="0" indexId="number">
                <div style="margin-bottom:10px"><label>${roleFilter.name}</label>
                    <div>
                        <c:choose>
                            <c:when test="${number=='0'}">
                                <eoms:comboBox name="specialty1" id="${roleFilter.businessName}"
                                               defaultValue="${sheetLimitForm.specialty1}"
                                               initDicId="${roleFilter.dictId}"
                                               sub="${roleFilter.sub}" size="1" styleClass="select-class"/>
                            </c:when>
                            <c:when test="${number=='1'}">
                                <eoms:comboBox name="specialty2" id="${roleFilter.businessName}"
                                               defaultValue="${sheetLimitForm.specialty2}"
                                               initDicId="${sheetLimitForm.specialty1}"
                                               sub="${roleFilter.sub}" size="1" styleClass="select-class"/>
                            </c:when>
                            <c:when test="${number=='2'}">
                                <eoms:comboBox name="specialty3" id="${roleFilter.businessName}"
                                               defaultValue="${sheetLimitForm.specialty3}"
                                               initDicId="${sheetLimitForm.specialty2}"
                                               sub="${roleFilter.sub}" size="1" styleClass="select-class"/>
                            </c:when>
                            <c:otherwise>
                                <eoms:comboBox name="specialty4" id="${roleFilter.businessName}"
                                               defaultValue="${sheetLimitForm.specialty4}"
                                               initDicId="${sheetLimitForm.specialty3}"
                                               sub="${roleFilter.sub}" size="1" styleClass="select-class"/>
                            </c:otherwise>
                        </c:choose>

                    </div>
                </div>
            </logic:iterate>
        </logic:present>
        <li>
            <eoms:label styleClass="desc" bundle="sheetLimit" key="sheetLimit.stepId"/>
            <html:errors property="stepId"/>
            <eoms:comboBox name="stepId" id="stepId" initDicId="10121" sub="roleName"
                           defaultValue="${sheetLimitForm.stepId}" styleClass="select-class"/>
        </li>

        <li>
            <eoms:label styleClass="desc" bundle="sheetLimit" key="sheetLimit.roleName"/>
            <html:errors property="roleName"/>
            <eoms:comboBox name="roleName" id="roleName"
                           initDicId="stepId" defaultValue="${sheetLimitForm.roleName}" styleClass="select-class"/>
        </li>
        <!--
   <li>
        <eoms:label styleClass="desc" bundle="sheetLimit" key="sheetLimit.specialty1"/>
        <html:errors property="specialty1"/>
        <html:text property="specialty1" styleId="specialty1" styleClass="text medium"/>
    </li>

    <li>
        <eoms:label styleClass="desc" bundle="sheetLimit" key="sheetLimit.specialty2"/>
        <html:errors property="specialty2"/>
        <html:text property="specialty2" styleId="specialty2" styleClass="text medium"/>
    </li>

    <li>
        <eoms:label styleClass="desc" bundle="sheetLimit" key="sheetLimit.specialty1"/>
        <html:errors property="specialty1"/>
        <html:text property="specialty1" styleId="specialty1" styleClass="text medium"/>
    </li>

    <li>
        <eoms:label styleClass="desc" bundle="sheetLimit" key="sheetLimit.specialty2"/>
        <html:errors property="specialty2"/>
        <html:text property="specialty2" styleId="specialty2" styleClass="text medium"/>
    </li>

    <li>
        <eoms:label styleClass="desc" bundle="sheetLimit" key="sheetLimit.specialty3"/>
        <html:errors property="specialty3"/>
        <html:text property="specialty3" styleId="specialty3" styleClass="text medium"/>
    </li>

    <li>
        <eoms:label styleClass="desc" bundle="sheetLimit" key="sheetLimit.specialty4"/>
        <html:errors property="specialty4"/>
        <html:text property="specialty4" styleId="specialty4" styleClass="text medium"/>
    </li>
     
-->
        <li>
            <eoms:label styleClass="desc" bundle="sheetLimit" key="sheetLimit.acceptLimit"/>
            <html:errors property="acceptLimit"/>
            <html:text property="acceptLimit" styleId="acceptLimit" styleClass="text medium"/>
        </li>

        <li>
            <eoms:label styleClass="desc" bundle="sheetLimit" key="sheetLimit.replyLimit"/>
            <html:errors property="replyLimit"/>
            <html:text property="replyLimit" styleId="replyLimit" styleClass="text medium"/>
        </li>
        <li class="buttonBar bottom">
            <html:submit styleClass="button" property="method.save" onclick="bCancel=false">
                <fmt:message key="button.save"/>
            </html:submit>

            <html:submit styleClass="button" property="method.delete" onclick="bCancel=true">
                <fmt:message key="button.delete"/>
            </html:submit>

            <html:cancel styleClass="button" onclick="bCancel=true">
                <fmt:message key="button.cancel"/>
            </html:cancel>
        </li>

    </ul>

</html:form>


<%@ include file="/common/footer_eoms.jsp" %>