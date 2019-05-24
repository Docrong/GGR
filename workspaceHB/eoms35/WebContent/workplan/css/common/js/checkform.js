/********************************************************************************
 Copyright (c) 2002-2005,亿阳信通网络事业部IP网管
 All rights reserved.
 Filename ：checkform.js
 Abstract ：常用表单检测函数集
 Version　：2.0
 Author   ：Liu Guoyuan
 Finished Date ：2002-09-20
 Last Modified ：2004-08-12

 目前函数有
 
 CheckIsNull(objName[,text])   //判断文本框是否为空,或SELECT菜单是否选中，参数：(对象名[，出错提示])
 CheckIsEmail(objName[,text])
 CheckIsString(objName,[objCnName])
 CheckIsIP(objName[,text])
 CheckIsNum(objName[,objCnName][,minNum,maxNum])  //是否数字，参数：表单元素名,[对象中文名],[最小值，最大值]
 CheckIsErrLength(objName,maxLength[,objCnName])  //字符串长度是否正确(区分中英文),参数：(对象名，最大长度[，对象中文名])
 CheckIsDateTime(objName[,objCnName])
 注：上述函数如果不输入可选参数，则不会弹出出错提示。
********************************************************************************/

//读取公共库
function _loadJS(src){
	var script=document.getElementsByTagName("SCRIPT");
	for(var i=0;i<script.length;i++){
		var s=script[i].src;
		if(s.indexOf(src)>=0) return;
		if(s.indexOf("/common/js/checkform.js")!=-1){jsPath=s.replace("checkform.js","")}
	}
	var oScript = document.createElement("<SCRIPT>");
	oScript.src = jsPath+src;
	script[0].insertAdjacentElement("afterEnd",oScript);
}_loadJS("lib.js");

function CheckIsNull(objName,text){
/*判断文本框是否为空,或SELECT菜单是否选中
 参数：(对象名，出错提示)
 返回: true 表示有错误
*/
	var Object=getObject(objName);
	var bError=false;
	if (typeof(Object)=="object" && Object!=null){
		switch (Object.tagName){
			case "SELECT":   //选择菜单
				if (Object.options[0].selected && (Object.options[0].value=="-1"||Object.options[0].value=="")) bError=true;
				break;
			case "INPUT":    //文本框
				if (Object.value=="") bError=true;
				break;
			case "TEXTAREA":    //多行文本框
				if (Object.value=="") bError=true;
				break;
			default :
				alert ("对象类型有误：（"+Object.tagName+"）");
				return true;
		}
		if (bError){
			if (arguments.length>1) alert (text); //弹出错误提示
			Object.focus();
			return bError;
		}
		return bError;
	}
	else{
		alert ("CheckIsNull:参数错误（对象\""+objName+"\"不存在）!")
		return true;
	}
}

function CheckIsErrLength(objName,maxLength,text){
//判断字符串真实长度，中文长为2，英文长1
//参数：表单元素名,最大长度,[表单中文名]
	var Object=getObject(objName);
	valueLength = Object.value.realLength();
	if (valueLength>maxLength){
		if (arguments.length>2) alert (text+"长度必须在 "+maxLength+" 以内!\n当前长度为："+valueLength+"\n(中文长度为２，英文为１)"); //弹出错误提示
		Object.focus();
		return true;
	}
	return false;
}

function CheckIsDateTime(objName,objCnName)
{//日期格式是否正确，参数：表单元素名,[元素中文名]
	var Object=getObject(objName);
	if (Date_istrue(Object.value)){
	  return true;
	}
	else{
	  if (arguments.length>1){
	    if (Object.value.indexOf(" ")>0)
	      alert (objCnName+"格式不正确.\nYYYY-MM-DD HH:MI:SS(年-月-日 时:分:秒)"); //弹出错误提示
	    else
	      alert (objCnName+"格式不正确.\nYYYY-MM-DD(年-月-日)"); //弹出错误提示
	  }
		Object.focus();
		Object.select();
		return false;
	}
}
function CheckDateTime(asDate){return Date_istrue(asDate);}
function Date_istrue(asDate){
  return asDate.isDate();
}

function CheckIsString(objName,objCnName){
//是否数字＋字母＋
//参数：表单元素名,[提示信息]
	var Object=getObject(objName);
	if (CheckString(Object.value)){
		return true;
	}
	else{
		if (arguments.length>1) alert (objCnName+"格式错误(必须为大小写字母+数字+下划线)"); //弹出错误提示
		Object.focus();
		return false;
	}
}
function CheckString(str){
//是否只包含数字＋字母＋下划线
	var re=/[^A-Za-z0-9_]/;
	if (str.search(re)==-1)	return true;
	else return false;
}

function CheckIsNum(objName,objCnName,minNum,maxNum)
{//是否数字，参数：表单元素名,[对象中文名],[最小值，最大值]
	var Object=getObject(objName);
	if (arguments.length>3){ //有最小值和最大值参数
		if (CheckNumber(Object.value,minNum,maxNum)) return true;
	}else{
		if (CheckNumber(Object.value)) return true;
	}
	if (objCnName!=""){
		errMsg=objCnName+"输入有误.";
		if 	(arguments.length>3) errMsg+="\n必须在("+minNum+"-"+maxNum+")之间!"
		alert (errMsg);
	}
	Object.focus();
	Object.select();
	return false;
}
function CheckNumber(num,minNum,maxNum)
{//是否整数,参数：(数字，[最小值，最大值])
	if (num=="") return false;
	var strNum = num+"";
	if (strNum.search(/\D/)>=0){
		return false;
	}
	num=parseInt(num);
	var minNum=parseInt(minNum);
	var maxNum=parseInt(maxNum);
	if (isNaN(num) || (num<minNum || num>maxNum))
		return false;
	else
		return true;
}
function isNumeric(strNumber) { //数字
  if ((parseFloat(strNumber)+"").length == strNumber.length) return true;
  return (strNumber.search(/^(-?\+)?\d+(\.\d+)?$/) != -1);
}

