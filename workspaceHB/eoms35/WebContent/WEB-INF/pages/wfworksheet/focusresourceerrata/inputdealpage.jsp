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

<%@ include file="/WEB-INF/pages/wfworksheet/focusresourceerrata/baseinputlinkhtmlnew.jsp" %>
<br/>
<table class="formTable">
    <input type="hidden" id="tmpCompleteLimit" value=""/>
    <input type="hidden" name="linkBeanId" value="iFocusResourceErrataLinkManager"/>
    <input type="hidden" name="beanId" value="iFocusResourceErrataMainManager"/>
    <input type="hidden" name="mainClassName"
           value="com.boco.eoms.sheet.focusresourceerrata.model.FocusResourceErrataMain"/>
    <input type="hidden" name="linkClassName"
           value="com.boco.eoms.sheet.focusresourceerrata.model.FocusResourceErrataLink"/>
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
            备注说明*
        </td>
        <td colspan="3">
					<textarea name="remark" class="textarea max" id="remark"
                              alt="allowBlank:false,width:500,vtext:'请最多输入1000字'"
                              alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%} %>


    <%if (taskName.equals("MonitoringDepart")) {%>
    <input type="hidden" name="operateType" id="operateType" value="<%=operateType %>"/>


    <%if (operateType.equals("101") || operateType.equals("11")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="TransmissionNet"/>


    <tr>

        <td class="label">
            <!-- 电路核查状态 -->
            <bean:message bundle="focusresourceerrata" key="focusResourceErrataLink.linkCheckState"/>
        </td>
        <td>
            <input type="text" class="text" name="linkCheckState" id="linkCheckState"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 电路核查状态 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkCheckState}"/>
        </td>
    </tr>


    <%}%>

    <%if (operateType.equals("102") || operateType.equals("11")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="HoldTask"/>


    <tr>

        <td class="label">
            <!-- 自动归档 -->
            <bean:message bundle="focusresourceerrata" key="focusResourceErrataLink.linkIsMysel"/>
        </td>
        <td>
            <input type="text" class="text" name="linkIsMysel" id="linkIsMysel"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 自动归档 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkIsMysel}"/>
        </td>
    </tr>

    <%}%>

    <%if (operateType.equals("103") || operateType.equals("11")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="TransmissionNet"/>


    <tr>

        <td class="label">
            <!-- 电路核查状态 -->
            <bean:message bundle="focusresourceerrata" key="focusResourceErrataLink.linkCheckState"/>
        </td>
        <td>
            <input type="text" class="text" name="linkCheckState" id="linkCheckState"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 电路核查状态 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkCheckState}"/>
        </td>
    </tr>

    <%}%>


    <%
        }
        if (taskName.equals("TransmissionNet")) {
    %>
    <input type="hidden" name="operateType" id="operateType" value="<%=operateType %>"/>

    <%if (operateType.equals("104") || operateType.equals("11")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="CitieErrata"/>


    <tr>

        <td class="label">
            <!-- 审批意见 -->
            <bean:message bundle="focusresourceerrata" key="focusResourceErrataLink.linkApprovalOpinion"/>
        </td>
        <td>
				<textarea class="textarea max" name="linkApprovalOpinion"
                          id="linkApprovalOpinion"
                          alt="allowBlank:true,maxLength:1000,vtext:'请填入 审批意见 信息，最多输入 1000 字符'">${sheetLink.linkApprovalOpinion}</textarea>

        </td>
    </tr>

    <%}%>

    <%if (operateType.equals("105") || operateType.equals("11")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="MonitoringDepart"/>


    <tr>

        <td class="label">
            <!-- 电路核查状态 -->
            <bean:message bundle="focusresourceerrata" key="focusResourceErrataLink.linkCheckState"/>
        </td>
        <td colspan="3">
            <input type="text" class="text" name="linkCheckState" id="linkCheckState"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 电路核查状态 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkCheckState}"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 审批意见 -->
            <bean:message bundle="focusresourceerrata" key="focusResourceErrataLink.linkApprovalOpinion"/>
        </td>
        <td colspan="3">
				<textarea class="textarea max" name="linkApprovalOpinion"
                          id="linkApprovalOpinion"
                          alt="allowBlank:true,maxLength:1000,vtext:'请填入 审批意见 信息，最多输入 1000 字符'">${sheetLink.linkApprovalOpinion}</textarea>

        </td>
    </tr>

    <%}%>


    <%
        }
        if (taskName.equals("CitieErrata")) {
    %>
    <input type="hidden" name="operateType" id="operateType" value="<%=operateType %>"/>

    <%if (operateType.equals("106") || operateType.equals("11")) { %>
    <input type="hidden" name="phaseId" id="phaseId" value="MonitoringDepart"/>


    <tr>

        <td class="label">
            <!-- 执行结果 -->
            <bean:message bundle="focusresourceerrata" key="focusResourceErrataLink.linkResult"/>
        </td>
        <td colspan="3">
            <input type="text" class="text" name="linkResult" id="linkResult"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 执行结果 信息，最多输入 1000 字符'"
                   value="${sheetLink.linkResult}"/>
        </td>

    </tr>

    <tr>
        <td class="label">
            <!-- 备注 -->
            <bean:message bundle="focusresourceerrata" key="focusResourceErrataLink.linkRemark"/>
        </td>
        <td colspan="3">
				<textarea class="textarea max" name="linkRemark"
                          id="linkRemark"
                          alt="allowBlank:true,maxLength:1000,vtext:'请填入 备注 信息，最多输入 1000 字符'">${sheetLink.linkRemark}</textarea>

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
                             scope="request" idField="nodeAccessories" appCode="focusresourceerrata"/>
        </td>
    </tr>
    <%}%>
    <%if (taskName.equals("MonitoringDepart") || taskName.equals("TransmissionNet") || taskName.equals("CitieErrata") || taskName.equals("HoldTask")) {%>
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


<% if (taskName.equals("MonitoringDepart")) { %>

<% if (operateType.equals("101")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:传输网部
									 </span>
    </legend>
    <div class="x-form-item">

        <eoms:chooser id="MonitoringDepart0" type="role" roleId="1003" flowId="100"
                      config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>

    </div>
</fieldset>
<% } %>
<% if (operateType.equals("102")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:申请人
									 </span>
    </legend>
    <div class="x-form-item">

        <eoms:chooser id="MonitoringDepart1" type="role" roleId="1001" flowId="100"
                      config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>

    </div>
</fieldset>
<% } %>
<% if (operateType.equals("103")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:传输网部
									 </span>
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="MonitoringDepart3" type="role" roleId="1003" flowId="100"
                      config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>
    </div>
</fieldset>
<% } %>

<% }
    if (taskName.equals("TransmissionNet")) { %>


<% if (operateType.equals("104")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:地市
									 </span>
    </legend>
    <div class="x-form-item">

        <eoms:chooser id="TransmissionNet0" type="role" roleId="1004" flowId="100"
                      config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>

    </div>
</fieldset>
<% } %>
<% if (operateType.equals("105")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:监控部
									 </span>
    </legend>
    <div class="x-form-item">

        <eoms:chooser id="TransmissionNet1" type="role" roleId="1002" flowId="100"
                      config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>

    </div>
</fieldset>
<% } %>

<% }
    if (taskName.equals("CitieErrata")) { %>


<% if (operateType.equals("106")) { %>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
        <span id="roleName">:监控部
									 </span>
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="CitieErrata0" type="role" roleId="1002" flowId="100"
                      config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>
    </div>
</fieldset>
<% } %>


<% } %>