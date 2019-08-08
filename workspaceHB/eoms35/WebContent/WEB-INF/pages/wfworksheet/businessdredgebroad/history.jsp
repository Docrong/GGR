<%@ include file="/common/taglibs.jsp" %>
<div id="history" class="panel">
    <div class="tabtool-history-detail">&nbsp;
        <a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet"
                                                                                      key="sheet.showDetail"/></a>
    </div>
    <%int jNum = 0;%>
    <logic:present name="HISTORY" scope="request">
        <logic:iterate id="baselink" name="HISTORY"
                       type="com.boco.eoms.sheet.businessdredgebroad.model.BusinessDredgebroadLink">
            <%
                jNum += 1;
                String divName = "buzhou" + jNum;
            %>
            <div class="history-item"><!-- add space to hack ie-->&nbsp;
                <div class="history-item-title">
                    <%=jNum%>.
                    <bean:write name="baselink" property="operateTime" formatKey="date.formatDateTimeAll" bundle="sheet"
                                scope="page"/>&nbsp;
                    <bean:message bundle="sheet" key="task.operateName"/>:
                    <eoms:dict key="dict-sheet-businessdredgebroad" dictId="activeTemplateId"
                               itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML"/>
                    <bean:message bundle="businessdredgebroad" key="businessdredgebroad.operateType"/>:
                    <eoms:dict key="dict-sheet-businessdredgebroad" dictId="mainOperateType"
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
                        <!--  <tr>
  				  <td class="column-title">
  				      <bean:message bundle="sheet" key="linkForm.operateRoleName"/>
  				  </td>
  				  <td class="column-content">
  				      <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemSubRoleDao" />&nbsp;
  			      </td>
  				  <td class="column-title">
  				     <bean:message bundle="sheet" key="linkForm.operateTime"/>
  				  </td>
  				  <td class="column-content">
  				     <bean:write name="baselink" property="operateTime" format="yyyy-MM-dd HH:mm:ss" scope="page"/>
                  </td>                  
  				</tr>-->
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.toOrgName"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemSubRoleDao"/>
                                <eoms:id2nameDB id="${baselink.toOrgRoleId}" beanId="tawSystemUserDao"/>&nbsp;&nbsp;

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

                        <%
                            if (baselink.getActiveTemplateId() != null && (baselink.getActiveTemplateId().equals("TaskCreateAuditHumTask")
                                    || baselink.getActiveTemplateId().equals("TaskCompleteAuditHumTask"))) {
                        %>
                        <%
                            if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 201 || baselink.getOperateType().intValue() == 202
                                    || baselink.getOperateType().intValue() == 208 || baselink.getOperateType().intValue() == 209)) {
                        %>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="businessdredgebroad" key="businessdredgebroad.auditResult"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="auditResult" scope="page"/></pre>
                            </td>
                        </tr>
                        <%
                                }
                            }
                        %>


                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 4)) {%>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="businessdredgebroad" key="businessdredgebroad.rejectCause"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="rejectReason" scope="page"/></pre>
                            </td>
                        </tr>
                        <%} %>

                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("AffirmHumTask")) {%>
                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 7)) {%>
                        <tr>

                            <td class="column-title">
                                <bean:message bundle="businessdredgebroad" key="businessdredgebroad.dealDesc"/>
                            </td>
                            <td class="column-content" colspan='3'>
                                <bean:write name="baselink" property="dealDesc" scope="page"/>
                            </td>
                        </tr>
                        <%} else if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 6)) {%>
                        <tr>

                            <td class="column-title">
                                <bean:message bundle="businessdredgebroad" key="businessdredgebroad.dealDesc"/>
                            </td>
                            <td class="column-content" colspan='3'>
                                <bean:write name="baselink" property="dealDesc" scope="page"/>
                            </td>
                        </tr>
                        <%
                                }
                            }
                        %>

                        <%
                            System.out.println("getOperateType========" + baselink.getOperateType());
                            if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("ExcuteHumTask")) {

                        %>
                        <%if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 111) {%>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="common.dispatchType"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <%
                                    if (baselink.getLinkNetType() != null && !baselink.getLinkNetType().equals("")) {
                                        String[] arrNetType = baselink.getLinkNetType().split(",");
                                        for (int i = 0; i < arrNetType.length; i++) {
                                %>
                                <eoms:id2nameDB id="<%=arrNetType[i] %>" beanId="ItawSystemDictTypeDao"/>&nbsp;
                                <%
                                        }
                                    }
                                %>
                            </td>
                        </tr>

                        <%} %>
                        <% if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 205 || baselink.getOperateType().intValue() == 206)) {%>


                        <tr>
                            <td class="column-title">
                                <bean:message bundle="businessdredgebroad" key="businessdredgebroad.ndeptContact"/>
                            </td>
                            <td class="column-content">
                                <bean:write name="baselink" property="ndeptContact" scope="page"/>
                            </td>

                            <td class="column-title">
                                <bean:message bundle="businessdredgebroad" key="businessdredgebroad.ndeptContactPhone"/>
                            </td>
                            <td class="column-content">
                                <bean:write name="baselink" property="ndeptContactPhone" scope="page"/>
                            </td>
                        </tr>

                        <%if (baselink.getApnID() != null && !baselink.getApnID().equals("")) { %>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="businessdredgebroad" key="businessdredgebroad.apnID"/>
                            </td>
                            <td class="column-content" colspan="3">
                                <pre><bean:write name="baselink" property="apnID" scope="page"/></pre>
                            </td>
                        </tr>
                        <%} %>

                        <%if (baselink.getLoginUserName() != null && !baselink.getLoginUserName().equals("")) { %>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="businessdredgebroad" key="businessdredgebroad.loginUserName"/>
                            </td>
                            <td class="column-content" colspan="3">
                                <pre><bean:write name="baselink" property="loginUserName" scope="page"/></pre>
                            </td>
                        </tr>
                        <%} %>

                        <%if (baselink.getLoginUserPassWord() != null && !baselink.getLoginUserPassWord().equals("")) { %>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="businessdredgebroad" key="businessdredgebroad.loginUserPassWord"/>
                            </td>
                            <td class="column-content" colspan="3">
                                <pre><bean:write name="baselink" property="loginUserPassWord" scope="page"/></pre>
                            </td>
                        </tr>
                        <%} %>

                        <%if (baselink.getNetResCapacity() != null && !baselink.getNetResCapacity().equals("")) { %>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="businessdredgebroad" key="businessdredgebroad.netResCapacity"/>
                            </td>
                            <td class="column-content" colspan="3">
                                <pre><bean:write name="baselink" property="netResCapacity" scope="page"/></pre>
                            </td>
                        </tr>
                        <%} %>

                        <%if (baselink.getExpectFinishdays() != null && !baselink.getExpectFinishdays().equals("")) { %>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="businessdredgebroad" key="businessdredgebroad.expectFinishdays"/>
                            </td>
                            <td class="column-content" colspan="3">
                                <pre><bean:write name="baselink" property="expectFinishdays" scope="page"/></pre>
                            </td>
                        </tr>
                        <%} %>

                        <%if (baselink.getCircuitCode() != null && !baselink.getCircuitCode().equals("")) { %>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="businessdredgebroad" key="businessdredgebroad.circuitCode"/>
                            </td>
                            <td class="column-content" colspan="3">
                                <pre><bean:write name="baselink" property="circuitCode" scope="page"/></pre>
                            </td>
                        </tr>
                        <%} %>

                        <%if (baselink.getClientPgmCapacity() != null && !baselink.getClientPgmCapacity().equals("")) { %>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="businessdredgebroad" key="businessdredgebroad.clientPgmCapacity"/>
                            </td>
                            <td class="column-content" colspan="3">
                                <pre><bean:write name="baselink" property="clientPgmCapacity" scope="page"/></pre>
                            </td>
                        </tr>
                        <%} %>
                        <%if (baselink.getTestReport() != null && !baselink.getTestReport().equals("")) { %>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="businessdredgebroad" key="businessdredgebroad.testReport"/>
                            </td>
                            <td class="column-content" colspan="3">
                                <pre><bean:write name="baselink" property="testReport" scope="page"/></pre>
                            </td>
                        </tr>
                        <%} %>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="businessdredgebroad" key="businessdredgebroad.dealResult"/>
                            </td>
                            <td class="column-content" colspan="3">
                                    <%-- <bean:write name="baselink" property="dealResult" scope="page"/>--%>
                                <eoms:id2nameDB id="${baselink.dealResult}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>

                            <td class="column-title">
                                <bean:message bundle="businessdredgebroad" key="businessdredgebroad.dealDesc"/>
                            </td>
                            <td class="column-content" colspan="3">
                                <bean:write name="baselink" property="dealDesc" scope="page"/>
                            </td>
                        </tr>
                        <!-- add by qinbo begin -->
                        <%if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 205) {%>
                        <tr>
                            <td class="label">${eoms:a2u('业务开通时间')}</td>
                            <td>
                                <bean:write name="baselink" property="loginUserName" format="yyyy-MM-dd HH:mm:ss"
                                            scope="page"/>
                            </td>
                            <td class="label">${eoms:a2u('业务序号')}</td>
                            <td>
                                <pre><bean:write name="baselink" property="apnID" scope="page"/></pre>
                            </td>
                        </tr>
                        <tr>
                            <td class="label">${eoms:a2u('装机地址')}</td>
                            <td colspan='3'>
                                <pre><bean:write name="baselink" property="rejectReason" scope="page"/></pre>
                            </td>

                        </tr>
                        <tr>
                            <td class="label">${eoms:a2u('后端施工人员姓名/工号')}</td>
                            <td>
                                <pre><bean:write name="baselink" property="auditResult" scope="page"/></pre>
                            </td>
                            <td class="label">${eoms:a2u('施工人员所属部门')}</td>
                            <td>
                                <pre><bean:write name="baselink" property="circuitCode" scope="page"/></pre>
                            </td>
                        </tr>
                        <tr>
                            <td class="label">${eoms:a2u('施工人员联系方式')}</td>
                            <td colspan='3'>
                                <pre><bean:write name="baselink" property="loginUserPassWord" scope="page"/></pre>
                            </td>
                            <!-- <td class="label">${eoms:a2u('施工情况')}</td><!-- netResCapacity 1011060 -->
                            <!--<td>
				<eoms:id2nameDB id="${baselink.netResCapacity}" beanId="ItawSystemDictTypeDao"/>
			</td> -->
                        </tr>
                        <tr>
                            <td class="label">${eoms:a2u('回复类型')}</td><!-- clientPgmCapacity 1011061 -->
                            <td>
                                <eoms:id2nameDB id="${baselink.clientPgmCapacity}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="label">${eoms:a2u('待装时间')}</td><!-- completeTime -->
                            <td>
                                <bean:write name="baselink" property="expectFinishdays" format="yyyy-MM-dd HH:mm:ss"
                                            scope="page"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="label">${eoms:a2u('完成原因类型')}</td><!-- linkNetType -->
                            <td colspan='3'>
                                <eoms:id2nameDB id="${baselink.linkNetType}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="label">${eoms:a2u('施工完成原因')}</td>
                            <td colspan='3'>
                                <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
                            </td>
                        </tr>
                        <tr>
                            <td class="label">${eoms:a2u('满意度')}</td><!-- testReport -->
                            <td>
                                <pre><bean:write name="baselink" property="testReport" scope="page"/></pre>
                            </td>
                            <td class="label">${eoms:a2u('客户意见')}</td><!-- dealResult -->
                            <td>
                                <pre><bean:write name="baselink" property="dealResult" scope="page"/></pre>
                            </td>
                        </tr>
                        <%}%>
                        <!-- add by qinbo end -->

                        <%if (baselink.getNodeAccessories() != null && !baselink.getNodeAccessories().equals("")) {%>
                        <tr>
                            <td class="label"><bean:message bundle="sheet" key="linkForm.nodeAccessories"/></td>
                            <td colspan=3>
                                <eoms:attachment name="baselink" property="nodeAccessories"
                                                 scope="page" idField="nodeAccessories" appCode="businessdredgebroad"
                                                 viewFlag="Y"/>
                            </td>
                        </tr>
                        <%}%>

                        <%
                                }
                            }
                        %>

                        <%if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("ExcuteHumTask")) {%>
                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 8)) {%>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="businessdredgebroad" key="businessdredgebroad.yijiao"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <bean:write name="baselink" property="transferReason" scope="page"/>
                            </td>
                        </tr>
                        <%if (baselink.getNodeAccessories() != null && !baselink.getNodeAccessories().equals("")) {%>
                        <tr>
                            <td class="label"><bean:message bundle="sheet" key="linkForm.nodeAccessories"/></td>
                            <td colspan=3>
                                <eoms:attachment name="baselink" property="nodeAccessories"
                                                 scope="page" idField="nodeAccessories" appCode="businessdredgebroad"
                                                 viewFlag="Y"/>
                            </td>
                        </tr>
                        <%}%>
                        <%}%>
                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 10)) {%>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.transferReason"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <bean:write name="baselink" property="transferReason" scope="page"/>
                            </td>
                        </tr>
                        <%if (baselink.getNodeAccessories() != null && !baselink.getNodeAccessories().equals("")) {%>
                        <tr>
                            <td class="label"><bean:message bundle="sheet" key="linkForm.nodeAccessories"/></td>
                            <td colspan=3>
                                <eoms:attachment name="baselink" property="nodeAccessories"
                                                 scope="page" idField="nodeAccessories" appCode="businessdredgebroad"
                                                 viewFlag="Y"/>
                            </td>
                        </tr>
                        <%}%>
                        <%}%>
                        <% }%>

                        <% if (baselink.getRemark() != null && !baselink.getRemark().equals("")) { %>
                        <%if (baselink.getActiveTemplateId() != null && (baselink.getOperateType().intValue() == -10 || baselink.getOperateType().intValue() == -11 || baselink.getOperateType().intValue() == -15 || baselink.getOperateType().intValue() == 9)) {%>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.remark"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
                            </td>
                        </tr>
                        <%} else {%>
                        <tr>
                            <td class="column-title">
                                <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 9)) {%>
                                <bean:message bundle="sheet" key="linkForm.remark"/>
                                <%
                                    }
                                    if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 10)) {
                                %>
                                <bean:message bundle="businessdredgebroad" key="businessdredgebroad.transmitReason"/>
                                <%}%>
                            </td>
                            <td class="column-content" colspan=3>
                                <bean:write name="baselink" property="remark" scope="page"/>
                            </td>
                        </tr>
                        <% }
                        }%>

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
                url: "businessdredgebroad.do?method=getJsonLink&id=" + id + "&beanName=BusinessDredgebroad",
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
