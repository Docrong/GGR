<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import="com.boco.eoms.km.expert.model.KmExpertAnswer"%>
<%@ page import="com.boco.eoms.commons.system.session.form.TawSystemSessionForm"%>
<script type="text/javascript" src="${app}/scripts/kmmanager/adapter-km.js"></script>

<%
TawSystemSessionForm sessionform = (TawSystemSessionForm) request.getSession().getAttribute("sessionform");
String operateUserId = sessionform.getUserid();
KmExpertAnswer kmExpertAnswer = (KmExpertAnswer)request.getAttribute("kmExpertAnswer");
boolean Answer_0 = "0".equals(kmExpertAnswer.getState())&&!operateUserId.equals(kmExpertAnswer.getAnswerUserId());
boolean Answer_1 = "1".equals(kmExpertAnswer.getState())&&!operateUserId.equals(kmExpertAnswer.getAnswerUserId());
boolean Answer_2 = "2".equals(kmExpertAnswer.getState())&&!operateUserId.equals(kmExpertAnswer.getAnswerUserId());
boolean Answer_3 = "3".equals(kmExpertAnswer.getState())&&!operateUserId.equals(kmExpertAnswer.getAnswerUserId());

boolean Create_0 = "0".equals(kmExpertAnswer.getState())&&operateUserId.equals(kmExpertAnswer.getCreateUserId());
boolean Create_1 = "1".equals(kmExpertAnswer.getState())&&operateUserId.equals(kmExpertAnswer.getCreateUserId());
boolean Create_2 = "2".equals(kmExpertAnswer.getState())&&operateUserId.equals(kmExpertAnswer.getCreateUserId());
boolean Create_3 = "3".equals(kmExpertAnswer.getState())&&operateUserId.equals(kmExpertAnswer.getCreateUserId());
%>

<html:form action="/kmExpertAnswer.do?method=saveQuestion" styleId="KmExpertAnswerForm" method="post" > 
<html:hidden property="id" value="${kmExpertAnswer.id}" />
<html:hidden property="createTime" value="${kmExpertAnswer.createTime}" />
<html:hidden property="createUserId" value="${kmExpertAnswer.createUserId}" />
<html:hidden property="answerUserId" value="${kmExpertAnswer.answerUserId}" />
<table class="formTable">
	<caption>
		<div class="header center">专家坐诊问答</div>
	</caption>

<%
if(Create_0||Create_3){
%>

	<tr>
		<td class="label">
			创建人
		</td>
		<td class="content">	
		<eoms:id2nameDB id="${kmExpertAnswer.createUserId}" beanId="tawSystemUserDao" />
		<html:hidden property="createUserId" value="${kmExpertAnswer.createUserId}" />
		</td>

		<td class="label">
			分类
		</td>
		<td class="content">		
	 	<eoms:id2nameDB id="${kmExpertAnswer.type}" beanId="ItawSystemDictTypeDao" />
		<html:hidden property="type" value="${kmExpertAnswer.type}" />
		</td>
	</tr>
	<tr>

		<td class="label">
			问题主题
		</td>
		<td class="content"  colspan="3">
		${kmExpertAnswer.title}	
		<html:hidden property="title" value="${kmExpertAnswer.title}" />
		</td>
	</tr>
	<tr>
		<td class="label">
			问题描述
		</td>
		<td class="content" colspan="3">
		${kmExpertAnswer.question}	
		<html:hidden property="question" value="${kmExpertAnswer.question}" />
		</td>

	</tr>


	<tr>
		<td class="label">
			备注	(可修改)
		</td>
		<td class="content" colspan="3">

		<textarea name="remark" id="remark" class="textarea max">${kmExpertAnswer.remark}</textarea>

	</tr>
	<tr>
	<td class="label">
	问题附件
	</td>
	<td class="content" colspan="3">
	<eoms:attachment name="kmExpertAnswer" property="questionAtt" scope="request" idField="questionAtt" appCode="kmmanager" viewFlag="Y"/>	
	<html:hidden property="questionAtt" value="${kmExpertAnswer.questionAtt}" />
	</td>
	</tr>
	<tr>
		<td class="label">
		解答专家
		</td>
		<td class="content">
			<eoms:id2nameDB id="${kmExpertAnswer.answerUserId}" beanId="tawSystemUserDao" />
		</td>
	<td class="label">
	问题状态
	</td>
	<td class="content">
	<eoms:dict key="dict-kmmanager" dictId="questionState" itemId="${kmExpertAnswer.state}" beanId="id2nameXML" />
	</td>
	</tr>
<%
} 

