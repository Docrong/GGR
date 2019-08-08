<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%

    request.setAttribute("roleId", "1920");


    long localTimes = com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
%>
<%@ include file="/WEB-INF/pages/wfworksheet/citycustom/baseinputmainhtmlnew.jsp" %>
<script type="text/javascript">
    selectLimit();

    function selectLimit() {
        Ext.Ajax.request({
            method: "get",
            url: "citycustom.do?method=newShowLimit&flowName=CityCustom",
            success: function (x) {
                var o = eoms.JSONDecode(x.responseText);
                if ((o.acceptLimit == null || o.acceptLimit == "") && (o.replyLimit == null || o.replyLimit == "")) {
                    // $("sheetAcceptLimit").value = "";
                    // $('sheetCompleteLimit').value = "";
                } else {
                    var times =<%=localTimes%>;
                    var dt1 = new Date().add(Date.MINUTE, parseInt(o.acceptLimit, 10));
                    var dt2 = dt1.add(Date.DAY, parseInt(5, 10));
                    $("sheetAcceptLimit").value = dt1.format('Y-m-d H:i:s');
                    $('sheetCompleteLimit').value = dt2.format('Y-m-d H:i:s');
                }
            }
        });
    }
</script>
<input type="hidden" name="processTemplateName" value="CityCustom"/>
<input type="hidden" name="operateName" value="newWorkSheet"/>
<c:if test="${status!=1}">


    <input type="hidden" name="phaseId" id="phaseId" value="OrderExamine"/>

    <input type="hidden" id="operateType" name="operateType" value="0"/>
    <input type="hidden" name="gatherPhaseId" id="gatherPhaseId" value="HoldTask"/>
</c:if>
<c:if test="${status==1}">
    <input type="hidden" name="phaseId" id="phaseId" value="OverTask"/>
</c:if>
<input type="hidden" name="beanId" value="iCityCustomMainManager"/>
<input type="hidden" name="mainClassName" value="com.boco.eoms.sheet.citycustom.model.CityCustomMain"/>
<input type="hidden" name="linkClassName" value="com.boco.eoms.sheet.citycustom.model.CityCustomLink"/>
<br>

