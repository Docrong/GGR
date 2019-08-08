<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
    request.setAttribute("roleId", "244");
    long localTimes = com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    System.out.println("=====taskName==" + taskName);

%>
<%@ include file="/WEB-INF/pages/wfworksheet/netdata/baseinputmainhtmlnew.jsp" %>
<input type="hidden" name="${sheetPageName}processTemplateName" value="NetDataMainProcess"/>
<input type="hidden" name="${sheetPageName}sheetTemplateName" value="NetDataMainProcess"/>
<input type="hidden" name="${sheetPageName}operateName" value="newWorkSheet"/>

<!-- 新增 -->
<c:if test="${status==0}">
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ProjectCreateTask"/>
</c:if>
<!-- 归档 -->
<c:if test="${status==1}">
    <input type="hidden" name="${sheetPageName}phaseId" value="Over"/>
</c:if>
<input type="hidden" name="${sheetPageName}beanId" value="iNetDataMainManager"/>
<input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.netdata.model.NetDataMain"/>
<input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.netdata.model.NetDataLink"/>
<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="0"/>
<input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="${status}"/>
<input type="hidden" name="${sheetPageName}mainRejectTimes" value="0"/>
<input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="false"/>
<!-- 驳回次数 -->
<input type="hidden" name="${sheetPageName}mainRejectTimes" value="${sheetMain.mainRejectTimes}"/>
<!-- 是否报备 -->
<input type="hidden" name="${sheetPageName}mainIfRecord" id="${sheetPageName}mainIfRecord"
       value="${sheetMain.mainIfrecord}"/>
