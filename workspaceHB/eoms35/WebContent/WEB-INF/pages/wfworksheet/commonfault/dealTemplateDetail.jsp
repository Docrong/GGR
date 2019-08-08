<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%
    com.boco.eoms.sheet.commonfault.model.CommonFaultLink commonFaultLink = (com.boco.eoms.sheet.commonfault.model.CommonFaultLink) request.getAttribute("sheetLink");
    java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(commonFaultLink.getActiveTemplateId());
    java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(commonFaultLink.getOperateType()));
%>
<script type="text/javascript">
    var frm = document.forms[0];

    function saveDealTemplate(type) {
        var form = document.forms[0];
        if (form.templateName.value == "") {
            alert('<bean:message bundle="sheet" key="template.save" />');
            return false;
        }
        form.action = "${app}/sheet/commonfault/commonfault.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
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
<%@ include file="/WEB-INF/pages/wfworksheet/common/baseinputlinkhtmlnew.jsp" %>
<html:form action="/commonfault.do" styleId="theform">
    <br/>
    <input type="hidden" name="${sheetPageName}beanId" value="iCommonFaultMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.commonfault.model.CommonFaultMain"/> <!--main表Model对象类名-->
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.commonfault.model.CommonFaultLink"/> <!--link表Model对象类名-->
    <!--<input type="hidden" name="${sheetPageName}roleId" id="${sheetPageName}roleId" value="106">-->

    <table class="formTable">
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
            if (taskName.equals("FirstExcuteHumTask") || taskName.equals("SecondExcuteHumTask") ||
                    taskName.equals("ThirdExcuteHumTask") || taskName.equals("firstSubTask") ||
                    taskName.equals("secondSubTask") || taskName.equals("thirdSubTask")) {
        %>


        <%if (operateType.equals("1")) { %>

        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="1"/>

        <%if (taskName.equals("FirstExcuteHumTask")) { %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SecondExcuteTask"/>
        <%} %>


        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainIfMutualCommunication"/></td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}linkIfMutualCommunication"
                               id="${sheetPageName}linkIfMutualCommunication" initDicId="10301"
                               defaultValue="${sheetLink.linkIfMutualCommunication}"/>
            </td>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainIfSafe"/></td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}linkIfSafe" id="${sheetPageName}linkIfSafe" initDicId="10301"
                               defaultValue="${sheetLink.linkIfSafe}"/>
            </td>
        </tr>


        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultFirstDealDesc"/>*</td>
            <td colspan="3">
			 <textarea name="${sheetPageName}linkFaultFirstDealDesc" id="${sheetPageName}linkFaultFirstDealDesc"
                       class="textarea max"
                       alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入500汉字'">${sheetLink.linkFaultFirstDealDesc}</textarea>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultDesc"/></td>
            <td colspan="3">
		   	<textarea name="linkFaultDesc" id="linkFaultDesc" class="textarea max"
                      alt="allowBlank:true,width:500,maxLength:1000,vtext:'最多输入1000汉字'">${sheetLink.linkFaultDesc}</textarea>
            </td>
        </tr>

        <%} %>


        <%if (operateType.equals("2")) { %>

        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="2"/>

        <%if (taskName.equals("SecondExcuteHumTask")) { %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ThirdExcuteTask"/>
        <%} %>
        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultDealInfo"/>*</td>
            <td colspan="3">
		  		<textarea name="${sheetPageName}linkFaultDealInfo" id="${sheetPageName}linkFaultDealInfo"
                          class="textarea max"
                          alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入100汉字'">${sheetLink.linkFaultDealInfo}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.linkTransmitReason"/>*</td>
            <td colspan="3">
		  		<textarea name="${sheetPageName}linkTransmitReason" id="${sheetPageName}linkTransmitReason"
                          class="textarea max"
                          alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入250汉字'">${sheetLink.linkTransmitReason}</textarea>
            </td>
        </tr>

        <%} %>

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
                <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId"
                       value="BackExcuteTask"/>
            </c:when>
            <c:when test="${'DraftHumTask' == fPreTaskName}">
                <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId"
                       value="BackExcuteTask"/>
            </c:when>
            <c:when test="${'BackHumTask' == fPreTaskName}">
                <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId"
                       value="BackExcuteTask"/>
            </c:when>
            <c:when test="${'FirstExcuteHumTask' == fPreTaskName}">
                <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId"
                       value="FirstExcuteTask"/>
            </c:when>
            <c:when test="${'SecondExcuteHumTask' == fPreTaskName}">
                <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId"
                       value="SecondExcuteTask"/>
            </c:when>
        </c:choose>
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.remark"/>*
            </td>
            <td colspan="3">
			        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                              alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入500汉字'"
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
        <!--
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  	
		 -->
        <%} %>
        <%if (operateType.equals("46") || operateType.equals("11")) { %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask"/>
        <%
            if (taskName.equals("FirstExcuteHumTask") || taskName.equals("SecondExcuteHumTask") ||
                    taskName.equals("ThirdExcuteHumTask")) {
        %>
        <%if (operateType.equals("46")) { %>
        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultDealResult"/>*</td>
            <td class="content">
                <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="46"/>
                <eoms:comboBox name="${sheetPageName}linkFaultDealResult"
                               id="${sheetPageName}linkFaultDealResult" initDicId="1010306"
                               defaultValue="${sheetLink.linkFaultDealResult}" alt="allowBlank:false"/>
            </td>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfGreatFault"/></td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}linkIfGreatFault"
                               id="${sheetPageName}linkIfGreatFault" initDicId="10301"
                               defaultValue="${sheetLink.linkIfGreatFault}"/>
            </td>
        </tr>
        <%} else if (operateType.equals("11")) { %>
        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfGreatFault"/></td>
            <td class="content" colspan='3'>
                <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="11"/>
                <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"
                       value="splitReply"/>
                <eoms:comboBox name="${sheetPageName}linkIfGreatFault"
                               id="${sheetPageName}linkIfGreatFault" initDicId="10301"
                               defaultValue="${sheetLink.linkIfGreatFault}"/>
            </td>
        </tr>
        <%} %>

        <%} %>

        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultReasonSort"/>*</td>
            <td class="content">

                <eoms:comboBox name="${sheetPageName}linkFaultReasonSort"
                               id="${sheetPageName}linkFaultReasonSort" initDicId="1010303"
                               sub="${sheetPageName}linkFaultReasonSubsection"
                               defaultValue="${sheetLink.linkFaultReasonSort}" alt="allowBlank:false"/>
            </td>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultReasonSubsection"/>*</td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}linkFaultReasonSubsection"
                               id="${sheetPageName}linkFaultReasonSubsection"
                               initDicId="${sheetLink.linkFaultReasonSort}"
                               defaultValue="${sheetLink.linkFaultReasonSubsection}" alt="allowBlank:false"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.linkDealStep"/>*</td>
            <td colspan="3">
			  		<textarea name="linkDealStep" id="linkDealStep" class="textarea max"
                              alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入250汉字'">${sheetLink.linkDealStep}</textarea>
            </td>
        </tr>
        <%
            if ((taskName.equals("FirstExcuteHumTask") || taskName.equals("SecondExcuteHumTask") ||
                    taskName.equals("ThirdExcuteHumTask")) && operateType.equals("46")) {
        %>

        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfExcuteNetChange"/>*</td>
            <td>
                <eoms:comboBox name="${sheetPageName}linkIfExcuteNetChange"
                               id="${sheetPageName}linkIfExcuteNetChange" initDicId="10301"
                               defaultValue="${sheetLink.linkIfExcuteNetChange}" alt="allowBlank:false"/>
            </td>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfFinallySolveProject"/>*</td>
            <td>
                <eoms:comboBox name="${sheetPageName}linkIfFinallySolveProject"
                               id="${sheetPageName}linkIfFinallySolveProject" initDicId="10301"
                               defaultValue="${sheetLink.linkIfFinallySolveProject}" alt="allowBlank:false"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfAddCaseDataBase"/>*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}linkIfAddCaseDataBase"
                               id="${sheetPageName}linkIfAddCaseDataBase" initDicId="10301"
                               defaultValue="${sheetLink.linkIfAddCaseDataBase}" alt="allowBlank:false"/>
            </td>
        </tr>
        <%} %>
        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultAvoidTime"/>*</td>
            <td>
                <input type="text" class="text" name="${sheetPageName}linkFaultAvoidTime" readonly="readonly"
                       id="${sheetPageName}linkFaultAvoidTime"
                       onclick="popUpCalendar(this, this, null, null, null, true, -1)"
                       value="${eoms:date2String(sheetLink.linkFaultAvoidTime)}"
                       alt="vtype:'moreThen',link:'${sheetPageName}mainFaultGenerantTime',vtext:'故障消除时间晚于故障发生时间',allowBlank:false"/>
                <input type="hidden" name="${sheetPageName}mainFaultGenerantTime"
                       id="${sheetPageName}mainFaultGenerantTime" value="${sheetMain.mainFaultGenerantTime}"/>
            </td>
            <td id="norequiredtd" class="label"><bean:message bundle="commonfault"
                                                              key="commonfault.linkOperRenewTime"/></td>
            <td id="requiredtd" class="label" style="display:none"><bean:message bundle="commonfault"
                                                                                 key="commonfault.linkOperRenewTime"/>*
            </td>
            <td id="ifrequired">
                <input type="text" class="text" name="${sheetPageName}linkOperRenewTime" readonly="readonly"
                       id="${sheetPageName}linkOperRenewTime"
                       onclick="popUpCalendar(this, this,null,null,null,true,-1)"
                       value="${eoms:date2String(sheetLink.linkOperRenewTime)}" alt="allowBlank:true"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.linkAffectTimeLength"/></td>
            <td colspan="3">
                <input type="text" class="text" name="${sheetPageName}linkAffectTimeLength"
                       id="${sheetPageName}linkAffectTimeLength" value="${sheetLink.linkAffectTimeLength}"
                       alt="allowBlank:true"/>
            </td>

        </tr>


        <%} %>


        <%if (operateType.equals("5")) { %>

        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExamineTask"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="5"/>

        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.linkTransmitContent"/></td>
            <td colspan="3">
		  		<textarea name="linkTransmitContent" id="linkTransmitContent" class="textarea max"
                          alt="allowBlank:true,width:500,maxLength:1000,vtext:'最多输入500汉字'">${sheetLink.linkTransmitContent}</textarea>
            </td>
        </tr>

        <%} %>


        <%if (operateType.equals("19")) { %>

        <%if (taskName.equals("FirstExcuteHumTask")) { %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="FirstExcuteTask"/>
        <%}%>
        <%if (taskName.equals("SecondExcuteHumTask")) { %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SecondExcuteTask"/>
        <%}%>
        <%if (taskName.equals("ThirdExcuteHumTask")) { %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ThirdExcuteTask"/>
        <%}%>

        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="19"/>
        <input type="hidden" name="${sheetPageName}linkIfUrgentFault" id="${sheetPageName}linkIfUrgentFault"
               value="1030101"/>

        <%} %>

        <%if (operateType.equals("8")) { %>
        <!-- 环节内容（组内） 移交处理 -->
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="8"/>
        <%if (taskName.equals("FirstExcuteHumTask")) { %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="FirstExcuteTask"/>
        <%} %>
        <%if (taskName.equals("SecondExcuteHumTask")) { %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SecondExcuteTask"/>
        <%} %>
        <%if (taskName.equals("ThirdExcuteHumTask")) { %>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ThirdExcuteTask"/>
        <%} %>

        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.transmitReason"/>
            </td>
            <td colspan="3">
	    				<textarea class="textarea max" name="${sheetPageName}transferReason"
                                  id="${sheetPageName}transferReason"
                                  alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入500汉字'"
                                  alt="width:'500px'">${sheetLink.transferReason}</textarea>
            </td>
        </tr>
        <%} %>


        <%if (!operateType.equals("19") && !operateType.equals("4") && !operateType.equals("61") && !operateType.equals("8")) {%>

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
                                 scope="request" idField="${sheetPageName}nodeAccessories" appCode="commonfault"/>
            </td>
        </tr>
        <%}%>


        <%}%>


        <%if (taskName.equals("ExamineHumTask")) {%>


        <%if (operateType.equals("66")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="66"/>
        <input type="hidden" name="${sheetPageName}linkIfDeferResolve" id="${sheetPageName}linkIfDeferResolve"
               value="1030101"/>
        <input type="hidden" name="${sheetPageName}mainDelayFlag" id="${sheetPageName}mainDelayFlag" value="1"/>
        <%} %>
        <%if (operateType.equals("64")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="64"/>
        <input type="hidden" name="${sheetPageName}linkIfDeferResolve" id="${sheetPageName}linkIfDeferResolve"
               value="1030102"/>
        <input type="hidden" name="${sheetPageName}mainDelayFlag" id="${sheetPageName}mainDelayFlag" value=""/>
        <%} %>
        <%if (operateType.equals("55")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="55"/>
        <%} %>
        <%if (operateType.equals("88")) { %>
        <!-- 环节内（组内） 转审处理 -->
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExamineTask"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="88"/>

        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.transmitReason"/>*
            </td>
            <td colspan="3">
	    				<textarea class="textarea max" name="${sheetPageName}transferReason"
                                  id="${sheetPageName}transferReason"
                                  alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入250汉字'"
                                  alt="width:'500px'">${sheetLink.transferReason}</textarea>
            </td>
        </tr>
        <%} %>

        <%if (operateType.equals("61")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
        <!--
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  
		-->
        <%} %>

        <%if (operateType.equals("66") || operateType.equals("64") || operateType.equals("55")) { %>
        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.linkExamineContent"/></td>
            <td colspan="3">
                <textarea name="linkExamineContent" id="linkExamineContent"
                          class="textarea max">${sheetLink.linkExamineContent}</textarea>
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
            <td class="label"><bean:message bundle="sheet" key="linkForm.accessories"/></td>
            <td colspan="3">
                <eoms:attachment name="sheetLink" property="nodeAccessories"
                                 scope="request" idField="${sheetPageName}nodeAccessories" appCode="commonfault"/>
            </td>
        </tr>
        <%} %>


        <%}%>

        <%if (taskName.equals("HoldHumTask")) {%>

        <%if (operateType.equals("17")) {%>
        <input type="hidden" name="${sheetPageName}mainRejectCount" id="${sheetPageName}mainRejectCount"
               value="${sheetMain.mainRejectCount}"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value=""/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="17"/>
        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.linkUntreadReason"/>*</td>
            <td colspan="3">
                <textarea name="linkUntreadReason" id="linkUntreadReason" class="textarea max"
                          alt="allowBlank:false">${sheetLink.linkUntreadReason}</textarea>
            </td>
        </tr>

        <%} %>


        <%if (operateType.equals("18")) { %>

        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value=""/>
        <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18"/>
        <input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>

        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainFaultResponseLevel"/></td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}linkFaultResponseLevel" id="${sheetPageName}linkFaultResponseLevel"
                               initDicId="1010304" defaultValue="101030402"/>
            </td>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfGreatFault"/></td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}linkIfGreatFault"
                               id="${sheetPageName}linkIfGreatFault" initDicId="10301" defaultValue="1030102"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}holdStatisfied"
                               id="${sheetPageName}holdStatisfied" defaultValue="${sheetMain.holdStatisfied}"
                               initDicId="10303" defaultValue="1030301" styleClass="select" alt="allowBlank:false"/>
            </td>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainIfAffectOperation"/>*</td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}mainIfAffectOperation" id="${sheetPageName}mainIfAffectOperation"
                               initDicId="10301" defaultValue="1030102" alt="allowBlank:false"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/>*</td>
            <td colspan="3">
                <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult" class="textarea max"
                          alt="allowBlank:false">${sheetMain.endResult}</textarea>
            </td>
        </tr>
        <tr>
            <td colspan="4">
                <input type="button" title="knowledge" value="新增知识" onclick="popupKnowledge();">
                <input type="button" title="knowledge" value="入遗留问题库" onclick="popupLeaveKnowledge();">
            </td>
        </tr>

        <%} %>
        <%if (operateType.equals("61")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
        <!--
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  	
		-->
        <%} %>

        <%} %>


        <% if (taskName.equals("cc")) {%>

        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.remark"/>*
            </td>
            <td colspan="3">
                <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15"/>
                <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入500汉字'"
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
       