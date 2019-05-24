/********************************************************************************
 Copyright (c) 2002,亿阳信通网络事业部IP网管
 All rights reserved.
 Filename ：checkform.js
 Abstract ：自动表单检测模块
 Version　：1.0
 Author   ：Liu Guoyuan
 Finished Date ：2003-02-25
 Last Modified ：2003-02-25
 
 功能：
 根据条件，自动检测表单
 <form onSubmit="AutoCheckForm(this)">
********************************************************************************/

function AutoCheckForm(formObject)
{//自动检测表单
	var formObjectCount=formObject.length; //表单元素数量
	var str="";
	var conditionStr;   //表单验证条件
	var conditionItem,conditionValue;
	for (i=0;i<formObjectCount;i++) 
	{
		with (formObject[i])
		{
			conditionStr=getAttribute("condition")
			objCnName=getAttribute("cnname")
			if (conditionStr!=null)
			{
				conditionStr=conditionStr.split(";")
				if (conditionStr.length>1) //数组
				{
				
				}
				else
				{
					conditionItem=conditionStr[0].split("=")[0];
					conditionValue=conditionStr[0].split("=")[1];
					//if (conditionStr.length<=1) {return 2;}//参数错误
					if (!CheckFormItem(formObject[i].name,objCnName,conditionItem,conditionValue)){return false};
				}
			}
		}
	}
	return true;
}
function CheckFormItem(objName,objCnName,conditionItem,conditionValue)
{
	switch (conditionItem)
	{
		case "null":
			if (conditionValue=="false")
				return (CheckIsNull(objName);
			else return true;
			break;
		case "email":
			return CheckIsEmail(objName,conditionValue);
			break;
	}
}