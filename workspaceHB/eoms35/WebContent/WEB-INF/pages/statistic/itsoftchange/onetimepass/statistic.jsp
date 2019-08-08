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
    var userTreeAction = '${app}/xtree.do?method=dept';

    var v;
    Ext.onReady(function () {

        eoms.form.enableArea("selrevdept");
        eoms.form.disableArea("selnetsys", true);
        displayTR(document.all.type);
        v = new eoms.form.Validation({form: "theform"});


        //处理部门树
        revDeptTree = new xbox({
            btnId: 'deptTreeBtn',
            dlgId: 'dlg-dept1',
            treeDataUrl: userTreeAction,
            treeRootId: '-1',
            treeRootText: '${eoms:a2u("所属部门")}',
            treeChkMode: '',
            treeChkType: 'dept',
            showChkFldId: 'revDeptName',
            saveChkFldId: 'revDeptId'
        });


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

    function validateCheck(data) {
        alert(data.getElementById("beginTime").value);
        alert(data.getElementById("endTime").value);
    }

    function setDeptType(sel) {
        if (sel.value == "netsys") {//网管支撑

            eoms.$("reportIndex").value = 1;
            eoms.form.enableArea("selnetsys");
            eoms.form.disableArea("selrevdept", true);

        } else {//申请单位
            eoms.$("reportIndex").value = 0;
            eoms.form.enableArea("selrevdept");
            eoms.form.disableArea("selnetsys", true);
        }
    }

</script>

<html:form action="/stat.do?method=performStatistic" styleId="theform">


    <table class="formTable">
        <caption>${eoms:a2u("输入条件")}</caption>
        <tr>
            <td>
                <input type="hidden" name="excelConfigURL" value="<%=excelConfigURL %>">
                <input type="hidden" name="findListForward" value="<%=findListForward %>">

            </td>
        </tr>


        <tr>
            <td noWrap class="label">
                <!-- <bean:message bundle="statistic" key="statistic.sendtime" /> -->
                    ${eoms:a2u("派单")}
                <select name="type" onchange="displayTR(this)">
                    <option value="season">${eoms:a2u("季度")}</option>
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

        <!-- -->
        <tr id="trSelectdept">
            <td noWrap class="label"><!-- bean:message bundle="statistictask" key="statistic.dept" / -->
                <select name="selectDeptType" onchange="setDeptType(this)">
                    <option value="revdept">${eoms:a2u("申请单位")}</option>
                    <option value="netsys">${eoms:a2u("网管系统")}</option>
                </select>

            </td>
            <input type="hidden" id="revDeptId" name="revDeptId">


            <td id="selnetsys">
                <eoms:comboBox name="${sheetPageName}mainnetsystem" id="${sheetPageName}mainnetsystem"
                               sub="" initDicId="1011404" defaultValue="${sheetMain.mainnetsystem}"/>
            </td>

            <td id="selrevdept">
                <input type="button" value="${eoms:a2u('申请单位')}" id="deptTreeBtn" class="btn"/>
                <input name="revDeptName" value="" id="revDeptName" class="text" readonly="readonly"
                       alt="allowBlank:true">

            </td>

        </tr>
        <tr>
            <td>
                <input type="hidden" id="reportIndex" name="reportIndex" value="0">
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
