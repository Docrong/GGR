<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<%@ include file="/common/extlibs.jsp" %>
<script type="text/javascript">
    Ext.onReady(function () {
        colorRows('list-table');
    })

    function openSheet(url) {
        if (parent.frames['portal-north']) {
            parent.frames['portal-north'].location.href = url;
        } else {
            location.href = url;
        }
    }
</script>
<logic:notPresent name="recordTotal">
    <bean:define id="url" value="netFault.do"/>
    <display:table name="taskList" cellspacing="0" cellpadding="0"
                   id="taskList" pagesize="${pageSize}" class="table businessdesignMain"
                   export="true" requestURI="netFault.do"
                   sort="list" size="total" partialList="true"
                   decorator="com.boco.eoms.sheet.base.webapp.action.QueryListDisplaytagDecoratorHelper">

        <display:column property="title" sortable="true"
                        headerClass="sortable" title="${eoms:a2u('工单主题')}"/>

        <display:column property="sheetId" sortable="true"
                        headerClass="sortable" title="${eoms:a2u('工单流水号')}"/>

        <display:column property="sheetAcceptLimit" sortable="true"
                        headerClass="sortable" title="${eoms:a2u('受理时限')}"
                        format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

        <display:column property="sheetCompleteLimit" sortable="true"
                        headerClass="sortable" title="${eoms:a2u('处理时限')}"
                        format="{0,date,yyyy-MM-dd HH:mm:ss}"/>
        <display:setProperty name="export.pdf" value="false"/>
        <display:setProperty name="export.xml" value="false"/>
        <display:setProperty name="export.csv" value="false"/>

    </display:table>
</logic:notPresent>
<logic:present name="recordTotal">
    <center>
        <bean:message bundle="sheet" key="worksheet.query.total"/>${recordTotal}<bean:message bundle="sheet" key="worksheet.query.totalNumber" />
    </center>
</logic:present>
<!--
html:link
page="/businessdesign.do?method=showDetailPage&sheetKey=${worksheet.sheetKey}&taskId=${worksheet.id}&taskName=${worksheet.taskName}"
target="portal-north"
-->

<%@ include file="/common/footer_eoms.jsp" %>
