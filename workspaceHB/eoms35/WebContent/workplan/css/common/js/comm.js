/********************************************************************************
 Copyright (c) 2002,亿阳信通网络事业部IP网管
 All rights reserved.
 Filename ：comm.js
 Abstract ：公共JavaScript函数集
 Version　：2.0
 Author   ：Liu Guoyuan
 Finished Date ：2002-08-30
 Last Modified ：2004-08-12

 修改记录：
   2004-04-25
    添加 showMsg(msg,hidePage) 方法 ,在页面显示提示信息
********************************************************************************/
var	sepi=0;
var box =0;
function tableTR(){
	sepi=1-sepi;
	if (sepi == 1) {
		S="<tr bgcolor='#EDF1F8' align='center' class=\"TableBodyOut\" onMouseOver=\"this.className='TableBodyOver'\" onMouseOut=\"this.className='TableBodyOut';\" >";
	}else{
		S="<tr bgcolor='#D5E0F7' align='center' class=\"TableBodyOut\" onMouseOver=\"this.className='TableBodyOver'\" onMouseOut=\"this.className='TableBodyOut';\">";
	}
	document.write(S);
}
function tableTD(c){
	box=1-box;
	if (box == 1) {
		C="<input name=\"mid\" type=\"checkbox\" style='backcolor:#EDF1F8;border: 1.0px #9CB9F5;' value='"+c+"' onclick=\"TableBgLock(this)\">";
	}else{
		C="<input name=\"mid\" type=\"checkbox\" style='backcolor:#D5E0F7;border: 1.0px #9CB9F5;' value='"+c+"' onclick=\"TableBgLock(this)\">";
	}
	document.write(C);
}
function TableBgLock(Obj){
	if(!Obj.checked){
		Obj.style.backgroundColor='';
	}else{
		Obj.style.backgroundColor='#ABCBE2';
	}
}

function SelectItem(strValue,ObjName,selMode){
/*函数功能：选中菜单(多选框、单选框)记录
  参数说明：
  strValue:　数字或字符
     值，用于与菜单值匹配，该内容一般来自数据库中，如果菜单为多选，则数值之间用“,”(逗号)分隔，如"24,33,25,23"
  ObjName:
     菜单名称,可用全名,如："document.form1.selMenu",也可用缩写，如"selMenu"
  selMode:
     选择模式，默认0单选，1多选。
*/
	var numargs = arguments.length; //返回的参数数量
	var sourceObject;               //进行匹配的菜单对象
	var isFinded = false;           //是否找到匹配内容
	
	if (numargs>1){
		if (ObjName.search(/\./g)<0)         //对象名称不包含"."
			sourceObject=document.getElementsByName(ObjName)[0];
		else
			sourceObject=eval(ObjName);
	}else{
		sourceObject=eval("document.all.selectTree"); //设置缺省对象
	}
	
	if (sourceObject.type=="radio"){//单选框
	  var aoRadio = document.getElementsByName(ObjName);
	  for (var i=0;i<aoRadio.length;i++){
	    if (aoRadio[i].value == strValue) aoRadio[i].checked = true; 
	  } 
	}
	else{//菜单
  	if ((""+strValue)==""){
  	  sourceObject.options[0].selected=true;
  	}else{
  	  if (selMode==1){ //多选菜单
    		for (i=0;i<sourceObject.options.length;i++){    		  
      		sourceObject.options[i].selected=false;  //清除原有选择
    		}
   	    var aryID=strValue.split(",");
      	if (aryID.length==0) return;
      	for (var j=0;j<aryID.length;j++){
      		for (var i=0;i<sourceObject.options.length;i++){    		  
      			if (sourceObject.options[i].value==aryID[j]){
      			  sourceObject.options[i].selected = true;
      			  isFinded = true;
      				break;
      			}
      		}
      	}
  	  }else{//单选菜单
    		var	count=sourceObject.options.length;
    		for (var i=0;i<count;i++){
    			if (sourceObject.options[i].value==(""+strValue)){
    			  sourceObject.options[i].selected = true;
    				isFinded = true;
    				break;
    			}
    		}
  	  }
  		if (!isFinded) sourceObject.options[0].selected=true;;
  	}
  }
}

function OpenWin(theURL,winName,features) {
  newWindow=window.open(theURL,winName,features);
  newWindow.focus();
}
function OpenCenterWindow(theURL,winName,width,height,features) { 
//打开永远居中的窗口
    var window_width = width;
    var window_height = height;
    var newfeatures= features;
    var window_top = (screen.height-window_height)/2;
    var window_left = (screen.width-window_width)/2;
    newWindow=window.open(''+ theURL + '',''+ winName + '','width=' + window_width + ',height=' + window_height + ',top=' + window_top + ',left=' + window_left + ',' + features + '');
    newWindow.focus();
}
function OpenActionWindow(theURL,vArguments,width,height,features) { 
//打开永远居中的模式窗口
    var window_width = width;
    var window_height = height;
    var newfeatures= features;
    var return_value = window.showModalDialog(""+theURL+"",""+vArguments+"","dialogWidth:"+window_width+"px;dialogHeight:"+window_height+"px;scroll:no;status:no;help:no");
    return return_value;
}

