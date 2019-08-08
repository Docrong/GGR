<%@ include file="/common/taglibs.jsp" %>
<%@page import="com.boco.eoms.base.util.ApplicationContextHolder" %>
<%@page import="com.boco.eoms.sheet.base.webapp.action.IBaseSheet" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseLink" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.businessupport.product.model.*" %>
<%@page import="java.util.*" %>
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
    TrancePoint support = (TrancePoint) request.getAttribute("support");
    IBaseSheet baseSheet = (IBaseSheet) ApplicationContextHolder.getInstance().getBean("ResourceConfirm");
    if (bl != null) {
        String prelinkid = com.boco.eoms.base.util.StaticMethod.nullObject2String(bl.getPreLinkId());
        if (!prelinkid.equals("")) {
            BaseLink base = baseSheet.getLinkService().getSingleLinkPO(prelinkid);
            operateUserId = base.getOperateUserId();
        }
    }
    List businesSupportList = (List) request.getAttribute("BusinesSupportList");
    TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
    String userName = sessionform.getUserid();
    String pwd = sessionform.getPassword();
%>
<%@ include file="/WEB-INF/pages/wfworksheet/resourceconfirm/baseinputlinkhtmlnew.jsp" %>
<br/>
<table class="formTable">
    <input type="hidden" name="${sheetPageName}beanId" value="iResourceConfirmMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.resourceconfirm.model.ResourceConfirmMain"/>
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.resourceconfirm.model.ResourceConfirmLink"/>
    <input type="hidden" name=businesSupportId value="${support.id}"/>
    <input type="hidden" name=orderSheetId value="${support.orderSheetId}"/>
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


    <!-- 需求分析 -->
    <%if (taskName.equals("AcceptTask")) { %>
    <%if (operateType.equals("200") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExplorateTask"/>
    <tr>
        <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkUrgency"/>*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}linkUrgency" id="${sheetPageName}linkUrgency"
                           initDicId="1010102" defaultValue="${sheetLink.linkUrgency}" alt="allowBlank:false"/>
        </td>
        <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkReqCompleteTime"/>*</td>
        <td class="content">
            <input type="text" class="text" name="${sheetPageName}linkReqCompleteTime" readonly="readonly"
                   id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetLink.linkReqCompleteTime)}"
                   onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>

        </td>
    </tr>
    <!-- 	              <td  class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkAcceptDept"/>*</td>
				  <td class="content">
	                 <input type="text"  class="text" name="${sheetPageName}linkAcceptDept" id="${sheetPageName}linkAcceptDept"  value="${sheetLink.linkAcceptDept}" alt="allowBlank:false,maxLength:50,vtext:'请填入受理部门，最多输入25字'"/>
				  </td>	 -->

    </tr>

    <tr>
        <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkSendSheetDesc"/>*</td>
        <td colspan="3">
            <textarea name="${sheetPageName}linkSendSheetDesc"
                      alt="allowBlank:false,maxLength:255,vtext:'请填入派单意见，最多输入255字符'" id="${sheetPageName}linkTestDesc"
                      class="textarea max">${sheetLink.linkSendSheetDesc}</textarea>
        </td>
    </tr>
    <%
            }
        }
    %>
    <!-- 现场勘查 -->
    <%if (taskName.equals("ExplorateTask")) { %>
    <%if (operateType.equals("201") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="MakeTask"/>
    <logic:present name="serviceList" scope="request">
        <tr>
            <td class="label">派发任务单</td>
            <td colspan='3'>
                <%int i = 0; %>
                <logic:iterate id="toServiceList" name="serviceList" type="java.util.Map"><%=++i%>
                    <input type="radio" name="${sheetPageName}checkTaskName" id="${sheetPageName}checkTaskName"
                           value="${toServiceList.taskName}" onclick='support()'>
                    <bean:write name="toServiceList" property="serviceCnName" scope="page"/>
                    <input type="hidden" name="${sheetPageName}serviceId" id="${sheetPageName}serviceId"
                           value="${toServiceList.id}"/>
                    <input type="hidden" name="${sheetPageName}specialtyId" id="${sheetPageName}specialtyId"
                           value="${toServiceList.specialtyId}"/>
                </logic:iterate>
                <input type="button" class="btn" value="派发任务单" onclick="javascript:openwin()"> <font color="RED">
                请按先后顺序派发任务单</font>
            </td>
        </tr>
    </logic:present>

    <tbody id='new1' style="display:none">
    <tr>
        <td class="label">电路接入点</td>
        <td colspan='3'>
            <%
                for (int i = 0; i < businesSupportList.size(); i++) {
                    TrancePoint business = (TrancePoint) businesSupportList.get(i);
            %>
            <input type="radio" name="${sheetPageName}supportId" id="${sheetPageName}supportId"
                   value=<%=business.getId() %>/>
            <%=business.getPortDetailAdd() %>
            <% }%>
        </td>
    </tr>
    </tbody>
    <!-- 		  	   <tr><td class="label">派发任务单</td>
			  	    <td colspan='3'>
			  	    <input type="radio" name="${sheetPageName}checkTaskName"  id ="${sheetPageName}checkTaskName" value="UserTask">派用户端资源勘查单
			  	    <input type="radio" name="${sheetPageName}checkTaskName"  id ="${sheetPageName}checkTaskName" value="AccessTask">派接入点资源勘查单
			  	    <input type="radio" name="${sheetPageName}checkTaskName"  id ="${sheetPageName}checkTaskName" value="CityTask">派城域网端口查询单
			  	    <input type="button" class="btn" value="派发任务单" onclick="javascript:openwin()"> </td>
			  	    </tr>
 -->
    <logic:present name="processTasksList" scope="request">
        <tr>
            <td colspan="4">
                <table class="listTable">
                    <tr>
                        <td class="label">
                            任务类型
                        </td>
                        <td class="label">
                            勘测地点
                        </td>
                        <td class="label">
                            处理人角色
                        </td>
                        <td class="label">
                            状态
                        </td>
                        <td class="label">
                            开始时间
                        </td>
                        <td class="label">
                            结束时间
                        </td>
                    </tr>
                    <logic:iterate id="toprocessTasksList" name="processTasksList"
                                   type="com.boco.eoms.businessupport.serviceprepare.model.ProcessTasks">
                        <tr>
                            <td>
                                <bean:write name="toprocessTasksList" property="serviceCnName" scope="page"/>
                            </td>
                            <td>
                                <bean:write name="toprocessTasksList" property="remark" scope="page"/>
                            </td>
                            <td>
                                <eoms:id2nameDB id="${toprocessTasksList.dealRoleId}"
                                                beanId="tawSystemSubRoleDao"/><eoms:id2nameDB
                                    id="${toprocessTasksList.dealRoleId}" beanId="tawSystemUserDao"/>&nbsp;
                            </td>
                            <td>
                                <c:if test="${toprocessTasksList.status=='2'}">
                                    未处理
                                </c:if>
                                <c:if test="${toprocessTasksList.status=='8'}">
                                    已处理
                                </c:if>
                            </td>
                            <td>
                                    ${eoms:date2String(toprocessTasksList.createtime)}
                            </td>
                            <td>
                                    ${eoms:date2String(toprocessTasksList.endtime)}
                            </td>
                        </tr>
                    </logic:iterate>
                </table>
            </td>
        </tr>
    </logic:present>
    <tr>
        <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkDealType"/>*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}linkDealType" id="${sheetPageName}linkDealType"
                           initDicId="1012204" defaultValue="${sheetLink.linkDealType}" alt="allowBlank:false"
                           onchange="dealType(this.value)"/>
        </td>
        <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkUrgency"/>*</td>
        <td class="content">
            <eoms:comboBox name="${sheetPageName}linkUrgency" id="${sheetPageName}linkUrgency"
                           initDicId="1010102" defaultValue="${sheetLink.linkUrgency}" alt="allowBlank:false"/>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkReqCompleteTime"/>*</td>
        <td colspan='3'>
            <input type="text" class="text" name="${sheetPageName}linkReqCompleteTime" readonly="readonly"
                   id="${sheetPageName}sheetCompleteLimit" value="${eoms:date2String(sheetLink.linkReqCompleteTime)}"
                   onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>

        </td>

    </tr>
    <tr>
        <td class="label">客户端工程能力确认*</td>
        <td colspan="3">
            <textarea name="${sheetPageName}mainBusinessDesc" alt="allowBlank:false,maxLength:255,vtext:'最多输入255字符'"
                      id="${sheetPageName}mainBusinessDesc"
                      class="textarea max">${sheetMain.mainBusinessDesc}</textarea>
        </td>
    </tr>
    <tr>
        <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkSendSheetDesc"/>*</td>
        <td colspan="3">
            <textarea name="${sheetPageName}linkSendSheetDesc" alt="allowBlank:false,maxLength:255,vtext:'最多输入255字符'"
                      id="${sheetPageName}linkTestDesc" class="textarea max">${sheetLink.linkSendSheetDesc}</textarea>
        </td>
    </tr>
    <%
            }
        }
    %>
    <!-- 用户端资源勘查反馈 -->
    <%if (taskName.equals("UserTask")) { %>
    <%if (operateType.equals("202") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over"/>
    <input type="hidden" name="${sheetPageName}processTasks_ParentLinkId" id="${sheetPageName}processTasks_ParentLinkId"
           value="${task.preLinkId}"/>
    <c:if test="${sheetMain.mainspecialtyType==101220101}">
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkCapabilityAffirm"/>*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}linkCapabilityAffirm" id="${sheetPageName}linkCapabilityAffirm"
                               initDicId="1012211" defaultValue="${sheetLink.linkCapabilityAffirm}"
                               alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkRemark"/></td>
            <td colspan="3">
                <textarea name="${sheetPageName}linkRemark" alt="allowBlank:true,maxLength:255,vtext:'请填入备注，最多输入255字符'"
                          id="${sheetPageName}linkRemark" class="textarea max">${sheetLink.linkRemark}</textarea>
            </td>
        </tr>
    </c:if>
    <c:if test="${sheetMain.mainspecialtyType==101220102}">
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkANetContact"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkANetContact"
                       id="${sheetPageName}linkANetContact" value="${sheetLink.linkANetContact}"
                       alt="allowBlank:false,maxLength:50,vtext:'请填入A点网络部联系人，最多输入25字'"/>
            </td>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkANetContactTel"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkANetContactTel"
                       id="${sheetPageName}linkANetContactTel" value="${sheetLink.linkANetContactTel}"
                       alt="allowBlank:false,maxLength:50,vtext:'请填入A点网络部门联系人电话，最多输入25字'"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkALongitude"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkALongitude"
                       id="${sheetPageName}linkALongitude" value="${sheetLink.linkALongitude}"
                       alt="allowBlank:false,maxLength:50,vtext:'请填入A点经度，最多输入25字'"/>
            </td>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkALatitude"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkALatitude" id="${sheetPageName}linkALatitude"
                       value="${sheetLink.linkALatitude}" alt="allowBlank:false,maxLength:50,vtext:'请填入A点纬度，最多输入25字'"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkBusinessBandwidth"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkBusinessBandwidth"
                       id="${sheetPageName}linkBusinessBandwidth" value="${sheetLink.linkBusinessBandwidth}"
                       alt="allowBlank:false,maxLength:50,vtext:'请填入业务带宽，最多输入25字'"/>
            </td>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkBusinessNumber"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkBusinessNumber"
                       id="${sheetPageName}linkBusinessNumber" value="${sheetLink.linkBusinessNumber}"
                       alt="allowBlank:false,maxLength:50,vtext:'请填入业务数量（传输条数），最多输入25字'"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkConnectType"/></td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkConnectType"
                       id="${sheetPageName}linkConnectType" value="${sheetLink.linkConnectType}"
                       alt="allowBlank:true,maxLength:50,vtext:'请填入连接方式，最多输入25字'"/>
            </td>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkTunnel"/></td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkTunnel" id="${sheetPageName}linkTunnel"
                       value="${sheetLink.linkTunnel}" alt="allowBlank:true,maxLength:50,vtext:'请填入隧道方式，最多输入25字'"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkGroupUserIP"/></td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkGroupUserIP"
                       id="${sheetPageName}linkGroupUserIP" value="${sheetLink.linkGroupUserIP}"
                       alt="allowBlank:true,maxLength:50,vtext:'请填入集团用户对端IP，最多输入25字'"/>
            </td>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkRadiusAddress"/></td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkRadiusAddress"
                       id="${sheetPageName}linkRadiusAddress" value="${sheetLink.linkRadiusAddress}"
                       alt="allowBlank:true,maxLength:50,vtext:'请填入radius地址，最多输入25字'"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkGroupUserIPReq"/></td>
            <td class="content" colspan="3">
                <input type="text" class="text" name="${sheetPageName}linkGroupUserIPReq"
                       id="${sheetPageName}linkGroupUserIPReq" value="${sheetLink.linkGroupUserIPReq}"
                       alt="allowBlank:true,maxLength:50,vtext:'请填入集团用户IP地址端需求，最多输入25字'"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkAPNName"/></td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkAPNName" id="${sheetPageName}linkAPNName"
                       value="${sheetLink.linkAPNName}"
                       alt="allowBlank:true,maxLength:50,vtext:'请填入linkAPNName，最多输入25字'"/>
            </td>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkIPDistribution"/></td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkIPDistribution"
                       id="${sheetPageName}linkIPDistribution" value="${sheetLink.linkIPDistribution}"
                       alt="allowBlank:true,maxLength:50,vtext:'请填入IP地址分配方式，最多输入25字'"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkRemark"/></td>
            <td colspan="3">
                <textarea name="${sheetPageName}linkRemark" alt="allowBlank:true,maxLength:255,vtext:'请填入备注，最多输入255字符'"
                          id="${sheetPageName}linkRemark" class="textarea max">${sheetLink.linkRemark}</textarea>
            </td>
        </tr>
    </c:if>
    <tr>
        <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkRemark"/></td>
        <td colspan="3">
            <textarea name="${sheetPageName}linkRemark" alt="allowBlank:true,maxLength:255,vtext:'请填入备注，最多输入255字符'"
                      id="${sheetPageName}linkRemark" class="textarea max">${sheetLink.linkRemark}</textarea>
        </td>
    </tr>
    <%
            }
        }
    %>
    <!-- 接入点资源勘反馈 -->
    <%if (taskName.equals("AccessTask")) { %>
    <%if (operateType.equals("203") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over"/>
    <input type="hidden" name="${sheetPageName}processTasks_ParentLinkId" id="${sheetPageName}processTasks_ParentLinkId"
           value="${task.preLinkId}"/>
    <c:if test="${sheetMain.mainspecialtyType==101220101}">
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkCapabilityAffirmN"/>*</td>
            <td class="content" colspan="3">
                <eoms:comboBox name="${sheetPageName}linkCapabilityAffirm" id="${sheetPageName}linkCapabilityAffirm"
                               initDicId="1012210" defaultValue="${sheetLink.linkCapabilityAffirm}"
                               alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkAccessPointName"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkAccessPointName"
                       id="${sheetPageName}linkAccessPointName" value="${sheetLink.linkAccessPointName}"
                       alt="allowBlank:false,maxLength:50,vtext:'请填入距离最近的接入点名称，最多输入25字'"/>
            </td>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkAccessPDistance"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkAccessPDistance"
                       id="${sheetPageName}linkAccessPDistance" value="${sheetLink.linkAccessPDistance}"
                       alt="allowBlank:false,maxLength:50,vtext:'请填入与最近的接入点之间的距离，最多输入25字'"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkRemark"/></td>
            <td colspan="3">
                <textarea name="${sheetPageName}linkRemark" alt="allowBlank:true,maxLength:255,vtext:'请填入备注，最多输入255字符'"
                          id="${sheetPageName}linkRemark" class="textarea max">${sheetLink.linkRemark}</textarea>
            </td>
        </tr>
    </c:if>
    <c:if test="${sheetMain.mainspecialtyType==101220102}">
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkAccessPointName"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkAccessPointName"
                       id="${sheetPageName}linkAccessPointName" value="${sheetLink.linkAccessPointName}"
                       alt="allowBlank:false,maxLength:50,vtext:'请填入距离最近的接入点名称，最多输入25字'"/>
            </td>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkAccessPDistance"/>*</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkAccessPDistance"
                       id="${sheetPageName}linkAccessPDistance" value="${sheetLink.linkAccessPDistance}"
                       alt="allowBlank:false,maxLength:50,vtext:'请填入与最近的接入点之间的距离，最多输入25字'"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkRemark"/></td>
            <td colspan="3">
                <textarea name="${sheetPageName}linkRemark" alt="allowBlank:true,maxLength:255,vtext:'请填入备注，最多输入255字符'"
                          id="${sheetPageName}linkRemark" class="textarea max">${sheetLink.linkRemark}</textarea>
            </td>
        </tr>
    </c:if>


    <%
            }
        }
    %>
    <!-- 城域网端口查询反馈 -->
    <%if (taskName.equals("CityTask")) { %>
    <%if (operateType.equals("204") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over"/>
    <input type="hidden" name="${sheetPageName}processTasks_ParentLinkId" id="${sheetPageName}processTasks_ParentLinkId"
           value="${task.preLinkId}"/>
    <c:if test="${sheetMain.mainspecialtyType==101220101}">
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkCapabilityAffirmN"/>*</td>
            <td colspan="3">
                <eoms:comboBox name="${sheetPageName}linkCapabilityAffirm" id="${sheetPageName}linkCapabilityAffirm"
                               initDicId="1012210" defaultValue="${sheetLink.linkCapabilityAffirm}"
                               alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkRemark"/></td>
            <td colspan="3">
                <textarea name="${sheetPageName}linkRemark" alt="allowBlank:true,maxLength:255,vtext:'请填入备注，最多输入255字符'"
                          id="${sheetPageName}linkRemark" class="textarea max">${sheetLink.linkRemark}</textarea>
            </td>
        </tr>
    </c:if>
    <c:if test="${sheetMain.mainspecialtyType==101220102}">
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkGPRSCoreAddress"/>*</td>
            <td class="content" colspan="3">
                <input type="text" class="text" name="${sheetPageName}linkGPRSCoreAddress"
                       id="${sheetPageName}linkGPRSCoreAddress" value="${sheetLink.linkGPRSCoreAddress}"
                       alt="allowBlank:false,maxLength:50,vtext:'请填入GPRS核心网详细地址，最多输入25字'"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkGPRSCoreContact"/></td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkGPRSCoreContact"
                       id="${sheetPageName}linkGPRSCoreContact" value="${sheetLink.linkGPRSCoreContact}"
                       alt="allowBlank:true,maxLength:50,vtext:'请填入GPRS核心网联系人，最多输入25字'"/>
            </td>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkGPRSCoreContactTel"/></td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkGPRSCoreContactTel"
                       id="${sheetPageName}linkGPRSCoreContactTel" value="${sheetLink.linkGPRSCoreContactTel}"
                       alt="allowBlank:true,maxLength:50,vtext:'请填入GPRS核心网联系人电话，最多输入25字'"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkGPRSCoreLongitude"/></td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkGPRSCoreLongitude"
                       id="${sheetPageName}linkGPRSCoreLongitude" value="${sheetLink.linkGPRSCoreLongitude}"
                       alt="allowBlank:true,maxLength:50,vtext:'请填入GPRS核心网经度，最多输入25字'"/>
            </td>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkGPRSCoreLatitude"/></td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkGPRSCoreLatitude"
                       id="${sheetPageName}linkGPRSCoreLatitude" value="${sheetLink.linkGPRSCoreLatitude}"
                       alt="allowBlank:true,maxLength:50,vtext:'请填入GPRS核心网纬度，最多输入25字'"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkRemark"/></td>
            <td colspan="3">
                <textarea name="${sheetPageName}linkRemark" alt="allowBlank:true,maxLength:255,vtext:'请填入备注，最多输入255字符'"
                          id="${sheetPageName}linkRemark" class="textarea max">${sheetLink.linkRemark}</textarea>
            </td>
        </tr>
    </c:if>
    <%
            }
        }
    %>
    <!-- 传输设备查勘 -->
    <%if (taskName.equals("TransfereTask")) { %>
    <%if (operateType.equals("207") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over"/>
    <input type="hidden" name="${sheetPageName}processTasks_ParentLinkId" id="${sheetPageName}processTasks_ParentLinkId"
           value="${task.preLinkId}"/>


    <tr>
        <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkRemark"/></td>
        <td colspan="3">
            <textarea name="${sheetPageName}linkRemark" alt="allowBlank:true,maxLength:255,vtext:'请填入备注，最多输入255字符'"
                      id="${sheetPageName}linkRemark" class="textarea max">${sheetLink.linkRemark}</textarea>
        </td>
    </tr>

    <%
            }
        }
    %>
    <!-- 传输线路查勘 -->
    <%if (taskName.equals("TransferlTask")) { %>
    <%if (operateType.equals("208") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over"/>
    <input type="hidden" name="${sheetPageName}processTasks_ParentLinkId" id="${sheetPageName}processTasks_ParentLinkId"
           value="${task.preLinkId}"/>


    <tr>
        <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkRemark"/></td>
        <td colspan="3">
            <textarea name="${sheetPageName}linkRemark" alt="allowBlank:true,maxLength:255,vtext:'请填入备注，最多输入255字符'"
                      id="${sheetPageName}linkRemark" class="textarea max">${sheetLink.linkRemark}</textarea>
        </td>
    </tr>

    <%
            }
        }
    %>
    <%if (taskName.equals("LauguageTask")) { %>
    <%if (operateType.equals("210") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over"/>
    <input type="hidden" name="${sheetPageName}processTasks_ParentLinkId" id="${sheetPageName}processTasks_ParentLinkId"
           value="${task.preLinkId}"/>


    <tr>
        <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkRemark"/></td>
        <td colspan="3">
            <textarea name="${sheetPageName}linkRemark" alt="allowBlank:true,maxLength:255,vtext:'请填入备注，最多输入255字符'"
                      id="${sheetPageName}linkRemark" class="textarea max">${sheetLink.linkRemark}</textarea>
        </td>
    </tr>

    <%
            }
        }
    %>
    <%if (taskName.equals("CaiXinTask")) { %>
    <%if (operateType.equals("220") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over"/>
    <input type="hidden" name="${sheetPageName}processTasks_ParentLinkId" id="${sheetPageName}processTasks_ParentLinkId"
           value="${task.preLinkId}"/>


    <tr>
        <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkRemark"/></td>
        <td colspan="3">
            <textarea name="${sheetPageName}linkRemark" alt="allowBlank:true,maxLength:255,vtext:'请填入备注，最多输入255字符'"
                      id="${sheetPageName}linkRemark" class="textarea max">${sheetLink.linkRemark}</textarea>
        </td>
    </tr>

    <%
            }
        }
    %>
    <!-- 组网方案制作 -->
    <%if (taskName.equals("MakeTask")) { %>
    <%if (operateType.equals("205") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="AuditTask"/>

    <c:if test="${sheetMain.mainspecialtyType==101220101}">
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkDealResult"/>*</td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}linkDealResult" id="${sheetPageName}linkDealResult"
                               initDicId="1012205" defaultValue="${sheetLink.linkDealResult}" alt="allowBlank:false"/>
            </td>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkDealDescription"/>*</td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}linkDealDescription" id="${sheetPageName}linkDealDescription"
                               initDicId="1012206" defaultValue="${sheetLink.linkDealDescription}"
                               alt="allowBlank:false" onchange="dealDescription(this.value)"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkPreInvestment"/>（单位：万元）*
            </td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkPreInvestment"
                       id="${sheetPageName}linkPreInvestment" value="${sheetLink.linkPreInvestment}"
                       alt="allowBlank:false,maxLength:50,vtext:'请填入预计投资，最多输入25字'"/>
            </td>
            <td class="label"><bean:message bundle="resourceconfirm"
                                            key="resourceconfirm.linkPreCompleteDays"/>（单位：天）*
            </td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}linkPreCompleteDays" id="${sheetPageName}linkPreCompleteDays"
                               initDicId="1012209" defaultValue="${sheetLink.linkPreCompleteDays}"
                               alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkIfOpenCondition"/>*</td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}linkIfOpenCondition" id="${sheetPageName}linkIfOpenCondition"
                               initDicId="1012207" defaultValue="${sheetLink.linkIfOpenCondition}"
                               alt="allowBlank:false"/>
            </td>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkIfPreHold"/></td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}linkIfPreHold" id="${sheetPageName}linkIfPreHold"
                               initDicId="1012208" defaultValue="${sheetLink.linkIfPreHold}" alt="allowBlank:true"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkDeviceName"/></td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkDeviceName"
                       id="${sheetPageName}linkDeviceName" value="${sheetLink.linkDeviceName}"
                       alt="allowBlank:true,maxLength:50,vtext:'请填入用户端设备名称，最多输入25字'"/>
            </td>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkDevicePort"/></td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkDevicePort"
                       id="${sheetPageName}linkDevicePort" value="${sheetLink.linkDevicePort}"
                       alt="allowBlank:true,maxLength:50,vtext:'请填入用户端设备端口，最多输入25字'"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkIPAddressOne"/></td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkIPAddressOne"
                       id="${sheetPageName}linkIPAddressOne" value="${sheetLink.linkIPAddressOne}"
                       alt="allowBlank:true,maxLength:50,vtext:'请填入IP地址1（账号），最多输入25字'"/>
            </td>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkIPAddressTwo"/></td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkIPAddressTwo"
                       id="${sheetPageName}linkIPAddressTwo" value="${sheetLink.linkIPAddressTwo}"
                       alt="allowBlank:true,maxLength:50,vtext:'请填入IP地址2（账号），最多输入25字'"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkMask"/></td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkMask" id="${sheetPageName}linkMask"
                       value="${sheetLink.linkMask}" alt="allowBlank:true,maxLength:50,vtext:'请填入子网掩码，最多输入25字'"/>
            </td>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkGateway"/></td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkGateway" id="${sheetPageName}linkGateway"
                       value="${sheetLink.linkGateway}" alt="allowBlank:true,maxLength:50,vtext:'请填入网关，最多输入25字'"/>
            </td>
        </tr>
    </c:if>
    <c:if test="${sheetMain.mainspecialtyType==101220102}">
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkDealDescriptionA"/>*</td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}linkDealDescriptionA" id="${sheetPageName}linkDealDescriptionA"
                               initDicId="1012206" defaultValue="${sheetLink.linkDealDescriptionA}"
                               alt="allowBlank:false" onchange="dealDescription(this.value)"/>
            </td>
            <td class="label"><bean:message bundle="resourceconfirm"
                                            key="resourceconfirm.linkPreCompleteDaysA"/>（单位：天）*
            </td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}linkPreCompleteDaysA" id="${sheetPageName}linkPreCompleteDaysA"
                               initDicId="1012209" defaultValue="${sheetLink.linkPreCompleteDaysA}"
                               alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkIfOpenConditionA"/>*</td>
            <td class="content" colspan="3">
                <eoms:comboBox name="${sheetPageName}linkIfOpenConditionA" id="${sheetPageName}linkIfOpenConditionA"
                               initDicId="1012207" defaultValue="${sheetLink.linkIfOpenConditionA}"
                               alt="allowBlank:false"/>
            </td>
        </tr>
    </c:if>
    <c:if test="${sheetMain.mainSpecifyType== '101230104'}">
        <tr>
            <td class="label">预计投资</td>
            <td colspan="3">
                <textarea name="${sheetPageName}mainProductsNumber"
                          alt="allowBlank:true,maxLength:255,vtext:'请填描述，最多输入255字符'"
                          id="${sheetPageName}mainProductsNumber"
                          class="textarea max">${sheetMain.mainProductsNumber}</textarea>
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
            <!-- 组网方案拓扑图  -->
            <td class="label">技术方案*</td>
            <td colspan="3">
                <eoms:attachment name="sheetLink" property="linkProgramTopology" scope="request"
                                 idField="${sheetPageName}linkProgramTopology" appCode="resourceconfirm"
                                 alt="allowBlank:false"/>
            </td>
        </tr>
    </c:if>
    <c:if test="${sheetMain.mainSpecifyType != '101230104'}">
        <tr>

        <td class="label">综管方案设计</td>


        <td colspan="">


        <c:if test="${sheetMain.mainSpecifyType==101230102}">
            <tr>
                <td class="label">方案设计</td>
                <td colspan="">

                    <html:button styleClass="btn" property="" styleId="method.save"
                                 onclick="javascript:optPlan();">
                        方案设计
                    </html:button>
                </td>
                <td colspan="2">

                    <html:button styleClass="btn" property="" styleId="method.save"
                                 onclick="javascript:viewPlan();">
                        方案预览
                    </html:button>
                </td>
            </tr>

        </c:if>

        <tr>
            <td class="label">预计投资</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}mainProductsNumber"
                       id="${sheetPageName}mainProductsNumber" value="${sheetMain.mainProductsNumber}"
                       alt="allowBlank:false"/>
            </td>
            <td class="label">预计完成天数</td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}mainProductsSerialNum"
                       id="${sheetPageName}mainProductsSerialNum" value="${sheetMain.mainProductsSerialNum}"
                       alt="allowBlank:false"/>
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
            <!-- 组网方案拓扑图  -->
            <td class="label">设计方案*</td>
            <td colspan="3">
                <eoms:attachment name="ordersheet" property="accessories"
                                 scope="request" idField="${sheetPageName}accessories" appCode="resourceconfirm"
                                 alt="allowBlank:false"/>
            </td>
        </tr>
    </c:if>

    <%
            }
        }
    %>
    <!-- 方案审核 -->
    <%if (taskName.equals("AuditTask")) { %>
    <%if (operateType.equals("209") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value=HandleTask/>
    <tr>
        <td class="label">审核结果*</td>
        <td colspan="3">
            <eoms:comboBox name="${sheetPageName}linkDealResult" id="${sheetPageName}linkDealResult"
                           initDicId="1012212" defaultValue="${sheetLink.linkDealResult}" alt="allowBlank:false"
                           onchange="ifAuditPass(this.value)"/>
        </td>
    </tr>
    <tr>
        <td class="label">审核意见*</td>
        <td colspan="3">
            <textarea name="${sheetPageName}linkDealDesc" alt="allowBlank:false,maxLength:255,vtext:'请填入处理意见，最多输入255字符'"
                      id="${sheetPageName}linkDealDesc" class="textarea max">${sheetLink.linkDealDesc}</textarea>
        </td>
    </tr>
    <%
            }
        }
    %>
    <!-- 处理回复 -->
    <%if (taskName.equals("HandleTask")) { %>
    <%if (operateType.equals("206") || operateType.equals("11")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="<%=operateType%>"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value=HoldTask/>

    <tr>
        <td class="label"><bean:message bundle="resourceconfirm" key="resourceconfirm.linkDealDesc"/>*</td>
        <td colspan="3">
            <textarea name="${sheetPageName}linkDealDesc" alt="allowBlank:false,maxLength:255,vtext:'请填入处理意见，最多输入255字符'"
                      id="${sheetPageName}linkDealDesc" class="textarea max">${sheetLink.linkDealDesc}</textarea>
        </td>
    </tr>
    <%
            }
        }
    %>
    <%if (operateType.equals("4")) { %>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="4"/>

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
    <tr>
        <td class="label">
            <bean:message bundle="resourceconfirm" key="resourceconfirm.linkDealDesc"/>*
        </td>
        <td colspan="3">
		        <textarea name="${sheetPageName}linkDealDesc" class="textarea max" id="${sheetPageName}linkDealDesc"
                          alt="allowBlank:false,maxLength:255,width:500,vtext:'请填入处理意见，最多输入125字'"
                          alt="width:'500px'">${sheetLink.linkDealDesc}</textarea>
        </td>
    </tr>

    <%} %>
    <!-- 待归档 -->
    <%if (taskName.equals("HoldTask")) {%>
    <%if (operateType.equals("18")) { %>
    <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
    <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType" value="18"/>
    <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over"/>
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

<%if (taskName.equals("AcceptTask")) { %>
<% if (operateType.equals("200")) { %>
<fieldset id="link1">
    <legend>
        <bean:message bundle="resourceconfirm" key="role.toOrgName"/>
        <span id="roleName">:资源勘查角色
					 </span>
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="test1" type="role" roleId="928" flowId="311" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
        />
    </div>
</fieldset>

<%
        }
    }
%>
<%if (taskName.equals("ExplorateTask")) { %>
<% if (operateType.equals("201")) { %>
<fieldset id="link1">
    <legend>
        <bean:message bundle="resourceconfirm" key="role.toOrgName"/>
        <span id="roleName">:需求提出人
					 </span>
    </legend>
    <div class="x-form-item">
        <eoms:id2nameDB id="${sheetMain.sendRoleId}" beanId="tawSystemSubRoleDao"/>
        <eoms:id2nameDB id="${sheetMain.sendRoleId}" beanId="tawSystemUserDao"/>
        <eoms:id2nameDB id="${sheetMain.sendRoleId}" beanId="tawSystemDeptDao"/>&nbsp;&nbsp; </br>
    </div>
</fieldset>
<fieldset id="link2">
    <legend>
        <bean:message bundle="resourceconfirm" key="role.toOrgName"/>
        <span id="roleName">:组网方案制作角色
					 </span>
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="test1" type="role" roleId="929" flowId="311" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
        />
    </div>
</fieldset>
<%
        }
    }
%>
<%if (taskName.equals("MakeTask")) { %>
<% if (operateType.equals("205")) { %>
<fieldset>
    <legend>
        <bean:message bundle="resourceconfirm" key="role.toOrgName"/>
        <span id="roleName">:方案审核人
					 </span>
    </legend>
    <div class="x-form-item">
        <eoms:chooser id="test1" type="role" roleId="4059" flowId="311" config="{returnJSON:true,showLeader:true}"
                      category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发对象'},{id:'copyPerformer',childType:'user,subrole',limit:'none',text:'抄送'}]"
        />
    </div>
</fieldset>

<%} %>
<%} %>
<%if (taskName.equals("AuditTask")) { %>
<% if (operateType.equals("209")) { %>
<fieldset id="link1">
    <legend>
        <bean:message bundle="resourceconfirm" key="role.toOrgName"/>
        <span id="roleName">:需求提出人
					 </span>
    </legend>
    <div class="x-form-item">
        <eoms:id2nameDB id="${sheetMain.sendRoleId}" beanId="tawSystemSubRoleDao"/>
        <eoms:id2nameDB id="${sheetMain.sendRoleId}" beanId="tawSystemUserDao"/>
        <eoms:id2nameDB id="${sheetMain.sendRoleId}" beanId="tawSystemDeptDao"/>&nbsp;&nbsp; </br>
    </div>
</fieldset>
<fieldset id="link2">
    <legend>
        <bean:message bundle="resourceconfirm" key="role.toOrgName"/>
        <span id="roleName">:方案制作人
					 </span>
    </legend>
</fieldset>
<%} %>
<%} %>
<%if (taskName.equals("HandleTask")) { %>
<% if (operateType.equals("206")) { %>
<fieldset>
    <legend>
        <bean:message bundle="resourceconfirm" key="role.toOrgName"/>
        <span id="roleName">:需求提出人
					 </span>
    </legend>
    <div class="x-form-item">
        <eoms:id2nameDB id="${sheetMain.sendRoleId}" beanId="tawSystemSubRoleDao"/>
        <eoms:id2nameDB id="${sheetMain.sendRoleId}" beanId="tawSystemUserDao"/>
        <eoms:id2nameDB id="${sheetMain.sendRoleId}" beanId="tawSystemDeptDao"/>&nbsp;&nbsp; </br>
    </div>
</fieldset>

<%} %>
<%} %>

<%if (taskName.equals("HoldTask")) { %>


<%} %>

<script type="text/javascript">
    function ifAuditPass(input) {
        if ('<%=operateType%>' != '11' && '<%=operateType%>' != '55') {
            if (input == "101221201") {
                $('${sheetPageName}phaseId').value = 'HandleTask';
                $('${sheetPageName}operateType').value = '2091';
                eoms.$('link1').show();
                eoms.$('link2').hide();

            } else if (input == "101221202") {
                $('${sheetPageName}phaseId').value = 'MakeTask';
                $('${sheetPageName}operateType').value = '2092';
                eoms.$('link1').hide();
                eoms.$('link2').show();
            } else {
                $('${sheetPageName}phaseId').value = '';
                $('${sheetPageName}operateType').value = '209';
                eoms.$('link2').hide();
                eoms.$('link1').hide();
            }
        }
    }

    if ('<%=operateType%>' == '209' || ('${taskName}' == 'AuditTask' && '<%=operateType%>' == '55')) {
        ifAuditPass($("${sheetPageName}linkDealResult").value);
    }

    function dealType(linkDealType) {
        if (linkDealType == '101220401') {
            $('${sheetPageName}phaseId').value = 'MakeTask';
            $('${sheetPageName}operateType').value = '2014';
            chooser_test1.enable();
            eoms.$('link1').hide();
            eoms.$('link2').show();


        } else if (linkDealType == '101220402') {
            $('${sheetPageName}phaseId').value = 'HandleTask';
            $('${sheetPageName}operateType').value = '2015';
            chooser_test1.disable();
            eoms.$('link2').hide();
            eoms.$('link1').show();

        } else {
            $('${sheetPageName}phaseId').value = 'MakeTask';
            $('${sheetPageName}operateType').value = '201';
            eoms.$('link1').hide();
            eoms.$('link2').hide();

        }
    }

    <%if(taskName.equals("ExplorateTask") && (operateType.equals("201") || operateType.equals("11"))){%>
    dealType($('${sheetPageName}linkDealType').value);
    <%}%>

    function dealDescription(linkDealDescription) {
        if ("${sheetMain.mainspecialtyType}" == "101220101") {
            if (linkDealDescription == '101220601') {
                $('${sheetPageName}linkIfOpenCondition').value = '101220701';
            } else if (linkDealDescription == '101220602' || linkDealDescription == '101220603' || linkDealDescription == '101220604') {
                $('${sheetPageName}linkIfOpenCondition').value = '101220702';
            } else {
                $('${sheetPageName}linkIfOpenCondition').value = '';
            }
        }
        if ("${sheetMain.mainspecialtyType}" == "101220102") {
            if (linkDealDescription == '101220601') {
                $('${sheetPageName}linkIfOpenConditionA').value = '101220701';
            } else if (linkDealDescription == '101220602' || linkDealDescription == '101220603' || linkDealDescription == '101220604') {
                $('${sheetPageName}linkIfOpenCondition').value = '101220702';
            } else {
                $('${sheetPageName}linkIfOpenConditionA').value = '';
            }
        }
    }

    <%if(taskName.equals("MakeTask") && (operateType.equals("205") || operateType.equals("11"))){%>
    if ("${sheetMain.mainspecialtyType}" == "101220101") {
        dealDescription($('${sheetPageName}linkDealDescription').value);
    }
    if ("${sheetMain.mainspecialtyType}" == "101220102") {
        dealDescription($('${sheetPageName}linkDealDescriptionA').value);
    }
    <%}%>

    function openwin() {
        var put1 = document.getElementsByName("${sheetPageName}checkTaskName");
        var put2 = document.getElementsByName("${sheetPageName}serviceId");
        var put3 = document.getElementsByName("${sheetPageName}specialtyId");

        var put4 = document.getElementsByName("${sheetPageName}supportId");

        var supportId;
        for (var i = 0; i < put4.length; i++) {
            if (put4[i].checked) {
                supportId = put4[i].value;
            }
        }

        var phaseId = "";
        var serviceId = "";
        var serviceCnName = "";
        var operateType = "";
        for (var i = 0; i < put1.length; i++) {
            if (put1[i].checked) {
                phaseId = put1[i].value;
                serviceId = put2[i].value
                specialtyId = put3[i].value
            }
        }

        if ((supportId == undefined) && (phaseId == 'UserTask' || phaseId == 'AccessTask')) {
            alert('请选择接入点');
            return false;
        }
        if (phaseId == "") {
            alert("请选择需要派发的任务单！");
        } else {
            var url = "${app}/sheet/resourceconfirm/resourceconfirm.do?method=showInitializeTaskPage&sheetKey=${sheetMain.id}&serviceId=" + serviceId + "&supportId=" + supportId + "&specialtyId=" + specialtyId + "&phaseId=" + phaseId + "&taskName=${taskName}&operateRoleId=${sheetLink.operateRoleId}&parentTaskId=${task.id}&preLinkId=${preLink.id}";
            //window.open(url, 'newwindow', 'height=800, width=1000, top=200, left=200,toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=no, status=no');
            window.showModalDialog(url, "", "dialogWidth=1200px;dialogHeight=100px;help:no;resizable:yes;status:no;dialogWidth=1024px;dialogHeight=700px");
            //window.location.href   =   window.location.href;
            window.location.href = "${app}/sheet/resourceconfirm/resourceconfirm.do?method=showDetailPage&sheetKey=${sheetMain.id}&taskId=${task.id}&taskName=${taskName}&operateRoleId=${sheetLink.operateRoleId}&TKID=${sheetLink.tkid}&piid=${sheetMain.piid}&taskStatus=${taskStatus}&preLinkId=${preLink.id}";
        }
    }

    function popupIrmsPreSurvey() {
        var userName = "<%=userName%>";
        var pwd = "<%=pwd%>";
        var cityCnName = "<eoms:id2nameDB id='${sheetMain.mainBelongCity}' beanId='tawSystemAreaDao'/>";
        var gisUrl = encodeURI("${app}/sheet/resourceconfirm./resourceconfirm..do?method=showTnmsPage&type=1&cityCnName=" + cityCnName + "");
        var params = window.showModalDialog(gisUrl, "", "dialogWidth:" + screen.width * 1.0 + "px;dialogHeight:" + screen.height * 0.9 + "px");
        if (params != null) {
            $('${sheetPageName}fiberEquipName').value = params["fiberEquipName"];
            $('${sheetPageName}fiberEquipCode').value = params["fiberEquipCode"];
            $('${sheetPageName}siteEquipCode').value = params["siteEquipCode"];
            $('${sheetPageName}siteName').value = params["siteName"];
            //$('${sheetPageName}accessSiteIden').value = params["accessSiteIden"];
        }

    }

    function support() {
        var task = document.getElementsByName("${sheetPageName}checkTaskName");
        var taskName;
        for (var i = 0; i < task.length; i++) {
            if (task[i].checked) {
                taskName = task[i].value;
            }
        }
        if (taskName == 'UserTask' || taskName == 'AccessTask') {
            eoms.form.enableArea('new1');
        } else {
            eoms.form.disableArea('new1', true);
        }

    }

    function viewPlan() {
        window.open('http://10.131.62.59:8080/Fulfillment/flw/jkkt/pre/viewOrderInfoEoms.action?orderId=${sheetMain.orderSheetId}');
    }

    function optPlan() {
        window.open('http://10.131.62.59:8080/Fulfillment/flw/jkkt/pre/findTaskRespInfoEoms.action?workid=5124&orderId=${sheetMain.orderSheetId}');
    }
</script>

