<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.commonfault.task.impl.CommonFaultTask" %>
<%@page import="com.boco.eoms.sheet.commonfault.service.ICommonFaultAutoManager" %>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<%
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
    String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId"));
    String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId"));
    System.out.println("=====taskName======" + taskName);
    String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
    System.out.println("=====operateType======" + operateType);
    String ifsub = "";
    String mainNetSortOne = "";
    String toDeptId = "";
    if (request.getAttribute("task") != null) {
        CommonFaultTask task = (CommonFaultTask) request.getAttribute("task");
        ifsub = StaticMethod.nullObject2String(task.getSubTaskFlag());
    }
    ICommonFaultAutoManager autoservice = (ICommonFaultAutoManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultAutoManager");
    Map condition = new HashMap();
    String where = "";
    if (request.getAttribute("sheetMain") != null) {
        CommonFaultMain commonfaultMain = (CommonFaultMain) request.getAttribute("sheetMain");
        String mainAlarmId = commonfaultMain.getMainAlarmId();
        mainNetSortOne = commonfaultMain.getMainNetSortOne();
        toDeptId = commonfaultMain.getToDeptId();
        System.out.println("lizhimainNetSortOne:" + mainNetSortOne);
        where = " where remark1 = '" + mainAlarmId + "'";
    } else {
        where = " where 1 != 1 ";
    }
    System.out.println("lizhi1mainNetSortOne:" + operateType + mainNetSortOne);
//自动归档模板查询	
    String mainAlarmIds = "";
    boolean isAlarmid = true;
    List batchmains = (List) request.getAttribute("mains");
    if (batchmains != null) {

        for (int i = 0; i < batchmains.size(); i++) {
            CommonFaultMain commonfaultMain = (CommonFaultMain) batchmains.get(i);
            if (mainAlarmIds.equals("")) {
                mainAlarmIds = "'" + commonfaultMain.getMainAlarmId() + "'";
            } else if (mainAlarmIds.indexOf(commonfaultMain.getMainAlarmId()) == -1) {
                isAlarmid = false;
                mainAlarmIds += ",'" + commonfaultMain.getMainAlarmId() + "'";
            }
        }
        where = " where remark1 in(" + mainAlarmIds + ")";
    }

    condition.put("where", where);
    int[] aTotal = {0};
    List steplist = autoservice.getObjectsByCondtion(new Integer(0), new Integer(-1), aTotal, condition, "record");
//List steplist=autoservice.getSteps();
    System.out.println("steplist");
    request.setAttribute("steplist", steplist);
%>

<%@page import="java.util.Map" %>
<%@page import="java.util.HashMap" %>
<%@page import="com.boco.eoms.sheet.commonfault.model.CommonFaultAuto" %>
<script type="text/javascript">
    var frm = document.forms[0];
    var count = 0;
    var fm = eoms.form;
    var mainSheetState = null;

    Ext.onReady(function () {
        //alert(<%=operateType%>);
        if ('<%=operateType%>' == '19') {
            eoms.util.appendPage("idSpecia2", "${app}/sheet/urgentfault/urgentfault.do?method=showNewInputSheetPage&sheetPageName=urgent&parentCorrelation=${sheetMain.correlationKey}&invokerObject=CommonFault&invokerId=${sheetMain.id}");
            fm.enableArea('idSpecia2');
        } else {
            fm.disableArea('idSpecia2', true);
        }
    });
    if ('<%=isAlarmid%>' == 'false') {
        alert("您选择的不是同类型告警工单!");
    }
</script>
<script type="text/javascript">
    function popupKnowledge() {

        Ajax.Request(
            "${app}/sheet/commonfault/commonfault.do?method=createKnowledge",
            {
                method: "GET",
                parameters: "&sheetKey=${sheetMain.id}",
                onComplete: handleCallBack
            }
        );
        var height = window.screen.height / 6;
        var width = window.screen.width / 4;

        features = "dialogWidth:" + 1024 + "px;dialogHeight:" + 768 + "px; scroll:2; help:0; status:No; fullscreen;";
        features += "dialogLeft:" + width + ";dialogTop:" + height;
    }

    function popupLeaveKnowledge() {

        Ajax.Request(
            "${app}/sheet/commonfault/commonfault.do?method=createLeaveKnowledge",
            {
                method: "GET",
                parameters: "&sheetKey=${sheetMain.id}",
                onComplete: handleCallBack
            }
        );
        var height = window.screen.height / 6;
        var width = window.screen.width / 4;

        features = "dialogWidth:" + 1024 + "px;dialogHeight:" + 768 + "px; scroll:2; help:0; status:No; fullscreen;";
        features += "dialogLeft:" + width + ";dialogTop:" + height;
    }

    function handleCallBack(originalRequest) //回调函数，对服务端的响应处理，监视response状态


    {
        var url = originalRequest.responseText;
        window.open(url);
    }

    function changRequired(selectedValue) {
        var obj1 = document.getElementById("norequiredtd");
        var obj3 = document.getElementById("${sheetPageName}linkOperRenewTime");
        var obj2 = document.getElementById("requiredtd");
        if (selectedValue == "101030603") {
            obj1.style.display = "inline";
            obj2.style.display = "none";
            obj3.setAttribute("alt", "allowBlank:true");
        } else {
            obj2.style.display = "inline";
            obj1.style.display = "none";
            obj3.setAttribute("alt", "allowBlank:false");
        }
    }

    var linkFaultReasonSubsection = "";
    var linkFaultReasonSubsectionTwo = "";

    function searchContent(id) {
        Ext.Ajax.request({
            url: "${app}/sheet/agentmaintenance/agentmaintenance.do?method=searchContent&operatedeptid=${sheetLink.operateDeptId}&type=commonfault&sheetid=" + id,
            method: 'POST',
            success: function (res) {
                var data = eoms.JSONDecode(res.responseText);
                var mainStatus = data[0].mainStatus;
                if (mainStatus == 1) {
                    if (data[0].nodeAccessories != null && data[0].nodeAccessories != "") {
                        var a = eoms.$('nodeAccessories');
                        a.value = "'" + data[0].nodeAccessories + "'";
                        var s = document.getElementById("UIFrame1-nodeAccessories");
                        s.src = "/accessories/pages/upload.jsp?appId=commonfault&filelist='" + data[0].nodeAccessories + "'&idField=nodeAccessories";
                    }
                    $('${sheetPageName}linkIfGreatFault').value = data[0].linkFaultIfGreatFault;
                    $('${sheetPageName}linkFaultDealResult').value = data[0].linkFaultDealResult;
                    $('${sheetPageName}linkFaultReasonSort').value = data[0].linkFaultReasonSort;
                    eoms.ComboBox.doCombo(document.getElementById("linkFaultReasonSort"), 'linkFaultReasonSubsection');
                    $('${sheetPageName}linkFaultReasonSubsection').value = data[0].linkFaultReasonSubsection;
                    linkFaultReasonSubsection = data[0].linkFaultReasonSubsection;
                    eoms.ComboBox.doCombo(document.getElementById("linkFaultReasonSort"), 'linkFaultReasonSubsectionTwo');
                    $('${sheetPageName}linkFaultReasonSubsectionTwo').value = data[0].linkFaultReasonSubsectionTwo;
                    linkFaultReasonSubsectionTwo = data[0].linkFaultReasonSubsectionTwo;
                    $('${sheetPageName}linkIfExcuteNetChange').value = data[0].linkFaultIfExcuteNetChange;
                    $('${sheetPageName}linkIfFinallySolveProject').value = data[0].linkFaultIfFinallySolveProject;
                    $('${sheetPageName}linkIfAddCaseDataBase').value = data[0].linkFaultIfAddCaseDataBase;
                    $('${sheetPageName}linkAffectTimeLength').value = data[0].linkFaultAffectTimeLength;
                    $('${sheetPageName}linkFaultAvoidTime').value = data[0].linkFaultAvoidTime;
                    $('${sheetPageName}linkOperRenewTime').value = data[0].linkFaultOperRenewTime;
                    $('${sheetPageName}linkDealStep').value = data[0].linkFaultDealStep;
                    $('${sheetPageName}faultdealTime').value = data[0].commonFaultdealTime;
                    $('${sheetPageName}faultTreatment').value = data[0].commonFaultTreatment;
                    $('${sheetPageName}faultdealdesc').value = data[0].commonFaultdealdesc;
                    $('${sheetPageName}linkDealdesc').value = data[0].commonLinkDealdesc;
                    $('selectStep').value = data[0].selectStep;
                    alert("代维工单已归档");
                } else {
                    var dwTotal = data[0].dwTotal;
                    if (dwTotal == 0) {
                        alert("此工单无对应代维流程!");
                    } else {
                        var total = data[0].total;
                        mainSheetState = data[0].mainSheetState;
                        if (mainSheetState == 1) {
                            total = 0;
                        }
                        if (total == 0) {
                            alert("此工单对应的代维流程未回复，无回复内容!");
                        } else {
                            document.getElementById('tr1').style.display = '';
                            document.getElementById('tr2').style.display = 'none';
                            document.getElementById('tr3').style.display = '';
                            document.getElementById('select1').selectedIndex = "0";
                            if (data[0].nodeAccessories != null && data[0].nodeAccessories != "") {
                                var a = eoms.$('nodeAccessories');
                                a.value = "'" + data[0].nodeAccessories + "'";
                                var s = document.getElementById("UIFrame1-nodeAccessories");
                                s.src = "/accessories/pages/upload.jsp?appId=commonfault&filelist='" + data[0].nodeAccessories + "'&idField=nodeAccessories";
                            }
                            $('${sheetPageName}linkIfGreatFault').value = data[0].linkFaultIfGreatFault;
                            $('${sheetPageName}linkFaultDealResult').value = data[0].linkFaultDealResult;
                            $('${sheetPageName}linkFaultReasonSort').value = data[0].linkFaultReasonSort;
                            eoms.ComboBox.doCombo(document.getElementById("linkFaultReasonSort"), 'linkFaultReasonSubsection');
                            $('${sheetPageName}linkFaultReasonSubsection').value = data[0].linkFaultReasonSubsection;
                            linkFaultReasonSubsection = data[0].linkFaultReasonSubsection;
                            eoms.ComboBox.doCombo(document.getElementById("linkFaultReasonSort"), 'linkFaultReasonSubsectionTwo');
                            $('${sheetPageName}linkFaultReasonSubsectionTwo').value = data[0].linkFaultReasonSubsection;
                            linkFaultReasonSubsectionTwo = data[0].linkFaultReasonSubsectionTwo;
                            $('${sheetPageName}linkIfExcuteNetChange').value = data[0].linkFaultIfExcuteNetChange;
                            $('${sheetPageName}linkIfFinallySolveProject').value = data[0].linkFaultIfFinallySolveProject;
                            $('${sheetPageName}linkIfAddCaseDataBase').value = data[0].linkFaultIfAddCaseDataBase;
                            $('${sheetPageName}linkAffectTimeLength').value = data[0].linkFaultAffectTimeLength;
                            $('${sheetPageName}linkFaultAvoidTime').value = data[0].linkFaultAvoidTime;
                            $('${sheetPageName}linkOperRenewTime').value = data[0].linkFaultOperRenewTime;
                            $('${sheetPageName}linkDealStep').value = data[0].linkFaultDealStep;
                            $('${sheetPageName}faultdealTime').value = data[0].commonFaultdealTime;
                            $('${sheetPageName}faultTreatment').value = data[0].commonFaultTreatment;
                            $('${sheetPageName}faultdealdesc').value = data[0].commonFaultdealdesc;
                            $('${sheetPageName}linkDealdesc').value = data[0].commonLinkDealdesc;
                            $('selectStep').value = data[0].selectStep;
                            isifdeal(data[0].selectStep);
                            if (count == 0) {
                                document.getElementById("tr1").style.display = "";
                                document.getElementById('tr2').style.display = 'none';
                                document.getElementById('tr3').style.display = '';
                                document.getElementById('select1').selectedIndex = "0";
                            }
                        }
                    }
                }
            }
        });
    }

    function search1() {
        if (linkFaultReasonSubsection.indexOf($('${sheetPageName}linkFaultReasonSort').value) != -1) {
            $('${sheetPageName}linkFaultReasonSubsection').value = linkFaultReasonSubsection;
        }
        if (linkFaultReasonSubsectionTwo.indexOf($('${sheetPageName}linkFaultReasonSubsection').value) != -1) {
            $('${sheetPageName}linkFaultReasonSubsectionTwo').value = linkFaultReasonSubsectionTwo;
        }
    }

    function openwin(id) {
        var url = "${app}/sheet/agentmaintenance/agentmaintenance.do?method=searchContent&operatedeptid=${sheetLink.operateDeptId}&type=commonfault&ifinvoke=true&sheetid=" + id;
        Ext.Ajax.request({
            url: url,
            method: 'POST',
            success: function (res) {
                var data = eoms.JSONDecode(res.responseText);
                var dwTotal = data[0].dwTotal;
                if (dwTotal == 0) {
                    var url = "${app}/sheet/agentmaintenance/agentmaintenance.do?method=showNewSheetPage&parentSheetId=${sheetMain.id}&parentSheetName=iCommonFaultMainManager"
                        + "&parentCorrelation=${sheetMain.correlationKey}&parentPhaseName=${taskName}&invokeMode=asynchronism&type=commonfault";
                    window.open(url, 'newwindow', 'height=800, width=1000, top=200, left=200,toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
                } else {
                    alert("此工单已调用代维流程!");
                }

            }
        });
    }

    //add by hanlili
    function ifchoosePass(obj) {
        var now = obj.selectedIndex;
        if ("1" == now) {
            document.getElementById('tr2').style.display = '';
            document.getElementById('noAuditReason').value = "请重新处理";
            document.getElementById('tr3').style.display = 'none';
        } else if ("0" == now) {
            document.getElementById('tr2').style.display = 'none';
            document.getElementById('tr3').style.display = '';
        }

    }

    function ifPass(mainId) {
        var select1 = document.getElementById('select1');
        var now = select1.selectedIndex
        if ("0" == now) {
            Ext.Ajax.request({
                url: "${app}/sheet/agentmaintenance/agentmaintenance.do?method=ifAllReply&operatedeptid=${sheetLink.operateDeptId}&id=" + mainId,
                method: 'POST',
                success: function (res) {
                    var data = eoms.JSONDecode(res.responseText);
                    var flag = data[0].flag;
                    if (flag == "true") {
                        var auditReason = document.getElementById('auditReason').value;
                        if (null == auditReason || "" == auditReason) {
                            alert("请填写处理回复意见，且最多输入1000汉字");
                        } else {
                            admit(mainId, auditReason);
                        }
                    } else {
                        alert("请查看所有子工单回复满意后再归档!");
                    }
                }
            });
        } else if ("1" == now) {
            var noAuditReason = document.getElementById('noAuditReason').value;
            if (null == noAuditReason || "" == noAuditReason) {
                alert("请填写工单驳回意见，且最多输入1000汉字");
            } else {
                noAdmit(mainId, noAuditReason);
            }
        }


    }

    //modify by lijiangrui
    function admit(id, auditReason) {
        if (mainSheetState == 0) {
            alert("代维回复在此之前已经通过了");
        } else {
            Ext.Ajax.request({
                url: "${app}/sheet/agentmaintenance/agentmaintenance.do?method=performDeal1&operatedeptid=${sheetLink.operateDeptId}&operateType=211&phaseId=Receive&id=" + id,
                method: 'POST',
                params: {
                    'auditReason': auditReason
                },
                success: function () {
                    alert("代维工单已归档");
                    document.getElementById("tr1").style.display = "none";
                    document.getElementById("tr3").style.display = "none";
                    count++;
                }
            });
        }
    }

    function noAdmit(id, noAuditReason) {
        var url = "${app}/sheet/agentmaintenance/agentmaintenance.do?method=performDeal1&operatedeptid=${sheetLink.operateDeptId}&operateType=212&phaseId=ExcuteHumTask";
        if (mainSheetState == 0) {
            alert("代维回复在此之前已经通过了");
        } else {
            Ext.Ajax.request({
                url: url,
                method: 'POST',
                params: {
                    'id': id,
                    'noAuditReason': noAuditReason
                },
                success: function () {
                    alert("代维工单已驳回");
                    document.getElementById("tr1").style.display = "none";
                    document.getElementById('tr2').style.display = 'none';
                    count++;
                }
            });
        }
    }

    function checkAlarm() {
        Ext.Ajax.request({
            url: '${app}/sheet/commonfault/commonfault.do?method=updateCheckStatus&sheetKey=${sheetMain.id}',
            method: 'POST',
            success: function (res) {
                var data = eoms.JSONDecode(res.responseText);
                var flag = data[0].flag;
                alert(flag);
            }
        });
    }

    function getlinkFaultAvoidTime() {
        var mainAlarmSolveDate = document.getElementById('mainAlarmSolveDate2').value;
        alert(mainAlarmSolveDate);
        var mainCheckTime = document.getElementById('mainCheckTime').value;
        alert(mainCheckTime);
        if (mainAlarmSolveDate == null || mainAlarmSolveDate == '') {
            document.getElementById('linkFaultAvoidTime').value = mainCheckTime;
            alert("获取核查告警清除时间");
        } else {
            document.getElementById('linkFaultAvoidTime').value = mainAlarmSolveDate;
            alert("获取故障清除时间");
        }
    }

</script>

<%if (operateType.equals("19")) {%>

<%@include file="/WEB-INF/pages/wfworksheet/commonfault/hiddendealtitle.jsp" %>

<%} else {%>

<%@ include file="/WEB-INF/pages/wfworksheet/commonfault/baseinputlinkhtmlnew.jsp" %>

<%}%>
<br/> <input type="hidden" name="${sheetPageName}processTemplateName" id="${sheetPageName}processTemplateName"
             value="CommonFaultMainFlowProcess"/>
<input type="hidden" name="${sheetPageName}operateName" id="${sheetPageName}operateName" value="nonFlowOperate"/>
<input type="hidden" name="${sheetPageName}beanId" value="iCommonFaultMainManager"/>
<input type="hidden" name="${sheetPageName}mainClassName"
       value="com.boco.eoms.sheet.commonfault.model.CommonFaultMain"/> <!--main表Model对象类名-->
<input type="hidden" name="${sheetPageName}linkClassName"
       value="com.boco.eoms.sheet.commonfault.model.CommonFaultLink"/> <!--link表Model对象类名-->
<!--<input type="hidden" name="${sheetPageName}roleId" id="${sheetPageName}roleId" value="106">-->
<input type="hidden" name="${sheetPageName}toDeptId" value="${sheetMain.toDeptId}"/>
<input type="hidden" name="${sheetPageName}mainNetSortOne" value="${sheetMain.mainNetSortOne}"/>
<input type="hidden" name="${sheetPageName}mainNetSortTwo" value="${sheetMain.mainNetSortTwo}"/>
<input type="hidden" name="${sheetPageName}mainNetSortThree" value="${sheetMain.mainNetSortThree}"/>
<input type="hidden" name="${sheetPageName}mainEquipmentFactory" value="${sheetMain.mainEquipmentFactory}"/>
<input type="hidden" name="${sheetPageName}centerMonitor" id="${sheetPageName}centerMonitor" value="${centerMonitor}"/>
<c:if test="${taskName != 'HoldHumTask' }">
    <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
</c:if>
<table class="formTable">
    <% if (taskName.equals("SecondExcuteHumTask") && operateType.equals("46")) {%>
    &nbsp;&nbsp;&nbsp;&nbsp;
    <c:if test="${centerMonitor != 'true' }">
        <input type="button" class="submit" value="故障工单调用代维流程"
               onclick="openwin('${sheetMain.id}')"/>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button" class="submit" value="代维回复内容" onclick="searchContent('${sheetMain.id}');"
               onmouseout="search1();"/>

        <c:if test="${empty sheetMain.mainAlarmSolveDate || sheetMain.mainAlarmSolveDate==null}">
            <input type="button" class="submit" value="申请核实告警" onclick="checkAlarm()"/>
        </c:if>

        <!-- add by hanlili 增加代维回复不通过时的不通过理由 -->
        <tr id="tr1" style="display:none">
            <td class="label">
                代维流程是否通过：
            </td>
            <td class="content">
                <select id="select1" name="select1" onchange="ifchoosePass(this)">
                    <option value="101130101">通过</option>
                    <option value="101130102">不通过</option>
            </td>
            <td colspan="2">
                <input type="button" class="submit" value="确定" onclick="ifPass('${sheetMain.id}')"/>
            </td>
        </tr>
        <tr id="tr2" style="display:none">
            <td class="label">工单驳回意见*</td>
            <td class="content" colspan="3">
					<textarea name="noAuditReason" id="noAuditReason"
                              class="textarea max">请重新处理</textarea>
            </td>
        </tr>
        <tr id="tr3" style="display:none">
            <td class="label">处理回复意见*</td>
            <td class="content" colspan="3">
					<textarea name="auditReason" id="auditReason"
                              class="textarea max"></textarea>
            </td>
        </tr>
    </c:if>
    <%} %>

    <%
        if (taskName.equals("FirstExcuteHumTask") || taskName.equals("SecondExcuteHumTask") ||
                taskName.equals("ThirdExcuteHumTask") || taskName.equals("firstSubTask") ||
                taskName.equals("secondSubTask") || taskName.equals("thirdSubTask")) {
    %>


    <%if (operateType.equals("1")) { %>

    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="1"/>

    <%if (taskName.equals("FirstExcuteHumTask")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SecondExcuteTask"/>
    <%} %>


    <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.mainIfMutualCommunication"/></td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}linkIfMutualCommunication"
                           id="${sheetPageName}linkIfMutualCommunication" initDicId="10301"
                           defaultValue="${sheetLink.linkIfMutualCommunication}"/>
        </td>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.mainIfSafe"/></td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}linkIfSafe" id="${sheetPageName}linkIfSafe" initDicId="10301"
                           defaultValue="${sheetLink.linkIfSafe}"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfGreatFault"/>*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}linkIfGreatFault" id="${sheetPageName}linkIfGreatFault"
                           initDicId="10301" defaultValue="1030102"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultFirstDealDesc"/>*</td>
        <td colspan="3">
			 <textarea name="${sheetPageName}linkFaultFirstDealDesc" id="${sheetPageName}linkFaultFirstDealDesc"
                       class="textarea max"
                       alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入500汉字'">${sheetLink.linkFaultFirstDealDesc}</textarea>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultDesc"/></td>
        <td colspan="3">
		   	<textarea name="linkFaultDesc" id="linkFaultDesc" class="textarea max"
                      alt="allowBlank:true,width:500,maxLength:1000,vtext:'最多输入1000汉字'">${sheetLink.linkFaultDesc}</textarea>
        </td>
    </tr>

    <%} %>


    <%if (operateType.equals("2")) { %>

    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="2"/>

    <%if (taskName.equals("SecondExcuteHumTask")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ThirdExcuteTask"/>
    <%} %>
    <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultDealInfo"/>*</td>
        <td colspan="3">
		  		<textarea name="${sheetPageName}linkFaultDealInfo" id="${sheetPageName}linkFaultDealInfo"
                          class="textarea max"
                          alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入100汉字'">${sheetLink.linkFaultDealInfo}</textarea>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkTransmitReason"/>*</td>
        <td colspan="3">
		  		<textarea name="${sheetPageName}linkTransmitReason" id="${sheetPageName}linkTransmitReason"
                          class="textarea max"
                          alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入250汉字'">${sheetLink.linkTransmitReason}</textarea>
        </td>
    </tr>

    <%} %>

    <%if (operateType.equals("4")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4"/>
    <c:choose>
        <c:when test="${taskName == 'SecondExcuteHumTask' && centerMonitor=='true' && taskStatus=='2'}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="FirstExcuteTask"/>
        </c:when>
        <c:otherwise>
            <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer"
                   value="${fOperateroleid}"/>
            <input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader"
                   value="${ftaskOwner}"/>
            <input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType"
                   value="${fOperateroleidType}"/>
            <%if (ifsub.equals("true")) {%>
            <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"
                   value="invokeProcess"/>
            <% } %>
            <c:choose>
                <c:when test="${empty fPreTaskName}">
                    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId"
                           value="BackExcuteTask"/>
                </c:when>
                <c:when test="${'DraftHumTask' == fPreTaskName}">
                    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId"
                           value="BackExcuteTask"/>
                </c:when>
                <c:when test="${'BackHumTask' == fPreTaskName}">
                    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId"
                           value="BackExcuteTask"/>
                </c:when>
                <c:when test="${'FirstExcuteHumTask' == fPreTaskName}">
                    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId"
                           value="FirstExcuteTask"/>
                </c:when>
                <c:when test="${'SecondExcuteHumTask' == fPreTaskName}">
                    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId"
                           value="SecondExcuteTask"/>
                </c:when>
                <c:when test="${'HoldHumTask' == fPreTaskName}">
                    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask"/>
                </c:when>
            </c:choose>
        </c:otherwise>
    </c:choose>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
			        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                              alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入500汉字'"
                              alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>

    <%} %>

    <%if (operateType.equals("61")) { %>
    <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer"
           value="${fOperateroleid}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader"
           value="${ftaskOwner}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType"
           value="${fOperateroleidType}"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
    <!--
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  	
		 -->
    <%} %>
    <%if (operateType.equals("46") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask"/>
    <%
        if (taskName.equals("FirstExcuteHumTask") || taskName.equals("SecondExcuteHumTask") ||
                taskName.equals("ThirdExcuteHumTask")) {
    %>
    <%if (operateType.equals("46")) { %>
    <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultDealResult"/>*</td>
        <td class="content">
            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="46"/>
            <eoms:comboBox name="${sheetPageName}linkFaultDealResult"
                           id="${sheetPageName}linkFaultDealResult" initDicId="1010306"
                           defaultValue="${sheetLink.linkFaultDealResult}" alt="allowBlank:false"
                           onchange="changRequired(this.value)"/>
        </td>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfGreatFault"/>*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}linkIfGreatFault"
                           id="${sheetPageName}linkIfGreatFault" initDicId="10301"
                           defaultValue="${sheetLink.linkIfGreatFault}"/>
        </td>
    </tr>
    <%} else if (operateType.equals("11")) { %>
    <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfGreatFault"/>*</td>
        <td class="content" colspan='3'>
            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="11"/>
            <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"
                   value="splitReply"/>
            <eoms:comboBox name="${sheetPageName}linkIfGreatFault"
                           id="${sheetPageName}linkIfGreatFault" initDicId="10301"
                           defaultValue="${sheetLink.linkIfGreatFault}"/>
        </td>
    </tr>
    <%} %>

    <%} %>

    <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultReasonSort"/>*</td>
        <td class="content">

            <eoms:comboBox name="${sheetPageName}linkFaultReasonSort"
                           id="${sheetPageName}linkFaultReasonSort" initDicId="1010303"
                           sub="${sheetPageName}linkFaultReasonSubsection"
                           defaultValue="${sheetLink.linkFaultReasonSort}" alt="allowBlank:false"/>
        </td>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultReasonSubsection"/>*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}linkFaultReasonSubsection"
                           id="${sheetPageName}linkFaultReasonSubsection" initDicId="${sheetLink.linkFaultReasonSort}"
                           sub="${sheetPageName}linkFaultReasonSubsectionTwo"
                           defaultValue="${sheetLink.linkFaultReasonSubsection}" alt="allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label">故障原因分类</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkFaultReasonSubsectionTwo"
                           id="${sheetPageName}linkFaultReasonSubsectionTwo"
                           initDicId="${sheetLink.linkFaultReasonSubsection}"
                           defaultValue="${sheetLink.linkFaultReasonSubsectionTwo}" alt="allowBlank:false"/>
        </td>
    </tr>

    <%if (operateType.equals("46") || operateType.equals("11")) { %>
    <tr>
        <td class="label">采取措施的时间*</td>
        <td>
            <input class="text" onclick="popUpCalendar(this, this, null, null, null, true, -1)" type="text"
                   name="${sheetPageName}faultdealTime" readonly="readonly"
                   value="${eoms:date2String(sheetLink.faultdealTime)}" id="${sheetPageName}faultdealTime"
                   alt="allowBlank:false"/>
        </td>
        <td class="label">故障处理人*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}faultTreatment"
                   id="${sheetPageName}faultTreatment" value="${sheetLink.faultTreatment}" alt="allowBlank:false"/>
        </td>
    </tr>
    <c:if test="${needRecoveryUnit == 'true'}">
        <tr>
            <td class="label">回复单位*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}recoveryUnit"
                               id="${sheetPageName}recoveryUnit" initDicId="1010311"
                               defaultValue="${sheetLink.recoveryUnit}" alt="allowBlank:false"/>
            </td>
        </tr>
    </c:if>
    <tr>
        <td class="label">故障原因说明*</td>
        <td colspan="3">
					  <textarea name="${sheetPageName}faultdealdesc" id="${sheetPageName}faultdealdesc"
                                class="textarea max"
                                alt="allowBlank:true,width:500,maxLength:1000,vtext:'最多输入500汉字'"
                                alt="width:'500px'">${sheetLink.faultdealdesc}</textarea>
        </td>

    </tr>
    <%}%>
    <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkDealStep"/>*</td>

        <td colspan="3">
            <select id="selectStep" name="selectStep" onchange="isifdeal(this.value)" alt="allowBlank:false">
                <option value="">见处理说明</option>
                <%
                    if (steplist.size() > 0) {
                        for (int i = 0; i < steplist.size(); i++) {
                            CommonFaultAuto auto = (CommonFaultAuto) steplist.get(i);
                            String commonFaultDesc = auto.getCommonFaultDesc();
                            String faultReasonSort = auto.getLinkFaultReasonSort();
                            String faultReasonSubsection = auto.getLinkFaultReasonSubsection();
                            String faultReasonSubsectionTwo = auto.getLinkFaultReasonSubsectionTwo();
                %>
                <option value="<%=commonFaultDesc%>">
                    <%=commonFaultDesc%>
                    <input type="hidden" name="faultReasonSort" id="faultReasonSort" value="<%=faultReasonSort%>"/>
                    <input type="hidden" name="faultReasonSubsection" id="faultReasonSubsection"
                           value="<%=faultReasonSubsection%>"/>
                    <input type="hidden" name="faultReasonSubsectionTwo" id="faultReasonSubsectionTwo"
                           value="<%=faultReasonSubsectionTwo%>"/>
                </option>
                <%
                    }
                } else {
                %>
                <option value="其它">其它</option>
                <% }%>
            </select>
            <input type="hidden" name="linkDealStep" id="linkDealStep" value=""/>
        </td>
    </tr>

    <tr id="isIfDeal">
        <td class="label">处理说明*</td>
        <td colspan="3">
            <textarea name="${sheetPageName}linkDealdesc" id="${sheetPageName}linkDealdesc" class="textarea max"
                      alt="allowBlank:true,width:500,maxLength:1000,vtext:'最多输入500汉字'"
                      alt="width:'500px'">${sheetLink.linkDealdesc}</textarea>

        </td>
    </tr>


    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="tawSheetAccessForm.remark"/>
        </td>
        <td colspan="3">
            <textarea name="${sheetPageName}remark" class="textarea max"
                      id="${sheetPageName}remark">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%
        if ((taskName.equals("FirstExcuteHumTask") || taskName.equals("SecondExcuteHumTask") ||
                taskName.equals("ThirdExcuteHumTask")) && operateType.equals("46")) {
    %>

    <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfExcuteNetChange"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}linkIfExcuteNetChange"
                           id="${sheetPageName}linkIfExcuteNetChange" initDicId="10301"
                           defaultValue="${sheetLink.linkIfExcuteNetChange}" alt="allowBlank:false"/>
        </td>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfFinallySolveProject"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}linkIfFinallySolveProject"
                           id="${sheetPageName}linkIfFinallySolveProject" initDicId="10301"
                           defaultValue="${sheetLink.linkIfFinallySolveProject}" alt="allowBlank:false"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfAddCaseDataBase"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkIfAddCaseDataBase"
                           id="${sheetPageName}linkIfAddCaseDataBase" initDicId="10301"
                           defaultValue="${sheetLink.linkIfAddCaseDataBase}" alt="allowBlank:false"/>
        </td>
    </tr>
    <%} %>

    <script type="text/javascript">
        Ext.onReady(function () {
            if (('${sheetMain.mainAlarmSolveDate}' == '' || '${sheetMain.mainAlarmSolveDate}' == null) && '${sheetMain.mainCheckStatus}' != '2') {
                document.getElementById('linkFaultAvoidTime').disabled = 'true';
            }
        });
    </script>

    <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultAvoidTime"/>*</td>
        <td>
            <input type="hidden" name="mainCheckTime" id="mainCheckTime" value="${sheetMain.mainCheckTime}"/>
            <input type="hidden" name="mainAlarmSolveDate2" id="mainAlarmSolveDate"
                   value="${sheetMain.mainAlarmSolveDate}"/>
            <input type="text" class="text" name="${sheetPageName}linkFaultAvoidTime" readonly="readonly"
                   id="${sheetPageName}linkFaultAvoidTime"
                   value="${eoms:date2String(sheetLink.linkFaultAvoidTime)}"
                   alt="vtype:'moreThen',link:'${sheetPageName}mainFaultGenerantTime',vtext:'故障消除时间晚于故障发生时间',allowBlank:false"/>
            <input type="button" class="submit" value="获取时间" onclick="getlinkFaultAvoidTime()"/>
            <input type="hidden" name="${sheetPageName}mainFaultGenerantTime" id="${sheetPageName}mainFaultGenerantTime"
                   value="${sheetMain.mainFaultGenerantTime}"/>
            <div id="typecombo" style="display: none;">
                <input type="hidden" id="${sheetPageName}mainAlarmSolveDateType"
                       name="${sheetPageName}mainAlarmSolveDateType" value="101030801">
            </div>
            <input type="hidden" id="mainAlarmSolveDate1" name="mainAlarmSolveDate1"
                   value="${sheetMain.mainAlarmSolveDate }">
        </td>
        <td id="norequiredtd" class="label"><bean:message bundle="commonfault"
                                                          key="commonfault.linkOperRenewTime"/></td>
        <td id="requiredtd" class="label" style="display:none"><bean:message bundle="commonfault"
                                                                             key="commonfault.linkOperRenewTime"/>*
        </td>
        <td id="ifrequired" colspan="3">
            <input type="text" class="text" name="${sheetPageName}linkOperRenewTime" readonly="readonly"
                   id="${sheetPageName}linkOperRenewTime"
                   onclick="popUpCalendar(this, this,null,null,null,true,-1)"
                   value="${eoms:date2String(sheetLink.linkOperRenewTime)}" alt="allowBlank:true"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkAffectTimeLength"/></td>
        <td colspan="3">
            <input type="text" class="text" name="${sheetPageName}linkAffectTimeLength"
                   id="${sheetPageName}linkAffectTimeLength" value="${sheetLink.linkAffectTimeLength}"
                   alt="allowBlank:true"/>
        </td>
    </tr>
    <%if ("101010415".equals(mainNetSortOne)) {%>
    <tr>
        <td class="label">故障归因*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkBackCase"
                           id="${sheetPageName}linkBackCase" initDicId="1010308"
                           defaultValue="${sheetLink.linkBackCase}" alt="allowBlank:false"/>
        </td>
    </tr>
    <%} %>
    <%if (taskName.equals("SecondExcuteHumTask") && "101010417".equals(mainNetSortOne)) {%>
    <tr>
        <td class="label">故障分类*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}faultClassification"
                           id="${sheetPageName}faultClassification" initDicId="1010310"
                           defaultValue="${sheetLink.faultClassification}" alt="allowBlank:false"/>
        </td>
    </tr>
    <%} %>
    <%} %>


    <%if (operateType.equals("5")) { %>

    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExamineTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="5"/>

    <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkTransmitContent"/></td>
        <td colspan="3">
		  		<textarea name="linkTransmitContent" id="linkTransmitContent" class="textarea max"
                          alt="allowBlank:true,width:500,maxLength:1000,vtext:'最多输入500汉字'">${sheetLink.linkTransmitContent}</textarea>
        </td>
    </tr>

    <%} %>


    <%if (operateType.equals("19")) { %>

    <%if (taskName.equals("FirstExcuteHumTask")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="FirstExcuteTask"/>
    <%}%>
    <%if (taskName.equals("SecondExcuteHumTask")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SecondExcuteTask"/>
    <%}%>
    <%if (taskName.equals("ThirdExcuteHumTask")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ThirdExcuteTask"/>
    <%}%>

    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="19"/>
    <input type="hidden" name="${sheetPageName}linkIfUrgentFault" id="${sheetPageName}linkIfUrgentFault"
           value="1030101"/>

    <%} %>

    <%if (operateType.equals("8")) { %>
    <!-- 环节内容（组内） 移交处理 -->
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="8"/>
    <%if (taskName.equals("FirstExcuteHumTask")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="FirstExcuteTask"/>
    <%} %>
    <%if (taskName.equals("SecondExcuteHumTask")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="SecondExcuteTask"/>
    <input type="hidden" name="${sheetPageName}roleLeader" id="${sheetPageName}roleLeader" value="${roleLeader}"/>
    <%} %>
    <%if (taskName.equals("ThirdExcuteHumTask")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ThirdExcuteTask"/>
    <%} %>

    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.transmitReason"/>
        </td>
        <td colspan="3">
	    				<textarea class="textarea max" name="${sheetPageName}transferReason"
                                  id="${sheetPageName}transferReason"
                                  alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入500汉字'"
                                  alt="width:'500px'">${sheetLink.transferReason}</textarea>
        </td>
    </tr>
    <%} %>


    <%if (!operateType.equals("19") && !operateType.equals("4") && !operateType.equals("61") && !operateType.equals("8")) {%>

    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
        </td>
        <td colspan="3">
            <eoms:attachment name="tawSheetAccess" property="accesss"
                             scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>
        </td>
    </tr>
    <!-- add by libo -->
    <!-- 隐藏域，负责显示复用的附件 Strat -->
    <tr id="attachmentF" style="display:none">
        <td class="label">复用附件</td>
        <td colspan="3">
            <input id="nodeAccessories2" type="hidden" value="">
            <div id="checkbox">

            </div>
            <br>
            <input id="checkboxbutton" type="hidden" onclick=dele() value="删除">
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="sheet" key="linkForm.accessories"/></td>
        <td colspan="3">

            <eoms:attachment name="sheetLink" property="nodeAccessories"
                             scope="request" idField="${sheetPageName}nodeAccessories" appCode="commonfault"/>

        </td>
    </tr>
    <%}%>


    <%}%>


    <%if (taskName.equals("ExamineHumTask")) {%>


    <%if (operateType.equals("66")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="66"/>
    <input type="hidden" name="${sheetPageName}linkIfDeferResolve" id="${sheetPageName}linkIfDeferResolve"
           value="1030101"/>
    <input type="hidden" name="${sheetPageName}mainDelayFlag" id="${sheetPageName}mainDelayFlag" value="1"/>
    <%} %>
    <%if (operateType.equals("64")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="64"/>
    <input type="hidden" name="${sheetPageName}linkIfDeferResolve" id="${sheetPageName}linkIfDeferResolve"
           value="1030102"/>
    <input type="hidden" name="${sheetPageName}mainDelayFlag" id="${sheetPageName}mainDelayFlag" value=""/>
    <%} %>
    <%if (operateType.equals("55")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="55"/>
    <%} %>
    <%if (operateType.equals("88")) { %>
    <!-- 环节内（组内） 转审处理 -->
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExamineTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="88"/>

    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.transmitReason"/>*
        </td>
        <td colspan="3">
	    				<textarea class="textarea max" name="${sheetPageName}transferReason"
                                  id="${sheetPageName}transferReason"
                                  alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入250汉字'"
                                  alt="width:'500px'">${sheetLink.transferReason}</textarea>
        </td>
    </tr>
    <%} %>

    <%if (operateType.equals("61")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
    <!--
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  
		-->
    <%} %>

    <%if (operateType.equals("66") || operateType.equals("64") || operateType.equals("55")) { %>
    <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkExamineContent"/></td>
        <td colspan="3">
            <textarea name="linkExamineContent" id="linkExamineContent"
                      class="textarea max">${sheetLink.linkExamineContent}</textarea>
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
        <td class="label"><bean:message bundle="sheet" key="linkForm.accessories"/></td>
        <td colspan="3">
            <eoms:attachment name="sheetLink" property="nodeAccessories"
                             scope="request" idField="${sheetPageName}nodeAccessories" appCode="commonfault"/>
        </td>
    </tr>
    <%} %>


    <%}%>

    <%if (taskName.equals("HoldHumTask")) {%>

    <%if (operateType.equals("17")) {%>
    <input type="hidden" name="${sheetPageName}mainRejectCount" id="${sheetPageName}mainRejectCount"
           value="${sheetMain.mainRejectCount}"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value=""/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="17"/>
    <input type="hidden" name="rejectActiveTemplateId" id="rejectActiveTemplateId" value="${rejectActiveTemplateId}"/>
    <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkUntreadReason"/>*</td>
        <td colspan="3">
            <textarea name="linkUntreadReason" id="linkUntreadReason" class="textarea max"
                      alt="allowBlank:false">${sheetLink.linkUntreadReason}</textarea>
        </td>
    </tr>

    <%} %>


    <%if (operateType.equals("18")) { %>

    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value=""/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18"/>
    <input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>

    <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.mainFaultResponseLevel"/></td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}linkFaultResponseLevel" id="${sheetPageName}linkFaultResponseLevel"
                           initDicId="1010304" defaultValue="101030402"/>
        </td>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfGreatFault"/>*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}linkIfGreatFault"
                           id="${sheetPageName}linkIfGreatFault" initDicId="10301" defaultValue="1030102"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}holdStatisfied"
                           id="${sheetPageName}holdStatisfied" defaultValue="${sheetMain.holdStatisfied}"
                           initDicId="10303" defaultValue="1030301" styleClass="select" alt="allowBlank:false"/>
        </td>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.mainIfAffectOperation"/>*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}mainIfAffectOperation" id="${sheetPageName}mainIfAffectOperation"
                           initDicId="10301" defaultValue="1030102" alt="allowBlank:false"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/>*</td>
        <td colspan="3">
            <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult" class="textarea max"
                      alt="allowBlank:false">${sheetMain.endResult}</textarea>
        </td>
    </tr>
    <tr>
        <td colspan="4">
            <input type="button" title="knowledge" value="新增知识" onclick="popupKnowledge();">
            <input type="button" title="knowledge" value="入遗留问题库" onclick="popupLeaveKnowledge();">
        </td>
    </tr>

    <%} %>
    <%if (operateType.equals("61")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
    <!--
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px'">${sheetLink.remark}</textarea>
		  </td>
		</tr>  	
		-->
    <%} %>

    <%} %>


    <!--<%  if(taskName.equals("cc")){%>
     
    	<tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />*
		    </td>
			<td colspan="3">			
			 <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15" />
		           <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入500汉字'" alt="width:'500px'" >${sheetLink.remark}</textarea>
		  </td>
		</tr>  
  <%} %>-->

