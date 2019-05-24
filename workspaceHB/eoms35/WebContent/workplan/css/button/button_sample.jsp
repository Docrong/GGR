<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import = "java.util.*,java.lang.*"%>

<!--引用genericButton组件时要用到的名字空间-->
<html xmlns:BOCO="BOCO Inter-Telecom">
<head>
</head>
<body>
<br>
<form name="form1" action="">

<!--引用genericButton组件-->
<?import namespace=BOCO implementation="<%=request.getContextPath()%>/css/button/genericButton.htc"/>

<TABLE cellpadding="10">
<TR>
	<TD>genericButton组件示例 【注意：按钮需放置在TABLE中，否则不能正常显示】
	</TD>
</TR>
<TR>
	<TD>onclick事件：<BR>
	<BOCO:genericButton css="oldblue" text="" onclick="alert('测试成功拉')"> 确定确定 </BOCO:genericButton>
	</TD>
</TR>
<TR>
	<TD>表单用提交按钮：<BR>
	名字：<INPUT TYPE="text" NAME="name" value=""> <BOCO:genericButton text="">俺叫<%=request.getParameter("name")%></BOCO:genericButton><BR>
	<BOCO:genericButton text="" onclick="form1.submit()"> 提交 </BOCO:genericButton>
	</TD>
</TR>
<TR>
	<TD>表单用重置按钮：<BR>
	<BOCO:genericButton text="" onclick="form1.reset()"> 重置 </BOCO:genericButton>
	</TD>
</TR>
<TR>
	<TD>超长按钮文字：<BR><BOCO:genericButton css="classic" text=""> 超长按钮文字测试，哈哈，13413 ASDLFKJAF DF </BOCO:genericButton>
	</TD>
</TR>
<TR>
	<TD>图片按钮：<BR>
	<BOCO:genericButton css="classic" text="" image="images/btnImage2.gif">Refresh</BOCO:genericButton>
	</TD>
</TR><TR>
	<TD>链接按钮：<BR>
	<BOCO:genericButton><A HREF="http://www.163.com" target="_blank">网易</A></BOCO:genericButton>
	</TD>
</TR>
</TABLE>

<!--处理表格所用的按钮 结束-->

</form>

</body>
</html>
