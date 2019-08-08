<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
    String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId"));
    String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId"));
    System.out.println("=====taskName======" + taskName);
    String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
    System.out.println("=====operateType======" + operateType);
    String ifInvoke = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifInvoke"));
    System.out.println("=====ifInvoke======" + ifInvoke);
    long localTimes = com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
%>
<%@ include file="/WEB-INF/pages/wfworksheet/netchange/baseinputlinkhtmlnew.jsp" %>
<br/>
<input type="hidden" name="${sheetPageName}processTemplateName" id="${sheetPageName}processTemplateName"
       value="NetChangeMainProcess"/>
<input type="hidden" name="${sheetPageName}operateName" id="${sheetPageName}operateName" value="nonFlowOperate"/>
<input type="hidden" name="${sheetPageName}beanId" value="iNetChangeMainManager"/>
<input type="hidden" name="${sheetPageName}mainClassName"
       value="com.boco.eoms.sheet.netchange.model.NetChangeMain"/> <!--main表Model对象类名-->
<input type="hidden" name="${sheetPageName}linkClassName"
       value="com.boco.eoms.sheet.netchange.model.NetChangeLink"/> <!--link表Model对象类名-->
<input type="hidden" name="${sheetPageName}toDeptId" value="${sheetMain.toDeptId}"/>
<input type="hidden" name="${sheetPageName}mainNetSortOne" id="${sheetPageName}mainNetSortOne"
       value="${sheetMain.mainNetSortOne}"/>
<input type="hidden" name="${sheetPageName}mainNetSortTwo" id="${sheetPageName}mainNetSortTwo"
       value="${sheetMain.mainNetSortTwo}"/>
<input type="hidden" name="${sheetPageName}mainNetSortThree" id="${sheetPageName}mainNetSortThree"
       value="${sheetMain.mainNetSortThree}"/>
<input type="hidden" name="${sheetPageName}mainEquipmentFactory" id="${sheetPageName}mainEquipmentFactory"
       value="${sheetMain.mainEquipmentFactory}"/>
<c:if test="${taskName != 'HoldTask' }">
    <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
