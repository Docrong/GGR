
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<content tag="heading"><fmt:message key="taskplanList.heading"/></content>
 

<!-- <c:out value="${buttons}" escapeXml="false"/> -->

<display:table name="taskplanList" cellspacing="0" cellpadding="0"
    id="taskplanList" pagesize="25" class="table taskplanList"
    export="true" requestURI="/taskplans.html" sort="external" partialList="true" size="resultSize">
	
	<display:column property="project_name" sortable="true" headerClass="sortable"
        url="/taskplan/taskplans.do?method=edit" paramId="id" paramProperty="id"
         titleKey="taskplanForm.project_name"/>
         
    <display:column property="project_decompose" sortable="true" headerClass="sortable"
     titleKey="taskplanForm.project_decompose"/>

    <display:column property="deptName" sortable="true" headerClass="sortable"
        titleKey="taskplanForm.deptid"/>

	<display:column property="stakeholdersName" sortable="true" headerClass="sortable"
        titleKey="taskplanForm.stakeholders"/>

    <display:column property="task_plan" sortable="true" headerClass="sortable"
       titleKey="taskplanForm.task_plan"/>

    <display:column property="task_complete" sortable="true" headerClass="sortable"
      titleKey="taskplanForm.task_complete"/>

	<display:column property="month_mark" sortable="true" headerClass="sortable"
     titleKey="taskplanForm.month_mark"/>
     
     <display:column property="insert_time" sortable="true" headerClass="sortable"
      titleKey="taskplanForm.insert_time"/>
    
    <display:setProperty name="paging.banner.item_name" value="taskplan"/>
    <display:setProperty name="paging.banner.items_name" value="taskplans"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>

<%@ include file="/common/footer_eoms.jsp"%>

