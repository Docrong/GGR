<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<script type="text/javascript">
    Ext.onReady(function () {
        colorRows('list-table');
    })
</script>
<%com.boco.eoms.base.webapp.listener.UserCounterListener.getOnlineUsers(request);%>
<div class="list-title">
    ${eoms:a2u('在线用户列表')}
</div>
<div class="list">
    <table cellspacing="0" cellpadding="0" border="0" id="list-table">
        <tr height="30" class="header">
            <td width="20%">
                ${eoms:a2u('用户名')}
            </td>
            <td width="20%">
                ${eoms:a2u('部门')}
            </td>
            <td width="20%">
                ${eoms:a2u('岗位')}
            </td>
            <td width="20%">
                ${eoms:a2u('登录IP')}
            </td>
        </tr>
        <logic:iterate id="usersMap" name="usersMap">
            <tr height="30">
                <td width="20%">
                    <bean:write name="usersMap" property="value.username"/>
                </td>
                <td width="20%">
                    <bean:write name="usersMap" property="value.deptname"/>
                </td>
                <td width="20%">
                    <bean:write name="usersMap" property="value.rolename"/>
                </td>
                <td width="20%">
                    <bean:write name="usersMap" property="value.romteaddr"/>
                </td>
            </tr>
        </logic:iterate>
    </table>
</div>
<br/>
<div align="center">
    <input type="button" name="refresh" value="${eoms:a2u('刷新')}" class="btn" onclick="window.location.reload()"/>
    <input type="button" name="close" value="${eoms:a2u('关闭')}" class="btn" onclick="window.close()"/>
</div>
<%@ include file="/common/footer_eoms.jsp" %>