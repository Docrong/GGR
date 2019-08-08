<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Hashtable" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="com.boco.eoms.workplan.util.TawwpStaticVariable" %>
<%@ page import="com.boco.eoms.workplan.util.TawwpUtil" %>

<%
    //  String yearFlag = TawwpUtil.getCurrentDateTime("yyyy");
//  String monthFlag = "08";
// dayCount = TawwpUtil.getDayCountOfMonth(yearFlag, monthFlag);
    int dayCount = 31;
    String tempStr = "";
%>
<script language="javascript">
    <!--
    begin
    by
    denden(贵州本地)
    增加对日期的选择-->
    function show() {
        if (window.tawwpquerymonthplan.monthflag.value == "0") {
            document.forms[0].type[0].checked = false;
            document.forms[0].type[1].checked = true;
            alert("<bean:message key='gzquerymonth.title.warnSeletMonth' />");
            return;
        } else {
            document.getElementById("dateselect").style.display = "";
        }
    }

    function hidden() {
        document.getElementById("dateselect").style.display = "none";
    }

    <!--end by denden(贵州本地) 增加对日期的选择-->
    function SubmitCheck() {
        if (window.tawwpquerymonthplan.yearflag.value == "0") {
            alert("请选择查询年份");
            return false;
        } else if (window.tawwpquerymonthplan.monthflag.value == "0") {
            alert("<bean:message key='gzquerymonth.title.warnSeletMonth' />");
            return false;
        } else {
            frmReg = document.tawwpquerymonthplan;
            return true;
        }
    }

    function onNet() {
        if (window.tawwpquerymonthplan.monthflag.value == "0") {
            alert("请选择网元类型")
            return;
        } else {
            var _sHeight = 350;
            var _sWidth = 400;
            var sTop = (window.screen.availHeight - _sHeight) / 2;
            var sLeft = (window.screen.availWidth - _sWidth) / 2;
            var sFeatures = "dialogHeight: " + _sHeight + "px; dialogWidth: " + _sWidth + "px; dialogTop: " + sTop + "; dialogLeft: " + sLeft + "; center: Yes; scroll:No;help: No; resizable: No; status: No;";
            window.showModalDialog('../tawwpyear/netindexbynettype.do?refresh=<%=TawwpUtil.getCurrentDateTime("yyyyMMddhhssmm")%>&deptid=' + window.tawwpquerymonthplan.deptid.value + '&nettypeid=' + window.tawwpquerymonthplan.nettype.value, window, sFeatures);
        }
    }

    //弹出选择部门窗口
    function selectTree() {
        dWinOrnaments = "status:no;scroll:no;resizable:yes;dialogHeight:450px;dialogWidth:480px;";
        dWin = showModalDialog('<%=request.getContextPath()%>/css/listbox_zyjh/listbox_depttoinput.jsp', window, dWinOrnaments);
    }


    <
    !--
    var onecount;
    onecount = 0;

    subcat = new Array();
    <%
    Hashtable hashtable = TawwpStaticVariable.SYS_NET_INF;
    Enumeration sysEnumeration = null;
    List netList = null;

    String[] sysType = null;
    String[] netType = null;
    String netID = "";
    String netName = "";

    int count = 0;

    sysEnumeration = hashtable.keys();
    while(sysEnumeration.hasMoreElements()){
      sysType = (String[])sysEnumeration.nextElement();
      netList = (List)hashtable.get(sysType);
      for(int i=0; i<netList.size(); i++){
        netType = (String[])netList.get(i);
    %>
    subcat[<%=count++%>] = new Array("<%=netType[0]%>", "<%=sysType[1]%>", "<%=netType[1]%>");
    <%
      }
    }
    %>

    onecount =<%=count%>;

    function changelocation(locationid) {
        document.tawwpquerymonthplan.nettype.length = 0;
        var locationid = locationid;
        var i;

        document.tawwpquerymonthplan.nettype.options[0] = new Option('<bean:message key="gzquerymonth.title.selNetType" />', '0');

        for (i = 0; i < onecount; i++) {
            if (subcat[i][1] == locationid) {
                document.tawwpquerymonthplan.nettype.options[document.tawwpquerymonthplan.nettype.length] = new Option(subcat[i][0], subcat[i][2]);
            }
        }
    }

    Ext.onReady(function () {
        var userTreeAction = '${app}/xtree.do?method=userFromDept';
        userViewer = new Ext.JsonView("user-list",
            '<div id="user-{id}" class="viewlistitem-user">{name}</div>',
            {
                multiSelect: true,
                emptyText: '<div></div>'
            }
        );
        userViewer.refresh();

        userTree = new xbox({
            btnId: 'userTreeBtn', dlgId: 'dlg-user',
            treeDataUrl: userTreeAction, treeRootId: '-1', treeRootText: '部门', treeChkMode: '', treeChkType: 'dept',
            viewer: userViewer, saveChkFldId: 'deptid'
        });
    })
    //-->

