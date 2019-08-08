<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ page import="com.boco.eoms.sheet.agentmaintenance.model.UserMade" %>
<%
    long localTimes = com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
%>
<%@ include file="/WEB-INF/pages/wfworksheet/agentmaintenance/baseinputmainhtmlnew.jsp" %>
<script type="text/javascript">
    //selectLimit();
    function selectLimit() {

    }


    //时限处理
    var d1 = '';
    if ('' != '${complaintMain.sheetCompleteLimit}') {
        d1 = '${complaintMain.sheetCompleteLimit}';
    } else if ('' != '${commontaskMain.sheetCompleteLimit}') {
        d1 = '${commontaskMain.sheetCompleteLimit}';
    } else {
        d1 = '${commonfaultMain.sheetCompleteLimit}';
    }
    if ('' != d1) {
        var d1s = d1.substring(0, 10).split('-');
        d1 = d1s[1] + '-' + d1s[2] + '-' + d1s[0] + ' ' + d1.substring(10, 19);
        var temp = new Date(Date.parse(d1));
        temp = temp.valueOf();
        temp = temp - 2 * 60 * 60 * 1000;
        temp = new Date(temp);
        $('${sheetPageName}sheetCompleteLimit').value = temp.format('Y-m-d H:i:s');
        $('${sheetPageName}sheetAcceptLimit').value = '';
    }

</script>
<input type="hidden" name="${sheetPageName}sheetTemplateName" value="AgentMaintenanceMainFlowProcess"/>
<input type="hidden" name="${sheetPageName}processTemplateName" value="AgentMaintenanceMainFlowProcess"/>
<input type="hidden" name="${sheetPageName}invokeMode" value="${sheetMain.invokeMode}"/>
<input type="hidden" name="${sheetPageName}operateName" value="newWorksheet"/>
<c:if test="${status!=1}">
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TaskCreateAuditHumTask"/>
    <input type="hidden" id="${sheetPageName}operateType" name="${sheetPageName}operateType"
           value="${sheetPageName}operateType"/>
</c:if>
<c:if test="${status==1}">
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverHumTask"/>
</c:if>
<input type="hidden" name="${sheetPageName}beanId" value="iAgentMaintenanceMainManager"/>
<input type="hidden" name="${sheetPageName}mainClassName"
       value="com.boco.eoms.sheet.agentmaintenance.model.AgentMaintenanceMain"/>
<input type="hidden" name="${sheetPageName}linkClassName"
       value="com.boco.eoms.sheet.agentmaintenance.model.AgentMaintenanceLink"/>
<input type="hidden" name="type" value="${type}"/>
<br>
<!-- sheet info -->
<table class="formTable">
    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.acceptLimit"/>*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly"
                   id="${sheetPageName}sheetAcceptLimit"
                   value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"
                   onclick="popUpCalendar(this, this,null,null,null,true,-1)"
                   alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
        </td>

        <td class="label"><bean:message bundle="sheet" key="mainForm.completeLimit"/>*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly"
                   id="${sheetPageName}sheetCompleteLimit"
                   value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"
                   onclick="popUpCalendar(this, this,null,null,null,true,-1)"
                   alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>
        </td>
    </tr>
