<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@ page import="java.util.List,
                 com.boco.eoms.mobilewap.webtool.bo.WebToolBO,
                 com.boco.eoms.mobilewap.base.tag.imp.WapSelectStaticTag,
                 com.boco.eoms.mobilewap.base.tag.imp.WapSelectOption" %>
<%@ page autoFlush="true" %>
<%
WapSelectStaticTag tag = (WapSelectStaticTag)request.getAttribute("selectStatic");

String wapNodeId=(String)request.getAttribute("wapNodeId");
String wapCardId = (String)request.getAttribute("wapCardId");
String app = request.getContextPath();
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
	   <td class="title">动态下拉框信息</td>
	   <td align="right">
	     <a href="<%=app %>/webtool/card?act=view&flag=modify&wapCardId=<%=wapCardId%>">页面标签列表</a>
	    |<a href="<%=app %>/webtool/tag?act=view&flag=modify&wapCardId=<%=wapCardId%>&wapNodeId=<%=wapNodeId%>&tagKey=<%=request.getParameter("tagKey") %>&tagType=selectstatic&class_str=<%=tag.getClassStr()%>">修改此标签</a>
	    |<a href="<%=app %>/webtool/tag?act=remove&wapCardId=<%=wapCardId%>&tagKey=<%=request.getParameter("tagKey")%>&wapNodeId=<%=wapNodeId%>">删除此标签</a>
       </td>
	 </tr>
  </table>
  <table class="form_table">
	<tr>
		<th width="30%">下拉框名称：</td>
		<td><%=tag.getName() %></td>
	</tr>
	<tr >
		<th width="30%">是否为多选：</td>
        <td>
	        <%if(tag.getMultiple().equals("false"))out.println("单选");
	          else out.println("多选"); %>
        </td>
	</tr>
	<tr>
		<th width="30%" >option参数列表：</td>
		<td></td>
	</tr>
</table>
<table width="100%" class="form_table">
<%if(tag.getOptions()!=null) 
    for(int j=0; j<tag.getOptions().size(); j++){
    WapSelectOption pfVO = (WapSelectOption)tag.getOptions().get(j);
%>
     <tr>
         <th width="10%">值:</td>
         <td width="23%"><%=pfVO.getVaule()%></td>
         <th width="10%">文本:</td>
         <td width="23%"h><%=pfVO.getText()%></td>			                
         <th width="10%">提交地址：</td>
      <td width="23%"><%=pfVO.getOnpick()%></td>
     </tr>
<%}%>
</table>
</form>
</body>
</html>
