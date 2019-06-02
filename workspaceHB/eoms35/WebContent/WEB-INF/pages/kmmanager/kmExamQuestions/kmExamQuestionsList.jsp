<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<jsp:directive.page import="com.boco.eoms.km.exam.util.KmExamSpecialtyConstants"/>

<c:set var="buttons">
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/kmExamQuestionss.do?method=getUpload'/>'"
		value="批量导入" />
	<input type="button" style="margin-right: 5px"
		onclick="location.href='<html:rewrite page='/kmExamQuestionss.do?method=download'/>'"
		value="下载导入模版" />
</c:set>

<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
<!-- 
<html:form action="/kmExamQuestionss.do?method=searchX" styleId="kmExamQuestionsForm" method="post"> 
<table align="center">
  <tr >
    <td>
		<fmt:message key="kmExamQuestions.specialtyID" />
	</td>
	<td>
		<input type="text"   id="specialtyName" name="specialtyName" class="text" readonly="readonly" value='<eoms:id2nameDB id="${kmExamQuestionsForm.specialtyID}" beanId="kmExamSpecialtyDao" />' alt="allowBlank:false'"/>
		<input type="hidden" id="specialtyID"   name="specialtyID" value="${kmExamQuestionsForm.specialtyID}" />
	</td>
	
	     <td>
			&nbsp;&nbsp;<fmt:message key="kmExamQuestions.deptId" />
		</td>
		<td>
		    <input type="text"   id="deptName" name="deptName" class="text" readonly="readonly" value='<eoms:id2nameDB id="${kmExamQuestionsForm.deptId}" beanId="tawSystemDeptDao" />' alt="allowBlank:false'"/>
			<input type="hidden" id=deptId   name="deptId" value="${kmExamQuestionsForm.deptId}" />
		</td>
	<td>
		 &nbsp;&nbsp;<fmt:message key="kmExamQuestions.questionType" />
	</td>
	 <td>
	<eoms:dict key="dict-kmmanager" dictId="questionType" isQuery="false" alt="allowBlank:false,vtext:'请选择问题类型'"
                    defaultId="${kmExamQuestionsForm.questionType}" selectId="questionType" beanId="selectXML"/>		
	 <td>		
	 <td>
		 <input type="submit" class="btn" value="<fmt:message key="kmTable.query"/>"/>		
	</td>
  </tr>
</table>
</html:form>
 -->

<b >单选题</b>
<display:table name="kmExamQuestionsList1" cellspacing="0" cellpadding="0"
		id="kmExamQuestionsList1" pagesize="${pageSize}" class="table kmExamQuestionsList"
		export="false"
		requestURI="${app}/kmmanager/kmExamQuestionss.do?method=search"
		sort="list" partialList="true" size="resultSize1">
	<display:column property="question" sortable="true"
			headerClass="sortable" titleKey="kmExamQuestions.question" href="${app}/kmmanager/kmExamQuestionss.do?method=edit" paramId="questionID" paramProperty="questionID"/>
	
	<display:column sortable="true" headerClass="sortable" titleKey="kmExamQuestions.difficulty">
	   <eoms:dict key="dict-kmmanager" dictId="difficulty" itemId="${kmExamQuestionsList1.difficulty}" beanId="id2nameXML" />			
	</display:column>
	
	<display:column sortable="true" headerClass="sortable" titleKey="kmExamQuestions.specialtyID">
		<eoms:id2nameDB id="${kmExamQuestionsList1.specialtyID}" beanId="kmExamSpecialtyDao" />
	</display:column>
	
    <display:column sortable="true" headerClass="sortable" titleKey="kmExamQuestions.deptId">
			<eoms:id2nameDB id="${kmExamQuestionsList1.deptId}" beanId="tawSystemDeptDao" />
	</display:column>
	
	<display:column sortable="true" headerClass="sortable" titleKey="kmExamQuestions.createUser">
		<eoms:id2nameDB id="${kmExamQuestionsList1.createUser}" beanId="tawSystemUserDao" />
	</display:column>
	
	<display:column property="createTime" sortable="true" format="{0,date,yyyy-MM-dd HH:mm:ss}"
			headerClass="sortable" titleKey="kmExamQuestions.createTime" href="${app}/kmmanager/kmExamQuestionss.do?method=edit" paramId="questionID" paramProperty="questionID"/>
	
		<display:setProperty name="paging.banner.item_name" value="kmExamQuestions" />
		<display:setProperty name="paging.banner.items_name" value="kmExamQuestionss" />
	</display:table>
	
