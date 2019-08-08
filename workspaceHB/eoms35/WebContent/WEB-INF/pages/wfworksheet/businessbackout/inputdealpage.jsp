<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.boco.eoms.sheet.businessbackout.task.impl.BusinessBackoutTaskImpl" %>

<%
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
    String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId"));
    String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId"));

    String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
    System.out.println("=====taskName======" + taskName + "operateType===" + operateType);
    String roleId = "";
    String roleName = "";

    //BusinessBackoutTask task =(BusinessPilotLink) request.getAttribute("task");

    if (request.getAttribute("task") != null) {
        BusinessBackoutTaskImpl task = (BusinessBackoutTaskImpl) request.getAttribute("task");
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
            url = "${app}/sheet/circuitdispatch/circuitdispatch.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iBusinessBackoutMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}@${task.processId}";
        } else if (flag == "101050802") {
            if ($('${sheetPageName}linkNetType').value.indexOf('101050802') == -1) {
                $('${sheetPageName}linkNetType').value += '101050802' + ',';
            }
            $('${sheetPageName}phaseId').value = 'receiveNet';

            url = "${app}/sheet/softchange/softchange.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iBusinessBackoutMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}@${task.processId}";
        } else if (flag == "101050803") {
            if ($('${sheetPageName}linkNetType').value.indexOf('101050803') == -1) {
                $('${sheetPageName}linkNetType').value += '101050803' + ',';
            }
            $('${sheetPageName}phaseId').value = 'receiveNet';

            url = "${app}/sheet/netdata/netdata.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iBusinessBackoutMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}@${task.processId}";
        } else if (flag == "101050804") {
            if ($('${sheetPageName}linkNetType').value.indexOf('101050804') == -1) {
                $('${sheetPageName}linkNetType').value += '101050804' + ',';
            }
            $('${sheetPageName}phaseId').value = 'receiveNet';

            url = "${app}/sheet/netchange/netchange.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iBusinessBackoutMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}@${task.processId}";
        }
        window.open(url, 'newwindow', 'height=800, width=1000, top=200, left=200,toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
    }
