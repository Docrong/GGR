<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%

    request.setAttribute("roleId", "1012");


    long localTimes = com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
%>
<%@ include file="/WEB-INF/pages/wfworksheet/focusresourceerrata/baseinputmainhtmlnew.jsp" %>
<script type="text/javascript">
    //selectLimit();
    function selectLimit() {
        Ext.Ajax.request({
            method: "get",
            url: "focusresourceerrata.do?method=newShowLimit&flowName=FocusResourceErrata",
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
<input type="hidden" name="processTemplateName" value="FocusResourceErrata"/>
<input type="hidden" name="operateName" value="newWorkSheet"/>
<c:if test="${status!=1}">


    <input type="hidden" name="phaseId" id="phaseId" value="MonitoringDepart"/>

    <input type="hidden" id="operateType" name="operateType" value="0"/>
    <input type="hidden" name="gatherPhaseId" id="gatherPhaseId" value="HoldTask"/>
</c:if>
<c:if test="${status==1}">
    <input type="hidden" name="phaseId" id="phaseId" value="OverTask"/>
</c:if>
<input type="hidden" name="beanId" value="iFocusResourceErrataMainManager"/>
<input type="hidden" name="mainClassName"
       value="com.boco.eoms.sheet.focusresourceerrata.model.FocusResourceErrataMain"/>
<input type="hidden" name="linkClassName"
       value="com.boco.eoms.sheet.focusresourceerrata.model.FocusResourceErrataLink"/>
<br>

<!-- 工单基本信息 -->
<table id="sheet" class="formTable">

    <tr>
        <td class="label">受理时限*</td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly"
                   id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"
                   onclick="popUpCalendar(this, this)"
                   alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>

        </td>
        <td class="label">处理时限*</td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly"
                   id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"
                   onclick="popUpCalendar(this, this)"
                   alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'处理时限不能早于受理时限',allowBlank:false"/>

        </td>
    </tr>
    <tr>

    <tr>
        <td class="label">
            <!-- 产品实例标识 -->
            <bean:message bundle="focusresourceerrata" key="focusResourceErrataMain.mainProductInstance"/>
        </td>
        <td>
            <input type="text" class="text" name="mainProductInstance" id="mainProductInstance"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 产品实例标识 信息，最多输入 1000 字符'"
                   value="${sheetMain.mainProductInstance}"/>
        </td>
        <td class="label">
            <!-- 客户名称 -->
            <bean:message bundle="focusresourceerrata" key="focusResourceErrataMain.mainCustomerName"/>
        </td>
        <td>
            <input type="text" class="text" name="mainCustomerName" id="mainCustomerName"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 客户名称 信息，最多输入 1000 字符'"
                   value="${sheetMain.mainCustomerName}"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 业务类型 -->
            <bean:message bundle="focusresourceerrata" key="focusResourceErrataMain.mainBusinessType"/>
        </td>
        <td>
            <input type="text" class="text" name="mainBusinessType" id="mainBusinessType"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 业务类型 信息，最多输入 1000 字符'"
                   value="${sheetMain.mainBusinessType}"/>
        </td>
        <td class="label">
            <!-- 电路代号 -->
            <bean:message bundle="focusresourceerrata" key="focusResourceErrataMain.mainCircuitCode"/>
        </td>
        <td>
            <input type="text" class="text" name="mainCircuitCode" id="mainCircuitCode"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 电路代号 信息，最多输入 1000 字符'"
                   value="${sheetMain.mainCircuitCode}"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 电路核查失败所属地市 -->
            <bean:message bundle="focusresourceerrata" key="focusResourceErrataMain.mainFailCity"/>
        </td>
        <td>
            <input type="text" class="text" name="mainFailCity" id="mainFailCity"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 电路核查失败所属地市 信息，最多输入 1000 字符'"
                   value="${sheetMain.mainFailCity}"/>
        </td>
        <td class="label">
            <!-- 电路核查状态 -->
            <bean:message bundle="focusresourceerrata" key="focusResourceErrataMain.mainCheckState"/>
        </td>
        <td>
            <input type="text" class="text" name="mainCheckState" id="mainCheckState"
                   alt="allowBlank:true,maxLength:1000,vtext:'请填入 电路核查状态 信息，最多输入 1000 字符'"
                   value="${sheetMain.mainCheckState}"/>
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
                             scope="request" idField="sheetAccessories" appCode="focusresourceerrata"
                             alt="allowBlank:true"/>
        </td>
    </tr>
</table>


<!--派单树-->
<br/>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>：
        <span id="roleName">
		 	 监控部
		 </span>
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="sendObject" type="role" roleId="1002" flowId="100" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sheetMain.sendObject}"/>
    </div>
</fieldset>