<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@ page import="java.util.List,
                 com.boco.eoms.mobilewap.webtool.bo.WebToolBO,
                 com.boco.eoms.mobilewap.base.tag.imp.WapAnchorTag,
                 com.boco.eoms.mobilewap.base.tag.imp.WapPostField" %>
<%@ page autoFlush="true" %>
<%
WapAnchorTag tag = (WapAnchorTag)request.getAttribute("anchor");

String wapNodeId=(String)request.getAttribute("wapNodeId");
String wapCardId = (String)request.getAttribute("wapCardId");
String app = request.getContextPath();  




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
	   <td align="right">
	     <a href="<%=app %>/webtool/card?act=view&flag=modify&wapCardId=<%=wapCardId%>&wapNodeId=<%=wapNodeId%>">页面标签列表</a>
	    |<a href="<%=app %>/webtool/tag?act=view&flag=modify&wapCardId=<%=wapCardId%>&wapNodeId=<%=wapNodeId%>&tagKey=<%=request.getParameter("tagKey") %>&tagType=anchor&class_str=<%=tag.getClassStr()%>">修改此标签</a>
	    |<a href="<%=app %>/webtool/tag?act=remove&wapCardId=<%=wapCardId%>&tagKey=<%=request.getParameter("tagKey")%>&wapNodeId=<%=wapNodeId%>">删除此标签</a>
	   </td>
	 </tr>
	</table>
	<table class="form_table">
	  <tr>
			<th width="30%">提交地址：</td>
			<td><%=tag.getHrefCardId()%></td>
	  </tr>
	  <tr >
			<th width="30%">超链接文字：</td>
            <td><%=tag.getText() %></td>
	  </tr>
	</table>
	<br/>
	<table class="form_table">
	<tr><td  class="nobg">参数列表</td></tr>
	</table>
	<%if(tag.getPostField()!=null) 
	     for(int j=0; j<tag.getPostField().size(); j++){
	     WapPostField pfVO = (WapPostField)tag.getPostField().get(j);
	     String idVal = "str" + j;
	 %>
        <table width="100%" class="form_table">
          <tr>
              <th width="18%">参数名称</td>
              <td width="27%"><%=pfVO.getName()%></td>
              <th width="13%">参数值</td>
              <td width="27%"><%=pfVO.getValue()%></td>
          </tr>
        </table>
    <%}%>
</form>
</body>
</html>
