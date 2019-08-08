<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
    request.setAttribute("roleId", "361");
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    long localTimes = com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
%>
<script type="text/javascript">
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
            url: "netOptimize.do?method=newShowLimit&specialty1=" + temp1 + "&specialty2=" + temp2 + "&specialty3=" + temp3 + "&flowName=NetOptimizeProcess",
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
<%@ include file="/WEB-INF/pages/wfworksheet/netOptimize/baseinputmainhtmlnew.jsp" %>

<input type="hidden" name="${sheetPageName}processTemplateName" value="NetOptimizeProcess"/>
<input type="hidden" name="${sheetPageName}sheetTemplateName" value="NetOptimizeProcess"/>
<input type="hidden" name="${sheetPageName}operateName" value="newSheet"/>
<c:if test="${status==0}">
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AcceptTask"/>
</c:if>

<input type="hidden" name="${sheetPageName}beanId" value="iNetOptimizeMainManager"/>
<input type="hidden" name="${sheetPageName}mainClassName"
       value="com.boco.eoms.sheet.netoptimize.model.NetOptimizeMain"/> <!--main表Model对象类名-->
<input type="hidden" name="${sheetPageName}linkClassName"
       value="com.boco.eoms.sheet.netoptimize.model.NetOptimizeLink"/> <!--link表Model对象类名-->

<!-- sheet info -->
<input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="0"/>


<br/>
<!-- 工单主单信息 -->
<table id="sheet" class="formTable">
    <c:if test="${status!=1}">
        <tr>
            <td class="label"><bean:message bundle="netOptimize" key="netOptimize.sheetAcceptLimit"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly"
                       id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"
                       alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'受理时限不能晚于处理时限',allowBlank:false"
                       onclick="popUpCalendar(this, this)"/>

            </td>
            <td class="label"><bean:message bundle="netOptimize" key="netOptimize.sheetCompleteLimit"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly"
                       id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"
                       onclick="popUpCalendar(this, this)"
                       alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'完成时限不能早于受理时限',allowBlank:false"/>

            </td>
        </tr>


        <tr>
            <td class="label"><bean:message bundle="netOptimize" key="netOptimize.mainNetSortOne"/>*</td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}mainNetSortOne" id="${sheetPageName}mainNetSortOne"
                               sub="${sheetPageName}mainNetSortTwo" initDicId="1010104"
                               defaultValue="${sheetMain.mainNetSortOne}" alt="allowBlank:false"
                               onchange="selectLimit(this.value);"/>
            </td>
            <td class="label"><bean:message bundle="netOptimize" key="netOptimize.mainNetSortTwo"/>*</td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}mainNetSortTwo" id="${sheetPageName}mainNetSortTwo"
                               sub="${sheetPageName}mainNetSortThree" initDicId="${sheetMain.mainNetSortOne}"
                               defaultValue="${sheetMain.mainNetSortTwo}" alt="allowBlank:false"
                               onchange="selectLimit(this.value);"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="netOptimize" key="netOptimize.mainNetSortThree"/>*</td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}mainNetSortThree" id="${sheetPageName}mainNetSortThree"
                               initDicId="${sheetMain.mainNetSortTwo}" defaultValue="${sheetMain.mainNetSortThree}"
                               alt="allowBlank:false" onchange="selectLimit(this.value);"/>
            </td>
            <td class="label"><bean:message bundle="netOptimize" key="netOptimize.mainCompleteTime"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}mainCompleteTime" readonly="readonly"
                       id="${sheetPageName}mainCompleteTime" value="${eoms:date2String(sheetMain.mainCompleteTime)}"
                       onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>

            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="netOptimize" key="netOptimize.mainOptimizeDemand"/>*</td>
            <td class="content" colspan="3">
                <textarea name="${sheetPageName}mainOptimizeDemand" id="${sheetPageName}mainOptimizeDemand"
                          class="textarea max"
                          alt="allowBlank:false,maxLength:200,vtext:'请输入专题优化需求，最大长度为200个汉字！'">${sheetMain.mainOptimizeDemand}</textarea>
            </td>

        </tr>

        <tr>
            <td class="label">
                <bean:message bundle="netOptimize" key="netOptimize.mainOptmizeScheme"/>*
            </td>
            <td class="content" colspan="3">
                <eoms:attachment name="sheetMain" property="mainOptmizeScheme"
                                 scope="request" alt="allowBlank:false" idField="${sheetPageName}mainOptmizeScheme"
                                 appCode="netOptimize"/>
            </td>
        </tr>
    </c:if>


</table>

<br/>

<c:if test="${status!=1}">
    <fieldset id="link1">
        <legend>
            <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
            <bean:message bundle="netOptimize" key="role.adminPerformer"/>
        </legend>
        <eoms:chooser id="test" type="role" roleId="361" flowId="046"
                      config="{returnJSON:true,showLeader:true,mainPanelTitle:'可选择的子角色'}"
                      category="[{id:'${sheetPageName}dealPerformer',text:'派发',allowBlank:true,vtext:'请选择派发人'},{id:'${sheetPageName}copyPerformer',text:'抄送',childType:'user,subrole,dept',limit:'none'}]"
                      data="${sendUserAndRoles}"/>
    </fieldset>


</c:if>
		