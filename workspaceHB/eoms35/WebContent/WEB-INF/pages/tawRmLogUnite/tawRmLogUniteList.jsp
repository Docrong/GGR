<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<style type="text/css">
  	body{background-image:none;}
</style>

<html:form action="tawRmLogUnite" method="post" styleId="tawRmLogUniteForm"> 
<content tag="heading"><fmt:message key="tawRmLogUniteList.heading"/></content>
<%
	String roomId=request.getParameter("roomId");
	String beginTime=request.getParameter("beginTime");
	String endTime=request.getParameter("endTime");
	session.setAttribute("myRoomId",roomId);
	session.setAttribute("myBeginTime",beginTime);
	session.setAttribute("myEndTime",endTime);
%>
<fmt:bundle basename="config/ApplicationResources-duty">
<display:table name="tawRmLogUniteList" cellspacing="0" cellpadding="0"
    id="tawRmLogUniteList" pagesize="${pageSize }" class="table tawRmLogUniteList"
    export="true"  sort="list" partialList="true" size="resultSize"
    requestURI="${app}/duty/tawRmLogUnite.do?method=searchList" 
    decorator="com.boco.eoms.duty.displaytag.support.DutyListDisplaytagDecorator" >


	<display:column property="workSerial" sortable="true" headerClass="sortable"
         titleKey="tawRmLogUniteForm.workSerial" paramId="id" paramProperty="id" href="${app}/duty/tawRmLogUnite.do?method=viewContent"/>
         
    <display:column property="beginTime" sortable="true" headerClass="sortable"
         titleKey="tawRmLogUniteForm.beginTime"/>

    <display:column property="endTime" sortable="true" headerClass="sortable"
         titleKey="tawRmLogUniteForm.endTime"/>

    

    <display:setProperty name="paging.banner.item_name" value="thread"/>
    <display:setProperty name="paging.banner.items_name" value="threads"/>
</display:table>
</fmt:bundle>
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>