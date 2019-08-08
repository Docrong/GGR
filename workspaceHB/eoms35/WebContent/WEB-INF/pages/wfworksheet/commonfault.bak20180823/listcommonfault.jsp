<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<% String source = request.getParameter("6578706f7274");
    if (source == null) {
%>
<jsp:include page="/WEB-INF/pages/wfworksheet/${module}/batchJs.jsp"/>
<c:if test="${findForward != null && findForward == 'listall' && ifNetOpt =='3'}">
    <jsp:include page="/WEB-INF/pages/wfworksheet/commonfault/listsendUndoJS.jsp"/>
</c:if>
<%} %>


<bean:define id="url" value="commonfault.do"/>

<display:table name="taskList" cellspacing="0" cellpadding="0"
               id="taskList" pagesize="${pageSize}" class="listTable taskList"
               export="true" requestURI="commonfault.do"
               sort="external" size="total" partialList="true"
               decorator="com.boco.eoms.sheet.commonfault.webapp.action.ProcessListCommonFaultDisplaytagDecoratorHelper">
    <% if (source == null) { %>
    <display:caption media="html">
        <span class="map alert colorrow">将要超时的工单</span>
        <span class="map serious">已经超时的工单</span>
        未调用代维流程
    </display:caption>
    <display:column sortable="true" property="id" media="html"
                    headerClass="sortable" title=""/>
    <display:column sortable="true" property="processId" headerClass="sortable" title="" class="icon" media="html"/>
    <%} %>
    <display:column property="sheetId" sortable="true" sortName="task.sheetId"
                    headerClass="sortable" title="工单流水号"/>
    <display:column property="title" sortable="true" sortName="main.title"
                    headerClass="sortable" title="工单主题"/>
    <display:column property="mainNetName" sortable="true" sortName="main.mainNetName"
                    headerClass="sortable" title="网元名称"/>
    <display:column sortable="true" sortName="main.mainNetSortOne"
                    headerClass="sortable" title="网络一级分类">
        <eoms:id2nameDB id="${taskList.mainNetSortOne}" beanId="ItawSystemDictTypeDao"/>
    </display:column>
    <display:column sortable="true" sortName="main.mainNetSortTwo"
                    headerClass="sortable" title="网络二级分类">
        <eoms:id2nameDB id="${taskList.mainNetSortTwo}" beanId="ItawSystemDictTypeDao"/>
    </display:column>
    <display:column sortable="true" sortName="main.mainNetSortThree"
                    headerClass="sortable" title="网络三级分类">
        <eoms:id2nameDB id="${taskList.mainNetSortThree}" beanId="ItawSystemDictTypeDao"/>
    </display:column>
    <display:column sortable="true" sortName="main.mainFaultResponseLevel"
                    headerClass="sortable" title="故障处理响应级别">
        <eoms:id2nameDB id="${taskList.mainFaultResponseLevel}" beanId="ItawSystemDictTypeDao"/>
    </display:column>

    <display:column property="sendTime" sortable="true" sortName="task.sendTime"
                    headerClass="sortable" title="派单时间"
                    format="{0,date,yyyy-MM-dd HH:mm:ss}"/>
    <display:column property="completeTimeLimit" sortable="true" sortName="task.completeTimeLimit"
                    headerClass="sortable" title="完成时限"
                    format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

    <display:column sortable="true" sortName="task.taskDisplayName" headerClass="sortable" title="处理环节">
        ${taskList.taskDisplayName}
        <c:if test="${taskList.sendRejectFlag=='0'&&taskList.taskName=='FirstExcuteHumTask'}">(新建派发)</c:if>
        <c:if test="${taskList.sendRejectFlag=='4'&&taskList.taskName=='FirstExcuteHumTask'}">(T2驳回)</c:if>
        <c:if test="${taskList.sendRejectFlag=='46'&&taskList.taskName=='HoldHumTask'}">(归档信息不全)</c:if>
    </display:column>

    <display:column sortable="true" headerClass="sortable" title="任务状态" sortName="task.taskStatus">

        <c:if test="${taskList.ifWaitForSubTask=='true'}">
            <eoms:dict key="dict-sheet-common" dictId="taskStatus" itemId="-1" beanId="id2descriptionXML"/>
        </c:if>
        <c:if test="${empty taskList.ifWaitForSubTask||taskList.ifWaitForSubTask=='false'}">
            <eoms:dict key="dict-sheet-common" dictId="taskStatus" itemId="${taskList.taskStatus}"
                       beanId="id2descriptionXML"/>
        </c:if>
    </display:column>
    <display:column sortable="true" headerClass="sortable" title="任务所有者" sortName="task.taskOwner">
        <eoms:id2nameDB id="${taskList.taskOwner}" beanId="tawSystemUserDao"/>
    </display:column>
    <display:column sortable="true" sortName="main.perAllocatedUser"
                    headerClass="sortable" title="预分配人员">
        <eoms:id2nameDB id="${taskList.perAllocatedUser}" beanId="tawSystemUserDao"/>
    </display:column>
    <display:column property="mainAlarmSolveDate" sortable="true" sortName="main.mainAlarmSolveDate"
                    headerClass="sortable" title="告警清除时间"
                    format="{0,date,yyyy-MM-dd HH:mm:ss}"/>
    <display:column sortable="true" headerClass="sortable" title="告警核查状态" sortName="task.checkStatus">
        <eoms:dict key="dict-sheet-common" dictId="checkStatus" itemId="${taskList.mainCheckStatus}"
                   beanId="id2descriptionXML"/>
    </display:column>
    <display:column sortable="true" headerClass="sortable" title="T1一键回复">
        <c:if test="${not empty taskList.mainAlarmSolveDate && ''!=taskList.mainAlarmSolveDate && 'FirstExcuteHumTask'==taskList.taskName}">
            <input type="button" class="submit" id="onekey" name="onekey" value="T1一键回复"
                   onclick="onekeyreply(this,'${taskList.sheetKey}')"/>
        </c:if>
    </display:column>

    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>
