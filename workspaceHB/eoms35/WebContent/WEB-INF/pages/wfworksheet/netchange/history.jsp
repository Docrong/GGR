<%@ include file="/common/taglibs.jsp" %>
<div id="history" class="panel">
    <div class="tabtool-history-detail">&nbsp;
        <a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet"
                                                                                      key="sheet.showDetail"/></a>
    </div>

    <%int jNum = 0;%>
    <logic:present name="HISTORY" scope="request">
        <logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.netchange.model.NetChangeLink">
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
                    <eoms:dict key="dict-sheet-netchange" dictId="activeTemplateId"
                               itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML"/>
                    <bean:message bundle="netchange" key="netchange.operateType"/>:
                    <eoms:dict key="dict-sheet-netchange" dictId="mainOperateType" itemId="${baselink.operateType}"
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
                            <%if (baselink.getOperateRoleId() != null && !baselink.getOperateRoleId().equals("")) {%>

                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.operateRoleName"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemSubRoleDao"/>
                                <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemUserDao"/>&nbsp;
                            </td>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.operaterContact"/>
                            </td>
                            <td class="column-content">
                                <bean:write name="baselink" property="operaterContact" scope="page"/>
                            </td>
                            <%} else { %>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.operaterContact"/>
                            </td>
                            <td class="column-content" colspan="3">
                                <bean:write name="baselink" property="operaterContact" scope="page"/>
                            </td>
                            <%} %>
                        </tr>

                        <tr>
                            <%if (baselink.getToOrgRoleId() != null && !baselink.getToOrgRoleId().equals("") && baselink.getOperateType().intValue() != 118) {%>
                            <td class="column-title">
                                <bean:message bundle="netchange" key="netchange.operateType"/>
                            </td>
                            <td class="column-content">
                                <eoms:dict key="dict-sheet-netchange" dictId="mainOperateType"
                                           itemId="${baselink.operateType}" beanId="id2descriptionXML"/>
                            </td>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.toOrgName"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemSubRoleDao"/>
                                <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemUserDao"/>&nbsp;&nbsp;
                                <!--    <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemUserDao" /> -->
                            </td>
                            <%} else { %>
                            <td class="column-title">
                                <bean:message bundle="netchange" key="netchange.operateType"/>
                            </td>
                            <td class="column-content" colspan="3">
                                <eoms:dict key="dict-sheet-netchange" dictId="mainOperateType"
                                           itemId="${baselink.operateType}" beanId="id2descriptionXML"/>
                            </td>
                            <%} %>
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
                        <%if (baselink.getTransferReason() != null && !baselink.getTransferReason().equals("")) { %>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.transmitReason"/>
                            </td>
                            <td class="column-content" colspan="3">
                                <pre><bean:write name="baselink" property="transferReason" scope="page"/></pre>
                            </td>
                        </tr>
                        <%} %>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.operateTime"/>
                            </td>
                            <td class="column-content" colspan='3'>
                                <bean:write name="baselink" property="operateTime" format="yyyy-MM-dd HH:mm:ss"
                                            scope="page"/>
                            </td>
                        </tr>
                        <!-- 方案制定 -->
                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("ProjectCreateTask")) {%>
                        <%if (baselink.getOperateType().intValue() == 124) {%>
                        <%} else if (baselink.getOperateType().intValue() == 110 || baselink.getOperateType().intValue() == 11) { %>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="netchange" key="netchange.linkProjectKey"/>
                            </td>
                            <td class="column-content" colspan="3">
                                <bean:write name="baselink" property="linkProjectKey" scope="page"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="netchange" key="netchange.linkRiskEvaluate"/>
                            </td>
                            <td class="column-content" colspan="3">
                                <pre><bean:write name="baselink" property="linkRiskEvaluate" scope="page"/></pre>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="netchange" key="netchange.linkOperationConstrue"/>
                            </td>
                            <td class="column-content" colspan="3">
                                <pre><bean:write name="baselink" property="linkOperationConstrue" scope="page"/></pre>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="netchange" key="netchange.linkProjectExplain"/>
                            </td>
                            <td class="column-content" colspan="3">
                                <pre><bean:write name="baselink" property="linkProjectExplain" scope="page"/></pre>
                            </td>
                        </tr>
                        <%if (baselink.getNodeAccessories() != null && !baselink.getNodeAccessories().equals("")) {%>
                        <tr>
                            <td class="label"><bean:message bundle="netchange" key="netchange.linkProjectAcc"/></td>
                            <td colspan=3>
                                <eoms:attachment name="baselink" property="nodeAccessories"
                                                 scope="page" idField="nodeAccessories" appCode="netchange"
                                                 viewFlag="Y"/>
                            </td>
                        </tr>
                        <%}%>
                        <%
                                }
                            }
                        %>
                        <%
                            if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("AuditTask")) {
                                if (baselink.getOperateType().intValue() == 116 || baselink.getOperateType().intValue() == 123 || baselink.getOperateType().intValue() == 55) {
                        %>
                        <tr>
                            <!-- 审核结果 -->
                            <td class="label"><bean:message bundle="netchange" key="netchange.linkAuditingResult"/></td>
                            <td colspan="3">
                                <eoms:id2nameDB id="${baselink.linkAuditingResult}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>
                            <!-- 审核意见 -->
                            <td class="label"><bean:message bundle="netchange" key="netchange.linkAuditingIdea"/></td>
                            <td colspan="3">
                                <pre><bean:write name="baselink" property="linkAuditingIdea" scope="page"/></pre>
                            </td>
                        </tr>

                        <%if (baselink.getNodeAccessories() != null && !baselink.getNodeAccessories().equals("")) {%>
                        <tr>
                            <!-- 附件 -->
                            <td class="label"><bean:message bundle="netchange" key="netchange.nodeAccessories"/></td>
                            <td colspan="3">
                                <eoms:attachment name="baselink" property="nodeAccessories"
                                                 scope="page" idField="nodeAccessories" appCode="netchange"
                                                 viewFlag="Y"/>
                            </td>
                        </tr>
                        <%}%>
                        <%
                                }
                            }
                        %>
                        <%
                            if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("PermitTask")) {
                                if (baselink.getOperateType().intValue() == 111 || baselink.getOperateType().intValue() == 121 || baselink.getOperateType().intValue() == 122 || baselink.getOperateType().intValue() == 55) {
                        %>
                        <tr>
                            <!-- 审批结果 -->
                            <td class="label"><bean:message bundle="netchange" key="netchange.linkPermitResult"/></td>
                            <td colspan="3">
                                <eoms:id2nameDB id="${baselink.linkPermitResult}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>
                            <!-- 审批意见 -->
                            <td class="label"><bean:message bundle="netchange" key="netchange.linkPermitIdea"/></td>
                            <td colspan="3">
                                <pre><bean:write name="baselink" property="linkPermitIdea" scope="page"/></pre>
                            </td>
                        </tr>

                        <%if (baselink.getNodeAccessories() != null && !baselink.getNodeAccessories().equals("")) {%>
                        <tr>
                            <!-- 附件 -->
                            <td class="label"><bean:message bundle="netchange" key="netchange.nodeAccessories"/></td>
                            <td colspan="3">
                                <eoms:attachment name="baselink" property="nodeAccessories"
                                                 scope="page" idField="nodeAccessories" appCode="netchange"
                                                 viewFlag="Y"/>
                            </td>
                        </tr>
                        <%}%>
                        <%
                                }
                            }
                        %>
                        <%
                            if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("ExecuteTask")) {
                                if (baselink.getOperateType().intValue() == 113 || baselink.getOperateType().intValue() == 118 || baselink.getOperateType().intValue() == 11) {
                        %>
                        <c:if test="${empty baselink.linkExcuteResult}">
                            <c:if test="${baselink.operateType!=11 }">
                                <tr>
                                    <td class="label"><bean:message bundle="netchange"
                                                                    key="netchange.linkIfStartCircuit"/></td>
                                    <td>
                                        <eoms:id2nameDB id="${baselink.linkIfStartCircuit}"
                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                    <td class="label"><bean:message bundle="netchange"
                                                                    key="netchange.linkIfStartData"/></td>
                                    <td>
                                        <eoms:id2nameDB id="${baselink.linkIfStartData}"
                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label"><bean:message bundle="netchange"
                                                                    key="netchange.linkIfStartSoft"/></td>
                                    <td colspan="3">
                                        <eoms:id2nameDB id="${baselink.linkIfStartSoft}"
                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                </tr>
                            </c:if>
                        </c:if>
                        <c:if test="${!empty baselink.linkExcuteResult}">
                            <tr>
                                <td class="label"><bean:message bundle="netchange"
                                                                key="netchange.linkExcuteResult"/></td>
                                <td>
                                    <eoms:id2nameDB id="${baselink.linkExcuteResult}" beanId="ItawSystemDictTypeDao"/>
                                </td>
                                <td class="label"><bean:message bundle="netchange"
                                                                key="netchange.linkFailedReason"/></td>
                                <td>
                                    <eoms:id2nameDB id="${baselink.linkFailedReason}" beanId="ItawSystemDictTypeDao"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="label"><bean:message bundle="netchange"
                                                                key="netchange.linkIfAccordingProject"/></td>
                                <td colspan="3">
                                    <eoms:id2nameDB id="${baselink.linkIfAccordingProject}"
                                                    beanId="ItawSystemDictTypeDao"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="label"><bean:message bundle="netchange"
                                                                key="netchange.linkColligateExcuteResult"/></td>
                                <td colspan="3">
                                    <pre><bean:write name="baselink" property="linkColligateExcuteResult"
                                                     scope="page"/></pre>
                                </td>
                            </tr>

                            <%if (baselink.getNodeAccessories() != null && !baselink.getNodeAccessories().equals("")) {%>
                            <tr>
                                <td class="label"><bean:message bundle="netchange"
                                                                key="netchange.linkColligateExcuteReport"/></td>
                                <td colspan="3">
                                    <eoms:attachment name="baselink" property="nodeAccessories"
                                                     scope="page" idField="${sheetPageName}nodeAccessories"
                                                     appCode="netchange" viewFlag="Y"/>
                                </td>
                            </tr>
                            <%}%>
                        </c:if>
                        <%
                                }
                            }
                        %>
                        <%
                            if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("TestTask")) {
                                if (baselink.getOperateType().intValue() == 117 || baselink.getOperateType().intValue() == 11) {
                        %>
                        <tr>
                            <td class="label"><bean:message bundle="netchange" key="netchange.linkTestSummarize"/></td>
                            <td colspan="3">
                                <pre><bean:write name="baselink" property="linkTestSummarize" scope="page"/></pre>
                            </td>
                        </tr>
                        <tr>
                            <td class="label"><bean:message bundle="netchange" key="netchange.linkWorkPlan"/></td>
                            <td colspan="3">
                                <pre><bean:write name="baselink" property="linkWorkPlan" scope="page"/></pre>
                            </td>
                        </tr>
                        <tr>
                            <td class="label"><bean:message bundle="netchange"
                                                            key="netchange.linkIsToMaintenance"/></td>
                            <td colspan="3">
                                <eoms:id2nameDB id="${baselink.linkIsToMaintenance}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>
                            <!-- 交维描述 -->
                            <td class="label"><bean:message bundle="netchange" key="netchange.linkMaintenanceDes"/></td>
                            <td colspan="3">
                                <pre><bean:write name="baselink" property="linkMaintenanceDes" scope="page"/></pre>
                            </td>
                        </tr>

                        <%if (baselink.getNodeAccessories() != null && !baselink.getNodeAccessories().equals("")) {%>
                        <tr>
                            <!-- 综合测试报告 -->
                            <td class="label"><bean:message bundle="netchange" key="netchange.linkExcuteReport"/></td>
                            <td colspan="3">
                                <eoms:attachment name="baselink" property="nodeAccessories"
                                                 scope="page" idField="${sheetPageName}nodeAccessories"
                                                 appCode="netchange" viewFlag="Y"/>
                            </td>
                        </tr>
                        <%}%>
                        <%
                                }
                            }
                        %>
                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("HoldTask")) {%>


                        <%} %>
                        <%//if(baselink.getActiveTemplateId()!=null && (baselink.getActiveTemplateId().equals("cc")||baselink.getActiveTemplateId().equals("reply")||baselink.getActiveTemplateId().equals("advice")||baselink.getOperateType().intValue() == 4||baselink.getOperateType().intValue() == -11||baselink.getOperateType().intValue() == 61||baselink.getOperateType().intValue()==124||baselink.getOperateType().intValue()==7)){%>
                        <%if (baselink.getRemark() != null && !baselink.getRemark().equals("")) { %>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.remark"/>
                            </td>
                            <td class="column-content" colspan="3">
                                <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
                            </td>
                        </tr>
                        <%//} %>
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
                url: "netchange.do?method=getJsonLink&id=" + id + "&beanName=NetChange",
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