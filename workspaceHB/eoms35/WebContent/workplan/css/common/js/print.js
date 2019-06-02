/*
------------------------------------------------------------------------------------
Copyright (c) 2002,亿阳信通网络事业部IP网管
All rights reserved.
Filename ：print.js
Abstract ：常用打印函数
Version　：1.0
Author   ：Liu Guoyuan
Finished Date ：2003-03-19
Last Modified ：2003-03-20
-------------------------------------------------------------------------------------
*/

with (document)
{//输出样式表及IE打印控件
	write ("<style type=\"text/css\" media=\"print\">");
	write ("  .noPrint{visibility:hidden}");
	write ("</style>");
	write ("<OBJECT classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0 id=WB width=0></object>");
}
function doPrintSetup()
{//打印设置
	WB.ExecWB(8,1);
}
function doPrintPreview()
{//打印预览
	WB.ExecWB(7,1);
}
function doPrint()
{
	window.print();
}
function showPrintBar()
{
	with (document)
	{
		write ("<div align=\"center\" class=\"noprint\">");
		write ("  <input type=\"button\" name=\"doBack\" value=\" &lt;&lt;返回  \" onClick=\"history.go(-1)\">");
		write ("  <input type=\"button\" name=\"doPrintPreview\" onClick=\"WB.ExecWB(8,1)\" value=\"打印设置\">");
		write ("  <input type=\"button\" name=\"doPrint\" value=\"  打印&gt;&gt; \" onClick=\"doPrint()\">");
		write ("</div>")
	}
}