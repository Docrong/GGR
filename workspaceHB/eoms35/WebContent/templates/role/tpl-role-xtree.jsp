<%@ include file="/common/header_tpl_json.jsp"%>
<%-- @see com.boco.eoms.commons.system.role.model.TawSystemRole --%>
<json:array name="roles" items="${list}" var="item">
  <json:object>
    <json:property name="id" value="${item.roleId}" />
    <json:property name="text" value="${item.roleName}" />
    <json:property name="nodeType" value="role" />
    <json:property name="iconCls" value="role" />
    <json:property name="leaf" value="${defaultLeaf !=null ? defaultLeaf : item.leaf}" />
  </json:object>
</json:array>