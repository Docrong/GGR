<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page import="java.util.*,java.text.SimpleDateFormat,java.lang.*, org.apache.struts.util.*,java.io.*,java.sql.*,javax.sql.*"%>
 

<html:form  method="post" action="/TawTestcardManager/returnupdate" >

<SCRIPT language=javascript>

var outObject;
var old_dd = null;
function setday(tt,obj) //主调函数
{
  if (arguments.length >  2){alert("对不起！传入本控件的参数太多！");return;}
  if (arguments.length == 0){alert("对不起！您没有传回本控件任何参数！");return;}
  var dads  = document.all.bltDateLayer.style;var th = tt;
  var ttop  = tt.offsetTop;     //TT控件的定位点高
  var thei  = tt.clientHeight;  //TT控件本身的高
  var tleft = tt.offsetLeft;    //TT控件的定位点宽
  var ttyp  = tt.type;          //TT控件的类型
  while (tt = tt.offsetParent){ttop+=tt.offsetTop; tleft+=tt.offsetLeft;}
  dads.top  = (ttyp=="image")? ttop+thei : ttop+thei+6;
  dads.left = tleft;
  outObject = (arguments.length == 1) ? th : obj;
  dads.display = '';
  event.returnValue=false;
}

var MonHead = new Array(12);    		   //定义阳历中每个月的最大天数
    MonHead[0] = 31; MonHead[1] = 28; MonHead[2] = 31; MonHead[3] = 30; MonHead[4]  = 31; MonHead[5]  = 30;
    MonHead[6] = 31; MonHead[7] = 31; MonHead[8] = 30; MonHead[9] = 31; MonHead[10] = 30; MonHead[11] = 31;

var bltTheYear=new Date().getFullYear(); //定义年的变量的初始值
var bltTheMonth=new Date().getMonth()+1; //定义月的变量的初始值
var bltWDay=new Array(37);               //定义写日期的数组
var m_yy, m_mm, m_dd;
function document.onclick() //任意点击时关闭该控件
{
return;
  with(window.event.srcElement)
  { if (tagName != "INPUT" && getAttribute("Author")==null)
    document.all.bltDateLayer.style.display="none";
  }
}

function bltWriteHead(yy,mm)  //往 head 中写入当前的年与月
  { document.all.bltYearHead.innerText  = yy;
    document.all.bltMonthHead.innerText = mm;
  }

function tmpSelectYearInnerHTML(strYear) //年份的下拉框
{
  if (strYear.match(/\D/)!=null){alert("年份输入参数不是数字！");return;}
  var m = (strYear) ? strYear : new Date().getFullYear();
  if (m < 1000 || m > 9999) {alert("年份值不在 1000 到 9999 之间！");return;}
  var n = m - 10;
  if (n < 1000) n = 1000;
  if (n + 26 > 9999) n = 9974;
  var s = "<select Author=blt name=tmpSelectYear style='font-size: 12px' "
     s += "onblur='document.all.tmpSelectYearLayer.style.display=\"none\"' "
     s += "onchange='document.all.tmpSelectYearLayer.style.display=\"none\";"
     s += "bltTheYear = this.value; bltSetDay(bltTheYear,bltTheMonth)'>\r\n";
  var selectInnerHTML = s;
  for (var i = n; i < n + 26; i++)
  {
    if (i == m)
       {selectInnerHTML += "<option value='" + i + "' selected>" + i + "年" + "</option>\r\n";}
    else {selectInnerHTML += "<option value='" + i + "'>" + i + "年" + "</option>\r\n";}
  }
  selectInnerHTML += "</select>";
  document.all.tmpSelectYearLayer.style.display="";
  document.all.tmpSelectYearLayer.innerHTML = selectInnerHTML;
  document.all.tmpSelectYear.focus();
}

function tmpSelectMonthInnerHTML(strMonth) //月份的下拉框
{
  if (strMonth.match(/\D/)!=null){alert("月份输入参数不是数字！");return;}
  document.all.tmpSelectMonth.focus();
}

