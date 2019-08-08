<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="com.boco.eoms.sheet.base.webapp.action.IBaseSheet" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
    String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("taskName"));
    String operateRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateRoleId"));
    String operateDeptId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("operateDeptId"));
    String currentRoleId = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("roleId"));
    System.out.println("=====taskName======" + taskName);
    String operateType = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getParameter("operateType"));
    request.setAttribute("operateType", operateType);
    String operateUserId = "";
    BaseLink bl = (BaseLink) request.getAttribute("preLink");
    IBaseSheet baseSheet = (IBaseSheet) ApplicationContextHolder.getInstance().getBean("BureaudataUpdate");
    if (bl != null) {
        String prelinkid = com.boco.eoms.base.util.StaticMethod.nullObject2String(bl.getPreLinkId());
        if (!prelinkid.equals("")) {
            BaseLink base = baseSheet.getLinkService().getSingleLinkPO(prelinkid);
            operateUserId = base.getOperateUserId();
        }
    }
    TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
    String userName = sessionform.getUserid();
%>
<%@ include file="/WEB-INF/pages/wfworksheet/bureaudataUpdate/baseinputlinkhtmlnew.jsp" %>
<br/>
<table class="formTable">
    <input type="hidden" name="${sheetPageName}beanId" value="iBureaudataUpdateMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.bureaudataUpdate.model.BureaudataUpdateMain"/>
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.bureaudataUpdate.model.BureaudataUpdateLink"/>
    <c:if test="${taskName != 'HoldTask' }">
        <%if (operateType.equals("")) { %>
        <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${task.operateRoleId}"/>
        <%} else {%>
        <input type="hidden" name="${sheetPageName}toOrgRoleId" value="${preLink.operateRoleId}"/>
        <%} %>
    </c:if>

    <c:choose>
        <c:when test="${task.subTaskFlag == 'true'}">
            <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"
                   value="true"/>
        </c:when>
    </c:choose>


    <!-- 号段审核 -->
    <%if (taskName.equals("Assessor")) { %>
    <%if (operateType.equals("200") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HoldTask"/>
    <tr>
        <td colspan="4">
            <table class="formTable">
                <tr>
                    <td class="label" style="text-align:center">启始号段</td>
                    <td class="label" style="text-align:center">终止号段</td>
                    <td class="label" style="text-align:center">HLR名称</td>
                    <td class="label" style="text-align:center">HLR信令点</td>
                    <td class="label" style="text-align:center">HLR编号</td>
                </tr>
                <%
                    java.lang.Object obj = request.getAttribute("BureaudataBasicdeal");
                    System.out.println("====wanghao======");
                    if (obj != null) {
                        java.util.ArrayList baseList = (java.util.ArrayList) obj;
                        for (int i = 0; i < baseList.size(); i++) {
                            com.boco.eoms.datum.vo.TawBureaudataSegmenthlrVO bas = (com.boco.eoms.datum.vo.TawBureaudataSegmenthlrVO) baseList.get(i);
                %>
                <tr>
                    <td align="center"><%=bas.getBeginSegment() %>
                    </td>
                    <td align="center"><%=bas.getEndSegment() %>
                    </td>
                    <td align="center"><%=bas.getHlrName() %>
                    </td>
                    <td align="center"><%=bas.getHlrSignalId() %>
                    </td>
                    <td align="center"><%=bas.getHlrId() %>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
            </table>
        </td>
    </tr>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
		       <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                         alt="allowBlank:false,width:500,maxLength:2000,vtext:'请最多输入1000汉字'"
                         alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>

    <%
            }
        }
    %>

    <%if (operateType.equals("4")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4"/>
    <tr>
        <td class="label">
            <bean:message bundle="sheet" key="linkForm.remark"/>*
        </td>
        <td colspan="3">
		       <textarea name="${sheetPageName}remark" class="textarea max" id="${sheetPageName}remark"
                         alt="allowBlank:false,width:500,maxLength:2000,vtext:'请最多输入1000汉字'"
                         alt="width:'500px'">${sheetLink.remark}</textarea>
        </td>
    </tr>
    <c:choose>
        <c:when test="${empty fPreTaskName}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask"/>
        </c:when>
        <c:when test="${fPreTaskName == 'DraftTask'}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="RejectTask"/>
        </c:when>
        <c:when test="${taskName == 'HandleTask'}">
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AcceptTask"/>
        </c:when>
        <c:otherwise>
            <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="${fPreTaskName}"/>
        </c:otherwise>
    </c:choose>

    <%} %>
    <!-- 待归档 -->
    <%if (taskName.equals("HoldTask")) {%>
    <%if (operateType.equals("18")) { %>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over"/>
    <input type="hidden" name="${sheetPageName}status" id="${sheetPageName}status" value="1"/>

    <%
        String parentProcessName = com.boco.eoms.base.util.StaticMethod.nullObject2String(request.getAttribute("parentProcessName"));
        if (parentProcessName.equals("iBusinessPilotMainManager")) {
    %>
    <input type="hidden" name="${sheetPageName}ifReplyInvoke" id="${sheetPageName}ifReplyInvoke" value="true"/>
    <input type="hidden" name="${sheetPageName}invokePiid" id="${sheetPageName}invokePiid" value="${parentMain.piid}"/>
    <input type="hidden" name="${sheetPageName}invokeOperateName" id="${sheetPageName}invokeOperateName"
           value="backToBaseStationOpenYN"/>
    <input type="hidden" name="${sheetPageName}invokeProcessBeanId" id="${sheetPageName}invokeProcessBeanId"
           value="BaseStationOpenYN"/>
    <input type="hidden" name="${sheetPageName}invokeSheetId" id="${sheetPageName}invokeSheetId"
           value="${parentMain.id}"/>
    <input type="hidden" name="${sheetPageName}invokePhaseId" id="${sheetPageName}invokePhaseId"
           value="CityEleCallTask"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag"
           value="holdReplyProcess"/>
    <%} %>


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
    <%}%>

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

    <%}%>


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

<%if (taskName.equals("Assessor")) { %>
<% if (operateType.equals("200")) { %>
<fieldset id="link1">
    <legend>
        <bean:message bundle="bureaudataUpdate" key="role.toOrgName"/>
        <span id="roleName">:建单人
					 </span>
    </legend>
    <div class="x-form-item">
    </div>
</fieldset>
<%
        }
    }
%>

<%if (taskName.equals("HoldTask")) { %>
<%} %>

