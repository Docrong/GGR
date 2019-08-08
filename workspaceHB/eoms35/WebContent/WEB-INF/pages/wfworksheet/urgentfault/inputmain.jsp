<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%

    request.setAttribute("roleId", "195");
    long localTimes = com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    System.out.println("=====taskName==" + taskName);

%>

<script type="text/javascript">
    var MiddleReport = "91";
    var BackReport = "92";

    var urgentFaltAdmin = "196";
    var greatFaltAdmin = "197";

    changeOperate = function (input) {

        //alert("aaaaaaa"+input);

        if (input == MiddleReport) {

            //alert($('${sheetPageName}status').value);
            if ($('${sheetPageName}status').value != '1') {
                chooser_testSendRole.enable();
                $('${sheetPageName}phaseId').value = "MiddleReportAffirmHumTask";
                $('${sheetPageName}operateType').value = "91";
            }
            //chooser_testSendRole.setRoleId(urgentFaltAdmin);

            //$('${sheetPageName}roleName').innerHTML = '<bean:message bundle="urgentfault" key="role.urgentFaltAdmin"/>';
            //alert($('${sheetPageName}operateType').value);
            //alert($('${sheetPageName}phaseId').value);
            eoms.form.disableArea('shihou1', true);
            eoms.form.disableArea('shihou2', true);
            eoms.form.disableArea('shihou3', true);
            eoms.form.disableArea('shihou4', true);

        } else if (input == BackReport) {

            if ($('${sheetPageName}status').value != '1') {
                chooser_testSendRole.enable();
                $('${sheetPageName}phaseId').value = "BackReportAffirmHumTask";
                $('${sheetPageName}operateType').value = "92";
            }
            //chooser_testSendRole.setRoleId(greatFaltAdmin);


            //$('${sheetPageName}roleName').innerHTML = '<bean:message bundle="urgentfault" key="role.greatFaltAdmin"/>';
            eoms.form.enableArea('shihou1');
            eoms.form.enableArea('shihou2');
            eoms.form.enableArea('shihou3');
            eoms.form.enableArea('shihou4');

        } else {
            chooser_testSendRole.disable();
        }

    }

    changeAccessories = function (input) {

    }

    function selectLimit(obj) {
        if ($("${sheetPageName}mainNetSortOne").value == null || $("${sheetPageName}mainNetSortOne").value == "") {
            // alert("请选择故障专业！");
            return false;
        }

        var temp1 = $("${sheetPageName}mainNetSortOne").value;
        var temp2 = $("${sheetPageName}mainNetSortTwo").value;
        var temp3 = $("${sheetPageName}mainNetSortThree").value;

        Ext.Ajax.request({
            method: "get",
            url: "urgentfault.do?method=newShowLimit&specialty1=" + temp1 + "&specialty2=" + temp2 + "&specialty3=" + temp3 + "&flowName=UrgentFaultMainFlowProcess",
            success: function (x) {
                var o = eoms.JSONDecode(x.responseText);
                if ((o.acceptLimit == null || o.acceptLimit == "") && (o.replyLimit == null || o.replyLimit == "")) {
                    //$("${sheetPageName}sheetAcceptLimit").value = "";
                    //$('${sheetPageName}sheetCompleteLimit').value = "";
                } else {
                    var times =<%=localTimes%>;
                    var dt1 = new Date(times).add(Date.MINUTE, parseInt(o.acceptLimit, 10));
                    var dt2 = dt1.add(Date.MINUTE, parseInt(o.replyLimit, 10));
                    //$("${sheetPageName}sheetAcceptLimit").value = dt1.format('Y-m-d H:i:s');
                    //$('${sheetPageName}sheetCompleteLimit').value = dt2.format('Y-m-d H:i:s');
                }
            }
        });
    }

    //changeOperate($('${sheetPageName}mainSendContentType').value);
</script>

<c:if test="${empty sheetMain.piid}">
    <script type="text/javascript">
        var dt = new Date();
        //alert(dt);
        $("${sheetPageName}mainFaultGenerantTime").value = dt.format('Y-m-d H:i:s');
        $("${sheetPageName}mainFaultAvoidTime").value = dt.format('Y-m-d H:i:s');
        $("${sheetPageName}mainAffectStartTime").value = dt.format('Y-m-d H:i:s');


    </script>
</c:if>

