<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%

    request.setAttribute("roleId", "190");

    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    long localTimes = com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
    System.out.println("=====taskName==" + taskName);
    String processname = "CommonFaultMainFlowProcess";
    String taskname = "FirstExcuteHumTask";
%>

<script type="text/javascript">
    function selectLimit(obj) {

        if ($("${sheetPageName}mainNetSortOne").value == null || $("${sheetPageName}mainNetSortOne").value == "") {
            // alert("请选择故障专业！");
            return false;
        }
        if ($("${sheetPageName}mainFaultResponseLevel").value == null || $("${sheetPageName}mainFaultResponseLevel").value == "") {
            // alert("请选择故障处理响应级别！");
            return false;
        }
        var temp1 = $("${sheetPageName}mainNetSortOne").value;
        var temp2 = $("${sheetPageName}mainFaultResponseLevel").value;
        var temp3 = $("${sheetPageName}mainNetSortTwo").value;
        var temp4 = $("${sheetPageName}mainNetSortThree").value;

        if ($("${sheetPageName}mainFaultResponseLevel").value != null && $("${sheetPageName}mainFaultResponseLevel").value != "") {

            Ext.Ajax.request({
                method: "get",
                url: "commonfault.do?method=showDealLimit&faultResponseLevel=" + temp2,
                success: function (x) {
                    var o = eoms.JSONDecode(x.responseText);

                    if (o.acceptLimit == null || o.acceptLimit == "") {
                        $("${sheetPageName}sheetAcceptLimit").value = "";
                        $('${sheetPageName}sheetCompleteLimit').value = "";
                        //alert("您选择的故障处理响应级别没有配置工单时长！");
                    } else {
                        var times =<%=localTimes%>;
                        var acceptdt = new Date(times).add(Date.MINUTE, parseInt(o.acceptLimit, 10));
                        var replydt = new Date(times).add(Date.MINUTE, parseInt(o.replyLimit, 10));
                        $("${sheetPageName}sheetAcceptLimit").value = acceptdt.format('Y-m-d H:i:s');
                        $('${sheetPageName}sheetCompleteLimit').value = replydt.format('Y-m-d H:i:s');
                        $("${sheetPageName}acceptLimit").value = parseInt(o.acceptLimit, 10);
                        $('${sheetPageName}replyLimit').value = parseInt(o.replyLimit, 10);
                    }

                }
            });
        }

        Ext.Ajax.request({
            method: "get",
            url: "commonfault.do?method=showLimit&faultSpecialty=" + temp1 + "&faultSpecialty3=" + temp3 + "&faultSpecialty4=" + temp4 + "&faultResponseLevel=" + temp2,
            success: function (x) {
                var o = eoms.JSONDecode(x.responseText);

                if ((o.t1Limit == null || o.t1Limit == "") && (o.t2Limit == null || o.t2Limit == "")) {
                    $("${sheetPageName}mainCompleteLimitT1").value = "";
                    $('${sheetPageName}mainCompleteLimitT2').value = "";
                    $('${sheetPageName}mainCompleteLimitT3').value = "";
                    //alert("您选择的故障专业类型和故障处理响应级别没有配置工单时长！");
                } else {
                    var times =<%=localTimes%>;
                    var dt1 = new Date(times).add(Date.MINUTE, parseInt(o.t1Limit, 10));
                    var dt2 = dt1.add(Date.MINUTE, parseInt(o.t2Limit, 10));
                    var dt3 = dt2.add(Date.MINUTE, parseInt(o.t3Limit, 10));
                    dt3 = dt3.add(Date.SECOND, -1);
                    $("${sheetPageName}mainCompleteLimitT1").value = dt1.format('Y-m-d H:i:s');
                    $('${sheetPageName}mainCompleteLimitT2').value = dt2.format('Y-m-d H:i:s');
                    $('${sheetPageName}mainCompleteLimitT3').value = dt3.format('Y-m-d H:i:s');
                    $("${sheetPageName}t1Limit").value = parseInt(o.t1Limit, 10);
                    $('${sheetPageName}t2Limit').value = parseInt(o.t2Limit, 10);
                    $('${sheetPageName}t3Limit').value = parseInt(o.t3Limit, 10);
                }

            }
        });

    }

