<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmAskQuestionForm'});
	w = new eoms.form.Validation({form:'kmAskReplyForm'});
});

   //搜索答案
	function search(){
		var name = document.getElementById('name').value;
		if(name==""){
			alert('请先填写要搜索的内容');
			return false;
		}
		var questionStatus = "1";
		document.forms[0].action = "${app}/kmmanager/kmAskQuestions.do?method=searchByName&questionStatus="+questionStatus;
  		document.forms[0].submit();   
	}
	
	//我要提问
	function ask(){
		var name = document.getElementById('name').value;
		if(name==""){
			alert('请先填写要提问的内容');
			return false;
		}
		var questionStatus = "1";
		document.forms[0].action = "${app}/kmmanager/kmAskQuestions.do?method=add&questionStatus="+questionStatus;
  		document.forms[0].submit();   
	}
	
	//我要回答
	function answer(){
		var name = document.getElementById('name').value;
		if(name==""){
			alert('请先填写要回答的内容');
			return false;
		}
		var questionStatus = "0";
		document.forms[0].action = "${app}/kmmanager/kmAskQuestions.do?method=searchByName&questionStatus="+questionStatus;
  		document.forms[0].submit();   
	}
	
	//评论
	function comment(){
		var questionId = document.getElementById('questionId').value;
		window.open("${app}/kmmanager/kmAskComments.do?method=search&questionId="+questionId,"查看评论"); 
	}
	
</script>

<html:form action="/kmAskQuestions.do?method=searchX" styleId="kmAskQuestionForm1" method="post"> 
<table align="center" >
  <tr >
  	<td>
  		<b>知识问答</b>&nbsp&nbsp
  	</td>
	 <td>
		<input type="text" style="WIDTH: 250px" id="name" name="name" class="text" maxlength="50" value="${kmAskQuestionForm.name}">
	</td>
	<td></td>
  </tr>
  
  <tr>
  <td></td>
  <td>
	<input type="button" class="btn" value="搜索答案" onclick="search()"/>&nbsp&nbsp
	 <input type="button" class="btn" value="我要提问" onclick="ask()"/>&nbsp&nbsp
	 <input type="button" class="btn" value="我要回答" onclick="answer()"/>
  </td>
  </tr>
</table>
<br><br>
</html:form>

<html:form action="/kmAskQuestions.do?method=save" styleId="kmAskQuestionForm" method="post"> 
<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
<b>已解决</b><br><br>
<table class="formTable">
	<tr>
		<td class="label">
			<fmt:message key="kmAskQuestion.name" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content" colspan="3">
			<html:text property="name" styleId="name"
						styleClass="text max" readonly="true"
						 value="${kmAskQuestionForm.name}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskQuestion.question" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content" colspan="3">
			<textarea name="question" id="question"  readonly="readonly"
			          cols="50" class="textarea max" 
			          alt="allowBlank:false,vtext:''">${kmAskQuestionForm.question}</textarea>	
		</td>
	</tr>

	<tr>		
		<td class="label">
			<fmt:message key="kmAskQuestion.score" />
		</td>
		<td class="content">
			${kmAskQuestionForm.score}
		</td>
		<td class="label">
			<fmt:message key="kmAskQuestion.askUser" />
		</td>
		<td class="content">
			<c:if test="${kmAskQuestionForm.isAnonymity==0}">
				<eoms:id2nameDB id="${kmAskQuestionForm.askUser}" beanId="tawSystemUserDao" />
			</c:if>
			<c:if test="${kmAskQuestionForm.isAnonymity==1}">
				匿名
			</c:if>
		</td>
	</tr>
</table>
</fmt:bundle>
</html:form>
<br><br><br>
<b>最佳答案</b>
<br><br>
<html:form action="/kmAskReplys.do?method=save" styleId="kmAskReplyForm" method="post"> 
<table class="formTable">
	<c:forEach items="${kmAskReplyList}" var="item">
		<c:if test="${item.isBest==1}">
		<table class="formTable">
		<tr>
		<td class="label">
			回答者：
			<c:if test="${item.isAnonymity==0}">
				<eoms:id2nameDB id="${item.answerUser}" beanId="tawSystemUserDao" />
			</c:if>
			<c:if test="${item.isAnonymity==1}">
				匿名
			</c:if>
		</td>
		<td class="content" colspan="3">
			<textarea name="answer" id="answer" 
			          cols="50" class="textarea max" readonly="readonly"
			          alt="allowBlank:false,vtext:''">${item.answer}</textarea>	
		</td>
	   </tr>
	    </table>
		<br>
		</c:if>
	</c:forEach>
	</table>
	
	<!-- 问答id -->
	<input type="hidden" id="questionId" name="questionId" value="${kmAskQuestionForm.id }">
	<c:if test="${kmAskQuestionForm.askUser==sessionScope.sessionform.userid}">
   		<table >
			<tr ><td>
	    		 <input type="button" class="btn" value="评论" onclick="comment()"/>&nbsp&nbsp
			</td></tr>
  		</table>
   </c:if>
<br><br>
</html:form>


	<div>
	<b>其它答案</b>
	<br><br>
	<table class="formTable">
	<c:forEach items="${kmAskReplyList}" var="item">
		<c:if test="${item.isBest==0}">
		<table class="formTable">
		<tr>
		<td class="label">
			回答者：
			<c:if test="${item.isAnonymity==0}">
				<eoms:id2nameDB id="${item.answerUser}" beanId="tawSystemUserDao" />
			</c:if>
			<c:if test="${item.isAnonymity==1}">
				匿名
			</c:if>
		</td>
		<td class="content" colspan="3">
			<textarea name="answer" id="answer" 
			          cols="50" class="textarea max"  readonly="readonly"
			          alt="allowBlank:false,vtext:''">${item.answer}</textarea>	
		</td>
	   </tr>
	    </table>
		<br>
		</c:if>
	</c:forEach>
	</table>
	</div>

<html:hidden property="id" value="${kmAskQuestionForm.id}" />
<%@ include file="/common/footer_eoms.jsp"%>