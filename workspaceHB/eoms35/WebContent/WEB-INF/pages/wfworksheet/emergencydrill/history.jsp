<%@ include file="/common/taglibs.jsp" %>


<div id="history" class="panel">
    <div class="tabtool-history-detail">&nbsp;
        <a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet"
                                                                                      key="sheet.showDetail"/></a>
    </div>

    <%int jNum = 0;%>
    <logic:present name="HISTORY" scope="request">
        <logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.emergencydrill.model.EmergencyDrillLink">
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
                    <eoms:dict key="dict-sheet-emergencydrill" dictId="activeTemplateId"
                               itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML"/>
                    <bean:message bundle="emergencydrill" key="emergencydrill.operateType"/>:
                    <eoms:dict key="dict-sheet-emergencydrill" dictId="mainOperateType" itemId="${baselink.operateType}"
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
                                <bean:message bundle="emergencydrill" key="emergencydrill.operateType"/>
                            </td>
                            <td class="column-content">
                                <eoms:dict key="dict-sheet-emergencydrill" dictId="mainOperateType"
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


                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("MakeProgramTask")) {%>
                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 91 || baselink.getOperateType().intValue() == 11)) {%>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="emergencydrill" key="emergencydrill.linkEmergencyDrillNote"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkEmergencyDrillNote" scope="page"/></pre>
                            </td>
                        </tr>

                        <tr>
                            <td class="label"><bean:message bundle="emergencydrill"
                                                            key="emergencydrill.linkEmergencyDrill"/></td>
                            <td colspan='3'>
                                <eoms:attachment name="baselink" property="linkEmergencyDrill" scope="page"
                                                 idField="${sheetPageName}linkEmergencyDrill" appCode="emergencydrill"
                                                 viewFlag="Y"/>
                            </td>
                        </tr>


                        <%
                                }
                            }
                        %>

                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("AuditProgramTask")) {%>
                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 921 || baselink.getOperateType().intValue() == 922 || baselink.getOperateType().intValue() == 55)) {%>
                        <tr>
                            <td class="label"><bean:message bundle="emergencydrill"
                                                            key="emergencydrill.linkAuditOpinion"/></td>
                            <td class="column-content" colspan=3>
                                <eoms:id2nameDB id="${baselink.linkAuditOpinion}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="emergencydrill" key="emergencydrill.linkAuditResult"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkAuditResult" scope="page"/></pre>
                            </td>
                        </tr>

                        <%
                                }
                            }
                        %>


                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("ImplementProgramTask")) {%>
                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 93 || baselink.getOperateType().intValue() == 11)) {%>


                        <tr>
                            <td class="column-title">
                                <bean:message bundle="emergencydrill" key="emergencydrill.linkAuditResult"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkAuditResult" scope="page"/></pre>
                            </td>
                        </tr>

                        <tr>
                            <td class="label"><bean:message bundle="emergencydrill"
                                                            key="emergencydrill.linkDrillReport"/></td>
                            <td colspan='3'>
                                <eoms:attachment name="baselink" property="linkDrillReport" scope="page"
                                                 idField="${sheetPageName}linkDrillReport" appCode="emergencydrill"
                                                 viewFlag="Y"/>
                            </td>
                        </tr>


                        <%
                                }
                            }
                        %>

                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("SubmitSummaryTask")) {%>
                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 94 || baselink.getOperateType().intValue() == 11)) {%>


                        <tr>
                            <td class="column-title">
                                <bean:message bundle="emergencydrill" key="emergencydrill.linkImplementSummary"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkImplementSummary" scope="page"/></pre>
                            </td>
                        </tr>

                        <tr>
                            <td class="label"><bean:message bundle="emergencydrill"
                                                            key="emergencydrill.linkDrillSummaryReport"/></td>
                            <td colspan='3'>
                                <eoms:attachment name="baselink" property="linkDrillSummaryReport" scope="page"
                                                 idField="${sheetPageName}linkDrillSummaryReport"
                                                 appCode="emergencydrill"
                                                 viewFlag="Y"/>
                            </td>
                        </tr>


                        <%
                                }
                            }
                        %>

                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("AuditSummaryTask")) {%>
                        <%
                            if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 951 || baselink.getOperateType().intValue() == 952 ||
                                    baselink.getOperateType().intValue() == 953 || baselink.getOperateType().intValue() == 55)) {
                        %>
                        <tr>
                            <td class="label"><bean:message bundle="emergencydrill"
                                                            key="emergencydrill.linkAuditOpinion"/></td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkAuditOpinion}" beanId="ItawSystemDictTypeDao"/>
                            </td>

                            <td class="label"><bean:message bundle="emergencydrill"
                                                            key="emergencydrill.linkIsAmendmentDrill"/></td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkIsAmendmentDrill}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="emergencydrill" key="emergencydrill.linkAuditResult"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkAuditResult" scope="page"/></pre>
                            </td>
                        </tr>


                        <%
                                }
                            }
                        %>


                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("AmendmentProgramTask")) {%>
                        <%
                            if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 96 ||
                                    baselink.getOperateType().intValue() == 11)) {
                        %>


                        <tr>
                            <td class="column-title">
                                <bean:message bundle="emergencydrill" key="emergencydrill.linkAmendmentDrillNote"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkAmendmentDrillNote" scope="page"/></pre>
                            </td>
                        </tr>

                        <tr>
                            <td class="label"><bean:message bundle="emergencydrill"
                                                            key="emergencydrill.linkEmergencyPreparedness"/></td>
                            <td colspan='3'>
                                <eoms:attachment name="baselink" property="linkEmergencyPreparedness" scope="page"
                                                 idField="${sheetPageName}linkEmergencyPreparedness"
                                                 appCode="emergencydrill"
                                                 viewFlag="Y"/>
                            </td>
                        </tr>


                        <%
                                }
                            }
                        %>


                        <%if (baselink.getActiveTemplateId() != null && (baselink.getActiveTemplateId().equals("cc") || baselink.getActiveTemplateId().equals("reply") || baselink.getActiveTemplateId().equals("advice") || baselink.getOperateType().intValue() == 4)) {%>

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
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.remark"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
                            </td>
                        </tr>


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
                url: "emergencydrill.do?method=getJsonLink&id=" + id + "&beanName=EmergencyDrill",
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