</table>

<%if (operateType.equals("1")) { %>
<%if (taskName.equals("FirstExcuteHumTask")) {%>
<fieldset id="link4">
    <c:choose>
        <c:when test="${centerMonitor=='true'}">
            <legend>
                <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
                <bean:message bundle="commonfault" key="role.SecondExcute"/>
            </legend>
            <c:choose>
                <c:when test="${firstsubroleid=='' || empty firstsubroleid}">
                    <eoms:chooser id="commonfaultSendRole" type="role" roleId="8005106" flowId="51"
                                  config="{returnJSON:true,showLeader:true}"
                                  category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                                  data="${sendUserAndRoles}"/>
                </c:when>
                <c:otherwise>
                    <eoms:chooser id="commonfaultSendRole" type="role" roleId="8005106" flowId="51"
                                  config="{returnJSON:true,showLeader:true}"
                                  category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                                  data="[{id:'${firstsubroleid}',nodeType:'subrole',categoryId:'dealPerformer'}]"/>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <legend>
                <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
                <bean:message bundle="commonfault" key="role.SecondExcute"/>
            </legend>
            <eoms:chooser id="commonfaultSendRole" type="role" roleId="192" flowId="51"
                          config="{returnJSON:true,showLeader:true}"
                          category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                          data="${sendUserAndRoles}"/>
        </c:otherwise>
    </c:choose>
</fieldset>
<%} %>
<%}%>

