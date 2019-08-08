<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="com.boco.eoms.sheet.base.webapp.action.IBaseSheet" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.businesschange.task.impl.BusinessChangeTaskImpl" %>

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
    IBaseSheet baseSheet = (IBaseSheet) ApplicationContextHolder.getInstance().getBean("BusinessChange");
    if (bl != null) {
        String prelinkid = com.boco.eoms.base.util.StaticMethod.nullObject2String(bl.getPreLinkId());
        if (!prelinkid.equals("")) {
            BaseLink base = baseSheet.getLinkService().getSingleLinkPO(prelinkid);
            operateUserId = base.getOperateUserId();
        }
    }
    if (request.getAttribute("task") != null) {
        BusinessChangeTaskImpl task = (BusinessChangeTaskImpl) request.getAttribute("task");
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
            url = "${app}/sheet/circuitdispatch/circuitdispatch.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iBusinessChangeMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}@${task.processId}";
        } else if (flag == "101050802") {
            if ($('${sheetPageName}linkNetType').value.indexOf('101050802') == -1) {
                $('${sheetPageName}linkNetType').value += '101050802' + ',';
            }
            $('${sheetPageName}phaseId').value = 'receiveNet';

            url = "${app}/sheet/softchange/softchange.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iBusinessChangeMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}@${task.processId}";

        } else if (flag == "101050803") {
            if ($('${sheetPageName}linkNetType').value.indexOf('101050803') == -1) {
                $('${sheetPageName}linkNetType').value += '101050803' + ',';
            }
            $('${sheetPageName}phaseId').value = 'receiveNet';

            url = "${app}/sheet/netdata/netdata.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iBusinessChangeMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}@${task.processId}";
        } else if (flag == "101050804") {
            if ($('${sheetPageName}linkNetType').value.indexOf('101050804') == -1) {
                $('${sheetPageName}linkNetType').value += '101050804' + ',';
            }
            $('${sheetPageName}phaseId').value = 'receiveNet';

            url = "${app}/sheet/netchange/netchange.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iBusinessChangeMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}@${task.processId}";
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

<%@ include file="/WEB-INF/pages/wfworksheet/businesschange/baseinputlinkhtmlnew.jsp" %>
<br/>
<table class="formTable">
    <input type="hidden" id="tmpCompleteLimit" value=""
           alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'${eoms:a2u("处理时限不能晚于工单完成时限")}'"/>
    <input type="hidden" name="${sheetPageName}beanId" value="iBusinessChangeMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.businesschange.model.BusinessChangeMain"/>
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.businesschange.model.BusinessChangeLink"/>
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
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <%if (operateType.equals("201")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TransmitMoreTask"/>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.auditResult"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}auditResult" id="${sheetPageName}auditResult"
                      alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入审核结果，最大长度为1000个汉字！')}'">${sheetLink.auditResult}</textarea>
        </td>
    </tr>
    <%} else if (operateType.equals("202")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ByRejectHumTask"/>

    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.auditResult"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}auditResult" id="${sheetPageName}auditResult"
                      alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入审核结果，最大长度为1000个汉字！')}'">${sheetLink.auditResult}</textarea>
        </td>
    </tr>
    <%}%>
    <%} else if (taskName.equals("TaskCompleteAuditHumTask")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>"/>
    <%if (operateType.equals("208")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AffirmHumTask"/>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.auditResult"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}auditResult" id="${sheetPageName}auditResult"
                      alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入审核结果，最大长度为1000个汉字！')}'">${sheetLink.auditResult}</textarea>
        </td>
    </tr>


    <%} else if (operateType.equals("209")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask"/>

    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.auditResult"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}auditResult" id="${sheetPageName}auditResult"
                      alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入审核结果，最大长度为1000个汉字！')}'">${sheetLink.auditResult}</textarea>
        </td>
    </tr>
    <%}%>
    <%} else if (taskName.equals("ExcuteHumTask")) {%>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>"/>
    <%if (operateType.equals("111")) { //启动变更配置工单    %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="111"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="receiveNet"/>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="common.dispatchType"/>*
        </td>
        <td class="content max" colspan=3>
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
    <%} else if (operateType.equals("205")) { //执行回复%>
    <c:if test="${mainparentCorrKey==sheetMain.correlationKey }">
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="receive"/>
    </c:if>
    <c:if test="${mainparentCorrKey!=sheetMain.correlationKey }">
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AffirmHumTask"/>

    </c:if>

    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.ndeptContact"/>*</td>
        <td class="content"><input type="text" class="text" name="${sheetPageName}ndeptContact"
                                   value="${sheetLink.ndeptContact}" id="${sheetPageName}ndeptContact"
                                   alt="allowBlank:false,maxLength:25,vtext:'${eoms:a2u('请输入网络部门联系人，最大长度为12个汉字！')}'"/>
        </td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.ndeptContactPhone"/>*</td>
        <td class="content"><input type="text" class="text" name="${sheetPageName}ndeptContactPhone"
                                   value="${sheetLink.ndeptContactPhone}" id="${sheetPageName}ndeptContactPhone"
                                   alt="allowBlank:false,maxLength:50,vtext:'${eoms:a2u('请输入网络部门联系人电话，最大长度为50个字符！')}'"/>
        </td>
    </tr>
    <!-- GPRS -->
    <logic:equal value="101100101" property="businesstype1" name="sheetMain">
        <tr>
            <td class="label"><bean:message bundle="businesschange" key="businesschange.apnID"/>*</td>
            <td class="content" colspan='3'><input type="text" class="text" name="${sheetPageName}apnID"
                                                   id="${sheetPageName}apnID" value="${sheetLink.apnID}"
                                                   alt="allowBlank:false,maxLength:32,vtext:'${eoms:a2u('请输入apnID，最大长度为16个汉字！')}'"/>
            </td>
        </tr>
    </logic:equal>

    <!-- MMS -->
    <logic:equal value="101100102" property="businesstype1" name="sheetMain">
        <tr>

        </tr>
    </logic:equal>
    <!-- SMS -->
    <logic:equal value="101100103" property="businesstype1" name="sheetMain">
        <tr>

        </tr>
    </logic:equal>
    <!-- chuanshu -->
    <logic:equal value="101100104" property="businesstype1" name="sheetMain">
        <tr>

            <td class="label"><bean:message bundle="businesschange" key="businesschange.circuitCode"/>*</td>
            <td class="content" colspan='3'><input type="text" class="text" name="${sheetPageName}circuitCode"
                                                   id="${sheetPageName}circuitCode" value="${sheetLink.circuitCode}"
                                                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入传输专线电路代号，最大长度为100个汉字！')}'"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="businesschange" key="businesschange.testReport"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}testReport" id="${sheetPageName}testReport"
                          alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入测试报告，最大长度为1000个汉字！')}'">${sheetLink.testReport }</textarea>
            </td>
        </tr>
    </logic:equal>
    <tr>

        <td class="label"><bean:message bundle="businesschange" key="businesschange.dealResult"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}dealResult" id="${sheetPageName}dealResult"
                           initDicId="1011020" alt="allowBlank:false" defaultValue="${sheetLink.dealResult}"
                           styleClass="select-class"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.dealDesc"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}dealDesc" id="${sheetPageName}dealDesc"
                      alt="width:500,allowBlank:false,maxLength:1000,vtext:'${eoms:a2u('请输入处理说明，最大长度为500个汉字！')}'">${sheetLink.dealDesc }</textarea>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="linkForm.nodeAccessories"/></td>
        <td colspan="3">
            <eoms:attachment name="sheetLink" property="nodeAccessories"
                             scope="request" idField="${sheetPageName}nodeAccessories" appCode="businesschangesheet"/>
        </td>
    </tr>
    <%} else if (operateType.equals("206")) { //回复送审%>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="206"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TaskCompleteAuditHumTask"/>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.ndeptContact"/>*</td>
        <td class="content"><input type="text" class="text" name="${sheetPageName}ndeptContact"
                                   value="${sheetLink.ndeptContact}" id="${sheetPageName}ndeptContact"
                                   alt="allowBlank:false,maxLength:25,vtext:'${eoms:a2u('请输入网络部门联系人，最大长度为12个汉字！')}'"/>
        </td>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.ndeptContactPhone"/>*</td>
        <td class="content"><input type="text" class="text" name="${sheetPageName}ndeptContactPhone"
                                   id="${sheetPageName}ndeptContactPhone" value="${sheetLink.ndeptContactPhone}"
                                   alt="allowBlank:false,maxLength:50,vtext:'${eoms:a2u('请输入网络部门联系人电话，最大长度为50个字符！')}'"/>
        </td>
    </tr>
    <!-- GPRS -->
    <logic:equal value="101100101" property="businesstype1" name="sheetMain">
        <tr>
            <td class="label"><bean:message bundle="businesschange" key="businesschange.apnID"/>*</td>
            <td class="content" colspan='3'><input type="text" class="text" name="${sheetPageName}apnID"
                                                   id="${sheetPageName}apnID" value="${sheetLink.apnID}"
                                                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入apnID，最大长度为100个汉字！')}'"/>
            </td>
        </tr>
    </logic:equal>
    <!-- MMS -->
    <logic:equal value="101100102" property="businesstype1" name="sheetMain">
        <tr>

        </tr>
    </logic:equal>
    <!-- SMS -->
    <logic:equal value="101100103" property="businesstype1" name="sheetMain">
        <tr>

        </tr>
    </logic:equal>
    <!-- chuanshu -->
    <logic:equal value="101100104" property="businesstype1" name="sheetMain">


        <tr>

            <td class="label"><bean:message bundle="businesschange" key="businesschange.circuitCode"/>*</td>
            <td class="content" colspan='3'><input type="text" class="text" name="${sheetPageName}circuitCode"
                                                   id="${sheetPageName}circuitCode" value="${sheetLink.circuitCode}"
                                                   alt="allowBlank:false,maxLength:200,vtext:'${eoms:a2u('请输入传输专线电路代号，最大长度为100个汉字！')}'"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="businesschange" key="businesschange.testReport"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}testReport" id="${sheetPageName}testReport"
                          alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入测试报告，最大长度为1000个汉字！')}'">${sheetLink.testReport }</textarea>
            </td>
        </tr>

    </logic:equal>
    <tr>

        <td class="label"><bean:message bundle="businesschange" key="businesschange.dealResult"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}dealResult" id="${sheetPageName}dealResult"
                           initDicId="1011020" alt="allowBlank:false" defaultValue="${sheetLink.dealResult }"
                           styleClass="select-class"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.dealDesc"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}dealDesc" id="${sheetPageName}dealDesc"
                      alt="width:500,allowBlank:false,maxLength:1000,vtext:'${eoms:a2u('请输入处理结果，最大长度为500个汉字！')}'">${sheetLink.dealDesc }</textarea>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="linkForm.nodeAccessories"/></td>
        <td colspan="3">
            <eoms:attachment name="sheetLink" property="nodeAccessories"
                             scope="request" idField="${sheetPageName}nodeAccessories" appCode="businesschangesheet"/>
        </td>
    </tr>
    <%} else if (operateType.equals("10")) { //分派%>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask"/>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="linkForm.remark"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}transferReason" id="${sheetPageName}transferReason"
                      alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入意见，最大长度为1000个汉字！')}'">${sheetLink.transferReason }</textarea>
        </td>
    </tr>
    <% } else if (operateType.equals("8")) {//移交 %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ShiftScopeTask"/>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="linkForm.transmitReason"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}transferReason" id="${sheetPageName}transferReason"
                      alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入移交理由，最大长度为1000个汉字！')}'">${sheetLink.transferReason }</textarea>
        </td>
    </tr>
    <%}%>
    <%} else if (taskName.equals("AffirmHumTask")) {%>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>"/>
    <%if (operateType.equals("212")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask"/>
    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.dealResult"/></td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}dealResult" id="${sheetPageName}dealResult"
                      alt="width:500,allowBlank:true,maxLength:2000,vtext:'${eoms:a2u('请输入处理结果，最大长度为1000个汉字！')}'">${sheetLink.dealResult }</textarea>
        </td>
    </tr>

    <%} else if (operateType.equals("211")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="receive"/>


    <tr>
        <td class="label"><bean:message bundle="businesschange" key="businesschange.dealResult"/></td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}dealResult" id="${sheetPageName}dealResult"
                      alt="width:500,allowBlank:true,maxLength:2000,vtext:'${eoms:a2u('请输入处理结果，最大长度为1000个汉字！')}'">${sheetLink.dealResult }</textarea>
        </td>
    </tr>
    <%}%>
    <%} else if (taskName.equals("HoldHumTask")) {%>
    <%if (operateType.equals("18")) { %>

    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverTask"/>
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
        <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/>*</td>
        <td colspan="3">
            <textarea name="${sheetPageName}endResult"
                      alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请填入信息，最多输入1000字')}'"
                      id="${sheetPageName}endResult" class="textarea max">${sheetMain.endResult}</textarea>
        </td>
    </tr>
    <%}%>
    <%
        }
        if (taskName.equals("cc")) {
    %>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15"/>
            <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                      alt="allowBlank:false,maxLength:1000,width:500,vtext:'${eoms:a2u('请填入信息，最多输入1000字')}'"
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


    <%if (operateType.equals("4")) { %>
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
                          alt="allowBlank:false,width:500,vtext:'${eoms:a2u('请最多输入1000字')}'"
                          alt="width:'500px'">${sheetLink.remark}</textarea>
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
<% if (operateType.equals("201")) { %>
<fieldset>
    <legend>
    </legend>
    <eoms:chooser id="test"
                  category="[{id:'dealPerformer',childType:'user',allowBlank:false,limit:'none',text:'${eoms:a2u('派发')}',vtext:'${eoms:a2u('请选择派发对象')}'}]"
                  data="${sendUserAndRoles}"/>

</fieldset>
<% }
}%>
<%if (taskName.equals("ExcuteHumTask")) { %>
<% if (operateType.equals("8")) { %>
<fieldset>
    <legend>
        <bean:message bundle="businesschange" key="businesschange.toOrgName"/>
        <span id="roleName">:<bean:message bundle="businesschange" key="businesschange.excute"/>
			 </span>
    </legend>

    <eoms:chooser id="test"
                  category="[{id:'dealPerformer',allowBlank:false,childType:'user',text:'${eoms:a2u('派发')}',vtext:'${eoms:a2u('请选择派发对象')}'},{id:'copyPerformer',childType:'user',limit:'none',text:'${eoms:a2u('抄送')}'}]"/>

</fieldset>
<% } %>
<% if (operateType.equals("10")) { %>
<fieldset>
    <legend>
        <bean:message bundle="businesschange" key="businesschange.toOrgName"/>
        <span id="roleName">:<bean:message bundle="businesschange" key="businesschange.excute"/>
			 </span>
    </legend>

    <eoms:chooser id="test"
                  category="[{id:'dealPerformer',childType:'user',allowBlank:false,limit:'none',text:'${eoms:a2u('派发')}',vtext:'${eoms:a2u('请选择派发对象')}'}]"/>

</fieldset>
<% } %>
<% if (operateType.equals("206")) { //回复送审%>
<fieldset>
    <legend>
        <bean:message bundle="businesschange" key="businesschange.toOrgName"/>
        <span id="roleName">:<bean:message bundle="businesschange" key="businesschange.TaskCompleteAudit"/> </span>
    </legend>
    <eoms:chooser id="test"
                  category="[{id:'dealPerformer',text:'${eoms:a2u('审核')}',childType:'user',limit:'1',allowBlank:false,vtext:'${eoms:a2u('请选择审核对象')}'},{id:'copyPerformer',childType:'user',limit:'none',text:'${eoms:a2u('抄送')}'}]"
    />

</fieldset>
<% } %>
<% if (operateType.equals("205")) { %>
<fieldset>
    <legend>
        <bean:message bundle="businesschange" key="businesschange.toOrgName"/>
        <span id="roleName">:${eoms:a2u('上一级执行者')}
			 </span>
    </legend>
</fieldset>
<% } %>
<%} %>
