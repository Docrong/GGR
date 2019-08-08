﻿<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="com.boco.eoms.sheet.base.webapp.action.IBaseSheet" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
    String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId"));
    String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId"));
    String sheetMain = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("sheetMain"));
    System.out.println("=====taskName======" + taskName);
    String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
    request.setAttribute("operateType", operateType);
    String operateUserId = "";
    BaseLink bl = (BaseLink) request.getAttribute("preLink");
    IBaseSheet baseSheet = (IBaseSheet) ApplicationContextHolder.getInstance().getBean("TechniqueSupportHb");
    if (bl != null) {
        String prelinkid = com.boco.eoms.base.util.StaticMethod.nullObject2String(bl.getPreLinkId());
        if (!prelinkid.equals("")) {
            BaseLink base = baseSheet.getLinkService().getSingleLinkPO(prelinkid);
            operateUserId = base.getOperateUserId();
        }
    }
%>
<script type="text/javascript">
    //处理时限不能超过工单完成时限
    var dtnow = new Date();
    var str = '${sheetMain.sheetCompleteLimit}';
    str = str.replace(/-/g, "/");
    str = str.substring(0, str.length - 2);
    var dt2 = new Date(str);
    if (dt2 > dtnow) {
        document.getElementById("tmpCompleteLimit").value = '${sheetMain.sheetCompleteLimit}';
    } else {
        document.getElementById("tmpCompleteLimit").value = '';
    }
</script>
<%@ include file="/WEB-INF/pages/wfworksheet/techniquesupporthb/baseinputlinkhtmlnew.jsp" %>
<br/>
<table class="formTable">
    <input type="hidden" id="tmpCompleteLimit" value=""
           alt="vtype:'moreThen',link:'${sheetPageName}nodeCompleteLimit',vtext:'处理时限不能晚于工单完成时限'"/>
    <input type="hidden" name="${sheetPageName}beanId" value="iTechniqueSupportHbMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.techniquesupporthb.model.TechniqueSupportHbMain"/>
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.techniquesupporthb.model.TechniqueSupportHbLink"/>
    <input type="hidden" name="${sheetPageName}toDeptId" value="${sheetMain.toDeptId}"/>
    <c:if test="${taskName != 'HoldTask' }">
        <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
    </c:if>

    <c:choose>
        <c:when test="${task.subTaskFlag == 'true'}">
            <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"
                   value="true"/>
        </c:when>
    </c:choose>


    <%if (operateType.equals("4")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4"/>
    <c:choose>
        <c:when test="${taskName=='DoneImplTask'}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask"/>
        </c:when>
    </c:choose>
    <tr>
        <td class="label">
            备注说明*
        </td>
        <td colspan="3">
		        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                          alt="allowBlank:false,width:500,vtext:'请最多输入1000字'"
                          alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>

    <%} %>


    <%if (taskName.equals("DoneImplTask")) {%>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>"/>
    <%if (operateType.equals("911") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ImplValidTask"/>

    <c:choose>
        <c:when test="${sheetMain.mainSheetType!='101470201'&&sheetMain.mainSheetType!='101470204'}">
            <tr>
                <td class="label">工时*</td>
                <td>
                    <input type="text" class="text" name="${sheetPageName}workTime"
                           id="${sheetPageName}workTime" value="${sheetLink.workTime}" alt="allowBlank:false"/>
                </td>
            </tr>
        </c:when>
    </c:choose>
    <tr>
        <td class="label"><bean:message bundle="techniquesupporthb" key="techniquesupporthb.linkDealOpinion"/>*</td>
        <td colspan="3">
			      <textarea class="textarea max" name="${sheetPageName}linkDealOpinion"
                            id="${sheetPageName}linkDealOpinion"
                            alt="allowBlank:false,maxLength:2000,vtext:'审核意见，最多输入1000汉字'">${sheetLink.linkDealOpinion}</textarea>
        </td>
    </tr>

    <%} %>

    <%} else if (taskName.equals("ImplValidTask")) {%>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType %>"/>
    <%if (operateType.equals("920") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="DoneImplTask"/>
    <input type="hidden" name="${sheetPageName}dealPerformer" id="${sheetPageName}dealPerformer"
           value="${fOperateroleid}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerLeader" id="${sheetPageName}dealPerformerLeader"
           value="${ftaskOwner}"/>
    <input type="hidden" name="${sheetPageName}dealPerformerType" id="${sheetPageName}dealPerformerType"
           value="${fOperateroleidType}"/>

    <%} else if (operateType.equals("921") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask"/>
    <%} %>


    <%} else if (taskName.equals("HoldTask")) {%>
    <%if (operateType.equals("18")) { %>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="OverTask"/>
    <input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>

    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*</td>
        <td colspan='3'>
            <eoms:comboBox name="${sheetPageName}holdStatisfied"
                           id="${sheetPageName}holdStatisfied"
                           defaultValue="${sheetMain.holdStatisfied != 0 ? sheetMain.holdStatisfied : 1030301}"
                           initDicId="10303" styleClass="select" alt="allowBlank:false"/>
        </td>
    </tr>

    <tr>
        <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/>*</td>
        <td colspan="3">
			      <textarea name="${sheetPageName}endResult" id="${sheetPageName}endResult" class="textarea max"
                            alt="allowBlank:false,maxLength:2000,vtext:'请输入归档内容，最多输入1000汉字'">${sheetMain.endResult}</textarea>
        </td>
    </tr>
    <%}%>
    <%if (operateType.equals("17")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="17"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ValidateTask"/>

    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
					        <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                                      alt="allowBlank:false,width:500,vtext:'请最多输入1000字'"
                                      alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <%}%>

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
                             scope="request" idField="${sheetPageName}nodeAccessories" appCode="techniquesupporthb"/>
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


