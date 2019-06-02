<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawSystemCptroomList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/cptroom/editTawSystemCptroom.do"/>'"
        value="<fmt:message key="button.add"/>"/>

    <input type="button" onclick="location.href='<html:rewrite forward="mainMenu"/>'"
        value="<fmt:message key="button.done"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawSystemCptroomList" cellspacing="0" cellpadding="0"
    id="tawSystemCptroomList" pagesize="25" class="table tawSystemCptroomList"
    export="true" requestURI="/tawSystemCptrooms.do" sort="external" partialList="true" size="resultSize">

    <display:column property="address" sortable="true" headerClass="sortable"
        url="/editTawSystemCptroom.do" paramId="id" paramProperty="id"
         titleKey="tawSystemCptroomForm.address"/>

    <display:column property="deleted" sortable="true" headerClass="sortable"
        url="/editTawSystemCptroom.do" paramId="id" paramProperty="id"
         titleKey="tawSystemCptroomForm.deleted"/>

    <display:column property="deptid" sortable="true" headerClass="sortable"
        url="/editTawSystemCptroom.do" paramId="id" paramProperty="id"
         titleKey="tawSystemCptroomForm.deptid"/>

    <display:column property="endtime" sortable="true" headerClass="sortable"
        url="/editTawSystemCptroom.do" paramId="id" paramProperty="id"
         titleKey="tawSystemCptroomForm.endtime"/>

    <display:column property="fax" sortable="true" headerClass="sortable"
        url="/editTawSystemCptroom.do" paramId="id" paramProperty="id"
         titleKey="tawSystemCptroomForm.fax"/>

    <display:column property="manager" sortable="true" headerClass="sortable"
        url="/editTawSystemCptroom.do" paramId="id" paramProperty="id"
         titleKey="tawSystemCptroomForm.manager"/>

    <display:column property="mobile" sortable="true" headerClass="sortable"
        url="/editTawSystemCptroom.do" paramId="id" paramProperty="id"
         titleKey="tawSystemCptroomForm.mobile"/>

    <display:column property="notes" sortable="true" headerClass="sortable"
        url="/editTawSystemCptroom.do" paramId="id" paramProperty="id"
         titleKey="tawSystemCptroomForm.notes"/>

    <display:column property="phone" sortable="true" headerClass="sortable"
        url="/editTawSystemCptroom.do" paramId="id" paramProperty="id"
         titleKey="tawSystemCptroomForm.phone"/>

    <display:column property="roomname" sortable="true" headerClass="sortable"
        url="/editTawSystemCptroom.do" paramId="id" paramProperty="id"
         titleKey="tawSystemCptroomForm.roomname"/>

    <display:column property="tempmanager" sortable="true" headerClass="sortable"
        url="/editTawSystemCptroom.do" paramId="id" paramProperty="id"
         titleKey="tawSystemCptroomForm.tempmanager"/>

    <display:column property="leaf" sortable="true" headerClass="sortable"
        url="/editTawSystemCptroom.do" paramId="id" paramProperty="id"
         titleKey="tawSystemCptroomForm.leaf"/>

    <display:column property="parentid" sortable="true" headerClass="sortable"
        url="/editTawSystemCptroom.do" paramId="id" paramProperty="id"
         titleKey="tawSystemCptroomForm.parentid"/>

    <display:setProperty name="paging.banner.item_name" value="tawSystemCptroom"/>
    <display:setProperty name="paging.banner.items_name" value="tawSystemCptrooms"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawSystemCptroomList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>