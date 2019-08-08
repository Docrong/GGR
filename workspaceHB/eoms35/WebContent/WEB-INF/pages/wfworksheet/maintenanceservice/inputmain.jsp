<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%

    request.setAttribute("roleId", "1760");


    long localTimes = com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
%>
<%@ include file="/WEB-INF/pages/wfworksheet/maintenanceservice/baseinputmainhtmlnew.jsp" %>
<script type="text/javascript">
    //selectLimit();
    function selectLimit() {
        Ext.Ajax.request({
            method: "get",
            url: "maintenanceservice.do?method=newShowLimit&flowName=MaintenanceService",
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
<input type="hidden" name="processTemplateName" value="MaintenanceService"/>
<input type="hidden" name="operateName" value="newWorkSheet"/>
<c:if test="${status!=1}">


    <input type="hidden" name="phaseId" id="phaseId" value="Examine"/>

    <input type="hidden" id="operateType" name="operateType" value="0"/>
    <input type="hidden" name="gatherPhaseId" id="gatherPhaseId" value="HoldTask"/>
</c:if>
<c:if test="${status==1}">
    <input type="hidden" name="phaseId" id="phaseId" value="OverTask"/>
</c:if>
<input type="hidden" name="beanId" value="iMaintenanceServiceMainManager"/>
<input type="hidden" name="mainClassName" value="com.boco.eoms.sheet.maintenanceservice.model.MaintenanceServiceMain"/>
<input type="hidden" name="linkClassName" value="com.boco.eoms.sheet.maintenanceservice.model.MaintenanceServiceLink"/>
<br>

<!-- 工单基本信息 -->
<table id="sheet" class="formTable">

    <tr>
        <td class="label">受理时限*</td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly"
                   id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"
                   onclick="popUpCalendar(this, this)"
                   alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"
            />

        </td>
        <td class="label">处理时限*</td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly"
                   id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"
                   onclick="popUpCalendar(this, this)"
                   alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"
            />
        </td>
    </tr>
    <tr>
        <td class="label">
            <!-- 种类 -->
            <bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainType"/>*
        </td>
        <td>
            <input type="text" class="text" name="mainType" id="mainType"
                   alt="allowBlank:false,maxLength:2000,vtext:'请填入 种类 信息，最多输入 2000 字符'" value="${sheetMain.mainType}"/>
        </td>
        <td class="label">
            <!-- 序号 -->
            <bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainSerialNumber"/>*
        </td>
        <td>
            <input type="text" class="text" name="mainSerialNumber" id="mainSerialNumber"
                   alt="allowBlank:false,maxLength:2000,vtext:'请填入 序号 信息，最多输入 2000 字符'"
                   value="${sheetMain.mainSerialNumber}"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 设备名称 -->
            <bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainDeviceName"/>*
        </td>
        <td>
            <input type="text" class="text" name="mainDeviceName" id="mainDeviceName"
                   alt="allowBlank:false,maxLength:2000,vtext:'请填入 设备名称 信息，最多输入 2000 字符'"
                   value="${sheetMain.mainDeviceName}"/>
        </td>
        <td class="label">
            <!-- 设备厂家 -->
            <bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainManufacturers"/>*
        </td>
        <td>
            <input type="text" class="text" name="mainManufacturers" id="mainManufacturers"
                   alt="allowBlank:false,maxLength:2000,vtext:'请填入 设备厂家 信息，最多输入 2000 字符'"
                   value="${sheetMain.mainManufacturers}"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 系统名称 -->
            <bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainSystemName"/>*
        </td>
        <td>
            <input type="text" class="text" name="mainSystemName" id="mainSystemName"
                   alt="allowBlank:false,maxLength:2000,vtext:'请填入 系统名称 信息，最多输入 2000 字符'"
                   value="${sheetMain.mainSystemName}"/>
        </td>
        <td class="label">
            <!-- 设备型号 -->
            <bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainEquipmentType"/>*
        </td>
        <td>
            <input type="text" class="text" name="mainEquipmentType" id="mainEquipmentType"
                   alt="allowBlank:false,maxLength:2000,vtext:'请填入 设备型号 信息，最多输入 2000 字符'"
                   value="${sheetMain.mainEquipmentType}"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 设备序列号 -->
            <bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainDeviceNumber"/>*
        </td>
        <td>
            <input type="text" class="text" name="mainDeviceNumber" id="mainDeviceNumber"
                   alt="allowBlank:false,maxLength:2000,vtext:'请填入 设备序列号 信息，最多输入 2000 字符'"
                   value="${sheetMain.mainDeviceNumber}"/>
        </td>
        <td class="label">
            <!-- 入网时间 -->
            <bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainAccessTime"/>*
        </td>
        <td>
            <input type="text" class="text" name="mainAccessTime" id="mainAccessTime"
                   alt="allowBlank:false,maxLength:2000,vtext:'请填入 入网时间 信息，最多输入 2000 字符'"
                   value="${sheetMain.mainAccessTime}"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 出保日期 -->
            <bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainOutDate"/>*
        </td>
        <td>
            <input type="text" class="text" name="mainOutDate" id="mainOutDate"
                   alt="allowBlank:false,maxLength:2000,vtext:'请填入 出保日期 信息，最多输入 2000 字符'"
                   value="${sheetMain.mainOutDate}"/>
        </td>
        <td class="label">
            <!-- 接口类型及数量 -->
            <bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainInterfaceType"/>*
        </td>
        <td>
            <input type="text" class="text" name="mainInterfaceType" id="mainInterfaceType"
                   alt="allowBlank:false,maxLength:2000,vtext:'请填入 接口类型及数量 信息，最多输入 2000 字符'"
                   value="${sheetMain.mainInterfaceType}"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 是否配置双机热备软件和其它随机软件 -->
            <bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainSoftware"/>*
        </td>
        <td>
            <input type="text" class="text" name="mainSoftware" id="mainSoftware"
                   alt="allowBlank:false,maxLength:2000,vtext:'请填入 是否配置双机热备软件和其它随机软件 信息，最多输入 2000 字符'"
                   value="${sheetMain.mainSoftware}"/>
        </td>
        <td class="label">
            <!-- 操作系统名称及版本 -->
            <bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainSystemName"/>*
        </td>
        <td>
            <input type="text" class="text" name="mainSystemName" id="mainSystemName"
                   alt="allowBlank:false,maxLength:2000,vtext:'请填入 操作系统名称及版本 信息，最多输入 2000 字符'"
                   value="${sheetMain.mainSystemName}"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 系统集成商名称 -->
            <bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainIntegratorName"/>*
        </td>
        <td>
            <input type="text" class="text" name="mainIntegratorName" id="mainIntegratorName"
                   alt="allowBlank:false,maxLength:2000,vtext:'请填入 系统集成商名称 信息，最多输入 2000 字符'"
                   value="${sheetMain.mainIntegratorName}"/>
        </td>
        <td class="label">
            <!-- 拟购买的服务级别 -->
            <bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainLevelService"/>*
        </td>
        <td>
            <input type="text" class="text" name="mainLevelService" id="mainLevelService"
                   alt="allowBlank:false,maxLength:2000,vtext:'请填入 拟购买的服务级别 信息，最多输入 2000 字符'"
                   value="${sheetMain.mainLevelService}"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 设备物理位置 -->
            <bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainPhysicalLocation"/>*
        </td>
        <td>
            <input type="text" class="text" name="mainPhysicalLocation" id="mainPhysicalLocation"
                   alt="allowBlank:false,maxLength:2000,vtext:'请填入 设备物理位置 信息，最多输入 2000 字符'"
                   value="${sheetMain.mainPhysicalLocation}"/>
        </td>
        <td class="label">
            <!-- 备注 -->
            <bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainRemarks"/>*
        </td>
        <td>
            <input type="text" class="text" name="mainRemarks" id="mainRemarks"
                   alt="allowBlank:false,maxLength:2000,vtext:'请填入 备注 信息，最多输入 2000 字符'"
                   value="${sheetMain.mainRemarks}"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 联系人 -->
            <bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainContacts"/>*
        </td>
        <td>
            <input type="text" class="text" name="mainContacts" id="mainContacts"
                   alt="allowBlank:false,maxLength:2000,vtext:'请填入 联系人 信息，最多输入 2000 字符'"
                   value="${sheetMain.mainContacts}"/>
        </td>
        <td class="label">
            <!-- 联系方式 -->
            <bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainContactInformation"/>*
        </td>
        <td>
            <input type="text" class="text" name="mainContactInformation" id="mainContactInformation"
                   alt="allowBlank:false,maxLength:2000,vtext:'请填入 联系方式 信息，最多输入 2000 字符'"
                   value="${sheetMain.mainContactInformation}"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 单位 -->
            <bean:message bundle="maintenanceservice" key="maintenanceServiceMain.mainCompany"/>*
        </td>
        <td>
            <input type="text" class="text" name="mainCompany" id="mainCompany"
                   alt="allowBlank:false,maxLength:2000,vtext:'请填入 单位 信息，最多输入 2000 字符'"
                   value="${sheetMain.mainCompany}"/>
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
            <bean:message bundle="sheet" key="mainForm.accessories"/>
        </td>
        <td colspan="3">
            <eoms:attachment name="sheetMain" property="sheetAccessories"
                             scope="request" idField="sheetAccessories" appCode="maintenanceservice"
                             alt="allowBlank:true"/>
        </td>
    </tr>
</table>


<!--派单树-->
<br/>
<fieldset>
    <c:choose>
        <c:when test="${auto=='true'}">
            <legend>
                <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>：
                <span id="roleName">
				 	 设备维护人
				 </span>
            </legend>
            <div class="x-form-item">
                <eoms:chooser id="Fill0" type="role" roleId="1761" flowId="60"
                              config="{returnJSON:true,showLeader:true}"
                              category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                              data="${sheetMain.sendObject}"/>
            </div>
        </c:when>
        <c:otherwise>
            <legend>
                <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>：
                <span id="roleName">
				 	 报修人
				 </span>
            </legend>
            <div class="x-form-item">
                <eoms:chooser id="Fill0" type="role" roleId="1763" flowId="60"
                              config="{returnJSON:true,showLeader:true}"
                              category="[{id:'dealPerformer',text:'派发',limit:'none',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                              data="${sheetMain.sendObject}"/>
            </div>
        </c:otherwise>
    </c:choose>
</fieldset>