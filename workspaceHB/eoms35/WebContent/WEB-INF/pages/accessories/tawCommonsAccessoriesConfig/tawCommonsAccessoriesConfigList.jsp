<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<content tag="heading">
 <bean:message key="tawCommonsAccessoriesConfigList.heading" />
</content>
<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/accessories/editTawCommonsAccessoriesConfig.do"/>'"
        value="<fmt:message key="button.add"/>"/>
</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawCommonsAccessoriesConfigList" cellspacing="0" cellpadding="0"
    id="tawCommonsAccessoriesConfigList" pagesize="25" class="table tawCommonsAccessoriesConfigList"
    export="true" requestURI="" sort="list">
   
    <display:column property="appCode" url="/accessories/editTawCommonsAccessoriesConfig.do" 
         paramId="id" paramProperty="id"
         sortable="true" headerClass="sortable"
         titleKey="tawCommonsAccessoriesConfigForm.appCode" />
     <display:column property="appName" sortable="true" headerClass="sortable"
         titleKey="tawCommonsAccessoriesConfigForm.appName"/>
    <display:column property="path" sortable="true" headerClass="sortable"
         titleKey="tawCommonsAccessoriesConfigForm.path"/>
    
    <display:column property="maxSize" sortable="true" headerClass="sortable"
         titleKey="tawCommonsAccessoriesConfigForm.maxSize"/>

    <display:column property="allowFileType" sortable="true" headerClass="sortable"
         titleKey="tawCommonsAccessoriesConfigForm.allowFileType"/>
    <display:setProperty name="paging.banner.item_name" value="tawCommonsAccessoriesConfig"/>
    <display:setProperty name="paging.banner.items_name" value="tawCommonsAccessoriesConfigs"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<%@ include file="/common/footer_eoms.jsp"%>