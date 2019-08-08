<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
    request.setAttribute("roleId", "339");
    long localTimes = com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
%>
<%@ include file="/WEB-INF/pages/wfworksheet/itsoftchange/baseinputmainhtmlnew.jsp" %>


<input type="hidden" name="${sheetPageName}processTemplateName" value="ITSoftChangeProcess"/>
<input type="hidden" name="${sheetPageName}operateName" value="newWork"/>
<input type="hidden" name="${sheetPageName}sheetTemplateName" value="ITSoftChangeProcess"/>
<c:if test="${status==0}">
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OperateTask"/>
    <input type="hidden" id="${sheetPageName}operateType" name="${sheetPageName}operateType"
           value="${sheetPageName}operateType"/>
</c:if>
<c:if test="${status==1}">
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverTask"/>
</c:if>
<input type="hidden" name="${sheetPageName}beanId" value="iITSoftChangeMainManager"/>
<input type="hidden" name="${sheetPageName}mainClassName"
       value="com.boco.eoms.sheet.itsoftchange.model.ITSoftChangeMain"/>
<input type="hidden" name="${sheetPageName}linkClassName"
       value="com.boco.eoms.sheet.itsoftchange.model.ITSoftChangeLink"/>
<!-- sheet info -->
<br/>
<table id=sheet class="formTable">
    <c:if test="${status!=1}">
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
            <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.mainITRequirementID"/></td>
            <td class="content"><input type="text" class="text" name="${sheetPageName}mainITRequirementID"
                                       value="${sheetMain.mainIsReferCost}" id="${sheetPageName}mainITRequirementID"
                                       alt="allowBlank:true,maxLength:50,vtext:'请相关IT需求申请工单号，最多输入25字'"/></td>
            <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.mainIsReferCost"/>*</td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}mainIsReferCost" id="${sheetPageName}mainIsReferCost"
                               initDicId="1011405" alt="allowBlank:false" defaultValue="${sheetMain.mainIsReferCost}"
                               styleClass="select-class"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.mainNetSystem"/>*</td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}mainNetSystem" id="${sheetPageName}mainNetSystem"
                               initDicId="1011404" alt="allowBlank:false" defaultValue="${sheetMain.mainNetSystem}"
                               styleClass="select-class"/>
            </td>
            <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.mainCompleteTime"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}mainCompleteTime" readonly="readonly"
                       id="${sheetPageName}mainCompleteTime" value="${eoms:date2String(sheetMain.mainCompleteTime)}"
                       onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>

            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.mainApplyer"/>*</td>
            <!--   <td  colspan="3"> <input type="text"  class="text" name="${sheetPageName}mainApplyer" value="${sheetMain.mainApplyer}" id="${sheetPageName}mainApplyer"  alt="allowBlank:false,maxLength:50,vtext:'请填入需求申请人，最多输入25字'"/></td>-->
            <td colspan='3'>
                <div id="areaview" class="hide"></div>
                <script type="text/javascript">
                    //viewer
                    var areaViewer = new Ext.JsonView("areaview",
                        '<div class="viewlistitem-{nodeType}">{name}</div>',
                        {
                            emptyText: '<div>没有选择项目</div>'
                        }
                    );
                    var data = "[{id:'${sheetMain.mainApplyer}',name:'<eoms:id2nameDB id='${sheetMain.mainApplyer}' beanId='tawSystemUserDao'/>',nodeType:'user'}]";
                    areaViewer.jsonData = eoms.JSONDecode(data);
                    areaViewer.refresh();

                    //area tree
                    var deptTreeAction = '${app}/xtree.do?method=userFromDept';
                    deptetree = new xbox({

                        btnId: '${sheetPageName}showDept',
                        dlgId: 'dlg3',

                        treeDataUrl: deptTreeAction,
                        treeRootId: '-1',
                        treeRootText: '人员',
                        treeChkMode: 'single',
                        treeChkType: 'user',
                        showChkFldId: '${sheetPageName}showDept',
                        saveChkFldId: '${sheetPageName}mainApplyer',
                        viewer: areaViewer
                    });
                </script>
                <input type="text" class="text" readonly="readonly" name="${sheetPageName}showDept"
                       id="${sheetPageName}showDept" alt="allowBlank:false,vtext:'请选择人员'"
                       value="<eoms:id2nameDB id="${sheetPageName}showDept" beanId='tawSystemUserDao'/>"/>
                <input type="hidden" name="${sheetPageName}mainApplyer" id="${sheetPageName}mainApplyer"
                       value="${sheetMain.mainApplyer}"/>
            </td>


        </tr>
        <tr>
            <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.mainChangeDesc"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}mainChangeDesc" id="${sheetPageName}mainChangeDesc"
                          alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入变更概述，最多输入125字'">${sheetMain.mainChangeDesc}</textarea>
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
            <td class="label"><bean:message bundle="itsoftchange" key="itsoftchange.mainChangeDetail"/>*</td>
            <td colspan='3'>
                <eoms:attachment name="sheetMain" property="mainChangeDetail"
                                 scope="request" idField="${sheetPageName}mainChangeDetail" appCode="itsoftchangesheet"
                                 alt="allowBlank:false"/>
            </td>
        </tr>

    </c:if>
</table>
<br/>
<c:if test="${status!=1}">
    <fieldset id="link1">
        <legend>
            <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
            <bean:message bundle="itsoftchange" key="role.operategroup"/>
            </span>
        </legend>
        <eoms:chooser id="test1" type="role" roleId="341" flowId="092" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',allowBlank:false,text:'派发',vtext:'请选择派发对象'}]"
        />
    </fieldset>
    <fieldset id="link2">
        <legend>
            抄送角色：<bean:message bundle="itsoftchange" key="role.applyer"/>
        </legend>
        <eoms:chooser id="test2" type="role" roleId="340" flowId="092" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'copyPerformer',childType:'user,subrole',allowBlank:false,text:'抄送',vtext:'请选择抄送对象'}]"
                      data="${copyUserAndRoles}"
        />
    </fieldset>

</c:if>
<script type="text/javascript">
    getparentsheetname();

    function getparentsheetname() {
        if ('${parentProcessName}' != null && '${parentProcessName}' != '') {
            document.all.${sheetPageName}mainITRequirementID.value = '${parentSheetId}';
            document.all.${sheetPageName}mainNetSystem.value = '${mainNetSystem}';
            document.all.${sheetPageName}mainChangeDesc.value = '${mainRequirementDesc}';
            // document.all.${sheetPageName}showDept.value='${tmpsendUserId}';


        }
    }

    selectLimit();

    function selectLimit() {
        Ext.Ajax.request({
            method: "get",
            url: "itsoftchange.do?method=newShowLimit&flowName=ITSoftChangeProcess",
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
</script>
