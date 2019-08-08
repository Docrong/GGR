<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="com.boco.eoms.sheet.netoptimize.model.NetOptimizeLink" %>
<%
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
    String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId"));
    String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId"));
    System.out.println("=====taskName=====" + taskName);
    String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
    System.out.println("=====operateType======" + operateType);


    String ifInvoke = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifInvoke"));
    NetOptimizeLink link = (NetOptimizeLink) request.getAttribute("preLink");
%>

<%@ include file="/WEB-INF/pages/wfworksheet/netOptimize/baseinputlinkhtmlnew.jsp" %>
<input type="hidden" id="tmpCompleteLimit" value=""
       alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'处理时限不能晚于工单完成时限'"/>
<br/>
<input type="hidden" name="${sheetPageName}processTemplateName" id="${sheetPageName}processTemplateName"
       value="NetOptimizeProcess"/>
<input type="hidden" name="${sheetPageName}operateName" id="${sheetPageName}operateName" value="nonFlowOperate"/>
<input type="hidden" name="${sheetPageName}beanId" value="iNetOptimizeMainManager"/>
<input type="hidden" name="${sheetPageName}mainClassName"
       value="com.boco.eoms.sheet.netoptimize.model.NetOptimizeMain"/> <!--main表Model对象类名-->
<input type="hidden" name="${sheetPageName}linkClassName"
       value="com.boco.eoms.sheet.netoptimize.model.NetOptimizeLink"/> <!--link表Model对象类名-->
