<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.boco.eoms.workplan.vo.TawwpMonthPlanVO" %>
<%@ page import="com.boco.eoms.workplan.vo.TawwpMonthExecuteVO" %>
<%@ page import="com.boco.eoms.common.util.StaticMethod" %>

<%
    List monthExecuteVOList = (ArrayList) request.getAttribute("monthexecutevolist");
    TawwpMonthExecuteVO tawwpMonthExecuteVO = null;
    String monthPlanId = (String) request.getAttribute("monthplanid");
    //added by lijia 2005-11-28
    String month = (String) request.getAttribute("month");
    String year = (String) request.getAttribute("year");
    String currTime = (String) request.getAttribute("currtime");
%>


<script language="JavaScript">

    Ext.onReady(function () {
        colorRows('listTable');
        var userTreeAction = '${app}/xtree.do?method=dept';
        userViewer = new Ext.JsonView("user-list", '<div id="user-{id}" class="viewlistitem-user">{name}</div>',
            {
                multiSelect: true,
                emptyText: '<div></div>'
            }
        );

        //var s = ' ';
        //userViewer.jsonData = eoms.JSONDecode(s);
        //userViewer.refresh();


        var config = {
            btnId: 'userTreeBtn', dlgId: 'dlg-user',
            treeDataUrl: userTreeAction, treeRootId: '-1', treeRootText: '部门', treeChkMode: '', treeChkType: 'dept',
            saveChkFldId: 'userId'
        };

        //添加人员树图和角色树图
        config.onLayout = function (cal, layout) {
            var config = {
                treeDataUrl: '${app}/xtree.do?method=userFromDept',
                treeChkMode: '',
                treeRootId: '-1',
                treeRootText: '人员树',
                treeChkType: 'user'

            };

            layout.add('west', cal.newTreePanel(config));
            var config2 = {
                treeDataUrl: '${app}/xtree.do?method=getCptroomTree',
                treeChkMode: '',
                treeRootId: '-1',
                treeRootText: '机房',
                treeChkType: 'cptroom'
            };
            layout.add('west', cal.newTreePanel(config2));
        };
        config.onBeforeCheck = function (node, checked) {
            if (checked && this.gridData.getCount() > 0) {
                var r = this.gridData.getAt(0);
                if (r.json.nodeType != node.attributes.nodeType) {
                    alert('每类作业计划只能选择一类执行人');
                    return;
                }
            }
            return true;
        };
        config.onOutput = function (textList, dataList, jsonData) {

            var deptTemp = [];
            var userTemp = [];
            var subRoleTemp = [];
            var valueId = textList;
            var value = dataList;
            var valueType = "";
            Ext.each(jsonData, function (item) {
                switch (item.nodeType) {
                    case "user" :
                        userTemp.push(item.id);
                        valueType = "0";
                        break;
                    case "dept" :
                        deptTemp.push(item.id);
                        valueType = "1";
                        break;
                    case "cptroom" :
                        valueType = "3";
                        subRoleTemp.push(item.id);
                }
            });
            var haschecked = false;
            if (document.forms[0].executeIds) {

                var obj = document.getElementsByName('executeIds');
                for (i = 1; i < obj.length + 1; i++) {
                    if (obj[i - 1].checked) {
                        haschecked = true;
                        var span = $('sort' + i + '_text');
                        var deptHidden = $('sort' + i + '_deptid');
                        var userHidden = $('sort' + i + '_userid');
                        var subRoleHidden = $('sort' + i + '_postid');
                        span.update(textList);
                        var monthExecuteObj = eval("document.getElementById('monthExecute" + i + "')");

                        //计划字符串(monthExecuteobj.value)的格式为 执行内容ID#执行人ID字符串#执行人类型#执行日期字符串
                        var monthExecuteArr = monthExecuteObj.value.split("#");
                        monthExecuteArr[1] = value;
                        monthExecuteArr[2] = valueType;
                        monthExecuteObj.value = monthExecuteArr.join("#");


                    }
                }
            }
            if (!haschecked) {
                alert("<bean:message key="monthexecuteedit.title.warnSelExeContent" />");
            }
        }
        userTree = new xbox(config);
    })

    function GoBack() {
        var monthPlanId = "<%=monthPlanId%>";
        location.href = "../tawwpmonth/monthview.do?monthplanid=" + monthPlanId;
    }

    function onDeleteExecute() {

        if (confirm("<bean:message key="monthexecuteedit.title.warnMonthConfirm" />")) {
            var monthExecuteIdStr = "";
            var flag = true;
            var monthPlanId = "<%=monthPlanId%>";
            var exe = document.getElementsByName('executeIds');

            if (exe.length == 0) {
                alert("<bean:message key="monthexecuteedit.title.warnSelExeContent" />");
            } else {
                for (var i = 0; i < exe.length; i++) {
                    if (exe[i].checked) {
                        if (flag) {
                            monthExecuteIdStr = eval("document.getElementById('monthexecuteid" + (i + 1) + "')").value;
                            flag = false;
                        } else {
                            monthExecuteIdStr = monthExecuteIdStr + "," + eval("document.getElementById('monthexecuteid" + (i + 1) + "')").value;
                        }
                    }
                }
                location.href = "../tawwpmonth/monthexecutedel.do?monthexecuteidstr=" + monthExecuteIdStr + "&monthplanid=" + monthPlanId;

            }
        }
    }

    //点击单元格时显示/隐藏对勾，并保存其值，选择为1，不选择为0
    function onExecuteDate(row, date) {

        var evt = getEvent();
        var td = evt.srcElement || evt.target;

        var currTime = document.forms[0].currtime.value;

        var tdIndex = td.cellIndex + 1;
        var clickTime = document.forms[0].year.value + "-" + document.forms[0].month.value + "-" + tdIndex + " 23:59:59";


        var setdate, settime, tmptime1, tmptime2;

        setdate = clickTime.split(" ")[0];
        settime = clickTime.split(" ")[1];
        tmptime1 = new Date(setdate.split("-")[0], setdate.split("-")[1] - 1, setdate.split("-")[2], settime.split(":")[0], settime.split(":")[1]);

        setdate = currTime.split(" ")[0];
        settime = currTime.split(" ")[1];
        tmptime2 = new Date(setdate.split("-")[0], setdate.split("-")[1] - 1, setdate.split("-")[2], settime.split(":")[0], settime.split(":")[1]);

        var temp = tmptime2.getTime() - tmptime1.getTime();

        if (temp > 0) {
            alert("<bean:message key="monthexecuteedit.title.warnCantSet" />");
            return;
        }


        if (td.innerHTML == "√") {
            td.innerHTML = " ";
            td.value = "0"
        } else {
            td.innerHTML = "√";
            td.value = "1";
        }
        //取出"tr2"、"tr3"中的序数
        update(td.parentNode.id.substring(2));
    }

    function getEvent() {
        if (document.all) {
            return window.event;//如果是ie
        }
        func = getEvent.caller;
        while (func != null) {
            var arg0 = func.arguments[0];
            if (arg0) {
                if ((arg0.constructor == Event || arg0.constructor == MouseEvent)
                    || (typeof (arg0) == "object" && arg0.preventDefault && arg0.stopPropagation)) {
                    return arg0;
                }
            }
            func = func.caller;
        }
        return null;
    }


    //更新计划字符串中的执行日期
    function update(id) {

        var tempStr = "";
        eoms.select('td', 'tr' + id).each(function (td, index) {

            if (td.innerHTML == "√") {

                tempStr += "1";
            } else {

                tempStr += "0";
            }

        });
        var monthExecuteObj = eval("document.getElementById('monthExecute" + id + "')");

        //计划字符串(monthExecuteobj.value)的格式为 执行内容ID#执行人ID字符串#执行人类型#执行日期字符串
        var monthExecuteArr = monthExecuteObj.value.split("#");

        monthExecuteArr[3] = tempStr;
        monthExecuteObj.value = monthExecuteArr.join("#");

    }

    //弹出选择执行人窗口
    function selectTree() {
        var haschecked = false;
        if (document.forms[0].executeIds) {
            var exe = document.getElementsByName('executeIds');
            for (i = 0; i < obj.length; i++) {
                if (obj[i].checked)
                    haschecked = true;
            }
            //增加只有一项执行内容的情况
            if (obj.length == null) {
                if (obj.checked)
                    haschecked = true;
            }
            if (!haschecked) {
                alert("<bean:message key="monthexecuteedit.title.warnSelExeContent" />");
            } else {
                dWinOrnaments = "status:no;scroll:no;resizable:yes;dialogHeight:450px;dialogWidth:480px;";
                dWin = showModalDialog('<%=request.getContextPath()%>/css/listbox_zyjh/listbox_zyjh.jsp?selectself=yes', window, dWinOrnaments);
            }
        }
    }

    //检验数据并提交
    function onSubmit() {

        var size = "<%=monthExecuteVOList.size()%>";

        for (i = 1; i <= size; i++) {
            var monthExecuteObj = eval("document.getElementById('monthExecute" + i + "')");


            var monthExecuteArr = monthExecuteObj.value.split("#");

            if (monthExecuteArr[1] == "" || monthExecuteArr[2] == "") {
                alert("<bean:message key="monthexecuteedit.title.warnSelPlanContent" /> \"" + monthExecuteObj.getAttribute("label") + "\" <bean:message key="monthexecuteedit.title.warnExecuter" />");
                return false;
            }
            if (monthExecuteArr[3] == "" || monthExecuteArr[3] == "0000000000000000000000000000000") {
                alert("<bean:message key="monthexecuteedit.title.warnSelPlanContent" /> \"" + monthExecuteObj.getAttribute("label") + "\" <bean:message key="monthexecuteedit.title.warnExecuteTime" />");
                return false;
            }
        }


    }

    //全选/反选开关
    function selectall(v) {
        var selobj = document.forms[0].executeIds;
        //增加了只有一项执行内容的情况
        if (selobj.length == null) {
            selobj.checked = v;
        } else {
            for (i = 0; i < selobj.length; i++) {
                selobj[i].checked = v;
            }
        }
    }

    function changeStartHH(i) {
        var startHH = Number(eoms.$('startHH' + i).value);
        var endHH = Number(eoms.$('endHH' + i).value);
        if (endHH < startHH) {
            alert('起始时间不能大于结束时间！');
            eoms.$('startHH' + i).value = 0
            return false;
        }
        var monthExecuteObj = eoms.$('monthExecute' + i);
        var monthExecuteArr = monthExecuteObj.value.split("#");
        //计划字符串(monthExecuteobj.value)的格式为 执行内容ID#执行人ID字符串#执行人类型#执行日期字符串#开始时间#结束时间
        monthExecuteArr[4] = startHH;
        monthExecuteObj.value = monthExecuteArr.join("#");
    }

    function changeEndHH(i) {
        var startHH = Number(eoms.$('startHH' + i).value);
        var endHH = Number(eoms.$('endHH' + i).value);
        if (endHH < startHH) {
            alert('起始时间不能大于结束时间！');
            eoms.$('endHH' + i).value = 23
            return false;
        }
        var monthExecuteObj = eoms.$('monthExecute' + i);
        var monthExecuteArr = monthExecuteObj.value.split("#");
        //计划字符串(monthExecuteobj.value)的格式为 执行内容ID#执行人ID字符串#执行人类型#执行日期字符串#开始时间#结束时间
        monthExecuteArr[5] = endHH;
        monthExecuteObj.value = monthExecuteArr.join("#");
    }
