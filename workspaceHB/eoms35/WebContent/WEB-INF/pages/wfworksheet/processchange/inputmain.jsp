<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
    long localTimes = com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
%>
<script type="text/javascript">
    selectLimit();

    function selectLimit() {
        Ext.Ajax.request({
            method: "get",
            url: "processchange.do?method=newShowLimit&flowName=ProcessChangeProcess",
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
<% request.setAttribute("roleId", "374");%>
<%@ include file="/WEB-INF/pages/wfworksheet/processchange/baseinputmainhtmlnew.jsp" %>
<input type="hidden" name="${sheetPageName}processTemplateName" value="ProcessChangeProcess"/>
<input type="hidden" name="${sheetPageName}operateName" value="newSheet"/>
<input type="hidden" name="${sheetPageName}sheetTemplateName" value="ProcessChangeProcess"/>
<c:if test="${status==0}">
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="EvaluateTask"/>
    <input type="hidden" id="${sheetPageName}operateType" name="${sheetPageName}operateType" value="${operateType}"/>
</c:if>
<c:if test="${status==1}">
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverTask"/>
</c:if>
<input type="hidden" name="${sheetPageName}beanId" value="iProcessChangeMainManager"/>
<input type="hidden" name="${sheetPageName}mainClassName"
       value="com.boco.eoms.sheet.processchange.model.ProcessChangeMain"/>
<input type="hidden" name="${sheetPageName}linkClassName"
       value="com.boco.eoms.sheet.processchange.model.ProcessChangeLink"/>
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
            <td class="label"><bean:message bundle="processchange" key="processchange.mainProcessType"/>*</td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainProcessType" id="${sheetPageName}mainProcessType"
                               initDicId="1010701" sub="${sheetPageName}mainProcess"
                               defaultValue="${sheetMain.mainProcessType}" alt="allowBlank:false"
                               styleClass="select-class"/>
            </td>
            <td class="label"><bean:message bundle="processchange" key="processchange.mainProcess"/>*</td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainProcess" id="${sheetPageName}mainProcess"
                               initDicId="${sheetMain.mainProcessType}" defaultValue="${sheetMain.mainProcess}"
                               alt="allowBlank:false" styleClass="select-class"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="processchange" key="processchange.mainEditReason"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}mainEditReason" id="${sheetPageName}mainEditReason"
                          alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入流程修订原因，最多输入125字'">${sheetMain.mainEditReason}</textarea>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="processchange" key="processchange.mainEditAdvice"/>*</td>
            <td colspan="3">
                <textarea class="textarea max" name="${sheetPageName}mainEditAdvice" id="${sheetPageName}mainEditAdvice"
                          alt="width:500,allowBlank:false,maxLength:255,vtext:'请填入流程修订建议，最多输入125字'">${sheetMain.mainEditAdvice}</textarea>
            </td>
        </tr>
    </c:if>
</table>
<br/>
<c:if test="${status!=1}">
    <fieldset id="link1">
        <legend>
            <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
            <bean:message bundle="processchange" key="role.adminGroup"/>
        </legend>
        <eoms:chooser id="test1" type="role" roleId="375" flowId="081" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',allowBlank:false,text:'派发',vtext:'请选择派发对象'},
			              {id:'copyPerformer',childType:'user,subrole',allowBlank:true,limit:'none',text:'抄送',vtext:'请选择抄送对象'}]"
                      data="${sendUserAndRoles}"/>
    </fieldset>

</c:if>

