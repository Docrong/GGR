<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%
    request.setAttribute("roleId", "253");
    long localTimes = com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
%>

<%@ include file="/WEB-INF/pages/wfworksheet/softchange/baseinputmainhtmlnew.jsp" %>
<input type="hidden" name="${sheetPageName}processTemplateName" value="SoftChangeMainProcess"/>
<input type="hidden" name="${sheetPageName}sheetTemplateName" value="SoftChangeMainProcess"/>
<input type="hidden" name="${sheetPageName}operateName" value="newWorkSheet"/>
<c:if test="${status==0}">
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ProjectCreateTask"/>
</c:if>
<c:if test="${status==1}">
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over"/>
</c:if>
<input type="hidden" name="${sheetPageName}beanId" value="iSoftChangeMainManager"/>
<input type="hidden" name="${sheetPageName}mainClassName" value="com.boco.eoms.sheet.softchange.model.SoftChangeMain"/>
<input type="hidden" name="${sheetPageName}linkClassName" value="com.boco.eoms.sheet.softchange.model.SoftChangeLink"/>
<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="0"/>
<input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="false"/>
<!-- sheet info -->


<br/>
<c:if test="${status!=1}">
    <table id="sheet" class="formTable">
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainNetTypeOne"/>*</td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainNetTypeOne" id="${sheetPageName}mainNetTypeOne"
                               sub="${sheetPageName}mainNetTypeTwo" initDicId="1010104"
                               defaultValue="${sheetMain.mainNetTypeOne}" alt="allowBlank:false"
                               onchange="selectLimit(this.value);"/>
            </td>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainNetTypeTwo"/></td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainNetTypeTwo" id="${sheetPageName}mainNetTypeTwo"
                               sub="${sheetPageName}mainNetTypeThree" initDicId="${sheetMain.mainNetTypeOne}"
                               defaultValue="${sheetMain.mainNetTypeTwo}" onchange="selectLimit(this.value);"/>
            </td>
        </tr>

        <tr>

            <td class="label"><bean:message bundle="softchange" key="softchange.mainNetTypeThree"/></td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainNetTypeThree" id="${sheetPageName}mainNetTypeThree"
                               initDicId="${sheetMain.mainNetTypeTwo}" defaultValue="${sheetMain.mainNetTypeThree}"
                               onchange="selectLimit(this.value);"/>
            </td>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainIsSecurity"/>*</td>
            <c:choose>
            <c:when test="${empty sheetMain.mainIsSecurity}">
            <td>
                <eoms:comboBox name="${sheetPageName}mainIsSecurity" id="${sheetPageName}mainIsSecurity"
                               initDicId="10301" alt="allowBlank:false" defaultValue="1030102"
                               styleClass="select-class"/>
            </td>
            </c:when>
            <c:otherwise>
            <td>
                <eoms:comboBox name="${sheetPageName}mainIsSecurity" id="${sheetPageName}mainIsSecurity"
                               initDicId="10301" alt="allowBlank:false" defaultValue="${sheetMain.mainIsSecurity}"
                               styleClass="select-class"/>
            </td>
            </c:otherwise>

            </c:choose>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainAssortSpeciality"/></td>
            <td colspan="3">
                <input type="button" name="usert" id="usert" value="请选择配合专业" class="btn"/>
                <textarea class="textarea max" readonly="true" name="mainAssortSpeciality2" style="height:50px"
                          id="mainAssortSpeciality2" alt="allowBlank:true"><eoms:invert appCode="softchange"
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
                                params: "appCode=softchange&rootId=1010104&sheetKey=${mainAssortSpeciality}&dictId=" + list
                                </c:if>
                                <c:if test ="${!empty sheetMain.mainAssortSpeciality}">
                                params: "appCode=softchange&rootId=1010104&sheetKey=${sheetMain.mainAssortSpeciality}&dictId=" + list
                                </c:if>

                            });
                        }
                    });
                </script>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainIsConnect"/>*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}mainIsConnect" id="${sheetPageName}mainIsConnect"
                               initDicId="10301" alt="allowBlank:false" styleClass="select-class"
                               defaultValue="${sheetMain.mainIsConnect}"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainFactory"/>*</td>
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
                <textarea class="textarea max" readonly="true" name="${sheetPageName}showFactory" style="height:50px"
                          id="${sheetPageName}showFactory"
                          alt="allowBlank:false,maxLength:250,vtext:'请填入设备厂家，最多输入125个汉字'"><c:forTokens
                        items="${sheetMain.mainFactory}" delims="," var="mainFactory" varStatus="status"><eoms:id2nameDB
                        id="${mainFactory}" beanId="ItawSystemDictTypeDao"/><c:choose><c:when
                        test="${status.last}"></c:when><c:otherwise>,</c:otherwise></c:choose></c:forTokens></textarea>
                <input type="hidden" name="${sheetPageName}mainFactory" id="${sheetPageName}mainFactory"
                       value="${sheetMain.mainFactory}"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainApplyReason"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}mainApplyReason"
                          id="${sheetPageName}mainApplyReason"
                          alt="width:500,allowBlank:false,maxLength:2000,vtext:'请填入申请依据，最多输入2000字'">${sheetMain.mainApplyReason}</textarea>
            </td>
        </tr>


    </table>
    <br>
    <table class="formTable">
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainCellInfo"/>*</td>
            <td colspan="3">
                <script type="text/javascript">
                    function resetNet(type) {
                        var obj;
                        if (type == 0) {
                            obj = document.getElementById("mainCellInfo");
                        } else if (type == 1) {
                            obj = document.getElementById("maincellinfo1");
                        } else if (type == 2) {
                            obj = document.getElementById("maincellinfo2");
                        }
                        obj.value = "";
                    }

                    function deleteNet(type) {
                        var obj;
                        if (type == 0) {
                            obj = document.getElementById("mainCellInfo");
                        } else if (type == 1) {
                            obj = document.getElementById("maincellinfo1");
                        } else if (type == 2) {
                            obj = document.getElementById("maincellinfo2");
                        }
                        var value = obj.value;
                        obj.value = value.substring(0, value.lastIndexOf(","));
                    }

                    function selectNet(type) {
                        var obj;
                        var softwareObj = "";
                        var softwareObjname = "";
                        if (type == 0) {
                            obj = document.getElementById("mainCellInfo");
                            softwareObj = document.getElementById("mainSoftCDKey");
                            softwareObjname = document.getElementById("mainSoftCDKeyName");
                        } else if (type == 1) {
                            obj = document.getElementById("maincellinfo1");
                            softwareObj = document.getElementById("mainsoftcdkey1");
                            softwareObjname = document.getElementById("mainsoftcdkey1Name");
                        } else if (type == 2) {
                            obj = document.getElementById("maincellinfo2");
                            softwareObj = document.getElementById("mainsoftcdkey2");
                            softwareObjname = document.getElementById("mainsoftcdkey2Name");
                        }
                        var urls = "${app}/repository/tawLocalRepositorys.do?method=softquery";
                        var mainCellInfo = obj.value;
                        var myObject = new Object();
                        myObject.mainCellInfo = mainCellInfo;
                        myObject.softwareRepository = softwareObj.value;
                        //myObject.softwareRepositoryName= softwareObjname.value;
                        var returnValue = window.showModalDialog(urls, myObject, "help:no;resizable:yes;status:no;dialogWidth=1024px;dialogHeight=700px");
                        if (returnValue != 'undefined' || returnValue != '') {
                            var allvalue = returnValue.split("&");
                            var softwareRepository = allvalue[1].split(",")[0];
                            //var softwareRepositoryname = allvalue[1].split(",")[1];
                            var returnValueS = allvalue[0].split(",");
                            for (var i = 0; i < returnValueS.length; i++) {
                                if (mainCellInfo.indexOf(returnValueS[i]) == -1) {
                                    if (mainCellInfo != '') {
                                        mainCellInfo += "," + returnValueS[i];
                                    } else {
                                        mainCellInfo += returnValueS[i];
                                    }
                                }
                            }
                            obj.value = mainCellInfo;
                            softwareObj.value = softwareRepository;
                            //softwareObjname.value= softwareRepositoryname;
                        }
                    }
                </script>
                <input onclick="selectNet(0)" value="添加网元" type="button" class="btn">
                <input onclick="deleteNet(0)" value="删除最后一个" type="button" class="btn">
                <input onclick="resetNet(0)" value="重置" type="button" class="btn">
                <textarea class="textarea max" readonly="true" name="${sheetPageName}mainCellInfo"
                          id="${sheetPageName}mainCellInfo"
                          alt="width:500,allowBlank:false,maxLength:2000,vtext:'请填入变更网元，请最多输入2000字'">${sheetMain.mainCellInfo}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainSoftCDKey"/>*</td>
            <td>

                <div id="factoryview" class="hide"></div>
                <script type="text/javascript">
                    //viewer
                    var factoryViewer = new Ext.JsonView("factoryview",
                        '<div class="viewlistitem-{nodeType}">{name}</div>',
                        {emptyText: '<div>没有选择项目</div>'}
                    );
                    var data = "[]";
                    areaViewer.jsonData = eoms.JSONDecode(data);
                    areaViewer.refresh();
                    //area tree
                    var factoryTreeAction = '${app}/xtree.do?method=dict';
                    factoryTree = new xbox({
                        btnId: '${sheetPageName}showSoftCDKey',
                        treeDataUrl: factoryTreeAction,
                        treeRootId: '1011701',
                        treeRootText: '网元版本号',
                        treeChkMode: '',
                        treeChkType: 'dict',
                        showChkFldId: '${sheetPageName}showSoftCDKey',
                        saveChkFldId: '${sheetPageName}mainSoftCDKey',
                        viewer: factoryViewer
                    });
                </script>
                <textarea class="textarea max" readonly="true" name="${sheetPageName}showSoftCDKey" style="height:50px"
                          id="${sheetPageName}showSoftCDKey"
                          alt="allowBlank:false,maxLength:250,vtext:'请填入设备厂家，最多输入125个汉字'"><c:forTokens
                        items="${sheetMain.mainSoftCDKey}" delims="," var="mainSoftCDKey"
                        varStatus="status"><eoms:id2nameDB id="${mainSoftCDKey}"
                                                           beanId="ItawSystemDictTypeDao"/><c:choose><c:when
                        test="${status.last}"></c:when><c:otherwise>,</c:otherwise></c:choose></c:forTokens></textarea>
                <input type="hidden" name="${sheetPageName}mainSoftCDKey" id="${sheetPageName}mainSoftCDKey"
                       value="${sheetMain.mainSoftCDKey}"/>
            </td>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainSoftPatchKey"/>*</td>
            <td><input type="text" class="text" name="${sheetPageName}mainSoftPatchKey"
                       id="${sheetPageName}mainSoftPatchKey" value="${sheetMain.mainSoftPatchKey}"
                       alt="allowBlank:false,maxLength:25,vtext:'请填入软件补丁号，最多输入25字'"/></td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainIsAllow"/>*</td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainIsAllow" id="${sheetPageName}mainIsAllow" initDicId="10301"
                               alt="allowBlank:false" styleClass="select-class"
                               defaultValue="${sheetMain.mainIsAllow}"/>
            </td>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainAllowKey"/></td>
            <td><input type="text" class="text" name="${sheetPageName}mainAllowKey" id="${sheetPageName}mainAllowKey"
                       value="${sheetMain.mainAllowKey}" alt="allowBlank:true,maxLength:25 ,vtext:'请填入入网许可证号，最多输入25字'"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainSoftDetail"/></td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}mainSoftDetail" id="${sheetPageName}mainSoftDetail"
                          alt="width:500,allowBlank:true,maxLength:2000,vtext:'请填入软件信息详细描述，最多输入2000字'">${sheetMain.mainSoftDetail}</textarea>
            </td>
        </tr>

    </table>

    <br>
    <table class="formTable">
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainCellInfo"/>1</td>
            <td colspan="3">
                <input onclick="selectNet(1)" value="添加网元" type="button" class="btn">
                <input onclick="deleteNet(1)" value="删除最后一个" type="button" class="btn">
                <input onclick="resetNet(1)" value="重置" type="button" class="btn">
                <textarea class="textarea max" readonly="true" name="${sheetPageName}maincellinfo1"
                          id="${sheetPageName}maincellinfo1">${sheetMain.maincellinfo1}</textarea>
            </td>
        </tr>
        <tr>


            <td class="label"><bean:message bundle="softchange" key="softchange.mainSoftCDKey"/>1</td>
            <td>
                <div id="factoryview" class="hide"></div>
                <script type="text/javascript">
                    //viewer
                    var factoryViewer = new Ext.JsonView("factoryview",
                        '<div class="viewlistitem-{nodeType}">{name}</div>',
                        {emptyText: '<div>没有选择项目</div>'}
                    );
                    var data = "[]";
                    areaViewer.jsonData = eoms.JSONDecode(data);
                    areaViewer.refresh();
                    //area tree
                    var factoryTreeAction = '${app}/xtree.do?method=dict';
                    factoryTree = new xbox({
                        btnId: '${sheetPageName}showSoftCDKey1',
                        treeDataUrl: factoryTreeAction,
                        treeRootId: '1011701',
                        treeRootText: '网元版本号',
                        treeChkMode: '',
                        treeChkType: 'dict',
                        showChkFldId: '${sheetPageName}showSoftCDKey1',
                        saveChkFldId: '${sheetPageName}mainsoftcdkey1',
                        viewer: factoryViewer
                    });
                </script>
                <textarea class="textarea max" readonly="true" name="${sheetPageName}showSoftCDKey1" style="height:50px"
                          id="${sheetPageName}showSoftCDKey1"><c:forTokens items="${sheetMain.mainsoftcdkey1}"
                                                                           delims="," var="mainsoftcdkey1"
                                                                           varStatus="status"><eoms:id2nameDB
                        id="${mainsoftcdkey1}" beanId="ItawSystemDictTypeDao"/><c:choose><c:when
                        test="${status.last}"></c:when><c:otherwise>,</c:otherwise></c:choose></c:forTokens></textarea>
                <input type="hidden" name="${sheetPageName}mainsoftcdkey1" id="${sheetPageName}mainsoftcdkey1"
                       value="${sheetMain.mainsoftcdkey1}"/>
            </td>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainSoftPatchKey"/>1</td>
            <td><input type="text" class="text" name="${sheetPageName}mainsoftpatchkey1"
                       id="${sheetPageName}mainsoftpatchkey1" value="${sheetMain.mainsoftpatchkey1}"/></td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainIsAllow"/>1</td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainisallow1" id="${sheetPageName}mainisallow1" initDicId="10301"
                               defaultValue="${sheetMain.mainisallow1}" styleClass="select-class"/>
            </td>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainAllowKey"/>1</td>
            <td><input type="text" class="text" name="${sheetPageName}mainallowkey1" id="${sheetPageName}mainallowkey1"
                       value="${sheetMain.mainallowkey1}"/></td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainSoftDetail"/>1</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}mainsoftdetail1"
                          id="${sheetPageName}mainsoftdetail1">${sheetMain.mainsoftdetail1}</textarea>
            </td>
        </tr>

    </table>
    <br>
    <br>
    <table class="formTable">
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainCellInfo"/>2</td>
            <td colspan="3">
                <input onclick="selectNet(2)" value="添加网元" type="button" class="btn">
                <input onclick="deleteNet(2)" value="删除最后一个" type="button" class="btn">
                <input onclick="resetNet(2)" value="重置" type="button" class="btn">
                <textarea class="textarea max" readonly="true" name="${sheetPageName}maincellinfo2"
                          id="${sheetPageName}maincellinfo2"></textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainSoftCDKey"/>2</td>
            <td>
                <div id="factoryview" class="hide"></div>
                <script type="text/javascript">
                    //viewer
                    var factoryViewer = new Ext.JsonView("factoryview",
                        '<div class="viewlistitem-{nodeType}">{name}</div>',
                        {emptyText: '<div>没有选择项目</div>'}
                    );
                    var data = "[]";
                    areaViewer.jsonData = eoms.JSONDecode(data);
                    areaViewer.refresh();
                    //area tree
                    var factoryTreeAction = '${app}/xtree.do?method=dict';
                    factoryTree = new xbox({
                        btnId: '${sheetPageName}showSoftCDKey2',
                        treeDataUrl: factoryTreeAction,
                        treeRootId: '1011701',
                        treeRootText: '网元版本号',
                        treeChkMode: '',
                        treeChkType: 'dict',
                        showChkFldId: '${sheetPageName}showSoftCDKey2',
                        saveChkFldId: '${sheetPageName}mainsoftcdkey2',
                        viewer: factoryViewer
                    });
                </script>
                <textarea class="textarea max" readonly="true" name="${sheetPageName}showSoftCDKey2" style="height:50px"
                          id="${sheetPageName}showSoftCDKey2"><c:forTokens items="${sheetMain.mainsoftcdkey2}"
                                                                           delims="," var="mainsoftcdkey2"
                                                                           varStatus="status"><eoms:id2nameDB
                        id="${mainsoftcdkey2}" beanId="ItawSystemDictTypeDao"/><c:choose><c:when
                        test="${status.last}"></c:when><c:otherwise>,</c:otherwise></c:choose></c:forTokens></textarea>
                <input type="hidden" name="${sheetPageName}mainsoftcdkey2" id="${sheetPageName}mainsoftcdkey2"
                       value="${sheetMain.mainsoftcdkey2}"/>
            </td>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainSoftPatchKey"/>2</td>
            <td><input type="text" class="text" name="${sheetPageName}mainsoftpatchkey2"
                       id="${sheetPageName}mainsoftpatchkey2" value="${sheetMain.mainsoftpatchkey2}"/></td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainIsAllow"/>2</td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainisallow2" id="${sheetPageName}mainisallow2" initDicId="10301"
                               styleClass="select-class"/>
            </td>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainAllowKey"/>2</td>
            <td><input type="text" class="text" name="${sheetPageName}mainallowkey2"
                       id="${sheetPageName}mainallowkey2"/></td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainSoftDetail"/>2</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}mainsoftdetail2"
                          id="${sheetPageName}mainsoftdetail2"></textarea>
            </td>
        </tr>

    </table>
    <br>
    <table class="formTable">
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainIsBack"/>*</td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainIsBack" id="${sheetPageName}mainIsBack"
                               initDicId="10301" alt="allowBlank:true" styleClass="select-class"
                               defaultValue="${sheetMain.mainIsBack}"/>
            </td>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainChangeSource"/>*</td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainChangeSource" id="${sheetPageName}mainChangeSource"
                               initDicId="1010901" alt="allowBlank:false" styleClass="select-class"
                               defaultValue="${sheetMain.mainChangeSource}" onchange="isHold(this.value);"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainParentProcessName"/></td>
            <td>
                <c:if test="${!empty parentSheetId}">
                    <input type="text" class="text" name="${sheetPageName}mainParentProcessName"
                           id="${sheetPageName}mainParentProcessName" value="${parentSheetId}" alt="allowBlank:true"
                           readonly/>
                </c:if>
                <c:if test="${empty parentSheetId}">
                    <input type="text" class="text" name="${sheetPageName}mainParentProcessName"
                           id="${sheetPageName}mainParentProcessName" value="${sheetMain.mainParentProcessName}"
                           alt="allowBlank:true"/>
                </c:if>
            </td>
            <td class="label"><bean:message bundle="softchange" key="softchange.mainIsNeedDesign"/></td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainIsNeedDesign" id="${sheetPageName}mainIsNeedDesign"
                               initDicId="10301" alt="allowBlank:true" styleClass="select-class"
                               defaultValue="${sheetMain.mainIsNeedDesign}" onchange="ifDoProjectDeal(this.value)"/>
                <input type="hidden" name="${sheetPageName}trueActiveTemplateId"
                       id="${sheetPageName}trueActiveTemplateId" value="${taskName}"/>
            </td>
        </tr>
        <!--     <tr>
		            <td  class="label"><bean:message bundle="softchange" key="softchange.mainDesignId"/>*</td>
		            <td colspan='3'> <input type="text"  class="text" name="${sheetPageName}mainDesignId" id="${sheetPageName}mainDesignId" value="${sheetMain.mainDesignId}" alt="allowBlank:false ,maxLength:25,vtext:'请填入资源方案号，最多输入25字'"/></td>
		     </tr>  -->
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
                                 scope="request" idField="${sheetPageName}sheetAccessories" appCode="softchange"/>
            </td>
        </tr>

    </table>
    <!-- 是否报备 -->
    <input type="hidden" name="${sheetPageName}mainifrecord" id="${sheetPageName}mainifrecord"
           value="${sheetMain.mainifrecord}"/>

    <!-- 方案制定 -->
    <table id="sheetProjectCreate" class="formTable" style="display:none">
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkCompleteLimitTime"/>*</td>
            <td class="content"><input class="text" onclick="popUpCalendar(this, this)" type="text"
                                       name="${sheetPageName}linkCompleteLimitTime" readonly="readonly"
                                       alt="allowBlank:false"
                                       value="${eoms:date2String(sheetMain.linkCompleteLimitTime)}"
                                       id="${sheetPageName}linkCompleteLimitTime"/>
            </td>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkDesignKey"/>*</td>
            <td class="content"><input type="text" class="text" name="${sheetPageName}linkDesignKey"
                                       id="${sheetPageName}linkDesignKey" value="${sheetMain.linkDesignKey}"
                                       alt="allowBlank:false,maxLength:25,vtext:'请填入技术方案关键字，最多输入25字'"/></td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkDesignComment"/></td>
            <td colspan="3" class="content">
                <textarea class="textarea max" name="${sheetPageName}linkDesignComment"
                          id="${sheetPageName}linkDesignComment"
                          alt="width:500,allowBlank:true,maxLength:2000,vtext:'请填入技术方案说明，最多输入2000字'">${sheetMain.linkDesignComment}</textarea>
            </td>
        </tr>
        <tr>
            <!-- 变更涉及省份 -->
            <td class="label">
                涉及省份及地市*
            </td>
            <td colspan="3">
                <input type="button" id="areabtn" value="选择涉及省份和地市" class="btn"/>


                <br/><br/>
                涉及省份:<textarea class="textarea max" readonly="true" name="linkInvolvedProvince" style="height:50px"
                               id="${sheetPageName}linkInvolvedProvince"
                               alt="allowBlank:false,maxLength:4000,vtext:'请填入备注信息，最多输入4000字符'">${sheetMain.linkInvolvedProvince }</textarea><br/>
                涉及地市:<textarea class="textarea max" readonly="true" name="linkInvolvedCity" style="height:50px"
                               id="${sheetPageName}linkInvolvedCity"
                               alt="allowBlank:true,maxLength:4000,vtext:'请填入备注信息，最多输入4000字符'">${sheetMain.linkInvolvedCity}</textarea>

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
            <td class="label"><bean:message bundle="softchange" key="softchange.linkRiskEstimate"/>*</td>
            <td colspan="3" class="content">
                <textarea class="textarea max" name="${sheetPageName}linkRiskEstimate"
                          id="${sheetPageName}linkRiskEstimate"
                          alt="width:500,allowBlank:false,maxLength:2000,vtext:'请填入风险评估，最多输入2000字'">${sheetMain.linkRiskEstimate}</textarea>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="softchange" key="softchange.linkEffectAnalyse"/>*</td>
            <td colspan="3" class="content">
                <textarea class="textarea max" name="${sheetPageName}linkEffectAnalyse"
                          id="${sheetPageName}linkEffectAnalyse"
                          alt="width:500,allowBlank:false,maxLength:2000,vtext:'请填入影响业务分析，最多输入2000字'">${sheetMain.linkEffectAnalyse}</textarea>
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
            <td class="label"><bean:message bundle="softchange" key="softchange.linkProjectAccessories"/></td>
            <td colspan="3">
                <eoms:attachment name="sheetMain" property="firstNodeAccessories"
                                 scope="request" idField="${sheetPageName}firstNodeAccessories" appCode="softchange"/>

            </td>
        </tr>

    </table>


