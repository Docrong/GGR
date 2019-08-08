<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Hashtable" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="com.boco.eoms.workplan.util.TawwpStaticVariable" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>

<script language="JavaScript">
    <!--
    function SubmitCheck() {

        frmReg = document.tawwpyearaddform;

        if (frmReg.planname.value == "") {
            alert("<bean:message key="yearadd.title.warnSelectName" />");
            frmReg.planname.focus();
            return false;
        }

        return true;
    }

    function onTitle() {
        if (document.tawwpyearaddform.nettypeid.value == "") {
            alert("<bean:message key="yearadd.title.warnSelectNetType" />")
            return;
        } else {
            var _sHeight = 200;
            var _sWidth = 320;
            var sTop = (window.screen.availHeight - _sHeight) / 2;
            var sLeft = (window.screen.availWidth - _sWidth) / 2;
            var sFeatures = "dialogHeight: " + _sHeight + "px; dialogWidth: " + _sWidth + "px; dialogTop: " + sTop + "; dialogLeft: " + sLeft + "; center: Yes; scroll:Yes;help: No; resizable: No; status: No;";
            window.showModalDialog('../tawwpyear/titleindexbynettype.do?nettypeid=' + document.tawwpyearaddform.nettypeid.value, window, sFeatures);
        }
    }

    function onNet() {
        if (window.tawwpyearaddform.nettypeid.value == "") {
            alert("<bean:message key="yearadd.title.warnSelectNetType" />")
            return;
        } else {
            var _sHeight = 300;
            var _sWidth = 400;
            var sTop = (window.screen.availHeight - _sHeight) / 2;
            var sLeft = (window.screen.availWidth - _sWidth) / 2;
            var sFeatures = "dialogHeight: " + _sHeight + "px; dialogWidth: " + _sWidth + "px; dialogTop: " + sTop + "; dialogLeft: " + sLeft + "; center: Yes; scroll:No;help: No; resizable: No; status: No;";

            window.showModalDialog('../tawwpyear/netindexbynettype.do?deptid=' + window.tawwpyearaddform.deptid.value + '&nettypeid=' + window.tawwpyearaddform.nettypeid.value, window, sFeatures);
        }
    }

    //-->
    <!--
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
        document.tawwpyearaddform.nettypeid.length = 0;
        var locationid = locationid;
        var i;

        document.tawwpyearaddform.nettypeid.options[0] = new Option('<bean:message key="yearadd.title.selNetType" />', '');

        for (i = 0; i < onecount; i++) {
            if (subcat[i][1] == locationid) {
                document.tawwpyearaddform.nettypeid.options[document.tawwpyearaddform.nettypeid.length] = new Option(subcat[i][0], subcat[i][2]);
            }
        }
    }

    //-->
</script>
<script type="text/javascript">
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'tawwpyearaddform'});
    });
</script>
<input type="hidden" name="path" id="path" value="/EOMS_J2EE">
<input type="hidden" name="sdomids" id="sdomids" value="">
<%
    TawSystemSessionForm saveSessionBeanForm = (TawSystemSessionForm) request.
            getSession().getAttribute(
            "sessionform");

    String deptId = saveSessionBeanForm.getDeptid();
    String deptName = saveSessionBeanForm.getDeptname();
    String userId = saveSessionBeanForm.getUserid();
    String userName = saveSessionBeanForm.getUsername();
%>
<form name="tawwpyearaddform" method="post" action="../tawwpyear/yearsave.do" onsubmit='return SubmitCheck()'>

    <table class="formTable">
        <caption><bean:message key="yearadd.title.formTitle"/></caption>
        <tr>
            <td class="label">
                <bean:message key="yearadd.title.formSysType"/>
            </td>
            <td width="400">
                <select size=1 name="systype" class="select"
                        onChange="changelocation(document.tawwpyearaddform.systype.options[document.tawwpyearaddform.systype.selectedIndex].value)">
                    <option value="-1" selected><bean:message key="yearadd.title.formSelSys"/></option>
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
            <td class="label">
                <bean:message key="yearadd.title.formNetType"/>
            </td>
            <td width="400">
                <select size="1" name="nettypeid" style="width:200">
                    <option value="" selected><bean:message key="yearadd.title.formSelNet"/></option>
                </select>
            </td>
        </tr>
        <tr>
            <td width="100" class="label">
                <bean:message key="yearadd.title.formPlanName"/>
            </td>
            <td width="500">
                <input type="text" name="planname" size="30" styleClass="text">
                <input type="hidden" name="planid" size="30" styleClass="clstext" readonly="true">
                <input type="button" value="<bean:message key="yearadd.title.btnModelList" />" name="B1" class="button"
                       Onclick="javascript:onTitle();">
                <input type="hidden" name="modelPlanId" size="30" styleClass="clstext">
            </td>
        </tr>
        <tr>
            <td width="100" class="label">
                <bean:message key="yearadd.title.formDeptName"/>
            </td>
            <td width="500">
                <input type="text" name="deptname" size="30" styleClass="text" readonly="true" value="<%=deptName%>">
                <input type="hidden" name="deptid" size="30" styleClass="clstext" readonly="true" value="<%=deptId%>">

            </td>
        </tr>

        <tr>
            <td width="100" class="label">
                智能巡检接口
            </td>
            <td width="500">
                <input type="text" name="command" size="30" styleClass="text">


            </td>
        </tr>
        <tr>
            <td width="100" class="label">
                <bean:message key="yearadd.title.formCruser"/>
            </td>
            <td width="500">
                <input type="text" name="cruser" size="30" styleClass="text" readonly="true" value="<%=userName%>">
                <input type="hidden" name="userid" size="30" styleClass="clstext" readonly="true" value="<%=userId%>">
            </td>
        </tr>

        <tr>
            <td width="100" class="label">
                <bean:message key="yearadd.title.formYear"/>
            </td>
            <td width="500">
                <select name="yearflag" class="select">
                    <%
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
                        Date curtime = new java.util.Date();
                        int crrtYear = Integer.parseInt(formatter.format(curtime));
                        for (int i = crrtYear; i < (crrtYear + 2); i++) {
                    %>
                    <option value="<%=i%>"><%=i%>
                    </option>
                    <%
                        }
                    %>
                </select>
            </td>
        </tr>
        <tr class="label"
        ">
        <td width="100" class="label">
            <bean:message key="yearaddnomodel.title.formContent"/>
        </td>
        <td width="500">

            <textarea name="content" class="textarea max" alt="vtext:'作业计划内容字数不能超过50个汉字',maxLength:100"></textarea>
        </td>
        </tr>
        <tr>
            <td width="100" class="label">
                <bean:message key="yearaddnomodel.title.formRemark"/>
            </td>
            <td width="500">
                <textarea name="remark" class="textarea max" alt="vtext:'备注字数不能超过50个汉字',maxLength:100"></textarea>
            </td>
        </tr>
    </table>
    <br>
    <input type="submit" value="<bean:message key="yearadd.title.btnSubmit" />" name="B1" class="submit">
    <input type="button" value="<bean:message key="yearadd.title.btnBack" />"
           onclick="javascript:window.history.back();" class="button">


</form>
<%@ include file="/common/footer_eoms.jsp" %>