<input type="hidden" name="${sheetPageName}toDeptId" value="${sheetMain.toDeptId}"/>
<input type="hidden" name="${sheetPageName}mainIsSuccess" id="${sheetPageName}mainIsSuccess" value=""/>
<input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
<table class="formTable">

    <%
        if (taskName.equals("AcceptTask") && (operateType.equals("80") || operateType.equals("11"))) {

    %>

    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteTask"/>

    <tr>
        <td class="label">
            <bean:message bundle="netOptimize" key="netOptimize.linkIsAccept"/>*
        </td>
        <td class="content max" colspan=3>
            <eoms:comboBox name="${sheetPageName}linkIsAccept" id="${sheetPageName}linkIsAccept" alt="allowBlank:false"
                           initDicId="1011801" onchange="AcceptChange(this.value);"/>
        </td>
    </tr>
    <tr>
        <td class="label">
            <bean:message bundle="netOptimize" key="netOptimize.linkTestResult"/>*
        </td>
        <td class="content max" colspan=3>
            <textarea name="linkTestResult" id="linkTestResult" class="textarea max"
                      alt="allowBlank:false,maxLength:200,vtext:'请输入分析/测试结果，最大长度为200个汉字'">${sheetLink.linkTestResult}</textarea>
        </td>
    </tr>
    <tr>
            <%}%>

            <%if(taskName.equals("ProjectCreateTask") && (operateType.equals("2")||operateType.equals("11"))){ %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType%>"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AuditTask"/>

    <tr>
        <td class="label"><bean:message bundle="netOptimize" key="netOptimize.linkProjectExplain"/>*</td>
        <td class="content max" colspan="3">
				        <textarea name="${sheetPageName}linkProjectExplain" class="textarea max"
                                  id="${sheetPageName}linkProjectExplain"
                                  alt="allowBlank:false,maxLength:200,vtext:'请输入网优方案说明，最大长度为200个汉字'">${sheetLink.linkProjectExplain}</textarea>
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
        <td class="label"><bean:message bundle="netOptimize" key="netOptimize.linkProjectAcc"/>*</td>
        <td class="content max" colspan="3">
            <eoms:attachment name="sheetLink" property="linkProjectAcc"
                             scope="request" idField="${sheetPageName}linkProjectAcc" alt="allowBlank:false"
                             appCode="netOptimize"/>
        </td>
    </tr>

    <%} %>


    <%if (taskName.equals("AuditTask")) {%>
    <%if (operateType.equals("56") || operateType.equals("55")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExcuteTask"/>
    <tr>
        <td class="label">
            <bean:message bundle="netOptimize" key="netOptimize.linkAuditOpinion"/>*
        </td>
        <td class="content max" colspan=3>
            <eoms:comboBox name="${sheetPageName}linkAuditOpinion" id="${sheetPageName}linkAuditOpinion"
                           alt="allowBlank:false" defaultValue="${sheetLink.linkAuditOpinion}" initDicId="1010503"
                           onchange="AuditIfPass(this.value);"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <bean:message bundle="netOptimize" key="netOptimize.linkAuditResult"/>*
        </td>
        <td class="content max" colspan=3>
            <textarea name="linkAuditResult" id="linkAuditResult" class="textarea max"
                      alt="allowBlank:false,maxLength:100,vtext:'请输入审批结果，最大长度为100个汉字！'">${sheetLink.linkAuditResult}</textarea>
        </td>
    </tr>
    <%
            }
        }
    %>


    <%if (taskName.equals("ExcuteTask")) {%>
    <%if (operateType.equals("62") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ResultEvaluateTask"/>

    <tr>
        <td class="label">
            <bean:message bundle="netOptimize" key="netOptimize.linkIsSuccess"/>*
        </td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}linkIsSuccess" id="${sheetPageName}linkIsSuccess" initDicId="1011803"
                           defaultValue="${sheetLink.linkIsSuccess}"/>
        </td>
        <td class="label">
            <bean:message bundle="netOptimize" key="netOptimize.linkIsStartSheet"/>*
        </td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}linkIsStartSheet" id="${sheetPageName}linkIsStartSheet"
                           initDicId="1011802" onchange="IsDispatchSheet(this.value);"
                           defaultValue="${sheetLink.linkIsStartSheet}"/>
        </td>
    </tr>

    <tr id="invokeButton" style="display:none">
        <td class="label">
            <bean:message bundle="netOptimize" key="netOptimize.linkIsStartSheet"/>*

        </td>
        <td class="content max" colspan=3>
            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="111"/>
            <input type="hidden" name="${sheetPageName}linkNetType" id="${sheetPageName}linkNetType" value=""/>

            <input type="button" class="btn" value="<eoms:id2nameDB id='101050804' beanId='ItawSystemDictTypeDao'/>"
                   onclick="javascript:openwin('101050804')">

        </td>
    </tr>


    <tr>
        <td class="label">
            <bean:message bundle="netOptimize" key="netOptimize.linkSummarize"/>*
        </td>
        <td class="content max" colspan=3>
		  				   <textarea name="linkSummarize"
                                     id="linkSummarize" class="textarea max"
                                     alt="allowBlank:true,maxLength:200,vtext:'您输入网优实施概述超出最大长度限制，最多支持200个汉字！'">${sheetLink.linkSummarize}</textarea>
        </td>
    </tr>


    <tr>
        <td class="label">
            <bean:message bundle="netOptimize" key="netOptimize.linkOperationAcc"/>*
        </td>
        <td class="content" colspan=3>
            <eoms:attachment name="sheetLink" property="linkOperationAcc"
                             scope="request" idField="${sheetPageName}linkOperationAcc" alt="allowBlank:false"
                             appCode="netOptimize"/>
        </td>
    </tr>

    <%
            }
        }
    %>


    <%if (taskName.equals("ResultEvaluateTask")) {%>
    <%if (operateType.equals("64") || operateType.equals("11")) {%>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask"/>

    <tr>
        <td class="label">
            <bean:message bundle="netOptimize" key="netOptimize.linkAssessResult"/>*
        </td>
        <td class="content max" colspan=3>
	  				   <textarea name="linkAssessResult"
                                 id="linkAssessResult" class="textarea max"
                                 alt="allowBlank:false,maxLength:1000,vtext:'请输入评定结果，最大长度为200个汉字！'">${sheetLink.linkAssessResult}</textarea>
        </td>
    </tr>

    <%
            }
        }
    %>

    <%if (taskName.equals("HoldTask") && !operateType.equals("61")) {%>

    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverTask"/>
    <input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>

    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
        <td class="content max" colspan="3">
            <eoms:comboBox name="${sheetPageName}holdStatisfied"
                           id="${sheetPageName}holdStatisfied"
                           defaultValue="${sheetMain.holdStatisfied != 0 ? sheetMain.holdStatisfied : 1030301}"
                           initDicId="10303" styleClass="select" alt="allowBlank:false"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/></td>
        <td class="content max" colspan="3">
            <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult"
                      class="textarea max">${sheetMain.endResult}</textarea>
        </td>
    </tr>


    <%} %>
    <%
        //  if(taskName.equals("cc")){%

        //<tr>
        //   <td class="label">
        //    <bean:message bundle="sheet" key="linkForm.remark" />
        //    </td>
        //	<td colspan="3">
        //	 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15" />
        //           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
        //       alt="allowBlank:false,width:500" alt="width:'500px'">${sheetlink.remark}</textarea>
        //  </td>
        //	</tr>
        //}
    %>
    <%if (operateType.equals("61")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
    <%} %>

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
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="rejectTask"/>
        </c:when>
        <c:when test="${fPreTaskName == 'draft'}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="rejectTask"/>
        </c:when>
        <c:otherwise>
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}"/>
        </c:otherwise>
    </c:choose>

    <tr>
        <td class="label"><bean:message bundle="sheet" key="linkForm.remark"/>*</td>
        <td class="max context" colspan="3">
			        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                              alt="allowBlank:false,width:500" alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>

    <%} %>

