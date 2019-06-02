<%@ include file="/common/header_tpl_json.jsp"%>
<%-- @see com.boco.eoms.commons.system.priv.model.TawSystemPrivOperation --%>
<json:array name="roles" items="${list}" var="item">
	<json:object>
		<json:property name="id" value="${item.code}" />
		<json:property name="text" value="${item.name}" />
		<json:property name="leaf" value="${item.isApp=='1'?1:0}" />
		<json:property name="href" value="${item.url}" />
		<json:property name="nodeType" value="operation" />
		<json:property name="iconCls" value="menu" />
	</json:object>
</json:array>
