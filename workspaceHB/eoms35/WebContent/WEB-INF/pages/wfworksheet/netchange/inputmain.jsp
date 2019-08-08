<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%

    request.setAttribute("roleId", "249");

    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    System.out.println("=====taskName==" + taskName);
    long localTimes = com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
%>

<%@ include file="/WEB-INF/pages/wfworksheet/netchange/baseinputmainhtmlnew.jsp" %>

<input type="hidden" name="${sheetPageName}processTemplateName" value="NetChangeMainProcess"/>
<input type="hidden" name="${sheetPageName}sheetTemplateName" value="NetChangeMainProcess"/>
<input type="hidden" name="${sheetPageName}operateName" value="newWorksheet"/>
<input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value=""/>
<c:if test="${status==0}">
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ProjectCreateTask"/>
</c:if>

<input type="hidden" name="${sheetPageName}beanId" value="iNetChangeMainManager"/>
<input type="hidden" name="${sheetPageName}mainClassName"
       value="com.boco.eoms.sheet.netchange.model.NetChangeMain"/> <!--main表Model对象类名-->
<input type="hidden" name="${sheetPageName}linkClassName"
       value="com.boco.eoms.sheet.netchange.model.NetChangeLink"/> <!--link表Model对象类名-->
<!-- templateName rule -->
<input type="hidden" name="templateNameRule" value="${sheetPageName}mainNetSortOne;${sheetPageName}mainNetSortTwo;
	                ${sheetPageName}mainNetSortThree"/>
<input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="${status}"/>
<!-- sheet info -->
<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="0"/>
<!-- 驳回次数 -->
<input id="${sheetPageName}mainRejectTimes" type="hidden" name="${sheetPageName}mainRejectTimes"
       value="${sheetMain.mainRejectTimes}"/>
<!-- 是否报备 -->
<input id="${sheetPageName}mainIfRecord" type="hidden" name="${sheetPageName}mainIfRecord"
       value="${sheetMain.mainIfRecord}"/>
<br/>

