<%@ include file="/common/header_tpl_json.jsp"%>
<%-- @see com.boco.eoms.commons.system.dict.model.TawSystemDictType --%>
<json:array name="dicts" items="${list}" var="item">
	<json:object>
		<json:property name="id" value="${item.dictId}" />
		<json:property name="text" value="${item.dictName}" />
		<json:property name="leaf" value="${!empty baseAttr_leaf ? baseAttr_leaf : item.leaf}" />
		<json:property name="nodeType" value="dict" />
		<json:property name="iconCls" value="dict" />
	</json:object>
</json:array>