<table width="100%">
	<tr align="right">  
		<td align="left">
			<input type="button" class="btn" value="添加" 
											 onclick="javascript:var questionsType = '1';
		                        			var url='${app}/kmmanager/kmExamQuestionss.do?method=add';
		                        			url = url + '&questionsType=' + questionsType ;
		                        			location.href=url"/>
		</td>
		<td align="right">
		<c:if test="${resultSize1>0}">
		    <a href="javascript:var questionsType = '1';
		                        			var url='${app}/kmmanager/kmExamQuestionss.do?method=searchForQuestions';
		                        			url = url + '&questionsType=' + questionsType ;
		                        			location.href=url">更多单选题</a>
		</c:if>
		</td>
	</tr>
</table>	
<br>
<b>多选题</b>
<display:table name="kmExamQuestionsList2" cellspacing="0" cellpadding="0"
		id="kmExamQuestionsList2" pagesize="${pageSize}" class="table kmExamQuestionsList"
		export="false"
		requestURI="${app}/kmmanager/kmExamQuestionss.do?method=search"
		sort="list" partialList="true" size="resultSize2">
	
	<display:column property="question" sortable="true"
			headerClass="sortable" titleKey="kmExamQuestions.question" href="${app}/kmmanager/kmExamQuestionss.do?method=edit" paramId="questionID" paramProperty="questionID"/>
	
	<display:column sortable="true" headerClass="sortable" titleKey="kmExamQuestions.difficulty">
	   <eoms:dict key="dict-kmmanager" dictId="difficulty" itemId="${kmExamQuestionsList2.difficulty}" beanId="id2nameXML" />			
	</display:column>
	
	<display:column sortable="true" headerClass="sortable" titleKey="kmExamQuestions.specialtyID">
		<eoms:id2nameDB id="${kmExamQuestionsList2.specialtyID}" beanId="kmExamSpecialtyDao" />
	</display:column>
	
    <display:column sortable="true" headerClass="sortable" titleKey="kmExamQuestions.deptId">
			<eoms:id2nameDB id="${kmExamQuestionsList2.deptId}" beanId="tawSystemDeptDao" />
	</display:column>
	
	<display:column sortable="true" headerClass="sortable" titleKey="kmExamQuestions.createUser">
		<eoms:id2nameDB id="${kmExamQuestionsList2.createUser}" beanId="tawSystemUserDao" />
	</display:column>
	
	<display:column property="createTime" sortable="true" format="{0,date,yyyy-MM-dd HH:mm:ss}"
			headerClass="sortable" titleKey="kmExamQuestions.createTime" href="${app}/kmmanager/kmExamQuestionss.do?method=edit" paramId="questionID" paramProperty="questionID"/>
	
		<display:setProperty name="paging.banner.item_name" value="kmExamQuestions" />
		<display:setProperty name="paging.banner.items_name" value="kmExamQuestionss" />
	</display:table>
	
<table width="100%">
	<tr align="right">  
		<td align="left">
			<input type="button" class="btn" value="添加" 
											 onclick="javascript:var questionsType = '2';
		                        			var url='${app}/kmmanager/kmExamQuestionss.do?method=add';
		                        			url = url + '&questionsType=' + questionsType ;
		                        			location.href=url"/>
		</td>
		<td align="right">
		<c:if test="${resultSize2>0}">
			<a href="javascript:var questionsType = '2';
		                        			var url='${app}/kmmanager/kmExamQuestionss.do?method=searchForQuestions';
		                        			url = url + '&questionsType=' + questionsType ;
		                        			location.href=url">更多多选题</a>
		</c:if>
		</td>
	</tr>
</table>
	
<br>
<b>判断题</b>
<display:table name="kmExamQuestionsList3" cellspacing="0" cellpadding="0"
		id="kmExamQuestionsList3" pagesize="${pageSize}" class="table kmExamQuestionsList"
		export="false"
		requestURI="${app}/kmmanager/kmExamQuestionss.do?method=search"
		sort="list" partialList="true" size="resultSize3">
	
	<display:column property="question" sortable="true"
			headerClass="sortable" titleKey="kmExamQuestions.question" href="${app}/kmmanager/kmExamQuestionss.do?method=edit" paramId="questionID" paramProperty="questionID"/>
	
	<display:column sortable="true" headerClass="sortable" titleKey="kmExamQuestions.difficulty">
	   <eoms:dict key="dict-kmmanager" dictId="difficulty" itemId="${kmExamQuestionsList3.difficulty}" beanId="id2nameXML" />			
	</display:column>
	
	<display:column sortable="true" headerClass="sortable" titleKey="kmExamQuestions.specialtyID">
		<eoms:id2nameDB id="${kmExamQuestionsList3.specialtyID}" beanId="kmExamSpecialtyDao" />
	</display:column>
	
    <display:column sortable="true" headerClass="sortable" titleKey="kmExamQuestions.deptId">
			<eoms:id2nameDB id="${kmExamQuestionsList3.deptId}" beanId="tawSystemDeptDao" />
	</display:column>
	
	<display:column sortable="true" headerClass="sortable" titleKey="kmExamQuestions.createUser">
		<eoms:id2nameDB id="${kmExamQuestionsList3.createUser}" beanId="tawSystemUserDao" />
	</display:column>
	
	<display:column property="createTime" sortable="true"  format="{0,date,yyyy-MM-dd HH:mm:ss}"
			headerClass="sortable" titleKey="kmExamQuestions.createTime" href="${app}/kmmanager/kmExamQuestionss.do?method=edit" paramId="questionID" paramProperty="questionID"/>
	
		<display:setProperty name="paging.banner.item_name" value="kmExamQuestions" />
		<display:setProperty name="paging.banner.items_name" value="kmExamQuestionss" />
	</display:table>
	