<table id="sheet" class="formTable">
    <c:if test="${status!=1}">
        <tr>
            <!-- 工单接单时限 -->
            <td class="label"><bean:message bundle="netchange" key="netchange.acceptLimit"/>*</td>
            <td>
                <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly"
                       id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"
                       onclick="popUpCalendar(this, this)"
                       alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于完成时限',allowBlank:false"/>
            </td>
            <!-- 工单完成时限 -->
            <td class="label"><bean:message bundle="netchange" key="netchange.completeLimit"/>*</td>
            <td>
                <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly"
                       id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"
                       onclick="popUpCalendar(this, this)"
                       alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>
            </td>
        </tr>
        <!-- <tr>
        设计完成时限
        <td class="label"><bean:message bundle="netchange" key="netchange.projectNodeCompleteLimit"/>*</td>
        <td colspan="3">
        <input type="text" class="text" name="${sheetPageName}nodeCompleteLimit" readonly="readonly"
        id="${sheetPageName}nodeCompleteLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}" onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>
        </td>
        </tr>-->
        <tr>
            <!-- 网络分类 -->
            <td class="label"><bean:message bundle="netchange" key="netchange.mainNetSortOne"/>*</td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainNetSortOne" id="${sheetPageName}mainNetSortOne"
                               sub="${sheetPageName}mainNetSortTwo" initDicId="1010104"
                               defaultValue="${sheetMain.mainNetSortOne}" alt="allowBlank:false"
                               onchange="specialtyType(this.value);selectLimit(this.value);"/>
            </td>
            <td class="label"><bean:message bundle="netchange" key="netchange.mainNetSortTwo"/></td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainNetSortTwo" id="${sheetPageName}mainNetSortTwo"
                               sub="${sheetPageName}mainNetSortThree" initDicId="${sheetMain.mainNetSortOne}"
                               defaultValue="${sheetMain.mainNetSortTwo}" onchange="selectLimit(this.value);"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="netchange" key="netchange.mainNetSortThree"/></td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainNetSortThree" id="${sheetPageName}mainNetSortThree"
                               initDicId="${sheetMain.mainNetSortTwo}" defaultValue="${sheetMain.mainNetSortThree}"
                               onchange="selectLimit(this.value);"/>
            </td>
            <!-- 牵头专业 -->
            <td class="label"><bean:message bundle="netchange" key="netchange.mainSpecialtyType"/></td>
            <td>
                <input type="text" class="text" name="${sheetPageName}mainSpecialtyType"
                       id="${sheetPageName}mainSpecialtyType" value="${sheetMain.mainSpecialtyType}"
                       alt="allowBlank:true,maxLength:40,vtext:'请填入牵头专业，最多输入40个字符"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="netchange" key="netchange.mainAssortSpeciality"/></td>
            <td colspan="3">
                <input type="button" name="usert" id="usert" value="请选择涉及专业" class="btn"/>
                <textarea class="textarea max" readonly="true" name="mainAssortSpeciality2" style="height:50px"
                          id="mainAssortSpeciality2" alt="allowBlank:true"><eoms:invert appCode="netchange"
                                                                                        sheetKey="${sheetMain.mainAssortSpeciality}"
                                                                                        beanId="CacheBean"
                                                                                        scope="page"/></textarea>
                <c:if test="${empty sheetMain.mainAssortSpeciality}">
                    <input type="hidden" name="${sheetPageName}mainAssortSpeciality" value="${mainAssortSpeciality}"/>
                </c:if>
                <c:if test="${!empty sheetMain.mainAssortSpeciality}">
                    <input type="hidden" name="${sheetPageName}mainAssortSpeciality"
                           value="${sheetMain.mainAssortSpeciality}"/>
                </c:if>

                <input type="hidden" name="saved2" id="saved2"/>
                <div id="xbox_dict_view" class="viewer-list"></div>
                <script type="text/javascript">
                    Ext.onReady(function () {

                        var dictConfig = {
                            id: 'dict',
                            btnId: 'usert',
                            treeDataUrl: '${app}/xtree.do?method=dict',
                            treeRootId: '1010104',
                            treeRootText: '专业树',
                            mode: 'clearPathById',
                            saveChkFldId: 'saved2',
                            showChkFldId: '${sheetPageName}mainAssortSpeciality2'
                        };
                        xbox_dictTree = new xbox(dictConfig);
                        xbox_dictTree.callback = function (json, list) {
                            Ext.Ajax.request({
                                url: eoms.appPath + '/mappingstorage/mappings.do?method=insertValue',
                                <c:if test ="${empty sheetMain.mainAssortSpeciality}">
                                params: "appCode=netchange&rootId=1010104&sheetKey=${mainAssortSpeciality}&dictId=" + list
                                </c:if>
                                <c:if test ="${!empty sheetMain.mainAssortSpeciality}">
                                params: "appCode=netchange&rootId=1010104&sheetKey=${sheetMain.mainAssortSpeciality}&dictId=" + list
                                </c:if>

                            });
                        }
                    });
                </script>
            </td>
        </tr>
        <tr>
            <!-- 是否需要技术方案 -->
            <td class="label"><bean:message bundle="netchange" key="netchange.mainIfRequireProject"/>*</td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainIfRequireProject" id="${sheetPageName}mainIfRequireProject"
                               initDicId="10301" defaultValue="${sheetMain.mainIfRequireProject}" alt="allowBlank:false"
                               onchange="ifDoProjectDeal(this.value)"/>
                <input type="hidden" name="${sheetPageName}trueActiveTemplateId"
                       id="${sheetPageName}trueActiveTemplateId" value="${taskName}"/>
            </td>
            <!-- 是否涉及安全 -->
            <td class="label"><bean:message bundle="netchange" key="netchange.mainIfSafe"/>*</td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainIfSafe" id="${sheetPageName}mainIfSafe" initDicId="10301"
                               defaultValue="${sheetMain.mainIfSafe}" alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <!-- 是否涉及互联互通 -->
            <td class="label"><bean:message bundle="netchange" key="netchange.mainIfMutualCommunication"/>*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}mainIfMutualCommunication"
                               id="${sheetPageName}mainIfMutualCommunication" initDicId="10301"
                               defaultValue="${sheetMain.mainIfMutualCommunication}" alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <!-- 设备厂家 -->
            <td class="label"><bean:message bundle="netchange" key="netchange.mainEquipmentFactory"/>*</td>
            <td colspan="3">
                <div id="factoryview" class="hide"></div>
                <script type="text/javascript">
                    //viewer
                    var factoryViewer = new Ext.JsonView("factoryview",
                        '<div class="viewlistitem-{nodeType}">{name}</div>',
                        {
                            emptyText: '<div>没有选择项目</div>'
                        }
                    );
                    var data = "[]";
                    areaViewer.jsonData = eoms.JSONDecode(data);
                    areaViewer.refresh();

                    //area tree
                    var factoryTreeAction = '${app}/xtree.do?method=dict';
                    factoryTree = new xbox({

                        btnId: '${sheetPageName}showFactory',

                        treeDataUrl: factoryTreeAction,
                        treeRootId: '1010103',
                        treeRootText: '设备厂家',
                        treeChkMode: '',
                        treeChkType: 'dict',
                        showChkFldId: '${sheetPageName}showFactory',
                        saveChkFldId: '${sheetPageName}mainEquipmentFactory',
                        viewer: factoryViewer
                    });
                </script>
                <textarea class="textarea max" readonly="true" name="${sheetPageName}showFactory" style="height:50px"
                          id="${sheetPageName}showFactory"
                          alt="allowBlank:false,maxLength:250,vtext:'请填入设备厂家，最多输入125个汉字'"><c:forTokens
                        items="${sheetMain.mainEquipmentFactory}" delims="," var="mainEquipmentFactory"
                        varStatus="status"><eoms:id2nameDB id="${mainEquipmentFactory}" beanId="ItawSystemDictTypeDao"/><c:choose><c:when
                        test="${status.last}"></c:when><c:otherwise>,</c:otherwise></c:choose></c:forTokens></textarea>
                <input type="hidden" name="${sheetPageName}mainEquipmentFactory"
                       id="${sheetPageName}mainEquipmentFactory" value="${sheetMain.mainEquipmentFactory}"/>
            </td>
        </tr>

        <tr>
            <!-- 期望完成时间 -->
            <td class="label"><bean:message bundle="netchange" key="netchange.mainExpectationCompleteTime"/>*</td>
            <td>
                <input type="text" class="text" name="${sheetPageName}mainExpectationCompleteTime" readonly="readonly"
                       id="${sheetPageName}mainExpectationCompleteTime"
                       value="${eoms:date2String(sheetMain.mainExpectationCompleteTime)}"
                       onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>
            </td>
            <!-- 变更来源 -->
            <td class="label"><bean:message bundle="netchange" key="netchange.mainChangeSource"/>*</td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainChangeSource" id="${sheetPageName}mainChangeSource"
                               initDicId="1010901" defaultValue="${sheetMain.mainChangeSource}" alt="allowBlank:false"
                               onchange="isHold(this.value);"/>
            </td>
        </tr>
        <!-- 相关工单号 -->
        <tr>
            <td class="label"><bean:message bundle="netchange" key="netchange.mainTouchSheetId"/></td>
            <td colspan="3">
                <c:if test="${!empty parentSheetId}">
                    <input type="text" class="text" name="${sheetPageName}mainTouchSheetId"
                           id="${sheetPageName}mainTouchSheetId" value="${parentSheetId}" alt="allowBlank:true"
                           readonly/>
                </c:if>
                <c:if test="${empty parentSheetId}">
                    <input type="text" class="text" name="${sheetPageName}mainTouchSheetId"
                           id="${sheetPageName}mainTouchSheetId" value="${sheetMain.mainTouchSheetId }"
                           alt="allowBlank:true"/>
                </c:if>
            </td>
        </tr>
        <!-- <tr>
        割接原因
        <td class="label"><bean:message bundle="netchange" key="netchange.mainCutoverReason"/>*</td>
        <td colspan="3">
        <pre><textarea name="${sheetPageName}mainCutoverReason" id="${sheetPageName}mainCutoverReason" class="textarea max" alt="allowBlank:false,maxLength:2000,vtext:'请填入割接原因，最多输入2000个字符'">${sheetMain.mainCutoverReason}</textarea></pre>
        </td>
        </tr> -->
        <tr>
            <!-- 综合变更需求说明 -->
            <td class="label"><bean:message bundle="netchange" key="netchange.mainChangeExplain"/>*</td>
            <td colspan="3">
                <textarea name="${sheetPageName}mainChangeExplain" id="${sheetPageName}mainChangeExplain"
                          class="textarea max"
                          alt="allowBlank:false,maxLength:255,vtext:'请填入综合变更需求说明，最多输入125个字符'">${sheetMain.mainChangeExplain}</textarea>
            </td>
        </tr>

    </c:if>

    <c:if test="${status==1}">

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
            <!--<eoms:attachment idList="" idField="${sheetPageName}sheetAccessories" appCode="netchange" />-->
            <eoms:attachment name="sheetMain" property="sheetAccessories"
                             scope="request" idField="${sheetPageName}sheetAccessories" appCode="netchange"/>
        </td>
    </tr>


