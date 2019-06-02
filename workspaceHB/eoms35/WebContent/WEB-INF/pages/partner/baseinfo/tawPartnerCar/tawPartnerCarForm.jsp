<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page language="java" import="java.util.*,com.boco.eoms.partner.baseinfo.webapp.form.*;"%>
<script language="JavaScript" type="text/JavaScript" src="${app}/scripts/module/partner/ajax.js">
</script>
<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'tawPartnerCarForm'});
});

</script>

<html:form action="/tawPartnerCars.do?method=save" styleId="tawPartnerCarForm" method="post" > 

<fmt:bundle basename="config/applicationResources-partner-baseinfo">

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="tawPartnerCar.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="tawPartnerCar.car_number" />&nbsp;*
		</td>
		<td class="content">
			<html:text property="car_number" styleId="car_number"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${tawPartnerCarForm.car_number}" />${fallure }
		</td>
	</tr>

	

	<tr>
		<td class="label">
			<fmt:message key="tawPartnerCar.area_id" />&nbsp;*
		</td>
		<td class="content">
		 <html:select property="area_id" styleId="area_id" size="1" onchange="changeDep();">
					 <%List listId =(List) request.getAttribute("listId");
					List listName = (List)request.getAttribute("listName");
					TawPartnerCarForm fm = (TawPartnerCarForm)request.getAttribute("tawPartnerCarForm");
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
						alt="allowBlank:false,vtext:''" value="${tawPartnerCarForm.area_id}" />
		--%></td>
	</tr>
<tr>
		<td class="label">
			<fmt:message key="tawPartnerCar.dept_id" />&nbsp;*
		</td>
		<td class="content">
				 <html:select property="dept_id" styleId="dept_id" size="1">
				 </html:select>
			<%--<html:text property="dept_id" styleId="dept_id"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${tawPartnerCarForm.dept_id}" />
		--%></td>
	</tr>
	<tr>
		<td class="label">
			<fmt:message key="tawPartnerCar.start_time" />
		</td>
		<td class="content">
		<html:text property="start_time"
						onclick="popUpCalendar(this, this,null,null,null,true,-1); " readonly="true"
						styleId="startTime" styleClass="text medium"
						alt="allowBlank:false,vtext:'请输入开始时间'" />
		
			<%--<html:text property="start_time" styleId="start_time"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${tawPartnerCarForm.start_time}" />
		--%></td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="tawPartnerCar.piece" />&nbsp;*
		</td>
		<td class="content"><%--
		<eoms:comboBox name="piece" id="piece" initDicId="11210"
						alt="allowBlank:false,vtext:''" 
						defaultValue='${tawPartnerCarForm.piece }' multiple="true" size="3"/>
			<html:text property="piece" styleId="piece"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${tawPartnerCarForm.piece}" />
		
		<html:checkbox property="piece"  value="1">aaa</html:checkbox>
		<html:checkbox property="piece" value="2">fff</html:checkbox>
		--%>
		<logic:iterate id="item" scope="request" name="tawPartnerCarForm" property="arrayPieceAll" >
		<html:multibox  property="arrayPiece" value="${item}"></html:multibox> 
	    <eoms:id2nameDB id="${item}" beanId="tawSystemDictTypeDao" />
		</logic:iterate>
		<html:hidden property="arrayPiece" styleId="arrayPiece" value="1"/> 
		
		</td>
	</tr>
<logic:notEmpty name="tawPartnerCarForm" property="addMan">
			<tr>
				<td class="label">
					添加人姓名
				</td>
				<td class="content">
					<eoms:id2nameDB id="${tawPartnerCarForm.addMan}"
						beanId="tawSystemUserDao" />&nbsp;&nbsp;
						(<bean:write name="tawPartnerCarForm" property="connectType" />)
				</td>
				</tr>
<tr>
				<td class="label">
					添加时间
				</td>
				<td class="content">
					<bean:write name="tawPartnerCarForm" property="addTime" />

				</td>
			</tr>
			</logic:notEmpty>
	<tr>
		<td class="label">
			<fmt:message key="tawPartnerCar.remark" />
		</td>
		<td class="content">
			<html:textarea property="remark" styleId="remark"
						styleClass="text medium" rows="3" cols="40"
						value="${tawPartnerCarForm.remark}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty tawPartnerCarForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('确认删除?')){
						var url='${app}/partner/baseinfo/tawPartnerCars.do?method=remove&id=${tawPartnerCarForm.id}';
						location.href=url}"	/>
				<%--<input type="button" class="btn" value="返回" 
					onclick="{
						var url='${app}/partner/baseinfo/tawPartnerCars.do?method=backToSearch';
						location.href=url}"	/>		
			--%></c:if>
			<input type="button" class="btn" value="批量导入"
						onclick="javascript:{
						var url='${app}/partner/baseinfo/tawPartnerCars.do?method=toXls';
						location.href=url}" />
		</td>
	</tr>
</table>
<html:hidden property="id" value="${tawPartnerCarForm.id}" />
</html:form>
<script type="text/javascript">
var id = document.getElementById("area_id").value;
			 var url="<%=request.getContextPath()%>/partner/baseinfo/tawPartnerCars.do?method=changeDep&id="+id;
			 var fieldName = "dept_id";
			 var deptId ="<%=((TawPartnerCarForm)request.getAttribute("tawPartnerCarForm")).getDept_id()%>";
			 changeList(url,fieldName,deptId);
function changeDep()
		{    
			 var id = document.getElementById("area_id").value;
			 var url="<%=request.getContextPath()%>/partner/baseinfo/tawPartnerCars.do?method=changeDep&id="+id;
			 var fieldName = "dept_id";
			 changeList(url,fieldName,"");
		}
  </script>
<%@ include file="/common/footer_eoms.jsp"%>