<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%@page import="com.boco.eoms.sheet.complaint.task.impl.ComplaintTask" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@page import="com.boco.eoms.sheet.complaint.model.ComplaintMain" %>
<%@page import="com.boco.eoms.commons.system.dict.service.ITawSystemDictTypeManager" %>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="com.boco.eoms.sheet.base.util.Constants" %>
<%
    TawSystemSessionForm sessionform = (TawSystemSessionForm) request
            .getSession().getAttribute("sessionform");
    String taskStatus = StaticMethod.nullObject2String(request.getAttribute("taskStatus"));
    System.out.println("taskStatus taskStatus=" + taskStatus);
    if (taskStatus.equals("")) {
        taskStatus = StaticMethod.nullObject2String(request.getParameter("taskStatus"));
        System.out.println("taskStatus taskStatus taskStatus=" + taskStatus);
    }
    String isAdmin = StaticMethod.nullObject2String(request.getParameter("isAdmin"));
    String userId = sessionform.getUserid();
    String roleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId"));

    ComplaintMain main = new ComplaintMain();
    main = (ComplaintMain) request.getAttribute("sheetMain");
    String username = sessionform.getUsername();
    String userdept = sessionform.getDeptname();
    String urgentDegree = com.boco.eoms.base.util.StaticMethod.nullObject2String(main.getUrgentDegree());
    String complaintType1 = com.boco.eoms.base.util.StaticMethod.nullObject2String(main.getComplaintType1());
    String complaintType2 = com.boco.eoms.base.util.StaticMethod.nullObject2String(main.getComplaintType2());
    String complaintType3 = com.boco.eoms.base.util.StaticMethod.nullObject2String(main.getComplaintType());
    String complaintType4 = com.boco.eoms.base.util.StaticMethod.nullObject2String(main.getComplaintType4());
    String complaintType5 = com.boco.eoms.base.util.StaticMethod.nullObject2String(main.getComplaintType5());
    String complaintType6 = com.boco.eoms.base.util.StaticMethod.nullObject2String(main.getComplaintType6());
    String complaintType7 = com.boco.eoms.base.util.StaticMethod.nullObject2String(main.getComplaintType7());
    String customBrand = com.boco.eoms.base.util.StaticMethod.nullObject2String(main.getCustomBrand());
    ITawSystemDictTypeManager m = (ITawSystemDictTypeManager) ApplicationContextHolder.getInstance().getBean("ItawSystemDictTypeManager");
    String urgentDegreeCn = m.id2Name(urgentDegree);
    String customBrandCn = m.id2Name(customBrand);
    String complaintTypeCn = m.id2Name(complaintType1) + "." + m.id2Name(complaintType2) + "." + m.id2Name(complaintType3) + "." + m.id2Name(complaintType4) + "." + m.id2Name(complaintType5) + "." + m.id2Name(complaintType6) + "." + m.id2Name(complaintType7);


    System.out.println(username + "-------------username------------ph-----------------------" + username);
    System.out.println(userdept + "--------------userdept-----------ph-----------------------" + userdept);
    System.out.println(urgentDegree + "-------------urgentDegree------------ph-----------------------" + urgentDegreeCn);
    System.out.println(customBrand + "--------------customBrand-----------ph-----------------------" + customBrandCn);
    System.out.println("-------------complaintType------------ph-----------------------" + complaintTypeCn);


    ComplaintTask task = new ComplaintTask();
    String operaterType = "";
    String ifsub = "";
    String ifwaitfor = "";
    if (request.getAttribute("task") != null) {
        task = (ComplaintTask) request.getAttribute("task");
        if (task.getTaskStatus().equals("2") || taskStatus.equals("8")) {
        } else {
            taskStatus = task.getTaskStatus();
        }
        operaterType = task.getOperateType();
        ifsub = StaticMethod.nullObject2String(task.getSubTaskFlag());
        ifwaitfor = StaticMethod.nullObject2String(task.getIfWaitForSubTask());
        System.out.println("ifsub===>" + ifsub);
        System.out.println("ifwaitfor===>" + ifwaitfor);
    }

    request.setAttribute("operaterType", operaterType);
    BaseMain basemain = (BaseMain) request.getAttribute("sheetMain");
    String sendUserId = basemain.getSendUserId();
    System.out.println("sendUserId>>>>>>" + sendUserId);
    String mainStatus = com.boco.eoms.base.util.StaticMethod.nullObject2String(basemain.getStatus());
    System.out.println("mainStatus>>>>>>>>>>>>>>>>>>>>>" + mainStatus);

    request.setAttribute("taskStatus", taskStatus);
    System.out.println("taskStatus>>>>>>>>" + taskStatus);
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));

    System.out.println("taskName>>>>>>" + taskName);
//add by weichao 20141229
    String ift1dealer = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("ift1dealer"));
    String aa = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("a"));
    System.out.println("aa>>>>>>" + aa + ">>ift1dealer>>>>>>" + ift1dealer);
//add by weichao 20141229
%>


