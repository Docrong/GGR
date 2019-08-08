<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/WEB-INF/pages/wfworksheet/securityevaluate/baseinputmainhtmlnew.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
    request.setAttribute("roleId", "324");
    long localTimes = com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
%>
<script type="text/javascript">
    function selectLimit(obj) {

        if ($("${sheetPageName}mainNetSortOne").value == null || $("${sheetPageName}mainNetSortOne").value == "") {

            return false;
        }
        var temp1 = $("${sheetPageName}mainNetSortOne").value;
        var temp2 = $("${sheetPageName}mainNetSortTwo").value;
        var temp3 = $("${sheetPageName}mainNetSortThree").value;
        Ext.Ajax.request({
            method: "get",
            url: "securityevaluate.do?method=newShowLimit&specialty1=" + temp1 + "&specialty2=" + temp2 + "&specialty3=" + temp3 + "&flowName=SecurityEvaluateProcess",
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
<input type="hidden" name="${sheetPageName}processTemplateName" value="SecurityEvaluateProcess"/>
<input type="hidden" name="${sheetPageName}sheetTemplateName" value="SecurityEvaluateProcess"/>
<input type="hidden" name="${sheetPageName}operateName" value="newWork"/>

<c:if test="${status==0}">
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="EvaluateTask"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="0"/>
</c:if>
<c:if test="${status==1}">
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value=""/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="0"/>
</c:if>
<input type="hidden" name="${sheetPageName}beanId" value="iSecurityEvaluateMainManager"/>
<input type="hidden" name="${sheetPageName}mainClassName"
       value="com.boco.eoms.sheet.securityevaluate.model.SecurityEvaluateMain"/>
<input type="hidden" name="${sheetPageName}linkClassName"
       value="com.boco.eoms.sheet.securityevaluate.model.SecurityEvaluateLink"/>
<!-- sheet info -->
<br/>
<table class="formTable">

    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.acceptLimit"/>*</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}sheetAcceptLimit" readonly="readonly"
                   id="${sheetPageName}sheetAcceptLimit" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"
                   defaultValue="${sheetMain.sheetAcceptLimit}" value="${eoms:date2String(sheetMain.sheetAcceptLimit)}"
                   onclick="popUpCalendar(this, this)"
                   alt="vtype:'lessThen',link:'${sheetPageName}sheetCompleteLimit',vtext:'" 接收时限不能晚于最终处理时限',allowBlank:false"/>
        </td>
        <td class="label"><bean:message bundle="sheet" key="mainForm.completeLimit"/>*</td>
        <td><input type="text" class="text" name="${sheetPageName}sheetCompleteLimit" readonly="readonly"
                   id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetMain.sheetCompleteLimit)}"
                   onclick="popUpCalendar(this, this)"
                   defaultValue="${sheetMain.sheetCompleteLimit}"
                   alt="vtype:'moreThen',link:'${sheetPageName}sheetAcceptLimit',vtext:'最终处理时限不能早于接收时限',allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="securityevaluate" key="securityevaluate.mainNetSortOne"/>*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}mainNetSortOne" id="${sheetPageName}mainNetSortOne"
                           sub="${sheetPageName}mainNetSortTwo" initDicId="1010104"
                           defaultValue="${sheetMain.mainNetSortOne}" alt="allowBlank:false"
                           onchange="selectLimit(this.value);"/>
        </td>
        <td class="label"><bean:message bundle="securityevaluate" key="securityevaluate.mainNetSortTwo"/>*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}mainNetSortTwo" id="${sheetPageName}mainNetSortTwo"
                           sub="${sheetPageName}mainNetSortThree" initDicId="${sheetMain.mainNetSortOne}"
                           defaultValue="${sheetMain.mainNetSortTwo}" alt="allowBlank:false"
                           onchange="selectLimit(this.value);"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="securityevaluate" key="securityevaluate.mainNetSortThree"/>*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}mainNetSortThree" id="${sheetPageName}mainNetSortThree"
                           initDicId="${sheetMain.mainNetSortTwo}" defaultValue="${sheetMain.mainNetSortThree}"
                           alt="allowBlank:false" onchange="selectLimit(this.value);"/>
        </td>
        <td class="label"><bean:message bundle="securityevaluate" key="securityevaluate.mainCompleteTime"/>*</td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}mainCompleteTime" readonly="readonly"
                   id="${sheetPageName}mainCompleteTime" value="${eoms:date2String(sheetMain.mainCompleteTime)}"
                   onclick="popUpCalendar(this, this)" defaultValue="${sheetMain.mainCompleteTime}"
                   alt="allowBlank:false"/>
        </td>

    </tr>
    <tr>
        <td class="label"><bean:message bundle="securityevaluate" key="securityevaluate.mainSafetyStatement"/></td>
        <td colspan="3" class="content">
		  		<textarea name="mainSafetyStatement" id="mainSafetyStatement" class="textarea max"
                          alt="allowBlank:ture,width:500,maxLength:255,vtext:'最多输入125汉字'">${sheetMain.mainSafetyStatement}</textarea>
        </td>
    </tr>

</table>
<br/>
<c:if test="${status!=1}">
    <fieldset>
        <legend>
            <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
            <bean:message bundle="securityevaluate" key="role.securitygroup"/>
        </legend>

        <eoms:chooser id="test" type="role" roleId="326" flowId="071" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                      data="${sendUserAndRoles}"/>
    </fieldset>
</c:if>
	
