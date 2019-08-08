<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<script type="text/javascript">

    function openWorkFlow() {
        var flowIds = document.getElementsByName("flowId");
        var flowId = "";
        var flowName = "";
        for (var i = 0; i < flowIds.length; i++) {
            if (flowIds[i].checked) {
                var flowIdvalues = flowIds[i].value;
                var idandnames = flowIdvalues.split(",");
                flowId = idandnames[0];
                flowName = idandnames[1];
            }
        }
        if (flowId == "") {
            alert("请选择一个流程");
            return false;
        }
        window.opener.document.getElementById("flowId").value = flowId;
        window.opener.document.getElementById("flowName").value = flowName;
        window.opener.changeFlow(flowId);
        window.close();
    }
</script>
<bean:define id="url" value="userdefinegroup.do"/>

<display:table name="taskList" cellspacing="0" cellpadding="0"
               id="taskList" pagesize="${pageSize}" class="table businessdesignMain"
               export="false" requestURI="userdefinegroup.do"
               sort="list" size="total" partialList="true">
    <display:column headerClass="sortable" title="选择">
        <input type="radio" value="${taskList.flowId},${taskList.remark}" name="flowId" id="flowId">
    </display:column>
    <display:column property="remark" sortable="true" headerClass="sortable" title="流程名" sortName="flowName"/>
</display:table>
<input type="button" value="确定" onclick="openWorkFlow()" class="btn">
<%@ include file="/common/footer_eoms.jsp" %>