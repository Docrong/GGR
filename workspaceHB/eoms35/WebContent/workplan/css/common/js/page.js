/********************************************************************************
 Copyright (c) 2004-2006, 亿阳信通网络事业部IP网管
 All rights reserved.
 Filename ：page.js
 Abstract ：数据显示页面相关的操作方法及样式管理
 Version　：1.5
 Author   ：Liu Guoyuan
 Finished Date ：2004-03-13
 Last Modified ：2004-08-12

 1.6
   2004-08-12 修改_loadJS，解决目录中有大写时找不到路径的问题(苗鹏)
			  修改doModify(formName,sURL),doDel(formName,sURL)加了一个参数sURL(苗鹏)

 1.5
   2004-05-02 修改 setTableStyle，解决当needMouseStyle参数=true时，排序时IE堆栈溢出问题
   2004-04-29 setTableStyle 添加点击标题排序功能，将标题行class设置为"SortTableTitle"即可，该功能需要调用table.js（自动调用）

 1.3
   2004-03-23
     setTableStyle 添加 checkbox.onpropertychange事件处理。当checkbox全部选中时，全选框同时选中，否则全选框不选中
     setTableStyle 保留单元格原有事件处理。

 1.2
   设置表格样式: setTableStyle(table,needMouseStyle)。实现onmouseover，onmouseout，onclick事件

 1.0
   全选checkbox: selectCheckBox(formName)
   反选checkbox: switchCheckBox(formName)
   以及相关操作如：
     修改: doModify(formName)
     删除: doDel(formName)
     其它操作: doOtherOpera(formName,actionFileName,enableMulSelect,message){
********************************************************************************/

//读取公共库
function _loadJS(src){
	var script=document.getElementsByTagName("SCRIPT");
	for(var i=0;i<script.length;i++){
		var s=script[i].src;
		if(s.indexOf(src)>=0) return;
		if(s.indexOf("/common/js/page.js")!=-1){jsPath=s.replace("page.js","")}
	}
	var oScript = document.createElement("<SCRIPT>");
	oScript.src = jsPath+src;
//	alert(oScript.src);
	script[0].insertAdjacentElement("afterEnd",oScript);
}
_loadJS("lib.js");

//选中全部多选框，页面中必须存在名称为"chkSelectAll"的复选框，用以标识全选状态
function selectCheckBox(formName){
	var oForm=eval(formName);
  var iptObjects = oForm.getElementsByTagName("INPUT");
  var iptCount = iptObjects.length;
  var chkAllObjects = document.getElementsByName("chkSelectAll");
  if (chkAllObjects.length==0) return; //不存在全选框，返回
  for (var i=0;i<iptCount;i++){
    if (iptObjects[i].type=="checkbox"&&iptObjects[i].id!="chkSelectAll"){
      iptObjects[i].checked = chkAllObjects[0].checked;
    }
  }
  for (var i=1;i<chkAllObjects.length;i++){
    chkAllObjects[i].checked =  chkAllObjects[0].checked;
  }
}

//交换多选框
function switchCheckBox(formName){
	var oForm=eval(formName);
  var iptObjects = oForm.getElementsByTagName("INPUT");
  var iptCount = iptObjects.length;
  for (var i=0;i<iptCount;i++){
    if (iptObjects[i].type=="checkbox"&&iptObjects[i].id!="chkSelectAll"){
      iptObjects[i].click();
    }
  }
}

//修改选中记录
function doModify(formName, sURL){
  var oForm=eval(formName);
  var selectCount = getSelectCount(formName);
	if (selectCount==0) {
	  alert("请选择一条要修改的记录！");return;
	}
	else if (selectCount>1){
	  alert("一次只能修改一条记录！");return;
	}else{
//	  var sURL = getFileName("modify");
		oForm.action=sURL;
		oForm.submit();
	}
}

//删除所有选中记录
function doDel(formName, sURL){
  var oForm=eval(formName);
  var selectCount = getSelectCount(formName);
	if (selectCount==0) {
	  alert("请选择要删除的记录！");return;
	}
	if (confirm("你确定要删除选定的记录吗？"))
	{
//		sURL=getFileName("delete")
		oForm.action=sURL;
		oForm.submit();
	}
}

/*********************************
 提交表单到指定页面
 参数：表单，页面名，[[是否允许多选(默认允许)],提示语句]
*********************************/
function doOtherOpera(formName,actionFileName,enableMulSelect,message){
  if (arguments.length<3) enableMulSelect = true;
	var oForm=eval(formName);
  var selectCount = getSelectCount(formName);
  var showText = enableMulSelect?"":"一条";
	if (selectCount==0) {
	  alert("请选择"+showText+"要操作的记录！");return;
	}
	if (!enableMulSelect)  //不允许选择多条记录
    if (selectCount>1){
	    alert("一次只能选择一条记录！");return;
	  }
	if (arguments.length==4 && message!=""){ //有提示信息时
	  if (!confirm(message)) return;
	}
	var sURL=getFileName(actionFileName)
	oForm.action = sURL;
	oForm.submit();
}

