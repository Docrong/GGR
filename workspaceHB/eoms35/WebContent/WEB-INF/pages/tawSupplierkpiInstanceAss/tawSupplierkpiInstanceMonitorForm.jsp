<%@ page language="java" import="java.util.*,com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInstance" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%
    String specialType = request.getAttribute("specialType").toString();
    String serviceType = request.getAttribute("serviceType").toString();
    String month = request.getAttribute("month").toString();
    String assessStatus = request.getAttribute("assessStatus").toString();
    String statusName = request.getAttribute("statusName").toString();
    List supplierTitle = (List) request.getAttribute("supplierTitle");
%>
<script type="text/javascript">
    Ext.onReady(function () {
        colorRows('list-table');
        colorRows('instance-list-table');
        //init Form validation and styles
        //valider({form:'tawSupplierkpiInstanceAssForm',vbtn:'method.save'});
    })

    function send() {
        document.forms[0].opertype.value = "1";
    }

    function pass() {
        document.forms[0].opertype.value = "2";
    }

    function setFlag() {
        document.forms[0].rejectFlag.value = "1";
    }

    function validate() {
        var rejectFlag = document.forms[0].rejectFlag.value;
        if (rejectFlag == 1) {
            alert("${eoms:a2u('请先处理被驳回的填写实例！')}");
            return false;
        } else {
            return true;
        }
    }
</script>
<fmt:bundle basename="config/ApplicationResources-supplierkpi">
    <div class="list-title">
            ${eoms:a2u('执行监控')}
    </div>
</fmt:bundle>

<html:form action="saveTawSupplierkpiInstanceAss" method="post" styleId="tawSupplierkpiInstanceAssForm"
           onsubmit="return validate();">
    <html:hidden property="specialType" value="<%=specialType%>"/>
    <html:hidden property="serviceType" value="<%=serviceType%>"/>
    <html:hidden property="opertype" value=""/>
    <P>
            <html:hidden property="rejectFlag" value=""/>
    <div class="list">
        <table cellspacing="0" cellpadding="0" border="0" id="list-table">
            <tr height="30">
                <td width="20%">
                        ${eoms:a2u('服务类型')}
                </td>
                <td width="20%">
                    <B><eoms:id2nameDB id="<%=serviceType%>" beanId="tawSupplierkpiDictDao"/></B>
                </td width="20%">
                <td>
                        ${eoms:a2u('专业类型')}
                </td>
                <td width="20%">
                    <B><eoms:id2nameDB id="<%=specialType%>" beanId="tawSupplierkpiDictDao"/></B>
                </td>
                <td width="10%">
                        ${eoms:a2u('月份')}
                </td>
                <td width="10%">
                    <B><%=month%>
                    </B>
                </td>
            </tr>
            <tr height="30">
                <td>
                        ${eoms:a2u('审核状态')}
                </td>
                <td colspan="5">
                    <B><%=statusName%>
                    </B>
                </td>
            </tr>
            <%if ("1".equals(assessStatus) && supplierTitle.size() > 1) {%>
            <tr height="30">
                <td colspan="6">
                    <input type="submit" class="btn" value="${eoms:a2u('送审')}" onClick="send()"/>
                    <input type="submit" class="btn" value="${eoms:a2u('归档')}" onClick="pass()"/>
                </td>
            </tr>
            <%}%>
        </table>
    </div>
    <p>
    <div class="list-title">
            ${eoms:a2u('已填写实例')}
    </div>
    <div class="list">
        <table cellspacing="0" cellpadding="0" border="0" id="instance-list-table">

            <%if (supplierTitle.size() > 1) {%>
            <tr height="60" class="header">
                <%for (int k = 0; k < supplierTitle.size(); k++) {%>
                <td width="10%">
                    <%=supplierTitle.get(k).toString()%>
                </td>
                <%}%>
            </tr>

            <%
                List rowList = (List) request.getAttribute("rowList");
            %>
            <%
                for (int i = 0; i < rowList.size(); i++) {
                    List contentList = (List) rowList.get(i);
            %>
            <tr class="normal">
                <%
                    for (int j = 0; j < contentList.size(); j++) {
                        TawSupplierkpiInstance instance = (TawSupplierkpiInstance) contentList.get(j);
                %>
                <%if (2 == instance.getFillFlag()) {%>
                <td style="BACKGROUND-COLOR:red">
                    <script language="javascript">
                        setFlag();
                    </script>
                            <%} else {%>
                <td>
                    <%}%>
                    <%if (j > 0 && !"---".equals(instance.getExamineContent()) && "1".equals(assessStatus)) {%>
                    <a href="editInstance.do?method=editInstance&id=<%=instance.getId()%>"><%=instance.getExamineContent()%>
                    </a>
                    <%} else {%>
                    <%if (j == 0) {%>
                    <%=instance.getKpiName()%>
                    <%} else {%>
                    <%=instance.getExamineContent()%>
                    <%
                            }
                        }
                    %>
                </td>
                <%}%>
            </tr>
            <%}%>

            <%} else {%>
            <tr height="60" class="header">
                <td width="10%">
                        ${eoms:a2u('没有供应商定制该专业的评估指标')}
                </td>
            </tr>
            <%}%>

        </table>
    </div>

</html:form>

<%@ include file="/common/footer_eoms.jsp" %>