</table>


<%if (operateType.equals("80")) { %>

<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <span id="roleName"></span>
    </legend>
    <eoms:chooser id="sendRole" type="role" roleId="363" flowId="046" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>
</fieldset>


<%}%>

<%if (operateType.equals("56")) { %>

<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <span id="roleName"></span>
    </legend>

    <eoms:chooser id="sendRole1" type="role" roleId="363" flowId="046" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>
    <eoms:chooser id="sendRole2" type="role" roleId="364" flowId="046" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',allowBlank:false,text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>
</fieldset>

<%} %>

<%if (operateType.equals("2")) { %>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="netOptimize" key="role.principalPerformer"/>
    </legend>
    <eoms:chooser id="sendRole" type="role" roleId="362" flowId="046" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>
</fieldset>
<%} %>


<%if (operateType.equals("62")) { %>

<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="netOptimize" key="role.adminPerformer"/>
    </legend>
    <eoms:chooser id="sendRole" type="role" roleId="361" flowId="046" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>

</fieldset>

<%}%>
<%if (operateType.equals("64")) { %>

<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="netOptimize" key="role.createSheetPerformer"/>
    </legend>
    <eoms:chooser id="sendRole" type="role" roleId="360" flowId="046" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送',vtext:'请选择抄送对象'}]"/>

</fieldset>

<%}%>

<br/>

