
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page language="java" import="java.util.*,com.boco.eoms.partner.baseinfo.webapp.form.*;"%>
<script language="JavaScript" type="text/JavaScript" src="${app}/scripts/module/partner/ajax.js"></script>
<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'partnerUserForm'});
});

function isEmail(strEmail) {
	if (strEmail.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1)
	return true;
	else{
		document.forms[0].email.value = '';
		alert("请检查邮箱格式！");
	}
}
function fucCheckNum()
   {
     var r,re;
           re = /^\d*\$/;
           r = document.forms[0].personCardNo.value.match(re);
           	if(r!=document.forms[0].personCardNo.value){   
           		document.forms[0].personCardNo.value = '';        		
           		alert("只能输入数字！");	
           	};
    }
function checkNum(theInput,str){ 
  a=parseInt(theInput); 
    if (isNaN(a)) 
  { 
      alert(str+"请输入数字"); 
      return false;
  } 
  else 
  return true;
  } 		

function openSelectAreas(){
    window.open ('${app}/partner/baseinfo/areaDeptTrees.do?method=selectAreas&type=addUser','newwindow','height=400,width=600,top=300,left=500,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no'); 
}
</script>

<html:form action="/partnerUsers.do?method=save" styleId="partnerUserForm" method="post" onsubmit="isEmail(this)"> 

<fmt:bundle basename="config/applicationResources-partner-baseinfo">

<html:hidden property="treeNodeId"/>

<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="partnerUser.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="partnerUser.name" />&nbsp;*
		</td>
		<td class="content">
			<html:text property="name" styleId="name"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${partnerUserForm.name}" />
		</td>
		
		<td class="label">
			<fmt:message key="partnerUser.personCardNo" />&nbsp;*
		</td>
		<td class="content">
			<html:text property="personCardNo" styleId="personCardNo" 
						styleClass="text medium"
						alt="allowBlank:false,vtype:'number',vtext:''" value="${partnerUserForm.personCardNo}" />
		</td>	

	</tr>

	<tr>	
	    <td class="label">
			<fmt:message key="partnerUser.areaName" />&nbsp;*
		</td>
		<td class="content">
		<!-- <html:text property="areaName" styleId="areaName"
						styleClass="text medium"
						alt="allowBlank:true,vtext:''" value="${partnerUserForm.areaName}" />  -->	
						
			 <html:select property="areaId" styleId="areaId" size="1" onchange="changeDep();">
					 <%List listId =(List) request.getAttribute("listId");
					List listName = (List)request.getAttribute("listName");
					PartnerUserForm fm = (PartnerUserForm)request.getAttribute("partnerUserForm");
					String nodeId = fm.getAreaId();
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
		</td>
		
		<td class="label">
			<fmt:message key="partnerUser.deptName" />&nbsp;*
		</td>
		<td class="content">
				<html:select property="deptId" styleId="deptId" size="1">
				</html:select>
	<!-- 		<html:text property="deptName" styleId="deptName"
						styleClass="text medium"
						alt="allowBlank:true,vtext:''" value="${partnerUserForm.deptName}" />  -->
		</td>
	<tr>	
		<td class="label">
			<fmt:message key="partnerUser.areaNames" />&nbsp;*
		</td>
		<td class="content">
			<html:text property="areaNames" styleId="areaNames" styleClass="text medium"
			onclick="openSelectAreas();" readonly="true"
						alt="allowBlank:false,vtext:''" value="${partnerUserForm.areaNames}" />
						
			<input type="hidden" name="areaType" id="areaType" >	<!-- 无用，只为不出错 -->		
		</td>
		
		<td class="label">
			<fmt:message key="partnerUser.userId" />&nbsp;*
		</td>
		<td class="content">
			<html:text property="userId" styleId="userId"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${partnerUserForm.userId}" /><font style="color: red;">${fallure }</font>
		</td>
	</tr>

	<tr>

		<td class="label">
			<fmt:message key="partnerUser.sex" />&nbsp;*
		</td>
		<td class="content">
		    
		    <eoms:comboBox name="sex" id="sex" initDicId="1120202"
					defaultValue='${partnerUserForm.sex}' alt="allowBlank:false,vtext:''" />

		</td>
		
		<td class="label">
			<fmt:message key="partnerUser.photo" />
		</td>
		<td class="content">
			<html:text property="photo" styleId="photo"
						styleClass="text medium"
						alt="allowBlank:true,vtext:''" value="${partnerUserForm.photo}" />
		</td>
	</tr>



	<tr>

		<td class="label">
			<fmt:message key="partnerUser.birthdey" />&nbsp;*
		</td>
		<td class="content">
			<html:text property="birthdey" styleId="birthdey" readonly="true"
						styleClass="text medium" onclick="popUpCalendar(this, this,null,null,null,false,-1);"
						alt="allowBlank:false,vtype:'lessThen',link:'workTime',vtext:'出生日期应早于工作日期'"
						 value="${partnerUserForm.birthdey}" />
		</td>
		
		<td class="label">
			<fmt:message key="partnerUser.diploma" />&nbsp;*
		</td>
		<td class="content">

			<eoms:comboBox name="diploma" id="diploma" initDicId="1120203"
					defaultValue='${partnerUserForm.diploma}' alt="allowBlank:false,vtext:''" />
		</td>
	</tr>



	<tr>

		<td class="label">
			<fmt:message key="partnerUser.workTime" />&nbsp;*
		</td>
		<td class="content">
			<html:text property="workTime" styleId="workTime" readonly="true"
						styleClass="text medium" onclick="popUpCalendar(this, this,null,null,null,false,-1);"
						alt="allowBlank:false,vtype:'moreThen',link:'birthdey',vtext:'工作时间应晚于出生时间'"
						 value="${partnerUserForm.workTime}" />
		</td>
		
		<td class="label">
			<fmt:message key="partnerUser.deptWorkTime" />&nbsp;*
		</td>
		<td class="content">
			<html:text property="deptWorkTime" styleId="deptWorkTime" readonly="true"
						styleClass="text medium" onclick="popUpCalendar(this, this,null,null,null,false,-1);"
						alt="allowBlank:false,vtype:'moreThen',link:'birthdey',vtext:'工作时间应晚于出生时间'"
						 value="${partnerUserForm.deptWorkTime}" />
		</td>
	</tr>



	<tr>

		<td class="label">
			<fmt:message key="partnerUser.licenseType" />&nbsp;*
		</td>
		<td class="content">
		    <eoms:comboBox name="licenseType" id="licenseType" initDicId="1120201"
					defaultValue='${partnerUserForm.licenseType}' alt="allowBlank:false,vtext:''" />

		</td>
		
		<td class="label">
			<fmt:message key="partnerUser.licenseNo" />&nbsp;*
		</td>
		<td class="content">
			<html:text property="licenseNo" styleId="licenseNo"
						styleClass="text medium"
						alt="allowBlank:false,vtext:''" value="${partnerUserForm.licenseNo}" />
		</td>
	</tr>



	<tr>

		<td class="label">
			<fmt:message key="partnerUser.post" />&nbsp;*
		</td>
		<td class="content">
						
			<eoms:comboBox name="post" id="post" initDicId="1120205"
					defaultValue='${partnerUserForm.post}' alt="allowBlank:false,vtext:''" />
		</td>
		
		<td class="label">
			<fmt:message key="partnerUser.postState" />&nbsp;*
		</td>
		<td class="content">
						
			<eoms:comboBox name="postState" id="postState" initDicId="1120204"
					defaultValue='${partnerUserForm.postState}' alt="allowBlank:false,vtext:''" />
		</td>
	</tr>



	<tr>

		<td class="label">
			<fmt:message key="partnerUser.phone" />&nbsp;*
		</td>
		<td class="content">
			<html:text property="phone" styleId="phone" 
						styleClass="text medium" 
						alt="allowBlank:false,vtext:''" value="${partnerUserForm.phone}" />
		</td>
		
		<td class="label">
			<fmt:message key="partnerUser.email" />&nbsp;*
		</td>
		<td class="content">
			<html:text property="email" styleId="email" 
						styleClass="text medium"
						alt="allowBlank:false,vtype:'email'" value="${partnerUserForm.email}" />
		</td>
	</tr>