</script>
<form name="tawwpmonthexecuteform" method="post" action="../tawwpmonth/monthexecuteeditsave.do"
      onsubmit="return onSubmit();">
    <!--   body begin   -->

    <br>

    <table class="listTable" id="listTable">
        <caption><bean:message key="monthexecuteedit.title.formTitle"/></caption>
        <thead>
        <tr>
            <td width="30" nowrap>
                <input type="checkbox" name="" onclick="selectall(this.checked);">
            </td>
            <td width="100" nowrap>
                作业项目
            </td>

            <td width="100" nowrap>
                业务类型
            </td>
            <td width="100" nowrap>
                执行单位级别
            </td>
            <td width="100" nowrap>
                适用说明
            </td>

            <td colspan="31">
                <bean:message key="monthexecuteedit.title.labSelectExecuter"/>
                <!--   input type="button" value="<bean:message key="monthexecuteedit.title.btnSetExecuter" />" name="" onclick="selectTree()" Class="button"> -->

                <input type="button" value="<bean:message key="monthexecuteedit.title.btnSetExecuter" />"
                       id="userTreeBtn" class="btn"/>

            </td>
        </tr>
        </thead>
        <tbody>
        <%
            for (int i = 1; i < (monthExecuteVOList.size() + 1); i++) {
                tawwpMonthExecuteVO = (TawwpMonthExecuteVO) (monthExecuteVOList.get(i - 1));
        %>

        <tr>
            <td rowspan="3" nowrap>


                <input type="checkbox" name="executeIds" value="<%=i%>">
                <input type="hidden" name="monthexecuteid<%=i%>" id="monthexecuteid<%=i%>"
                       value="<%=tawwpMonthExecuteVO.getId()%>">
            </td>
            <td rowspan="3" nowrap>
                <%=tawwpMonthExecuteVO.getName()%>
            </td>
            <td rowspan="3" nowrap>
                <%=tawwpMonthExecuteVO.getBotype() %>
            </td>
            <td rowspan="3" nowrap>
                <%=tawwpMonthExecuteVO.getExecutedeptlevel() %>
            </td>
            <td rowspan="3" nowrap>
                <%=tawwpMonthExecuteVO.getAppdesc() %>
            </td>
            <td colspan="31">

                执行单位或人员：
                <input type="hidden" id="userId" name="userId"/>
                <span id="sort<%=i%>_text"><%=tawwpMonthExecuteVO.getExecuterName()%></span>
                <INPUT TYPE="hidden" name="sort<%=i%>_deptid" id="sort<%=i%>_deptid" value="">
                <INPUT TYPE="hidden" name="sort<%=i%>_userid" id="sort<%=i%>_userid" value="">
                <INPUT TYPE="hidden" name="sort<%=i%>_postid" id="sort<%=i%>_postid" value="">

                &nbsp;&nbsp;&nbsp;&nbsp;<bean:message
                    key="monthexecuteedit.title.labCycle"/><%=tawwpMonthExecuteVO.getCycleName()%>

                &nbsp;&nbsp;&nbsp;&nbsp;<bean:message key="monthexecuteedit.title.labForm"/><a target="_blank"
                                                                                               href="../tawwpaddons/addonsread.do?action=read&window=new&myid=&model=50&addonsid=<%=tawwpMonthExecuteVO.getFormId()%>&reaction=./"><%=tawwpMonthExecuteVO.getFormName()%>
            </a>
                &nbsp;&nbsp;&nbsp;&nbsp;是否必须上传附件：<%=tawwpMonthExecuteVO.getFileFlagName() %>
                <p/>
                起始时间：
                <select id="startHH<%=i%>" name="startHH<%=i%>" class="select"
                        onchange=javascript:changeStartHH('<%=i%>');>
                    <%
                        int sh = 0;
                        if (tawwpMonthExecuteVO.getStartHH() != null && !tawwpMonthExecuteVO.getStartHH().equals("")) {
                            sh = StaticMethod.null2int(tawwpMonthExecuteVO.getStartHH());
                        }
                        for (int m = 0; m < 24; m++) {
                            if (m == sh) {
                    %>
                    <option value="<%=m%>" selected><%=m%>
                    </option>
                    <% } else {%>
                    <option value="<%=m%>"><%=m%>
                    </option>
                    <%
                            }
                        }
                    %>
                </select>
                时
                &nbsp;&nbsp;
                结束时间：
                <select id="endHH<%=i%>" name="endHH<%=i%>" class="select" onchange=javascript:changeEndHH('<%=i%>');>
                    <%
                        int eh = 23;
                        if (tawwpMonthExecuteVO.getEndHH() != null && !tawwpMonthExecuteVO.getEndHH().equals("")) {
                            eh = StaticMethod.null2int(tawwpMonthExecuteVO.getEndHH());
                        }
                        for (int n = 0; n < 24; n++) {
                            if (n == eh) {
                    %>
                    <option value="<%=n%>" selected><%=n%>
                    </option>
                    <% } else {%>
                    <option value="<%=n%>"><%=n%>
                    </option>
                    <%
                            }
                        }
                    %>
                </select>
                时
                <div id="user-list" class="viewer-list"></div>
            </td>
        </tr>

        <tr>
            <%
                for (int j = 0; j < Integer.parseInt(tawwpMonthExecuteVO.getDayCount()); j++) {
            %>
            <td width="5">
                <%=j + 1%>
            </td>
            <%
                }
            %>
        </tr>

        <INPUT TYPE="hidden" name="monthExecute<%=i%>" id="monthExecute<%=i%>"
               value="<%=tawwpMonthExecuteVO.getId()%>#<%=tawwpMonthExecuteVO.getExecuter()%>#<%=tawwpMonthExecuteVO.getExecuterType()%>#<%=tawwpMonthExecuteVO.getExecuteDuty()%>#<%=tawwpMonthExecuteVO.getStartHH()%>#<%=tawwpMonthExecuteVO.getEndHH()%>"
               label="<%=tawwpMonthExecuteVO.getName()%>">

        <tr id="tr<%=i%>">
            <%
                char[] temp = (tawwpMonthExecuteVO.getExecuteDate()).toCharArray();
                for (int k = 0; k < temp.length; k++) {
                    if (temp[k] == '1') {
            %>
            <td onclick="javascript:onExecuteDate();" class="d" value="1">√</td>
            <%
            } else {
            %>
            <td onclick="javascript:onExecuteDate();" class="d" value="0">&nbsp;</td>
            <%
                    }
                }
            %>

        </tr>
        <%
            }
        %>
        </tbody>
        <tr>
            <td width="20%" colspan="35">
                <!--added by lijia 2005-11-28-->
                <INPUT type="hidden" value="<%=month%>" name="month">
                <INPUT type="hidden" value="<%=year%>" name="year">
                <INPUT type="hidden" value="<%=currTime%>" name="currtime">
                <!-- end -->
                <INPUT type="hidden" value="<%=monthExecuteVOList.size()%>" name="count">
                <INPUT type="hidden" value="<%=monthPlanId%>" name="monthplanid">
                <INPUT type="button" value="<bean:message key="monthexecuteedit.title.btnRemove" />" name="B1"
                       Onclick="javascript:onDeleteExecute();" Class="button">
                <INPUT type="submit" value="<bean:message key="monthexecuteedit.title.btnSubmit" />" name="submit"
                       Class="submit">
                <input type="button" value="<bean:message key="monthexecuteedit.title.btnBack" />" name="B1"
                       class="button" onclick="javascript:GoBack();">
            </td>
        </tr>
    </table>

    <script type="text/javascript">
        <%for(int i=1; i<(monthExecuteVOList.size()+1); i++){%>update(<%=i%>);
        <%}%>
    </script>
    <!-- body end -->
</form>
<%@ include file="/common/footer_eoms.jsp" %>



