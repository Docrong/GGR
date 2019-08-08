<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="com.boco.eoms.sheet.base.webapp.action.IBaseSheet" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.resourceaffirm.task.impl.ResourceAffirmTaskImpl" %>
<%
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
    String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId"));
    String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId"));

    String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
    System.out.println("=====taskName======" + taskName + "operateType===" + operateType);
    String roleId = "";
    String roleName = "";
    String operateUserId = "";
    BaseLink bl = (BaseLink) request.getAttribute("preLink");
    IBaseSheet baseSheet = (IBaseSheet) ApplicationContextHolder.getInstance().getBean("ResourceAffirm");
    if (bl != null) {
        String prelinkid = com.boco.eoms.base.util.StaticMethod.nullObject2String(bl.getPreLinkId());
        System.out.println("prelinkid:" + prelinkid);
        if (!prelinkid.equals("")) {
            BaseLink base = baseSheet.getLinkService().getSingleLinkPO(prelinkid);
            operateUserId = base.getOperateUserId();
        }
    }
    if (request.getAttribute("task") != null) {
        ResourceAffirmTaskImpl task = (ResourceAffirmTaskImpl) request.getAttribute("task");
        System.out.println("task piid is =====" + task.getProcessId());

    }
%>
<script type="text/javascript">

    function openwin(flag) {
        var url;

        if (flag == "101050801") {
            if ($('${sheetPageName}linkNetType').value.indexOf('101050801') == -1) {
                $('${sheetPageName}linkNetType').value += '101050801' + ',';
            }
            $('${sheetPageName}phaseId').value = 'receiveNet';
            url = "${app}/sheet/circuitdispatch/circuitdispatch.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iResourceAffirmMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}@${task.processId}";
        } else if (flag == "101050802") {
            if ($('${sheetPageName}linkNetType').value.indexOf('101050802') == -1) {
                $('${sheetPageName}linkNetType').value += '101050802' + ',';
            }
            $('${sheetPageName}phaseId').value = 'receiveNet';

            url = "${app}/sheet/softchange/softchange.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iResourceAffirmMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}@${task.processId}";

        } else if (flag == "101050803") {
            if ($('${sheetPageName}linkNetType').value.indexOf('101050803') == -1) {
                $('${sheetPageName}linkNetType').value += '101050803' + ',';
            }
            $('${sheetPageName}phaseId').value = 'receiveNet';

            url = "${app}/sheet/netdata/netdata.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iResourceAffirmMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}@${task.processId}";
        } else if (flag == "101050804") {
            if ($('${sheetPageName}linkNetType').value.indexOf('101050804') == -1) {
                $('${sheetPageName}linkNetType').value += '101050804' + ',';
            }
            $('${sheetPageName}phaseId').value = 'receiveNet';

            url = "${app}/sheet/netchange/netchange.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iResourceAffirmMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}@${task.processId}";
        }
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
<%@ include file="/WEB-INF/pages/wfworksheet/resourceaffirm/baseinputlinkhtmlnew.jsp" %>
<br/>
<table class="formTable">
    <input type="hidden" id="tmpCompleteLimit" value=""
           alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'${eoms:a2u("处理时限不能晚于工单完成时限")}'"/>
    <input type="hidden" name="${sheetPageName}beanId" value="iResourceAffirmMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.resourceaffirm.model.ResourceAffirmMain"/>
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.resourceaffirm.model.ResourceAffirmLink"/>
    <c:if test="${taskName != 'HoldHumTask' }">
        <c:choose>
            <c:when test="${preLink.activeTemplateId == 'TaskCreateAuditHumTask' }">
                <input type="hidden" name="${sheetPageName}toOrgRoleId" value="<%=operateUserId %>"/>
            </c:when>
            <c:otherwise>
                <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateUserId}"/>
            </c:otherwise>
        </c:choose>
    </c:if>
    <%if (taskName.equals("TaskCreateAuditHumTask")) { %>
    <%if (operateType.equals("201")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="201"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TransmitMoreTask"/>
    <tr>
        <td class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.auditResult"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}auditResult" id="${sheetPageName}auditResult"
                      alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入审核结果，最大长度为1000个汉字！')}'">${sheetLink.auditResult}</textarea>
        </td>
    </tr>
    <%} else if (operateType.equals("202")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="202"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ByRejectHumTask"/>
    <tr>
        <td class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.auditResult"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}auditResult" id="${sheetPageName}auditResult"
                      alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入审核结果，最大长度为1000个汉字！')}'">${sheetLink.auditResult}</textarea>
        </td>
    </tr>
    <%}%>

    <%} else if (taskName.equals("TaskCompleteAuditHumTask")) { %>
    <%if (operateType.equals("208")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="203"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AffirmHumTask"/>

    <tr>
        <td class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.auditResult"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}auditResult" id="${sheetPageName}auditResult"
                      alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入审核结果，最大长度为1000个汉字！')}'">${sheetLink.auditResult}</textarea>
        </td>
    </tr>

    <%} else if (operateType.equals("209")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="204"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask"/>
    <tr>
        <td class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.auditResult"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}auditResult" id="${sheetPageName}auditResult"
                      alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入审核结果，最大长度为1000个汉字！')}'">${sheetLink.auditResult}</textarea>
        </td>
    </tr>
    <%}%>

    <%} else if (taskName.equals("ExcuteHumTask")) {%>
    <%
        if (operateType.equals("111")) {
            //确认受理
    %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="111"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="receiveNet"/>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="common.dispatchType"/>*
        </td>
        <td class="content" colspan=3>
            <input type="hidden" name="${sheetPageName}linkNetType" id="${sheetPageName}linkNetType" value=""/>
            <input type="button" class="btn" value="<eoms:id2nameDB id='101050801' beanId='ItawSystemDictTypeDao'/>"
                   onclick="javascript:openwin('101050801')">
            <input type="button" class="btn" value="<eoms:id2nameDB id='101050802' beanId='ItawSystemDictTypeDao'/>"
                   onclick="javascript:openwin('101050802')">
            <input type="button" class="btn" value="<eoms:id2nameDB id='101050803' beanId='ItawSystemDictTypeDao'/>"
                   onclick="javascript:openwin('101050803')">
            <input type="button" class="btn" value="<eoms:id2nameDB id='101050804' beanId='ItawSystemDictTypeDao'/>"
                   onclick="javascript:openwin('101050804')">

        </td>
    </tr>
    <%
    } else if (operateType.equals("61")) {
        //确认受理
    %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask"/>
    <%--  <tr>
      <td class="label">
      <bean:message bundle="sheet" key="linkForm.remark" />*
      </td>
      <td colspan="3">
       <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
        alt="width:'500px'">${sheetLink.remark}</textarea>
      </td>
   </tr>
   --%>
    <%} else if (operateType.equals("205")) { //回复%>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="205"/>
    <c:if test="${mainparentCorrKey==sheetMain.correlationKey }">
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="receive"/>
    </c:if>
    <c:if test="${mainparentCorrKey!=sheetMain.correlationKey }">
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AffirmHumTask"/>

    </c:if>
    <tr>
        <td class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.ndeptContact"/>*</td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}ndeptContact" id="${sheetPageName}ndeptContact"
                   value="${sheetLink.ndeptContact}"
                   alt="width:500,allowBlank:false,maxLength:25,vtext:'${eoms:a2u('请输入网络部门联系人，最大长度为12个汉字！')}'"/>
        </td>
        <td class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.ndeptContactPhone"/>*</td>
        <td class="content"><input type="text" class="text" name="${sheetPageName}ndeptContactPhone"
                                   id="${sheetPageName}ndeptContactPhone" value="${sheetLink.ndeptContactPhone}"
                                   alt="width:500,allowBlank:false,maxLength:50,vtext:'${eoms:a2u('请输入网络部门联系人电话，最大长度为50个字符！')}'"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.netResCapacity"/>&nbsp;&nbsp;*</td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}netResCapacity" id="${sheetPageName}netResCapacity"
                   value="${sheetLink.netResCapacity}"
                   alt="width:500,allowBlank:false,maxLength:500,vtext:'${eoms:a2u('请输入网络资源能力确认，最大长度为250个汉字！')}'"/>
        </td>
        <td class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.clientPgmCapacity"/>&nbsp;&nbsp;*
        </td>
        <td class="content"><input type="text" class="text" name="${sheetPageName}clientPgmCapacity"
                                   id="${sheetPageName}clientPgmCapacity" value="${sheetLink.clientPgmCapacity}"
                                   alt="width:500,allowBlank:false,maxLength:500,vtext:'${eoms:a2u('请输入客户端工程能力确认，最大长度为250个汉字！')}'"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.investEvaluate"/>*</td>
        <td class="content"><input type="text" class="text" name="${sheetPageName}investEvaluate"
                                   id="${sheetPageName}investEvaluate" value="${sheetLink.investEvaluate}"
                                   alt="width:500,allowBlank:false,maxLength:254,vtext:'${eoms:a2u('请输入预计投资，最大长度为127个汉字！')}'"/>
        </td>
        <td class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.expectFinishdays"/>&nbsp;&nbsp;*
        </td>
        <td class="content"><input type="text" class="text" name="${sheetPageName}expectFinishdays"
                                   id="${sheetPageName}expectFinishdays" value="${sheetLink.expectFinishdays}"
                                   alt="width:500,allowBlank:false,maxLength:16,vtext:'${eoms:a2u('请输入预计完成天数，最大长度为8个汉字！')}'"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.isOccupied"/>*</td>
        <td colspan='3'>
            <eoms:comboBox name="${sheetPageName}isOccupied" id="${sheetPageName}isOccupied"
                           initDicId="1011017" alt="allowBlank:true" defaultValue="${sheetLink.isOccupied}"
                           alt="allowBlank:false" styleClass="select-class"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="sheet" key="linkForm.nodeAccessories"/></td>
        <td colspan="3">
            <eoms:attachment name="sheetLink" property="nodeAccessories"
                             scope="request" idField="${sheetPageName}nodeAccessories" appCode="resourceAffirm"/>
        </td>
    </tr>
    <%} else if (operateType.equals("206")) { //回复送审%>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="206"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TaskCompleteAuditHumTask"/>
    <tr>
        <td class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.ndeptContact"/>*</td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}ndeptContact" id="${sheetPageName}ndeptContact"
                   value="${sheetLink.ndeptContact}"
                   alt="width:500,allowBlank:false,maxLength:25,vtext:'${eoms:a2u('请输入网络部门联系人，最大长度为12个汉字！')}'"/>
        <td class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.ndeptContactPhone"/>*</td>
        <td class="content"><input type="text" class="text" name="${sheetPageName}ndeptContactPhone"
                                   id="${sheetPageName}ndeptContactPhone" value="${sheetLink.ndeptContactPhone}"
                                   alt="width:500,allowBlank:false,maxLength:50,vtext:'${eoms:a2u('请输入网络部门联系人电话，最大长度为50个字符！')}'"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.netResCapacity"/>&nbsp;&nbsp;*</td>
        <td class="content"><input type="text" class="text" name="${sheetPageName}netResCapacity"
                                   id="${sheetPageName}netResCapacity" value="${sheetLink.netResCapacity}"
                                   alt="width:500,allowBlank:false,maxLength:500,vtext:'${eoms:a2u('请输入网络资源能力确认，最大长度为250个汉字！')}'"/>
        </td>
        <td class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.clientPgmCapacity"/>&nbsp;&nbsp;*
        </td>
        <td class="content"><input type="text" class="text" name="${sheetPageName}clientPgmCapacity"
                                   id="${sheetPageName}clientPgmCapacity" value="${sheetLink.clientPgmCapacity}"
                                   alt="width:500,allowBlank:false,maxLength:500,vtext:'${eoms:a2u('请输入客户端工程能力确认，最大长度为250个汉字！')}'"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.expectFinishdays"/>&nbsp;&nbsp;*
        </td>
        <td class="content"><input type="text" class="text" name="${sheetPageName}expectFinishdays"
                                   id="${sheetPageName}expectFinishdays" value="${sheetLink.expectFinishdays}"
                                   alt="width:500,allowBlank:false,maxLength:16,vtext:'${eoms:a2u('请输入预计完成天数，最大长度为8个汉字！')}'"/>
        </td>

        <td class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.investEvaluate"/>*</td>
        <td class="content"><input type="text" class="text" name="${sheetPageName}investEvaluate"
                                   id="${sheetPageName}investEvaluate" value="${sheetLink.investEvaluate}"
                                   alt="width:500,allowBlank:false,maxLength:254,vtext:'${eoms:a2u('请输入预计投资，最大长度为127个汉字！')}'"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.isOccupied"/>*</td>
        <td colspan='3'>
            <eoms:comboBox name="${sheetPageName}isOccupied" id="${sheetPageName}isOccupied"
                           initDicId="1011017" alt="allowBlank:true" defaultValue="${sheetLink.isOccupied}"
                           alt="allowBlank:false" styleClass="select-class"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="linkForm.nodeAccessories"/></td>
        <td colspan="3">
            <eoms:attachment name="sheetLink" property="nodeAccessories"
                             scope="request" idField="${sheetPageName}nodeAccessories" appCode="resourceAffirm"/>
        </td>
    </tr>
    <%} else if (operateType.equals("10")) { //转派%>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="10"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask"/>
    <tr>
        <td class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.transmitReason"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}transferReason" id="${sheetPageName}transferReason"
                      alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入转派理由，最大长度为1000个汉字！')}'">${sheetLink.transferReason }</textarea>
        </td>
    </tr>
    <% } else if (operateType.equals("8")) { //移交%>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="8"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ShiftScopeTask"/>
    <tr>
        <td class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.yijiao"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}transferReason" id="${sheetPageName}transferReason"
                      alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入移交理由，最大长度为1000个汉字！')}'">${sheetLink.transferReason }</textarea>
        </td>
    </tr>
    <%}%>
    <%} else if (taskName.equals("AffirmHumTask")) {%>
    <%if (operateType.equals("7")) { //处理回复不通过%>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="7"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask"/>
    <tr>
        <td class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.dealResult"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}dealResult" id="${sheetPageName}dealResult"
                      alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入处理结果，最大长度为1000个汉字！')}'">${sheetLink.dealResult }</textarea>
        </td>
    </tr>

    <%} else if (operateType.equals("6")) { //处理回复通过%>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="6"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="receive"/>
    <tr>
        <td class="label"><bean:message bundle="resourceaffirm" key="resourceaffirm.dealResult"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}dealResult" id="${sheetPageName}dealResult"
                      alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入处理结果，最大长度为1000个汉字！')}'">${sheetLink.dealResult }</textarea>
        </td>
    </tr>

    <%}%>
    <%} else if (taskName.equals("DraftHumTask")) {%>

    <%if (operateType.equals("22")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="22"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TaskCreateAuditHumTask"/>


    <%}%>

    <%} else if (taskName.equals("HoldHumTask")) {%>
    <%if (operateType.equals("18")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverTask"/>
    <input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
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
        <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/>*</td>
        <td colspan="3">
            <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult" class="textarea max"
                      alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入归档意见，最大长度为1000个汉字！')}'">${sheetMain.endResult}</textarea>
        </td>
    </tr>
    <%}%>
    <%} else if (taskName.equals("cc")) {%>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15"/>

    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                             alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入备注，最大长度为1000个汉字！')}'"
                             alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%} %>
    <%if (taskName.equals("TaskCreateAuditHumTask") || taskName.equals("ExcuteHumTask") || taskName.equals("TaskCompleteAuditHumTask") || taskName.equals("AffirmHumTask")) {%>

    <%if (operateType.equals("61")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
    <%-- <tr>
       <td class="label">
        <bean:message bundle="sheet" key="linkForm.remark" />
        </td>
        <td colspan="3">
            <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
            alt="width:'500px',maxLength:1000,vtext:'${eoms:a2u('请最多输入1000字')}'">${sheetLink.remark}</textarea>
      </td>
    </tr>  	--%>

    <%
            }
        }
    %>
    <%if (operateType.equals("4")) { //驳回%>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4"/>
    <c:choose>
        <c:when test="${taskName=='TaskCreateAuditHumTask'}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ByRejectHumTask"/>
        </c:when>
        <c:when test="${taskName=='ExcuteHumTask'}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AffirmHumTask"/>
        </c:when>
        <c:when test="${taskName=='TaskCompleteAuditHumTask'}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask"/>
        </c:when>
    </c:choose>

    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
		          <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                            alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入备注信息，最大长度为1000个汉字！')}'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%} %>