/*********************************
 从当前URL得到匹配的操作文件名，如删除、修改、添加,或其它
*********************************/
function getFileName(actionFileName){
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

/*********************************
 得到表单选中的数量
*********************************/
function getSelectCount(formName){
  var oForm=eval(formName);
  var selectCount = 0; //选中数量
  var iptObjects = oForm.getElementsByTagName("INPUT");
  var iptCount = iptObjects.length;
  for (var i=0;i<iptCount;i++){
    if (iptObjects[i].type=="checkbox"&&iptObjects[i].id!="chkSelectAll"){
      if (iptObjects[i].checked) selectCount ++;
    }
  }
  return selectCount;
}

/*********************************
 设置表格样式，用于页面初始化时，如 document.onload = setTableStyle(table)
 功能：1、单元格背景颜色交换
 　　　2、点击单元格时选中相应CheckBox按纽，并固定单元格背景色
　　　 3、标题行点击排序，设置 class = "SortTableTitle"，此功能需要调用table.js（自动调用）,且标题列必须在thead中,排序列必须在tbody中
*********************************/
function setTableStyle(table,needMouseStyle){
  InitStyle();
  //初始化表格样式
  function InitStyle(){
    //*** 保存该表格已处理样式信息 ***
    table.isSetStyle = true;
    table.needMouseStyle = needMouseStyle;

    //*** 表格事件处理 ***
    if (needMouseStyle != false){
      table.onmouseover = TR_onMouseOver;
      table.onmouseout = TR_onMouseOut;
      table.onclick = TR_onClick;
    }

    //*** 表格样式处理 ***
    if (table.className == "") table.className = "TableContent";
    if (table.cellSpacing == "") table.cellSpacing = 1;
    if (table.cellPadding == "") table.cellPadding = 1;

    //*** 表格行列样式处理 ***
    var tr = table.rows;
    var curRow = 0;
    for (var i=0;i<tr.length;i++){
      var oTR = tr[i];
      //当前行是标题
      if (oTR.parentNode.tagName=="THEAD" || oTR.parentNode.className.toLowerCase()=="tabletitle"){
        //*** 处理排序标题 ***
        if (oTR.className == "SortTableTitle"){
          if (i<=1){
            _loadJS("table.js");//读取表格操作函数集
          }
          for (var j=0;j<oTR.cells.length;j++){
            if (oTR.cells[j].rowSpan == 1 && oTR.cells[j].colSpan == 1 && oTR.cells[j].innerText.length>1){
              //允许排序：当前列为单行单列，且内容大于1个字
              oTR.cells[j].title = "点击排序";
              oTR.cells[j].onmousedown = function(){this.style.borderStyle="inset";}
              oTR.cells[j].onmouseup = function(){this.style.borderStyle="outset";}
              oTR.cells[j].onclick = TD_SortTable;
            }else{
              oTR.cells[j].style.cursor = "default";
              oTR.cells[j].style.textDecoration = "none";
            }
          }
        }else{
          if (oTR.className == "") oTR.className = "TableTitle";
        }
      }
      //当前是行是表格正文
      else{
        //*** 不处理隐藏状态行 ***
        if (oTR.style.display == "none") continue;

        oTR.curRowIndex = curRow; //保存当前显示行数,用于行颜色交换,因为tr.sectionRowIndex属性会包括隐藏的行。
        oTR.className = "TableBodyOut" + (curRow++%2+1);

        //*** 处理点击事件 ***
        if (needMouseStyle != false){ //需要样式切换
          var td = oTR.cells[0];
          var chkObj = td.firstChild;
          if (chkObj!=null && chkObj.type=="checkbox"){ //第一个单元格内元素为checkbox时，设置单元格样式
            if (i<3 && tr[0].parentNode.rows.length==1){//当表头只有一行时，设置第一行第一列背景为白色
              tr[0].cells[0].style.backgroundColor = "#FFFFFF";
            }
            td.width = 22;
            td.style.textAlign = "center";
            td.style.paddingLeft = 0;
            td.style.paddingRight = 0;
            chkObj.onpropertychange = CheckBox_Change;
          } // if checkbox
        } // if needMouseStyle
      } //if else
    } //for
  }

  /*** 鼠标移入效果 ***/
  function TR_onMouseOver(){
    var obj = event.srcElement;
    var objTR = getTableParent(obj,"TR");
    if (objTR == null || objTR.parentNode.tagName=="THEAD" || objTR.parentNode.className.toLowerCase=="tabletitle") return; //当前行是标题
    if (objTR.className!="TableBodyClicked"){
      objTR.className = "TableBodyOver";
    }
  }
  /*** 鼠标移出效果 ***/
  function TR_onMouseOut(){
    var obj = event.srcElement;
    var objTR = getTableParent(obj,"TR");
    if (objTR == null || objTR.parentNode.tagName=="THEAD" || objTR.parentNode.className.toLowerCase=="tabletitle") return; //当前行是标题
    if (objTR.className!="TableBodyClicked"){
      objTR.className = "TableBodyOut" + (objTR.curRowIndex%2+1);
    }
  }
  /*** 鼠标点击效果 ***/
  function TR_onClick(){
    var obj = event.srcElement;
    var objTR = getTableParent(obj,"TR");
		if (objTR == null || obj.tagName=="INPUT" || obj.tagName=="A" || obj.type=="checkbox") return;
		var chkObj = objTR.cells[0].firstChild;
		if (chkObj!=null && chkObj.type=="checkbox"){
		  chkObj.click();
		}
		//this.onClickOrigin();
  }
  /*** 单选框属性改变事件 ***/
  function CheckBox_Change(){
    try {
      var chkAllObj = document.getElementsByName("chkSelectAll")
      var objTR = getTableParent(this,"TR");
      //未选中时：将全选框设为false
      if (!this.checked){
        objTR.className = "TableBodyOut" + (objTR.sectionRowIndex%2+1);
        for (var i=0;i<chkAllObj.length;i++){
          chkAllObj[i].checked = false;
        }
      }else{
        objTR.className = "TableBodyClicked";
        if (chkAllObj[0].checked) return; //全选框已选中时，返回
        formObj = getParent(this,"FORM");
        var selectCount = 0; //选中数量
        var chkCount = 0;    //全部单选框数量
        var iptObjects = formObj.getElementsByTagName("INPUT");
        var iptCount = iptObjects.length;
        for (var i=0;i<iptCount;i++){
          if (iptObjects[i].type=="checkbox" && iptObjects[i].id!="chkSelectAll"){
            chkCount ++;
            if (iptObjects[i].checked) selectCount ++;
          }
        }
        if (chkCount == selectCount){
          for (var i=0;i<chkAllObj.length;i++) chkAllObj[i].checked = true;
        }
      }
    }//try
    catch (e){}
  }

  /*** 表格排序事件 ***/
  function TD_SortTable(){
    if (typeof(sortTable)=="function"){
      sortTable(getParent(this,"TABLE")); //sortTable()方法在table.js中
    }
  }

  function getTableParent(el, pTagName) {
  	if (el == null) return null;
  	else if (el.nodeType == 1 && el.tagName.toLowerCase() == pTagName.toLowerCase())
  		return el;
  	else{
  	  if (el.tagName.toLowerCase() == "tbody" || el.tagName.toLowerCase() == "thead" || el.tagName.toLowerCase() == "table") return null;
  		return getTableParent(el.parentNode, pTagName);
    }
  }
}

/*********************************
 输出样式表
 =============================
 表格样式:             .TableContent
 表格标题样式:         .TableTitle
 可排序标题样式:       .SortTableTitle
 过滤列表行样式:       .TableFilterList
 表格正文第i行样式:    .TableBodyOut1
 表格正文第i+1行样式:  .TableBodyOut2
 表格鼠标移入样式:     .TableBodyOver
 表格被选中样式:       .TableBodyClicked
 工具按钮样式:         .Page_Tools
 分页链接样式:         .Page_List
*********************************/
with (document){
  write ("<style type=\"text/css\">")
  write ("<!--")
  write ("  a{color:#006699;text-decoration: none;}")
  write ("  .TableContent {font-size: 12px;background-color:#9CB8F4;}")
  write ("  .TableContent td{padding-left:4px;padding-right:4px;line-height: 150%; word-break:keep-all;	word-wrap:normal;}")
  write ("  .TableTitle {background-color:#0080C0;color: #ffffff;font-weight:bold;text-align:center;}")
  write ("  .SortTableTitle {background-color:#0080C0;color: #ffffff;font-weight:bold;text-align:center;}")
  write ("  .SortTableTitle td{cursor: hand;border-left:0px;border-top:0px;border-right: 1px outset;border-bottom: 1px outset;white-space: nowrap;text-decoration: none;overflow: hidden;}")
  write ("  .TableFilterList {line-height: 100%}")
  write ("  .TableFilterList td{padding-left:0px;padding-right:0px;}")
  write ("  .TableBodyOut1 {background-color: #EDF1F8;cursor:default;}")
  write ("  .TableBodyOut2 {background-color: #D5E0F7;cursor:default;}")
  write ("  .TableBodyOver {background-color: #ABCBE2;cursor:default;}")
  write ("  .TableBodyClicked {background-color: #C1CDD8;}")
  write ("  .Page_Tools a{font-size: 12px;color: #000000;text-decoration: none;border: 1px solid #0066CC;line-height: 120%;letter-spacing: 4px;padding: 2px 0px 0px 4px;}")
  write ("  .Page_Tools a:link,.Page_Tools a:visited{background-color: #FFFFFF;}")
  write ("  .Page_Tools a:hover{color: red;}")
  write ("  .Page_List {font-size: 12px;color: #000000;}")
  write ("  .Page_List a{color: #000000;text-decoration: none;border: 1px solid #0066CC;line-height: 120%;letter-spacing: 1px;padding: 2px 0px 0px 2px;}")
  write ("  .Page_List a:link,.Page_Tools a:visited{background-color: #FFFFFF;}")
  write ("  .Page_List a:hover{background-color: #006699;color: #FFFFFF;}")
  write ("  .Page_List_input{border: 1px solid #0066CC;}")
  write ("  .Page_List_button{height: 20px;}")
  write ("-->")
  write ("</style>")
}
