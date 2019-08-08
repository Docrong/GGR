<%@ include file="/common/taglibs.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<div id="history" class="panel">
    <div class="tabtool-history-detail">&nbsp;
        <a href="#" onclick="javascript:switcher.toggle();return false"><bean:message bundle="sheet"
                                                                                      key="sheet.showDetail"/></a>
    </div>

    <%int jNum = 0;%>
    <logic:present name="HISTORY" scope="request">
        <logic:iterate id="baselink" name="HISTORY" type="com.boco.eoms.sheet.commonfault.model.CommonFaultLink">
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
                    <eoms:dict key="dict-sheet-commonfault" dictId="activeTemplateId"
                               itemId="${baselink.activeTemplateId}" beanId="id2nameXML"/>
                    <bean:message bundle="commonfault" key="commonfault.operateType"/>:
                    <eoms:dict key="dict-sheet-commonfault" dictId="mainOperateType" itemId="${baselink.operateType}"
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
                                <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemSubRoleDao"/>
                                <eoms:id2nameDB id="${baselink.operateRoleId}" beanId="tawSystemUserDao"/>&nbsp;
                            </td>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.operaterContact"/>
                            </td>
                            <td class="column-content">
                                <pre><bean:write name="baselink" property="operaterContact" scope="page"/></pre>
                            </td>
                        </tr>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.operateTime"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="operateTime" format="yyyy-MM-dd HH:mm:ss"
                                                 scope="page"/></pre>
                            </td>
                        </tr>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.operateType"/>
                            </td>
                            <td class="column-content">
                                <eoms:dict key="dict-sheet-commonfault" dictId="mainOperateType"
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


                        <%
                            //T1环节
                            if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("FirstExcuteHumTask")) {
                        %>
                        <%
                            //移交T2
                            if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 1) {
                        %>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.mainIfMutualCommunication"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkIfMutualCommunication}"
                                                beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                <pre><bean:message bundle="commonfault" key="commonfault.mainIfSafe"/></pre>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkIfSafe}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkIfGreatFault"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <eoms:id2nameDB id="${baselink.linkIfGreatFault}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkFaultFirstDealDesc"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkFaultFirstDealDesc" scope="page"/></pre>
                            </td>
                        </tr>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkFaultDesc"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkFaultDesc" scope="page"/></pre>
                            </td>
                        </tr>

                        <%} %>

                        <%
                            //处理完成
                            if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 46) {
                        %>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkFaultDealResult"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkFaultDealResult}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkIfGreatFault"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkIfGreatFault}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                归因一级
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSort}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                归因二级
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSubsection}"
                                                beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                归因三级
                            </td>
                            <td colspan="3">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSubsectionTwo}"
                                                beanId="ItawSystemDictTypeDao"/>
                            </td>

                        </tr>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkDealStep"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkDealStep" scope="page"/></pre>
                            </td>
                        </tr>
                        <tr>
                            <td class="label">
                                <bean:message bundle="sheet" key="tawSheetAccessForm.remark"/>
                            </td>
                            <td colspan="3">
                                <bean:write name="baselink" property="remark" scope="page"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkIfExcuteNetChange"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <eoms:id2nameDB id="${baselink.linkIfExcuteNetChange}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkIfFinallySolveProject"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <eoms:id2nameDB id="${baselink.linkIfFinallySolveProject}"
                                                beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkIfAddCaseDataBase"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkIfAddCaseDataBase}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                设备厂商
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkUntreadIdea}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkFaultAvoidTime"/>
                            </td>
                            <td class="column-content">
                                <bean:write name="baselink" property="linkFaultAvoidTime" format="yyyy-MM-dd HH:mm:ss"
                                            scope="page"/>
                            </td>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkOperRenewTime"/>
                            </td>
                            <td class="column-content">
                                <bean:write name="baselink" property="linkOperRenewTime" format="yyyy-MM-dd HH:mm:ss"
                                            scope="page"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkAffectTimeLength"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkAffectTimeLength" scope="page"/></pre>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                是否是手机操作
                            </td>
                            <td class="column-content">
                                <%if (baselink.getLinkIfMobile() != null && baselink.getLinkIfMobile().equals("mobileDeal")) {%>
                                是
                                <%} else {%>
                                否
                                <%}%>
                            </td>
                            <td class="column-title">
                                故障原因
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.faultReason}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                故障归因
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkBackCase}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                回复单位
                            </td>
                            <td class="column-content" colspan="3">
                                <eoms:id2nameDB id="${baselink.recoveryUnit}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                预处理是否准确
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkIfConfirmPreSolve}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                预处理结果不准确原因
                            </td>
                            <td class="column-content" colspan="3">
                                <pre><bean:write name="baselink" property="confirmReason" scope="page"/></pre>
                            </td>
                        </tr>
                        <%} %>


                        <%
                            //分派回复
                            if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 11) {
                        %>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkIfGreatFault"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <eoms:id2nameDB id="${baselink.linkIfGreatFault}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="column-title">
                                归因一级
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSort}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                归因二级
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSubsection}"
                                                beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                归因三级
                            </td>
                            <td colspan="3">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSubsectionTwo}"
                                                beanId="ItawSystemDictTypeDao"/>
                            </td>

                        </tr>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkDealStep"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkDealStep" scope="page"/></pre>
                            </td>
                        </tr>


                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkFaultAvoidTime"/>
                            </td>
                            <td class="column-content">
                                <bean:write name="baselink" property="linkFaultAvoidTime" format="yyyy-MM-dd HH:mm:ss"
                                            scope="page"/>
                            </td>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkOperRenewTime"/>
                            </td>
                            <td class="column-content">
                                <bean:write name="baselink" property="linkOperRenewTime" format="yyyy-MM-dd HH:mm:ss"
                                            scope="page"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkAffectTimeLength"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkAffectTimeLength" scope="page"/></pre>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                故障归因
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkBackCase}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                回复单位
                            </td>
                            <td class="column-content" colspan="3">
                                <eoms:id2nameDB id="${baselink.recoveryUnit}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <%} %>

                        <%
                            //延期申请
                            if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 5) {
                        %>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkTransmitContent"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkTransmitContent" scope="page"/></pre>
                            </td>
                        </tr>

                        <%} %>


                        <%} %>


                        <%if (baselink.getActiveTemplateId() != null && (baselink.getActiveTemplateId().equals("SecondExcuteHumTask") || baselink.getActiveTemplateId().equals("secondSubTask"))) { %>

                        <%
                            //移交T3
                            if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 2) {
                        %>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkIfGreatFault"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <eoms:id2nameDB id="${baselink.linkIfGreatFault}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="label"><bean:message bundle="commonfault"
                                                            key="commonfault.linkFaultDealInfo"/></td>
                            <td colspan="3">
                                <pre><bean:write name="baselink" property="linkFaultDealInfo" scope="page"/></pre>
                            </td>
                        </tr>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkTransmitReason"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkTransmitReason" scope="page"/></pre>
                            </td>
                        </tr>

                        <%} %>
                        <%
                            //处理完成
                            if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 46) {
                        %>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkFaultDealResult"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkFaultDealResult}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkIfGreatFault"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkIfGreatFault}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                归因一级
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSort}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                归因二级
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSubsection}"
                                                beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                归因三级
                            </td>
                            <td colspan="3">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSubsectionTwo}"
                                                beanId="ItawSystemDictTypeDao"/>
                            </td>

                        </tr>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkDealStep"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkDealStep" scope="page"/></pre>
                            </td>
                        </tr>
                        <tr>
                            <td class="label">
                                <bean:message bundle="sheet" key="tawSheetAccessForm.remark"/>
                            </td>
                            <td colspan="3">
                                <bean:write name="baselink" property="remark" scope="page"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkIfExcuteNetChange"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <eoms:id2nameDB id="${baselink.linkIfExcuteNetChange}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkIfFinallySolveProject"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <eoms:id2nameDB id="${baselink.linkIfFinallySolveProject}"
                                                beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkIfAddCaseDataBase"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkIfAddCaseDataBase}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                设备厂商
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkUntreadIdea}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkFaultAvoidTime"/>
                            </td>
                            <td class="column-content">
                                <bean:write name="baselink" property="linkFaultAvoidTime" format="yyyy-MM-dd HH:mm:ss"
                                            scope="page"/>
                            </td>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkOperRenewTime"/>
                            </td>
                            <td class="column-content">
                                <bean:write name="baselink" property="linkOperRenewTime" format="yyyy-MM-dd HH:mm:ss"
                                            scope="page"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkAffectTimeLength"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkAffectTimeLength" scope="page"/></pre>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                是否是手机操作
                            </td>
                            <td class="column-content">
                                <%if (baselink.getLinkIfMobile() != null && baselink.getLinkIfMobile().equals("mobileDeal")) {%>
                                是
                                <%} else {%>
                                否
                                <%}%>
                            </td>
                            <td class="column-title">
                                故障原因
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.faultReason}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                故障归因
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkBackCase}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                回复单位
                            </td>
                            <td class="column-content" colspan="3">
                                <eoms:id2nameDB id="${baselink.recoveryUnit}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                故障分类
                            </td>
                            <td class="column-content" colspan=3>
                                <eoms:id2nameDB id="${baselink.faultClassification}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="column-title">
                                预处理是否准确
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkIfConfirmPreSolve}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                预处理结果不准确原因
                            </td>
                            <td class="column-content" colspan="3">
                                <pre><bean:write name="baselink" property="confirmReason" scope="page"/></pre>
                            </td>
                        </tr>
                        <%} %>
                        <%
                            //分派回复
                            if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 11) {
                        %>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkIfGreatFault"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <eoms:id2nameDB id="${baselink.linkIfGreatFault}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="column-title">
                                归因一级
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSort}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                归因二级
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSubsection}"
                                                beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                归因三级
                            </td>
                            <td colspan="3">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSubsectionTwo}"
                                                beanId="ItawSystemDictTypeDao"/>
                            </td>

                        </tr>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkDealStep"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkDealStep" scope="page"/></pre>
                            </td>
                        </tr>


                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkFaultAvoidTime"/>
                            </td>
                            <td class="column-content">
                                <bean:write name="baselink" property="linkFaultAvoidTime" format="yyyy-MM-dd HH:mm:ss"
                                            scope="page"/>
                            </td>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkOperRenewTime"/>
                            </td>
                            <td class="column-content">
                                <bean:write name="baselink" property="linkOperRenewTime" format="yyyy-MM-dd HH:mm:ss"
                                            scope="page"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkAffectTimeLength"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkAffectTimeLength" scope="page"/></pre>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                故障归因
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkBackCase}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                回复单位
                            </td>
                            <td class="column-content" colspan="3">
                                <eoms:id2nameDB id="${baselink.recoveryUnit}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                故障分类
                            </td>
                            <td class="column-content" colspan=3>
                                <eoms:id2nameDB id="${baselink.faultClassification}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <%} %>


                        <%
                            //延期申请
                            if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 5) {
                        %>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkTransmitContent"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkTransmitContent" scope="page"/></pre>
                            </td>
                        </tr>

                        <%} %>

                        <%} %>

                        <% if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 101) {%>
                        <tr>
                            <td class="column-title">
                                智能质检结果
                            </td>
                            <td class="column-content" colspan="3">
                                <pre><bean:write name="baselink" property="linkReserved3" scope="page"/></pre>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                智能质检意见
                            </td>
                            <td class="column-content" colspan="3">
                                <pre><bean:write name="baselink" property="linkReserved4" scope="page"/></pre>
                            </td>
                        </tr>
                        <%} %>

                        <%
                            //T3处理环节
                            if (baselink.getActiveTemplateId() != null && (baselink.getActiveTemplateId().equals("ThirdExcuteHumTask")
                                    || baselink.getActiveTemplateId().equals("firstSubTask"))) {
                        %>
                        <%
                            //处理完成
                            if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 46) {
                        %>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkFaultDealResult"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkFaultDealResult}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkIfGreatFault"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkIfGreatFault}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                归因一级
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSort}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                归因二级
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSubsection}"
                                                beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                归因三级
                            </td>
                            <td colspan="3">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSubsectionTwo}"
                                                beanId="ItawSystemDictTypeDao"/>
                            </td>

                        </tr>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkDealStep"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkDealStep" scope="page"/></pre>
                            </td>
                        </tr>
                        <tr>
                            <td class="label">
                                <bean:message bundle="sheet" key="tawSheetAccessForm.remark"/>
                            </td>
                            <td colspan="3">
                                <bean:write name="baselink" property="remark" scope="page"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkIfExcuteNetChange"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <eoms:id2nameDB id="${baselink.linkIfExcuteNetChange}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkIfFinallySolveProject"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <eoms:id2nameDB id="${baselink.linkIfFinallySolveProject}"
                                                beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkIfAddCaseDataBase"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkIfAddCaseDataBase}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                设备厂商
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkUntreadIdea}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkFaultAvoidTime"/>
                            </td>
                            <td class="column-content">
                                <bean:write name="baselink" property="linkFaultAvoidTime" format="yyyy-MM-dd HH:mm:ss"
                                            scope="page"/>
                            </td>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkOperRenewTime"/>
                            </td>
                            <td class="column-content">
                                <bean:write name="baselink" property="linkOperRenewTime" format="yyyy-MM-dd HH:mm:ss"
                                            scope="page"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkAffectTimeLength"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkAffectTimeLength" scope="page"/></pre>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                是否是手机操作
                            </td>
                            <td class="column-content">
                                <%if (baselink.getLinkIfMobile() != null && baselink.getLinkIfMobile().equals("mobileDeal")) {%>
                                是
                                <%} else {%>
                                否
                                <%}%>
                            </td>
                            <td class="column-title">
                                故障原因
                            </td>
                            <eoms:id2nameDB id="${baselink.faultReason}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                故障归因
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkBackCase}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                回复单位
                            </td>
                            <td class="column-content" colspan="3">
                                <eoms:id2nameDB id="${baselink.recoveryUnit}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="column-title">
                                预处理是否准确
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkIfConfirmPreSolve}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                预处理结果不准确原因
                            </td>
                            <td class="column-content" colspan="3">
                                <pre><bean:write name="baselink" property="confirmReason" scope="page"/></pre>
                            </td>
                        </tr>
                        <%} %>
                        <%
                            //分派回复
                            if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 11) {
                        %>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkIfGreatFault"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <eoms:id2nameDB id="${baselink.linkIfGreatFault}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="column-title">
                                归因一级
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSort}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                归因二级
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSubsection}"
                                                beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                归因三级
                            </td>
                            <td colspan="3">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSubsectionTwo}"
                                                beanId="ItawSystemDictTypeDao"/>
                            </td>

                        </tr>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkDealStep"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkDealStep" scope="page"/></pre>
                            </td>
                        </tr>


                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkFaultAvoidTime"/>
                            </td>
                            <td class="column-content">
                                <bean:write name="baselink" property="linkFaultAvoidTime" format="yyyy-MM-dd HH:mm:ss"
                                            scope="page"/>
                            </td>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkOperRenewTime"/>
                            </td>
                            <td class="column-content">
                                <bean:write name="baselink" property="linkOperRenewTime" format="yyyy-MM-dd HH:mm:ss"
                                            scope="page"/>
                            </td>
                        </tr>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkAffectTimeLength"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkAffectTimeLength" scope="page"/></pre>
                            </td>
                        </tr>
                        <tr>
                            <td class="column-title">
                                故障归因
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkBackCase}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                回复单位
                            </td>
                            <td class="column-content" colspan="3">
                                <eoms:id2nameDB id="${baselink.recoveryUnit}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>
                        <%} %>

                        <%
                            //延期申请
                            if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 5) {
                        %>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkTransmitContent"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkTransmitContent" scope="page"/></pre>
                            </td>
                        </tr>

                        <%} %>


                        <%} %>

                        <%
                            //延期申请审批
                            if (baselink.getActiveTemplateId() != null && (baselink.getActiveTemplateId().equals("ExamineHumTask")
                                    || baselink.getActiveTemplateId().equals("fourSubTask"))) {
                        %>


                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkIfDeferResolve"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <eoms:id2nameDB id="${baselink.linkIfDeferResolve}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                        </tr>


                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkExamineContent"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkExamineContent" scope="page"/></pre>
                            </td>
                        </tr>


                        <%} %>

                        <%
                            //归档环节
                            if (baselink.getActiveTemplateId() != null && baselink.getActiveTemplateId().equals("HoldHumTask")) {
                        %>

                        <%if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 17) {%>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkUntreadReason"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkUntreadReason" scope="page"/></pre>
                            </td>
                        </tr>
                        <!--  <tr>
		  			<td class="column-title">
		  			  <bean:message bundle="commonfault" key="commonfault.linkUntreadIdea"/>
		  			</td>
		  			<td class="column-content" colspan=3>
		  			  <pre><bean:write name="baselink" property="linkUntreadIdea" scope="page"/></pre>
		            </td>
		  		 </tr>	-->

                        <%} %>

                        <%if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 18) {%>
                        <!--   <tr>
	  				  <td class="column-title">
	  				      <bean:message bundle="commonfault" key="commonfault.mainFaultResponseLevel"/>
	  				  </td>
	  				  <td class="column-content">
	  				      <eoms:id2nameDB id="${baselink.linkFaultResponseLevel}" beanId="ItawSystemDictTypeDao"/>
	  			      </td>
	  				  <td class="column-title">
	  				     <bean:message bundle="commonfault" key="commonfault.linkIfGreatFault"/>
	  				  </td>
	  				  <td class="column-content">
	  				  	 <eoms:id2nameDB id="${baselink.linkIfGreatFault}" beanId="ItawSystemDictTypeDao"/>
	                  </td>                  
  				</tr>
  				<tr>
  				 <td class="label"><bean:message bundle="commonfault" key="commonfault.mainIfAffectOperation"/></td>
		         <td class="content" colspan=3><eoms:id2nameDB id="${sheetMain.mainIfAffectOperation}" beanId="ItawSystemDictTypeDao"/></td>
  				 </tr>	-->
                        <%} %>

                        <%} %>
                        <%
                            //非流程动作（抄送、阶段回复、阶段通知、驳回）
                            if (baselink.getActiveTemplateId() != null && (baselink.getActiveTemplateId().equals("cc")
                                    || baselink.getOperateType().intValue() == 9 || baselink.getActiveTemplateId().equals("advice") ||
                                    baselink.getOperateType().intValue() == 4)) {
                        %>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.remark"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
                            </td>
                        </tr>

                        <%} %>
                        <%
                            //确认受理
                            if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 61) {
                        %>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.remark"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
                            </td>

                        </tr>

                        <%} %>
                        <%
                            //移交
                            if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 8) {
                        %>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.transmitReason"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="transferReason" scope="page"/></pre>
                            </td>

                        </tr>

                        <%} %>
                        <%
                            //处理回复
                            if (baselink.getOperateType() != null && (baselink.getOperateType().intValue() == 6 || baselink.getOperateType().intValue() == 7)) {
                        %>

                        <tr>
                            <td class="column-title">
                                <bean:message bundle="sheet" key="linkForm.remark"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
                            </td>

                        </tr>

                        <%} %>
                        <%
                            //分派
                            if (baselink.getOperateType() != null && baselink.getOperateType().intValue() == 10) {
                        %>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkIfGreatFault"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <eoms:id2nameDB id="${baselink.linkIfGreatFault}" beanId="ItawSystemDictTypeDao"/>
                            </td>

                        </tr>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkFaultDealInfo"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="linkFaultDealInfo" scope="page"/></pre>
                            </td>

                        </tr>
                        <tr>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkTransmitReason1"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <pre><bean:write name="baselink" property="remark" scope="page"/></pre>
                            </td>

                        </tr>

                        <%} %>

                        <%
                            //附件
                            if (baselink.getNodeAccessories() != null && !baselink.getNodeAccessories().equals("")) {
                        %>
                        <tr>
                            <td>
                                <bean:message bundle="commonfault" key="commonfault.nodeAccessories"/>
                            </td>
                            <td class="column-content" colspan=3>
                                <eoms:attachment name="baselink" property="nodeAccessories"
                                                 scope="page" idField="nodeAccessories" appCode="commonfault"
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

    //add by libo
    //故障原因细分的处理
    var linkFaultReasonSubsection = "";

    function check() {
        if (linkFaultReasonSubsection.indexOf($('${sheetPageName}linkFaultReasonSort').value) != -1) {
            $('${sheetPageName}linkFaultReasonSubsection').value = linkFaultReasonSubsection;
        }
    }

    // add by libo
    //复用chekbox选择时调用的方法，得到后台的数据并在页面显示
    function copy(id) {
        var ifCheck = document.getElementById(id);
        if (ifCheck.checked == true) {
            Ext.Ajax.request({
                method: "get",
                url: "commonfault.do?method=getJsonLink&id=" + id + "&beanName=CommonFault",
                success: function (x) {
                    var y = eoms.JSONDecode(x.responseText);
                    var o = y[0];
                    for (p in o) {
                        var a = eoms.$(p);
                        if (p == 'linkIfGreatFault' || p == 'linkFaultReasonSort' || p == 'linkFaultReasonSubsection' || p == 'linkDealdesc'
                            || p == 'faultdealTime' || p == 'faultTreatment' || p == 'faultdealdesc'
                            || p == 'remark' || p == 'selectStep' || p == 'linkFaultAvoidTime' || p == 'nodeAccessories'
                            || p == 'linkOperRenewTime' || p == 'linkAffectTimeLength' || p == 'nodeAccessories') {

                            if (a && (a.tagName == "TEXTAREA" || a.tagName == 'INPUT' || a.tagName == 'SELECT')) {
                                if (p == 'selectStep') {
                                    if (o[p]) {
                                        document.getElementById('isIfDeal').style.display = "";
                                    }
                                }
                                if (p == 'linkFaultReasonSort') {
                                    a.value = o[p];
                                    eoms.ComboBox.doCombo(document.getElementById("linkFaultReasonSort"), 'linkFaultReasonSubsection');
                                } else if (p == 'linkFaultReasonSubsection') {
                                    $('${sheetPageName}linkFaultReasonSubsection').value = o[p];
                                    linkFaultReasonSubsection = o[p];
                                } else {

                                    if (p == 'nodeAccessories') {
                                        //外层DIV
                                        var s = document.getElementById("checkbox");

                                        while (s.hasChildNodes()) //当div下还存在子节点时 循环继续
                                        {
                                            s.removeChild(s.firstChild);
                                        }
                                        //按钮
                                        var ss = document.getElementById("checkboxbutton");
                                        //tr 当选择复用时显碿
                                        var flag = document.getElementById("attachmentF");
                                        //复用附件是否显示
                                        if (o[p]) {
                                            flag.style.display = "";
                                        } else {
                                            flag.style.display = "none";
                                        }
                                        var len = y.length;
                                        // 附件隐藏坿
                                        var sss = document.getElementById("nodeAccessories2");
                                        var file = "";
                                        for (var i = 1; i < len; i++) {


                                            var div = document.createElement("div");
                                            var input = document.createElement("input");
                                            var a = document.createElement("a");
                                            var text = document.createTextNode(y[i].fileName);
                                            var br = document.createElement("br");
                                            input.type = "checkbox";
                                            input.name = "files";
                                            div.id = y[i].name + '_div';
                                            input.id = y[i].name;
                                            a.href = "${app}/accessories/tawCommonsAccessoriesConfigs.do?method=download&id=" + y[i].id + "";
                                            a.className = "checkbox";
                                            a.appendChild(text);
                                            div.appendChild(input);
                                            div.appendChild(a);
                                            div.appendChild(br);
                                            s.appendChild(div);

                                            ss.type = "button";

                                            file += "'" + y[i].name + "'" + ","
                                        }

                                        sss.value = file.substring(0, file.length - 1);
                                    } else {
                                        a.value = o[p];
                                        if (p == 'faultdealTime' || p == 'linkFaultAvoidTime' || p == 'linkOperRenewTime') {

                                            if (o[p] != null) {
                                                var year = o[p]["year"];
                                                var month = o[p]["month"] + 1;
                                                if (month < 10) {
                                                    month = "0" + month;
                                                }
                                                var date = o[p]["date"];
                                                if (date < 10) {
                                                    date = "0" + date;
                                                }
                                                var hours = o[p]["hours"];
                                                if (hours < 10) {
                                                    hours = "0" + hours;
                                                }
                                                var minutes = o[p]["minutes"];
                                                if (minutes < 10) {
                                                    minutes = "0" + minutes;
                                                }
                                                var seconds = o[p]["seconds"];
                                                if (seconds < 10) {
                                                    seconds = "0" + seconds;
                                                }

                                                a.value = 1900 + year + "-" + month + "-" + date + " " + hours + ":" + minutes + ":" + seconds;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            });
        }
    }
</script>