<script type="text/javascript" src="${app}/scripts/Sheet.js"></script>
<script type="text/javascript" src="${app}/scripts/widgets/TabPanel.js"></script>
<script type="text/javascript">
    Ext.onReady(function () {
        var tabConfig = {
            items: [{
                id: 'sheetinfo',
                text: '<bean:message bundle="sheet" key="sheet.sheetInfo"/>'
            }, {
                text: '<bean:message bundle="sheet" key="sheet.historyView"/>',
                url: 'complaint.do?method=showSheetDealList&sheetKey=${sheetMain.id}'
            }, {
                text: '<bean:message bundle="sheet" key="sheet.flowchar"/>',
                url: 'complaint.do?method=showWorkFlow&linkServiceName=iComplaintLinkManager&dictSheetName=dict-sheet-complaint&description=mainOperateType&sheetKey=${sheetMain.id}',
                isIframe: true
            }, {
                text: '<bean:message bundle="sheet" key="sheet.filesView"/>',
                url: 'complaint.do?method=showSheetAccessoriesList&id=${sheetMain.id}',
                isIframe: true
            }, {
                text: '<bean:message bundle="sheet" key="sheet.allSheetsView"/>',
                url: '../sheetRelation/sheetRelation.do?method=showInvokeRelationShipList&sheetKey=${sheetMain.id}',
                isIframe: true
            }]
        };
        var tabs = new eoms.TabPanel('sheet-detail-page', tabConfig);
        <%if(taskStatus.equals("2") || taskStatus.equals("3") || taskStatus.equals("8")){%>
        <%if(taskName.equals("cc")){ %>
        var url2 = "complaint.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateDeptId=${operateDeptId}&operateUserId=${operateUserId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=-15&taskStatus=${taskStatus}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}%>
        <%if(taskName.equals("reply")){ %>
        var url2 = "complaint.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}%>
        <%if(taskName.equals("advice")){ %>
        var url2 = "complaint.do?method=showRemarkPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}}%>
        //add by weichao 20141226
        <%if(taskStatus.equals("4")&&ift1dealer.equals("true")){ %>
        var url2 = "complaint.do?method=showDealAsleep&sheetKey=${sheetMain.id}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}%>
        <%if(taskStatus.equals("6")&&userId.equals(aa)){ %>
        var url2 = "complaint.do?method=showActivationSheet&sheetKey=${sheetMain.id}";
        eoms.util.appendPage("sheet-deal-content", url2);
        <%}%>
//add by weichao 20141226	


    });

    function forceOperation(obj) {

        if (obj == 1) {
            var url2 = "complaint.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceHold";
            eoms.util.appendPage("sheet-deal-content", url2);
        } else if (obj == 2) {

            var url2 = "complaint.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=nullity";
            eoms.util.appendPage("sheet-deal-content", url2);
        } else {

            var url2 = "complaint.do?method=showForceHoldPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&taskName=${taskName}&correlationKey=${sheetMain.correlationKey}&operateType=forceNullity";
            eoms.util.appendPage("sheet-deal-content", url2);
        }
    }

    function eventOperation(obj) {
        if (obj == 1) {
            var url2 = "complaint.do?method=showPhaseAdvicePage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=advice&operateFlag=driveForward&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}";
            eoms.util.appendPage("sheet-deal-content", url2, true);
        }
        if (obj == 2) {
            var url2 = "complaint.do?method=showBackCheckDealPage&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}";
            //alert(url2);
            eoms.util.appendPage("sheet-deal-content", url2, true);
        }
    }

    function operateType(url) {
        var R = GetQueryString("operateType", url);
        var operateTypeObj = document.getElementById("operateType");
        operateTypeObj.value = opertateTypeValue;
        var divObj = document.getElementById("templateButtonDiv");
        if (opertateTypeValue == "") {
            divObj.style.display = "none";
        } else {
            divObj.style.display = "";
        }
    }

    function GetQueryString(name, url) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = url.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return "";
    }

    var urlLocal = "http://10.30.174.16:8089";

    function EomsToTest() {
        alert(1);
        document.getElementById("form1").action = urlLocal + "/AiNetEmos/testTask/toTestTaskNegativeList";
        document.getElementById("form1").submit();
    }

    function EomsToSearch() {
        var url = urlLocal + "/AiNetEmos/testTask/toTestTaskList?emosID=${sheetMain.sheetId}";
        window.open(url, 'newwindow', 'height=800, width=1000, top=200, left=200,toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
    }


    function createNewSheet() {
        //alert("444444444444");
        window.location.href = "${app}/sheet/complaint/complaint.do?method=showNewSendPage&sheetKey=${sheetMain.id}&processname=ComplaintProcess";


    }


</script>

<h3 class="sheet-title">

</h3>

<!-- Sheet Tabs Start -->
<div id="sheet-detail-page">
    <!-- Sheet Detail Tab Start -->
    <div id="sheetinfo">
        <logic:present name="sheetMain" scope="request">
            <%@ include file="/WEB-INF/pages/wfworksheet/complaint/basedetailnew.jsp" %>
            <br/>


            <table class="formTable">
                <caption></caption>

                <tr>
                    <td class="label"><bean:message bundle="complaint" key="complaint.urgentDegree"/></td>
                    <td class="content" colspan="3"><eoms:id2nameDB id="${sheetMain.urgentDegree}"
                                                                    beanId="ItawSystemDictTypeDao"/></td>
                </tr>

                <!-- <tr>
        <td class="label"><bean:message bundle="complaint" key="complaint.dealTime1"/></td>
		<td class="content">
			<bean:write name="sheetMain" property="dealTime1" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
		</td>
		<td class="label"><bean:message bundle="complaint" key="complaint.dealTime2"/></td>
		<td class="content">
			<bean:write name="sheetMain" property="dealTime2" formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
		</td>
	  </tr>-->

                <tr>
                    <td class="label"><bean:message bundle="complaint" key="complaint.acceptLimit"/></td>
                    <td class="content">
                        <bean:write name="sheetMain" property="sheetAcceptLimit" formatKey="date.formatDateTimeAll"
                                    bundle="sheet" scope="request"/>
                    </td>
                    <td class="label"><bean:message bundle="complaint" key="complaint.completeLimit"/></td>
                    <td class="content">
                        <bean:write name="sheetMain" property="sheetCompleteLimit" formatKey="date.formatDateTimeAll"
                                    bundle="sheet" scope="request"/>
                    </td>
                </tr>

                <tr>
                    <td class="label"><bean:message bundle="complaint" key="complaint.mainCompleteLimitT1"/></td>
                    <td class="content">
                        <bean:write name="sheetMain" property="mainCompleteLimitT1" formatKey="date.formatDateTimeAll"
                                    bundle="sheet" scope="request"/>
                    </td>
                    <td class="label"><bean:message bundle="complaint" key="complaint.mainCompleteLimitT2"/></td>
                    <td class="content">
                        <bean:write name="sheetMain" property="mainCompleteLimitT2" formatKey="date.formatDateTimeAll"
                                    bundle="sheet" scope="request"/>
                    </td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="complaint" key="complaint.complaintType1"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.complaintType1}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                    <td class="label"><bean:message bundle="complaint" key="complaint.complaintType2"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.complaintType2}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                </tr>

                <tr>
                    <td class="label"><bean:message bundle="complaint" key="complaint.complaintType"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.complaintType}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                    <td class="label"><bean:message bundle="complaint" key="complaint.complaintType4"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.complaintType4}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                </tr>

                <tr>
                    <td class="label"><bean:message bundle="complaint" key="complaint.complaintType5"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.complaintType5}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                    <td class="label"><bean:message bundle="complaint" key="complaint.complaintType6"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.complaintType6}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                </tr>

                <tr>
                    <td class="label"><bean:message bundle="complaint" key="complaint.complaintType7"/></td>
                    <td class="content" colspan="3"><eoms:id2nameDB id="${sheetMain.complaintType7}"
                                                                    beanId="ItawSystemDictTypeDao"/></td>
                </tr>
            </table>

            <br/>
            <table class="formTable">
                <caption></caption>
                <tr>
                    <td class="label"><bean:message bundle="complaint" key="complaint.btype1"/></td>
                    <td class="content"><bean:write name="sheetMain" property="btype1" scope="request"/></td>
                    <td class="label"><bean:message bundle="complaint" key="complaint.bdeptContact"/></td>
                    <td class="content"><bean:write name="sheetMain" property="bdeptContact" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="complaint" key="complaint.bdeptContactPhone"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.bdeptContactPhone}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                    <td class="label"><bean:message bundle="complaint" key="complaint.repeatComplaintTimes"/></td>
                    <td class="content"><bean:write name="sheetMain" property="repeatComplaintTimes"
                                                    scope="request"/></td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="complaint" key="complaint.customerName"/></td>
                    <td class="content"><bean:write name="sheetMain" property="customerName" scope="request"/></td>
                    <td class="label"><bean:message bundle="complaint" key="complaint.customPhone"/></td>
                    <td class="content"><bean:write name="sheetMain" property="customPhone" scope="request"/>&nbsp;
                        <a href="${app}/sheet/complaint/complaint.do?method=showRepeatDetails&complaintTime=${sheetMain.complaintTime }&cusPhone=${sheetMain.customPhone}&mainid=${sheetMain.id}&repeatNum=1">重复投诉详情</a>
                    </td>
                </tr>
                <tr>
                    <td class="label">是否重复投诉</td>
                    <c:choose>
                        <c:when test="${ifrepeat=='是' }">
                            <td class="content"><font color="red" style="font-weight: bold">${ifrepeat}</font></td>
                        </c:when>
                        <c:otherwise>
                            <td class="content">${ifrepeat}</td>
                        </c:otherwise>
                    </c:choose>


                    <td class="label">重复投诉量</td>

                    <td class="content">${repeatNum }</td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="complaint" key="complaint.customLevel"/></td>
                    <td class="content"><bean:write name="sheetMain" property="customLevel" scope="request"/></td>
                    <td class="label"><bean:message bundle="complaint" key="complaint.customBrand"/></td>
                    <td class="content"><eoms:id2nameDB id="${sheetMain.customBrand}"
                                                        beanId="ItawSystemDictTypeDao"/></td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="complaint" key="complaint.startDealCity"/></td>
                    <td class="content"><bean:write name="sheetMain" property="startDealCity" scope="request"/></td>
                    <td class="label"><bean:message bundle="complaint" key="complaint.customAttribution"/></td>
                    <td class="content"><bean:write name="sheetMain" property="customAttribution" scope="request"/></td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="complaint" key="complaint.complaintTime"/></td>
                    <td class="content">
                        <bean:write name="sheetMain" property="complaintTime" formatKey="date.formatDateTimeAll"
                                    bundle="sheet" scope="request"/>
                    </td>
                    <td class="label"><bean:message bundle="complaint" key="complaint.faultTime"/></td>
                    <td class="content">
                        <bean:write name="sheetMain" property="faultTime" formatKey="date.formatDateTimeAll"
                                    bundle="sheet" scope="request"/>
                    </td>
                </tr>
                <!--
	  <tr>
	  	<td class="label">客户姓名</td>
		<td class="content"><bean:write name="sheetMain" property="customerName" scope="request"/></td>
		<td class="label">客户号码</td>
		<td class="content"><bean:write name="sheetMain" property="customPhone" scope="request"/></td>
	  </tr>

	<tr>
	  <td class="label"><bean:message bundle="complaint" key="complaint.complaintAdd"/></td>
	  <td class="content" colspan="3">
	  	<pre><bean:write name="sheetMain" property="complaintAdd" scope="request"/></pre>
	  </td>
	</tr>
