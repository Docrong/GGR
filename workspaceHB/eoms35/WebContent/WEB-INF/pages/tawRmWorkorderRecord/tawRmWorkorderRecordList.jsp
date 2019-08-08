<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>
<style type="text/css">
    body {
        background-image: none;
    }
</style>

<html:form action="tawRmWorkorderRecord" method="post" styleId="tawRmWorkorderRecordForm">
    <content tag="heading"><fmt:message key="tawRmWorkorderRecordList.heading"/></content>
    <%
        String title = request.getParameter("title");
        String workOrderId = request.getParameter("workOrderId");
        String orderState = request.getParameter("orderState");
        String replyTime = request.getParameter("replyTime");
        String roomId = request.getParameter("roomId");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        session.setAttribute("myTitle", title);
        session.setAttribute("myWorkOrderId", workOrderId);
        session.setAttribute("myOrderState", orderState);
        session.setAttribute("myReplyTime", replyTime);
        session.setAttribute("myRoomId", roomId);
        session.setAttribute("myStartTime", startTime);
        session.setAttribute("myEndTime", endTime);
    %>
    <fmt:bundle basename="config/ApplicationResources-duty">
        <display:table name="tawRmWorkorderRecordList" cellspacing="0" cellpadding="0"
                       id="tawRmWorkorderRecordList" pagesize="${pageSize }" class="table tawRmWorkorderRecordList"
                       export="true" sort="list" partialList="true" size="resultSize"
                       requestURI="${app}/duty/tawRmWorkorderRecord.do?method=searchList"
                       decorator="com.boco.eoms.duty.displaytag.support.DutyListDisplaytagDecorator">


            <display:column property="link" sortable="true" headerClass="sortable"
                            titleKey="tawRmWorkorderRecordForm.orderTitle"/>

            <display:column property="workOrderId" sortable="true" headerClass="sortable"
                            titleKey="tawRmWorkorderRecordForm.workOrderId"/>

            <display:column property="sender" sortable="true" headerClass="sortable"
                            titleKey="tawRmWorkorderRecordForm.sender"/>

            <display:column property="orderState" sortable="true" headerClass="sortable"
                            titleKey="tawRmWorkorderRecordForm.orderState"/>


            <display:setProperty name="paging.banner.item_name" value="thread"/>
            <display:setProperty name="paging.banner.items_name" value="threads"/>
        </display:table>
    </fmt:bundle>
</html:form>
<%@ include file="/common/footer_eoms.jsp" %>