<%if (operateType.equals("2")) { %>
<%if (taskName.equals("SecondExcuteHumTask")) {%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="commonfault" key="role.ThirdExcute"/>
    </legend>

    <eoms:chooser id="commonfaultSendRole" type="role" roleId="193" flowId="51"
                  config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>
</fieldset>
<%} %>
<%}%>

<%if (operateType.equals("8")) { %>
<%if (taskName.equals("FirstExcuteHumTask")) {%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="commonfault" key="role.FirstExcute"/>
    </legend>
    <eoms:chooser id="test" type="role" roleId="191" flowId="51" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'dealPerformer',childType:'subrole',text:'派发',allowBlank:false,vtext:'请选择派发人'}]"/>
</fieldset>
<%} %>
<%if (taskName.equals("SecondExcuteHumTask")) {%>
<fieldset id="link4">
    <c:choose>
        <c:when test="${roleLeader=='true' && taskStatus=='2'}">
            <legend>请选择移交对象</legend>
            <div class="x-form-item">
                <eoms:chooser id="test" config="{returnJSON:true}"
                              category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发人'}]"
                              panels="[
                   {text:'可选人员',dataUrl:'/role/tawSystemSubRoles.do?method=xgetUsersBySubRole&subRoleId=${sheetLink.operateRoleId}'},
                   {text:'备选子角色',dataUrl:'/role/tawSystemSubRoles.do?method=xgetUsersByRole&roleId=${bigRoleId}&subRoleId=${sheetLink.operateRoleId}'}
                 ]"
                />
            </div>
        </c:when>
        <c:when test="${centerMonitor=='true'}">
            <legend>
                <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
                <bean:message bundle="commonfault" key="role.SecondExcute"/>
            </legend>
            <eoms:chooser id="test" type="role" roleId="8005106" flowId="51" config="{returnJSON:true,showLeader:true}"
                          category="[{id:'dealPerformer',childType:'subrole',text:'派发',allowBlank:false,vtext:'请选择派发人'}]"/>
        </c:when>
        <c:otherwise>
            <legend>
                <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
                <bean:message bundle="commonfault" key="role.SecondExcute"/>
            </legend>
            <eoms:chooser id="test" type="role" roleId="192" flowId="51" config="{returnJSON:true,showLeader:true}"
                          category="[{id:'dealPerformer',childType:'subrole',text:'派发',allowBlank:false,vtext:'请选择派发人'}]"/>
        </c:otherwise>
    </c:choose>
