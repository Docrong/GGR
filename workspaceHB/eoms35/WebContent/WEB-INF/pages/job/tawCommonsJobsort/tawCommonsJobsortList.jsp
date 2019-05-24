<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading">
  <fmt:message key="tawCommonsJobsortList.heading"/>
</content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/job/editTawCommonsJobsort.do"/>'"
        value="<fmt:message key="button.add"/>"/>
</c:set>
<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawCommonsJobSortList" cellspacing="0" cellpadding="0"
    id="tawCommonsJobSortList" pagesize="25" class="table tawCommonsJobSortList"
    export="true" requestURI="" sort="list">

    <display:column property="jobSortName" url="/job/editTawCommonsJobsort.do" 
         paramId="id" paramProperty="id"
         sortable="true" headerClass="sortable"
         titleKey="tawCommonsJobsortForm.jobSortName"/>

    <display:column property="jobClassName" sortable="true" headerClass="sortable"
         titleKey="tawCommonsJobsortForm.jobClassName"/>
   
    <display:column property="maxExecuteTime" sortable="true" headerClass="sortable"
         titleKey="tawCommonsJobsortForm.maxExecuteTime"/>
   
    <display:column property="remark" sortable="true" headerClass="sortable"
         titleKey="tawCommonsJobsortForm.remark"/>

    <display:setProperty name="paging.banner.item_name" value="tawCommonsJobsort"/>
    <display:setProperty name="paging.banner.items_name" value="tawCommonsJobsorts"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawCommonsJobSortList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>