<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
    String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId"));
    String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId"));
    System.out.println("=====taskName======" + taskName);
    String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
    System.out.println("=====operateType======" + operateType);
    String ifInvoke = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifInvoke"));
    System.out.println("=====ifInvoke======" + ifInvoke);
    String roleId = "";
    String roleName = "";
%>
<script type="text/javascript">
    function openwin(flag) {
        var url;

        if (flag == "101130401") {
            $('${sheetPageName}phaseId').value = 'callProcess';
            url = "${app}/sheet/circuitdispatch/circuitdispatch.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iSecurityDealMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}";
        } else if (flag == "101130402") {
            $('${sheetPageName}phaseId').value = 'callProcess';

            url = "${app}/sheet/softchange/softchange.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iSecurityDealMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}";
        } else if (flag == "101130403") {
            $('${sheetPageName}phaseId').value = 'callProcess';
            url = "${app}/sheet/netdata/netdata.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iSecurityDealMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}";
        } else if (flag == "101130404") {
            $('${sheetPageName}phaseId').value = 'callProcess';

            url = "${app}/sheet/netchange/netchange.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iSecurityDealMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}";
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
<%@ include file="/WEB-INF/pages/wfworksheet/securitydeal/baseinputlinkhtmlnew.jsp" %>
<br/>
<table class="formTable">
    <%if (!operateType.equals("61")) {%>
    <caption><bean:message bundle="securitydeal" key="securitydeal.header"/></caption>
    <%} %>
    <input type="hidden" name="${sheetPageName}toDeptId" value="${sheetMain.toDeptId}"/>
    <input type="hidden" name="${sheetPageName}mainNetSortOne" value="${sheetMain.mainNetSortOne}"/>
    <input type="hidden" name="${sheetPageName}mainNetSortTwo" value="${sheetMain.mainNetSortTwo}"/>
    <input type="hidden" name="${sheetPageName}mainNetSortThree" value="${sheetMain.mainNetSortThree}"/>
    <input type="hidden" id="tmpCompleteLimit" value=""
           alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'处理时限不能晚于工单完成时限'"/>
    <input type="hidden" name="${sheetPageName}beanId" value="iSecurityDealMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.securitydeal.model.SecurityDealMain"/>
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.securitydeal.model.SecurityDealLink"/>
    <c:if test="${taskName != 'HoldTask' }">
        <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateUserId}"/>
    </c:if>
    <%
        if (taskName.equals("MakeTask")) {
            if (operateType.equals("90") || operateType.equals("11")) {
    %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AuditTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="90"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>

    <tr>
        <td class="label"><bean:message bundle="securitydeal" key="securitydeal.linkSecurityInproveAdvice"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkSecurityInproveAdvice"
                      value="${sheetLink.linkSecurityInproveAdvice}" id="${sheetPageName}linkSecurityInproveAdvice"
                      alt="allowBlank:false,maxLength:500,vtext:'请填入信息，最多输入250字'">${sheetLink.linkSecurityInproveAdvice}</textarea>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="securitydeal" key="securitydeal.linkSecurityInproveProgram"/></td>
        <td colspan='3'>
            <eoms:attachment name="sheetLink" property="nodeAccessories"
                             scope="request" idField="${sheetPageName}nodeAccessories" appCode="securitydeal"
                             alt="allowBlank:true"/>
        </td>
    </tr>
    <%
            }
        }
    %>
    <%
        if (taskName.equals("AuditTask")) {
            if (operateType.equals("91")) {
    %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="PerformTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>"/>

    <tr>
        <td class="label"><bean:message bundle="securitydeal" key="securitydeal.linkAuditViews"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkAuditViews" id="${sheetPageName}linkAuditViews"
                           initDicId="1011301" alt="allowBlank:false" styleClass="select-class"
                           defaultValue="${sheetLink.linkAuditViews}" onchange="ifAuditPass(this.value,'AuditTask')"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="securitydeal" key="securitydeal.linkAuditResult"/>*</td>
        <td colspan='3'>
            <textarea class="textarea max" name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult"
                      alt="allowBlank:false,maxLength:500,vtext:'请填入信息，最多输入250字'">${sheetLink.linkAuditResult}</textarea>
        </td>
    </tr>

    <%} else if (operateType.equals("55")) {%>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>"/>

    <tr>
        <td class="label"><bean:message bundle="securitydeal" key="securitydeal.linkAuditViews"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkAuditViews" id="${sheetPageName}linkAuditViews"
                           initDicId="1011301" alt="allowBlank:false" styleClass="select-class"
                           defaultValue="${sheetLink.linkAuditViews}"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="securitydeal" key="securitydeal.linkAuditResult"/>*</td>
        <td colspan='3'>
            <textarea class="textarea max" name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult"
                      alt="allowBlank:false,maxLength:500,vtext:'请填入信息，最多输入250字'">${sheetLink.linkAuditResult}</textarea>
        </td>
    </tr>

    <%
            }
        }
    %>
    <%if (taskName.equals("PerformTask") && (operateType.equals("92") || operateType.equals("11"))) {%>

    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="callProcess"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>

    <tr>
        <td class="label"><bean:message bundle="securitydeal" key="securitydeal.linkPerformResult"/>*</td>
        <td colspan='3'>
            <textarea class="textarea max" name="${sheetPageName}linkPerformResult"
                      id="${sheetPageName}linkPerformResult"
                      alt="allowBlank:false,maxLength:500,vtext:'请填入信息，最多输入250字'">${sheetLink.linkPerformResult}</textarea>

        </td>
    </tr>

    <%if (operateType.equals("92")) { %>
    <tr>
        <!-- 是否启动网络变更配置工单 -->
        <td class="label"><bean:message bundle="securitydeal" key="securitydeal.linkIfStartChangeProcess"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkIfStartChangeProcess" id="${sheetPageName}linkIfStartChangeProcess"
                           initDicId="1011302" alt="allowBlank:false" styleClass="select-class"
                           defaultValue="${sheetLink.linkIfStartChangeProcess}" onchange="popOtherFlow(this.value);"/>
            <html:button styleClass="btn" style="display:none" property="method.save" styleId="startCircuit"
                         onclick="openwin('101130401')">电路调度工单</html:button>
            <html:button styleClass="btn" style="display:none" property="method.save" styleId="startSoftChange"
                         onclick="openwin('101130402')">软件变更工单</html:button>
            <html:button styleClass="btn" style="display:none" property="method.save" styleId="startNetChange"
                         onclick="openwin('101130404')">网络综合调整工单</html:button>
            <html:button styleClass="btn" style="display:none" property="method.save" styleId="startNetData"
                         onclick="openwin('101130403')">网络数据管理工单</html:button>
        </td>
    </tr>

    <%} else if (operateType.equals("11")) {%>
    <!-- 分派说明 -->
    <input type="hidden" name="${sheetPageName}operateType" value="11"/>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
				           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                                     alt="allowBlank:false,width:500" alt="width:'500px'">${sheetlink.remark}</textarea>
        </td>
    </tr>
    <%} %>

    <%} %>
    <%if (taskName.equals("HoldTask")) {%>
    <%if (operateType.equals("18") || operateType.equals("11")) { %>

    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="over"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18"/>
    <input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}holdStatisfied"
                           id="${sheetPageName}holdStatisfied"
                           defaultValue="${sheetMain.holdStatisfied != 0 ? sheetMain.holdStatisfied : 1030301}"
                           initDicId="10303" styleClass="select"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/>*</td>
        <td colspan="3">
            <textarea name="${sheetPageName}endResult" alt="allowBlank:false,maxLength:500,vtext:'请填入信息，最多输入250字'"
                      id="${sheetPageName}endResult" class="textarea max">${sheetMain.endResult}</textarea>
        </td>
    </tr>
    <%
            }
        }
    %>
    <!--流程中的字段域 结束-->
    <!-- 公共功能，抄送和确认受理 -->
    <!-- 驳回到上一级 -->
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
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="reject"/>
        </c:when>
        <c:when test="${fPreTaskName == 'DraftTask'}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="reject"/>
        </c:when>
        <c:otherwise>
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}"/>
        </c:otherwise>
    </c:choose>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="link.linkRejectCause"/>*
        </td>
        <td colspan="3">
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="allowBlank:false,maxLength:500,width:500,vtext:'请填入信息，最多输入250字'">${sheetLink.remark}</textarea>
        </td>
    </tr>

    <%} %>
    <!-- 抄送-->
    <% if (taskName.equals("cc")) {%>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15"/>
            <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                      alt="allowBlank:false,maxLength:500,width:500,vtext:'请填入信息，最多输入250字'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%} %>
    <!-- 确认受理 -->
    <%if (operateType.equals("61")) { %>
    <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer"
           value="${fOperateroleid}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader"
           value="${ftaskOwner}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType"
           value="${fOperateroleidType}"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>

    <%}%>
</table>
<!-- 公共功能，抄送和确认受理 结束 -->
<!-- 各个环节中的选择角色 -->
<br/>
<!-- 方案制定 -->
<%if (taskName.equals("MakeTask") && operateType.equals("90")) {%>
<fieldset id="link10">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="securitydeal" key="role.secmanager"/>
    </legend>
    <eoms:chooser id="sendRole" type="role" roleId="327" flowId="073" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',allowBlank:true,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}subAuditPerformer',childType:'user,subrole',limit:'none',text:'会审'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>
</fieldset>
<%} %>
<!-- 方案审核 -->
<%if (taskName.equals("AuditTask") && (operateType.equals("91") || operateType.equals("55"))) {%>
<fieldset id="link10">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <span id="roleName"></span>
    </legend>
    <eoms:chooser id="sendRole" type="role" roleId="317" flowId="073" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',allowBlank:true,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>
</fieldset>
<%} %>
<!-- 实施 -->
<%if (taskName.equals("PerformTask") && operateType.equals("92")) {%>
<fieldset id="link10">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="securitydeal" key="role.secmanager"/>
    </legend>
    <eoms:chooser id="sendRole" type="role" roleId="327" flowId="073" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',allowBlank:true,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>
</fieldset>
<%} %>
<br/>

<div ID="idSpecia2"></div>

<script type="text/javascript">
    var v1 = eoms.form;
    var operateType = "${operateType}";

    function ifAuditPass(input, task) {
        if (operateType != "55") {
            if (input == "101130101" && task == "AuditTask") {
                chooser_sendRole.enable();
                //审核通过到实施
                $('${sheetPageName}phaseId').value = 'PerformTask';
                $('roleName').innerHTML = "安全维护组";
                $('${sheetPageName}operateType').value = '911';

            } else if (input == "101130102" && task == "AuditTask") {
                //审核不通过到方案制定
                chooser_sendRole.enable();
                $('${sheetPageName}phaseId').value = 'MakeTask';
                $('roleName').innerHTML = "安全维护组";
                $('${sheetPageName}operateType').value = '912';
            } else {
                chooser_sendRole.enable();
                $('${sheetPageName}phaseId').value = '';
                $('roleName').innerHTML = "";
                $('${sheetPageName}operateType').value = '';
            }
        }
    }

    if ("${taskName}" == "AuditTask" && '<%=operateType%>' != '61') {
        ifAuditPass('${sheetLink.linkAuditViews}', 'AuditTask');
    }

    function popOtherFlow(value) {
        if (value == '101130201') {
            $('${sheetPageName}operateType').value = '111';
            document.getElementById('startCircuit').style.display = '';
            document.getElementById('startSoftChange').style.display = '';
            document.getElementById('startNetData').style.display = '';
            document.getElementById('startNetChange').style.display = '';

        } else if (value == '101130202') {
            if ("${ifInvoke}" == "no") {
                alert("任务正在等待回调中!请选择'是'选项");
                $('${sheetPageName}operateType').value = '111';
                document.all.${sheetPageName}linkIfStartChangeProcess.value = "101130201"
                // document.forms[0].linkIfStartChangeProcess.unselectable="on";
            } else {
                $('${sheetPageName}operateType').value = '92';
                $('${sheetPageName}phaseId').value = 'HoldTask'
                document.getElementById('startCircuit').style.display = 'none';
                document.getElementById('startSoftChange').style.display = 'none';
                document.getElementById('startNetData').style.display = 'none';
                document.getElementById('startNetChange').style.display = 'none';
            }
        }
    }

    if ("${taskName}" == "PerformTask" && '<%=operateType%>' != '61') {
        var frm = document.forms[0];
        if ("${ifInvoke}" == "no") {
            document.forms[0].linkIfStartChangeProcess.value = "101130201";
        }
        var temp = frm.linkIfStartChangeProcess ? frm.linkIfStartChangeProcess.value : '';
        popOtherFlow(temp);
    }

</script>
  
   