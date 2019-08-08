<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="com.boco.eoms.sheet.base.DateUtil" %>
<%@page import="com.boco.eoms.sheet.urgentfault.model.UrgentFaultMain" %>

<%
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
    String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId"));
    String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId"));
    System.out.println("=====taskName======" + taskName);
    String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
    System.out.println("=====operateType======" + operateType);
    //String adate=com.boco.eoms.sheet.base.DateUtil.getStrCurrentDateAddDay(2);
    UrgentFaultMain urgentFaultMain = (UrgentFaultMain) request.getAttribute("sheetMain");
    java.util.Date mainFaultGenerantTime = urgentFaultMain.getMainFaultGenerantTime();
    mainFaultGenerantTime.setTime(mainFaultGenerantTime.getTime() + 86400 * 1000 * 2);
    String mainReportLimit = "";
    if (mainFaultGenerantTime != null) {
        mainReportLimit = mainFaultGenerantTime.toString().substring(0, 19);
    }
%>
<script type="text/javascript">
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
<%@ include file="/WEB-INF/pages/wfworksheet/common/baseinputlinkhtmlnew.jsp" %>
<input type="hidden" id="tmpCompleteLimit" value=""
       alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'处理时限不能晚于工单完成时限'"/>
<input type="hidden" name="${sheetPageName}processTemplateName" id="${sheetPageName}processTemplateName"
       value="UrgentFaultMainFlowProcess"/>
<input type="hidden" name="${sheetPageName}operateName" id="${sheetPageName}operateName" value="nonFlowOperate"/>
<input type="hidden" name="${sheetPageName}beanId" value="iUrgentFaultMainManager"/>
<input type="hidden" name="${sheetPageName}mainClassName"
       value="com.boco.eoms.sheet.urgentfault.model.UrgentFaultMain"/> <!--main表Model对象类名-->
<input type="hidden" name="${sheetPageName}linkClassName"
       value="com.boco.eoms.sheet.urgentfault.model.UrgentFaultLink"/> <!--link表Model对象类名-->
<!--<input type="hidden" name="${sheetPageName}roleId" id="${sheetPageName}roleId" value="106">-->
<input type="hidden" name="${sheetPageName}toDeptId" value="${sheetMain.toDeptId}"/>
<input type="hidden" name="${sheetPageName}mainNetSortOne" value="${sheetMain.mainNetSortOne}"/>
<input type="hidden" name="${sheetPageName}mainNetSortTwo" value="${sheetMain.mainNetSortTwo}"/>
<input type="hidden" name="${sheetPageName}mainNetSortThree" value="${sheetMain.mainNetSortThree}"/>
<c:if test="${taskName != 'HoldHumTask' }">
    <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
</c:if>
<br>

