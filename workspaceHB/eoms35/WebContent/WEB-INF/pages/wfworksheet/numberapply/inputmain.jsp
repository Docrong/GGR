<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>
<%
    long localTimes = com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
%>
<% BaseMain basemain = (BaseMain) request.getAttribute("sheetMain");
    String sheetKey = basemain.getId();
    System.out.println("sheetKey === " + sheetKey);
%>
<%@ include file="/WEB-INF/pages/wfworksheet/numberapply/baseinputmainhtmlnew.jsp" %>
<script type="text/javascript">
    //selectLimit();
    function selectLimit() {
        Ext.Ajax.request({
            method: "get",
            url: "numberapply.do?method=newShowLimit&flowName=NumberApply",
            success: function (x) {
                var o = eoms.JSONDecode(x.responseText);
                if ((o.acceptLimit == null || o.acceptLimit == "") && (o.replyLimit == null || o.replyLimit == "")) {
                    // $("sheetAcceptLimit").value = "";
                    // $('sheetCompleteLimit').value = "";
                } else {
                    var times =<%=localTimes%>;
                    var dt1 = new Date().add(Date.MINUTE, parseInt(o.acceptLimit, 10));
                    var dt2 = dt1.add(Date.MINUTE, parseInt(o.replyLimit, 10));
                    $("sheetAcceptLimit").value = dt1.format('Y-m-d H:i:s');
                    $('sheetCompleteLimit').value = dt2.format('Y-m-d H:i:s');
                }
            }
        });
    }
</script>
<input type="hidden" name="processTemplateName" value="NumberApply"/>
<input type="hidden" name="operateName" value="newWorkSheet"/>
<c:if test="${status!=1}">


    <input type="hidden" name="phaseId" id="phaseId" value="PermitTask"/>

    <input type="hidden" id="operateType" name="operateType" value="0"/>
    <input type="hidden" name="gatherPhaseId" id="gatherPhaseId" value="HoldTask"/>
</c:if>
<c:if test="${status==1}">
    <input type="hidden" name="phaseId" id="phaseId" value="OverTask"/>
