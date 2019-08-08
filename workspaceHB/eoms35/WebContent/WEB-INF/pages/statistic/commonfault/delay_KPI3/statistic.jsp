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
    var userTreeAction = '${app}/xtree.do?method=dept';//部门树
    var userByDeptTree = '${app}/xtree.do?method=userFromDept';//部门用户树
    var areaTree = '${app}/xtree.do?method=areaTree'; //地域树
    var v;
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: "theform"});

        //选择地域对话框
        areaTree = new xbox({
            btnId: 'areaTreeBtn',
            dlgId: 'dlg-area',
            treeDataUrl: areaTree,
            treeRootId: '-1',
            treeRootText: '${eoms:a2u("区域")}',
            treeChkMode: '',
            treeChkType: 'area',
            showChkFldId: 'areaName',
            saveChkFldId: 'areaid'
        });

        //用户树
        userByDeptTree = new xbox({
            btnId: 'userByDeptTreeBtn',
            dlgId: 'dlg-user',
            treeDataUrl: userByDeptTree,
            treeRootId: '-1',
            treeRootText: '${eoms:a2u("人员")}',
            treeChkMode: '',
            treeChkType: 'user',
            showChkFldId: 'userByDeptName',
            saveChkFldId: 'userByDeptid'
        });

        //部门用户树
        var flowRoleSubroleTree = "${app}/sheet/workflow/workflow.do?method=getAllWorkflow";
        var cfg = {
            btnId: 'subroleFromDeptBtn',
            treeDataUrl: flowRoleSubroleTree,
            treeRootId: '-1',
            treeRootText: '${eoms:a2u("流程角色树")}',
            treeChkMode: '',
            treeChkType: 'subrole',
            showChkFldId: 'subroleFromDeptName',
            saveChkFldId: 'subroleFromDeptid',
            baseAttrs: {
                loader: new Ext.tree.TreeLoader({
                    dataUrl: eoms.appPath + '/xtree.do?method=flowRoleSubrole',
                    listeners: {
                        beforeload: function (loader, node, callback) {
                            loader.baseParams['nodeType'] = node.attributes.nodeType || '';
                        }
                    }
                })
            }
        }
        var frTree = new xbox(cfg);
        frTree.defaultTree.loader.applyLoader = false;
    })

    function displayTR(sel) {


        //alert('${eoms:a2u("")}');

        //alert(sel.value);
        if (sel.value == "time") {
            eoms.form.disableArea("td1", true);
            eoms.form.enableArea("td2");
        } else if (sel.value == "month") {
            eoms.form.disableArea("td2", true);
            eoms.form.enableArea("td1");
        } else if (sel.value == 0) {
            eoms.form.disableArea("operateroletd", true);
            eoms.form.enableArea("operateusertd");
        } else if (sel.value == 1) {
            eoms.form.disableArea("operateusertd", true);
            eoms.form.enableArea("operateroletd");
        }

        return;
    }

    function validateCheck(data) {
        alert(data.getElementById("beginTime").value);
        alert(data.getElementById("endTime").value);
    }

    //alert(${"statspecial"}.value);


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
                    <option value="time">${eoms:a2u("时间")}</option>
                    <option value="month">${eoms:a2u("年月")}</option>
                </select>
            </td>

            <td id="td1" style="display:none;">
                    ${eoms:a2u("按月统计")}
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

            <td width="80%" id="td2">
                <bean:message bundle="statistic" key="statistic.begin"/>
                <input type="text" name="beginTime" id="beginTime"
                       alt="allowBlank:false,vtype:'lessThen',link:'endTime',vtext:'${eoms:a2u("开始时间不能为空或晚于结束时间")}'"
                       readonly="readonly" onclick="popUpCalendar(this, this,null,null,null,true,-1);" class="text"/>
                <bean:message bundle="statistic" key="statistic.end"/>
                <input type="text" name="endTime" id="endTime"
                       alt="allowBlank:false,vtype:'moreThen',link:'beginTime',vtext:'${eoms:a2u("结束时间不能为空或早于开始时间")}'"
                       readonly="readonly" onclick="popUpCalendar(this, this,null,null,null,true,-1);" class="text"/>
            </td>
        </tr>

        <td noWrap class="label">
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

        <td id=operateusertd><!-- alt="allowBlank:false" -->
            <input type="button" value="${eoms:a2u('部门人员选择')}" id="userByDeptTreeBtn" class="btn"/>
            <input type="text" name="userByDeptName" value="" id="userByDeptName" class="text"/>
            <input type="hidden" id="userByDeptid" value="" name="userByDeptid"/>
        </td>

        <td id=operateroletd style="display:none;">
            <!--
  		  	 <input type="button" value="${eoms:a2u('角色选择')}" id="roleTreeBtn" class="btn" />
  		 	 <input type="text"  name="roleName"  value="" id ="roleName" class="text" />
  		 	 <input type="hidden" id="roleid" name="roleid" />
  		 	  -->

            <input type="button" value="${eoms:a2u('部门角色选择')}" id="subroleFromDeptBtn" class="btn"/>
            <input type="text" name="subroleFromDeptName" value="" id="subroleFromDeptName" class="text"/>
            <input type="hidden" id="subroleFromDeptid" value="" name="subroleFromDeptid"/>
        </td>

        </tr>
        <tr>

            <td noWrap class="label">${eoms:a2u("选择地域")}</td>

            <td>
                <input type="button" value="${eoms:a2u('地域选择')}" id="areaTreeBtn" class="btn"/>
                <input type="text" name="areaName" value="" id="areaName" class="text" readonly="readonly"/>
                <input type="hidden" id="areaid" value="" name="areaid"/>
            </td>

        </tr>

        <tr>
            <td class="label">${eoms:a2u("网络分类一级")}</td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainNetSortOne" id="${sheetPageName}mainNetSortOne"
                               sub="${sheetPageName}mainNetSortTwo" initDicId="1010104"
                               defaultValue="${sheetMain.mainNetSortOne}"/>
            </td>
        </tr>
        <tr>
            <td class="label">${eoms:a2u("网络分类二级")}</td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainNetSortTwo" id="${sheetPageName}mainNetSortTwo"
                               sub="${sheetPageName}mainNetSortThree" initDicId="${sheetPageName}mainNetSortOne"
                               defaultValue="${sheetMain.mainNetSortTwo}"/>
            </td>
        </tr>
        <tr>
            <td class="label">${eoms:a2u("网络分类三级")}</td>
            <td>
                <eoms:comboBox name="${sheetPageName}mainNetSortThree" id="${sheetPageName}mainNetSortThree"
                               sub="" initDicId="${sheetPageName}mainNetSortTwo"
                               defaultValue="${sheetMain.mainNetSortThree}"/>
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