function closeLayer()               //这个层的关闭
  {
    document.all.bltDateLayer.style.display="none";
  }

function document.onkeydown()
  {
    if (window.event.keyCode==27)document.all.bltDateLayer.style.display="none";
  }

function IsPinYear(year)            //判断是否闰平年
  {
    if (0==year%4&&((year%100!=0)||(year%400==0))) return true;else return false;
  }

function GetMonthCount(year,month)  //闰年二月为29天
  {
    var c=MonHead[month-1];if((month==2)&&IsPinYear(year)) c++;return c;
  }

function GetDOW(day,month,year)     //求某天的星期几
  {
    var dt=new Date(year,month-1,day).getDay()/7; return dt;
  }

function bltPrevY()  //往前翻 Year
  {
    if(bltTheYear > 999 && bltTheYear <10000){bltTheYear--;}
    else{alert("年份超出范围（1000-9999）！");}
    bltSetDay(bltTheYear,bltTheMonth);
  }
function bltNextY()  //往后翻 Year
  {
    if(bltTheYear > 999 && bltTheYear <10000){bltTheYear++;}
    else{alert("年份超出范围（1000-9999）！");}
    bltSetDay(bltTheYear,bltTheMonth);
  }
function bltToday()  //Today Button
  {
    bltTheYear = new Date().getFullYear();
    bltTheMonth = new Date().getMonth()+1;
    bltSetDay(bltTheYear,bltTheMonth);
  }
function bltPrevM()  //往前翻月份
  {
    if(bltTheMonth>1){bltTheMonth--}else{bltTheYear--;bltTheMonth=12;}
    bltSetDay(bltTheYear,bltTheMonth);
  }
function bltNextM()  //往后翻月份
  {
    if(bltTheMonth==12){bltTheYear++;bltTheMonth=1}else{bltTheMonth++}
    bltSetDay(bltTheYear,bltTheMonth);
  }

function bltSetDay(yy,mm)   //主要的写程序**********
{
  bltWriteHead(yy,mm);
  for (var i = 0; i < 37; i++)
  {
  	bltWDay[i]=""
  };  //将显示框的内容全部清空
  var day1 = 1,firstday = new Date(yy,mm-1,1).getDay();  //某月第一天的星期几
  for( var i = firstday; day1 < GetMonthCount(yy,mm)+1; i++)
  {
  	bltWDay[i]=day1;
  	day1++;
  }
  for( var i = 0; i < 37; i++)
  { var da = eval("document.all.bltDay"+i)     //书写新的一个月的日期星期排列
    if (bltWDay[i]!="")
    {
    	da.innerHTML = "<b>" + bltWDay[i] + "</b>";
      da.style.backgroundColor = (yy == new Date().getFullYear() &&
//      mm == new Date().getMonth()+1 && bltWDay[i] == new Date().getDate()) ? "#FFD700" : "#ADD8E6";
      mm == new Date().getMonth()+1 && bltWDay[i] == new Date().getDate()) ? "#ADD8E6" : "#ADD8E6";
      da.style.cursor="hand"
    }
    else
    {
    	da.innerHTML="";
    	da.style.backgroundColor="";
    	da.style.cursor="default";
   	}
  }
}

