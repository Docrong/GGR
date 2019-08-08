<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!-- 不是从历史页面进来 -->
<c:if test="${readOnly != 'true'}">
    <table class="formTable">
        <!--流程中的字段域 -->

        <c:if test="${operateType == '93' || operateType == '11'}">
            <input type="hidden" name="phaseId" id="phaseId" value="${toPhaseIdMap[operateType]}"/>
            <input type="hidden" name="hasNextTaskFlag" id="hasNextTaskFlag" value="true"/>

            <tr>
                <td class="label">
                    <!-- 实施结果 -->
                    <bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkCutResult"/>*
                </td  class="content">
                <td>
                    <eoms:comboBox name="${sheetPageName}linkCutResult" id="${sheetPageName}linkCutResult"
                                   initDicId="1010903" alt="allowBlank:false" defaultValue="${sheetLink.linkCutResult}"
                                   styleClass="select-class" onchange="executeResult(this.value);"/>
                </td>
                <td class="label">
                    <!-- 失败原因 -->
                    <bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkFailedReason"/>*
                </td>
                <td class="content">
                    <eoms:comboBox name="${sheetPageName}linkFailedReason" id="${sheetPageName}linkFailedReason"
                                   initDicId="1010910" alt="allowBlank:false"
                                   defaultValue="${sheetLink.linkFailedReason}" styleClass="select-class"/>
                </td>
            </tr>
            <tr>
                <td class="label">
                    <!-- 是否完全按照方案实施 -->
                    <bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkIsPlan"/>*
                </td>
                <td colspan="3">
                    <eoms:comboBox name="linkIsPlan" id="linkIsPlan"

                                   defaultValue="${sheetLink.linkIsPlan}" alt="allowBlank:false" initDicId="10301"/>
                </td>
            </tr>
            <tr>
                <td class="label">
                    <!-- 实施情况说明 -->
                    <bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkCutComment"/>*
                </td>
                <td colspan="3">
				<textarea class="textarea max" name="linkCutComment"
                          id="linkCutComment"
                          alt="allowBlank:false,maxLength:255,vtext:'请填入 实施情况说明 信息，最多输入 255 字符'">${sheetLink.linkCutComment}</textarea>

                </td>
            </tr>

            <tr>
                <td class="label">
                    <!-- 影响业务情况说明 -->
                    <bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkEffectBusinessInstance"/>*
                </td>
                <td colspan="3">
				<textarea class="textarea max" name="linkEffectBusinessInstance"
                          id="linkEffectBusinessInstance"
                          alt="allowBlank:false,maxLength:255,vtext:'请填入 影响业务情况说明 信息，最多输入 255 字符'">${sheetLink.linkEffectBusinessInstance}</textarea>

                </td>
            </tr>

            <tr>
                <td class="label">
                    <!-- 告警情况记录 -->
                    <bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkAlertRecord"/>*
                </td>
                <td colspan="3">
				<textarea class="textarea max" name="linkAlertRecord"
                          id="linkAlertRecord"
                          alt="allowBlank:false,maxLength:255,vtext:'请填入 告警情况记录 信息，最多输入 255 字符'">${sheetLink.linkAlertRecord}</textarea>

                </td>
            </tr>
            <tr>
                <td class="label">
                    <!-- 统计报告异常说明 -->
                    <bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkUnnormalComment"/>*
                </td>
                <td colspan="3">
				<textarea class="textarea max" name="linkUnnormalComment"
                          id="linkUnnormalComment"
                          alt="allowBlank:false,maxLength:255,vtext:'请填入 统计报告异常说明 信息，最多输入 255 字符'">${sheetLink.linkUnnormalComment}</textarea>

                </td>
            </tr>
            <tr>
                <td class="label">
                    <!-- 测试报告 -->
                    <bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkTestReport"/>*
                </td>
                <td colspan="3">
                    <eoms:attachment name="sheetLink" property="linkTestReport" scope="request" idField="linkTestReport"
                                     appCode="commonchangedeploy"/>

                </td>
            </tr>
        </c:if>

        <!--流程中的字段域 结束-->
    </table>


    <c:if test="${operateType == '93'}">
        <!-- 派单树 -->
        <fieldset id="link4">
            <legend>
                <bean:message bundle="sheet" key="mainForm.toOperateRoleName"/>:
                <span id="roleName"><bean:message bundle="commonchangedeploy" key="role.changeproposer"/></span>
            </legend>
            <div ID="permit3">
                <eoms:chooser id="sendObject" type="role" roleId="405" flowId="${flowId}"
                              config="{returnJSON:true,showLeader:true,mainPanelTitle:'可选择的子角色'}"
                              category="[{id:'dealPerformer',text:'派发',allowBlank:false,vtext:'请选择派发人',limit:'none'},{id:'copyPerformer',text:'抄送',childType:'user,subrole,dept',limit:'none'}]"
                              data="${sheetLink.sendObject}"/>
            </div>
        </fieldset>
    </c:if>

</c:if>

<!-- 历史页面进入 -->
<c:if test="${readOnly == 'true'}">


    <c:if test="${operateType == '93' || operateType == '11'}">

        <tr>
            <td class="label">
                <!-- 实施结果 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkCutResult"/>
            </td>
            <td class="content">
                <eoms:id2nameDB id="${sheetLink.linkCutResult}" beanId="ItawSystemDictTypeDao"/>
            </td>
            <td class="label">
                <!-- 失败原因 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkFailedReason"/>
            </td>
            <td class="content">
                <eoms:id2nameDB id="${sheetLink.linkFailedReason}" beanId="ItawSystemDictTypeDao"/>
            </td>
        </tr>
        <tr>
            <td class="label">
                <!-- 是否完全按照方案实施 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkIsPlan"/>
            </td>
            <td class="content" colspan="3">
                <eoms:id2nameDB id="${sheetLink.linkIsPlan}" beanId="ItawSystemDictTypeDao"/>
            </td>
        </tr>
        <tr>
            <td class="label">
                <!-- 实施情况说明 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkCutComment"/>
            </td>
            <td class="content" colspan="3">
                <pre>${sheetLink.linkCutComment}</pre>

            </td>
        </tr>
        <tr>
            <td class="label">
                <!-- 影响业务情况说明 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkEffectBusinessInstance"/>
            </td>
            <td class="content" colspan="3">
                <pre>${sheetLink.linkEffectBusinessInstance}</pre>

            </td>
        </tr>
        <tr>
            <td class="label">
                <!-- 测试报告 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkTestReport"/>
            </td>
            <td class="content" colspan="3">
                <eoms:attachment name="sheetLink" property="linkTestReport" scope="request" idField="linkTestReport"
                                 appCode="commonchangedeploy" viewFlag="Y"/>

            </td>
        </tr>
        <tr>
            <td class="label">
                <!-- 告警情况记录 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkAlertRecord"/>
            </td>
            <td class="content" colspan="3">
                <pre>${sheetLink.linkAlertRecord}</pre>

            </td>
        </tr>
        <tr>
            <td class="label">
                <!-- 统计报告异常说明 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkUnnormalComment"/>
            </td>
            <td class="content" colspan="3">
                <pre>${sheetLink.linkUnnormalComment}</pre>

            </td>
        </tr>
    </c:if>

</c:if>