</script>

<form name="tawwpquerymonthplan" method="post" action="gzlistmonthplan.do" onsubmit='return SubmitCheck()'>


    <table class="formTable">
        <caption><bean:message key="gzquerymonth.title.formTitle"/></caption>
        <!--月度统计,屏蔽月度查询的一些按钮如：系统类型，网元类型，相关网元等三个选择项        -->


        <tr>
            <td width="100" class="label">


                <bean:message key="gzquerymonth.title.formDeptName"/>
            </td>
            <td width="500" colspan="6">
                <div id="user-list" class="viewer-list"></div>
                <input type="button" value="<bean:message key="gzquerymonth.title.btnSelDept" />" id="userTreeBtn"
                       class="btn"/>
                <INPUT TYPE="hidden" name="deptid" id="deptid" value="">
            </td>
        </tr>


        <tr>
            <td width="100" class="label">
                <bean:message key="gzquerymonth.title.formYear"/>
            </td>
            <td width="100">
                <select name="yearflag" class="select">
                    <option value="0"><bean:message key="gzquerymonth.title.formSelect"/></option>
                    <option value="2006">2006</option>
                    <option value="2007">2007</option>
                    <option value="2008">2008</option>
                    <option value="2009">2009</option>
                    <option value="2010">2010</option>
                    <option value="2011">2011</option>
                    <option value="2012">2012</option>
                </select>
            </td>

            <td width="100" class="label">
                <bean:message key="gzquerymonth.title.formMonth"/>
            </td>
            <td width="300" colspan="3">
                <select name="monthflag" class="select">
                    <option value="0"><bean:message key="gzquerymonth.title.formSelect"/></option>
                    <option value="01">01</option>
                    <option value="02">02</option>
                    <option value="03">03</option>
                    <option value="04">04</option>
                    <option value="05">05</option>
                    <option value="06">06</option>
                    <option value="07">07</option>
                    <option value="08">08</option>
                    <option value="09">09</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                </select>
            </td>
            <td width="300">
                <input type="radio" name="type" value="week" onclick="javascript:show();"><bean:message
                    key="gzquerymonth.title.formWeekly"/>
                <input type="radio" name="type" value="month" onclick="javascript:hidden();"
                       checked="checked"><bean:message key="gzquerymonth.title.formMonthly"/>
            </td>
        </tr>
        <tr id="dateselect" style="display:none">
            <td width="100" class="label">
                <bean:message key="gzquerymonth.title.formWeeklyDate"/>
            </td>
            <td width="300" colspan="6">
                <select name="sDate" class="select">
                    <%
                        for (int i = 1; i < (dayCount + 1); i++) {
                            tempStr = String.valueOf(i);
                            if (tempStr.length() == 1) {
                                tempStr = "0" + tempStr;
                            }
                    %>
                    <option value="<%=tempStr%>"><%=tempStr%>
                    </option>
                    <%
                        }
                    %>
                </select>
                <bean:message key="gzquerymonth.title.formDay"/>
                <select name="eDate" class="select">
                    <%
                        for (int j = 1; j < (dayCount + 1); j++) {
                            tempStr = String.valueOf(j);
                            if (tempStr.length() == 1) {
                                tempStr = "0" + tempStr;
                            }
                    %>
                    <option value="<%=tempStr%>"><%=tempStr%>
                    </option>
                    <%
                        }
                    %>

                </select>
                <bean:message key="gzquerymonth.title.formDay"/>
            </td>
        </tr>

        <tr class="label">
            <td width="100" class="label">
                <bean:message key="gzquerymonth.title.formConstituteState"/>
            </td>
            <td width="100">
                <select name="constitutestate" class="select">
                    <option value="1" selected="selected">通过</option>
                    <option value="0">新建</option>
                    <option value="2">驳回</option>
                    <option value="3">审批中</option>
                </select>
            </td>

            <td width="100" class="label">
                <bean:message key="gzquerymonth.title.formExecuteState"/>
            </td>
            <td width="300" colspan="4">
                <select name="executestate" class="select">
                    <option value="-1">-- <bean:message key="gzquerymonth.title.formSelect"/> --</option>
                    <option value="1">归档</option>
                    <option value="4">执行中</option>
                </select>
            </td>
        </tr>

        <tr class="label">
            <td colspan="7">
                <input type="submit" value="<bean:message key='queryyearplan.title.formSubmit' />" name="B1"
                       class="submit">
            </td>
        </tr>
    </table>
</form>

<%@ include file="/common/footer_eoms.jsp" %>