//全部选中多选按钮
var checkAllFlag=0;
function checkAll(obj)
{
	if (checkAllFlag==0){
		if(obj.length>1){
			for(var i=0;i<obj.length;i++)
				obj[i].checked=true;
				document.all.selectall.checked=true;
		}
		else{
			document.all.selectall.checked=true;
			obj.checked=true;
		}
		checkAllFlag=1;
	}
	else{
		if(obj.length>1)
		{
			for(var i=0;i<obj.length;i++)
				obj[i].checked=false;
				document.all.selectall.checked=false;
		}
		else
		{	
			document.all.selectall.checked=false;
			obj.checked=false;
		}
		checkAllFlag=0;
	}
}

function SwitchAll(form)
{
//交换多选按钮
	var form=eval(form);
	count=parseInt(form.elements.length);
	if (count==0) return false;
	if (count==1)
	{
		form.mid.checked = !form.mid.checked;
		return true;
	}
	for (var i = 0; i < count; i++) 
	{
		var e = form.mid[i];
		e.checked = !e.checked;
	}
	return true;
}

function modify(form)
{
//修改选中记录
	var form=eval(form);
	var e,count,eTest
	var selectFlag = "FALSE";
	var selectCount = 0;
	var firstSelected;
	count=parseInt(form.elements.length);
	if (count==0) return;
	if (count==1)
	{
		//if (form.mid.checked)
		//{
			selectFlag = "TRUE";
			e = form.mid
		//}
	}
	else
	{
		for (var i = 0; i < count; i++) 
		{
			var eTest = form.mid[i];
			if (eTest.checked)
			{
				e = eTest;
				selectFlag = "TRUE";
				selectCount++;
				if (selectCount>1)
				{
					alert ("最多选择一条记录！");
					return;
				}
			}
		}
	}
	if (selectFlag=="FALSE") 
	{	alert("请选择记录！");return;
	}
	else
	{	URL=GetFileName("modify")+"?id="+e.value;
		window.location = URL ;	
	}
}

function del(object)
{
//删除所有选中记录
	var form=eval(object);
	var selectFlag = "FALSE"
	count=parseInt(form.elements.length);
	if (count==0) return;
	if (count==1)
	{
		if (form.mid.checked) selectFlag = "TRUE";
	}
	else
	{
		for (var i = 0; i < count; i++) 
		{
			var e = form.mid[i];
			if (e.checked){selectFlag = "TRUE";break;}
		}
	}
	if (selectFlag=="FALSE") {alert("请选择记录！");return;}
	if (confirm("你确定要删除选定的记录吗？"))
	{
		var	form=eval(form);
		URL=GetFileName("delete")
		form.action=URL;
		form.submit();
	}
}

function del2(object)
{
//删除所有选中记录
	var form=eval(object);
	var selectFlag = "FALSE"
	count=parseInt(form.elements.length);
	if (count==0) return;
	if (count==1)
	{
		if (form.mid.checked) selectFlag = "TRUE";
	}
	else
	{
		for (var i = 0; i < count; i++) 
		{
			var e = form.mid[i];
			if (e.checked){selectFlag = "TRUE";break;}
		}
	}
	if (selectFlag=="FALSE") {alert("请选择记录！");return;}
	if (confirm("你确定要级联收回该用户所有授出的该权限吗？"))
	{
		var	form=eval(form);
		URL="user_privs_delete2.jsp" //GetFileName("delete")
		form.action=URL;
		form.submit();
	}
}

function del3(object)
{
//删除所有选中记录
	var form=eval(object);
	var selectFlag = "FALSE"
	count=parseInt(form.elements.length);
	if (count==0) return;
	if (count==1)
	{
		if (form.mid.checked) selectFlag = "TRUE";
	}
	else
	{
		for (var i = 0; i < count; i++) 
		{
			var e = form.mid[i];
			if (e.checked){selectFlag = "TRUE";break;}
		}
	}
	if (selectFlag=="FALSE") {alert("请选择记录！");return;}
	if (confirm("你确定要级联删除该用户所有创建的对象吗？"))
	{
		var	form=eval(form);
		URL="user_delete3.jsp" //GetFileName("delete")
		form.action=URL;
		form.submit();
	}
}


function GetFileName(actionFileName)
{
  //loadJS("page.js");
	//return getFileName(action);
	var arrayURL = new Array();
	var tempURL = new String();
	var sURL = new String();
	tempURL = window.location + "";
	arrayURL = tempURL.split("/");
	tempURL=arrayURL[arrayURL.length-1];
	var fileExtName = tempURL.substr(tempURL.indexOf("."),4); //文件扩展名
	tempURL=tempURL.substr(0,tempURL.indexOf(fileExtName));
	if (tempURL.indexOf("_manage")>=0){
		sURL = tempURL.substr(0,tempURL.indexOf("_manage"));
	}
	else{
		if (tempURL.indexOf("_init")>=0){
			sURL=tempURL.substr(0,tempURL.indexOf("_init"));
		}else{
			if (tempURL.indexOf("_list")>=0){
				sURL=tempURL.substr(0,tempURL.indexOf("_list"));
			}
			else{
				sURL = tempURL;
			}
		}
	}
	sURL += "_";
	switch (actionFileName){
		case "add": sURL += "add"+fileExtName;break;
		case "modify": sURL += "modify"+fileExtName;break;
		case "delete": sURL += "delete"+fileExtName;break;
	  default : sURL += actionFileName+fileExtName;
	}
	return sURL;
}