</script>
<%@ include file="/WEB-INF/pages/wfworksheet/businessbackout/baseinputlinkhtmlnew.jsp" %>
<br/>
<table class="formTable">
    <input type="hidden" id="tmpCompleteLimit" value=""
           alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'${eoms:a2u("处理时限不能晚于工单完成时限")}'"/>
    <input type="hidden" name="${sheetPageName}beanId" value="iBusinessBackoutMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.businessbackout.model.BusinessBackoutMain"/>
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.businessbackout.model.BusinessBackoutLink"/>
    <c:if test="${taskName != 'HoldHumTask' }">
        <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateUserId}"/>
    </c:if>

    <%
        System.out.println("********taskName:" + taskName);
        System.out.println("********operateType:" + operateType);
    %>
    <%if (taskName.equals("TaskCreateAuditHumTask")) { %>
    <%if (operateType.equals("201")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="201"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TransmitMoreTask"/>
    <%} else if (operateType.equals("202")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="202"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ByRejectHumTask"/>
    <%}%>
    <%if (!operateType.equals("61") && !operateType.equals("4")) { %>
    <tr>
        <td class="label"><bean:message bundle="businessbackout" key="businessbackout.auditResult"/></td>
        <td colspan="3"><textarea class="textarea max" name="${sheetPageName}auditResult"
                                  id="${sheetPageName}auditResult"
                                  alt="width:500,allowBlank:true">${sheetLink.auditResult}</textarea></td>
    </tr>
    <%
        }
    } else if (taskName.equals("TaskCompleteAuditHumTask")) {
    %>
    <%if (operateType.equals("208")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="203"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AffirmHumTask"/>
    <tr>
        <td class="label"><bean:message bundle="businessbackout" key="businessbackout.auditResult"/></td>
        <td colspan="3"><textarea class="textarea max" name="${sheetPageName}auditResult"
                                  id="${sheetPageName}auditResult"
                                  alt="width:500,allowBlank:true">${sheetLink.auditResult}</textarea></td>
    </tr>
    <%} else if (operateType.equals("209")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="204"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask"/>
    <tr>
        <td class="label"><bean:message bundle="businessbackout" key="businessbackout.auditResult"/></td>
        <td colspan="3"><textarea class="textarea max" name="${sheetPageName}auditResult"
                                  id="${sheetPageName}auditResult"
                                  alt="width:500,allowBlank:true">${sheetLink.auditResult}</textarea></td>
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
    <%
    } else if (operateType.equals("61")) {
        //确认受理
    %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
    <%--  <tr>
      <td class="label"><bean:message bundle="businessbackout" key="businessbackout.remark" /></td>
      <td colspan="3"><textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" alt="width:'500px'">${sheetLink.remark}</textarea></td>
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
        <td class="label"><bean:message bundle="businessbackout" key="businessbackout.ndeptContact"/>*</td>
        <td class="content"><input type="text" class="text" value="${sheetLink.ndeptContact}"
                                   name="${sheetPageName}ndeptContact" id="${sheetPageName}ndeptContact"
                                   alt="allowBlank:false,maxLength:25,vtext:'${eoms:a2u('请输入网络部门联系人，最大长度为12个汉字！')}'"/>
        </td>
        <td class="label"><bean:message bundle="businessbackout" key="businessbackout.ndeptContactPhone"/>*</td>
        <td class="content"><input type="text" class="text" value="${sheetLink.ndeptContactPhone}"
                                   name="${sheetPageName}ndeptContactPhone" id="${sheetPageName}ndeptContactPhone"
                                   alt="allowBlank:false,maxLength:50,vtext:'${eoms:a2u('请输入网络部门联系人电话，最大长度为25个汉字！')}'"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businessbackout" key="businessbackout.dealResult"/>*</td>
        <td class="content" colspan="3"><eoms:comboBox defaultValue="${sheetLink.dealResult}"
                                                       name="${sheetPageName}dealResult" id="${sheetPageName}dealResult"
                                                       initDicId="1011051" alt="allowBlank:false"
                                                       styleClass="select-class"
                                                       alt="allowBlank:false,vtext:'${eoms:a2u('请选择处理结果！')}'"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businessbackout" key="businessbackout.dealDesc"/></td>
        <td colspan="3"><textarea class="textarea max" name="${sheetPageName}dealDesc" id="${sheetPageName}dealDesc"
                                  alt="width:500,allowBlank:false,maxLength:1000,vtext:'${eoms:a2u('请输入处理说明，最大长度为500个汉字！')}'">${sheetLink.dealDesc}</textarea>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="sheet" key="linkForm.nodeAccessories"/></td>
        <td colspan="3">
            <eoms:attachment name="sheetLink" property="nodeAccessories"
                             scope="request" idField="${sheetPageName}nodeAccessories" appCode="businessbackout"/>
        </td>
    </tr>
    <%} else if (operateType.equals("206")) { //回复送审%>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="206"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TaskCompleteAuditHumTask"/>
    <tr>
        <td class="label"><bean:message bundle="businessbackout" key="businessbackout.ndeptContact"/>*</td>
        <td class="content"><input type="text" class="text" value="${sheetLink.ndeptContact}"
                                   name="${sheetPageName}ndeptContact" id="${sheetPageName}ndeptContact"
                                   alt="allowBlank:false,maxLength:25,vtext:'${eoms:a2u('请输入网络部门联系人，最大长度为12个汉字！')}'"/>
        </td>
        <td class="label"><bean:message bundle="businessbackout" key="businessbackout.ndeptContactPhone"/>*</td>
        <td class="content"><input type="text" class="text" value="${sheetLink.ndeptContactPhone}"
                                   name="${sheetPageName}ndeptContactPhone" id="${sheetPageName}ndeptContactPhone"
                                   alt="allowBlank:false,maxLength:50,vtext:'${eoms:a2u('请输入网络部门联系人电话，最大长度为25个汉字！')}'"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businessbackout" key="businessbackout.dealResult"/>*</td>
        <td class="content" colspan="3"><eoms:comboBox defaultValue="${sheetLink.dealResult}"
                                                       name="${sheetPageName}dealResult" id="${sheetPageName}dealResult"
                                                       initDicId="1011051" alt="allowBlank:false"
                                                       styleClass="select-class"
                                                       alt="allowBlank:false,vtext:'${eoms:a2u('请选择处理结果！')}'"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businessbackout" key="businessbackout.dealDesc"/></td>
        <td colspan="3"><textarea class="textarea max" name="${sheetPageName}dealDesc" id="${sheetPageName}dealDesc"
                                  alt="width:500,allowBlank:false,maxLength:1000,vtext:'${eoms:a2u('请输入处理说明，最大长度为500个汉字！')}'">${sheetLink.dealDesc}</textarea>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="linkForm.nodeAccessories"/></td>
        <td colspan="3">
            <eoms:attachment name="sheetLink" property="nodeAccessories"
                             scope="request" idField="${sheetPageName}nodeAccessories" appCode="businessbackout"/>
        </td>
    </tr>
    <%} else if (operateType.equals("10")) { //转派%>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="10"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask"/>
    <tr>
        <td class="label"><bean:message bundle="businessbackout" key="businessbackout.transmitReason"/></td>
        <td colspan="3"><textarea class="textarea max" name="${sheetPageName}transferReason"
                                  id="${sheetPageName}transferReason"
                                  alt="width:500,allowBlank:true,maxLength:2000,vtext:'${eoms:a2u('请输入转派理由，最大长度为1000个汉字！')}'">${sheetLink.transferReason}</textarea>
        </td>
    </tr>
    <% } else if (operateType.equals("8")) { //移交%>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="8"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ShiftScopeTask"/>
    <tr>
        <td class="label"><bean:message bundle="businessbackout" key="businessbackout.yijiaoresion"/></td>
        <td colspan="3"><textarea class="textarea max" name="${sheetPageName}transferReason"
                                  id="${sheetPageName}transferReason"
                                  alt="width:500,allowBlank:true">${sheetLink.transferReason}</textarea></td>
    </tr>
    <%}%>
    <%} else if (taskName.equals("AffirmHumTask")) {%>
    <%if (operateType.equals("7")) { //处理回复不通过%>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="7"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteHumTask"/>
    <tr>
        <td class="label"><bean:message bundle="businessbackout" key="businessbackout.dealDesc"/></td>
        <td colspan="3"><textarea class="textarea max" name="${sheetPageName}dealDesc" id="${sheetPageName}dealDesc"
                                  alt="width:500,allowBlank:true,maxLength:2000,vtext:'${eoms:a2u('请输入处理说明，最大长度为1000个汉字！')}'">${sheetLink.dealDesc}</textarea>
        </td>
    </tr>

    <%} else if (operateType.equals("6")) { //处理回复通过%>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="6"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="receive"/>
    <tr>
        <td class="label"><bean:message bundle="businessbackout" key="businessbackout.dealDesc"/></td>
        <td colspan="3"><textarea class="textarea max" name="${sheetPageName}dealDesc" id="${sheetPageName}dealDesc"
                                  alt="width:500,allowBlank:true,maxLength:2000,vtext:'${eoms:a2u('请输入处理说明，最大长度为1000个汉字！')}'">${sheetLink.dealDesc}</textarea>
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
                           initDicId="10303" styleClass="select"
                           alt="allowBlank:false,vtext:'${eoms:a2u('请选择归档满意度！')}'"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/>*</td>
        <td colspan="3">
            <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult"
                      alt="width:500,allowBlank:false,maxLength:2000,vtext:'${eoms:a2u('请输入归档意见，最大长度为1000个汉字！')}'"
                      class="textarea max">${sheetMain.endResult}</textarea>
        </td>
    </tr>
    <%}%>
    <%} else if (taskName.equals("cc")) {%>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15"/>

    <tr>
        <td class="label"><bean:message bundle="businessbackout" key="businessbackout.remark"/></td>
        <td colspan="3"><textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                                  alt="allowBlank:false,maxLength:1000,width:500,vtext:'${eoms:a2u('请填入意见，最多输入1000字')}'">${sheetLink.remark}</textarea>
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
            <bean:message bundle="businessbackout" key="businessbackout.rejectReason"/>*
        </td>
        <td colspan="3">
		        <textarea name="${sheetPageName}rejectReason" class="textarea max" id="${sheetPageName}rejectReason"
                          alt="allowBlank:false,width:500,vtext:'${eoms:a2u('请最多输入200字')}'"
                          alt="width:'500px'">${sheetLink.rejectReason}</textarea>
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
        <bean:message bundle="businessbackout" key="role.toOrgName"/>
        <span id="roleName">:<bean:message bundle="businessbackout" key="role.excute"/>
			 </span>
    </legend>
    <eoms:chooser id="test"
                  category="[{id:'dealPerformer',childType:'user',allowBlank:false,limit:'none',text:'${eoms:a2u('派发')}',allowBlank:false,vtext:'${eoms:a2u('请选择派发对象')}'}]"
                  data="${sendUserAndRoles}"/>

</fieldset>
<% }
} %>

