<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@page import="com.boco.eoms.commons.statistic.base.config.excel.* ,java.util.*" %>
<%
    Excel excelConfig = (Excel) request.getAttribute("excelConfig");
    if (excelConfig == null) throw new Exception("读取配置统计文件失败!");

    String excelConfigURL = String.valueOf(request.getAttribute("excelConfigURL"));
    String findListForward = String.valueOf(request.getAttribute("findListForward"));
    String findForward = String.valueOf(request.getAttribute("findForward"));

    Calendar cld = Calendar.getInstance();
    int year = cld.get(Calendar.YEAR);
    int mondth = cld.get(Calendar.MONTH) + 1;
%>
<script type="text/javascript">
    var userTreeAction = '${app}/xtree.do?method=dept';
    var treeAction = '${app}/xtree.do?method=userByDept';
    var v;
    Ext.onReady(function () {
//	${"customDescribe"}.value=123;
        ${"statspecial"}.
        value = 1;

        eoms.form.disableArea("tr2", true);

        v = new eoms.form.Validation({form: "theform"});
        sendDeptTree = new xbox({
            btnId: 'userTreeBtn',
            dlgId: 'dlg-dept',
            treeDataUrl: userTreeAction,
            treeRootId: '-1',
            treeRootText: '${eoms:a2u("所属部门")}',
            treeChkMode: '',
            treeChkType: 'user',
            showChkFldId: 'sendDeptName',
            saveChkFldId: 'sendDeptId'
        });

        revDeptTree = new xbox({
            btnId: 'userTreeBtn1',
            dlgId: 'dlg-dept1',
            treeDataUrl: userTreeAction,
            treeRootId: '-1',
            treeRootText: '${eoms:a2u("所属部门")}',
            treeChkMode: '',
            treeChkType: 'user',
            showChkFldId: 'revDeptName',
            saveChkFldId: 'revDeptId'
        });

    })

    function displayTR(sel) {

        //alert(sel.value);
        //alert('${eoms:a2u("")}');
        if (sel.value == 0) {
            eoms.form.disableArea("tr2", true);
            eoms.form.enableArea("tr1");
            //document.getElementById("tr1").style.display="block";
            //document.getElementById("tr2").style.display="none";
        } else if (sel.value == 1) {
            eoms.form.disableArea("tr1", true);
            eoms.form.enableArea("tr2");
            //document.getElementById("tr1").style.display="none";
            //document.getElementById("tr2").style.display="block";
        } else if (sel.value == "time") {
            eoms.form.disableArea("td1", true);
            eoms.form.enableArea("td2");
        } else if (sel.value == "month") {
            eoms.form.disableArea("td2", true);
            eoms.form.enableArea("td1");
        }
        return;
    }

    function validateCheck(data) {
        alert(data.getElementById("beginTime").value);
        alert(data.getElementById("endTime").value);
    }

    function setStatspecial(sel) {
        if (sel.value == "14,1562,1566,1568,15,16,17,18,19,20,21,22") {
            ${"statspecial"}.
            value = 1;
        }
    }
</script>

<html:form action="/stat.do?method=saveCustomSatatistInfo" styleId="theform">


    <table class="formTable">
        <caption>${eoms:a2u("输入条件")}</caption>

        <tr>
            <td>
                <input type="hidden" name="excelConfigURL" value="<%=excelConfigURL %>">
                <input type="hidden" name="findListForward" value="<%=findListForward %>">
                <input type="hidden" name="findForward" value="<%=findForward %>">
            </td>
        </tr>

        <tr>
            <td>
                    ${eoms:a2u("定制类型")}
            </td>

            <
            <td>
                <select name="reportType">
                    <option value="yearReport">${eoms:a2u("年报")}</option>
                    <option value="seasonReport">${eoms:a2u("季报")}</option>
                    <option value="monthReport">${eoms:a2u("月报")}</option>
                    <option value="weekReport">${eoms:a2u("周报")}</option>
                    <option value="dailyReport">${eoms:a2u("日报")}</option>
                </select>
            </td>
        </tr>

        <tr>
            <td noWrap class="label">${eoms:a2u("选择报表")}</td>
            <td width="80%">

                <select name="reportIndex" onchange="displayTR(this)">
                    <%
                        System.out.println(excelConfig.getSheets().length);
                        for (int i = 0; i < excelConfig.getSheets().length; i++) {
                            Sheet sheet = excelConfig.getSheets()[i];
                    %>
                    <option value="<%=sheet.getSheetIndex() %>"><%=sheet.getSheetName()%>
                    </option>


                    <%}%>
                </select>
            </td>
        </tr>

        <tr id="tr1">
            <td noWrap class="label"><bean:message bundle="statistictask" key="statistic.city"/>
            </td>
            <td width="80%">
                <input id="statspecial" type="hidden" name="statspecial">
                <select name="cityDeptId" onchange="setStatspecial(this)">

                    <option value="14,1562,1566,1568">${eoms:a2u("贵阳分公司")}</option>
                    <option value="15">${eoms:a2u("遵义分公司")}</option>
                    <option value="16">${eoms:a2u("安顺分公司(运行维护部）")}</option>
                    <option value="17">${eoms:a2u("黔南分公司")}</option>
                    <option value="18">${eoms:a2u("黔东南分公司")}</option>
                    <option value="19">${eoms:a2u("铜仁分公司")}</option>
                    <option value="20">${eoms:a2u("毕节分公司")}</option>
                    <option value="21">${eoms:a2u("六盘水分公司")}</option>
                    <option value="22">${eoms:a2u("黔西南分公司")}</option>
                    <option value="14,1562,1566,1568,15,16,17,18,19,20,21,22">${eoms:a2u("全部")}</option>

                </select>
            </td>


        </tr>

        <!-- -->
        <tr id="tr2" style="display:none;">
            <td noWrap class="label"><bean:message bundle="statistictask" key="statistic.dept"/>
            </td>

            <td>
                <input type="button" value="${eoms:a2u('派发部门')}" id="userTreeBtn" class="btn"/>

                <input type="text" name="sendDeptName" value="" id="sendDeptName" class="text" readonly="readonly"
                       alt="allowBlank:false">
                <input type="hidden" name="sendDeptId">

                <input type="button" value="${eoms:a2u('受理部门')}" id="userTreeBtn1" class="btn"/>
                <input name="revDeptName" value="" id="revDeptName" class="text" readonly="readonly"
                       alt="allowBlank:false">
                <input type="hidden" name="revDeptId">
            </td>

        </tr>


        <input id="customDescribe" type="hidden" name="customDescribe" value="showCustomDescribe">

    </table>

    <br/>
    <!-- buttons -->

    <html:submit styleClass="btn" property="method.save" styleId="method.save">
        <bean:message bundle="statistic" key="button.custom"/>
    </html:submit>

</html:form>

<%@ include file="/common/footer_eoms.jsp" %>
