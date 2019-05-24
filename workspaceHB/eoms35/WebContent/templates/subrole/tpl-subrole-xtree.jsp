<%@ include file="/common/header_tpl_json.jsp"%>
<%-- @see com.boco.eoms.commons.system.role.model.TawSystemSubRole --%>
<json:array name="roles" items="${list}" var="item">
	<json:object>
		<json:property name="id" value="${item.id}" />
		<json:property name="text" value="${item.subRoleName}" />
		<json:property name="leaf" value="${!empty baseAttr_leaf ? baseAttr_leaf : item.leaf}" />
		<json:property name="nodeType" value="subrole" />
		<json:property name="iconCls" value="subrole" />
	</json:object>
</json:array>
