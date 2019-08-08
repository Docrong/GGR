<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%

    request.setAttribute("roleId", "1970");


    long localTimes = com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
%>
<%@ include file="/WEB-INF/pages/wfworksheet/plannadjust/baseinputmainhtmlnew.jsp" %>
<script type="text/javascript">
    //selectLimit();
    function selectLimit() {
        Ext.Ajax.request({
            method: "get",
            url: "plannadjust.do?method=newShowLimit&flowName=PlannAdjust",
            success: function (x) {
                var o = eoms.JSONDecode(x.responseText);
                if ((o.acceptLimit == null || o.acceptLimit == "") && (o.replyLimit == null || o.replyLimit == "")) {
                    // $("sheetAcceptLimit").value = "";
                    // $('sheetCompleteLimit').value = "";
                } else {
                    var times =<%=localTimes%>;
                    var dt1 = new Date().add(Date.MINUTE, parseInt(o.acceptLimit, 10));
                    var dt2 = dt1.add(Date.MINUTE, parseInt(o.replyLimit, 10));
                    $("sheetAcceptLimit").value = dt1.format('Y-m-d H:i:s');
                    $('sheetCompleteLimit').value = dt2.format('Y-m-d H:i:s');
                }
            }
        });
    }
</script>
<input type="hidden" name="processTemplateName" value="PlannAdjust"/>
<input type="hidden" name="operateName" value="newWorkSheet"/>
<c:if test="${status!=1}">


    <input type="hidden" name="phaseId" id="phaseId" value="ConfirmTask"/>

    <input type="hidden" id="operateType" name="operateType" value="0"/>
    <input type="hidden" name="gatherPhaseId" id="gatherPhaseId" value="HoldTask"/>
</c:if>
<c:if test="${status==1}">
    <input type="hidden" name="phaseId" id="phaseId" value="OverTask"/>
</c:if>
<input type="hidden" name="beanId" value="iPlannAdjustMainManager"/>
<input type="hidden" name="mainClassName" value="com.boco.eoms.sheet.plannadjust.model.PlannAdjustMain"/>
<input type="hidden" name="linkClassName" value="com.boco.eoms.sheet.plannadjust.model.PlannAdjustLink"/>
<br>

