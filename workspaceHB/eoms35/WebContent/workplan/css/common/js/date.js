/********************************************************************************
 Copyright (c) 2004-2006,亿阳信通网络事业部IP网管
 All rights reserved.
 Filename ：date.js
 Abstract ：日期相关函数集
 Version　：1.0
 Author   ：Liu Guoyuan
 Finished Date ：2004-04-17
 Last Modified ：2004-08-12
 ********************************************************************************/

//读取公共库
if (typeof(loadJS)=="function") {
  _loadJS("lib.js");
  _loadJS("checkform.js");
}
else {
  alert ("date.js: 请调用comm.js!");
}

/*********************************
 从时间控件中获取完整时间
  控件命名规则：
    开始年月日控件名：start_time
    结束年月日控件名：end_time
    开始小时控件名：  bHour
    结束小时控件名：  eHour
    开始分钟控件名：  bMin
    结束分钟控件名：  eMin
 参数：
  timeType:
    "start" 返回开始时间,有错误时返回false
    "end" 返回结束时间,有错误时返回false
    无参数时，返回true|false，即仅做格式验证
  objIndex:
    日期输入对象索引，当页面存在多个日期选择框时，需要此参数。值从0开始
*********************************/
function getDateStrFromObj(timeType,objIndex){ 
  if (objIndex == null) objIndex = 0;
	if (!CheckIsDateTime(document.getElementsByName("start_time")[objIndex],"开始日期")) return false;
	//没有结束时间,返回开始时间
  if (typeof(document.getElementsByName("end_time")[objIndex])!="object"){
	  return document.getElementsByName("start_time")[objIndex].value+" "+
	         document.getElementsByName("bHour")[objIndex].value+":"+
	         document.getElementsByName("bMin")[objIndex].value+":00"; 
  }
  
  //有结束时间，判断结束时间是否大于开始时间
	if (!CheckIsDateTime(document.getElementsByName("end_time")[objIndex],"结束日期")) return false;

  var start_time = document.getElementsByName("start_time")[objIndex].value;
  var end_time = document.getElementsByName("end_time")[objIndex].value  
	var bHour=parseInt(document.getElementsByName("bHour")[objIndex].value);
	var eHour=parseInt(document.getElementsByName("eHour")[objIndex].value);
	var bMin=parseInt(document.getElementsByName("bMin")[objIndex].value);
	var eMin=parseInt(document.getElementsByName("eMin")[objIndex].value);
	
	var dateOffsetValue = end_time.toDate().diff(start_time.toDate(),"d"); //时间差值
	if (dateOffsetValue<0){
		alert ("结束日期必须大于开始日期！");
		document.getElementsByName("end_time")[objIndex].focus();
		return false;
	}
	else if (dateOffsetValue==0){
		if (bHour>eHour){
		  alert ("结束时间必须大于开始时间！");
			document.getElementsByName("eHour")[objIndex].focus();
			return false;
		}
		else {
			if (bHour==eHour&&bMin>=eMin){
			 	alert ("结束时间必须大于开始时间！");
				document.getElementsByName("eMin")[objIndex].focus();
				return false;
			}
		}
	}
	if (timeType == "start"){ //返回开始时间
	  return document.getElementsByName("start_time")[objIndex].value+" "+bHour+":"+bMin+":00"; 
	}else if (timeType == "end"){//返回结束时间
	  return document.getElementsByName("end_time")[objIndex].value+" "+eHour+":"+eMin+":00"; 
  }else{
    return true;
  }
}

/*********************************
 得到时间输入控件串
  控件命名：
    开始年月日控件名：start_time
    结束年月日控件名：end_time
    开始小时控件名：  bHour
    结束小时控件名：  eHour
    开始分钟控件名：  bMin
    结束分钟控件名：  eMin
 参数：
   timeType: 时间类型
      默认，"start" 返回开始时间输入控件串
      "end" 返回结束时间输入控件串,有错误时返回false
   defaultDate: 默认时间，为空时时间为当天
   minStep: 分钟步长，默认为1
   
 注：此方法应与getDateStrFromObj()方法配合使用，以获取时间。
*********************************/
function getDateInputObj(timeType,defaultDate,minStep){
  var asTimeObjName;
  if (timeType!=null && timeType.toLowerCase() == "end"){
    asTimeObjName = new Array ("end_time","eHour","eMin");  //控件名
  }else{
    asTimeObjName = new Array ("start_time","bHour","bMin");  //控件名
  }
  
  var defDate,defHour,defMin; //默认时间
  if (defaultDate == null || defaultDate == ""){//没有默认时间，开始时间为当天
    defDate = new Date().toDateString();
    defHour = 0;
    defMin = 0;
  }else{
    defDate = defaultDate.split(" ")[0];
    defHour = defaultDate.split(" ")[1].split(":")[0];
    defMin = defaultDate.split(" ")[1].split(":")[1];
  }
  if (minStep == null || minStep == ""){ //分钟步长
    minStep = 1;
  }
    
  //小时列表
  var strHourMenu = "";
  for (var i=0;i<24;i++){
    strHourMenu += "<option value='"+i+"'";
    if (i==defHour) strHourMenu += " selected";
    strHourMenu += ">";
    if (i<10) strHourMenu += "&nbsp;";
    strHourMenu += i+"时</option>";
  }
  //分钟列表
  var strMinMenu = ""
  for (var i=0;i<60;i+=minStep){
    strMinMenu += "<option value='"+i+"'";
    if (i==defMin) strMinMenu += " selected";
    strMinMenu += ">";
    if (i<10) strMinMenu += "&nbsp;";
    strMinMenu += +i+"分</option>";
  }
  var strResult = "<INPUT style='width:66;font-size:12px' type='text' name='"+asTimeObjName[0]+"' value='"+defDate+"' size='10' maxlength='10'";
  if (typeof(calendar)=="function"){//载入了日历控件
      strResult+= " onfocus='calendar()'";
  }
      strResult+= ">"+
                  "<SELECT style='width:47;font-size:12px' name='"+asTimeObjName[1]+"'>"+strHourMenu+"</SELECT>"+
                  "<SELECT style='width:47;font-size:12px' name='"+asTimeObjName[2]+"'>"+strMinMenu+"</SELECT>";
  return strResult;
}