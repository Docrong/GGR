<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%

    request.setAttribute("roleId", "6018");

    long localTimes = com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
%>
<%@ include file="/WEB-INF/pages/wfworksheet/offlineData/baseinputmainhtmlnew.jsp" %>
<input type="hidden" name="${sheetPageName}processTemplateName" value="OfflineDataProcess"/>
<input type="hidden" name="${sheetPageName}sheetTemplateName" value="OfflineDataProcess"/>
<input type="hidden" name="${sheetPageName}operateName" value="newWorkSheet"/>
<c:if test="${status!=1}">
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TaskAuditHumTask"/>
    <input type="hidden" id="${sheetPageName}operateType" name="${sheetPageName}operateType" value="${operateType}"/>
</c:if>
<c:if test="${status==1}">
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over"/>
</c:if>
<input type="hidden" name="${sheetPageName}beanId" value="iOfflineDataMainManager"/>
<input type="hidden" name="${sheetPageName}extendKey2" id="${sheetPageName}extendKey2" value="HoldTask"/>
<input type="hidden" name="${sheetPageName}mainClassName"
       value="com.boco.eoms.sheet.offlineData.model.OfflineDataMain"/>
<input type="hidden" name="${sheetPageName}linkClassName"
       value="com.boco.eoms.sheet.offlineData.model.OfflineDataLink"/>
<br>
<!-- sheet info -->
<table class="formTable">
    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.acceptLimit"/>*</td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly"
                   id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"
                   onclick="popUpCalendar(this, this)"
                   alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
        </td>

        <td class="label"><bean:message bundle="sheet" key="mainForm.completeLimit"/>*</td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly"
                   id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"
                   onclick="popUpCalendar(this, this)"
                   alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label">下线类型</td>
        <td>
            <eoms:comboBox name="offlineType" id="offlineType" initDicId="1011901"
                           defaultValue="${sheetMain.offlineType}" alt="allowBlank:false"/>
        </td>
        <td class="label">工程文号</td>
        <td>
            <input type="text" class="text" name="offlineNum"
                   id="offlineNum" value="${sheetMain.offlineNum}" alt="allowBlank:false,maxLength:32,vtext:'请填写工程文号'"/>
        </td>
    </tr>
    <tr>
        <td class="label">备注</td>
        <td colspan="3">
            <textarea class="textarea max" name="mainRemark" id="mainRemark"
                      alt="allowBlank:false,maxLength:255,vtext:'请填入备注 信息，最多输入 255 字符'">${sheetMain.mainRemark}</textarea>
        </td>
    </tr>


    <tr>
        <td class="label">
            下线技术方案
        </td>
        <td colspan="3">

            <eoms:attachment name="sheetMain" property="sheetAccessories"
                             scope="request" idField="${sheetPageName}sheetAccessories" appCode="offlineData"/>
        </td>
    </tr>
    <tr>
        <td width="200px">
            <input type="button" value="单条新增" Onclick=" return onOneAdd();" class="button">
            <input type="hidden" name="actionForword" id="actionForword" value="infoNew"/>
        </td>
    </tr>
    <tr>
        <td colspan="4">
            <iframe id="frame1" src="" width="100%" height="300px" style="display:none"></iframe>
        </td>
    </tr>
</table>
<c:if test="${status!=1}">
    <fieldset id="link1">
        <legend>
            <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
            <span id="roleName">:任务创建审批人
			 </span>
        </legend>
        <eoms:chooser id="test1" type="role" roleId="6018" flowId="51" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>
    </fieldset>
</c:if>
<script type="text/javascript">
    if ('${taskName}' == 'DraftTask' || '${taskName}' == 'RejectTask') {
        var forward = "";

        var frames = $('frame1');
        frames.style.display = "block";
        frames.src = "${app}/sheet/offlineData/offlineData.do?method=showList&sheetKey=${sheetMain.id}&actionForword=" + forward + "&stylepage=new";
    }

    function onOneAdd() {
        var forward = $('actionForword').value;
        var url = "${app}/sheet/offlineData/offlineData.do?method=showOneAdd&actionForword=" + forward + "&sheetKey=${sheetMain.id}";
        window.open(url);
        return true;
    }
</script>
