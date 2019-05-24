<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawSheetSpecialDetail.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/sheet/special/editTawSheetSpecial.do"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawSheetSpecialList" cellspacing="0" cellpadding="0"
    id="tawSheetSpecialList" pagesize="25" class="table tawSheetSpecialList"
    export="true" requestURI="/tawSheetSpecials.do" sort="external" partialList="true" size="resultSize">

    <display:column property="remark" sortable="true" headerClass="sortable"
        url="/sheet/special/editTawSheetSpecial.do" paramId="id" paramProperty="id"
         titleKey="tawSheetSpecialForm.remark"/>

    <display:column property="leaf" sortable="true" headerClass="sortable"
        url="/sheet/special/editTawSheetSpecial.do" paramId="id" paramProperty="id"
         titleKey="tawSheetSpecialForm.leaf"/>

    <display:column property="ordercode" sortable="true" headerClass="sortable"
        url="/sheet/special/editTawSheetSpecial.do" paramId="id" paramProperty="id"
         titleKey="tawSheetSpecialForm.ordercode"/>

    <display:column property="parspeid" sortable="true" headerClass="sortable"
        url="/sheet/special/editTawSheetSpecial.do" paramId="id" paramProperty="id"
         titleKey="tawSheetSpecialForm.parspeid"/>

    <display:column property="refuserid" sortable="true" headerClass="sortable"
        url="/sheet/special/editTawSheetSpecial.do" paramId="id" paramProperty="id"
         titleKey="tawSheetSpecialForm.refuserid"/>

    <display:column property="specialname" sortable="true" headerClass="sortable"
        url="/sheet/special/editTawSheetSpecial.do" paramId="id" paramProperty="id"
         titleKey="tawSheetSpecialForm.specialname"/>

    <display:column property="specialtype" sortable="true" headerClass="sortable"
        url="/sheet/special/editTawSheetSpecial.do" paramId="id" paramProperty="id"
         titleKey="tawSheetSpecialForm.specialtype"/>

    <display:column property="speid" sortable="true" headerClass="sortable"
        url="/sheet/special/editTawSheetSpecial.do" paramId="id" paramProperty="id"
         titleKey="tawSheetSpecialForm.speid"/>

    <display:column property="style" sortable="true" headerClass="sortable"
        url="/sheet/special/editTawSheetSpecial.do" paramId="id" paramProperty="id"
         titleKey="tawSheetSpecialForm.style"/>

    <display:setProperty name="paging.banner.item_name" value="tawSheetSpecial"/>
    <display:setProperty name="paging.banner.items_name" value="tawSheetSpecials"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawSheetSpecialList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>