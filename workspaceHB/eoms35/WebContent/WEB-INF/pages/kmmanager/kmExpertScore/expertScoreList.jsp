<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="com.boco.eoms.km.expert.model.KmExpertScore"%>
<%@ page import="com.boco.eoms.base.util.StaticMethod"%>
<%
List result = (List)request.getAttribute("expertScores");
String treeType = StaticMethod.nullObject2String(request.getAttribute("treeType"));
if(result.size()==0){
	if("uninputed".equals(treeType)){
%>
本月无未打分专家记录
<%
}else{
%>
本月无已打分专家记录
<%
	}
}else{
%>
<script type="text/javascript">
Ext.onReady(function() {
	v = new eoms.form.Validation({form:'kmExpertScoreForm'});
	});
</script>
<form action="${app}/kmmanager/expertScore.do?method=saveInputScore" name="kmExpertScoreForm" method="post">	
<table class="formTable">
	<caption>
		<div class="header center">当月专家积分录入</div>
	</caption>
  <tr>
    <td class="label">专家姓名</td>    
    <td class="label">专家基础分</td>
    <td class="label">坐诊答复数</td>
    <td class="label">答复满意度</td>
    <td class="label">知识贡献</td>
    <td class="label">主观积分</td>
  </tr>
    <% 
    for(int i=0;i<result.size();i++){
    	KmExpertScore kmExpertScore = (KmExpertScore)result.get(i);
	%>
	<tr>
	  <td><eoms:id2nameDB id="<%=kmExpertScore.getExpertUserId() %>" beanId="tawSystemUserDao" /></td>
	  <td><%=kmExpertScore.getBaseScore() %></td>
	  <td><%=kmExpertScore.getAnswerNum() %></td>
	  <td><%=kmExpertScore.getAnswerSati() %></td>
	  <td><%=kmExpertScore.getKnowledgeScore() %></td>
	  <td>
	  <input type="hidden" name="id" value="<%=kmExpertScore.getId() %>" />
	  <input type="hidden" name="userId" value="<%=kmExpertScore.getExpertUserId() %>" />
	  <input type="hidden" name="baseScore" value="<%=kmExpertScore.getBaseScore() %>" />
	  <input type="hidden" name="answerNum" value="<%=kmExpertScore.getAnswerNum() %>" />
	  <input type="hidden" name="answerSati" value="<%=kmExpertScore.getAnswerSati() %>" />
	  <input type="hidden" name="knowledgeScore" value="<%=kmExpertScore.getKnowledgeScore() %>" />
	  <input name="inputScore" type="text" size="5" value="<%=StaticMethod.nullObject2String(kmExpertScore.getInputScore()) %>" alt="vtype:'number'">
	  </td>
	<%
    	}
    %>
  </tr>
</table>
	<table>
		<tr>
			<td>
				<input type="submit" class="btn" value='<fmt:message key="button.save"/>' />
			</td>
		</tr>
	</table>
</form>
<%
}
%>
<%@ include file="/common/footer_eoms.jsp"%>