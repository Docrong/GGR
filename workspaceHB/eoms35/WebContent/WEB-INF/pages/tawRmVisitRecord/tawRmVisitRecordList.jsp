<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ page import="com.boco.eoms.commons.system.user.service.ITawSystemUserManager"%>
<%@ page import="com.boco.eoms.base.util.ApplicationContextHolder"%>
<style type="text/css">
  	body{background-image:none;}
</style>
<html:form action="tawRmVisitRecord" method="post" styleId="tawRmVisitRecordForm"> 
<content tag="heading"><fmt:message key="tawRmVisitRecordList.heading"/></content>
<%
	String visitorName=request.getParameter("tmpVisitorName");
	String company=request.getParameter("tmpCompany");
	String visitTime=request.getParameter("tmpVisitTime");
	String leftTime=request.getParameter("tmpLeftTime");
	String receiver=request.getParameter("tmpReceiver");
	String roomId=request.getParameter("roomId");
	String startTime=request.getParameter("startTime");
	String endTime=request.getParameter("endTime");
	session.setAttribute("myTmpVisitorName",visitorName);
	session.setAttribute("myTmpCompany",company);
	session.setAttribute("myTmpVisitTime",visitTime);
	session.setAttribute("myTmpLeftTime",leftTime);
	session.setAttribute("myTmpReceiver",receiver);
	session.setAttribute("myRoomId",roomId);
	session.setAttribute("myStartTime",startTime);
	session.setAttribute("myEndTime",endTime);
%>
 
<display:table name="tawRmVisitRecordList" cellspacing="0" cellpadding="0"
    id="tawRmVisitRecordList" pagesize="${pageSize }" class="table tawRmVisitRecordList"
    export="true"  sort="list" partialList="true" size="resultSize"
    requestURI="${app}/duty/tawRmVisitRecord.do?method=searchList" 
    decorator="com.boco.eoms.duty.displaytag.support.VisitListDisplaytagDecorator" >


	<display:column property="visitorName" sortable="true" headerClass="sortable"
         titleKey="tawRmVisitRecordForm.visitorName" paramId="id" paramProperty="id" href="${app}/duty/tawRmVisitRecord.do?method=edit"/>
         
    <display:column property="company" sortable="true" headerClass="sortable"
         titleKey="tawRmVisitRecordForm.company"/>

    <display:column property="visitTime" sortable="true" headerClass="sortable"
         titleKey="tawRmVisitRecordForm.visitTime"/>

    <display:column property="leftTime" sortable="true" headerClass="sortable"
         titleKey="tawRmVisitRecordForm.leftTime"/>

    <display:column property="receiver" sortable="true" headerClass="sortable"
         titleKey="tawRmVisitRecordForm.receiver"/>

    <display:column property="userId" sortable="true" headerClass="sortable"
         titleKey="tawWorkbenchMemoForm.userid"/>

    

    <display:setProperty name="paging.banner.item_name" value="thread"/>
    <display:setProperty name="paging.banner.items_name" value="threads"/>
</display:table>
 
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>

