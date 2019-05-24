<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="tawCommonsJobsubscibeList.heading"/></content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/job/editTawCommonsJobsubscibe.do"/>'"
        value="<fmt:message key="button.add"/>"/>

</c:set>

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="tawCommonsJobSubscibeList" cellspacing="0" cellpadding="0"
    id="tawCommonsJobsubscibeList" pagesize="25" class="table tawCommonsJobsubscibeList"
    export="true" requestURI="" sort="list">

   <display:column property="subId" url="/job/editTawCommonsJobsubscibe.do" 
      paramId="id" paramProperty="id"
      sortable="true" headerClass="sortable"
      titleKey="tawCommonsJobsubscibeForm.subId"/>

    <display:column property="jobSortId" sortable="true" headerClass="sortable"
         titleKey="tawCommonsJobsubscibeForm.jobSortId"/>

    <display:column property="jobCycle" sortable="true" headerClass="sortable"
         titleKey="tawCommonsJobsubscibeForm.jobCycle"/>

    <display:column property="jobType" sortable="true" headerClass="sortable"
         titleKey="tawCommonsJobsubscibeForm.jobType"/>
   
    <display:column property="subscriberDeptId" sortable="true" headerClass="sortable"
         titleKey="tawCommonsJobsubscibeForm.subscriberDeptId"/>

    <display:column property="subscriberId" sortable="true" headerClass="sortable"
         titleKey="tawCommonsJobsubscibeForm.subscriberId"/>

    <display:column property="subscribeTime" sortable="true" headerClass="sortable"
         titleKey="tawCommonsJobsubscibeForm.subscribeTime"/>

    <display:setProperty name="paging.banner.item_name" value="tawCommonsJobsubscibe"/>
    <display:setProperty name="paging.banner.items_name" value="tawCommonsJobsubscibes"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<script type="text/javascript">
    highlightTableRows("tawCommonsJobsubscibeList");
</script>

<%@ include file="/common/footer_eoms.jsp"%>