</script>

<c:if test="${empty sheetMain.piid}">
    <script type="text/javascript">
        if ($("${sheetPageName}mainFaultGenerantTime").value == "") {
            var times =<%=localTimes%>;
            var dt = new Date(times);
            //alert(dt);
            $("${sheetPageName}mainFaultGenerantTime").value = dt.format('Y-m-d H:i:s');
        }

    </script>
</c:if>


<%@ include file="/WEB-INF/pages/wfworksheet/commonfault/baseinputmainhtmlnew.jsp" %>
<input type="hidden" name="linkBeanId" value="iCommonFaultLinkManager"/>
<input type="hidden" name="interfaceType" value=""/>
<input type="hidden" name="methodType" value=""/>
<input type="hidden" name="sendType" value=""/>
<input type="hidden" name="${sheetPageName}processTemplateName" value="CommonFaultMainFlowProcess"/>
<input type="hidden" name="${sheetPageName}sheetTemplateName" value="CommonFaultMainFlowProcess"/>
<input type="hidden" name="${sheetPageName}ifAgent" value="1"/>
<input type="hidden" name="${sheetPageName}operateName" value="newWorksheet"/>
<c:if test="${status==0}">
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="FirstExcuteTask"/>
</c:if>

<input type="hidden" name="${sheetPageName}beanId" value="iCommonFaultMainManager"/>
<input type="hidden" name="${sheetPageName}mainClassName"
       value="com.boco.eoms.sheet.commonfault.model.CommonFaultMain"/> <!--main表Model对象类名-->
<input type="hidden" name="${sheetPageName}linkClassName"
       value="com.boco.eoms.sheet.commonfault.model.CommonFaultLink"/> <!--link表Model对象类名-->

<!-- templateName rule -->
<input type="hidden" name="templateNameRule" value="${sheetPageName}mainNetSortOne;${sheetPageName}mainNetSortTwo;
	                ${sheetPageName}mainNetSortThree;${sheetPageName}mainFaultResponseLevel"/>

<!-- sheet info -->

<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="0"/>
<input type="hidden" name="${sheetPageName}acceptLimit" id="${sheetPageName}acceptLimit" value="-1"/>
<input type="hidden" name="${sheetPageName}replyLimit" id="${sheetPageName}replyLimit" value="-1"/>
<input type="hidden" name="${sheetPageName}t1Limit" id="${sheetPageName}t1Limit" value="-1"/>
<input type="hidden" name="${sheetPageName}t2Limit" id="${sheetPageName}t2Limit" value="-1"/>
<input type="hidden" name="${sheetPageName}t3Limit" id="${sheetPageName}t3Limit" value="-1"/>


<br/>


<table id="sheet" class="formTable">
    <c:if test="${status!=1}">


        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainAlarmId"/></td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}mainAlarmId" id="${sheetPageName}mainAlarmId"
                       value="${sheetMain.mainAlarmId}" alt="allowBlank:true"/>
            </td>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainAlarmNum"/></td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}mainAlarmNum" id="${sheetPageName}mainAlarmNum"
                       value="${sheetMain.mainAlarmNum}" alt="allowBlank:true"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainAlarmSolveDate"/></td>
            <td colspan="3">
                <input type="text" class="text" name="${sheetPageName}mainAlarmSolveDate" readonly="readonly"
                       id="${sheetPageName}mainAlarmSolveDate" value="${eoms:date2String(sheetMain.mainAlarmSolveDate)}"
                       onclick="popUpCalendar(this, this)"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainAlarmLevel"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}mainAlarmLevel"
                       id="${sheetPageName}mainAlarmLevel" value="${sheetMain.mainAlarmLevel}" alt="allowBlank:true"/>
            </td>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainAlarmSource"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}mainAlarmSource"
                       id="${sheetPageName}mainAlarmSource" value="${sheetMain.mainAlarmSource}" alt="allowBlank:true"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainAlarmLogicSort"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}mainAlarmLogicSort"
                       id="${sheetPageName}mainAlarmLogicSort" value="${sheetMain.mainAlarmLogicSort}"
                       alt="allowBlank:true"/>
            </td>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainAlarmLogicSortSub"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}mainAlarmLogicSortSub"
                       id="${sheetPageName}mainAlarmLogicSortSub" value="${sheetMain.mainAlarmLogicSortSub}"
                       alt="allowBlank:true"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainAlarmDesc"/></td>
            <td colspan="3">
		  		<textarea name="mainAlarmDesc" id="mainAlarmDesc" class="textarea max"
                          alt="allowBlank:ture,width:500,maxLength:1000,vtext:'最多输入1000汉字'">${sheetMain.mainAlarmDesc}</textarea>
            </td>
        </tr>

    </c:if>

    <c:if test="${status==1}">
        <!-- 归档时显示已经选择的字典值 -->

    </c:if>

