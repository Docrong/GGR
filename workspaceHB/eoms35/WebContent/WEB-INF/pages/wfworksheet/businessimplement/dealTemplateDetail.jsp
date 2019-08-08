<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
    com.boco.eoms.sheet.businessimplement.model.BusinessImplementLink businessimplementLink = (com.boco.eoms.sheet.businessimplement.model.BusinessImplementLink) request.getAttribute("sheetLink");
    java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(businessimplementLink.getActiveTemplateId());
    java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(businessimplementLink.getOperateType()));
%>
<script type="text/javascript">
    var frm = document.forms[0];

    function saveDealTemplate(type) {
        var form = document.forms[0];
        if (form.templateName.value == "") {

            return false;
        }
        form.action = "${app}/sheet/businessimplement/businessimplement.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
        form.submit();
    }

    function removeTemplate() {
        if (confirm('<bean:message bundle="sheet" key="worksheet.delete.confirm" />')) {
            var thisform = document.forms[0];
            thisform.action = thisform.action + "?method=removeDealTemplate&dealTemplateId=${sheetLink.id}";
            thisform.submit();
        }
    }
</script>
<%@ include file="/WEB-INF/pages/wfworksheet/businessimplement/baseinputlinkhtmlnew.jsp" %>
<html:form action="/businessimplement.do" styleId="theform">
    <br/>

    <table class="formTable">


        <%if (taskName.equals("ImplementDealTask") && (operateType.equals("91") || operateType.equals("11"))) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType%>"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ProjectDealTask"/>
        <tr>
            <td class="label">
                <bean:message bundle="businessimplement" key="businessimplement.linkArugmentlevel"/> *
            </td>
            <td>
                <eoms:comboBox name="${sheetPageName}linkArugmentlevel" id="${sheetPageName}linkArugmentlevel"
                               initDicId="1012302" styleClass="select-class" alt="allowBlank:false"
                               defaultValue="${sheetLink.linkArugmentlevel}"/>

            </td>

            <td class="label">
                <bean:message bundle="businessimplement" key="businessimplement.linkNeedFinishTime"/>*
            </td>
            <td class="content">
                <input class="text" onclick="popUpCalendar(this, this)" type="text"
                       name="${sheetPageName}linkNeedFinishTime" readonly="readonly"
                       value="${eoms:date2String(sheetLink.linkNeedFinishTime)}"
                       id="${sheetPageName}linkNeedFinishTime"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="businessimplement"
                                            key="businessimplement.linkSenderOpinition"/></td>
            <td colspan="3">
			      <textarea class="textarea max" name="${sheetPageName}linkSenderOpinition"
                            id="${sheetPageName}linkSenderOpinition"
                            alt="allowBlank:true,maxLength:200,vtext:'审核意见，最多输入100汉字'">${sheetLink.linkSenderOpinition}</textarea>
            </td>
        </tr>

        <%} else if (taskName.equals("ProjectDealTask") && (operateType.equals("93") || operateType.equals("11"))) { %>

        <tr>
            <td class="label">
                <bean:message bundle="businessimplement" key="businessimplement.linkDoResourt"/>
            </td>
            <td>
                <eoms:comboBox name="${sheetPageName}linkDoResourt" id="${sheetPageName}linkDoResourt"
                               initDicId="1012303" styleClass="select-class" alt="allowBlank:false"
                               defaultValue="${sheetLink.linkArugmentlevel}"/>

            </td>

            <td class="label">
                <bean:message bundle="businessimplement" key="businessimplement.linkEleNeed"/>
            </td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkEleNeed" id="${sheetPageName}linkEleNeed"
                       alt="allowBlank:true" value="${sheetLink.linkEleNeed}"/>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message bundle="businessimplement" key="businessimplement.linkStartA"/>
            </td>
            <td>
                <input type="text" class="text" name="${sheetPageName}linkStartA" id="${sheetPageName}linkStartA"
                       alt="allowBlank:true" value="${sheetLink.linkStartA}"/>
            </td>

            <td class="label">
                <bean:message bundle="businessimplement" key="businessimplement.linkACompteHome"/>
            </td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkACompteHome"
                       id="${sheetPageName}linkACompteHome" alt="allowBlank:true" value="${sheetLink.linkACompteHome}"/>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message bundle="businessimplement" key="businessimplement.linkABusinessDDF"/>
            </td>
            <td>
                <input type="text" class="text" name="${sheetPageName}linkABusinessDDF"
                       id="${sheetPageName}linkABusinessDDF" alt="allowBlank:true"
                       value="${sheetLink.linkABusinessDDF}"/>
            </td>

            <td class="label">
                <bean:message bundle="businessimplement" key="businessimplement.linkADevHost"/>
            </td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkADevHost" id="${sheetPageName}linkADevHost"
                       alt="allowBlank:true" value="${sheetLink.linkADevHost}"/>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message bundle="businessimplement" key="businessimplement.linkATrasitionDDF"/>
            </td>
            <td>
                <input type="text" class="text" name="${sheetPageName}linkATrasitionDDF"
                       id="${sheetPageName}llinkATrasitionDDF" alt="allowBlank:true"
                       value="${sheetLink.linkATrasitionDDF}"/>
            </td>

            <td class="label">
                <bean:message bundle="businessimplement" key="businessimplement.linkScopeTrasitionHost"/>
            </td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkScopeTrasitionHost"
                       id="${sheetPageName}linkScopeTrasitionHost" alt="allowBlank:true"
                       value="${sheetLink.linkScopeTrasitionHost}"/>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message bundle="businessimplement" key="businessimplement.linkEndZ"/>*
            </td>
            <td>
                <input type="text" class="text" name="${sheetPageName}linkEndZ" id="${sheetPageName}linkEndZ"
                       alt="allowBlank:false" value="${sheetLink.linkEndZ}"/>
            </td>

            <td class="label">
                <bean:message bundle="businessimplement" key="businessimplement.linkDevType"/>*
            </td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}linkDevType" id="${sheetPageName}linkDevType"
                               sub="${sheetPageName}linkOne" initDicId="1012304" styleClass="select-class"
                               alt="allowBlank:false" defaultValue="${sheetLink.linkDevType}"/>
            </td>
        </tr>

        <tr>
            <td class="label">
                方式*
            </td>
            <td class="content" colspan="3">
                <eoms:comboBox name="${sheetPageName}linkOne" id="${sheetPageName}linkOne"
                               initDicId="${sheetPageName}linkDevType" styleClass="select-class" alt="allowBlank:false"
                               defaultValue="${sheetLink.linkOne}"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="businessimplement" key="businessimplement.linkFailureResson"/></td>
            <td colspan="3">
			      <textarea class="textarea max" name="${sheetPageName}linkFailureResson"
                            id="${sheetPageName}linkFailureResson"
                            alt="allowBlank:true,maxLength:200,vtext:'审核意见，最多输入100汉字'">${sheetLink.linkFailureResson}</textarea>
            </td>
        </tr>

        <%} else if (taskName.equals("CityNetTask") && (operateType.equals("92") || operateType.equals("11"))) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType%>"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="TrasitionTask"/>

        <tr>
            <td class="label">
                <bean:message bundle="businessimplement" key="businessimplement.linkDesinResourt"/>
            </td>
            <td>
                <input type="text" class="text" name="${sheetPageName}linkDesinResourt"
                       id="${sheetPageName}linkDesinResourt" alt="allowBlank:true"
                       value="${sheetLink.linkDesinResourt}"/>
            </td>

            <td class="label">
                <bean:message bundle="businessimplement" key="businessimplement.linkDesinType"/>
            </td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkDesinType" id="${sheetPageName}linkDesinType"
                       alt="allowBlank:true" value="${sheetLink.linkDesinType}"/>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message bundle="businessimplement" key="businessimplement.linkIPAddress"/>
            </td>
            <td>
                <input type="text" class="text" name="${sheetPageName}linkIPAddress" id="${sheetPageName}linkIPAddress"
                       alt="allowBlank:true" value="${sheetLink.linkIPAddress}"/>
            </td>

            <td class="label">
                <bean:message bundle="businessimplement" key="businessimplement.linkSubNetNumber"/>
            </td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkSubNetNumber"
                       id="${sheetPageName}linkSubNetNumber" alt="allowBlank:true"
                       value="${sheetLink.linkSubNetNumber}"/>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message bundle="businessimplement" key="businessimplement.linkGetWay"/>
            </td>
            <td>
                <input type="text" class="text" name="${sheetPageName}linkGetWay" id="${sheetPageName}linkGetWay"
                       alt="allowBlank:true" value="${sheetLink.linkGetWay}"/>
            </td>

            <td class="label">
                <bean:message bundle="businessimplement" key="businessimplement.linkVLAN"/>
            </td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkVLAN" id="${sheetPageName}linkVLAN"
                       alt="allowBlank:true" value="${sheetLink.linkVLAN}"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="businessimplement" key="businessimplement.linkFailureResson"/></td>
            <td colspan="3">
			      <textarea class="textarea max" name="${sheetPageName}linkFailureResson"
                            id="${sheetPageName}linkFailureResson"
                            alt="allowBlank:true,maxLength:200,vtext:'审核意见，最多输入100汉字'">${sheetLink.linkFailureResson}</textarea>
            </td>
        </tr>

        <%} else if (taskName.equals("ApnTask") && (operateType.equals("11") || operateType.equals("95"))) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType%>"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="BusinessTestTask"/>
        <tr>
            <td class="label">
                <bean:message bundle="businessimplement" key="businessimplement.linkDesinResourt"/>
            </td>
            <td colspan="3">
                <input type="text" class="text" name="${sheetPageName}linkDesinResourt"
                       id="${sheetPageName}linkDesinResourt" alt="allowBlank:true"
                       value="${sheetLink.linkDesinResourt}"/>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message bundle="businessimplement" key="businessimplement.linkGroupUserAddressZone"/>
            </td>
            <td colspan="3">
                <input type="text" class="text" name="${sheetPageName}linkGroupUserAddressZone"
                       id="${sheetPageName}linkGroupUserAddressZone" alt="allowBlank:true"
                       value="${sheetLink.linkGroupUserAddressZone}"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="businessimplement" key="businessimplement.linkFailureResson"/></td>
            <td colspan="3">
			      <textarea class="textarea max" name="${sheetPageName}linkFailureResson"
                            id="${sheetPageName}linkFailureResson"
                            alt="allowBlank:true,maxLength:200,vtext:'审核意见，最多输入100汉字'">${sheetLink.linkFailureResson}</textarea>
            </td>
        </tr>
        <%} else if (taskName.equals("BusinessTestTask") && (operateType.equals("11") || operateType.equals("94") || operateType.equals("941"))) { %>

        <tr>

        <tr>
            <td class="label">
                <bean:message bundle="businessimplement" key="businessimplement.linkDealResourt"/>**
            </td>
            <td colspan="3">
                <input type="text" class="text" name="${sheetPageName}linkDealResourt"
                       id="${sheetPageName}linkDealResourt" alt="allowBlank:false"
                       value="${sheetLink.linkDealResourt}"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="businessimplement" key="businessimplement.linkDealDescriptin"/> *
            </td>
            <td colspan="3">
			      <textarea class="textarea max" name="${sheetPageName}linkDealDescriptin"
                            id="${sheetPageName}linkDealDescriptin"
                            alt="allowBlank:false,maxLength:200,vtext:'处理描述，最多输入100汉字'">${sheetLink.linkDealDescriptin}</textarea>
            </td>
        </tr>
        <%} else if (taskName.equals("BusinessTestTask") && (operateType.equals("11") || operateType.equals("941"))) { %>

        <tr>

        <tr>
            <td class="label">
                <bean:message bundle="businessimplement" key="businessimplement.linkDealResourt"/>*
            </td>
            <td colspan="3">
                <input type="text" class="text" name="${sheetPageName}linkDealResourt"
                       id="${sheetPageName}linkDealResourt" alt="allowBlank:false"
                       value="${sheetLink.linkDealResourt}"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="businessimplement" key="businessimplement.linkDealDescriptin"/> *
            </td>
            <td colspan="3">
			      <textarea class="textarea max" name="${sheetPageName}linkDealDescriptin"
                            id="${sheetPageName}linkDealDescriptin"
                            alt="allowBlank:false,maxLength:200,vtext:'处理结果，最多输入100汉字'">${sheetLink.linkDealDescriptin}</textarea>
            </td>
        </tr>
        <%} else if (taskName.equals("TrasitionTask") && (operateType.equals("11") || operateType.equals("97"))) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType%>"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="BusinessTestTask"/>
        <tr>
            <td class="label">
                <bean:message bundle="businessimplement" key="businessimplement.linkEleCallResourt"/>
            </td>
            <td>
                <input type="text" class="text" name="${sheetPageName}linkEleCallResourt"
                       id="${sheetPageName}linkEleCallResourt" alt="allowBlank:fasle"
                       value="${sheetLink.linkEleCallResourt}"/>
            </td>
        </tr>
        <td class="label">
            <bean:message bundle="businessimplement" key="businessimplement.linkTrasitionEleNumber"/>
        </td>
        <td>
            <input type="text" class="text" name="${sheetPageName}linkTrasitionEleNumber"
                   id="${sheetPageName}linkTrasitionEleNumber" alt="allowBlank:fasle"
                   value="${sheetLink.linkTrasitionEleNumber}"/>
        </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message bundle="businessimplement" key="businessimplement.linkGroupUserAddressZone"/>
            </td>
            <td colspan="3">
                <input type="text" class="text" name="${sheetPageName}linkGroupUserAddressZone"
                       id="${sheetPageName}linkGroupUserAddressZone" alt="allowBlank:true"
                       value="${sheetLink.linkGroupUserAddressZone}"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="businessimplement" key="businessimplement.linkFailureResson"/></td>
            <td colspan="3">
			      <textarea class="textarea max" name="${sheetPageName}linkFailureResson"
                            id="${sheetPageName}linkFailureResson"
                            alt="allowBlank:true,maxLength:200,vtext:'审核意见，最多输入100汉字'">${sheetLink.linkFailureResson}</textarea>
            </td>
        </tr>

        <%} else if (taskName.equals("HoldTask")) {%>


        <%}%>
        <%if (!operateType.equals("61") && !operateType.equals("18") && !operateType.equals("4") && !taskName.equals("OneAuditTask") && !taskName.equals("TwoAuditTask") && !taskName.equals("ProvinceTask")) { %>
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
            <td class="label"><bean:message bundle="sheet" key="linkForm.accessories"/></td>
            <td colspan="3">
                <eoms:attachment name="sheetLink" property="nodeAccessories"
                                 scope="request" idField="${sheetPageName}nodeAccessories" appCode="businessimplement"/>
            </td>
        </tr>
        <%}%>
        <%if (taskName.equals("AcceptTask") || taskName.equals("ExecuteTask") || taskName.equals("AcceptReply")) {%>
        <%if (operateType.equals("61")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="61"/>
        <!--  <tr>
	       <td class="label">
	        <bean:message bundle="sheet" key="linkForm.remark" />
		    </td>
			<td colspan="3">			
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark" 
		        alt="width:'500px',maxLength:200,vtext:'最多输入100汉字'">${sheetLink.remark}</textarea>
		  </td>
		</tr> -->

        <%
                }
            }
        %>


        <% if (taskName.equals("cc")) {%>

        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="linkForm.remark"/>*
            </td>
            <td colspan="3">
                <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="-15"/>
                <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="allowBlank:false,width:500,maxLength:2000,vtext:'请最多输入1000汉字'"
                          alt="width:'500px'">${sheetLink.remark}</textarea>
            </td>
        </tr>
        <%} %>

    </table>
</html:form>
<logic:present name="type">
    <c:if test="${dealTemplateId != null && dealTemplateId != ''}">
        <html:button styleClass="btn" property="method.save" styleId="method.save"
                     onclick="saveDealTemplate('current')">
            <bean:message bundle="sheet" key="button.saveCurTemplate"/>
        </html:button>
    </c:if>
    <html:button styleClass="btn" property="method.save" styleId="method.save" onclick="removeTemplate()">
        <bean:message bundle="sheet" key="button.delete"/>
    </html:button>
</logic:present>
<html:button styleClass="btn" property="method.save" styleId="method.save" onclick="history.back(-1)">
    <bean:message bundle="sheet" key="button.back"/>
</html:button>
