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

<%@ include file="/WEB-INF/pages/wfworksheet/transprovincial/baseinputlinkhtmlnew.jsp" %>
<br/>
<table class="formTable">
    <input type="hidden" id="tmpCompleteLimit" value=""/>
    <input type="hidden" name="linkBeanId" value="iTransprovincialLinkManager"/>
    <input type="hidden" name="beanId" value="iTransprovincialMainManager"/>
    <input type="hidden" name="mainClassName" value="com.boco.eoms.sheet.transprovincial.model.TransprovincialMain"/>
    <input type="hidden" name="linkClassName" value="com.boco.eoms.sheet.transprovincial.model.TransprovincialLink"/>
    <input type="hidden" name="toDeptId" value="${sheetMain.toDeptId}"/>
    <c:choose>
        <c:when test="${task.subTaskFlag == 'true'}">
            <input type="hidden" name="hasNextTaskFlag" id="hasNextTaskFlag" value="true"/>
        </c:when>
    </c:choose>
    <%if (operateType.equals("4")) { %>
    <input type="hidden" name="operateType" id="operateType" value="4"/>
    <input type="hidden" name="phaseId" id="phaseId" value="${fPreTaskName}"/>
    <tr>
        <td class="label">
            备注说明*
        </td>
        <td colspan="3">
		        <textarea name="remark" class="textarea max" id="remark"
                          alt="allowBlank:false,width:500,vtext:'请最多输入1000字'"
                          alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%} %>


    <%if (taskName.equals("Assignment")) {%>
    <input type="hidden" name=operateType" id="operateType" value="<%=operateType %>"/>


    <%if (operateType.equals("102") || operateType.equals("102")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="ProjectDesign"/>


    <tr>

        <td class="label">
            <!-- 分派结果 -->
            <bean:message bundle="transprovincial" key="transprovincialLink.assignmentResult"/>*
        </td>
        <td>
            <input type="text" class="text" name="assignmentResult" id="assignmentResult"
                   alt="allowBlank:false,maxLength:1000,vtext:'请填入 分派结果 信息，最多输入 1000 字符'"
                   value="${sheetLink.assignmentResult}"/>
        </td>
    </tr>

    <%}%>


    <%
        }
        if (taskName.equals("ProjectDesign")) {
    %>
    <input type="hidden" name=operateType" id="operateType" value="<%=operateType %>"/>

    <%if (operateType.equals("103") || operateType.equals("103")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="SubmitExamine"/>


    <tr>

        <td class="label">
            <!-- 设计结果 -->
            <bean:message bundle="transprovincial" key="transprovincialLink.desginResult"/>*
        </td>
        <td>
            <input type="text" class="text" name="desginResult" id="desginResult"
                   alt="allowBlank:false,maxLength:1000,vtext:'请填入 设计结果 信息，最多输入 1000 字符'"
                   value="${sheetLink.desginResult}"/>
        </td>
    </tr>

    <%}%>


    <%
        }
        if (taskName.equals("SubmitExamine")) {
    %>
    <input type="hidden" name=operateType" id="operateType" value="<%=operateType %>"/>

    <%if (operateType.equals("104") || operateType.equals("104")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="ProjectExamine"/>


    <tr>

        <td class="label">
            <!-- 审核结果 -->
            <bean:message bundle="transprovincial" key="transprovincialLink.examineResult"/>*
        </td>
        <td>
            <textarea class="textarea max" name="examineResult" id="examineResult"
                      alt="allowBlank:false,maxLength:2000,vtext:'请输入审核结果，多输入100汉字'">${sheetLink.examineResult}</textarea>
        </td>
    </tr>

    <%}%>
    <%if (operateType.equals("114") || operateType.equals("114")) { %>
    <input type="hidden" name="operateType" id="operateType" value="4"/>
    <input type="hidden" name="phaseId" id="phaseId" value="ProjectDesign"/>


    <tr>

        <td class="label">
            <!-- 审核结果 -->
            <bean:message bundle="transprovincial" key="transprovincialLink.examineResult"/>*
        </td>
        <td>
            <input type="text" class="text" name="examineResult" id="examineResult"
                   alt="allowBlank:false,maxLength:1000,vtext:'请填入 审核结果 信息，最多输入 1000 字符'"
                   value="${sheetLink.examineResult}"/>
        </td>
    </tr>

    <%}%>


    <%
        }
        if (taskName.equals("ProjectExamine")) {
    %>
    <input type="hidden" name=operateType" id="operateType" value="<%=operateType%>"/>

    <%if (operateType.equals("105") || operateType.equals("105")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="ProjectConstract"/>


    <tr>

        <td class="label">
            <!-- 审核情况 -->
            审核情况*
        </td>
        <td>
            <textarea class="textarea max" name="constractCondition" id="constractCondition"
                      alt="allowBlank:false,maxLength:2000,vtext:'请输入审核情况，多输入100汉字'">${sheetLink.constractCondition}</textarea>
        </td>
    </tr>

    <%}%>
    <%if (operateType.equals("112") || operateType.equals("112")) { %>
    <input type="hidden" name="operateType" id="operateType" value="4"/>
    <input type="hidden" name="phaseId" id="phaseId" value="SubmitExamine"/>


    <tr>

        <td class="label">
            <!-- 回复结果 -->
            <bean:message bundle="transprovincial" key="transprovincialLink.linkGroupComplete"/>*
        </td>
        <td>
            <input type="text" class="text" name="linkGroupComplete" id="linkGroupComplete"
                   alt="allowBlank:false,maxLength:1000,vtext:'请填入 回复结果 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkGroupComplete}"/>
        </td>
    </tr>

    <%}%>


    <%
        }
        if (taskName.equals("ProjectConstract")) {
    %>
    <input type="hidden" name=operateType" id="operateType" value="<%=operateType %>"/>

    <%if (operateType.equals("106") || operateType.equals("106")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="SubmibAcceptance"/>


    <tr>

        <td class="label">
            <!-- 完成情况 -->
            <bean:message bundle="transprovincial" key="transprovincialLink.completeResult"/>*
        </td>
        <td>
            <textarea class="textarea max" name="completeResult" id="completeResult"
                      alt="allowBlank:false,maxLength:2000,vtext:'请输入审核情况，多输入100汉字'">${sheetLink.completeResult}</textarea>
        </td>
    </tr>

    <%}%>


    <%
        }
        if (taskName.equals("SubmibAcceptance")) {
    %>
    <input type="hidden" name=operateType" id="operateType" value="<%=operateType %>"/>

    <%if (operateType.equals("107") || operateType.equals("107")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="AcceptanceExamine"/>


    <tr>

        <td class="label">
            <!-- 验收情况 -->
            <bean:message bundle="transprovincial" key="transprovincialLink.acceptanceCondition"/>*
        </td>
        <td>
            <input type="text" class="text" name="acceptanceCondition" id="acceptanceCondition"
                   alt="allowBlank:false,maxLength:1000,vtext:'请填入 验收情况 信息，最多输入 1000 字符'"
                   value="${sheetLink.acceptanceCondition}"/>
        </td>
    </tr>

    <%}%>


    <%
        }
        if (taskName.equals("AcceptanceExamine")) {
    %>
    <input type="hidden" name=operateType" id="operateType" value="<%=operateType %>"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>

    <%if (operateType.equals("108") || operateType.equals("108")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="CompletionTask"/>


    <tr>

        <td class="label">
            <!-- 审核情况 -->
            审核情况*
        </td>
        <td>
            <textarea class="textarea max" name="acceptanceResult" id="acceptanceResult"
                      alt="allowBlank:false,maxLength:2000,vtext:'请输入审核情况，多输入100汉字'">${sheetLink.acceptanceResult}</textarea>
        </td>
    </tr>

    <%}%>
    <%if (operateType.equals("113") || operateType.equals("113")) { %>
    <input type="hidden" name="operateType" id="operateType" value="4"/>
    <input type="hidden" name="phaseId" id="phaseId" value="SubmibAcceptance"/>


    <tr>

        <td class="label">
            <!-- 回复结果 -->
            <bean:message bundle="transprovincial" key="transprovincialLink.linkGroupComplete"/>*
        </td>
        <td>
            <input type="text" class="text" name="linkGroupComplete" id="linkGroupComplete"
                   alt="allowBlank:false,maxLength:1000,vtext:'请填入 回复结果 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkGroupComplete}"/>
        </td>
    </tr>

    <%}%>


    <%
        }
        if (taskName.equals("CompletionTask")) {
    %>
    <input type="hidden" name=operateType" id="operateType" value="<%=operateType %>"/>

    <%if (operateType.equals("109") || operateType.equals("109")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="AffirmHumTask"/>


    <tr>

        <td class="label">
            <!-- 开通结果 -->
            <bean:message bundle="transprovincial" key="transprovincialLink.completionResult"/>*
        </td>
        <td>
            <input type="text" class="text" name="completionResult" id="completionResult"
                   alt="allowBlank:false,maxLength:1000,vtext:'请填入 开通结果 信息，最多输入 1000 字符'"
                   value="${sheetLink.completionResult}"/>
        </td>
    </tr>

    <%}%>


    <%
        }
        if (taskName.equals("AffirmHumTask")) {
    %>
    <input type="hidden" name=operateType" id="operateType" value="<%=operateType %>"/>

    <%if (operateType.equals("110") || operateType.equals("110")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="HoldTask"/>


    <tr>

        <td class="label">
            <!-- 回复结果 -->
            <bean:message bundle="transprovincial" key="transprovincialLink.replyResult"/>*
        </td>
        <td>
            <input type="text" class="text" name="replyResult" id="replyResult"
                   alt="allowBlank:false,maxLength:1000,vtext:'请填入 回复结果 信息，最多输入 1000 字符'"
                   value="${sheetLink.replyResult}"/>
        </td>
    </tr>

    <%}%>

    <%if (operateType.equals("111") || operateType.equals("111")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="CompletionTask"/>


    <tr>

        <td class="label">
            <!-- 回复结果 -->
            <bean:message bundle="transprovincial" key="transprovincialLink.linkGroupComplete"/>*
        </td>
        <td>
            <input type="text" class="text" name="linkGroupComplete" id="linkGroupComplete"
                   alt="allowBlank:false,maxLength:1000,vtext:'请填入 回复结果 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkGroupComplete}"/>
        </td>
    </tr>

    <%}%>


    <%
        }
        if (taskName.equals("HoldTask")) {
    %>
    <input type="hidden" name=operateType" id="operateType" value="<%=operateType %>"/>

    <%if (operateType.equals("18")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="Over"/>
    <input type="hidden" name="hasNextTaskFlag" id="hasNextTaskFlag" value="true"/>
    <input type="hidden" name="status" id="status" value="1"/>

    <%}%>


    <%
        }
        if (!operateType.equals("61") && !operateType.equals("18") && !operateType.equals("4")) {
    %>
    <%if (operateType.equals("103")) { %>
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
        <td class="label">方案设计</td>
        <td colspan="3">
            <eoms:attachment name="sheetLink" property="nodeAccessories"
                             scope="request" idField="nodeAccessories" appCode="transprovincial"
                             alt="allowBlank:false"/>
        </td>
    </tr>
    <%} else if (operateType.equals("106")) { %>
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
        <td class="label">验收报告</td>
        <td colspan="3">
            <eoms:attachment name="sheetLink" property="nodeAccessories"
                             scope="request" idField="nodeAccessories" appCode="transprovincial"
                             alt="allowBlank:false"/>
        </td>
    </tr>
    <%} else { %>
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
                             scope="request" idField="nodeAccessories" appCode="transprovincial"/>
        </td>
    </tr>
    <%}%>
    <%}%>
    <%if (taskName.equals("Assignment") || taskName.equals("ProjectDesign") || taskName.equals("SubmitExamine") || taskName.equals("ProjectExamine") || taskName.equals("ProjectConstract") || taskName.equals("SubmibAcceptance") || taskName.equals("AcceptanceExamine") || taskName.equals("CompletionTask") || taskName.equals("AffirmHumTask") || taskName.equals("HoldTask")) {%>
    <input type="hidden" name="operateType" id="operateType" value="<%=operateType %>"/>
    <!--  <tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="remark" class="textarea max" id="remark" 
		        alt="width:'500px',maxLength:200,vtext:'最多输入100汉字'">${sheetLink.remark}</textarea>
		  </td>
		</tr> -->
    <%}%>
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


