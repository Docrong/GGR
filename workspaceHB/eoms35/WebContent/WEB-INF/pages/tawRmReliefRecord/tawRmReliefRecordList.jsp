<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<style type="text/css">
  	body{background-image:none;}
</style>
<html:form action="tawRmReliefRecord" method="post" styleId="tawRmReliefRecordForm"> 
<content tag="heading"><fmt:message key="tawRmReliefRecordList.heading"/></content>
<%
	String tmpHandoverId=request.getParameter("tmpHandoverId");
	String tmpSuccessorId = request.getParameter("tmpSuccessorId");
	String tmpRoomId = request.getParameter("tmpRoomId");
	String startTime = request.getParameter("startTime");
	String endTime = request.getParameter("endTime");
	String tmpTime = request.getParameter("tmpTime");
	session.setAttribute("myHandoverId",tmpHandoverId);
	session.setAttribute("mySuccessorId",tmpSuccessorId);
	session.setAttribute("myRoomId",tmpRoomId);
	session.setAttribute("myStartTime",startTime);
	session.setAttribute("myEndTime",endTime);
	session.setAttribute("myTime",tmpTime);
%>    
    
<display:table name="tawRmReliefRecordList" cellspacing="0" cellpadding="0"
    id="tawRmReliefRecordList" pagesize="${pageSize }" class="table tawRmReliefRecordList"
    export="true"  sort="list" partialList="true" size="resultSize"
    requestURI="${app}/duty/tawRmReliefRecord.do?method=searchList" 
    decorator="com.boco.eoms.duty.displaytag.support.DutyListDisplaytagDecorator" >
   

	<display:column property="handoverId" sortable="true" headerClass="sortable"
         titleKey="tawRmReliefRecordForm.handoverId" paramId="id" paramProperty="id" href="${app}/duty/tawRmReliefRecord.do?method=edit"/>
         
    <display:column property="successorId" sortable="true" headerClass="sortable"
         titleKey="tawRmReliefRecordForm.successorId"/>

    <display:column property="roomId" sortable="true" headerClass="sortable"
         titleKey="tawRmReliefRecordForm.roomId"/>

    <display:column property="time" sortable="true" headerClass="sortable"
         titleKey="tawRmReliefRecordForm.time"/>

    

    <display:setProperty name="paging.banner.item_name" value="thread"/>
    <display:setProperty name="paging.banner.items_name" value="threads"/>
</display:table>
 
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>

