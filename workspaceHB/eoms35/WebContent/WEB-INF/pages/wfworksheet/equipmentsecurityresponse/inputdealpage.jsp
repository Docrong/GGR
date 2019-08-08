<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="com.boco.eoms.sheet.base.webapp.action.IBaseSheet" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>

<%
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
    String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId"));
    String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId"));
    System.out.println("=====taskName======" + taskName);
    String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
    request.setAttribute("operateType", operateType);
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

<%@ include file="/WEB-INF/pages/wfworksheet/equipmentsecurityresponse/baseinputlinkhtmlnew.jsp" %>
<br/>
<table class="formTable">
    <input type="hidden" id="tmpCompleteLimit" value=""/>
    <input type="hidden" name="linkBeanId" value="iEquipmentSecurityResponseLinkManager"/>
    <input type="hidden" name="beanId" value="iEquipmentSecurityResponseMainManager"/>
    <input type="hidden" name="mainClassName"
           value="com.boco.eoms.sheet.equipmentsecurityresponse.model.EquipmentSecurityResponseMain"/>
    <input type="hidden" name="linkClassName"
           value="com.boco.eoms.sheet.equipmentsecurityresponse.model.EquipmentSecurityResponseLink"/>
    <input type="hidden" name="toDeptId" value="${sheetMain.toDeptId}"/>
    <c:choose>
        <c:when test="${task.subTaskFlag == 'true'}">
            <input type="hidden" name="hasNextTaskFlag" id="hasNextTaskFlag" value="true"/>
        </c:when>
    </c:choose>
    <%if (operateType.equals("4")) { %>
    <input type="hidden" name="operateType" id="operateType" value="4"/>
    <c:choose>
        <c:when test="${empty fPreTaskName}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask"/>
        </c:when>
        <c:when test="${fPreTaskName =='DraftTask'}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask"/>
        </c:when>
        <c:otherwise>
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName }"/>
        </c:otherwise>
    </c:choose>
    <tr>
        <td class="label">
            备注说明
        </td>
        <td colspan="3">
            <textarea name="remark" class="textarea max" id="remark" alt="allowBlank:true,width:500,vtext:'请最多输入1000字'"
                      alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%} %>


    <%if (taskName.equals("Audit")) {%>
    <input type="hidden" name="operateType" id="operateType" value="<%=operateType %>"/>


    <%if (operateType.equals("102") || operateType.equals("11")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="AuditReply"/>


    <tr>

        <td class="label">
            <!-- 审核结果 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.linkAuditResult"/>
        </td>
        <td>
            <input type="text" class="text" name="linkAuditResult" id="linkAuditResult"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 审核结果 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkAuditResult}"/>
        </td>


        <td class="label">
            <!-- 审核意见 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.linkAuditOpinion"/>
        </td>
        <td>
            <input type="text" class="text" name="linkAuditOpinion" id="linkAuditOpinion"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 审核意见 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkAuditOpinion}"/>
        </td>
    </tr>


    <tr>

        <td class="label">
            <!-- 挂起状态 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.linkUpStuts"/>
        </td>
        <td>
            <input type="text" class="text" name="linkUpStuts" id="linkUpStuts"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 挂起状态 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkUpStuts}"/>
        </td>


        <td class="label">
            <!-- 挂起原因 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.linkUpReason"/>
        </td>
        <td>
            <input type="text" class="text" name="linkUpReason" id="linkUpReason"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 挂起原因 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkUpReason}"/>
        </td>
    </tr>


    <tr>

        <td class="label">
            <!-- 操作描述 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.linkOperation"/>
        </td>
        <td>
            <input type="text" class="text" name="linkOperation" id="linkOperation"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 操作描述 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkOperation}"/>
        </td>


        <td class="label">
            <!-- 审批结果 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.linkAuditResult"/>
        </td>
        <td>
            <input type="text" class="text" name="linkAuditResult" id="linkAuditResult"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 审批结果 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkAuditResult}"/>
        </td>
    </tr>


    <tr>

        <td class="label">
            <!-- 审核意见 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.linkAuditOpinion"/>
        </td>
        <td>
            <input type="text" class="text" name="linkAuditOpinion" id="linkAuditOpinion"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 审核意见 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkAuditOpinion}"/>
        </td>


        <td class="label">
            <!-- 解除说明 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.linkRelieveState"/>
        </td>
        <td>
            <input type="text" class="text" name="linkRelieveState" id="linkRelieveState"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 解除说明 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkRelieveState}"/>
        </td>
    </tr>


    <tr>

        <td class="label">
            <!-- 最长处理时间 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.mainDealTime"/>
        </td>
        <td>
            <input type="text" class="text" name="mainDealTime" readonly="readonly"
                   id="mainDealTime" value="${eoms:date2String(sheetLink.mainDealTime)}"
                   onclick="popUpCalendar(this, this)" alt="allowBlank:true"/>
        </td>


        <td class="label">
            <!-- 最长处理时间 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.mainDealTime"/>
        </td>
        <td>
            <input type="text" class="text" name="mainDealTime" readonly="readonly"
                   id="mainDealTime" value="${eoms:date2String(sheetLink.mainDealTime)}"
                   onclick="popUpCalendar(this, this)" alt="allowBlank:true"/>
        </td>
    </tr>


    <tr>

        <td class="label">
            <!-- 预留字段1 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.linkExtend1"/>
        </td>
        <td>
            <input type="text" class="text" name="linkExtend1" id="linkExtend1"
                   alt="allowBlank:true,maxLength:2000,vtext:'请填入 预留字段1 信息，最多输入 2000 字符'"
                   value="${sheetLink.linkExtend1}"/>
        </td>


        <td class="label">
            <!-- 预留字段1 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.linkExtend2"/>
        </td>
        <td>
            <input type="text" class="text" name="linkExtend2" id="linkExtend2"
                   alt="allowBlank:true,maxLength:2000,vtext:'请填入 预留字段1 信息，最多输入 2000 字符'"
                   value="${sheetLink.linkExtend2}"/>
        </td>
    </tr>


    <tr>

        <td class="label">
            <!-- 预留字段1 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.linkExtend3"/>
        </td>
        <td>
            <input type="text" class="text" name="linkExtend3" id="linkExtend3"
                   alt="allowBlank:true,maxLength:2000,vtext:'请填入 预留字段1 信息，最多输入 2000 字符'"
                   value="${sheetLink.linkExtend3}"/>
        </td>


        <td class="label">
            <!-- 预留字段1 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.linkExtend4"/>
        </td>
        <td>
            <input type="text" class="text" name="linkExtend4" id="linkExtend4"
                   alt="allowBlank:true,maxLength:2000,vtext:'请填入 预留字段1 信息，最多输入 2000 字符'"
                   value="${sheetLink.linkExtend4}"/>
        </td>
    </tr>


    <tr>

        <td class="label">
            <!-- 预留字段1 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.linkExtend5"/>
        </td>
        <td>
            <input type="text" class="text" name="linkExtend5" id="linkExtend5"
                   alt="allowBlank:true,maxLength:2000,vtext:'请填入 预留字段1 信息，最多输入 2000 字符'"
                   value="${sheetLink.linkExtend5}"/>
        </td>
    </tr>

    <%}%>


    <%
        }
        if (taskName.equals("AuditReply")) {
    %>
    <input type="hidden" name="operateType" id="operateType" value="<%=operateType %>"/>

    <%if (operateType.equals("104") || operateType.equals("11")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="QualityInspection"/>


    <tr>

        <td class="label">
            <!-- 回复对象 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.linkReplyObj"/>
        </td>
        <td>
            <input type="text" class="text" name="linkReplyObj" id="linkReplyObj"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 回复对象 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkReplyObj}"/>
        </td>


        <td class="label">
            <!-- 回复说明 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.linkReplyExp"/>
        </td>
        <td>
            <input type="text" class="text" name="linkReplyExp" id="linkReplyExp"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 回复说明 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkReplyExp}"/>
        </td>
    </tr>


    <tr>

        <td class="label">
            <!-- 采取措施的时间点 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.linkMeasuresTime"/>
        </td>
        <td>
            <input type="text" class="text" name="linkMeasuresTime" readonly="readonly"
                   id="linkMeasuresTime" value="${eoms:date2String(sheetLink.linkMeasuresTime)}"
                   onclick="popUpCalendar(this, this)" alt="allowBlank:true"/>
        </td>


        <td class="label">
            <!-- 故障消除时间 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.linkRemoveTime"/>
        </td>
        <td>
            <input type="text" class="text" name="linkRemoveTime" readonly="readonly"
                   id="linkRemoveTime" value="${eoms:date2String(sheetLink.linkRemoveTime)}"
                   onclick="popUpCalendar(this, this)" alt="allowBlank:true"/>
        </td>
    </tr>


    <tr>

        <td class="label">
            <!-- 故障原因 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.linkFaultCause"/>
        </td>
        <td>
            <input type="text" class="text" name="linkFaultCause" id="linkFaultCause"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 故障原因 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkFaultCause}"/>
        </td>


        <td class="label">
            <!-- 处理措施 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.linkDealMeasures"/>
        </td>
        <td>
            <input type="text" class="text" name="linkDealMeasures" id="linkDealMeasures"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 处理措施 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkDealMeasures}"/>
        </td>
    </tr>


    <tr>

        <td class="label">
            <!-- 是否为最终解决方案 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.linkIfSolution"/>
        </td>
        <td>
            <input type="text" class="text" name="linkIfSolution" id="linkIfSolution"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 是否为最终解决方案 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkIfSolution}"/>
        </td>


        <td class="label">
            <!-- 备注 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.linkRemark"/>
        </td>
        <td>
            <input type="text" class="text" name="linkRemark" id="linkRemark"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 备注 信息，最多输入 1000 字符'" value="${sheetLink.linkRemark}"/>
        </td>
    </tr>

    <%}%>


    <%
        }
        if (taskName.equals("QualityInspection")) {
    %>
    <input type="hidden" name="operateType" id="operateType" value="<%=operateType %>"/>

    <%if (operateType.equals("105") || operateType.equals("11")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="AuditReply"/>


    <tr>

        <td class="label">
            <!-- 质检结果 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.linkQualityResult"/>
        </td>
        <td>
				<textarea class="textarea max" name="linkQualityResult"
                          id="linkQualityResult"
                          alt="allowBlank:true,maxLength:1000,vtext:'请填入 质检结果 信息，最多输入 1000 字符'">${sheetLink.linkQualityResult}</textarea>

        </td>


        <td class="label">
            <!-- 质检概述 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.linkQualityView"/>
        </td>
        <td>
				<textarea class="textarea max" name="linkQualityView"
                          id="linkQualityView"
                          alt="allowBlank:true,maxLength:1000,vtext:'请填入 质检概述 信息，最多输入 1000 字符'">${sheetLink.linkQualityView}</textarea>

        </td>
    </tr>

    <%}%>

    <%if (operateType.equals("106") || operateType.equals("11")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="HoldTask"/>


    <tr>

        <td class="label">
            <!-- 质检结果 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.linkQualityResult"/>
        </td>
        <td>
				<textarea class="textarea max" name="linkQualityResult"
                          id="linkQualityResult"
                          alt="allowBlank:true,maxLength:1000,vtext:'请填入 质检结果 信息，最多输入 1000 字符'">${sheetLink.linkQualityResult}</textarea>

        </td>


        <td class="label">
            <!-- 质检概述 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.linkQualityView"/>
        </td>
        <td>
				<textarea class="textarea max" name="linkQualityView"
                          id="linkQualityView"
                          alt="allowBlank:true,maxLength:1000,vtext:'请填入 质检概述 信息，最多输入 1000 字符'">${sheetLink.linkQualityView}</textarea>

        </td>
    </tr>

    <%}%>


    <%
        }
        if (taskName.equals("HoldTask")) {
    %>
    <input type="hidden" name="operateType" id="operateType" value="<%=operateType %>"/>

    <%if (operateType.equals("18")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="Over"/>
    <input type="hidden" name="hasNextTaskFlag" id="hasNextTaskFlag" value="true"/>
    <input type="hidden" name="status" id="status" value="1"/>

    <%}%>

    <%if (operateType.equals("107") || operateType.equals("11")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="QualityInspection"/>


    <tr>

        <td class="label">
            <!-- 工单驳回描述 -->
            <bean:message bundle="equipmentsecurityresponse" key="equipmentSecurityResponseLink.linkReject"/>
        </td>
        <td>
				<textarea class="textarea max" name="linkReject"
                          id="linkReject"
                          alt="allowBlank:true,maxLength:1000,vtext:'请填入 工单驳回描述 信息，最多输入 1000 字符'">${sheetLink.linkReject}</textarea>

        </td>
    </tr>

    <%}%>


    <%
        }
        if (!operateType.equals("61") && !operateType.equals("18") && !operateType.equals("4")) {
    %>
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
                             scope="request" idField="nodeAccessories" appCode="equipmentsecurityresponse"/>
        </td>
    </tr>
    <%}%>
    <%if (taskName.equals("Audit") || taskName.equals("AuditReply") || taskName.equals("QualityInspection") || taskName.equals("HoldTask")) {%>
    <%if (operateType.equals("61")) { %>
    <input type="hidden" name="operateType" id="operateType" value="61"/>
    <!--  <tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="remark" class="textarea max" id="remark" 
		        alt="width:'500px',maxLength:200,vtext:'最多输入100汉字'">${sheetLink.remark}</textarea>
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
            <input type="hidden" name="operateType" id="operateType" value="-15"/>
            <textarea name="remark" class="textarea max" id="remark"
                      alt="allowBlank:false,width:500,maxLength:2000,vtext:'请最多输入1000汉字'"
                      alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%} %>
</table>


<% if (taskName.equals("Audit")) { %>

<% if (operateType.equals("102")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:处理人
									 </span>
    </legend>
    <div class="x-form-item">

        <eoms:chooser id="Audit0" type="role" roleId="4403" flowId="44" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>

    </div>
</fieldset>
<% } %>

<% }
    if (taskName.equals("AuditReply")) { %>


<% if (operateType.equals("104")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:质检员
									 </span>
    </legend>
    <div class="x-form-item">

        <eoms:chooser id="AuditReply0" type="role" roleId="4404" flowId="44" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>

    </div>
</fieldset>
<% } %>

<% }
    if (taskName.equals("QualityInspection")) { %>


<% if (operateType.equals("105")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:处理人
									 </span>
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="Audit0" type="role" roleId="4403" flowId="44" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>
    </div>
</fieldset>
<% } %>
<% if (operateType.equals("106")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:申请人
									 </span>
    </legend>
    <div class="x-form-item">

        <eoms:chooser id="QualityInspection1" type="role" roleId="4401" flowId="44"
                      config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>

    </div>
</fieldset>
<% } %>


<% } %>