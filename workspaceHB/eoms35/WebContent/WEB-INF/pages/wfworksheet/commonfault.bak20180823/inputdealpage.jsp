<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.commonfault.task.impl.CommonFaultTask" %>
<%@page import="com.boco.eoms.sheet.commonfault.service.ICommonFaultAutoManager" %>
<%@page import="com.boco.eoms.sheet.netownership.service.INetOwnershipManager" %>
<%@page import="com.boco.eoms.sheet.commonfaultpack.service.ICommonFaultPackMainManager" %>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@ page import="java.util.*,java.text.SimpleDateFormat" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Iterator" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%
    TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
    String userId = sessionform.getUserid();
    String currentDate = com.boco.eoms.base.util.StaticMethod.getCurrentDateTime();
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
    String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId"));
    String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId"));
    System.out.println("=====taskName======" + taskName);
    String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
    System.out.println("=====operateType======" + operateType);
    String firstsubroleid = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("firstsubroleid"));
    String tietasubroleid = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("tietasubroleid"));
    System.out.println("=====firstsubroleid======" + firstsubroleid);
    System.out.println("=====tietasubroleid======" + tietasubroleid);
    String ifsub = "";
    String mainNetSortOne = "";
    String mainAlarmId = "";
    String alarmFlag = "false";
    String toDeptId = "";
    String mainId = "";
    String mainIsPack = "";
    String packflag = "";

    Calendar c = Calendar.getInstance();
    c.add(Calendar.SECOND, 1);
    java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");
    String newdate = dateFormat.format(c.getTime());
    if (request.getAttribute("task") != null) {
        CommonFaultTask task = (CommonFaultTask) request.getAttribute("task");
        ifsub = StaticMethod.nullObject2String(task.getSubTaskFlag());
    }
    ICommonFaultAutoManager autoservice = (ICommonFaultAutoManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultAutoManager");
    INetOwnershipManager netOwnershipManager = (INetOwnershipManager) ApplicationContextHolder.getInstance().getBean("iNetOwnershipManager");

    Map condition = new HashMap();
    String where = "";
    if (request.getAttribute("sheetMain") != null) {
        CommonFaultMain commonfaultMain = (CommonFaultMain) request.getAttribute("sheetMain");
        mainAlarmId = commonfaultMain.getMainAlarmId();
        mainNetSortOne = commonfaultMain.getMainNetSortOne();
        toDeptId = commonfaultMain.getToDeptId();
        mainIsPack = commonfaultMain.getMainIsPack();
        System.out.println("----------mainIsPack----------------:" + mainIsPack);
        mainId = commonfaultMain.getId();
        System.out.println("----------mainId----------------:" + mainId);
        System.out.println("lizhimainNetSortOne:" + mainNetSortOne);
        where = " where remark1 = '" + mainAlarmId + "'";
    } else {
        where = " where 1 != 1 ";
    }
    if (mainAlarmId != null && !"".equals(mainAlarmId)) {
        System.out.println("--ph--mainAlarmId:" + mainAlarmId);
        String tableName = "commonfault_after_alarmid";
        List newList = (List) netOwnershipManager.getListByAlarmId(tableName, mainAlarmId);
        if (newList.size() == 0) {
            alarmFlag = "false";
        } else {
            alarmFlag = "true";
        }
    }
    System.out.println("---ph----alarmFlag:" + alarmFlag);
    System.out.println("lizhi1mainNetSortOne:" + operateType + mainNetSortOne);
//自动归档模板查询
    String mainAlarmIds = "";
    boolean isAlarmid = true;
    List batchmains = (List) request.getAttribute("mains");
    if (batchmains != null) {

        System.out.println("-----size---" + batchmains.size());
        for (int i = 0; i < batchmains.size(); i++) {
            CommonFaultMain commonfaultMain = (CommonFaultMain) batchmains.get(i);
            String mainAlarmid = commonfaultMain.getMainAlarmId();
            System.out.println("---mainAlarmid---" + mainAlarmid);
            if (!"".equals(mainAlarmid) && mainAlarmid != null) {
                if ("".equals(mainAlarmIds) || mainAlarmIds == null) {
                    mainAlarmIds = "'" + commonfaultMain.getMainAlarmId() + "'";
                } else if (mainAlarmIds.indexOf(mainAlarmid) == -1) {
                    isAlarmid = false;
                    mainAlarmIds += ",'" + commonfaultMain.getMainAlarmId() + "'";
                }
            }
        }

        if (!"".equals(mainAlarmIds)) {
            where = " where remark1 in(" + mainAlarmIds + ")";
            System.out.println("----where-" + where);
        }
    }
    System.out.println("+++++++++++++++mainAlarmId++++++++++++++++" + mainAlarmIds);
    condition.put("where", where);
    int[] aTotal = {0};
    List steplist = autoservice.getObjectsByCondtion(new Integer(0), new Integer(-1), aTotal, condition, "record");
//List steplist=autoservice.getSteps();
    System.out.println("steplist");
    request.setAttribute("steplist", steplist);
    if ("1030101".equals(mainIsPack) && "46".equals(operateType)) {
        ICommonFaultPackMainManager commonfaultPack = (ICommonFaultPackMainManager) ApplicationContextHolder.getInstance().getBean("iCommonFaultPackMainManager");
        packflag = commonfaultPack.getIfAlarmSolve(mainId);
        System.out.println("0-------------packflag---------------0:" + packflag);
    }
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

    function searchContent1(id) {
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
                    //eoms.ComboBox.doCombo(document.getElementById("linkFaultReasonSort"),'linkFaultReasonSubsection');
                    $('${sheetPageName}linkFaultReasonSubsection').value = data[0].linkFaultReasonSubsection;
                    linkFaultReasonSubsection = data[0].linkFaultReasonSubsection;
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
                    $('select3').value = data[0].linkFaultReasonSubsection;
                    choosemodel(data[0].linkFaultReasonSubsection);
                    $('selectStep').value = data[0].selectStep;

                    var sel = document.getElementById('linkFaultReasonSort');
                    sel.options[0].value = data[0].linkFaultReasonSort;
                    sel.options[0].innerText = data[0].linkFaultReasonSortName;
                    var sel1 = document.getElementById('linkFaultReasonSubsection');
                    sel1.options[0].value = data[0].linkFaultReasonSubsection;
                    sel1.options[0].innerText = data[0].linkFaultReasonSubsectionName;
                    var sel2 = document.getElementById('linkFaultReasonSubsectionTwo');
                    sel2.options[0].value = data[0].linkFaultReasonSubsectionTwo;
                    sel2.options[0].innerText = data[0].linkFaultReasonSubsectionTwoName;
                    document.getElementById("linkFaultReasonSort").value = data[0].linkFaultReasonSort;
                    document.getElementById("linkFaultReasonSubsection").value = data[0].linkFaultReasonSubsection;
                    document.getElementById("linkFaultReasonSubsectionTwo").value = data[0].linkFaultReasonSubsectionTwo;
                    var sort1 = document.getElementById('linkFaultReasonSort1');
                    sort1.style.display = 'none';
                    var sort2 = document.getElementById('linkFaultReasonSort2');
                    sort2.innerText = data[0].linkFaultReasonSortName;
                    sort2.style.display = 'block';
                    var sort3 = document.getElementById('linkFaultReasonSubsection1');
                    sort3.style.display = 'none';
                    var sort4 = document.getElementById('linkFaultReasonSubsection2');
                    sort4.innerText = data[0].linkFaultReasonSubsectionName;
                    sort4.style.display = 'block';
                    var sort5 = document.getElementById('linkFaultReasonSubsectionTwo1');
                    sort5.style.display = 'none';
                    var sort6 = document.getElementById('linkFaultReasonSubsectionTwo2');
                    sort6.innerText = data[0].linkFaultReasonSubsectionTwoName;
                    sort6.style.display = 'block';
                    document.getElementById("linkFaultReasonSubsection").disabled = false;
                    document.getElementById("linkFaultReasonSubsectionTwo").disabled = false;
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
                            //eoms.ComboBox.doCombo(document.getElementById("linkFaultReasonSort"),'linkFaultReasonSubsection');
                            $('${sheetPageName}linkFaultReasonSubsection').value = data[0].linkFaultReasonSubsection;
                            linkFaultReasonSubsection = data[0].linkFaultReasonSubsection;
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
                            $('select3').value = data[0].linkFaultReasonSubsection;
                            choosemodel(data[0].linkFaultReasonSubsection);
                            $('selectStep').value = data[0].selectStep;


                            var sel = document.getElementById('linkFaultReasonSort');
                            sel.options[0].value = data[0].linkFaultReasonSort;
                            sel.options[0].innerText = data[0].linkFaultReasonSortName;
                            var sel1 = document.getElementById('linkFaultReasonSubsection');
                            sel1.options[0].value = data[0].linkFaultReasonSubsection;
                            sel1.options[0].innerText = data[0].linkFaultReasonSubsectionName;
                            var sel2 = document.getElementById('linkFaultReasonSubsectionTwo');
                            sel2.options[0].value = data[0].linkFaultReasonSubsectionTwo;
                            sel2.options[0].innerText = data[0].linkFaultReasonSubsectionTwoName;
                            document.getElementById("linkFaultReasonSort").value = data[0].linkFaultReasonSort;
                            document.getElementById("linkFaultReasonSubsection").value = data[0].linkFaultReasonSubsection;
                            document.getElementById("linkFaultReasonSubsectionTwo").value = data[0].linkFaultReasonSubsectionTwo;
                            var sort1 = document.getElementById('linkFaultReasonSort1');
                            sort1.style.display = 'none';
                            var sort2 = document.getElementById('linkFaultReasonSort2');
                            sort2.innerText = data[0].linkFaultReasonSortName;
                            sort2.style.display = 'block';
                            var sort3 = document.getElementById('linkFaultReasonSubsection1');
                            sort3.style.display = 'none';
                            var sort4 = document.getElementById('linkFaultReasonSubsection2');
                            sort4.innerText = data[0].linkFaultReasonSubsectionName;
                            sort4.style.display = 'block';
                            var sort5 = document.getElementById('linkFaultReasonSubsectionTwo1');
                            sort5.style.display = 'none';
                            var sort6 = document.getElementById('linkFaultReasonSubsectionTwo2');
                            sort6.innerText = data[0].linkFaultReasonSubsectionTwoName;
                            sort6.style.display = 'block';
                            document.getElementById("linkFaultReasonSubsection").disabled = false;
                            document.getElementById("linkFaultReasonSubsectionTwo").disabled = false;
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
                    //eoms.ComboBox.doCombo(document.getElementById("linkFaultReasonSort"),'linkFaultReasonSubsection');
                    $('${sheetPageName}linkFaultReasonSubsection').value = data[0].linkFaultReasonSubsection;
                    linkFaultReasonSubsection = data[0].linkFaultReasonSubsection;
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


                    var sel = document.getElementById('linkFaultReasonSort');
                    sel.options[0].value = data[0].linkFaultReasonSort;
                    sel.options[0].innerText = data[0].linkFaultReasonSortName;
                    var sel1 = document.getElementById('linkFaultReasonSubsection');
                    sel1.options[0].value = data[0].linkFaultReasonSubsection;
                    sel1.options[0].innerText = data[0].linkFaultReasonSubsectionName;
                    var sel2 = document.getElementById('linkFaultReasonSubsectionTwo');
                    sel2.options[0].value = data[0].linkFaultReasonSubsectionTwo;
                    sel2.options[0].innerText = data[0].linkFaultReasonSubsectionTwoName;
                    document.getElementById("linkFaultReasonSort").value = data[0].linkFaultReasonSort;
                    document.getElementById("linkFaultReasonSubsection").value = data[0].linkFaultReasonSubsection;
                    document.getElementById("linkFaultReasonSubsectionTwo").value = data[0].linkFaultReasonSubsectionTwo;
                    var sort1 = document.getElementById('linkFaultReasonSort1');
                    sort1.style.display = 'none';
                    var sort2 = document.getElementById('linkFaultReasonSort2');
                    sort2.innerText = data[0].linkFaultReasonSortName;
                    sort2.style.display = 'block';
                    var sort3 = document.getElementById('linkFaultReasonSubsection1');
                    sort3.style.display = 'none';
                    var sort4 = document.getElementById('linkFaultReasonSubsection2');
                    sort4.innerText = data[0].linkFaultReasonSubsectionName;
                    sort4.style.display = 'block';
                    var sort5 = document.getElementById('linkFaultReasonSubsectionTwo1');
                    sort5.style.display = 'none';
                    var sort6 = document.getElementById('linkFaultReasonSubsectionTwo2');
                    sort6.innerText = data[0].linkFaultReasonSubsectionTwoName;
                    sort6.style.display = 'block';
                    document.getElementById("linkFaultReasonSubsection").disabled = false;
                    document.getElementById("linkFaultReasonSubsectionTwo").disabled = false;
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
                            //eoms.ComboBox.doCombo(document.getElementById("linkFaultReasonSort"),'linkFaultReasonSubsection');
                            $('${sheetPageName}linkFaultReasonSubsection').value = data[0].linkFaultReasonSubsection;
                            linkFaultReasonSubsection = data[0].linkFaultReasonSubsection;
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


                            var sel = document.getElementById('linkFaultReasonSort');
                            sel.options[0].value = data[0].linkFaultReasonSort;
                            sel.options[0].innerText = data[0].linkFaultReasonSortName;
                            var sel1 = document.getElementById('linkFaultReasonSubsection');
                            sel1.options[0].value = data[0].linkFaultReasonSubsection;
                            sel1.options[0].innerText = data[0].linkFaultReasonSubsectionName;
                            var sel2 = document.getElementById('linkFaultReasonSubsectionTwo');
                            sel2.options[0].value = data[0].linkFaultReasonSubsectionTwo;
                            sel2.options[0].innerText = data[0].linkFaultReasonSubsectionTwoName;
                            document.getElementById("linkFaultReasonSort").value = data[0].linkFaultReasonSort;
                            document.getElementById("linkFaultReasonSubsection").value = data[0].linkFaultReasonSubsection;
                            document.getElementById("linkFaultReasonSubsectionTwo").value = data[0].linkFaultReasonSubsectionTwo;
                            var sort1 = document.getElementById('linkFaultReasonSort1');
                            sort1.style.display = 'none';
                            var sort2 = document.getElementById('linkFaultReasonSort2');
                            sort2.innerText = data[0].linkFaultReasonSortName;
                            sort2.style.display = 'block';
                            var sort3 = document.getElementById('linkFaultReasonSubsection1');
                            sort3.style.display = 'none';
                            var sort4 = document.getElementById('linkFaultReasonSubsection2');
                            sort4.innerText = data[0].linkFaultReasonSubsectionName;
                            sort4.style.display = 'block';
                            var sort5 = document.getElementById('linkFaultReasonSubsectionTwo1');
                            sort5.style.display = 'none';
                            var sort6 = document.getElementById('linkFaultReasonSubsectionTwo2');
                            sort6.innerText = data[0].linkFaultReasonSubsectionTwoName;
                            sort6.style.display = 'block';
                            document.getElementById("linkFaultReasonSubsection").disabled = false;
                            document.getElementById("linkFaultReasonSubsectionTwo").disabled = false;
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
        var applyaccessories = document.getElementById("applyAccessories").value;
        var url = "${app}/sheet/commonfault/commonfault.do?method=updateCheckStatus&sheetKey=${sheetMain.id}&applyAccessories=" + applyaccessories + "&type=interface&interface=interface&userName=<%=userId%>";
        //  alert(url)
        Ext.Ajax.request({
            url: url,
            method: 'POST',
            success: function (res) {
                var data = eoms.JSONDecode(res.responseText);
                var flag = data[0].flag;
                alert(flag);
            }
        });
    }

    function getlinkFaultAvoidTime() {
        var attachment = document.getElementById("nodeAccessories").value;
        var timestamp = new Date();
        var avoidTime = document.getElementById('linkFaultAvoidTime');
        var mainAlarmSolveDate = document.getElementById('mainAlarmSolveDate2').value;
        var mainCheckTime = document.getElementById('mainCheckTime').value;
        if (mainAlarmSolveDate == null || mainAlarmSolveDate == '') {

            if (attachment == null || attachment == '') {
                if (mainCheckTime != null && mainCheckTime != '') {
                    document.getElementById('linkFaultAvoidTime').value = mainCheckTime;
                    alert("获取核查告警清除时间");
                } else {
                    alert("无法获取故障清除时间,请申请告警核实或上传附件！");
                }
            } else {
                document.getElementById('linkFaultAvoidTime').value = timestamp.format('Y-m-d H:i:s');
                avoidTime.readonly = "";
                avoidTime.readOnly = false;
                alert("附件回单，获取当前时间，可以进行修改");
            }
        } else {
            document.getElementById('linkFaultAvoidTime').value = mainAlarmSolveDate;
            alert("获取告警清除时间");
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
    <c:if test="${centerMonitor == 'true' }">
        <c:if test="${empty sheetMain.mainAlarmSolveDate || sheetMain.mainAlarmSolveDate==null}">
            <tr>
                <td class="label">申请核实附件</td>
                <td colspan="2">
                    <eoms:attachment name="sheetMain" property="applyAccessories"
                                     scope="request" idField="${sheetPageName}applyAccessories" appCode="commonfault"/>
                    <input type="button" class="submit" value="申请核实告警" onclick="checkAlarm()"/>
                </td>

            </tr>
        </c:if>
    </c:if>
    <c:if test="${centerMonitor != 'true' }">
        <input type="button" class="submit" value="故障工单调用代维流程"
               onclick="openwin('${sheetMain.id}')"/>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <%if ("WL-001-00-800003".equals(mainAlarmId)) {%>
        <input type="button" class="submit" value="代维回复内容" onclick="searchContent1('${sheetMain.id}');"
               onmouseout="search1();"/>
        <%} else {%>
        <input type="button" class="submit" value="代维回复内容" onclick="searchContent('${sheetMain.id}');"
               onmouseout="search1();"/>
        <%}%>
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
    <input type="button" class="submit" id="handcorrigendum" name="handcorrigendum" value="手工勘误"
           onclick="onhandcorrigendum(this)"/>

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

    <!-- lizhi -->
    <tr>
        <td class="label">
            故障工单初步处理情况
        </td>
        <td colspan="3">
            <br>无线侧选项：<br>
            <input type="checkbox" id="_wireless1" name="_wireless" value="基站退服"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">基站退服
            <input type="checkbox" id="_wireless2" name="_wireless" value="小区退服"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">小区退服
            <input type="checkbox" id="_wireless3" name="_wireless" value="正常" onclick="changeFaultFirstDealDesc(this)"
                   style="vertical-align: middle;">正常
            <input type="checkbox" id="_wireless4" name="_wireless" value="设备掉电"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">设备掉电
            <input type="checkbox" id="_wireless5" name="_wireless" value="时钟参考源异常"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">时钟参考源异常
            <input type="checkbox" id="_wireless6" name="_wireless" value="单板通讯链路断"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">单板通讯链路断
            <input type="checkbox" id="_wireless7" name="_wireless" value="被汇聚射频单元链路异常"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">被汇聚射频单元链路异常
            <input type="checkbox" id="_wireless8" name="_wireless" value="光电上行链路中断"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">光电上行链路中断
            <br>射频单元：
            <input type="checkbox" id="_wireless9" name="_wireless" value="射频单元维护链路异常"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">维护链路异常
            <input type="checkbox" id="_wireless10" name="_wireless" value="射频单元光模块收发异常"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">光模块收发异常
            <input type="checkbox" id="_wireless11" name="_wireless" value="射频单元配置但不可用"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">配置但不可用
            <input type="checkbox" id="_wireless12" name="_wireless" value="射频单元光接口性能恶化"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">光接口性能恶化
            <input type="checkbox" id="_wireless13" name="_wireless" value="射频单元驻波告警"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">驻波告警
            <br>BBU：
            <input type="checkbox" id="_wireless14" name="_wireless" value="BBU CPRI接口异常"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">CPRI接口异常
            <input type="checkbox" id="_wireless15" name="_wireless" value="BBU IR光模块收发异常"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">IR光模块收发异常
            <input type="checkbox" id="_wireless16" name="_wireless" value="BBU光模块收发异常"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">光模块收发异常
            <input type="checkbox" id="_wireless17" name="_wireless" value="BBU与RRU通讯链路断"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">与RRU通讯链路断

            <br><br>动环侧选项：<br>
            <input type="checkbox" id="_dynring1" name="_dynring" value="市电无,直流电压低"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">市电无,直流电压低
            <input type="checkbox" id="_dynring2" name="_dynring" value="监控中断,无法查询相关信息"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">监控中断,无法查询相关信息
            <input type="checkbox" id="_dynring3" name="_dynring" value="正常" onclick="changeFaultFirstDealDesc(this)"
                   style="vertical-align: middle;">正常
            <input type="checkbox" id="_dynring4" name="_dynring" value="市电正常,直流电压低"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">市电正常,直流电压低
            <input type="checkbox" id="_dynring5" name="_dynring" value="门开告警" onclick="changeFaultFirstDealDesc(this)"
                   style="vertical-align: middle;">门开告警
            <input type="checkbox" id="_dynring6" name="_dynring" value="高温告警" onclick="changeFaultFirstDealDesc(this)"
                   style="vertical-align: middle;">高温告警
            <input type="checkbox" id="_dynring7" name="_dynring" value="烟雾告警" onclick="changeFaultFirstDealDesc(this)"
                   style="vertical-align: middle;">烟雾告警
            <br><input type="checkbox" id="_dynring8" name="_dynring" value="室分站点无相关信息,请实地核实故障原因"
                       onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">室分站点无相关信息,请实地核实故障原因
            <input type="checkbox" id="_dynring9" name="_dynring" value="4G站点无对应网元信息,请实地核实故障原因"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">4G站点无对应网元信息,请实地核实故障原因

            <br><br>传输侧选项：<br>
            <input type="checkbox" id="_transmission1" name="_transmission" value="E1/T1信号丢失告警"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">E1/T1信号丢失告警
            <input type="checkbox" id="_transmission2" name="_transmission" value="以太网物理接口信号丢失告警"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">以太网物理接口信号丢失告警
            <input type="checkbox" id="_transmission3" name="_transmission" value="网元脱管,无法查询相关信息"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">网元脱管,无法查询相关信息
            <input type="checkbox" id="_transmission4" name="_transmission" value="光路告警"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">光路告警
            <input type="checkbox" id="_transmission5" name="_transmission" value="正常"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">正常
            <br><input type="checkbox" id="_transmission6" name="_transmission" value="室分站点无相关信息,请实地核实故障原因"
                       onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">室分站点无相关信息,请实地核实故障原因
            <input type="checkbox" id="_transmission7" name="_transmission" value="4G站点无对应网元信息,请实地核实故障原因"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">4G站点无对应网元信息,请实地核实故障原因

            <br><br>初步判断选项：<br>
            <input type="checkbox" id="_judge1" name="_judge" value="停电导致退服" onclick="changeFaultFirstDealDesc(this)"
                   style="vertical-align: middle;">停电导致退服
            <input type="checkbox" id="_judge2" name="_judge" value="光路中断导致退服" onclick="changeFaultFirstDealDesc(this)"
                   style="vertical-align: middle;">光路中断导致退服
            <input type="checkbox" id="_judge3" name="_judge" value="传输设备故障导致退服"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">传输设备故障导致退服
            <input type="checkbox" id="_judge4" name="_judge" value="无线设备故障导致退服"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">无线设备故障导致退服
            <input type="checkbox" id="_judge5" name="_judge" value="请核实告警原因并及时处理。"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">请核实告警原因并及时处理。
            <input type="checkbox" id="_judge6" name="_judge" value="网元对应有误,请匹配正确班组。"
                   onclick="changeFaultFirstDealDesc(this)" style="vertical-align: middle;">网元对应有误,请匹配正确班组。
        </td>
    </tr>
    <script type="text/javascript">
        function changeFaultFirstDealDesc(sel) {
            var wirelessValue = '无线侧告警：';
            var dynringValue = '动环侧告警：';
            var transmissionValue = '传输侧告警：';
            var judgeValue = '初步判断';
            var wirelessTotal = 0;
            var dynringTotal = 0;
            var transmissionTotal = 0;
            var judgeTotal = 0;
            var linkFaultFirstDealDescValue = '查询各专业平台';
            var linkFaultFirstDealDesc = document.getElementById("linkFaultFirstDealDesc");

            var _wireless = document.getElementsByName("_wireless");
            for (var i = 0; i < _wireless.length; i++) {
                if (_wireless[i].type == "checkbox" && _wireless[i].checked) {
                    wirelessValue = wirelessValue + _wireless[i].value + ',';
                    wirelessTotal = wirelessTotal + 1;
                }
            }
            wirelessValue = wirelessValue.substring(0, wirelessValue.length - 1);

            var _dynring = document.getElementsByName("_dynring");
            for (var i = 0; i < _dynring.length; i++) {
                if (_dynring[i].type == "checkbox" && _dynring[i].checked) {
                    dynringValue = dynringValue + _dynring[i].value + ',';
                    dynringTotal = dynringTotal + 1;
                }
            }
            dynringValue = dynringValue.substring(0, dynringValue.length - 1);

            var _transmission = document.getElementsByName("_transmission");
            for (var i = 0; i < _transmission.length; i++) {
                if (_transmission[i].type == "checkbox" && _transmission[i].checked) {
                    transmissionValue = transmissionValue + _transmission[i].value + ',';
                    transmissionTotal = transmissionTotal + 1;
                }
            }
            transmissionValue = transmissionValue.substring(0, transmissionValue.length - 1);

            var _judge = document.getElementsByName("_judge");
            for (var i = 0; i < _judge.length; i++) {
                if (_judge[i].type == "checkbox" && _judge[i].checked) {
                    judgeValue = judgeValue + _judge[i].value + ',';
                    judgeTotal = judgeTotal + 1;
                }
            }
            judgeValue = judgeValue.substring(0, judgeValue.length - 1);

            if (wirelessTotal > 0) {
                linkFaultFirstDealDescValue = linkFaultFirstDealDescValue + '\n' + wirelessValue;
            }
            if (dynringTotal > 0) {
                linkFaultFirstDealDescValue = linkFaultFirstDealDescValue + '\n' + dynringValue;
            }
            if (transmissionTotal > 0) {
                linkFaultFirstDealDescValue = linkFaultFirstDealDescValue + '\n' + transmissionValue;
            }
            if (judgeTotal > 0) {
                linkFaultFirstDealDescValue = linkFaultFirstDealDescValue + '\n' + judgeValue;
            }
            if (wirelessTotal == 0 && dynringTotal == 0 && transmissionTotal == 0 && judgeTotal == 0) {
                linkFaultFirstDealDescValue = '';
            }

            var _judge5 = document.getElementById("_judge5");
            if (_judge5.checked) {
                linkFaultFirstDealDescValue = _judge5.value;
            }

            var _judge6 = document.getElementById("_judge6");
            if (_judge6.checked) {
                linkFaultFirstDealDescValue = _judge6.value;
            }

            linkFaultFirstDealDesc.value = linkFaultFirstDealDescValue;
        }
    </script>
    <!-- lizhi -->

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
        <c:when test="${taskName == 'SecondExcuteHumTask' && centerMonitor=='true'}">
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
    <%if (taskName.equals("FirstExcuteHumTask") && operateType.equals("46")) { %>
    <input type="button" class="submit" id="handcorrigendum" name="handcorrigendum" value="手工勘误"
           onclick="onhandcorrigendum(this)"/>
    <%} %>

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
                           defaultValue="101030601" alt="allowBlank:false" onchange="changRequired(this.value)"/>
        </td>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfGreatFault"/>*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}linkIfGreatFault"
                           id="${sheetPageName}linkIfGreatFault" initDicId="10301" defaultValue="1030102"/>
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


    <%if ("true".equals(alarmFlag)) {%>
    <tr>
        <td class="label">归因一级*</td>
        <td class="content" id="linkFaultReasonSort1">
            <eoms:comboBox name="${sheetPageName}linkFaultReasonSort"
                           id="${sheetPageName}linkFaultReasonSort" initDicId="1010317"
                           sub="${sheetPageName}linkFaultReasonSubsection"
                           defaultValue="" alt="allowBlank:false,vtext:'归因一级未填写'"/>
        </td>
        <td class="column-content" id="linkFaultReasonSort2" style="display: none"></td>
        <td class="label">归因二级*</td>
        <td class="content" id="linkFaultReasonSubsection1">
            <eoms:comboBox name="${sheetPageName}linkFaultReasonSubsection"
                           id="${sheetPageName}linkFaultReasonSubsection" initDicId="${sheetLink.linkFaultReasonSort}"
                           sub="${sheetPageName}linkFaultReasonSubsectionTwo"
                           defaultValue="" alt="allowBlank:false,vtext:'归因二级未填写'"/>
        </td>
        <td class="column-content" id="linkFaultReasonSubsection2" style="display: none"></td>
    </tr>
    <tr>
        <td class="label">归因三级*</td>
        <td colspan="3" id="linkFaultReasonSubsectionTwo1">
            <eoms:comboBox name="${sheetPageName}linkFaultReasonSubsectionTwo"
                           id="${sheetPageName}linkFaultReasonSubsectionTwo"
                           initDicId="${sheetLink.linkFaultReasonSubsection}"
                           defaultValue="" alt="allowBlank:false,vtext:'归因三级未填写'"/>
        </td>
        <td class="column-content" id="linkFaultReasonSubsectionTwo2" style="display: none"></td>
    </tr>

    <%} else if ("false".equals(alarmFlag)) {%>
    <tr>
        <td class="label">归因一级*</td>
        <td class="content" id="linkFaultReasonSort1">
            <eoms:comboBox name="${sheetPageName}linkFaultReasonSort"
                           id="${sheetPageName}linkFaultReasonSort" initDicId="1010303"
                           sub="${sheetPageName}linkFaultReasonSubsection"
                           defaultValue="${sheetLink.linkFaultReasonSort}" alt="allowBlank:false,vtext:'归因一级未填写'"/>
        </td>
        <td class="column-content" id="linkFaultReasonSort2" style="display: none"></td>
        <td class="label">归因二级*</td>
        <td class="content" id="linkFaultReasonSubsection1">
            <eoms:comboBox name="${sheetPageName}linkFaultReasonSubsection"
                           id="${sheetPageName}linkFaultReasonSubsection" initDicId="${sheetLink.linkFaultReasonSort}"
                           sub="${sheetPageName}linkFaultReasonSubsectionTwo"
                           defaultValue="${sheetLink.linkFaultReasonSubsection}"
                           alt="allowBlank:false,vtext:'归因二级未填写'"/>
        </td>
        <td class="column-content" id="linkFaultReasonSubsection2" style="display: none"></td>
    </tr>
    <tr>
        <td class="label">归因三级*</td>
        <td colspan="3" id="linkFaultReasonSubsectionTwo1">
            <eoms:comboBox name="${sheetPageName}linkFaultReasonSubsectionTwo"
                           id="${sheetPageName}linkFaultReasonSubsectionTwo"
                           initDicId="${sheetLink.linkFaultReasonSubsection}"
                           defaultValue="${sheetLink.linkFaultReasonSubsectionTwo}"
                           alt="allowBlank:false,vtext:'归因三级未填写'"/>
        </td>
        <td class="column-content" id="linkFaultReasonSubsectionTwo2" style="display: none"></td>
    </tr>

    <%}%>
    <%if (operateType.equals("46") || operateType.equals("11")) { %>
    <tr>
        <td class="label">采取措施的时间*</td>
        <td>
            <input class="text" onclick="popUpCalendar(this, this, null, null, null, true, -1)" type="text"
                   name="${sheetPageName}faultdealTime" readonly="readonly"
                   value="<%=currentDate %>" id="${sheetPageName}faultdealTime"
                   alt="vtype:'lessThen',link:'${sheetPageName}linkFaultAvoidTime',vtext:'故障消除时间不能早于故障处理时间',allowBlank:false"/>
        </td>
        <td class="label">故障处理人*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}faultTreatment"
                   id="${sheetPageName}faultTreatment" value="其他" alt="allowBlank:false"/>
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
    <%if ("101010417".equals(mainNetSortOne)) {%>
    <tr>
        <td class="label">故障原因*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}faultReason"
                           id="${sheetPageName}faultReason" initDicId="1010318"
                           defaultValue="${sheetLink.faultReason}" alt="allowBlank:false"/>
        </td>
    </tr>
    <%}%>
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
    <%if ("WL-001-00-800003".equals(mainAlarmId)) {%>
    <tr>
        <td class="label">原因类别选择*</td>
        <td colspan="3">
            <select id="select3" name="select3" onchange="choosemodel(this.value)"
                    alt="allowBlank:false,vtext:'原因类别未填写'">
                <option value="0">其它</option>
                <option value="10103030102">软件</option>
                <option value="10103030301">工程</option>
                <option value="10103030109">网管</option>
                <option value="10103030107">参数配置</option>
                <option value="10103030201">光缆</option>
                <option value="10103030303">局数据</option>
                <option value="10103030101">硬件</option>
                <option value="10103030304">被盗</option>
                <option value="10103030204">传输设备</option>
                <option value="10103030407">供电原因</option>
                <option value="10103030405">外部环境</option>
                <option value="10103030106">设备连线</option>
                <option value="10103030406">对端设备</option>
            </select>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkDealStep"/>*</td>
        <td colspan="3" id="selectStep1" name="selectStep1">
            <select id="selectStep" name="selectStep" onchange="isifdeal(this.value)" alt="allowBlank:false">
                <option value="其它">其它</option>
                <!-- modify by chenzhinian 2017-11-04 begin -->
                <c:choose>
                <c:when test="${!empty dealDescList}">
                <select id="selectStep" name="selectStep" onchange="getFaultReason(this.value)" alt="allowBlank:false">
                    <option value="">请选择</option>
                    <c:forEach items="${dealDescList }" var="dealDesc">
                        <option value="${dealDesc }">${dealDesc }</option>
                    </c:forEach>
                </select>
                </c:when>
                <c:otherwise>
                <select id="selectStep" name="selectStep" onchange="isifdeal(this.value)" alt="allowBlank:false">
                    <option value="其它">其它</option>
                </select>
                </c:otherwise>
                </c:choose>
                <!-- modify by chenzhinian 2017-11-04 end -->
                <input type="hidden" name="linkDealStep" id="linkDealStep" value=""/>
                    <%
			  	    if (steplist.size() > 0 ) {
					 for(int i=0;i<steplist.size();i++){
					 	  CommonFaultAuto auto =  (CommonFaultAuto)steplist.get(i);

					 	  String faultReasonSort = auto.getLinkFaultReasonSort();
					 	  String faultReasonSubsection = auto.getLinkFaultReasonSubsection();
					 	  String faultReasonSubsectionTwo = auto.getLinkFaultReasonSubsectionTwo();
					%>
                <input type="hidden" name="faultReasonSort" id="faultReasonSort" value="<%=faultReasonSort%>"/>
                <input type="hidden" name="faultReasonSubsection" id="faultReasonSubsection"
                       value="<%=faultReasonSubsection%>"/>
                <input type="hidden" name="faultReasonSubsectionTwo" id="faultReasonSubsectionTwo"
                       value="<%=faultReasonSubsectionTwo%>"/>

                <input type="hidden" name="faultReasonSortDictValue" id="faultReasonSortDictValue"
                       value="<eoms:id2nameDB id="<%=faultReasonSort%>" beanId="ItawSystemDictTypeDao"/>"/>
                <input type="hidden" name="faultReasonSubsectionDictValue" id="faultReasonSubsectionDictValue"
                       value="<eoms:id2nameDB id="<%=faultReasonSubsection%>" beanId="ItawSystemDictTypeDao"/>"/>
                <input type="hidden" name="faultReasonSubsectionTwoDictValue" id="faultReasonSubsectionTwoDictValue"
                       value="<eoms:id2nameDB id="<%=faultReasonSubsectionTwo%>" beanId="ItawSystemDictTypeDao"/>"/>
                    <%} }%>
        </td>
    </tr>
    <%} else {%>
    <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkDealStep"/>*</td>

        <td colspan="3">
            <!-- modify by chenzhinian 2017-11-04 begin -->
            <c:choose>
                <c:when test="${!empty dealDescList}">
                    <select id="selectStep" name="selectStep" onchange="getFaultReason(this.value)"
                            alt="allowBlank:false">
                        <option value="">请选择</option>
                        <c:forEach items="${dealDescList }" var="dealDesc">
                            <option value="${dealDesc }">${dealDesc }</option>
                        </c:forEach>
                    </select>
                </c:when>
                <c:otherwise>
                    <select id="selectStep" name="selectStep" onchange="isifdeal(this.value)" alt="allowBlank:false">
                        <option value="其它">见处理说明</option>
                        <%
                            if (steplist.size() > 0) {
                                for (int i = 0; i < steplist.size(); i++) {
                                    CommonFaultAuto auto = (CommonFaultAuto) steplist.get(i);
                                    String commonFaultDesc = auto.getCommonFaultDesc();
                        %>
                        <option value="<%=commonFaultDesc%>">
                            <%=commonFaultDesc%>
                        </option>
                        <%
                            }
                        } else {
                        %>
                        <option value="其它">其它</option>
                        <% }%>
                    </select>
                </c:otherwise>
            </c:choose>
            <!-- modify by chenzhinian 2017-11-04 end -->
            </select>
            <input type="hidden" name="linkDealStep" id="linkDealStep" value=""/>
            <%
                if (steplist.size() > 0) {
                    for (int i = 0; i < steplist.size(); i++) {
                        CommonFaultAuto auto = (CommonFaultAuto) steplist.get(i);

                        String faultReasonSort = auto.getLinkFaultReasonSort();
                        String faultReasonSubsection = auto.getLinkFaultReasonSubsection();
                        String faultReasonSubsectionTwo = auto.getLinkFaultReasonSubsectionTwo();
            %>
            <input type="hidden" name="faultReasonSort" id="faultReasonSort" value="<%=faultReasonSort%>"/>
            <input type="hidden" name="faultReasonSubsection" id="faultReasonSubsection"
                   value="<%=faultReasonSubsection%>"/>
            <input type="hidden" name="faultReasonSubsectionTwo" id="faultReasonSubsectionTwo"
                   value="<%=faultReasonSubsectionTwo%>"/>

            <input type="hidden" name="faultReasonSortDictValue" id="faultReasonSortDictValue"
                   value="<eoms:id2nameDB id="<%=faultReasonSort%>" beanId="ItawSystemDictTypeDao"/>"/>
            <input type="hidden" name="faultReasonSubsectionDictValue" id="faultReasonSubsectionDictValue"
                   value="<eoms:id2nameDB id="<%=faultReasonSubsection%>" beanId="ItawSystemDictTypeDao"/>"/>
            <input type="hidden" name="faultReasonSubsectionTwoDictValue" id="faultReasonSubsectionTwoDictValue"
                   value="<eoms:id2nameDB id="<%=faultReasonSubsectionTwo%>" beanId="ItawSystemDictTypeDao"/>"/>
            <%
                    }
                }
            %>
        </td>
    </tr>
    <%}%>
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
                           defaultValue="1030102" alt="allowBlank:false"/>
        </td>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfFinallySolveProject"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}linkIfFinallySolveProject"
                           id="${sheetPageName}linkIfFinallySolveProject" initDicId="10301"
                           defaultValue="1030101" alt="allowBlank:false"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfAddCaseDataBase"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}linkIfAddCaseDataBase"
                           id="${sheetPageName}linkIfAddCaseDataBase" initDicId="10301"
                           defaultValue="1030102" alt="allowBlank:false"/>
        </td>
    </tr>

    <%if ("101010406".equals(mainNetSortOne)) {%>
    <tr>
        <td class="label">设备厂商*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}linkIdea"
                           id="${sheetPageName}linkIdea" initDicId="1010312" sub="${sheetPageName}linkUntreadIdea"
                           alt="allowBlank:false"/>
        </td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}linkUntreadIdea"
                           id="${sheetPageName}linkUntreadIdea" initDicId="${sheetPageName}linkIdea"
                           alt="allowBlank:false"/>
        </td>

    </tr>

    <%}%>
    <tr id='confirmPreSolve'>
        <td class="label">预处理是否准确*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkIfConfirmPreSolve"
                           id="${sheetPageName}linkIfConfirmPreSolve" initDicId="10301"
                           defaultValue="1030101" alt="allowBlank:false"
                           onchange="changeConfirm(this.value);"/>
        </td>
    </tr>
    <tbody id='ConfirmReasonbody' style="display:none">
    <tr>
        <td class="label">原因说明</td>
        <td colspan="3">
			  <textarea name="confirmReason" id="confirmReason" class="textarea max"
                        alt="width:500,maxLength:1000,vtext:'最多输入500汉字'">${confirmReason}</textarea>
        </td>
    <tr>
    </tbody>
    <%} %>

    <tr>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultAvoidTime"/>*</td>
        <td>
            <input type="hidden" name="packflag" id="packflag" value="<%=packflag %>"/>

            <input type="hidden" name="centerMonitor" id="centerMonitor" value="${centerMonitor}"/>
            <input type="hidden" name="mainCheckTime" id="mainCheckTime" value="${sheetMain.mainCheckTime}"/>
            <input type="hidden" name="mainAlarmSolveDate2" id="mainAlarmSolveDate2"
                   value="${sheetMain.mainAlarmSolveDate}"/>
            <c:choose>
                <c:when test="${centerMonitor == 'true'&& sheetMain.mainAlarmSolveDate==null &&taskName == 'SecondExcuteHumTask'}">
                    <input type="text" class="text" name="${sheetPageName}linkFaultAvoidTime" readonly="readonly"
                           id="${sheetPageName}linkFaultAvoidTime"
                           value="${eoms:date2String(sheetLink.linkFaultAvoidTime)}"
                           alt="vtype:'moreThen',link:'${sheetPageName}mainFaultGenerantTime',vtext:'故障消除时间晚于故障发生时间',allowBlank:false"/>
                    <input type="button" class="submit" value="获取时间" onclick="getlinkFaultAvoidTime()"/>
                </c:when>
                <c:otherwise>
                    <input type="text" class="text" name="${sheetPageName}linkFaultAvoidTime" readonly="readonly"
                           id="${sheetPageName}linkFaultAvoidTime"
                           onclick="popUpCalendar(this, this, null, null, null, true, -1)" value="<%=newdate %>"
                           alt="vtype:'moreThen',link:'${sheetPageName}mainFaultGenerantTime',vtext:'故障消除时间晚于故障发生时间',allowBlank:false"/>
                </c:otherwise>
            </c:choose>
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
                   value="${eoms:date2String(sheetLink.linkOperRenewTime)}"
                   alt="vtype:'moreThen',link:'${sheetPageName}faultdealTime',vtext:'故障处理时间晚于告警恢复时间', allowBlank:true"/>
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
                    <c:if test="${(ccsubroleid=='' || empty ccsubroleid) && (tietasubroleid ==''|| empty tietasubroleid)}">
                        <eoms:chooser id="commonfaultSendRole" type="role" roleId="8005106" flowId="51"
                                      config="{returnJSON:true,showLeader:true}"
                                      category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                                      data="${sendUserAndRoles}"/>
                    </c:if>
                    <c:if test="${(ccsubroleid!=''&&!empty ccsubroleid)&& (tietasubroleid!=''&&!empty tietasubroleid)}">
                        <eoms:chooser id="commonfaultSendRole" type="role" roleId="8005106" flowId="51"
                                      config="{returnJSON:true,showLeader:true}"
                                      category="[{id:'${sheetPageName}dealPerformer',text:'铁塔',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                                      data="[{id:'${ccsubroleid}',nodeType:'subrole',categoryId:'copyPerformer'},{id:'${tietasubroleid}',nodeType:'subrole',categoryId:'dealPerformer'}]"/>
                    </c:if>
                    <c:if test="${(tietasubroleid!=''&&!empty tietasubroleid) && (ccsubroleid=='' || empty ccsubroleid)}">
                        <eoms:chooser id="commonfaultSendRole" type="role" roleId="8005106" flowId="51"
                                      config="{returnJSON:true,showLeader:true}"
                                      category="[{id:'${sheetPageName}dealPerformer',text:'铁塔',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                                      data="[{id:'${tietasubroleid}',nodeType:'subrole',categoryId:'dealPerformer'}]"/>
                    </c:if>
                    <c:if test="${(ccsubroleid!=''&&!empty ccsubroleid)&& (tietasubroleid ==''&& empty tietasubroleid)}">
                        <eoms:chooser id="commonfaultSendRole" type="role" roleId="8005106" flowId="51"
                                      config="{returnJSON:true,showLeader:true}"
                                      category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                                      data="[{id:'${ccsubroleid}',nodeType:'subrole',categoryId:'copyPerformer'}]"/>
                    </c:if>
                </c:when>
                <c:otherwise>
                    <c:if test="${(ccsubroleid=='' || empty ccsubroleid)&& (tietasubroleid ==''&& empty tietasubroleid)}">
                        <eoms:chooser id="commonfaultSendRole" type="role" roleId="8005106" flowId="51"
                                      config="{returnJSON:true,showLeader:true}"
                                      category="[{id:'${sheetPageName}dealPerformer',text:'代维',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                                      data="[{id:'${firstsubroleid}',nodeType:'subrole',categoryId:'dealPerformer'}]"/>
                    </c:if>
                    <c:if test="${(ccsubroleid=='' || empty ccsubroleid)&& (tietasubroleid !=''&& !empty tietasubroleid)}">
                        <eoms:chooser id="commonfaultSendRole" type="role" roleId="8005106" flowId="51"
                                      config="{returnJSON:true,showLeader:true}"
                                      category="[{id:'${sheetPageName}dealPerformer',text:'代维&铁塔',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                                      data="[{id:'${firstsubroleid}',nodeType:'subrole',categoryId:'dealPerformer'},{id:'${tietasubroleid}',nodeType:'subrole',categoryId:'dealPerformer'}]"/>
                    </c:if>
                    <c:if test="${(ccsubroleid!=''&& !empty ccsubroleid) && (tietasubroleid ==''&& empty tietasubroleid)}">
                        <eoms:chooser id="commonfaultSendRole" type="role" roleId="8005106" flowId="51"
                                      config="{returnJSON:true,showLeader:true}"
                                      category="[{id:'${sheetPageName}dealPerformer',text:'代维',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                                      data="[{id:'${firstsubroleid}',nodeType:'subrole',categoryId:'dealPerformer'},{id:'${ccsubroleid}',nodeType:'subrole',categoryId:'copyPerformer'}]"/>
                    </c:if>
                    <c:if test="${(ccsubroleid!=''&& !empty ccsubroleid) && (tietasubroleid !=''&& !empty tietasubroleid)}">
                        <eoms:chooser id="commonfaultSendRole" type="role" roleId="8005106" flowId="51"
                                      config="{returnJSON:true,showLeader:true}"
                                      category="[{id:'${sheetPageName}dealPerformer',text:'代维&铁塔',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                                      data="[{id:'${firstsubroleid}',nodeType:'subrole',categoryId:'dealPerformer'},{id:'${tietasubroleid}',nodeType:'subrole',categoryId:'dealPerformer'},{id:'${ccsubroleid}',nodeType:'subrole',categoryId:'copyPerformer'}]"/>
                    </c:if>
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
    <c:when test="${centerMonitor=='true'}">
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
            <eoms:chooser id="test" type="role" roleId="8005106" flowId="51" ifSpecial="${preDeptId}"
                          config="{returnJSON:true,showLeader:true}"
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

    var array = new Array();

    function choosemodel(input) {
        var temp = new Array();
        temp = document.getElementById("selectStep");
        var selectStep1 = document.getElementById("selectStep");
        var p = 1;

        for (var i = selectStep1.length; i >= 0; i--) {
            temp.remove(i);
        }
        temp[0] = new Option('--请选择--', '');
        if (input == '0') {
            temp[1] = new Option('其它', '其它');
        } else if (input != '0') {
            <%
  for(int i=0; i<steplist.size();i++){
    CommonFaultAuto auto =  (CommonFaultAuto)steplist.get(i);
		 String commonFaultDesc = auto.getCommonFaultDesc();
		 String linkFaultReasonSubsection =auto.getLinkFaultReasonSubsection();
		 System.out.println("------------------linkFaultReasonSubsection:-------------"+linkFaultReasonSubsection);
 %>

            array[<%=i%>] = new Array('<%=commonFaultDesc%>');
            if (input == '<%=linkFaultReasonSubsection%>') {
                temp[p] = new Option(array[<%=i%>], array[<%=i%>]);
                p++;
            }
            <%}%>
        }
    }

    //add by chenzhinian 2017-11-04 begin 传输网时根据告警id和用户选择的处理措施匹配归因一到三
    function getFaultReason(val) {
        document.getElementById("linkDealdesc").value = val;
        if (val != '') {
            Ext.Ajax.request({
                method: 'post',
                url: 'ptnpretreatmentrule.do?method=getFaultReason&alarmID=${sheetMain.mainAlarmId}&faultDealDesc=' + val.URLEncode(),
                success: function (res) {
                    var data = eoms.JSONDecode(res.responseText);
                    var linkFaultReasonSort = document.getElementById('linkFaultReasonSort');
                    var linkFaultReasonSubsection = document.getElementById('linkFaultReasonSubsection');
                    var linkFaultReasonSubsectionTwo = document.getElementById('linkFaultReasonSubsectionTwo');
                    var sortDictId = data.sort1DictId;
                    var sortDictName = data.sort1DictName;
                    if (sortDictId != '') {
                        linkFaultReasonSort.options.length = 0;
                        linkFaultReasonSort.options.add(new Option('请选择', ''));
                        for (var i = 0; i < sortDictId.length; i++) {
                            linkFaultReasonSort.options.add(new Option(sortDictName[i], sortDictId[i]));
                        }
                    }
                    sortDictId = data.sort2DictId;
                    sortDictName = data.sort2DictName;
                    if (sortDictId != '') {
                        linkFaultReasonSubsection.removeAttribute('disabled');
                        linkFaultReasonSubsection.options.length = 0;
                        linkFaultReasonSubsection.options.add(new Option('请选择', ''));
                        for (var i = 0; i < sortDictId.length; i++) {
                            linkFaultReasonSubsection.options.add(new Option(sortDictName[i], sortDictId[i]));
                        }
                    }
                    sortDictId = data.sort3DictId;
                    sortDictName = data.sort3DictName;
                    if (sortDictId != '') {
                        linkFaultReasonSubsectionTwo.removeAttribute('disabled');
                        linkFaultReasonSubsectionTwo.options.length = 0;
                        linkFaultReasonSubsectionTwo.options.add(new Option('请选择', ''));
                        for (var i = 0; i < sortDictId.length; i++) {
                            linkFaultReasonSubsectionTwo.options.add(new Option(sortDictName[i], sortDictId[i]));
                        }
                    }
                    for (var i = 0; i < linkFaultReasonSort.options.length; i++) {
                        if (data.faultReasonSort1 == linkFaultReasonSort.options[i].value) {
                            linkFaultReasonSort.options[i].selected = true;
                        }
                    }
                    for (var i = 0; i < linkFaultReasonSubsection.options.length; i++) {
                        if (data.faultReasonSort2 == linkFaultReasonSubsection.options[i].value) {
                            linkFaultReasonSubsection.options[i].selected = true;
                            break;
                        }
                    }
                    for (var i = 0; i < linkFaultReasonSubsectionTwo.options.length; i++) {
                        if (data.faultReasonSort3 == linkFaultReasonSubsectionTwo.options[i].value) {
                            linkFaultReasonSubsectionTwo.options[i].selected = true;
                            break;
                        }
                    }
                }
            });
        }
    }

    //add by chenzhinian 2017-11-04 end

    function isifdeal(input) {
        document.getElementById("linkDealdesc").value = input;
        var selectStep = document.getElementById("selectStep");//处理措施
        <%
  for(int j=0; j<steplist.size();j++){
    CommonFaultAuto auto =  (CommonFaultAuto)steplist.get(j);
		 String commonFaultDesc = auto.getCommonFaultDesc();
 %>
        if (input != '其它' && input != '') {
            if (input == '<%=commonFaultDesc%>') {
                //获取归因三级的字典值
                var i = '<%=j%>';
                var faultReasonSort = document.getElementsByName('faultReasonSort');
                var faultReasonSubsection = document.getElementsByName('faultReasonSubsection');
                var faultReasonSubsectionTwo = document.getElementsByName('faultReasonSubsectionTwo');
                //获取归因三级的中文名
                var faultReasonSortDictValue = document.getElementsByName('faultReasonSortDictValue');
                var faultReasonSubsectionDictValue = document.getElementsByName('faultReasonSubsectionDictValue');
                var faultReasonSubsectionTwoDictValue = document.getElementsByName('faultReasonSubsectionTwoDictValue');
                //为所选中的归因三级字段赋值
                var sel = document.getElementById('linkFaultReasonSort');
                sel.options[0].value = faultReasonSort[i].value;
                sel.options[0].innerText = faultReasonSortDictValue[i].value;
                var sel3 = document.getElementById('linkFaultReasonSubsection');
                sel3.options[0].value = faultReasonSubsection[i].value;
                sel3.options[0].innerText = faultReasonSubsectionDictValue[i].value;
                var sel4 = document.getElementById('linkFaultReasonSubsectionTwo');
                sel4.options[0].value = faultReasonSubsectionTwo[i].value;
                sel4.options[0].innerText = faultReasonSubsectionTwoDictValue[i].value;
                document.getElementById("linkFaultReasonSort").value = faultReasonSort[i].value;
                document.getElementById("linkFaultReasonSubsection").value = faultReasonSubsection[i].value;
                document.getElementById("linkFaultReasonSubsectionTwo").value = faultReasonSubsectionTwo[i].value;

                //隐藏下拉选项，显示文字
                var sort1 = document.getElementById('linkFaultReasonSort1');
                sort1.style.display = 'none';
                var sort2 = document.getElementById('linkFaultReasonSort2');
                sort2.innerText = faultReasonSortDictValue[i].value;
                sort2.style.display = 'block';
                var sort3 = document.getElementById('linkFaultReasonSubsection1');
                sort3.style.display = 'none';
                var sort4 = document.getElementById('linkFaultReasonSubsection2');
                sort4.innerText = faultReasonSubsectionDictValue[i].value;
                sort4.style.display = 'block';
                var sort5 = document.getElementById('linkFaultReasonSubsectionTwo1');
                sort5.style.display = 'none';
                var sort6 = document.getElementById('linkFaultReasonSubsectionTwo2');
                sort6.innerText = faultReasonSubsectionTwoDictValue[i].value;
                sort6.style.display = 'block';
                document.getElementById("linkFaultReasonSubsection").disabled = false;
                document.getElementById("linkFaultReasonSubsectionTwo").disabled = false;
            }
        } else {
            //显示下拉选项，隐藏文字文本
            var sort1 = document.getElementById('linkFaultReasonSort1');
            sort1.style.display = 'block';
            var sort2 = document.getElementById('linkFaultReasonSort2');
            sort2.style.display = 'none';
            var sort3 = document.getElementById('linkFaultReasonSubsection1');
            sort3.style.display = 'block';
            var sort4 = document.getElementById('linkFaultReasonSubsection2');
            sort4.style.display = 'none';
            var sort5 = document.getElementById('linkFaultReasonSubsectionTwo1');
            sort5.style.display = 'block';
            var sort6 = document.getElementById('linkFaultReasonSubsectionTwo2');
            sort6.style.display = 'none';
            document.getElementById("linkFaultReasonSort").disabled = false;
            document.getElementById("linkFaultReasonSubsection").disabled = true;
            document.getElementById("linkFaultReasonSubsectionTwo").disabled = true;
        }

        <%}%>
    }

    var stepflag = false;

    function setStep() {
        var stepobj = document.getElementById("linkDealStep");
        var descobj = document.getElementById("linkDealdesc");
        var ss = document.getElementById("selectStep");
        var ft = document.getElementById("${sheetPageName}faultdealTime");
        var fd = document.getElementById("${sheetPageName}faultdealdesc");
        var faultTreatment = document.getElementById("${sheetPageName}faultTreatment");
        var operaterContact = document.getElementById("${sheetPageName}operaterContact");
        var linkDealdesc = document.getElementById("${sheetPageName}linkDealdesc");

        var ope = '<%=operateType%>';
        var taskName = "<%=taskName%>";
        if (ope == '46' && taskName == 'SecondExcuteHumTask') {
            var nodeAccessories = document.getElementById("nodeAccessories").value;
            var mainAlarmSolveDate = '${sheetMain.mainAlarmSolveDate}';
            var mainCheckTime = '${sheetMain.mainCheckTime}';

            if (mainAlarmSolveDate == '' && mainCheckTime == '' && nodeAccessories == '') {
                alert("没有故障清除时间,请申请告警核实或上传附件！");
                return false;
            }
        }

        if ('<%=operateType%>' == '1') {
            var dealPerformer = document.getElementsByName("dealPerformer");
            var dealPerformerValue = dealPerformer[0].value;
            if (dealPerformerValue.indexOf(",") != -1 || dealPerformerValue.split(",").length == 2) {
                alert("派发对象只能选择一个,请您重新选择！");
                return false;
            }
        }

        if (ss.value == "其他" || ss.value == "其它") {
            stepflag = false;
            stepobj.value = "采取措施的时间：" + ft.value + "\n" + "处理措施：" + linkDealdesc.value + "\n" + "故障原因说明：" + fd.value + "\n" + "故障处理人：" + faultTreatment.value + " " + operaterContact.value;
            if (descobj.value == "") {
                alert("处理说明不能为空!");
                return false;
            } else {
                return true;
            }
        } else {
            stepflag = false;
            stepobj.value = "采取措施的时间：" + ft.value + "\n" + "处理措施：" + ss.value + "\n" + "故障原因说明：" + fd.value + "\n" + "故障处理人：" + faultTreatment.value + " " + operaterContact.value;
            return true;
        }
    }

    function changeConfirm(objectvalue) {
        var objC = document.getElementById("confirmReason");

        if (objectvalue == '1030102') {

            eoms.form.enableArea('ConfirmReasonbody');
            objC.setAttribute("alt", "allowBlank:false");

        } else {
            eoms.form.disableArea('ConfirmReasonbody', true);
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
                alert("该故障没有故障清除时间！");
                typecombo.style.display = "block";

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
                alert("该故障没有故障清除时间！");
            }
        }
    }

    function onhandcorrigendum(handcorrigendum) {
        Ext.Ajax.request({
            url: "${app}/sheet/commonfaultcorrigendum/commonfaultcorrigendum.do?method=getReply&sheetKey=${sheetMain.id}",
            method: 'POST',
            success: function (res) {
                var data = eoms.JSONDecode(res.responseText);
                var reply = data[0].reply;
                if ('false' == reply) {
                    alert('不满足生成勘误流程条件');
                } else {
                    alert('成功生成勘误流程');
                }
            }
        });
        handcorrigendum.disabled = true;
    }

</script>