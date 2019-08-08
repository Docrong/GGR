<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div id="history" class="panel">
    <div class="tabtool-history-detail">&nbsp;
        <a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet"
                                                                                      key="sheet.showDetail"/></a>
    </div>
    <%int jNum = 0;%>
    <logic:present name="HISTORY" scope="request">
        <logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.securitydeal.model.SecurityDealLink">
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
                    <eoms:dict key="dict-sheet-securitydeal" dictId="activeTemplateId"
                               itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML"/>
                    <bean:message bundle="securitydeal" key="securitydeal.operateType"/>:
                    <eoms:dict key="dict-sheet-securitydeal" dictId="mainOperateType" itemId="${baselink.operateType}"
                               beanId="id2descriptionXML"/>

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
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.operateRoleName"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemSubRoleDao"/>&nbsp;
                                <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemUserDao"/>&nbsp;
                            </td>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.operateTime"/>
                            </td>
                            <td class="column-content">
                                <bean:write name="baselink" property="operateTime" format="yyyy-MM-dd HH:mm:ss"
                                            scope="page"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.operaterContact"/>
                            </td>
                            <%if (baselink.getOperateType().intValue() == 22 || baselink.getOperateType().intValue() == -10 || baselink.getOperateType().intValue() == 0 || baselink.getOperateType().intValue() == 3 || baselink.getOperateType().intValue() == -12) {%>
                            <td class="column-content">
                                    ${sheetMain.sendContact}
                            </td>
                            <%} else {%>
                            <td class="column-content">
                                    ${baselink.operaterContact}
                            </td>
                            <% }%>

                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.toOrgName"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemSubRoleDao"/>
                                <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemUserDao"/>&nbsp;&nbsp;
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
                        <!-- 方案制定 -->
                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("MakeTask") && (baselink.getOperateType().intValue() == 90 || baselink.getOperateType().intValue() == 11)) {%>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="securitydeal" key="securitydeal.linkSecurityInproveAdvice"/></td>
                            <td class="column-content" colspan="3">
                                <pre><bean:write name="baselink" property="linkSecurityInproveAdvice"
                                                 scope="page"/></pre>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="securitydeal" key="securitydeal.linkSecurityInproveProgram"/></td>
                            <td class="column-content" colspan="3">
                                <eoms:attachment name="baselink" property="nodeAccessories"
                                                 scope="page" idField="nodeAccessories" appCode="securitydeal"
                                                 viewFlag="Y"/>
                            </td>
                        </tr>

                        <%}%>
                        <!-- 方案审核 -->
                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("AuditTask") && (baselink.getOperateType().intValue() == 91 || baselink.getOperateType().intValue() == 911 || baselink.getOperateType().intValue() == 912 || baselink.getOperateType().intValue() == 11)) {%>
                        <tr>
                            <td class="label"><bean:message bundle="securitydeal"
                                                            key="securitydeal.linkAuditViews"/></td>
                            <td class="column-content" colspan="3">
                                <eoms:id2nameDB id="${baselink.linkAuditViews}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="label"><bean:message bundle="securitydeal"
                                                            key="securitydeal.linkAuditResult"/></td>
                            <td class="column-content" colspan="3">
                                <pre><bean:write name="baselink" property="linkAuditResult" scope="page"/></pre>
                            </td>
                        </tr>
                        <%}%>
                        <!-- 实施 -->
                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("PerformTask") && (baselink.getOperateType().intValue() == 92 || baselink.getOperateType().intValue() == 11 || baselink.getOperateType().intValue() == 111)) {%>

                        <tr>
                            <td class="label"><bean:message bundle="securitydeal"
                                                            key="securitydeal.linkPerformResult"/></td>
                            <td colspan="3">
                                <pre><bean:write name="baselink" property="linkPerformResult" scope="page"/></pre>
                            </td>
                        </tr>
                        <tr>
                            <td class="label"><bean:message bundle="securitydeal"
                                                            key="securitydeal.linkIfStartChangeProcess"/></td>
                            <td colspan="3">
                                <eoms:id2nameDB id="${baselink.linkIfStartChangeProcess}"
                                                beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <%}%>
                        <!-- 归档 -->
                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("HoldTask") && (baselink.getOperateType().intValue() == 18)) {%>

                        <tr>
                            <td class="label"><bean:message bundle="sheet" key="mainForm.holdStatisfied"/></td>
                            <td colspan="3">
                                <eoms:id2nameDB id="${sheetMain.holdStatisfied}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/></td>
                            <td colspan='3'>
                                <pre>${sheetMain.endResult}</pre>
                            </td>
                        </tr>
                        <%}%>

                        <!-- 移交、转审 -->
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
                        <!-- 分派、回复通过、回复不通过 -->
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

                        <!-- 抄送、阶段回复、阶段通知 -->
                        <%if (baselink.getActiveTemplateId() != null && (baselink.getActiveTemplateId().equals("cc") || baselink.getActiveTemplateId().equals("reply") || baselink.getActiveTemplateId().equals("advice"))) {%>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.remark"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
                            </td>
                        </tr>
                        <%} %>
                        <!-- 驳回 -->
                        <%if (baselink.getActiveTemplateId() != null && baselink.getOperateType().intValue() == 4) {%>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="link.linkRejectCause"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
                            </td>
                        </tr>
                        <%} %>
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
                url: "securitydeal.do?method=getJsonLink&id=" + id + "&beanName=SecurityDeal",
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

