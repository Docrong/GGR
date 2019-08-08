<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
    com.boco.eoms.sheet.businessimplementsms.model.BusinessImplementSmsLink businessimplementsmsLink = (com.boco.eoms.sheet.businessimplementsms.model.BusinessImplementSmsLink) request.getAttribute("sheetLink");
    java.lang.String taskName = com.boco.eoms.base.util.StaticMethod.nullObject2String(businessimplementsmsLink.getActiveTemplateId());
    java.lang.String operateType = java.lang.String.valueOf(com.boco.eoms.base.util.StaticMethod.nullObject2String(businessimplementsmsLink.getOperateType()));
%>
<script type="text/javascript">
    var frm = document.forms[0];

    function saveDealTemplate(type) {
        var form = document.forms[0];
        if (form.templateName.value == "") {

            return false;
        }
        form.action = "${app}/sheet/businessimplementsms/businessimplementsms.do?method=saveDealTemplate&type=dealTemplateManage&dealTemplateId=${sheetLink.id}";
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
<%@ include file="/WEB-INF/pages/wfworksheet/businessimplementsms/baseinputlinkhtmlnew.jsp" %>
<html:form action="/businessimplementsms.do" styleId="theform">
    <br/>
    <input type="hidden" name="${sheetPageName}beanId" value="iBusinessImplementSmsMainManager"/>
    <input type="hidden" name="${sheetPageName}mainClassName"
           value="com.boco.eoms.sheet.businessimplementsms.model.businessimplementsmsMain"/>
    <input type="hidden" name="${sheetPageName}linkClassName"
           value="com.boco.eoms.sheet.businessimplementsms.model.businessimplementsmsLink"/>
    <table class="formTable">
        <caption><bean:message bundle="businessimplementsms" key="businessimplementsms.header"/></caption>
        <tr>
            <td class="label">
                <bean:message bundle="sheet" key="input.templateName"/>
            </td>
            <td colspan="3">
                <input type="text" name="templateName" class="text max" id="templateName"
                       value="${sheetLink.templateName}"/>
            </td>
        </tr>

        <!-- 需求分析 -->
        <%if (taskName.equals("AcceptTask")) { %>
        <%if (operateType.equals("200") || operateType.equals("11")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType%>"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="ExplorateTask"/>
        <tr>
            <td class="label"><bean:message bundle="businessimplementsms" key="businessimplementsms.linkUrgency"/>*</td>
            <td class="content" colspan="3">
                <eoms:comboBox name="${sheetPageName}linkUrgency" id="${sheetPageName}linkUrgency"
                               initDicId="1010102" defaultValue="${sheetLink.linkUrgency}" alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="businessimplementsms"
                                            key="businessimplementsms.linkReqCompleteTime"/>*
            </td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkReqCompleteTime" readonly="readonly"
                       id="${sheetPageName}sheetCompleteLimit"
                       value="${eoms:date2String(sheetLink.linkReqCompleteTime)}"
                       onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>

            </td>
            <td class="label"><bean:message bundle="businessimplementsms" key="businessimplementsms.linkAcceptDept"/>*
            </td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkAcceptDept"
                       id="${sheetPageName}linkAcceptDept" value="${sheetLink.linkAcceptDept}"
                       alt="allowBlank:false,maxLength:50,vtext:'请填入受理部门，最多输入25字'"/>
            </td>
        </tr>

        <tr>
            <td class="label"><bean:message bundle="businessimplementsms" key="businessimplementsms.linkSendSheetDesc"/>*</td>
            <td colspan="3">
                <textarea name="${sheetPageName}linkSendSheetDesc"
                          alt="allowBlank:false,maxLength:255,vtext:'请填入派单意见，最多输入255字符'"
                          id="${sheetPageName}linkTestDesc"
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
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType%>"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="MakeTask"/>
        <logic:present name="serviceList" scope="request">
            <tr>
                <td class="label">派发任务单</td>
                <td colspan='3'>
                    <logic:iterate id="toServiceList" name="serviceList" type="java.util.Map">
                        <input type="radio" name="${sheetPageName}checkTaskName" id="${sheetPageName}checkTaskName"
                               value="${toServiceList.taskName}">
                        <bean:write name="toServiceList" property="serviceCnName" scope="page"/>
                        <input type="hidden" name="${sheetPageName}serviceId" id="${sheetPageName}serviceId"
                               value="${toServiceList.id}"/>
                        <input type="hidden" name="${sheetPageName}serviceCnName" id="${sheetPageName}serviceCnName"
                               value="${toServiceList.serviceCnName}"/>
                    </logic:iterate>
                    <input type="button" class="btn" value="派发任务单" onclick="javascript:openwin()"></td>
                </td>
            </tr>
        </logic:present>
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
                                    <eoms:id2nameDB id="${toprocessTasksList.dealRoleId}" beanId="tawSystemSubRoleDao"/><eoms:id2nameDB
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
            <td class="label"><bean:message bundle="businessimplementsms" key="businessimplementsms.linkDealType"/>*
            </td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}linkDealType" id="${sheetPageName}linkDealType"
                               initDicId="1012204" defaultValue="${sheetLink.linkDealType}" alt="allowBlank:false"
                               onchange="dealType(this.value)"/>
            </td>
            <td class="label"><bean:message bundle="businessimplementsms" key="businessimplementsms.linkUrgency"/>*</td>
            <td class="content">
                <eoms:comboBox name="${sheetPageName}linkUrgency" id="${sheetPageName}linkUrgency"
                               initDicId="1010102" defaultValue="${sheetLink.linkUrgency}" alt="allowBlank:false"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="businessimplementsms"
                                            key="businessimplementsms.linkReqCompleteTime"/>*
            </td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkReqCompleteTime" readonly="readonly"
                       id="${sheetPageName}sheetCompleteLimit"
                       value="${eoms:date2String(sheetLink.linkReqCompleteTime)}"
                       onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>

            </td>
            <td class="label"><bean:message bundle="businessimplementsms" key="businessimplementsms.linkAcceptDept"/>*
            </td>
            <td class="content">
                <input type="text" class="text" name="${sheetPageName}linkAcceptDept"
                       id="${sheetPageName}linkAcceptDept" value="${sheetLink.linkAcceptDept}"
                       alt="allowBlank:false,maxLength:50,vtext:'请填入受理部门，最多输入25字'"/>
            </td>
        </tr>
        <tr>
            <td class="label"><bean:message bundle="businessimplementsms" key="businessimplementsms.linkSendSheetDesc"/>*</td>
            <td colspan="3">
                <textarea name="${sheetPageName}linkSendSheetDesc"
                          alt="allowBlank:false,maxLength:255,vtext:'请填入派单意见，最多输入255字符'"
                          id="${sheetPageName}linkTestDesc"
                          class="textarea max">${sheetLink.linkSendSheetDesc}</textarea>
            </td>
        </tr>
        <%
                }
            }
        %>
        <!-- 用户端资源勘查反馈 -->
        <%if (taskName.equals("UserTask")) { %>
        <%if (operateType.equals("202") || operateType.equals("11")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType%>"/>
        <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over"/>
        <input type="hidden" name="${sheetPageName}processTasks_ParentLinkId"
               id="${sheetPageName}processTasks_ParentLinkId" value="${task.preLinkId}"/>
        <c:if test="${sheetMain.mainspecialtyType==101220101}">
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkCapabilityAffirm"/>*
                </td>
                <td colspan="3">
                    <eoms:comboBox name="${sheetPageName}linkCapabilityAffirm" id="${sheetPageName}linkCapabilityAffirm"
                                   initDicId="1012211" defaultValue="${sheetLink.linkCapabilityAffirm}"
                                   alt="allowBlank:false"/>
                </td>
            </tr>
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkRemark"/></td>
                <td colspan="3">
                    <textarea name="${sheetPageName}linkRemark"
                              alt="allowBlank:true,maxLength:255,vtext:'请填入备注，最多输入255字符'"
                              id="${sheetPageName}linkRemark" class="textarea max">${sheetLink.linkRemark}</textarea>
                </td>
            </tr>
        </c:if>
        <c:if test="${sheetMain.mainspecialtyType==101220102}">
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkANetContact"/>*
                </td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}linkANetContact"
                           id="${sheetPageName}linkANetContact" value="${sheetLink.linkANetContact}"
                           alt="allowBlank:false,maxLength:50,vtext:'请填入A点网络部联系人，最多输入25字'"/>
                </td>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkANetContactTel"/>*
                </td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}linkANetContactTel"
                           id="${sheetPageName}linkANetContactTel" value="${sheetLink.linkANetContactTel}"
                           alt="allowBlank:false,maxLength:50,vtext:'请填入A点网络部门联系人电话，最多输入25字'"/>
                </td>
            </tr>
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkALongitude"/>*
                </td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}linkALongitude"
                           id="${sheetPageName}linkALongitude" value="${sheetLink.linkALongitude}"
                           alt="allowBlank:false,maxLength:50,vtext:'请填入A点经度，最多输入25字'"/>
                </td>
                <td class="label"><bean:message bundle="businessimplementsms" key="businessimplementsms.linkALatitude"/>*</td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}linkALatitude"
                           id="${sheetPageName}linkALatitude" value="${sheetLink.linkALatitude}"
                           alt="allowBlank:false,maxLength:50,vtext:'请填入A点纬度，最多输入25字'"/>
                </td>
            </tr>
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkBusinessBandwidth"/>*
                </td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}linkBusinessBandwidth"
                           id="${sheetPageName}linkBusinessBandwidth" value="${sheetLink.linkBusinessBandwidth}"
                           alt="allowBlank:false,maxLength:50,vtext:'请填入业务带宽，最多输入25字'"/>
                </td>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkBusinessNumber"/>*
                </td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}linkBusinessNumber"
                           id="${sheetPageName}linkBusinessNumber" value="${sheetLink.linkBusinessNumber}"
                           alt="allowBlank:false,maxLength:50,vtext:'请填入业务数量（传输条数），最多输入25字'"/>
                </td>
            </tr>
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkConnectType"/></td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}linkConnectType"
                           id="${sheetPageName}linkConnectType" value="${sheetLink.linkConnectType}"
                           alt="allowBlank:true,maxLength:50,vtext:'请填入连接方式，最多输入25字'"/>
                </td>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkTunnel"/></td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}linkTunnel" id="${sheetPageName}linkTunnel"
                           value="${sheetLink.linkTunnel}" alt="allowBlank:true,maxLength:50,vtext:'请填入隧道方式，最多输入25字'"/>
                </td>
            </tr>
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkGroupUserIP"/></td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}linkGroupUserIP"
                           id="${sheetPageName}linkGroupUserIP" value="${sheetLink.linkGroupUserIP}"
                           alt="allowBlank:true,maxLength:50,vtext:'请填入集团用户对端IP，最多输入25字'"/>
                </td>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkRadiusAddress"/></td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}linkRadiusAddress"
                           id="${sheetPageName}linkRadiusAddress" value="${sheetLink.linkRadiusAddress}"
                           alt="allowBlank:true,maxLength:50,vtext:'请填入radius地址，最多输入25字'"/>
                </td>
            </tr>
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkGroupUserIPReq"/></td>
                <td class="content" colspan="3">
                    <input type="text" class="text" name="${sheetPageName}linkGroupUserIPReq"
                           id="${sheetPageName}linkGroupUserIPReq" value="${sheetLink.linkGroupUserIPReq}"
                           alt="allowBlank:true,maxLength:50,vtext:'请填入集团用户IP地址端需求，最多输入25字'"/>
                </td>
            </tr>
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkAPNName"/></td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}linkAPNName" id="${sheetPageName}linkAPNName"
                           value="${sheetLink.linkAPNName}"
                           alt="allowBlank:true,maxLength:50,vtext:'请填入linkAPNName，最多输入25字'"/>
                </td>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkIPDistribution"/></td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}linkIPDistribution"
                           id="${sheetPageName}linkIPDistribution" value="${sheetLink.linkIPDistribution}"
                           alt="allowBlank:true,maxLength:50,vtext:'请填入IP地址分配方式，最多输入25字'"/>
                </td>
            </tr>
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkRemark"/></td>
                <td colspan="3">
                    <textarea name="${sheetPageName}linkRemark"
                              alt="allowBlank:true,maxLength:255,vtext:'请填入备注，最多输入255字符'"
                              id="${sheetPageName}linkRemark" class="textarea max">${sheetLink.linkRemark}</textarea>
                </td>
            </tr>
        </c:if>
        <%
                }
            }
        %>
        <!-- 接入点资源勘反馈 -->
        <%if (taskName.equals("AccessTask")) { %>
        <%if (operateType.equals("203") || operateType.equals("11")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType%>"/>
        <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over"/>
        <input type="hidden" name="${sheetPageName}processTasks_ParentLinkId"
               id="${sheetPageName}processTasks_ParentLinkId" value="${task.preLinkId}"/>
        <c:if test="${sheetMain.mainspecialtyType==101220101}">
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkCapabilityAffirmN"/>*
                </td>
                <td class="content" colspan="3">
                    <eoms:comboBox name="${sheetPageName}linkCapabilityAffirm" id="${sheetPageName}linkCapabilityAffirm"
                                   initDicId="1012210" defaultValue="${sheetLink.linkCapabilityAffirm}"
                                   alt="allowBlank:false"/>
                </td>
            </tr>
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkAccessPointName"/>*
                </td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}linkAccessPointName"
                           id="${sheetPageName}linkAccessPointName" value="${sheetLink.linkAccessPointName}"
                           alt="allowBlank:false,maxLength:50,vtext:'请填入距离最近的接入点名称，最多输入25字'"/>
                </td>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkAccessPDistance"/>*
                </td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}linkAccessPDistance"
                           id="${sheetPageName}linkAccessPDistance" value="${sheetLink.linkAccessPDistance}"
                           alt="allowBlank:false,maxLength:50,vtext:'请填入与最近的接入点之间的距离，最多输入25字'"/>
                </td>
            </tr>
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkRemark"/></td>
                <td colspan="3">
                    <textarea name="${sheetPageName}linkRemark"
                              alt="allowBlank:true,maxLength:255,vtext:'请填入备注，最多输入255字符'"
                              id="${sheetPageName}linkRemark" class="textarea max">${sheetLink.linkRemark}</textarea>
                </td>
            </tr>
        </c:if>
        <c:if test="${sheetMain.mainspecialtyType==101220102}">
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkAccessPointName"/>*
                </td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}linkAccessPointName"
                           id="${sheetPageName}linkAccessPointName" value="${sheetLink.linkAccessPointName}"
                           alt="allowBlank:false,maxLength:50,vtext:'请填入距离最近的接入点名称，最多输入25字'"/>
                </td>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkAccessPDistance"/>*
                </td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}linkAccessPDistance"
                           id="${sheetPageName}linkAccessPDistance" value="${sheetLink.linkAccessPDistance}"
                           alt="allowBlank:false,maxLength:50,vtext:'请填入与最近的接入点之间的距离，最多输入25字'"/>
                </td>
            </tr>
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkRemark"/></td>
                <td colspan="3">
                    <textarea name="${sheetPageName}linkRemark"
                              alt="allowBlank:true,maxLength:255,vtext:'请填入备注，最多输入255字符'"
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
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType%>"/>
        <input type="hidden" name="${sheetPageName}hasNextTaskFlag" id="${sheetPageName}hasNextTaskFlag" value="true"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="Over"/>
        <input type="hidden" name="${sheetPageName}processTasks_ParentLinkId"
               id="${sheetPageName}processTasks_ParentLinkId" value="${task.preLinkId}"/>
        <c:if test="${sheetMain.mainspecialtyType==101220101}">
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkCapabilityAffirmN"/>*
                </td>
                <td colspan="3">
                    <eoms:comboBox name="${sheetPageName}linkCapabilityAffirm" id="${sheetPageName}linkCapabilityAffirm"
                                   initDicId="1012210" defaultValue="${sheetLink.linkCapabilityAffirm}"
                                   alt="allowBlank:false"/>
                </td>
            </tr>
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkRemark"/></td>
                <td colspan="3">
                    <textarea name="${sheetPageName}linkRemark"
                              alt="allowBlank:true,maxLength:255,vtext:'请填入备注，最多输入255字符'"
                              id="${sheetPageName}linkRemark" class="textarea max">${sheetLink.linkRemark}</textarea>
                </td>
            </tr>
        </c:if>
        <c:if test="${sheetMain.mainspecialtyType==101220102}">
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkGPRSCoreAddress"/>*
                </td>
                <td class="content" colspan="3">
                    <input type="text" class="text" name="${sheetPageName}linkGPRSCoreAddress"
                           id="${sheetPageName}linkGPRSCoreAddress" value="${sheetLink.linkGPRSCoreAddress}"
                           alt="allowBlank:false,maxLength:50,vtext:'请填入GPRS核心网详细地址，最多输入25字'"/>
                </td>
            </tr>
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkGPRSCoreContact"/></td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}linkGPRSCoreContact"
                           id="${sheetPageName}linkGPRSCoreContact" value="${sheetLink.linkGPRSCoreContact}"
                           alt="allowBlank:true,maxLength:50,vtext:'请填入GPRS核心网联系人，最多输入25字'"/>
                </td>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkGPRSCoreContactTel"/></td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}linkGPRSCoreContactTel"
                           id="${sheetPageName}linkGPRSCoreContactTel" value="${sheetLink.linkGPRSCoreContactTel}"
                           alt="allowBlank:true,maxLength:50,vtext:'请填入GPRS核心网联系人电话，最多输入25字'"/>
                </td>
            </tr>
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkGPRSCoreLongitude"/></td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}linkGPRSCoreLongitude"
                           id="${sheetPageName}linkGPRSCoreLongitude" value="${sheetLink.linkGPRSCoreLongitude}"
                           alt="allowBlank:true,maxLength:50,vtext:'请填入GPRS核心网经度，最多输入25字'"/>
                </td>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkGPRSCoreLatitude"/></td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}linkGPRSCoreLatitude"
                           id="${sheetPageName}linkGPRSCoreLatitude" value="${sheetLink.linkGPRSCoreLatitude}"
                           alt="allowBlank:true,maxLength:50,vtext:'请填入GPRS核心网纬度，最多输入25字'"/>
                </td>
            </tr>
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkRemark"/></td>
                <td colspan="3">
                    <textarea name="${sheetPageName}linkRemark"
                              alt="allowBlank:true,maxLength:255,vtext:'请填入备注，最多输入255字符'"
                              id="${sheetPageName}linkRemark" class="textarea max">${sheetLink.linkRemark}</textarea>
                </td>
            </tr>
        </c:if>
        <%
                }
            }
        %>
        <!-- 组网方案制作 -->
        <%if (taskName.equals("MakeTask")) { %>
        <%if (operateType.equals("205") || operateType.equals("11")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType%>"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value="HandleTask"/>
        <c:if test="${sheetMain.mainspecialtyType==101220101}">
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkDealResult"/>*
                </td>
                <td class="content">
                    <eoms:comboBox name="${sheetPageName}linkDealResult" id="${sheetPageName}linkDealResult"
                                   initDicId="1012205" defaultValue="${sheetLink.linkDealResult}"
                                   alt="allowBlank:false"/>
                </td>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkDealDescription"/>*
                </td>
                <td class="content">
                    <eoms:comboBox name="${sheetPageName}linkDealDescription" id="${sheetPageName}linkDealDescription"
                                   initDicId="1012206" defaultValue="${sheetLink.linkDealDescription}"
                                   alt="allowBlank:false" onchange="dealDescription(this.value)"/>
                </td>
            </tr>
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkPreInvestment"/>（单位：万元）*
                </td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}linkPreInvestment"
                           id="${sheetPageName}linkPreInvestment" value="${sheetLink.linkPreInvestment}"
                           alt="allowBlank:false,maxLength:50,vtext:'请填入预计投资，最多输入25字'"/>
                </td>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkPreCompleteDays"/>（单位：天）*
                </td>
                <td class="content">
                    <eoms:comboBox name="${sheetPageName}linkPreCompleteDays" id="${sheetPageName}linkPreCompleteDays"
                                   initDicId="1012209" defaultValue="${sheetLink.linkPreCompleteDays}"
                                   alt="allowBlank:false"/>
                </td>
            </tr>
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkIfOpenCondition"/>*
                </td>
                <td class="content">
                    <eoms:comboBox name="${sheetPageName}linkIfOpenCondition" id="${sheetPageName}linkIfOpenCondition"
                                   initDicId="1012207" defaultValue="${sheetLink.linkIfOpenCondition}"
                                   alt="allowBlank:false"/>
                </td>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkIfPreHold"/></td>
                <td class="content">
                    <eoms:comboBox name="${sheetPageName}linkIfPreHold" id="${sheetPageName}linkIfPreHold"
                                   initDicId="1012208" defaultValue="${sheetLink.linkIfPreHold}" alt="allowBlank:true"/>
                </td>
            </tr>
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkDeviceName"/></td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}linkDeviceName"
                           id="${sheetPageName}linkDeviceName" value="${sheetLink.linkDeviceName}"
                           alt="allowBlank:true,maxLength:50,vtext:'请填入用户端设备名称，最多输入25字'"/>
                </td>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkDevicePort"/></td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}linkDevicePort"
                           id="${sheetPageName}linkDevicePort" value="${sheetLink.linkDevicePort}"
                           alt="allowBlank:true,maxLength:50,vtext:'请填入用户端设备端口，最多输入25字'"/>
                </td>
            </tr>
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkIPAddressOne"/></td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}linkIPAddressOne"
                           id="${sheetPageName}linkIPAddressOne" value="${sheetLink.linkIPAddressOne}"
                           alt="allowBlank:true,maxLength:50,vtext:'请填入IP地址1（账号），最多输入25字'"/>
                </td>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkIPAddressTwo"/></td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}linkIPAddressTwo"
                           id="${sheetPageName}linkIPAddressTwo" value="${sheetLink.linkIPAddressTwo}"
                           alt="allowBlank:true,maxLength:50,vtext:'请填入IP地址2（账号），最多输入25字'"/>
                </td>
            </tr>
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms" key="businessimplementsms.linkMask"/></td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}linkMask" id="${sheetPageName}linkMask"
                           value="${sheetLink.linkMask}" alt="allowBlank:true,maxLength:50,vtext:'请填入子网掩码，最多输入25字'"/>
                </td>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkGateway"/></td>
                <td class="content">
                    <input type="text" class="text" name="${sheetPageName}linkGateway" id="${sheetPageName}linkGateway"
                           value="${sheetLink.linkGateway}" alt="allowBlank:true,maxLength:50,vtext:'请填入网关，最多输入25字'"/>
                </td>
            </tr>
        </c:if>
        <c:if test="${sheetMain.mainspecialtyType==101220102}">
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkDealDescriptionA"/>*
                </td>
                <td class="content">
                    <eoms:comboBox name="${sheetPageName}linkDealDescriptionA" id="${sheetPageName}linkDealDescriptionA"
                                   initDicId="1012206" defaultValue="${sheetLink.linkDealDescriptionA}"
                                   alt="allowBlank:false" onchange="dealDescription(this.value)"/>
                </td>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkPreCompleteDaysA"/>（单位：天）*
                </td>
                <td class="content">
                    <eoms:comboBox name="${sheetPageName}linkPreCompleteDaysA" id="${sheetPageName}linkPreCompleteDaysA"
                                   initDicId="1012209" defaultValue="${sheetLink.linkPreCompleteDaysA}"
                                   alt="allowBlank:false"/>
                </td>
            </tr>
            <tr>
                <td class="label"><bean:message bundle="businessimplementsms"
                                                key="businessimplementsms.linkIfOpenConditionA"/>*
                </td>
                <td class="content" colspan="3">
                    <eoms:comboBox name="${sheetPageName}linkIfOpenConditionA" id="${sheetPageName}linkIfOpenConditionA"
                                   initDicId="1012207" defaultValue="${sheetLink.linkIfOpenConditionA}"
                                   alt="allowBlank:false"/>
                </td>
            </tr>
        </c:if>

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
            <td class="label"><bean:message bundle="businessimplementsms"
                                            key="businessimplementsms.linkProgramTopology"/>*
            </td>
            <td colspan="3">
                <eoms:attachment name="sheetLink" property="linkProgramTopology"
                                 scope="request" idField="${sheetPageName}linkProgramTopology"
                                 appCode="businessimplementsms" alt="allowBlank:false"/>
            </td>
        </tr>
        <%
                }
            }
        %>

        <!-- 处理回复 -->
        <%if (taskName.equals("HandleTask")) { %>
        <%if (operateType.equals("206") || operateType.equals("11")) { %>
        <input type="hidden" name="${sheetPageName}operateType" id="${sheetPageName}operateType"
               value="<%=operateType%>"/>
        <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId" value=HoldTask/>

        <tr>
            <td class="label"><bean:message bundle="businessimplementsms" key="businessimplementsms.linkDealDesc"/>*
            </td>
            <td colspan="3">
                <textarea name="${sheetPageName}linkDealDesc"
                          alt="allowBlank:false,maxLength:255,vtext:'请填入处理意见，最多输入255字符'"
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
                <input type="hidden" name="${sheetPageName}phaseId" id="${sheetPageName}phaseId"
                       value="${fPreTaskName}"/>
            </c:otherwise>
        </c:choose>
        <tr>
            <td class="label">
                <bean:message bundle="businessimplementsms" key="businessimplementsms.linkDealDesc"/>*
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
