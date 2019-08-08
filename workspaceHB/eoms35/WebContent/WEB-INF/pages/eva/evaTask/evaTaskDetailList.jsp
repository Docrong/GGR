<%@ page language="java" pageEncoding="UTF-8" %>
<jsp:directive.page import="com.boco.eoms.eva.util.EvaConstants"/>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>

<script type="text/javascript">
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'evaKpiInstanceForm'});
    })
</script>

<html:form action="/evaTasks.do?method=saveTaskDetail" styleId="evaKpiInstanceForm" method="post">
    <table class="listTable" id="list-table">
        <caption>
            <div class="header center">
                任务(${requestScope.taskName})-合作伙伴(${requestScope.partner})-周期(${requestScope.timeType})-时间(${requestScope.time})
                考核执行列表
            </div>
        </caption>
        <thead>
        <tr>
            <td colspan="${requestScope.maxLevel}">
                考核指标
            </td>
            <td>
                算法描述
            </td>
            <td>
                实际得分
            </td>
            <td>
                增扣分原因
            </td>
            <td>
                备注
            </td>
        </tr>
        </thead>
        <tbody>
        <input type="hidden" id="taskId" name="taskId" value="${requestScope.taskId}"/>
        <input type="hidden" id="partner" name="partner" value="${requestScope.partner}"/>
        <input type="hidden" name="timeType" value="${requestScope.timeType}"/>
        <input type="hidden" name="time" value="${requestScope.time}"/>

        <logic:iterate id="rowList" name="tableList" type="java.util.List" indexId="index">
            <tr>
                <logic:iterate id="taskDetail" name="rowList">
                    <bean:define id="leaf" name="taskDetail" property="leaf"/>
                    <td rowspan="${taskDetail.rowspan}" colspan="${taskDetail.colspan}" class="label"
                        style="vertical-align:middle;text-align:left">
                        <eoms:id2nameDB id="${taskDetail.kpiId}" beanId="evaKpiDao"/>(${taskDetail.weight}分)
                    </td>
                    <%if (EvaConstants.NODE_LEAF.equals(leaf)) { %>
                    <bean:define id="isPublish" name="taskDetail" property="isPublish"/>
                    <td>
                            ${taskDetail.algorithm}
                    </td>
                    <%if (EvaConstants.TASK_PUBLISHED.equals(isPublish)) {%>
                    <td>
                        <input type="text" class="text" id="${taskDetail.nodeId}_sc"
                               name="${taskDetail.nodeId}_sc" value="${taskDetail.realScore}" readonly=""
                               alt="allowBlank:false,vtext:'请输入分数',maxLength:32"/>
                    </td>
                    <td>
                        <input type="text" class="text" id="${taskDetail.nodeId}_rs"
                               name="${taskDetail.nodeId}_rs" value="${taskDetail.reduceReason}" readonly=""
                               alt="maxLength:255"/>
                    </td>
                    <td>
                        <input type="text" class="text" id="${taskDetail.nodeId}_rm"
                               name="${taskDetail.nodeId}_rm" value="${taskDetail.remark}" readonly=""
                               alt="maxLength:255"/>
                    </td>
                    <%} else {%>
                    <td>
                        <input type="text" class="text" id="${taskDetail.nodeId}_sc"
                               name="${taskDetail.nodeId}_sc" value="${taskDetail.realScore}"
                               alt="allowBlank:false,vtext:'请输入分数',maxLength:32"/>
                    </td>
                    <td>
                        <input type="text" class="text" id="${taskDetail.nodeId}_rs"
                               name="${taskDetail.nodeId}_rs" value="${taskDetail.reduceReason}"
                               alt="maxLength:255"/>
                    </td>
                    <td>
                        <input type="text" class="text" id="${taskDetail.nodeId}_rm"
                               name="${taskDetail.nodeId}_rm" value="${taskDetail.remark}"
                               alt="maxLength:255"/>
                    </td>
                    <%
                            }
                        }
                    %>
                </logic:iterate>
            </tr>
        </logic:iterate>
        </tbody>
    </table>
    <table>
        <tr>
            <td>
                <input type="submit" class="btn" value="保存草稿" onclick="save()" style="${requestScope.isPublishButton}"/>
                &nbsp;
                <input type="submit" class="btn" value="发布" onclick="commit()" style="${requestScope.isPublishButton}"/>
                &nbsp;
                <input type="submit" class="btn" value="返回" onclick="${requestScope.queryType}Back()"/>
            </td>
        </tr>
    </table>
</html:form>
<%@ include file="/common/footer_eoms.jsp" %>
<script type="text/javascript">
    function save() {
        document.forms[0].action = "evaTasks.do?method=saveTaskDetail";
    };

    function commit() {
        document.forms[0].action = "evaTasks.do?method=commitTaskDetail";
    };

    function runBack() {
        v.passing = "true";
        document.forms[0].action = "evaTasks.do?method=taskView";
    };

    function queryBack() {
        v.passing = "true";
        document.forms[0].action = "evaTasks.do?method=queryInit";
    };
</script>