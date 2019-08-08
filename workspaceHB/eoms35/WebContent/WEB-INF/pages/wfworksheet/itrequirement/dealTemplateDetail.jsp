<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%
    com.boco.eoms.sheet.itrequirement.model.ITRequirementLink itrequirementLink = (com.boco.eoms.sheet.itrequirement.model.ITRequirementLink) request.getAttribute("sheetLink");
    java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(itrequirementLink.getActiveTemplateId());
    java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(itrequirementLink.getOperateType()));
%>
<script type="text/javascript">
    var frm = document.forms[0];

    function saveDealTemplate(type) {
        var form = document.forms[0];
        if (form.templateName.value == "") {
            alert('<bean:message bundle="sheet" key="template.save" />');
            return false;
        }
        form.action = "${app}/sheet/itrequirement/itrequirement.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
        form.submit();
    }

    function removeTemplate() {
        if (confirm('<bean:message bundle="sheet" key="worksheet.delete.confirm" />')) {
            var thisform = document.forms[0];
            thisform.action = thisform.action + "?method=removeDealTemplate&dealTemplateId=${sheetLink.id}";
            thisform.submit();
        }
    }
</script>
<%@ include file="/WEB-INF/pages/wfworksheet/itrequirement/baseinputlinkhtmlnew.jsp" %>
<html:form action="/itrequirement.do" styleId="theform">
    <br/>


    <input type="hidden" name="${sheetPageName}beanId" value="iITRequirementMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.itrequirement.model.ITRequirementMain"/> <!--main表Model对象类名-->
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.itrequirement.model.ITRequirementLink"/> <!--link表Model对象类名-->
    <!--<input type="hidden" name="${sheetPageName}roleId" id="${sheetPageName}roleId" value="106">-->

    <table class="formTable">
        <caption><bean:message bundle="itrequirement" key="itrequirement.header"/></caption>
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="input.templateName"/>
            </td>
            <td colspan="3">
                <input type="text" name="templateName" class="text" id="templateName"
                       value="${sheetLink.templateName}"/>
            </td>
        </tr>
        <%
            if (taskName.equals("AuditTask")) {
                if (operateType.equals("91") || operateType.equals("55")) {
        %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AnalysisTask"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType %>"/>
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
                          alt="width:500,allowBlank:false,maxLength:2000,vtext:'请填入审批意见，最多输入1000字'">${sheetLink.linkAuditDesc}</textarea>
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
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType %>"/>
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
                <textarea class="textarea max" name="${sheetPageName}linkAnalysisDesc"
                          id="${sheetPageName}linkAnalysisDesc"
                          alt="width:500,allowBlank:false,maxLength:2000,vtext:'请填入需求分析概述，最多输入1000字'">${sheetLink.linkAnalysisDesc}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="itrequirement" key="itrequirement.linkHardWareExp"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkHardWareExp"
                          id="${sheetPageName}linkHardWareExp"
                          alt="width:500,allowBlank:false,maxLength:2000,vtext:'请填入硬件扩容需求，最多输入1000字'">${sheetLink.linkHardWareExp}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="itrequirement" key="itrequirement.linkSystemImpact"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkSystemImpact"
                          id="${sheetPageName}linkSystemImpact"
                          alt="width:500,allowBlank:false,maxLength:2000,vtext:'请填入系统性能影响，最多输入1000字'">${sheetLink.linkSystemImpact}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="itrequirement" key="itrequirement.linkOtherImpact"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkOtherImpact"
                          id="${sheetPageName}linkOtherImpact"
                          alt="width:500,allowBlank:false,maxLength:2000,vtext:'请填入其他影响，最多输入1000字'">${sheetLink.linkOtherImpact}</textarea>
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
                <eoms:attachment idList="" idField="${sheetPageName}linkTechnicalprogram" name="sheetLink"
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
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType %>"/>

        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
            </td>
            <td class="content">
                <input class="text" onclick="popUpCalendar(this, this)" type="text"
                       name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                       readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                       alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'${eoms:a2u("受理时限不能晚于处理时限")}',allowBlank:false"/>

            </td>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
            </td>
            <td class="content">
                <input class="text" onclick="popUpCalendar(this, this)" type="text"
                       name="${sheetPageName}nodeCompleteLimit" readonly="readonly"
                       value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit"
                       alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'${eoms:a2u("处理时限不能早于受理时限")}',allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="itrequirement" key="itrequirement.linkRequirementDesc"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkRequirementDesc"
                          id="${sheetPageName}linkRequirementDesc"
                          alt="width:500,allowBlank:false,maxLength:2000,vtext:'请填入需求概述，最多输入1000字'">${sheetLink.linkRequirementDesc}</textarea>
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
                <eoms:attachment idList="" idField="${sheetPageName}linkRequirementDetail"
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
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType %>"/>
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
                          alt="width:500,allowBlank:false,maxLength:2000,vtext:'请填入审批意见，最多输入1000字'">${sheetLink.linkAuditDesc}</textarea>
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
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType %>"/>
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
            </td>
            <td class="content">
                <input class="text" onclick="popUpCalendar(this, this)" type="text"
                       name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                       readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                       alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'${eoms:a2u("受理时限不能晚于处理时限")}',allowBlank:false"/>

            </td>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
            </td>
            <td class="content">
                <input class="text" onclick="popUpCalendar(this, this)" type="text"
                       name="${sheetPageName}nodeCompleteLimit" readonly="readonly"
                       value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit"
                       alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'${eoms:a2u("完成时限不能早于受理时限")}',allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="itrequirement" key="itrequirement.linkCompleteDesc"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkCompleteDesc"
                          id="${sheetPageName}linkCompleteDesc"
                          alt="width:500,allowBlank:false,maxLength:2000,vtext:'请填入完成情况概述，最多输入1000字'">${sheetLink.linkCompleteDesc}</textarea>
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
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType %>"/>
        <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
        <input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="4"/>
        <!--      <tr>
		            <td  class="label"><bean:message bundle="itrequirement" key="itrequirement.linkTempSaveDesc"/>*</td>
		              <td colspan="3"> 	
    				  <textarea class="textarea max" name="${sheetPageName}linkTempSaveDesc" id="${sheetPageName}linkTempSaveDesc" alt="width:500,allowBlank:false,maxLength:2000,vtext:'请填入说明，最多输入1000字'">${sheetLink.linkTempSaveDesc}</textarea>
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
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType %>"/>
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
            </td>
            <td class="content">
                <input class="text" onclick="popUpCalendar(this, this)" type="text"
                       name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                       readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                       alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'${eoms:a2u("受理时限不能晚于处理时限")}',allowBlank:false"/>

            </td>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
            </td>
            <td class="content">
                <input class="text" onclick="popUpCalendar(this, this)" type="text"
                       name="${sheetPageName}nodeCompleteLimit" readonly="readonly"
                       value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit"
                       alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'${eoms:a2u("完成时限不能早于受理时限")}',allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="itrequirement" key="itrequirement.linkReplyDesc"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkReplyDesc" id="${sheetPageName}linkReplyDesc"
                          alt="width:500,allowBlank:false,maxLength:2000,vtext:'请填入回复内容，最多输入1000字'">${sheetLink.linkReplyDesc}</textarea>
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
                          alt="allowBlank:false,maxLength:2000,width:500,vtext:'请填入信息，最多输入1000字'"
                          alt="width:'500px'">${sheetLink.remark}</textarea>
            </td>
        </tr>

        <%} %>

        <%if (taskName.equals("HoldTask")) {%>
        <%if (operateType.equals("18")) { %>

        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverTask"/>
        <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18"/>
        <input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>
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
                <textarea name="${sheetPageName}endResult" alt="allowBlank:false,maxLength:2000,vtext:'请最多输入1000字'"
                          id="${sheetPageName}endResult" class="textarea max">${sheetMain.endResult}</textarea>
            </td>
        </tr>
        <%} else if (operateType.equals("102")) {%>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ReplyTask"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType %>"/>
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.remark"/>*
            </td>
            <td colspan="3">
			        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                              alt="allowBlank:false,maxLength:2000,width:500,vtext:'请填入信息，最多输入1000字'"
                              alt="width:'500px'">${sheetLink.remark}</textarea>
            </td>
        </tr>
        <%
                }
            }
        %>

        <%if (taskName.equals("ConfirmHoldTask")) {%>
        <%if (operateType.equals("99")) { %>

        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverTask"/>
        <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18"/>
        <input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>
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
                <textarea name="${sheetPageName}endResult" alt="allowBlank:false,maxLength:2000,vtext:'请最多输入1000字'"
                          id="${sheetPageName}endResult" class="textarea max">${sheetMain.endResult}</textarea>
            </td>
        </tr>
        <%} else if (operateType.equals("101")) {%>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OperateTask"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType %>"/>
        <tr>
            <td class="label"><bean:message bundle="itrequirement" key="itrequirement.linkTestDesc"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkTestDesc" id="${sheetPageName}linkTestDesc"
                          alt="allowBlank:false,maxLength:2000,vtext:'请填入测试说明，请最多输入1000字'">${sheetMain.linkTestDesc}</textarea>
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
                          alt="allowBlank:false,maxLength:2000,vtext:'请填入信息，最多输入1000字'"
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
                    alt="width:'500px,vtext:'请最多输入1000字''">${sheetLink.remark}</textarea>
              </td>
            </tr>  	--%>

        <%}%>
    </table>
</html:form>
<logic:present name="type">
    <c:if test="${dealTemplateId != null && dealTemplateId != ''}">
        <html:button styleClass="btn" property="method.save" styleId="method.save"
                     onclick="saveDealTemplate('current')">
            <bean:message bundle="sheet" key="button.saveCurTemplate"/>
        </html:button>
    </c:if>
    <html:button styleClass="btn" property="method.save" styleId="method.save" onclick="removeTemplate()">
        <bean:message bundle="sheet" key="button.delete"/>
    </html:button>
</logic:present>
<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="history.back(-1)">
    <bean:message bundle="sheet" key="button.back"/>
</html:button>
       