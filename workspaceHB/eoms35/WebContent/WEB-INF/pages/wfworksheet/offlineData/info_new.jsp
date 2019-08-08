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

<form name="newFormPage" method="POST" id="newFormPage" action="offlineData.do?method=performSignalSave">
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
        <c:if test="${!empty offlineDataInfoList.id }">
            <input type="hidden" name="id" id="id" value="${offlineDataInfoList.id }"/>
        </c:if>
        <input type="hidden" name="actionForword" id="actionForword" value="infoList"/>
        <input type="hidden" name="mainid" id="mainid" value="${offlineDataInfoList.mainid }"/>
        <tr>
            <td class="label">信息列表*</td>
            <td class="content">
                <input type="text" class="text" name="infolist" id="infolist" value="${offlineDataInfoList.infolist }"
                       alt="allowBlank:false,vtext:'请填写网元名称'"/>
            </td>
            <td class="label">在线存放设备*</td>
            <td class="content">
                <input type="text" class="text" name="storageequipment" id="storageequipment"
                       value="${offlineDataInfoList.storageequipment }" alt="allowBlank:false,vtext:'请填写网元代号'"/>
            </td>
        </tr>
        <tr>
            <td class="label">维护责任单位*</td>
            <td class="content">
                <input type="text" class="text" name="maintenance" id="maintenance"
                       value="${offlineDataInfoList.maintenance }" alt="allowBlank:false,vtext:'请填写网元属性'"/>
            </td>
            <td class="label">责任人*</td>
            <td class="content">
                <input type="text" class="text" name="responsible" id="responsible"
                       value="${offlineDataInfoList.responsible }" alt="allowBlank:false,vtext:'请填写建设地点'"/>
            </td>
        </tr>
        <tr>
            <td class="label">在线情况*</td>
            <td class="content"><!-- 供应商的字典值 -->
                <input type="text" class="text" name="information" id="information"
                       value="${offlineDataInfoList.information }" alt="allowBlank:false,vtext:'请填写建设地点'"/>
            </td>
            <td class="label">信息定级</td>
            <td class="content">
                <input type="text" class="text" name="onlinestatus" id="onlinestatus"
                       value="${offlineDataInfoList.onlinestatus }"/>
            </td>
        </tr>

    </table>


    <input type="submit" value="保存" class="submit">
    <input type="button" value="关闭" Onclick="onBack();" class="button">
</form>

<%@ include file="/common/footer_eoms.jsp" %>