function setDayTime()
{
	var f = window.document.forms[ 0 ];
	//注：在这里你可以输出改成你想要的格式
	if( m_yy != null && m_mm != null && m_dd != null )
	{

		if( f.m_hour.value == '-' || f.m_min.value == '-' )	//如果没选时间
			outObject.value= m_yy + "-" + m_mm + "-" + m_dd;
		else
  		outObject.value= m_yy + "-" + m_mm + "-" + m_dd + " " + f.m_hour.value + ":" + f.m_min.value + ":00";
  }
  closeLayer();
}
function bltDayClick( n, obj )  //点击显示框选取日期，主输入函数*************
{
  var yy = bltTheYear;
  var mm = bltTheMonth;
  m_yy = yy;
  if (mm < 10)
  {
    m_mm = "0" + mm;
  }
  else
  {
    m_mm = "" + mm;
  }
  if (outObject)
  {
    if (!n)
    {
    	outObject.value="";
    	return;
    }
    if ( n < 10 )
		{
			m_dd = "0" + n;
		}
		else
		{
			m_dd = "" + n;
		}
		if( old_dd != null )
			old_dd.style.backgroundColor = "#ADD8E6";
		old_dd = obj;
		obj.style.backgroundColor = "#FFD700";
  }
  else
  {
  	closeLayer();
  	alert("输出有误！");
  }
}
</SCRIPT>

<SCRIPT language=javascript>
document.writeln('<div id=bltDateLayer style="position: absolute; width: 142; height: 166; z-index: 9998; display: none">');
document.writeln('<span id=tmpSelectYearLayer  style="z-index: 9999;position: absolute;top: 2; left: 18;display: none"></span>');
document.writeln('<span id=tmpSelectMonthLayer style="z-index: 9999;position: absolute;top: 2; left: 75;display: none"></span>');
document.writeln('<table border=0 cellspacing=1 cellpadding=0 width=142 height=160 bgcolor=#808080 onselectstart="return false">');
document.writeln('  <tr><td width=142 height=23 bgcolor=#FFFFFF><table border=0 cellspacing=1 cellpadding=0 width=140 height=23>');
document.writeln('      <tr align=center><td width=20 align=center bgcolor=#808080 style="font-size:12px;cursor: hand;color: #FFD700" ');
document.writeln('        onclick="bltPrevM()" title="向前翻一月" Author=blt><b Author=blt>&lt;&lt;</b>');
document.writeln('        </td><td width=100 align=center style="font-size:12px;cursor:default" Author=blt>');
document.writeln('        <span Author=blt id=bltYearHead onclick="tmpSelectYearInnerHTML(this.innerText)"></span>&nbsp;年&nbsp;<span');
document.writeln('         id=bltMonthHead Author=blt onclick="tmpSelectMonthInnerHTML(this.innerText)"></span>&nbsp;月</td>');
document.writeln('        <td width=20 bgcolor=#808080 align=center style="font-size:12px;cursor: hand;color: #FFD700" ');
document.writeln('         onclick="bltNextM()" title="往后翻一月" Author=blt><b Author=blt>&gt;&gt;</b></td></tr>');
document.writeln('    </table></td></tr>');
document.writeln('  <tr><td width=142 height=18 bgcolor=#808080>');
document.writeln('<table border=0 cellspacing=0 cellpadding=0 width=140 height=1 style="cursor:default">');
document.writeln('<tr align=center><td style="font-size:12px;color:#FFFFFF" Author=blt>日</td>');
document.writeln('<td style="font-size:12px;color:#FFFFFF" Author=blt>一</td><td style="font-size:12px;color:#FFFFFF" Author=blt>二</td>');
document.writeln('<td style="font-size:12px;color:#FFFFFF" Author=blt>三</td><td style="font-size:12px;color:#FFFFFF" Author=blt>四</td>');
document.writeln('<td style="font-size:12px;color:#FFFFFF" Author=blt>五</td><td style="font-size:12px;color:#FFFFFF" Author=blt>六</td></tr>');
document.writeln('</table></td></tr><!-- Author:F.R.Huang(blt) http://www.blt.com/ mail: blt@hzcnc.com 2002-10-8 -->');
document.writeln('  <tr><td width=142 height=120>');
document.writeln('    <table border=0 cellspacing=1 cellpadding=0 width=140 height=120 bgcolor=#FFFFFF Author=blt> ');
var n=0; for (j=0;j<6;j++){ document.writeln (' <tr align=center>'); for (i=0;i<7;i++){
document.writeln('<td width=20 height=20 id=bltDay'+n+' style="font-size:12px" Author=blt onclick=bltDayClick(this.innerText,this)></td>');n++;}
document.writeln('</tr>');}
document.writeln('      <tr align=center>');
document.writeln('        <td align=center colspan=4 Author=blt >');
document.writeln('         <select name="m_hour" Author=blt>');
for( j = 0; j < 24; j++ )
{
	if( j < 10 )
		document.writeln('         <option value="0' + j + '">' + j + '</option>');
	else
		document.writeln('         <option value="' + j + '">' + j + '</option>');
}
document.writeln('         </select>时</td>');
document.writeln('        <td align=center colspan=4 Author=blt>');
document.writeln('         <select name="m_min" Author=blt>');
for( j = 0; j < 60; j++ )
{
	if( j < 10 )
		document.writeln('         <option value="0' + j + '">' + j + '</option>');
	else
		document.writeln('         <option value="' + j + '">' + j + '</option>');
}
document.writeln('         </select>分');
document.writeln('         </td></tr>');
document.writeln('         <tr>');
document.writeln('        <td colspan=4 align=center Author=blt>');
document.writeln('         <Label onclick=setDayTime()>确 定</Label>');
document.writeln('        </td>');