<%if (taskName.equals("ExcuteHumTask")) { %>
<% if (operateType.equals("8")) { //移交%>
<fieldset>
    <legend>
        <bean:message bundle="businessbackout" key="role.toOrgName"/>
        <span id="roleName">:<bean:message bundle="businessbackout" key="role.excute"/>
			 </span>
    </legend>


    <eoms:chooser id="test"
                  category="[{id:'dealPerformer',allowBlank:false,childType:'user',text:'${eoms:a2u('派发')}',allowBlank:false,vtext:'${eoms:a2u('请选择派发对象')}'}]"/>

</fieldset>
<% } %>
<% if (operateType.equals("10")) { //转派%>
<fieldset>
    <legend>
        <bean:message bundle="businessbackout" key="role.toOrgName"/>
        <span id="roleName">:<bean:message bundle="businessbackout" key="role.excute"/>
			 </span>
    </legend>

    <eoms:chooser id="test"
                  category="[{id:'dealPerformer',childType:'user',allowBlank:false,limit:'none',text:'${eoms:a2u('派发')}',vtext:'${eoms:a2u('请选择派发对象')}'}]"/>


</fieldset>
<% } %>
<% if (operateType.equals("206")) { //回复送审%>
<fieldset>
    <legend>
        <bean:message bundle="businessbackout" key="role.toOrgName"/>
        <span id="roleName">:<bean:message bundle="businessbackout" key="role.TaskCompleteAudit"/> </span>
    </legend>
    <eoms:chooser id="test"
                  category="[{id:'dealPerformer',text:'${eoms:a2u('审核')}',childType:'user',limit:'1',allowBlank:false,vtext:'${eoms:a2u('请选择审核对象')}'},{id:'copyPerformer',childType:'user',limit:'none',text:'${eoms:a2u('抄送')}'}]"
    />

</fieldset>
<% } %>
<% if (operateType.equals("205")) { //回复%>
<fieldset>
    <legend>
        <bean:message bundle="businessbackout" key="role.toOrgName"/>
        <span id="roleName">:${eoms:a2u('上一级执行者')}
			 </span>
    </legend>
</fieldset>
<% } %>
<%} %>
<input type="hidden" name="${sheetPageName}operName" id="${sheetPageName}operName"/>
<script type="text/javascript">
    <%//operateName初始化赋值%>
    try {
        var dealSel = document.getElementById('dealSelector');
        var selIndex = dealSel.selectedIndex;
        document.getElementById('${sheetPageName}operName').value = dealSel.options[selIndex].innerText;
    } catch (e) {
        //alert(e.message);//用于测试
        document.getElementById('${sheetPageName}operName').value = '';
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
