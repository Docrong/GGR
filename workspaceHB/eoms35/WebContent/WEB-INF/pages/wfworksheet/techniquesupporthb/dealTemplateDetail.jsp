<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%
    com.boco.eoms.sheet.techniquesupporthb.model.TechniqueSupportHbLink techniquesupporthbLink = (com.boco.eoms.sheet.techniquesupporthb.model.TechniqueSupportHbLink) request.getAttribute("sheetLink");
    java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(techniquesupporthbLink.getActiveTemplateId());
    java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(techniquesupporthbLink.getOperateType()));
%>
<script type="text/javascript">
    var frm = document.forms[0];

    function saveDealTemplate(type) {
        var form = document.forms[0];
        if (form.templateName.value == "") {

            return false;
        }
        form.action = "${app}/sheet/techniquesupporthb/techniquesupporthb.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
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
<%@ include file="/WEB-INF/pages/wfworksheet/techniquesupporthb/baseinputlinkhtmlnew.jsp" %>
<html:form action="/techniquesupporthb.do" styleId="theform">
    <br/>

    <table class="formTable">

        <caption><bean:message bundle="techniquesupporthb" key="techniquesupporthb.header"/></caption>
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="input.templateName"/>
            </td>
            <td colspan="3">
                <input type="text" name="templateName" class="text max" id="templateName"
                       value="${sheetLink.templateName}"/>
            </td>
        </tr>

        <input type="hidden" id="tmpCompleteLimit" value=""
               alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'å¤çæ¶éä¸è½æäºå·¥åå®ææ¶é'"/>
        <input type="hidden" name="${sheetPageName}beanId" value="iTechniqueSupportHbMainManager"/>
        <input type="hidden" name="${sheetPageName}mainClassName"
               value="com.boco.eoms.sheet.techniquesupporthb.model.TechniqueSupportHbMain"/>
        <input type="hidden" name="${sheetPageName}linkClassName"
               value="com.boco.eoms.sheet.techniquesupporthb.model.TechniqueSupportHbLink"/>
        <input type="hidden" name="${sheetPageName}toDeptId" value="${sheetMain.toDeptId}"/>
        <c:if test="${taskName != 'HoldTask' }">
            <%if (operateType.equals("204")) { %>
            <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${task.operateRoleId}"/>
            <%} else {%>
            <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
            <%} %>
        </c:if>

        <c:choose>
            <c:when test="${task.subTaskFlag == 'true'}">
                <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"
                       value="true"/>
            </c:when>
        </c:choose>


        <%if (operateType.equals("4")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4"/>
        <c:choose>
            <c:when test="${taskName=='AcceptTask'}">
                <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask"/>
            </c:when>
            <c:when test="${taskName=='ExecuteTask'}">
                <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AcceptTask"/>
            </c:when>
            <c:when test="${taskName=='AcceptReply'}">
                <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExecuteTask"/>
            </c:when>
        </c:choose>
        <tr>
            <td class="label">
                å¤æ³¨è¯´æ*
            </td>
            <td colspan="3">
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="allowBlank:false,width:500,vtext:'è¯·æå¤è¾å¥1000å­'"
                          alt="width:'500px'">${sheetLink.remark}</textarea>
            </td>
        </tr>

        <%} %>


        <%if (taskName.equals("DoneImplTask")) {%>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType %>"/>
        <%if (operateType.equals("911") || operateType.equals("11")) { %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ImplValidTask"/>


        <tr>
            <td class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.linkDealOpinion"/>*</td>
            <td colspan="3">
			      <textarea class="textarea max" name="${sheetPageName}linkDealOpinion"
                            id="${sheetPageName}linkDealOpinion"
                            alt="allowBlank:false,maxLength:200,vtext:'审核意见，最多输入100汉字'">${sheetLink.linkDealOpinion}</textarea>
            </td>
        </tr>

        <%} %>
        <%} else if (taskName.equals("ExecuteTask")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType%>"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AcceptReply"/>
        <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
        <%if (operateType.equals("202") || operateType.equals("11")) { %>

        <tr>
            <td class="label">å¸å·ç±»å</td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}linkAccountType" id="${sheetPageName}linkAccountType"
                               initDicId="1011501" defaultValue="${sheetLink.linkAccountType}" styleClass="select"/>
            </td>
            <td class="label">æ¯å¦å¼éæå</td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}linkIfDredge" id="${sheetPageName}linkIfDredge" initDicId="10301"
                               defaultValue="${sheetLink.linkIfDredge}" styleClass="select"/>
            </td>
        </tr>

        <tr>
            <td class="label">å¼éæåµè¯´æ</td>
            <td colspan="3" class="content">
			      <textarea class="textarea max" name="${sheetPageName}linkDredgeDesc"
                            id="${sheetPageName}linkDredgeDesc"
                            alt="allowBlank:true,maxLength:500,vtext:'å®æ´å°åæè¿°ï¼æå¤è¾å¥250æ±å­'">${sheetLink.linkDredgeDesc}</textarea>
            </td>
        </tr>
        <%} %>

        <%} else if (taskName.equals("AcceptReply")) {%>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType %>"/>
        <%if (operateType.equals("201") || operateType.equals("11")) { %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExecuteTask"/>
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
            </td>
            <td class="content">
                <input class="text" onclick="popUpCalendar(this, this)" type="text"
                       name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                       readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                       alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'åçæ¶éä¸è½æäºå¤çæ¶é',allowBlank:false"/>

            </td>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
            </td>
            <td class="content">
                <input class="text" onclick="popUpCalendar(this, this)" type="text"
                       name="${sheetPageName}nodeCompleteLimit" readonly="readonly"
                       value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit"
                       alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'å¤çæ¶éä¸è½æ©äºåçæ¶é',allowBlank:false"/>
            </td>
        </tr>

        <tr>
            <td class="label">å®æ½åå®¹</td>
            <td colspan="3">
				      <textarea class="textarea max" name="${sheetPageName}linkTransmitReason"
                                id="${sheetPageName}linkTransmitReason"
                                alt="allowBlank:true,maxLength:500,vtext:'å®æ´å°åæè¿°ï¼æå¤è¾å¥250æ±å­'">${sheetLink.linkTransmitReason}</textarea>
            </td>
        </tr>

        <%} %>
        <%if (operateType.equals("203") || operateType.equals("11")) { %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask"/>

        <tr>
            <td class="label">å¸å·ç±»å</td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}linkAccountType" id="${sheetPageName}linkAccountType"
                               initDicId="1011501" defaultValue="${sheetLink.linkAccountType}" styleClass="select"/>
            </td>
            <td class="label">æ¯å¦å¼éæå</td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}linkIfDredge" id="${sheetPageName}linkIfDredge" initDicId="10301"
                               defaultValue="${sheetLink.linkIfDredge}" styleClass="select"/>
            </td>
        </tr>

        <tr>
            <td class="label">ç½ç»é¨é¨èç³»äºº</td>
            <td>
                <input type="text" class="text" name="${sheetPageName}linkNdeptContact"
                       id="${sheetPageName}linkNdeptContact" value="${sheetLink.linkNdeptContact}"
                       alt="allowBlank:true,maxLength:50"/>
            </td>

            <td class="label">ç½ç»é¨é¨èç³»äººçµè¯</td>
            <td>
                <input type="text" class="text" name="${sheetPageName}linkNdeptContactPhone"
                       id="${sheetPageName}linkNdeptContactPhone" value="${sheetLink.linkNdeptContactPhone}"
                       alt="allowBlank:true,maxLength:50"/>
            </td>
        </tr>

        <tr>
            <td class="label">å»ºè®¾æ¹å¼</td>
            <td class="content" colspan='3'>
                <eoms:comboBox name="${sheetPageName}linkResBuildType" id="${sheetPageName}linkResBuildType"
                               initDicId="1011506" defaultValue="${sheetLink.linkResBuildType}" styleClass="select"/>
            </td>
        </tr>

        <tr>
            <td class="label">å¼éæåµè¯´æ</td>
            <td colspan="3" class="content">
			      <textarea class="textarea max" name="${sheetPageName}linkDredgeDesc"
                            id="${sheetPageName}linkDredgeDesc"
                            alt="allowBlank:true,maxLength:500,vtext:'å®æ´å°åæè¿°ï¼æå¤è¾å¥250æ±å­'">${sheetLink.linkDredgeDesc}</textarea>
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
                            alt="allowBlank:false,maxLength:2000,vtext:'è¯·è¾å¥å½æ¡£åå®¹ï¼æå¤è¾å¥1000æ±å­'">${sheetMain.endResult}</textarea>
            </td>
        </tr>
        <%}%>
        <%if (operateType.equals("17")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="17"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AcceptTask"/>

        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.remark"/>*
            </td>
            <td colspan="3">
					        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                                      alt="allowBlank:false,width:500,vtext:'è¯·æå¤è¾å¥1000å­'"
                                      alt="width:'500px'">${sheetLink.remark}</textarea>
            </td>
        </tr>
        <%}%>

        <%}%>
        <%if (!operateType.equals("61") && !operateType.equals("18") && !operateType.equals("4")) { %>
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
            <td class="label"><bean:message bundle="sheet" key="linkForm.accessories"/></td>
            <td colspan="3">
                <eoms:attachment name="sheetLink" property="nodeAccessories"
                                 scope="request" idField="${sheetPageName}nodeAccessories"
                                 appCode="techniquesupporthb"/>
            </td>
        </tr>
        <%}%>
        <%if (taskName.equals("AcceptTask") || taskName.equals("ExecuteTask") || taskName.equals("AcceptReply")) {%>
        <%if (operateType.equals("61")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
        <!--  <tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px',maxLength:200,vtext:'æå¤è¾å¥100æ±å­'">${sheetLink.remark}</textarea>
		  </td>
		</tr> -->

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
                          alt="allowBlank:false,width:500,maxLength:2000,vtext:'è¯·æå¤è¾å¥1000æ±å­'"
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
