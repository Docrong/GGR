<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@page import="com.boco.eoms.commons.statistic.base.config.excel.* ,java.util.*" %>
<%
    Excel excelConfig = (Excel) request.getAttribute("excelConfig");
    if (excelConfig == null) throw new Exception("读取配置统计文件失败!");

    String excelConfigURL = String.valueOf(request.getAttribute("excelConfigURL"));
    String findListForward = String.valueOf(request.getAttribute("findListForward"));
    Calendar cld = Calendar.getInstance();
    int year = cld.get(Calendar.YEAR);
    int mondth = cld.get(Calendar.MONTH) + 1;
%>
<script type="text/javascript">


    function validateCheck(data) {
        alert(data.getElementById("beginTime").value);
        alert(data.getElementById("endTime").value);
    }

</script>

<html:form action="/stat.do?method=performStatistic" styleId="theform">
    <input type="hidden" name="reportIndex" value="0">

    <table class="formTable">
        <caption>${eoms:a2u("输入条件")}</caption>
        <tr>
            <td>
                <input type="hidden" name="excelConfigURL" value="<%=excelConfigURL %>">
                <input type="hidden" name="findListForward" value="<%=findListForward %>">
            </td>
        </tr>


        <tr>

            <td id="td2">
                <bean:message bundle="statistic" key="statistic.begin"/>
                <input type="text" name="beginTime" id="beginTime"
                       alt="allowBlank:false,vtype:'lessThen',link:'endTime',vtext:'${eoms:a2u("开始时间不能为空或晚于结束时间")}'"
                       readonly="readonly" onclick="popUpCalendar(this, this);" class="text"/>
                <bean:message bundle="statistic" key="statistic.end"/>
                <input type="text" name="endTime" id="endTime"
                       alt="allowBlank:false,vtype:'moreThen',link:'beginTime',vtext:'${eoms:a2u("结束时间不能为空或早于开始时间")}'"
                       readonly="readonly" onclick="popUpCalendar(this, this);" class="text"/>
            </td>
        </tr>


    </table>

    <br/>
    <!-- buttons -->

    <html:submit styleClass="btn" property="method.save" styleId="method.save">
        <bean:message bundle="statistic" key="button.done"/>
    </html:submit>

</html:form>

<%@ include file="/common/footer_eoms.jsp" %>
