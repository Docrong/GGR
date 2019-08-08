<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.boco.eoms.sheet.businesspilot.model.BusinessPilotLink" %>
<%
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
    String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId"));
    String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId"));
    System.out.println("=====taskName=====" + taskName);
    String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
    System.out.println("=====operateType======" + operateType);


    String ifInvoke = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifInvoke"));
    System.out.println("=====ifInvoke======" + ifInvoke);

    BusinessPilotLink link = (BusinessPilotLink) request.getAttribute("preLink");

%>

<script type="text/javascript">

    var frm = document.forms[0];
    var count = 0;
    var fm = eoms.form;

    function isFinish(input) {

        if (input == "101051101") {
            fm.enableArea('isChange');


        } else if (input == "101051102") {
            fm.disableArea('isChange', true);
            fm.disableArea('invokeAgin1', true);

        }
    }

    function ifTestPass(input) {
        var tempFlag =
        <%=operateType %>
        if (input == "101050701") {
            $('${sheetPageName}operateType').value = '1';
            //chooser_sendRole.setRoleId(222);
            if (tempFlag != '11') {

                $('${sheetPageName}phaseId').value = 'strategy';
                $('${sheetPageName}operateType').value = '1';
                $('roleName').innerHTML = "${eoms:a2u('维护规程管理组')}";
            } else {
                $('${sheetPageName}operateType').value = '11';
            }

        } else if (input == "101050702") {

            if (tempFlag != '11') {
                $('${sheetPageName}phaseId').value = 'hold';
                $('${sheetPageName}operateType').value = '2';
                $('roleName').innerHTML = "${eoms:a2u('新业务接口人')}";
            } else {
                $('${sheetPageName}operateType').value = '11';
            }
        }
    }

    function ifSuccess(input) {
        $('${sheetPageName}mainIsSuccess').value = input;

    }

    function InvokeAgin(input) {
        if (input == "1030101") {
            $('${sheetPageName}phaseId').value = 'callProcess';
            $('${sheetPageName}operateType').value = '111';
            fm.enableArea('invokeAgin');
            fm.disableArea('test', true);
            fm.disableArea('testfile', true);
        } else if (input == "1030102") {
            $('${sheetPageName}operateType').value = '1';
            fm.enableArea('test');
            fm.enableArea('testfile');
            var attch = $("UIFrame1-${sheetPageName}linkOperateDesc");
            if (eoms.isIE) {
                attch.setStyle("width:300px;height:100px");
            } else {
                attch.contentWindow.autoIframe();
            }
            fm.disableArea('invokeAgin', true);
        } else {
            fm.disableArea('test', true);
            fm.disableArea('testfile', true);
            fm.disableArea('invokeAgin', true);
        }

    }

    function holdInvokeAgin(input) {
        if (input == "1030101") {
            fm.enableArea('invokeAgin1');
        } else if (input == "1030102") {
            fm.disableArea('invokeAgin1', true);
        } else {
            fm.disableArea('invokeAgin1', true);
        }

    }

    function openwin(flag) {
        var url;

        if (flag == "101050801") {
            if ($('${sheetPageName}linkNetType').value.indexOf('101050801') == -1) {
                $('${sheetPageName}linkNetType').value += '101050801' + ',';
            }
            $('${sheetPageName}phaseId').value = 'callProcess';
            url = "${app}/sheet/circuitdispatch/circuitdispatch.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iBusinessPilotMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}";
        } else if (flag == "101050802") {
            if ($('${sheetPageName}linkNetType').value.indexOf('101050802') == -1) {
                $('${sheetPageName}linkNetType').value += '101050802' + ',';
            }
            $('${sheetPageName}phaseId').value = 'callProcess';

            url = "${app}/sheet/softchange/softchange.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iBusinessPilotMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}";
        } else if (flag == "101050803") {
            if ($('${sheetPageName}linkNetType').value.indexOf('101050803') == -1) {
                $('${sheetPageName}linkNetType').value += '101050803' + ',';
            }
            $('${sheetPageName}phaseId').value = 'callProcess';

            url = "${app}/sheet/netdata/netdata.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iBusinessPilotMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}";
        } else if (flag == "101050804") {
            if ($('${sheetPageName}linkNetType').value.indexOf('101050804') == -1) {
                $('${sheetPageName}linkNetType').value += '101050804' + ',';
            }
            $('${sheetPageName}phaseId').value = 'callProcess';

            url = "${app}/sheet/netchange/netchange.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iBusinessPilotMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}";
        }
        window.open(url, 'newwindow', 'height=800, width=1000, top=200, left=200,toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
    }

    function openybwin(flag) {
        var url;

        if (flag == "101050801") {
            if ($('${sheetPageName}linkNetType').value.indexOf('101050801') == -1) {
                $('${sheetPageName}linkNetType').value += '101050801' + ',';
            }
            $('${sheetPageName}phaseId').value = 'taskOver';
            url = "${app}/sheet/circuitdispatch/circuitdispatch.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iBusinessPilotMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}";
        } else if (flag == "101050802") {
            if ($('${sheetPageName}linkNetType').value.indexOf('101050802') == -1) {
                $('${sheetPageName}linkNetType').value += '101050802' + ',';
            }
            $('${sheetPageName}phaseId').value = 'taskOver';

            url = "${app}/sheet/softchange/softchange.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iBusinessPilotMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}";
        } else if (flag == "101050803") {
            if ($('${sheetPageName}linkNetType').value.indexOf('101050803') == -1) {
                $('${sheetPageName}linkNetType').value += '101050803' + ',';
            }
            $('${sheetPageName}phaseId').value = 'taskOver';

            url = "${app}/sheet/netdata/netdata.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iBusinessPilotMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}";
        } else if (flag == "101050804") {
            if ($('${sheetPageName}linkNetType').value.indexOf('101050804') == -1) {
                $('${sheetPageName}linkNetType').value += '101050804' + ',';
            }
            $('${sheetPageName}phaseId').value = 'taskOver';

            url = "${app}/sheet/netchange/netchange.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iBusinessPilotMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}";
        }
        window.open(url, 'newwindow', 'height=800, width=1000, top=200, left=200,toolbar=no, menubar=no, scrollbars=yes, resizable=no,location=no, status=no');
    }

    var temp = frm.linkTestResult ? frm.linkTestResult.value : '';
    if (temp != '') {
        ifTestPass(temp);
    }
    var tempvalue = frm.linkIsFinish ? frm.linkIsFinish.value : '';
    if (tempvalue != '') {
        isFinish(tempvalue);
    }
</script>

<%@ include file="/WEB-INF/pages/wfworksheet/businesspilot/baseinputlinkhtmlnew.jsp" %>
<br/>
<input type="hidden" name="${sheetPageName}processTemplateName" id="${sheetPageName}processTemplateName"
       value="BusinessPilotProcess"/>
<input type="hidden" name="${sheetPageName}operateName" id="${sheetPageName}operateName" value="nonFlowOperate"/>
<input type="hidden" name="${sheetPageName}beanId" value="iBusinessPilotMainManager"/>
<input type="hidden" name="${sheetPageName}mainClassName"
       value="com.boco.eoms.sheet.businesspilot.model.BusinessPilotMain"/> <!--main表Model对象类名-->
<input type="hidden" name="${sheetPageName}linkClassName"
       value="com.boco.eoms.sheet.businesspilot.model.BusinessPilotLink"/> <!--link表Model对象类名-->
<input type="hidden" name="${sheetPageName}toDeptId" value="${sheetMain.toDeptId}"/>
<input type="hidden" name="${sheetPageName}mainIsSuccess" id="${sheetPageName}mainIsSuccess" value=""/>
<input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
<table class="formTable">

    <%
        if (taskName.equals("pilot") && (operateType.equals("1") || operateType.equals("11"))) {
            if (ifInvoke.equals("") || ifInvoke.equals("no")) {
    %>
    <% if (operateType.equals("1")) {%>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="111"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="callProcess"/>

    <tr>
        <td class="label">
            <bean:message bundle="businesspilot" key="businesspilot.linkOperStartTime"/>*
        </td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}linkOperStartTime" readonly="readonly"
                   id="${sheetPageName}linkOperStartTime"
                   onclick="popUpCalendar(this, this)"
                   alt="vtype:'lessThen',link:'${sheetPageName}linkOperEndTime',vtext:'${eoms:a2u("试点开始时间不能晚于试点结束时间")}',allowBlank:false"
                   value="${eoms:date2String(sheetLink.linkOperStartTime)}"/>
        </td>
        <td class="label">
            <bean:message bundle="businesspilot" key="businesspilot.linkOperEndTime"/>*
        </td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}linkOperEndTime" readonly="readonly"
                   id="${sheetPageName}linkOperEndTime"
                   onclick="popUpCalendar(this, this)"
                   alt="vtype:'moreThen',link:'${sheetPageName}linkOperStartTime',vtext:'${eoms:a2u("试点结束时间不能早于试点开始时间")}',allowBlank:false"
                   value="${eoms:date2String(sheetLink.linkOperEndTime)}"/>
        </td>
    </tr>


    <tr>
        <td class="label">
            <bean:message bundle="businesspilot" key="businesspilot.linkNetType"/>*
        </td>
        <td class="content" colspan=3>
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
    <tr>
        <td class="label">
            <bean:message bundle="businesspilot" key="businesspilot.linkBusDesc"/>*
        </td>
        <td class="content" colspan=3>
            <textarea name="linkBusDesc" id="linkBusDesc" class="textarea max"
                      alt="allowBlank:false,maxLength:1000,vtext:'${eoms:a2u('请输入业务试点说明，最大长度为1000个汉字')}'">${sheetLink.linkBusDesc}</textarea>
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

        <td class="label">
            <bean:message bundle="businesspilot" key="businesspilot.linkPilotScheme"/>*
        </td>
        <td class="content" colspan=3>
            <eoms:attachment name="sheetLink" property="linkPilotScheme"
                             scope="request" idField="${sheetPageName}linkPilotScheme" alt="allowBlank:false"
                             appCode="businesspilot"/>

        </td>
    </tr>
    <%} else if (operateType.equals("11")) { %>
    <!-- 分派说明 -->
    <input type="hidden" name="${sheetPageName}operateType" value="11"/>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
					           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                                         alt="allowBlank:false,width:500"
                                         alt="width:'500px'">${sheetlink.remark}</textarea>
        </td>
    </tr>
    <%} %>
    <%} else if (ifInvoke.equals("yes")) { %>

    <%if (operateType.equals("1")) { %>
    <tr>
        <td class="label">${eoms:a2u("是否调变更配置流程")}</td>

        <td class="content max" colspan=3>
            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value=""/>
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value=""/>
            <eoms:comboBox name="${sheetPageName}ifInvokeAgin" id="${sheetPageName}ifInvokeAgin" alt="allowBlank:false"
                           initDicId="10301" onchange="InvokeAgin(this.value);"/>
        </td>
    </tr>


    <tr id="invokeAgin" style="display:none">
        <td class="label">
            <bean:message bundle="businesspilot" key="businesspilot.linkNetType"/>*
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
    <%} else if (operateType.equals("11")) { %>
    <!-- 分派说明 -->
    <input type="hidden" name="${sheetPageName}operateType" value="11"/>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
					           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                                         alt="allowBlank:false,width:500"
                                         alt="width:'500px'">${sheetlink.remark}</textarea>
        </td>
    </tr>
    <%} %>
    <tr id="test" style="display:none">
        <td class="label">
            <bean:message bundle="businesspilot" key="businesspilot.linkTestResult"/>*
        </td>
        <td class="content max" colspan=3>
            <eoms:comboBox name="${sheetPageName}linkTestResult" id="${sheetPageName}linkTestResult"
                           alt="allowBlank:false" initDicId="1010507" onchange="ifTestPass(this.value);"
                           defaultValue="${sheetLink.linkTestResult}"/>
        </td>
    </tr>

    <tr id="testfile" style="display:none">
        <td class="label">
            <bean:message bundle="businesspilot" key="businesspilot.linkOperateDesc"/>*
        </td>

        <td class="content  max" colspan="3">
            <eoms:attachment name="sheetLink" property="linkOperateDesc"
                             scope="request" idField="${sheetPageName}linkOperateDesc" alt="allowBlank:false"
                             appCode="businesspilot"/>
        </td>

    </tr>

    <%
            }
        }
    %>
    <%if (taskName.equals("pilot") && operateType.equals("4")) { %>
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
        <c:when test="${fPreTaskName == 'draft'}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="reject"/>
        </c:when>
        <c:otherwise>
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}"/>
        </c:otherwise>
    </c:choose>

    <tr>
        <td class="label"><bean:message bundle="sheet" key="linkForm.remark"/>*</td>
        <td colspan="3">
			        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                              alt="allowBlank:false,width:500" alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>

    <%} %>
    <%if (taskName.equals("strategy")) {%>
    <%if (operateType.equals("5") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="prepare"/>

    <tr>
        <td class="label">
            <bean:message bundle="businesspilot" key="businesspilot.linkIlluminate"/>*
        </td>
        <td colspan=3>
            <textarea name="linkIlluminate" id="linkIlluminate" class="textarea max"
                      alt="allowBlank:false,maxLength:1000,vtext:'${eoms:a2u('请输入说明，最大长度为1000个汉字！')}'">${sheetLink.linkIlluminate}</textarea>
        </td>
    </tr>
    <tr>

    </tr>
    <tr>
        <td class="label">
            <bean:message bundle="businesspilot" key="businesspilot.linkTGPolicyAcc"/>*
        </td>
        <td colspan=3>
            <eoms:attachment name="sheetLink" property="linkTGPolicyAcc"
                             scope="request" idField="${sheetPageName}linkTGPolicyAcc" alt="allowBlank:false"
                             appCode="businesspilot"/>
        </td>
    </tr>
    <%} %>
    <%if (operateType.equals("4")) { %>
    <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer"
           value="${fOperateroleid}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader"
           value="${ftaskOwner}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType"
           value="${fOperateroleidType}"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4"/>
    <!--<input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="pilot" />  -->
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}"/>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="linkForm.remark"/>*</td>
        <td colspan="3">
			        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                              alt="allowBlank:false,width:500" alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>

    <%
            }
        }
    %>

    <%if (taskName.equals("prepare")) {%>
    <%if (operateType.equals("66") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="run"/>

    <tr>
        <td class="label">
            <bean:message bundle="businesspilot" key="businesspilot.linkDeviceVerify"/>
        </td>
        <td colspan=3>
            <eoms:comboBox name="${sheetPageName}linkDeviceVerify" id="${sheetPageName}linkDeviceVerify"
                           initDicId="1010514" defaultValue="${sheetLink.linkDeviceVerify}"/>

        </td>
    </tr>
    <tr>
        <td class="label">
            <bean:message bundle="businesspilot" key="businesspilot.linkDataFile"/>
        </td>
        <td colspan=3>
		  				   <textarea name="linkDataFile"
                                     id="linkDataFile" class="textarea max"
                                     alt="allowBlank:true,maxLength:1000,vtext:'${eoms:a2u('您输入的局数据资料超出最大长度限制，最多支持1000个汉字！')}'">${sheetLink.linkDataFile}</textarea>
        </td>
    </tr>

    <tr>
        <td class="label">
            <bean:message bundle="businesspilot" key="businesspilot.linkPassMan"/>
        </td>
        <td colspan=3>
		  				  <textarea name="linkPassMan"
                                    id="linkPassMan" class="textarea max"
                                    alt="allowBlank:true,maxLength:1000,vtext:'${eoms:a2u('您输入的口令管理的移交信息超出最大长度限制，最多支持1000个汉字！')}'">${sheetLink.linkPassMan}</textarea>
        </td>
    </tr>

    <tr>
        <td class="label">
            <bean:message bundle="businesspilot" key="businesspilot.linkReport"/>
        </td>
        <td colspan=3>
		  				   <textarea name="linkReport"
                                     id="linkReport" class="textarea max"
                                     alt="allowBlank:true,maxLength:1000,vtext:'${eoms:a2u('您输入的初验报告信息超出最大长度限制，最多支持1000个汉字！')}'">${sheetLink.linkReport}</textarea>
        </td>
    </tr>

    <tr>
        <td class="label">
            <bean:message bundle="businesspilot" key="businesspilot.linkWorkplan"/>
        </td>
        <td colspan=3>
		  				   <textarea name="linkWorkplan"
                                     id="linkWorkplan" class="textarea max"
                                     alt="allowBlank:true,maxLength:1000,vtext:'${eoms:a2u('您输入的作业计划制定情况超出最大长度限制，最多支持1000个汉字！')}'">${sheetLink.linkWorkplan}</textarea>
        </td>
    </tr>
    <tr>
        <!-- <td class="label">
			    	<bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
				</td>	
				<td colspan="3">					
			    <eoms:attachment name="tawSheetAccess" property="accesss" 
			            scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>			
			    </td>
			  </tr> -->
    <tr>
        <td class="label">
            <bean:message bundle="businesspilot" key="businesspilot.linkMeetingAcc"/>*
        </td>
        <td colspan=3>
            <eoms:attachment name="sheetLink" property="linkMeetingAcc"
                             scope="request" idField="${sheetPageName}linkMeetingAcc" alt="allowBlank:false"
                             appCode="businesspilot"/>
        </td>
    </tr>

    <%
        }
        if (operateType.equals("4")) {
    %>
    <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer"
           value="${fOperateroleid}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader"
           value="${ftaskOwner}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType"
           value="${fOperateroleidType}"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4"/>
    <!--   <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="strategy" />-->
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}"/>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="linkForm.remark"/>*</td>
        <td colspan="3">
			        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                              alt="allowBlank:false,width:500" alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>

    <%
            }
        }
    %>


    <%if (taskName.equals("run")) {%>
    <%if (operateType.equals("46") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="check"/>

    <tr>
        <td class="label">
            <bean:message bundle="businesspilot" key="businesspilot.linkRunFeedBack"/>*
        </td>
        <td colspan=3>
	  				   <textarea name="linkRunFeedBack"
                                 id="linkRunFeedBack" class="textarea max"
                                 alt="allowBlank:false,maxLength:1000,vtext:'${eoms:a2u('请输入试运行反馈，最大长度为1000个汉字！')}'">${sheetLink.linkRunFeedBack}</textarea>
        </td>
    </tr>
    <tr>
        <!-- <td class="label">
		    	<bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
			</td>	
			<td colspan="3">					
		    <eoms:attachment name="tawSheetAccess" property="accesss" 
		            scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>			
		    </td> -->
    </tr>
    <tr>
        <td class="label">
            <bean:message bundle="businesspilot" key="businesspilot.linkRunReport"/>
        </td>
        <td colspan=3>
            <eoms:attachment name="sheetLink" property="linkRunReport"
                             scope="request" idField="${sheetPageName}linkRunReport" appCode="businesspilot"/>
        </td>
    </tr>

    <%
        }
        if (operateType.equals("4")) {
    %>
    <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer"
           value="${fOperateroleid}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader"
           value="${ftaskOwner}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType"
           value="${fOperateroleidType}"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4"/>
    <!-- <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="prepare" /> -->
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}"/>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="linkForm.remark"/>*</td>
        <td colspan="3">
			        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                              alt="allowBlank:false,width:500" alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>

    <%
            }
        }
    %>

    <%if (taskName.equals("check")) {%>
    <%if (operateType.equals("64") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="report"/>
    <tr>
        <td class="label">
            <bean:message bundle="businesspilot" key="businesspilot.linkIsUpdate"/>*
        </td>
        <td colspan=3>
            <eoms:comboBox name="${sheetPageName}linkIsUpdate" id="${sheetPageName}linkIsUpdate" alt="allowBlank:false"
                           initDicId="1010509" defaultValue="${sheetLink.linkIsUpdate}"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <bean:message bundle="businesspilot" key="businesspilot.linkUpdateAdvice"/>*
        </td>
        <td colspan=3>
            <textarea name="linkUpdateAdvice" id="linkUpdateAdvice" class="textarea max"
                      alt="allowBlank:false,maxLength:1000,vtext:'${eoms:a2u('请输入技术规范修订建议，最大长度为1000个汉字！')}'">${sheetLink.linkUpdateAdvice}</textarea>
        </td>
    </tr>
    <%
        }
        if (operateType.equals("4")) {
    %>
    <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer"
           value="${fOperateroleid}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader"
           value="${ftaskOwner}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType"
           value="${fOperateroleidType}"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4"/>
    <!-- <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="run" /> -->
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}"/>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="linkForm.remark"/>*</td>
        <td colspan="3">
			        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                              alt="allowBlank:false,width:500" alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>

    <%
            }
        }
    %>
    <%if (taskName.equals("report")) {%>
    <%if (operateType.equals("62") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="note"/>

    <tr>
        <td class="label">
            <bean:message bundle="businesspilot" key="businesspilot.linkIsSuccess"/>*
        </td>
        <td colspan=3>
            <eoms:comboBox name="${sheetPageName}linkIsSuccess" id="${sheetPageName}linkIsSuccess"
                           alt="allowBlank:false" onchange="ifSuccess(this.value);" initDicId="1010510"
                           defaultValue="${sheetLink.linkIsSuccess}"/>
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
        <td class="label">
            <bean:message bundle="businesspilot" key="businesspilot.linkConsult"/>*
        </td>
        <td colspan=3>
            <textarea name="linkConsult" id="linkConsult" class="textarea max"
                      alt="allowBlank:false,maxLength:1000,vtext:'${eoms:a2u('请输入试点总结概述，最大长度为1000个汉字！')}'">${sheetLink.linkConsult}</textarea>
        </td>
    </tr>


    <tr>

        <td class="label">
            <bean:message bundle="businesspilot" key="businesspilot.linkSummarizeAcc"/>*
        </td>
        <td colspan="3">
            <eoms:attachment name="sheetLink" property="linkSummarizeAcc"
                             scope="request" idField="${sheetPageName}linkSummarizeAcc" alt="allowBlank:false"
                             appCode="businesspilot"/>

        </td>
    </tr>
    <%
        }
        if (operateType.equals("4")) {
    %>
    <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer"
           value="${fOperateroleid}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader"
           value="${ftaskOwner}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType"
           value="${fOperateroleidType}"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4"/>
    <!-- <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="check" /> -->
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}"/>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="linkForm.remark"/>*</td>
        <td colspan="3">
			        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                              alt="allowBlank:false,width:500" alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>

    <%
            }
        }
    %>

    <%if (taskName.equals("note")) {%>
    <%if (operateType.equals("80") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="temp"/>

    <tr>
        <td class="label">
            <bean:message bundle="businesspilot" key="businesspilot.linkSDEndTime"/>
        </td>
        <td colspan=3>
            <input type="text" class="text" name="${sheetPageName}linkSDEndTime" readonly="readonly"
                   id="${sheetPageName}linkSDEndTime"
                   onclick="popUpCalendar(this, this)" value="${eoms:date2String(sheetLink.linkSDEndTime)}"/>
        </td>
    </tr>
    <%} %>
    <%if (operateType.equals("56") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="taskOver"/>
    <input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}holdStatisfied"
                           id="${sheetPageName}holdStatisfied" defaultValue="${sheetMain.holdStatisfied}"
                           initDicId="10303" styleClass="select" alt="allowBlank:false"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/></td>
        <td colspan="3">
            <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult"
                      class="textarea max">${sheetMain.endResult}</textarea>
        </td>
    </tr>


    <tr>
        <td class="label"><bean:message bundle="businesspilot" key="businesspilot.linkIsFinish"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkIsFinish"
                           id="${sheetPageName}linkIsFinish" defaultValue="${sheetLink.linkIsFinish}"
                           initDicId="1010511" onchange="isFinish(this.value);" styleClass="select"
                           alt="allowBlank:false"/>
        </td>
    </tr>

    <tr id="isChange" style="display:none">
        <td class="label"><bean:message bundle="businesspilot" key="businesspilot.linkIsChange"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkIsChange"
                           id="${sheetPageName}linkIsChange" defaultValue="${sheetLink.linkIsChange}" initDicId="10301"
                           styleClass="select" onchange="holdInvokeAgin(this.value);" alt="allowBlank:false"/>
        </td>
    </tr>


    <tr id="invokeAgin1" style="display:none">
        <td class="label">
            <bean:message bundle="businesspilot" key="businesspilot.linkNetType"/>*
        </td>
        <td class="content max" colspan=3>
            <input type="hidden" name="${sheetPageName}linkNetType" id="${sheetPageName}linkNetType" value=""/>
            <input type="button" class="btn" value="<eoms:id2nameDB id='101050801' beanId='ItawSystemDictTypeDao'/>"
                   onclick="javascript:openybwin('101050801')">
            <input type="button" class="btn" value="<eoms:id2nameDB id='101050802' beanId='ItawSystemDictTypeDao'/>"
                   onclick="javascript:openybwin('101050802')">
            <input type="button" class="btn" value="<eoms:id2nameDB id='101050803' beanId='ItawSystemDictTypeDao'/>"
                   onclick="javascript:openybwin('101050803')">
            <input type="button" class="btn" value="<eoms:id2nameDB id='101050804' beanId='ItawSystemDictTypeDao'/>"
                   onclick="javascript:openybwin('101050804')">

        </td>
    </tr>
    <%} %>
    <%if (operateType.equals("4")) { %>
    <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer"
           value="${fOperateroleid}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader"
           value="${ftaskOwner}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType"
           value="${fOperateroleidType}"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4"/>
    <!--  <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="report" />-->
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}"/>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="linkForm.remark"/>*</td>
        <td colspan="3">
			        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                              alt="allowBlank:false,width:500" alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>

    <%
            }
        }
    %>

    <%if (taskName.equals("hold") && !operateType.equals("61")) {%>

    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="taskOver"/>
    <input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>

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
        <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/></td>
        <td colspan="3">
            <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult"
                      class="textarea max">${sheetMain.endResult}</textarea>
        </td>
    </tr>


    <%} %>
    <%if (operateType.equals("61")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="allowBlank:false,width:'500px',maxLength:1000,vtext:'${eoms:a2u('请最多输入500字')}'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%} %>
</table>


<%if (taskName.equals("pilot") && !ifInvoke.equals("no") && !ifInvoke.equals("") && !operateType.equals("61") && !operateType.equals("4") && !operateType.equals("11")) { %>

<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <span id="roleName"></span>
    </legend>
    <eoms:chooser id="sendRole" type="role" roleId="222" flowId="022" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'${eoms:a2u('抄送')}'}]"/>
