<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>

<html:html>

  <link rel="stylesheet" type="text/css" media="all" href="<%=request.getContextPath()%>/styles/red/theme.css" />
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/report/default.css" />--%>
<script language="JavaScript">
var readyState = "";
//删除选中的纪录
function  delRecord()
{
	if(window.confirm("你确定要删除记录吗?")==true)
	{
        document.InfoTypeDetailForm.action="<%=request.getContextPath()%>/filemanager/topic/InfoTypeDetailAction.do?actId=1&onlyTopic=1";
        document.InfoTypeDetailForm.submit();
		document.InfoTypeDetailForm.btnDelType.disabled=true;
	}

}
//判断中文
function PosChar2Byte(str, pos)
	{
		var cLen;
		var bLen = 0;
		var lenstr = str.length;

		if (pos >= 0 && lenstr > pos) lenstr = pos;

		for (cLen=0; cLen<lenstr; cLen++)
		{
			if (str.charCodeAt(cLen)>255) bLen += 2;
			else bLen += 1;
		}

		return bLen;
	}
//修改选中的纪录
function  modifyRecord()
{
	document.InfoTypeDetailForm.action.value = "2";
	document.InfoTypeDetailForm.submit();
}
//保存的纪录
function  saveRecord()
{
	var flag=false;
	//检查输入的值是否合法
	var topicName = document.InfoTypeDetailForm.topicName.value;
	var topicMemo = document.InfoTypeDetailForm.topicMemo.value;
	var topicOrder = document.InfoTypeDetailForm.topicOrder.value;
//	var topicPath = document.InfoTypeDetailForm.topicPath.value;

	if(topicName=='')
	{
		alert('名称不为空');
		flag=true;
		document.InfoTypeDetailForm.topicName.focus();
	}
	else if(topicOrder=="" || topicOrder.match(/^[0-9]*$/)==null)
	{
		alert("顺序请输入整数");
		flag=true;
		document.InfoTypeDetailForm.topicOrder.focus();
	}
//	else if(topicPath=="" || topicPath.search(/[\/\\<>\?|:"\*\. ]/)!=-1)
//	{
//		alert('路径中不能包含：\n / \\ < > ? | : \" * \. 及空格!');
//		flag=true;
//		document.InfoTypeDetailForm.topicPath.focus();
//	}
//    else if (PosChar2Byte(topicPath,-1) != topicPath.length)
//	{
//		alert("路径中不能包含中文字符,请重新输入!");
//		flag=true;
//		document.InfoTypeDetailForm.topicPath.focus();
//	}
//	else if (PosChar2Byte(topicPath,-1)>32)
//	{
//		alert('路径名称不能大于32Byte');
//	 	flag=true;
//		document.InfoTypeDetailForm.topicPath.focus();
//	}
	if(!flag)
	{
		document.InfoTypeDetailForm.action="<%=request.getContextPath()%>/filemanager/topic/InfoTypeDetailAction.do?actId=3&onlyTopic=1";
		document.InfoTypeDetailForm.submit();
		document.InfoTypeDetailForm.btnSaveType.disabled=true;
	}

}
//创建新类别
function  addRecord()
{
	document.InfoTypeDetailForm.action="<%=request.getContextPath()%>/filemanager/topic/InfoTypeDetailAction.do?actId=4&onlyTopic=1";
	document.InfoTypeDetailForm.submit();
}
</script>
<BODY>
 
<html:form  method="POST"  action="/topic/InfoTypeDetailAction.do" enctype="multipart/form-data" >
<html:hidden property="hdId"/>
<html:hidden property="hdParentId"/>
<html:hidden property="hdClassId"/>
<html:hidden property="hdTopicPath"/>
<html:hidden property="actId" />
<br/>
<center>
<TABLE id="list" width="100%">
	<TR><TD>
<%--			<input type="button" value="创建新专题" <%=request.getAttribute("newenable")%> name="btnCrtType" onclick='addRecord()' Class="button">&nbsp;&nbsp--%>
			<input type="button" value="保存专题信息" <%=request.getAttribute("saveenable")%> name="btnSaveType" onclick='saveRecord()' Class="button">&nbsp;&nbsp
<%--			<input type="button" value="删除专题" <%=request.getAttribute("deleteenable")%> name="btnDelType" onclick='delRecord()' Class="button">&nbsp;&nbsp--%>
	</TD></TR>
</TABLE>
</center>
<%=request.getAttribute("htmlBegin")%>
<br/><br/>
<table width="100%" id="listTitle">
	 <TR>
	 	<TD>
	 		<font size=2><b>&nbsp;&nbsp;报表专题定义</b></font>
	 	</TD>
	 </TR>
</table>
<br/>
<TABLE id="list" class="formTable" width="100%">

	 <TR>
	 	<TD width="30%" class="label"><b>专题级别</b></TD>
	 	<TD ><%=request.getAttribute("htmlClassId")%></TD>
	 </TR>
	 <TR>
	 	<TD width="30%" class="label"><b>专题路径</b></TD>
	 	<TD ><%=request.getAttribute("htmlTopicPath")%></TD>
	 </TR>
	 <TR>
	 	<TD width="30%" class="label"><b>专题名称</b></TD>
	 	<td> <html:text  property="topicName" size="40" styleClass="text"/> </td>
	 </TR>
	 <TR>
	 	<TD width="30%" class="label"><b>专题备注</b></TD>
	 	<td> <html:textarea  property="topicMemo" cols="20" rows="3" styleClass="testarea"/> </td>
	 </TR>
     	 <TR>
	 	<TD width="30%" class="label"><b>专题类型</b></TD>
	 	<td>
        <html:select property="topicTypeId" styleClass="select">
            <option value="1">固定报表专题</option>
            <option value="2">临时报表专题</option>
        </html:select>
        </td>
	 </TR>
	 <TR>
	 	<TD width="30%" class="label"><b>排列顺序</b></TD>
	 	<td> <html:text property="topicOrder" size="40" styleClass="text"/> </td>
	 </TR>
</TABLE>

<%=request.getAttribute("htmlEnd")%>
</html:form>

</body>
</html:html>
<script language="JavaScript">
<%if(request.getAttribute("successed")!=null) out.print(request.getAttribute("successed"));%>
readyState = "complete";
</script>