function CheckIsIP(objName,text){
//判断IP地址是否正确
//参数：表单元素名,[提示信息]
	var Object=getObject(objName);
	if (CheckIPAddress(Object.value)){
		return true;
	}
	else{
		if (arguments.length>1) alert (text); //弹出错误提示
		Object.focus();
		return false;
	}
}function CheckIPAddress (ipAddressString){
  return ipAddressString.isIP();
}

function CheckIsEmail(objName,text){
//判断Email地址是否正确
//参数：表单元素名,[提示信息]
	var Object=getObject(objName);
	if (CheckEmail(Object.value)){
		return true;
	}
	else{
		if (arguments.length>1) alert (text); //弹出错误提示
		Object.focus();
		return false;
	}
}function CheckEmail(emailStr){
  return emailStr.isEmail();
}

function IsBeginEndIP(startIP,endIP)
{
	var		iTotalDots = 0;
	var		iFlag = 0;
	var		strMedia = '';
	var		startIP1 = 0;
	var 	startIP2 = 0;
	var		startIP3 = 0;
	var		startIP4 = 0;
	var		endIP1 = 0;
	var 	endIP2 = 0;
	var		endIP3 = 0;
	var 	endIP4 = 0;
	for (var i = 0; i < startIP.length; i++)
	{
		var chMedia = startIP.substring(i, i + 1);
		if (chMedia >= '0' && chMedia <= '9' )
			strMedia = strMedia + chMedia;
		if (chMedia < '0' || chMedia > '9' )
		{
			if( chMedia == '.' )
			{
				if( strMedia > 255 )
					return	false;
				if ( iTotalDots == 0 ) startIP1 = parseInt(strMedia);
				else if (iTotalDots == 1 ) startIP2 = parseInt(strMedia);
				else if (iTotalDots == 2 ) startIP3 = parseInt(strMedia);
				strMedia = '';
				if( iFlag == 1 )
					return	false;
				iTotalDots++;
				iFlag = 1;
				continue;
			}
			return false;
		}
		iFlag = 0;
	}
	if( strMedia > 255 )
		return	false;
	startIP4 = parseInt(strMedia);
	if( iTotalDots != 3 )
		return	false;
	if( startIP.substring(startIP.length-1, startIP.length) == '.' )
		return	false;
		
	strMedia = '';
	iTotalDots = 0;
	iFlag = 0 ;
	for (var i = 0; i < endIP.length; i++)
	{
		var chMedia = endIP.substring(i, i + 1);
		if (chMedia >= '0' && chMedia <= '9' )
			strMedia = strMedia + chMedia;
		if (chMedia < '0' || chMedia > '9' )
		{
			if( chMedia == '.' )
			{
				if( strMedia > 255 )
					return	false;
				if ( iTotalDots == 0 ) endIP1 = parseInt(strMedia);
				else if (iTotalDots == 1 ) endIP2 = parseInt(strMedia);
				else if (iTotalDots == 2 ) endIP3 = parseInt(strMedia);
				strMedia = '';
				if( iFlag == 1 )
					return	false;
				iTotalDots++;
				iFlag = 1;
				continue;
			}
			return false;
		}
		iFlag = 0;
	}
	if( strMedia > 255 )
		return	false;
	endIP4 = parseInt(strMedia);
	if( iTotalDots != 3 )
		return	false;
	if( endIP.substring(endIP.length-1, endIP.length) == '.' )
		return	false;
	if ( parseInt(startIP1) > parseInt(endIP1) ){
		return false;
	}
	else if (( parseInt(startIP1) == parseInt(endIP1) ) && (parseInt(startIP2) > parseInt(endIP2))){
		return false;
	} 
	else if (( parseInt(startIP1) == parseInt(endIP1) ) && (parseInt(startIP2) == parseInt(endIP2)) && (parseInt(startIP3) > parseInt(endIP3))){
		return false;
	} 
	else if (( parseInt(startIP1) == parseInt(endIP1) ) && (parseInt(startIP2) == parseInt(endIP2)) && (parseInt(startIP3) == parseInt(endIP3))&& (parseInt(startIP4) >= parseInt(endIP4))){	    
		return false;
	} 
	return true;	
}

function IsValidMAC ( strMAC )
{
	if ( strMAC.length != 17 ) return false;
	for ( var i = 0 ; i < strMAC.length ; i ++ ) {
		var chTemp = strMAC.substring(i, i + 1);
		if ( i == 2 || i == 5 || i == 8 || i == 11 || i == 14 ){
			if ( chTemp != ':' ) return false;
		}
		else {
			if ( !( (chTemp >= '0' && chTemp <= '9'  ) || (chTemp >= 'a'  && chTemp <= 'f' ) || ( chTemp >= 'A'  &&  chTemp <= 'F' )))			
			{
				return false;
			}
		}
	}
	return true;
}		