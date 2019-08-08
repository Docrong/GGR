<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:directive.page import="com.boco.eoms.eva.util.EvaConstants"/>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<html:form action="/evaTasks.do?method=saveTaskDetail" styleId="evaReportMultiDeptForm" method="post">
    <table class="listTable" id="list-table">
        <caption>
            <div class="header center">
                任务(${requestScope.taskName})-合作伙伴(${requestScope.partner})-周期(${requestScope.timeType})-时间(${requestScope.time})
                考核报表
            </div>
        </caption>

        <c:if test="${not empty requestScope.isListEmpty}">
            <thead>
            <tr>
                <td align="left">
                    地市名称
                </td>
                <td align="left">
                    厂家名称
                </td>
                <td align="left">
                    总分(标准总分-${requestScope.totalWeight})
                </td>
                <logic:iterate id="kpiWeight" name="kpiWeights">
                    <td align="left">
                        <bean:write name="kpiWeight" property="key"/>(
                        标准分-
                        <bean:write name="kpiWeight" property="value"/>)
                    </td>
                </logic:iterate>
            </tr>
            </thead>
            <tbody>
            <logic:iterate id="rowList" name="tableList">
                <tr>
                    <td align="left">
                        <bean:write name="rowList" property="areaId"/>
                    </td>
                    <td align="left">
                        <bean:write name="rowList" property="deptId"/>
                    </td>
                    <td align="left">
                        <bean:write name="rowList" property="totalScore"/>
                    </td>
                    <logic:iterate id="kpiScoreList" name="rowList" property="kpiScoreList">
                        <td align="left">
                            <bean:write name="kpiScoreList"/>
                        </td>
                    </logic:iterate>
                </tr>
            </logic:iterate>
            </tbody>
        </c:if>
    </table>
    <c:if test="${ empty requestScope.isListEmpty}">
        列表记录为空。
    </c:if>
    <table>
        <tr>
            <td>
                <input type="submit" class="btn" value="返回" onclick="queryBack()"/>
            </td>
        </tr>
    </table>
</html:form>
<%@ include file="/common/footer_eoms.jsp" %>
<script type="text/javascript">
    function queryBack() {
        document.forms[0].action = "evaReportMultiDepts.do?method=queryInitMultiDept";
    };
</script>