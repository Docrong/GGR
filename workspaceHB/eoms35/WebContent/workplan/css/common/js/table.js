/********************************************************************************
 Copyright (c) 2004-2006, 亿阳信通网络事业部IP网管
 All rights reserved.
 Filename ：table.js
 Abstract ：表格相关控制方法
 Version　：2.0
 Author   ：Liu Guoyuan
 Finished Date ：2004-03-30
 Last Modified ：2004-08-12

 2.1
   2004-08-12 修改_loadJS，解决目录中有大写时找不到路径的问题(苗鹏)
			  将装载pingying.js,bihua.js的函数改为_loadJS

 2.0
   2004-05-19 修改 sortTable() 方法，增强容错能力
   2004-04-28 添加 sortTable(tbl,colIndex,dataType,By) 表格排序方法

 1.11
   2004-04-28 添加 fixTableWidth(srcTable,fixTable) 将两个表格宽度设为相等。
   2004-04-17 添加 switchRow2Col(objTable) 表格行列交换

 1.1
  修改 getFilterSelect() 逻辑，1.0为页面载入完成后一次读取全部的列数据，当列数据较多时会影响页面载入速度，
  改为点击SELECT列表时读取列数据，增加loadFilterListOnDemand方法（读入指定列数据）

 1.0
  getFilterSelect(tableName,colIndex)  从表格指定列中得到过滤列表（Select）
  filterTable(table,colIndex,value)  过滤表格显示内容   参数：表格名，列索引，保留显示的内容
********************************************************************************/

//读取公共库
function _loadJS(src){
	var script=document.getElementsByTagName("SCRIPT");
	for(var i=0;i<script.length;i++){
		var s=script[i].src;
		if(s.indexOf(src)>=0) return;
		if(s.indexOf("/common/js/table.js")!=-1){jsPath=s.replace("table.js","")}
	}
	var oScript = document.createElement("<SCRIPT>");
	oScript.src = jsPath+src;
	script[0].insertAdjacentElement("afterEnd",oScript);
}
_loadJS("lib.js");

/*********************************************
 从表格指定列中得到过滤列表（Select）
 参数：表格名，列索引
*********************************************/
function getFilterSelect(tableName,colIndex){
  var table = getObject(tableName);
  if (!checkTable(tableName,colIndex)) return "";
  try{
    var colWidth = table.rows[0].cells[colIndex].offsetWidth;
  }
  catch (E){
    return "";
  }
  returnStr = "<select style=\"width:"+ colWidth +"\" isload=\"false\" "+
              " onclick=\"loadFilterListOnDemand('"+ tableName +"'," + colIndex + ");\""+
              ">";
  returnStr+= "<option value=\"\">全部</option>";
  returnStr+= "</select>";
  return returnStr;
}

/******************************************
 读取指定列数据至Select列表
******************************************/
function loadFilterListOnDemand(tableName,colIndex){
  var oSelect = event.srcElement;
  if (oSelect.isload == true){ //数据已读入
    return;
  }else{
    oSelect.isload = true;
  }
  var table = eval(tableName);
  var tr = table.rows;
  var td;
  var trCount = tr.length;
  var aryAllData = new Array(trCount);
  var arySize = 0;  //数组实际大小
  for (var i=0;i<trCount;i++){
    if (tr[i].parentElement.tagName=="THEAD" || tr[i].className.toLowerCase().indexOf("tabletitle")>=0) continue;
    td = tr[i].cells[colIndex];
    if (td!=null) { //单元格正常
      if (td.innerText!=""){
        aryAllData[arySize] = td.innerText;
        arySize ++;
      }
    };
  }
  if (arySize<trCount) aryAllData = aryAllData.left(arySize);
  aryAllData = aryAllData.distinct();

  for (var i=0;i<aryAllData.length;i++){
    oSelect.add(new Option(aryAllData[i],aryAllData[i]));
  }
  oSelect.onchange = function(){
    /*** 将其它过滤列表设为选中全部 ***/
    var oAllFilterSelect = document.getElementsByTagName("SELECT");
    for (var i=0;i<oAllFilterSelect.length;i++){
      if (oAllFilterSelect[i].isload && oAllFilterSelect[i].sourceIndex!=oSelect.sourceIndex){
        oAllFilterSelect[i].options[0].selected = true;
      }
    }
    /*** 过滤指定列 ***/
    filterTable(tableName,colIndex,this.value);
  }
}