<% if (taskName.equals("Assignment")) { %>

<% if (operateType.equals("102")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:地市工建人
									 </span>
    </legend>
    <div class="x-form-item">

        <eoms:chooser id="Assignment0" type="role" roleId="2022" flowId="994" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>

    </div>
</fieldset>
<% } %>

<% }
    if (taskName.equals("ProjectDesign")) { %>


<% if (operateType.equals("103")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:本地市维护人
									 </span>
    </legend>
    <div class="x-form-item">

        <eoms:chooser id="ProjectDesign0" type="role" roleId="2023" flowId="994"
                      config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>

    </div>
</fieldset>
<% } %>

<% }
    if (taskName.equals("SubmitExamine")) { %>


<% if (operateType.equals("104")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:网管二级审核人
									 </span>
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="SubmitExamine0" type="role" roleId="2021" flowId="994"
                      config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>
    </div>
</fieldset>
<% } %>
<% if (operateType.equals("114")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:地市工建人
									 </span>
    </legend>
    <div class="x-form-item">

    </div>
</fieldset>
<% } %>

<% }
    if (taskName.equals("ProjectExamine")) { %>


<% if (operateType.equals("105")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:地市工建人
									 </span>
    </legend>
    <div class="x-form-item">

    </div>
</fieldset>
<% } %>
<% if (operateType.equals("112")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:地市维护人
									 </span>
    </legend>
    <div class="x-form-item">

    </div>
</fieldset>
<% } %>

<% }
    if (taskName.equals("ProjectConstract")) { %>


<% if (operateType.equals("106")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:地市维护人
									 </span>
    </legend>
    <div class="x-form-item">

    </div>
</fieldset>
<% } %>

<% }
    if (taskName.equals("SubmibAcceptance")) { %>


<% if (operateType.equals("107")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:网管二级审核人
									 </span>
    </legend>
    <div class="x-form-item">

    </div>
</fieldset>
<% } %>

<% }
    if (taskName.equals("AcceptanceExamine")) { %>


<% if (operateType.equals("108")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:网管二级审核人
									 </span>
    </legend>
    <div class="x-form-item">

    </div>
</fieldset>
<% } %>
<% if (operateType.equals("113")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:地市维护人
									 </span>
    </legend>
    <div class="x-form-item">

    </div>
</fieldset>
<% } %>

<% }
    if (taskName.equals("CompletionTask")) { %>


<% if (operateType.equals("109")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:网管一级审核人
									 </span>
    </legend>
    <div class="x-form-item">

    </div>
</fieldset>
<% } %>

<% }
    if (taskName.equals("AffirmHumTask")) { %>


<% if (operateType.equals("110")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:网管一级审核人
									 </span>
    </legend>
    <div class="x-form-item">

    </div>
</fieldset>
<% } %>
<% if (operateType.equals("111")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:网管二级审核人
									 </span>
    </legend>
    <div class="x-form-item">

    </div>
</fieldset>
<% } %>


<% } %>