<!-- 工单基本信息 -->
<table id="sheet" class="formTable">
    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.acceptLimit"/>*</td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly"
                   id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"
                   onclick="popUpCalendar(this, this)"
                   alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>

        </td>
        <td class="label"><bean:message bundle="sheet" key="mainForm.completeLimit"/>*</td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly"
                   id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"
                   onclick="popUpCalendar(this, this)"
                   alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>

        </td>
    </tr>
    <tr>
        <td class="label">
            <!-- A端客户经理 -->
            A端客户经理*
        </td>
        <td class="content">
            <input type="text" class="text" name="manager" id="manager"
                   alt="allowBlank:false,maxLength:500,vtext:'请填入 发起方集客部客户经理 信息，最多输入 500 字符'"
                   value="${sheetMain.manager}"/>
        </td>
        <td class="label">
            <!-- 客户经理联系电话 -->
            <bean:message bundle="citycustom" key="cityCustomMain.managerNum"/>*
        </td>
        <td class="content">
            <input type="text" class="text" name="managerNum" id="managerNum"
                   alt="allowBlank:false,maxLength:500,vtext:'请填入 客户经理联系电话 信息，最多输入 500 字符'"
                   value="${sheetMain.managerNum}"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 产品名称 -->
            <bean:message bundle="citycustom" key="cityCustomMain.prodouct"/>*
        </td>
        <td class="content">
            <eoms:comboBox name="prodouct" id="prodouct"
                           initDicId="1012101"
                           defaultValue="${sheetMain.prodouct}" alt="allowBlank:false"/>
        </td>
        <td class="label">
            <!-- 集团编号 -->
            <bean:message bundle="citycustom" key="cityCustomMain.groupNum"/>*
        </td>
        <td class="content">
            <input type="text" class="text" name="groupNum" id="groupNum"
                   alt="allowBlank:false,maxLength:500,vtext:'请填入 集团编号 信息，最多输入 500 字符'" value="${sheetMain.groupNum}"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 集团名称 -->
            <bean:message bundle="citycustom" key="cityCustomMain.groupName"/>*
        </td>
        <td class="content">
            <input type="text" class="text" name="groupName" id="groupName"
                   alt="allowBlank:false,maxLength:500,vtext:'请填入 集团名称 信息，最多输入 500 字符'" value="${sheetMain.groupName}"/>
        </td>
        <td class="label">
            <!-- 集团级别 -->
            集团级别*
        </td>
        <td>
            <eoms:comboBox name="groupLevel" id="groupLevel"
                           initDicId="1012102"
                           defaultValue="${sheetMain.groupLevel}" alt="allowBlank:false"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 业务保障等级 -->
            <bean:message bundle="citycustom" key="cityCustomMain.businessGrade"/>*
        </td>
        <td class="content">
            <eoms:comboBox name="businessGrade" id="businessGrade"
                           initDicId="1012104"
                           defaultValue="${sheetMain.businessGrade}" alt="allowBlank:false"/>
        </td>
        <td class="label">
            <!-- 任务类型 -->
            <bean:message bundle="citycustom" key="cityCustomMain.taskType"/>*
        </td>
        <td class="content">
            <eoms:comboBox name="taskType" id="taskType"
                           initDicId="1012105"
                           defaultValue="${sheetMain.taskType}" alt="allowBlank:false"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 所属地市 -->
            <bean:message bundle="citycustom" key="cityCustomMain.area"/>*
        </td>
        <td class="content">
            <eoms:comboBox name="area" id="area"
                           initDicId="1010107"
                           defaultValue="${sheetMain.area}" alt="allowBlank:false"/>
        </td>
        <td class="label">
            <!-- 带宽 -->
            <bean:message bundle="citycustom" key="cityCustomMain.bandWidth"/>(M)*
        </td>
        <td class="content">
            <input type="text" class="text" name="bandWidth" id="bandWidth"
                   alt="allowBlank:false,maxLength:500,vtext:'请填入 带宽 信息，最多输入 500 字符'" value="${sheetMain.bandWidth}"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 专线数量 -->
            <bean:message bundle="citycustom" key="cityCustomMain.lineNum"/>*
        </td>
        <td class="content">
            <input type="text" class="text" name="lineNum" id="lineNum"
                   alt="allowBlank:false,maxLength:500,vtext:'请填入 专线数量 信息，最多输入 500 字符'" value="${sheetMain.lineNum}"/>
        </td>
        <td class="label">
            <!-- A端地市 -->
            <bean:message bundle="citycustom" key="cityCustomMain.atypearea"/>*
        </td>
        <td class="content">
            <eoms:comboBox name="atypearea" id="atypearea"
                           initDicId="1010107"
                           defaultValue="${sheetMain.atypearea}" alt="allowBlank:false"/>
        </td>
    </tr>

    <tr>
        <td class="label">Z端地市*</td>
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
                    var data = "[<c:forTokens items="${sheetMain.ztypearea}" delims="," var="ztypearea" varStatus="status">{id:'${ztypearea}',name:'<eoms:id2nameDB id="${ztypearea}" beanId="ItawSystemDictTypeDao"/>',nodeType:'dict'}<c:choose><c:when test="${status.last}"></c:when><c:otherwise>,</c:otherwise></c:choose></c:forTokens>]"
                    factoryViewer.jsonData = eoms.JSONDecode(data);
                    factoryViewer.refresh();

                    var factoryTreeAction = '${app}/xtree.do?method=dict';
                    factoryTree = new xbox({
                        btnId: '${sheetPageName}showFactory',
                        dlgId: 'dlg3',
                        treeDataUrl: factoryTreeAction,
                        treeRootId: '1010107',
                        treeRootText: 'Z端地市',
                        treeChkMode: '',
                        treeChkType: 'dict',
                        showChkFldId: '${sheetPageName}showFactory',
                        saveChkFldId: '${sheetPageName}ztypearea',
                        viewer: factoryViewer
                    });
                });
            </script>
            <textarea class="textarea max" readonly="readonly" name="${sheetPageName}showFactory" style="height:50px"
                      id="${sheetPageName}showFactory"
                      alt="allowBlank:true,maxLength:250,vtext:'请填入HLR资源类型，最多输入125个汉字'"><c:forTokens
                    items="${sheetMain.ztypearea}" delims="," var="ztypearea" varStatus="status"><eoms:id2nameDB
                    id="${ztypearea}" beanId="ItawSystemDictTypeDao"/><c:choose><c:when
                    test="${status.last}"></c:when><c:otherwise>,</c:otherwise></c:choose></c:forTokens></textarea>
            <input type="hidden" name="ztypearea" id="ztypearea" value="${sheetMain.ztypearea}"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 客户服务等级 -->
            <bean:message bundle="citycustom" key="cityCustomMain.customGrade"/>*
        </td>
        <td class="content">
            <eoms:comboBox name="customGrade" id="customGrade"
                           initDicId="1012103"
                           defaultValue="${sheetMain.customGrade}" alt="allowBlank:false"/>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 任务描述 -->
            任务描述*
        </td>
        <td colspan="3" class="content">
    			<textarea class="textarea max" name="mainTaskDescription" id="mainTaskDescription"
                          alt="allowBlank:false,maxLength:2000,vtext:'请输入任务描述，多输入100汉字'">${sheetMain.mainTaskDescription}</textarea>
        </td>
    </tr>

    <tr>
        <td class="label">
            <!-- 资源确认汇总表 -->
            <bean:message bundle="citycustom" key="cityCustomMain.resourceReport"/>*
        </td>
        <td class="content">
            <eoms:attachment name="sheetMain" property="resourceReport" scope="request" idField="resourceReport"
                             appCode="citycustom"/>

        </td>
    </tr>

</table>


<!-- 附件 -->
<table id="sheet" class="formTable">
    <!--附件模板-->
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
            协议或合同
        </td>
        <td colspan="3">
            <eoms:attachment name="sheetMain" property="sheetAccessories"
                             scope="request" idField="sheetAccessories" appCode="citycustom" alt="allowBlank:true"/>
        </td>
    </tr>
</table>


<!--派单树-->
<br/>
<fieldset>
    <legend>
        <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>：
        <span id="roleName">
		 	 省集客审核人
		 </span>
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="sendObject" type="role" roleId="1921" flowId="614" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sheetMain.sendObject}"/>
    </div>
</fieldset>