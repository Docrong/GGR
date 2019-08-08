<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
           onclick="location.href='<html:rewrite page='/tawContracts.do?method=add'/>'"
           value="<fmt:message key="button.add"/>"/>
</c:set>

<fmt:bundle basename="config/applicationResource-tawcontract">

    <content tag="heading">
        <fmt:message key="tawContract.list.heading"/>
    </content>


    <html:form action="tawContracts.do?method=xquery" method="post"
               styleId="tawContractForm">
        <table class="formTable">
            <caption>
                <div class="header center">
                    <fmt:message key="tawContract.list.heading"/>
                </div>
            </caption>
            <tr>
                <td class="label">
                    <fmt:message key="tawContract.contractName"/>
                </td>
                <td class="content">
                    <html:text property="contractName" styleId="contractName"
                               styleClass="text medium" alt="allowBlank:false,vtext:''"
                               value="${tawContractForm.contractName}"/>
                </td>
                <td class="label">
                    <fmt:message key="tawContract.name_A"/>
                </td>
                <td class="content">
                    <html:text property="name_A" styleId="name_A"
                               styleClass="text medium" alt="allowBlank:false,vtext:''"
                               value="${tawContractForm.name_A}"/>
                </td>
            </tr>
            <tr>
                <td class="label">
                    <fmt:message key="tawContract.name_B"/>
                </td>
                <td class="content">
                    <html:text property="name_B" styleId="name_B"
                               styleClass="text medium" alt="allowBlank:false,vtext:''"
                               value="${tawContractForm.name_B}"/>
                </td>
                <td class="label">
                    <fmt:message key="tawContract.creator"/>
                </td>
                <td class="content">
                    <html:text property="creator" styleId="creator"
                               styleClass="text medium" alt="allowBlank:false,vtext:''"
                               value="${tawContractForm.creator}"/>
                </td>
            </tr>
            <tr>
                <td colspan="4" align="center">
                    <html:submit styleClass="button" property="method.query">
                    查询
                    </html:submit>
            </tr>
        </table>
    </html:form>
    <display:table name="tawContractList" cellspacing="0" cellpadding="0"
                   id="tawContractList" pagesize="${pageSize}"
                   class="table tawContractList" export="false"
                   requestURI="${app}/contract/tawContracts.do?method=search" sort="list"
                   partialList="true" size="resultSize">

        <display:column sortable="true" headerClass="sortable"
                        titleKey="tawContract.contractName">
            <html:link href="${app}/contract/tawContracts.do?method=detail"
                       paramId="id" paramProperty="id" paramName="tawContractList"
                       onclick="showDetail(this);return false;">
                ${tawContractList.contractName}
            </html:link>

        </display:column>

        <display:column property="name_A" sortable="true"
                        headerClass="sortable" titleKey="tawContract.name_A"/>

        <display:column property="name_B" sortable="true"
                        headerClass="sortable" titleKey="tawContract.name_B"/>

        <display:column sortable="true" headerClass="sortable"
                        titleKey="tawContract.payinfo">
            <html:link href="${app}/contract/tawContractPays.do?method=payinfo"
                       paramId="id" paramProperty="id" paramName="tawContractList"
                       onclick="showDetail(this);return false;">
                付费信息
            </html:link>
        </display:column>

        <display:column property="cost" sortable="true" headerClass="sortable"
                        titleKey="tawContract.cost"/>

        <display:column property="payType" sortable="true"
                        headerClass="sortable" titleKey="tawContract.payType"/>

        <display:column property="payCycle" sortable="true"
                        headerClass="sortable" titleKey="tawContract.payCycle"/>


        <display:column property="timeLimit" sortable="true"
                        headerClass="sortable" titleKey="tawContract.timeLimit"/>


        <display:column sortable="true" headerClass="sortable"
                        titleKey="tawContract.creator">
            <eoms:id2nameDB id="${tawContractList.creator}"
                            beanId="tawSystemUserDao"/>
        </display:column>

        <display:column property="payed" sortable="true"
                        headerClass="sortable" titleKey="tawContract.payed"/>
        <display:column headerClass="sortable" titleKey="tawContract.edit">
            <html:link href="${app}/contract/tawContracts.do?method=edit"
                       paramId="id" paramProperty="id" paramName="tawContractList"
                       onclick="showDetail(this);return false;">
                编辑
            </html:link>
        </display:column>
        <display:column headerClass="sortable" titleKey="tawContract.deleted">
            <html:link href="${app}/contract/tawContracts.do?method=remove"
                       paramId="id" paramProperty="id" paramName="tawContractList"
                       onclick="javascrip:return confirm('确定要删除？')">
                删除
            </html:link>
        </display:column>
        <display:setProperty name="paging.banner.item_name"
                             value="tawContract"/>
        <display:setProperty name="paging.banner.items_name"
                             value="tawContracts"/>
    </display:table>

    <c:out value="${buttons}" escapeXml="false"/>

</fmt:bundle>
<script type="text/javascript">
    function showDetail(link) {
        var par = "height=600,width=900,status=yes,toolbar=no,menubar=no,location=no,resizable=yes,scrollbars=yes";
        var yingjiDrillDetail = window.open(link, 'ExceptionReport', par);
        yingjiDrillDetail.focus();
    }
</script>
<%@ include file="/common/footer_eoms.jsp" %>
