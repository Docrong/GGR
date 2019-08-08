<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>


<content tag="heading">
    ${eoms:a2u('工单环节时长配置')}
</content>

<c:set var="buttons">
    <input type="button" style="margin-right: 5px"
           onclick="location.href='<c:url value="/sheet/sheetLimit/editSheetLimit.do"/>'"
           value="<fmt:message key="button.add"/>"/>
</c:set>


<display:table name="sheetLimitList" cellspacing="0" cellpadding="0"
               id="sheetLimitList" pagesize="25" class="table sheetLimitList"
               export="true" requestURI="" sort="list"
               decorator="com.boco.eoms.sheet.tool.limit.webapp.action.SheetLimitListDisplaytagDecoratorHelper">

    <display:column sortable="true" headerClass="sortable"
                    title="${eoms:a2u('网络分类（一级）')}" url="/sheet/sheetLimit/editSheetLimit.do" paramId="id"
                    paramProperty="id">
        <eoms:id2nameDB id="${sheetLimitList.specialty1}" beanId="ItawSystemDictTypeDao"/>
    </display:column>

    <display:column sortable="true" headerClass="sortable"
                    title="${eoms:a2u('网络分类（二级）')}">
        <eoms:id2nameDB id="${sheetLimitList.specialty3}" beanId="ItawSystemDictTypeDao"/>
    </display:column>

    <display:column sortable="true" headerClass="sortable"
                    title="${eoms:a2u('网络分类（三级）')}">
        <eoms:id2nameDB id="${sheetLimitList.specialty4}" beanId="ItawSystemDictTypeDao"/>
    </display:column>

    <display:column sortable="true" headerClass="sortable"
                    title="${eoms:a2u(' 故障响应处理级别 ')}">
        <eoms:id2nameDB id="${sheetLimitList.specialty2}" beanId="ItawSystemDictTypeDao"/>
    </display:column>
    <display:column property="t1Limit" sortable="true" headerClass="sortable"
                    title="${eoms:a2u('T1时长（单位：分钟）')}"/>

    <display:column property="t2Limit" sortable="true" headerClass="sortable"
                    title="${eoms:a2u('T2时长（单位：分钟）')}"/>

    <display:column property="t3Limit" sortable="true" headerClass="sortable"
                    title="${eoms:a2u('T3时长（单位：分钟）')}"/>
    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>
</display:table>

<c:out value="${buttons}" escapeXml="false"/>
<%@ include file="/common/footer_eoms.jsp" %>