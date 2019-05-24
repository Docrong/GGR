
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<%--<content tag="heading"><fmt:message key="tawRmReplaceList.heading"/></content>


--%><!-- <c:out value="${buttons}" escapeXml="false"/> -->
<fmt:bundle basename="config/applicationResource-duty">
<display:table name="tawRmReplaceList" cellspacing="0" cellpadding="0"
    id="tawRmReplaceList" pagesize="15" class="table tawRmReplaceList"
    export="true" requestURI="/tawRmReplaces.do" sort="external" partialList="true" size="resultSize">
    
	<display:column property="id" title="<input type='checkbox' onclick='javascript:choose();'/>" />
    <display:column property="dutydate" sortable="true" headerClass="sortable"
         titleKey="tawRmReplaceForm.dutydate"/>

    <display:column property="flag" sortable="true" headerClass="sortable"
         titleKey="tawRmReplaceForm.flag"/>

    <display:column property="hander" sortable="true" headerClass="sortable"
         titleKey="tawRmReplaceForm.hander"/>

    <display:column property="inputdate" sortable="true" headerClass="sortable"
         titleKey="tawRmReplaceForm.inputdate"/>

    <display:column property="reason" sortable="true" headerClass="sortable"
         titleKey="tawRmReplaceForm.reason"/>

    <display:column property="receiver" sortable="true" headerClass="sortable"
         titleKey="tawRmReplaceForm.receiver"/>

    <display:column property="remark" sortable="true" headerClass="sortable"
         titleKey="tawRmReplaceForm.remark"/>

    <display:column property="roomId" sortable="true" headerClass="sortable"
         titleKey="tawRmReplaceForm.roomId"/>

    <display:column property="workserial" sortable="true" headerClass="sortable"
         titleKey="tawRmReplaceForm.workserial"/>

    <display:setProperty name="paging.banner.item_name" value="tawRmReplace"/>
    <display:setProperty name="paging.banner.items_name" value="tawRmReplaces"/>
</display:table>
</fmt:bundle>
<c:out value="${buttons}" escapeXml="false"/>

<%@ include file="/common/footer_eoms.jsp"%>