document.writeln('        <td colspan=4 align=center Author=blt>');
document.writeln('         <Label onclick=closeLayer()>取 消</Label>');
document.writeln('         &nbsp;</td></tr>');
document.writeln('    </table></td></tr><tr><td>');
document.writeln('        <table border=0 cellspacing=1 cellpadding=0 width=100% bgcolor=#FFFFFF>');
document.writeln('          <tr><td Author=blt align=left><Label Author=blt title="向前翻一年" onclick="bltPrevY()" ');
document.writeln('             onfocus="this.blur()">< </Label> <Label Author=blt title="向前翻一月" ');
document.writeln('             onclick="bltPrevM()" onfocus="this.blur()"><<</Label></td><td ');
document.writeln('             Author=blt align=center><Label Author=blt onclick="bltToday()" ');
document.writeln('             onfocus="this.blur()" title="现在的年月">今 天</Label></td><td ');
document.writeln('             Author=blt align=right><Label Author=blt onclick="bltNextM()" ');
document.writeln('             onfocus="this.blur()" title="往后翻一月">>></Label> <Label ');
document.writeln('             Author=blt title="往后翻一年" onclick="bltNextY()"');
document.writeln('             onfocus="this.blur()"> ></Label></td>');
document.writeln('</tr></table></td></tr></table></div>');
bltSetDay(bltTheYear,bltTheMonth);



</SCRIPT>




<SCRIPT language=javascript>
var outObject;
var old_dd = null;
function setday(tt,obj) //主调函数
{
  if (arguments.length >  2){alert("对不起！传入本控件的参数太多！");return;}
  if (arguments.length == 0){alert("对不起！您没有传回本控件任何参数！");return;}
  var dads  = document.all.bltDateLayer.style;var th = tt;
  var ttop  = tt.offsetTop;     //TT控件的定位点高
  var thei  = tt.clientHeight;  //TT控件本身的高
  var tleft = tt.offsetLeft;    //TT控件的定位点宽
  var ttyp  = tt.type;          //TT控件的类型
  while (tt = tt.offsetParent){ttop+=tt.offsetTop; tleft+=tt.offsetLeft;}
  dads.top  = (ttyp=="image")? ttop+thei : ttop+thei+6;
  dads.left = tleft;
  outObject = (arguments.length == 1) ? th : obj;
  dads.display = '';
  event.returnValue=false;
}

var MonHead = new Array(12);    		   //定义阳历中每个月的最大天数
    MonHead[0] = 31; MonHead[1] = 28; MonHead[2] = 31; MonHead[3] = 30; MonHead[4]  = 31; MonHead[5]  = 30;
    MonHead[6] = 31; MonHead[7] = 31; MonHead[8] = 30; MonHead[9] = 31; MonHead[10] = 30; MonHead[11] = 31;

