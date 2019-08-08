<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Hashtable" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="com.boco.eoms.workplan.util.TawwpStaticVariable" %>
<%@ page import="com.boco.eoms.workplan.util.TawwpUtil" %>
<%@ page import="com.boco.eoms.base.util.StaticMethod" %>
<script language="javascript">

    function SubmitCheck() {

        frmReg = document.tawwpquerymonthplan;

        if (frmReg.systype.value == '0') {
            alert("<bean:message key="querymonthplan.title.warnSelectSys"/>");
            return false;
        }

        if (frmReg.nettype.value == '0') {
            alert("<bean:message key="querymonthplan.title.warnSelectNet"/>");
            return false;
        }

        return true;
    }

    function onNet() {
        if (window.tawwpquerymonthplan.nettype.value == "0") {
            alert("<bean:message key="querymonthplan.title.warnSelectNet"/>")
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

    //����ѡ���Ŵ���
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

        document.tawwpquerymonthplan.nettype.options[0] = new Option('<bean:message key="querymonthplan.title.formSelectNetType"/>', '0');

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

    //-->

</script>

<form name="tawwpquerymonthplan" method="post" action="listmonthplan.do" onsubmit='return SubmitCheck()'>

    <table border="0" width="600" class="formTable">
        <caption><bean:message key="querymonthplan.title.formTitle"/></caption>
        <tr>
            <td width="100" class="label">
                <bean:message key="querymonthplan.title.formSys"/>
            </td>
            <td width="500" colspan="5">
                <select size=1 name="systype" class="select"
                        onChange="changelocation(document.tawwpquerymonthplan.systype.options[document.tawwpquerymonthplan.systype.selectedIndex].value)">
                    <option value="0" selected><bean:message key="querymonthplan.title.formSelectSys"/></option>
                    <%
                        sysEnumeration = hashtable.keys();
                        while (sysEnumeration.hasMoreElements()) {
                            sysType = (String[]) sysEnumeration.nextElement();
                    %>
                    <option value="<%=sysType[1]%>"><%=sysType[0]%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </td>
        </tr>
        <tr>
            <td width="100" class="label">
                <bean:message key="querymonthplan.title.formNetType"/>
            </td>
            <td width="500" colspan="5">
                <select size="1" name="nettype" class="select">
                    <option value="0" selected><bean:message key="querymonthplan.title.formSelectNetType"/></option>
                </select>
            </td>
        </tr>

        <tr>
            <td width="100" class="label">
                <bean:message key="querymonthplan.title.formDept"/>
            </td>
            <td width="500" colspan="5">


                <div id="user-list" class="viewer-list"></div>
                <input type="button" value="<bean:message key="queryyearplan.title.formSelectDept" />" id="userTreeBtn"
                       class="btn"/>

                <INPUT TYPE="hidden" name="deptid" id="deptid" value="">

            </td>

        </tr>

        <tr>
            <td width="100" class="label">
                <bean:message key="querymonthplan.title.formConcernedNet"/>
            </td>
            <td width="500" colspan="5">
                <input type="text" name="netnamelist" size="30" readonly="true" value="" class="text">
                <input type="hidden" name="netlist" size="30" styleClass="clstext" readonly="true" value="">
                <input type="button" value="<bean:message key="querymonthplan.title.formSelectNet"/>" name="B1"
                       class="button" Onclick="javascript:onNet();">
            </td>
        </tr>


        <tr>
            <td width="100" class="label">
                <bean:message key="querymonthplan.title.formYearFlag"/>
            </td>
            <td width="100">
                <select name="yearflag" class="select">
                    <%
                        String yyyy = TawwpUtil.getCurrentDateTime("yyyy");
                        int year = StaticMethod.null2int(yyyy);
                        for (int i = year; i > year - 10; i--) {
                            if (i == year) {
                    %>
                    <option value='<%=i%>' selected><%=i%>
                    </option>
                    <% } else {%>
                    <option value='<%=i%>'><%=i%>
                    </option>
                    <% }
                    }
                    %>
                </select>
            </td>

            <td width="100" class="label">
                <bean:message key="querymonthplan.title.formMonthFlag"/>
            </td>
            <td width="300" colspan="3">
                <select name="monthflag" class="select">
                    <option value="0"><bean:message key="querymonthplan.title.sel"/></option>
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
            <!--
                  <td width="100" class="td_label">
                    ����
                  </td>
                  <td width="100">
                    <select name="cycle">
                      <option value="-1"></option>
                      <option value="1">��</option>
                      <option value="2">��</option>
                      <option value="3">����</option>
                      <option value="4">��</option>
                      <option value="5">����</option>
                      <option value="6">����</option>
                      <option value="7">��</option>
                      <option value="0">����</option>
                    </select>
                  </td>
                  -->
        </tr>

        <tr>
            <td width="100" class="label">
                <bean:message key="querymonthplan.title.formConstituteState"/>
            </td>
            <td width="100">
                <select name="constitutestate" class="select">
                    <option value="-1"><bean:message key="querymonthplan.title.sel"/></option>
                    <option value="0">新建</option>
                    <option value="1">通过</option>
                    <option value="2">驳回</option>
                    <option value="3">审批中</option>
                </select>
            </td>

            <td width="100" class="label">
                <bean:message key="querymonthplan.title.formExecuteState"/>
            </td>
            <td width="300" colspan="3">
                <select name="executestate" class="select">
                    <option value="-1"><bean:message key="querymonthplan.title.sel"/></option>
                    <option value="1">归档</option>
                    <option value="4">执行中</option>
                </select>
            </td>
        </tr>
    </table>
    <br>
    <input type="submit" value="<bean:message key="querymonthplan.title.formSubmit"/>" name="B1" class="submit">
</form>

<%@ include file="/common/footer_eoms.jsp" %>

