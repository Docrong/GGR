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
    var v;
    Ext.onReady(function () {
        displayTR(document.all.type);
        v = new eoms.form.Validation({form: "theform"});
    })

    function displayTR(sel) {

        if (sel.value == "time") {
            eoms.form.enableArea("td2");
            eoms.form.disableArea("td1", true);
            eoms.form.disableArea("td3", true);
        }
        if (sel.value == "month") {
            eoms.form.enableArea("td1");
            eoms.form.disableArea("td2", true);
            eoms.form.disableArea("td3", true);
        }
        if (sel.value == "season") {
            eoms.form.enableArea("td3");
            eoms.form.disableArea("td1", true);
            eoms.form.disableArea("td2", true);
        }
        return;
    }
</script>

<html:form action="/stat.do?method=performStatistic" styleId="theform">


    <table class="formTable">
        <caption>${eoms:a2u("输入条件")}</caption>
        <tr>
            <td>
                <input type="hidden" name="excelConfigURL" value="<%=excelConfigURL %>">
                <input type="hidden" name="findListForward" value="<%=findListForward %>">
                <input type="hidden" id="reportIndex" name="reportIndex" value="0">
            </td>
        </tr>
        <tr>
            <td noWrap class="label">
                <!-- <bean:message bundle="statistic" key="statistic.sendtime" /> -->
                    ${eoms:a2u("派单")}
                <select name="type" onchange="displayTR(this)">
                    <option value="season">${eoms:a2u("季度")}</option>
                    <option value="time">${eoms:a2u("时间")}</option>
                    <option value="month">${eoms:a2u("年月")}</option>
                </select>
            </td>

            <td id="td1" style="display:none;">
                    ${eoms:a2u("按月统计")}
                <select name="beginyear">
                    <%
                        for (int i = 2008; i <= year + 1; i++) {
                            String select = "";
                            if (year == i) {
                                select = "Selected";
                            }
                    %>
                    <option value="<%=i%>" <%=select %>><%= i%>
                    </option>
                    <%}%>
                </select>
                    ${eoms:a2u("年")}

                <select name="beginmonth">
                    <%
                        for (int i = 1; i <= 12; i++) {

                            String value = String.valueOf(i);
                            if (i < 10) {
                                value = "0" + String.valueOf(i);
                            }

                            String select = "";
                            if (mondth == i) {
                                select = "Selected";
                            }
                    %>
                    <option value="<%=value%>" <%=select %>><%= value%>
                    </option>
                    <%}%>
                </select>
                    ${eoms:a2u("月")}
            </td>

            <td id="td2" style="display:none;">
                <bean:message bundle="statistic" key="statistic.begin"/>
                <input type="text" name="beginTime" id="beginTime"
                       alt="allowBlank:false,vtype:'lessThen',link:'endTime',vtext:'${eoms:a2u("开始时间不能为空或晚于结束时间")}'"
                       readonly="readonly" onclick="popUpCalendar(this, this,null,null,null,true,-1);" class="text"/>
                <bean:message bundle="statistic" key="statistic.end"/>
                <input type="text" name="endTime" id="endTime"
                       alt="allowBlank:false,vtype:'moreThen',link:'beginTime',vtext:'${eoms:a2u("结束时间不能为空或早于开始时间")}'"
                       readonly="readonly" onclick="popUpCalendar(this, this,null,null,null,true,-1);" class="text"/>
            </td>

            <td width="80%" id="td3">
                    ${eoms:a2u("按季度统计")}
                <select name="beginyear">
                    <%
                        for (int i = 2001; i <= year + 1; i++) {
                            String select = "";
                            if (year == i) {
                                select = "Selected";
                            }
                    %>
                    <option value="<%=i%>" <%=select %>><%= i%>
                    </option>
                    <%}%>
                </select>
                    ${eoms:a2u("年")}
                <select name="seasonselect">
                    <option value="season_one">${eoms:a2u("第一季度")}</option>
                    <option value="season_two">${eoms:a2u("第二季度")}</option>
                    <option value="season_three">${eoms:a2u("第三季度")}</option>
                    <option value="season_four">${eoms:a2u("第四季度")}</option>
                </select>
            </td>
        </tr>

        <tr>
            <td>
                <input id="customDescribe" type="hidden" name="customDescribe">
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
