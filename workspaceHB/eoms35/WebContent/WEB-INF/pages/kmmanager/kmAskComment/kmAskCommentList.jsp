<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'123'});
});

</script>

<div>
<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
	<b>该问题评论</b>
	<display:table name="kmAskCommentList" cellspacing="0" cellpadding="0"
		id="kmAskCommentList" pagesize="${pageSize}" class="table kmAskCommentList"
		export="false"
		requestURI="${app}/kmmanager/kmAskComments.do?method=search"
		sort="list" partialList="true" size="resultSize">

	<display:column  sortable="false"	headerClass="sortable" titleKey="kmAskComment.commentUser" >
		<eoms:id2nameDB id="${kmAskCommentList.commentUser}" beanId="tawSystemUserDao" />
	</display:column>

	<display:column property="commentDate" sortable="false"  format="{0,date,yyyy-MM-dd HH:mm:ss}"
			headerClass="sortable" titleKey="kmAskComment.commentDate"  paramId="id" paramProperty="id"/>

	<display:column  sortable="false" headerClass="sortable" titleKey="kmAskComment.commentDept" >
		<eoms:id2nameDB id="${kmAskCommentList.commentDept}" beanId="tawSystemDeptDao" />
	</display:column>

	<display:column property="content" sortable="false"
			headerClass="sortable" titleKey="kmAskComment.comment"  paramId="id" paramProperty="id"/>

		<display:setProperty name="paging.banner.item_name" value="kmAskComment" />
		<display:setProperty name="paging.banner.items_name" value="kmAskComments" />
	</display:table>
	<table width="100%">
		
	</table>
</fmt:bundle>	
</div>
<br>

<div>
<b><br><br>
<html:form action="/kmAskComments.do?method=save" styleId="123" method="post"> 
<fmt:bundle basename="com/boco/eoms/km/config/applicationResource-kmmanager">
<table class="formTable">
	<tr>
		<td class="label">
			我来评论
		</td>
		<td class="content">
			<textarea name="content" id="content" 
			          cols="50" class="textarea max" 
			          alt="allowBlank:false,vtext:'',maxLength:50">${kmAskCommentForm.content}</textarea>	
		</td>
	</tr>
</table>
	
<table>
	<tr>
		<td>
			<input type="submit" class="btn" value="提交评论" />
		</td>
	</tr>
</table>
<input type="hidden" id="questionId" name="questionId" value="${questionId}"/>
<html:hidden property="id" value="${kmAskCommentForm.id}" />
</fmt:bundle>
</html:form>

</div>
<%@ include file="/common/footer_eoms.jsp"%>