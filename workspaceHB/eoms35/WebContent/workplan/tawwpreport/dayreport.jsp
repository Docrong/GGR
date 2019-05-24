<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>

<script language="javaScript">

function onflag()
{
var flag=document.planForm.flag.value;
window.navigate("timeflag.do?flag="+flag);
}

</script>


<form method="post" action="load.jsp" name="planForm">


<table class="formTable middle">
<caption>每日作业计划补报</caption> 

<tr>
<td class="label">
时间
</td>
<td>
<input type="text" name="time" size="20"
					onclick="popUpCalendar(this, this,null,null,null,false,-1);"
					readonly="readonly" class="text">
</td>
</tr>

<tr>
<td class="label">
操作类型
</td>

<td>
<input  type="radio" name="type" value="1" checked="checked">生成文件</input>
<input  type="radio" name="type" value="2">上传文件</input>
<input  type="radio" name="type" value="3">生成并上传文件</input>
</td>
</tr>

<tr>
<td colSpan="2" >
<input type="submit" value="提交" name="B1"  Class="button">
</td>
</tr>

</table>
</form>