var bltTheYear=new Date().getFullYear(); //定义年的变量的初始值
var bltTheMonth=new Date().getMonth()+1; //定义月的变量的初始值
var bltWDay=new Array(37);               //定义写日期的数组
var m_yy, m_mm, m_dd;
function document.onclick() //任意点击时关闭该控件
{
return;
  with(window.event.srcElement)
  { if (tagName != "INPUT" && getAttribute("Author")==null)
    document.all.bltDateLayer.style.display="none";
  }
}

function bltWriteHead(yy,mm)  //往 head 中写入当前的年与月
  { document.all.bltYearHead.innerText  = yy;
    document.all.bltMonthHead.innerText = mm;
  }

function tmpSelectYearInnerHTML(strYear) //年份的下拉框
{
  if (strYear.match(/\D/)!=null){alert("年份输入参数不是数字！");return;}
  var m = (strYear) ? strYear : new Date().getFullYear();
  if (m < 1000 || m > 9999) {alert("年份值不在 1000 到 9999 之间！");return;}
  var n = m - 10;
  if (n < 1000) n = 1000;
  if (n + 26 > 9999) n = 9974;
  var s = "<select Author=blt name=tmpSelectYear style='font-size: 12px' "
     s += "onblur='document.all.tmpSelectYearLayer.style.display=\"none\"' "
     s += "onchange='document.all.tmpSelectYearLayer.style.display=\"none\";"
     s += "bltTheYear = this.value; bltSetDay(bltTheYear,bltTheMonth)'>\r\n";
  var selectInnerHTML = s;
  for (var i = n; i < n + 26; i++)
  {
    if (i == m)
       {selectInnerHTML += "<option value='" + i + "' selected>" + i + "年" + "</option>\r\n";}
    else {selectInnerHTML += "<option value='" + i + "'>" + i + "年" + "</option>\r\n";}
  }
  selectInnerHTML += "</select>";
  document.all.tmpSelectYearLayer.style.display="";
  document.all.tmpSelectYearLayer.innerHTML = selectInnerHTML;
  document.all.tmpSelectYear.focus();
}

function tmpSelectMonthInnerHTML(strMonth) //月份的下拉框
{
  if (strMonth.match(/\D/)!=null){alert("月份输入参数不是数字！");return;}
  var m = (strMonth) ? strMonth : new Date().getMonth() + 1;
  var s = "<select Author=blt name=tmpSelectMonth style='font-size: 12px' "
     s += "onblur='document.all.tmpSelectMonthLayer.style.display=\"none\"' "
     s += "onchange='document.all.tmpSelectMonthLayer.style.display=\"none\";"
     s += "bltTheMonth = this.value; bltSetDay(bltTheYear,bltTheMonth)'>\r\n";
  var selectInnerHTML = s;
  for (var i = 1; i < 13; i++)
  {
    if (i == m)
       {selectInnerHTML += "<option value='"+i+"' selected>"+i+"月"+"</option>\r\n";}
    else {selectInnerHTML += "<option value='"+i+"'>"+i+"月"+"</option>\r\n";}
  }
  selectInnerHTML += "</select>";
  document.all.tmpSelectMonthLayer.style.display="";
  document.all.tmpSelectMonthLayer.innerHTML = selectInnerHTML;
  document.all.tmpSelectMonth.focus();
}

function closeLayer()               //这个层的关闭
  {
    document.all.bltDateLayer.style.display="none";
  }

function document.onkeydown()
  {
    if (window.event.keyCode==27)document.all.bltDateLayer.style.display="none";
  }

function IsPinYear(year)            //判断是否闰平年
  {
    if (0==year%4&&((year%100!=0)||(year%400==0))) return true;else return false;
  }

function GetMonthCount(year,month)  //闰年二月为29天
  {
    var c=MonHead[month-1];if((month==2)&&IsPinYear(year)) c++;return c;
  }

