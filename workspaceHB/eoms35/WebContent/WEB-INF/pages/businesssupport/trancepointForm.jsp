<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"  %>
<%

response.setHeader("P3P","CP=CAO PSA OUR");
%>
<script type="text/javascript">
	window.name = "myname";
	function winclose2(){	
		var f = document.forms[0];
		var params=new Object();
		params["fiberEquipName"]=f.fiberEquipName.value;
		params["sevPointContactPhone"]=f.sevPointContactPhone.value;
		params["sevPointContactEmail"]=f.sevPointContactEmail.value;
		params["sevPointContactAdd"]=f.sevPointContactAdd.value;
		params["siteEquipCode"]=f.siteEquipCode.value;
		params["siteName"]=f.siteName.value;
		params["sevPointContact"]=f.sevPointContact.value;
		params["fiberOwner"]=f.fiberOwner.value;
		params["fiberLength"]=f.fiberLength.value;
		params["accessSiteIden"]=f.accessSiteIden.value;
		
		
		window.returnValue = params;
		f.submit();
		window.close();
	}
	

</script>



<html:form action="/trancePointSheet.do?method=save" styleId="trancePointForm" method="post" target="myname" > 


<table class="formTable">
	<caption>
		<div class="header center">接入点数据</div>
	</caption>
	<html:hidden property="orderSheetId" styleId="orderSheetId"
						styleClass="text medium"
						 value="${trancePointForm.orderSheetId}" />
	<html:hidden property="id" styleId="id"
						styleClass="text medium"
						 value="${trancePointForm.id}" />				 
		
	<input type=hidden name="specialType"    value="${specialType}" />			 
	<!--  
	<tr>
			<tr>
			<td class="label">
			端点详细地址
		</td>
		<td class="content">
			<html:text property="portDetailAdd" styleId="portDetailAdd"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trancePointForm.portDetailAdd}" />
		</td>
	<td class="label">
			业务设备名称
		</td>
		<td class="content">
			<html:text property="portDeviceName" styleId="portDeviceName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trancePointForm.portDeviceName}" />
		</td>
	
	
	</tr>

	

	<tr>
		<td class="label">
			客户在当地的配合人
		</td>
		<td class="content">
			<html:text property="portCustAidPerson" styleId="portCustAidPerson"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trancePointForm.portCustAidPerson}" />
		</td>
	
		<td class="label">
			客户在当地的配合人的联系电话
		</td>
		<td class="content">
			<html:text property="portACustAidPhone" styleId="portACustAidPhone"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trancePointForm.portACustAidPhone}" />
		</td>
	</tr>
	
	-->
	<tr>
		<td class="label">
			接口类型及型号
		</td>
		<td class="content">
			<html:text property="portInterfaceType" styleId="portInterfaceType"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trancePointForm.portInterfaceType}" />
		</td>
	<td class="label">
			机房名称
		</td>
		<td class="content">
			<html:text property="portEngineRoomName" styleId="portEngineRoomName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trancePointForm.portEngineRoomName}" />
		</td>
		
	</tr>
	<tr>
		<td class="label">
			业务接入点客户联系电话
		</td>
		<td class="content">
			<html:text property="sevPointContactPhone" styleId="sevPointContactPhone"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trancePointForm.sevPointContactPhone}" />
		</td>

		<td class="label">
			业务接入点客户联系邮箱
		</td>
		<td class="content">
			<html:text property="sevPointContactEmail" styleId="sevPointContactEmail"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trancePointForm.sevPointContactEmail}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			业务接入点客户联系地址
		</td>
		<td class="content">
			<html:text property="sevPointContactAdd" styleId="sevPointContactAdd"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trancePointForm.sevPointContactAdd}" />
		</td>

		<td class="label">
			光纤设备名称
		</td>
		<td class="content">
			<html:text property="fiberEquipName" styleId="fiberEquipName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trancePointForm.fiberEquipName}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			光纤设备编号
		</td>
		<td class="content">
			<html:text property="fiberEquipCode" styleId="fiberEquipCode"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trancePointForm.fiberEquipCode}" />
		</td>
	
		<td class="label">
			站点设备编码
		</td>
		<td class="content">
			<html:text property="siteEquipCode" styleId="siteEquipCode"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trancePointForm.siteEquipCode}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			站点名称
		</td>
		<td class="content">
			<html:text property="siteName" styleId="siteName"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trancePointForm.siteName}" />
		</td>
	
		<td class="label">
			接入站点标识
		</td>
		<td class="content">
			<html:text property="accessSiteIden" styleId="accessSiteIden"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trancePointForm.accessSiteIden}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			光缆纤芯个数
		</td>
		<td class="content">
			<html:text property="fiberNum" styleId="fiberNum"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trancePointForm.fiberNum}" />
		</td>
	
		<td class="label">
			最后一公里光缆长度
		</td>
		<td class="content" colspan=3>
			<html:text property="fiberLength" styleId="fiberLength"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trancePointForm.fiberLength}" />
		</td>
	</tr>
	<tr>
		<td class="label">
			光缆产权
		</td>
         <td>
            <c:if test="${trancePointForm.fiberOwner=='2'}">
            	<select name=fiberOwner>
            		<option value='2' selected>租用</option>
            		<option value='1' >自有</option>
            	</select>
            </c:if>
            <c:if test="${trancePointForm.fiberOwner=='1'}">
            	<select name=fiberOwner>
            		<option value='2' >租 用</option>
            		<option value='1' selected >自 有</option>
            	</select>
            </c:if>
            <c:if test="${empty trancePointForm.fiberOwner}">
            	<select name=fiberOwner>
            		<option value='2' selected>租 用</option>
            		<option value='1'  >自 有</option>
            	</select>
            </c:if>
            
         
         </td>
	
		<td class="label">
			业务接入点客户联系人
		</td>
		<td class="content" colspan=3>
			<html:text property="sevPointContact" styleId="sevPointContact"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${trancePointForm.sevPointContact}" />
		</td>
	</tr>

</table>


<table>
	<tr>
		<td>
			<!-- <input type="submit" class="btn" value="<fmt:message key="button.save"/>" />-->
			 <input type="button" class="btn" value="保存" onclick="javascript:winclose2();"/>
			<!-- 
			<c:if test="${not empty trancePointForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('confirm?')){
						var url='${app}businessupport/trancepoint/trancePointSheet.do?method=remove&id=${trancePointForm.id}';
						location.href=url}"	/>
			</c:if>
			 -->
		</td>
	</tr>
</table>
<html:hidden property="id" value="${trancePointForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>