</table>
<br>
<table style="border: 0px" width="100%">
    <tr id="commonfault">
        <input type="hidden" name="sourceId" id="sourceId" value="${sourceId}">
        <td colspan="4">
            <table class="formTable">
                <tr>
                    <td class="label">
                        <!-- 网管告警ID -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainFaultAlarmId"/>*
                    </td>
                    <td>
                        <input type="text" class="text" name="mainFaultAlarmId" id="mainFaultAlarmId"
                               alt="allowBlank:false,maxLength:100,vtext:'请填入 网管告警ID 信息，最多输入 100 字符'"
                               value="${commonfaultMain.mainAlarmId}${sheetMain.mainFaultAlarmId}"/>
                    </td>
                    <td class="label">
                        <!-- 网管告警流水号 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainFaultAlarmNum"/>*
                    </td>
                    <td>
                        <input type="text" class="text" name="mainFaultAlarmNum" id="mainFaultAlarmNum"
                               alt="allowBlank:false,maxLength:100,vtext:'请填入 网管告警流水号 信息，最多输入 100 字符'"
                               value="${commonfaultMain.mainAlarmNum}${sheetMain.mainFaultAlarmNum}"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 告警清除时间 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainFaultAlarmSolveDate"/>*
                    </td>
                    <td>
                        <input type="text" class="text" name="mainFaultAlarmSolveDate" readonly="readonly"
                               id="mainFaultAlarmSolveDate"
                               value="${commonfaultMain.mainAlarmSolveDate}${eoms:date2String(sheetMain.mainFaultAlarmSolveDate)}"
                               onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt="allowBlank:false"/>
                    </td>
                    <td class="label">
                        <!-- 告警来源 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainFaultAlarmSource"/>*
                    </td>
                    <td>
                        <input type="text" class="text" name="mainFaultAlarmSource" id="mainFaultAlarmSource"
                               alt="allowBlank:false,maxLength:100,vtext:'请填入 告警来源 信息，最多输入 100 字符'"
                               value="${commonfaultMain.mainAlarmSource}${sheetMain.mainFaultAlarmSource}"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 告警级别 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainFaultAlarmLevel"/>*
                    </td>
                    <td>
                        <input type="text" class="text" name="mainFaultAlarmLevel" id="mainFaultAlarmLevel"
                               alt="allowBlank:false,maxLength:100,vtext:'请填入 告警级别 信息，最多输入 100 字符'"
                               value="${commonfaultMain.mainAlarmLevel}${sheetMain.mainFaultAlarmLevel}"/>
                    </td>
                    <td class="label">
                        <!-- 告警逻辑分类 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainFaultAlarmLogicSort"/>*
                    </td>
                    <td>
                        <input type="text" class="text" name="mainFaultAlarmLogicSort" id="mainFaultAlarmLogicSort"
                               alt="allowBlank:false,maxLength:100,vtext:'请填入 告警逻辑分类 信息，最多输入 100 字符'"
                               value="${commonfaultMain.mainAlarmLogicSort}${sheetMain.mainFaultAlarmLogicSort}"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 告警逻辑子类 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainFaultAlarmLogicSortSub"/>*
                    </td>
                    <td>
                        <input type="text" class="text" name="mainFaultAlarmLogicSortSub"
                               id="mainFaultAlarmLogicSortSub"
                               alt="allowBlank:false,maxLength:100,vtext:'请填入 告警逻辑子类 信息，最多输入 100 字符'"
                               value="${commonfaultMain.mainAlarmLogicSortSub}${sheetMain.mainFaultAlarmLogicSortSub}"/>
                    </td>
                    <td class="label">
                        <!-- 故障描述 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainFaultDesc"/>*
                    </td>
                    <td>
		  		<textarea name="mainAlarmDesc" id="mainAlarmDesc" class="textarea max"
                          alt="allowBlank:ture,width:500,maxLength:1000,vtext:'最多输入1000汉字'">${sheetMain.mainAlarmDesc}</textarea>
                        <input type="text" class="text" name="mainFaultDesc" id="mainFaultDesc"
                               alt="allowBlank:false,maxLength:100,vtext:'请填入 故障描述 信息，最多输入 100 字符'"
                               value="${commonfaultMain.mainAlarmDesc}${sheetMain.mainFaultDesc}"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 故障发现方式 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainFaultDiscoverableMode"/>*
                    </td>
                    <td>
                        <eoms:comboBox name="mainFaultDiscoverableMode" id="mainFaultDiscoverableMode"
                                       initDicId="1010301"
                                       defaultValue="${commonfaultMain.mainFaultDiscoverableMode}${sheetMain.mainFaultDiscoverableMode}"
                                       alt="allowBlank:false"/>
                    </td>
                    <td class="label">
                        <!-- 故障处理响应级别 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainFaultResponseLevel"/>*
                    </td>
                    <td>
                        <eoms:comboBox name="mainFaultResponseLevel" id="mainFaultResponseLevel" initDicId="1010304"
                                       defaultValue="${commonfaultMain.mainFaultResponseLevel}${sheetMain.mainFaultResponseLevel}"
                                       alt="allowBlank:false"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 网络分类(一级) -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainFaultNetSortOne"/>*
                    </td>
                    <td>
                        <eoms:comboBox name="mainFaultNetSortOne" id="mainFaultNetSortOne"
                                       sub="mainFaultNetSortTwo" initDicId="1010104"
                                       defaultValue="${commonfaultMain.mainNetSortOne}${sheetMain.mainFaultNetSortOne}"
                                       alt="allowBlank:false"/>
                    </td>
                    <td class="label">
                        <!-- 网络分类(二级) -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainFaultNetSortTwo"/>*
                    </td>
                    <td>
                        <eoms:comboBox name="mainFaultNetSortTwo" id="mainFaultNetSortTwo" sub="mainFaultNetSortThree"
                                       initDicId="${commonfaultMain.mainNetSortOne}${sheetMain.mainFaultNetSortOne}"
                                       defaultValue="${commonfaultMain.mainNetSortTwo}${sheetMain.mainFaultNetSortTwo}"
                                       alt="allowBlank:false"/>
                    </td>
                </tr>

                <tr>
                    <td class="label">
                        <!-- 网络分类(三级) -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainFaultNetSortThree"/>*
                    </td>
                    <td>
                        <eoms:comboBox name="mainFaultNetSortThree" id="mainFaultNetSortThree"
                                       initDicId="${commonfaultMain.mainNetSortTwo}${sheetMain.mainFaultNetSortTwo}"
                                       defaultValue="${commonfaultMain.mainNetSortThree}${sheetMain.mainFaultNetSortThree}"
                                       alt="allowBlank:false"/>
                    </td>
                    <td class="label">
                        <!-- 派单方式 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainFaultSendMode"/>*
                    </td>
                    <c:if test="${empty sheetMain.mainFaultSendMode && empty commonfaultMain.mainSendMode}">
                        <td>
                            <input type="hidden" name="mainFaultSendMode" id="mainFaultSendMode" value="101030502"/>
                            <eoms:id2nameDB id="101030502" beanId="ItawSystemDictTypeDao"/>
                        </td>
                    </c:if>
                    <c:if test="${!empty sheetMain.mainFaultSendMode || !empty commonfaultMain.mainSendMode}">
                        <td>
                            <eoms:comboBox name="mainFaultSendMode" id="mainFaultSendMode" initDicId="1010305"
                                           defaultValue="${commonfaultMain.mainSendMode}${sheetMain.mainFaultSendMode}"/>
                        </td>
                    </c:if>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 故障设备厂商 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainFaultEquipmentFactory"/>*
                    </td>
                    <td>
                        <eoms:comboBox name="mainFaultEquipmentFactory" id="mainFaultEquipmentFactory"
                                       initDicId="1010103"
                                       defaultValue="${commonfaultMain.mainEquipmentFactory}${sheetMain.mainFaultEquipmentFactory}"
                                       alt="allowBlank:false"/>
                    </td>
                    <td class="label">
                        <!-- 网元名称 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainFaultNetName"/>*
                    </td>
                    <td>
                        <input type="text" class="text" name="mainFaultNetName" id="mainFaultNetName"
                               alt="allowBlank:false,maxLength:100,vtext:'请填入 网元名称 信息，最多输入 100 字符'"
                               value="${commonfaultMain.mainNetName}${sheetMain.mainFaultNetName}"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 故障设备型号 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainFaultEquipmentModel"/>*
                    </td>
                    <td>
                        <input type="text" class="text" name="mainFaultEquipmentModel" id="mainFaultEquipmentModel"
                               alt="allowBlank:false,maxLength:100,vtext:'请填入 故障设备型号 信息，最多输入 100 字符'"
                               value="${commonfaultMain.mainEquipmentModel}${sheetMain.mainFaultEquipmentModel}"/>
                    </td>
                    <td class="label">
                        <!-- 故障发生时间 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainFaultGenerantTime"/>*
                    </td>
                    <td>
                        <input type="text" class="text" name="mainFaultGenerantTime" readonly="readonly"
                               id="mainFaultGenerantTime"
                               value="${eoms:date2String(commonfaultMain.mainFaultGenerantTime)}${eoms:date2String(sheetMain.mainFaultGenerantTime)}"
                               onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt="allowBlank:false"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 故障省份 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainFaultGenerantPriv"/>*
                    </td>
                    <td>
                        <input type="text" name="mainFaultGenerantPriv" id="mainFaultGenerantPriv"
                               value="${commonfaultMain.mainFaultGenerantPriv}${sheetMain.mainFaultGenerantPriv}"
                               alt="allowBlank:false"/>
                    </td>
                    <td class="label">
                        <!-- 故障地市 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainFaultGenerantCity"/>*
                    </td>
                    <td>
                        <div id="areaview" class="hide"></div>
                        <input type="text" class="text" readonly="readonly" name="mainFaultGenerantCity"
                               id="mainFaultGenerantCity" alt="allowBlank:false,vtext:'请选择地域名称'"
                               value="<eoms:id2nameDB id='${commonfaultMain.toDeptId}${sheetMain.toDeptId}' beanId='tawSystemAreaDao'/>"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 是否影响业务 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainFaultIfAffectOperation"/>*
                    </td>
                    <td>
                        <eoms:comboBox name="mainFaultIfAffectOperation" id="mainFaultIfAffectOperation"
                                       initDicId="10301"
                                       defaultValue="${commonfaultMain.mainIfAffectOperation}${sheetMain.mainFaultIfAffectOperation}"
                                       alt="allowBlank:false"/>
                    </td>
                    <td class="label">
                        <!-- 相关投诉处理工单号 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainFaultApplySheetId"/>*
                    </td>
                    <td>
                        <input type="text" class="text" name="mainFaultApplySheetId" id="mainFaultApplySheetId"
                               alt="allowBlank:false,maxLength:100,vtext:'请填入 相关投诉处理工单号 信息，最多输入 100 字符'"
                               value="${commonfaultMain.mainApplySheetId}${sheetMain.mainFaultApplySheetId}"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 是否已预处理 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainFaultPretreatment"/>*
                    </td>
                    <td>
                        <eoms:comboBox name="mainFaultPretreatment" id="mainFaultPretreatment" initDicId="10301"
                                       defaultValue="${commonfaultMain.mainPretreatment}${sheetMain.mainFaultPretreatment}"
                                       alt="allowBlank:false" onchange="ifDoT1Deal(this.value)"/>
                    </td>
                    <td class="label">
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr id="commontask">
        <input type="hidden" name="sourceId" id="sourceId" value="${sourceId }">
        <td colspan="4">
            <table class="formTable">
                <tr>
                    <td class="label">
                        <!-- 网络分类一级类别 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainTaskNetSort1"/>*
                    </td>
                    <td>
                        <eoms:comboBox name="mainTaskNetSort1" id="mainTaskNetSort1" initDicId="1010104"
                                       sub="mainTaskNetSort2" alt="allowBlank:false"
                                       defaultValue="${commontaskMain.mainNetSort1}${sheetMain.mainTaskNetSort1}"
                                       styleClass="select-class"/>
                    </td>
                    <td class="label">
                        <!-- 网络分类二级类别 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainTaskNetSort2"/>*
                    </td>
                    <td>
                        <eoms:comboBox name="mainTaskNetSort2" id="mainTaskNetSort2"
                                       initDicId="${commontaskMain.mainNetSort1}${sheetMain.mainTaskNetSort1}"
                                       sub="mainTaskNetSort3" alt="allowBlank:false"
                                       defaultValue="${commontaskMain.mainNetSort2}${sheetMain.mainTaskNetSort2}"
                                       styleClass="select-class"/>
                    </td>
                </tr>

                <tr>
                    <td class="label">
                        <!-- 网络分类三级类别 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainTaskNetSort3"/>*
                    </td>
                    <td>
                        <eoms:comboBox name="mainTaskNetSort3" id="mainTaskNetSort3"
                                       initDicId="${commontaskMain.mainNetSort2}${sheetMain.mainTaskNetSort2}"
                                       alt="allowBlank:false"
                                       defaultValue="${commontaskMain.mainNetSort3}${sheetMain.mainTaskNetSort3}"
                                       styleClass="select-class"/>
                    </td>
                    <td class="label">
                        <!-- 任务类型 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainTaskType"/>*
                    </td>
                    <td>
                        <eoms:comboBox name="mainTaskType" id="mainTaskType" initDicId="1010102" alt="allowBlank:false"
                                       defaultValue="${commontaskMain.mainTaskType}${sheetMain.mainTaskType}"
                                       styleClass="select-class"/>
                    </td>
                </tr>

                <tr>
                    <td class="label">
                        <!-- 任务描述 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainTaskDescription"/>*
                    </td>
                    <td colspan="3">
				<textarea class="textarea max" name="mainTaskDescription" id="mainTaskDescription"
                          alt="allowBlank:false,maxLength:2000,vtext:'请输入任务描述，多输入100汉字'">${commontaskMain.mainTaskDescription}${sheetMain.mainTaskDescription}</textarea>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <tr id="complaint">
        <input type="hidden" name="sourceId" id="sourceId" value="${sourceId }">
        <td colspan="4">
            <table class="formTable">
                <tr>
                    <td class="label">
                        <!-- 紧急程度 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainComurgentDegree"/>*
                    </td>
                    <td>
                        <eoms:comboBox name="mainComurgentDegree" id="mainComurgentDegree" initDicId="1010606"
                                       defaultValue="${complaintMain.urgentDegree}${sheetMain.mainComurgentDegree}"
                                       alt="allowBlank:ture"/>
                    </td>
                    <td class="label">
                        <!-- 投诉分类一级类别 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainComType1"/>*
                    </td>
                    <td>
                        <eoms:comboBox name="mainComType1" id="mainComType1" sub="mainComType2" initDicId="1010601"
                                       styleClass="select-class"
                                       defaultValue="${complaintMain.complaintType1}${sheetMain.mainComType1}"
                                       alt="allowBlank:ture" onchange="selectLimit(this.value);"/>
                    </td>
                </tr>

                <tr>
                    <td class="label">
                        <!-- 投诉分类二级类别 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainComType2"/>*
                    </td>
                    <td>
                        <eoms:comboBox name="mainComType2" id="mainComType2" sub="mainComType"
                                       initDicId="${complaintMain.complaintType1}${sheetMain.mainComType1}"
                                       styleClass="select-class"
                                       defaultValue="${complaintMain.complaintType2}${sheetMain.mainComType2}"
                                       alt="allowBlank:ture" onchange="selectLimit(this.value);"/>
                    </td>
                    <td class="label">
                        <!-- 投诉分类三级类别 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainComType"/>*
                    </td>
                    <td>
                        <eoms:comboBox name="mainComType" id="mainComType" sub="mainComType4"
                                       initDicId="${complaintMain.complaintType2}${sheetMain.mainComType2}"
                                       styleClass="select-class"
                                       defaultValue="${complaintMain.complaintType}${sheetMain.mainComType}"
                                       alt="allowBlank:true" onchange="selectLimit(this.value);"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 投诉分类四级类别 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainComType4"/>*
                    </td>
                    <td>
                        <eoms:comboBox name="mainComType4" id="mainComType4" sub="mainComType5"
                                       initDicId="${complaintMain.complaintType}${sheetMain.mainComType}"
                                       styleClass="select-class"
                                       defaultValue="${complaintMain.complaintType4}${sheetMain.mainComType4}"
                                       alt="allowBlank:true"/>
                    </td>
                    <td class="label">
                        <!-- 投诉分类五级类别 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainComType5"/>*
                    </td>
                    <td>
                        <eoms:comboBox name="mainComType5" id="mainComType5" sub="mainComType6"
                                       initDicId="${complaintMain.complaintType4}${sheetMain.mainComType4}"
                                       styleClass="select-class"
                                       defaultValue="${complaintMain.complaintType5}${sheetMain.mainComType5}"
                                       alt="allowBlank:ture"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 投诉分类六级类别 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainComType6"/>*
                    </td>
                    <td>
                        <eoms:comboBox name="mainComType6" id="mainComType6" sub="mainComType7"
                                       initDicId="${complaintMain.complaintType5}${sheetMain.mainComType5}"
                                       styleClass="select-class"
                                       defaultValue="${complaintMain.complaintType6}${sheetMain.mainComType6}"
                                       alt="allowBlank:ture"/>
                    </td>
                    <td class="label">
                        <!-- 投诉分类七级类别 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainComType7"/>*
                    </td>
                    <td>
                        <eoms:comboBox name="mainComType7" id="mainComType7"
                                       initDicId="${complaintMain.complaintType6}${sheetMain.mainComType6}"
                                       styleClass="select-class"
                                       defaultValue="${complaintMain.complaintType7}${sheetMain.mainComType7}"
                                       alt="allowBlank:ture"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 派发联系人 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainCombtype1"/>*
                    </td>
                    <td>
                        <input type="text" class="text" name="mainCombtype1" id="mainCombtype1"
                               alt="allowBlank:false,maxLength:100,vtext:'请填入 派发联系人 信息，最多输入 100 字符'"
                               value="${complaintMain.btype1}${sheetMain.mainCombtype1}"/>
                    </td>
                    <td class="label">
                        <!-- 派发联系人电话 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainCombdeptContact"/>*
                    </td>
                    <td>
                        <input type="text" class="text" name="mainCombdeptContact" id="mainCombdeptContact"
                               alt="allowBlank:false,maxLength:100,vtext:'请填入 派发联系人电话 信息，最多输入 100 字符'"
                               value="${complaintMain.bdeptContact}${sheetMain.mainCombdeptContact}"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 是否大面积投诉 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainCombdeptContactPhone"/>*
                    </td>
                    <td>
                        <eoms:comboBox name="mainCombdeptContactPhone" id="mainCombdeptContactPhone" initDicId="10301"
                                       defaultValue="${complaintMain.bdeptContactPhone}${sheetMain.mainCombdeptContactPhone}"
                                       alt="allowBlank:true"/>
                    </td>
                    <td class="label">
                        <!-- 重复投诉次数 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainrepeatComTimes"/>*
                    </td>
                    <td>
                        <input type="text" class="text" name="mainrepeatComTimes" id="mainrepeatComTimes"
                               alt="allowBlank:false,maxLength:100,vtext:'请填入 重复投诉次数 信息，最多输入 100 字符'"
                               value="${complaintMain.repeatComplaintTimes}${sheetMain.mainrepeatComTimes}"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 客户姓名 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainComcustomerName"/>*
                    </td>
                    <td>
                        <input type="text" class="text" name="mainComcustomerName" id="mainComcustomerName"
                               alt="allowBlank:false,maxLength:100,vtext:'请填入 客户姓名 信息，最多输入 100 字符'"
                               value="${complaintMain.customerName}${sheetMain.mainComcustomerName}"/>
                    </td>
                    <td class="label">
                        <!-- 客户电话 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainComcustomPhone"/>*
                    </td>
                    <td>
                        <input type="text" class="text" name="mainComcustomPhone" id="mainComcustomPhone"
                               alt="allowBlank:false,maxLength:100,vtext:'请填入 客户电话 信息，最多输入 100 字符'"
                               value="${complaintMain.customPhone}${sheetMain.mainComcustomPhone}"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 客户品牌 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainComcustomBrand"/>*
                    </td>
                    <td>
                        <eoms:comboBox name="mainComcustomBrand" id="mainComcustomBrand" initDicId="1010604"
                                       defaultValue="${complaintMain.customBrand}${sheetMain.mainComcustomBrand}"
                                       alt="allowBlank:true"/>
                    </td>
                    <td class="label">
                        <!-- 投诉受理省份 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainComstartDealCity"/>*
                    </td>
                    <td>
                        <div id="areaview1" class="hide"></div>
                        <script type="text/javascript">
                            var areaViewer = new Ext.JsonView("areaview1",
                                '<div class="viewlistitem-{nodeType}">{name}</div>',
                                {
                                    emptyText: '<div>没有选择项目</div>'
                                }
                            );
                            var data = "[{id:'${sheetMain.toDeptId}',name:'<eoms:id2nameDB id='${sheetMain.toDeptId}' beanId='tawSystemAreaDao'/>',nodeType:'area'}]";
                            areaViewer.jsonData = eoms.JSONDecode(data);
                            areaViewer.refresh();
                            var deptTreeAction = '${app}/xtree.do?method=areaTree';
                            deptetree = new xbox({
                                btnId: '${sheetPageName}showDept1',
                                dlgId: 'dlg3',
                                treeDataUrl: deptTreeAction,
                                treeRootId: '-1',
                                treeRootText: '地市',
                                treeChkMode: 'single',
                                treeChkType: 'area',
                                showChkFldId: '${sheetPageName}showDept1',
                                saveChkFldId: '${sheetPageName}toDeptId',
                                viewer: areaViewer
                            });
                        </script>
                        <input type="text" class="text" readonly="readonly" name="${sheetPageName}showDept1"
                               id="${sheetPageName}showDept1" alt="allowBlank:false,vtext:'请选择地域名称'"
                               value="<eoms:id2nameDB id='${complaintMain.toDeptId}${sheetMain.toDeptId}' beanId='tawSystemAreaDao'/>"/>
                        <input type="hidden" name="${sheetPageName}toDeptId" id="${sheetPageName}toDeptId"
                               value="${complaintMain.toDeptId}${sheetMain.toDeptId}"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 用户归属地 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainComcustomAttribution"/>*
                    </td>
                    <td>
                        <input type="text" class="text" name="mainComcustomAttribution" id="mainComcustomAttribution"
                               alt="allowBlank:false,maxLength:100,vtext:'请填入 用户归属地 信息，最多输入 100 字符'"
                               value="${complaintMain.customAttribution}${sheetMain.mainComcustomAttribution}"/>
                    </td>
                    <td class="label">
                        <!-- 故障时间 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainComfaultTime"/>*
                    </td>
                    <td>
                        <input type="text" class="text" name="mainComfaultTime" readonly="readonly"
                               id="mainComfaultTime"
                               value="${complaintMain.faultTime}${eoms:date2String(sheetMain.mainComfaultTime)}"
                               onclick="popUpCalendar(this, this)"
                               alt="vtype:'lessThen',link:'mainComTime',vtext:'故障时间不能晚于投诉时间',allowBlank:false"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 投诉时间 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainComTime"/>*
                    </td>
                    <td>
                        <input type="text" class="text" name="mainComTime" readonly="readonly" id="mainComTime"
                               value="${complaintMain.complaintTime}${eoms:date2String(sheetMain.mainComTime)}"
                               onclick="popUpCalendar(this, this)"
                               alt="vtype:'moreThen',link:'mainComfaultTime',vtext:'投诉时间不能早于故障时间',allowBlank:false"/>
                    </td>
                    <td class="label">
                        <!-- 故障号码 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainComtNum"/>*
                    </td>
                    <td>
                        <input type="text" class="text" name="mainComtNum" id="mainComtNum"
                               alt="allowBlank:false,maxLength:100,vtext:'请填入 故障号码 信息，最多输入 100 字符'"
                               value="${complaintMain.complaintNum}${sheetMain.mainComtNum}"/>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 故障地点 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainComfaultSite"/>*
                    </td>
                    <td>
                        <input type="text" class="text" name="mainComfaultSite" id="mainComfaultSite"
                               alt="allowBlank:false,maxLength:100,vtext:'请填入 故障地点 信息，最多输入 100 字符'"
                               value="${complaintMain.faultSite}${sheetMain.mainComfaultSite}"/>
                    </td>
                    <td class="label">
                    </td>
                    <td>
                    </td>
                <tr>
                    <td class="label">
                        <!-- 终端描述 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainComterminalType"/>*
                    </td>
                    <td colspan="3">
				<textarea name="mainComterminalType" id="mainComterminalType" class="textarea max"
                          alt="allowBlank:true,maxLength:2000,vtext:'终端描述，最多输入1000汉字'">
                    ${complaintMain.terminalType}${sheetMain.mainComterminalType}</textarea>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 投诉内容 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainComDesc"/>*
                    </td>
                    <td colspan="3">
				<textarea name="mainComDesc" id="mainComDesc" class="textarea max"
                          alt="allowBlank:true,maxLength:2000,vtext:'投诉内容，最多输入1000汉字'">
                    ${complaintMain.complaintDesc}${sheetMain.mainComDesc}</textarea>
                    </td>
                </tr>
                <tr>
                    <td class="label">
                        <!-- 预处理情况 -->
                        <bean:message bundle="agentmaintenance" key="agentMaintenanceMain.mainCompreDealResult"/>*
                    </td>
                    <td colspan="3">
				<textarea name="mainCompreDealResult" id="mainCompreDealResult" class="textarea max"
                          alt="allowBlank:true,maxLength:2000,vtext:'预处理情况，最多输入1000汉字'">
                    ${complaintMain.preDealResult}${sheetMain.mainCompreDealResult}</textarea>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>

