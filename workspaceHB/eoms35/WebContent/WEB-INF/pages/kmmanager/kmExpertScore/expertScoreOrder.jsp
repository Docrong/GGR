<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="com.boco.eoms.common.util.StaticMethod"%>
<%@ page import="com.boco.eoms.km.expert.model.KmExpertScore"%>

<%@ page import="java.util.List"%>
<%
List scorelist = (List)request.getAttribute("scoresOrder");
%>

<table class="formTable">
	<caption>
		<div class="header center">专家上月累计积分排行</div>
	</caption>
  <tr>
  	<td class="label">排行</td>
    <td class="label">专家姓名</td>
    <td class="label">上月累计积分</td>
  </tr>
  <%
for(int i=0;i<scorelist.size();i++){
	KmExpertScore kmExpertScore = (KmExpertScore)scorelist.get(i);
%>
  <tr>
  	<td class="content"><%=i+1 %></td>
    <td class="content"><eoms:id2nameDB id="<%=kmExpertScore.getExpertUserId() %>" beanId="tawSystemUserDao" /></td>
    <td class="content"><%=kmExpertScore.getInputScore() %></td>
  </tr>

<%
}
%>

</table>
<%@ include file="/common/footer_eoms.jsp"%>