<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>
<style type="text/css">
    body {
        background-image: none;
    }
</style>

<html:form action="tawRmPlanContent" method="get" styleId="tawRmPlanContentForm">
    <content tag="heading"><fmt:message key="tawRmPlanContentDetail.heading"/></content>
    <%
        String monthplanName = request.getParameter("monthplanName");
        String roomId = request.getParameter("roomId");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String month = request.getParameter("month");
        session.setAttribute("myMonthplanName", monthplanName);
        session.setAttribute("myRoomId", roomId);
        session.setAttribute("myStartTime", startTime);
        session.setAttribute("myEndTime", endTime);
        session.setAttribute("myMonth", month);
    %>


    <!-- <html:hidden property="roomId" value="</%=roomId %>"/>-->
    <!-- <html:text property="roomId" styleId="roomId" styleClass="text medium" value="</%=roomId %>"/>-->
    <!-- <c:out value="${buttons}" escapeXml="false"/> -->
    <fmt:bundle basename="config/ApplicationResources-duty">
        <display:table name="tawRmPlanContentList" cellspacing="0" cellpadding="0"
                       id="tawRmPlanContentList" pagesize="${pageSize }" class="table tawRmPlanContentList"
                       export="true" sort="list" partialList="true" size="resultSize"
                       requestURI="${app}/duty/tawRmPlanContent.do?method=searchList"
                       decorator="com.boco.eoms.duty.displaytag.support.DutyListDisplaytagDecorator">


            <display:column property="monthplanName" sortable="true" headerClass="sortable"
                            titleKey="tawRmPlanContentForm.monthplanName" paramId="id" paramProperty="id"
                            href='${app}/duty/tawRmPlanContent.do?method=viewContent'/>

            <display:column property="month" sortable="true" headerClass="sortable"
                            titleKey="tawRmPlanContentForm.month"/>

            <display:column property="userId" sortable="true" headerClass="sortable"
                            titleKey="tawWorkbenchMemoForm.userid"/>


            <display:setProperty name="paging.banner.item_name" value="thread"/>
            <display:setProperty name="paging.banner.items_name" value="threads"/>
        </display:table>
    </fmt:bundle>
</html:form>
<%@ include file="/common/footer_eoms.jsp" %>