</fieldset>
<script type="text/javascript">
    Ext.onReady(function () {
        chooser_sendRole.body.down("input[type='button']").dom.value = "${eoms:a2u('请选择抄送对象')}";
    });
</script>
<%}%>

<%if (operateType.equals("2")) { %>

<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="businesspilot" key="role.managePerformer"/>
    </legend>

    <eoms:chooser id="sendRole" type="role" roleId="222" flowId="022" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',allowBlank:false,text:'${eoms:a2u('派发')}',vtext:'${eoms:a2u('请选择派发对象')}'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'${eoms:a2u('抄送')}'}]"/>
</fieldset>

<%}%>
<%if (operateType.equals("5")) { %>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="businesspilot" key="role.supportPerformer"/>
    </legend>
    <eoms:chooser id="sendRole" type="role" roleId="221" flowId="022" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'${eoms:a2u('抄送')}'}]"/>
</fieldset>
<script type="text/javascript">
    Ext.onReady(function () {
        chooser_sendRole.body.down("input[type='button']").dom.value = "${eoms:a2u('请选择抄送对象')}";
    });
</script>
<%} %>

<%if (operateType.equals("66")) { %>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="businesspilot" key="role.maintainPerformer"/>
    </legend>
    <eoms:chooser id="sendRole" type="role" roleId="223" flowId="022" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',allowBlank:false,text:'${eoms:a2u('派发')}',vtext:'${eoms:a2u('请选择派发对象')}'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'${eoms:a2u('抄送')}'}]"/>
</fieldset>
<%} %>