-->
                <tr>
                    <td class="label">客服流水号</td>
                    <td class="content">
                        <pre><bean:write name="sheetMain" property="parentCorrelation" scope="request"/></pre>
                    </td>
                    <td class="label">近九个月投诉量</td>

                    <td class="content"><font color="red" style="font-weight: bold">${repeateNum1}</font>&nbsp;
                        <a href="${app}/sheet/complaint/complaint.do?method=showRepeatDetails&complaintTime=${sheetMain.complaintTime }&cusPhone=${sheetMain.customPhone}&mainid=${sheetMain.id}&repeatNum=2">重复投诉详情</a>
                    </td>

                </tr>

                <tr>
                    <td class="label"><bean:message bundle="complaint" key="complaint.complaintNum"/></td>
                    <td class="content"><bean:write name="sheetMain" property="complaintNum" scope="request"/></td>
                    <td class="label"><bean:message bundle="complaint" key="complaint.faultSite"/></td>
                    <td class="content"><bean:write name="sheetMain" property="faultSite" scope="request"/></td>
                </tr>

                <tr>
                    <td class="label">故障地市</td>
                    <td class="content"><bean:write name="sheetMain" property="mainFaultCity" scope="request"/></td>
                    <td class="label">故障区县</td>
                    <td class="content"><bean:write name="sheetMain" property="mainFaultCounty" scope="request"/></td>
                </tr>


                <tr>
                    <td class="label"><bean:message bundle="complaint" key="complaint.terminalType"/></td>
                    <td class="content" colspan="3">
                        <pre><bean:write name="sheetMain" property="terminalType" scope="request"/></pre>
                    </td>
                </tr>
                <tr>
                    <td class="label"><bean:message bundle="complaint" key="complaint.complaintDesc"/>
                    </td>
                    <td class="content" colspan="3">
                        <pre><bean:write name="sheetMain" property="complaintDesc" scope="request"/></pre>
                    </td>
                </tr>
                <!--
	  <tr>
	    <td class="label"><bean:message bundle="complaint" key="complaint.bdeptContactPhone"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.bdeptContactPhone}" beanId="ItawSystemDictTypeDao"/></td>
		<td class="label"><bean:message bundle="complaint" key="complaint.repeatComplaintTimes"/></td>
		<td class="content"><bean:write name="sheetMain" property="repeatComplaintTimes" scope="request"/></td>
	  </tr>	
	
	  <tr>
	    <td class="label"><bean:message bundle="complaint" key="complaint.customType"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.customType}" beanId="ItawSystemDictTypeDao"/></td>
		<td class="label"><bean:message bundle="complaint" key="complaint.customBrand"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.customBrand}" beanId="ItawSystemDictTypeDao"/></td>
	  </tr>	
	  
	  <tr>
	    <td class="label"><bean:message bundle="complaint" key="complaint.customLevel"/></td>
		<td class="content"><bean:write name="sheetMain" property="customLevel" scope="request"/></td>
		<td class="label"><bean:message bundle="complaint" key="complaint.customAttribution"/></td>
		<td class="content"><bean:write name="sheetMain" property="customAttribution" scope="request"/></td>
	  </tr>	

	  <tr>
	    <td class="label"><bean:message bundle="complaint" key="complaint.complaintType1"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.complaintType1}" beanId="ItawSystemDictTypeDao"/></td>
		<td class="label"><bean:message bundle="complaint" key="complaint.complaintType2"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.complaintType2}" beanId="ItawSystemDictTypeDao"/></td>
	  </tr>	

	  <tr>
	    <td class="label"><bean:message bundle="complaint" key="complaint.complaintType"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.complaintType}" beanId="ItawSystemDictTypeDao"/></td>
		<td class="label"><bean:message bundle="complaint" key="complaint.complaintType4"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.complaintType4}" beanId="ItawSystemDictTypeDao"/></td>
	  </tr>	
	  
	  <tr>
	    <td class="label"><bean:message bundle="complaint" key="complaint.complaintType5"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.complaintType5}" beanId="ItawSystemDictTypeDao"/></td>
		<td class="label"><bean:message bundle="complaint" key="complaint.complaintType6"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.complaintType6}" beanId="ItawSystemDictTypeDao"/></td>
	  </tr>		  

	  <tr>
	    <td class="label"><bean:message bundle="complaint" key="complaint.complaintType7"/></td>
		<td class="content" colspan="3"><eoms:id2nameDB id="${sheetMain.complaintType7}" beanId="ItawSystemDictTypeDao"/></td>
	  </tr>	
