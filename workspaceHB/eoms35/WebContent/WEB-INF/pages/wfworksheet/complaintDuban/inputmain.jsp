<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%

    request.setAttribute("roleId", "249");

    long localTimes = com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
%>
<%@ include file="/WEB-INF/pages/wfworksheet/complaintDuban/baseinputmainhtmlnew.jsp" %>
<input type="hidden" name="${sheetPageName}processTemplateName" value="ComplaintDubanProcesses"/>
<input type="hidden" name="${sheetPageName}sheetTemplateName" value="ComplaintDubanProcesses"/>
<input type="hidden" name="${sheetPageName}operateName" value="newWorksheet"/>
<c:if test="${status!=1}">
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Assessor"/>
    <input type="hidden" id="${sheetPageName}operateType" name="${sheetPageName}operateType" value="${operateType}"/>
</c:if>
<c:if test="${status==1}">
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over"/>
</c:if>
<input type="hidden" name="${sheetPageName}beanId" value="iComplaintDubanMainManager"/>
<input type="hidden" name="${sheetPageName}mainClassName"
       value="com.boco.eoms.sheet.complaintDuban.model.ComplaintDubanMain"/>
<input type="hidden" name="${sheetPageName}linkClassName"
       value="com.boco.eoms.sheet.complaintDuban.model.ComplaintDubanLink"/>
<br>
<!-- sheet info -->
<table class="formTable">
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
        <td class="label">投诉跟踪工单流水号</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}trailId" id="${sheetPageName}trailId"
                   value="${sheetMain.trailId}" alt="allowBlank:false"/>
        </td>
        <td class="label">EOMS投诉工单流水号</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}eomsId" id="${sheetPageName}eomsId"
                   value="${sheetMain.eomsId}" alt="allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label">投诉类别</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}complaintSort" id="${sheetPageName}complaintSort"
                   value="${sheetMain.complaintSort}" alt="allowBlank:false"/>
        </td>
        <td class="label">投诉现象</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}appearance" id="${sheetPageName}appearance"
                   value="${sheetMain.appearance}" alt="allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label">受理号码</td>
        <td>
            <input type="text" class="text" name="${sheetPageName}acceptNum" id="${sheetPageName}acceptNum"
                   value="${sheetMain.acceptNum}" alt="allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label">
            报结意见
        </td>
        <td colspan="3">
		  	<textarea name="${sheetPageName}opinion" class="textarea max" id="${sheetPageName}opinion"
                      alt="allowBlank:false,maxLength:1000,vtext:'请填写意见，最多输入1000字符'">${sheetMain.opinion}</textarea>
        </td>
    </tr>
    <tr>
        <td class="label">
            <bean:message bundle="complaintDuban" key="complaintDuban.complaintInfo"/>*
        </td>
        <td colspan="3">
		  	<textarea name="${sheetPageName}complaintInfo" class="textarea max" id="${sheetPageName}complaintInfo"
                      alt="allowBlank:false,maxLength:4000,vtext:'请填写意见，最多输入4000字符'">${sheetMain.complaintInfo}</textarea>
        </td>
    </tr>
    <tr>
        <td class="label">
            <bean:message bundle="complaintDuban" key="complaintDuban.complaintText"/>*
        </td>
        <td colspan="3">
		  	<textarea name="${sheetPageName}complaintText" class="textarea max" id="${sheetPageName}complaintText"
                      alt="allowBlank:false,maxLength:4000,vtext:'请填写意见，最多输入4000字符'">${sheetMain.complaintText}</textarea>
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
            <bean:message bundle="sheet" key="mainForm.accessories"/>
        </td>
        <td colspan="3">

            <eoms:attachment name="sheetMain" property="sheetAccessories"
                             scope="request" idField="${sheetPageName}sheetAccessories" appCode="commontask"/>
        </td>
    </tr>
</table>
<c:if test="${status!=1}">
    <fieldset id="link1">
        <legend>
            <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>
            <span id="roleName">:号段审核人
			 </span>
        </legend>
        <div class="x-form-item">
            <eoms:chooser id="test" config="{returnJSON:true,showLeader:true}"
                          category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
                          panels="[{text:'部门与人员',dataUrl:'/xtree.do?method=userFromDept'},{text:'个性化部门树',dataUrl:'/sheet/userdefinegroup/userdefinegroup.do?method=showTree&flowId=1'}]"
                          data="${sendUserAndRoles}"/>
        </div>
    </fieldset>
</c:if>
