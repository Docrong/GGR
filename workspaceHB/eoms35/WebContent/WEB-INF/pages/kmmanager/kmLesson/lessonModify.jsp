<%@ page language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>


<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
<form enctype="multipart/form-data" name="CreatlessonForm" action="${app}/kmmanager/creatlessons.do?method=edited" styleId="creatlessonForm" method="post"> 
<!-- 详细信息 -->
	<table class="formTable">
	<caption>
		<div class="header center"><fmt:message key="creatlesson.detail.title"/></div>
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
		<html:text property="subjectName" styleId="subjectName"
						styleClass="text medium" maxlength="30"
						alt="allowBlank:false,vtext:''" value="${detailList.subjectName}" />
			&nbsp;&nbsp;<font color="red">*</font>
		  	
		</td>
	</tr>
	<tr>
		<td class="label">
			<fmt:message key="creatlesson.theme" />
		</td>
		<td class="content">
		  <input type="text" id="theme" name="theme" class="text medium" maxlength="30" value="${detailList.theme}"/>
		  	
		  	
		</td>
	</tr>
	<tr>
		<td class="label">
			<fmt:message key="creatlesson.businessType" />
		</td>
		<td class="content">
		<html:text property="businessType" styleId="businessType"
						styleClass="text medium" maxlength="32"
						alt="allowBlank:false,vtext:''" value="${detailList.businessType}" />
			&nbsp;&nbsp;<font color="red">*</font>
		  	
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
			<fmt:message key="creatlesson.startTime"/> 
		</td>
		<td class="content" colspan="2">
			<input type="text" size="20" readonly="readonly" class="text" 
			          name="startTime"
			          id="startTime" 
			          onclick="popUpCalendar(this,this,null,null,null,true,-1);"
			          alt="allowBlank:false,vtext:'请选择开始时间...'" value="${detailList.startTime}"/>&nbsp;&nbsp;
			          <font color="red">*</font>
		</td>
	</tr>
	<tr>
		<td class="label">	          
			<fmt:message key="creatlesson.endTime"/>
		</td>
		<td class="content" colspan="2">	 
			<input type="text" size="20" readonly="readonly" class="text" 
			          name="endTime" 
			          id="endTime" 
			          onclick="popUpCalendar(this,this,null,null,null,true,-1);" 
			          alt="allowBlank:false,vtext:'请选择结束时间...'" value="${detailList.endTime}"/>&nbsp;&nbsp;
			          <font color="red">*</font>  
		</td>
	</tr>
	
	
	<tr>
		<td class="label">
			<fmt:message key="creatlesson.subjectContents" />
		</td>
		<td class="content">
		<textarea name="subjectContents" id="subjectContents" 
			          cols="50" class="textarea max" 
			          alt="allowBlank:false,vtext:'请输入课题内容...'" >${detailList.subjectContents}</textarea>
			&nbsp;&nbsp;<font color="red">*</font>
		  	
		</td>
	</tr>
	<tr>
		<td class="label">
			<fmt:message key="creatlesson.attachment" />
		</td>
		<td class="content">
		 <fmt:message key="creatlesson.uploaded"/>: 
		 <eoms:attachment name="detailList" property="attachment" scope="request" idField="attachment" appCode="kmmanager" viewFlag="Y"/>
		  </br><fmt:message key="creatlesson.attention"/>
		 <eoms:attachment name="Creatlesson" property="${detailList.attachment}" scope="request" idField="attachment" appCode="kmmanager" startsWith="0" />
		</td>
	</tr>
	
</table>
<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>"/>
			<input type="hidden" name="id" value="${detailList.id}" />
		</td>
	</tr>
</table>
</form>
</fmt:bundle>	
<%@ include file="/common/footer_eoms.jsp"%>