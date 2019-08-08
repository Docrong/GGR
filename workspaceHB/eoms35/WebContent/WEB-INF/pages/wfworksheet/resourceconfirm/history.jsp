<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div id="history" class="panel">
    <div class="tabtool-history-detail">&nbsp;
        <a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet"
                                                                                      key="sheet.showDetail"/></a>
    </div>

    <%int jNum = 0;%>
    <logic:present name="HISTORY" scope="request">
        <logic:iterate id="baselink" name="HISTORY"
                       type="com.boco.eoms.sheet.resourceconfirm.model.ResourceConfirmLink">
            <%
                jNum += 1;
                String divName = "buzhou" + jNum;
            %>
            <div class="history-item"><!-- add space to hack ie-->&nbsp;
                <div class="history-item-title">
                    <%=jNum%>.
                    <bean:write name="baselink" property="operateTime" formatKey="date.formatDateTimeAll" bundle="sheet"
                                scope="page"/>&nbsp;
                    <eoms:id2nameDB id="${baselink.operateUserId}" beanId="tawSystemUserDao"/>
                    <bean:message bundle="sheet" key="task.operateName"/>:
                    <eoms:dict key="dict-sheet-resourceconfirm" dictId="activeTemplateId"
                               itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML"/>
                    <bean:message bundle="resourceconfirm" key="resourceconfirm.operateType"/>:
                    <eoms:dict key="dict-sheet-resourceconfirm" dictId="mainOperateType"
                               itemId="${baselink.operateType}" beanId="id2descriptionXML"/>
                    <img class="switchIcon" src="${app}/images/icons/closed.gif" align="absmiddle"/>
                </div>

                <div class="history-item-content hide">
                    <c:if test="${taskName==baselink.activeTemplateId && ifWaitForSubTask=='false'&&(baselink.operateType=='11'||baselink.operateType=='55')}">
                        <input type="checkbox" name="${sheetPageName}checkuse" id="${baselink.id}"
                               value="${baselink.id}" onclick="copy(this.value);"/>
                        <bean:message bundle="sheet" key="common.copy"/>
                    </c:if>
                    <table class="history-item-table" width="100%" cellpadding="0" cellspacing="0">
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.operateUserName"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.operateUserId}" beanId="tawSystemUserDao"/>&nbsp;
                            </td>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.operateDeptName"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.operateDeptId}" beanId="tawSystemDeptDao"/>&nbsp;
                            </td>
                        </tr>
                        <tr>
                            <%if (baselink.getOperateRoleId() != null && !baselink.getOperateRoleId().equals("")) { %>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.operateRoleName"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemSubRoleDao"/>
                                <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemUserDao"/>&nbsp;
                            </td>
                            <%} %>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.operaterContact"/>
                            </td>
                            <td class="column-content">
                                <bean:write name="baselink" property="operaterContact" scope="page"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="resourceconfirm" key="resourceconfirm.operateType"/>
                            </td>
                            <td class="column-content">
                                <eoms:dict key="dict-sheet-resourceconfirm" dictId="mainOperateType"
                                           itemId="${baselink.operateType}" beanId="id2descriptionXML"/>
                            </td>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.operateTime"/>
                            </td>
                            <td class="column-content">
                                <bean:write name="baselink" property="operateTime" format="yyyy-MM-dd HH:mm:ss"
                                            scope="page"/>
                            </td>
                        </tr>
                        <%
                            if (baselink.getNodeAcceptLimit() != null && !baselink.getNodeAcceptLimit().equals("")
                                    && baselink.getNodeCompleteLimit() != null && !baselink.getNodeCompleteLimit().equals("")) {
                        %>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.acceptLimit"/>
                            </td>
                            <td class="column-content">
                                <bean:write name="baselink" property="nodeAcceptLimit" format="yyyy-MM-dd HH:mm:ss"
                                            scope="page"/>
                            </td>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.completeLimit"/>
                            </td>
                            <td class="column-content">
                                <bean:write name="baselink" property="nodeCompleteLimit" format="yyyy-MM-dd HH:mm:ss"
                                            scope="page"/>
                            </td>
                        </tr>
                        <%} %>
                        <%if (baselink.getToOrgRoleId() != null && !baselink.getToOrgRoleId().equals("")) {%>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.toOrgName"/>
                            </td>
                            <td class="column-content" colspan="3">
                                <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemSubRoleDao"/>
                                <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemUserDao"/>

                            </td>
                        </tr>
                        <%} %>
                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("AcceptTask")) {%>
                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 200 || baselink.getOperateType().intValue() == 11)) {%>
                        <tr>
                            <td class="label"><bean:message bundle="resourceconfirm"
                                                            key="resourceconfirm.linkUrgency"/></td>
                            <td class="content">
                                <eoms:id2nameDB id="${baselink.linkUrgency}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="label"><bean:message bundle="resourceconfirm"
                                                            key="resourceconfirm.linkReqCompleteTime"/></td>
                            <td class="content">
                                    ${eoms:date2String(baselink.linkReqCompleteTime)}
                            </td>
                        </tr>
                        <tr>
                            <td class="label"><bean:message bundle="resourceconfirm"
                                                            key="resourceconfirm.linkSendSheetDesc"/></td>
                            <td colspan="3">
                                <pre><bean:write name="baselink" property="linkSendSheetDesc" scope="page"/></pre>
                            </td>
                        </tr>
                        <%
                                }
                            }
                        %>
                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("TransfereTask")) {%>
                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 207 || baselink.getOperateType().intValue() == 11)) {%>
                        <tr>
                            <td class="label"><bean:message bundle="resourceconfirm"
                                                            key="resourceconfirm.linkRemark"/></td>
                            <td colspan="3">
                                <pre><bean:write name="baselink" property="linkRemark" scope="page"/></pre>
                            </td>
                        </tr>
                        <%
                                }
                            }
                        %>
                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("ExplorateTask")) {%>
                        <%
                            if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 201 || baselink.getOperateType().intValue() == 2011 || baselink.getOperateType().intValue() == 2012 ||
                                    baselink.getOperateType().intValue() == 2013 || baselink.getOperateType().intValue() == 2014 || baselink.getOperateType().intValue() == 2015 || baselink.getOperateType().intValue() == 11)) {
                        %>
                        <tr>
                            <td class="label">客户端工程能力确认</td>
                            <td colspan="3">${sheetMain.mainBusinessDesc}</td>
                        </tr>
                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 2014 || baselink.getOperateType().intValue() == 2015 || baselink.getOperateType().intValue() == 11)) {%>
                        <tr>
                            <td class="label"><bean:message bundle="resourceconfirm"
                                                            key="resourceconfirm.linkDealType"/></td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkDealType}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="label"><bean:message bundle="resourceconfirm"
                                                            key="resourceconfirm.linkUrgency"/></td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkUrgency}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <%} %>
                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 2011 || baselink.getOperateType().intValue() == 2012 || baselink.getOperateType().intValue() == 2013 || baselink.getOperateType().intValue() == 11)) {%>
                        <tr>
                            <td class="label"><bean:message bundle="resourceconfirm"
                                                            key="resourceconfirm.linkUrgency"/></td>
                            <td class="column-content" colspan="3">
                                <eoms:id2nameDB id="${baselink.linkUrgency}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <%} %>
                        <tr>
                            <td class="label"><bean:message bundle="resourceconfirm"
                                                            key="resourceconfirm.linkReqCompleteTime"/></td>
                            <td colspan='3'>
                                    ${eoms:date2String(baselink.linkReqCompleteTime)}
                            </td>
                        </tr>
                        <tr>
                            <td class="label"><bean:message bundle="resourceconfirm"
                                                            key="resourceconfirm.linkSendSheetDesc"/></td>
                            <td colspan="3">
                                <pre><bean:write name="baselink" property="linkSendSheetDesc" scope="page"/></pre>
                            </td>
                        </tr>
                        <%
                                }
                            }
                        %>
                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("UserTask")) {%>
                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 202 || baselink.getOperateType().intValue() == 11)) {%>
                        <tr>
                            <td class="label"><bean:message bundle="resourceconfirm"
                                                            key="resourceconfirm.linkRemark"/></td>
                            <td colspan="3">
                                <pre><bean:write name="baselink" property="linkRemark" scope="page"/></pre>
                            </td>
                        </tr>
                        <c:if test="${sheetMain.mainspecialtyType==101220101}">
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkCapabilityAffirm"/></td>
                                <td class="column-content" colspan="3">
                                    <eoms:id2nameDB id="${baselink.linkCapabilityAffirm}"
                                                    beanId="ItawSystemDictTypeDao"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkRemark"/></td>
                                <td colspan="3">
                                    <pre><bean:write name="baselink" property="linkRemark" scope="page"/></pre>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${sheetMain.mainspecialtyType==101220102}">
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkANetContact"/></td>
                                <td class="column-content">
                                    <pre><bean:write name="baselink" property="linkANetContact" scope="page"/></pre>
                                </td>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkANetContactTel"/></td>
                                <td class="column-content">
                                    <pre><bean:write name="baselink" property="linkANetContactTel" scope="page"/></pre>
                                </td>
                            </tr>
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkALongitude"/></td>
                                <td class="column-content">
                                    <pre><bean:write name="baselink" property="linkALongitude" scope="page"/></pre>
                                </td>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkALatitude"/></td>
                                <td class="column-content">
                                    <pre><bean:write name="baselink" property="linkALatitude" scope="page"/></pre>
                                </td>
                            </tr>
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkBusinessBandwidth"/></td>
                                <td class="column-content">
                                    <pre><bean:write name="baselink" property="linkBusinessBandwidth"
                                                     scope="page"/></pre>
                                </td>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkBusinessNumber"/></td>
                                <td class="column-content">
                                    <pre><bean:write name="baselink" property="linkBusinessNumber" scope="page"/></pre>
                                </td>
                            </tr>
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkConnectType"/></td>
                                <td class="column-content">
                                    <pre><bean:write name="baselink" property="linkConnectType" scope="page"/></pre>
                                </td>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkTunnel"/></td>
                                <td class="column-content">
                                    <pre><bean:write name="baselink" property="linkTunnel" scope="page"/></pre>
                                </td>
                            </tr>
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkGroupUserIP"/></td>
                                <td class="column-content">
                                    <pre><bean:write name="baselink" property="linkGroupUserIP" scope="page"/></pre>
                                </td>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkRadiusAddress"/></td>
                                <td class="column-content">
                                    <pre><bean:write name="baselink" property="linkRadiusAddress" scope="page"/></pre>
                                </td>
                            </tr>
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkGroupUserIPReq"/></td>
                                <td class="column-content" colspan="3">
                                    <pre><bean:write name="baselink" property="linkGroupUserIPReq" scope="page"/></pre>
                                </td>
                            </tr>
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkAPNName"/></td>
                                <td class="column-content">
                                    <pre><bean:write name="baselink" property="linkAPNName" scope="page"/></pre>
                                </td>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkIPDistribution"/></td>
                                <td class="column-content">
                                    <pre><bean:write name="baselink" property="linkIPDistribution" scope="page"/></pre>
                                </td>
                            </tr>
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkRemark"/></td>
                                <td colspan="3">
                                    <pre><bean:write name="baselink" property="linkRemark" scope="page"/></pre>
                                </td>
                            </tr>
                        </c:if>
                        <%
                                }
                            }
                        %>
                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("AccessTask")) {%>
                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 203 || baselink.getOperateType().intValue() == 11)) {%>
                        <tr>
                            <td class="label"><bean:message bundle="resourceconfirm"
                                                            key="resourceconfirm.linkRemark"/></td>
                            <td colspan="3">
                                <pre><bean:write name="baselink" property="linkRemark" scope="page"/></pre>
                            </td>
                        </tr>
                        <c:if test="${sheetMain.mainspecialtyType==101220101}">
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkCapabilityAffirmN"/></td>
                                <td class="column-content" colspan="3">
                                    <eoms:id2nameDB id="${baselink.linkCapabilityAffirm}"
                                                    beanId="ItawSystemDictTypeDao"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkAccessPointName"/></td>
                                <td class="column-content">
                                    <pre><bean:write name="baselink" property="linkAccessPointName" scope="page"/></pre>
                                </td>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkAccessPDistance"/></td>
                                <td class="column-content">
                                    <pre><bean:write name="baselink" property="linkAccessPDistance" scope="page"/></pre>
                                </td>
                            </tr>
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkRemark"/></td>
                                <td colspan="3">
                                    <pre><bean:write name="baselink" property="linkRemark" scope="page"/></pre>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${sheetMain.mainspecialtyType==101220102}">
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkAccessPointName"/></td>
                                <td class="column-content">
                                    <pre><bean:write name="baselink" property="linkAccessPointName" scope="page"/></pre>
                                </td>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkAccessPDistance"/></td>
                                <td class="column-content">
                                    <pre><bean:write name="baselink" property="linkAccessPDistance" scope="page"/></pre>
                                </td>
                            </tr>
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkRemark"/></td>
                                <td colspan="3">
                                    <pre><bean:write name="baselink" property="linkRemark" scope="page"/></pre>
                                </td>
                            </tr>
                        </c:if>
                        <%
                                }
                            }
                        %>
                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("CityTask")) {%>
                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 204 || baselink.getOperateType().intValue() == 11)) {%>
                        <tr>
                            <td class="label"><bean:message bundle="resourceconfirm"
                                                            key="resourceconfirm.linkRemark"/></td>
                            <td colspan="3">
                                <pre><bean:write name="baselink" property="linkRemark" scope="page"/></pre>
                            </td>
                        </tr>
                        <c:if test="${sheetMain.mainspecialtyType==101220101}">
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkCapabilityAffirmN"/></td>
                                <td class="column-content" colspan="3">
                                    <eoms:id2nameDB id="${baselink.linkCapabilityAffirm}"
                                                    beanId="ItawSystemDictTypeDao"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkRemark"/></td>
                                <td colspan="3">
                                    <pre><bean:write name="baselink" property="linkRemark" scope="page"/></pre>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${sheetMain.mainspecialtyType==101220102}">
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkGPRSCoreAddress"/></td>
                                <td class="column-content" colspan="3">
                                    <pre><bean:write name="baselink" property="linkGPRSCoreAddress" scope="page"/></pre>
                                </td>
                            </tr>
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkGPRSCoreContact"/></td>
                                <td class="column-content">
                                    <pre><bean:write name="baselink" property="linkGPRSCoreContact" scope="page"/></pre>
                                </td>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkGPRSCoreContactTel"/></td>
                                <td class="column-content">
                                    <pre><bean:write name="baselink" property="linkGPRSCoreContactTel"
                                                     scope="page"/></pre>
                                </td>
                            </tr>
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkGPRSCoreLongitude"/></td>
                                <td class="column-content">
                                    <pre><bean:write name="baselink" property="linkGPRSCoreLongitude"
                                                     scope="page"/></pre>
                                </td>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkGPRSCoreLatitude"/></td>
                                <td class="column-content">
                                    <pre><bean:write name="baselink" property="linkGPRSCoreLatitude"
                                                     scope="page"/></pre>
                                </td>
                            </tr>
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkRemark"/></td>
                                <td colspan="3">
                                    <pre><bean:write name="baselink" property="linkRemark" scope="page"/></pre>
                                </td>
                            </tr>
                        </c:if>
                        <%
                                }
                            }
                        %>
                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("MakeTask")) {%>
                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 205 || baselink.getOperateType().intValue() == 11)) {%>
                        <tr>
                            <td class="label">预计投资</td>
                            <td colspan="3">${sheetMain.mainProductsNumber}</td>
                        </tr>
                        <c:if test="${sheetMain.mainspecialtyType==101220101}">
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkDealResult"/></td>
                                <td class="column-content">
                                    <eoms:id2nameDB id="${baselink.linkDealResult}" beanId="ItawSystemDictTypeDao"/>
                                </td>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkDealDescription"/></td>
                                <td class="column-content">
                                    <eoms:id2nameDB id="${baselink.linkDealDescription}"
                                                    beanId="ItawSystemDictTypeDao"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkPreInvestment"/></td>
                                <td class="column-content">
                                    <pre><bean:write name="baselink" property="linkPreInvestment" scope="page"/></pre>
                                </td>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkPreCompleteDays"/></td>
                                <td class="column-content">
                                    <eoms:id2nameDB id="${baselink.linkPreCompleteDays}"
                                                    beanId="ItawSystemDictTypeDao"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkIfOpenCondition"/></td>
                                <td class="column-content">
                                    <eoms:id2nameDB id="${baselink.linkIfOpenCondition}"
                                                    beanId="ItawSystemDictTypeDao"/>
                                </td>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkIfPreHold"/></td>
                                <td class="column-content">
                                    <eoms:id2nameDB id="${baselink.linkIfPreHold}" beanId="ItawSystemDictTypeDao"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkDeviceName"/></td>
                                <td class="column-content">
                                    <pre><bean:write name="baselink" property="linkDeviceName" scope="page"/></pre>
                                </td>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkDevicePort"/></td>
                                <td class="column-content">
                                    <pre><bean:write name="baselink" property="linkDevicePort" scope="page"/></pre>
                                </td>
                            </tr>
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkIPAddressOne"/></td>
                                <td class="column-content">
                                    <pre><bean:write name="baselink" property="linkIPAddressOne" scope="page"/></pre>
                                </td>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkIPAddressTwo"/></td>
                                <td class="column-content">
                                    <pre><bean:write name="baselink" property="linkIPAddressTwo" scope="page"/></pre>
                                </td>
                            </tr>
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkMask"/></td>
                                <td class="column-content">
                                    <pre><bean:write name="baselink" property="linkMask" scope="page"/></pre>
                                </td>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkGateway"/></td>
                                <td class="column-content">
                                    <pre><bean:write name="baselink" property="linkGateway" scope="page"/></pre>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${sheetMain.mainspecialtyType==101220102}">
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkDealDescriptionA"/></td>
                                <td class="column-content">
                                    <eoms:id2nameDB id="${baselink.linkDealDescriptionA}"
                                                    beanId="ItawSystemDictTypeDao"/>
                                </td>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkPreCompleteDaysA"/></td>
                                <td class="column-content">
                                    <eoms:id2nameDB id="${baselink.linkPreCompleteDaysA}"
                                                    beanId="ItawSystemDictTypeDao"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="label"><bean:message bundle="resourceconfirm"
                                                                key="resourceconfirm.linkIfOpenConditionA"/></td>
                                <td class="column-content" colspan="3">
                                    <eoms:id2nameDB id="${baselink.linkIfOpenConditionA}"
                                                    beanId="ItawSystemDictTypeDao"/>
                                </td>
                            </tr>
                        </c:if>
                        <tr>
                            <td class="label"><bean:message bundle="resourceconfirm"
                                                            key="resourceconfirm.linkProgramTopology"/></td>
                            <td colspan='3'>
                                <eoms:attachment name="baselink" property="linkProgramTopology" scope="page"
                                                 idField="${sheetPageName}linkProgramTopology" appCode="resourceconfirm"
                                                 viewFlag="Y"/>
                            </td>
                        </tr>

                        <%
                                }
                            }
                        %>
                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("HandleTask")) {%>

                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 206 || baselink.getOperateType().intValue() == 11)) {%>
                        <tr>
                            <td class="label"><bean:message bundle="resourceconfirm"
                                                            key="resourceconfirm.linkDealDesc"/></td>
                            <td colspan="3">
                                <pre><bean:write name="baselink" property="linkDealDesc" scope="page"/></pre>
                            </td>
                        </tr>
                        <%
                                }
                            }
                        %>

                        <%if (baselink.getActiveTemplateId() != null && (baselink.getActiveTemplateId().equals("cc") || baselink.getOperateType().intValue() == 9 || baselink.getActiveTemplateId().equals("advice"))) {%>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.remark"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
                            </td>
                        </tr>

                        <%} %>


                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("HoldTask")) {%>
                        <%if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 102) {%>

                        <%
                                }
                            }
                        %>
                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 8 || baselink.getOperateType().intValue() == 88)) {%>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.transmitReason"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="transferReason" scope="page"/></pre>
                            </td>
                        </tr>

                        <%} %>

                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == -12 || baselink.getOperateType().intValue() == -13 || baselink.getOperateType().intValue() == -14)) {%>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.transmitReason"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="transferReason" scope="page"/></pre>
                            </td>
                        </tr>

                        <%} %>
                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 6 || baselink.getOperateType().intValue() == 7 || baselink.getOperateType().intValue() == 10 || baselink.getOperateType().intValue() == 30)) {%>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.remark"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
                            </td>
                        </tr>

                        <%}%>
                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 4)) {%>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="resourceconfirm" key="resourceconfirm.linkDealDesc"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkDealDesc" scope="page"/></pre>
                            </td>
                        </tr>

                        <%}%>
                        <%if (baselink.getNodeAccessories() != null && !baselink.getNodeAccessories().equals("")) {%>
                        <tr>
                            <td>
                                <bean:message bundle="sheet" key="linkForm.accessories"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <eoms:attachment name="baselink" property="nodeAccessories"
                                                 scope="page" idField="nodeAccessories" appCode="businessoperation"
                                                 viewFlag="Y"/>
                            </td>
                        </tr>
                        <%}%>
                    </table>
                </div>
            </div>
        </logic:iterate>
    </logic:present>
</div>
<script type="text/javascript">
    switcher = new detailSwitcher();
    switcher.init({
        container: 'history',
        handleEl: 'div.history-item-title'
    });

    function copy(id) {
        var ifCheck = document.getElementById(id);
        if (ifCheck.checked == true) {
            Ext.Ajax.request({
                method: "get",
                url: "resourceconfirm.do?method=getJsonLink&id=" + id + "&beanName=ResourceConfirm",
                success: function (x) {
                    var o = eoms.JSONDecode(x.responseText);
                    for (p in o) {
                        var a = eoms.$(p);
                        if (a && a.tagName == "TEXTAREA") {
                            if (a.value == "") {
                                a.value = o[p];
                            } else {
                                a.value += "   " + o[p];
                            }
                        }
                    }
                }
            });
        }
    }
</script>