</c:if>
<table class="formTable">
    <input type="hidden" id="tmpCompleteLimit" value=""
           alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'处理时限不能晚于工单完成时限'"/>
    <%if (taskName.equals("ProjectCreateTask")) {%>
    <%if (operateType.equals("110") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AuditTask"/>

    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <tr>
        <!-- 接单时限 -->
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                   readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                   alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
        </td>
        <!-- 完成时限 -->
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeCompleteLimit" readonly="readonly"
                   value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit"
                   alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <!-- 完成时限 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkExecuteEndDate"/>*</td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}linkExecuteEndDate" readonly="readonly"
                   id="${sheetPageName}linkExecuteEndDate" value="${eoms:date2String(sheetLink.linkExecuteEndDate)}"
                   onclick="popUpCalendar(this, this)"/>
        </td>

        <!-- 技术方案关键字 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkProjectKey"/>*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}linkProjectKey" id="${sheetPageName}linkProjectKey"
                   value="${sheetLink.linkProjectKey}"
                   alt="allowBlank:false,maxLength:40,vtext:'请填入技术方案关键字，最多输入40个字符'"/>
        </td>
    </tr>
    <tr>
        <!-- 总技术方案说明 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkProjectExplain"/></td>
        <td colspan="3">
            <textarea name="${sheetPageName}linkProjectExplain" id="${sheetPageName}linkProjectExplain"
                      class="textarea max"
                      alt="allowBlank:true,maxLength:255,vtext:'请填入总技术方案说明，最多输入125个字符'">${sheetLink.linkProjectExplain}</textarea>
        </td>
    </tr>
    <tr>
        <!-- 风险评估 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkRiskEvaluate"/>*</td>
        <td colspan="3">
            <textarea name="${sheetPageName}linkRiskEvaluate" id="${sheetPageName}linkRiskEvaluate" class="textarea max"
                      alt="allowBlank:false,maxLength:255,vtext:'请填入风险评估，最多输入125个字符'">${sheetLink.linkRiskEvaluate}</textarea>
        </td>
    </tr>
    <tr>
        <!-- 影响业务分析 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkOperationConstrue"/>*</td>
        <td colspan="3">
            <textarea name="${sheetPageName}linkOperationConstrue" id="${sheetPageName}linkOperationConstrue"
                      class="textarea max"
                      alt="allowBlank:false,maxLength:255,vtext:'请填入影响业务分析，最多输入125个字符">${sheetLink.linkOperationConstrue}</textarea>
        </td>
    </tr>

    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
        </td>
        <td colspan="3">
            <eoms:attachment name="tawSheetAccess" property="accesss"
                             scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>
        </td>
    </tr>
    <tr>
        <!-- 总技术方案附件 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkProjectAcc"/></td>
        <td colspan="3">
            <eoms:attachment name="sheetLink" property="nodeAccessories"
                             scope="request" idField="${sheetPageName}nodeAccessories" appCode="netchange"/>
        </td>
    </tr>


    <%} %>
    <%} else if (taskName.equals("AuditTask")) {%>
    <%if (operateType.equals("130") || operateType.equals("55")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="PermitTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value=""/>
    <input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value=""/>
    <input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value=""/>
    <tr>
        <!-- 审核结果 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkAuditingResult"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkAuditingResult" id="${sheetPageName}linkAuditingResult"
                           initDicId="1010907" defaultValue="${sheetLink.linkAuditingResult}" alt="allowBlank:false"
                           onchange="ifAuditPass1(this.value);"/>
        </td>
    </tr>
    <tr>
        <!-- 审核意见 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkAuditingIdea"/>*</td>
        <td colspan="3">
            <textarea name="linkAuditingIdea" id="linkAuditingIdea" class="textarea max"
                      alt="allowBlank:false,maxLength:255,vtext:'请填入审核意见，最多输入125个字符'">${sheetLink.linkAuditingIdea}</textarea>
        </td>
    </tr>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
        </td>
        <td colspan="3">
            <eoms:attachment name="tawSheetAccess" property="accesss"
                             scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>
        </td>
    </tr>
    <tr>
        <!-- 附件 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.nodeAccessories"/></td>
        <td colspan="3">
            <eoms:attachment name="sheetLink" property="nodeAccessories"
                             scope="request" idField="${sheetPageName}nodeAccessories" appCode="netchange"/>
        </td>
    </tr>
    <%} %>
    <%} else if (taskName.equals("PermitTask")) {%>
    <%if (operateType.equals("131") || operateType.equals("55")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExecuteTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer" value=""/>
    <input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader" value=""/>
    <input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType" value=""/>
    <tr>
        <!-- 接单时限 -->
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                   readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                   alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
        </td>
        <!-- 完成时限 -->
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeCompleteLimit" readonly="readonly"
                   value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit"
                   alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <!-- 审批结果 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkPermitResult"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkPermitResult" id="${sheetPageName}linkPermitResult"
                           initDicId="1010908" defaultValue="${sheetLink.linkPermitResult}" alt="allowBlank:false"
                           onchange="ifAuditPass(this.value);"/>
        </td>
    </tr>
    <tr>
        <!-- 审批意见 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkPermitIdea"/>*</td>
        <td colspan="3">
            <textarea name="linkPermitIdea" id="linkPermitIdea" class="textarea max"
                      alt="allowBlank:false,maxLength:255,vtext:'请填入审批意见，最多输入125个字符'">${sheetLink.linkPermitIdea}</textarea>
        </td>
    </tr>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
        </td>
        <td colspan="3">
            <eoms:attachment name="tawSheetAccess" property="accesss"
                             scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>
        </td>
    </tr>
    <tr>
        <!-- 附件 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.nodeAccessories"/></td>
        <td colspan="3">
            <eoms:attachment name="sheetLink" property="nodeAccessories"
                             scope="request" idField="${sheetPageName}nodeAccessories" appCode="netchange"/>
        </td>
    </tr>
    <%} %>
    <%} else if (taskName.equals("ExecuteTask") && (operateType.equals("113") || operateType.equals("11"))) {%>
    <%if (ifInvoke.equals("") || ifInvoke.equals("no")) {%>
    <%if (operateType.equals("113")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="waitReturn"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="118"/>
    <tr>
        <!-- 是否启动电路调度工单 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkIfStartCircuit"/>*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}linkIfStartCircuit" id="${sheetPageName}linkIfStartCircuit"
                           initDicId="10301" defaultValue="${sheetLink.linkIfStartCircuit}" alt="allowBlank:false"
                           onchange="popOtherFlow('circuitdispatch',this.value);"/>
            <html:button styleClass="btn" property="method.save" styleId="startCircuit"
                         onclick="openwin('startCircuit')">
                电路调度工单
            </html:button>
        </td>
        <!-- 是否启动网络数据管理工单 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkIfStartData"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}linkIfStartData" id="${sheetPageName}linkIfStartData" initDicId="10301"
                           defaultValue="${sheetLink.linkIfStartData}" alt="allowBlank:false"
                           onchange="popOtherFlow('netdata',this.value);"/>
            <html:button styleClass="btn" property="method.save" styleId="startNetData"
                         onclick="openwin('startNetData')">
                网络数据管理工单
            </html:button>
        </td>
    </tr>
    <tr>
        <!-- 是否启动软件变更工单 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkIfStartSoft"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkIfStartSoft" id="${sheetPageName}linkIfStartSoft" initDicId="10301"
                           defaultValue="${sheetLink.linkIfStartSoft}" alt="allowBlank:false"
                           onchange="popOtherFlow('softchange',this.value);"/>
            <html:button styleClass="btn" property="method.save" styleId="startSoftChange"
                         onclick="openwin('startSoftChange')">
                软件变更工单
            </html:button>
        </td>
    </tr>
    <%} else if (operateType.equals("11")) {%>
    <!-- 分派说明 -->
    <input type="hidden" name="${sheetPageName}operateType" value="11"/>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
				           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                                     alt="allowBlank:false,width:500" alt="width:'500px'">${sheetlink.remark}</textarea>
        </td>
    </tr>
    <%} %>
    <%} else if (ifInvoke.equals("yes")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TestTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="113"/>
    <tr>
        <td class="label">是否调用其它工单实施</td>
        <td class="content max" colspan=3>
            <eoms:comboBox name="${sheetPageName}ifInvokeAgin" id="${sheetPageName}ifInvokeAgin" alt="allowBlank:false"
                           initDicId="10301" onchange="InvokeAgin(this.value);"/>
        </td>
    </tr>
    <tr id="invokeAgin1" style="display:none">
        <%if (operateType.equals("113")) { %>
        <!-- 是否启动电路调度工单 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkIfStartCircuit"/>*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}linkIfStartCircuit" id="${sheetPageName}linkIfStartCircuit"
                           initDicId="10301" defaultValue="${sheetLink.linkIfStartCircuit}" alt="allowBlank:false"
                           onchange="popOtherFlow('circuitdispatch',this.value);"/>
            <html:button styleClass="btn" property="method.save" styleId="startCircuit"
                         onclick="openwin('startCircuit')">
                电路调度工单
            </html:button>
        </td>
        <!-- 是否启动网络数据管理工单 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkIfStartData"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}linkIfStartData" id="${sheetPageName}linkIfStartData" initDicId="10301"
                           defaultValue="${sheetLink.linkIfStartData}" alt="allowBlank:false"
                           onchange="popOtherFlow('netdata',this.value);"/>
            <html:button styleClass="btn" property="method.save" styleId="startNetData"
                         onclick="openwin('startNetData')">
                网络数据管理工单
            </html:button>
        </td>
    </tr>
    <tr id="invokeAgin2" style="display:none">
        <!-- 是否启动软件变更工单 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkIfStartSoft"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkIfStartSoft" id="${sheetPageName}linkIfStartSoft" initDicId="10301"
                           defaultValue="${sheetLink.linkIfStartSoft}" alt="allowBlank:false"
                           onchange="popOtherFlow('softchange',this.value);"/>
            <html:button styleClass="btn" property="method.save" styleId="startSoftChange"
                         onclick="openwin('startSoftChange')">
                软件变更工单
            </html:button>
        </td>
    </tr>
    <%} else if (operateType.equals("11")) { %>
    <!-- 分派说明 -->
    <input type="hidden" name="${sheetPageName}operateType" value="11"/>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
				           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                                     alt="allowBlank:false,width:500" alt="width:'500px'">${sheetlink.remark}</textarea>
        </td>
    </tr>
    <%} %>
    <tr id="test1" style="display:none">
        <!-- 接单时限 -->
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                   readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                   alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
        </td>
        <!-- 完成时限 -->
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeCompleteLimit" readonly="readonly"
                   value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit"
                   alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
        </td>
    </tr>
    <tr id="test2" style="display:none">
        <!-- 实施结果 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkExcuteResult"/>*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}linkExcuteResult" id="${sheetPageName}linkExcuteResult"
                           initDicId="1010903" defaultValue="${sheetLink.linkExcuteResult}" alt="allowBlank:false"
                           onchange="executeResult(this.value);"/>
        </td>
        <!-- 失败原因 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkFailedReason"/></td>
        <td>
            <eoms:comboBox name="${sheetPageName}linkFailedReason" id="${sheetPageName}linkFailedReason"
                           initDicId="1010910" defaultValue="${sheetLink.linkFailedReason}" alt="allowBlank:true"/>
        </td>
    </tr>
    <tr id="test3" style="display:none">
        <!-- 是否完全按照方案实施 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkIfAccordingProject"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkIfAccordingProject" id="${sheetPageName}linkIfAccordingProject"
                           initDicId="10301" defaultValue="${sheetLink.linkIfAccordingProject}" alt="allowBlank:false"/>
        </td>
    </tr>
    <tr id="test4" style="display:none">
        <!-- 综合实施情况说明 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkColligateExcuteResult"/>*</td>
        <td colspan="3">
            <textarea name="linkColligateExcuteResult" id="linkColligateExcuteResult" class="textarea max"
                      alt="allowBlank:false,maxLength:255,vtext:'请填入综合实施结果，最多输入125个字符'">${sheetLink.linkColligateExcuteResult}</textarea>
        </td>
    </tr>
    <tr id="test5" style="display:none">
        <td class="label">
            <bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
        </td>
        <td colspan="3">
            <eoms:attachment name="tawSheetAccess" property="accesss"
                             scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>
        </td>
    </tr>

    <tr id="testfile" style="display:none">
        <!-- 综合实施报告 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkColligateExcuteReport"/></td>
        <td colspan="3">
            <eoms:attachment name="sheetLink" property="nodeAccessories"
                             scope="request" idField="${sheetPageName}nodeAccessories" appCode="netchange"/>
        </td>
    </tr>
    <%} %>
    <%} else if (taskName.equals("TestTask")) {%>
    <%if (operateType.equals("117") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <tr>
        <!-- 接单时限 -->
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                   readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                   alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
        </td>
        <!-- 完成时限 -->
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeCompleteLimit" readonly="readonly"
                   value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit"
                   alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <!-- 综合测试情况概述 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkTestSummarize"/>*</td>
        <td colspan="3">
            <textarea name="${sheetPageName}linkTestSummarize" id="${sheetPageName}linkTestSummarize"
                      class="textarea max"
                      alt="allowBlank:false,maxLength:255,vtext:'请填入综合测试情况概述，最多输入125个字符'">${sheetLink.linkTestSummarize}</textarea>
        </td>
    </tr>
    <tr>
        <!-- 后续工作安排 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkWorkPlan"/>*</td>
        <td colspan="3">
            <textarea name="${sheetPageName}linkWorkPlan" id="${sheetPageName}linkWorkPlan" class="textarea max"
                      alt="allowBlank:false,maxLength:255,vtext:'请填入后续工作安排，最多输入125个字符'">${sheetLink.linkWorkPlan}</textarea>
        </td>
    </tr>
    <tr>
        <!-- 是否涉及工程交维 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkIsToMaintenance"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkIsToMaintenance" id="${sheetPageName}linkIsToMaintenance"
                           initDicId="10301" defaultValue="${sheetLink.linkIsToMaintenance}" alt="allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <!-- 交维描述 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkMaintenanceDes"/>*</td>
        <td colspan="3">
            <textarea name="${sheetPageName}linkMaintenanceDes" id="${sheetPageName}linkMaintenanceDes"
                      class="textarea max"
                      alt="allowBlank:false,maxLength:255,vtext:'请填入交维描述，最多输入125个字符'">${sheetLink.linkMaintenanceDes}</textarea>
        </td>
    </tr>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
        </td>
        <td colspan="3">
            <eoms:attachment name="tawSheetAccess" property="accesss"
                             scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>
        </td>
    </tr>
    <tr>
        <!-- 综合测试报告 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkExcuteReport"/>*</td>
        <td colspan="3">
            <eoms:attachment name="sheetLink" property="nodeAccessories"
                             scope="request" idField="${sheetPageName}nodeAccessories" appCode="netchange"
                             alt="allowBlank:false"/>
        </td>
    </tr>
    <%
        }
    } else if (taskName.equals("HoldTask") && (operateType.equals("115") || operateType.equals("11"))) {
    %>
    <input type="hidden" name="${sheetPageName}phaseId" value="Over"/>
    <input type="hidden" name="${sheetPageName}status" value="1"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <%
        String parentProcessName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("parentProcessName"));
        if (parentProcessName.equals("iBusinessPilotMainManager")) {
    %>
    <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true"/>
    <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentMain.piid}"/>
    <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName"
           value="callProcess"/>
    <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId"
           value="BusinessPilot"/>
    <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId"
           value="${parentMain.id}"/>
    <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="pilot"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"
           value="holdReplyProcess"/>
    <%} else if (parentProcessName.equals("iBusinessOperationMainManager")) {%>
    <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true"/>
    <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentMain.piid}"/>
    <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName"
           value="callProcess"/>
    <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId"
           value="BusinessOperation"/>
    <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId"
           value="${parentMain.id}"/>
    <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="operate"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"
           value="holdReplyProcess"/>
    <%} else if (parentProcessName.equals("iBusinessBackoutMainManager")) {%>
    <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true"/>
    <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}"/>
    <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName"
           value="receiveNet"/>
    <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId"
           value="BusinessBackout"/>
    <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId"
           value="${parentMain.id}"/>
    <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="ExcuteHumTask"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"
           value="holdReplyProcess"/>
    <%} else if (parentProcessName.equals("iBusinessDredgeMainManager")) {%>
    <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true"/>
    <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}"/>
    <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName"
           value="receiveNet"/>
    <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId"
           value="BusinessDredge"/>
    <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId"
           value="${parentMain.id}"/>
    <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="ExcuteHumTask"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"
           value="holdReplyProcess"/>
    <%} else if (parentProcessName.equals("iBusinessChangeMainManager")) {%>
    <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true"/>
    <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}"/>
    <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName"
           value="receiveNet"/>
    <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId"
           value="BusinessChange"/>
    <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId"
           value="${parentMain.id}"/>
    <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="ExcuteHumTask"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"
           value="holdReplyProcess"/>
    <%} else if (parentProcessName.equals("iResourceAffirmMainManager")) {%>
    <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true"/>
    <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}"/>
    <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName"
           value="receiveNet"/>
    <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId"
           value="ResourceAffirm"/>
    <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId"
           value="${parentMain.id}"/>
    <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="ExcuteHumTask"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"
           value="holdReplyProcess"/>
    <%} else if (parentProcessName.equals("iSecurityDealMainManager")) {%>
    <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true"/>
    <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}"/>
    <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName"
           value="backToSecurityDeal"/>
    <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId"
           value="SecurityDeal"/>
    <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId"
           value="${parentMain.id}"/>
    <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="PerformTask"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"
           value="holdReplyProcess"/>
    <%} else if (parentProcessName.equals("iNetOptimizeMainManager")) {%>
    <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true"/>
    <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}"/>
    <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName"
           value="callProcess"/>
    <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId"
           value="NetOptimize"/>
    <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId"
           value="${parentMain.id}"/>
    <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="ExcuteTask"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"
           value="holdReplyProcess"/>
    <%} else if (parentProcessName.equals("iGreatEventMainManager")) {%>
    <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true"/>
    <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentPiid}"/>
    <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName"
           value="backToGreatEvent"/>
    <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId"
           value="GreatEvent"/>
    <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId"
           value="${parentMain.id}"/>
    <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="PerformTask"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"
           value="holdReplyProcess"/>
    <%} else {%>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <%}%>
    <tr>
        <td class="label"><bean:message bundle="netchange" key="netchange.mainIfDemonstrateCase"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}mainIfDemonstrateCase" id="${sheetPageName}mainIfDemonstrateCase"
                           initDicId="1010912" defaultValue="${sheetMain.mainIfDemonstrateCase}"
                           alt="width:'500px',allowBlank:false" styleClass="select"/>
        </td>
        <td class="label"><bean:message bundle="netchange" key="netchange.mainCaseKeywords"/>*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}mainCaseKeywords"
                   id="${sheetPageName}mainCaseKeywords" value="${sheetMain.mainCaseKeywords}"
                   alt="allowBlank:false,maxLength:255,vtext:'请填入案例关键字，最多输入255个字符'"/>
        </td>
    </tr>
    <tr>

        <td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}holdStatisfied" id="${sheetPageName}holdStatisfied"
                           defaultValue="${sheetMain.holdStatisfied != 0 ? sheetMain.holdStatisfied : 1030301}"
                           initDicId="10303" defaultValue="${sheetMain.holdStatisfied}"
                           alt="width:'500px',allowBlank:false" styleClass="select"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/>*</td>
        <td colspan="3">
            <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult" class="textarea max"
                      alt="width:'500px',allowBlank:false">${sheetMain.endResult}</textarea>
        </td>
    </tr>


    <%} %>

    <% if (taskName.equals("cc")) {%>

    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>
        </td>
        <td colspan="3">
            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15"/>
            <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                      alt="allowBlank:false,width:500,maxLength:255,vtext:'请填入备注，最多输入125个字符'"
                      alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%} %>
    <%if (operateType.equals("61")) { %>
    <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer"
           value="${fOperateroleid}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader"
           value="${ftaskOwner}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType"
           value="${fOperateroleidType}"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
    <!--  <tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px',maxLength:255,vtext:'请填入备注，最多输入125个字符'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  -->

    <%} %>
    <%if (operateType.equals("4")) { %>
    <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer"
           value="${fOperateroleid}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader"
           value="${ftaskOwner}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType"
           value="${fOperateroleidType}"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4"/>
    <c:if test="${taskName=='ExecuteTask' }">
        <c:choose>
            <c:when test="${empty fPreTaskName}">
                <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="PermitTask"/>
            </c:when>
            <c:otherwise>
                <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId"
                       value="${fPreTaskName}"/>
            </c:otherwise>
        </c:choose>
    </c:if>
    <c:if test="${taskName!='ExecuteTask' }">
        <c:choose>
            <c:when test="${empty fPreTaskName}">
                <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask"/>
            </c:when>
            <c:when test="${fPreTaskName == 'DraftTask'}">
                <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask"/>
            </c:when>
            <c:otherwise>
                <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId"
                       value="${fPreTaskName}"/>
            </c:otherwise>
        </c:choose>
    </c:if>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
			        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                              alt="allowBlank:false,width:500,maxLength:255,vtext:'请填入备注，最多输入125个字符'">${sheetLink.remark}</textarea>
        </td>
    </tr>

    <%} %>
