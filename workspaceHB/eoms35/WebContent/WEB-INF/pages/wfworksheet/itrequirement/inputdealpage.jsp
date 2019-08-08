<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
    String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId"));
    String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId"));
    System.out.println("=====taskName======" + taskName);
    String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
    System.out.println("=====operateType======" + operateType);
    String roleName = "";
    String ifInvoke = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifInvoke"));
    System.out.println("=====ifInvoke======" + ifInvoke);
%>

<%@ include file="/WEB-INF/pages/wfworksheet/itrequirement/baseinputlinkhtmlnew.jsp" %>
<br/>
<table class="formTable">
    <input type="hidden" id="tmpCompleteLimit" value=""
           alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'处理时限不能晚于工单完成时限'"/>
    <input type="hidden" name="${sheetPageName}beanId" value="iITRequirementMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.itrequirement.model.ITRequirementMain"/>
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.itrequirement.model.ITRequirementLink"/>
    <c:if test="${taskName != 'HoldTask'&& taskName != 'ConfirmHoldTask' && taskName != 'TempSaveTask'}">
        <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
    </c:if>

    <%
        if (taskName.equals("AuditTask")) {
            if (operateType.equals("91") || operateType.equals("55")) {
    %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AnalysisTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>"/>

    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                   readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                   alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>

        </td>
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
        <td class="label"><bean:message bundle="itrequirement" key="itrequirement.linkAuditResult"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult"
                           initDicId="1011401" alt="allowBlank:false" styleClass="select-class"
                           onchange="ifAuditPass(this.value);" defaultValue="${sheetLink.linkAuditResult}"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="itrequirement" key="itrequirement.linkAuditDesc"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkAuditDesc" id="${sheetPageName}linkAuditDesc"
                      alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入审批意见，最多输入125字'">${sheetLink.linkAuditDesc}</textarea>
        </td>
    </tr>
    <%
            }
        }
    %>


    <%
        if (taskName.equals("AnalysisTask")) {
            if (operateType.equals("92") || operateType.equals("11")) {
    %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="DevAuditTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>"/>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                   readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                   alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>

        </td>
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
        <td class="label"><bean:message bundle="itrequirement" key="itrequirement.linkChangeType"/>*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}linkChangeType" id="${sheetPageName}linkChangeType"
                           initDicId="1011402" alt="allowBlank:false" styleClass="select-class"
                           defaultValue="${sheetLink.linkChangeType}"/>
        </td>
        <td class="label"><bean:message bundle="itrequirement" key="itrequirement.linkAnalysisResult"/>*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}linkAnalysisResult" id="${sheetPageName}linkAnalysisResult"
                           initDicId="1011403" alt="allowBlank:false" onchange="ifAnalysisPass(this.value);"
                           defaultValue="${sheetLink.linkAnalysisResult}" styleClass="select-class"/>
        </td>
    </tr>

    <!--       <tr>
		            <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.linkDevAmount"/></td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}linkDevAmount" id="${sheetPageName}linkDevAmount"  alt="allowBlank:true"/></td>
		            <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.linkAuditPer"/></td>
		            <td  class="content"> <input type="text"  class="text" name="${sheetPageName}linkAuditPer" id="${sheetPageName}linkAuditPer"  alt="allowBlank:true"/></td>
		          </tr>	-->
    <tr>
        <td class="label"><bean:message bundle="itrequirement" key="itrequirement.linkAnalysisDesc"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkAnalysisDesc" id="${sheetPageName}linkAnalysisDesc"
                      alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入需求分析概述，最多输入125字'">${sheetLink.linkAnalysisDesc}</textarea>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="itrequirement" key="itrequirement.linkHardWareExp"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkHardWareExp" id="${sheetPageName}linkHardWareExp"
                      alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入硬件扩容需求，最多输入125字'">${sheetLink.linkHardWareExp}</textarea>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="itrequirement" key="itrequirement.linkSystemImpact"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkSystemImpact" id="${sheetPageName}linkSystemImpact"
                      alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入系统性能影响，最多输入125字'">${sheetLink.linkSystemImpact}</textarea>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="itrequirement" key="itrequirement.linkOtherImpact"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkOtherImpact" id="${sheetPageName}linkOtherImpact"
                      alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入其他影响，最多输入125字'">${sheetLink.linkOtherImpact}</textarea>
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
        <td class="label"><bean:message bundle="itrequirement" key="itrequirement.linkTechnicalprogram"/></td>
        <td colspan='3'>
            <eoms:attachment idList="" scope="request" idField="${sheetPageName}linkTechnicalprogram" name="sheetLink"
                             property="linkTechnicalprogram" appCode="itrequirementsheet" alt="allowBlank:true"/>
        </td>
    </tr>
    <%
            }
        }
    %>
    <%
        if (taskName.equals("AddInfoTask")) {
            if (operateType.equals("93")) {
    %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AnalysisTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>"/>

    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                   readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                   alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>

        </td>
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
        <td class="label"><bean:message bundle="itrequirement" key="itrequirement.linkRequirementDesc"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkRequirementDesc"
                      id="${sheetPageName}linkRequirementDesc"
                      alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入需求概述，最多输入125字'">${sheetLink.linkRequirementDesc}</textarea>
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
        <td class="label"><bean:message bundle="itrequirement" key="itrequirement.linkRequirementDetail"/>*</td>
        <td colspan='3'>
            <eoms:attachment scope="request" idList="" idField="${sheetPageName}linkRequirementDetail"
                             property="linkRequirementDetail" name="sheetLink" appCode="itrequirementsheet"
                             alt="allowBlank:false"/>
        </td>
    </tr>
    <%
            }
        }
    %>


    <%
        if (taskName.equals("DevAuditTask")) {
            if (operateType.equals("94") || operateType.equals("55")) {
    %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AnalysisTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>"/>
    <tr>
        <td class="label"><bean:message bundle="itrequirement" key="itrequirement.linkAuditResult"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult"
                           initDicId="1011408" alt="allowBlank:false" styleClass="select-class"
                           onchange="ifAuditPass1(this.value);" defaultValue="${sheetLink.linkAuditResult}"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="itrequirement" key="itrequirement.linkAuditDesc"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkAuditDesc" id="${sheetPageName}linkAuditDesc"
                      alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入审批意见，最多输入125字'">${sheetLink.linkAuditDesc}</textarea>
        </td>
    </tr>
    <%
            }
        }
    %>


    <%
        if (taskName.equals("OperateTask")) {
            if (operateType.equals("95") || operateType.equals("11")) {
    %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ConfirmHoldTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>"/>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                   readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                   alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>

        </td>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeCompleteLimit" readonly="readonly"
                   value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit"
                   alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="itrequirement" key="itrequirement.linkCompleteDesc"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkCompleteDesc" id="${sheetPageName}linkCompleteDesc"
                      alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入完成情况概述，最多输入125字'">${sheetLink.linkCompleteDesc}</textarea>
        </td>
    </tr>
    <%
            }
        }
    %>

    <%
        if (taskName.equals("TempSaveTask")) {
            if (operateType.equals("96")) {
    %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>"/>
    <input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="4"/>
    <%
        String parentProcessName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("parentProcessName"));
        if (parentProcessName.equals("iProcessChangeMainManager")) {
    %>
    <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true"/>
    <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentMain.piid}"/>
    <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName"
           value="backToProcessChange"/>
    <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId"
           value="ProcessChange"/>
    <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId"
           value="${parentMain.id}"/>
    <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="ReleaseTask"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"
           value="holdReplyProcess"/>

    <%} else {%>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <%}%>

    <!--      <tr>
		            <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.linkTempSaveDesc"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkTempSaveDesc" id="${sheetPageName}linkTempSaveDesc" alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入说明，最多输入125字'">${sheetLink.linkTempSaveDesc}</textarea>
                    </td>
		          </tr>-->
    <%
            }
        }
    %>
    <%
        if (taskName.equals("ReplyTask")) {
            if (operateType.equals("97") || operateType.equals("11")) {
    %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>"/>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                   readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                   alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>

        </td>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeCompleteLimit" readonly="readonly"
                   value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit"
                   alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label">调用IT需求开发流程*</td>
        <td colspan='3'><input type="button" class="btn" value="IT需求开发流程" onclick="javascript:openwin()"></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="itrequirement" key="itrequirement.linkReplyDesc"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkReplyDesc" id="${sheetPageName}linkReplyDesc"
                      alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入回复内容，最多输入125字'">${sheetLink.linkReplyDesc}</textarea>
        </td>
    </tr>
    <tr>
            <%}}%>

            <%if(operateType.equals("4")){ %>
        <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer"
               value="${fOperateroleid}"/>
        <input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader"
               value="${ftaskOwner}"/>
        <input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType"
               value="${fOperateroleidType}"/>

        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4"/>

        <c:choose>
        <c:when test="${empty fPreTaskName}">
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask"/>
        </c:when>
        <c:when test="${fPreTaskName == 'DraftTask'}">
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask"/>
        </c:when>
        <c:otherwise>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}"/>
        </c:otherwise>
        </c:choose>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="allowBlank:false,maxLength:255,width:500,vtext:'请填入信息，最多输入125字'"
                          alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>

    <%} %>

    <%if (taskName.equals("HoldTask")) {%>
    <%if (operateType.equals("99")) { %>

    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="99"/>
    <input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>
    <%
        String parentProcessName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("parentProcessName"));
        if (parentProcessName.equals("iProcessChangeMainManager")) {
    %>
    <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true"/>
    <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentMain.piid}"/>
    <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName"
           value="backToProcessChange"/>
    <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId"
           value="ProcessChange"/>
    <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId"
           value="${parentMain.id}"/>
    <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="ReleaseTask"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"
           value="holdReplyProcess"/>
    <%} else {%>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <%}%>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}holdStatisfied"
                           id="${sheetPageName}holdStatisfied" defaultValue="${sheetMain.holdStatisfied}"
                           initDicId="10303" styleClass="select" alt="allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/>*</td>
        <td colspan="3">
            <textarea name="${sheetPageName}endResult" alt="allowBlank:false,maxLength:255,vtext:'请最多输入125字'"
                      id="${sheetPageName}endResult" class="textarea max">${sheetMain.endResult}</textarea>
        </td>
    </tr>
    <%} else if (operateType.equals("102")) {%>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ReplyTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>"/>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
			        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                              alt="allowBlank:false,maxLength:255,width:500,vtext:'请填入信息，最多输入125字'"
                              alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%
            }
        }
    %>

    <%if (taskName.equals("ConfirmHoldTask")) {%>
    <%if (operateType.equals("18")) { %>

    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18"/>
    <input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>
    <%
        String parentProcessName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("parentProcessName"));
        if (parentProcessName.equals("iProcessChangeMainManager")) {
    %>
    <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true"/>
    <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentMain.piid}"/>
    <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName"
           value="backToProcessChange"/>
    <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId"
           value="ProcessChange"/>
    <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId"
           value="${parentMain.id}"/>
    <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId" value="ReleaseTask"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"
           value="holdReplyProcess"/>
    <%} else {%>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <%}%>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}holdStatisfied"
                           id="${sheetPageName}holdStatisfied" defaultValue="${sheetMain.holdStatisfied}"
                           initDicId="10303" styleClass="select" alt="allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/>*</td>
        <td colspan="3">
            <textarea name="${sheetPageName}endResult" alt="allowBlank:false,maxLength:255,vtext:'请最多输入125字'"
                      id="${sheetPageName}endResult" class="textarea max">${sheetMain.endResult}</textarea>
        </td>
    </tr>
    <%} else if (operateType.equals("101")) {%>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OperateTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>"/>
    <tr>
        <td class="label"><bean:message bundle="itrequirement" key="itrequirement.linkTestDesc"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkTestDesc" id="${sheetPageName}linkTestDesc"
                      alt="allowBlank:false,maxLength:255,vtext:'请填入测试说明，请最多输入125字'">${sheetLink.linkTestDesc}</textarea>
        </td>
    </tr>

    <% }
    }%>

    <% if (taskName.equals("cc")) {%>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15"/>
            <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                      alt="allowBlank:false,maxLength:255,vtext:'请填入信息，最多输入125字'"
                      alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%} %>

    <%if (operateType.equals("61")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>

    <%-- <tr>
       <td class="label">
        <bean:message bundle="sheet" key="linkForm.remark" />
        </td>
        <td colspan="3">
            <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
            alt="width:'500px,vtext:'请最多输入125字''">${sheetLink.remark}</textarea>
      </td>
    </tr>  	--%>

    <%}%>
