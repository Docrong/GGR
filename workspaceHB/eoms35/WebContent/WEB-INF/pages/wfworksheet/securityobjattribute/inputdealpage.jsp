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

<%@ include file="/WEB-INF/pages/wfworksheet/securityobjattribute/baseinputlinkhtmlnew.jsp" %>
<br/>
<table class="formTable">
    <input type="hidden" id="tmpCompleteLimit" value=""/>
    <input type="hidden" name="linkBeanId" value="iSecurityObjAttributeLinkManager"/>
    <input type="hidden" name="beanId" value="iSecurityObjAttributeMainManager"/>
    <input type="hidden" name="mainClassName"
           value="com.boco.eoms.sheet.securityobjattribute.model.SecurityObjAttributeMain"/>
    <input type="hidden" name="linkClassName"
           value="com.boco.eoms.sheet.securityobjattribute.model.SecurityObjAttributeLink"/>
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
            <bean:message bundle="securityobjattribute" key="securityObjAttributeLink.linkAuditResult"/>
        </td>
        <td>
            <input type="text" class="text" name="linkAuditResult" id="linkAuditResult"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 审核结果 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkAuditResult}"/>
        </td>


        <td class="label">
            <!-- 审核意见 -->
            <bean:message bundle="securityobjattribute" key="securityObjAttributeLink.linkAuditOpinion"/>
        </td>
        <td>
            <input type="text" class="text" name="linkAuditOpinion" id="linkAuditOpinion"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 审核意见 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkAuditOpinion}"/>
        </td>
    </tr>


    <tr>

        <td class="label">
            <!-- 预留字段1 -->
            <bean:message bundle="securityobjattribute" key="securityObjAttributeLink.linkExtend1"/>
        </td>
        <td>
            <input type="text" class="text" name="linkExtend1" id="linkExtend1"
                   alt="allowBlank:true,maxLength:2000,vtext:'请填入 预留字段1 信息，最多输入 2000 字符'"
                   value="${sheetLink.linkExtend1}"/>
        </td>


        <td class="label">
            <!-- 预留字段1 -->
            <bean:message bundle="securityobjattribute" key="securityObjAttributeLink.linkExtend2"/>
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
            <bean:message bundle="securityobjattribute" key="securityObjAttributeLink.linkExtend3"/>
        </td>
        <td>
            <input type="text" class="text" name="linkExtend3" id="linkExtend3"
                   alt="allowBlank:true,maxLength:2000,vtext:'请填入 预留字段1 信息，最多输入 2000 字符'"
                   value="${sheetLink.linkExtend3}"/>
        </td>


        <td class="label">
            <!-- 预留字段1 -->
            <bean:message bundle="securityobjattribute" key="securityObjAttributeLink.linkExtend4"/>
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
            <bean:message bundle="securityobjattribute" key="securityObjAttributeLink.linkExtend5"/>
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
            <bean:message bundle="securityobjattribute" key="securityObjAttributeLink.linkReplyObj"/>
        </td>
        <td>
            <input type="text" class="text" name="linkReplyObj" id="linkReplyObj"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 回复对象 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkReplyObj}"/>
        </td>


        <td class="label">
            <!-- 回复说明 -->
            <bean:message bundle="securityobjattribute" key="securityObjAttributeLink.linkReplyExp"/>
        </td>
        <td>
            <input type="text" class="text" name="linkReplyExp" id="linkReplyExp"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 回复说明 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkReplyExp}"/>
        </td>
    </tr>


    <tr>

        <td class="label">
            <!-- 备注 -->
            <bean:message bundle="securityobjattribute" key="securityObjAttributeLink.linkRemark"/>
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
            <bean:message bundle="securityobjattribute" key="securityObjAttributeLink.linkQualityResult"/>
        </td>
        <td>
				<textarea class="textarea max" name="linkQualityResult"
                          id="linkQualityResult"
                          alt="allowBlank:true,maxLength:1000,vtext:'请填入 质检结果 信息，最多输入 1000 字符'">${sheetLink.linkQualityResult}</textarea>

        </td>


        <td class="label">
            <!-- 质检概述 -->
            <bean:message bundle="securityobjattribute" key="securityObjAttributeLink.linkQualityView"/>
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
            <bean:message bundle="securityobjattribute" key="securityObjAttributeLink.linkQualityResult"/>
        </td>
        <td>
				<textarea class="textarea max" name="linkQualityResult"
                          id="linkQualityResult"
                          alt="allowBlank:true,maxLength:1000,vtext:'请填入 质检结果 信息，最多输入 1000 字符'">${sheetLink.linkQualityResult}</textarea>

        </td>


        <td class="label">
            <!-- 质检概述 -->
            <bean:message bundle="securityobjattribute" key="securityObjAttributeLink.linkQualityView"/>
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
            <bean:message bundle="securityobjattribute" key="securityObjAttributeLink.linkReject"/>
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
                             scope="request" idField="nodeAccessories" appCode="securityobjattribute"/>
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

        <eoms:chooser id="Audit0" type="role" roleId="4203" flowId="42" config="{returnJSON:true,showLeader:true}"
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

        <eoms:chooser id="AuditReply0" type="role" roleId="4204" flowId="42" config="{returnJSON:true,showLeader:true}"
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

        <eoms:chooser id="QualityInspection1" type="role" roleId="4201" flowId="42"
                      config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>

    </div>
</fieldset>
<% } %>


<% } %>