</table>

<br/>

<table id="sheet" class="formTable">
    <c:if test="${status!=1}">
        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainNetSortOne"/>*</td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainNetSortOne" id="${sheetPageName}mainNetSortOne"
                               sub="${sheetPageName}mainNetSortTwo" initDicId="1010104"
                               defaultValue="${sheetMain.mainNetSortOne}" alt="allowBlank:false"
                               onchange="selectLimit(this.value);"/>
            </td>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainNetSortTwo"/>*</td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainNetSortTwo" id="${sheetPageName}mainNetSortTwo"
                               sub="${sheetPageName}mainNetSortThree" initDicId="${sheetMain.mainNetSortOne}"
                               defaultValue="${sheetMain.mainNetSortTwo}" alt="allowBlank:false"
                               onchange="selectLimit(this.value);"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainNetSortThree"/>*</td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainNetSortThree" id="${sheetPageName}mainNetSortThree"
                               initDicId="${sheetMain.mainNetSortTwo}" defaultValue="${sheetMain.mainNetSortThree}"
                               alt="allowBlank:false" onchange="selectLimit(this.value);"/>
            </td>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainFaultResponseLevel"/>*</td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainFaultResponseLevel" id="${sheetPageName}mainFaultResponseLevel"
                               initDicId="1010304" defaultValue="${sheetMain.mainFaultResponseLevel}"
                               alt="allowBlank:false" onchange="selectLimit(this.value);"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.acceptLimit"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly"
                       id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"
                       onclick="popUpCalendar(this, this)"
                       alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>

            </td>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.completeLimit"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly"
                       id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"
                       onclick="popUpCalendar(this, this)"
                       alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainCompleteLimitT1"/>*</td>
            <td>
                <input type="text" class="text" name="${sheetPageName}mainCompleteLimitT1" readonly="readonly"
                       id="${sheetPageName}mainCompleteLimitT1"
                       value="${eoms:date2String(sheetMain.mainCompleteLimitT1)}"
                       onclick="popUpCalendar(this, this)"
                       alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'T1处理时限不能晚于处理时限',allowBlank:false"/>
            </td>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainCompleteLimitT2"/>*</td>
            <td>
                <input type="text" class="text" name="${sheetPageName}mainCompleteLimitT2" readonly="readonly"
                       id="${sheetPageName}mainCompleteLimitT2"
                       value="${eoms:date2String(sheetMain.mainCompleteLimitT2)}"
                       onclick="popUpCalendar(this, this)"
                       alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'T2处理时限不能晚于处理时限',allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainCompleteLimitT3"/>*</td>
            <td colspan="3">
                <input type="text" class="text" name="${sheetPageName}mainCompleteLimitT3" readonly="readonly"
                       id="${sheetPageName}mainCompleteLimitT3"
                       value="${eoms:date2String(sheetMain.mainCompleteLimitT3)}"
                       onclick="popUpCalendar(this, this)"
                       alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'T3处理时限不能晚于处理时限',allowBlank:false"/>
            </td>

        </tr>
        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainSendMode"/>*</td>
            <c:if test="${empty sheetMain.mainSendMode}">
                <td>
                    <input type="hidden" name="${sheetPageName}mainSendMode"
                           id="${sheetPageName}mainSendMode" value="101030502"/>
                    <eoms:id2nameDB id="101030502" beanId="ItawSystemDictTypeDao"/>
                </td>
            </c:if>
            <c:if test="${!empty sheetMain.mainSendMode}">
                <td>
                    <eoms:comboBox name="${sheetPageName}mainSendMode" id="${sheetPageName}mainSendMode"
                                   initDicId="1010305" defaultValue="${sheetMain.mainSendMode}"/>
                </td>
            </c:if>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainNetName"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}mainNetName" id="${sheetPageName}mainNetName"
                       value="${sheetMain.mainNetName}" alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainEquipmentFactory"/>*</td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainEquipmentFactory" id="${sheetPageName}mainEquipmentFactory"
                               initDicId="1010103" defaultValue="${sheetMain.mainEquipmentFactory}"
                               alt="allowBlank:false"/>
            </td>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainEquipmentModel"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}mainEquipmentModel"
                       id="${sheetPageName}mainEquipmentModel" value="${sheetMain.mainEquipmentModel}"
                       alt="allowBlank:true"/>
            </td>

        </tr>
        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainFaultGenerantTime"/>*</td>
            <td>
                <input type="text" class="text" name="${sheetPageName}mainFaultGenerantTime" readonly="readonly"
                       id="${sheetPageName}mainFaultGenerantTime"
                       value="${eoms:date2String(sheetMain.mainFaultGenerantTime)}"
                       onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt="allowBlank:false"/>
            </td>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainFaultDiscoverableMode"/>*</td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainFaultDiscoverableMode"
                               id="${sheetPageName}mainFaultDiscoverableMode" initDicId="1010301"
                               defaultValue="${sheetMain.mainFaultDiscoverableMode}" alt="allowBlank:false"/>
            </td>
        </tr>


        <tr>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainFaultGenerantPriv"/>*</td>
            <td>
                <fmt:message key="webapp.province"/>
                <input type="hidden" name="${sheetPageName}mainFaultGenerantPriv"
                       id="${sheetPageName}mainFaultGenerantPriv"
                       value="<fmt:message key='webapp.province'/>"
                       alt="allowBlank:false"/>
            </td>
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainFaultGenerantCity"/>*</td>
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
            <td class="label"><bean:message bundle="commonfault" key="commonfault.mainApplySheetId"/></td>
            <td colspan="3">
                <input type="text" class="text" name="${sheetPageName}mainApplySheetId"
                       id="${sheetPageName}mainApplySheetId" value="${sheetMain.mainApplySheetId}"/>
            </td>

        </tr>

    </c:if>

    <c:if test="${status==1}">
        <!-- 归档时显示已经选择的字典值 -->

    </c:if>

