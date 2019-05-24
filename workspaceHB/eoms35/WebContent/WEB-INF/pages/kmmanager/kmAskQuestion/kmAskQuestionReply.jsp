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
	
	//将问题设为投票状态
	function setVote(){
		var questionId = document.getElementById('id').value;
		var questionStatus = "2";
		document.forms[1].action = "${app}/kmmanager/kmAskQuestions.do?method=setVote&questionStatus="+questionStatus+"&questionId="+questionId;
  		document.forms[1].submit();  
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
<b>待解决</b><br><br>
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
<html:hidden property="id" value="${kmAskQuestionForm.id}" />
<!-- 当待解决的问题有两个以上答案的时候提问者可以将问题设置为投票状态  根据投票来选择答案 -->
<c:if test="${kmAskQuestionForm.askUser ==sessionScope.sessionform.userid}">
	<c:if test="${replaySize > 1}">
		<table>
			<tr><td>
	    		<input type="button" class="btn" value="设为投票" onclick="setVote()"/>
			</td></tr>
  		</table>
	</c:if>
</c:if> 
</fmt:bundle>
</html:form>

<!-- 问答人自己不能对问题进行回答 -->
<c:if test="${kmAskQuestionForm.askUser !=sessionScope.sessionform.userid}"></c:if> 
<br><br><br>
<b>您可以对该问题作答</b>
<br><br>
<html:form action="/kmAskReplys.do?method=save" styleId="kmAskReplyForm" method="post"> 
<table class="formTable">
	<tr>
		<td class="label">
			我来回答：&nbsp;<font color='red'>*</font>
		</td>
		<td class="content" colspan="3">
			<textarea name="answer" id="answer" 
			          cols="50" class="textarea max" 
			          alt="allowBlank:false,vtext:'',maxLength:2000">${kmAskReplyForm.answer}</textarea>	
		</td>
	</tr>
	<tr>
		<td class="label">
			参考附件
		</td>
		<td class="content" >
			<eoms:attachment name="kmAskReplyForm" property="fileName" scope="request" idField="fileName" appCode="kmmanager" />
		</td>
		
		<td class="label">
			 是否匿名&nbsp;<font color='red'>*</font>
		</td>
		<td class="content" >
			<eoms:dict key="dict-kmmanager" dictId="isOrNot" isQuery="false" 
			           defaultId="" selectId="isAnonymity" beanId="selectXML" 
			           alt="allowBlank:false,vtext:'请选择是否匿名(字典)...'"/>
		</td>
	</tr>
</table>
	<!-- 保存答案的提交的问答id -->
	<input type="hidden" id="questionId" name="questionId" value="${questionId }">
<table>
	<tr><td>
	<input type="submit" class="btn" value="提交回答" />
	</td></tr>
</table>
</html:form> 

<br><br>
<c:if test="${replaySize != 0}">
<b>其它回答</b>
</c:if>
<c:forEach items="${kmAskReplyList}" var="item">
<html:form action="/kmAskReplys.do?method=setBest" styleId="${item.id}" method="post"> 
	<br>
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
		
	<!-- 设为最佳答案id -->
	<input type="hidden" id="id" name="id" value="${item.id }">
	<c:if test="${kmAskQuestionForm.askUser==sessionScope.sessionform.userid}">
   		<table>
			<tr><td>
	    		<input type="submit" class="btn" value="设为最佳答案" />
			</td></tr>
  		</table>
  	</c:if>
   <br>
</html:form>
</c:forEach>
<br><br>
<html:hidden property="id" value="${kmAskQuestionForm.id}" />
<%@ include file="/common/footer_eoms.jsp"%>