function GetDOW(day,month,year)     //求某天的星期几
  {
    var dt=new Date(year,month-1,day).getDay()/7; return dt;
  }

function bltPrevY()  //往前翻 Year
  {
    if(bltTheYear > 999 && bltTheYear <10000){bltTheYear--;}
    else{alert("年份超出范围（1000-9999）！");}
    bltSetDay(bltTheYear,bltTheMonth);
  }
function bltNextY()  //往后翻 Year
  {
    if(bltTheYear > 999 && bltTheYear <10000){bltTheYear++;}
    else{alert("年份超出范围（1000-9999）！");}
    bltSetDay(bltTheYear,bltTheMonth);
  }
function bltToday()  //Today Button
  {
    bltTheYear = new Date().getFullYear();
    bltTheMonth = new Date().getMonth()+1;
    bltSetDay(bltTheYear,bltTheMonth);
  }
function bltPrevM()  //往前翻月份
  {
    if(bltTheMonth>1){bltTheMonth--}else{bltTheYear--;bltTheMonth=12;}
    bltSetDay(bltTheYear,bltTheMonth);
  }
function bltNextM()  //往后翻月份
  {
    if(bltTheMonth==12){bltTheYear++;bltTheMonth=1}else{bltTheMonth++}
    bltSetDay(bltTheYear,bltTheMonth);
  }

function bltSetDay(yy,mm)   //主要的写程序**********
{
  bltWriteHead(yy,mm);
  for (var i = 0; i < 37; i++)
  {
  	bltWDay[i]=""
  };  //将显示框的内容全部清空
  var day1 = 1,firstday = new Date(yy,mm-1,1).getDay();  //某月第一天的星期几
  for( var i = firstday; day1 < GetMonthCount(yy,mm)+1; i++)
  {
  	bltWDay[i]=day1;
  	day1++;
  }
  for( var i = 0; i < 37; i++)
  { var da = eval("document.all.bltDay"+i)     //书写新的一个月的日期星期排列
    if (bltWDay[i]!="")
    {
    	da.innerHTML = "<b>" + bltWDay[i] + "</b>";
      da.style.backgroundColor = (yy == new Date().getFullYear() &&
//      mm == new Date().getMonth()+1 && bltWDay[i] == new Date().getDate()) ? "#FFD700" : "#ADD8E6";
      mm == new Date().getMonth()+1 && bltWDay[i] == new Date().getDate()) ? "#ADD8E6" : "#ADD8E6";
      da.style.cursor="hand"
    }
    else
    {
    	da.innerHTML="";
    	da.style.backgroundColor="";
    	da.style.cursor="default";
   	}
  }
}

function setDayTime()
{
	var f = window.document.forms[ 0 ];
	//注：在这里你可以输出改成你想要的格式
	if( m_yy != null && m_mm != null && m_dd != null )
	{

		if( f.m_hour.value == '-' || f.m_min.value == '-' )	//如果没选时间
			outObject.value= m_yy + "-" + m_mm + "-" + m_dd;
		else
  		outObject.value= m_yy + "-" + m_mm + "-" + m_dd + " " + f.m_hour.value + ":" + f.m_min.value + ":00";
  }
  closeLayer();
}
function bltDayClick( n, obj )  //点击显示框选取日期，主输入函数*************
{
  var yy = bltTheYear;
  var mm = bltTheMonth;
  m_yy = yy;
  if (mm < 10)
  {
    m_mm = "0" + mm;
  }
  else
  {
    m_mm = "" + mm;
  }
  if (outObject)
  {
    if (!n)
    {
    	outObject.value="";
    	return;
    }
    if ( n < 10 )
		{
			m_dd = "0" + n;
		}
		else
		{
			m_dd = "" + n;
		}
		if( old_dd != null )
			old_dd.style.backgroundColor = "#ADD8E6";
		old_dd = obj;
		obj.style.backgroundColor = "#FFD700";
  }
  else
  {
  	closeLayer();
  	alert("输出有误！");
  }
}
</SCRIPT>


