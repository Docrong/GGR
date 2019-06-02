<%@ include file="/common/header_tpl_json.jsp"%>
<%-- @see com.boco.eoms.commons.system.user.model.TawSystemUser --%>
<json:array name="users" items="${list}" var="item">
	<json:object>
		<json:property name="id" value="${item.userid}" />
		<json:property name="text" value="${item.username}" />
		<json:property name="name" value="${item.username}" />
		<json:property name="leaf" value="1" />
		<json:property name="nodeType" value="user" />
		<json:property name="iconCls" value="user" />
	</json:object>
</json:array>