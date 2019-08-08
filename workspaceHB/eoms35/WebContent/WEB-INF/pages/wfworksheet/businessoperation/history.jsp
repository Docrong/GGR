<%@ include file="/common/taglibs.jsp" %>
<div id="history" class="panel">
    <div class="tabtool-history-detail">&nbsp;
        <a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet"
                                                                                      key="sheet.showDetail"/></a>
    </div>

    <%int jNum = 0;%>
    <logic:present name="HISTORY" scope="request">
        <logic:iterate id="baselink" name="HISTORY"
                       type="com.boco.eoms.sheet.businessoperation.model.BusinessOperationLink">
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
                    <eoms:dict key="dict-sheet-businessoperation" dictId="activeTemplateId"
                               itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML"/>
                    <bean:message bundle="businessoperation" key="businessoperation.operateType"/>:
                    <eoms:dict key="dict-sheet-businessoperation" dictId="mainOperateType"
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
                                <bean:message bundle="businessoperation" key="businessoperation.operateType"/>
                            </td>
                            <td class="column-content">
                                <eoms:dict key="dict-sheet-businessoperation" dictId="mainOperateType"
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
                        <% if (baselink.getOperateType() != null && baselink.getOperateType().intValue() != 61 && baselink.getOperateType().intValue() != -15 && baselink.getOperateType().intValue() != -12 && baselink.getOperateType().intValue() != -13 && baselink.getOperateType().intValue() != -14 && baselink.getOperateType().intValue() != 111) {%>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.toOrgName"/>
                            </td>
                            <td class="column-content" colspan="3">
                                <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemSubRoleDao"/>
                                <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemUserDao"/>&nbsp;&nbsp;

                            </td>
                        </tr>
                        <%} %>


                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("operate")) {%>

                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 91 || baselink.getOperateType().intValue() == 111 || baselink.getOperateType().intValue() == 11)) {%>
                        <c:if test="${empty baselink.linkTestResult}">
                            <c:if test="${baselink.operateType!=11 }">
                                <tr>
                                    <td class="label"><bean:message bundle="businessoperation"
                                                                    key="businessoperation.linkOperStartTime"/></td>
                                    <td class="column-content">
                                        <bean:write name="baselink" property="linkOperStartTime"
                                                    format="yyyy-MM-dd HH:mm:ss" scope="page"/>
                                    </td>
                                    <td class="label"><bean:message bundle="businessoperation"
                                                                    key="businessoperation.linkOperEndTime"/></td>
                                    <td>
                                        <bean:write name="baselink" property="linkOperEndTime"
                                                    format="yyyy-MM-dd HH:mm:ss" scope="page"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label"><bean:message bundle="businessoperation"
                                                                    key="businessoperation.linkNetType"/></td>
                                    <td class="column-content" colspan=3>
                                        <%
                                            String[] arrNetType = baselink.getLinkNetType().split(",");
                                            for (int i = 0; i < arrNetType.length; i++) {
                                        %>
                                        <eoms:id2nameDB id="<%=arrNetType[i] %>" beanId="ItawSystemDictTypeDao"/>&nbsp;
                                        <%} %>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label"><bean:message bundle="businessoperation"
                                                                    key="businessoperation.linkBusinessDesc"/></td>
                                    <td class="column-content" colspan=3>
                                        <pre><bean:write name="baselink" property="linkBusinessDesc"
                                                         scope="page"/></pre>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label"><bean:message bundle="businessoperation"
                                                                    key="businessoperation.linkOperateScheme"/></td>
                                    <td class="column-content" colspan=5>
                                        <eoms:attachment name="baselink" property="linkOperateScheme"
                                                         scope="page" idField="${sheetPageName}linkOperateScheme"
                                                         appCode="businessoperation"
                                                         viewFlag="Y"/>
                                    </td>
                                </tr>
                            </c:if>
                        </c:if>
                        <c:if test="${!empty baselink.linkTestResult}">
                            <tr>
                                <td class="label"><bean:message bundle="businessoperation"
                                                                key="businessoperation.linkTestResult"/></td>
                                <td class="column-content" colspan=3>
                                    <eoms:id2nameDB id="${baselink.linkTestResult}" beanId="ItawSystemDictTypeDao"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="label"><bean:message bundle="businessoperation"
                                                                key="businessoperation.linkAlterationAcc"/></td>
                                <td class="column-content" colspan=3>
                                    <eoms:attachment name="baselink" property="linkAlterationAcc"
                                                     scope="page" idField="${sheetPageName}linkAlterationAcc"
                                                     appCode="businessoperation"
                                                     viewFlag="Y"/>
                                </td>
                            </tr>
                        </c:if>
                        <%
                                }
                            }
                        %>
                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("refine")) {%>

                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 92 || baselink.getOperateType().intValue() == 11)) {%>

                        <tr>
                            <td class="label"><bean:message bundle="businessoperation"
                                                            key="businessoperation.linkTGPolicyAcc"/></td>
                            <td class="column-content" colspan=3>
                                <eoms:attachment name="baselink" property="linkTGPolicyAcc"
                                                 scope="page" idField="${sheetPageName}linkTGPolicyAcc"
                                                 appCode="businessoperation"
                                                 viewFlag="Y"/>
                            </td>
                        </tr>
                        <%
                                }
                            }
                        %>


                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("prepare")) {%>

                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 93 || baselink.getOperateType().intValue() == 11)) {%>
                        <tr>
                            <td class="label"><bean:message bundle="businessoperation"
                                                            key="businessoperation.linkDataFile"/></td>
                            <td class="column-content" colspan='3'>
                                <bean:write name="baselink" property="linkDataFile" scope="page"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="label"><bean:message bundle="businessoperation"
                                                            key="businessoperation.linkVerify"/></td>
                            <td class="column-content" colspan='3'>
                                <bean:write name="baselink" property="linkVerify" scope="page"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="label"><bean:message bundle="businessoperation"
                                                            key="businessoperation.linkPassMan"/></td>
                            <td class="column-content" colspan='3'>
                                <bean:write name="baselink" property="linkPassMan" scope="page"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="label"><bean:message bundle="businessoperation"
                                                            key="businessoperation.linkReport"/></td>
                            <td class="column-content" colspan='3'>
                                <bean:write name="baselink" property="linkReport" scope="page"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="label"><bean:message bundle="businessoperation"
                                                            key="businessoperation.linkWorkplan"/></td>
                            <td class="column-content" colspan='3'>
                                <bean:write name="baselink" property="linkWorkplan" scope="page"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="label"><bean:message bundle="businessoperation"
                                                            key="businessoperation.linkMeetingAcc"/></td>
                            <td class="column-content" colspan=3>
                                <eoms:attachment name="baselink" property="linkMeetingAcc"
                                                 scope="page" idField="${sheetPageName}linkMeetingAcc"
                                                 appCode="businessoperation"
                                                 viewFlag="Y"/>
                            </td>
                        </tr>
                        <%
                                }
                            }
                        %>

                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("report")) {%>

                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 94 || baselink.getOperateType().intValue() == 11)) {%>

                        <tr>
                            <td class="label"><bean:message bundle="businessoperation"
                                                            key="businessoperation.linkIsSuccess"/></td>
                            <td class="column-content" colspan=3>
                                <eoms:id2nameDB id="${baselink.linkIsSuccess}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            </td>
                        </tr>
                        <tr>
                            <td class="label"><bean:message bundle="businessoperation"
                                                            key="businessoperation.linkTGReportAcc"/></td>
                            <td class="column-content" colspan=3>
                                <eoms:attachment name="baselink" property="linkTGReportAcc"
                                                 scope="page" idField="${sheetPageName}linkTGReportAcc"
                                                 appCode="businessoperation"
                                                 viewFlag="Y"/>
                            </td>
                        </tr>
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


                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("hold")) {%>

                        <%}%>

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
                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 6 || baselink.getOperateType().intValue() == 7 || baselink.getOperateType().intValue() == 10 || baselink.getOperateType().intValue() == 30) || baselink.getOperateType().intValue() == 11) {%>

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
                url: "businessoperation.do?method=getJsonLink&id=" + id + "&beanName=BusinessOperation",
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