/********************************************************************************
 Copyright (c) 2004-2005,亿阳信通网络事业部IP网管
 All rights reserved.
 Filename ：ui.js
 Abstract ：WEB界面相关操作方法集
 Version　：1.1
 Author   ：Liu Guoyuan
 Finished Date ：2004-04-08
 Last Modified ：2004-08-12
 
 更新记录：
 2004-04-22 v1.1
   setLabelAction() 修改，设置标签(LABEL)默认动作：将标签与该标签第一个子对象绑定，使该方法完美。
   　　　　　　　　 　　　此方法必须在body.onload事件中最先执行，否则在外部指定的LABEL中第一个子对象的事件将失效
   添加 getItemText() 方法,  获取指定元素文本
   
 2004-04-20 v1.1
   修改 getItemValue() 方法添加isChar(是否字符)参数，为true在value前后加上'value'
 
 2004-04-17 v1.1
   添加 getParent() 方法
 
 2004-04-16 v1.1
   添加 setSelectItem() 方法,  设置对象(菜单、复选框、单选框)的默认选中状态。
   添加 getItemValue() 方法,   获取指定对象(菜单、复选框、单选框)值
   添加 setComboBox() 方法,    将文本输入框和选择菜单绑定为ComboBox菜单（可编辑菜单）
   
 2004-04-08 v1.0 创建
   addItem()
   removeItem()
   addAllItem()
   removeAllItem()
   selectAllItem()
   selectAllCheckBox(() 设置checkbox选中状态
   setCheckAllAction()  绑定全选框及需要监控的复选框(菜单)
   setLabelAction()     设置标签(LABEL)默认动作：点击标签时调用该标签第一个子对象的click事件
   
********************************************************************************/

//读取公共库
function _loadJS(src){
	var script=document.getElementsByTagName("SCRIPT");
	for(var i=0;i<script.length;i++){
		var s=script[i].src;
		if(s.indexOf(src)>=0) return;
		if(s.indexOf("/common/js/ui.js")!=-1){jsPath=s.replace("ui.js","")}
	}
	var oScript = document.createElement("<SCRIPT>");
	oScript.src = jsPath+src;
	script[0].insertAdjacentElement("afterEnd",oScript);
}
_loadJS("lib.js");

/*****************************
  增加菜单项，参数(源select名称，目标select名称)
******************************/
function addItem(source,dest){
  source = getObject(source);
  dest = getObject(dest);
  var i=0;
  var soulen=source.options.length-1;
  var j=0;
  var deslen=dest.options.length-1;

  if(deslen==0){
    if(dest.options[0].value=="nothing"){
      dest.options[0]=null;
      deslen-=1;
    }
  }
  while(i<=soulen) {
    bflag=true;
    addvalue=source.options[i].value;
    if(source.options[i].selected&&addvalue!="") {
     for (j=0; j<=deslen; j++){
      if (dest.options[j].value==addvalue){
        bflag=false;
        break;
      }
     }
     if (bflag) {
       var sText = source.options[i].text+"";
       sText = sText.replace(" └","");
       var test1=new Option(sText,addvalue);
       dest.options[++deslen]=test1;
     }
    }
    i++;
  }
}

//删除指定选择菜单的已选中选项
function removeItem(dest){
 dest = getObject(dest);
 var j=0;
 var deslen=dest.options.length-1;
 while(j<=deslen) {
    if(dest.options[j].selected) {
     dest.options[j]=null;
     j--;
     deslen--;
   }
   j++;
  }
}
//添加指定选择菜单的全部选项到目标菜单
function addAllItem(source,dest){
  source = getObject(source);
  dest = getObject(dest);
  var i=0;
  var soulen=source.options.length-1;
  var j=0;
  var deslen=dest.options.length-1;
  if(deslen==0){
    if(dest.options[0].value=="nothing") {
      dest.options[0]=null;
      deslen-=1;
    }
  }
  for (var i=0;i<=soulen;i++) {
    bflag=true;
    addvalue=source.options[i].value;
    if (addvalue=="-1"||addvalue==""){
      continue;
    }

    for (var j=0; j<=deslen; j++){
      if (dest.options[j].value==addvalue){
        bflag=false;
        break;
      }
    }

    if (bflag) {
      var sText = source.options[i].text+"";
      sText = sText.replace(" └","");
      var test1=new Option(sText,addvalue);
      dest.options[++deslen]=test1;
    }
  }
}

//删除选择菜单中的全部选项
function removeAllItem(dest){
  delAllItem(dest);
  }function delAllItem(dest){
   dest = getObject(dest);
   while(dest.options.length>0) dest.options[0]=null;
}

//设置菜单项目的选中状态
function selectAllItem(objName,flag){
  var obj = getObject(objName);
  count=obj.options.length
  if (count==0) return;
  if (!obj.multiple){//单选
    obj.options[0].selected = flag;
    return;
  }
  for (i=0;i<count;i++){
    obj.options[i].selected = flag;
  }
}

