<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import = "java.util.*,java.lang.*"%>

<html xmlns:BOCO="BOCO Inter-Telecom">
<head>
<SCRIPT LANGUAGE="JavaScript">
<!--
//弹出派发树窗口
function selectTree(){
	dWinOrnaments = "status:no;scroll:no;resizable:yes;dialogHeight:450px;dialogWidth:480px;";
	dWin = showModalDialog('<%=request.getContextPath()%>/css/listbox/listbox.jsp', window, dWinOrnaments);
}

//查看取到的id，测试用
function confirm(){
	var frm = document.form1;
	alert("sort1_deptid=("+frm.sort1_deptid.value+")");
	alert("sort1_userid=("+frm.sort1_userid.value+")");
	alert("sort2_deptid=("+frm.sort2_deptid.value+")");
	alert("sort2_userid=("+frm.sort2_userid.value+")");
	alert("sort3_deptid=("+frm.sort3_deptid.value+")");
	alert("sort3_userid=("+frm.sort3_userid.value+")");
}
//-->
</SCRIPT>
</head>
<body>
<br>
<form name="form1" action="">

<TABLE cellpadding="10">
<TR>
	<TD>listbox组件示例 【注意：接收数据的显示域及隐藏域的id及name不要更改】
	</TD>
</TR>
<TR>
    <td>取得数据的显示区域：<BR>
		<br>
		派往:<span id="sort1_text"></span>
		<INPUT TYPE="hidden" name="sort1_deptid" id="sort1_deptid" value="">
		<INPUT TYPE="hidden" name="sort1_userid" id="sort1_userid" value="">
		<BR>
		抄送:<span id="sort2_text"></span>
		<INPUT TYPE="hidden" name="sort2_deptid" id="sort2_deptid" value="">
		<INPUT TYPE="hidden" name="sort2_userid" id="sort2_userid" value="">
		<BR>
		审核:<span id="sort3_text"></span>
		<INPUT TYPE="hidden" name="sort3_deptid" id="sort3_deptid" value="">
		<INPUT TYPE="hidden" name="sort3_userid" id="sort3_userid" value="">
		<BR><BR>
		<?import namespace=BOCO implementation="<%=request.getContextPath()%>/css/button/genericButton.htc"/>
		<BOCO:genericButton onclick="selectTree();"> 选择 </BOCO:genericButton><BR><BR>
		<BOCO:genericButton onclick="confirm();"> 查看取到的id </BOCO:genericButton>

	</td>
</TR>
</TABLE>
</form>

</body>
</html>
