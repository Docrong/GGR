<%@ include file="/common/taglibs.jsp" %>
<%
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    String dealPerformer = (String) request.getAttribute("dealPerformer");
    String dealPerformerLeader = (String) request.getAttribute("dealPerformerLeader");
    System.out.println("dealPerformerLeader>>>>>>" + dealPerformerLeader);
    String dealPerformerType = (String) request.getAttribute("dealPerformerType");
    System.out.println("dealPerformer>>>>>>" + dealPerformer);
%>

<input type="hidden" name="${sheetPageName}beanId" value="iagentmaintenanceMainManager"/>
<input type="hidden" name="${sheetPageName}mainClassName"
       value="com.boco.eoms.sheet.agentmaintenance.model.AgentMaintenanceMain"/> <!--main\u8868Model\u5bf9\u8c61\u7c7b\u540d-->
<input type="hidden" name="${sheetPageName}linkClassName"
       value="com.boco.eoms.sheet.agentmaintenance.model.AgentMaintenanceLink"/> <!--link\u8868Model\u5bf9\u8c61\u7c7b\u540d-->
<input type="hidden" name="${sheetPageName}processTemplateName" id="${sheetPageName}processTemplateName"
       value="AgentMaintenanceMainFlowProcess"/>
<input type="hidden" name="${sheetPageName}operateName" id="${sheetPageName}operateName" value="nonFlowOperate"/>
<input type="hidden" name="${sheetPageName}dealPerformer" value="${dealPerformer}"/>
<input type="hidden" name="${sheetPageName}dealPerformerLeader" value="${dealPerformerLeader}"/>
<input type="hidden" name="${sheetPageName}dealPerformerType" value="${dealPerformerType}"/>
<%@ include file="/WEB-INF/pages/wfworksheet/agentmaintenance/baseinputlinkhtmlnew.jsp" %>
<table class="listTable">
</table>
<%} %>