<script type="text/javascript">

    var frm = document.forms[0];
    var count = 0;
    var fm = eoms.form;

    function AuditIfPass(input) {
        if ('<%=operateType%>' != '55') {
            if (input == "101050301") {
                //审批通过
                chooser_sendRole1.enable();
                chooser_sendRole2.disable();
                $('${sheetPageName}phaseId').value = 'ExcuteTask';
                $('${sheetPageName}operateType').value = '66';
                $('roleName').innerHTML = "网优工作组";
            } else if (input == "101050302") {
                //审批不通过
                chooser_sendRole2.enable();
                chooser_sendRole1.disable();
                $('${sheetPageName}phaseId').value = 'ProjectCreateTask';
                $('${sheetPageName}operateType').value = '5';
                $('roleName').innerHTML = "网优方案员";
            } else {
                chooser_sendRole1.disable();
                chooser_sendRole2.disable();
                $('${sheetPageName}phaseId').value = '';
                $('${sheetPageName}operateType').value = '';
                $('roleName').innerHTML = "";
            }
        }
    }

    var tempAudit = frm.linkAuditOpinion ? frm.linkAuditOpinion.value : '';
    if ("${taskName}" == "AuditTask" && '<%=operateType%>' != '61' && tempAudit != '') {

        AuditIfPass(tempAudit);
    }
    if ("${taskName}" == "AuditTask" && '<%=operateType%>' != '61' && '<%=operateType%>' != '4' && tempAudit == '') {
        chooser_sendRole1.disable();
        chooser_sendRole2.disable();
    }

    //chooser_sendRole.setRoleId(222);
    function AcceptChange(input) {
        var tempFlag =
        <%=operateType %>
        if (input == "101180101") {
            chooser_sendRole.disable();
            $('${sheetPageName}phaseId').value = 'rejectTask';
            $('roleName').innerHTML = "建单人";
            $('${sheetPageName}operateType').value = tempFlag;

        } else if (input == "101180102") {

            chooser_sendRole.enable();
            $('${sheetPageName}phaseId').value = 'ExcuteTask';
            $('${sheetPageName}operateType').value = tempFlag;
            $('roleName').innerHTML = "网优工作组";
            $('${sheetPageName}operateType').value = 1;
            chooser_sendRole.setRoleId(363);
        } else {
            $('${sheetPageName}operateType').value = 46;
            chooser_sendRole.enable();
            $('${sheetPageName}phaseId').value = 'ProjectCreateTask';
            $('${sheetPageName}operateType').value = tempFlag;
            $('roleName').innerHTML = "网优方案员";
            chooser_sendRole.setRoleId(364);
        }
    }

    function ifSuccess(input) {
        $('${sheetPageName}mainIsSuccess').value = input;

    }


    function IsDispatchSheet(input) {
        if (input == "101180201") {
            fm.enableArea('invokeButton');
            $('${sheetPageName}operateType').value = '111';
            $('${sheetPageName}phaseId').value = 'callProcess';
            chooser_sendRole.body.down("input[type='button']").setDisplayed(false);

        } else if (input == "101180202") {

            if ('${ifInvoke}' == "no") {
                alert("您已经调用了工单，正在等待任务回调中!此处请选择'是'选项!");
                fm.enableArea('invokeButton');
                $('${sheetPageName}phaseId').value = 'callProcess';
                $('${sheetPageName}operateType').value = '111';
                document.all.${sheetPageName}linkIsStartSheet.value = "101180201"
                chooser_sendRole.body.down("input[type='button']").setDisplayed(false);
            } else {
                $('${sheetPageName}operateType').value = '62';
                $('${sheetPageName}phaseId').value = 'ResultEvaluateTask';
                chooser_sendRole.body.down("input[type='button']").setDisplayed(true);
                fm.disableArea('invokeButton', true);

            }


        } else {
            fm.disableArea('invokeButton', true);
            $('${sheetPageName}operateType').value = '62';
            $('${sheetPageName}phaseId').value = 'ResultEvaluateTask';
            chooser_sendRole.body.down("input[type='button']").setDisplayed(true);
        }

    }

    function openwin(flag) {
        var url;

        if (flag == "101050804") {
            if ($('${sheetPageName}linkNetType').value.indexOf('101050804') == -1) {
                $('${sheetPageName}linkNetType').value += '101050804' + ',';
            }
            $('${sheetPageName}phaseId').value = 'callProcess';

            url = "${app}/sheet/netchange/netchange.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iNetOptimizeMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}";
        }
        window.open(url, 'newwindow', 'height=800, width=1000, top=200, left=200,toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
    }

    if ("${taskName}" == "ExcuteTask" && '<%=operateType%>' != '61') {
        var frm = document.forms[0];
        if ("${ifInvoke}" == "no") {
            document.forms[0].linkIsStartSheet.value = "101180201";
        }
        var temp1 = frm.linkIsStartSheet ? frm.linkIsStartSheet.value : '';

        if (temp1 != '') {
            IsDispatchSheet(temp1);
        }

    }

</script>
<div ID="idSpecia2"></div>    