</display:table>

<display:table name="taskListAgent" cellspacing="0" cellpadding="0"
               id="taskListAgent" pagesize="${pageSize}" class="listTable taskListAgent"
               export="true" requestURI="commonfault.do"
               sort="external" size="totalAgent" partialList="true"
               decorator="com.boco.eoms.sheet.commonfault.webapp.action.ProcessListCommonFaultDisplaytagDecoratorHelper">
    <% if (source == null) { %>
    <display:caption media="html">
        <span class="map alert colorrow">将要超时的工单</span>
        <span class="map serious">已经超时的工单</span>
        已调用代维流程
    </display:caption>
    <display:column sortable="true" property="id" media="html"
                    headerClass="sortable" title=""/>
    <display:column sortable="true" property="processId" headerClass="sortable" title="" class="icon" media="html"/>
    <%} %>
    <display:column property="sheetId" sortable="true" sortName="task.sheetId"
                    headerClass="sortable" title="工单流水号"/>
    <display:column property="title" sortable="true" sortName="main.title"
                    headerClass="sortable" title="工单主题"/>
    <display:column property="mainNetName" sortable="true" sortName="main.mainNetName"
                    headerClass="sortable" title="网元名称"/>
    <display:column sortable="true" sortName="main.mainNetSortOne"
                    headerClass="sortable" title="网络一级分类">
        <eoms:id2nameDB id="${taskListAgent.mainNetSortOne}" beanId="ItawSystemDictTypeDao"/>
    </display:column>
    <display:column sortable="true" sortName="main.mainNetSortTwo"
                    headerClass="sortable" title="网络二级分类">
        <eoms:id2nameDB id="${taskListAgent.mainNetSortTwo}" beanId="ItawSystemDictTypeDao"/>
    </display:column>
    <display:column sortable="true" sortName="main.mainNetSortThree"
                    headerClass="sortable" title="网络三级分类">
        <eoms:id2nameDB id="${taskListAgent.mainNetSortThree}" beanId="ItawSystemDictTypeDao"/>
    </display:column>
    <display:column sortable="true" sortName="main.mainFaultResponseLevel"
                    headerClass="sortable" title="故障处理响应级别">
        <eoms:id2nameDB id="${taskListAgent.mainFaultResponseLevel}" beanId="ItawSystemDictTypeDao"/>
    </display:column>

    <display:column property="sendTime" sortable="true" sortName="task.sendTime"
                    headerClass="sortable" title="派单时间"
                    format="{0,date,yyyy-MM-dd HH:mm:ss}"/>
    <display:column property="completeTimeLimit" sortable="true" sortName="task.completeTimeLimit"
                    headerClass="sortable" title="完成时限"
                    format="{0,date,yyyy-MM-dd HH:mm:ss}"/>

    <display:column property="taskDisplayName" sortable="true" sortName="task.taskDisplayName"
                    headerClass="sortable" title="处理环节"/>

    <display:column sortable="true" headerClass="sortable" title="任务状态" sortName="task.taskStatus">

        <c:if test="${taskListAgent.ifWaitForSubTask=='true'}">
            <eoms:dict key="dict-sheet-common" dictId="taskStatus" itemId="-1" beanId="id2descriptionXML"/>
        </c:if>
        <c:if test="${empty taskListAgent.ifWaitForSubTask||taskListAgent.ifWaitForSubTask=='false'}">
            <eoms:dict key="dict-sheet-common" dictId="taskStatus" itemId="${taskListAgent.taskStatus}"
                       beanId="id2descriptionXML"/>
        </c:if>
    </display:column>
    <display:column sortable="true" headerClass="sortable" title="代维流程是否回复">
        <c:if test="${taskListAgent.revert=='1'}">
            是
        </c:if>
        <c:if test="${taskListAgent.revert=='0'||empty taskListAgent.revert}">
            否
        </c:if>
    </display:column>

    <display:column sortable="true" headerClass="sortable" title="任务所有者" sortName="task.taskOwner">
        <eoms:id2nameDB id="${taskListAgent.taskOwner}" beanId="tawSystemUserDao"/>
    </display:column>
    <display:column sortable="true" sortName="main.perAllocatedUser"
                    headerClass="sortable" title="预分配人员">
        <eoms:id2nameDB id="${taskListAgent.perAllocatedUser}" beanId="tawSystemUserDao"/>
    </display:column>
    <display:column property="mainAlarmSolveDate" sortable="true" sortName="main.mainAlarmSolveDate"
                    headerClass="sortable" title="告警清除时间"
                    format="{0,date,yyyy-MM-dd HH:mm:ss}"/>
    <display:column sortable="true" headerClass="sortable" title="告警核查状态" sortName="task.checkStatus">
        <eoms:dict key="dict-sheet-common" dictId="checkStatus" itemId="${taskListAgent.mainCheckStatus}"
                   beanId="id2descriptionXML"/>
    </display:column>
    <display:column sortable="true" headerClass="sortable" title="T1一键回复">
        <c:if test="${not empty taskList.mainAlarmSolveDate && ''!=taskList.mainAlarmSolveDate}">
            <input type="button" class="submit" id="onekey" name="onekey" value="T1一键回复"
                   onclick="onekeyreply(this,'${taskList.sheetKey}')"/>
        </c:if>
    </display:column>

    <display:setProperty name="export.pdf" value="false"/>
    <display:setProperty name="export.xml" value="false"/>
    <display:setProperty name="export.csv" value="false"/>