/**********************************************
函数功能：选中菜单(复选框、单选框)记录
  参数说明：
  objName:
     菜单名称,可用全名,如："document.form1.selMenu",也可用缩写，如"selMenu"
  strValue:　数字或字符
     值，用于与菜单值匹配，该内容一般来自数据库中，如果菜单为多选，则数值之间用“,”(逗号)分隔，如"24,33,25,23"
**********************************************/
function setSelectItem(objName,strValue){
  var numargs = arguments.length; //返回的参数数量
  var sourceObject;               //进行匹配的菜单对象
  var isFinded = false;           //是否找到匹配内容
  strValue = "" + strValue + "";
  sourceObject = getObject(objName);

  //单选框
  if (sourceObject.type=="radio"){
    var aoRadio = document.getElementsByName(objName);
    for (var i=0;i<aoRadio.length;i++){
      if (aoRadio[i].value == strValue){
        aoRadio[i].checked = true;
        break;
      }
    }
  }
  //复选框
  else if (sourceObject.type=="checkbox"){
    var aoCheckBox = document.getElementsByName(objName);
    var asValue = strValue.split(",");
    for (var i=0;i<aoCheckBox.length;i++){
      for (var j=0;j<asValue.length;j++){
        if (aoCheckBox[i].value == asValue[j]){
          aoCheckBox[i].checked = true;
          break;
        }
      }
    }
  }
  //菜单
  else{
    if ((strValue)==""){
      sourceObject.options[0].selected=true;
      return;
    }
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
    if (!isFinded) sourceObject.options[0].selected=true;;
  }
}

/*****************************
 从指定对象中获取值
 参数：
 　objName: 对象名，一般为复选框、单选框和菜单
   isChar:  是否字符，为true在value前后加上''，
 返回：
   选中值列表，例：　11,24,12,56 或 '11','24','12','56'
*****************************/
function getItemValue(objName,isChar){
  var sValue = "";
  var pos = "";
  if (isChar !=null && isChar == true){
    pos = "'";
  }
  var aObj = document.getElementsByName(objName);
  //单选框
  if (aObj[0].type=="radio"){
    for (var i=0;i<aObj.length;i++){
      if (aObj[i].checked){ sValue += pos + aObj[i].value + pos + ",";}
    }
  }
  //复选框
  else if (aObj[0].type=="checkbox"){ 
    for (var i=0;i<aObj.length;i++){
      if (aObj[i].checked){ sValue += pos + aObj[i].value + pos + ",";}
    }
  }
  //菜单
  else{
    var count = aObj[0].options.length;
    for(var i=0;i<count;i++){
      if(aObj[0].options[i].value!="nothing" && aObj[0].options[i].value!=""){
        sValue += pos + aObj[0].options[i].value.replace("\\","\\\\") + pos + ",";
      }
    }
  }
  if (sValue!="") sValue = sValue.substring(0,sValue.length-1); //去除最后一个","
  return sValue;
}

/*****************************
 从指定对象中获取文本
 参数：
 　objName: 对象名，一般为复选框、单选框和菜单
 返回：
   选中文本列表，例：  北京,上海,天津
*****************************/
function getItemText(objName){
  var sValue = "";
  var aObj = document.getElementsByName(objName);
  //单选框
  if (aObj[0].type=="radio"){
    for (var i=0;i<aObj.length;i++){
      if (aObj[i].checked){ sValue += aObj[i].parentNode.innerText + ",";}
    }
  }
  //复选框
  else if (aObj[0].type=="checkbox"){ 
    for (var i=0;i<aObj.length;i++){
      if (aObj[i].checked){ sValue += aObj[i].parentNode.innerText + ",";}
    }
  }
  //菜单
  else{
    var count = aObj[0].options.length;
    for(var i=0;i<count;i++){
      if(aObj[0].options[i].value!="nothing" && aObj[0].options[i].value!=""){
        sValue += aObj[0].options[i].text.replace(" └","") + ",";
      }
    }
  }
  if (sValue!="") sValue = sValue.substring(0,sValue.length-1); //去除最后一个","
  return sValue;
}

/*****************************
 设置复选框状态
 参数：
   objName: 复选框名称
   flag: 设置状态 true|false
*****************************/
function selectAllCheckBox(objName,flag){
  var aoCheckBox = document.getElementsByName(objName);
  for (var i=0;i<aoCheckBox.length;i++){
    aoCheckBox[i].checked = flag;
  }
}

