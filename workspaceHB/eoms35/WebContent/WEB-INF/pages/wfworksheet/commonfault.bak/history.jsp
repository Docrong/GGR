<%@ include file="/common/taglibs.jsp" %>
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
                                <bean:message bundle="commonfault" key="commonfault.linkFaultReasonSort"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSort}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkFaultReasonSubsection"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSubsection}"
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
                            <td class="column-content" colspan=3>
                                <eoms:id2nameDB id="${baselink.linkIfAddCaseDataBase}" beanId="ItawSystemDictTypeDao"/>
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
                                <bean:message bundle="commonfault" key="commonfault.linkFaultReasonSort"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSort}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkFaultReasonSubsection"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSubsection}"
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


                        <%if (baselink.getActiveTemplateId() != null && (baselink.getActiveTemplateId().equals("SecondExcuteHumTask") || baselink.getActiveTemplateId().equals("secondSubTask"))) {%>

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
                                <bean:message bundle="commonfault" key="commonfault.linkFaultReasonSort"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSort}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkFaultReasonSubsection"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSubsection}"
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
                            <td class="column-content" colspan=3>
                                <eoms:id2nameDB id="${baselink.linkIfAddCaseDataBase}" beanId="ItawSystemDictTypeDao"/>
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
                                <bean:message bundle="commonfault" key="commonfault.linkFaultReasonSort"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSort}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkFaultReasonSubsection"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSubsection}"
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
                                <bean:message bundle="commonfault" key="commonfault.linkFaultReasonSort"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSort}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkFaultReasonSubsection"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSubsection}"
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
                            <td class="column-content" colspan=3>
                                <eoms:id2nameDB id="${baselink.linkIfAddCaseDataBase}" beanId="ItawSystemDictTypeDao"/>
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
                                <bean:message bundle="commonfault" key="commonfault.linkFaultReasonSort"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSort}" beanId="ItawSystemDictTypeDao"/>
                            </td>
                            <td class="column-title">
                                <bean:message bundle="commonfault" key="commonfault.linkFaultReasonSubsection"/>
                            </td>
                            <td class="column-content">
                                <eoms:id2nameDB id="${baselink.linkFaultReasonSubsection}"
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

    function copy(id) {
        var ifCheck = document.getElementById(id);
        if (ifCheck.checked == true) {
            Ext.Ajax.request({
                method: "get",
                url: "commonfault.do?method=getJsonLink&id=" + id + "&beanName=CommonFault",
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