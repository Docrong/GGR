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

<%@ include file="/WEB-INF/pages/wfworksheet/citycustom/baseinputlinkhtmlnew.jsp" %>
<br/>
<table class="formTable">
    <input type="hidden" id="tmpCompleteLimit" value=""/>
    <input type="hidden" name="linkBeanId" value="iCityCustomLinkManager"/>
    <input type="hidden" name="beanId" value="iCityCustomMainManager"/>
    <input type="hidden" name="mainClassName" value="com.boco.eoms.sheet.citycustom.model.CityCustomMain"/>
    <input type="hidden" name="linkClassName" value="com.boco.eoms.sheet.citycustom.model.CityCustomLink"/>
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


    <%if (taskName.equals("OrderExamine")) {%>
    <input type="hidden" name=operateType" id="operateType" value="<%=operateType %>"/>


    <%if (operateType.equals("102") || operateType.equals("102")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="NetOrderExamine"/>


    <tr>

        <td class="label">
            <!-- 审核情况 -->
            审核情况*
        </td>
        <td>
            <textarea class="textarea max" name="linkGroupComplete" id="linkGroupComplete"
                      alt="allowBlank:false,maxLength:2000,vtext:'请输入审核情况，多输入100汉字'">${sheetLink.linkGroupComplete}</textarea>
        </td>
    </tr>

    <%}%>
    <%if (operateType.equals("117") || operateType.equals("117")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="RejectTask"/>


    <tr>

        <td class="label">
            <!-- 审核情况 -->
            审核情况*
        </td>
        <td>
            <textarea class="textarea max" name="linkGroupComplete" id="linkGroupComplete"
                      alt="allowBlank:false,maxLength:2000,vtext:'请输入审核情况，多输入100汉字'">${sheetLink.linkGroupComplete}</textarea>
        </td>
    </tr>

    <%}%>


    <%
        }
        if (taskName.equals("NetOrderExamine")) {
    %>
    <input type="hidden" name=operateType" id="operateType" value="<%=operateType %>"/>

    <%if (operateType.equals("103") || operateType.equals("103")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="SchemeDesign"/>
    <input type="hidden" name="extendKey2" id="extendKey2" value="OpenComplete"/>


    <tr>

        <td class="label">
            <!-- 审核情况 -->
            审核情况*
        </td>
        <td>
            <textarea class="textarea max" name="linkGroupComplete" id="linkGroupComplete"
                      alt="allowBlank:false,maxLength:2000,vtext:'请输入审核情况，多输入100汉字'">${sheetLink.linkGroupComplete}</textarea>
        </td>
    </tr>

    <%}%>
    <%if (operateType.equals("118") || operateType.equals("118")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="OrderExamine"/>


    <tr>

        <td class="label">
            <!-- 审核情况 -->
            审核情况*
        </td>
        <td>
            <textarea class="textarea max" name="linkGroupComplete" id="linkGroupComplete"
                      alt="allowBlank:false,maxLength:2000,vtext:'请输入审核情况，多输入100汉字'">${sheetLink.linkGroupComplete}</textarea>
        </td>
    </tr>

    <%}%>


    <%
        }
        if (taskName.equals("SchemeDesign")) {
    %>
    <input type="hidden" name=operateType" id="operateType" value="<%=operateType %>"/>

    <%if (operateType.equals("104") || operateType.equals("104")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="SubmitExamine"/>


    <tr>

        <td class="label">
            <!-- 完成情况 -->
            <bean:message bundle="citycustom" key="cityCustomLink.linkGroupComplete"/>*
        </td>
        <td>
            <input type="text" class="text" name="linkGroupComplete" id="linkGroupComplete"
                   alt="allowBlank:false,maxLength:1000,vtext:'请填入 完成情况 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkGroupComplete}"/>
        </td>
    </tr>

    <%}%>


    <%
        }
        if (taskName.equals("SubmitExamine")) {
    %>
    <input type="hidden" name=operateType" id="operateType" value="<%=operateType %>"/>

    <%if (operateType.equals("105") || operateType.equals("105")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="ExamineScheme"/>


    <tr>

        <td class="label">
            <!-- 审核情况 -->
            审核情况*
        </td>
        <td>
            <textarea class="textarea max" name="linkGroupComplete" id="linkGroupComplete"
                      alt="allowBlank:false,maxLength:2000,vtext:'请输入审核情况，多输入100汉字'">${sheetLink.linkGroupComplete}</textarea>
        </td>
    </tr>

    <%}%>
    <%if (operateType.equals("115") || operateType.equals("115")) { %>
    <input type="hidden" name="operateType" id="operateType" value="4"/>
    <input type="hidden" name="phaseId" id="phaseId" value="SchemeDesign"/>


    <tr>

        <td class="label">
            <!-- 完成情况 -->
            <bean:message bundle="citycustom" key="cityCustomLink.linkGroupComplete"/>*
        </td>
        <td>
            <input type="text" class="text" name="linkGroupComplete" id="linkGroupComplete"
                   alt="allowBlank:false,maxLength:1000,vtext:'请填入 完成情况 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkGroupComplete}"/>
        </td>
    </tr>

    <%}%>


    <%
        }
        if (taskName.equals("ExamineScheme")) {
    %>
    <input type="hidden" name=operateType" id="operateType" value="<%=operateType %>"/>

    <%if (operateType.equals("106") || operateType.equals("106")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="Construction"/>


    <tr>

        <td class="label">
            <!-- 审核情况 -->
            审核情况*
        </td>
        <td>
            <textarea class="textarea max" name="linkGroupComplete" id="linkGroupComplete"
                      alt="allowBlank:false,maxLength:2000,vtext:'请输入审核情况，多输入100汉字'">${sheetLink.linkGroupComplete}</textarea>
        </td>
    </tr>

    <%}%>
    <%if (operateType.equals("113") || operateType.equals("113")) { %>
    <input type="hidden" name="operateType" id="operateType" value="4"/>
    <input type="hidden" name="phaseId" id="phaseId" value="SubmitExamine"/>


    <tr>

        <td class="label">
            <!-- 完成情况 -->
            <bean:message bundle="citycustom" key="cityCustomLink.linkGroupComplete"/>*
        </td>
        <td>
            <input type="text" class="text" name="linkGroupComplete" id="linkGroupComplete"
                   alt="allowBlank:false,maxLength:1000,vtext:'请填入 完成情况 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkGroupComplete}"/>
        </td>
    </tr>

    <%}%>


    <%
        }
        if (taskName.equals("Construction")) {
    %>
    <input type="hidden" name=operateType" id="operateType" value="<%=operateType %>"/>

    <%if (operateType.equals("107") || operateType.equals("107")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="SubmitCheck"/>


    <tr>

        <td class="label">
            <!-- 完成情况 -->
            <bean:message bundle="citycustom" key="cityCustomLink.linkGroupComplete"/>*
        </td>
        <td>
            <textarea class="textarea max" name="linkGroupComplete" id="linkGroupComplete"
                      alt="allowBlank:false,maxLength:2000,vtext:'请输入审核情况，多输入100汉字'">${sheetLink.linkGroupComplete}</textarea>
        </td>
    </tr>

    <%}%>


    <%
        }
        if (taskName.equals("SubmitCheck")) {
    %>
    <input type="hidden" name=operateType" id="operateType" value="<%=operateType %>"/>

    <%if (operateType.equals("108") || operateType.equals("108")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="CheckExamine"/>


    <tr>

        <td class="label">
            <!-- 验收情况 -->
            验收情况*
        </td>
        <td>
            <input type="text" class="text" name="linkGroupComplete" id="linkGroupComplete"
                   alt="allowBlank:false,maxLength:1000,vtext:'请填入 完成情况 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkGroupComplete}"/>
        </td>
    </tr>

    <%}%>
    <%if (operateType.equals("116") || operateType.equals("116")) { %>
    <input type="hidden" name="operateType" id="operateType" value="4"/>
    <input type="hidden" name="phaseId" id="phaseId" value="Construction"/>


    <tr>

        <td class="label">
            <!-- 完成情况 -->
            <bean:message bundle="citycustom" key="cityCustomLink.linkGroupComplete"/>*
        </td>
        <td>
            <input type="text" class="text" name="linkGroupComplete" id="linkGroupComplete"
                   alt="allowBlank:false,maxLength:1000,vtext:'请填入 完成情况 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkGroupComplete}"/>
        </td>
    </tr>

    <%}%>


    <%
        }
        if (taskName.equals("CheckExamine")) {
    %>
    <input type="hidden" name=operateType" id="operateType" value="<%=operateType %>"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>

    <%if (operateType.equals("109") || operateType.equals("109")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="OpenComplete"/>


    <tr>

        <td class="label">
            <!-- 审核情况 -->
            审核情况*
        </td>
        <td>
            <textarea class="textarea max" name="linkGroupComplete" id="linkGroupComplete"
                      alt="allowBlank:false,maxLength:2000,vtext:'请输入审核情况，多输入100汉字'">${sheetLink.linkGroupComplete}</textarea>
        </td>
    </tr>

    <%}%>
    <%if (operateType.equals("114") || operateType.equals("114")) { %>
    <input type="hidden" name="operateType" id="operateType" value="4"/>
    <input type="hidden" name="phaseId" id="phaseId" value="SubmitCheck"/>


    <tr>

        <td class="label">
            <!-- 完成情况 -->
            <bean:message bundle="citycustom" key="cityCustomLink.linkGroupComplete"/>*
        </td>
        <td>
            <input type="text" class="text" name="linkGroupComplete" id="linkGroupComplete"
                   alt="allowBlank:false,maxLength:1000,vtext:'请填入 完成情况 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkGroupComplete}"/>
        </td>
    </tr>

    <%}%>


    <%
        }
        if (taskName.equals("OpenComplete")) {
    %>
    <input type="hidden" name=operateType" id="operateType" value="<%=operateType %>"/>

    <%if (operateType.equals("110") || operateType.equals("110")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="AffirmHumTask"/>


    <tr>

        <td class="label">
            <!-- 完成情况 -->
            <bean:message bundle="citycustom" key="cityCustomLink.linkGroupComplete"/>*
        </td>
        <td>
            <input type="text" class="text" name="linkGroupComplete" id="linkGroupComplete"
                   alt="allowBlank:false,maxLength:1000,vtext:'请填入 完成情况 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkGroupComplete}"/>
        </td>
    </tr>

    <%}%>


    <%
        }
        if (taskName.equals("AffirmHumTask")) {
    %>
    <input type="hidden" name=operateType" id="operateType" value="<%=operateType %>"/>

    <%if (operateType.equals("111") || operateType.equals("111")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="HoldTask"/>


    <tr>

        <td class="label">
            <!-- 完成情况 -->
            <bean:message bundle="citycustom" key="cityCustomLink.linkGroupComplete"/>*
        </td>
        <td>
            <input type="text" class="text" name="linkGroupComplete" id="linkGroupComplete"
                   alt="allowBlank:false,maxLength:1000,vtext:'请填入 完成情况 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkGroupComplete}"/>
        </td>
    </tr>

    <%}%>

    <%if (operateType.equals("112") || operateType.equals("112")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="OpenComplete"/>


    <tr>

        <td class="label">
            <!-- 完成情况 -->
            <bean:message bundle="citycustom" key="cityCustomLink.linkGroupComplete"/>*
        </td>
        <td>
            <input type="text" class="text" name="linkGroupComplete" id="linkGroupComplete"
                   alt="allowBlank:false,maxLength:1000,vtext:'请填入 完成情况 信息，最多输入 1000 字符'"
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
    <%if (operateType.equals("104")) { %>
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
                             scope="request" idField="nodeAccessories" appCode="citycustom" alt="allowBlank:false"/>
        </td>
    </tr>
    <%} else if (operateType.equals("107")) { %>
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
                             scope="request" idField="nodeAccessories" appCode="citycustom" alt="allowBlank:false"/>
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
                             scope="request" idField="nodeAccessories" appCode="citycustom"/>
        </td>
    </tr>
    <%}%>
    <%}%>
    <%if (taskName.equals("OrderExamine") || taskName.equals("NetOrderExamine") || taskName.equals("SchemeDesign") || taskName.equals("SubmitExamine") || taskName.equals("ExamineScheme") || taskName.equals("Construction") || taskName.equals("SubmitCheck") || taskName.equals("CheckExamine") || taskName.equals("OpenComplete") || taskName.equals("AffirmHumTask") || taskName.equals("HoldTask")) {%>
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


<% if (taskName.equals("OrderExamine")) { %>

<% if (operateType.equals("102")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:网管一级审核人
									 </span>
    </legend>
    <div class="x-form-item">

        <eoms:chooser id="OrderExamine0" type="role" roleId="1922" flowId="614"
                      config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>

    </div>
</fieldset>
<% } %>
<% if (operateType.equals("117")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:建单人
									 </span>
    </legend>
    <div class="x-form-item">

    </div>
</fieldset>
<% } %>

<% }
    if (taskName.equals("NetOrderExamine")) { %>


<% if (operateType.equals("103")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:地市工建人
									 </span>
    </legend>
    <div class="x-form-item">

        <eoms:chooser id="NetOrderExamine0" type="role" roleId="1923" flowId="614"
                      config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>

    </div>
</fieldset>
<% } %>
<% if (operateType.equals("118")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:省集客审核人
									 </span>
    </legend>
    <div class="x-form-item">

    </div>
</fieldset>
<% } %>

<% }
    if (taskName.equals("SchemeDesign")) { %>


<% if (operateType.equals("104")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:本地市维护人
									 </span>
    </legend>
    <div class="x-form-item">

        <eoms:chooser id="SchemeDesign0" type="role" roleId="1924" flowId="614"
                      config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>

    </div>
</fieldset>
<% } %>

<% }
    if (taskName.equals("SubmitExamine")) { %>


<% if (operateType.equals("105")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:网管二级审核人
									 </span>
    </legend>
    <div class="x-form-item">

        <eoms:chooser id="SubmitExamine0" type="role" roleId="1925" flowId="614"
                      config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>

    </div>
</fieldset>
<% } %>
<% if (operateType.equals("115")) { %>
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
    if (taskName.equals("ExamineScheme")) { %>


<% if (operateType.equals("106")) { %>
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
    if (taskName.equals("Construction")) { %>


<% if (operateType.equals("107")) { %>
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
    if (taskName.equals("SubmitCheck")) { %>


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
<% if (operateType.equals("116")) { %>
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
    if (taskName.equals("CheckExamine")) { %>


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
<% if (operateType.equals("114")) { %>
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
    if (taskName.equals("OpenComplete")) { %>


<% if (operateType.equals("110")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:地市集客建单人
									 </span>
    </legend>
    <div class="x-form-item">

    </div>
</fieldset>
<% } %>

<% }
    if (taskName.equals("AffirmHumTask")) { %>


<% if (operateType.equals("111")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:地市集客建单人
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
        <span id="roleName">:网管一级审核人
									 </span>
    </legend>
    <div class="x-form-item">

    </div>
</fieldset>
<% } %>


<% } %>