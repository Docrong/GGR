<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@ page import="java.util.List,
                 com.boco.eoms.mobileWap.common.StaticMothod,
                 com.boco.eoms.mobilewap.webtool.bo.WebToolBO,
                 com.boco.eoms.mobilewap.tag.imp.SubmitTag,
                 com.boco.eoms.mobilewap.tag.vo.PostFieldVO" %>
<%@ page autoFlush="true" %>
<%
SubmitTag tag = (SubmitTag)request.getAttribute("submit");
String wapNodeId=tag.getCardId().substring(tag.getCardId().indexOf("_")+1,tag.getCardId().length());
String app = request.getContextPath(); 
WebToolBO webtool = new WebToolBO(request);
List arrayList = webtool.getNodeVariableList();
StaticMothod sm = new StaticMothod(request);
int n=0;
 %>
<html>
<head>
<title>管理员WAP配置工具</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<body>
  <table width="100%">
	 <tr>
	   <td class="title">提交信息</td>
	   <td align="right">
	     <a href="<%=app %>/webtool/card?act=view&flag=modify&cardId=<%=tag.getCardId()%>">页面标签列表</a>
	    |<a href="<%=app %>/webtool/tag?act=view&flag=modify&cardId=<%=tag.getCardId()%>&wapNodeId=<%=wapNodeId%>&tagKey=<%=request.getParameter("tagKey") %>&tagType=submit&class_str=<%=tag.getClass_str()%>">修改此标签</a>
	    |<a href="<%=app %>/webtool/tag?act=remove&cardId=<%=tag.getCardId()%>&tagKey=<%=request.getParameter("tagKey") %>">删除此标签</a>
       </td>
	 </tr>
  </table>
  <table width="100%" class="form_table">
		<tr>
			<th width="30%">提交地址：</td>
			<td><%=sm.cardId2cardName(tag.getHrefCardId()) %></td>
		</tr>
	</table>
			<br/>
	<table width="100%" class="form_table">
			<tr><td  class="nobg">参数列表</td></tr>
	</table>
    <table width="100%" class="form_table">
	<%if(tag.getPostField()!=null) 
	     for(int j=0; j<tag.getPostField().size(); j++){
	     PostFieldVO pfVO = (PostFieldVO)tag.getPostField().get(j);
	 %>			        
	            <tr>
	                <th width="20%">参数名称</td>
	                <td width="30%"><%=pfVO.getName()%></td>
	                <th width="20%">参数值</td>
	                <td width="30%"><%=pfVO.getValue()%></td>
	            </tr>
     <%}%>			
 </table>
</body>
</html>