<SCRIPT LANGUAGE=javascript>
function check(qq)
{

  return true;
}
function isnumeric(p)
{
 if (p == "")
  return false;
 var l = p.length;
 var count=0;
 for(var i=0; i<l; i++)
 {
  var digit = p.charAt(i);
  if(digit == "." )
 {
    ++count;
    if(count>1) {
	return false; }
   }
  else if(digit < "0" || digit > "9")
  {
  return false;
  }
 }
 return true;
}

</SCRIPT>
<script language="JavaScript" >
function bb(main){
if (document.tawTranfaultreportForm.city.options.length!=0) {
for (i=1;i<=document.tawTranfaultreportForm.city.options.length;i++){
if(document.tawTranfaultreportForm.city.options[i].value==main)
{document.tawTranfaultreportForm.city.options[i].selected="true";
document.tawTranfaultreportForm.city.disabled="false";
}
}
}
}

function check(){


}
</script>
 
</head>
<body background="<%=request.getContextPath()%>/images/bottom/bj_1_1.gif">
<div align="center">
	<table border="0" width="80%" cellspacing="0">
    <tr>
      <td class="table_title" align="center">
        <b>${eoms:a2u("测试卡清查历史信息")}</b>
      </td>
    </tr>    
	</table>
    <table border="0" width="95%" cellspacing="1" class="formTable">
       <tr>
                           
           		<td width="100%" colspan="14" height="25" bgcolor="#EEECED" align="center">
      			<bean:write name="pagerHeader" scope="request" filter="false"/>
      			<%! String key;%>
    		</td>                      
	<tr bgcolor="#FFFFFF">
          <td nowrap class="label" align="center" height="25">
          		${eoms:a2u("卡号")}
        	</td>
        	          <td nowrap class="label" align="center" height="25">
          		${eoms:a2u("msisdn号")}
        	</td>
        	          <td nowrap class="label" align="center" height="25">
          		${eoms:a2u("清查人")}
        	</td>
        	<td nowrap class="label" align="center" height="25">
          		${eoms:a2u("清查时间")}
        	</td>
        	<td nowrap class="label" align="center" height="25">
          		${eoms:a2u("原状态")}
        	</td>
       		<td nowrap class="label" align="center" height="25">
          		${eoms:a2u("清查后状态")}
        	</td>
        	<td nowrap class="label" align="center" height="25">
           		${eoms:a2u("清查备注")}
        	</td>
        	   	<logic:iterate id="tawTestcardClearNote" name="tawTestcardClearNote" type="com.boco.eoms.testcard.model.TawTestcardClearNote">
		<tr bgcolor="#FFFFFF">
     			<td nowrap   align="center">    
     			  <bean:write name="tawTestcardClearNote" property="iccid" scope="page"/>
    			</td>
    			<td nowrap   align="center">    
     			  <bean:write name="tawTestcardClearNote" property="msisdn" scope="page"/>
    			</td>
    			<td nowrap   align="center">    
     			  <bean:write name="tawTestcardClearNote" property="clearuser" scope="page"/>
    			</td>
    			<td nowrap   align="center" >
                  <bean:write name="tawTestcardClearNote" property="cleartime" scope="page"/>
    			</td>
                <td nowrap   align="center" >
                  <bean:write name="tawTestcardClearNote" property="oldstate" scope="page"/>
                </td>
    			<td nowrap   align="center">
                  <bean:write name="tawTestcardClearNote" property="newstate" scope="page"/>
    			</td>
    			<td nowrap   align="center" >
                  <bean:write name="tawTestcardClearNote" property="clearyuanyin" scope="page"/>
    			</td>
        </tr>
    	</logic:iterate>
      </table>
</html:form>

<logic:messagesPresent>
                  <html:messages id="error">
	<script type="text/javascript">
		<!--
                    alert("<bean:write name="error"/>");
		-->
	</script>
                  </html:messages>
</logic:messagesPresent>
</body>