if(Create_1||Create_2||Answer_0||Answer_1||Answer_2||Answer_3){
%>
<tr>
		<td class="label">
			创建人
		</td>
		<td class="content">	
		<eoms:id2nameDB id="${kmExpertAnswer.createUserId}" beanId="tawSystemUserDao" />
		<html:hidden property="createUserId" value="${kmExpertAnswer.createUserId}" />
		</td>

		<td class="label">
			分类
		</td>
		<td class="content">		
	 	<eoms:id2nameDB id="${kmExpertAnswer.type}" beanId="ItawSystemDictTypeDao" />
		<html:hidden property="type" value="${kmExpertAnswer.type}" />
		</td>
	</tr>
	<tr>

		<td class="label">
			问题主题
		</td>
		<td class="content"  colspan="3">
		${kmExpertAnswer.title}	
		<html:hidden property="title" value="${kmExpertAnswer.title}" />
		</td>
	</tr>
	<tr>
		<td class="label">
			问题描述
		</td>
		<td class="content" colspan="3">
		${kmExpertAnswer.question}	
		<html:hidden property="question" value="${kmExpertAnswer.question}" />
		</td>

	</tr>
	<tr>
		<td class="label">
			备注	<% if(Create_1||Create_3){%>(可修改)<%} %>
		</td>
		<td class="content" colspan="3">
		<% 
		if(Create_1||Create_3){
		%>
		<textarea name="remark" id="remark" class="textarea max">${kmExpertAnswer.remark}</textarea>
		<% 
		}else {
		%>
		${kmExpertAnswer.remark}
		<html:hidden property="remark" value="${kmExpertAnswer.remark}" />
		<% 
		}
		%>
		</td>
	</tr>
	<tr>
	<td class="label">
	问题附件
	</td>
	<td class="content" colspan="3">
	<eoms:attachment name="kmExpertAnswer" property="questionAtt" scope="request" idField="questionAtt" appCode="kmmanager" viewFlag="Y"/>	
	<html:hidden property="questionAtt" value="${kmExpertAnswer.questionAtt}" />
	</td>
	</tr>
	<tr>
		<td class="label">
		解答专家
		</td>
		<td class="content">
			<eoms:id2nameDB id="${kmExpertAnswer.answerUserId}" beanId="tawSystemUserDao" />
			<html:hidden property="answerUserId" value="${kmExpertAnswer.answerUserId}" />
		</td>
	<td class="label">
	问题状态
	</td>
	<td class="content">
	<eoms:dict key="dict-kmmanager" dictId="questionState" itemId="${kmExpertAnswer.state}" beanId="id2nameXML" />
	</td>
	</tr>
<% 
if(Answer_2||Create_1||Create_2){
%>
<tr>
<td colspan="4">

		<div class="header center">专家回复
		<%
		if(kmExpertAnswer.getAnswerTime()!=null&&!kmExpertAnswer.getAnswerTime().equals("")){
		%>
		(最后修改时间:${kmExpertAnswer.answerTime})
		<%
		}
		%>
		</div>

	</td>
	</tr>
	<tr>
		<td class="label">
			问题解答
		</td>
		<td class="content" colspan="3">
		${kmExpertAnswer.answer}


		<html:hidden property="answer" value="${kmExpertAnswer.answer}" /><br><br>
		<a id='answerUrla' href="${kmExpertAnswer.answerUrl}" target=_blank><%if(kmExpertAnswer.getAnswerUrl()!=null&&!"".equals(kmExpertAnswer.getAnswerUrl())){%>相关链接<%}%></a> 
		</td>

	</tr>
	<tr>
	<td class="label">
	解答附件
	</td>
	<td class="content" colspan="3">
	<eoms:attachment name="kmExpertAnswer" property="answerAtt" scope="request" idField="answerAtt" appCode="kmmanager" viewFlag="Y"/>	
	<html:hidden property="answerAtt" value="${kmExpertAnswer.answerAtt}" />
	</td>
	</tr>
		<%
		if(kmExpertAnswer.getScoreRemark()!=null&&!kmExpertAnswer.getScoreRemark().equals("")){
		%>
			<tr>
	<td class="label">
	最后一次解答评述
	</td>
	<td class="content" colspan="3">
	${kmExpertAnswer.scoreRemark}
	<html:hidden property="answerAtt" value="${kmExpertAnswer.scoreRemark}" />
	</td>
	</tr>
		<%
		}
		if(kmExpertAnswer.getScore()!=null&&!kmExpertAnswer.getScore().equals("")){
		%>
	<tr>
	<td class="label">
	解答评价
	</td>
	<td class="content" colspan="3">
	<eoms:dict key="dict-kmmanager" dictId="answerScore" itemId="${kmExpertAnswer.score}" beanId="id2nameXML" />
	</td>
	</tr>
		<%
		}
		%>
<%
}

if(Answer_0||Answer_1||Answer_3){
%>
<html:hidden property="state" value="1" />
<html:hidden property="operate" value="modifyAnswer" />
<html:hidden property="answerTime" value="${kmExpertAnswer.answerTime}" />
<tr>
<td colspan="4">

		<div class="header center">专家回复
		<%
		if(kmExpertAnswer.getAnswerTime()!=null&&!kmExpertAnswer.getAnswerTime().equals("")){
		%>
		(最后修改时间:${kmExpertAnswer.answerTime})
		<%
		}
		%>
		</div>

	</td>
	</tr>
	<tr>
		<td class="label">
			问题解答
		</td>
		<td class="content" colspan="3">
		<textarea name="answer" id="answer" class="textarea max"><%if(Answer_3){%>上一次答复：<%}%>${kmExpertAnswer.answer}</textarea>
		<html:hidden property="answerUrl" value="${kmExpertAnswer.answerUrl}" />
		<a id='answerUrla' href="<%=kmExpertAnswer.getAnswerUrl() %>" target=_blank><%if(kmExpertAnswer.getAnswerUrl()!=null&&!"".equals(kmExpertAnswer.getAnswerUrl())){%>相关链接<%}%></a>   <input type="button" name="answerUrlt" id="answerUrlt" value="增加(修改)帮助链接" class="btn" onclick="modifyUrl('<%=StaticMethod.null2String(kmExpertAnswer.getAnswerUrl()) %>');"/>
		</td>

	</tr>
	<tr>
	<td class="label">
	解答附件
	</td>
	<td class="content" colspan="3">
	<eoms:attachment name="kmExpertAnswer" property="answerAtt" scope="request" idField="answerAtt" appCode="kmmanager"/>	
	</td>
	</tr>


<%
}
}
if(Answer_0||Answer_1||Answer_3){
%>

<html:hidden property="state" value="1" />
<html:hidden property="operate" value="modifyAnswer" />
<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
		</td>
	</tr>
</table>
<%
}
if(Create_1){
%>
<html:hidden property="operate" value="dealAnswer" />
<tr>
<td colspan="4">
		<div class="header center">回复评价</div>
</td>
</tr>
	<tr>
		<td class="label">
			解答评述
		</td>
		<td class="content" colspan="3">
		<textarea name="scoreRemark" id="scoreRemark" class="textarea max">${kmExpertAnswer.score}</textarea>
		</td>

	</tr>
		<tr>
		<td class="label">
			处理回复
		</td>
		<td class="content">
			<eoms:dict key="dict-kmmanager" dictId="auditResult" isQuery="false" 
			           defaultId="1" selectId="dealAnswer" beanId="selectXML" 
			           alt="allowBlank:false,vtext:'请选择评论星级(字典)...'"  onchange=""/>	
		</td>
		<td class="label">
			解答评价
		</td>
		<td class="content">
			<eoms:dict key="dict-kmmanager" dictId="answerScore" isQuery="false" 
			           defaultId="1" selectId="score" beanId="selectXML" 
			           alt="allowBlank:false,vtext:'请选择评论星级(字典)...'"  onchange=""/>
		</td>

	</tr>
	</tr>
</table>
<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="<fmt:message key="button.save"/>" />
		</td>
	</tr>
</table>
<%
}
if(Create_0||Create_3){
%>
<html:hidden property="state" value="${kmExpertAnswer.state}" />
<html:hidden property="answer" value="${kmExpertAnswer.answer}" />
<html:hidden property="answer" value="${kmExpertAnswer.answerUrl}" />
<html:hidden property="answerTime" value="${kmExpertAnswer.answerTime}" />
<html:hidden property="answerAtt" value="${kmExpertAnswer.answerAtt}" />
<html:hidden property="answerAtt" value="${kmExpertAnswer.scoreRemark}" />
<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="修改" />
		</td>
	</tr>
</table>
<%
}
%>
</table>

</html:form>
<script>
	function modifyUrl(url){
	 var url =  document.getElementById("answerUrl").value;
	 window.open ('${app}/kmmanager/kmExpertAnswer.do?method=selectUrl&url='+url,'newwindow','height=600,width=600,top=0,left=0,toolbar=no,menubar=no,scrollbars=yes, resizable=no,location=no, status=no');
    }
</script>
<%@ include file="/common/footer_eoms.jsp"%>