<%@ include file="/WEB-INF/pages/wfworksheet/urgentfault/baseinputmainhtmlnew.jsp" %>
<input type="hidden" name="mainId" value="${sheetMain.id}"/>
<input type="hidden" name="${sheetPageName}processTemplateName" value="UrgentFaultMainFlowProcess"/>
<input type="hidden" name="${sheetPageName}operateName" value="newWorksheet"/>
<c:if test="${status==1}">
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value=""/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18"/>
</c:if>
<c:if test="${status==0}">
    <c:if test="${empty sheetMain.mainSendContentType}">
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="DraftHumTask"/>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="22"/>
    </c:if>
    <c:if test="${!empty sheetMain.mainSendContentType}">
        <c:if test="${sheetMain.mainSendContentType == '91'}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId"
                   value="MiddleReportAffirmHumTask"/>
            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="91"/>
            <script type="text/javascript">
                Ext.onReady(function () {
                    eoms.form.disableArea('shihou1', true);
                    eoms.form.disableArea('shihou2', true);
                    eoms.form.disableArea('shihou3', true);
                    eoms.form.disableArea('shihou4', true);
                });
            </script>
        </c:if>
        <c:if test="${sheetMain.mainSendContentType == '92'}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId"
                   value="BackReportAffirmHumTask"/>
            <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="92"/>
            <script type="text/javascript">
                Ext.onReady(function () {
                    eoms.form.enableArea('shihou1');
                    eoms.form.enableArea('shihou2');
                    eoms.form.enableArea('shihou3');
                    eoms.form.enableArea('shihou4');
                });
            </script>
        </c:if>
    </c:if>
</c:if>

<input type="hidden" name="${sheetPageName}beanId" value="iUrgentFaultMainManager"/>
<input type="hidden" name="${sheetPageName}mainClassName"
       value="com.boco.eoms.sheet.urgentfault.model.UrgentFaultMain"/> <!--main表Model对象类名-->
<input type="hidden" name="${sheetPageName}linkClassName"
       value="com.boco.eoms.sheet.urgentfault.model.UrgentFaultLink"/> <!--link表Model对象类名-->

<!-- templateName rule -->
<input type="hidden" name="templateNameRule" value="${sheetPageName}mainNetSortOne;${sheetPageName}mainNetSortTwo;
	                ${sheetPageName}mainNetSortThree"/>

<!-- sheet info -->
<br/>
<table id="sheet" class="formTable">


    <tr>
        <td class="label"><bean:message bundle="urgentfault" key="operate.sendType"/>*</td>
        <td colspan="3">
            <eoms:dict key="dict-sheet-urgentfault" onchange="changeOperate(this.value);"
                       dictId="subOperateType" selectId="${sheetPageName}mainSendContentType"
                       defaultId="${sheetMain.mainSendContentType}" beanId="selectXML" alt="allowBlank:false"/>
        </td>
    </tr>


</table>
<br/>