<table width="100%">
	<tr align="right">  
		<td align="left">
			<input type="button" class="btn" value="添加" 
											 onclick="javascript:var questionsType = '3';
		                        			var url='${app}/kmmanager/kmExamQuestionss.do?method=add';
		                        			url = url + '&questionsType=' + questionsType ;
		                        			location.href=url"/>
		</td>
		<td align="right">
		<c:if test="${resultSize3>0}">
			<a href="javascript:var questionsType = '3';
		                        			var url='${app}/kmmanager/kmExamQuestionss.do?method=searchForQuestions';
		                        			url = url + '&questionsType=' + questionsType ;
		                        			location.href=url">更多判断题</a>
		</c:if>
		</td>
	</tr>
</table>	
	
<br>
<b>填空题</b>	
<display:table name="kmExamQuestionsList4" cellspacing="0" cellpadding="0"
		id="kmExamQuestionsList4" pagesize="${pageSize}" class="table kmExamQuestionsList"
		export="false"
		requestURI="${app}/kmmanager/kmExamQuestionss.do?method=search"
		sort="list" partialList="true" size="resultSize4">
	
	<display:column property="question" sortable="true"
			headerClass="sortable" titleKey="kmExamQuestions.question" href="${app}/kmmanager/kmExamQuestionss.do?method=edit" paramId="questionID" paramProperty="questionID"/>
	
	<display:column sortable="true" headerClass="sortable" titleKey="kmExamQuestions.difficulty">
	   <eoms:dict key="dict-kmmanager" dictId="difficulty" itemId="${kmExamQuestionsList4.difficulty}" beanId="id2nameXML" />			
	</display:column>
	
	<display:column sortable="true" headerClass="sortable" titleKey="kmExamQuestions.specialtyID">
		<eoms:id2nameDB id="${kmExamQuestionsList4.specialtyID}" beanId="kmExamSpecialtyDao" />
	</display:column>
	
    <display:column sortable="true" headerClass="sortable" titleKey="kmExamQuestions.deptId">
			<eoms:id2nameDB id="${kmExamQuestionsList4.deptId}" beanId="tawSystemDeptDao" />
	</display:column>
	
	<display:column sortable="true" headerClass="sortable" titleKey="kmExamQuestions.createUser">
		<eoms:id2nameDB id="${kmExamQuestionsList4.createUser}" beanId="tawSystemUserDao" />
	</display:column>
	
	<display:column property="createTime" sortable="true"  format="{0,date,yyyy-MM-dd HH:mm:ss}"
			headerClass="sortable" titleKey="kmExamQuestions.createTime" href="${app}/kmmanager/kmExamQuestionss.do?method=edit" paramId="questionID" paramProperty="questionID"/>
	
		<display:setProperty name="paging.banner.item_name" value="kmExamQuestions" />
		<display:setProperty name="paging.banner.items_name" value="kmExamQuestionss" />
	</display:table>
	
<table width="100%">
	<tr align="right">  
		<td align="left">
			<input type="button" class="btn" value="添加" 
											 onclick="javascript:var questionsType = '4';
		                        			var url='${app}/kmmanager/kmExamQuestionss.do?method=add';
		                        			url = url + '&questionsType=' + questionsType ;
		                        			location.href=url"/>
		</td>
		<td align="right">
		<c:if test="${resultSize4>0}">
			<a href="javascript:var questionsType = '4';
		                        			var url='${app}/kmmanager/kmExamQuestionss.do?method=searchForQuestions';
		                        			url = url + '&questionsType=' + questionsType ;
		                        			location.href=url">更多填空题</a>
		</c:if>
		</td>
	</tr>
</table>	
	
