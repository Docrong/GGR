<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<div id="history" class="panel">
    <div class="tabtool-history-detail">&nbsp;
        <a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet"
                                                                                      key="sheet.showDetail"/></a>
    </div>
    <%int jNum = 0;%>
    <logic:present name="HISTORY" scope="request">
        <logic:iterate id="baselink" name="HISTORY"
                       type="com.boco.eoms.sheet.businessimplement.model.BusinessImplementLink">
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
                    <eoms:dict key="dict-sheet-businessimplement" dictId="activeTemplateId"
                               itemId="${baselink.activeTemplateId}" beanId="id2descriptionXML"/>
                    <bean:message bundle="businessimplement" key="businessimplement.operateType"/>:
                    <eoms:dict key="dict-sheet-businessimplement" dictId="mainOperateType"
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
                            <!-- 			<%if(baselink.getOperateRoleId()!=null && ! baselink.getOperateRoleId().equals("")) {%>
  				  <td class="column-title">
  				      <bean:message bundle="sheet" key="linkForm.operateRoleName"/>
  				  </td>
  				  <td class="column-content">
  				    <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemSubRoleDao" /> 
  				    <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemUserDao" />&nbsp;  				   
  			      </td>
  			     
  			      <%} %> -->

                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.operateTime"/>
                            </td>
                            <td class="column-content">
                                <bean:write name="baselink" property="operateTime" format="yyyy-MM-dd HH:mm:ss"
                                            scope="page"/>
                            </td>

                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.operaterContact"/>
                            </td>
                            <td class="column-content">
                                <bean:write name="baselink" property="operaterContact" scope="page"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="businessimplement" key="businessimplement.operateType"/>
                            </td>
                            <td class="column-content">
                                <eoms:dict key="dict-sheet-businessimplement" dictId="mainOperateType"
                                           itemId="${baselink.operateType}" beanId="id2descriptionXML"/>
                            </td>
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
                        <!-- 开始字段 -->
                        <c:if test="${!empty baselink.activeTemplateId && baselink.activeTemplateId == 'ImplementDealTask'}">
                            <c:if test="${!empty baselink.operateType && (baselink.operateType == '91'||baselink.operateType == '11')}">


                                <tr>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement"
                                                      key="businessimplement.linkArugmentlevel"/>
                                    </td>
                                    <td class="column-content">
                                        <eoms:id2nameDB id="${baselink.linkArugmentlevel}"
                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement"
                                                      key="businessimplement.linkNeedFinishTime"/>
                                    </td>
                                    <td class="column-content">
                                        <bean:write name="baselink" property="linkNeedFinishTime"
                                                    format="yyyy-MM-dd HH:mm:ss" scope="page"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement"
                                                      key="businessimplement.linkSenderOpinition"/>
                                    </td>
                                    <td class="column-content" colspan="3">
                                        <pre> <bean:write name="baselink" property="linkSenderOpinition"
                                                          scope="page"/></pre>
                                    </td>
                                </tr>

                            </c:if>
                        </c:if>
                        <c:if test="${!empty baselink.activeTemplateId && baselink.activeTemplateId == 'ProjectDealTask'}">
                            <c:if test="${!empty baselink.operateType && (baselink.operateType == '93'||baselink.operateType == '11')}">
                                <tr>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement" key="businessimplement.linkDoResourt"/>
                                    </td>
                                    <td class="column-content">
                                        <eoms:id2nameDB id="${baselink.linkDoResourt}" beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement" key="businessimplement.linkEleNeed"/>
                                    </td>
                                    <td class="column-content">
                                        <pre><bean:write name="baselink" property="linkEleNeed" scope="page"/></pre>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement" key="businessimplement.linkStartA"/>
                                    </td>
                                    <td class="column-content">
                                        <pre><bean:write name="baselink" property="linkStartA" scope="page"/></pre>
                                    </td>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement"
                                                      key="businessimplement.linkACompteHome"/>
                                    </td>
                                    <td class="column-content">
                                        <pre><bean:write name="baselink" property="linkACompteHome" scope="page"/></pre>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement"
                                                      key="businessimplement.linkABusinessDDF"/>
                                    </td>
                                    <td class="column-content">
                                        <pre><bean:write name="baselink" property="linkABusinessDDF"
                                                         scope="page"/></pre>
                                    </td>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement" key="businessimplement.linkADevHost"/>
                                    </td>
                                    <td class="column-content">
                                        <pre><bean:write name="baselink" property="linkADevHost" scope="page"/></pre>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement"
                                                      key="businessimplement.linkATrasitionDDF"/>
                                    </td>
                                    <td class="column-content">
                                        <pre><bean:write name="baselink" property="linkATrasitionDDF"
                                                         scope="page"/></pre>
                                    </td>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement"
                                                      key="businessimplement.linkScopeTrasitionHost"/>
                                    </td>
                                    <td class="column-content">
                                        <pre><bean:write name="baselink" property="linkScopeTrasitionHost"
                                                         scope="page"/></pre>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement" key="businessimplement.linkEndZ"/>
                                    </td>
                                    <td class="column-content">
                                        <pre><bean:write name="baselink" property="linkEndZ" scope="page"/></pre>
                                    </td>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement" key="businessimplement.linkDevType"/>
                                    </td>
                                    <td class="column-content">
                                        <eoms:id2nameDB id="${baselink.linkDevType}" beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                </tr>

                                <tr>
                                    <td class="column-title">
                                        开通方式
                                    </td>
                                    <td class="column-content" colspan="3">
                                        <eoms:id2nameDB id="${baselink.linkOne}" beanId="ItawSystemDictTypeDao"/>
                                    </td>

                                </tr>
                                <tr>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement"
                                                      key="businessimplement.linkFailureResson"/>
                                    </td>
                                    <td class="column-content" colspan="3">
                                        <pre><bean:write name="baselink" property="linkFailureResson"
                                                         scope="page"/></pre>
                                    </td>

                                </tr>

                            </c:if>
                        </c:if>
                        <c:if test="${!empty baselink.activeTemplateId && baselink.activeTemplateId == 'CityNetTask'}">
                            <c:if test="${!empty baselink.operateType && (baselink.operateType == '741'||baselink.operateType == '11')}">
                                <tr>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement"
                                                      key="businessimplement.linkDesinResourt"/>
                                    </td>
                                    <td class="column-content">
                                        <eoms:id2nameDB id="${baselink.linkDesinResourt}"
                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement" key="businessimplement.linkDesinType"/>
                                    </td>
                                    <td class="column-content">
                                        <eoms:id2nameDB id="${baselink.linkDesinType}" beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement" key="businessimplement.linkIPAddress"/>
                                    </td>
                                    <td class="column-content">
                                        <pre><bean:write name="baselink" property="linkIPAddress" scope="page"/></pre>
                                    </td>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement"
                                                      key="businessimplement.linkSubNetNumber"/>
                                    </td>
                                    <td class="column-content">
                                        <pre><bean:write name="baselink" property="linkSubNetNumber"
                                                         scope="page"/></pre>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement" key="businessimplement.linkGetWay"/>
                                    </td>
                                    <td class="column-content">
                                        <pre><bean:write name="baselink" property="linkGetWay" scope="page"/></pre>
                                    </td>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement" key="businessimplement.linkVLAN"/>
                                    </td>
                                    <td class="column-content">
                                        <pre><bean:write name="baselink" property="linkVLAN" scope="page"/></pre>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement"
                                                      key="businessimplement.linkFailureResson"/>
                                    </td>
                                    <td class="column-content" colspan="3">
                                        <pre><bean:write name="baselink" property="linkFailureResson"
                                                         scope="page"/></pre>
                                    </td>

                                </tr>
                            </c:if>
                        </c:if>
                        <c:if test="${!empty baselink.activeTemplateId && baselink.activeTemplateId == 'TrasitionTask'}">
                            <c:if test="${!empty baselink.operateType && (baselink.operateType == '743'||baselink.operateType == '11')}">

                                <tr>
                                    <td class="label">
                                        <bean:message bundle="businessimplement"
                                                      key="businessimplement.linkEleCallResourt"/>*
                                    </td>
                                    <td colspan="3">
                                        <eoms:id2nameDB id="${baselink.linkEleCallResourt}"
                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label">
                                        <bean:message bundle="businessimplement"
                                                      key="businessimplement.linkTrasitionEleNumber"/>*
                                    </td>
                                    <td>
                                        <pre><bean:write name="baselink" property="linkTrasitionEleNumber"
                                                         scope="page"/></pre>
                                    </td>
                                    <td class="label">
                                        <bean:message bundle="businessimplement"
                                                      key="businessimplement.linkGroupUserAddressZone"/>*
                                    </td>
                                    <td>
                                        <pre><bean:write name="baselink" property="linkGroupUserAddressZone"
                                                         scope="page"/></pre>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label"><bean:message bundle="businessimplement"
                                                                    key="businessimplement.linkFailureResson"/> *
                                    </td>
                                    <td colspan="3">
                                        <pre><bean:write name="baselink" property="linkFailureResson"
                                                         scope="page"/></pre>
                                    </td>
                                </tr>
                            </c:if>
                        </c:if>
                        <c:if test="${!empty baselink.activeTemplateId && baselink.activeTemplateId == 'ApnTask'}">
                            <c:if test="${!empty baselink.operateType && (baselink.operateType == '742'||baselink.operateType == '11')}">
                                <tr>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement"
                                                      key="businessimplement.linkDesinResourt"/>
                                    </td>
                                    <td class="column-content">
                                        <eoms:id2nameDB id="${baselink.linkDesinResourt}"
                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement"
                                                      key="businessimplement.linkGroupUserAddressZone"/>
                                    </td>
                                    <td class="column-content">
                                        <pre><bean:write name="baselink" property="linkGroupUserAddressZone"
                                                         scope="page"/></pre>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement"
                                                      key="businessimplement.linkFailureResson"/>
                                    </td>
                                    <td class="column-content" colspan="3">
                                        <pre><bean:write name="baselink" property="linkFailureResson"
                                                         scope="page"/></pre>
                                    </td>

                                </tr>
                            </c:if>
                        </c:if>
                        <c:if test="${!empty baselink.activeTemplateId && baselink.activeTemplateId == 'BusinessTestTask'}">
                            <c:if test="${!empty baselink.operateType && (baselink.operateType == '75'||baselink.operateType == '11')}">

                                <tr>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement"
                                                      key="businessimplement.linkDealResourt"/>
                                    </td>
                                    <td class="column-content" colspan="3">
                                        <eoms:id2nameDB id="${baselink.linkDealResourt}"
                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>

                                </tr>
                                <tr>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement"
                                                      key="businessimplement.linkDealDescriptin"/>
                                    </td>
                                    <td class="column-content" colspan="3">
                                        <pre><bean:write name="baselink" property="linkDealDescriptin"
                                                         scope="page"/></pre>
                                    </td>

                                </tr>
                                <!-- 附件挂接 注意 appCode -->
                                <tr>
                                    <td class="column-title">
                                        <bean:message bundle="sheet" key="linkForm.accessories"/>
                                    </td>
                                    <td class="column-content" colspan=3>
                                        <eoms:attachment name="baselink" property="nodeAccessories"
                                                         scope="page" idField="nodeAccessories"
                                                         appCode="businessimplement"
                                                         viewFlag="Y"/>
                                    </td>
                                </tr>
                            </c:if>
                        </c:if>
                        <c:if test="${!empty baselink.activeTemplateId && baselink.activeTemplateId == 'ApnTask'}">
                            <c:if test="${!empty baselink.operateType && (baselink.operateType == '95'||baselink.operateType == '11')}">
                                <tr>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement"
                                                      key="businessimplement.linkEleCallResourt"/>
                                    </td>
                                    <td class="column-content">
                                        <pre><bean:write name="baselink" property="linkEleCallResourt"
                                                         scope="page"/></pre>
                                    </td>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement"
                                                      key="businessimplement.linkTrasitionEleNumber"/>
                                    </td>
                                    <td class="column-content">
                                        <pre><bean:write name="baselink" property="linkTrasitionEleNumber"
                                                         scope="page"/></pre>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement"
                                                      key="businessimplement.linkGroupUserAddressZone"/>
                                    </td>
                                    <td class="column-content" colspan="3">
                                        <pre><bean:write name="baselink" property="linkGroupUserAddressZone"
                                                         scope="page"/></pre>
                                    </td>

                                </tr>
                                <tr>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement"
                                                      key="businessimplement.linkFailureResson"/>
                                    </td>
                                    <td class="column-content" colspan="3">
                                        <pre><bean:write name="baselink" property="linkFailureResson"
                                                         scope="page"/></pre>
                                    </td>

                                </tr>
                            </c:if>
                        </c:if>
                        <c:if test="${!empty baselink.activeTemplateId && baselink.activeTemplateId == 'DredgeAffirmTask'}">
                            <c:if test="${!empty baselink.operateType && (baselink.operateType == '76'||baselink.operateType == '11')}">
                                <tr>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement"
                                                      key="businessimplement.linkDealResourt"/>
                                    </td>
                                    <td class="column-content" colspan="3">
                                        <eoms:id2nameDB id="${baselink.linkDealResourt}"
                                                        beanId="ItawSystemDictTypeDao"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="label">网络部门联系人</td>
                                    <td>${sheetMain.mainGroupConnPerson}</td>
                                    <td class="label">网络部门联系人电话</td>
                                    <td>${sheetMain.mainGroupConnType}</td>
                                </tr>
                                <tr>
                                    <td class="column-title">
                                        <bean:message bundle="businessimplement"
                                                      key="businessimplement.linkDealDescriptin"/>
                                    </td>
                                    <td class="column-content" colspan="3">
                                        <pre><bean:write name="baselink" property="linkDealDescriptin"
                                                         scope="page"/></pre>
                                    </td>

                                </tr>
                            </c:if>
                        </c:if>


                        <c:if test="${!empty baselink.activeTemplateId && (baselink.activeTemplateId == 'HoldTask') }">
                            <c:if test="${!empty baselink.operateType && (baselink.operateType == '17')}">

                                <tr>
                                    <td class="column-title">
                                        备注说明
                                    </td>
                                    <td class="column-content" colspan=3>
                                        <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
                                    </td>
                                </tr>

                            </c:if>
                        </c:if>

                        <c:if test="${!empty baselink.operateType && baselink.operateType == '8'}">
                            <tr>
                                <td class="column-title">
                                    <bean:message bundle="businessimplement" key="businessimplement.yijiaoresion"/>
                                </td>
                                <td class="column-content" colspan="3">
                                    <pre><bean:write name="baselink" property="transferReason" scope="page"/></pre>
                                </td>
                            </tr>
                        </c:if>

                        <c:if test="${!empty baselink.operateType && baselink.operateType == '10'}">

                            <tr>
                                <td class="column-title">
                                    <bean:message bundle="businessimplement" key="businessimplement.fenpairesion"/>
                                </td>
                                <td class="column-content" colspan="3">
                                    <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
                                </td>
                            </tr>

                        </c:if>
                        <%if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 9 || baselink.getOperateType().intValue() == 1 || baselink.getOperateType().intValue() == 10 || baselink.getOperateType().intValue() == 6 || baselink.getOperateType().intValue() == 7 || baselink.getOperateType().intValue() == 30 || baselink.getOperateType().intValue() == -11)) {%>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.remark"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
                            </td>
                        </tr>

                        <%}%>


                        <%if (baselink.getActiveTemplateId() != null && baselink.getOperateType().intValue() == 9) {%>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.remark"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
                            </td>
                        </tr>
                        <%} %>
                        <%if (baselink.getActiveTemplateId() != null && (baselink.getOperateType().intValue() == -12 || baselink.getOperateType().intValue() == -14)) {%>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="mainForm.cancelReason"/>
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
                url: "businessimplement.do?method=getJsonLink&id=" + id + "&beanName=BusinessImplement",
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
