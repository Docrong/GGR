<jsp:directive.page import="java.util.ArrayList,java.util.Iterator"/>
<jsp:directive.page
        import="com.boco.eoms.extra.supplierkpi.model.TawSupplierkpiInstance"/>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms.jsp" %>
<%@ include file="/common/extlibs.jsp" %>
<script type="text/javascript">
    Ext.onReady(function () {
        colorRows('list-table');
    })

    function getAllKpiItemName(el) {
        var tmpels = document.getElementsByName(el.name);
        var checkStatus = '';
        var checkName = '';
        for (var i = 0; i < tmpels.length; i++) {
            if (tmpels[i].checked == true) {
                checkStatus = checkStatus + tmpels[i].value;
                checkStatus = checkStatus + '@@';
            }
        }

        document.getElementById("kpiItemName_1").value = checkStatus;
    }

</script>
<body>
<div class="list">
    <table cellspacing="0" cellpadding="0" border="0">
        <table>
            <tr class="header" align="center">
                <td width="5%">
                    <label>
                        ${eoms:a2u('请选择')}
                    </label>
                </td>
                <td width="8%">
                    <bean:message key="tawSupplierkpiInstanceList.supplier"/>
                </td>

                <td width="8%">
                    <bean:message key="tawSupplierkpiInstanceList.kpiName"/>
                </td>
                <td width="25%">
                    <bean:message key="tawSupplierkpiInstanceList.assessContent"/>
                </td>
                <td width="5%">
                    <bean:message key="tawSupplierkpiInstanceList.unit"/>
                </td>
            </tr>

            <logic:notEmpty name="viewList">
            <logic:iterate id="aal" name="viewList"
                           indexId="aalid">
            <bean:define id="unit" name="aal" property="unit"
                         type="java.lang.String"/>
            <bean:define id="kpiItemId" name="aal" property="kpiItemId"
                         type="java.lang.String"/>
            <bean:define id="manufacturerId" name="aal" property="manufacturerId"
                         type="java.lang.String"/>
            <tr class="normal">
                <td>
                    <input type=checkbox name=itemName value="<%=kpiItemId%>,<%=manufacturerId%>"
                           onClick="getAllKpiItemName(this);"></input>
                </td>
                <td>
                    <bean:write name="aal" property="manufacturerName"/>
                </td>
                <td>
                    <bean:write name="aal" property="kpiName"/>
                </td>
                <td>
                    <bean:write name="aal" property="assessContent"/>
                </td>
                <td>
                    <eoms:id2nameDB id="<%=unit%>" beanId="ItawSystemDictTypeDao"/>
                </td>
                </logic:iterate>
                </logic:notEmpty>
                <input type="hidden" id="kpiItemName_1" name="kpiItemId" value=""/>
            <tr align="right">
                <td colspan="5">
                    <input type="button" class="btn" value="${eoms:a2u('保存售前售中指标')}" onclick="toSetFormData();"/>
                    <input type="button" class="btn" value="${eoms:a2u('返回')}"
                           onclick="javascript:window.history.back();"/>
                </td>
            </tr>
        </table>
    </table>
    <%
        String serviceType = String.valueOf(request.getAttribute("serviceType"));
        String specialType = String.valueOf(request.getAttribute("specialType"));
    %>
    <script>
        function toSetFormData() {
            var kpiItemId = document.getElementById("kpiItemName_1").value;
            alert(kpiItemId);
            var url = "<c:url value="/supplierkpi/saveTawSupplierkpiInstance.do?method=saveSell&kpiItemId="/>";
            url += kpiItemId;
            url += "&specialType=" + "<%=specialType%>";
            url += "&serviceType=" + "<%=serviceType%>";
            alert(url);
            location.href = url;
        }
    </script>

</div>
</body>

<%@ include file="/common/footer_eoms.jsp" %>
				