<table class="formTable">

    <tr>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainIfGreatFault"/>*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}mainIfGreatFault" id="${sheetPageName}mainIfGreatFault"
                           initDicId="10301" defaultValue="${sheetMain.mainIfGreatFault}" alt="allowBlank:false"/>
        </td>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainGreatFaultId"/></td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}mainGreatFaultId"
                   id="${sheetPageName}mainGreatFaultId" value="${sheetMain.mainGreatFaultId}" alt="allowBlank:true"/>
            <!-- <eoms:comboBox name="${sheetPageName}mainGreatFaultId" id="${sheetPageName}mainGreatFaultId"
		  	  initDicId="1011609" defaultValue="${sheetMain.mainGreatFaultId}" />-->
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainFaultSheetId"/></td>
        <td><input type="text" class="text" name="${sheetPageName}mainFaultSheetId"
                   id="${sheetPageName}mainFaultSheetId" value="${sheetMain.mainFaultSheetId}" alt="allowBlank:true"/>
        </td>
        <td class="label"></td>
        <td></td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainFaultGenerantTime"/>*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}mainFaultGenerantTime" readonly="readonly"
                   id="${sheetPageName}mainFaultGenerantTime"
                   value="${eoms:date2String(sheetMain.mainFaultGenerantTime)}"
                   onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt="allowBlank:false"/>
        </td>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainFaultGenerantPlace"/>*</td>
        <td>
            <div id="areaview" class="hide"></div>
            <script type="text/javascript">

                //viewer
                var areaViewer = new Ext.JsonView("areaview",
                    '<div class="viewlistitem-{nodeType}">{name}</div>',
                    {
                        emptyText: '<div>没有选择项目</div>'
                    }
                );
                var data = "[{id:'${sheetMain.toDeptId}',name:'<eoms:id2nameDB id='${sheetMain.toDeptId}' beanId='tawSystemAreaDao'/>',nodeType:'area'}]";
                areaViewer.jsonData = eoms.JSONDecode(data);
                areaViewer.refresh();

                //area tree
                var deptTreeAction = '${app}/xtree.do?method=areaTree';
                deptetree = new xbox({

                    btnId: '${sheetPageName}showDept',
                    dlgId: 'dlg3',

                    treeDataUrl: deptTreeAction,
                    treeRootId: '-1',
                    treeRootText: '地市',
                    treeChkMode: 'single',
                    treeChkType: 'area',
                    showChkFldId: '${sheetPageName}showDept',
                    saveChkFldId: '${sheetPageName}toDeptId',
                    viewer: areaViewer
                });
            </script>

            <input type="text" class="text" readonly="readonly" name="${sheetPageName}showDept"
                   id="${sheetPageName}showDept" alt="allowBlank:false,vtext:'请选择地域名称'"
                   value="<eoms:id2nameDB id='${sheetMain.toDeptId}' beanId='tawSystemAreaDao'/>"/>
            <input type="hidden" name="${sheetPageName}toDeptId" id="${sheetPageName}toDeptId"
                   value="${sheetMain.toDeptId}"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainNetSortOne"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}mainNetSortOne" id="${sheetPageName}mainNetSortOne"
                           initDicId="1010104" sub="${sheetPageName}mainNetSortTwo"
                           defaultValue="${sheetMain.mainNetSortOne}" alt="allowBlank:false"
                           onchange="selectLimit(this.value);"/>
        </td>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainNetSortTwo"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}mainNetSortTwo" id="${sheetPageName}mainNetSortTwo"
                           initDicId="${sheetMain.mainNetSortOne}" sub="${sheetPageName}mainNetSortThree"
                           defaultValue="${sheetMain.mainNetSortTwo}"
                           alt="allowBlank:false" onchange="selectLimit(this.value);"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainNetSortThree"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}mainNetSortThree" id="${sheetPageName}mainNetSortThree"
                           initDicId="${sheetMain.mainNetSortTwo}" defaultValue="${sheetMain.mainNetSortThree}"
                           alt="allowBlank:false" onchange="selectLimit(this.value);"/>
        </td>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainEquipmentFactory"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}mainEquipmentFactory" id="${sheetPageName}mainEquipmentFactory"
                           initDicId="1010103" defaultValue="${sheetMain.mainEquipmentFactory}" alt="allowBlank:true"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainFaultDesc"/>*</td>
        <td colspan="3">
            <textarea name="mainFaultDesc" id="mainFaultDesc" class="textarea max"
                      alt="allowBlank:false">${sheetMain.mainFaultDesc}</textarea>
        </td>
    </tr>

    <!--shi hou  start-->
    <tr id="shihou1" style="display:none">
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainFaultReason"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}mainFaultReason" id="${sheetPageName}mainFaultReason"
                           initDicId="1010303" sub="${sheetPageName}mainFaultSubReason"
                           defaultValue="${sheetMain.mainFaultReason}" alt="allowBlank:false"/>
        </td>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainFaultSubReason"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}mainFaultSubReason" id="${sheetPageName}mainFaultSubReason"
                           initDicId="${sheetMain.mainFaultReason}" defaultValue="${sheetMain.mainFaultSubReason}"
                           alt="allowBlank:false"/>
        </td>
    </tr>
    <tr id="shihou2" style="display:none">
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainUrgentLeve"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}mainUrgentLeve" id="${sheetPageName}mainUrgentLeve"
                           initDicId="1010102" defaultValue="${sheetMain.mainUrgentLeve}" alt="allowBlank:false"/>
        </td>

    </tr>
    <!--shi hou  end-->
    <tr>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainTrafficLoss"/></td>
        <td>
            <input type="text" class="text" name="${sheetPageName}mainTrafficLoss" id="${sheetPageName}mainTrafficLoss"
                   value="${sheetMain.mainTrafficLoss}" alt="allowBlank:true"/>
        </td>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainTriggerUserComplaint"/></td>
        <td>
            <input type="text" class="text" name="${sheetPageName}mainTriggerUserComplaint"
                   id="${sheetPageName}mainTriggerUserComplaint" value="${sheetMain.mainTriggerUserComplaint}"
                   alt="allowBlank:true"/>
        </td>
    </tr>


    <tr>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainIfAffectOperation"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}mainIfAffectOperation" id="${sheetPageName}mainIfAffectOperation"
                           initDicId="10301" defaultValue="${sheetMain.mainIfAffectOperation}" alt="allowBlank:false"/>
        </td>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainAffectStartTime"/>*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}mainAffectStartTime" readonly="readonly"
                   id="${sheetPageName}mainAffectStartTime" value="${eoms:date2String(sheetMain.mainAffectStartTime)}"
                   onclick="popUpCalendar(this, this,null,null,null,false,-1)" alt="allowBlank:false"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainAffectArea"/>*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}mainAffectArea"
                   id="${sheetPageName}mainAffectArea" value="${sheetMain.mainAffectArea}" alt="allowBlank:true"/>

            <!-- <eoms:comboBox name="${sheetPageName}mainAffectArea" id="${sheetPageName}mainAffectArea"
		  	   initDicId="1011610" defaultValue="${sheetMain.mainAffectArea}" /> -->
        </td>

        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainImpactUserNum"/></td>
        <td>
            <input type="text" class="text" name="${sheetPageName}mainImpactUserNum"
                   id="${sheetPageName}mainImpactUserNum" value="${sheetMain.mainImpactUserNum}" alt="allowBlank:true"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainAffectOperationDesc"/></td>
        <td colspan="3">
            <textarea name="${sheetPageName}mainAffectOperationDesc" id="${sheetPageName}mainAffectOperationDesc"
                      class="textarea max">${sheetMain.mainAffectOperationDesc}</textarea>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainAffectOperationLevel"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}mainAffectOperationLevel"
                           id="${sheetPageName}mainAffectOperationLevel" initDicId="1010202"
                           defaultValue="${sheetMain.mainAffectOperationLevel}" alt="allowBlank:false"/>
        </td>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainAffectOperationSort"/>*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}mainAffectOperationSort"
                   id="${sheetPageName}mainAffectOperationSort" value="${sheetMain.mainAffectOperationSort}"
                   alt="allowBlank:false"/>
        </td>
    </tr>


    <tr>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainIfReport"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}mainIfReport" id="${sheetPageName}mainIfReport" initDicId="10301"
                           defaultValue="${sheetMain.mainIfReport}" alt="allowBlank:false"/>
        </td>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainIfAchieveProjectStartup"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}mainIfAchieveProjectStartup"
                           id="${sheetPageName}mainIfAchieveProjectStartup" initDicId="10301"
                           defaultValue="${sheetMain.mainIfAchieveProjectStartup}" alt="allowBlank:false"
                           onchange="changeAccessories(this.value);"/>
        </td>
    </tr>


    <!--shi hou start -->
    <tr id="shihou3" style="display:none">
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainEquipIntegrator"/>*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}mainEquipIntegrator"
                           id="${sheetPageName}mainEquipIntegrator" initDicId="1010201"
                           defaultValue="${sheetMain.mainEquipIntegrator}" alt="allowBlank:false"/>
        </td>
    </tr>

    <tr id="shihou4" style="display:none">
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainFaultAvoidTime"/>*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}mainFaultAvoidTime" readonly="readonly"
                   id="${sheetPageName}mainFaultAvoidTime" value="${eoms:date2String(sheetMain.mainFaultAvoidTime)}"
                   onclick="popUpCalendar(this, this,null,null,null,false,-1)" alt="allowBlank:false"/>
        </td>
        <td class="label"><bean:message bundle="urgentfault" key="urgentfault.mainAffectTimeLength"/>*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}mainAffectTimeLength"
                   id="${sheetPageName}mainAffectTimeLength" value="${sheetMain.mainAffectTimeLength}"
                   alt="allowBlank:false"/>
        </td>
    </tr>

    <!--shi hou end -->