</table>
<br>

<!-- 方案制定 -->
<table id="sheetProjectCreate" class="formTable" style="display:none">
    <tr>
        <!-- 完成时限 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkExecuteEndDate"/>*</td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}linkExecuteEndDate" readonly="readonly"
                   id="${sheetPageName}linkExecuteEndDate" value="${eoms:date2String(sheetMain.linkExecuteEndDate)}"
                   onclick="popUpCalendar(this, this)"/>
        </td>

        <!-- 技术方案关键字 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkProjectKey"/>*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}linkProjectKey" id="${sheetPageName}linkProjectKey"
                   value="${sheetMain.linkProjectKey}"
                   alt="allowBlank:false,maxLength:40,vtext:'请填入技术方案关键字，最多输入40个字符'"/>
        </td>
    </tr>
    <tr>
        <!-- 总技术方案说明 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkProjectExplain"/></td>
        <td colspan="3">
            <textarea name="${sheetPageName}linkProjectExplain" id="${sheetPageName}linkProjectExplain"
                      class="textarea max"
                      alt="allowBlank:true,maxLength:255,vtext:'请填入总技术方案说明，最多输入125个字符'">${sheetMain.linkProjectExplain}</textarea>
        </td>
    </tr>
    <tr>
        <!-- 风险评估 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkRiskEvaluate"/>*</td>
        <td colspan="3">
            <textarea name="${sheetPageName}linkRiskEvaluate" id="${sheetPageName}linkRiskEvaluate" class="textarea max"
                      alt="allowBlank:false,maxLength:255,vtext:'请填入风险评估，最多输入125个字符'">${sheetMain.linkRiskEvaluate}</textarea>
        </td>
    </tr>
    <tr>
        <!-- 影响业务分析 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkOperationConstrue"/>*</td>
        <td colspan="3">
            <textarea name="${sheetPageName}linkOperationConstrue" id="${sheetPageName}linkOperationConstrue"
                      class="textarea max"
                      alt="allowBlank:false,maxLength:255,vtext:'请填入影响业务分析，最多输入125个字符'">${sheetMain.linkOperationConstrue}</textarea>
        </td>
    </tr>

    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
        </td>
        <td colspan="3">
            <eoms:attachment name="tawSheetAccess1" property="accesss"
                             scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>
        </td>
    </tr>
    <tr>
        <!-- 总技术方案附件 -->
        <td class="label"><bean:message bundle="netchange" key="netchange.linkProjectAcc"/></td>
        <td colspan="3">
            <eoms:attachment name="sheetMain" property="firstNodeAccessories"
                             scope="request" idField="${sheetPageName}firstNodeAccessories" appCode="netchange"/>
        </td>
    </tr>
