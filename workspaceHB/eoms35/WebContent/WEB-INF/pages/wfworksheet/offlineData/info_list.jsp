<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<bean:define id="url" value="offlineData.do"/>
<display:table name="taskList" cellspacing="0" cellpadding="0"
               id="taskList" pagesize="${pageSize}" class="listTable taskList"
               export="false" requestURI="offlineData.do"
               sort="list" size="total" partialList="true"
               decorator="com.boco.eoms.sheet.offlineData.webapp.action.InfoListDisplaytagDecoratorHelper">
    <display:column property="sel" sortable="true"
                    headerClass="sortable" title=""/>
    <c:if test="${stylepage == 'assign'}">
        <display:column property="manual" sortable="true"
                        headerClass="sortable" title=""/>
    </c:if>
    <c:if test="${stylepage == 'new'}">
        <display:column property="edit" sortable="true"
                        headerClass="sortable" title=""/>

        <display:column property="del" sortable="true"
                        headerClass="sortable" title=""/>
    </c:if>

    <display:column property="infolist" sortable="true"
                    headerClass="sortable" title="信息列表"/>

    <display:column property="storageequipment" sortable="true"
                    headerClass="sortable" title="在线存放设备"/>

    <display:column property="maintenance" sortable="true"
                    headerClass="sortable" title="维护责任单位"/>
    <display:column property="responsible" sortable="true"
                    headerClass="sortable" title="责任人"/>

    <display:column property="information" sortable="true"
                    headerClass="sortable" title="在线情况"/>

    <display:column property="onlinestatus" sortable="true"
                    headerClass="sortable" title="信息定级"/>

</display:table>
<%@ include file="/common/footer_eoms.jsp" %>