</table>
<%if (taskName.equals("ProjectCreateTask") && operateType.equals("110")) {%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="netchange" key="role.changeAdmin"/>
    </legend>
    <eoms:chooser id="netchangeSendRole" type="role" roleId="250" flowId="045"
                  config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}subAuditPerformer',childType:'user,subrole',limit:'none',text:'会审'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>
</fieldset>
<%} %>
<%if (taskName.equals("AuditTask") && operateType.equals("130")) {%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <span id="roleName1"></span>
    </legend>
    <div ID="permit4">
        <eoms:chooser id="netchangeSendRole" type="role" roleId="251" flowId="045"
                      config="{returnJSON:true,showLeader:true}"
                      category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}subAuditPerformer',childType:'user,subrole',limit:'none',text:'会审'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>
    </div>
</fieldset>

<%} %>
<%if (taskName.equals("PermitTask") && operateType.equals("131")) {%>

<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <span id="roleName"></span>
    </legend>
    <div ID="permit3">
        <eoms:chooser id="netchangeSendRole" type="role" roleId="250" flowId="045"
                      config="{returnJSON:true,showLeader:true}"
                      category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>
    </div>
</fieldset>
<%} %>

<%if (taskName.equals("ExecuteTask") && operateType.equals("113")) {%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="netchange" key="role.changeAdmin"/>
    </legend>
    <eoms:chooser id="netchangeSendRole" type="role" roleId="250" flowId="045"
                  config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>
</fieldset>
<%} %>
<%if (taskName.equals("TestTask") && operateType.equals("117")) {%>

<%} %>
<%if (taskName.equals("cc")) {%>
<fieldset id="link4">
    <eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'copyPerformer',childType:'user',limit:'none',text:'抄送'}]"/>
