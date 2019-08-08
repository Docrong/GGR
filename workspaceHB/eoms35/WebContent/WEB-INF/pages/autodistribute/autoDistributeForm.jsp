<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<script type="text/javascript">
    var v;
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'autoDistributeForm'});
    });

    function ifThreshold(autoTypeValue) {
        var v1 = eoms.form;
        if (autoTypeValue == '101010603') {
            v1.enableArea('thresholdtd');
        } else {
            v1.disableArea('thresholdtd', true);
        }
    }


    function makeThreshold() {
        var thresholdvalue = document.all.threshold.value;
        var autoTypeValue = document.all.autoType.value;
        if (autoTypeValue != '101010603') {
            return true;
        }
        var strTemp = "0123456789";
        var i, j, k;
        var tmplimit = thresholdvalue;
        var tmplength = tmplimit.length;

        for (k = 0; k < tmplength; k++) {
            if (tmplimit.substring(0, 1) == '0' && tmplimit.length > 1) {
                tmplimit = tmplimit.substring(1);
            } else {
                j = strTemp.indexOf(tmplimit.charAt(k));
                if (j == -1) {
                    alert("请在阀值中输入一个正整数!");
                    return false;
                }
            }
            document.all.threshold.value = tmplimit;
        }
        if (tmplength == 0) {
            alert("请在阀值中输入一个正整数!");
        }
        return true;
    }
</script>
<title><bean:message bundle="autodistributeRes" key="autoDistribute.title"/></title>


<html:form action="autodistribute.do" method="post" styleId="autoDistributeForm">
    <table class="formTable">
        <caption><bean:message bundle="autodistributeRes" key="autoDistribute.title"/></caption>
        <tr>
            <td class="label">
                <bean:message bundle="autodistributeRes" key="autoDistribute.flowName"/>
            </td>
            <td>
                    ${AutoDistribute.flowName }
                <input type="hidden" id="flowName" name="flowName" value="${AutoDistribute.flowName }"/>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message bundle="autodistributeRes" key="autoDistribute.autoType"/>
            </td>
            <td>
                <eoms:comboBox
                        name="autoType"
                        id="autoType"
                        initDicId="1010106"
                        defaultValue="${AutoDistribute.autoType}"
                        alt="allowBlank:false"
                        onchange="ifThreshold(this.value);"/>
            </td>
        </tr>
        <tr>
            <td class="label">
                <bean:message bundle="autodistributeRes" key="autoDistribute.roleName"/>
            </td>
            <td class="content">
                <input type="hidden" id="roleId" name="roleId" value="${AutoDistribute.roleId }"/>
                    ${roleName }
            </td>
        </tr>
        <tr></tr>
        <tr id="thresholdtd" style="display:none">
            <td class="label">
                <bean:message bundle="autodistributeRes" key="threshold.threshold"/>
            </td>
            <td>
                <input type="text" class="text" name="threshold"
                       id="threshold" value="${AutoDistribute.threshold}"
                       alt="allowBlank:false,maxLength:9,vtext:'请填入阀值，最多输入9个字符'"/>
            </td>
        </tr>
        <input type="hidden" id="id" name="autoDistributeId" value="${AutoDistribute.id }"/>
    </table>
    <html:submit styleClass="button" property="method.save" onclick="return makeThreshold();">
        <bean:message bundle='sheet' key='button.save'/>
    </html:submit>
    <html:cancel styleClass="button" onclick="v.passing=true">
        <bean:message bundle='sheet' key='button.back'/>
    </html:cancel>
</html:form>
<script type="text/javascript">
    ifThreshold(document.getElementById('autoType').value);
</script>
<%@ include file="/common/footer_eoms.jsp" %>