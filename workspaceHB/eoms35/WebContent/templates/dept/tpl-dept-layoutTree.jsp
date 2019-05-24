<%@ include file="/common/header_tpl_json.jsp"%>
<%-- @see com.boco.eoms.commons.system.dept.model.TawSystemDept --%>
<json:array name="depts" items="${list}" var="item">
	<json:object>
		<json:property name="id" value="${item.deptId}" />
		<json:property name="text" value="${item.deptName}" />
		<json:property name="leaf" value="${item.leaf=='0'?0:1}" />
		<json:property name="nodeType" value="${item.isPartners=='1'?'partner-dept':'dept'}" />
		<json:property name="iconCls" value="${item.isPartners=='1'?'partner-dept':'dept'}" />
		<json:property name="isPartners" value="${item.isPartners}" />
		<json:property name="allowChild" value="${item.isPartners=='1'?false:true}" />
		<json:property name="allowDelete" value="true" />
		<json:property name="parentDeptid" value="${item.parentDeptid}" />
		<json:property name="qtipTitle" value="${item.deptName}" />
		<json:property name="qtip" escapeXml="false">
			${eoms:a2u("部门负责人")}:${item.deptmanager}<br/>
			${eoms:a2u("部门电话")}:${item.deptphone}<br/>
			${eoms:a2u("备注")}:${item.remark}
		</json:property>
	</json:object>
</json:array>