<!-- sheet info -->
<c:if test="${status!=1}">
    <BR/>
    <c:if test="${empty operateType||operateType!='4' }">
        <table id="sheet" class="formTable">
            <tr>
                <td class="label">
                    <bean:message bundle="sheet" key="linkForm.acceptLimit"/>*
                </td>
                <td class="content">
                    <input class="text" onclick="popUpCalendar(this, this)" type="text"
                           name="${sheetPageName}sheetAcceptLimit" id="${sheetPageName}sheetAcceptLimit"
                           readonly="readonly" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"
                           alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于完成时限',allowBlank:false"/>

                </td>
                <td class="label"><bean:message bundle="netdata" key="netdata.linkCompleteLimitTime"/>*</td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly"
                           id="${sheetPageName}sheetCompleteLimit"
                           value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"
                           onclick="popUpCalendar(this, this)"
                           alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>

                </td>
            </tr>
            <tr>      <!-- 网络一级分类 -->
                <td class="label"><bean:message bundle="netdata" key="netdata.mainNetTypeOne"/>*</td>
                <td>
                    <eoms:comboBox name="${sheetPageName}mainNetTypeOne" id="${sheetPageName}mainNetTypeOne"
                                   sub="${sheetPageName}mainNetTypeTwo" initDicId="1010104"
                                   defaultValue="${sheetMain.mainNetTypeOne}" alt="allowBlank:false"
                                   onchange="selectLimit(this.value);"/>
                </td>
                <!-- 网络二级分类 -->
                <td class="label"><bean:message bundle="netdata" key="netdata.mainNetTypeTwo"/></td>
                <td>
                    <eoms:comboBox name="${sheetPageName}mainNetTypeTwo" id="${sheetPageName}mainNetTypeTwo"
                                   sub="${sheetPageName}mainNetTypeThree" initDicId="${sheetMain.mainNetTypeOne}"
                                   defaultValue="${sheetMain.mainNetTypeTwo}" onchange="selectLimit(this.value);"/>
                </td>
            </tr>
            <tr>
                <!-- 网络三级分类 -->
                <td class="label"><bean:message bundle="netdata" key="netdata.mainNetTypeThree"/></td>
                <td colspan="3">
                    <eoms:comboBox name="${sheetPageName}mainNetTypeThree" id="${sheetPageName}mainNetTypeThree"
                                   initDicId="${sheetMain.mainNetTypeTwo}" defaultValue="${sheetMain.mainNetTypeThree}"
                                   onchange="selectLimit(this.value);"/>
                </td>
            </tr>
            <tr>
                <td class="label"><bean:message bundle="netdata" key="netdata.mainAssortSpeciality"/></td>
                <td colspan="3">
                    <input type="button" name="usert" id="usert" value="请选择配合专业" class="btn"/>
                    <textarea class="textarea max" readonly="true" name="mainAssortSpeciality2" style="height:50px"
                              id="mainAssortSpeciality2" alt="allowBlank:true"><eoms:invert appCode="netdata"
                                                                                            sheetKey="${sheetMain.mainAssortSpeciality}"
                                                                                            beanId="CacheBean"
                                                                                            scope="page"/></textarea>
                    <c:if test="${empty sheetMain.mainAssortSpeciality}">
                        <input type="hidden" name="${sheetPageName}mainAssortSpeciality"
                               value="${mainAssortSpeciality}"/>
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
                                    params: "appCode=netdata&rootId=1010104&sheetKey=${mainAssortSpeciality}&dictId=" + list
                                    </c:if>
                                    <c:if test ="${!empty sheetMain.mainAssortSpeciality}">
                                    params: "appCode=netdata&rootId=1010104&sheetKey=${sheetMain.mainAssortSpeciality}&dictId=" + list
                                    </c:if>

                                });
                            }
                        });
                    </script>
                </td>
            </tr>
            <tr>        <!-- 是否涉及安全 -->
                <td class="label"><bean:message bundle="netdata" key="netdata.mainIsSecurity"/>*</td>
                <td>
                    <eoms:comboBox name="${sheetPageName}mainIsSecurity" id="${sheetPageName}mainIsSecurity"
                                   initDicId="10301" alt="allowBlank:false" styleClass="select-class"
                                   defaultValue="${sheetMain.mainIsSecurity}"/>
                </td>

                <!-- 是否涉及互联互通 -->
                <td class="label"><bean:message bundle="netdata" key="netdata.mainIsConnect"/>*</td>
                <td>
                    <eoms:comboBox name="${sheetPageName}mainIsConnect" id="${sheetPageName}mainIsConnect"
                                   initDicId="10301" alt="allowBlank:false" styleClass="select-class"
                                   defaultValue="${sheetMain.mainIsConnect}"/>
                </td>
            </tr>
            <tr>
                <!-- 设备厂家 -->
                <td class="label"><bean:message bundle="netdata" key="netdata.mainFactory"/>*</td>
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
                            saveChkFldId: '${sheetPageName}mainFactory',
                            viewer: factoryViewer
                        });
                    </script>
                    <textarea class="textarea max" readonly="true" name="${sheetPageName}showFactory"
                              style="height:50px" id="${sheetPageName}showFactory"
                              alt="allowBlank:false,maxLength:250,vtext:'请填入设备厂家，最多输入125个汉字'"><c:forTokens
                            items="${sheetMain.mainFactory}" delims="," var="mainFactory"
                            varStatus="status"><eoms:id2nameDB id="${mainFactory}"
                                                               beanId="ItawSystemDictTypeDao"/><c:choose><c:when
                            test="${status.last}"></c:when><c:otherwise>,</c:otherwise></c:choose></c:forTokens></textarea>
                    <input type="hidden" name="${sheetPageName}mainFactory" id="${sheetPageName}mainFactory"
                           value="${sheetMain.mainFactory}"/>
                </td>
            </tr>

            <!-- 变更来源 -->
            <td class="label"><bean:message bundle="netdata" key="netdata.mainChangeSource"/>*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}mainChangeSource" id="${sheetPageName}mainChangeSource"
                               initDicId="1010901" defaultValue="${sheetMain.mainChangeSource}" alt="allowBlank:false"
                               onchange="isHold(this.value);"/>
            </td>
            </tr>
            <tr>
                <!--  相关工单号 -->
                <td class="label"><bean:message bundle="netdata" key="netdata.mainParentProcessName"/></td>
                <td>
                    <c:if test="${!empty parentSheetId}">
                    <input type="text" class="text" name="${sheetPageName}mainParentSheetId"
                           id="${sheetPageName}mainParentSheetId"
                           alt="allowBlank:true,maxLength:32,vtext:'请填入相关工单号信息，最多输入32字符'" value="${parentSheetId}"
                           readonly/>
                    </c:if>
                    <c:if test="${empty parentSheetId}">
                    <input type="text" class="text" name="${sheetPageName}mainParentSheetId"
                           id="${sheetPageName}mainParentSheetId"
                           alt="allowBlank:true,maxLength:32,vtext:'请填入相关工单号信息，最多输入32字符'"
                           value="${sheetMain.mainParentSheetId}"/>
                    </c:if>

                    <!-- 是否需要技术方案 -->
                <td class="label"><bean:message bundle="netdata" key="netdata.mainIsNeedDesign"/>*</td>
                <td>
                    <eoms:comboBox name="${sheetPageName}mainIsNeedDesign" id="${sheetPageName}mainIsNeedDesign"
                                   initDicId="10301" alt="allowBlank:false" styleClass="select-class"
                                   defaultValue="${sheetMain.mainIsNeedDesign}" onchange="ifDoProjectDeal(this.value)"/>
                    <input type="hidden" name="${sheetPageName}trueActiveTemplateId"
                           id="${sheetPageName}trueActiveTemplateId" value="${taskName}"/>
                </td>
            </tr>
            <tr>
                <!-- 变更网元 -->
                <td class="label"><bean:message bundle="netdata" key="netdata.mainCellInfo"/>*</td>
                <td colspan="3">
                    <input type="text" class="text" name="${sheetPageName}mainCellInfo"
                           id="${sheetPageName}mainCellInfo"
                           alt="allowBlank:false,maxLength:32,vtext:'请填入变更网元，最多输入32字符'"
                           value="${sheetMain.mainCellInfo}"/>
                </td>
            </tr>
            <tr>
                <!-- 网络数据变更需求说明 -->
                <td class="label"><bean:message bundle="netdata" key="netdata.mainNetDataCommont"/>*</td>
                <td colspan="3">
                    <textarea class="textarea max" name="${sheetPageName}mainNetDataCommont"
                              id="${sheetPageName}mainNetDataCommont"
                              alt="allowBlank:false,maxLength:4000,vtext:'请填入网络数据变更需求说明，最多输入4000字符'">${sheetMain.mainNetDataCommont}</textarea>
                </td>
            </tr>
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
                    <eoms:attachment name="sheetMain" property="sheetAccessories"
                                     scope="request" idField="${sheetPageName}sheetAccessories" appCode="netdata"
                                     alt="allowBlank:true"/>
                </td>
            </tr>
        </table>
        <br>
        <table id="sheetProjectCreate" class="formTable" style="display:none">
            <!-- 方案制定  -->
            <tr>
                <!-- 完成时限 -->
                <td class="label"><bean:message bundle="netdata" key="netdata.linkCompleteLimitTime"/>*</td>
                <td>
                    <input type="text" class="text" name="${sheetPageName}linkCompleteLimitTime" readonly="readonly"
                           id="${sheetPageName}linkCompleteLimitTime"
                           value="${eoms:date2String(sheetMain.linkCompleteLimitTime)}"
                           onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>
                </td>
                <!-- 技术方案关键字 -->
                <td class="label"><bean:message bundle="netdata" key="netdata.linkDesignKey"/>*</td>
                <td><input type="text" class="text" name="${sheetPageName}linkDesignKey"
                           id="${sheetPageName}linkDesignKey" value="${sheetMain.linkDesignKey}"
                           alt="allowBlank:false,maxLength:32,vtext:'请填入技术方案关键字，最多输入32字符'"/></td>
            </tr>
            <tr>
                <!-- 方案资源号 -->
                <td class="label"><bean:message bundle="netdata" key="netdata.mainDesignId"/></td>
                <td colspan="3">
                    <input type="text" class="text" name="${sheetPageName}mainDesignId"
                           value="${sheetMain.mainDesignId}" alt="allowBlank:true,maxLength:255,vtext:'最多输入255字符'"/>
                </td>
            </tr>
            <tr>
                <!-- 技术方案说明 -->
                <td class="label"><bean:message bundle="netdata" key="netdata.linkDesignComment"/></td>
                <td colspan="3">
                    <textarea class="textarea max" name="${sheetPageName}linkDesignComment"
                              id="${sheetPageName}linkDesignComment"
                              alt="allowBlank:true,maxLength:4000,vtext:'请填入技术方案说明，最多输入4000字符'">${sheetMain.linkDesignComment}</textarea>
                </td>
            </tr>
            <tr>
                <!-- 变更涉及省份 -->
                <td class="label">
                    <!--
		            <bean:message bundle="netdata" key="netdata.linkInvolvedProvince"/>
		            <bean:message bundle="netdata" key="netdata.linkInvolvedCity"/>
		            -->
                    涉及省份及地市*
                </td>
                <td colspan="3">
                    <input type="button" id="areabtn" value="选择涉及省份和地市" class="btn"/>


                    <br/><br/>
                    涉及省份:<textarea class="textarea max" readonly="true" name="linkInvolvedProvince" style="height:50px"
                                   id="${sheetPageName}linkInvolvedProvince"
                                   alt="allowBlank:false,maxLength:4000,vtext:'请填入涉及省份，最多输入4000字符'">${sheetMain.linkInvolvedProvince }</textarea><br/>
                    涉及地市:<textarea class="textarea max" readonly="true" name="linkInvolvedCity" style="height:50px"
                                   id="${sheetPageName}linkInvolvedCity"
                                   alt="allowBlank:true,maxLength:4000,vtext:'请填入涉及地市，最多输入4000字符'">${sheetMain.linkInvolvedCity}</textarea>

                    <script type="text/javascript">
                        Ext.onReady(function () {
                            function callback(jsonData, str) {
                                var baseFlag = 2, shengNameArr = [], shengIdArr = [], shiNameArr = [], shiIdArr = [];
                                eoms.log(jsonData);
                                Ext.each(jsonData, function (data) {
                                    switch (data.id.length) {
                                        case 1 :
                                            shengNameArr.push(data.name);
                                            shengIdArr.push(data.id);
                                            break;
                                        case baseFlag :
                                            shengNameArr.push(data.name);
                                            shengIdArr.push(data.id);
                                            break;
                                        case baseFlag + 1 :
                                            shiNameArr.push(data.name);
                                            shiIdArr.push(data.id);
                                            break;
                                        case baseFlag + 2 :
                                            shiNameArr.push(data.name);
                                            shiIdArr.push(data.id);
                                            break;
                                    }
                                });
                                $('${sheetPageName}linkInvolvedProvince').value = shengNameArr.join(",");
                                $('${sheetPageName}linkInvolvedCity').value = shiNameArr.join(",");
                                //$('province-name').value = shengNameArr.join(",");
                                //$('city-name').value = shiNameArr.join(",");
                            }

                            var treeAction = '${app}/area/tawSystemAreas.do?method=getNodes';
                            var cfg = {
                                btnId: 'areabtn',
                                baseAttrs: {checked: false},
                                treeDataUrl: treeAction,
                                treeRootId: '-1',
                                treeRootText: '地域树图',
                                treeChkMode: '',
                                treeChkType: 'area',
                                callback: callback
                            }
                            var areaTree = new xbox(cfg);
                            areaTree.onBeforeCheck = function (node, checked) {
                                if (checked && node.parentNode) {
                                    node.parentNode.getUI().toggleCheck(true);
                                }
                                return true;
                            }
                        });
                    </script>
                </td>
            </tr>
            <tr>
                <!-- 风险评估 -->
                <td class="label"><bean:message bundle="netdata" key="netdata.linkRiskEstimate"/>*</td>
                <td colspan="3">
                    <textarea class="textarea max" name="${sheetPageName}linkRiskEstimate"
                              id="${sheetPageName}linkRiskEstimate"
                              alt="allowBlank:false,maxLength:4000,vtext:'请填入风险评估，最多输入4000字符'">${sheetMain.linkRiskEstimate}</textarea>
                </td>
            </tr>
            <tr>
                <!-- 影响业务分析 -->
                <td class="label"><bean:message bundle="netdata" key="netdata.linkEffectAnalyse"/>*</td>
                <td colspan="3">
                    <textarea class="textarea max" name="${sheetPageName}linkEffectAnalyse"
                              id="${sheetPageName}linkEffectAnalyse"
                              alt="allowBlank:false,maxLength:4000,vtext:'请填入影响业务分析，最多输入4000字符'">${sheetMain.linkEffectAnalyse}</textarea>
                </td>
            </tr>
            <tr>
                <td class="label">
                    <bean:message bundle="sheet" key="tawSheetAccessForm.access"/>
                </td>
                <td colspan="3">
                    <div class="x-form-item">
                        <div class="x-form-element">
                            <eoms:attachment name="tawSheetAccess1" property="accesss"
                                             scope="request" idField="accesss" appCode="toolaccess" viewFlag="Y"/>
                        </div>
                    </div>
                </td>
            </tr>
            <tr>
                <!-- 技术方案附件 -->
                <td class="label"><bean:message bundle="netdata" key="netdata.linkDesignAttachment"/></td>
                <td colspan="3">
                    <eoms:attachment name="sheetMain" property="firstNodeAccessories"
                                     scope="request" idField="${sheetPageName}firstNodeAccessories" appCode="netdata"/>
                </td>
            </tr>
        </table>
    </c:if>
    <br/>
    <div id="test1">
        <fieldset id="link1">
            <legend>
                <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
                <bean:message bundle="netdata" key="role.ProjectCreate"/>
            </legend>
            <div class="x-form-item">
                <eoms:chooser id="test11" type="role" roleId="245" flowId="78"
                              config="{returnJSON:true,showLeader:true,mainPanelTitle:'可选择的子角色'}"
                              category="[{id:'${sheetPageName}dealPerformer',text:'派发',allowBlank:true,vtext:'请选择派发人',limit:'none'},{id:'${sheetPageName}copyPerformer',text:'抄送',childType:'user,subrole,dept',limit:'none'}]"
                              data="${sendUserAndRoles}"/>
            </div>
        </fieldset>
        <fieldset id="link3">
            <legend>
                排程角色:<bean:message bundle="netdata" key="role.PlanTask"/>
            </legend>
            <div class="x-form-item">
                <eoms:chooser id="test33" type="role" roleId="247" flowId="78"
                              config="{returnJSON:true,showLeader:true,mainPanelTitle:'可选择的子角色'}"
                              category="[{id:'${sheetPageName}extendPerformer',childType:'user,subrole',limit:'none',text:'排程'}]"
                              data="${sendUserAndRoles}"/>
            </div>
        </fieldset>
    </div>