</table>

<br/>
<table class="formTable">


    <%-- accessories --%>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
        </td>
        <td colspan="3">
            <eoms:attachment name="tawSheetAccess" property="accesss"
                             scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>
        </td>
    </tr>
    <tr id="accessories">
        <td class="label">
            <bean:message bundle="sheet" key="mainForm.accessories"/>
        </td>
        <td colspan="3">
            <!--<eoms:attachment idList="" idField="${sheetPageName}sheetAccessories" appCode="urgentfault" />-->
            <eoms:attachment name="sheetMain" property="sheetAccessories"
                             scope="request" idField="${sheetPageName}sheetAccessories" appCode="urgentfault"/>

        </td>
    </tr>

</table>


<br/>

<c:if test="${status!=1}">
    <c:if test="${!empty sheetMain.mainSendContentType}">
        <fieldset id="link1" style="display:block">

            <legend>
                <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
                <span id="${sheetPageName}roleName">
					<bean:message bundle="urgentfault" key="role.urgentFaltAdmin"/>
				</span>

            </legend>

            <eoms:chooser id="testSendRole" type="role" roleId="196" flowId="53"
                          config="{returnJSON:true,showLeader:true}"
                          category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                          data="${sendUserAndRoles}"/>


        </fieldset>
    </c:if>

    <c:if test="${empty sheetMain.mainSendContentType}">
        <fieldset>

            <legend>
                <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
                <span id="${sheetPageName}roleName">
					<bean:message bundle="urgentfault" key="role.urgentFaltAdmin"/>
				</span>

            </legend>

            <eoms:chooser id="testSendRole" type="role" roleId="196" flowId="53"
                          config="{returnJSON:true,showLeader:true}"
                          category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                          data="${sendUserAndRoles}"/>

            <script type="text/javascript">
                Ext.onReady(function () {
                    chooser_testSendRole.disable();
                });
            </script>
        </fieldset>
    </c:if>

</c:if>
		