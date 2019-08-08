<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ include file="/common/taglibs.jsp" %>
<%@ include file="/common/header_eoms_form.jsp" %>
<script type="text/javascript">
    Ext.onReady(function () {
        colorRows('list-table');
    })

    function add() {
        location.href = "./userdefinegroup.do?method=showUserdefineGroupPage";
    }

    function openSheet(url) {
        if (parent.frames['portal-north']) {
            parent.frames['portal-north'].location.href = url;
        } else {
            location.href = url;
        }
    }
</script>

<bean:define id="url" value="userdefinegroup.do"/>
<input type="button" value="新增" onclick="add()" class="btn">
<display:table name="taskList" cellspacing="0" cellpadding="0"
               id="taskList" pagesize="${pageSize}" class="table businessdesignMain"
               export="false" requestURI="userdefinegroup.do"
               sort="list" size="total" partialList="true">

    <display:column sortable="true" headerClass="sortable" title="模块名" sortName="flowName">
        <a href="./userdefinegroup.do?method=edit&type=open&id=${taskList.id}">
                ${taskList.flowName}
        </a>
    </display:column>
    <display:column property="dealRoleName" sortable="true" headerClass="sortable" title="常用角色" sortName="dealRoleId"/>
    <display:column property="dealUserName" sortable="true" headerClass="sortable" title="常用人员" sortName="dealUserId"/>
    <display:column property="dealDeptName" sortable="true" headerClass="sortable" title="常用部门" sortName="dealDeptId"/>
</display:table>
<%@ include file="/common/footer_eoms.jsp" %>