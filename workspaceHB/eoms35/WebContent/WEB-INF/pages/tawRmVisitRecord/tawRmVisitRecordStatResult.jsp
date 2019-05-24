<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ page import="com.boco.eoms.commons.system.user.service.ITawSystemUserManager"%>
<%@ page import="com.boco.eoms.base.util.ApplicationContextHolder"%>
<style type="text/css">
  	body{background-image:none;}
</style>
<html:form action="tawRmVisitRecord" method="post" styleId="tawRmVisitRecordForm"> 
<content tag="heading"><fmt:message key="tawRmVisitRecordList.heading"/></content>
 
<display:table name="tawRmVisitStatList" cellspacing="0" cellpadding="0"
    id="tawRmVisitRecordList" pagesize="${pageSize}" class="table tawRmVisitStatList"
    export="true"  sort="list" partialList="true" size="resultSize"
    requestURI="${app}/duty/tawRmVisitRecord.do?method=visitRecordStatResult" 
    decorator="com.boco.eoms.duty.displaytag.support.VisitListDisplaytagDecorator" >


	<display:column property="roomId" sortable="true" headerClass="sortable"
         titleKey="tawWorkbenchMemoForm.roomid"/>
         
    <display:column property="companyStat" sortable="true" headerClass="sortable"
         titleKey="tawRmVisitRecordForm.company"/>

    <display:column property="dutymaster" sortable="true" headerClass="sortable"
         titleKey="tawWorkbenchMemoForm.dutymaster"/>

    <display:column property="dutydate" sortable="true" headerClass="sortable"
         titleKey="tawWorkbenchMemoForm.dutydate"/>

    <display:column property="timeDefined" sortable="true" headerClass="sortable"
         titleKey="tawWorkbenchMemoForm.timeDefined"/>

    <display:column property="visiterCount" sortable="true" headerClass="sortable"
         titleKey="tawWorkbenchMemoForm.visiterCount"/>

    <display:setProperty name="paging.banner.item_name" value="thread"/>
    <display:setProperty name="paging.banner.items_name" value="threads"/>
</display:table>
 
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>

