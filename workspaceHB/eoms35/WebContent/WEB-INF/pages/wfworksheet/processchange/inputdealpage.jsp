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

<%@ include file="/WEB-INF/pages/wfworksheet/processchange/baseinputlinkhtmlnew.jsp" %>
<br/>
<table class="formTable">
    <input type="hidden" id="tmpCompleteLimit" value=""
           alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'处理时限不能晚于工单完成时限'"/>
    <input type="hidden" name="${sheetPageName}beanId" value="iProcessChangeMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.processchange.model.ProcessChangeMain"/>
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.processchange.model.ProcessChangeLink"/>
    <c:if test="${taskName != 'HoldTask'}">
        <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
    </c:if>

    <%
        if (taskName.equals("EvaluateTask")) {
            if (operateType.equals("91") || operateType.equals("11")) {
    %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="MakeTask"/>
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
        <td class="label"><bean:message bundle="processchange" key="processchange.linkProcessType"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}linkProcessType" id="${sheetPageName}linkProcessType"
                           initDicId="1010701" sub="${sheetPageName}linkProcess"
                           defaultValue="${sheetLink.linkProcessType}" alt="allowBlank:false"
                           styleClass="select-class"/>
        </td>
        <td class="label"><bean:message bundle="processchange" key="processchange.linkProcess"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}linkProcess" id="${sheetPageName}linkProcess"
                           initDicId="${sheetLink.linkProcessType}" defaultValue="${sheetLink.linkProcess}"
                           alt="allowBlank:false" styleClass="select-class"/>
        </td>
    </tr>
    <td class="label"><bean:message bundle="processchange" key="processchange.linkFrameDesc"/>*</td>
    <td colspan="3">
        <textarea class="textarea max" name="${sheetPageName}linkFrameDesc" id="${sheetPageName}linkFrameDesc"
                  alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入流程变更方案框架概述，最多输入125字'">${sheetLink.linkFrameDesc}</textarea>
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
        <td class="label"><bean:message bundle="processchange" key="processchange.linkSchemeFrame"/>*</td>
        <td colspan='3'>
            <eoms:attachment idList="" scope="request" idField="${sheetPageName}linkSchemeFrame" name="sheetLink"
                             property="linkSchemeFrame" appCode="processchange" alt="allowBlank:false"/>
        </td>
    </tr>
    <%
            }
        }
    %>

    <%
        if (taskName.equals("MakeTask")) {
            if (operateType.equals("92") || operateType.equals("11")) {
    %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AuditTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>"/>
    </tr>
    <td class="label"><bean:message bundle="processchange" key="processchange.linkSchemeDesc"/></td>
    <td colspan="3">
        <textarea class="textarea max" name="${sheetPageName}linkSchemeDesc" id="${sheetPageName}linkSchemeDesc"
                  alt="width:500,allowBlank:true,maxLength:255,vtext:'请填入流程变更方案概述，最多输入125字'">${sheetLink.linkSchemeDesc}</textarea>
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
        <td class="label"><bean:message bundle="processchange" key="processchange.linkChangeScheme"/></td>
        <td colspan='3'>
            <eoms:attachment idList="" scope="request" idField="${sheetPageName}linkChangeScheme" name="sheetLink"
                             property="linkChangeScheme" appCode="processchange" alt="allowBlank:true"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="processchange" key="processchange.linkITChangeScheme"/></td>
        <td colspan='3'>
            <eoms:attachment idList="" scope="request" idField="${sheetPageName}linkITChangeScheme" name="sheetLink"
                             property="linkITChangeScheme" appCode="processchange" alt="allowBlank:true"/>
        </td>
    </tr>
    <%
            }
        }
    %>

    <%
        if (taskName.equals("AuditTask")) {
            if (operateType.equals("93") || operateType.equals("55")) {
    %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ReleaseTask"/>
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
        <td class="label"><bean:message bundle="processchange" key="processchange.linkAuditResult"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult"
                           initDicId="1010702" alt="allowBlank:false" styleClass="select-class"
                           onchange="ifAuditPass(this.value);" defaultValue="${sheetLink.linkAuditResult}"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="processchange" key="processchange.linkAuditDesc"/></td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkAuditDesc" id="${sheetPageName}linkAuditDesc"
                      alt="width:500,allowBlank:true,maxLength:255,vtext:'请填入审批意见，最多输入125字'">${sheetLink.linkAuditDesc}</textarea>
        </td>
    </tr>
    <%
            }
        }
    %>

    <%
        if (taskName.equals("ReleaseTask")) {
            if (operateType.equals("94") || operateType.equals("11")) {
    %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OperateTask"/>
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
    <%if (!operateType.equals("11")) {%>
    <tr>
        <td class="label"><bean:message bundle="processchange" key="processchange.linkIfInvoke"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkIfInvoke" id="${sheetPageName}linkIfInvoke"
                           initDicId="1010703" alt="allowBlank:false" styleClass="select-class"
                           onchange="popOtherFlow(this.value);" defaultValue="${sheetLink.linkIfInvoke}"/>
            <html:button styleClass="btn" style="display:none" property="method.save" styleId="startITRequirement"
                         onclick="openwin()">IT需求申请工单</html:button>
        </td>
    </tr>
    <%} %>
    <tr>
        <td class="label"><bean:message bundle="processchange" key="processchange.linkReleaseDesc"/></td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkReleaseDesc" id="${sheetPageName}linkReleaseDesc"
                      alt="width:500,allowBlank:true,maxLength:255,vtext:'请填入发布流程变更概述，最多输入125字'">${sheetLink.linkReleaseDesc}</textarea>
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
                   alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="processchange" key="processchange.linkOptimizeImpact"/></td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkOptimizeImpact" id="${sheetPageName}linkOptimizeImpact"
                           initDicId="1010704" alt="allowBlank:true" styleClass="select-class"
                           defaultValue="${sheetLink.linkOptimizeImpact}"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="processchange" key="processchange.linkEvaluateDesc"/></td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkEvaluateDesc" id="${sheetPageName}linkEvaluateDesc"
                      alt="width:500,allowBlank:true,maxLength:255,vtext:'请填入实施后评估概述，最多输入125字'">${sheetLink.linkEvaluateDesc}</textarea>
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
        <td class="label"><bean:message bundle="processchange" key="processchange.linkEvaluateAttach"/>*</td>
        <td colspan='3'>
            <eoms:attachment idList="" scope="request" idField="${sheetPageName}linkEvaluateAttach" name="sheetLink"
                             property="linkEvaluateAttach" appCode="processchange" alt="allowBlank:false"/>
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
            <textarea name="${sheetPageName}endResult" alt="allowBlank:false,maxLength:255,vtext:'请最多输入125字'"
                      id="${sheetPageName}endResult" class="textarea max">${sheetMain.endResult}</textarea>
        </td>
    </tr>


    <%} else if (operateType.equals("102")) {%>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OperateTask"/>
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
            alt="width:'500px,vtext:'请最多输入1000字''">${sheetLink.remark}</textarea>
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
    if (taskName.equals("EvaluateTask")) {
        if (operateType.equals("91")) {
%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="processchange" key="role.techGroup"/>

    </legend>

    <eoms:chooser id="sendRole" type="role" roleId="377" flowId="081" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',allowBlank:false,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>
</fieldset>
<%
        }
    }
%>
<%
    if (taskName.equals("MakeTask")) {
        if (operateType.equals("92")) {
%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="processchange" key="role.leader"/>

    </legend>

    <eoms:chooser id="sendRole" type="role" roleId="376" flowId="081" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',allowBlank:false,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}subAuditPerformer',childType:'user,subrole',limit:'none',text:'会审'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>
</fieldset>
<%
        }
    }
%>
<%
    if (taskName.equals("AuditTask")) {
        if (operateType.equals("93")) {
%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <span id="roleName"></span>
    </legend>
    <eoms:chooser id="sendRole1" type="role" roleId="375" flowId="011" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',allowBlank:false,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>
        <%}} %>

        <%if(taskName.equals("ReleaseTask")) {
	  	   if(operateType.equals("94")){ %>
    <fieldset id="link4">
        <legend>
            <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
            <bean:message bundle="processchange" key="role.adminGroup"/>
        </legend>
        <eoms:chooser id="sendRole1" type="role" roleId="375" flowId="011" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'${sheetPageName}dealPerformer',allowBlank:false,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>
            <%}} %>
            <%if(taskName.equals("OperateTask")) {
	  	   if(operateType.equals("95")){ %>
        <fieldset id="link4">
            <legend>
                <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
                <bean:message bundle="processchange" key="role.newer"/>
            </legend>
                <%}} %>
                <%if(taskName.equals("HoldTask")) {
	  	   if(operateType.equals("102")){ %>
            <fieldset id="link4">
                <legend>
                    <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
                    <bean:message bundle="processchange" key="role.adminGroup"/>
                </legend>
            </fieldset>
                <%}} %>
            <script type="text/javascript">
                function ifAuditPass(input) {
                    if ('<%=operateType%>' != '55') {
                        if (input == "101070201") {
                            chooser_sendRole1.enable();
                            //测试通过
                            $('${sheetPageName}phaseId').value = 'ReleaseTask';
                            $('${sheetPageName}operateType').value = '931';
                            $('roleName').innerHTML = "流程变更管理组";
                        } else if (input == "101070202") {
                            //测试不通过
                            chooser_sendRole1.disable();
                            $('${sheetPageName}phaseId').value = 'MakeTask';
                            $('${sheetPageName}operateType').value = '932';
                            $('roleName').innerHTML = "流程变更技术组";
                        } else if (input == "101070203") {
                            //无法实现
                            chooser_sendRole1.disable();
                            $('${sheetPageName}phaseId').value = 'HoldTask';
                            $('${sheetPageName}operateType').value = '933';
                            $('roleName').innerHTML = "建单人";
                        } else {
                            chooser_sendRole1.disable();
                            $('${sheetPageName}phaseId').value = '';
                            $('${sheetPageName}operateType').value = '';
                            $('roleName').innerHTML = "";
                        }
                    }
                }

                var frm = document.forms[0];
                var temp = frm.linkAuditResult ? frm.linkAuditResult.value : '';
                if (temp != '') {
                    ifAuditPass(temp);
                }

                function popOtherFlow(value) {
                    if (value == '101070301') {
                        $('${sheetPageName}operateType').value = '941';
                        $('${sheetPageName}phaseId').value = 'callProcess'
                        document.getElementById('startITRequirement').style.display = '';
                    } else if (value == '101070302') {
                        if ("${ifInvoke}" == "no") {
                            alert("任务正在等待回调中!请选择'是'选项");
                            $('${sheetPageName}operateType').value = '941';
                            $('${sheetPageName}phaseId').value = 'callProcess'
                            document.all.${sheetPageName}linkIfInvoke.value = "101070301"
                            document.getElementById('startITRequirement').style.display = '';

                        } else {
                            $('${sheetPageName}operateType').value = '94';
                            $('${sheetPageName}phaseId').value = 'OperateTask'
                            document.getElementById('startITRequirement').style.display = 'none';
                        }
                    }
                }

                if ("${taskName}" == "ReleaseTask" && '<%=operateType%>' != '61') {
                    var frm = document.forms[0];
                    if ("${ifInvoke}" == "no") {
                        document.forms[0].linkIfInvoke.value = "101070301";
                    }
                    var temp1 = frm.linkIfInvoke ? frm.linkIfInvoke.value : '';
                    popOtherFlow(temp1);
                }

                function openwin(flag) {
                    var url;
                    $('${sheetPageName}phaseId').value = 'callProcess';
                    url = "${app}/sheet/itrequirement/itrequirement.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iProcessChangeMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}";
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
