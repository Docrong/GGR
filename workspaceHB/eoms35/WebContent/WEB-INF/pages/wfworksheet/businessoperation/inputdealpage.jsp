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
    String roleName = "";
    String ifInvoke = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ifInvoke"));
    System.out.println("=====ifInvoke======" + ifInvoke);
%>
<script type="text/javascript">
    function ifSuccess(input) {

        $('${sheetPageName}mainIsSuccess').value = input;
        //	alert($('${sheetPageName}mainIsSuccess').value);

    }

    function openwin(flag) {
        var url;
        if (flag == "101050801") {
            if ($('${sheetPageName}linkNetType').value.indexOf('101050801') == -1) {
                $('${sheetPageName}linkNetType').value += '101050801' + ",";
            }
            $('${sheetPageName}phaseId').value = 'callProcess';
            url = "${app}/sheet/circuitdispatch/circuitdispatch.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iBusinessOperationMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}";
        } else if (flag == "101050802") {
            if ($('${sheetPageName}linkNetType').value.indexOf('101050802') == -1) {
                $('${sheetPageName}linkNetType').value += '101050802' + ",";
            }
            $('${sheetPageName}phaseId').value = 'callProcess';

            url = "${app}/sheet/softchange/softchange.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iBusinessOperationMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}";
        } else if (flag == "101050803") {
            if ($('${sheetPageName}linkNetType').value.indexOf('101050803') == -1) {
                $('${sheetPageName}linkNetType').value += '101050803' + ",";
            }
            $('${sheetPageName}phaseId').value = 'callProcess';

            url = "${app}/sheet/netdata/netdata.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iBusinessOperationMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}";
        } else if (flag == "101050804") {
            if ($('${sheetPageName}linkNetType').value.indexOf('101050804') == -1) {
                $('${sheetPageName}linkNetType').value += '101050804' + ",";
            }
            $('${sheetPageName}phaseId').value = 'callProcess';

            url = "${app}/sheet/netchange/netchange.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iBusinessOperationMainManager&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}";
        }
        window.open(url, 'newwindow', 'height=800, width=1000, top=200, left=200,toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
    }

    var fm = eoms.form;

    function InvokeAgin(input) {
        if (input == "1030101") {
            $('${sheetPageName}phaseId').value = 'callProcess';
            $('${sheetPageName}operateType').value = '111';
            fm.enableArea('invokeAgin');
            fm.disableArea('test', true);
            fm.disableArea('testfile', true);
        } else if (input == "1030102") {
            $('${sheetPageName}phaseId').value = 'refine';
            $('${sheetPageName}operateType').value = '91';
            fm.enableArea('test');
            fm.enableArea('testfile');
            var attch = $("UIFrame1-${sheetPageName}linkAlterationAcc");
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
</script>
<%@ include file="/WEB-INF/pages/wfworksheet/businessoperation/baseinputlinkhtmlnew.jsp" %>
<br/>
<table class="formTable">
    <!--  	<caption><bean:message bundle="businessoperation" key="businessoperation.header"/></caption>-->
    <input type="hidden" name="${sheetPageName}beanId" value="iBusinessOperationMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.businessoperation.model.BusinessOperationMain"/>
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.businessoperation.model.BusinessOperationLink"/>
    <c:if test="${taskName != 'hold' }">
        <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
    </c:if>

    <%if (taskName.equals("operate") && (operateType.equals("91") || operateType.equals("11"))) {%>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="refine"/>
    <%if (ifInvoke.equals("") || ifInvoke.equals("no")) {%>
    <%if (operateType.equals("91")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="111"/>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                   readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                   alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'${eoms:a2u("受理时限不能晚于处理时限")}',allowBlank:false"/>

        </td>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeCompleteLimit" readonly="readonly"
                   value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit"
                   alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'${eoms:a2u("完成时限不能早于受理时限")}',allowBlank:false"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessoperation" key="businessoperation.linkOperStartTime"/>*</td>
        <td>
            <input class="text" onclick="popUpCalendar(this, this)" type="text" name="${sheetPageName}linkOperStartTime"
                   readonly="readonly"
                   alt="vtype:'lessThen',link:'${sheetPageName}linkOperEndTime',vtext:'${eoms:a2u("实施开始时间不能晚于实施结束时间")}',allowBlank:false"
                   value="${eoms:date2String(sheetLink.linkOperStartTime)}" id="${sheetPageName}linkOperStartTime"/>
        </td>
        <td class="label"><bean:message bundle="businessoperation" key="businessoperation.linkOperEndTime"/>*</td>
        <td>
            <input class="text" onclick="popUpCalendar(this, this)" type="text" name="${sheetPageName}linkOperEndTime"
                   readonly="readonly"
                   alt="vtype:'moreThen',link:'${sheetPageName}linkOperStartTime',vtext:'${eoms:a2u("实施结束时间不能早于实施开始时间")}',allowBlank:false"
                   value="${eoms:date2String(sheetLink.linkOperEndTime)}" id="${sheetPageName}linkOperEndTime"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="businessoperation" key="businessoperation.linkNetType"/>*</td>
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
        <td class="label"><bean:message bundle="businessoperation" key="businessoperation.linkBusinessDesc"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkBusinessDesc" id="${sheetPageName}linkBusinessDesc"
                      alt="width:500,allowBlank:false,maxLength:1000,vtext:'${eoms:a2u('请填入业务试点说明，最多输入2000字')}'">${sheetLink.linkBusinessDesc}</textarea>
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
        <td class="label"><bean:message bundle="businessoperation" key="businessoperation.linkOperateScheme"/>*</td>
        <td colspan='3'>
            <eoms:attachment idList="" name="sheetLink" scope="request" property="linkOperateScheme"
                             idField="${sheetPageName}linkOperateScheme" appCode="businessoperation"
                             alt="allowBlank:false"/>
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
    <%if (operateType.equals("91")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>"/>
    <tr>
        <td class="label">${eoms:a2u("是否调用变更")}</td>
        <td class="content max" colspan=3>
            <eoms:comboBox name="${sheetPageName}ifInvokeAgin" id="${sheetPageName}ifInvokeAgin" alt="allowBlank:false"
                           initDicId="10301" onchange="InvokeAgin(this.value);"/>
        </td>
    </tr>
    <tr id="invokeAgin" style="display:none">
        <td class="label">
            <bean:message bundle="businessoperation" key="businessoperation.linkNetType"/>*
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
        <td class="label"><bean:message bundle="businessoperation" key="businessoperation.linkTestResult"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkTestResult" id="${sheetPageName}linkTestResult"
                           initDicId="1010507" alt="allowBlank:false" styleClass="select-class"
                           defaultValue="${sheetLink.linkTestResult}"/>
        </td>
    </tr>
    <tr id="testfile" style="display:none">
        <td class="label"><bean:message bundle="businessoperation" key="businessoperation.linkAlterationAcc"/>*</td>
        <td colspan='3'>
            <eoms:attachment idList="" name="sheetLink" scope="request" property="linkAlterationAcc"
                             idField="${sheetPageName}linkAlterationAcc" appCode="businessoperation"
                             alt="allowBlank:false"/>
        </td>
    </tr>
    <%
            }
        }
    %>

    <%
        if (taskName.equals("refine")) {
            if (operateType.equals("92") || operateType.equals("11")) {
    %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="prepare"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>"/>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                   readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                   alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'${eoms:a2u("受理时限不能晚于处理时限")}',allowBlank:false"/>

        </td>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeCompleteLimit" readonly="readonly"
                   value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit"
                   alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'${eoms:a2u("处理时限不能早于受理时限")}',allowBlank:false"/>
        </td>
    </tr>

    <!--    <tr>
					    <td class="label">
					    	<bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
						</td>	
						<td colspan="3">					
					    <eoms:attachment name="tawSheetAccess" property="accesss" 
					            scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>			
					    </td>
		           </tr>		               
	               -->

    <tr>
        <td class="label"><bean:message bundle="businessoperation" key="businessoperation.linkTGPolicyAcc"/>*</td>
        <td colspan='3'>
            <eoms:attachment idList="" name="sheetLink" scope="request" property="linkTGPolicyAcc"
                             idField="${sheetPageName}linkTGPolicyAcc" appCode="businessoperation"
                             alt="allowBlank:false"/>
        </td>
    </tr>
    <%
            }
        }
    %>


    <%
        if (taskName.equals("prepare")) {
            if (operateType.equals("93") || operateType.equals("11")) {
    %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="report"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>"/>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                   readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                   alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'${eoms:a2u("受理时限不能晚于处理时限")}',allowBlank:false"/>

        </td>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeCompleteLimit" readonly="readonly"
                   value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit"
                   alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'${eoms:a2u("完成时限不能早于受理时限")}',allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businessoperation" key="businessoperation.linkDataFile"/></td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkDataFile" id="${sheetPageName}linkDataFile"
                      alt="width:500,allowBlank:true,maxLength:1000,vtext:'${eoms:a2u('请填入局数据资料，最多输入1000字')}'">${sheetLink.linkDataFile}</textarea>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businessoperation" key="businessoperation.linkVerify"/></td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkVerify" id="${sheetPageName}linkVerify"
                      alt="width:500,allowBlank:true,maxLength:1000,vtext:'${eoms:a2u('请填入设备功能验证，最多输入1000字')}'">${sheetLink.linkVerify}</textarea>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businessoperation" key="businessoperation.linkPassMan"/></td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkPassMan" id="${sheetPageName}linkPassMan"
                      alt="width:500,allowBlank:true,maxLength:1000,vtext:'${eoms:a2u('请填入口令管理的移交，最多输入1000字')}'">${sheetLink.linkPassMan}</textarea>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businessoperation" key="businessoperation.linkReport"/></td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkReport" id="${sheetPageName}linkReport"
                      alt="width:500,allowBlank:true,maxLength:1000,vtext:'${eoms:a2u('请填入初验报告，最多输入1000字')}'">${sheetLink.linkReport}</textarea>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businessoperation" key="businessoperation.linkWorkplan"/></td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}linkWorkplan" id="${sheetPageName}linkWorkplan"
                      alt="width:500,allowBlank:true,maxLength:1000,vtext:'${eoms:a2u('请填入作业计划制定情况，最多输入1000字')}'">${sheetLink.linkWorkplan}</textarea>
        </td>
    </tr>
    <!-- <tr>
					    <td class="label">
					    	<bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
						</td>	
						<td colspan="3">					
					    <eoms:attachment name="tawSheetAccess" property="accesss" 
					            scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>			
					    </td>
		           </tr>			          
		            -->
    <tr>
        <td class="label"><bean:message bundle="businessoperation" key="businessoperation.linkMeetingAcc"/>*</td>
        <td colspan='3'>
            <eoms:attachment idList="" name="sheetLink" scope="request" property="linkMeetingAcc"
                             idField="${sheetPageName}linkMeetingAcc" appCode="businessoperation"
                             alt="allowBlank:false"/>
        </td>
    </tr>
    <%
            }
        }
    %>
    <%
        if (taskName.equals("report")) {
            if (operateType.equals("94") || operateType.equals("11")) {
    %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="hold"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>"/>
    <input type="hidden" name="${sheetPageName}mainIsSuccess" id="${sheetPageName}mainIsSuccess"
           value="${sheetPageName}mainIsSuccess"/>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeAcceptLimit" id="${sheetPageName}nodeAcceptLimit"
                   readonly="readonly" value="${eoms:date2String(sheetLink.nodeAcceptLimit)}"
                   alt="vtype:'lessThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'${eoms:a2u("受理时限不能晚于处理时限")}',allowBlank:false"/>

        </td>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.completeLimit"/>*
        </td>
        <td class="content">
            <input class="text" onclick="popUpCalendar(this, this)" type="text"
                   name="${sheetPageName}nodeCompleteLimit" readonly="readonly"
                   value="${eoms:date2String(sheetLink.nodeCompleteLimit)}" id="${sheetPageName}nodeCompleteLimit"
                   alt="vtype:'moreThen',link:'${sheetPageName}nodeAcceptLimit',vtext:'${eoms:a2u("完成时限不能早于受理时限")}',allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="businessoperation" key="businessoperation.linkIsSuccess"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkIsSuccess" id="${sheetPageName}linkIsSuccess"
                           initDicId="1010513" alt="allowBlank:false" onchange="ifSuccess(this.value);"
                           defaultValue="${sheetLink.linkIsSuccess}" styleClass="select-class"/>
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
        <td class="label"><bean:message bundle="businessoperation" key="businessoperation.linkTGReportAcc"/>*</td>
        <td colspan='3'>
            <eoms:attachment idList="" name="sheetLink" scope="request" property="linkTGReportAcc"
                             idField="${sheetPageName}linkTGReportAcc" appCode="businessoperation"
                             alt="allowBlank:false"/>
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
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="allowBlank:false,width:500,vtext:'${eoms:a2u('请最多输入1000字')}'"
                          alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>

    <%} %>

    <%if (taskName.equals("hold")) {%>
    <%if (operateType.equals("18")) { %>

    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="taskOver"/>
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
            <textarea name="${sheetPageName}endResult" alt="allowBlank:false,vtext:'${eoms:a2u('请最多输入1000字')}'"
                      id="${sheetPageName}endResult" class="textarea max">${sheetMain.endResult}</textarea>
        </td>
    </tr>
    <%
            }
        }
    %>

    <%if (taskName.equals("operate") || taskName.equals("refine") || taskName.equals("report") || taskName.equals("prepare")) {%>

    <%if (operateType.equals("61")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>

    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="width:'500px',allowBlank:false,vtext:'${eoms:a2u('请最多输入1000字')}'">${sheetLink.remark}</textarea>
        </td>
    </tr>

    <%
            }
        }
    %>
</table>

<%if (taskName.equals("cc")) {%>
<fieldset id="link4">
    <eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'copyPerformer',childType:'user',limit:'none',text:'${eoms:a2u('抄送')}'}]"/>
</fieldset>
<%} %>


<%
    if (taskName.equals("operate")) {
        if (operateType.equals("91") && !ifInvoke.equals("no") && !ifInvoke.equals("")) {
%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="businessoperation" key="role.adminGroup"/>
    </legend>

    <eoms:chooser id="sendRole" type="role" roleId="222" flowId="023" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',allowBlank:false,text:'${eoms:a2u('派发')}',vtext:'${eoms:a2u('请选择派发对象')}'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'${eoms:a2u('抄送')}'}]"/>
</fieldset>
<%
        }
    }
%>

<%
    if (taskName.equals("refine")) {
        if (operateType.equals("92")) {
%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="businessoperation" key="role.businessSupportG"/>
    </legend>
    <%-- <div id="audit" display="none">
          <eoms:chooser id="sendRole" type="role" roleId="221" flowId="023" config="{returnJSON:true,showLeader:true}"
             category="[{id:'${sheetPageName}dealPerformer',allowBlank:false,text:'${eoms:a2u('派发')}',vtext:'${eoms:a2u('请选择派发对象')}'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'${eoms:a2u('抄送')}'}]"/>
        </div>--%>
</fieldset>
<%
        }
    }
%>


<%
    if (taskName.equals("report")) {
        if (operateType.equals("94")) {
%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="businessoperation" key="role.businessSender"/>
    </legend>

    <eoms:chooser id="sendRole" type="role" roleId="204" flowId="023" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',allowBlank:false,text:'${eoms:a2u('派发')}',vtext:'${eoms:a2u('请选择派发对象')}'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'${eoms:a2u('抄送')}'}]"/>
</fieldset>
<%
        }
    }
%>

<%
    if (taskName.equals("prepare")) {
        if (operateType.equals("93")) {
%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="businessoperation" key="role.vindicateGroup"/>

    </legend>

    <eoms:chooser id="sendRole" type="role" roleId="223" flowId="023" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',allowBlank:false,text:'${eoms:a2u('派发')}',vtext:'${eoms:a2u('请选择派发对象')}'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'${eoms:a2u('抄送')}'}]"/>
</fieldset>
<%
        }
    }
%>
	 
