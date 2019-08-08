<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<script type="text/javascript">
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'theform'});
    });
</script>

<div id="sheetform">
    <html:form action="/plannconfirm.do?method=performFroceHold" styleId="theform">
        <%@ include file="/WEB-INF/pages/wfworksheet/netdata/baseinputlinkhtmlnew.jsp" %>
        <table class="formTable">
            <div ID="idSpecial"></div>
            <tr>
                <td width="320" colspan="2">
                            <%
   String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateType"));
   if(operateType != null){
      if(operateType.equals("forceHold")) {
      %>
                    <input type="hidden" name="operateType" value="-13"/>
            <tr>
                <td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
                <td colspan="3">
                    <eoms:comboBox name="holdStatisfied" id="holdStatisfied" alt="allowBlank:false" initDicId="10303"
                                   styleClass="select"/>
                </td>
            </tr>
            <%
            } else if (operateType.equals("nullity")) {
            %>
            <input type="hidden" name="operateType" value="-12"/>
            <%
            } else if (operateType.equals("forceNullity")) {
            %>
            <input type="hidden" name="operateType" value="-14"/>
            <%
                    }
                }
            %>
            <input type="hidden" name="beanId" value="iPlannConfirmMainManager"/>
            <input type="hidden" name="mainClassName" value="com.boco.eoms.sheet.plannconfirm.model.PlannConfirmMain"/>
            <input type="hidden" name="linkClassName" value="com.boco.eoms.sheet.plannconfirm.model.PlannConfirmLink"/>
            <input type="hidden" name="id" id="id" value="${sheetMain.id}"/>
            <input type="hidden" name="processTemplateName" value="PlannConfirm"/>
            <input type="hidden" name="operateName" value="forceHold"/>
            <input type="hidden" name="correlationKey" id="correlationKey" value="${sheetMain.correlationKey}"/>
            <input type="hidden" name="piid" id="piid" value="${sheetMain.piid}"/>
            <input type="hidden" name="operateUserId" id="operateUserId" value="${sheetMain.sendUserId}"/>
            <input type="hidden" name="operateDeptId" id="operateDeptId" value="${sheetMain.sendDeptId}"/>
            <input type="hidden" name="operateRoleId" id="operateRoleId" value="${sheetMain.sendRoleId}"/>
            <input type="hidden" name="sheetId" id="sheetId" value="${sheetMain.sheetId}"/>

            </td>
            </tr>
            <!--
	   		如果有互调的情况，目前只能给出模板样式，，根据实际情况请自行添加
	   	<% 
	   	  //String parentProcessName=com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("parentProcessName")); 
     	  //if(parentProcessName.equals("iNetChangeMainManager")){
     	 %>
     	   <input type="hidden" name="ifReplyInvoke" id="ifReplyInvoke" value="true" />
		   <input type="hidden" name="invokePiid" id="invokePiid" value="${parentMain.piid}" />	
		   <input type="hidden" name="invokeOperateName" id="invokeOperateName" value="backToNetChange" />
		   <input type="hidden" name="invokeProcessBeanId" id="invokeProcessBeanId" value="NetChange" />							
     	   <input type="hidden" name="invokeSheetId" id="invokeSheetId" value="${parentMain.id}" />		
     	   <input type="hidden" name="invokePhaseId" id="invokePhaseId" value="ExecuteTask" />	
     	   <input type="hidden" name="hasNextTaskFlag" id="hasNextTaskFlag" value="holdReplyProcess" />
     	 <//}%>
	   -->
            <tr>
                <td class="label">
                    <bean:message bundle="sheet" key="mainForm.cancelReason"/>
                </td>
                <td colspan="3">
                    <c:set var="vtext" value="请填入备注信息，最多输入4000字符"/>
                    <textarea class="textarea max" name="cancelReason" id="cancelReason"
                              alt="allowBlank:false,maxLength:4000,vtext:'${vtext}'"></textarea>
                </td>
            </tr>
        </table>
        <div class="form-btns">
            <html:submit styleClass="btn" property="method.save2" styleId="method.save2">
                <bean:message bundle="sheet" key="button.done"/>
            </html:submit>
        </div>
    </html:form>

</div>
