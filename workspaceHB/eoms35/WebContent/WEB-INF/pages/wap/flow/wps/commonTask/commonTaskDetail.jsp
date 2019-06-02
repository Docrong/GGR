<%@page import="java.util.List,java.util.ArrayList,com.boco.eoms.wap.model.*" pageEncoding="UTF-8"%>
<%@ include file="/wap/common/taglibs.jsp"%>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html PUBLIC "-//WAPFORUM//DTD XHTML Mobile 1.0//EN" "http://www.wapforum.org/DTD/xhtml-mobile10.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
	<%
		Object obj = request.getAttribute("attributes");
		List attributes = (obj != null) ? (List)obj : new ArrayList();
		
		Object hiddenObj = request.getAttribute("hiddens");
		StringBuilder hiddenStr = new StringBuilder("");
		List hiddenList = hiddenObj != null ? (List) hiddenObj : new ArrayList();
		Parameter param;
		for (int i = 0; i < hiddenList.size(); i++) {
			param = (Parameter) hiddenList.get(i);
			hiddenStr.append(param.getId() + "=" + param.getValue() + "&");
		}
	 %>
<link href="${app}/wap/css/style.css" rel="stylesheet" type="text/css" />
<title>工单信息呈现页面</title>
</head>
<body >
	<form action="${app}/wap/CommonTaskAction.do?method=performDeal" method="POST">
<jsp:include page="../../../head.jsp" flush="true"/>
<table border="0" cellspacing="0" cellpadding="0" class="sub_table_bg">
  
  			<tr>
			    <td class="title_bg_01"><img src="${app}/wap/images/s_ico_01.gif"/>工单处理</td>
			  </tr>
  <tr>
  	<td>
  		<%@ include file="commonTaskDetailButtons.jsp"%> 
  	</td>
  </tr>
  <tr>
    <td class="list_text_03">
    	<div class="div_all">
    	<table width="100%" border="0" cellpadding="1" cellspacing="1" class="add_table_bg" style="table-layout:fixed;word-break:break-all">
    <%
			for(int i = 0; i < attributes.size(); i++) {
			Attribute attribute = (Attribute) attributes.get(i);
	%>
	
      <tr class="fb_xx_head_tr">
        <td class="tt_head_bg"><%=attribute.getTitle() %>:</td>
        <td class="fb_add_content"><%=attribute.getName() %></td>
        </tr>
    <%
				}
		if (("TaskCreateAuditHumTask".equalsIgnoreCase((String) request.getAttribute("taskName")) 
			|| !"ExcuteHumTask".equalsIgnoreCase((String) request.getAttribute("taskName")) 
			|| !"TaskCompleteAuditHumTask".equalsIgnoreCase((String) request.getAttribute("taskName"))
			|| !"AffirmHumTask".equalsIgnoreCase((String) request.getAttribute("taskName"))) 
			&& "2".equalsIgnoreCase((String) request.getAttribute("taskStatus"))
			&& !"HoldHumTask".equalsIgnoreCase((String) request.getAttribute("taskName"))) {
	%>
	  <tr class="fb_xx_head_tr">
   	 	 <td class="tt_head_bg"><nobr>驳回意见:</nobr></td>
   	 	 <td class="fb_add_content"><textarea name="Remark"></textarea></td>
	  </tr>
	<%} %>
    </table></div> </td>
  </tr>
  <tr>
    <td>
    	<%@ include file="commonTaskDetailButtons.jsp"%> 
	</td>
  </tr>
  
</table>
</form>
</body>
</html> 