<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<%@page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm" %>
<%
    long localTimes = com.boco.eoms.base.util.StaticMethod.getLocalTime().getTime();
    TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
    String userId = sessionform.getUserid();
    String deptId = sessionform.getDeptid();
%>
<script type="text/javascript">
    function check() {
        var team = document.getElementsByName("teamRoleId");
        var ids = document.getElementById("id").value;
        var teamValue = '';
        if (team.length > 0) {
            for (var i = 0; i < team.length; i++) {
                teamValue = team[i].value;
            }
        }
        if (teamValue == '') {
            alert("维护班组或运维中心不能为空");
            return false;
        }

        /*	Ext.Ajax.request({
                        url : "netownership.do?method=xeditandsave",
                        method : 'POST',
                        params:{id:ids},
                        success : function(x){
                            var data = eoms.JSONDecode(x.responseText);
                            Ext.each(data,function(d){
                                alert(d.alarmMsg);
                            });
                        }
                    });
                    */
    }
</script>
<html:form action="/netownership.do" style="theform">
    <input type="hidden" name="id" id="id" value="${main.id }"/>
    <table id="sheet" class="formTable">
        <tr>
            <td class="label">网元名称</td>
            <td class="content">${main.netName }</td>
            <td class="label">综资网元名称</td>
            <td class="content">${main.netNameByEdis }</td>
        </tr>
        <tr>
            <td class="label">网元类型</td>
            <td class="content">${main.netType }</td>
            <td class="label">地市</td>
            <td class="content">${main.city }</td>
        </tr>
        <tr>
            <td class="label">区县</td>
            <td class="content">${main.county }</td>
            <td class="label">入库时间</td>
            <td class="content">${main.saveTime }</td>
        </tr>
        <tr>
            <td class="label">创建人</td>
            <td class="content">
                <eoms:id2nameDB id="${main.createUserId }" beanId="tawSystemUserDao"/>
            </td>
            <td class="label">创建人所属部门</td>
            <td class="content">
                <eoms:id2nameDB id="${main.createDeptId }" beanId="tawSystemDeptDao"/>
            </td>
        </tr>
        <tr>
            <td class="label">是否自动移交</td>
            <td class="content" colspan="3">
                <select name="ifAutotran" id="ifAutotran">
                    <option value="0">否</option>
                    <option value="1">是</option>
                </select>
            </td>
        </tr>
        <c:if test="${ifRight!=null && main.teamRoleId!=null}">
            <tr>
                <td class="label">维护班组</td>
                <td class="content" colspan="3">
                    <eoms:chooser id="test22" type="role" roleId="8005106" flowId="051"
                                  config="{returnJSON:true,showLeader:true}"
                                  category="[{id:'teamRoleId',text:'选择',nodeType:'subrole',vtext:'请选择修改对象'}]"
                                  data="[{id:'${main.teamRoleId}',nodeType:'subrole',categoryId:'teamRoleId'}]"/>
                </td>
            </tr>
        </c:if>
        <c:if test="${ifRight!=null && main.teamRoleId==null}">
            <tr>
                <td class="label">维护班组</td>
                <td class="content" colspan="3">
                    <eoms:chooser id="test22" type="role" roleId="8005106" flowId="051"
                                  config="{returnJSON:true,showLeader:true}"
                                  category="[{id:'teamRoleId',text:'选择',nodeType:'subrole',vtext:'请选择修改对象'}]"/>
                </td>
            </tr>
        </c:if>
        <c:if test="${ifRight==null }">
            <tr>
                <td class="label">维护班组</td>
                <td class="content" colspan="3">
                    <eoms:id2nameDB id="${main.teamRoleId }" beanId="tawSystemSubRoleDao"/>
                </td>

            </tr>
        </c:if>
        <tr>
            <td class="label">抄送对象</td>
            <td class="content" colspan="3">
                <c:if test="${main.ccObject!=null}">
                    <eoms:chooser id="test2" type="role" roleId="8005106" flowId="051"
                                  config="{returnJSON:true,showLeader:true}"
                                  category="[{id:'ccObject',text:'选择',nodeType:'subrole',vtext:'请选择修改对象'}]"
                                  data="[{id:'${main.ccObject}',nodeType:'subrole',categoryId:'ccObject'}]"/>
                </c:if>
                <c:if test="${main.ccObject==null}">
                    <eoms:chooser id="test3" type="role" roleId="8005106" flowId="051"
                                  config="{returnJSON:true,showLeader:true}"
                                  category="[{id:'ccObject',text:'选择',nodeType:'subrole',vtext:'请选择修改对象'}]"/>
                </c:if>
            </td>
        </tr>
    </table>
    <c:if test="${ifRight!=null }">
        <input type="submit" name="method.xeditandsave" value="保存" id="xeditandsave" class="btn"
               onclick="return check();"/>
    </c:if>
</html:form>
<script type="text/javascript">
    var ifAutotran = '${main.ifAutotran}';
    if (ifAutotran != null && ifAutotran != '') {
        document.getElementById("ifAutotran").options[ifAutotran].selected = "selected";
    }
</script>	
		