</c:if>
<input type="hidden" name="beanId" value="iNumberApplyMainManager"/>
<input type="hidden" name="mainClassName" value="com.boco.eoms.sheet.numberapply.model.NumberApplyMain"/>
<input type="hidden" name="linkClassName" value="com.boco.eoms.sheet.numberapply.model.NumberApplyLink"/>
<br>
<!-- 工单基本信息 -->
<table id="sheet" class="formTable">
    <tr>
        <td class="label">受理时限*</td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly"
                   id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"
                   onclick="popUpCalendar(this, this)"
                   alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"/>

        </td>
        <td class="label">处理时限*</td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly"
                   id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"
                   onclick="popUpCalendar(this, this)"
                   alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="numberapply" key="numberApplyMain.mainResourceType"/>*</td>
        <td colspan="3">
            <!-- HLR和MSC分类的字典值 -->
            <eoms:comboBox name="${sheetPageName}mainResourceType" id="${sheetPageName}mainResourceType"
                           initDicId="1013402" alt="allowBlank:false" styleClass="select-class"
                           defaultValue="${sheetMain.mainResourceType}"
                           onchange=" return  ifAuditPass(this.value);"/>
            <input type="hidden" name="actionForword" id="actionForword" value=""/>
        </td>
        <script type="text/javascript">
            function ifAuditPass(obj) {
                if (obj == "101340201") {  //申请类型为“HLR”
                    $('actionForword').value = "hlrnew";
                    eoms.form.enableArea('notpassreasion');
                    eoms.form.disableArea('passreasion', true);
                    return true;
                }
                if (obj == "101340202") {  //申请类型为“MSC”
                    $('actionForword').value = "mscnew";
                    eoms.form.enableArea('passreasion');
                    eoms.form.disableArea('notpassreasion', true);
                    return true;
                }
            }

        </script>
    <tr>
        <!-- 分类为HLR的字典值 -->
        <tbody id='notpassreasion' style="display:
        <c:choose>
        <c:when test="${sheetMain.mainResourceType == '101340201'}">block</c:when>
        <c:otherwise>none</c:otherwise></c:choose>">
        <td class="label"><bean:message bundle="numberapply" key="numberApplyMain.mainHLRResource"/>*</td>
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
                    var data = "[<c:forTokens items="${sheetMain.mainHLRResource}" delims="," var="mainHLRResource" varStatus="status">{id:'${mainHLRResource}',name:'<eoms:id2nameDB id="${mainHLRResource}" beanId="ItawSystemDictTypeDao"/>',nodeType:'dict'}<c:choose><c:when test="${status.last}"></c:when><c:otherwise>,</c:otherwise></c:choose></c:forTokens>]"
                    factoryViewer.jsonData = eoms.JSONDecode(data);
                    factoryViewer.refresh();

                    var factoryTreeAction = '${app}/xtree.do?method=dict';
                    factoryTree = new xbox({
                        btnId: '${sheetPageName}showFactory',
                        dlgId: 'dlg3',
                        treeDataUrl: factoryTreeAction,
                        treeRootId: '1013401',
                        treeRootText: 'HLR资源类型',
                        treeChkMode: '',
                        treeChkType: 'dict',
                        showChkFldId: '${sheetPageName}showFactory',
                        saveChkFldId: '${sheetPageName}mainHLRResource',
                        viewer: factoryViewer
                    });
                });
            </script>
            <textarea class="textarea max" readonly="readonly" name="${sheetPageName}showFactory" style="height:50px"
                      id="${sheetPageName}showFactory"
                      alt="allowBlank:true,maxLength:250,vtext:'请填入HLR资源类型，最多输入125个汉字'"><c:forTokens
                    items="${sheetMain.mainHLRResource}" delims="," var="mainHLRResource"
                    varStatus="status"><eoms:id2nameDB id="${mainHLRResource}"
                                                       beanId="ItawSystemDictTypeDao"/><c:choose><c:when
                    test="${status.last}"></c:when><c:otherwise>,</c:otherwise></c:choose></c:forTokens></textarea>
            <input type="hidden" name="${sheetPageName}mainHLRResource" id="${sheetPageName}mainHLRResource"
                   value="${sheetMain.mainHLRResource}"/>
        </td>
        </tbody>
        <!-- 分类为MSC的字典值 -->
        <tbody id='passreasion' style="display:
        <c:choose>
        <c:when test="${sheetMain.mainResourceType == '101340202'}">block</c:when>
        <c:otherwise>none</c:otherwise></c:choose>">
        <td class="label"><bean:message bundle="numberapply" key="numberApplyMain.mainMSCResource"/>*</td>
        <td colspan="3">
            <div id="factoryview1" class="hide"></div>
            <script type="text/javascript">
                Ext.onReady(function () {
                    //viewer
                    var factoryViewer1 = new Ext.JsonView("factoryview1",
                        '<div class="viewlistitem-{nodeType}">{name}</div>',
                        {
                            emptyText: '<div>没有选择项目</div>'
                        }
                    );
                    var data = "[<c:forTokens items="${sheetMain.mainMSCResource}" delims="," var="mainHLRResource" varStatus="status">{id:'${mainHLRResource}',name:'<eoms:id2nameDB id="${mainHLRResource}" beanId="ItawSystemDictTypeDao"/>',nodeType:'dict'}<c:choose><c:when test="${status.last}"></c:when><c:otherwise>,</c:otherwise></c:choose></c:forTokens>]"
                    factoryViewer1.jsonData = eoms.JSONDecode(data);
                    factoryViewer1.refresh();

                    //area tree
                    var factoryTreeAction = '${app}/xtree.do?method=dict';
                    factoryTree = new xbox({

                        btnId: '${sheetPageName}showFactory2',

                        treeDataUrl: factoryTreeAction,
                        treeRootId: '1013403',
                        treeRootText: 'MSC、GMSC资源类型',
                        treeChkMode: '',
                        treeChkType: 'dict',
                        showChkFldId: '${sheetPageName}showFactory2',
                        saveChkFldId: '${sheetPageName}mainMSCResource',
                        viewer: factoryViewer1
                    });
                });
            </script>
            <textarea class="textarea max" readonly="readonly" name="${sheetPageName}showFactory2" style="height:50px"
                      id="${sheetPageName}showFactory2"
                      alt="allowBlank:true,maxLength:250,vtext:'请填入MSC、GMSC资源类型，最多输入125个汉字'"><c:forTokens
                    items="${sheetMain.mainMSCResource}" delims="," var="mainMSCResource"
                    varStatus="status"><eoms:id2nameDB id="${mainMSCResource}"
                                                       beanId="ItawSystemDictTypeDao"/><c:choose><c:when
                    test="${status.last}"></c:when><c:otherwise>,</c:otherwise></c:choose></c:forTokens></textarea>
            <input type="hidden" name="${sheetPageName}mainMSCResource" id="${sheetPageName}mainMSCResource"
                   value="${sheetMain.mainMSCResource}"/>
        </td>
        </tbody>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="numberapply" key="numberApplyMain.mainContentDescribe"/>*</td>
        <td colspan="3">
            <textarea class="textarea max" name="${sheetPageName}mainContentDescribe"
                      id="${sheetPageName}mainContentDescribe"
                      alt="allowBlank:false,maxLength:500,vtext:'请输入申请内容描述，最大长度为255个汉字'">${sheetMain.mainContentDescribe}</textarea>
        </td>
    </tr>
    <script type="text/javascript">

        function onOneAdd() {
            if ($('mainResourceType').value == '') {
                alert("请选择申请资源类型");
                return false;
            }
            var forward = "";

//if('${taskName}' == 'DraftTask'||'${taskName}' == 'RejectTask'){
            //申请分类为HLR的字典值
            if (${sheetMain.mainResourceType == '101340201'}) {
                $('actionForword').value = 'hlrnew';
            }
            //申请分类为MSC的字典值
            if (${sheetMain.mainResourceType == '101340202'}) {
                $('actionForword').value = 'mscnew';
            }
//}
            forward = $('actionForword').value;
            var url = "${app}/sheet/numberapply/numberapply.do?method=showOneAdd&actionForword=" + forward + "&sheetKey=${sheetMain.id}";
            window.open(url);
            return true;
        }

        function onBatchAdd() {
            if ($('mainResourceType').value == '') {
                alert("请选择申请资源类型");
                return false;
            }
            var forward = "";
//if('${taskName}' == 'DraftTask'||'${taskName}' == 'RejectTask'){
            //申请分类为HLR的字典值
            if (${sheetMain.mainResourceType == '101340201'}) {
                $('actionForword').value = 'hlrnew';
            }
            //申请分类为MSC的字典值
            if (${sheetMain.mainResourceType == '101340202'}) {
                $('actionForword').value = 'mscnew';
            }
//}
            forward = $('actionForword').value;
            var url = "${app}/sheet/numberapply/numberapply.do?method=performBatchAdd&actionForword=" + forward + "&sheetKey=${sheetMain.id}";
            window.open(url);
            return true;
        }
    </script>

    <tr>
        <td width="200px">
            <input type="button" value="单条新增" Onclick=" return onOneAdd();" class="button">
            <input type="button" value="批量导入" Onclick=" return onBatchAdd();" class="button">
        </td>
    </tr>
    <tr>
        <td colspan="4">
            <iframe id="frame1" src="" width="100%" height="300px" style="display:none"></iframe>
        </td>
    </tr>