</c:if>
<br>
<script type="text/javascript">
    var v1 = eoms.form;
    isHold($('${sheetPageName}mainChangeSource').value);

    function isHold(input) {
        if (input == "101090101" || input == "101090102") {
            if (document.getElementById("submitone")) {
                document.getElementById("submitone").value = "报备";
                eoms.$('test1').hide();
                $('${sheetPageName}phaseId').value = 'Over';
                $('${sheetPageName}status').value = '1';
                $('${sheetPageName}mainIfRecord').value = '1';
                $('${sheetPageName}operateType').value = '18';
                $('${sheetPageName}hasNextTaskFlag').value = 'true';
            }
        } else {
            if (document.getElementById("submitone")) {
                document.getElementById("submitone").value = "派发";
                eoms.$('test1').show();
                $('${sheetPageName}phaseId').value = 'ProjectCreateTask';
                $('${sheetPageName}status').value = '0';
                $('${sheetPageName}mainIfRecord').value = '0';
                if ('${taskName}' == 'DraftTask') {
                    $('${sheetPageName}operateType').value = "3";
                } else if ('${taskName}' == 'RejectTask') {
                    $('${sheetPageName}operateType').value = "54";
                } else {
                    $('${sheetPageName}operateType').value = '0';
                }
            }
        }
    }

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

    function selectLimit(obj) {
        if ($("${sheetPageName}mainNetTypeOne").value == null || $("${sheetPageName}mainNetTypeOne").value == "") {
            // alert("请选择故障专业！");
            return false;
        }

        var temp1 = $("${sheetPageName}mainNetTypeOne").value;
        var temp2 = $("${sheetPageName}mainNetTypeTwo").value;
        var temp3 = $("${sheetPageName}mainNetTypeThree").value;
        var temp4 = $("${sheetPageName}mainFactory").value;

        Ext.Ajax.request({
            method: "get",
            url: "netdata.do?method=newShowLimit&specialty1=" + temp1 + "&specialty2=" + temp2 + "&specialty3=" + temp3 + "&specialty4=" + temp4 + "&flowName=NetDataMainProcess",
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
            chooser_test11.reset();
            chooser_test11.category[0].attr('limit', 1);
            chooser_test33.reset();
            chooser_test33.enable();
            eoms.$('link3').show();

        } else {
            v1.disableArea('sheetProjectCreate', true);
            chooser_test11.reset();
            if ($("${sheetPageName}operateType").value == '4') {
                chooser_test11.category[0].attr('limit', 1);
            } else if ($("${sheetPageName}operateType").value == null || $("${sheetPageName}operateType").value == ''
                || $("${sheetPageName}operateType").value == '0' || $("${sheetPageName}operateType").value == '3'
                || $("${sheetPageName}operateType").value == '54') {
                chooser_test11.category[0].attr('limit', -1);
            }

            chooser_test33.disable();
            eoms.$('link3').hide();
        }
    }

    ifDoProjectDeal($('${sheetPageName}mainIsNeedDesign').value);
    factoryTree.callback = function (json, list) {
        selectLimit('');
    }
</script>