<!-- 工单基本信息 -->
<table id="sheet" class="formTable">
    <tr>
        <td class="label">受理时限*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly"
                   id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"
                   onclick="popUpCalendar(this, this)"
                   alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
        </td>
        <td class="label">回复时限*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly"
                   id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"
                   onclick="popUpCalendar(this, this)"
                   alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label">
            <!-- 属地分公司 -->
            <bean:message bundle="plannadjust" key="plannAdjustMain.territorialBranch"/>*
        </td>
        <td>
            <eoms:id2nameDB id="${sheetMain.territorialBranch}" beanId="tawSystemDeptDao"/>
            <input type="hidden" name="territorialBranch" id="territorialBranch"
                   value="${sheetMain.territorialBranch}"/>
        </td>
        <td class="label">
            <!-- 行政区域 -->
            <bean:message bundle="plannadjust" key="plannAdjustMain.administrativeArea"/>*
        </td>
        <td>
            <input type="text" class="text" name="administrativeArea" id="administrativeArea"
                   alt="allowBlank:false,maxLength:2000,vtext:'请填入 行政区域 信息，最多输入 2000 字符'"
                   value="${sheetMain.administrativeArea}"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 申请时间 -->
            <bean:message bundle="plannadjust" key="plannAdjustMain.applicationTime"/>*
        </td>
        <td>
            <input type="text" class="text" name="applicationTime" readonly="readonly"
                   id="applicationTime" value="${eoms:date2String(sheetMain.applicationTime)}" alt="allowBlank:false"/>
        </td>
        <td class="label">
            <!-- 规划编号 -->
            <bean:message bundle="plannadjust" key="plannAdjustMain.planningNumber"/>*
        </td>
        <td>
            <input type="text" class="text" name="planningNumber" id="planningNumber"
                   alt="allowBlank:false,maxLength:1000,vtext:'请填入 规划编号 信息，最多输入 1000 字符'"
                   value="${sheetMain.planningNumber}"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 所属规划方案 -->
            <bean:message bundle="plannadjust" key="plannAdjustMain.planningScheme"/>*
        </td>
        <td>
            <input type="text" class="text" name="planningScheme" id="planningScheme"
                   alt="allowBlank:false,maxLength:2000,vtext:'请填入 所属规划方案 信息，最多输入 2000 字符'"
                   value="${sheetMain.planningScheme}"/>
        </td>
        <td class="label">
            <!-- 规划站经度 -->
            <bean:message bundle="plannadjust" key="plannAdjustMain.longitude"/>*
        </td>
        <td>
            <input type="text" class="text" name="longitude" id="longitude"
                   alt="allowBlank:false,maxLength:1000,vtext:'请填入 规划站经度 信息，最多输入 1000 字符'"
                   value="${sheetMain.longitude}"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 规划站纬度 -->
            <bean:message bundle="plannadjust" key="plannAdjustMain.latitude"/>*
        </td>
        <td>
            <input type="text" class="text" name="latitude" id="latitude"
                   alt="allowBlank:false,maxLength:1000,vtext:'请填入 规划站纬度 信息，最多输入 1000 字符'"
                   value="${sheetMain.latitude}"/>
        </td>
        <td class="label">
            <!-- 网元 -->
            <bean:message bundle="plannadjust" key="plannAdjustMain.netType"/>*
        </td>
        <td>
            <eoms:comboBox name="netType" id="netType"
                           initDicId="1013001"
                           defaultValue="${sheetMain.netType}" alt="allowBlank:false"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 所属专业 -->
            <bean:message bundle="plannadjust" key="plannAdjustMain.professional"/>*
        </td>
        <td>
            <eoms:comboBox name="professional" id="professional"
                           initDicId="1013002"
                           defaultValue="${sheetMain.professional}" alt="allowBlank:false"/>
        </td>
        <td class="label">
            <!-- 调整原因属性 -->
            <bean:message bundle="plannadjust" key="plannAdjustMain.reasonAdjust"/>*
        </td>
        <td>
            <eoms:comboBox name="reasonAdjust" id="reasonAdjust"
                           initDicId="1013003"
                           defaultValue="${sheetMain.reasonAdjust}" alt="allowBlank:false"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 调整原因属性说明 -->
            <bean:message bundle="plannadjust" key="plannAdjustMain.illustrate"/>*
        </td>
        <td>
            <input type="text" class="text" name="illustrate" id="illustrate"
                   alt="maxLength:2000,vtext:'请填入 调整原因属性说明 信息，最多输入 2000 字符'" value="${sheetMain.illustrate}"/>
        </td>
        <td class="label">
            <!-- 规划起始时间 -->
            <bean:message bundle="plannadjust" key="plannAdjustMain.planningStartTime"/>*
        </td>
        <td>
            <input type="text" class="text" name="planningStartTime" readonly="readonly"
                   id="planningStartTime" value="${eoms:date2String(sheetMain.planningStartTime)}"
                   onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 规划截至时间 -->
            <bean:message bundle="plannadjust" key="plannAdjustMain.planningEndTime"/>*
        </td>
        <td>
            <input type="text" class="text" name="planningEndTime" readonly="readonly"
                   id="planningEndTime" value="${eoms:date2String(sheetMain.planningEndTime)}"
                   onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>
        </td>
        <td class="label">
            <!-- 调整申请简要说明 -->
            <bean:message bundle="plannadjust" key="plannAdjustMain.briefDescription"/>*
        </td>
        <td>
            <input type="text" class="text" name="briefDescription" id="briefDescription"
                   alt="allowBlank:false,maxLength:2000,vtext:'请填入 调整申请简要说明 信息，最多输入 2000 字符'"
                   value="${sheetMain.briefDescription}"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 规划区域内投诉情况 -->
            <bean:message bundle="plannadjust" key="plannAdjustMain.planningAreaComplaint"/>*
        </td>
        <td>
            <eoms:comboBox name="planningAreaComplaint" id="planningAreaComplaint"
                           initDicId="10301"
                           defaultValue="${sheetMain.planningAreaComplaint}" alt="allowBlank:false"/>
        </td>
        <td class="label">
            <!-- 规划区域内覆盖需求 -->
            <bean:message bundle="plannadjust" key="plannAdjustMain.coverageRequirement"/>*
        </td>
        <td>
            <eoms:comboBox name="coverageRequirement" id="coverageRequirement"
                           initDicId="10301"
                           defaultValue="${sheetMain.coverageRequirement}" alt="allowBlank:false"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            规划区域内竞争对手覆盖情况
        </td>
        <td class="label">
            信号电平值*
        </td>
        <td class="label">
            基站位置*
        </td>
        <td class="label">
            距离规划区（米）*
        </td>
    </tr>

    <tr>
        <td>
            联通GSM
        </td>
        <td>
            <input type="text" class="text" name="gsmSignalLevel" id="gsmSignalLevel"
                   alt="allowBlank:false,maxLength:200,vtext:'请填入 GSM信号电平值 信息，最多输入 200 字符'"
                   value="${sheetMain.gsmSignalLevel}"/>
        </td>
        <td>
            <input type="text" class="text" name="gsmStationLocation" id="gsmStationLocation"
                   alt="allowBlank:false,maxLength:200,vtext:'请填入 GSM基站位置 信息，最多输入 200 字符'"
                   value="${sheetMain.gsmStationLocation}"/>
        </td>
        <td>
            <input type="text" class="text" name="gsmPlanningArea" id="gsmPlanningArea"
                   alt="allowBlank:false,maxLength:200,vtext:'请填入 GSM距离规划区(米) 信息，最多输入 200 字符'"
                   value="${sheetMain.gsmPlanningArea}"/>
        </td>
    </tr>

    <tr>
        <td>
            联通WCDMA
        </td>
        <td>
            <input type="text" class="text" name="wcdmaSignalLevel" id="wcdmaSignalLevel"
                   alt="allowBlank:false,maxLength:200,vtext:'请填入 WCDMA信号电平值 信息，最多输入 200 字符'"
                   value="${sheetMain.wcdmaSignalLevel}"/>
        </td>
        <td>
            <input type="text" class="text" name="wcdmaStationLocation" id="wcdmaStationLocation"
                   alt="allowBlank:false,maxLength:200,vtext:'请填入 WCDMA基站位置 信息，最多输入 200 字符'"
                   value="${sheetMain.wcdmaStationLocation}"/>
        </td>
        <td>
            <input type="text" class="text" name="wcdmaPlanningArea" id="wcdmaPlanningArea"
                   alt="allowBlank:false,maxLength:200,vtext:'请填入 WCDMA距离规划区(米) 信息，最多输入 200 字符'"
                   value="${sheetMain.wcdmaPlanningArea}"/>
        </td>
    </tr>

    <tr>
        <td>
            电信CDMA
        </td>
        <td>
            <input type="text" class="text" name="cdmaSignalLevel" id="cdmaSignalLevel"
                   alt="allowBlank:false,maxLength:200,vtext:'请填入 CDMA信号电平值 信息，最多输入 200 字符'"
                   value="${sheetMain.cdmaSignalLevel}"/>
        </td>
        <td>
            <input type="text" class="text" name="cdmaStationLocation" id="cdmaStationLocation"
                   alt="allowBlank:false,maxLength:200,vtext:'请填入 CDMA基站位置 信息，最多输入 200 字符'"
                   value="${sheetMain.cdmaStationLocation}"/>
        </td>
        <td>
            <input type="text" class="text" name="cdmaPlanningArea" id="cdmaPlanningArea"
                   alt="allowBlank:false,maxLength:200,vtext:'请填入 CDMA距离规划区(米) 信息，最多输入 200 字符'"
                   value="${sheetMain.cdmaPlanningArea}"/>
        </td>
    </tr>

</table>


<!-- 附件 -->
<table id="sheet" class="formTable">
    <!--附件模板-->
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
            相关调整申请说明凭证*
        </td>
        <td colspan="3">
            <eoms:attachment name="sheetMain" property="sheetAccessories"
                             scope="request" idField="sheetAccessories" appCode="plannadjust" alt="allowBlank:false"/>
        </td>
    </tr>
</table>


<!--派单树-->
<br/>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>：
        <span id="roleName">
		 	 规划站址调整管理员
		 </span>
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="sendObject" type="role" roleId="1971" flowId="618" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sheetMain.sendObject}"/>
    </div>
</fieldset>
<script type="text/javascript">
    function dispre(input) {
        var reasonAdjust = document.getElementById("reasonAdjust").value;
        var illustrate = document.getElementById("illustrate").value;
        if (reasonAdjust != null && reasonAdjust == '101300303' && illustrate == '') {
            alert("调整原因属性说明不能为空");
            return false;
        } else {
            return true;
        }
    }
</script>