</table>


<br/>

<%-- accessories --%>


<table id="sheet" class="formTable">

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
            <!--<eoms:attachment idList="" idField="${sheetPageName}sheetAccessories" appCode="commonfault" />-->
            <eoms:attachment name="sheetMain" property="sheetAccessories"
                             scope="request" idField="${sheetPageName}sheetAccessories" appCode="commonfault"/>
        </td>
    </tr>

    <br>
</table>
<table id="sheet" class="formTable">
    <!-- 是否预处理 -->
    <tr>

        <td class="label"><bean:message bundle="commonfault" key="commonfault.mainIfAffectOperation"/>*</td>
        <td>
            <eoms:comboBox name="${sheetPageName}mainIfAffectOperation" id="${sheetPageName}mainIfAffectOperation"
                           initDicId="10301" defaultValue="${sheetMain.mainIfAffectOperation}" alt="allowBlank:false"/>
        </td>

        <td class="label"><bean:message bundle="commonfault" key="commonfault.mainPretreatment"/></td>
        <td>
            <eoms:comboBox name="${sheetPageName}mainPretreatment" id="${sheetPageName}mainPretreatment"
                           initDicId="10301" defaultValue="${sheetMain.mainPretreatment}" alt="allowBlank:false"
                           onchange="ifDoT1Deal(this.value)"/>
            <input type="hidden" name="${sheetPageName}trueActiveTemplateId" id="${sheetPageName}trueActiveTemplateId"
                   value="${taskName}"/>
        </td>
    </tr>

    <tr id="t1tr1">
        <td class="label"><bean:message bundle="commonfault" key="commonfault.mainIfMutualCommunication"/></td>
        <td>
            <eoms:comboBox name="${sheetPageName}linkIfMutualCommunication"
                           id="${sheetPageName}linkIfMutualCommunication" initDicId="10301"
                           defaultValue="${sheetMain.linkIfMutualCommunication}"/>
        </td>
        <td class="label"><bean:message bundle="commonfault" key="commonfault.mainIfSafe"/></td>
        <td>
            <eoms:comboBox name="${sheetPageName}linkIfSafe" id="${sheetPageName}linkIfSafe" initDicId="10301"
                           defaultValue="${sheetMain.linkIfSafe}"/>
        </td>
    </tr>
    <tr id="t1tr2">
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkIfGreatFault"/></td>
        <td coslpan="3">
            <eoms:comboBox name="${sheetPageName}linkIfGreatFault" id="${sheetPageName}linkIfGreatFault"
                           initDicId="10301" defaultValue="${sheetMain.linkIfGreatFault}"/>
        </td>

    </tr>

    <tr id="t1tr3">
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultFirstDealDesc"/>*</td>
        <td colspan="3">
				 <textarea name="${sheetPageName}linkFaultFirstDealDesc" id="${sheetPageName}linkFaultFirstDealDesc"
                           class="textarea max"
                           alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入500汉字'">${sheetMain.linkFaultFirstDealDesc}</textarea>
        </td>
    </tr>

    <tr id="t1tr4">
        <td class="label"><bean:message bundle="commonfault" key="commonfault.linkFaultDesc"/>*</td>
        <td colspan="3">
			   	<textarea name="linkFaultDesc" id="linkFaultDesc" class="textarea max"
                          alt="allowBlank:false,width:500,maxLength:1000,vtext:'最多输入1000汉字'">${sheetMain.linkFaultDesc}</textarea>
        </td>
    </tr>
    <!-- T1处理的附件 -->
    <tr id="t1tr5">
        <td class="label">
            <bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
        </td>
        <td colspan="3">

            <eoms:attachment name="tawSheetAccess1" property="accesss"
                             scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>

        </td>
    </tr>

    <tr id="t1tr6">
        <td class="label"><bean:message bundle="sheet" key="linkForm.accessories"/></td>
        <td colspan="3">
            <eoms:attachment name="sheetMain" property="firstNodeAccessories"
                             scope="request" idField="${sheetPageName}firstNodeAccessories" appCode="commonfault"/>
        </td>
    </tr>
