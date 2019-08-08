<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%
    com.boco.eoms.sheet.itsoftchange.model.ITSoftChangeLink itsoftchangeLink = (com.boco.eoms.sheet.itsoftchange.model.ITSoftChangeLink) request.getAttribute("sheetLink");
    java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(itsoftchangeLink.getActiveTemplateId());
    java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(itsoftchangeLink.getOperateType()));
%>
<script type="text/javascript">
    var frm = document.forms[0];

    function saveDealTemplate(type) {
        var form = document.forms[0];
        if (form.templateName.value == "") {
            alert('<bean:message bundle="sheet" key="template.save" />');
            return false;
        }
        form.action = "${app}/sheet/itsoftchange/itsoftchange.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
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
<%@ include file="/WEB-INF/pages/wfworksheet/itsoftchange/baseinputlinkhtmlnew.jsp" %>
<html:form action="/itsoftchange.do" styleId="theform">
    <br/>


    <input type="hidden" name="${sheetPageName}beanId" value="iITSoftChangeMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.itsoftchange.model.ITSoftChangeMain"/> <!--main表Model对象类名-->
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.itsoftchange.model.ITSoftChangeLink"/> <!--link表Model对象类名-->
    <!--<input type="hidden" name="${sheetPageName}roleId" id="${sheetPageName}roleId" value="106">-->

    <table class="formTable">
        <caption><bean:message bundle="itsoftchange" key="itsoftchange.header"/></caption>
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
            if (taskName.equals("OperateTask")) {
                if (operateType.equals("91") || operateType.equals("11")) {
        %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TestTask"/>
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
            <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkDevContacter"/>*</td>
            <td colspan="3"><input type="text" class="text" name="${sheetPageName}linkDevContacter"
                                   id="${sheetPageName}linkDevContacter"
                                   alt="allowBlank:false,,maxLength:50,vtext:'请填入开发人员及联系方式，最多输入25字'"/></td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkCompleteDesc"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkCompleteDesc"
                          id="${sheetPageName}linkCompleteDesc"
                          alt="width:500,allowBlank:false,maxLength:500,vtext:'请填入完成说明，最多输入250字'">${sheetLink.linkCompleteDesc}</textarea>
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
            <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkTestGuide"/>*</td>
            <td colspan='3'>
                <eoms:attachment idList="" scope="request" idField="${sheetPageName}linkTestGuide" name="sheetLink"
                                 property="linkTestGuide" appCode="itsoftchangesheet" alt="allowBlank:false"/>
            </td>
        </tr>
        <%
                }
            }
        %>


        <%
            if (taskName.equals("TestTask")) {
                if (operateType.equals("92") || operateType.equals("11")) {
        %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OnlineTask"/>
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
            <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkTestResult"/>*</td>
            <td cclass="content">
                <eoms:comboBox name="${sheetPageName}linkTestResult" id="${sheetPageName}linkTestResult"
                               initDicId="1011406" alt="allowBlank:false" styleClass="select-class"
                               onchange="ifTestPass(this.value);" defaultValue="${sheetLink.linkTestResult }"/>
            </td>
            <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkTestSatisfaction"/>*</td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}linkTestSatisfaction" id="${sheetPageName}linkTestSatisfaction"
                               initDicId="10303" alt="allowBlank:false" styleClass="select-class"
                               defaultValue="${sheetLink.linkTestSatisfaction }"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkTestDesc"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkTestDesc" id="${sheetPageName}linkTestDesc"
                          alt="width:500,allowBlank:false,maxLength:500,vtext:'请填入测试说明，最多输入250字'">${sheetLink.linkTestDesc}</textarea>
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
            <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkTestNote"/>*</td>
            <td colspan='3'>
                <eoms:attachment idList="" scope="request" idField="${sheetPageName}linkTestNote" name="sheetLink"
                                 property="linkTestNote" appCode="itsoftchangesheet" alt="allowBlank:false"/>
            </td>
        </tr>
        <%
                }
            }
        %>

        <%
            if (taskName.equals("OnlineTask")) {
                if (operateType.equals("93") || operateType.equals("11")) {
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
            <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkOnlineTime"/>*</td>
            <td class="content" colspan='3'>
                <input type="text" class="text" name="${sheetPageName}linkOnlineTime" readonly="readonly"
                       id="${sheetPageName}linkOnlineTime" value="${eoms:date2String(sheetLink.linkOnlineTime)}"
                       onclick="popUpCalendar(this, this)" alt="false"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkOnlineDesc"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkOnlineDesc" id="${sheetPageName}linkOnlineDesc"
                          alt="width:500,allowBlank:false,maxLength:500,vtext:'请填入上线说明，最多输入250字'">${sheetLink.linkOnlineDesc}</textarea>
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
            <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkUserNoteDesc"/>*</td>
            <td colspan='3'>
                <eoms:attachment idList="" scope="request" idField="${sheetPageName}linkUserNoteDesc" name="sheetLink"
                                 property="linkUserNoteDesc" appCode="itsoftchangesheet" alt="allowBlank:false"/>
            </td>
        </tr>
        <%
                }
            }
        %>
        <%if (operateType.equals("4")) { %>
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
                <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId"
                       value="${fPreTaskName}"/>
            </c:otherwise>
        </c:choose>
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.remark"/>*
            </td>
            <td colspan="3">
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="allowBlank:false,maxLength:500,width:500,vtext:'请填入信息，最多输入250字'"
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
            <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkAuditResult"/>*</td>
            <td>
                <eoms:comboBox name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult"
                               initDicId="1011407" alt="allowBlank:false" styleClass="select-class"
                               defaultValue="${sheetLink.linkAuditResult}"/>
            </td>

            <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkAnalysisCount"/>*</td>
            <td><input type="text" class="text" name="${sheetPageName}linkAnalysisCount"
                       id="${sheetPageName}linkAnalysisCount"
                       alt="allowBlank:false,,maxLength:50,vtext:'请填入需求分析工作量，最多输入25字'"/></td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkTestCount"/>*</td>
            <td><input type="text" class="text" name="${sheetPageName}linkTestCount" id="${sheetPageName}linkTestCount"
                       alt="allowBlank:false,,maxLength:50,vtext:'请填入现场实施与测试工作量，最多输入25字'"/></td>
            <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkDevCount"/>*</td>
            <td><input type="text" class="text" name="${sheetPageName}linkDevCount" id="${sheetPageName}linkDevCount"
                       alt="allowBlank:false,,maxLength:50,vtext:'请填入研发工作量，最多输入25字'"/></td>
        </tr>
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
                              alt="allowBlank:false,maxLength:500,width:500,vtext:'请填入信息，最多输入250字'"
                              alt="width:'500px'">${sheetLink.remark}</textarea>
            </td>
        </tr>
        <%
                }
            }
        %>
        <% if (taskName.equals("cc")) {%>
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.remark"/>*
            </td>
            <td colspan="3">
                <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15"/>
                <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="allowBlank:false,maxLength:500,vtext:'请填入信息，最多输入250字'"
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
       