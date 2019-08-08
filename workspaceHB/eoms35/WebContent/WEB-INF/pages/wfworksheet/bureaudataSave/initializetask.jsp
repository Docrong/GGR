<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>

<html:form action="/bureaudataSave.do?method=newTaskPerformAdd" styleId="theform" target="myname">
    <%@ include file="/WEB-INF/pages/wfworksheet/bureaudataSave/baseinputlinkhtmlnew.jsp" %>
    <input type="hidden" name="${sheetPageName}processTemplateName" value="BureaudataSaveProcesses"/>
    <input type="hidden" name="${sheetPageName}operateName" value="newTask"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${phaseId}"/>
    <input type="hidden" name="${sheetPageName}serviceId" id="${sheetPageName}serviceId" value="${serviceId}"/>
    <input type="hidden" name="${sheetPageName}serviceCnName" id="${sheetPageName}serviceCnName"
           value="${serviceCnName}"/>
    <input type="hidden" id="${sheetPageName}sheetKey" name="${sheetPageName}sheetKey" value="${sheetKey}"/>
    <input type="hidden" id="${sheetPageName}parentTaskId" name="${sheetPageName}parentTaskId" value="${parentTaskId}"/>
    <input type="hidden" id="${sheetPageName}parentLinkId" name="${sheetPageName}parentLinkId" value="${preLinkId}"/>
    <input type="hidden" name="${sheetPageName}beanId" value="iBureaudataSaveMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.bureaudataSave.model.BureaudataSaveMain"/>
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.bureaudataSave.model.BureaudataSaveLink"/>
    <table class="formTable">
        <tr>
            <td class="label"><bean:message bundle="bureaudataSave" key="bureaudataSave.linkUrgency"/>*</td>
            <td class="content" colspan="3">
                <eoms:comboBox name="${sheetPageName}linkUrgency" id="${sheetPageName}linkUrgency"
                               initDicId="1010102" defaultValue="${sheetLink.linkUrgency}" alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="bureaudataSave" key="bureaudataSave.linkReqCompleteTime"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkReqCompleteTime" readonly="readonly"
                       id="${sheetPageName}sheetCompleteLimit"
                       value="${eoms:date2String(sheetLink.linkReqCompleteTime)}"
                       onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>

            </td>
            <td class="label"><bean:message bundle="bureaudataSave" key="bureaudataSave.linkAcceptDept"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkAcceptDept"
                       id="${sheetPageName}linkAcceptDept" value="${sheetLink.linkAcceptDept}"
                       alt="allowBlank:false,maxLength:50,vtext:'请填入受理部门，最多输入25字'"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="bureaudataSave" key="bureaudataSave.linkSendSheetDesc"/>*</td>
            <td colspan="3">
                <textarea name="${sheetPageName}linkSendSheetDesc"
                          alt="allowBlank:false,maxLength:255,vtext:'请填入派单意见，最多输入255字符'"
                          id="${sheetPageName}linkTestDesc"
                          class="textarea max">${sheetLink.linkSendSheetDesc}</textarea>
            </td>
        </tr>
    </table>
    <c:if test="${phaseId=='UserTask'}">
        <input type="hidden" id="${sheetPageName}operateType" name="${sheetPageName}operateType" value="2011"/>
        <fieldset id="link1">
            <legend>
                <bean:message bundle="bureaudataSave" key="role.toOrgName"/>
                <span id="roleName">:用户侧资源勘查角色
					 </span>
            </legend>
            <div class="x-form-item">
                <eoms:chooser id="test1" type="role" roleId="930" flowId="311"
                              config="{returnJSON:true,showLeader:true}"
                              category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'}]"
                />
            </div>
        </fieldset>
    </c:if>
    <c:if test="${phaseId=='AccessTask'}">
        <input type="hidden" id="${sheetPageName}operateType" name="${sheetPageName}operateType" value="2012"/>
        <fieldset id="link1">
            <legend>
                <bean:message bundle="bureaudataSave" key="role.toOrgName"/>
                <span id="roleName">:接入点资源勘查角色
					 </span>
            </legend>
            <div class="x-form-item">
                <eoms:chooser id="test1" type="role" roleId="931" flowId="311"
                              config="{returnJSON:true,showLeader:true}"
                              category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'}]"
                />
            </div>
        </fieldset>
    </c:if>
    <c:if test="${phaseId=='CityTask'}">
        <input type="hidden" id="${sheetPageName}operateType" name="${sheetPageName}operateType" value="2013"/>
        <fieldset id="link1">
            <legend>
                <bean:message bundle="bureaudataSave" key="role.toOrgName"/>
                <span id="roleName">:城域网资源查看角色
					 </span>
            </legend>
            <div class="x-form-item">
                <eoms:chooser id="test1" type="role" roleId="932" flowId="311"
                              config="{returnJSON:true,showLeader:true}"
                              category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'}]"
                />
            </div>
        </fieldset>
    </c:if>
    <c:if test="${phaseId=='TransfereTask'}">
        <input type="hidden" id="${sheetPageName}operateType" name="${sheetPageName}operateType" value="2013"/>
        <fieldset id="link1">
            <legend>
                <bean:message bundle="bureaudataSave" key="role.toOrgName"/>
                <span id="roleName">:传输设备查勘角色
					 </span>
            </legend>
            <div class="x-form-item">
                <eoms:chooser id="test1" type="role" roleId="4030" flowId="311"
                              config="{returnJSON:true,showLeader:true}"
                              category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'}]"
                />
            </div>
        </fieldset>
    </c:if>
    <c:if test="${phaseId=='TransferlTask'}">
        <input type="hidden" id="${sheetPageName}operateType" name="${sheetPageName}operateType" value="2013"/>
        <fieldset id="link1">
            <legend>
                <bean:message bundle="bureaudataSave" key="role.toOrgName"/>
                <span id="roleName">:传输线路查勘角色
					 </span>
            </legend>
            <div class="x-form-item">
                <eoms:chooser id="test1" type="role" roleId="4031" flowId="311"
                              config="{returnJSON:true,showLeader:true}"
                              category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'}]"
                />
            </div>
        </fieldset>
    </c:if>
    <div class="form-btns" id="btns">
        <html:submit styleClass="btn" property="method.save" styleId="method.save">
            <bean:message bundle="sheet" key="button.send"/>
        </html:submit>
    </div>
</html:form>
<script type="text/javascript">
    v = new eoms.form.Validation({form: 'theform'});
    window.name = "myname";
</script>
<%@ include file="/common/footer_eoms.jsp" %>