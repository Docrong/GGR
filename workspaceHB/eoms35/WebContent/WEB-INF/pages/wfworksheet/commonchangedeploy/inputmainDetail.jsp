<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>


<!-- 不是从查看页面过来 -->
<c:if test="${readOnly != 'true'}">

    <!-- 工单基本信息 -->
    <table id="sheet" class="formTable">

        <tr>
            <td class="label">
                <!-- 网络分类一级 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainNetTypeOne"/>*
            </td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainNetTypeOne" id="${sheetPageName}mainNetTypeOne"
                               sub="${sheetPageName}mainNetTypeTwo" initDicId="1010104"
                               defaultValue="${sheetMain.mainNetTypeOne}" alt="allowBlank:false"
                               onchange="selectLimit(this.value);"/>
            </td>
            <td class="label">
                <!-- 网络分类二级 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainNetTypeTwo"/>*
            </td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainNetTypeTwo" id="${sheetPageName}mainNetTypeTwo"
                               sub="${sheetPageName}mainNetTypeThree" initDicId="${sheetMain.mainNetTypeOne}"
                               defaultValue="${sheetMain.mainNetTypeTwo}" onchange="selectLimit(this.value);"/>
            </td>
        </tr>
        <tr>
            <td class="label">
                <!-- 网络分类三级 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainNetTypeThree"/>*
            </td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainNetTypeThree" id="${sheetPageName}mainNetTypeThree"
                               initDicId="${sheetMain.mainNetTypeTwo}" defaultValue="${sheetMain.mainNetTypeThree}"
                               onchange="selectLimit(this.value);"/>
            </td>
            <td class="label">
                <!-- 是否涉及安全 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainIsSecurity"/>*
            </td>
            <td>
                <eoms:comboBox name="mainIsSecurity" id="mainIsSecurity"

                               defaultValue="${sheetMain.mainIsSecurity}" alt="allowBlank:false" initDicId="10301"/>
            </td>
        </tr>

        <tr>
            <td class="label">
                <!-- 是否涉及互联互通 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainIsConnect"/>*
            </td>
            <td>
                <eoms:comboBox name="mainIsConnect" id="mainIsConnect"

                               defaultValue="${sheetMain.mainIsConnect}" alt="allowBlank:false" initDicId="10301"/>
            </td>
            <td class="label">
                <!-- 设备厂家 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainFactory"/>*
            </td>
            <td colspan="3">
                <div id="factoryview" class="hide"></div>
                <script type="text/javascript">
                    Ext.onReady(function () {
                        //viewer
                        var factoryViewer = new Ext.JsonView("factoryview",
                            '<div class="viewlistitem-{nodeType}">{name}</div>',
                            {
                                emptyText: '<div>没有选择项目</div>'
                            }
                        );
                        var data = "[]";
                        factoryViewer.jsonData = eoms.JSONDecode(data);
                        factoryViewer.refresh();

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
                    });
                </script>
                <textarea class="textarea max" readonly="true" name="${sheetPageName}showFactory" style="height:50px"
                          id="${sheetPageName}showFactory"
                          alt="allowBlank:true,maxLength:250,vtext:'请填入设备厂家，最多输入125个汉字'"><c:forTokens
                        items="${sheetMain.mainFactory}" delims="," var="mainFactory" varStatus="status"><eoms:id2nameDB
                        id="${mainFactory}" beanId="ItawSystemDictTypeDao"/><c:choose><c:when
                        test="${status.last}"></c:when><c:otherwise>,</c:otherwise></c:choose></c:forTokens></textarea>
                <input type="hidden" name="${sheetPageName}mainFactory" id="${sheetPageName}mainFactory"
                       value="${sheetMain.mainFactory}"/>
            </td>
        </tr>
        <tr>
            <td class="label">
                <!-- 申请依据 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainApplyReason"/>*
            </td>
            <td>
                <input type="text" class="text" name="mainApplyReason" id="mainApplyReason"
                       alt="allowBlank:false,maxLength:32,vtext:'请填入 申请依据 信息，最多输入 32 字符'"
                       value="${sheetMain.mainApplyReason}"/>
            </td>
            <td class="label">
                <!-- 变更网元 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainCellInfo"/>*
            </td>
            <td>
                <input type="text" class="text" name="mainCellInfo" id="mainCellInfo"
                       alt="allowBlank:false,maxLength:32,vtext:'请填入 变更网元 信息，最多输入 32 字符'"
                       value="${sheetMain.mainCellInfo}"/>
            </td>
        </tr>

        <tr>
            <td class="label">
                <!-- 是否变更实施备案 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainIsBack"/>
            </td>
            <td>
                <eoms:comboBox name="mainIsBack" id="mainIsBack"

                               defaultValue="${sheetMain.mainIsBack}" alt="allowBlank:true" initDicId="10301"/>
            </td>
            <td class="label">
                <!-- 变更来源 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainChangeSource"/>*
            </td>
            <td>
                <input type="text" class="text" name="mainChangeSource" id="mainChangeSource"
                       alt="allowBlank:false,maxLength:32,vtext:'请填入 变更来源 信息，最多输入 32 字符'"
                       value="${sheetMain.mainChangeSource}"/>
            </td>
        </tr>
        <tr>
            <td class="label">
                <!-- 审核对象 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainCheckModel"/>*
            </td>
            <td>
                <input type="text" class="text" name="mainCheckModel" id="mainCheckModel"
                       alt="allowBlank:false,maxLength:32,vtext:'请填入 审核对象 信息，最多输入 32 字符'"
                       value="${sheetMain.mainCheckModel}"/>
            </td>
            <td class="label">
                <!-- 完成时限 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainCompleteLimitTime"/>*
            </td>
            <td>
                <input type="text" class="text" name="sheetCompleteLimit" readonly="readonly"
                       id="sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"
                       onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>
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
                涉及省份:<textarea class="textarea max" readonly="true" name="mainInvolvedProvince" style="height:50px"
                               id="${sheetPageName}mainInvolvedProvince"
                               alt="allowBlank:false,maxLength:4000,vtext:'请填入备注信息，最多输入4000字符'">${sheetMain.mainInvolvedProvince }</textarea><br/>
                涉及地市:<textarea class="textarea max" readonly="true" name="mainInvolvedCity" style="height:50px"
                               id="${sheetPageName}mainInvolvedCity"
                               alt="allowBlank:true,maxLength:4000,vtext:'请填入备注信息，最多输入4000字符'">${sheetMain.mainInvolvedCity}</textarea>

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
                            $('${sheetPageName}mainInvolvedProvince').value = shengNameArr.join(",");
                            $('${sheetPageName}mainInvolvedCity').value = shiNameArr.join(",");

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
            <td class="label">
                <!-- 技术方案关键字 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainDesignKey"/>*
            </td>
            <td>
                <input type="text" class="text" name="mainDesignKey" id="mainDesignKey"
                       alt="allowBlank:false,maxLength:32,vtext:'请填入 技术方案关键字 信息，最多输入 32 字符'"
                       value="${sheetMain.mainDesignKey}"/>
            </td>
        </tr>
        <tr>
            <td class="label">
                <!-- 技术方案说明 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainDesignComment"/>
            </td>
            <td colspan="3">
				<textarea class="textarea max" name="mainDesignComment"
                          id="mainDesignComment"
                          alt="allowBlank:true,maxLength:255,vtext:'请填入 技术方案说明 信息，最多输入 255 字符'">${sheetMain.mainDesignComment}</textarea>

            </td>
        </tr>

        <tr>
            <td class="label">
                <!-- 风险评估 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainRiskEstimate"/>*
            </td>
            <td colspan="3">
				<textarea class="textarea max" name="mainRiskEstimate"
                          id="mainRiskEstimate"
                          alt="allowBlank:false,maxLength:32,vtext:'请填入 风险评估 信息，最多输入 32 字符'">${sheetMain.mainRiskEstimate}</textarea>

            </td>
        </tr>
        <tr>
            <td class="label">
                <!-- 影响业务分析 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainEffectAnalyse"/>*
            </td>
            <td colspan="3">
				<textarea class="textarea max" name="mainEffectAnalyse"
                          id="mainEffectAnalyse"
                          alt="allowBlank:false,maxLength:255,vtext:'请填入 影响业务分析 信息，最多输入 255 字符'">${sheetMain.mainEffectAnalyse}</textarea>

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
            <td class="label">
                <!-- 技术方案附件 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainProjectAccessories"/>
            </td>
            <td colspan="3">
                <eoms:attachment name="sheetMain" property="mainProjectAccessories" scope="request"
                                 idField="mainProjectAccessories" appCode="commonchangedeploy"/>

            </td>
        </tr>

    </table>


    <!--派单树-->
    <br/>
    <fieldset>
        <legend>
            <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>：
            <span id="roleName">
		 	 抄送角色：<bean:message bundle="commonchangedeploy" key="role.changeadmin"/>
		 </span>
        </legend>
        <div class="x-form-item">
            <eoms:chooser id="sendObject" type="role" roleId="406" flowId="${flowId}"
                          config="{returnJSON:true,showLeader:true}"
                          category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                          data="${sheetMain.sendObject}"/>
            <input type="hidden" name="phaseId" id="phaseId" value="${toPhaseIdMap[operateType]}"/>
        </div>
    </fieldset>
