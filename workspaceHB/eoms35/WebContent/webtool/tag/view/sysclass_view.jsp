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
<title>管理员WAP配置工具</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
</head>
<body>
	<table width="100%">
	 <tr>
	   <td align="right">
	     <a href="<%=app %>/webtool/card?act=view&flag=modify&wapCardId=<%=wapCardId%>">页面标签列表</a>
	    |<a href="<%=app %>/webtool/tag?act=view&flag=modify&wapCardId=<%=wapCardId%>&wapNodeId=<%=wapNodeId%>&tagKey=<%=request.getParameter("tagKey") %>&tagType=sysclass&class_str=<%=tag.getClassStr()%>">修改此标签</a>
	    |<a href="<%=app %>/webtool/tag?act=remove&wapCardId=<%=wapCardId%>&tagKey=<%=request.getParameter("tagKey")%>&wapNodeId=<%=wapNodeId%>">删除此标签</a>
	   </td>
	 </tr>
	</table>
	<table width="100%" class="form_table">
      <tr>
		<th width="30%">此操作返回变量名称：</td>
		<td><%=tag.getName()%></td>
	  </tr>
      <tr>
		<th width="30%">内部类路径：</td>
		<td><%=tag.getClassUrl() %></td>
	  </tr>
	  <tr>
		<th width="30%">内部类方法名：</td>
		<td><%=tag.getMethod()%></td>
	  </tr>
	  <tr>
		<th width="30%">内部类方法输入参数值</td>
		<td><%=tag.getInPutParam()%></td>
	  </tr>
	  <tr>
		<th width="30%">内部类方法参数类型</td>
		<td><%=tag.getParamTypes() %></td>
	  </tr>
	  <tr>
		<th width="30%">返回值是否为List集合</td>
		<td>
              <%if(tag.getIsList().equals("true"))out.print("是"); 
                else if(!tag.getIsList().equals("false"))out.print("否"); %>
		</td>
	</tr>
	<tr>
		<th width="30%">结果显示或判断</td>
		<td>
           <%if(tag.getVerOrDis().equals("display"))out.print("显示"); 
             else if(tag.getVerOrDis().equals("verify"))out.print("判断");
           %>
        </td>
	</tr>
</table>
<table width="100%" class="form_table">
<%if(tag.getVerOrDis().equals("display")){
      if(tag.getIsList().equals("true")){
 %>        
		<tr>
		  <th width="30%">是否分页</td>
		  <td><%if(tag.getIsPageination().equals("false"))out.print("否");
              else if(tag.getIsPageination().equals("true"))out.print("是"); %>
          </td>
		</tr>
		<tr>
	       <th width="30%">显示内容：</td>
	       <td><%=tag.getText() %></td>
	    </tr>
		  <%
		  if(tag.getIsPageination().equals("true")){ %>
				<tr>
				  <th width="30%">每页显示记录数：</td>
				  <td><%=tag.getPageSize() %>条</td>
				</tr>
		  <%} %>
<%
      }else{
%>
		<tr>
	       <th width="30%">显示内容：</td>
	       <td><%=tag.getText() %></td>
	    </tr>		
<%      
      }
}else if(tag.getVerOrDis().equals("verify")){ %>
		<tr>
			<th width="30%">选择校验方法</td>
			<td><%if(!(tag.getVerifyMethod().indexOf("listSize")<0))out.print("判断记录数"); 
	              else if(!(tag.getVerifyMethod().indexOf("isEqual")<0))out.print("两值比对"); %>
			</td>
		</tr>
		<tr>
			<th>校验失败是否继续执行下面标签</td>
			<td>
				<%
				if(tag.getIsDisplayNext()!=null){
				if((tag.getIsDisplayNext().equals("false")))
					out.print("终止"); 
	              else if((tag.getIsDisplayNext().equals("true")))
	              	out.print("继续");
	              	}
	            %>
            </td>
		</tr>
		<tr>
			<th>校验正确显示内容</td>
			<%String[] content=tag.getText().split("@:"); %>
			<td><%=content[0] %></td>
		</tr>
		<tr>
			<th>校验失败显示内容</td>
			<td><%=content[1] %></td>
		</tr>
		<%if(!(tag.getVerifyMethod().indexOf("listSize")<0)){
		    String temp_str=tag.getVerifyMethod();
		    temp_str=temp_str.substring(temp_str.indexOf("(")+1,temp_str.lastIndexOf(")"));
		%>
				<tr>
				   <th>比对记录数：</td>
				   <td><%=temp_str %></td>
				</tr>
		<%}else{
		    String temp_str=tag.getVerifyMethod();
		    String[] temp=temp_str.substring(temp_str.indexOf("(")+1,temp_str.lastIndexOf(")")).split(",");
		%>
		<tr><td colspan="2">
		    <table>
			  <tr>
			    <th>变量值：</td>
			    <td><%=temp[0] %></td>
			    <th>比对值：</td>
			    <td><%=temp[1]%></td>
			  </tr>
			</table>
		</td></tr>
		<%} %>
<%} %>
</table>	  
</body>
</html>
