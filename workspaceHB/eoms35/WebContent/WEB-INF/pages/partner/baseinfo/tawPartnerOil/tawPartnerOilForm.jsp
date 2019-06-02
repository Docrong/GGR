<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page language="java" import="java.util.*,com.boco.eoms.partner.baseinfo.webapp.form.*;"%>
<script language="JavaScript" type="text/JavaScript" src="${app}/scripts/module/partner/ajax.js">
</script>
<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawPartnerOilForm'});
});

</script>

<html:form action="/tawPartnerOils.do?method=save" styleId="tawPartnerOilForm" method="post"> 

<fmt:bundle basename="config/applicationResources-partner-baseinfo">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="tawPartnerOil.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="tawPartnerOil.oil_number" />&nbsp;*
		</td>
		<td class="content">
			<html:text property="oil_number" styleId="oil_number"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${tawPartnerOilForm.oil_number}" />${fallure }
		</td>
		<td class="label">
			<fmt:message key="tawPartnerOil.factory" />&nbsp;*
		</td>
		<td class="content">
		<eoms:comboBox name="factory" id="factory" initDicId="11208"
					defaultValue='${tawPartnerOilForm.factory}' alt="allowBlank:false,vtext:''" />
			<%--<html:text property="factory" styleId="factory"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${tawPartnerOilForm.factory}" />
		--%></td>
	</tr>

	<tr>
	<td class="label">
			<fmt:message key="tawPartnerOil.area_id" />&nbsp;*
		</td>
		<td class="content">
		<html:select property="area_id"  styleId="area_id" size="1" onchange="changeDep();">
					 <%List listId =(List) request.getAttribute("listId");
					List listName = (List)request.getAttribute("listName");
					TawPartnerOilForm fm = (TawPartnerOilForm)request.getAttribute("tawPartnerOilForm");
					String nodeId = fm.getArea_id();
					for(int i=0;i<listId.size();i++){
					if(nodeId.equals(listId.get(i))){
					 %>
					 <option value="<%=listId.get(i) %>" selected>
					<%=listName.get(i) %>
						</option>
						 <%}else{ %>
						  <option value="<%=listId.get(i) %>">
					<%=listName.get(i) %>
						</option>
						<%}} %>
						 </html:select>
			<%--<html:text property="area_id" styleId="area_id"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${tawPartnerOilForm.area_id}" />
		--%></td>
		<td class="label">
			<fmt:message key="tawPartnerOil.dept_id" />&nbsp;*
		</td>
		<td class="content">
				 <html:select property="dept_id" styleId="dept_id" size="1">
				 </html:select>
			<%--<html:text property="dept_id" styleId="dept_id"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${tawPartnerOilForm.dept_id}" />
		--%></td>
		
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="tawPartnerOil.type" />&nbsp;*
		</td>
		<td class="content">
		<eoms:comboBox name="type" id="type" initDicId="11209"
					defaultValue='${tawPartnerOilForm.type}' alt="allowBlank:false,vtext:''" />
			<%--<html:text property="type" styleId="type"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${tawPartnerOilForm.type}" />
		--%></td>
		<td class="label">
			<fmt:message key="tawPartnerOil.power" />&nbsp;*
		</td>
		<td class="content">
		<eoms:comboBox name="power" id="power" initDicId="11207"
					defaultValue='${tawPartnerOilForm.power}' alt="allowBlank:false,vtext:''" />
			<%--<html:text property="power" styleId="power"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${tawPartnerOilForm.power}" />
		--%></td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="tawPartnerOil.kind" />&nbsp;*
		</td>
		<td class="content">
		<eoms:comboBox name="kind" id="kind" initDicId="11206"
					defaultValue='${tawPartnerOilForm.kind}' alt="allowBlank:false,vtext:''" />
			<%--<html:text property="kind" styleId="kind"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${tawPartnerOilForm.kind}" />
		--%></td>
		<td class="label">
			<fmt:message key="tawPartnerOil.state" />&nbsp;*
		</td>
		<td class="content">
		<eoms:comboBox name="state" id="state" initDicId="11205"
					defaultValue='${tawPartnerOilForm.state}' alt="allowBlank:false,vtext:''" />
			<%--<html:text property="state" styleId="state"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${tawPartnerOilForm.state}" />
		--%></td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="tawPartnerOil.storage" />&nbsp;*
		</td>
		<td class="content" colspan="3">
			<html:text property="storage" styleId="storage"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${tawPartnerOilForm.storage}" />
		</td>
		</tr>
		<logic:notEmpty name="tawPartnerOilForm" property="addMan">
			<tr>
				<td class="label">
					添加人姓名
				</td>
				<td class="content">
					<eoms:id2nameDB id="${tawPartnerOilForm.addMan}"
						beanId="tawSystemUserDao" />&nbsp;&nbsp;
						(<bean:write name="tawPartnerOilForm" property="connectType" />)
				</td>

				<td class="label">
					添加时间
				</td>
				<td class="content">
					<bean:write name="tawPartnerOilForm" property="addTime" />

				</td>
			</tr>
			</logic:notEmpty>
		<tr>
		<td class="label">
			<fmt:message key="tawPartnerOil.remark" />
		</td>
		<td class="content" colspan="3">
			<html:textarea property="remark" styleId="remark"
						styleClass="text medium" cols="40" rows="4"
						value="${tawPartnerOilForm.remark}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty tawPartnerOilForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('确认删除?')){
						var url='${app}/partner/baseinfo/tawPartnerOils.do?method=remove&id=${tawPartnerOilForm.id}';
						location.href=url}"	/>
				<%--<input type="button" class="btn" value="返回" 
					onclick="{
						var url='${app}/partner/baseinfo/tawPartnerOils.do?method=backToSearch';
						location.href=url}"	/>		
			--%></c:if>
			<input type="button" class="btn" value="批量导入"
						onclick="javascript:{
						var url='${app}/partner/baseinfo/tawPartnerOils.do?method=toXls';
						location.href=url}" />
		</td>
	</tr>
</table>
<html:hidden property="id" value="${tawPartnerOilForm.id}" />
</html:form>
<script type="text/javascript">
var id = document.getElementById("area_id").value;
			 var url="<%=request.getContextPath()%>/partner/baseinfo/tawPartnerOils.do?method=changeDep&id="+id;
			 var fieldName = "dept_id";
			 var deptId ="<%=((TawPartnerOilForm)request.getAttribute("tawPartnerOilForm")).getDept_id()%>";
			 changeList(url,fieldName,deptId);
function changeDep()
		{    
			 var id = document.getElementById("area_id").value;
			 var url="<%=request.getContextPath()%>/partner/baseinfo/tawPartnerOils.do?method=changeDep&id="+id;
			 var fieldName = "dept_id";
			 changeList(url,fieldName,"");
		}
  </script>
<%@ include file="/common/footer_eoms.jsp"%>