</fieldset>
<%} %>
<%if (taskName.equals("ThirdExcuteHumTask")) {%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="commonfault" key="role.ThirdExcute"/>
    </legend>
    <eoms:chooser id="test" type="role" roleId="193" flowId="51" config="{returnJSON:true,showLeader:true}"
                  category="[{id:'dealPerformer',childType:'subrole',text:'派发',allowBlank:false,vtext:'请选择派发人'}]"/>
</fieldset>
<%} %>

<%}%>


<%if (operateType.equals("5")) { %>
<%if (taskName.equals("FirstExcuteHumTask") || taskName.equals("SecondExcuteHumTask") || taskName.equals("ThirdExcuteHumTask")) {%>
<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="commonfault" key="role.Examine"/>
    </legend>
    <eoms:chooser id="commonfaultSendRole" type="role" roleId="194" flowId="51"
                  config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer' ,childType:'subrole',text:'派发',allowBlank:true,vtext:'请选择派发对象'} ]"/>
</fieldset>
<%} %>
<%}%>

<%if (operateType.equals("88")) { %>

<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="commonfault" key="role.Examine"/>
    </legend>
    <eoms:chooser id="commonfaultSendRole" type="role" roleId="194" flowId="51"
                  config="{returnJSON:true,showLeader:true}"
                  category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'} ]"/>
</fieldset>

<%}%>