</display:table>
<!-- add by weichao 20150513 批量接单按钮 begin-->
<c:if test="${showBatchAcceptBtn=='true'}">
    <input type="button" class="btn"
           onclick="javascript:window.location='<html:rewrite
                   page='/commonfault.do?method=showBatchAcceptSheetList&psize=20'/>'" value="批量接单"/>&nbsp;
    <input type="button" class="btn"
           onclick="javascript:window.location='<html:rewrite
                   page='/commonfault.do?method=showCombineSheetList&psize=20&tt=1'/>'" value="T1环节合并工单"/>&nbsp;
    <input type="button" class="btn"
           onclick="javascript:window.location='<html:rewrite
                   page='/commonfault.do?method=showCombineSheetList&psize=20&tt=2'/>'" value="T2环节合并工单"/>&nbsp;
</c:if>
<!-- add by weichao 20150513 批量接单按钮 end-->
<script type="text/javascript">
    function onekeyreply(onekey, sheetKey) {
        Ext.Ajax.request({
            url: "${app}/sheet/commonfault/commonfault.do?method=getReply&sheetKey=" + sheetKey,
            method: 'POST',
            success: function (res) {
                var data = eoms.JSONDecode(res.responseText);
                var reply = data[0].reply;
                if ('false' == reply) {
                    alert('工单不在T1环节');
                }
            }
        });
        onekey.disabled = true;
    }
</script>
<%@ include file="/common/footer_eoms.jsp" %>