</table>
<%if (taskName.equals("cc")) {%>
<fieldset id="link4">
    <eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'copyPerformer',childType:'user',limit:'none',text:'${eoms:a2u('抄送')}'}]"/>
</fieldset>
<%} %>
<%if (taskName.equals("TaskCreateAuditHumTask")) { %>
<% if (operateType.equals("201")) { //reject%>
<fieldset>
    <legend>
        <bean:message bundle="resourceaffirm" key="role.toOrgName"/>
        <span id="roleName">:<bean:message bundle="resourceaffirm" key="role.excute"/>
			 </span>
    </legend>

    <eoms:chooser id="test" category="[{id:'dealPerformer',childType:'user',limit:'none',text:'${eoms:a2u('派发')}'
			,allowBlank:false,vtext:'${eoms:a2u('请选择派发对象')}'}]" data="${sendUserAndRoles}"/>

</fieldset>
<% }
} %>

<%if (taskName.equals("ExcuteHumTask")) { %>
<% if (operateType.equals("8")) { //移交%>
<fieldset>
    <legend>
        <bean:message bundle="resourceaffirm" key="role.toOrgName"/>
        <span id="roleName">:<bean:message bundle="resourceaffirm" key="role.excute"/>
			 </span>
    </legend>

    <eoms:chooser id="test"
                  category="[{id:'dealPerformer',text:'${eoms:a2u('派发')}',childType:'user',allowBlank:false,vtext:'${eoms:a2u('请选择派发人')}',limit:'none'}]"
    />
</fieldset>
<% } %>
<% if (operateType.equals("10")) { //转派%>
<fieldset>
    <legend>
        <bean:message bundle="resourceaffirm" key="role.toOrgName"/>
        <span id="roleName">:<bean:message bundle="resourceaffirm" key="role.excute"/>
			 </span>
    </legend>

    <eoms:chooser id="test"
                  category="[{id:'dealPerformer',childType:'user',limit:'none',text:'${eoms:a2u('派发')}',allowBlank:false,vtext:'${eoms:a2u('请选择任务执行人')}'}]"/>

</fieldset>
<% } %>
<% if (operateType.equals("206")) { //回复送审%>
<fieldset>
    <legend>
        <bean:message bundle="resourceaffirm" key="role.toOrgName"/>
        <span id="roleName">:<bean:message bundle="resourceaffirm" key="role.TaskCompleteAudit"/> </span>
    </legend>
    <eoms:chooser id="test"
                  category="[{id:'dealPerformer',text:'${eoms:a2u('审核')}',childType:'user',limit:'1',allowBlank:false,vtext:'${eoms:a2u('请选择审核对象')}'},{id:'copyPerformer',childType:'user',limit:'none',text:'${eoms:a2u('抄送')}'}]"
    />

</fieldset>
<% } %>
<% if (operateType.equals("205")) { //回复%>
<fieldset>
    <legend>
        <bean:message bundle="resourceaffirm" key="role.toOrgName"/>
        <span id="roleName">:${eoms:a2u('上一级执行者')}
			 </span>
    </legend>

</fieldset>
<% } %>

<%} %>