<br/>
<c:if test="${status!=1}">
    <fieldset>
        <legend>
            <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
            <span id="roleName">:任务创建审批人
			</span>
        </legend>
        <div class="x-form-item">
            <%
                int total = Integer.parseInt(request.getAttribute("total").toString());
                if (total == 0) {
            %>

            <eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
                          category="[{id:'dealPerformer',childType:'user,dept',limit:'none',text:'派发',allowBlank:false,vtext:'请选择任务执行人'}]"
                          panels="[{text:'部门与人员',dataUrl:'/xtree.do?method=userFromDept'}]"
                          data="${sendUserAndRoles}"/></div>
        <%
        } else {
            UserMade userMade = (UserMade) request.getAttribute("userMade");
            String[] users = userMade.getUsertoRole().split(",");
            for (int i = 0; i < users.length; i++) {
        %>
        <input type="checkbox" name="user" value="<%=users[i]%>" onclick="addDealPerformer()"><eoms:id2nameDB
            id="<%=users[i]%>" beanId="tawSystemUserDao"/><br>
        <%
            }
            String[] depts = userMade.getUsertoDept().split(",");
            for (int i = 0; i < depts.length; i++) {
        %>
        <input type="checkbox" name="dept" value="<%=depts[i]%>" onclick="addDealPerformer()"><eoms:id2nameDB
            id="<%=depts[i]%>" beanId="tawSystemDeptDao"/><br>

        <%}%>
        <input type="hidden" name="dealPerformer" id="dealPerformer" value="">
        <input type="hidden" name="dealPerformerLeader" id="dealPerformerLeader" value="">
        <input type="hidden" name="dealPerformerType" id="dealPerformerType" value="">
        <%}%>
    </fieldset>
