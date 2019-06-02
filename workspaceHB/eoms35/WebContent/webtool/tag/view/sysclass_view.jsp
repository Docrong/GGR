<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@ page import="com.boco.eoms.mobilewap.base.tag.imp.WapSysClassTag" %>
<%@ page autoFlush="true" %>
<%
WapSysClassTag tag = (WapSysClassTag)request.getAttribute("sysClass");

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
	   <td align="right">
	     <a href="<%=app %>/webtool/card?act=view&flag=modify&wapCardId=<%=wapCardId%>">ҳ���ǩ�б�</a>
	    |<a href="<%=app %>/webtool/tag?act=view&flag=modify&wapCardId=<%=wapCardId%>&wapNodeId=<%=wapNodeId%>&tagKey=<%=request.getParameter("tagKey") %>&tagType=sysclass&class_str=<%=tag.getClassStr()%>">�޸Ĵ˱�ǩ</a>
	    |<a href="<%=app %>/webtool/tag?act=remove&wapCardId=<%=wapCardId%>&tagKey=<%=request.getParameter("tagKey")%>&wapNodeId=<%=wapNodeId%>">ɾ���˱�ǩ</a>
	   </td>
	 </tr>
	</table>
	<table width="100%" class="form_table">
      <tr>
		<th width="30%">�˲������ر������ƣ�</td>
		<td><%=tag.getName()%></td>
	  </tr>
      <tr>
		<th width="30%">�ڲ���·����</td>
		<td><%=tag.getClassUrl() %></td>
	  </tr>
	  <tr>
		<th width="30%">�ڲ��෽������</td>
		<td><%=tag.getMethod()%></td>
	  </tr>
	  <tr>
		<th width="30%">�ڲ��෽���������ֵ</td>
		<td><%=tag.getInPutParam()%></td>
	  </tr>
	  <tr>
		<th width="30%">�ڲ��෽����������</td>
		<td><%=tag.getParamTypes() %></td>
	  </tr>
	  <tr>
		<th width="30%">����ֵ�Ƿ�ΪList����</td>
		<td>
              <%if(tag.getIsList().equals("true"))out.print("��"); 
                else if(!tag.getIsList().equals("false"))out.print("��"); %>
		</td>
	</tr>
	<tr>
		<th width="30%">�����ʾ���ж�</td>
		<td>
           <%if(tag.getVerOrDis().equals("display"))out.print("��ʾ"); 
             else if(tag.getVerOrDis().equals("verify"))out.print("�ж�");
           %>
        </td>
	</tr>
</table>
<table width="100%" class="form_table">
<%if(tag.getVerOrDis().equals("display")){
      if(tag.getIsList().equals("true")){
 %>        
		<tr>
		  <th width="30%">�Ƿ��ҳ</td>
		  <td><%if(tag.getIsPageination().equals("false"))out.print("��");
              else if(tag.getIsPageination().equals("true"))out.print("��"); %>
          </td>
		</tr>
		<tr>
	       <th width="30%">��ʾ���ݣ�</td>
	       <td><%=tag.getText() %></td>
	    </tr>
		  <%
		  if(tag.getIsPageination().equals("true")){ %>
				<tr>
				  <th width="30%">ÿҳ��ʾ��¼����</td>
				  <td><%=tag.getPageSize() %>��</td>
				</tr>
		  <%} %>
<%
      }else{
%>
		<tr>
	       <th width="30%">��ʾ���ݣ�</td>
	       <td><%=tag.getText() %></td>
	    </tr>		
<%      
      }
}else if(tag.getVerOrDis().equals("verify")){ %>
		<tr>
			<th width="30%">ѡ��У�鷽��</td>
			<td><%if(!(tag.getVerifyMethod().indexOf("listSize")<0))out.print("�жϼ�¼��"); 
	              else if(!(tag.getVerifyMethod().indexOf("isEqual")<0))out.print("��ֵ�ȶ�"); %>
			</td>
		</tr>
		<tr>
			<th>У��ʧ���Ƿ����ִ�������ǩ</td>
			<td>
				<%
				if(tag.getIsDisplayNext()!=null){
				if((tag.getIsDisplayNext().equals("false")))
					out.print("��ֹ"); 
	              else if((tag.getIsDisplayNext().equals("true")))
	              	out.print("����");
	              	}
	            %>
            </td>
		</tr>
		<tr>
			<th>У����ȷ��ʾ����</td>
			<%String[] content=tag.getText().split("@:"); %>
			<td><%=content[0] %></td>
		</tr>
		<tr>
			<th>У��ʧ����ʾ����</td>
			<td><%=content[1] %></td>
		</tr>
		<%if(!(tag.getVerifyMethod().indexOf("listSize")<0)){
		    String temp_str=tag.getVerifyMethod();
		    temp_str=temp_str.substring(temp_str.indexOf("(")+1,temp_str.lastIndexOf(")"));
		%>
				<tr>
				   <th>�ȶԼ�¼����</td>
				   <td><%=temp_str %></td>
				</tr>
		<%}else{
		    String temp_str=tag.getVerifyMethod();
		    String[] temp=temp_str.substring(temp_str.indexOf("(")+1,temp_str.lastIndexOf(")")).split(",");
		%>
		<tr><td colspan="2">
		    <table>
			  <tr>
			    <th>����ֵ��</td>
			    <td><%=temp[0] %></td>
			    <th>�ȶ�ֵ��</td>
			    <td><%=temp[1]%></td>
			  </tr>
			</table>
		</td></tr>
		<%} %>
<%} %>
</table>	  
</body>
</html>