-->
                <tr>
                    <td class="label"><bean:message bundle="complaint" key="complaint.preDealResult"/></td>
                    <td class="content" colspan="3">
                        <pre><bean:write name="sheetMain" property="preDealResult" scope="request"/></pre>
                    </td>
                </tr>

                <!--
	  <tr>
	    <td class="label"><bean:message bundle="complaint" key="complaint.complaintReason1"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.complaintReason1}" beanId="ItawSystemDictTypeDao"/></td>
		<td class="label"><bean:message bundle="complaint" key="complaint.complaintReason2"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.complaintReason2}" beanId="ItawSystemDictTypeDao"/></td>
	  </tr>	
	  
	  <tr>
	    <td class="label"><bean:message bundle="complaint" key="complaint.complaintReason"/></td>
		<td class="content" colspan="3"><eoms:id2nameDB id="${sheetMain.complaintReason}" beanId="ItawSystemDictTypeDao"/></td>
	  </tr>	
	  <tr>
	    <td class="label"><bean:message bundle="complaint" key="complaint.startDealCity"/></td>
		<td class="content" colspan="3"><eoms:id2nameDB id="${sheetMain.toDeptId}" beanId="tawSystemAreaDao"/></td>
	  </tr>	

	  <tr>
	    <td class="label"><bean:message bundle="complaint" key="complaint.faultArea"/></td>
		<td class="content"><pre><bean:write name="sheetMain" property="faultArea" scope="request"/></pre></td>
		<td class="label"><bean:message bundle="complaint" key="complaint.faultRoad"/></td>
		<td class="content"><pre><bean:write name="sheetMain" property="faultRoad" scope="request"/></pre></td>
	  </tr>	

	  <tr>
	    <td class="label"><bean:message bundle="complaint" key="complaint.faultNo"/></td>
		<td class="content" colspan="3"><pre><bean:write name="sheetMain" property="faultNo" scope="request"/></pre></td>
	  </tr>

	  <tr>
	    <td class="label"><bean:message bundle="complaint" key="complaint.faultRoad1"/></td>
		<td class="content"><pre><bean:write name="sheetMain" property="faultRoad1" scope="request"/></pre></td>
		<td class="label"><bean:message bundle="complaint" key="complaint.faultRoad2"/></td>
		<td class="content"><pre><bean:write name="sheetMain" property="faultRoad2" scope="request"/></pre></td>
	  </tr>	

	  <tr>
	    <td class="label"><bean:message bundle="complaint" key="complaint.faultVill"/></td>
		<td class="content"><pre><bean:write name="sheetMain" property="faultVill" scope="request"/></pre></td>
		<td class="label"><bean:message bundle="complaint" key="complaint.faultVill"/></td>
		<td class="content"><eoms:id2nameDB id="${sheetMain.faultVill}" beanId="ItawSystemDictTypeDao"/></td>
	  </tr>	  

	  <tr>
	    <td class="label"><bean:message bundle="complaint" key="complaint.faultDesc"/></td>
		<td class="content" colspan="3"><pre><bean:write name="sheetMain" property="faultDesc" scope="request"/></pre></td>
	  </tr>	  	  
  
		<tr>
			<td class="label"><bean:message bundle="sheet" key="mainForm.accessories"/></td>
		    <td  colspan='3'>
		    <eoms:attachment name="sheetMain" property="sheetAccessories" 
		            scope="request" idField="sheetAccessories" appCode="complaint" 
		             viewFlag="Y"/> 
		    </td>
		</tr>-->
                <!-- add by weichao 20141229 -->
                <c:if test="${sheetMain.mainSleepStatus!='0' }">
                    <tr>
                        <td class="label">工单休眠状态</td>
                        <td class="content">
                            <c:if test="${sheetMain.mainSleepStatus=='1' }">申请休眠中</c:if>
                            <c:if test="${sheetMain.mainSleepStatus=='2' }">休眠中</c:if>
                            <c:if test="${sheetMain.mainSleepStatus=='3' }">休眠申请被驳回</c:if>
                            <c:if test="${sheetMain.mainSleepStatus=='4' }">休眠工单已激活</c:if>
                        </td>
                        <td class="label">工单休眠时长</td>
                        <td class="content">
                            <c:if test="${sheetMain.mainSleepTime=='7' }">2~7天</c:if>
                            <c:if test="${sheetMain.mainSleepTime != '7' }">${sheetMain.mainSleepTime }天</c:if>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">工单休眠申请人</td>
                        <td class="content">
                            <eoms:id2nameDB id="${sheetMain.mainSleepUser}" beanId="tawSystemUserDao"/>
                        </td>
                        <td class="label">工单旧处理时限</td>
                        <td class="content">
                            <bean:write name="sheetMain" property="mainOldCompleteLimit"
                                        formatKey="date.formatDateTimeAll" bundle="sheet" scope="request"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">工单休眠申请原因</td>
                        <td colspan="3" class="content">
                                ${sheetMain.mainSleepReason }
                        </td>
                    </tr>
                    <tr>
                        <td class="label">工单休眠申请时间</td>
                        <td class="content">
                            <bean:write name="sheetMain" property="mainT2ApplyTime" formatKey="date.formatDateTimeAll"
                                        bundle="sheet" scope="request"/>
                        </td>
                        <td class="label">工单休眠处理时间</td>
                        <td class="content">
                            <bean:write name="sheetMain" property="mainT1DealTime" formatKey="date.formatDateTimeAll"
                                        bundle="sheet" scope="request"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="label">工单激活时间</td>
                        <td class="content">
                            <bean:write name="sheetMain" property="mainActivateTime" formatKey="date.formatDateTimeAll"
                                        bundle="sheet" scope="request"/>
                        </td>
                        <td class="label">工单激活对象</td>
                        <td class="content">
                                ${sheetMain.mainActivateDealer }
                        </td>
                    </tr>
                </c:if>
                <!-- add by weichao 20141229 -->
            </table>


        </logic:present>
    </div>
    <!-- Sheet Detail Tab End -->
