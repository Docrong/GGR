<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<script type="text/javascript">

    function checkData() {
        var thisform = document.forms[0];
        var dealPerformer = document.getElementById('dealPerformer').value;
        var auditPerformer = document.getElementById('auditPerformer').value;
        Ext.Ajax.request({
            url: "${app}/sheet/agentmaintenance/agentmaintenance.do?method=checkData&dealPerformer=" + dealPerformer,
            method: 'POST',
            success: function (res) {
                var data = eoms.JSONDecode(res.responseText);
                var result = data[0].result;
                if (result == 0) {
                    if (confirm("确定保存信息？")) {
                        thisform.action = "${app}/sheet/agentmaintenance/agentmaintenance.do?method=saveUser&dealPerformer=" + dealPerformer + "&auditPerformer=" + auditPerformer;
                        thisform.submit();
                    } else {
                        return false;
                    }
                } else {
                    alert('此用户已经配置了相应的角色，如需改变其下的角色，请进行修改！');
                    return false;
                }
            }
        });
    }
</script>
<style>
    .formTable1 {
        width: 60%;
        border-collapse: collapse;
        font-size: 12px;
    }

    .formTable1 td {
        border: 1px solid #C9DEFA;
        padding: 6px 6px;
        background-color: #FFFFFF;
    }

    .formTable1 tr.header td {
        background: #cae8ea;
        color: #006699;
    }

    .formTable1 td.label {
        vertical-align: middle;
        background-color: #EDF5FD;
    }
</style>
<form method="post">
    <table class="formTable1" align="center">
        <caption>
            <div class="header center">角色定制</div>
        </caption>
        <tr>
            <td class="label" width="40%" align="middle">操作人</td>
            <td width="60%">
                <legend>
                    <bean:message bundle="agentmaintenance" key="role.toOrgName"/>
                    <span id="roleName">:操作人
				</span>
                </legend>
                <div class="x-form-item">
                    <eoms:chooser id="userMade" category="[{id:'dealPerformer',childType:'user',text:'选择'}]" type="user"
                                  config="{returnJSON:true,showLeader:true}"/>
                </div>
            </td>
        </tr>
        <tr>
            <td class="label" width="40%" align="middle">定制人员</td>
            <td width="60%">
                <legend>
                    <bean:message bundle="agentmaintenance" key="role.toOrgName"/>
                    <span id="roleName">:人员或部门
				</span>
                </legend>
                <div class="x-form-item">
                    <eoms:chooser id="subuser"
                                  category="[{id:'auditPerformer',childType:'user,dept',limit:'none',text:'选择'}]"
                                  type="user,dept" config="{returnJSON:true,showLeader:true}"/>
                </div>

            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input class="button" type="button" name="save" value="保存" onclick="return checkData()">
            </td>
        </tr>
    </table>
</form>
<%@ include file="/common/footer_eoms.jsp" %>