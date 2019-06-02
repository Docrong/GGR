<%@ include file="/common/header_tpl_json.jsp"%>
<json:array name="list" items="${mobilesList}" var="item">
	<json:object>
		<json:property name="id" value="${item.mobiles}" />
		<json:property name="leaf" value="${1}" />
		<json:property name="text" value="${item.name}" />
		<json:property name="mobile" value="${item.mobiles}" />
		<json:property name="users" value="${item.users}" />
		<json:property name="remark" value="${item.remark}" />
		<json:property name="qtip" value="${item.mobiles}" />
	</json:object>
</json:array>
