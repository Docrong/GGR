/********************************************************************
' Copyright (c) 2002,亿阳信通网络事业部IP网管
' All rights reserved.
' Filename ：comm_advance.js
' Abstract ：常用内容显示页特效操作
' Version  ：1.0
' Author   ：Liu Guoyuan
' Finished Date ：2003-01-14
' Last Modify ：2003-01-16

' 一般用于记录呈现页面。
' 功能：单元格背景颜色交换，点击单元格时选中相应CheckBox按纽，并固定单元格背景色
*********************************************************************/

function TD_OnMouseOver_ChangeColor()
{
	var clickObject = event.srcElement;
	var objTD = GetParent(clickObject,"TD")
	var objTR = GetParent(clickObject,"TR");
	var objTable = GetParent(clickObject,"TABLE");
	var obj;
	if (objTD != null && objTR.className=="TableBodyOut")
	{
		objTR.className ="TableBodyOver";
	}
}
function TD_OnMouseOut_ChangeColor()
{
		var clickObject = event.srcElement;
		var objTD = GetParent(clickObject,"TD")
		var objTR = GetParent(clickObject,"TR")
		var objTable = GetParent(clickObject,"TABLE")
		var obj
		if (objTD != null && objTR.className=="TableBodyOver")
		{
			intRowIndex=objTR.rowIndex;
			if (typeof(document.del)!="undefined") //如果表单存在，则根据所选行判断是否固定单元格颜色
				if (typeof(document.del.mid)!="undefined")
					if (objTable.rows.length==2)
					{	if (!document.del.mid.checked) objTR.className ="TableBodyOut";}
					else
					{	if (!document.del.mid[intRowIndex-1].checked) objTR.className ="TableBodyOut";}
			else
				{objTR.className ="TableBodyOut";}
		}
}
function TD_OnClick_SelectCheckbox()
{//点击单元格时选中相应CheckBox
	if (typeof(document.del)!="undefined")
	{
		var obj;
		var clickObject = event.srcElement;
		var objTD = GetParent(clickObject,"TD")
		var objTR = GetParent(clickObject,"TR")
		if (GetParent(clickObject,"TD") != null && objTR.className=="TableBodyOver")
		{
			objTable=GetParent(clickObject,"TABLE");
			intRowIndex=objTR.rowIndex;
			SelectCheckbox("document.del",intRowIndex-1);
			//Obj.style.backgroundColor='#ABCBE2';
		}
	}
}
document.onmouseover = TD_OnMouseOver_ChangeColor
document.onmouseout = TD_OnMouseOut_ChangeColor
document.onclick = TD_OnClick_SelectCheckbox

function SelectCheckbox(form,i)
{
//点击单元格时选中相应的checkbox
//参数为：表单，索引值  
	try
	{                                                                
		var clickObject = event.srcElement;
		if (clickObject.type=="checkbox" || clickObject.tagName=="A")
		{
			return;
		}
		var form=eval(form);
		count=parseInt(form.elements.length);
		if (count==0) return;
		if (count==1)
		{
			 form.mid.checked = !form.mid.checked;
		}
		else
		{
			form.mid[i].checked = !form.mid[i].checked;
		}
	}
	catch(e)
	{
		return;
	}
}

function GetParent(el, pTagName) {
	if (el == null) return null;
	else if (el.nodeType == 1 && el.tagName.toLowerCase() == pTagName.toLowerCase())	// Gecko bug, supposed to be uppercase
		return el;
	else
		return GetParent(el.parentNode, pTagName);
}