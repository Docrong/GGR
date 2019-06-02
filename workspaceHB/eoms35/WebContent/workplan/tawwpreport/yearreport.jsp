<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<script language="javaScript">
function onflag()
{
var flag=document.planForm.flag.value;
window.navigate("timeflag.do?flag="+flag);
}
</script>

<form method="post" action="load.jsp" name="planForm">

<table class="formTable middle">
<caption>年作业计划上报</caption> 

<tr>

<td class="label">
年份
</td>

<td>
<select name="time">
<%
SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
Date curtime = new java.util.Date();
int crrtYear=Integer.parseInt(formatter.format(curtime));
for(int i=crrtYear-1;i<(crrtYear+2);i++){
	if(i == crrtYear){
  %>
  <option value="<%=i%>" selected><%=i%></option>
  <%
	}else{
  %>
  <option value="<%=i%>"><%=i%></option>
  <%		
	}
  }
%>
</select>年
</td>

</tr>


<tr>

<td class="label">
类型
</td>

<td>
<input  type="radio" name="type" value="6" checked="checked">生成文件</input>
<input  type="radio" name="type" value="8">上报总部</input>
</td>

</tr>

<tr>

<td colSpan="2" >
<input type="submit" value="提交" name="B1"  Class="button">
</td>

</tr>
</table>

</form>

