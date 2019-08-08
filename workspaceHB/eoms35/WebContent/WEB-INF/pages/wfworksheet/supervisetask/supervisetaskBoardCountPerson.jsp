<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
         contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%
    String startDate = com.boco.eoms.base.util.StaticMethod
            .getLocalString(-1);
    String endDate = com.boco.eoms.base.util.StaticMethod
            .getCurrentDateTime();
    ;
%>

<script type="text/javascript">
    window.onload = function () {
        var th = document.getElementsByTagName("th");
        for (var i = 0; i < th.length; i++) {
            th[i].style = "text-align:center;";
        }
    };
</script>
<display:table name="taskList" cellspacing="0" cellpadding="0"
               style="text-align:center;" id="taskList" pagesize="${pageSize}"
               class="listTable" export="false" size="${total}" partialList="true"
               requestURI="supervisetask.do">

    <display:column title="序号" style="text-align:center;">
        ${taskList_rowNum }
    </display:column>
    <display:column title="统计开始时间" style="text-align:center;">
        ${taskList_rowNum }
    </display:column>
    <display:column title="统计结束时间" style="text-align:center;">
        ${taskList_rowNum }
    </display:column>
    <display:column title="地市" style="text-align:center;">
        ${taskList_rowNum }
    </display:column>
    <display:column title="区县" style="text-align:center;">
        ${taskList_rowNum }
    </display:column>
    <display:column title="专业" style="text-align:center;">
        ${taskList_rowNum }
    </display:column>
    <display:column title="督办任务执行人" style="text-align:center;">
        ${taskList_rowNum }
    </display:column>
    <display:column title="联系方式" style="text-align:center;">
        ${taskList_rowNum }
    </display:column>
    <display:column title="督办任务完成及时率" style="text-align:center;">
        ${taskList_rowNum }
    </display:column>

</display:table>


<%@ include file="/common/footer_eoms.jsp" %>