</table>

<%if (taskName.equals("cc")) {%>
<fieldset id="link4">
    <eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'copyPerformer',childType:'user',limit:'none',text:'抄送'}]"/>
</fieldset>
<%} %>


<%
    if (taskName.equals("AuditTask")) {
        if (operateType.equals("91")) {
%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <span id="roleName"></span>

    </legend>

    <eoms:chooser id="sendRole" type="role" roleId="320" flowId="091" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',allowBlank:false,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>
</fieldset>
<%
        }
    }
%>

<%
    if (taskName.equals("AnalysisTask")) {
        if (operateType.equals("92")) {
%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <span id="roleName"></span>
    </legend>
    <eoms:chooser id="sendRole1" type="role" roleId="320" flowId="091" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',allowBlank:false,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>
    <eoms:chooser id="sendRole2" type="role" roleId="321" flowId="091" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',allowBlank:false,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}subAuditPerformer',childType:'user,subrole',limit:'none',text:'会审'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>
    <eoms:chooser id="sendRole3" type="role" roleId="323" flowId="091" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',allowBlank:false,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>

</fieldset>
<%
        }
    }
%>


<%
    if (taskName.equals("AddInfoTask")) {
        if (operateType.equals("93")) {
%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="itrequirement" key="role.admin"/>
    </legend>
</fieldset>
<%
        }
    }
