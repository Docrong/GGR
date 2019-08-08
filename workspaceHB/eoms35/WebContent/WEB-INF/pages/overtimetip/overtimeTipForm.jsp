<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="com.boco.eoms.sheet.overtimetip.util.TimeFilter" %>
<script type="text/javascript">

    var v;
    Ext.onReady(function () {
        v = new eoms.form.Validation({form: 'overtimeTipForm'});
    });

    function limitCheck() {
        var limit = document.all.overtimeLimit.value;
        var i, j, strTemp;
        strTemp = "0123456789";
        var tmplimit = limit;
        for (i = 0; i < limit.length; i++) {
            if (tmplimit.substring(0, 1) == '0' && tmplimit.length > 1) {
                tmplimit = tmplimit.substring(1);
            } else {
                document.all.overtimeLimit.value = tmplimit;
                if (tmplimit == '0') {
                    alert("请在预超时提醒时间中输入一个大于0的整数!");
                    return false;
                } else {
                    j = strTemp.indexOf(tmplimit.charAt(i));
                    if (j == -1) {
                        alert("请在预超时提醒时间中输入一个大于0的整数!");
                        return false;
                    }
                }
            }
        }
        if ('${overtimetip.userId }' == 'system') {
            s = confirm("本次设置的时间值将覆盖系统默认的设置，请确认！");
        } else if ('${userId}' == 'admin') {
            s = confirm("本次设置的时间值将覆盖系统所有其他用户的设置，请确认");
        } else {
            s = confirm("本次设置的时间值将覆盖管理员或您上次的设置，请确认！");
        }
        if (s) {
            return true;
        } else {
            return false;
        }
    }

    function deleteCheck() {
        if ('${userId}' == 'admin') {
            s = confirm("删除该分类的时间值后，将使用系统默认的时间值，请确认！");
        } else {
            s = confirm("删除该分类的时间值后，将使用管理员或系统默认的时间值，请确认！");
        }
        if (s) {
            return true;
        } else {
            return false;
        }
    }
</script>
<title><bean:message bundle="overtimetipRes" key="overtimeTip.title"/></title>


<html:form action="overtimetip.do" method="post" styleId="overtimeTipForm">
    <table class="formTable">
        <caption><bean:message bundle="overtimetipRes" key="overtimeTip.title"/></caption>
        <c:if test="${!empty columnMap}">
            <%
                HashMap columnMap = (HashMap) request.getAttribute("columnMap");
                HashMap htmlMap = (HashMap) request.getAttribute("htmlMap");
                List list = (List) request.getAttribute("columnList");
                HashMap defaultValueMap = (HashMap) request.getAttribute("defaultValue");
                for (int i = 0; i < list.size(); i++) {
                    String key = (String) list.get(i);
                    String columnCnName = (String) columnMap.get(key);
                    TimeFilter filter = (TimeFilter) htmlMap.get(key);
            %>
            <tr>
                <td class="label">
                    <%=(String) columnMap.get(key) %>
                </td>
                <td>
                    <%if (filter.getHtmlType().equals("dict")) {%>
                    <eoms:comboBox
                            name="<%=key %>"
                            id="<%=key %>"
                            defaultValue="<%=(String)defaultValueMap.get(key) %>"
                            initDicId="<%=filter.getDictId() %>"
                            sub="<%=filter.getSub() %>"
                            size="<%=filter.getSize() %>"
                            alt="allowBlank:false"
                            styleClass="select-class"/>
                    <%} %>
                    <%if (filter.getHtmlType().equals("text")) {%>
                    <input
                            type="text"
                            class="text"
                            name="<%=key %>"
                            id="<%=key %>"
                            value="<%=(String)defaultValueMap.get(key) %>"
                            alt="allowBlank:true,maxLength:25"/>
                    <%} %>
                </td>
            </tr>
            <%} %>
        </c:if>
        <tr>
            <td class="label">
                <bean:message bundle="overtimetipRes" key="overtimeTip.overtimeLimit"/>
            </td>
            <td>
                <html:text property="overtimeLimit" styleId="overtimeLimit" styleClass="text medium"
                           alt="allowBlank:false,maxLength:9,vtext:'请填入预超时提醒时间，最多输入9个字符'"
                           value="${overtimetip.overtimeLimit}"/>
            </td>
        </tr>
    </table>
    <input type="hidden" name="flowName" value="${overtimetip.flowName }"/>
    <input type="hidden" name="userId" value="${overtimetip.userId }"/>
    <input type="hidden" name="id" value="${overtimetip.id }"/>
    <c:if test="${isCanSave=='1' }">
        <html:submit styleClass="button" property="method.save" onclick="return limitCheck();">
            <bean:message bundle='sheet' key='button.save'/>
        </html:submit>
    </c:if>
    <c:if test="${isCanDelete=='1' }">
        <html:submit styleClass="button" property="method.delete" onclick="v.passing=true;return deleteCheck();">
            <bean:message bundle='sheet' key='button.delete'/>
        </html:submit>
    </c:if>
    <html:cancel styleClass="button" onclick="v.passing=true">
        <bean:message bundle='sheet' key='button.back'/>
    </html:cancel>
</html:form>
<script type="text/javascript">
    if ('${isCanSave}' != '1') {
        document.all.overtimeLimit.readOnly = 'true';
    }
</script>
<%@ include file="/common/footer_eoms.jsp" %>