/*********************************************
 过滤表格显示内容
 参数：表格对象，列索引，保留显示的内容
*********************************************/
function filterTable(tableName,colIndex,showValue){
  var table = getObject(tableName);
  if (!checkTable(tableName,colIndex)) return;
  var tr = table.rows;
  var trCount = tr.length;
  var td;
  var showFlag;
  for (var i=0;i<trCount;i++){
    if (tr[i].parentElement.tagName=="THEAD" || tr[i].className.toLowerCase().indexOf("tabletitle")>=0) continue;
    td = tr[i].cells[colIndex];
    if (td==null){ //单元格有误
      showFlag = false;
    }else{
      showFlag = (showValue == "" || showValue == "nothing" || td.innerText==showValue);
    }
    if (showFlag){//显示
      tr[i].style.display = "block";
    }else{
      tr[i].style.display = "none";
    }
  }
  //处理样式，当表格使用过page.js中的setTableStyle方法，过滤后会使单元格颜色混乱，需要重新调用该方法
  if (table.isSetStyle) {setTableStyle(table,table.needMouseStyle);}
}

/*********************************************
 表格行列交换
 参数：表格对象
*********************************************/
function switchRow2Col(objTab){
  var tabArray = new Array();
  for(var i = 0;i<objTab.rows[0].cells.length;i++){
    var tmpArray = new Array()
    for(var j = 0; j<objTab.rows.length;j++){
      tmpArray[tmpArray.length] = objTab.rows[j].cells[i].outerHTML
    }
    tabArray[tabArray.length] = tmpArray;
  }
  var str = "";
  for(var i =0;i<tabArray.length;i++){
    str += "<tr>" + tabArray[i].join("") + "</tr>"
  }
  str = "<table width=\"" + objTab.width +
          "\" cellPadding=\""+ objTab.cellPadding +
          "\" cellSpacing=\""+ objTab.cellSpacing +
          "\" id=\"" + objTab.id +
        "\">" + str + "</table>";
  objTab.outerHTML = str
}

/*********************************************
 将两个表格宽度设为相等。
 参数：源表格对象，需要修改宽度的表格对象
*********************************************/
function fixTableWidth(srcTable,fixTable){
  var srcCellCount = srcTable.rows[0].cells.length;
  if (srcCellCount != fixTable.rows[0].cells.length) return;
  for(var i = 0;i<srcCellCount;i++){
    var srcWidth = srcTable.rows[0].cells[i].offsetWidth;
    var fixTD = fixTable.rows[0].cells[i];
    if (fixTD.firstChild.tagName=="SELECT"){
      fixTD.firstChild.style.width = srcWidth;
    }
    fixTD.style.width = srcWidth;
  }
}