</c:if>
<script type="text/javascript">
    var v1 = eoms.form;

    function addDealPerformer() {
        var dealPerformer = "";
        var dealPerformerType = "";
        var arr = document.getElementsByName("user");
        for (var i = 0; i < arr.length; i++) {
            if (arr[i].type == "checkbox" && arr[i].checked) {
                if (dealPerformer.length > 0) {
                    dealPerformer = dealPerformer + ",";
                    dealPerformerType = dealPerformerType + ",";
                }
                dealPerformer = dealPerformer + arr[i].value;
                dealPerformerType = dealPerformerType + "user";
            }
        }
        var arr1 = document.getElementsByName("dept");
        for (var i = 0; i < arr1.length; i++) {
            if (arr1[i].type == "checkbox" && arr1[i].checked) {
                if (dealPerformer.length > 0) {
                    dealPerformer = dealPerformer + ",";
                    dealPerformerType = dealPerformerType + ",";
                }
                dealPerformer = dealPerformer + arr1[i].value;
                dealPerformerType = dealPerformerType + "dept";
            }
        }
        $('dealPerformer').value = dealPerformer;
        $('dealPerformerLeader').value = dealPerformer;
        $('dealPerformerType').value = dealPerformerType;
    }

    Ext.onReady(function () {
        if ('${type}' == 'commonfault') {
            v1.enableArea('commonfault');
            v1.disableArea('commontask', true);
            v1.disableArea('complaint', true);
        }
        if ('${type}' == 'commontask') {
            v1.enableArea('commontask');
            v1.disableArea('commonfault', true);
            v1.disableArea('complaint', true);
        }
        if ('${type}' == 'complaint') {
            v1.enableArea('complaint');
            v1.disableArea('commontask', true);
            v1.disableArea('commonfault', true);
        }
    });
</script>