<%if (taskName.equals("cc")) {%>
<fieldset id="link4">
    <eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'copyPerformer',childType:'user',limit:'none',text:'${eoms:a2u('抄送')}'}]"/>
</fieldset>
<%} %>


<%if (operateType.equals("46")) { %>

<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="businesspilot" key="role.supportPerformer"/>
    </legend>
    <eoms:chooser id="sendRole" type="role" roleId="221" flowId="022" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'${eoms:a2u('抄送')}'}]"/>
</fieldset>
<script type="text/javascript">
    Ext.onReady(function () {
        chooser_sendRole.body.down("input[type='button']").dom.value = "${eoms:a2u('请选择抄送对象')}";
    });
</script>
<%}%>
<%if (operateType.equals("64") || operateType.equals("7")) { %>

<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="businesspilot" key="role.interfacePerformer"/>
    </legend>
    <eoms:chooser id="sendRole" type="role" roleId="221" flowId="022" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'${eoms:a2u('抄送')}'}]"/>
</fieldset>
<script type="text/javascript">
    Ext.onReady(function () {
        chooser_sendRole.body.down("input[type='button']").dom.value = "${eoms:a2u('请选择抄送对象')}";
    });
</script>
<%}%>
<br/>

</script>
<div ID="idSpecia2"></div>    