</table>
<script type="text/javascript">
    if ('${taskName}' == 'DraftTask' || '${taskName}' == 'RejectTask') {
        var forward = "";
        //申请分类为HLR的字典值
        if (${sheetMain.mainResourceType == '101340201'}) {
            forward = 'hlrlist';
        }
        //申请分类为MSC的字典值
        if (${sheetMain.mainResourceType == '101340202'}) {
            forward = 'msclist';
        }

        var frames = $('frame1');
        frames.style.display = "block";
        frames.src = "${app}/sheet/numberapply/numberapply.do?method=showList&sheetKey=${sheetMain.id}&actionForword=" + forward + "&stylepage=new";
    }

</script>


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
            <bean:message bundle="sheet" key="mainForm.accessories"/>
        </td>
        <td colspan="3">
            <eoms:attachment name="sheetMain" property="sheetAccessories"
                             scope="request" idField="sheetAccessories" appCode="numberapply" alt="allowBlank:true"/>
        </td>
    </tr>
</table>


<!--派单树-->
<br/>
<fieldset>
    <legend> 派往组织：资源管理员</legend>
    <div class="x-form-item">
        <eoms:chooser id="sendObject" type="dept" roleId="4051" flowId="93" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sheetMain.sendObject}"/>
    </div>
</fieldset>