</div>
<!-- Sheet Tabs End -->

<!-- add by weichao 20141226 -->
<%if (taskStatus.equals("4") && ift1dealer.equals("true")) { %>
<!-- 工单休眠 add by weichao 20141223 -->
<div class="sheet-deal">
    <div class='sheet-deal-header'>
        <table>
            <tr>
                <td width="50%">
                    <bean:message bundle="sheet" key="sheet.deal"/>:
                    <select id="dealSelector">
                        <option>休眠审批</option>
                    </select>
                </td>
            </tr>
        </table>
    </div>
    <div class="sheet-deal-content" id="sheet-deal-content"></div>
</div>
<%} %>
<%if (taskStatus.equals("6") && userId.equals(aa)) { %>
<div class="sheet-deal">
    <div class='sheet-deal-header'>
        <table>
            <tr>
                <td width="50%">
                    <bean:message bundle="sheet" key="sheet.deal"/>:
                    <select id="dealSelector">
                        <option>激活休眠工单</option>
                    </select>
                </td>
            </tr>
        </table>
    </div>
    <div class="sheet-deal-content" id="sheet-deal-content"></div>
</div>
<%} %>
<!-- add by weichao 20141226 -->


<%if (taskStatus.equals("2") || taskStatus.equals("3") || taskStatus.equals("8")) {%>

<!-- Sheet Deal Content Start -->
<!-- 工单休眠 add by weichao 20141223 -->
<c:url var="urlShowAsleepOp" value="complaint.do">
    <c:param name="method" value="showAsleepOp"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
</c:url>
<!-- 工单休眠 add by weichao 20141223 -->

<!-- 移交T2 -->
<c:url var="urlShowTransmitDealPage" value="complaint.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="1"/>
</c:url>
<!-- 处理完成 -->
<c:url var="urlShowCompleteDealPage" value="complaint.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="46"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>
<!-- 归档 -->
<c:url var="urlShowHoldDealPage" value="complaint.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="18"/>
</c:url>
<!-- 待归档-退回 -->
<c:url var="urlShowRejectDealPage" value="complaint.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="17"/>
</c:url>
<!-- 质检合格 -->
<c:url var="urlShowCheckYesDealPage" value="complaint.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="56"/>
</c:url>

<!-- 质检不合格 -->
<c:url var="urlShowCheckNoDealPage" value="complaint.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="54"/>
</c:url>
<!-- 组内移交 -->
<c:url var="urlShowTransferPage" value="complaint.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="8"/>
</c:url>
<!-- 阶段回复 -->
<c:url var="urlShowPhaseReplyPage" value="complaint.do">
    <c:param name="method" value="showPhaseBackToUpPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="reply"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operaterType" value="${operaterType}"/>
</c:url>

<!-- 被驳回，显示草稿 -->
<c:url var="urlShowDraftDealPage" value="complaint.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="firstSubTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="53"/>
    <c:param name="backFlag" value="yes"/>
</c:url>
<!-- 驳回 -->
<c:url var="urlShowRejectBackPage" value="complaint.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="4"/>
</c:url>
<!-- 接单 -->
<c:url var="urlShowAcceptDealPage" value="complaint.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="61"/>
</c:url>

<!--任务分派-->
<c:url var="urlShowInputSplit" value="complaint.do">
    <c:param name="method" value="showInputSplit"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="subTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="10"/>
</c:url>
<!-- 分派回复 -->
<c:url var="urlShowDispatchPage" value="complaint.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="11"/>
    <c:param name="dealTemplateId" value="${dealTemplateId}"/>
</c:url>
<!-- 分派，处理回复通过 -->
<c:url var="urlShowDealReplyAcceptPage" value="complaint.do">
    <c:param name="method" value="showDealReplyAcceptPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="6"/>
</c:url>

<!-- 分派，处理回复不通过 -->
<c:url var="urlShowDealReplyRejectPage" value="complaint.do">
    <c:param name="method" value="showDealReplyRejectPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="subtaskName" value="subTask"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="7"/>
</c:url>
<!-- 申请延期 -->
<c:url var="urlShowSendExaminePage" value="complaint.do">
    <c:param name="method" value="showPhaseBackToUpPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="SendDeferExamine"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operaterType" value="${operateType}"/>
</c:url>

<!-- 审批 延期申请 通过 -->
<c:url var="urlShowExamineYesDealPage" value="complaint.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="66"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
</c:url>
<!-- 审批 延期申请 通过 -->
<c:url var="urlShowExamineNoDealPage" value="complaint.do">
    <c:param name="method" value="showDealPage"/>
    <c:param name="sheetKey" value="${sheetMain.id}"/>
    <c:param name="piid" value="${sheetMain.piid}"/>
    <c:param name="taskId" value="${taskId}"/>
    <c:param name="taskName" value="${taskName}"/>
    <c:param name="operateRoleId" value="${operateRoleId}"/>
    <c:param name="TKID" value="${TKID}"/>
    <c:param name="preLinkId" value="${preLinkId}"/>
    <c:param name="operateType" value="64"/>
    <c:param name="taskStatus" value="${taskStatus}"/>
</c:url>


<div class="sheet-deal">
    <div class='sheet-deal-header'>
        <table>
            <tr>
                <td width="50%">
                    <%
                        if (!taskName.equals("cc")) {
                            if (!taskName.equals("reply")) {
                                if (!taskName.equals("advice")) {
                    %>
                    <bean:message bundle="sheet" key="sheet.deal"/>:<%
                            }
                        }
                    }
                %>
                    <%if (taskName.equals("FirstExcuteHumTask")) { %>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet"
                                                                               key="common.rejectBack"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>

                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) {%>
                        <!-- 不需要等待回复 -->
                        <% if (ifwaitfor.equals("false")) {%>
                        <!-- 流程步骤，移交，阶段回复，分派 -->
                        <option value="${urlShowTransmitDealPage}"><bean:message bundle="complaint"
                                                                                 key="operateType.transmit1"/></option>
                        <option value="${urlShowCompleteDealPage}"><bean:message bundle="complaint"
                                                                                 key="operateType.dealComplete"/></option>
                        <option value="${urlShowTransferPage}"><bean:message bundle="sheet"
                                                                             key="common.transfer"/></option>
                        <option value="${urlShowRejectBackPage}">驳回客服</option>

                        <!--		  <option value="${urlShowSendExaminePage}"><bean:message bundle="complaint" key="operateType.sendToExamine"/></option>    -->
                        <option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet"
                                                                               key="event.reply"/></option>
                        <!--<option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>-->
                        <%} else {%>
                        <!--<option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>-->
                        <%} %>

                        <%} else { %>
                        <!-- 是子任务 -->
                        <!-- 不需要等待回复 -->
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDispatchPage}"><bean:message bundle="complaint"
                                                                             key="operateType.subTaskReply"/></option>
                        <%}%>
                        <% } %>
                        <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
                        <c:if test="${needDealReply == 'true'}">
                            <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyAccept"/></option>
                            <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyReject"/></option>
                        </c:if>

                        <%} %>
                    </select>
                    <%} else if (taskName.equals("SecondExcuteHumTask")) {%>
                    <select id="dealSelector"
                            onchange="javascript:eoms.util.appendPage('sheet-deal-content',this.value,true);">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet"
                                                                               key="common.rejectBack"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>

                        <!-- 是父任务 -->
                        <%if (ifsub.equals("") || ifsub.equals("false")) {%>
                        <!-- 不需要等待回复 -->
                        <% if (ifwaitfor.equals("false")) {%>
                        <!-- 流程步骤，移交，阶段回复，分派 -->
                        <option value="${urlShowCompleteDealPage}"><bean:message bundle="complaint"
                                                                                 key="operateType.dealComplete"/></option>
                        <option value="${urlShowTransferPage}"><bean:message bundle="sheet"
                                                                             key="common.transfer"/></option>
                        <!-- <option value="${urlShowSendExaminePage}"><bean:message bundle="complaint" key="operateType.sendToExamine"/></option>   -->
                        <!--<option value="${urlShowPhaseReplyPage}"><bean:message bundle="sheet" key="event.reply"/></option>-->


                        <!--  <c:if test="${sheetMain.complaintType1 != '101062501' }"><option value="${urlShowAsleepOp}">工单休眠</option></c:if>  -->
                        <!-- add by weichao 20141223 -->

                        <!--<option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>-->
                        <%} else {%>
                        <!--<option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>-->
                        <%} %>

                        <%} else { %>
                        <!-- 是子任务 -->
                        <!-- 不需要等待回复 -->
                        <% if (ifwaitfor.equals("false")) {%>
                        <option value="${urlShowDispatchPage}"><bean:message bundle="complaint"
                                                                             key="operateType.subTaskReply"/></option>
                        <%}%>
                        <%} %>
                        <option value="${urlShowInputSplit}"><bean:message bundle="sheet" key="common.split"/></option>
                        <c:if test="${needDealReply == 'true'}">
                            <option value="${urlShowDealReplyAcceptPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyAccept"/></option>
                            <option value="${urlShowDealReplyRejectPage}"><bean:message bundle="sheet"
                                                                                        key="common.dealReplyReject"/></option>
                        </c:if>

                        <%} %>
                    </select>
                    <%} else if (taskName.equals("CheckingHumTask")) {%>
                    <select id="dealSelector">
                        <%if (taskStatus.equals(Constants.TASK_STATUS_READY)) {%>
                        <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                               key="common.accept"/></option>
                        <option value="${urlShowRejectBackPage}"><bean:message bundle="sheet"
                                                                               key="common.rejectBack"/></option>
                        <%} else if (taskStatus.equals(Constants.TASK_STATUS_CLAIMED)) { %>
                        <option value="${urlShowCheckYesDealPage}"><bean:message bundle="complaint"
                                                                                 key="operateType.checkYes"/></option>
                        <option value="${urlShowCheckNoDealPage}"><bean:message bundle="complaint"
                                                                                key="operateType.checkNo"/></option>
                        <%}%>
                    </select>
                    <%} else if (taskName.equals("DraftHumTask")) {%>
                    <select id="dealSelector">
                        <option value="${urlShowCompleteDealPage}"><bean:message bundle="complaint"
                                                                                 key="operateType.draft"/></option>
                    </select>
                    <%} else if (taskName.equals("ByRejectHumTask")) {%>
                    <select id="dealSelector">
                        <option value="${urlShowDraftDealPage}"><bean:message bundle="complaint"
                                                                              key="operateType.reSend"/></option>
                    </select>
                    <%} else if (taskName.equals("DeferExamineHumTask")) {%>
                    <select id="dealSelector">
                        <option value="${urlShowExamineYesDealPage}"><bean:message bundle="complaint"
                                                                                   key="operateType.Examine"/></option>
                        <option value="${urlShowExamineNoDealPage}"><bean:message bundle="complaint"
                                                                                  key="operateType.examineReject"/></option>
                    </select>
                    <%} else if (taskName.equals("HoldHumTask")) { %>
                    <select id="dealSelector">
                        <option value="${urlShowHoldDealPage}"><bean:message bundle="complaint"
                                                                             key="operateType.hold"/></option>
                        <option value="${urlShowRejectDealPage}"><bean:message bundle="complaint"
                                                                               key="operateType.reject"/></option>
                    </select>
                    <%}%>

                </td>
            </tr>
        </table>
    </div>
    <div class="sheet-deal-content" id="sheet-deal-content"></div>