<%if (operateType.equals("46")) { %>

<fieldset id="link4">
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
        <bean:message bundle="commonfault" key="role.faultSheetCreater"/>
    </legend>
</fieldset>

<%}%>

<%if (operateType.equals("4")) { %>
<%if (taskName.equals("SecondExcuteHumTask")) {%>
<c:choose>
    <c:when test="${centerMonitor=='true' && taskStatus=='2'}">
        <fieldset>
            <legend>
                <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
                <span id="roleName"></span>
            </legend>
            <div id="test">
                <div class="viewer-list" id="test-chooser-show">
                    <div>派发:
                        <div style="display: inline;" class="viewlistitem-dept">故障一级处理组(T1)</div>
                    </div>
                </div>
                <input type="hidden" name="dealPerformer" id="ext-gen185" value="8aa0813b1c6f2386011c6f39c8350027">
                <input type="hidden" name="dealPerformerType" id="ext-gen186" value="subrole">
                <input type="hidden" name="dealPerformerLeader" id="ext-gen187"
                       value="8aa0813b1c6f2386011c6f39c8350027">
            </div>
        </fieldset>
    </c:when>
</c:choose>
<%} %>
<%}%>

<%if (operateType.equals("17")) { %>
<%if (taskName.equals("HoldHumTask")) {%>
<c:choose>
    <c:when test="${centerMonitor=='true' && rejectActiveTemplateId=='SecondExcuteHumTask'}">
        <fieldset>
            <legend>
                <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
                <span id="roleName"></span>
            </legend>
            <eoms:chooser id="test" type="role" roleId="8005106" flowId="51" config="{returnJSON:true,showLeader:true}"
                          category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'}]"
                          data="[{id:'${preOperateRoleId}',nodeType:'subrole',categoryId:'dealPerformer'}]"/>
        </fieldset>
    </c:when>
</c:choose>
<%} %>
<%}%>

