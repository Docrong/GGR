<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>

<fmt:bundle basename="config/ApplicationResources-sheet-sheetLimit">
<content tag="heading">
  <fmt:message key="sheetLimit.title"/>
</content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
        onclick="location.href='<c:url value="/sheet/sheetLimit/editSheetLimit.do"/>'"
        value="<fmt:message key="button.add"/>"/>
</c:set>


<display:table name="sheetLimitList" cellspacing="0" cellpadding="0"
    id="proxyList" pagesize="25" class="table sheetLimitList"
    export="true" requestURI="" sort="list" decorator="com.boco.eoms.sheet.tool.limit.webapp.action.SheetLimitListDisplaytagDecoratorHelper">
   
    <display:column property="moudleId"  paramId="id" paramProperty="id"
         sortable="true" headerClass="sortable" value=""
         titleKey="sheetLimit.moudleId"/>
         
    <display:column property="deptId" sortable="true" headerClass="sortable"
         titleKey="sheetLimit.deptId"/>
   
    <display:column property="specialty1" sortable="true" headerClass="sortable"
         titleKey="sheetLimit.specialty1"/>
    
   <display:column property="specialty2" sortable="true" headerClass="sortable"
         titleKey="sheetLimit.specialty2"/>
         
   <display:column property="specialty3" sortable="true" headerClass="sortable"
         titleKey="sheetLimit.specialty3"/>
         
   <display:column property="specialty4" sortable="true" headerClass="sortable"
         titleKey="sheetLimit.specialty4"/>
         
   <display:column property="stepId" sortable="true" headerClass="sortable"
         titleKey="sheetLimit.stepId"/>
         
   <display:column property="roleName" sortable="true" headerClass="sortable"
         titleKey="sheetLimit.roleName"/>
         
   <display:column property="acceptLimit" sortable="true" headerClass="sortable"
         titleKey="sheetLimit.acceptLimit"/>
         
   <display:column property="replyLimit" sortable="true" headerClass="sortable"
         titleKey="sheetLimit.replyLimit"/>

   
  </display:table>
 </fmt:bundle>
<c:out value="${buttons}" escapeXml="false"/>


<%@ include file="/common/footer_eoms.jsp"%>