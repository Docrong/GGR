<%@ include file="/common/header_tpl_json.jsp"%>
<%-- @see com.boco.eoms.commons.system.cptroom.model.TawSystemCptroom --%>
<json:array name="cptrooms" items="${list}" var="item">
	<json:object>
		<json:property name="id" value="${item.id}" />
		<json:property name="text" value="${item.roomname}" />
		<json:property name="leaf" value="${item.leaf=='0'?0:1}" />
		<json:property name="nodeType" value="cptroom" />
		<json:property name="iconCls" value="cptroom" />
	</json:object>
</json:array>
