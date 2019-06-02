<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>

<script type="text/javascript">
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
		
</script>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
<html:form action="/kmAskQuestions.do?method=searchX" styleId="kmAskQuestionForm" method="post"> 
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
	
	
	<display:table name="kmAskQuestionList" cellspacing="0" cellpadding="0"
		id="kmAskQuestionList" pagesize="${pageSize}" class="table kmAskQuestionList"
		export="false"
		requestURI="${app}/kmmanager/kmAskQuestions.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column property="name" sortable="true"
			headerClass="sortable" titleKey="kmAskQuestion.name" href="${app}/kmmanager/kmAskQuestions.do?method=attend" paramId="id" paramProperty="id"/>


	<display:column  sortable="false" headerClass="sortable" titleKey="kmAskQuestion.speciality" >
		<eoms:id2nameDB id="${kmAskQuestionList.speciality}" beanId="kmAskSpecialityDao" />
	</display:column>

	<display:column property="answerCount" sortable="false"
			headerClass="sortable" titleKey="kmAskQuestion.answerCount"  paramId="id" paramProperty="id"/>

	<display:column  sortable="false" headerClass="sortable" titleKey="kmAskQuestion.questionStatus" >
		<eoms:dict key="dict-kmmanager" dictId="questionStatus" itemId="${kmAskQuestionList.questionStatus}" beanId="id2nameXML" />	
	</display:column>

	<display:column property="isAddScore" sortable="false"
			headerClass="sortable" titleKey="kmAskQuestion.askTime"  paramId="id" paramProperty="id"/>

	<display:column property="score" sortable="true"
			headerClass="sortable" titleKey="kmAskQuestion.score"  paramId="id" paramProperty="id"/>

		<display:setProperty name="paging.banner.item_name" value="kmAskQuestion" />
		<display:setProperty name="paging.banner.items_name" value="kmAskQuestions" />
	</display:table>
	
</fmt:bundle>

<%@ include file="/common/footer_eoms.jsp"%>