function ClearInput(object,text){
//清除表单内容
	var object=eval(object);
	var sValue=object.value;
	if (object.value==text) object.value=""
}

function RestoreInput(object,text){
//恢复表单内容
	var object=eval(object);
	var text=text;
	if (object.value=="") object.value=text;
}

//生成树型菜单
//数据格式：ID，CLASS，NAME;  对应ID值，级别，名称
//如 1,1,全国;2,2,北京;
function BuilderSelectTree(sourceObject,targetObject){
	var numargs = arguments.length; //返回的参数数量
	if (numargs>0){
		var sObject=eval(sourceObject);
		var tObject=eval(targetObject);
	}
	else{
		var sObject=eval("document.form1.treeCode");       //源对象：源数据所在对象,源数据格式为 "id,class,name;"　如"1000,1,全国;1001,2,北京;"
		var tObject=eval("document.form1.selectTree");    //目标对象
	}
	var name,count;
	var treeCode=new Array();
	treeCode=sObject.value.split(";");
	count=treeCode.length-1;
	j=0;
	for (i=0;i<count;i++){
		var treeCodeItem=new Array(3);
		treeCodeItem=treeCode[i].split(",");
		nodeId=treeCodeItem[0];        //节点ID
		nodeClass=treeCodeItem[1];     //节点级数
		nodeName=treeCodeItem[2];      //节点名称
		name=nodeClass+"级";
		for (k=1;k<=nodeClass;k++){
			name=name+"...";
		}
		tObject.options.length=j+1;
		if (nodeClass==0){
			tObject.options[j].className="OptionRedColor";
			nodeName="···"+nodeName+"···";
		}
		else{
			nodeName=name+"|"+nodeName;
		}
		tObject.options[j].text=nodeName;
		tObject.options[j].value=nodeId;
		tObject.options[j].classvalue=nodeClass;
		j++;
	}
	tObject.options[0].selected=true;
}

/***************************
 在页面显示提示信息窗口
 参数：
   msg :      提示信息，无参数时隐藏提示窗口
   hidePage : 是否隐藏页面显示,默认false,在原页面上显示提示，
***************************/
function showMsg(msg,hidePage){
  if (hidePage){
    document.body.innerHTML = "";
  }
  if (typeof(divMsgWin00001)=="object"){//提示窗口已经创建
    if (msg == null){
      divMsgWin00001.style.display = "none";
    }else{
      divMsgWin00001.style.display = "block";
      divMsgWinText00001.innerText = msg;
    }
  }else{
    if (msg == null){
      alert ("showMsg():请输入提示信息参数！"); 
      return;
    }
    var msg="<DIV "+
            " id=\"divMsgWin00001\""+
            " style=\"Z-INDEX: 300;"+
            " padding:0;WIDTH: 308px;"+
            " position: absolute; "+
            " font-size:14px;"+
            " border:1 #99ccff solid;"+
            " background-color:#ffffff;"+
            " left:expression((document.body.clientWidth+document.body.scrollLeft-308)/2);"+
            " top:expression((document.body.clientHeight+document.body.scrollTop-120)/2)\""+
            ">"+
            "<table width=100%  border=0 cellspacing=2 cellpadding=2>"+
            "  <tr>"+
            "    <td rowspan=2><IMG src="+getPath()+"/images/background/msgbg.gif border=0></td>"+
            "    <td valign=bottom id=divMsgWinText00001 style=\"font-size:14px;\">　"+msg+"</td>"+
            "  </tr>"+
            "  <tr height=16>"+
            "    <td valign=bottom><IMG src="+getPath()+"/images/background/processbar.gif border=0></td>"+
            "  </tr>"+
            "</table>"+
            "<table>"+
            "</DIV>";
    var oDIV = document.createElement("DIV");
    document.body.insertAdjacentElement("beforeEnd",oDIV);
    oDIV.outerHTML = msg;
  }
}

//从相同目录读取JS文件
function loadJS(src){
	var script=document.getElementsByTagName("SCRIPT");
	for(var i=0;i<script.length;i++){
		var s=script[i].src;
		if(s.indexOf(src)>=0) return;
		if(s.indexOf("/common/js/comm.js")!=-1){jsPath=s.replace("comm.js","")}
	}
	var oScript = document.createElement("<SCRIPT>");
	oScript.src = jsPath+src;
	script[0].insertAdjacentElement("afterEnd",oScript);
}

//获取当前网站相对路径
function getPath(){
	var script=document.getElementsByTagName("SCRIPT");
	for(var i=0;i<script.length;i++){
		var s=script[i].src.toLowerCase()
		if(s.indexOf("/common/js/comm.js")!=-1){jsPath=s.replace("/common/js/comm.js","")}
	}
  return jsPath;
}