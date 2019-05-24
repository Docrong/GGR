<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmAskQuestionForm'});
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
	//提问
	function add(){
		var score = document.getElementById('score').value;
		var countScore = document.getElementById('countScore').value;
		if(parseInt(score)>parseInt(countScore)){
			alert('悬赏分不能超过您的当前积分');
			$("score").focus();
			return false;	
		}
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
</html:form>

<div>
<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
	<b>您寻找的答案可能在下面</b>
	<display:table name="kmAskQuestionList" cellspacing="0" cellpadding="0"
		id="kmAskQuestionList" pagesize="${pageSize}" class="table kmAskQuestionList"
		export="false"
		requestURI="${app}/kmmanager/kmAskQuestions.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="name" sortable="true"
			headerClass="sortable" titleKey="kmAskQuestion.name" href="${app}/kmmanager/kmAskQuestions.do?method=edit" paramId="id" paramProperty="id"/>


	<display:column property="speciality" sortable="true"
			headerClass="sortable" titleKey="kmAskQuestion.speciality"  paramId="id" paramProperty="id"/>

	<display:column property="answerCount" sortable="true"
			headerClass="sortable" titleKey="kmAskQuestion.answerCount"  paramId="id" paramProperty="id"/>

	<display:column property="askTime" sortable="true"
			headerClass="sortable" titleKey="kmAskQuestion.askTime"  paramId="id" paramProperty="id"/>

	<display:column property="score" sortable="true"
			headerClass="sortable" titleKey="kmAskQuestion.score"  paramId="id" paramProperty="id"/>

		<display:setProperty name="paging.banner.item_name" value="kmAskQuestion" />
		<display:setProperty name="paging.banner.items_name" value="kmAskQuestions" />
	</display:table>
	<table width="100%">
		
	</table>
</fmt:bundle>	
</div>
<br>

<eoms:xbox id="tree" dataUrl="${app}/kmmanager/kmAskSpecialitys.do?method=getNodesRadioTree" 
	  	rootId="1" 
	  	rootText='专业' 
	  	valueField="speciality" handler="specialityName"
		textField="specialityName"
		checktype="forums" single="true"		
	  ></eoms:xbox>

<div>
<b>如果您没有找到合适的回答，请继续提问</b><br><br>
<html:form action="/kmAskQuestions.do?method=save" styleId="kmAskQuestionForm" method="post"> 
<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
<table class="formTable">
	<tr>
		<td class="label">
			<fmt:message key="kmAskQuestion.name" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content" colspan="3">
			<html:text property="name" styleId="name"
						styleClass="text max" maxlength="50"
						alt="allowBlank:false,vtext:''" value="${kmAskQuestionForm.name}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskQuestion.question" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content" colspan="3">
			<textarea name="question" id="question" 
			          cols="50" class="textarea max" 
			          alt="allowBlank:false,vtext:'',maxLength:2000">${kmAskQuestionForm.question}</textarea>	
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskQuestion.speciality" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content" >
			<input type="text"   id="specialityName" name="specialityName" class="text max"  value='<eoms:id2nameDB id="${kmAskQuestionForm.speciality}" beanId="kmAskSpecialtyDao" />' alt="allowBlank:false,vtext:''"/>
			<input type="hidden" id="speciality"   name="speciality" value="${kmAskQuestionForm.speciality}" />
		</td>
		
		<td class="label">
			<fmt:message key="kmAskQuestion.score" />
		</td>
		<td class="content">
			<html:text property="score" styleId="score" 
						styleClass="text radio"  alt="allowBlank:true,vtext:'',vtype:'number',maxLength:5"
						 value="${kmAskQuestionForm.score}" />
			您的积分是${countScore}
			<html:hidden property="countScore" styleId="countScore"
						styleClass="text radio"  
						 value="${countScore}" />
		</td>
	</tr>

	<tr>
		<td class="label">
			<fmt:message key="kmAskQuestion.isAnonymity" />&nbsp;<font color='red'>*</font>
		</td>
		<td class="content" >
			<eoms:dict key="dict-kmmanager" dictId="isOrNot" isQuery="false" 
			           defaultId="" selectId="isAnonymity" beanId="selectXML" 
			           alt="allowBlank:false,vtext:'请选择是否匿名(字典)...'"/>
		</td>
	</tr>
</table>
</fmt:bundle>

<table>
	<tr>
		<td>
			<input type="button" class="btn" value="<fmt:message key="button.save"/>" onclick="add()"/>
		</td>
	</tr>
</table>
<html:hidden property="id" value="${kmAskQuestionForm.id}" />
</html:form>
</div>
<%@ include file="/common/footer_eoms.jsp"%>