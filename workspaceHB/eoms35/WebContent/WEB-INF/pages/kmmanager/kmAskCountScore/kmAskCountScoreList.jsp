<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.util.List"%>

<table class="formTable">
	<caption>
		<div class="header center">问答积分排名</div>
	</caption>
  <tr>
  	<td class="label">排行</td>
    <td class="label">用户名</td>
    <td class="label">总积分</td>
  </tr>
 <c:forEach items="${kmAskCountScoreList}" var="item" varStatus="status" begin="0" step="1">
 	<tr>
  	<td class="content">${status.count}</td>
  	<td class="content">${item.userName}</td>
  	<td class="content">${item.countScore}</td>
    </tr>
 </c:forEach>

</table>
<%@ include file="/common/footer_eoms.jsp"%>