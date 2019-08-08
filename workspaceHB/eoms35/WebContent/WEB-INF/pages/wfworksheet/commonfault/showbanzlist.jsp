<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@page import="com.boco.eoms.base.util.StaticMethod" %>
<%@page import="java.util.*;" %>

<script type="text/javascript">
    function queding() {

        var obj2 = document.getElementsByName("subroleid");
        var temp = '';
        for (var i = 0; i < obj2.length; i++) {
            if (obj2[i].checked == true) {
                temp = obj2[i].value;
                break;
            } else {
                temp = 0;
            }
        }
        if (temp == 0) {
            alert("请选择班组后在提交");
            return false;
        } else {
            var subRoleId = temp.split("#")[0];
            var subRoleName = temp.split("#")[1];
            window.opener.document.getElementById("dealPerformer1").value = subRoleId;
            window.opener.document.getElementById("subRoleName").innerHTML = subRoleName;
            window.opener.document.getElementById("mainIsOtherFlag").value = "0";

            //没有申请跨地市选择班组，将标准位置为空
            window.opener.document.getElementById("mainIsOther").value = "";
            window.opener.document.getElementById("mainOtherSubrole").value = "";
            window.opener.document.getElementById("mainThisSubrole").value = "";

            window.close();
        }


    }
</script>

<html:form action="/commonfault.do?method=seleBanz" styleId="theform">
    <table class="formTable">
        <tr>
            <td>
                班组名称
            </td>
            <td>
                <input type="text" class="text" name="subRoleName" id="subRoleName" value="${subRoleName}"/>
                <input type="hidden" name="operateRoleId" id="operateRoleId" value="${operateRoleId}"/>
            </td>
        </tr>
    </table>


    <html:submit styleClass="btn" property="method.save" styleId="method.save">
        查询
    </html:submit>

</html:form>

<table class="formTable">
    <% List banzList = (List) request.getAttribute("banzList");
        if (banzList != null && banzList.size() > 0) {
            for (int i = 0; i < banzList.size(); i++) {
                Map banzMap = (Map) banzList.get(i);
                String id = StaticMethod.nullObject2String(banzMap.get("id"));
                String subrolename = StaticMethod.nullObject2String(banzMap.get("subrolename"));
    %>
    <input type="radio" name="subroleid" value="<%=id%>#<%=subrolename%>"/><%=subrolename %></br>
    <%} %>
    <% }%>

    <html:button styleClass="btn" property="method.save" styleId="method.save" onclick="queding()">
        确定
    </html:button>

</table>


<%@ include file="/common/footer_eoms.jsp" %>