<%@ include file="/common/header_tpl_json.jsp"%>
<%-- @see com.boco.eoms.commons.system.dept.model.TawSystemDept --%>
<json:array name="depts" items="${list}" var="item">
	<json:object>
		<json:property name="id" value="${item.deptId}" />
		<json:property name="text" value="${item.deptName}" />
		<json:property name="leaf" value="${item.leaf=='0'?0:1}" />
		<json:property name="nodeType" value="dept" />
		<json:property name="iconCls" value="${item.isPartners=='1'?'partner-dept':'dept'}" />
		<json:property name="isPartners" value="${item.isPartners}" />
	</json:object>
</json:array>