</table>

<br/>

<c:if test="${status!=1}">
    <fieldset>
        <legend>
            <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
            <span id="roleName"></span>
        </legend>
        <eoms:chooser id="test1" type="role" roleId="191" flowId="51" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>
    </fieldset>

</c:if>
<script type="text/javascript">
    var v1 = eoms.form;

    function ifDoT1Deal(pretreatment) {
        if (pretreatment == '1030101') {
            chooser_test1.setRoleId('192');
            $('roleName').innerHTML = "专业维护组（T2）";
            v1.enableArea('t1tr1');
            v1.enableArea('t1tr2');
            v1.enableArea('t1tr3');
            v1.enableArea('t1tr4');
            v1.enableArea('t1tr5');
            v1.enableArea('t1tr6');
            var attch = $("UIFrame1-${sheetPageName}firstNodeAccessories");
            attch.setStyle("width:300px;height:100px");
            var attch1 = $("VIFrame1-accesss");
            attch1.setStyle("width:300px;height:100px");
        } else {
            chooser_test1.setRoleId('191');
            $('roleName').innerHTML = "故障一级处理组（T1）";
            v1.disableArea('t1tr1', true);
            v1.disableArea('t1tr2', true);
            v1.disableArea('t1tr3', true);
            v1.disableArea('t1tr4', true);
            v1.disableArea('t1tr5', true);
            v1.disableArea('t1tr6', true);
        }
    }

    ifDoT1Deal($('${sheetPageName}mainPretreatment').value);

</script>