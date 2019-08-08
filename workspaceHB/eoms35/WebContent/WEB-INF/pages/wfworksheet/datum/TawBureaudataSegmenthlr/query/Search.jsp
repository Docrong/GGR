<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>


<display:table name="bureaudataHlrList" cellspacing="0" cellpadding="0"
               id="bureaudataHlrList" pagesize="10" class="table bureaudataHlrList"
               export="true" requestURI="" sort="list"
               decorator="com.boco.eoms.datum.webapp.action.BureaudataHlrListDisplaytagDecoratorHelper">
    <display:column
            paramId="hlrId" paramProperty="hlrId" sortable="false" headerClass="sortable"
            title="${eoms:a2u('HLR名称')}">
        <bean:write property="hlrName" name="bureaudataHlrList"/>

    </display:column>
    <display:column property="hlrSignalId" sortable="false" headerClass="sortable"
                    title="${eoms:a2u('HLR信令点')}"/>

    <display:column property="beginSegment" sortable="false" headerClass="sortable"
                    title="${eoms:a2u('启始号段')}"/>
    <display:column property="endSegment" sortable="false" headerClass="sortable"
                    title="${eoms:a2u('终止号段')}"/>
    <display:column property="cityName" sortable="false" headerClass="sortable"
                    title="${eoms:a2u('所属城市')}"/>

    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>
</display:table>


<c:out value="${buttons}" escapeXml="false"/>
<%@ include file="/common/footer_eoms.jsp" %>