<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="com.boco.eoms.sheet.agentmaintenance.model.UserMade" %>
<%
    List list = (List) request.getAttribute("list");
%>
<form method="post">
    <table class="formTable">
        <caption>角色定制列表</caption>
        <tr>
            <td align="center" style="background-color: #EDF5FD">角色</td>
            <td align="center" style="background-color: #EDF5FD">定制人员</td>
            <td align="center" style="background-color: #EDF5FD">定制部门</td>
            <td align="center" style="background-color: #EDF5FD">操作</td>
        </tr>
        <%
            UserMade userMade = new UserMade();
            for (int i = 0; i < list.size(); i++) {
                userMade = (UserMade) list.get(i);
        %>
        <tr>
            <td align="center"><eoms:id2nameDB id="<%=userMade.getUser_id()%>" beanId="tawSystemUserDao"/></td>
            <td align="center">
                <%
                    String subUser = userMade.getUsertoRole();
                    if (subUser != null && !"".equals(subUser)) {
                        String[] user = subUser.split(",");

                        for (int j = 0; j < user.length; j++) {
                %>
                <eoms:id2nameDB id="<%=user[j]%>" beanId="tawSystemUserDao"/>
                <%if (j < user.length - 1) { %>
                ,
                <%} %>
                <%
                        }
                    }
                %>
            </td>
            <td align="center">
                <%
                    String subDept = userMade.getUsertoDept();
                    if (subDept != null && !"".equals(subDept)) {
                        String[] dpet = subDept.split(",");

                        for (int k = 0; k < dpet.length; k++) {
                %>
                <eoms:id2nameDB id="<%=dpet[k]%>" beanId="tawSystemDeptDao"/>
                <%if (k < dpet.length - 1) { %>
                ,
                <%} %>
                <%
                        }
                    }
                %>
            </td>
            <td align="center"><input type="button" name="modify" id="modify" value="修改" class="button"
                                      onclick="toModify('<%=userMade.getId()%>')">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" name="delete" id="delete" value="删除" class="button"
                       onclick="return deleteData('<%=userMade.getId()%>')">
            </td>
        </tr>
        <%} %>
    </table>
    <br>
    <input type="button" value="定制角色" id="made" name="made" onclick="toNew()" class="button">
</form>
<script type="text/javascript">
    function toNew() {
        var thisform = document.forms[0];
        thisform.action = "${app}/sheet/agentmaintenance/agentmaintenance.do?method=showUserMade";
        thisform.submit();
    }

    function toModify(id) {
        var thisform = document.forms[0];
        thisform.action = "${app}/sheet/agentmaintenance/agentmaintenance.do?method=showModify&id=" + id;
        thisform.submit();
    }

    function deleteData(id) {
        if (confirm('确认删除吗?')) {
            var thisform = document.forms[0];
            thisform.action = "${app}/sheet/agentmaintenance/agentmaintenance.do?method=deleteUser&id=" + id;
            thisform.submit();
        } else {
            return false;
        }
    }
</script>
<%@ include file="/common/footer_eoms.jsp" %>