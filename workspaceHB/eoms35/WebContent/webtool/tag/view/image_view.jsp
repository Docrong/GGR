<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@ page import="com.boco.eoms.mobilewap.base.tag.imp.WapImageTag" %>
<%@ page autoFlush="true" %>
<%
WapImageTag tag = (WapImageTag)request.getAttribute("image");

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
	   <td class="title">图片信息</td>
	   <td align="right">
	     <a href="<%=app %>/webtool/card?act=view&flag=modify&wapCardId=<%=wapCardId%>">页面标签列表</a>
	    |<a href="<%=app %>/webtool/tag?act=view&flag=modify&wapCardId=<%=wapCardId%>&wapNodeId=<%=wapNodeId%>&tagKey=<%=request.getParameter("tagKey") %>&tagType=image&class_str=<%=tag.getClassStr()%>">修改此标签</a>
        |<a href="<%=app %>/webtool/tag?act=remove&wapCardId=<%=wapCardId%>&tagKey=<%=request.getParameter("tagKey")%>&wapNodeId=<%=wapNodeId%>">删除此标签</a>
       </td>
	 </tr>
	</table>
	<table class="form_table">
    <tr>
      <th width="30%" >图片名称：</td>
      <td><%=tag.getAlt()%></td>
    </tr>
    <tr >
      <th width="30%" >图片位置：</td>
      <td>
      <%if(tag.getAlign().equals("middle"))out.println("中间");
       else if(tag.getAlign().equals("top"))out.println("顶部");
       else if(tag.getAlign().equals("bottom"))out.println("底部");
      %>
	  </td>
    </tr>
	 <tr >
      <th width="30%" >图片宽度：</td>
      <td>
           <%=tag.getWidth()%>pix
	  </td>
    </tr>
	<tr >
      <th width="40%" >图片高度：</td>
      <td>
          <%=tag.getHeight() %>pix
	  </td>
    </tr>
    <tr>
    	<th width="30%" >图片路径：</td>
    	<td><%=tag.getSrc() %></td>
    </tr>
    <tr >
      <th width="30%" >图片类型：</td>
      <td>
            <%
            if(tag.getImageType().equals("auto"))
              		out.println("正常本地上传"); 
              else if(tag.getType().equals("cake"))
              		out.println("网管数据饼图"); 
              else if(tag.getType().equals("line"))
              		out.println("网管数据线图");
              else if(tag.getType().equals("pole"))
              		out.println("网管数据柱图");
            %>
	  </td>
    </tr>
<%if(tag.getImageType().equals("auto")){ %>
    <table class="form_table"> 
      <tr>
        <th><img alt="没有上传图片" src="<%=request.getRealPath("/")+tag.getSrc()%>" width="<%=tag.getWidth()%>" height="<%=tag.getHeight() %>"/>
            <br/>
        </th>
	    <td>
	  <!-- <input name="src" type="file" align="left"  onkeydown="javascript:fileClick();"> 
	  <font color="#FF0000">*</font></td></tr>-->
	</table>
<%} %>
</table>
</body>
</html>
