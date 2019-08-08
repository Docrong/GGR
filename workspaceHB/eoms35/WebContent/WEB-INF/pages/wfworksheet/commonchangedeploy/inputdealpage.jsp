<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!-- 保存模板JS，提交后，抄送的业务逻辑处理，和保存信息的公共JS -->
<%@ include file="/WEB-INF/pages/wfworksheet/common/sheet/dealpageJs.jsp" %>


<html:form action="/${module}.do?method=performDealNew" styleId="theform">

    <!-- 处理环节基本信息表头部分内容 -->
    <%@ include file="/WEB-INF/pages/wfworksheet/common/sheet/baseinputlinkhtmlnew.jsp" %>
    <!-- 公共隐藏域 -->
    <%@ include file="/WEB-INF/pages/wfworksheet/common/sheet/dealPageInputHidden.jsp" %>

    <!-- 个性化隐藏域 -->


    <!-- 标题栏 -->
    <br/>
    <table class="formTable">
        <caption><bean:message bundle="${module}" key="${module}.header"/></caption>


        <!-- 环节中处理的内容 -->
        <c:choose>
            <c:when test="${operateType != '1'&& operateType != '100'}">
                <jsp:include page="/WEB-INF/pages/wfworksheet/${module}/workflow/${forwordJsp}.jsp"/>
            </c:when>
            <c:otherwise>

                <!-- 同意驳回继续派发 -->
                <!-- 待审核 -->

                <c:if test="${taskName == 'AuditTask' }">
                    <c:if test="${operateType == '1'}">
                        <input type="hidden" name="operateType" id="operateType" value="1"/>
                        <input type="hidden" name="phaseId" id="phaseId" value="PlanTask"/>
                        <tr>
                            <td class="label">
                                <!-- 审批结果通过 -->
                                <bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkIsCheck"/>*
                            </td colspan='3'>
                            <td>
                                <eoms:comboBox name="linkIsCheck" id="linkIsCheck"

                                               defaultValue="101040102" alt="allowBlank:false" initDicId="1010401"/>

                            </td>
                        </tr>
                        <tr>
                            <td class="label">
                                <!-- 审批意见 -->
                                <bean:message bundle="commonchangedeploy"
                                              key="commonChangeDeployLink.linkCheckComment"/>*
                            </td>
                            <td colspan="3">
												<textarea class="textarea max" name="linkCheckComment"
                                                          id="linkCheckComment"
                                                          alt="allowBlank:false,maxLength:255,vtext:'请填入 审批意见 信息，最多输入 255 字符'">${sheetLink.linkCheckComment}</textarea>

                            </td>
                        </tr>
                    </c:if>
                </c:if>

                <!--同意驳回不再派发 -->

                <c:if test="${operateType == '100'}">
                    <input type="hidden" name="operateType" id="operateType" value="100"/>
                    <input type="hidden" name="phaseId" id="phaseId" value="OverTask"/>
                    <input type="hidden" name="hasNextTaskFlag" id="hasNextTaskFlag" value="true"/>
                    <tr>
                        <td class="label">
                            <bean:message bundle="sheet" key="linkForm.remark"/>
                        </td>
                        <td colspan="3">
										        <textarea name="remark" class="textarea max" id="remark"
                                                          alt="allowBlank:true,width:500,maxLength:1000,vtext:'最多输入500汉字'"
                                                          alt="width:'500px'">${sheetLink.remark}</textarea>
                        </td>
                    </tr>
                </c:if>

                <!-- 同意驳回继续派发 -->
                <!-- 排程 -->

                <c:if test="${taskName == 'PlanTask' }">
                    <c:if test="${operateType == '1'}">
                        <input type="hidden" name="operateType" id="operateType" value="1"/>
                        <input type="hidden" name="phaseId" id="phaseId" value="ImplementTask"/>
                        <tr>
                            <td class="label">
                                <!-- 实施负责人及联系方式 -->
                                <bean:message bundle="commonchangedeploy"
                                              key="commonChangeDeployLink.linkManagerContact"/>*
                            </td>
                            <td class="content">
                                <input type="text" class="text" name="linkManagerContact" id="linkManagerContact"
                                       alt="allowBlank:false,maxLength:32,vtext:'请填入 实施负责人及联系方式 信息，最多输入 32 字符'"
                                       value="${sheetLink.linkManagerContact}"/>
                            </td>
                            <td class="label">
                                <!-- 抄送对象 -->
                                <bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkCopyObject"/>*
                            </td>
                            <td class="content">
                                <input type="text" class="text" name="linkCopyObject" id="linkCopyObject"
                                       alt="allowBlank:false,maxLength:32,vtext:'请填入 抄送对象 信息，最多输入 32 字符'"
                                       value="${sheetLink.linkCopyObject}"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="label">
                                <!-- 计划开始时间 -->
                                <bean:message bundle="commonchangedeploy"
                                              key="commonChangeDeployLink.linkPlanStartTime"/>*
                            </td>
                            <td>
                                <input type="text" class="text" name="linkPlanStartTime" readonly="readonly"
                                       id="linkPlanStartTime" value="${eoms:date2String(sheetLink.linkPlanStartTime)}"
                                       onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>
                            </td>
                            <td class="label">
                                <!-- 计划结束时间 -->
                                <bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkPlanEndTime"/>*
                            </td>
                            <td>
                                <input type="text" class="text" name="linkPlanEndTime" readonly="readonly"
                                       id="linkPlanEndTime" value="${eoms:date2String(sheetLink.linkPlanEndTime)}"
                                       onclick="popUpCalendar(this, this)" alt="allowBlank:false"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="label">
                                <!-- 影响网元范围 -->
                                <bean:message bundle="commonchangedeploy"
                                              key="commonChangeDeployLink.linkEffectCellScope"/>*
                            </td>
                            <td>
                                <input type="text" class="text" name="linkEffectCellScope" id="linkEffectCellScope"
                                       alt="allowBlank:false,maxLength:32,vtext:'请填入 影响网元范围 信息，最多输入 32 字符'"
                                       value="${sheetLink.linkEffectCellScope}"/>
                            </td>
                            <td class="label">
                                <!-- 是否影响业务 -->
                                <bean:message bundle="commonchangedeploy"
                                              key="commonChangeDeployLink.linkEffectBusiness"/>*
                            </td>
                            <td>
                                <eoms:comboBox name="linkEffectBusiness" id="linkEffectBusiness"

                                               defaultValue="${sheetLink.linkEffectBusiness}" alt="allowBlank:false"
                                               initDicId="10301" onchange="executeEffectBusiness(this.value);"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="label">
                                <!-- 影响业务范围及时长 -->
                                <bean:message bundle="commonchangedeploy"
                                              key="commonChangeDeployLink.linkEffectCondition"/>*
                            </td>
                            <td colspan="3">
                                <input type="text" class="text" name="linkEffectCondition" id="linkEffectCondition"
                                       alt="allowBlank:false,maxLength:32,vtext:'请填入 影响业务范围及时长 信息，最多输入 32 字符'"
                                       value="${sheetLink.linkEffectCondition}"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="label">
                                <!-- 涉及业务部门 -->
                                <bean:message bundle="commonchangedeploy"
                                              key="commonChangeDeployLink.linkBusinessDept"/>*
                            </td>
                            <td>
                                <eoms:comboBox name="linkBusinessDept" id="linkBusinessDept"

                                               defaultValue="${sheetLink.linkBusinessDept}" alt="allowBlank:false"
                                               initDicId="1010902"/>
                            </td>
                            <td class="label">
                                <!-- 是否通知客服 -->
                                <bean:message bundle="commonchangedeploy"
                                              key="commonChangeDeployLink.linkIsSendToFront"/>*
                            </td>
                            <td>
                                <eoms:comboBox name="linkIsSendToFront" id="linkIsSendToFront"

                                               defaultValue="${sheetLink.linkIsSendToFront}" alt="allowBlank:false"
                                               initDicId="10301"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="label">
                                <!-- 实施方案 -->
                                <bean:message bundle="commonchangedeploy"
                                              key="commonChangeDeployLink.linkExecuteAccessories"/>*
                            </td>
                            <td colspan="3">
                                <eoms:attachment name="sheetLink" property="linkExecuteAccessories" scope="request"
                                                 idField="linkExecuteAccessories" appCode="commonchangedeploy"/>

                            </td>
                        </tr>
                        <tr>
                            <td class="label">
                                <!-- 影响网管情况 -->
                                <bean:message bundle="commonchangedeploy"
                                              key="commonChangeDeployLink.linkEffectNetInstance"/>*
                            </td>
                            <td colspan="3">
				<textarea class="textarea max" name="linkEffectNetInstance"
                          id="linkEffectNetInstance"
                          alt="allowBlank:false,maxLength:255,vtext:'请填入 影响网管情况 信息，最多输入 255 字符'">${sheetLink.linkEffectNetInstance}</textarea>

                            </td>
                        </tr>
                    </c:if>
                </c:if>
            </c:otherwise>
        </c:choose>
    </table>
    <!-- 驳回判断 -->
    <c:choose>
        <c:when test="${empty fPreTaskName}">
            <input type="hidden" name="phaseId" id="phaseId" value="RejectTask"/>
        </c:when>
        <c:when test="${fPreTaskName == 'DraftTask'}">
            <input type="hidden" name="phaseId" id="phaseId" value="RejectTask"/>
        </c:when>
        <c:otherwise>
            <input type="hidden" name="phaseId" id="phaseId" value="${fPreTaskName}"/>
        </c:otherwise>
    </c:choose>


    <!-- 处理环节中的按钮 -->
    <br>
    <div id="sheetform">

        <c:if test="${operateType == '1'}">
            <!-- 派单树 -->
            <fieldset id="link4">
                <legend>
                    <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
                    <span id="roleName">
				<bean:message bundle="commonchangedeploy" key="role.changeadmin"/>
                </span>
                </legend>
                <div ID="permit3">
                    <eoms:chooser id="sendObject" type="role" roleId="407" flowId="${flowId}"
                                  config="{returnJSON:true,showLeader:true,mainPanelTitle:'可选择的子角色'}"
                                  category="[{id:'dealPerformer',text:'派发',allowBlank:true,vtext:'请选择派发人',limit:'none'},{id:'copyPerformer',text:'抄送',childType:'user,subrole,dept',limit:'none'}]"
                                  data="${sheetLink.sendObject}"/>
                </div>
            </fieldset>
        </c:if>

        <jsp:include page="/WEB-INF/pages/wfworksheet/common/sheet/dealpagebutton.jsp"/>
    </div>


    <!-- 个性化处理环节中的JS -->
    <jsp:include page="/WEB-INF/pages/wfworksheet/${module}/workflow/inputdealpagejs.jsp"/>

</html:form>

 