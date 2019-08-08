<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>

<html:form action="/widencomplaint.do?method=performListQuery" styleId="queryListForm">
    <!-- 公共隐藏域的值 -->
    <input type="hidden" name="findForward" value="${findForward}"/>
    <!-- 未处理动作 -->
    <input type="hidden" name="doneType" value="sendundo"/>
    <table width="100%">
        <tr>
            <td width="100px">
                <bean:message bundle="sheet" key="mainForm.title"/>:
            </td>
            <td width="170px">
                <input type="text" name="main.title" id="title" size="30" class="text"/>
                <input type="hidden" name="titleStringExpression" value="like"/>
            </td>
            <td>
                <bean:message bundle="sheet" key="querylist.completeLimit"/>:

            </td>
            <td>
                <input type="hidden" name="main.sheetCompleteLimit"/>
                <input type="hidden" id="sheetCompleteLimitStartDateExpression"
                       name="sheetCompleteLimitStartDateExpression" value=">="/>
                <input type="text" name="sheetCompleteLimitStartDate"
                       onclick="popUpCalendar(this, this, null, null, null, true, -1)" readonly="true" class="text"/>
                <input type="hidden" name="sheetCompleteLimitLogicExpression" value="and"/>
                to
                <input type="hidden" id="sheetCompleteLimitEndDateExpression" name="sheetCompleteLimitEndDateExpression"
                       value="<=">
                <input type="text" name="sheetCompleteLimitEndDate" id="sheetCompleteLimitEndDate"
                       onclick="popUpCalendar(this, this,null,null,null,true,-1)" alt="" readonly="true" class="text"/>

            </td>
        </tr>


        <tr>
            <td>
                工单流水号:
            </td>
            <td width="170px">
                <input type="text" name="main.sheetId" id="sheetId" size="30" class="text"/>
                <input type="hidden" name="sheetIdStringExpression" value="like"/>
            </td>
            <td>
                故障地点:
            </td>
            <td>
                <input type="text" name="main.faultSite" id="faultSite" size="30" class="text"/>
                <input type="hidden" name="faultSiteStringExpression" value="like"/>
            </td>
        </tr>
        <tr>

        <tr>
            <td>
                <bean:message bundle="widencomplaint" key="widenComplaintMain.startDealCity"/>:

            </td>
            <td>
                <input type="hidden" name="toDeptIdStringExpression" value="in"/>
                <input type="text" class="text" readonly="readonly" name="showArea" id="showArea"
                       beanId="tawSystemAreaDao"/>
                <input type="hidden" name="main.toDeptId" id="toAreaId"/>

            </td>
            <td>
                <bean:message bundle="sheet" key="query.status.stepId"/>:

            </td>
            <td>
                <input type="hidden" name="task.taskName"/>
                <select name="taskNameChoiceExpression" id="stepId" class="select">
                    <option value=""></option>
                    <logic:iterate name="stepIdList" id="stepId">
                        <option value="${stepId}"> ${phaseIdMap[stepId]}</option>
                    </logic:iterate>
                </select>
            </td>
        </tr>
        <tr>
            <td>
                投诉分类:
            </td>
            <td colspan="3">
                <input type="hidden" name="titleStringExpression" value="like"/>
                <input type="hidden" name="main.complaintType1"/>
                <eoms:comboBox name="complaintType1ChoiceExpression" id="complaintType1" sub="complaintType2"
                               initDicId="1010601"/>
                <input type="hidden" name="main.complaintType2"/>
                <eoms:comboBox name="complaintType2ChoiceExpression" id="complaintType2" sub="complaintType"/>
                <input type="hidden" name="main.complaintType"/>
                <eoms:comboBox name="complaintTypeChoiceExpression" id="complaintType" sub="complaintType4"/>
                <input type="hidden" name="main.complaintType4"/>
                <eoms:comboBox name="complaintType4ChoiceExpression" id="complaintType4" sub="complaintType5"/>
                <input type="hidden" name="main.complaintType5"/>
                <eoms:comboBox name="complaintType5ChoiceExpression" id="complaintType5" sub="complaintType6"/>
                <input type="hidden" name="main.complaintType6"/>
                <eoms:comboBox name="complaintType6ChoiceExpression" id="complaintType6" sub="complaintType7"/>
                <input type="hidden" name="main.complaintType7"/>
                <eoms:comboBox name="complaintType7ChoiceExpression" id="complaintType7"/>

            </td>
        </tr>
        <tr>
            <td>
                <input type="submit" value="查询" class="submit"/>
                <input type="reset" value="重置" class="button"/>
            </td>
        </tr>
    </table>
    <br/>
</html:form>
<script>
    Ext.onReady(function () {
        //地域
        var areatreeAction = '${app}/xtree.do?method=areaTree';
        deptetree = new xbox({
            btnId: 'showArea',
            treeDataUrl: areatreeAction, treeRootId: '-1', treeRootText: '地市', treeChkMode: '', treeChkType: 'area',
            showChkFldId: 'showArea', saveChkFldId: 'toAreaId'
        });
    });
</script>