<table class="formTable">
    <%
        if (taskName.equals("MiddleReportAffirmHumTask") ||
                taskName.equals("BackReportAffirmHumTask") ||
                taskName.equals("SolveFaultAffirmHumTask")) {
    %>

    <%if (operateType.equals("913") || operateType.equals("923") || operateType.equals("933")) { %>

    <%if (taskName.equals("MiddleReportAffirmHumTask")) {%>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="913"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SolveFaultHumTask"/>
    <input type="hidden" name="${sheetPageName}mainReportLimit" id="${sheetPageName}mainReportLimit"
           value="<%=mainReportLimit%>"/>
    <%} %>

    <%if (taskName.equals("BackReportAffirmHumTask")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="923"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ReportHumTask"/>
    <%} %>

    <%if (taskName.equals("SolveFaultAffirmHumTask")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="933"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ReportHumTask"/>
    <%} %>


    <tr>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainIfGreatFault"/>*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}linkIfGreatFault" id="${sheetPageName}linkIfGreatFault"
                           initDicId="10301" defaultValue="${sheetLink.linkIfGreatFault}" alt="allowBlank:true"/>
        </td>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainGreatFaultId"/></td>
        <td class="content">

            <input type="text" class="text" name="${sheetPageName}mainGreatFaultId"
                   id="${sheetPageName}mainGreatFaultId" value="${sheetMain.mainGreatFaultId}" alt="allowBlank:true"/>
            <!--  <eoms:comboBox name="${sheetPageName}mainGreatFaultId" id="${sheetPageName}mainGreatFaultId"
			  	  initDicId="1011609" defaultValue="${sheetMain.mainGreatFaultId}" />-->
        </td>
    </tr>

    <tr>
        <%if (taskName.equals("SolveFaultAffirmHumTask") || taskName.equals("subTask4")) {%>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.linkIfAffirm3"/>*</td>
        <%} else if (taskName.equals("BackReportAffirmHumTask") || taskName.equals("subTask2")) { %>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.linkIfAffirm1"/>*</td>
        <%} else if (taskName.equals("MiddleReportAffirmHumTask") || taskName.equals("subTask1")) { %>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.linkIfAffirm2"/>*</td>
        <%} %>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkIfAffirm" id="${sheetPageName}linkIfAffirm"
                           initDicId="10301" defaultValue="${sheetLink.linkIfAffirm}" alt="allowBlank:false"/>
        </td>
    </tr>


    <%if (taskName.equals("SolveFaultAffirmHumTask") || taskName.equals("subTask4")) {%>
    <tr>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.linkFaultAvoidReport"/></td>
        <td colspan="3">
            <textarea name="linkFaultAvoidReport" id="linkFaultAvoidReport"
                      class="textarea max">${sheetLink.linkFaultAvoidReport}</textarea>
        </td>
    </tr>
    <%} else { %>
    <tr>
        <td class="label">
            <%if (taskName.equals("MiddleReportAffirmHumTask") || taskName.equals("subTask1")) {%>
            <bean:message bundle="urgentfault" key="urgentfault.linkFaultReportDesc2"/>
            <%} %>
            <%if (taskName.equals("BackReportAffirmHumTask") || taskName.equals("subTask2")) {%>
            <bean:message bundle="urgentfault" key="urgentfault.linkFaultReportDesc1"/>
            <%} %>
        </td>
        <td colspan="3">
            <textarea name="linkFaultReportDesc" id="linkFaultReportDesc"
                      class="textarea max">${sheetLink.linkFaultReportDesc}</textarea>
        </td>
    </tr>
    <%} %>


    <%} %>

    <%if (!operateType.equals("61") && !operateType.equals("4") && !operateType.equals("8")) { %>
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
                             scope="request" idField="${sheetPageName}nodeAccessories" appCode="urgentfault"/>
        </td>
    </tr>
    <%} %>

    <%if (operateType.equals("61")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="allowBlank:false,width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%} %>


    <%}%>


    <%if (operateType.equals("4")) { %>

    <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer"
           value="${fOperateroleid}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader"
           value="${ftaskOwner}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType"
           value="${fOperateroleidType}"/>

    <c:choose>
        <c:when test="${empty fPreTaskName}">
            <%if (taskName.equals("MiddleReportAffirmHumTask")) { %>
            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="914"/>
            <%} %>
            <%if (taskName.equals("BackReportAffirmHumTask")) { %>
            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="924"/>
            <%} %>
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value=BackExcuteTask/>
        </c:when>
        <c:when test="${'DraftHumTask' == fPreTaskName}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="BackExcuteTask"/>
        </c:when>
        <c:when test="${'BackHumTask' == fPreTaskName}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="BackExcuteTask"/>
            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4"/>
        </c:when>
        <c:when test="${'SolveFaultHumTask' == fPreTaskName}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SolveFaultHumTask"/>
            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="934"/>
        </c:when>
        <c:otherwise>
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}"/>
            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4"/>
        </c:otherwise>
    </c:choose>

    <tr>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.linkRejectReason"/>*</td>
        <td colspan="3">
            <textarea name="linkFaultReportDesc" id="linkFaultReportDesc"
                      class="textarea max">${sheetLink.linkFaultReportDesc}</textarea>
        </td>
    </tr>

    <%} %>

    <%if (operateType.equals("8")) { %>
    <!-- 组内 移交处理 -->
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="8"/>
    <%if (taskName.equals("MiddleReportAffirmHumTask")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="MiddleReportAffirmHumTask"/>
    <%} %>
    <%if (taskName.equals("BackReportAffirmHumTask")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="BackReportAffirmHumTask"/>
    <%} %>
    <%if (taskName.equals("SolveFaultHumTask")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SolveFaultHumTask"/>
    <%} %>
    <%if (taskName.equals("SolveFaultAffirmHumTask")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SolveFaultAffirmHumTask"/>
    <%} %>
    <%if (taskName.equals("ReportHumTask")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ReportHumTask"/>
    <%} %>
    <%if (taskName.equals("SumUpHumTask")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SumUpHumTask"/>
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


    <!-- 待销障，销障上报 -->
    <%if (taskName.equals("SolveFaultHumTask") || taskName.equals("subTask3")) {%>

    <%if (operateType.equals("93")) {%>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="93"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SolveFaultAffirmHumTask"/>

    <tr>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainIfGreatFault"/></td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkIfGreatFault" id="${sheetPageName}linkIfGreatFault"
                           initDicId="10301" defaultValue="${sheetLink.linkIfGreatFault}"/>
        </td>

    </tr>

    <tr>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.linkFaultReason"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}linkFaultReason" id="${sheetPageName}linkFaultReason"
                           initDicId="1010303" sub="" defaultValue="${sheetLink.linkFaultReason}"
                           alt="allowBlank:true"/>
        </td>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.linkFaultSubReason"/>*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}linkFaultSubReason"
                   id="${sheetPageName}linkFaultSubReason" value="${sheetLink.linkFaultSubReason}"
                   alt="allowBlank:true"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainFaultAvoidTime"/></td>
        <td>
            <input type="text" class="text" name="${sheetPageName}linkFaultAvoidTime" readonly="readonly"
                   id="${sheetPageName}linkFaultAvoidTime"
                   onclick="popUpCalendar(this, this,null,null,null,true,-1)"
                   value="${eoms:date2String(sheetLink.linkFaultAvoidTime)}"/>
        </td>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainAffectTimeLength"/></td>
        <td>
            <input type="text" class="text" name="${sheetPageName}linkAffectTimeLength"
                   id="${sheetPageName}linkAffectTimeLength" value="${sheetLink.linkAffectTimeLength}"
                   alt="allowBlank:true"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainAffectOperationDesc"/></td>
        <td colspan="3">
            <textarea name="linkAffectOperationDesc" id="linkAffectOperationDesc"
                      class="textarea max">${sheetLink.linkAffectOperationDesc}</textarea>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.linkFaultAvoidReport"/></td>
        <td colspan="3">
            <textarea name="linkFaultAvoidReport" id="linkFaultAvoidReport"
                      class="textarea max">${sheetLink.linkFaultAvoidReport}</textarea>
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
                             scope="request" idField="${sheetPageName}nodeAccessories" appCode="urgentfault"/>
        </td>
    </tr>

    <%} %>

    <%if (operateType.equals("61")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="allowBlank:false,width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%} %>


    <%}%>


    <%if (taskName.equals("ReportHumTask") || taskName.equals("subTask5")) {%>
    <%if (operateType.equals("94")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="94"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SumUpHumTask"/>
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
                             scope="request" idField="${sheetPageName}nodeAccessories" appCode="urgentfault"/>
        </td>
    </tr>
    <%} %>

    <%if (operateType.equals("61")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="allowBlank:false,width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%} %>

    <%}%>
    <%if (taskName.equals("cc")) {%>

    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>
        </td>
        <td colspan="3">
            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15"/>
            <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                      alt="allowBlank:false,width:500" alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%} %>


    <%if (taskName.equals("SumUpHumTask") || taskName.equals("subTask6")) {%>

    <%if (operateType.equals("95")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="95"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldHumTask"/>
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
                             scope="request" idField="${sheetPageName}nodeAccessories" appCode="urgentfault"/>
        </td>
    </tr>
    <%} %>

    <%if (operateType.equals("61")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="allowBlank:false,width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%} %>

    <%}%>
    <%if (taskName.equals("HoldHumTask")) {%>
    <%if (operateType.equals("18")) { %>

    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value=""/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18"/>
    <input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}holdStatisfied"
                           id="${sheetPageName}holdStatisfied"
                           defaultValue="${sheetMain.holdStatisfied != 0 ? sheetMain.holdStatisfied : 1030301}"
                           initDicId="10303" styleClass="select" alt="allowBlank:false"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/></td>
        <td colspan="3">
            <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult"
                      class="textarea max">${sheetMain.endResult}</textarea>
        </td>
    </tr>
    <%} %>
    <%} %>
</table>
<br>

<% if (taskName.equals("MiddleReportAffirmHumTask")) {%>
<%if (operateType.equals("913")) {%>
<fieldset id="link4">
    <legend>
        <legend>
            <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
            <bean:message bundle="urgentfault" key="role.faultAdmin"/>
        </legend>
    </legend>
</fieldset>
<%
        }
    }
%>

<% if (taskName.equals("BackReportAffirmHumTask")) {%>
<%if (operateType.equals("923")) {%>
<fieldset id="link4">
    <legend>
        <legend>
            <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
            <bean:message bundle="urgentfault" key="role.faultAdmin"/>
        </legend>
    </legend>
</fieldset>
<%
        }
    }
%>

<% if (taskName.equals("SolveFaultHumTask")) {%>
<%if (operateType.equals("93")) {%>
<fieldset id="link4">
    <legend>
        <legend>
            <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
            <bean:message bundle="urgentfault" key="role.urgentFaltAdmin"/>
        </legend>
    </legend>
</fieldset>
<%
        }
    }
%>

<% if (taskName.equals("SolveFaultAffirmHumTask")) {%>
<%if (operateType.equals("933")) {%>
<fieldset id="link4">
    <legend>
        <legend>
            <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
            <bean:message bundle="urgentfault" key="role.faultAdmin"/>
        </legend>
    </legend>
</fieldset>
<%
        }
    }
%>

<% if (taskName.equals("ReportHumTask")) {%>
<%if (operateType.equals("94")) {%>
<fieldset id="link4">
    <legend>
        <legend>
            <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
            <bean:message bundle="urgentfault" key="role.urgentFaltAdmin"/>
        </legend>
    </legend>
</fieldset>
<%
        }
    }
%>
<% if (taskName.equals("SumUpHumTask")) {%>
<%if (operateType.equals("95")) {%>
<fieldset id="link4">
    <legend>
        <legend>
            <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
            <bean:message bundle="urgentfault" key="role.urgentFaltAdmin"/>
        </legend>
    </legend>
</fieldset>
<%
        }
    }
%>


<%if (operateType.equals("8")) { %>

<%
    if (taskName.equals("MiddleReportAffirmHumTask") || taskName.equals("BackReportAffirmHumTask")
            || taskName.equals("SolveFaultAffirmHumTask") || taskName.equals("SumUpHumTask")) {
%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="urgentfault" key="role.faultAdmin"/>
    </legend>
    <eoms:chooser id="test" type="role" roleId="196" flowId="53" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'dealPerformer',childType:'subrole',text:'派发',allowBlank:false,vtext:'请选择派发人'}]"/>
</fieldset>
<%} %>

<%if (taskName.equals("SolveFaultHumTask") || taskName.equals("ReportHumTask")) {%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="urgentfault" key="role.faultAdmin"/>
    </legend>
    <eoms:chooser id="test" type="role" roleId="195" flowId="53" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'dealPerformer',childType:'subrole',text:'派发',allowBlank:false,vtext:'请选择派发人'}]"/>
</fieldset>
<%} %>

<%} %>

<%if (taskName.equals("cc")) {%>

<fieldset id="link4">
    <eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'copyPerformer',childType:'user',limit:'none',text:'抄送'}]"/></fieldset>
<%} %>
