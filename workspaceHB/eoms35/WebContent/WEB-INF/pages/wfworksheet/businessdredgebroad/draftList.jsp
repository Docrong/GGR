<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<%@ include file="/common/extlibs.jsp" %>
<script type="text/javascript">
    Ext.onReady(function () {
        colorRows('list-table');
    })

    function openSheet(url) {
        if (parent.frames['north']) {
            parent.frames['north'].location.href = url;
        } else {
            location.href = url;
        }
    }
</script>

<bean:define id="url" value="businessdredgebroad.do"/>
<display:table name="taskList" cellspacing="0" cellpadding="0"
               id="taskList" pagesize="${pageSize}" class="table resourceAssessTable"
               export="true" requestURI="businessdredgebroad.do"
               sort="list" size="total" partialList="true"
               decorator="com.boco.eoms.sheet.businessdredgebroad.webapp.action.ListDisplaytagDecoratorHelper">

    <display:column property="sheetId" sortable="true"
                    headerClass="sortable" title="${eoms:a2u('工单流水号')}"/>

    <display:column property="title" sortable="true"
                    headerClass="sortable" title="${eoms:a2u('工单主题')}"/>
    <%-- <display:column property="urgentDegree" sortable="true"
        headerClass="sortable" title="${eoms:a2u('紧急程度')}" />
    <display:column property="businessType" sortable="true"
        headerClass="sortable" title="${eoms:a2u('集团客户业务分类')}" />
    --%>
    <display:column property="createTime" sortable="true"
                    headerClass="sortable" title="${eoms:a2u('创建时间')}"
                    format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

    <display:column property="taskDisplayName" sortable="true"
                    headerClass="sortable" title="${eoms:a2u('处理环节')}"/>

    <display:column sortable="true"
                    headerClass="sortable" title="${eoms:a2u('任务所有者')}">
        <eoms:id2nameDB id="${taskList.taskOwner}" beanId="tawSystemUserDao"/>
    </display:column>

    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>
</display:table>
<%@ include file="/common/footer_eoms.jsp" %>