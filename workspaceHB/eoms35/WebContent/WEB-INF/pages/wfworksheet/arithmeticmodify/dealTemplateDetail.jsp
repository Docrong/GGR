<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
    com.boco.eoms.sheet.arithmeticmodify.model.ArithmeticModifyLink arithmeticmodifyLink = (com.boco.eoms.sheet.arithmeticmodify.model.ArithmeticModifyLink) request.getAttribute("sheetLink");
    java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(arithmeticmodifyLink.getActiveTemplateId());
    java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(arithmeticmodifyLink.getOperateType()));
%>
<script type="text/javascript">
    var frm = document.forms[0];

    function saveDealTemplate(type) {
        var form = document.forms[0];
        if (form.templateName.value == "") {

            return false;
        }
        form.action = "${app}/sheet/arithmeticmodify/arithmeticmodify.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
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
<%@ include file="/WEB-INF/pages/wfworksheet/arithmeticmodify/baseinputlinkhtmlnew.jsp" %>
<html:form action="/arithmeticmodify.do" styleId="theform">
    <br/>
    <table class="formTable">
        <input type="hidden" id="tmpCompleteLimit" value=""
               alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'处理时限不能晚于工单完成时限'"/>
        <input type="hidden" name="${sheetPageName}beanId" value="iArithmeticModifyMainManager"/>
        <input type="hidden" name="${sheetPageName}mainClassName"
               value="com.boco.eoms.sheet.arithmeticmodify.model.ArithmeticModifyMain"/>
        <input type="hidden" name="${sheetPageName}linkClassName"
               value="com.boco.eoms.sheet.arithmeticmodify.model.ArithmeticModifyLink"/>
        <c:if test="${taskName != 'HoldTask' }">
            <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
        </c:if>
        <c:choose>
            <c:when test="${task.subTaskFlag == 'true'}">
                <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"
                       value="true"/>
            </c:when>
        </c:choose>
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
                驳回原因*
            </td>
            <td colspan="3">
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="allowBlank:false,width:500,vtext:'请最多输入1000字'"
                          alt="width:'500px'">${sheetLink.remark}</textarea>
            </td>
        </tr>
        <%} %>
        <%if (taskName.equals("ArithmeticModifyTask")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType%>"/>
        <%if (operateType.equals("90") || operateType.equals("11")) { %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="CheckTask"/>
        <tr>
            <td class="label">解决时间*</td>
            <td colspan="3">
                <input type="text" class="text" name="${sheetPageName}linkResolveTime" readonly="readonly"
                       id="${sheetPageName}linkResolveTime" value="${eoms:date2String(sheetLink.linkResolveTime)}"
                       onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label">后续处理过程*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}linkArithmeticModifyProcess"
                          id="${sheetPageName}linkArithmeticModifyProcess"
                          alt="width:500,allowBlank:false,maxLength:500,vtext:'请填入后续处理过程，最多输入500字符'">${sheetLink.linkArithmeticModifyProcess}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label">测试报告等相关文档*</td>
            <td colspan="3">
                <eoms:attachment name="sheetLink" property="linkTestReport"
                                 scope="request" idField="${sheetPageName}linkTestReport" appCode="arithmeticmodify"
                                 alt="allowBlank:false"/>
            </td>
        </tr>
        </tr>
        <%} %>
        <%} else if (taskName.equals("CheckTask")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType%>"/>
        <!-- 质检通过 -->
        <%if (operateType.equals("911")) { %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="911"/>
        <input type="hidden" name="${sheetPageName}linkCheckResult" id="${sheetPageName}linkCheckResult"
               value="1030101"/>
        <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
        <tr>
            <td class="label">质检结果*</td>
            <td colspan="3">
                通过
            </td>
        </tr>
        <tr>
            <td class="label">质检概述*</td>
            <td colspan="3">
                <textarea name="linkCheckIdea" id="linkCheckIdea" class="textarea max"
                          alt="allowBlank:false,maxLength:1000,vtext:'最多输入500汉字'">${sheetLink.linkCheckIdea}</textarea>
            </td>
        </tr>
        <%} %>
        <!-- 质检不通过 -->
        <%if (operateType.equals("912")) { %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ArithmeticModifyTask"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="912"/>
        <input type="hidden" name="${sheetPageName}linkCheckResult" id="${sheetPageName}linkCheckResult"
               value="1030102"/>
        <tr>
            <td class="label">质检结果*</td>
            <td colspan="3">
                不通过
            </td>
        </tr>
        <tr>
            <td class="label">质检概述*</td>
            <td colspan="3">
                <textarea name="linkCheckIdea" id="linkCheckIdea" class="textarea max"
                          alt="allowBlank:false,maxLength:1000,vtext:'最多输入500汉字'">${sheetLink.linkCheckIdea}</textarea>
            </td>
        </tr>
        <%} %>
        <%} else if (taskName.equals("HoldTask")) {%>
        <%if (operateType.equals("18")) { %>
        <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverTask"/>
        <input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>
        <tr>
            <td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
            <td colspan='3'>
                <eoms:comboBox name="${sheetPageName}holdStatisfied"
                               id="${sheetPageName}holdStatisfied"
                               defaultValue="${sheetMain.holdStatisfied != 0 ? sheetMain.holdStatisfied : 1030301}"
                               initDicId="10303" styleClass="select" alt="allowBlank:false"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/>*</td>
            <td colspan="3">
			      <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult" class="textarea max"
                            alt="allowBlank:false,maxLength:2000,vtext:'请输入归档内容，最多输入1000汉字'">${sheetMain.endResult}</textarea>
            </td>
        </tr>
        <%}%>
        <%if (operateType.equals("17")) { %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="CheckTask"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType %>"/>
        <tr>
            <td class="label">退回原因*</td>
            <td colspan="3">
			        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                              alt="allowBlank:false,maxLength:255,width:500,vtext:'请填入信息，最多输入125字'"
                              alt="width:'500px'">${sheetLink.remark}</textarea>
            </td>
        </tr>
        <%}%>
        <%}%>
        <%if (!operateType.equals("61") && !operateType.equals("18") && !operateType.equals("4") && !operateType.equals("17") && !operateType.equals("90")) { %>
        <tr>
            <td class="label"><bean:message bundle="sheet" key="linkForm.accessories"/></td>
            <td colspan="3">
                <eoms:attachment name="sheetLink" property="nodeAccessories"
                                 scope="request" idField="${sheetPageName}nodeAccessories" appCode="arithmeticmodify"/>
            </td>
        </tr>
        <%}%>
        <%if (taskName.equals("ArithmeticModifyTask") || taskName.equals("CheckTask")) {%>
        <%if (operateType.equals("61")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
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
                          alt="allowBlank:false,width:500,maxLength:2000,vtext:'请最多输入1000汉字'"
                          alt="width:'500px'">${sheetLink.remark}</textarea>
            </td>
        </tr>
        <%} %>
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
