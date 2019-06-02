<%@ page language="java" import="com.boco.eoms.base.util.StaticMethod" %> 
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%
String oper = StaticMethod.null2String(request.getAttribute("oper").toString());;
String parentDictId = StaticMethod.null2String(request.getAttribute("parentDictId").toString());
%>
<script type="text/javascript">
Ext.onReady(function(){
	v = new eoms.form.Validation({form:'tawSupplierkpiItemTypeForm'});
})
</script>
<fmt:bundle basename="config/ApplicationResources-supplierkpi">
<div class="list-title">
<fmt:message key="tawSupplierkpiItemTypeDetail.heading"/></content>
</div>
</fmt:bundle>
<html:form action="saveTawSupplierkpiItemType" method="post" styleId="tawSupplierkpiItemTypeForm"> 
<html:hidden property="parentDictId" value="<%=parentDictId%>" />
<html:hidden property="oper" value="<%=oper%>" />

<table>
<tr height="30">
	<td width="50">
		<eoms:label styleClass="desc" key="tawSupplierkpiItemTypeForm.dictName"/>
	</td>
	<td width="100">
		<html:text property="dictName" styleId="dictName" styleClass="text medium"
		  alt="allowBlank:false,vtext:'${eoms:a2u('请输入项目类型名称')}'" />
	</td>
</tr>
<tr height="60">
	<td>
		<eoms:label styleClass="desc" key="tawSupplierkpiItemTypeForm.dictRemark"/>
	</td>
	<td>
		<html:textarea property="dictRemark" styleId="dictRemark" styleClass="textarea medium"/>
	</td>
</tr>
<tr height="30">
	<td colspan="2">
		<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
	</td>
</tr>
</table>
</html:form>
<%@ include file="/common/footer_eoms.jsp"%>