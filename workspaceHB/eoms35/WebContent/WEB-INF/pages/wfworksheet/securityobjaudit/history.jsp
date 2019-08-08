<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>


<div id="history" class="panel">
    <div class="tabtool-history-detail">&nbsp;
        <a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet"
                                                                                      key="sheet.showDetail"/></a>
    </div>

    <%int jNum = 0;%>

    <logic:present name="HISTORY" scope="request">
        <bean:define id="readOnly" value="true"/>
        <logic:iterate id="baselink" name="HISTORY"
                       type="com.boco.eoms.sheet.securityobjaudit.model.SecurityObjAuditLink">
            <%
                jNum += 1;
                String divName = "buzhou" + jNum;
            %>
            <div class="history-item">&nbsp;
                <!-- 历史列表标题开始 -->
                <div class="history-item-title">
                    <%=jNum%>.
                    <bean:write name="baselink" property="operateTime" formatKey="date.formatDateTimeAll" bundle="sheet"
                                scope="page"/>&nbsp;
                    <eoms:id2nameDB id="${baselink.operateUserId}" beanId="tawSystemUserDao"/>
                    <bean:message bundle="sheet" key="task.operateName"/>:
                    <eoms:dict key="dict-sheet-securityobjaudit" dictId="activeTemplateId"
                               itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML"/>
                    <bean:message bundle="securityobjaudit" key="securityobjaudit.operateType"/>:
                    <eoms:dict key="dict-sheet-securityobjaudit" dictId="mainOperateType"
                               itemId="${baselink.operateType}" beanId="id2descriptionXML"/>
                    <img class="switchIcon" src="${app}/images/icons/closed.gif" align="absmiddle"/>
                </div>
                <!-- 历史列表标题结束 -->

                <!-- 历史步骤的详细信息-->
                <div class="history-item-content hide">
                    <table class="formTable" width="100%" cellpadding="0" cellspacing="0">
                        <!-- 开头的公共部份 -->
                        <tr>
                            <td class="label">
                                <bean:message bundle="sheet" key="linkForm.operateUserName"/>
                            </td>
                            <td class="content">
                                <eoms:id2nameDB id="${baselink.operateUserId}" beanId="tawSystemUserDao"/>&nbsp;
                            </td>
                            <td class="label">
                                <bean:message bundle="sheet" key="linkForm.operateDeptName"/>
                            </td>
                            <td class="content">
                                <eoms:id2nameDB id="${baselink.operateDeptId}" beanId="tawSystemDeptDao"/>&nbsp;
                            </td>
                        </tr>
                        <tr>
                            <td class="label">
                                <bean:message bundle="${module}" key="${module}.operateType"/>
                            </td>
                            <td class="content">
                                <eoms:dict key="dict-sheet-${module}" dictId="mainOperateType"
                                           itemId="${baselink.operateType}" beanId="id2nameXML"/>
                            </td>
                            <td class="label">
                                <bean:message bundle="sheet" key="linkForm.operateRoleName"/>
                            </td>
                            <td class="content">
                                <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemSubRoleDao"/>
                                <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemUserDao"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="label">
                                <bean:message bundle="sheet" key="linkForm.operaterContact"/>
                            </td>
                            <!-- 是新建，草稿派发，作废 -->
                            <%if (baselink.getOperateType().intValue() == 0 || baselink.getOperateType().intValue() == 3 || baselink.getOperateType().intValue() == -12) {%>
                            <td class="content">
                                    ${sheetMain.sendContact}
                            </td>
                            <%} else {%>
                            <td class="content">
                                    ${baselink.operaterContact}
                            </td>
                            <% }%>
                            <%if (baselink.getOperateType() != null && baselink.getOperateType().intValue() != 61) { %>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.toOrgName"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemSubRoleDao"/>
                                <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemUserDao"/>&nbsp;&nbsp;
                            </td>
                            <%} else {%>
                            <td class="column-title"></td>
                            <td class="column-content"></td>
                            <% }%>
                        </tr>
                        <tr>
                            <td class="label">
                                <bean:message bundle="sheet" key="linkForm.operateTime"/>
                            </td>
                            <td class="content">
                                <bean:write name="baselink" property="operateTime" format="yyyy-MM-dd HH:mm:ss"
                                            scope="page"/>
                            </td>
                            <%if (baselink.getNodeCompleteLimit() != null) {%>
                            <td class="label">
                                <bean:message bundle="sheet" key="linkForm.acceptLimit"/>
                            </td>
                            <td class="content">
                                <bean:write name="baselink" property="nodeCompleteLimit" format="yyyy-MM-dd HH:mm:ss"
                                            scope="page"/>
                            </td>
                            <%} else {%>
                            <td class="label">
                            </td>
                            <td class="content">
                            </td>
                            <% }%>
                        </tr>
                        <!-- 开头的公共部份结束 -->


                        <!-- 流程中的历史页面 -->


                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("Audit")) {%>

                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 102 || baselink.getOperateType().intValue() == 11)) { %>


                        <tr>

                            <td class="label">
                                <!-- 审核结果 -->
                                <bean:message bundle="securityobjaudit" key="securityObjAuditLink.linkAuditResult"/>
                            </td>
                            <td class="content">

                                <pre>${baselink.linkAuditResult}</pre>
                            </td>


                            <td class="label">
                                <!-- 审核意见 -->
                                <bean:message bundle="securityobjaudit" key="securityObjAuditLink.linkAuditOpinion"/>
                            </td>
                            <td class="content">

                                <pre>${baselink.linkAuditOpinion}</pre>
                            </td>


                        </tr>


                        <tr>

                            <td class="label">
                                <!-- 挂起状态 -->
                                <bean:message bundle="securityobjaudit" key="securityObjAuditLink.linkUpStuts"/>
                            </td>
                            <td class="content">

                                <pre>${baselink.linkUpStuts}</pre>
                            </td>


                            <td class="label">
                                <!-- 挂起原因 -->
                                <bean:message bundle="securityobjaudit" key="securityObjAuditLink.linkUpReason"/>
                            </td>
                            <td class="content">

                                <pre>${baselink.linkUpReason}</pre>
                            </td>


                        </tr>


                        <tr>

                            <td class="label">
                                <!-- 操作描述 -->
                                <bean:message bundle="securityobjaudit" key="securityObjAuditLink.linkOperation"/>
                            </td>
                            <td class="content">

                                <pre>${baselink.linkOperation}</pre>
                            </td>


                            <td class="label">
                                <!-- 审批结果 -->
                                <bean:message bundle="securityobjaudit" key="securityObjAuditLink.linkAuditResult"/>
                            </td>
                            <td class="content">

                                <pre>${baselink.linkAuditResult}</pre>
                            </td>


                        </tr>


                        <tr>

                            <td class="label">
                                <!-- 审核意见 -->
                                <bean:message bundle="securityobjaudit" key="securityObjAuditLink.linkAuditOpinion"/>
                            </td>
                            <td class="content">

                                <pre>${baselink.linkAuditOpinion}</pre>
                            </td>


                            <td class="label">
                                <!-- 解除说明 -->
                                <bean:message bundle="securityobjaudit" key="securityObjAuditLink.linkRelieveState"/>
                            </td>
                            <td class="content">

                                <pre>${baselink.linkRelieveState}</pre>
                            </td>


                        </tr>


                        <tr>

                            <td class="label">
                                <!-- 最长处理时间 -->
                                <bean:message bundle="securityobjaudit" key="securityObjAuditLink.mainDealTime"/>
                            </td>
                            <td class="content">

                                    ${eoms:date2String(baselink.mainDealTime)}


                            <td class="label">
                                <!-- 最长处理时间 -->
                                <bean:message bundle="securityobjaudit" key="securityObjAuditLink.mainDealTime"/>
                            </td>
                            <td class="content">

                                    ${eoms:date2String(baselink.mainDealTime)}


                        </tr>


                        <tr>

                            <td class="label">
                                <!-- 预留字段1 -->
                                <bean:message bundle="securityobjaudit" key="securityObjAuditLink.linkExtend1"/>
                            </td>
                            <td class="content">

                                <pre>${baselink.linkExtend1}</pre>
                            </td>


                            <td class="label">
                                <!-- 预留字段1 -->
                                <bean:message bundle="securityobjaudit" key="securityObjAuditLink.linkExtend2"/>
                            </td>
                            <td class="content">

                                <pre>${baselink.linkExtend2}</pre>
                            </td>


                        </tr>


                        <tr>

                            <td class="label">
                                <!-- 预留字段1 -->
                                <bean:message bundle="securityobjaudit" key="securityObjAuditLink.linkExtend3"/>
                            </td>
                            <td class="content">

                                <pre>${baselink.linkExtend3}</pre>
                            </td>


                            <td class="label">
                                <!-- 预留字段1 -->
                                <bean:message bundle="securityobjaudit" key="securityObjAuditLink.linkExtend4"/>
                            </td>
                            <td class="content">

                                <pre>${baselink.linkExtend4}</pre>
                            </td>


                        </tr>


                        <tr>

                            <td class="label">
                                <!-- 预留字段1 -->
                                <bean:message bundle="securityobjaudit" key="securityObjAuditLink.linkExtend5"/>
                            </td>
                            <td class="content">

                                <pre>${baselink.linkExtend5}</pre>
                            </td>


                        </tr>


                        <%}%>

                        <%}%>


                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("AuditReply")) {%>

                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 104 || baselink.getOperateType().intValue() == 11)) { %>


                        <tr>

                            <td class="label">
                                <!-- 回复对象 -->
                                <bean:message bundle="securityobjaudit" key="securityObjAuditLink.linkReplyObj"/>
                            </td>
                            <td class="content">

                                <pre>${baselink.linkReplyObj}</pre>
                            </td>


                            <td class="label">
                                <!-- 回复说明 -->
                                <bean:message bundle="securityobjaudit" key="securityObjAuditLink.linkReplyExp"/>
                            </td>
                            <td class="content">

                                <pre>${baselink.linkReplyExp}</pre>
                            </td>


                        </tr>


                        <tr>

                            <td class="label">
                                <!-- 备注 -->
                                <bean:message bundle="securityobjaudit" key="securityObjAuditLink.linkRemark"/>
                            </td>
                            <td class="content">

                                <pre>${baselink.linkRemark}</pre>
                            </td>


                        </tr>


                        <%}%>

                        <%}%>


                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("QualityInspection")) {%>

                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 105 || baselink.getOperateType().intValue() == 11)) { %>


                        <tr>

                            <td class="label">
                                <!-- 质检结果 -->
                                <bean:message bundle="securityobjaudit" key="securityObjAuditLink.linkQualityResult"/>
                            </td>
                            <td class="content">

                                <pre>${baselink.linkQualityResult}</pre>


                            <td class="label">
                                <!-- 质检概述 -->
                                <bean:message bundle="securityobjaudit" key="securityObjAuditLink.linkQualityView"/>
                            </td>
                            <td class="content">

                                <pre>${baselink.linkQualityView}</pre>


                        </tr>


                        <%}%>

                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 106 || baselink.getOperateType().intValue() == 11)) { %>


                        <tr>

                            <td class="label">
                                <!-- 质检结果 -->
                                <bean:message bundle="securityobjaudit" key="securityObjAuditLink.linkQualityResult"/>
                            </td>
                            <td class="content">

                                <pre>${baselink.linkQualityResult}</pre>


                            <td class="label">
                                <!-- 质检概述 -->
                                <bean:message bundle="securityobjaudit" key="securityObjAuditLink.linkQualityView"/>
                            </td>
                            <td class="content">

                                <pre>${baselink.linkQualityView}</pre>


                        </tr>


                        <%}%>

                        <%}%>


                        <!-- 流程页面结束 -->

                        <!-- 下面是公共的 -->
                        <!-- 移交或转审 -->
                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 8 || baselink.getOperateType().intValue() == 88)) {%>

                        <tr>
                            <td class="label">
                                <bean:message bundle="sheet" key="linkForm.transmitReason"/>
                            </td>
                            <td class="content" colspan=3>
                                <pre><bean:write name="baselink" property="transferReason" scope="page"/></pre>
                            </td>
                        </tr>


                        <%} %>

                        <!-- 处理回复不通过 或 处理回复通过 -->
                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 6 || baselink.getOperateType().intValue() == 7 || baselink.getOperateType().intValue() == 10 || baselink.getOperateType().intValue() == 30)) {%>

                        <tr>
                            <td class="label">
                                <bean:message bundle="sheet" key="linkForm.remark"/>
                            </td>
                            <td class="content" colspan=3>
                                <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
                            </td>
                        </tr>

                        <%}%>

                        <!-- 抄送，阶段回复，阶段通知 -->
                        <%if (baselink.getActiveTemplateId() != null && (baselink.getActiveTemplateId().equals("cc") || baselink.getActiveTemplateId().equals("reply") || baselink.getActiveTemplateId().equals("advice") || baselink.getOperateType().intValue() == 4)) {%>

                        <tr>
                            <td class="label">
                                <bean:message bundle="sheet" key="linkForm.remark"/>
                            </td>
                            <td class="content" colspan=3>
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
                url: "securityobjaudit.do?method=getJsonLink&id=" + id + "&beanName=SecurityObjAudit",
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