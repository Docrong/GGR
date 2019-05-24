<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'trainPlanForm'});
});
	
	var xmlHttp;
	function createXMLHttpRequest() {
		//表示当前浏览器不是ie,如ns,firefox
		if(window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	
	function getRequire(requireId) {
		document.getElementById('trainRequireNo').value = requireId;
		if (requireId != "") {
			createXMLHttpRequest();
			var url = '${app}/kmmanager/trainRequires.do?method=getRequire&id='+requireId;
			xmlHttp.open("POST", url , true);
			xmlHttp.onreadystatechange=callback;
			xmlHttp.send(null);
		}
	}
	
	function callback() {
	    if (xmlHttp.readyState == 4) {
		  if (xmlHttp.status == 200) {
		  	var xmlText = xmlHttp.responseText;
		  	var textArr ;
		  	textArr = xmlText.split('|||');
			document.getElementById('trainRequireNo').value = textArr[0];
			document.getElementById('specialityName').value =  textArr[1] ;
			document.getElementById('trainSpeciality').value =  textArr[2] ;
		   }
	   }
	}
	//选择需求编号的详情页面
	function opentest(){
		 window.open('${app}/kmmanager/trainRequires.do?method=searchMust');
	}
</script>

<!-- 
<eoms:xbox id="tree" dataUrl="${app}/kmmanager/trainRequires.do?method=getNodesRadioTree" 
	  	rootId="1" 
	  	rootText='培训需求' 
	  	valueField="trainRequireNo" handler="trainRequireNoName"
		textField="trainRequireNoName"
		checktype="forums" single="true"		
	  ></eoms:xbox>
 -->
 <eoms:xbox id="tree" dataUrl="${app}/kmmanager/trainSpecialtys.do?method=getNodesRadioTree" 
	  	rootId="1" 
	  	rootText='专业'  
	  	valueField="trainSpeciality" handler="specialityName"
		textField="specialityName"
		checktype="forums" single="true"	 	
	  ></eoms:xbox>

<html:form action="/trainPlans.do?method=save" styleId="trainPlanForm" method="post"> 

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
<font color='red'>*</font>号为必填内容
<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="trainPlan.form.heading"/></div>
	</caption>

	<tr>
		<td class="label">
			<fmt:message key="trainPlan.trainName" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content" colspan="3">
			<html:text property="trainName" styleId="trainName"
						styleClass="text max"
						alt="allowBlank:false,vtext:''trainPlans,maxLength:32" value="${trainPlanForm.trainName}" />
		</td>
	</tr>
	
	<tr>
		<td class="label">
			<fmt:message key="trainPlan.trainUser" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${trainPlanForm.trainUser}" beanId="tawSystemUserDao" />
			<input type="hidden" name="trainUser" value="${trainPlanForm.trainUser}" />
		</td>
		<td class="label">
			<fmt:message key="trainPlan.trainDept" />
		</td>
		<td class="content">
			<eoms:id2nameDB id="${trainPlanForm.trainDept}" beanId="tawSystemDeptDao" />
			<input type="hidden" name="trainDept" value="${trainPlanForm.trainDept}" />
		</td>
	</tr>
	
	<tr>
		<td class="label">
			<fmt:message key="trainPlan.trainTel" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<html:text property="trainTel" styleId="trainTel"
						styleClass="text"
						alt="allowBlank:false,vtext:'',vtype:'number',maxLength:32" value="${trainPlanForm.trainTel}" />
		</td>
		
		<td class="label">
			<fmt:message key="trainPlan.trainUnit" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<html:text property="trainUnit" styleId="trainUnit"
						styleClass="text"
						alt="allowBlank:false,vtext:'',maxLength:32" value="${trainPlanForm.trainUnit}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="trainPlan.trainBeginTime" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<html:text property="trainBeginTime" styleId="trainBeginTime"
						styleClass="text" readonly="true"
			           onclick="popUpCalendar(this, this);"
						alt="allowBlank:false,vtext:''" value="${trainPlanForm.trainBeginTime}" />
		</td>
		
		<td class="label">
			<fmt:message key="trainPlan.trainTime" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<html:text property="trainTime" styleId="trainTime"
						styleClass="text"
						alt="allowBlank:false,vtext:'',vtype:'number',maxLength:10" value="${trainPlanForm.trainTime}" />
		</td>
	</tr>
	
	<tr>
		<td class="label">
			<fmt:message key="trainPlan.trainEndTime" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<html:text property="trainEndTime" styleId="trainEndTime"
						styleClass="text"  readonly="true"
						 onclick="popUpCalendar(this, this);"
						alt="allowBlank:false,vtext:''" value="${trainPlanForm.trainEndTime}" />
		</td>
		
		<td class="label">
			<fmt:message key="trainPlan.trainAddress" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<html:text property="trainAddress" styleId="trainAddress"
						styleClass="text"
						alt="allowBlank:false,vtext:'',maxLength:512" value="${trainPlanForm.trainAddress}" />
		</td>
	
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="trainPlan.trainVender" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<html:text property="trainVender" styleId="trainVender"
						styleClass="text"
						alt="allowBlank:false,vtext:'',maxLength:32" value="${trainPlanForm.trainVender}" />
		</td>
			<td class="label">
			<fmt:message key="trainPlan.trainType" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<html:text property="trainType" styleId="trainType"
						styleClass="text"
						alt="allowBlank:false,vtext:'',maxLength:32" value="${trainPlanForm.trainType}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="trainPlan.trainSpeciality" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content">
			<input type="text"   id="specialityName" name="specialityName" class="text"  value='<eoms:id2nameDB id="${trainPlanForm.trainSpeciality}" beanId="trainSpecialtyDao" />' alt="allowBlank:false'"  readonly="true"/>
			<input type="hidden" id=trainSpeciality   name="trainSpeciality" value="${trainPlanForm.trainSpeciality}" />
		</td>
		
		<td class="label">
			<fmt:message key="trainPlan.trainRequireNo" />
		</td>
		<td class="content">
			<input type="text" id="trainRequireNoName" class="text" onclick="opentest()" name="trainRequireNo" value="${trainPlanForm.trainRequireNo}" readonly="true" />
		</td>
			<input type="hidden" name="trainRequireNo" id="trainRequireNo" />
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="trainPlan.trainContent" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content" colspan="3">
			<textarea name="trainContent" id="trainContent" 
			          cols="50" class="textarea max" 
			          alt="allowBlank:false,vtext:'',maxLength:2000">${trainPlanForm.trainContent}</textarea>	
		</td>
	</tr>
	<tr>
		<td class="label">
			<fmt:message key="trainPlan.fileName" />
		</td>
		<td class="content" colspan="3">
			<eoms:attachment name="trainPlanForm" property="fileName" scope="request" idField="fileName" appCode="kmmanager" />
		</td>
	</tr>
</table>

</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
		</td>
	</tr>
</table>
<html:hidden property="id" value="${trainPlanForm.id}" />
</html:form>

<%@ include file="/common/footer_eoms.jsp"%>