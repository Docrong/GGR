<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_ext.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="com.boco.eoms.sheet.tool.complaintmsgconfig.model.ComplaintSheetMsgConfig" %>
<%@ page import="com.boco.eoms.base.util.StaticMethod" %>
<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
           onclick="location.href='<html:rewrite page='/complaintSheetMsgConfigs.do?method=add'/>'"
           value="<fmt:message key="button.add"/>"/>
</c:set>

<fmt:bundle basename="config/applicationResource-complaintsheetmsgconfig">

    <display:table name="complaintSheetMsgConfigList" cellspacing="0" cellpadding="0"
                   id="complaintSheetMsgConfigList" pagesize="${pageSize}" class="table complaintSheetMsgConfigList"
                   export="false"
                   sort="list" partialList="true" size="resultSize"
                   decorator="com.boco.eoms.sheet.tool.complaintmsgconfig.webapp.action.ListDisplaytagDecoratorHelper">

        <display:column headerClass="sortable" title="订阅者"
                        href="${app}/sheet/complaintSheetMsgConfig/complaintSheetMsgConfigs.do?method=edit"
                        paramId="id" paramProperty="id">
            <eoms:id2nameDB id="${complaintSheetMsgConfigList.userId}" beanId="tawSystemUserDao"/>
        </display:column>
        <display:column headerClass="sortable" title="故障地市">
            <eoms:id2nameDB id="${complaintSheetMsgConfigList.areaId}" beanId="tawSystemAreaDao"/>
        </display:column>
        <display:column headerClass="sortable" title="投诉类型">
            <eoms:id2nameDB id="${complaintSheetMsgConfigList.complaintType}" beanId="ItawSystemDictTypeDao"/>
        </display:column>

        <display:column property="notifyUserIds" sortable="true"
                        headerClass="sortable" title="需通知的人员"/>


        <display:setProperty name="paging.banner.item_name" value="complaintSheetMsgConfig"/>
        <display:setProperty name="paging.banner.items_name" value="complaintSheetMsgConfigs"/>
    </display:table>

    <c:out value="${buttons}" escapeXml="false"/>

</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp" %>