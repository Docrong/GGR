<%@ page language="java" contentType="text/html;charset=gb2312" %>
<%@page import="java.util.List,
                java.util.Iterator,
                com.boco.eoms.mobilewap.base.tag.WapTagInterface,
                com.boco.eoms.mobilewap.base.card.imp.WapCard" %>
<%
WapCard vo = (WapCard)request.getAttribute("cardVO");
List list = (List)request.getAttribute("wmlTagList");
String app = request.getContextPath(); 
String wapCardId=request.getParameter("wapCardId");
String wapNodeId=request.getParameter("wapNodeId");
%>

<html>
<head><title>管理员WAP配置工具</title>
<link type="text/css" rel="stylesheet" href="<%=app%>/webtool/styles/style.css"/>
<script language="javascript">
function checkInput(){
     var nameValue = document.form2.getElementById("name").value;
     if(nameValue==""){       
       alert("'系统名称'不能为空");
       document.form1.name.focus();
       return false;
     }
     var descriptionValue = document.form2.getElementById("description").value;
     if(descriptionValue.length==0){       
       alert("'系统描述'不能为空");
       document.form1.description.focus();
       return false;
     }
}
</script>
</head>
<body onload="javascript:top.leftFrame.reloadtree()">
<form name="form1" action="<%=app %>/webtool/tag/addControl.jsp" onSubmit="return checkInput()">	
    <input name="wapCardId" type="hidden" value="<%=wapCardId %>">
    <input name="wapNodeId" type="hidden" value="<%=wapNodeId %>">
	<table width="100%">
	<tr>
	  <td  align="right">请选择标签类型进行增加标签的操作
	    <select id="tagType" name="tagType">
	      　<option value="anchor">提交链接标签Anchor</option>
	       <option value="href">页面链接标签Href</option>
	       <option value="image">图片标签标签Image</option>
	       <option value="input">输入框标签Input</option>
	       <option value="onevent">定时跳转标签Onevent</option>
	       <option value="selectstatic">下拉列表标签SelectStatic</option>
	       <option value="sqlClass">数据库标签Sqlclass</option>
	       <option value="sysClass">内部类标签Sysclass</option>
	       <option value="text">文本标签Text</option>
	       <option value="br">换行标签Br</option>
	    </select>
        <input type="submit"  class="inputsubmit" value="增加页面标签"></td>
	</tr>
	</table>
  </form>
  <form name="form2" action="<%=app %>/webtool/card">
    <input name="act" type="hidden" value="modify">
    <input name="wapCardId" type="hidden" value="<%=wapCardId %>">
    <input name="wapNodeId" type="hidden" value="<%=wapNodeId %>">
	<table class="form_table">
	<tr>
	   <th width="30%">页面ID</th>
	   <td width="70%">
	     <%=vo.getCardId() %>
	   </td>
	 </tr>
	 <tr>
	   <th>页面名称</th>
	   <td width="70%">
	     <input name="name" type="text" value="<%=vo.getName() %>"><font color="#FF0000">*</font>
	   </td>
	 </tr>
	 <tr>
	   <th>页面描述</th>
	   <td>
	     <input name="description" type="text" value="<%=vo.getDescription() %>"><font color="#FF0000">*</font>
	   </td>
	 </tr>
	 <tr>
	   <th>页面title</th>
	   <td>
	    <input name="title" type="text" value="<%=vo.getTitle() %>">
       </td>
	 </tr>
	</table>
	<br/>
	<table class="list_table">
	<tr><td colspan="3" class="nobg">标签列表</td></tr>
	<tr>
	   <th width="25%">标签名称</th>	   	
	   <th width="25%">所在页面中位置排序</th>
	   <th width="50%">操作</th>
    </tr>
	 <%for(Iterator it = list.iterator(); it.hasNext();){ 
	     WapTagInterface tagVo =(WapTagInterface)it.next();
	 %>
	 <tr>
	   <th class="spec"><%=tagVo.getType() %></th>
	   <td>
	   　<select name="orderIds">
	       <%for(int i=1; i<list.size()+1; i++){%>
	       <option value=<%=i %> <%if((i+"").equals(tagVo.getOrderId()))out.print("selected");%>><%=i %></option>
	       <%} %>	     
	    </select>
	    <input name="tagKeys" type="hidden" value="<%=tagVo.getTagKey() %>">
	   </td> 
	   <td>
	     <%if(tagVo.getClassStr()==null||tagVo.getClassStr().equals("")) {%>
	      查看&nbsp&nbsp
	      修改&nbsp&nbsp	     
	     <%}else{ %>
	      <a href="<%=app %>/webtool/tag?act=view&flag=view&wapNodeId=<%=wapNodeId %>&wapCardId=<%=wapCardId %>&tagKey=<%=tagVo.getTagKey()%>&tagType=<%=tagVo.getType()%>&class_str=<%=tagVo.getClassStr()%>">查看</a>&nbsp&nbsp
	      <%if(!tagVo.getType().equals("Br")){ %>
	      <a href="<%=app %>/webtool/tag?act=view&flag=modify&wapNodeId=<%=wapNodeId %>&wapCardId=<%=wapCardId %>&tagKey=<%=tagVo.getTagKey()%>&tagType=<%=tagVo.getType()%>&class_str=<%=tagVo.getClassStr()%>">修改</a>&nbsp&nbsp
	      <%}else{ %>
	      &nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
	     <%}} %>
	     <a href="<%=app %>/webtool/tag?act=remove&wapCardId=<%=request.getParameter("wapCardId") %>&tagKey=<%=tagVo.getTagKey()%>&wapNodeId=<%=wapNodeId%>">删除</a>
	   </td>  	 	   
	 </tr>
	 <%} %>
	</table>
	
	<table width="100%">
	<tr>
	  <td align="right"><input type='submit' class="inputsubmit" value='提交'/></td>
	</tr>
	</table>
  </form>
</body>
</html>