%>

<%
    if (taskName.equals("OperateTask")) {
        if (operateType.equals("95")) {
%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="itrequirement" key="role.applyer"/>
    </legend>
</fieldset>
<%
        }
    }
%>
<%
    if (taskName.equals("DevAuditTask")) {
        if (operateType.equals("94")) {
%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <span id="roleName"></span>
    </legend>

    <eoms:chooser id="sendRole5" type="role" roleId="320" flowId="091" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',allowBlank:false,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>
</fieldset>
<%
        }
    }
%>
<%
    if (taskName.equals("HoldTask")) {
        if (operateType.equals("102")) {
%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="itrequirement" key="role.admin"/>
    </legend>
</fieldset>
<%
        }
    }
%>
<%
    if (taskName.equals("ConfirmHoldTask")) {
        if (operateType.equals("101")) {
%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="itrequirement" key="role.operategroup"/>
    </legend>
</fieldset>
<%
        }
    }
%>
<script type="text/javascript">
    function ifAuditPass(input) {
        if ('<%=operateType%>' != '55') {
            if (input == "101140101") {
                chooser_sendRole.enable();
                //审核通过到需求分析
                $('${sheetPageName}phaseId').value = 'AnalysisTask';
                $('${sheetPageName}operateType').value = '911';
                $('roleName').innerHTML = "IT需求管理员";
            } else if (input == "101140102") {
                //审核不通过到驳回
                chooser_sendRole.disable();
                $('${sheetPageName}phaseId').value = 'RejectTask';
                $('${sheetPageName}operateType').value = '912';
                $('roleName').innerHTML = "IT需求申请人";
            } else {
                chooser_sendRole.disable();
                $('${sheetPageName}phaseId').value = '';
                $('${sheetPageName}operateType').value = '';
                $('roleName').innerHTML = "";
            }
        }
    }

    function ifAuditPass1(input) {
        if ('<%=operateType%>' != '55') {
            if (input == "101140801") {
                //本期实现到回复申请
                chooser_sendRole5.enable();
                $('${sheetPageName}phaseId').value = 'ReplyTask';
                $('${sheetPageName}operateType').value = '941';
                $('roleName').innerHTML = "IT需求管理员";
            } else if (input == "101140802") {
                //下期实现到暂存
                chooser_sendRole5.disable();
                $('${sheetPageName}phaseId').value = 'TempSaveTask';
                $('${sheetPageName}operateType').value = '942';
                $('roleName').innerHTML = "IT需求申请人";
            } else {
                chooser_sendRole5.disable();
                $('${sheetPageName}phaseId').value = '';
                $('${sheetPageName}operateType').value = '';
                $('roleName').innerHTML = "";
            }
        }
    }

    function ifAnalysisPass(input) {
        if ('<%=operateType%>' != '11') {
            if (input == "101140301") {
                chooser_sendRole1.disable();
                chooser_sendRole2.disable();
                chooser_sendRole3.disable();
                $('${sheetPageName}phaseId').value = 'RejectTask';
                $('${sheetPageName}operateType').value = '925';
                $('roleName').innerHTML = "IT需求申请人";
            } else if (input == "101140302") {
                chooser_sendRole1.disable();
                chooser_sendRole2.disable();
                chooser_sendRole3.disable();
                $('${sheetPageName}phaseId').value = 'AddInfoTask';
                $('${sheetPageName}operateType').value = '921';
                $('roleName').innerHTML = "IT需求申请人";
            } else if (input == "101140303") {
                chooser_sendRole1.disable();
                chooser_sendRole2.disable();
                chooser_sendRole3.enable();
                $('${sheetPageName}phaseId').value = 'OperateTask';
                $('${sheetPageName}operateType').value = '924';
                $('roleName').innerHTML = "IT需求实施组";
            } else if (input == "101140304") {
                chooser_sendRole1.enable();
                chooser_sendRole2.disable();
                chooser_sendRole3.disable();
                $('${sheetPageName}phaseId').value = 'ReplyTask';
                $('${sheetPageName}operateType').value = '922';
                $('roleName').innerHTML = "IT需求管理员";
            } else if (input == "101140305") {
                chooser_sendRole1.disable();
                chooser_sendRole2.enable();
                chooser_sendRole3.disable();
                $('${sheetPageName}phaseId').value = 'DevAuditTask';
                $('${sheetPageName}operateType').value = '923';
                $('roleName').innerHTML = "IT负责人";
            } else {
                chooser_sendRole1.disable();
                chooser_sendRole2.disable();
                chooser_sendRole3.disable();
                $('${sheetPageName}phaseId').value = '';
                $('${sheetPageName}operateType').value = '';
                $('roleName').innerHTML = "";
            }
        }
    }

    var frm = document.forms[0];
    var temp = frm.linkAuditResult ? frm.linkAuditResult.value : '';
    var temp1 = frm.linkAnalysisResult ? frm.linkAnalysisResult.value : '';
    if ("${taskName}" == "AuditTask" && '<%=operateType%>' != '61' && temp != '') {
        ifAuditPass(temp);
    }
    if ("${taskName}" == "DevAuditTask" && '<%=operateType%>' != '61' && temp != '') {
        ifAuditPass(temp);
    }
    if ("${taskName}" == "AnalysisTask" && '<%=operateType%>' != '61' && temp1 != '') {
        ifAnalysisPass(temp1);
    }
    if ("${taskName}" == "AnalysisTask" && '<%=operateType%>' != '61' && '<%=operateType%>' != '11' && '<%=operateType%>' != '4' && temp1 == '') {
        chooser_sendRole1.disable();
        chooser_sendRole2.disable();
        chooser_sendRole3.disable();
    }

    function openwin() {

        var url = "${app}/sheet/itsoftchange/itsoftchange.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iITRequirementMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}&invokeMode=asynchronism";
        window.open(url, 'newwindow', 'height=800, width=1000, top=200, left=200,toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
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
</script>	 