<br/>

<div ID="idSpecia2"></div>

<script type="text/javascript">
    if (<%=operateType%> == 46
    )
    {
        document.getElementById("linkFaultReasonSort").disabled = 'disabled';
    }

    function isifdeal(input) {
        document.getElementById("linkDealdesc").value = input;
        var selectStep = document.getElementById("selectStep");
        var i = selectStep.selectedIndex;
        alert(i);
        if (input != '其它' && input != '') {
            document.getElementById("linkFaultReasonSort").disabled = false;
            var faultReasonSort = document.getElementsByName('faultReasonSort');
            var faultReasonSubsection = document.getElementsByName('faultReasonSubsection');
            var faultReasonSubsectionTwo = document.getElementsByName('faultReasonSubsectionTwo');
            var linkFaultReasonSort = document.getElementById('linkFaultReasonSort');
            $('${sheetPageName}linkFaultReasonSort').value = faultReasonSort[i - 1].value;
            eoms.ComboBox.doCombo(document.getElementById("linkFaultReasonSort"), 'linkFaultReasonSubsection');
            $('${sheetPageName}linkFaultReasonSubsection').value = faultReasonSubsection[i - 1].value;
            eoms.ComboBox.doCombo(document.getElementById("linkFaultReasonSubsection"), 'linkFaultReasonSubsectionTwo');
            $('${sheetPageName}linkFaultReasonSubsectionTwo').value = faultReasonSubsectionTwo[i - 1].value;
        }


    }

    var stepflag = false;

    function setStep() {
        var stepobj = document.getElementById("linkDealStep");
        var descobj = document.getElementById("linkDealdesc");
        var ss = document.getElementById("selectStep");
        var ft = document.getElementById("${sheetPageName}faultdealTime");
        var fd = document.getElementById("${sheetPageName}faultdealdesc");
        var faultTreatment = document.getElementById("${sheetPageName}faultTreatment");
        var linkDealdesc = document.getElementById("${sheetPageName}linkDealdesc");

        if (ss.value == "其他" || ss.value == "其它") {
            stepflag = false;
            stepobj.value = "采取措施的时间：" + ft.value + "\n" + "处理措施：" + linkDealdesc.value + "\n" + "故障原因说明：" + fd.value + "\n" + "故障处理人：" + faultTreatment.value;
            if (descobj.value == "") {
                alert("处理说明不能为空!");
                return false;
            } else {
                return true;
            }
        } else {
            stepflag = false;
            stepobj.value = "采取措施的时间：" + ft.value + "\n" + "处理措施：" + ss.value + "\n" + "故障原因说明：" + fd.value + "\n" + "故障处理人：" + faultTreatment.value;
            return true;
        }
    }

    function changeStep(obj) {
        var step = document.getElementById("linkDealStep");
        var selectSetp = document.getElementById("selectStep");
        if (obj == "") {
            alert("请选择填写");
            step.value = "";
        } else if (obj == "其它" || obj == "其他") {
            step.readonly = "";
            step.readOnly = false;
            step.value = "";
            stepflag = true;
        } else {
            selectSetp = obj
            step.readOnly = true;
            step.value = selectSetp;
            stepflag = false;
        }
    }

    function onFaultAvoid() {
        var linkFaultAvoidTime = document.getElementById("linkFaultAvoidTime");
        var mainAlarmSolveDate = document.getElementById("mainAlarmSolveDate1");
        var mainAlarmSolveDateType = document.getElementById("mainAlarmSolveDateType");
        var typecombo = document.getElementById("typecombo");
        if (mainAlarmSolveDate.value == '') {
            Ext.Ajax.request({
                method: 'post',
                url: '${app}/sheet/alarmsolveDate/alarmsolveDate.do?method=getAlarmsolveDate&sheetKey=${sheetMain.id}',
                success: success
            });
        }

        function success(x) {
            var o = eoms.JSONDecode(x.responseText);
            if (o.status == '0') {
                alert("告警平台有故障清除时间,已同步告警清除时间!");
                typecombo.style.display = "none";
                mainAlarmSolveDate.value = o.data[0].date;
                linkFaultAvoidTime.value = o.data[0].date;
            } else if (o.status == '1') {
                alert("该故障没有故障清除时间！您可以通过上传附件方式证实告警已恢复回单！");
                typecombo.style.display = "block";
                linkFaultAvoidTime.value = "";
            }
        }
    }

    function onTypecombo(value) {
        if (value == '101030801') {
            document.getElementById("GJFaultAvoidTime").style.display = "none";
        } else if (value == '101030802') {
            document.getElementById("GJFaultAvoidTime").style.display = "block";
        }
    }

    function onGJFaultAvoidTime() {
        var linkFaultAvoidTime = document.getElementById("linkFaultAvoidTime");
        var GJFaultAvoidTime = document.getElementById("GJFaultAvoidTime");

        if (linkFaultAvoidTime.value == null || linkFaultAvoidTime.value == '') {
            alert("请选择告警清除时间！");
        } else {
            Ext.Ajax.request({
                method: 'post',
                url: '${app}/sheet/alarmsolveDate/alarmsolveDate.do?method=updateAlarmsolveDate&sheetKey=${sheetMain.id}&linkFaultAvoidTime=' + linkFaultAvoidTime.value,
                success: success
            });
        }

        function success(x) {
            var o = eoms.JSONDecode(x.responseText);
            if (o.status == '0') {
                document.getElementById("mainAlarmSolveDate1").value = linkFaultAvoidTime.value;
                alert("同步告警时间成功！");
            } else {
                alert("同步告警时间失败！");
            }
        }
    }

    var operateType = <%=operateType%>;
    if ('${sheetMain.mainSendMode}' != '101030502' && operateType == 46) {
        onFaultAvoid();
    }

    function onFaultAvoidDetail() {
        alert("1");
        var selValueAlarm = document.getElementById("selValueAlarm");
        var selValueAlarmDisplay1 = document.getElementById("selValueAlarmDisplay1");
        var selValueAlarmDisplay2 = document.getElementById("selValueAlarmDisplay2");

        Ext.Ajax.request({
            method: 'post',
            url: '${app}/sheet/alarmsolveDate/alarmsolveDate.do?method=getAlarmsolveDate&sheetKey=${sheetMain.id}',
            success: success
        });

        function success(x) {
            var o = eoms.JSONDecode(x.responseText);
            if (o.status == '0') {
                selValueAlarm.value = o.data[0].date;
                selValueAlarmDisplay1.style.display = "none";
                selValueAlarmDisplay2.style.display = "block";
                alert("告警平台有故障清除时间,已同步告警清除时间!");
            } else if (o.status == '1') {
                alert("该故障没有故障清除时间！您可以通过上传附件方式证实告警已恢复回单！");
            }
        }
    }
</script>