</c:if>
<br/>
<c:if test="${status!=1}">
    <div id='test1'>
        <fieldset id="link1">
            <legend>
                <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
                <bean:message bundle="softchange" key="role.changeAdmin"/>
            </legend>
            <eoms:chooser id="test11" type="role" roleId="255" flowId="43" config="{returnJSON:true,showLeader:true}"
                          category="[{id:'dealPerformer',limit:'none',text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                          data="${sendUserAndRoles}"/>
        </fieldset>
        <fieldset id="link3">
            <legend>
                排程角色:<bean:message bundle="softchange" key="role.netSchemer"/>
            </legend>
            <eoms:chooser id="test33" type="role" roleId="256" flowId="43"
                          config="{returnJSON:true,showLeader:true,mainPanelTitle:'可选择的子角色'}"
                          category="[{id:'${sheetPageName}extendPerformer',childType:'user,subrole',limit:'none',text:'排程',allowBlank:false,vtext:'请选择排程人'}]"
                          data="${sendUserAndRoles}"/>
        </fieldset>

    </div>
</c:if>
<script type="text/javascript">
    var v1 = eoms.form;
    isHold(document.all.${sheetPageName}mainChangeSource.value);

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
            url: "softchange.do?method=newShowLimit&specialty1=" + temp1 + "&specialty2=" + temp2 + "&specialty3=" + temp3 + "&specialty4=" + temp4 + "&flowName=SoftChangeMainProcess",
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
</script> 