</c:if>


<!-- 是从查看页面过来 -->
<c:if test="${readOnly == 'true'}">

    <table class="formTable">

        <tr>
            <td class="label">
                <!-- 网络分类一级 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainNetTypeOne"/>
            </td  class="content">
            <td>
                <eoms:id2nameDB id="${sheetMain.mainNetTypeOne}" beanId="ItawSystemDictTypeDao"/>
            </td>
            <td class="label">
                <!-- 网络分类二级 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainNetTypeTwo"/>
            </td>
            <td class="content">
                <eoms:id2nameDB id="${sheetMain.mainNetTypeTwo}" beanId="ItawSystemDictTypeDao"/>
            </td>
        </tr>
        <tr>
            <td class="label">
                <!-- 网络分类三级 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainNetTypeThree"/>
            </td  class="content">
            <td>
                <eoms:id2nameDB id="${sheetMain.mainNetTypeThree}" beanId="ItawSystemDictTypeDao"/>
            </td>
            <td class="label">
                <!-- 是否涉及安全 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainIsSecurity"/>
            </td>
            <td class="content">
                <eoms:id2nameDB id="${sheetMain.mainIsSecurity}" beanId="ItawSystemDictTypeDao"/>
            </td>
        </tr>
        <tr>
            <td class="label">
                <!-- 是否涉及互联互通 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainIsConnect"/>
            </td>
            <td class="content">
                <eoms:id2nameDB id="${sheetMain.mainIsConnect}" beanId="ItawSystemDictTypeDao"/>
            </td>
            <td class="label">
                <!-- 设备厂家 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainFactory"/>
            </td>

            <td class="content">
                <eoms:id2nameDB id="${sheetMain.mainFactory}" beanId="ItawSystemDictTypeDao"/>
            </td>
        </tr>
        <tr>
            <td class="label">
                <!-- 申请依据 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainApplyReason"/>
            </td>
            <td class="content">
                    ${sheetMain.mainApplyReason}
            </td>
            <td class="label">
                <!-- 变更网元 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainCellInfo"/>
            </td>
            <td class="content">
                    ${sheetMain.mainCellInfo}
            </td>
        </tr>
        <tr>
            <td class="label">
                <!-- 是否变更实施备案 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainIsBack"/>
            </td>
            <td class="content">
                <eoms:id2nameDB id="${sheetMain.mainIsBack}" beanId="ItawSystemDictTypeDao"/>
            </td>
            <td class="label">
                <!-- 变更来源 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainChangeSource"/>
            </td>
            <td class="content">
                    ${sheetMain.mainChangeSource}
            </td>
        </tr>
        <tr>
            <td class="label">
                <!-- 审核对象 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainCheckModel"/>
            </td>
            <td class="content">
                    ${sheetMain.mainCheckModel}
            </td>
            <td class="label">
                <!-- 完成时限 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainCompleteLimitTime"/>
            </td>
            <td class="content">
                    ${eoms:date2String(sheetMain.sheetCompleteLimit)}
            </td>
        </tr>
        <tr>
            <td class="label">
                <!-- 变更涉及省份 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainInvolvedProvince"/>
            </td>
            <td class="content">
                    ${sheetMain.mainInvolvedProvince}

            </td>
            <td class="label">
                <!-- 变更涉及地市 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainInvolvedCity"/>
            </td>
            <td class="content">
                    ${sheetMain.mainInvolvedCity}

            </td>
        </tr>
        <tr>
            <td class="label">
                <!-- 技术方案关键字 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainDesignKey"/>
            </td>
            <td class="content" colspan="3">
                    ${sheetMain.mainDesignKey}
            </td>
        </tr>
        <tr>
            <td class="label">
                <!-- 技术方案说明 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainDesignComment"/>
            </td>
            <td class="content" colspan="3">
                <pre>${sheetMain.mainDesignComment}</pre>

            </td>
        </tr>
        <tr>
            <td class="label">
                <!-- 风险评估 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainRiskEstimate"/>
            </td>
            <td class="content" colspan="3">
                <pre>${sheetMain.mainRiskEstimate}</pre>

            </td>
        </tr>
        <tr>
            <td class="label">
                <!-- 影响业务分析 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainEffectAnalyse"/>
            </td>
            <td class="content" colspan="3">
                <pre>${sheetMain.mainEffectAnalyse}</pre>

            </td>
        </tr>
        <tr>
            <td class="label">
                <!-- 技术方案附件 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployMain.mainProjectAccessories"/>
            </td>
            <td class="content" colspan="3">
                <eoms:attachment name="sheetMain" property="mainProjectAccessories" scope="request"
                                 idField="mainProjectAccessories" appCode="commonchangedeploy" viewFlag="Y"/>

            </td>
        </tr>
    </table>
</c:if>


 