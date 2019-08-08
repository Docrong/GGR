<%@ page language="java" pageEncoding="UTF-8"
         contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%
    request.setAttribute("roleId", "199601");

    long localTimes = com.boco.eoms.base.util.StaticMethod
            .getLocalTime().getTime();
%>
<%@ include
        file="/WEB-INF/pages/wfworksheet/daiweiindexreduction/baseinputmainhtmlnew.jsp" %>
<script type="text/javascript">
    //selectLimit();
    function selectLimit() {
        Ext.Ajax.request({
            method: "get",
            url: "daiweiindexreduction.do?method=newShowLimit&flowName=DaiWeiIndexReduction",
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

    var v1 = eoms.form;

    function selectRoleId(val) {  // 选择子角色
        var url = "${app}/sheet/daiweiindexreduction/daiweiindexreduction.do?method=getSubRole&mainSubType=" + val;
        Ext.Ajax.request({
            url: url,
            method: "post",
            success: function (x) {
                var result = eoms.JSONDecode(x.responseText);
                if (result.subRoleId != '') {
                    v1.enableArea('div2');//生效
                    v1.disableArea('div1', true);//失效
                    document.getElementById("dealPerformerLeader1").value = result.subRoleId;
                    document.getElementById("dealPerformer1").value = result.subRoleId;
                    document.getElementById("dealPerformerType1").value = result.type;
                    document.getElementById("subRoleName").innerHTML = result.subRoleName;
                } else {
                    v1.enableArea('div1');//生效
                    v1.disableArea('div2', true);//失效
                }
            }
        });

    }


    //add by lyg
    function selectSubRole(val) { // 选择子角色
        if (val != null && val != null) {
            var url = "${app}/sheet/daiweiindexreduction/daiweiindexreduction.do?method=getSubRoleId&mainSubtractReason=" + val;
            Ext.Ajax.request({
                url: url,
                method: "post",
                success: function (x) {
                    var result = eoms.JSONDecode(x.responseText);
                    if (result.subRoleId != '') {
                        v1.enableArea('div2');//生效
                        v1.disableArea('div1', true);//失效
                        document.getElementById("dealPerformerLeader1").value = result.subRoleId;
                        document.getElementById("dealPerformer1").value = result.subRoleId;
                        document.getElementById("dealPerformerType1").value = result.type;
                        document.getElementById("subRoleName").innerHTML = result.subRoleName;
                    } else {
                        v1.enableArea('div1');//生效
                        v1.disableArea('div2', true);//失效
                    }
                }
            });
        }


    }
</script>
<input type="hidden" name="processTemplateName"
       value="DaiWeiIndexReduction"/>
<input type="hidden" name="operateName" value="newWorkSheet"/>
<c:if test="${status!=1}">
    <input type="hidden" name="phaseId" id="phaseId" value="TrialTask"/>

    <input type="hidden" id="operateType" name="operateType" value="0"/>
    <input type="hidden" name="gatherPhaseId" id="gatherPhaseId"
           value="HoldTask"/>
</c:if>
<c:if test="${status==1}">
    <input type="hidden" name="phaseId" id="phaseId" value="OverTask"/>
</c:if>
<input type="hidden" name="beanId"
       value="iDaiWeiIndexReductionMainManager"/>
<input type="hidden" name="mainClassName"
       value="com.boco.eoms.sheet.daiweiindexreduction.model.DaiWeiIndexReductionMain"/>
<input type="hidden" name="linkClassName"
       value="com.boco.eoms.sheet.daiweiindexreduction.model.DaiWeiIndexReductionLink"/>
<input type="hidden" id="listsize" name="listsize"/> <!-- 隐藏 listsize -->
<input type="hidden" id="noPass" name="noPass"/>
<br>

<!-- 工单基本信息 -->
<input type="hidden" id="mainReserveA" name="mainReserveA" value="${sheetMain.mainReserveA }"/>
<table id="sheet" class="formTable">


    <c:choose>
        <c:when test="${newFlag == 'newFlag' || sheetMain.newFlag == 'newFlag'}">
            <tr>

                <td class="label">核减专业 *</td>
                <td class="content"><eoms:comboBox
                        name="mainSubtractProfessional" id="mainSubtractProfessional"
                        defaultValue="${sheetMain.mainSubtractProfessional}"
                        sub="mainSubtractIndexName"
                        initDicId="10144" alt="allowBlank:false"/>
                </td>
                <td class="label">核减指标类型*</td>
                <td class="content">
                    <eoms:comboBox
                            name="mainSubtractIndexName" id="mainSubtractIndexName"
                            defaultValue="${sheetMain.mainSubtractIndexName}"
                            sub="mainSubtractReason"
                            alt="allowBlank:false"/>
                </td>
            </tr>
            <tr>
                <td class="label">核减原因模板*</td>
                <input type="hidden" id="newFlag" name="newFlag" value="${newFlag }"/>
                <td class="content">
                    <eoms:comboBox onchange="selectSubRole(this.value);"
                                   name="mainSubtractReason" id="mainSubtractReason"
                                   defaultValue="${sheetMain.mainSubtractReason}"
                                   alt="allowBlank:false"/>
                </td>

                <td class="label">地市*</span></td>
                <td class="content">
                    <select name="mainCity" id="mainCity" class="input select" alt="allowBlank:false,vtext:'请选择所在地市'">
                        <option value="">

                        </option>
                        <logic:iterate id="cityList" name="cityList">
                            <option value="${cityList.keyId}"
                                    <c:if test="${sheetMain.mainCity==cityList.keyId}">selected="selected"</c:if>>${cityList.keyName}</option>
                        </logic:iterate>
                    </select>
                </td>

            </tr>
        </c:when>
        <c:otherwise>
            <tr>
                <td class="label"><!-- 核减指标名称 --> <bean:message
                        bundle="daiweiindexreduction"
                        key="daiWeiIndexReductionMain.mainSubtractIndexName"/>*
                </td>
                <td class="content"><input type="text" class="text" name="mainSubtractIndexName"
                                           id="mainSubtractIndexName"
                                           alt="allowBlank:false,maxLength:20,vtext:'请填入 核减指标名称 信息，最多输入 20 字符'"
                                           value="${sheetMain.mainSubtractIndexName}"/></td>
                <td class="label"><!-- 核减专业 -->
                    <bean:message
                            bundle="daiweiindexreduction"
                            key="daiWeiIndexReductionMain.mainSubtractProfessional"/>*
                </td>
                <td class="content"><eoms:comboBox
                        name="mainSubtractProfessional" id="mainSubtractProfessional"
                        defaultValue="${sheetMain.mainSubtractProfessional}"
                        initDicId="1014201" alt="allowBlank:false";/>
                </td>
            </tr>
        </c:otherwise>

    </c:choose>


    <tr>
        <td class="label">申请核减时间*</td>
        <td class="content">
            <input type="text" class="text" name="mainSubtractTime"
                   id="mainSubtractTime" value="${eoms:date2String(sheetMain.mainSubtractTime)}"
                   alt="allowBlank:false" readonly="readonly"/></td>


        <td class="label">受理时限*</td>
        <td class="content">
            <input type="text" class="text" name="sheetAcceptLimit"
                   id="sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"
                   alt="allowBlank:false" readonly="readonly"/></td>
    </tr>
    <tr>
        <td class="label">处理时限*</td>
        <td class="content">
            <input type="text" class="text" name="sheetCompleteLimit"
                   id="sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"
                   alt="allowBlank:false" readonly="readonly"/></td>

        <td class="label">初审处理时限*</td>
        <td class="content">
            <input type="text" class="text" name="mainProcessTime"
                   id="mainProcessTime" value="${eoms:date2String(sheetMain.mainProcessTime)}"
                   alt="allowBlank:false" readonly="readonly"/></td>

    </tr>
    <tr>
        <td class="label"><!-- 核减信息确认人 -->
            <bean:message
                    bundle="daiweiindexreduction"
                    key="daiWeiIndexReductionMain.mainConfirmingPerson"/></td>
        <td class="content"><input type="text" class="text" name="mainConfirmingPerson"
                                   id="mainConfirmingPerson"
                                   alt="allowBlank:true,maxLength:20,vtext:'请填入 核减信息确认人 信息，最多输入 20 字符'"
                                   value="${sheetMain.mainConfirmingPerson}"/></td>
        <td class="label"><!-- 核减信息确认人电话 -->
            <bean:message
                    bundle="daiweiindexreduction"
                    key="daiWeiIndexReductionMain.mainConfirmingTelephone"/></td>
        <td class="content"><input type="text" class="text"
                                   name="mainConfirmingTelephone" id="mainConfirmingTelephone"
                                   alt="allowBlank:true,maxLength:20,vtext:'请填入 核减信息确认人电话 信息，最多输入 20 字符'"
                                   value="${sheetMain.mainConfirmingTelephone}"/></td>
    </tr>

    <c:choose>
        <c:when test="${newFlag == 'newFlag' || sheetMain.newFlag == 'newFlag'}">
            <tr style="height:35%;">
                <td class="label">核减内容*</td>
                <td colspan="3">
                    <eoms:attachment name="sheetMain" alt="allowBlank:false"
                                     property="" scope="request"
                                     idField="sheetAccessories" appCode="daiweiindexreduction"/>
                </td>
            </tr>
        </c:when>
        <c:otherwise>
            <!-- 核减内容Excel表 -->
            <c:choose>
                <c:when test="${operateType == '54' }">
                    <tr style="height:25%;">
                        <td class="label">核减内容*</td>
                        <td style="height:30%;" colspan="3"><eoms:attachmentDw scope="request"
                                                                               idField="sheetAccessories"
                                                                               name="sheetMain"
                                                                               property=""
                                                                               appCode="daiweiindexreduction"
                                                                               sheetFlag="renew"
                                                                               alt="allowBlank:true"/></td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr style="height:25%;">
                        <td class="label">核减内容*</td>
                        <td style="height:30%;" colspan="3"><eoms:attachmentDw scope="request"
                                                                               idField="sheetAccessories"
                                                                               name="sheetMain"
                                                                               property=""
                                                                               appCode="daiweiindexreduction"
                                                                               sheetFlag="new"
                                                                               alt="allowBlank:true"/></td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </c:otherwise>


    </c:choose>


    <!-- 隐藏一个iframe的 入数据信息列表 -->
    <c:if test="${operateType == null || operateType == '' }">
        <tr id="importInforList" style="display:none;">
            <td colspan="4">
                <iframe src="" scrolling="auto" width="100%"
                        id="iframepage"></iframe>
            </td>
        </tr>
    </c:if>

    <tr>
        <td class="label"><!-- 说明 -->
            <bean:message
                    bundle="daiweiindexreduction"
                    key="daiWeiIndexReductionMain.mainIllustrate"/></td>
        <td colspan="3"><textarea class="textarea max"
                                  name="mainIllustrate" id="mainIllustrate"
                                  alt="allowBlank:true,maxLength:500,vtext:'请填入 说明 信息，最多输入 500 字符'">${sheetMain.mainIllustrate}</textarea>
    </tr>

</table>

<!-- 附件 -->
<table id="sheet" class="formTable">
    <tr>
        <td class="label">核减依据*</td>
        <td colspan="3"><eoms:attachment name="sheetMain"
                                         property="sheetAccessories" scope="request"
                                         idField="sheetAccessories" appCode="daiweiindexreduction"
                                         alt="allowBlank:false"/></td>
    </tr>
</table>

<!-- 重派页面 -->
<c:if test="${operateType == '54' }">
    <table class="formTable">
        <tr id="importInforList">
            <td colspan="4">
                <iframe src="daiweiindexreduction.do?method=search&ifShowOther=no&ifSend=no&refSheetId=${sheetMain.mainReserveA }&scrolling="
                        auto
                " width="100%"
                id="iframepage"></iframe></td>
        </tr>
    </table>
    <br/>
    <div id="btn" align="right">
        <input type="button" class="btn" onclick="exportList();" value="导出未通过核减列表"/>
    </div>
    <script type="text/javascript">
        function exportList() {
            var url = 'daiweiindexreduction.do?method=exportList&refSheetId=${sheetMain.mainReserveA }';
            window.location.href = url;
        }
    </script>
</c:if>

<!--派单树-->
<br/>
<div id="div1">
    <fieldset>
        <legend><bean:message bundle="sheet"
                              key="mainForm.toOperateRoleName"/>： <span id="roleName"> 初审人 </span></legend>
        <div class="x-form-item"><eoms:chooser id="sendObject"
                                               type="role" roleId="199602" flowId="1996"
                                               config="{returnJSON:true,showLeader:true}"
                                               category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                                               data="${sheetMain.sendObject}"/></div>
    </fieldset>
</div>
<div id="div2">
    <fieldset>
        <legend><bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>: 初审人</legend>
        <input name="dealPerformerLeader" id="dealPerformerLeader1" type="hidden" value=""
               alt="allowBlank:false,vtext:'未匹配到派往对象'"/>
        <input name="dealPerformer" id="dealPerformer1" value="" type="hidden"/>
        <input name="dealPerformerType" id="dealPerformerType1" value="" type="hidden"/>
        <span id="subRoleName"></span>
    </fieldset>
</div>
<script type="text/javascript">


    Ext.onReady(function () {
        var val = document.getElementById("mainSubtractProfessional").value;
        selectRoleId();
    });

</script>   