/******************************
  绑定全选框及需要监控的复选框(菜单)
  功能：
  　当全选框选中时，将监控的复选框（菜单选项）全部选中，否则设为未选中状态。
  　反之亦然。
  参数：
   chkAllObj: 用于全选的复选框名称
   chkOtherObj: 监控的复选框(菜单)名称
******************************/
function setCheckAllAction(chkAllObjName,chkOtherObjName){
  var aoCheckBox = document.getElementsByName(chkOtherObjName);
  for (var i=0;i<aoCheckBox.length;i++){
    /*** 监控类型是复选框 ***/
    if (aoCheckBox[i].type=="checkbox"){
      if (i==0){//全选框事件处理
        document.all[chkAllObjName].onclick = function(){selectAllCheckBox(chkOtherObjName,this.checked);}
      }
      //监控的复选框属性改变事件
      aoCheckBox[i].onpropertychange = function(){
          if (!this.checked){
            document.all[chkAllObjName].checked = false;
          }
          else{
            //查找监控的复选框是否全部选中，将全选框状态设置相应状态
            var aoCheckBox = document.getElementsByName(chkOtherObjName);
            var bAllChk = true;
            for (var i=0;i<aoCheckBox.length;i++){
              if (!aoCheckBox[i].checked){bAllChk = false;break;}
            }
            document.all[chkAllObjName].checked = bAllChk;
          }
        }
    }// if checkbox
    /*** 监控类型是选择菜单 ***/
    else{
      if (i==0){//全选框事件处理
        document.all[chkAllObjName].onclick = function(){selectAllItem(chkOtherObjName,this.checked);}
      }
      //监控的选择菜单事件处理
      document.all[chkOtherObjName].onchange = function(){
            if (this.multiple){//允许多选，当项目全部选中时将全选框设为选中状态
              var count = this.options.length;
              var selCount = 0;
              for (var i=0;i<count;i++){
                if (this.options[i].selected) selCount++;
              }
              document.all[chkAllObjName].checked = selCount == count;
            }else{
              document.all[chkAllObjName].checked = this.value == "" || this.value == "nothing";
            }
        }
    }
  }
}

/******************************
  设置标签(LABEL)默认动作：将标签与该标签第一个子对象绑定
　此方法必须在body.onload事件中最先执行，否则在外部指定的LABEL中第一个子对象的事件将失效
******************************/
function setLabelAction(){
  try{
    var aoLabel = document.getElementsByTagName("LABEL");
    var tmpStr;
    var objID;
    for (var i=0;i<aoLabel.length;i++){
      if (!aoLabel[i].htmlFor){//没有绑定元素
        if (aoLabel[i].children[0].id){//第一个子元素有ID，设置for
          aoLabel[i].htmlFor = aoLabel[i].children[0].id;
        }else{//第一个子元素没有ID，添加ID
          objID = aoLabel[i].children[0].name + i;
          tmpStr = aoLabel[i].children[0].outerHTML;
          tmpStr = tmpStr.substring(0,tmpStr.length-1);
          tmpStr = tmpStr + " id=\"" + objID + "\">";
          aoLabel[i].children[0].outerHTML = tmpStr;
          aoLabel[i].htmlFor = objID;
        }
      }
      if (!aoLabel[i].title){ //没有提示文字
        aoLabel[i].title = aoLabel[i].innerText;
      }
    }
  }catch (e){}
}

/***************************
 将文本输入框和选择菜单绑定为ComboBox菜单（可编辑菜单）
 参数：txtObj：文本输入框名称，selectObj：选择菜单名称
***************************/
function setComboBox(txtObj,selectObj){
  txtObj = getObject(txtObj);
  selectObj = getObject(selectObj);
    
  var downList = document.createElement("SPAN")
  with (downList){
    innerHTML = "<font face=webdings>6</font>";
    style.cursor = "hand";
    style.textAlign = "center";
    style.color = "#4D6185";
    style.backgroundColor = "#D5E0F6";
    style.lineHeight = "14px";
    style.width = "16px";
    style.border = "1px solid #7BAAD6";
    onclick = function() {
        txtObj.style.display='none';
        style.display="none";
        selectObj.style.display='';
        selectObj.focus();
      }
  }
  txtObj.insertAdjacentElement("afterEnd",downList);
  txtObj.onkeydown = function () {
      if (event.keyCode==40||event.keyCode==38){
        downList.click();return false;
      }
    }
  selectObj.style.display = "none";
  if (selectObj.clientWidth<(txtObj.offsetWidth+16)){
    selectObj.style.width = txtObj.offsetWidth+16;
  }
  selectObj.onclick = function () {
        txtObj.value=selectObj.value;
      }
  selectObj.onblur = function () {
      txtObj.style.display="";
      txtObj.focus();
      downList.style.display=""; 
      selectObj.style.display="none";
    }
  selectObj.onkeydown = function () {
      if (event.keyCode==27){
        selectObj.blur();
        return false;
      }else if(event.keyCode==13){
        txtObj.value=selectObj.value;
        selectObj.blur();return false;
      }
    }
}