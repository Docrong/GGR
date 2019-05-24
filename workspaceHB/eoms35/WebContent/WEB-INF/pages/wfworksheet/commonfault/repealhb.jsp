<%@ include file="/common/taglibs.jsp"%>
<%@page import="java.util.*"%>
<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
String oldsql = (String) request.getAttribute("oldsql");
%>
<script type="text/javascript">
Ext.onReady(function(){
 v = new eoms.form.Validation({form:'theform'});
 
});
function getr(){
	var ids="";
	var oldsql=document.getElementById("oldsqls").value;
	var excelid=document.getElementById("excelid").value;
	var objs = document.getElementsByName("ids");
		for(var i=0; i<objs.length; i++) {
    			if(objs[i].type.toLowerCase() == "checkbox" ){
    				if(objs[i].checked==true){
    					ids += objs[i].value;
    						ids+=",";
    				}
    			}
		}
		if(ids!=null&&ids!=''){
		
		document.getElementById("sheetkeys").value=ids;
		document.getElementById("oldsql").value=oldsql;
		document.getElementById("excelids").value=excelid;
		//alert(oldsql);
		var form = document.getElementById("theform");
		//alert(document.getElementById("sheetkeys").value);
		//form.action='commonfault.do?method=referHB';
		form.submit();
		}else{
			alert("选择不能为空");
		}
		
}
</script>

<div id="sheetform"><html:form
	action="/commonfault.do?method=referHB" styleId="theform">
	<input type="hidden" name="oldsql" id="oldsql"/>
	<input type="hidden" name="excelid" id="excelids"/>
	<input type="hidden" name="sheetkeys" id="sheetkeys"/>
	<input type="hidden" name="Userid" value="${Userid}"/>
	<input type="hidden" name="Deptid" value="${Deptid}"/>
	<input type="hidden" name="LocalTime" value="${LocalTime}"/>
	<table class="formTable">
		<caption><bean:message bundle="sheet" key="linkForm.header" /></caption>
		<tr>
			<td class="label"><bean:message bundle="sheet"
				key="linkForm.operateUserName" />* </td>
			<td class="content"><eoms:id2nameDB
				id="${Userid}" beanId="tawSystemUserDao" />&nbsp;</td>
			<td class="label"><bean:message bundle="sheet"
				key="linkForm.operateDeptName" />* </td>
			<td class="content"><eoms:id2nameDB
				id="${Deptid}" beanId="tawSystemDeptDao" />&nbsp;</td>
		</tr>
		<tr>
			<td class="label"><bean:message bundle="sheet"
				key="linkForm.operaterContact" /> *</td>
			<td class="content"><input type="text" class="text"
				name="operaterContact"
				id="operaterContact"
				value="${ContactMobile}" alt="allowBlank:false" /></td>
			<td class="label"><bean:message bundle="sheet"
				key="linkForm.operateTime" />*</td>
			<td class="content">${LocalTime}</td>
		</tr>
		<tr>
			<td class="label"><bean:message bundle="sheet"
				key="mainForm.cancelReason" />*</td>
			<td colspan="3"><c:set var="vtext" value="4000" /> <textarea
				class="textarea max" name="cancelReason"
				id="cancelReason"
				alt="allowBlank:false,maxLength:4000,vtext:'${vtext}'"></textarea></td>
		</tr>
	</table>
	<input type="button" onclick="getr();" value="提交" class="btn">
	<div class="form-btns"></div>
</html:form></div>
