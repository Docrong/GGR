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
<title>����ԱWAP���ù���</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<body>
  <table width="100%">
	 <tr>
	   <td class="title">ͼƬ��Ϣ</td>
	   <td align="right">
	     <a href="<%=app %>/webtool/card?act=view&flag=modify&wapCardId=<%=wapCardId%>">ҳ���ǩ�б�</a>
	    |<a href="<%=app %>/webtool/tag?act=view&flag=modify&wapCardId=<%=wapCardId%>&wapNodeId=<%=wapNodeId%>&tagKey=<%=request.getParameter("tagKey") %>&tagType=image&class_str=<%=tag.getClassStr()%>">�޸Ĵ˱�ǩ</a>
        |<a href="<%=app %>/webtool/tag?act=remove&wapCardId=<%=wapCardId%>&tagKey=<%=request.getParameter("tagKey")%>&wapNodeId=<%=wapNodeId%>">ɾ���˱�ǩ</a>
       </td>
	 </tr>
	</table>
	<table class="form_table">
    <tr>
      <th width="30%" >ͼƬ���ƣ�</td>
      <td><%=tag.getAlt()%></td>
    </tr>
    <tr >
      <th width="30%" >ͼƬλ�ã�</td>
      <td>
      <%if(tag.getAlign().equals("middle"))out.println("�м�");
       else if(tag.getAlign().equals("top"))out.println("����");
       else if(tag.getAlign().equals("bottom"))out.println("�ײ�");
      %>
	  </td>
    </tr>
	 <tr >
      <th width="30%" >ͼƬ��ȣ�</td>
      <td>
           <%=tag.getWidth()%>pix
	  </td>
    </tr>
	<tr >
      <th width="40%" >ͼƬ�߶ȣ�</td>
      <td>
          <%=tag.getHeight() %>pix
	  </td>
    </tr>
    <tr>
    	<th width="30%" >ͼƬ·����</td>
    	<td><%=tag.getSrc() %></td>
    </tr>
    <tr >
      <th width="30%" >ͼƬ���ͣ�</td>
      <td>
            <%
            if(tag.getImageType().equals("auto"))
              		out.println("���������ϴ�"); 
              else if(tag.getType().equals("cake"))
              		out.println("�������ݱ�ͼ"); 
              else if(tag.getType().equals("line"))
              		out.println("����������ͼ");
              else if(tag.getType().equals("pole"))
              		out.println("����������ͼ");
            %>
	  </td>
    </tr>
<%if(tag.getImageType().equals("auto")){ %>
    <table class="form_table"> 
      <tr>
        <th><img alt="û���ϴ�ͼƬ" src="<%=request.getRealPath("/")+tag.getSrc()%>" width="<%=tag.getWidth()%>" height="<%=tag.getHeight() %>"/>
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
