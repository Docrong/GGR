<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<%@ page import="com.boco.eoms.commons.system.user.service.ITawSystemUserManager"%>
<%@ page import="com.boco.eoms.base.util.ApplicationContextHolder"%>
<style type="text/css">
  	body{background-image:none;}
</style>
<html:form action="tawRmDispatchRecord" method="post" styleId="tawRmDispatchRecordForm"> 
<content tag="heading"><fmt:message key="tawRmDispatchRecordList.heading"/></content>
<%
	ITawSystemUserManager userManager=(ITawSystemUserManager)ApplicationContextHolder.getInstance().getBean("itawSystemUserManager");
	//ITawSystemDictTypeManager dictManager=(ITawSystemDictTypeManager)ApplicationContextHolder.getInstance().getBean("ItawSystemDictTypeManager");
	String fileName=request.getParameter("tmpFileName");
	String fileSource=request.getParameter("tmpFileSource");
	String tmpfileProperty=request.getParameter("tmpFileProperty");
	String time=request.getParameter("tmpTime");
	String roomId=request.getParameter("roomId");
	String startTime=request.getParameter("startTime");
	String endTime=request.getParameter("endTime");
	String dispatchDeptId=request.getParameter("tmpDispatchDeptId");
	String dispatchDept=request.getParameter("tmpDispatchDept");
	String dispatcherId=request.getParameter("tmpDispatcherId");
	String dispatcher=request.getParameter("tmpDispatcher");
	session.setAttribute("myFileName",fileName);
	session.setAttribute("myFileSource",fileSource);
	session.setAttribute("myTmpfileProperty",tmpfileProperty);
	session.setAttribute("myTime",time);
	session.setAttribute("myRoomId",roomId);
	session.setAttribute("myStartTime",startTime);
	session.setAttribute("myEndTime",endTime);
	session.setAttribute("myDispatchDeptId",dispatchDeptId);
	session.setAttribute("myDispatchDept",dispatchDept);
	session.setAttribute("myDispatcherId",dispatcherId);
	session.setAttribute("myDispatcher",dispatcher);
%>
 
<display:table name="tawRmDispatchRecordList" cellspacing="0" cellpadding="0"
    id="tawRmDispatchRecordList" pagesize="${pageSize }" class="table tawRmDispatchRecordList"
    export="true"  sort="list" partialList="true" size="resultSize"
    requestURI="${app}/duty/tawRmDispatchRecord.do?method=searchList" 
    decorator="com.boco.eoms.duty.displaytag.support.DutyListDisplaytagDecorator" >


	<display:column property="fileName" sortable="true" headerClass="sortable"
         titleKey="tawRmDispatchRecordForm.fileName" paramId="id" paramProperty="id" href="${app}/duty/tawRmDispatchRecord.do?method=edit"/>
         
    <display:column property="fileSource" sortable="true" headerClass="sortable"
         titleKey="tawRmDispatchRecordForm.fileSource"/>

    <display:column property="fileProperty" sortable="true" headerClass="sortable"
         titleKey="tawRmDispatchRecordForm.fileProperty"/>
         
    <display:column property="time" sortable="true" headerClass="sortable"
         titleKey="tawRmDispatchRecordForm.time"/>
         
    <display:column property="dispatchDept" sortable="true" headerClass="sortable"
         titleKey="tawRmDispatchRecordForm.dispatchDept"/>
         
    <display:column property="dispatcher" sortable="true" headerClass="sortable"
         titleKey="tawRmDispatchRecordForm.dispatcher"/>
         
    <display:column property="receiver" sortable="true" headerClass="sortable"
         titleKey="tawRmDispatchRecordForm.receiver"/>

    

    <display:setProperty name="paging.banner.item_name" value="thread"/>
    <display:setProperty name="paging.banner.items_name" value="threads"/>
</display:table>
 
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>

