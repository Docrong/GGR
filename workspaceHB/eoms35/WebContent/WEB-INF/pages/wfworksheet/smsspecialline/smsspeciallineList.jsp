<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
           onclick="location.href='<html:rewrite page='/smsspeciallines.do?method=add'/>'"
           value="<fmt:message key="button.add"/>"/>
</c:set>

<fmt:bundle basename="config/applicationResource-smsspecialline">

    <content tag="heading">
        <fmt:message key="smsspecialline.list.heading"/>
    </content>

    <display:table name="smsspeciallineList" cellspacing="0" cellpadding="0"
                   id="smsspeciallineList" pagesize="${pageSize}" class="table smsspeciallineList"
                   export="false"
                   requestURI="${app}/smsspecialline/smsspeciallines.do?method=search"
                   sort="list" partialList="true" size="resultSize">

        <display:column property="cableModem" sortable="true"
                        headerClass="sortable" titleKey="smsspecialline.cableModem"
                        href="${app}/smsspecialline/smsspeciallines.do?method=edit" paramId="id" paramProperty="id"/>

        <display:column property="scopeOfBusiness" sortable="true"
                        headerClass="sortable" titleKey="smsspecialline.scopeOfBusiness"
                        href="${app}/smsspecialline/smsspeciallines.do?method=edit" paramId="id" paramProperty="id"/>

        <display:column property="hostAddress" sortable="true"
                        headerClass="sortable" titleKey="smsspecialline.hostAddress"
                        href="${app}/smsspecialline/smsspeciallines.do?method=edit" paramId="id" paramProperty="id"/>

        <display:column property="upUrl" sortable="true"
                        headerClass="sortable" titleKey="smsspecialline.upUrl"
                        href="${app}/smsspecialline/smsspeciallines.do?method=edit" paramId="id" paramProperty="id"/>

        <display:column property="provisionURL" sortable="true"
                        headerClass="sortable" titleKey="smsspecialline.provisionURL"
                        href="${app}/smsspecialline/smsspeciallines.do?method=edit" paramId="id" paramProperty="id"/>

        <display:column property="ifCable" sortable="true"
                        headerClass="sortable" titleKey="smsspecialline.ifCable"
                        href="${app}/smsspecialline/smsspeciallines.do?method=edit" paramId="id" paramProperty="id"/>

        <display:column property="useAccord" sortable="true"
                        headerClass="sortable" titleKey="smsspecialline.useAccord"
                        href="${app}/smsspecialline/smsspeciallines.do?method=edit" paramId="id" paramProperty="id"/>

        <display:column property="businessLimit" sortable="true"
                        headerClass="sortable" titleKey="smsspecialline.businessLimit"
                        href="${app}/smsspecialline/smsspeciallines.do?method=edit" paramId="id" paramProperty="id"/>

        <display:column property="businessName" sortable="true"
                        headerClass="sortable" titleKey="smsspecialline.businessName"
                        href="${app}/smsspecialline/smsspeciallines.do?method=edit" paramId="id" paramProperty="id"/>

        <display:column property="businessCode" sortable="true"
                        headerClass="sortable" titleKey="smsspecialline.businessCode"
                        href="${app}/smsspecialline/smsspeciallines.do?method=edit" paramId="id" paramProperty="id"/>

        <display:column property="ipAdressType" sortable="true"
                        headerClass="sortable" titleKey="smsspecialline.ipAdressType"
                        href="${app}/smsspecialline/smsspeciallines.do?method=edit" paramId="id" paramProperty="id"/>

        <display:column property="ipAdress" sortable="true"
                        headerClass="sortable" titleKey="smsspecialline.ipAdress"
                        href="${app}/smsspecialline/smsspeciallines.do?method=edit" paramId="id" paramProperty="id"/>

        <display:column property="billingType" sortable="true"
                        headerClass="sortable" titleKey="smsspecialline.billingType"
                        href="${app}/smsspecialline/smsspeciallines.do?method=edit" paramId="id" paramProperty="id"/>

        <display:column property="informationFeeds" sortable="true"
                        headerClass="sortable" titleKey="smsspecialline.informationFeeds"
                        href="${app}/smsspecialline/smsspeciallines.do?method=edit" paramId="id" paramProperty="id"/>

        <display:column property="illustrate" sortable="true"
                        headerClass="sortable" titleKey="smsspecialline.illustrate"
                        href="${app}/smsspecialline/smsspeciallines.do?method=edit" paramId="id" paramProperty="id"/>

        <display:column property="productNum" sortable="true"
                        headerClass="sortable" titleKey="smsspecialline.productNum"
                        href="${app}/smsspecialline/smsspeciallines.do?method=edit" paramId="id" paramProperty="id"/>

        <display:setProperty name="paging.banner.item_name" value="smsspecialline"/>
        <display:setProperty name="paging.banner.items_name" value="smsspeciallines"/>
    </display:table>

    <c:out value="${buttons}" escapeXml="false"/>

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp" %>