</table>

<br/>

<c:if test="${status!=1}">
    <div id='test1'>
        <fieldset id="link1">
            <legend>
                <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
                <bean:message bundle="netchange" key="role.changeAdmin"/>
            </legend>


            <eoms:chooser id="test11" type="role" roleId="250" flowId="045" config="{returnJSON:true,showLeader:true}"
                          category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                          data="${sendUserAndRoles}"/>
        </fieldset>
        <fieldset id="link2">
            <legend>
                <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
                <bean:message bundle="netchange" key="role.changeAdmin"/>
            </legend>
            <eoms:chooser id="test22" type="role" roleId="250" flowId="045" config="{returnJSON:true,showLeader:true}"
                          category="[{id:'${sheetPageName}dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'${sheetPageName}subAuditPerformer',childType:'user,subrole',limit:'none',text:'会审'},{id:'${sheetPageName}copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"/>
        </fieldset>
    </div>
</c:if>
<script type="text/javascript">
    if ($('${sheetPageName}mainNetSortOne').value == null || $('${sheetPageName}mainNetSortOne').value == '101010410') {
        //$('${sheetPageName}mainSpecialtyType').disabled=false;
        eoms.form.enable("${sheetPageName}mainSpecialtyType");
    } else {
        $('${sheetPageName}mainSpecialtyType').value = '';
        //$('${sheetPageName}mainSpecialtyType').disabled=true;
        eoms.form.disable("${sheetPageName}mainSpecialtyType");
    }

    function specialtyType(input) {
        if (input == '101010410') {
            //$('${sheetPageName}mainSpecialtyType').disabled=false;
            eoms.form.enable("${sheetPageName}mainSpecialtyType");
        } else {
            $('${sheetPageName}mainSpecialtyType').value = '';
            //$('${sheetPageName}mainSpecialtyType').disabled=true;
            eoms.form.disable("${sheetPageName}mainSpecialtyType");
        }
    }

    var v1 = eoms.form;
    getparentsheetname();

    function getparentsheetname() {
        if ('${sheetMain.mainChangeSource}' != null && '${sheetMain.mainChangeSource}' != '') {
            document.all.${sheetPageName}mainChangeSource.defaultValue = '${sheetMain.mainChangeSource}';
        } else if ('${sheetMain.parentSheetName}' != null && '${sheetMain.parentSheetName}' != '') {
            if ('${parentProcessName}' == 'CommonFaultMainFlowProcess') {
                document.all.${sheetPageName}mainChangeSource.value = '101090101';
            } else if ('${parentProcessName}' == 'ComplaintProcess') {
                document.all.${sheetPageName}mainChangeSource.value = '101090102';
            } else if ('${parentProcessName}' == 'SecurityDealProcess') {
                document.all.${sheetPageName}mainChangeSource.value = '101090103';
            } else if ('${parentProcessName}' == 'BusinessPilotProcess') {
                document.all.${sheetPageName}mainChangeSource.value = '101090104';
            } else if ('${parentProcessName}' == 'BusinessOperationProcess') {
                document.all.${sheetPageName}mainChangeSource.value = '101090105';
            } else if ('${parentProcessName}' == 'GreatEventProcess') {
                document.all.${sheetPageName}mainChangeSource.value = '101090106';
            } else if ('${parentProcessName}' == 'NetChangeMainProcess') {
                document.all.${sheetPageName}mainChangeSource.value = '101090107';
            } else {
                document.all.${sheetPageName}mainChangeSource.value = '101090109';
            }
        }
    }


    isHold(document.all.${sheetPageName}mainChangeSource.value);

    function isHold(input) {
        if (input == "101090101" || input == "101090102") {
            if ($('${sheetPageName}mainIfRequireProject').value == "1030101") {
                chooser_test22.disable();
            } else {
                chooser_test11.disable();
            }
            if (document.getElementById("submitone")) {
                document.getElementById("submitone").value = "报备";
                //v1.disableArea('test1',true);
                //	chooser_test22.category[1].hide();
                eoms.$('test1').hide();
                document.all.${sheetPageName}phaseId.value = 'Over';
                $('${sheetPageName}status').value = '1';
                $('${sheetPageName}mainIfRecord').value = '1';
                $('${sheetPageName}operateType').value = '18';
                $('${sheetPageName}hasNextTaskFlag').value = 'true';
            }
        } else {
            if ($('${sheetPageName}mainIfRequireProject').value == "1030101") {
                chooser_test22.enable();
            } else {
                chooser_test11.enable();
            }
            //v1.enableArea('test1');
            eoms.$('test1').show();
            if (document.getElementById("submitone")) {
                document.getElementById("submitone").value = "派发";
                document.all.${sheetPageName}phaseId.value = 'ProjectCreateTask';
                $('${sheetPageName}status').value = '0';
                $('${sheetPageName}mainIfRecord').value = '0';
                if ('${taskName}' == 'DraftTask') {
                    $('${sheetPageName}operateType').value = "3";
                } else if ('${taskName}' == 'RejectTask') {
                    $('${sheetPageName}operateType').value = "54";
                } else {
                    $('${sheetPageName}operateType').value = "0";
                }
            }
        }
    }

    function selectLimit(obj) {
        if ($("${sheetPageName}mainNetSortOne").value == null || $("${sheetPageName}mainNetSortOne").value == "") {
            // alert("请选择故障专业！");
            return false;
        }

        var temp1 = $("${sheetPageName}mainNetSortOne").value;
        var temp2 = $("${sheetPageName}mainNetSortTwo").value;
        var temp3 = $("${sheetPageName}mainNetSortThree").value;
        var temp4 = $("${sheetPageName}mainEquipmentFactory").value;

        Ext.Ajax.request({
            method: "get",
            url: "netchange.do?method=newShowLimit&specialty1=" + temp1 + "&specialty2=" + temp2 + "&specialty3=" + temp3 + "&specialty4=" + temp4 + "&flowName=NetChangeMainProcess",
            success: function (x) {
                var o = eoms.JSONDecode(x.responseText);
                if ((o.acceptLimit == null || o.acceptLimit == "") && (o.replyLimit == null || o.replyLimit == "")) {
                    //$("${sheetPageName}sheetAcceptLimit").value = "";
                    //$('${sheetPageName}sheetCompleteLimit').value = "";
                } else {
                    var times =<%=localTimes%>;
                    var dt1 = new Date(times).add(Date.MINUTE, parseInt(o.acceptLimit, 10));
                    var dt2 = dt1.add(Date.MINUTE, parseInt(o.replyLimit, 10));
                    $("${sheetPageName}sheetAcceptLimit").value = dt1.format('Y-m-d H:i:s');
                    $('${sheetPageName}sheetCompleteLimit').value = dt2.format('Y-m-d H:i:s');
                }
            }
        });
    }

    function ifDoProjectDeal(pretreatment) {
        if (pretreatment == '1030101') {
            v1.enableArea('sheetProjectCreate');
            var attch = $("UIFrame1-${sheetPageName}firstNodeAccessories");
            attch.setStyle("width:300px;height:100px");
            var attch1 = $("VIFrame1-accesss");
            attch1.setStyle("width:300px;height:100px");
            chooser_test22.enable();
            chooser_test11.disable();
            //v1.disableArea('link1',true);
            //v1.enableArea('link2');
            eoms.$('link1').hide();
            eoms.$('link2').show();
        } else {
            v1.disableArea('sheetProjectCreate', true);
            chooser_test11.enable();
            chooser_test22.disable();
            //v1.disableArea('link2',true);
            //v1.enableArea('link1');
            eoms.$('link2').hide();
            eoms.$('link1').show();
        }
    }

    ifDoProjectDeal($('${sheetPageName}mainIfRequireProject').value);
    factoryTree.callback = function (json, list) {
        selectLimit('');
    }
</script>