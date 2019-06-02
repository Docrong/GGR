<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm"%>

<%
String operateType = StaticMethod.nullObject2String(request.getAttribute("operateType"));
TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
String operateUserId = sessionform.getUserid();
if(operateType.indexOf("R")==-1&&!"admin".equals(operateUserId)){
%>
	<caption>
		<div class="header center">您没有查看权限</div>
	</caption>
<%
}else{
%>
<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">

<!-- 详细信息 -->
<!-- <html:form action="/creatlessons.do?method=modify" styleId="creatlessonForm" method="post">-->
	<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="creatlesson.detail.title"/>&nbsp;&nbsp;
		<img src="${app}/images/icons/edit.gif"/></div>
	</caption>
	</table>
	<table class="formTable">

	<tr>
		<td class="label">
			<fmt:message key="creatlesson.id" />
		</td>
		<td class="content">
					${detailList.id}
		</td>
	</tr>
	<tr>
		<td class="label">
			<fmt:message key="creatlesson.creatUserID" />
		</td>
		<td class="content">
			${detailList.creatUserID}
		</td>
	</tr>
	<tr>
		<td class="label">
			<fmt:message key="creatlesson.subjectName" />
		</td>
		<td class="content">
		  	${detailList.subjectName}
		</td>
	</tr>
	<tr>
		<td class="label">
			<fmt:message key="creatlesson.theme" />
		</td>
		<td class="content">
		  	<eoms:id2nameDB id="theme" beanId="creatlessonDao" />
		  	${detailList.theme}
		  	
		</td>
	</tr>
	<tr>
		<td class="label">
			<fmt:message key="creatlesson.businessType" />
		</td>
		<td class="content">
		  	${detailList.businessType}
		</td>
	</tr>
	<tr>
		<td class="label">
			<fmt:message key="creatlesson.creatTime" />
		</td>
		<td class="content">
		  	${detailList.creatTime}
		</td>
	</tr>
		<tr>
		<td class="label">
			<fmt:message key="creatlesson.startTime" />
		</td>
		<td class="content">
		  	${detailList.startTime}
		</td>
	</tr>
		<tr>
		<td class="label">
			<fmt:message key="creatlesson.endTime" />
		</td>
		<td class="content">
		  	${detailList.endTime}
		</td>
	</tr>
	<tr>
		<td class="label">
			<fmt:message key="creatlesson.totalTime" />
		</td>
		<td class="content">
		  	${detailList.totalTime}
		</td>
	</tr>
	<tr>
		<td class="label">
			<fmt:message key="creatlesson.subjectContents" />
		</td>
		<td class="content">
		<textarea name="subjectContents" id="subjectContents" 
			          cols="50" class="textarea max" 
			          alt="allowBlank:false,vtext:''" >${detailList.subjectContents}</textarea>
		</td>
	</tr>
	<tr>
		<td class="label">
			<fmt:message key="creatlesson.attachment" />
		</td>
		<td class="content">
		  <eoms:attachment name="detailList" property="attachment" scope="request" idField="attachment" appCode="kmmanager" viewFlag="Y"/>
		</td>
	</tr>
	
</table>
<table>
	<tr>
		<td>
		<input type="button" class="btn" value="<fmt:message key="button.modify"/>"  onclick="javascript:var id='${detailList.id }';
		   						var url='${app}/kmmanager/creatlessons.do?method=modify';
					    		url = url + '&ID=' + id ;
		    					location.href=url"/>
		  
		 <!-- <input type="submit" class="btn" value="<fmt:message key="button.modify"/>" />
		  	<img src="${app}/images/icons/edit.gif"/>
		  	<input type="hidden" name="id" value="${detailList.id}" />  --> 					
		</td>    					
	</tr>
</table>
<!--</html:form>-->
</fmt:bundle>	
<%
}
%>
<%@ include file="/common/footer_eoms.jsp"%>