/*********************************************
  排序表格
  参数：
   tbl       - TABLE, TBODY, THEAD or TFOOT 元素
   colIndex  - 列索引,空为点击的列索引
   dataType  - 数据类型: 空：自动判断，　CHAR(字符),NUM(数字),DATE(日期),CNPY(中文拼音),CNBH(中文笔划) ,
   By        - 默认排序方向，空：ASC:reverse， DESC:descending
*********************************************/
function sortTable(tbl,colIndex,dataType,By){
  if (!checkTable(tbl,colIndex)) return;

  //处理表格对象，设置表格排序对象为其tbody
  if (tbl.tagName=="TABLE"){
    tbl = tbl.tBodies[0];
  }
  table = tbl.parentNode; //表格对象
  
  var obj = event.srcElement;    //点击的对象，用于保存排序方向
  var objTD;                     //排序列的第一行,用于设置排序箭头

  //处理列索引
  if (colIndex == null){//没有指定列索引，为当前点击的列索引
    if (obj.tagName!="TD") obj=objTD.parentNode;
    if (obj.tagName == "TD"){
      colIndex = obj.cellIndex;
      objTD = obj;
    }else{
      return;
    }
  }else if (colIndex>=0){//指定了列索引
    var objTD = table.rows[0].cells[colIndex];
  }
  else{
    alert ("请设置要排序的列！");
    return;
  }

  //处理排序方向（利用点击的对象保存当前列排序方向）
  if (obj.sortTableBy == null){//没有排序，保存指定排序方向
    obj.sortTableBy = By == null ? "ASC" : By;
  }else{
    obj.sortTableBy = obj.sortTableBy == "ASC" ? "DESC" : "ASC";
  }
  By = obj.sortTableBy;

  //处理排序箭头
  if (objTD.tagName == "TD" && objTD.parentNode.parentNode.tagName == "THEAD"){ //点击的对象为单元格以及为表格标题
    for (var i=0;i<objTD.parentNode.cells.length;i++){
      objTD.parentNode.cells[i].innerHTML = objTD.parentNode.cells[i].innerHTML.replace(/↑|↓/,"");
    }
    objTD.innerHTML += obj.sortTableBy == "DESC" ? "↓" : "↑";
  }

  if (!checkTable(tbl,colIndex)) return;
  //自动判断数据类型
  if (dataType==null){
    checkText = tbl.rows[0].cells[colIndex].innerText;
    if (checkText == "" || checkText == null){
      dataType = "CHAR";
    }else if (checkText.isDate()){
      dataType = "DATE";
    }else if (checkText.isChinese()){
      dataType = "CNPY";
    }else if (checkText.isNumeric()){
      dataType = "NUM";
    }else{
      dataType = "CHAR";
    }
  }
  if(dataType.toUpperCase()=="CNPY")
	_loadJS("pingying.js");
  else if(dataType.toUpperCase()=="CNBH") 
	_loadJS("bihua.js");
  //此处可新增特殊格式处理
  var extConvert=function(Data,Type){
    switch(Type.toLowerCase()){
      case "NUM2":  //自定义类型名
        return parseFloat(Data.replace(/,/g,""));
        break;
      default:
        return Data
    }
  }
  var convert=function(Data,Type){
    var Rst=Data;
    var cn_code=function(idx){
      if  (idx<100){return String.fromCharCode(0,idx);}
      else{var s=idx.toString();return String.fromCharCode(parseInt(s.substring(0,s.length-2)),idx%100);}
    }
    switch(Type.toUpperCase()){
      case "CHAR":  Rst=Data;break;
      case "NUM":  Rst=parseFloat(Data.replace(/,/g,""));if(isNaN(Rst))Rst=Data;break;
      case "DATE":  Rst=Date.parse(Data.replace(/\-/g, '/'));if(isNaN(Rst))Rst=Data;break;
      case "CNPY":
        if(typeof(strChinesePingYing)=="undefined"){return Data;}
        Rst="";
        for(var i=0;i<Data.length;i++){var idx=strChinesePingYing.indexOf(Data.charAt(i));Rst+=idx!=-1?cn_code(idx):Data.charAt(i);}
        break;
      case "CNBH":
        if(typeof(strChineseBiHua)=="undefined"){return Data;}
        Rst="";
        for(var i=0;i<Data.length;i++){var idx=strChineseBiHua.indexOf(Data.charAt(i));Rst+=idx!=-1?cn_code(idx):Data.charAt(i);}
        break;
      default : Rst=extConvert(Data,Type);if(Rst==null){Rst=Data};
    }
    return Rst;
  }
  var ByAsc=(By=="ASC");
  var arySort=[];
  for(var i=0;i<tbl.rows.length;i++){
    var Data=(tbl.rows[i].cells[colIndex])?(tbl.rows[i].cells[colIndex].innerText.toLowerCase()):null;
    Data=convert(Data,dataType);
    arySort[i]=new Array(Data,tbl.rows[i]);
  }
  arySort.sort(function(){var a=arguments;return ByAsc?(a[0][0]>a[1][0]?1:(a[0][0]<a[1][0]?-1:0)):(a[0][0]<a[1][0]?1:(a[0][0]>a[1][0]?-1:0));})
  for(i=0;i<arySort.length;i++){tbl.appendChild(arySort[i][1]);}
  //处理样式，当表格使用过page.js中的setTableStyle方法，排序后会使单元格颜色混乱，需要重新调用该方法
  if (table.isSetStyle) {setTableStyle(table,table.needMouseStyle);}
}

/*********************************************
 验证表格及列索引参数是否正确
 参数：表格对象，列索引
*********************************************/
function checkTable(table,colIndex){
  var table = getObject(table);
  if (table == null) return false;
  var tr = table.rows;
  if (tr.length<1 || tr[0].cells == null){
    return false;
  }
  else if (isNull(colIndex)){
    if (tr[0].cells.length<colIndex) return false;
  }
  return true;
}