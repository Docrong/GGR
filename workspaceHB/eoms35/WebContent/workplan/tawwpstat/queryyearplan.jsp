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

        frmReg = document.forms[0];
        if (frmReg.systype.value == '0') {
            alert("<bean:message key="queryyearplan.title.warnSelectSys" />");
            return false;
        }

        if (frmReg.nettype.value == '0') {
            alert("<bean:message key="queryyearplan.title.warnSelectNet" />");
            return false;
        }

        return true;
    }

    function onModel() {
        if (window.tawwpqueryyearplan.nettype.value == "0") {
            alert("<bean:message key="queryyearplan.title.warnSelectNet" />")
            return;
        } else {
            var _sHeight = 330;
            var _sWidth = 400;
            var sTop = (window.screen.availHeight - _sHeight) / 2;
            var sLeft = (window.screen.availWidth - _sWidth) / 2;
            var sFeatures = "dialogHeight: " + _sHeight + "px; dialogWidth: " + _sWidth + "px; dialogTop: " + sTop + "; dialogLeft: " + sLeft + "; center: Yes; scroll:No;help: No; resizable: No; status: No;";

            window.showModalDialog('../tawwpmodel/modelselect.do?refresh=<%=TawwpUtil.getCurrentDateTime("yyyyMMddhhssmm")%>&nettype=' + window.tawwpqueryyearplan.nettype.value, window, sFeatures);
        }
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
        document.forms[0].nettype.length = 0;
        var locationid = locationid;
        var i;

        document.forms[0].nettype.options[0] = new Option('<bean:message key='queryyearplan.title.formSelectNet' />', '0');

        for (i = 0; i < onecount; i++) {
            if (subcat[i][1] == locationid) {
                document.forms[0].nettype.options[document.forms[0].nettype.length] = new Option(subcat[i][0], subcat[i][2]);
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

<form name="tawwpqueryyearplan" method="post" action="listyearplan.do" onsubmit='return SubmitCheck()'>

    <table class="formTable">
        <caption><bean:message key="queryyearplan.title.formTitle"/></caption>
        <tr>
            <td width="100" class="label">
                <bean:message key="queryyearplan.title.formSystem"/>
            </td>
            <td width="500" colspan="5">
                <select size=1 name="systype" class="select"
                        onChange="changelocation(document.tawwpqueryyearplan.systype.options[document.tawwpqueryyearplan.systype.selectedIndex].value)">
                    <option value="0" selected><bean:message key="queryyearplan.title.formSelectSys"/></option>
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
                <bean:message key="queryyearplan.title.formNetType"/>
            </td>
            <td width="500" colspan="5">
                <select size="1" name="nettype" class="select">
                    <option value="0" selected><bean:message key="queryyearplan.title.formSelectNet"/></option>
                </select>
            </td>
        </tr>

        <tr>
            <td width="100" class="label">
                <bean:message key="queryyearplan.title.formModel"/>
            </td>
            <td width="500" colspan="5">

                <input type="text" name="modelnamelist" size="30" readonly="true" value="" class="text">
                <input type="hidden" name="modellist" size="30" styleClass="clstext" readonly="true" value="">
                <input type="button" value="<bean:message key="queryyearplan.title.formSelectModel" />" name="B1"
                       class="button" Onclick="javascript:onModel();">
                <%--<html:button property="B1" onclick="javascript:onModel();" value="queryyearplan.title.formSelectModel"/>--%>
            </td>
        </tr>

        <tr>
            <td width="100" class="label">
                <bean:message key="queryyearplan.title.formDept"/>
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
                <bean:message key="queryyearplan.title.formYear"/>
            </td>
            <td width="100">
                <select name="yearflag" class="select">
                    <%
                        String yyyy = TawwpUtil.getCurrentDateTime("yyyy");
                        int year = StaticMethod.null2int(yyyy);
                        for (int i = year; i > year - 5; i--) {
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
                <bean:message key="queryyearplan.title.formState"/>
            </td>
            <td width="300" colspan="3" class="select">
                <select name="state" class="select">
                    <option value="-1">-- 请选择 --</option>
                    <option value="0">新建</option>
                    <option value="1">通过</option>
                    <option value="2">驳回</option>
                    <option value="3">审批中</option>
                </select>
            </td>
        </tr>
    </table>
    <br>
    <input type="submit" value="<bean:message key="queryyearplan.title.formSubmit" />" name="B1" class="submit">
</form>

<%@ include file="/common/footer_eoms.jsp" %>

