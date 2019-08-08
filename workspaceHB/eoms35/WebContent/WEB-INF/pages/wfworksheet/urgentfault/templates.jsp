<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<%@ include file="/common/extlibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
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

    function check() {
        var templateIds = document.getElementsByName("templateId");
        var i = 0;
        var templateId = "";
        for (i = 0; i < templateIds.length; i++) {
            if (templateIds[i].checked) {
                templateId = templateIds[i].value;
            }
        }
        if (templateId == "") {
            alert("<bean:message bundle="sheet" key="template.select"/>");
            return false;
        } else {
            if (parent == null)
                return;
            var taskName = "${draft}";
            if (taskName != null && taskName == "DraftHumTask") {
                window.opener.location.href = "${app}/sheet/urgentfault/urgentfault.do?method=showDetailPage&sheetKey=${sheetKey}&piid=${piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId=" + templateId;
            } else {
                window.opener.location.href = "${app}/sheet/urgentfault/urgentfault.do?method=referenceTemplate&templateId=" + templateId;
            }

            window.close();
        }

    }
</script>
<c:set var="tLevelName">
    <bean:message bundle="urgentfault" key="urgentfault.mainAffectOperationLevel"/>
</c:set>
<c:set var="tSpectialityName">
    <bean:message bundle="urgentfault" key="urgentfault.mainFaultSpeciality"/>
</c:set>
<bean:define id="url" value="urgentfault.do"/>
<fmt:bundle basename="config/ApplicationResources-sheet">
    <display:table name="taskList" cellspacing="0" cellpadding="0"
                   id="taskList" pagesize="${pageSize}" class="table resourceAssessTable"
                   export="false" requestURI="urgentfault.do"
                   sort="list" size="total" partialList="true">
        <display:column>
            <input type="radio" name="templateId" value="${taskList.id}"/>
        </display:column>
        <display:column sortable="true" headerClass="sortable" titleKey="template.templateName">
            <a href="${app}/sheet/urgentfault/urgentfault.do?method=editTemplateInfo&templateId=${taskList.id}&type=${templateManage}">
                    ${taskList.sheetTemplateName}
            </a>
        </display:column>
        <display:column sortable="true" headerClass="sortable" title="${tSpectialityName}">
            <eoms:id2nameDB id="${taskList.mainFaultSpeciality}" beanId="ItawSystemDictTypeDao"/>
        </display:column>
        <display:column property="sendTime" sortable="true"
                        headerClass="sortable" title="创建时间"
                        format="{0,date,yyyy-MM-dd HH:mm:ss}"/>
    </display:table>
</fmt:bundle>
<logic:notPresent name="templateManage">
    <html:button styleClass="btn" property="method.save" styleId="method.save" onclick="check()">
        <bean:message bundle="sheet" key="button.refere"/>
    </html:button>
</logic:notPresent>
<%@ include file="/common/footer_eoms.jsp" %>