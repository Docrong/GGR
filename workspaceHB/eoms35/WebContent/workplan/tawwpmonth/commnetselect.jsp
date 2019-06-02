<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import ="java.util.List"%>
<%@ page import ="com.boco.eoms.workplan.vo.TawwpTaskInforVO"%>

<html>
<head>
  <title>月度作业计划管理-浏览网元命令</title>
<link rel="stylesheet" href="../css/table_style.css" type="text/css">
<script language="JavaScript">
var callerWindowObj = dialogArguments;
function onClick(){
	document.commselectform.taskname.value=document.commselectform.taskid.options[document.commselectform.taskid.selectedIndex].id;
}
 </script>
</head>
<body>
  <base target="_self">
  <%
  String executeid=(String)request.getParameter("executeid");
  List taskList = (List)request.getAttribute("taskList"); //获取网元的执行内容列表
  String close=(String)request.getAttribute("close");
  if(close!=null){

  %>
  <TABLE cellSpacing=0 cellPadding=0 align=center border=0>
	<TR>
		<TD vAlign=top align=center width='100%'><P>对应网元命令修改成功！</P></TD>
	<TR>
	<TD borderColor=#008000 width='100%' height=37 colspan='3'><P align=center>
	<input type='button' value='关闭' name='sub4' onclick="javascript:window.close()">
	</P></TD></TR> </TABLE>
	 
<%}else{

  %>
<FORM name='commselectform' method='post' action="commnetsave.do">
  <input type="hidden" name="executeid" value="<%=executeid%>">
	<TABLE cellSpacing=0 cellPadding=0 align=center border=0>
	<TR>
		<TD vAlign=top align=center width='100%'><P>对应网元命令：</P></TD>
	</TR>
	<tr>
		<td>
                <input type="hidden" name="executeid" value="<%=executeid%>">
                  <%if(taskList.size()<1){%>
                    没有可选的网元命令！
                    <%}else{%>
		<SELECT size="<%=taskList.size()%>" name='taskid' onclick="javascript:onClick()" style='width:200pt'>
		<%
		for (int i=0;i<taskList.size();i++){
  			TawwpTaskInforVO tawwpTaskInforVO = (TawwpTaskInforVO)taskList.get(i);
                      %>
  			<option id="<%=tawwpTaskInforVO.getTaskName()%>" value="<%=tawwpTaskInforVO.getTaskID()%>"><%=tawwpTaskInforVO.getTaskName()%></option>
  		<%
			}
		%>
		</SELECT>
                <%}%>
		</TD>
		<input type="hidden" name="taskname">
	</TR>
	<TR>
	<TD borderColor=#008000 width='100%' height=37 colspan='3'><P align=center>
	<%if(taskList.size()>0){%><input type='submit' value='保存' name='sub4'><%}%>
	<input type='button' value='关闭' name='sub4' onclick="javascript:window.close()">
	</P></TD></TR> </TABLE>
	<input type='hidden' name='selvalue' value=''>
	 
</FORM>
<%}%>
</body>
