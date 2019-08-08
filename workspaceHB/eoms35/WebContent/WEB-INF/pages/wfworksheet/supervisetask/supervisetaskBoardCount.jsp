<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
         contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%
    String startDate = com.boco.eoms.base.util.StaticMethod.getLocalString(-1);
    String endDate = com.boco.eoms.base.util.StaticMethod.getCurrentDateTime();
    ;
%>
<script src="${app}/supervisetask/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
    window.onload = function () {
//console.log('${taskList }');

        var th = document.getElementsByTagName("th");
        for (var i = 0; i < th.length; i++) {
            th[i].style = "text-align:center;";
        }

        publicReplaceTh();
    }
    var jq = jQuery.noConflict();

    function publicReplaceTh(tablename) {//表头合并，用于实现：2行表头、多行表头
        if (typeof (tablename) == 'undefined') {
            tablename = "taskList";
        }
        console.log(tablename);
        var trs = jq("#" + tablename).find("tr");
        var tr = trs[0];
        var title = jq("#titleId").find("tr");
        console.log(tr);
        console.log(title);
        for (var i = 0; i < title.length; i++) {
            console.log(title[i]);
            jq(tr).before(title[i]);
        }
        //jq(tr).remove();
    }
</script>

<table class="listTable" cellpadding="0" cellspacing="0" id="titleId" style="display: none;">
    <thead>
    <tr>


        <th colspan="1"></th>
        <th colspan="1"></th>
        <th colspan="1"></th>
        <th colspan="1"></th>

        <th colspan="3">一级督办</th>
        <th colspan="3">二级督办</th>
        <th colspan="3">三级督办</th>
        <th colspan="3">四级督办</th>


    </thead>
</table>


<display:table name="taskList" cellspacing="0" cellpadding="0" style="text-align:center;"
               id="taskList" pagesize="${pageSize}" class="listTable" export="false"
               size="${total}" partialList="true" requestURI="supervisetask.do">

    <display:column title="序号" style="text-align:center;">
        ${taskList_rowNum }
    </display:column>
    <display:column title="生产任务下发方式" style="text-align:center;">
        <c:if test="${taskList.workflowType=='commonfault'}">故障工单</c:if>
        <c:if test="${taskList.workflowType=='listedregulation'}">摘挂牌工单</c:if>
    </display:column>
    <display:column title="生产任务下发时间" style="text-align:center;">
        ${taskList.sendtime }
    </display:column>
    <display:column title="工单号" style="text-align:center;">
        ${taskList.sheetid }
    </display:column>
    <display:column title="督办任务责任人" style="text-align:center;">
        ${taskList.username1 }
    </display:column>
    <display:column title="联系方式" style="text-align:center;">
        ${taskList.mobile1 }
    </display:column>
    <display:column title="督办任务是否按时完成" style="text-align:center;">
        ${taskList.status1 }
    </display:column>
    <display:column title="督办任务责任人" style="text-align:center;">
        ${taskList.username2 }
    </display:column>
    <display:column title="联系方式" style="text-align:center;">
        ${taskList.mobile2 }
    </display:column>
    <display:column title="督办任务是否按时完成" style="text-align:center;">
        ${taskList.status2 }
    </display:column>
    <display:column title="督办任务责任人" style="text-align:center;">
        ${taskList.username3 }
    </display:column>
    <display:column title="联系方式" style="text-align:center;">
        ${taskList.mobile3 }
    </display:column>
    <display:column title="督办任务是否按时完成" style="text-align:center;">
        ${taskList.status3 }
    </display:column>
    <display:column title="督办任务责任人" style="text-align:center;">
        ${taskList.username4 }
    </display:column>
    <display:column title="联系方式" style="text-align:center;">
        ${taskList.mobile4 }
    </display:column>
    <display:column title="督办任务是否按时完成" style="text-align:center;">
        ${taskList.status4 }
    </display:column>

</display:table>


<%@ include file="/common/footer_eoms.jsp" %>
