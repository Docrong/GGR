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

<%@ include file="/WEB-INF/pages/wfworksheet/itsoftchange/baseinputlinkhtmlnew.jsp" %>
<br/>
<table class="formTable">
    <input type="hidden" id="tmpCompleteLimit" value=""
           alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'处理时限不能晚于工单完成时限'"/>
    <input type="hidden" name="${sheetPageName}beanId" value="iITSoftChangeMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.itsoftchange.model.ITSoftChangeMain"/>
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.itsoftchange.model.ITSoftChangeLink"/>
    <c:if test="${taskName != 'HoldTask'}">
        <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
    </c:if>

    <%
        if (taskName.equals("OperateTask")) {
            if (operateType.equals("91") || operateType.equals("11")) {
    %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TestTask"/>
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
        <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkDevContacter"/>*</td>
        <td colspan="3"><input type="text" class="text" name="${sheetPageName}linkDevContacter"
                               id="${sheetPageName}linkDevContacter"
                               value="${sheetLink.linkDevContacter}"
                               alt="allowBlank:false,,maxLength:50,vtext:'请填入开发人员及联系方式，最多输入25字'"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkMakerTestResult"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkMakerTestResult"
                      id="${sheetPageName}linkMakerTestResult"
                      alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入厂家测试结果，最多输入125字'">${sheetLink.linkMakerTestResult}</textarea>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkImpactElseDCL"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkImpactElseDCL"
                      id="${sheetPageName}linkImpactElseDCL"
                      alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入影响其他系统或模块说明，最多输入125字'">${sheetLink.linkImpactElseDCL}</textarea>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkIfNotifyInterfixSystem"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkIfNotifyInterfixSystem"
                      id="${sheetPageName}linkIfNotifyInterfixSystem"
                      alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入是否通知相关系统及沟通情况，最多输入125字'">${sheetLink.linkIfNotifyInterfixSystem}</textarea>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkCompleteDesc"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkCompleteDesc" id="${sheetPageName}linkCompleteDesc"
                      alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入完成说明，最多输入125字'">${sheetLink.linkCompleteDesc}</textarea>
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
        <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkTestGuide"/>*</td>
        <td colspan='3'>
            <eoms:attachment idList="" scope="request" idField="${sheetPageName}linkTestGuide" name="sheetLink"
                             property="linkTestGuide" appCode="itsoftchangesheet" alt="allowBlank:false"/>
        </td>
    </tr>
    <%
            }
        }
    %>


    <%
        if (taskName.equals("TestTask")) {
            if (operateType.equals("92") || operateType.equals("11")) {
    %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OnlineTask"/>
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
        <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkTestResult"/>*</td>
        <td cclass="content">
            <eoms:comboBox name="${sheetPageName}linkTestResult" id="${sheetPageName}linkTestResult"
                           initDicId="1011406" alt="allowBlank:false" styleClass="select-class"
                           onchange="ifTestPass(this.value);" defaultValue="${sheetLink.linkTestResult }"/>
        </td>
        <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkTestSatisfaction"/>*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}linkTestSatisfaction" id="${sheetPageName}linkTestSatisfaction"
                           initDicId="10303" alt="allowBlank:false" styleClass="select-class"
                           defaultValue="${sheetLink.linkTestSatisfaction }"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkTestDesc"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkTestDesc" id="${sheetPageName}linkTestDesc"
                      alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入测试说明，最多输入125字'">${sheetLink.linkTestDesc}</textarea>
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
        <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkTestNote"/>*</td>
        <td colspan='3'>
            <eoms:attachment idList="" scope="request" idField="${sheetPageName}linkTestNote" name="sheetLink"
                             property="linkTestNote" appCode="itsoftchangesheet" alt="allowBlank:false"/>
        </td>
    </tr>
    <%
            }
        }
    %>

    <%
        if (taskName.equals("OnlineTask")) {
            if (operateType.equals("93") || operateType.equals("11")) {
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
        <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkOnlineTime"/>*</td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}linkOnlineTime" readonly="readonly"
                   id="${sheetPageName}linkOnlineTime" value="${eoms:date2String(sheetLink.linkOnlineTime)}"
                   onclick="popUpCalendar(this, this)" alt="false"/>
        </td>
        <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkSoftEdition"/>*</td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}linkSoftEdition" id="${sheetPageName}linkSoftEdition"
                   value="${sheetLink.linkSoftEdition}" alt="allowBlank:false,,maxLength:100,vtext:'请填入软件版本，最多输入50字'"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkSoftEditionDCL"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkSoftEditionDCL"
                      id="${sheetPageName}linkSoftEditionDCL"
                      alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入软件版本说明，最多输入125字'">${sheetLink.linkSoftEditionDCL}</textarea>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkOnlineDesc"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkOnlineDesc" id="${sheetPageName}linkOnlineDesc"
                      alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入上线说明，最多输入125字'">${sheetLink.linkOnlineDesc}</textarea>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkUserNoteChangeDCL"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkUserNoteChangeDCL"
                      id="${sheetPageName}linkUserNoteChangeDCL"
                      alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入用户手册变更说明，最多输入125字'">${sheetLink.linkUserNoteChangeDCL}</textarea>
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
        <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkUserNoteDesc"/></td>
        <td colspan='3'>
            <eoms:attachment idList="" scope="request" idField="${sheetPageName}linkUserNoteDesc" name="sheetLink"
                             property="linkUserNoteDesc" appCode="itsoftchangesheet" alt="allowBlank:true"/>
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
        <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkAuditResult"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}linkAuditResult" id="${sheetPageName}linkAuditResult"
                           initDicId="1011407" alt="allowBlank:false" styleClass="select-class"
                           defaultValue="${sheetLink.linkAuditResult}"/>
        </td>

        <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkAnalysisCount"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}linkAnalysisCount"
                   id="${sheetPageName}linkAnalysisCount" value="${sheetLink.linkAnalysisCount}"
                   alt="allowBlank:false,maxLength:50,vtext:'请填入需求分析工作量，最多输入25字'"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkTestCount"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}linkTestCount" id="${sheetPageName}linkTestCount"
                   value="${sheetLink.linkTestCount}"
                   alt="allowBlank:false,maxLength:50,vtext:'请填入现场实施与测试工作量，最多输入25字'"/></td>
        <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.linkDevCount"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}linkDevCount" id="${sheetPageName}linkDevCount"
                   value="${sheetLink.linkDevCount}" alt="allowBlank:false,maxLength:50,vtext:'请填入研发工作量，最多输入25字'"/></td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}holdStatisfied"
                           id="${sheetPageName}holdStatisfied" defaultValue="${sheetMain.holdStatisfied}"
                           initDicId="10303" styleClass="select" alt="allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/>*</td>
        <td colspan="3">
            <textarea name="${sheetPageName}endResult" alt="allowBlank:false,maxLength:2000,vtext:'请最多输入1000字'"
                      id="${sheetPageName}endResult" class="textarea max">${sheetMain.endResult}</textarea>
        </td>
    </tr>


    <%} else if (operateType.equals("102")) {%>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OnlineTask"/>
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
    if (taskName.equals("OperateTask")) {
        if (operateType.equals("91")) {
%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="itsoftchange" key="role.applyer"/>

    </legend>

    <eoms:chooser id="sendRole" type="role" roleId="340" flowId="092" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',allowBlank:false,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>
</fieldset>
<%
        }
    }
%>

<%
    if (taskName.equals("TestTask")) {
        if (operateType.equals("92")) {
%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <span id="roleName"></span>
    </legend>
    <eoms:chooser id="sendRole1" type="role" roleId="341" flowId="092" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',allowBlank:false,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>

</fieldset>
<%
        }
    }
%>

<%
    if (taskName.equals("HoldTask")) {
        if (operateType.equals("102")) {
%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="itsoftchange" key="role.operategroup"/>
    </legend>
</fieldset>
<%
        }
    }
%>
<script type="text/javascript">
    function ifTestPass(input) {
        if ('<%=operateType%>' != '11') {
            if (input == "101140601") {
                chooser_sendRole1.enable();
                //测试通过
                $('${sheetPageName}phaseId').value = 'OnlineTask';
                $('${sheetPageName}operateType').value = '921';
                $('roleName').innerHTML = "IT需求实施组";
                document.forms[0].linkTestSatisfaction.disabled = false;
            } else if (input == "101140602") {
                //测试不通过
                chooser_sendRole1.disable();
                $('${sheetPageName}phaseId').value = 'OperateTask';
                $('${sheetPageName}operateType').value = '922';
                $('roleName').innerHTML = "IT需求实施组";
                document.forms[0].linkTestSatisfaction.disabled = true;
                document.forms[0].linkTestSatisfaction.value = "";
            } else {
                chooser_sendRole1.disable();
                $('${sheetPageName}phaseId').value = '';
                $('${sheetPageName}operateType').value = '';
                $('roleName').innerHTML = "";
                document.forms[0].linkTestSatisfaction.disabled = false;
                document.forms[0].linkTestSatisfaction.value = "";
            }
        }
    }

    var frm = document.forms[0];
    var temp = frm.linkTestResult ? frm.linkTestResult.value : '';
    if (temp != '') {
        ifTestPass(temp);
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