</fieldset>
<%} %>
<br/>

<div ID="idSpecia2"></div>
<script type="text/javascript">
    var v1 = eoms.form;
    Ext.onReady(function () {
        if ('<%=operateType%>' == '116' || ('${taskName}' == 'AuditTask' && '<%=operateType%>' == '55')) {
            var audit = $('${sheetPageName}linkAuditingResult').value;
            if (audit != null && audit != '') {
                ifAuditPass1(audit);
            }
        }
        if ('<%=operateType%>' == '111' || ('${taskName}' == 'PermitTask' && '<%=operateType%>' == '55')) {
            var permit = $('${sheetPageName}linkPermitResult').value;
            if (permit != null && permit != '') {
                ifAuditPass(permit);
            }
        }

        if (('<%=operateType%>' == '113' || ('${taskName}' == 'ExecuteTask' && '<%=operateType%>' == '11')) && '${ifInvoke}' == 'yes') {
            var result = $('${sheetPageName}linkExcuteResult').value;
            if (result != null && result != '') {
                executeResult(result);
            }
        }

        if ($('startCircuit')) v1.disableArea('startCircuit', true);
        if ($('startNetData')) v1.disableArea('startNetData', true);
        if ($('startSoftChange')) v1.disableArea('startSoftChange', true);
    });


    function ifAuditPass(input) {
        if ('<%=operateType%>' != '11' && '<%=operateType%>' != '55') {
            if (input == "101090801") {
                chooser_netchangeSendRole.enable();
                $('${sheetPageName}phaseId').value = 'ExecuteTask';
                $('${sheetPageName}operateType').value = '111';
                $('roleName').innerHTML = "变更管理员和技术组";
                $('${sheetPageName}dealPerformer').disabled = true;
                $('${sheetPageName}dealPerformerLeader').disabled = true;
                $('${sheetPageName}dealPerformerType').disabled = true;
                selectLimit('ExecuteTask');
            } else if (input == "101090803") {
                chooser_netchangeSendRole.disable();
                $('${sheetPageName}phaseId').value = 'RejectTask';
                $('${sheetPageName}operateType').value = '122';
                $('roleName').innerHTML = "建单人";
                $('${sheetPageName}dealPerformer').disabled = false;
                $('${sheetPageName}dealPerformerLeader').disabled = false;
                $('${sheetPageName}dealPerformerType').disabled = false;
                $('${sheetPageName}dealPerformer').value = '${Operateroleid}';
                $('${sheetPageName}dealPerformerLeader').value = '${TaskOwner}';
                $('${sheetPageName}dealPerformerType').value = '${OperateroleidType}';
                selectLimit('RejectTask');
            } else if (input == "101090802") {
                chooser_netchangeSendRole.disable();
                $('${sheetPageName}phaseId').value = 'ProjectCreateTask';
                $('${sheetPageName}operateType').value = '121';
                $('roleName').innerHTML = "变更管理员和技术组";
                $('${sheetPageName}dealPerformer').disabled = false;
                $('${sheetPageName}dealPerformerLeader').disabled = false;
                $('${sheetPageName}dealPerformerType').disabled = false;
                $('${sheetPageName}dealPerformer').value = '${ProjectCreateTaskOperateroleid}';
                $('${sheetPageName}dealPerformerLeader').value = '${ProjectCreateTaskTaskOwner}';
                $('${sheetPageName}dealPerformerType').value = '${ProjectCreateTaskOperateroleidType}';
                selectLimit('ProjectCreateTask');
            }
        }
    }

    function ifAuditPass1(input) {
        if ('<%=operateType%>' != '11' && '<%=operateType%>' != '55') {
            if (input == "101090701") {
                chooser_netchangeSendRole.enable();
                $('${sheetPageName}phaseId').value = 'PermitTask';
                $('${sheetPageName}operateType').value = '116';
                $('${sheetPageName}dealPerformer').disabled = true;
                $('${sheetPageName}dealPerformerLeader').disabled = true;
                $('${sheetPageName}dealPerformerType').disabled = true;
                $('roleName1').innerHTML = "变更管理负责人";
            } else if (input == "101090702") {
                chooser_netchangeSendRole.disable();
                $('${sheetPageName}phaseId').value = 'ProjectCreateTask';
                $('${sheetPageName}operateType').value = '123';
                $('${sheetPageName}dealPerformer').disabled = false;
                $('${sheetPageName}dealPerformerLeader').disabled = false;
                $('${sheetPageName}dealPerformerType').disabled = false;
                $('${sheetPageName}dealPerformer').value = '${fOperateroleid}';
                $('${sheetPageName}dealPerformerLeader').value = '${ftaskOwner}';
                $('${sheetPageName}dealPerformerType').value = '${fOperateroleidType}';
                $('roleName1').innerHTML = "变更管理员和技术组";

            }
        }
    }

    if ('<%=operateType%>' == '113') {
        popOtherFlow('circuitdispatch', $('${sheetPageName}linkIfStartCircuit').value);
        popOtherFlow('softchange', $('${sheetPageName}linkIfStartSoft').value);
        popOtherFlow('netdata', $('${sheetPageName}linkIfStartData').value);
    }

    function popOtherFlow(input, value) {
        if (input == 'circuitdispatch' && value == '1030101') {
            document.getElementById('startCircuit').style.display = '';
        } else if (input == 'circuitdispatch' && value != '1030101') {
            document.getElementById('startCircuit').style.display = 'none';
        } else if (input == 'softchange' && value == '1030101') {
            document.getElementById('startSoftChange').style.display = '';
        } else if (input == 'softchange' && value != '1030101') {
            document.getElementById('startSoftChange').style.display = 'none';
        } else if (input == 'netdata' && value == '1030101') {
            document.getElementById('startNetData').style.display = '';
        } else if (input == 'netdata' && value != '1030101') {
            document.getElementById('startNetData').style.display = 'none';
        }
    }

    function executeResult(input) {
        if (input == "101090301") {
            document.all.${sheetPageName}linkFailedReason.disabled = true;
            document.all.${sheetPageName}linkFailedReason.value = '';
        } else {
            document.all.${sheetPageName}linkFailedReason.disabled = false;
        }
    }

    function openwin(flag) {
        var url;
        //alert(flag);
        if (flag == "startCircuit") {
            url = "${app}/sheet/circuitdispatch/circuitdispatch.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iNetChangeMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}";
            $('${sheetPageName}phaseId').value = 'waitReturn';
        } else if (flag == "startSoftChange") {
            $('${sheetPageName}phaseId').value = 'waitReturn';
            url = "${app}/sheet/softchange/softchange.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iNetChangeMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}";
        } else if (flag == "startNetData") {
            $('${sheetPageName}phaseId').value = 'waitReturn';
            url = "${app}/sheet/netdata/netdata.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iNetChangeMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}";
        }
        window.open(url, 'newwindow', 'height=800, width=1000, top=200, left=200,toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
    }

    ifAuditPass1('${sheetLink.linkAuditingResult}');
    ifAuditPass('${sheetLink.linkPermitResult}');

    function InvokeAgin(input) {
        if (input == "1030101") {
            $('${sheetPageName}phaseId').value = 'waitReturn';
            $('${sheetPageName}operateType').value = '118';
            v1.enableArea('invokeAgin1');
            v1.enableArea('invokeAgin2');
            v1.disableArea('test1', true);
            v1.disableArea('test2', true);
            v1.disableArea('test3', true);
            v1.disableArea('test4', true);
            v1.disableArea('test5', true);
            v1.disableArea('testfile', true);
        } else if (input == "1030102") {
            $('${sheetPageName}phaseId').value = 'TestTask';
            $('${sheetPageName}operateType').value = '113';
            v1.enableArea('test1');
            v1.enableArea('test2');
            v1.enableArea('test3');
            v1.enableArea('test4');
            v1.enableArea('test5');
            v1.enableArea('testfile');
            var attch = $("UIFrame1-${sheetPageName}nodeAccessories");
            if (eoms.isIE) {
                attch.setStyle("width:300px;height:100px");
            } else {
                attch.contentWindow.autoIframe();
            }
            v1.disableArea('invokeAgin1', true);
            v1.disableArea('invokeAgin2', true);
        }
    }

    //处理时限不能超过工单完成时限
    var dtnow = new Date();
    var str = '${sheetMain.sheetCompleteLimit}';
    str = str.replace(/-/g, "/");
    str = str.substring(0, str.length - 2);
    var dt2 = new Date(str);
    if (dt2 > dtnow) {
        document.getElementById("tmpCompleteLimit").value = '${sheetMain.sheetCompleteLimit}';
    } else {
        document.getElementById("tmpCompleteLimit").value = '';
    }


    function selectLimit(nextPhaseId) {
        var temp1 = $("${sheetPageName}mainNetSortOne").value;
        var temp2 = $("${sheetPageName}mainNetSortTwo").value;
        var temp3 = $("${sheetPageName}mainNetSortThree").value;
        var temp4 = $("${sheetPageName}mainEquipmentFactory").value;
        Ext.Ajax.request({
            method: "get",
            url: "softchange.do?method=newShowLimit&specialty1=" + temp1 + "&specialty2=" + temp2 + "&specialty3=" + temp3 + "&specialty4=" + temp4 + "&flowName=SoftChangeMainProcess&taskName=" + nextPhaseId,
            success: function (x) {
                var o = eoms.JSONDecode(x.responseText);
                if ((o.acceptLimit == null || o.acceptLimit == "") && (o.replyLimit == null || o.replyLimit == "")) {
                    //$("${sheetPageName}sheetAcceptLimit").value = "";
                    //$('${sheetPageName}sheetCompleteLimit').value = "";
                } else {
                    var times =<%=localTimes%>;
                    var dt1 = new Date(times).add(Date.MINUTE, parseInt(o.acceptLimit, 10));
                    var dt2 = dt1.add(Date.MINUTE, parseInt(o.replyLimit, 10));
                    $("${sheetPageName}nodeAcceptLimit").value = dt1.format('Y-m-d H:i:s');
                    $('${sheetPageName}nodeCompleteLimit').value = dt2.format('Y-m-d H:i:s');
                }
            }
        });
    }
</script>