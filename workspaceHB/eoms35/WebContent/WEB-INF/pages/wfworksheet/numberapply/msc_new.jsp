<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<script type="text/javascript">
    var frmReg;
    Ext.onReady(function () {
        frmReg = new eoms.form.Validation({form: 'newFormPage'});
    });

    function onBack() {
        window.close();
    }
</script>
<form name="newFormPage" method="POST" action="numberapply.do?method=performSignalSave">
    <table class="formTable">
        <input type="hidden" name="sheetKey" id="sheetKey" value="${sheetKey}"/>
        <input type="hidden" name="type" id="type"/>
        <script type="text/javascript">
            if ('${type}' == 'new') {
                $('type').value = 'new';
            } else {
                $('type').value = 'edit';
            }
        </script>
        <c:if test="${!empty numberApplyMscid.id }">
            <input type="hidden" name="id" id="id" value="${numberApplyMscid.id }"/>
        </c:if>
        <input type="hidden" name="actionForword" id="actionForword" value="msclist"/>
        <tr>
            <td class="label">网元类型(模板类型)</td>
            <td class="content">
                <input type="text" class="text" name="netType" id="netType" value="${numberApplyMscid.netType}"/>
            </td>
            <td class="label">网元名称*</td>
            <td class="content">
                <input type="text" class="text" name="netName" id="netName" alt="allowBlank:falsevtext:'请填入 网元名称 '"
                       value="${numberApplyMscid.netName}"/>
            </td>
        </tr>
        <tr>
            <td class="label">网元代号*</td>
            <td class="content">
                <input type="text" class="text" name="netId" id="netId" alt="allowBlank:false,vtext:'请填入 网元代号'"
                       value="${numberApplyMscid.netId}"/>
            </td>
            <td class="label">网元属性*</td>
            <td class="content">
                <input type="text" class="text" name="netProp" id="netProp" alt="allowBlank:false,vtext:'请填入 网元属性'"
                       value="${numberApplyMscid.netProp}"/>
            </td>
        </tr>
        <tr>
            <td class="label">相连网元</td>
            <td class="content">
                <input type="text" class="text" name="connNet" id="connNet" value="${numberApplyMscid.connNet}"/>
            </td>
            <td class="label">建设地点*</td>
            <td class="content">
                <input type="text" class="text" name="buildAddress" id="buildAddress"
                       alt="allowBlank:false,vtext:'请填入 建设地点'" value="${numberApplyMscid.buildAddress}"/>
            </td>
        </tr>
        <tr>
            <td class="label">供应商*</td>
            <td class="content"><!-- 供应商字典值 -->
                <eoms:comboBox name="supplier" id="supplier" initDicId="1013404" alt="allowBlank:false"
                               styleClass="select-class" defaultValue="${numberApplyMscid.supplier}"/>
            </td>
            <td class="label">设备架构</td>
            <td class="content">
                <input type="text" class="text" name="equipmentArc" id="equipmentArc"
                       value="${numberApplyMscid.equipmentArc}"/>
            </td>
        </tr>
        <tr>
            <td class="label">信令点编码(24位)</td>
            <td class="content">
                <input type="text" class="text" name="commandCode24" id="commandCode24" value=""/>
            </td>
            <td class="label">MSCID</td>
            <td class="content">
                <input type="text" class="text" name="mscId" id="mscId" value=""/>
            </td>
        </tr>
        <tr>
            <td class="label">信令点编码(14位)</td>
            <td class="content">
                <input type="text" class="text" name="commandCode14" id="commandCode14" value=""/>
            </td>
            <td class="label">硬件平台</td>
            <td class="content">
                <input type="text" class="text" name="hardwareFlatRoof" id="hardwareFlatRoof"
                       value="${numberApplyMscid.hardwareFlatRoof}"/>
            </td>
        </tr>
        <tr>
            <td class="label">软件版本</td>
            <td class="content">
                <input type="text" class="text" name="softwareVersion" id="softwareVersion"
                       value="${numberApplyMscid.softwareVersion}"/>
            </td>
            <td class="label">容量（万）</td>
            <td class="content">
                <input type="text" class="text" name="capability" id="capability"
                       value="${numberApplyMscid.capability}"/>
            </td>
        </tr>
        <tr>
            <td class="label">信令链路数（2MB）</td>
            <td class="content">
                <input type="text" class="text" name="commondLink" id="commondLink"
                       value="${numberApplyMscid.commondLink}"/>
            </td>
            <td class="label">E1端口总数（承载窄带)</td>
            <td class="content">
                <input type="text" class="text" name="portCount" id="portCount" value="${numberApplyMscid.portCount}"/>
            </td>
        </tr>
        <tr>
            <td class="label">IP接口总数（FE/GE）</td>
            <td class="content">
                <input type="text" class="text" name="iptotalNum" id="iptotalNum"
                       value="${numberApplyMscid.iptotalNum}"/>
            </td>
            <td class="label">呼叫处理能力(CAPS)</td>
            <td class="content">
                <input type="text" class="text" name="caps" id="caps" value="${numberApplyMscid.caps}"/>
            </td>
        </tr>
        <tr>
            <td class="label">最大源信令点数量</td>
            <td class="content">
                <input type="text" class="text" name="maxSourceNum" id="maxSourceNum"
                       value="${numberApplyMscid.maxSourceNum}"/>
            </td>
            <td class="label">最大目的信令点数量</td>
            <td class="content">
                <input type="text" class="text" name="maxTargetNum" id="maxTargetNum"
                       value="${numberApplyMscid.maxTargetNum}"/>
            </td>
        </tr>
        <tr>
            <td class="label">覆盖地区</td>
            <td class="content">
                <input type="text" class="text" name="coverArea" id="coverArea" value="${numberApplyMscid.coverArea}"/>
            </td>
            <td class="label">覆盖地区长途区号</td>
            <td class="content">
                <input type="text" class="text" name="areaNumber" id="areaNumber"
                       value="${numberApplyMscid.areaNumber}"/>
            </td>
        </tr>
        <tr>
            <td class="label">城市</td>
            <td class="content">
                <input type="text" class="text" name="city" id="city" value="${numberApplyMscid.city}"/>
            </td>
            <td class="label">覆盖地区范围</td>
            <td class="content">
                <input type="text" class="text" name="coverAreaRange" id="coverAreaRange"
                       value="${numberApplyMscid.coverAreaRange}"/>
            </td>
        </tr>
        <tr>
            <td class="label">端口数</td>
            <td class="content">
                <input type="text" class="text" name="portNum" id="portNum" value="${numberApplyMscid.portNum}"/>
            </td>
            <td class="label">设备所在本地网名称</td>
            <td class="content">
                <input type="text" class="text" name="deviceName" id="deviceName"
                       value="${numberApplyMscid.deviceName}"/>
            </td>
        </tr>
        <tr>
            <td class="label">归属区域</td>
            <td class="content">
                <input type="text" class="text" name="attachArea" id="attachArea"
                       value="${numberApplyMscid.attachArea}"/>
            </td>
            <td class="label">批复文件号</td>
            <td class="content">
                <input type="text" class="text" name="fileNumber" id="fileNumber"
                       value="${numberApplyMscid.fileNumber}"/>
            </td>
        </tr>
    </table>
    <script type="text/javascript">
        if ('${numberApplyMscid.commandCode24}' == '') {
            $('commandCode24').value = '等待管理员分配';
            var element = document.getElementById("commandCode24");
            element.setAttribute("readonly", "true", 0);
        } else {
            $('commandCode24').value = '${numberApplyMscid.commandCode24}';
        }

        if ('${numberApplyMscid.commandCode14}' == '') {
            $('commandCode14').value = '等待管理员分配';
            var element = document.getElementById("commandCode14");
            element.setAttribute("readonly", "true", 0);
        } else {
            $('commandCode14').value = '${numberApplyMscid.commandCode14}';
        }

        if ('${numberApplyMscid.mscId}' == '') {
            $('mscId').value = '等待管理员分配';
            var element = document.getElementById("mscId");
            element.setAttribute("readonly", "true", 0);
        } else {
            $('mscId').value = '${numberApplyMscid.mscId}';
        }
    </script>


    <input type="submit" value="保存" class="submit">
    <input type="button" value="关闭" Onclick="onBack();" class="button">
</form>

<%@ include file="/common/footer_eoms.jsp" %>