<br>
<b>简答题</b>
<display:table name="kmExamQuestionsList5" cellspacing="0" cellpadding="0"
		id="kmExamQuestionsList5" pagesize="${pageSize}" class="table kmExamQuestionsList"
		export="false"
		requestURI="${app}/kmmanager/kmExamQuestionss.do?method=search"
		sort="list" partialList="true" size="resultSize5">
	
	<display:column property="question" sortable="true"
			headerClass="sortable" titleKey="kmExamQuestions.question" href="${app}/kmmanager/kmExamQuestionss.do?method=edit" paramId="questionID" paramProperty="questionID"/>
	
	<display:column sortable="true" headerClass="sortable" titleKey="kmExamQuestions.difficulty">
	   <eoms:dict key="dict-kmmanager" dictId="difficulty" itemId="${kmExamQuestionsList5.difficulty}" beanId="id2nameXML" />			
	</display:column>
	
	<display:column sortable="true" headerClass="sortable" titleKey="kmExamQuestions.specialtyID">
		<eoms:id2nameDB id="${kmExamQuestionsList5.specialtyID}" beanId="kmExamSpecialtyDao" />
	</display:column>
	
    <display:column sortable="true" headerClass="sortable" titleKey="kmExamQuestions.deptId">
			<eoms:id2nameDB id="${kmExamQuestionsList5.deptId}" beanId="tawSystemDeptDao" />
	</display:column>
	
	<display:column sortable="true" headerClass="sortable" titleKey="kmExamQuestions.createUser">
		<eoms:id2nameDB id="${kmExamQuestionsList5.createUser}" beanId="tawSystemUserDao" />
	</display:column>
	
	<display:column property="createTime" sortable="true"  format="{0,date,yyyy-MM-dd HH:mm:ss}"
			headerClass="sortable" titleKey="kmExamQuestions.createTime" href="${app}/kmmanager/kmExamQuestionss.do?method=edit" paramId="questionID" paramProperty="questionID"/>
	
		<display:setProperty name="paging.banner.item_name" value="kmExamQuestions" />
		<display:setProperty name="paging.banner.items_name" value="kmExamQuestionss" />
	</display:table>
	
<table width="100%">
	<tr align="right">  
		<td align="left">
			<input type="button" class="btn" value="添加" 
											 onclick="javascript:var questionsType = '5';
		                        			var url='${app}/kmmanager/kmExamQuestionss.do?method=add';
		                        			url = url + '&questionsType=' + questionsType ;
		                        			location.href=url"/>
		</td>
		<td align="right">
		<c:if test="${resultSize5>0}">
			<a href="javascript:var questionsType = '5';
		                        			var url='${app}/kmmanager/kmExamQuestionss.do?method=searchForQuestions';
		                        			url = url + '&questionsType=' + questionsType ;
		                        			location.href=url">更多简答题</a>
		</c:if>
		</td>
	</tr>
</table>	
	
	<br><br>
	<c:out value="${buttons}" escapeXml="false" />

</fmt:bundle>
<%-- 
    <eoms:xbox id="tree" dataUrl="${app}/kmmanager/kmExamSpecialtys.do?method=getNodesRadioTree" 
	  	rootId="<%=KmExamSpecialtyConstants.TREE_ROOT_ID%>" 
	  	rootText='专业' 
	  	valueField="specialtyID" handler="specialtyName"
		textField="specialtyName"
		checktype="forums" single="true"		
	  ></eoms:xbox>
	  
	  <eoms:xbox id="tree1" dataUrl="${app}/xtree.do?method=dept" 
	  	rootId="1" 
	  	rootText='部门树' 
	  	valueField="deptId" handler="deptName"
		textField="deptName"
		checktype="dept" single="true"		
	  ></eoms:xbox>	
--%>
<!-- 
<script type="text/javascript">
var init = function(){
	xbox_tree = new xbox({"showChkFldId":"specialtyName","treeRootText":"专业","treeRootId":"<%=KmExamSpecialtyConstants.TREE_ROOT_ID%>","saveChkFldId":"specialtyID","treeDataUrl":"${app}/kmmanager/kmExamSpecialtys.do?method=getNodesRadioTree","btnId":"specialtyName","checktype":"forums","id":"tree","single":true});
	xbox_tree1 = new xbox({"showChkFldId":"deptName","treeRootText":"部门树","treeRootId":"1","saveChkFldId":"deptId","treeDataUrl":"${app}/xtree.do?method=dept","btnId":"deptName","checktype":"dept","id":"tree1","single":true});	
}
Ext.onReady(function(){window.setTimeout(init,10)});
</script>
 -->
<%@ include file="/common/footer_eoms.jsp"%>