</div>

<script type="text/javascript">
    Ext.onReady(function () {
        //设置下拉框为ajax页面载入器

        eoms.Sheet.setPageLoader("dealSelector", "sheet-deal-content");

        var url = "";
        try {
            url = $("dealSelector").value + "&taskStatus=${taskStatus}";
        } catch (e) {
        }

        var dealTemplateId = "${dealTemplateId}";
        if (dealTemplateId != null && dealTemplateId != "") {
            url = "complaint.do?method=referenceTemplate&sheetKey=${sheetMain.id}&piid=${sheetMain.piid}&taskId=${taskId}&taskName=${taskName}&operateRoleId=${operateRoleId}&TKID=${TKID}&preLinkId=${preLinkId}&operateType=${operateType}&taskStatus=${taskStatus}&dealTemplateId=" + dealTemplateId
        }
        eoms.util.appendPage("sheet-deal-content", url);
    });
</script>
<!-- Sheet Deal Content End -->
<%}%>

<c:if test="${sheetMain.status==0}">
    <div class="sheet-deal-content" id="sheet-deal-content"></div>
    <%if (isAdmin.equals("true")) {%>
    <div id="buttonDisplay" style="display:block">
        <input type="button" title="执行该操作，工单将进入强制归档状态，其他人不能在处理工单"
               value="<bean:message bundle="sheet" key="button.forceHold"/>"
               onclick="$('buttonDisplay').style.display='none';forceOperation(1);">
        <input type="button" title="执行该操作，工单将进入强制作废状态，其他人不能在处理工单"
               value="<bean:message bundle="sheet" key="button.forceNullity"/>"
               onclick="$('buttonDisplay').style.display='none';forceOperation(3);">
        <input type="button" value="<bean:message bundle="sheet" key="event.advice"/>"
               onclick="$('buttonDisplay').style.display='none';eventOperation(1);">
    </div>
    <% } else if (taskStatus.equals("5") && !taskName.equals("cc") && !taskName.equals("reply") && !taskName.equals("advice")) {%>
    <div id="buttonDisplay">
        <input type="button" value="<bean:message bundle="sheet" key="event.advice"/>"
               onclick="$('buttonDisplay').style.display='none';eventOperation(1);">
    </div>
    <% } else if ((taskStatus == null || taskStatus.equals("")) && (userId.equals(sendUserId))) {%>
    <div id="advice" style="display:block">
        <input type="button" title="执行该操作，工单将进入作废状态，其他人不能在处理工单"
               value="<bean:message bundle="sheet" key="button.nullity"/>"
               onclick="$('advice').style.display='none';forceOperation(2);">
        <input type="button" value="<bean:message bundle="sheet" key="event.advice"/>"
               onclick="$('advice').style.display='none';eventOperation(1);">
    </div>
    <% }%>
</c:if>

<c:if test="${sheetMain.status==1 && sheetMain.mainIfCheck =='0' }">
    <div class="sheet-deal-content" id="sheet-deal-content"></div>

    <div id="advice" style="display:block">
        <input type="button" value="质检" onclick="$('advice').style.display='none';eventOperation(2);">
    </div>
</c:if>
<%if (roleId.indexOf("213") > -1) {%>
<input type="button" title="newSheet" value="<bean:message bundle='sheet' key='button.creatnew'/>"
       onclick="createNewSheet();">
<%} %>
<%@ include file="/common/footer_eoms.jsp" %>