<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<script type="text/javascript" src="../../sheetfllowline/css/functions.js"></script>
<%
    String jsonString = (String) request.getAttribute("jsonString");

%>

<script language="javascript">
    Ext.onReady(function () {

        var userTreeAction = '${app}/xtree.do?method=userFromDept';
        userViewer = new Ext.JsonView("user-list",
            '<div id="user-{id}" class="viewlistitem-user">{name}</div>',
            {
                multiSelect: true,
                emptyText: '<div>请选择用户</div>'
            }
        );

        var l = '<%=jsonString%>';
        userViewer.jsonData = eoms.JSONDecode(l);
        userViewer.refresh();
        userTree = new xbox({
            btnId: 'userTreeBtn', dlgId: 'dlg-user',
            treeDataUrl: userTreeAction, treeRootId: '-1', treeRootText: '用户', treeChkMode: '', treeChkType: 'user',
            viewer: userViewer, saveChkFldId: 'operateUserId'
        });

    });

    function SubmitCheck() {

        frmReg = document.forms[0];
        if (frmReg.faultDealResult.value == '') {
            alert("请输入故障处理结果");
            return false;
        }
        if (frmReg.ifBigFault.value == '') {
            alert("请输入是否为重大故障");
            return false;
        }
        if (frmReg.faultReasonSort.value == '') {
            alert("请输入故障原因类别");
            return false;
        }
        if (frmReg.faultReasonSubsection.value == '') {
            alert("请输入故障原因细分");
            return false;
        }
        if (frmReg.ifCarryNetChange.value == '') {
            alert("请输入是否实施网络变更");
            return false;
        }
        if (frmReg.ifLastPlan.value == '') {
            alert("请输入是否为最终处理方案");
            return false;
        }
        if (frmReg.ifAddCaseDataBase.value == '') {
            alert("是否申请入案例库");
            return false;
        }
        if (frmReg.faultAvoidTime.value == '') {
            alert("请输入故障消除时间");
            return false;
        }
        if (frmReg.operRenewTime.value == '') {
            alert("请输入业务恢复时间");
            return false;
        }
        if (frmReg.affectTimeLength.value == '') {
            alert("请输入影响业务时长");
            return false;
        }
        if (frmReg.faultReasonInfo.value == '') {
            alert("请输入故障原因说明");
            return false;
        }
        if (frmReg.dealStep.value == '') {
            alert("请输入处理措施");
            return false;
        }
        if (frmReg.remark.value == '') {
            alert("请输入备注");
            return false;
        }
        return true;
    }

    function GoBack() {
        window.history.back();
    }


</script>

<form name="addform" method="post" action="../sheetflowline/autoDealsop.do?method=saveObject"
      onsubmit="return SubmitCheck();">

    <table width="500" class="formTable">
        <caption>回复归档信息</caption>
        <tr>
            <td class="label">
                <input type="button" name="userTreeBtn" id="userTreeBtn" value="操作人" class="button"/>
            </td>
            <td>
                <div id="user-list" class="viewer-list"></div>
                <input type="hidden" class="hidden" name="operateUserId" id="operateUserId"/>
            </td>
            <td class="label">
                故障处理结果
            </td>
            <td>
                <eoms:comboBox name="faultDealResult" id="faultDealResult" initDicId="1010306"
                               defaultValue="${object.faultDealResult}"/>
                <input type="hidden" name="alarmId" id="alarmId" class="hidden" value="${alarmId}"/>
                <input type="hidden" name="alarmTitle" id="alarmTitle" class="hidden" value="${alarmTitle}"/>
                <input type="hidden" name="autoDealTask" id="autoDealTask" class="hidden" value="${autoDealTask}"/>
                <input type="hidden" name="autoDealMode" id="autoDealMode" class="hidden" value="${autoDealMode}"/>
                <input type="hidden" name="id" id="id" class="hidden" value="${object.id}"/>
            </td>
        </tr>
        <tr>

            <td class="label">
                是否重大故障

            </td>
            <td>
                <eoms:comboBox name="ifBigFault" id="ifBigFault" initDicId="10301" defaultValue="${object.ifBigFault}"/>
            </td>
            <td class="label">
                故障原因类别
            </td>
            <td>

                <eoms:comboBox name="faultReasonSort"
                               id="faultReasonSort" initDicId="1010303" sub="faultReasonSubsection"
                               defaultValue="${object.faultReasonSort}"/>

            </td>
        </tr>
        <tr>
            <td class="label">
                故障原因细分
            </td>
            <td>
                <eoms:comboBox name="faultReasonSubsection"
                               id="faultReasonSubsection" initDicId="${object.faultReasonSort}"
                               defaultValue="${object.faultReasonSubsection}"/>
            </td>
            <td class="label">
                是否实施网络变更
            </td>
            <td>

                <eoms:comboBox name="ifCarryNetChange" id="ifCarryNetChange" initDicId="10301"
                               defaultValue="${object.ifCarryNetChange}"/>

            </td>
        </tr>
        <tr>
            <td class="label">
                是否最终解决方案
            </td>
            <td>
                <eoms:comboBox name="ifLastPlan" id="ifLastPlan" initDicId="10301" defaultValue="${object.ifLastPlan}"/>
            </td>
            <td class="label">
                是否申请入案例库
            </td>
            <td>

                <eoms:comboBox name="ifAddCaseDataBase" id="ifAddCaseDataBase" initDicId="10301"
                               defaultValue="${object.ifAddCaseDataBase}"/>

            </td>
        </tr>
        <tr>
            <td class="label">
                故障消除时间
            </td>
            <td>
                <input type="text" class="text" name="faultAvoidTime" readonly="readonly"
                       id="faultAvoidTime" value="${eoms:date2String(object.faultAvoidTime)}"
                       onclick="popUpCalendar(this, this)"/>
            </td>
            <td class="label">
                业务恢复时间
            </td>
            <td>

                <input type="text" class="text" name="operRenewTime" readonly="readonly"
                       id="operRenewTime" value="${eoms:date2String(object.operRenewTime)}"
                       onclick="popUpCalendar(this, this)"/>

            </td>
        </tr>
        <tr>
            <td class="label">
                影响业务时长
            </td>
            <td>
                <input type="text" class="text" name="affectTimeLength"
                       id="affectTimeLength" value="${object.affectTimeLength}"/>

            </td>
            <td class="label">

            </td>
            <td>
            </td>
        </tr>
        <tr>
            <td class="label">
                故障原因说明
            </td>
            <td colspan="3">
    	<textarea name="faultReasonInfo" id="faultReasonInfo" class="textarea max"
        >${object.faultReasonInfo}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label">
                处理措施
            </td>
            <td colspan="3">
    	<textarea name="dealStep" id="dealStep" class="textarea max"
        >${object.dealStep}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label">
                备注
            </td>
            <td colspan="3">
    	<textarea name="remark" id="remark" class="textarea max"
        >${object.remark}</textarea>
            </td>
        </tr>
    </table>
    <br>
    <input type="submit" value="确定" name="B1" Class="submit">
    <input type="reset" value="重置" name="B2" Class="button">
    <input type="button" value="返回" name="B3" Class="button" onclick="javascript:GoBack();">


</form>

<%@ include file="/common/footer_eoms.jsp" %>

    	
