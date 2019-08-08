<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="com.boco.eoms.sheet.base.task.ITask" %>
<%@page import="com.boco.eoms.sheet.commonchangedeploy.model.CommonChangeDeployLink" %>
<%@page import="com.boco.eoms.sheet.base.model.BaseMain" %>

<%
    CommonChangeDeployLink PriLink = (CommonChangeDeployLink) request.getAttribute("preLink");

%>
<script type="text/javascript" src="${app}/scripts/widgets/TabPanel.js"></script>
<script type="text/javascript" src="${app}/scripts/Sheet.js"></script>

<!-- 查看页面公共JS，包括Ext.onReady，强制归档，强制作废，阶段建议 -->
==> ${linkServiceName}
==> ${dictSheetName}
<jsp:include page="/WEB-INF/pages/wfworksheet/common/sheet/detailJs.jsp"/>

<!-- 工单标签 -->
<div id="sheet-detail-page">
    <!-- 工单基本信息 -->
    <div id="sheetinfo">
        <logic:present name="sheetMain" scope="request">
            <%@ include file="/WEB-INF/pages/wfworksheet/common/sheet/basedetailnew.jsp" %>
            <br/>

            <!-- 导入工单的基本信息 -->
            <bean:define id="readOnly" value="true"/>
            <%@ include file="/WEB-INF/pages/wfworksheet/commonchangedeploy/inputmainDetail.jsp" %>
        </logic:present>
    </div>
    <!-- 工单基本信息结束 -->
</div>
<!-- 工单标签结束-->


<!-- 如果taskName等于cc,reply,adivce或者是从管理者页面入口进入，就不显示工单操作的下拉框 -->
<c:choose>
    <c:when test="${(taskName == 'cc' || taskName == 'reply' || taskName == 'advice') || entryAdmin == 'true'}">
        <br/>
        <!-- 工单操作的下拉框不显示 -->
        <div class="sheet-deal-content" id="sheet-deal-content"></div>
        <div class="sheet-deal-content" id="sheet-deal-content-self-force"></div>
    </c:when>

    <c:otherwise>
        <!-- 下面是公共的URL -->
        <!-- 子任务名，需要自己写正确 -->
        <bean:define id="subTask" value="SubTask"/>
        <%@ include file="/WEB-INF/pages/wfworksheet/common/sheet/dealPageCommonURL.jsp" %>
        <!-- 同意驳回继续派发 -->
        <c:url var="urlShowTransmitDealPage" value="${module}.do" scope="request">
            <c:param name="method" value="newShowInputDealPage"/>
            <c:param name="sheetKey" value="${sheetMain.id}"/>
            <c:param name="piid" value="${sheetMain.piid}"/>
            <c:param name="taskId" value="${taskId}"/>
            <c:param name="taskName" value="${taskName}"/>
            <c:param name="operateRoleId" value="${operateRoleId}"/>
            <c:param name="TKID" value="${TKID}"/>
            <c:param name="taskStatus" value="${taskStatus}"/>
            <c:param name="preLinkId" value="${preLinkId}"/>
            <c:param name="teamFlag" value="${teamFlag}"/>
            <c:param name="operateType" value="1"/>
        </c:url>
        <!--同意驳回不再派发 -->
        <c:url var="urlShowAcceptRejectPage" value="${module}.do" scope="request">
            <c:param name="method" value="newShowInputDealPage"/>
            <c:param name="sheetKey" value="${sheetMain.id}"/>
            <c:param name="piid" value="${sheetMain.piid}"/>
            <c:param name="taskId" value="${taskId}"/>
            <c:param name="taskName" value="${taskName}"/>
            <c:param name="operateRoleId" value="${operateRoleId}"/>
            <c:param name="TKID" value="${TKID}"/>
            <c:param name="taskStatus" value="${taskStatus}"/>
            <c:param name="preLinkId" value="${preLinkId}"/>
            <c:param name="operateType" value="100"/>
        </c:url>
        <!-- 公共的URL 结束 -->

        <c:if test="${!empty taskStatus}">
            <div class="sheet-deal">
                <div class='sheet-deal-header'>
                    <!-- 下面是各个处理环节的下拉框功能 -->
                    <table>
                        <tr>
                            <td width="50%">
                                <bean:message bundle="sheet" key="sheet.deal"/>:
                                <select id="dealSelector">

                                    <c:if test="${sheetMain.status != 1}">
                                        <c:if test="${taskStatus==2}">
                                            <c:if test="${preLink.operateType==4}">
                                                <c:if test="${taskName == 'AuditTask' || taskName == 'PlanTask' }">
                                                    <option value="${urlShowAcceptRejectPage}"><bean:message
                                                            bundle="sheet" key="common.acceptReject"/></option>
                                                    <option value="${urlShowTransmitDealPage}"><bean:message
                                                            bundle="sheet" key="common.acceptRejectReSend"/></option>
                                                </c:if>
                                            </c:if>
                                            <c:if test="${displayconfirm == 'true' }">
                                                <!-- 确认受理 -->
                                                <option value="${urlShowAcceptDealPage}"><bean:message bundle="sheet"
                                                                                                       key="common.accept"/></option>

                                                <c:if test="${displayreject == 'true' }">
                                                    <!-- 驳回 -->
                                                    <option value="${urlShowRejectBackPage}"><bean:message
                                                            bundle="sheet" key="common.rejectBack"/></option>
                                                </c:if>
                                            </c:if>

                                            <!-- 不需要确认受理直接进入操作界面 -->
                                            <c:if test="${displayconfirm == 'false' }">
                                                <c:if test="${(ifsub == '' || ifsub == 'false') && (ifwaitfor == 'false')}">
                                                    <!--产生环节中的操作 -->
                                                    <logic:notEmpty name="stempNameList">
                                                        <logic:iterate id="stempName" name="stempNameList">
                                                            <option value="${stempNameAndUrl[stempName]}">${stempName}</option>
                                                        </logic:iterate>
                                                    </logic:notEmpty>
                                                </c:if>
                                            </c:if>

                                        </c:if>
                                    </c:if>

                                    <!-- 流程步骤中的操作 -->
                                    <c:if test="${taskStatus==8}">

                                        <c:if test="${(ifsub == '' || ifsub == 'false') && (ifwaitfor == 'false')}">
                                            <!--产生环节中的操作 -->
                                            <logic:notEmpty name="stempNameList">
                                                <logic:iterate id="stempName" name="stempNameList">
                                                    <option value="${stempNameAndUrl[stempName]}">${stempName}</option>
                                                </logic:iterate>
                                            </logic:notEmpty>
                                        </c:if>


                                        <!-- 这个页面包括移交，阶段回复，分派公共操作 -->
                                        <%@ include
                                                file="/WEB-INF/pages/wfworksheet/common/sheet/dealPageSelectorOption.jsp" %>

                                    </c:if>

                                </select>

                            </td>
                        </tr>
                    </table>
                </div>
                <!-- 各个处理环节的下拉框功能结束 -->
                <!-- 用ajax显示的内容 -->
                <div class="sheet-deal-content" id="sheet-deal-content"></div>

            </div>
        </c:if>
    </c:otherwise>
</c:choose>


<!-- 公共的强制归档，强制作废等按钮 -->
<div id="buttonDisplay" style="display:block">
    <jsp:include page="/WEB-INF/pages/wfworksheet/common/sheet/dealPageHoldButtons.jsp"/>
</div>


<%@ include file="/common/footer_eoms.jsp" %>