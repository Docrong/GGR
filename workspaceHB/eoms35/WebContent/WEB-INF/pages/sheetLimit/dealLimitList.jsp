<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>


<content tag="heading">
    ${eoms:a2u('工单时长配置')}
</content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
           onclick="location.href='<c:url value="/sheet/sheetLimit/editSheetLimit.do?method=editDealLimit"/>'"
           value="<fmt:message key="button.add"/>"/>
</c:set>


<display:table name="sheetLimitList" cellspacing="0" cellpadding="0"
               id="sheetLimitList" pagesize="25" class="table sheetLimitList"
               export="true" requestURI="" sort="list"
               decorator="com.boco.eoms.sheet.tool.limit.webapp.action.SheetLimitListDisplaytagDecoratorHelper">


    <display:column url="/sheet/sheetLimit/editSheetLimit.do?method=editDealLimit"
                    paramId="id" paramProperty="id" sortable="true" headerClass="sortable"
                    title="${eoms:a2u(' 故障响应处理级别 ')}">
        <eoms:id2nameDB id="${sheetLimitList.specialty2}" beanId="ItawSystemDictTypeDao"/>
    </display:column>
    <display:column property="acceptLimit" sortable="true" headerClass="sortable"
                    title="${eoms:a2u('受理时长（单位：分钟） ')}"/>

    <display:column property="replyLimit" sortable="true" headerClass="sortable"
                    title="${eoms:a2u('处理时长（单位：分钟） ')}"/>

    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>
</display:table>


<c:out value="${buttons}" escapeXml="false"/>
<%@ include file="/common/footer_eoms.jsp" %>