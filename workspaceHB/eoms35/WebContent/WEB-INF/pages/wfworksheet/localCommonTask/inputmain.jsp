<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%

    request.setAttribute("roleId", "249");

    long localTimes = com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
%>
<%@ include file="/WEB-INF/pages/wfworksheet/localCommonTask/baseinputmainhtmlnew.jsp" %>
<input type="hidden" name="${sheetPageName}processTemplateName" value="LocalCommonTaskProcess"/>
<input type="hidden" name="${sheetPageName}sheetTemplateName" value="LocalCommonTaskProcess"/>
<input type="hidden" name="${sheetPageName}operateName" value="newWorkSheet"/>
<c:if test="${status!=1}">
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TaskCompleteAuditHumTask"/>
    <input type="hidden" id="${sheetPageName}operateType" name="${sheetPageName}operateType" value="${operateType}"/>
</c:if>
<c:if test="${status==1}">
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over"/>
</c:if>
<input type="hidden" name="${sheetPageName}beanId" value="iLocalCommonTaskMainManager"/>
<input type="hidden" name="${sheetPageName}extendKey2" id="${sheetPageName}extendKey2" value="HoldTask"/>
<input type="hidden" name="${sheetPageName}mainClassName"
       value="com.boco.eoms.sheet.localCommonTask.model.LocalCommonTaskMain"/>
<input type="hidden" name="${sheetPageName}linkClassName"
       value="com.boco.eoms.sheet.localCommonTask.model.LocalCommonTaskLink"/>
<br>
<!-- sheet info -->
<table class="formTable">
    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.acceptLimit"/>*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly"
                   id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"
                   onclick="popUpCalendar(this, this)"
                   alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>
        </td>

        <td class="label"><bean:message bundle="sheet" key="mainForm.completeLimit"/>*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly"
                   id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"
                   onclick="popUpCalendar(this, this)"
                   alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="localCommonTask" key="localCommonTask.mainNetSort1"/>*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}mainNetSort1" id="${sheetPageName}mainNetSort1"
                           initDicId="1010104" sub="${sheetPageName}mainNetSort2" alt="allowBlank:false"
                           defaultValue="${sheetMain.mainNetSort1}" styleClass="select-class"/>
        </td>
        <td class="label"><bean:message bundle="localCommonTask" key="localCommonTask.mainNetSort2"/>*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}mainNetSort2" id="${sheetPageName}mainNetSort2"
                           initDicId="${sheetMain.mainNetSort1}" sub="${sheetPageName}mainNetSort3"
                           alt="allowBlank:false" defaultValue="${sheetMain.mainNetSort2}" styleClass="select-class"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="localCommonTask" key="localCommonTask.mainNetSort3"/>*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}mainNetSort3" id="${sheetPageName}mainNetSort3"
                           initDicId="${sheetMain.mainNetSort2}" alt="allowBlank:false"
                           defaultValue="${sheetMain.mainNetSort3}" styleClass="select-class"/>
        </td>
        <td class="label"><bean:message bundle="localCommonTask" key="localCommonTask.mainTaskType"/>*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}mainTaskType" id="${sheetPageName}mainTaskType"
                           onchange="onmainTaskType(this.value);" initDicId="1010108" alt="allowBlank:false"
                           defaultValue="${sheetMain.mainTaskType}" styleClass="select-class"/>
        </td>
    </tr>


    <tr>
        <td class="label">工单分类*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}sheettype" id="${sheetPageName}sheettype" initDicId="1010109"
                           alt="allowBlank:false" defaultValue="${sheetMain.sheettype}" styleClass="select-class"/>
        </td>
        <td class="label">站号</td>
        <td><input type="text" name="${sheetPageName}netNo" id="${sheetPageName}netNo" value="${sheetMain.netNo}"/></td>
    </tr>
    <tr>
        <td class="label">站名</td>
        <td><input type="text" name="${sheetPageName}netName" id="${sheetPageName}netName"
                   value="${sheetMain.netName}"/></td>
        <td class="label">告警描述*</td>
        <td>
            <input type="text" name="${sheetPageName}netText" id="${sheetPageName}netText" value="${sheetMain.netText}"
                   alt="allowBlank:false,vtext:'告警描述'"/>
            <input type="button" value="生成标题" onclick="onTitle();">
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="localCommonTask" key="localCommonTask.mainTaskDescription"/>*</td>
        <td colspan="3" class="content">
    				  <textarea class="textarea max" name="${sheetPageName}mainTaskDescription"
                                id="${sheetPageName}mainTaskDescription"
                                alt="allowBlank:false,maxLength:2000,vtext:'请输入任务描述，多输入100汉字'">${sheetMain.mainTaskDescription}</textarea>
        </td>
    </tr>
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
                             scope="request" idField="${sheetPageName}sheetAccessories" appCode="localCommonTask"/>
        </td>
    </tr>
</table>
<c:if test="${status!=1}">
    <fieldset id="link1">
        <legend>
            <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
            <span id="roleName">:
			 </span>
        </legend>
        <eoms:chooser id="testlocal2" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',childType:'user,dept',limit:'none',text:'派发',allowBlank:true,vtext:'请选择任务执行人'}]"
                      panels="[{text:'部门与人员',dataUrl:'/xtree.do?method=userFromDept'},{text:'个性化部门树',dataUrl:'/sheet/userdefinegroup/userdefinegroup.do?method=showTree&flowId=1'}]"
                      data="[{id:'1223',nodeType:'dept',categoryId:'dealPerformer'}]"/>
    </fieldset>
</c:if>
<script type="text/javascript">

    function onmainTaskType(value) {
        var times =<%=localTimes%>;
        var statr = '';
        var end = '';
        if (value == '101010801') {
            statr = 3;
            end = 4;
        }
        if (value == '101010802') {
            statr = 14;
            end = 15;
        }
        if (value == '101010803') {
            statr = 23;
            end = 24;
        }
        var dt1 = new Date(times).add(Date.HOUR, statr);
        var dt2 = new Date(times).add(Date.HOUR, end);
        $("${sheetPageName}sheetAcceptLimit").value = dt1.format('Y-m-d H:i:s');
        $('${sheetPageName}sheetCompleteLimit').value = dt2.format('Y-m-d H:i:s');
    }

    function onTitle() {
        var netNo = document.getElementById("netNo").value;
        var netName = document.getElementById("netName").value;
        var netText = document.getElementById("netText").value;
        var sheettype = document.getElementById("sheettype").options[document.getElementById("sheettype").selectedIndex].text;
        if (document.getElementById("sheettype").selectedIndex == 0) {
            alert('请选择工单分类');
            return "";
        }

        if (netName == null || netName == '') {
            alert('请填写站名');
            return "";

        }
        if (netText == null || netText == '') {
            alert('请填写告警描述');
            return "";
        }
        if (netNo != null && netNo != '') {
            document.getElementById("title").value = sheettype + "-" + netNo + "-" + netName + "-" + netText;
        } else {
            document.getElementById("title").value = sheettype + "-" + netName + "-" + netText;
        }
    }
</script>
