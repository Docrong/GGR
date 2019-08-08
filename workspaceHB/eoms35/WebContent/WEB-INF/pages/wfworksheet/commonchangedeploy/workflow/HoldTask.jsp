<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!-- 不是从历史页面进来 -->
<c:if test="${readOnly != 'true'}">
    <table class="formTable">
        <!--流程中的字段域 -->

        <c:if test="${operateType == '18'}">
            <input type="hidden" name="phaseId" id="phaseId" value="${toPhaseIdMap[operateType]}"/>

            <input type="hidden" name="status" value="1"/>
            <input type="hidden" name="hasNextTaskFlag" id="hasNextTaskFlag" value="true"/>
            <tr>
                <td class="label">
                    <bean:message bundle="sheet" key="mainForm.holdStatisfied"/>*
                </td>
                <td colspan="3">
                    <eoms:comboBox name="holdStatisfied" id="holdStatisfied" initDicId="10303"
                                   alt="width:'500px',allowBlank:false" styleClass="select"
                                   defaultValue="${sheetMain.holdStatisfied != 0 ? sheetMain.holdStatisfied : 1030301}"/>
                </td>
            </tr>
            <tr>
                <td class="label">
                    <!-- 是否示范案例 -->
                    <bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.mainIfDemonstrateCase"/>*
                </td>
                <td>
                    <eoms:comboBox name="mainIfDemonstrateCase" id="mainIfDemonstrateCase"

                                   defaultValue="${sheetLink.mainIfDemonstrateCase}" alt="allowBlank:false"
                                   initDicId="10301" onchange="executeDemonstrateCase(this.value);"/>
                </td>
                <td class="label">
                    <!-- 案例关键字 -->
                    <bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkCasesMainKey"/>*
                </td>
                <td class="content">
                    <input type="text" class="text" name="linkCasesMainKey" id="linkCasesMainKey"
                           alt="allowBlank:false,maxLength:32,vtext:'请填入 案例关键字 信息，最多输入 32 字符'"
                           value="${sheetLink.linkCasesMainKey}"/>
                </td>
            </tr>


            <tr>
                <td class="label"><bean:message bundle="sheet" key="mainForm.endResult"/>*</td>
                <td colspan="3">
                    <textarea class="textarea max" name="endResult" id="endResult"
                              alt="allowBlank:false,maxLength:4000,vtext:'请填入备注信息，最多输入4000字符'">${sheetMain.endResult}</textarea>
                </td>
            </tr>
            <tr>
                <td class="label"><bean:message bundle="commonchangedeploy"
                                                key="commonChangeDeployLink.linkCutAnalyse"/>*
                </td>
                <td colspan="3">
                    <textarea class="textarea max" name="linkCutAnalyse" id="linkCutAnalyse"
                              alt="allowBlank:false,maxLength:4000,vtext:'请填入 执行情况分析，最多输入4000字符'">${sheetLink.linkCutAnalyse}</textarea>
                </td>
            </tr>
        </c:if>

        <!--流程中的字段域 结束-->
    </table>


</c:if>

<!-- 历史页面进入 -->
<c:if test="${readOnly == 'true'}">
    <c:if test="${operateType == '18'}">

        <td class="label">
            <!-- 是否示范案例 -->
            <bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.mainIfDemonstrateCase"/>
        </td>
        <td class="content" colspan="3">
            <eoms:id2nameDB id="${sheetLink.mainIfDemonstrateCase}" beanId="ItawSystemDictTypeDao"/>
        </td>
        </tr>
        <tr>
            <td class="label">
                <!-- 案例关键字 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkCasesMainKey"/>
            </td>
            <td class="content" colspan="3">
                    ${sheetLink.linkCasesMainKey}
            </td>
        </tr>
        <tr>
            <td class="label">
                <!-- 执行情况分析 -->
                <bean:message bundle="commonchangedeploy" key="commonChangeDeployLink.linkCutAnalyse"/>
            </td>
            <td class="content" colspan="3">
                <pre>${sheetLink.linkCutAnalyse}</pre>

            </td>
        </tr>


    </c:if>
</c:if>