</table>

</fmt:bundle>

<table>
	<tr>
		<td>
		<c:if test="${requestScope.hasRightForDel=='1'}" >
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
			<c:if test="${not empty partnerUserForm.id}">
				<input type="button" class="btn" value="<fmt:message key="button.delete"/>" 
					onclick="javascript:if(confirm('确认删除?')){
						var url='${app}/partner/baseinfo/partnerUsers.do?method=remove&id=${partnerUserForm.id}';
						location.href=url}"	/>
			</c:if>
		</c:if>
			<input type="button" class="btn" value="批量导入"
						onclick="javascript:{
						var url='${app}/partner/baseinfo/partnerUsers.do?method=toXls&treeNodeId=${partnerUserForm.treeNodeId }';
						location.href=url}" />
		</td>
	</tr>
</table>
<html:hidden property="id" value="${partnerUserForm.id}" />
</html:form>
<script type="text/javascript">
            var id = null;
            if(document.getElementById("areaId")!=null){
                id = document.getElementById("areaId").value;
            }
			 var url="<%=request.getContextPath()%>/partner/baseinfo/partnerUsers.do?method=changeDep&id="+id;
			 var fieldName = "deptId";
			 var deptId ="<%=((PartnerUserForm)request.getAttribute("partnerUserForm")).getDeptId()%>";
			 changeList(url,fieldName,deptId);
  function changeDep()
		{    
			 var id = null;
			 if(document.getElementById("areaId")){
			    id = document.getElementById("areaId").value;
			 }
			 var url="<%=request.getContextPath()%>/partner/baseinfo/partnerUsers.do?method=changeDep&id="+id;
			 var fieldName = "deptId";
			 changeList(url,fieldName,"");
		}
  </script>
<%@ include file="/common/footer_eoms.jsp"%>