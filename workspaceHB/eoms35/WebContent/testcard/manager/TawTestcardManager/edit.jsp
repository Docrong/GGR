<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_form.jsp"%>
<%@ include file="/common/xtreelibs.jsp"%>
<%@ page
	import="java.util.*,java.text.SimpleDateFormat,com.boco.eoms.infopub.util.*,java.lang.*, org.apache.struts.util.*,java.io.*,java.sql.*,javax.sql.*"%>
<%@ page
	import="com.boco.eoms.common.util.*,com.boco.eoms.common.controller.*, com.boco.eoms.common.dao.*,com.boco.eoms.jbzl.model.*,com.boco.eoms.jbzl.dao.*,com.boco.eoms.eventmanager.dao.TawEventDicDAO"%>

<html:form method="post" action="/TawTestcardManager/save"
	onsubmit="return check(this);">

	<SCRIPT language=javascript>

var outObject;
var old_dd = null;
function setday(tt,obj) //\u4E3B\u8C03\u51FD\u6570
{
  if (arguments.length >  2){alert("\u5BF9\u4E0D\u8D77\uFF01\u4F20\u5165\u672C\u63A7\u4EF6\u7684\u53C2\u6570\u592A\u591A\uFF01");return;}
  if (arguments.length == 0){alert("\u5BF9\u4E0D\u8D77\uFF01\u60A8\u6CA1\u6709\u4F20\u56DE\u672C\u63A7\u4EF6\u4EFB\u4F55\u53C2\u6570\uFF01");return;}
  var dads  = document.all.bltDateLayer.style;var th = tt;
  var ttop  = tt.offsetTop;     //TT\u63A7\u4EF6\u7684\u5B9A\u4F4D\u70B9\u9AD8
  var thei  = tt.clientHeight;  //TT\u63A7\u4EF6\u672C\u8EAB\u7684\u9AD8
  var tleft = tt.offsetLeft;    //TT\u63A7\u4EF6\u7684\u5B9A\u4F4D\u70B9\u5BBD
  var ttyp  = tt.type;          //TT\u63A7\u4EF6\u7684\u7C7B\u578B
  while (tt = tt.offsetParent){ttop+=tt.offsetTop; tleft+=tt.offsetLeft;}
  dads.top  = (ttyp=="image")? ttop+thei : ttop+thei+6;
  dads.left = tleft;
  outObject = (arguments.length == 1) ? th : obj;
  dads.display = '';
  event.returnValue=false;
}

var MonHead = new Array(12);    		   //\u5B9A\u4E49\u9633\u5386\u4E2D\u6BCF\u4E2A\u6708\u7684\u6700\u5927\u5929\u6570
    MonHead[0] = 31; MonHead[1] = 28; MonHead[2] = 31; MonHead[3] = 30; MonHead[4]  = 31; MonHead[5]  = 30;
    MonHead[6] = 31; MonHead[7] = 31; MonHead[8] = 30; MonHead[9] = 31; MonHead[10] = 30; MonHead[11] = 31;

var bltTheYear=new Date().getFullYear(); //\u5B9A\u4E49\u5E74\u7684\u53D8\u91CF\u7684\u521D\u59CB\u503C
var bltTheMonth=new Date().getMonth()+1; //\u5B9A\u4E49\u6708\u7684\u53D8\u91CF\u7684\u521D\u59CB\u503C
var bltWDay=new Array(37);               //\u5B9A\u4E49\u5199\u65E5\u671F\u7684\u6570\u7EC4
var m_yy, m_mm, m_dd;
function document.onclick() //\u4EFB\u610F\u70B9\u51FB\u65F6\u5173\u95ED\u8BE5\u63A7\u4EF6
{
return;
  with(window.event.srcElement)
  { if (tagName != "INPUT" && getAttribute("Author")==null)
    document.all.bltDateLayer.style.display="none";
  }
}

function bltWriteHead(yy,mm)  //\u5F80 head \u4E2D\u5199\u5165\u5F53\u524D\u7684\u5E74\u4E0E\u6708
  { document.all.bltYearHead.innerText  = yy;
    document.all.bltMonthHead.innerText = mm;
  }

function tmpSelectYearInnerHTML(strYear) //\u5E74\u4EFD\u7684\u4E0B\u62C9\u6846
{
  if (strYear.match(/\D/)!=null){alert("\u5E74\u4EFD\u8F93\u5165\u53C2\u6570\u4E0D\u662F\u6570\u5B57\uFF01");return;}
  var m = (strYear) ? strYear : new Date().getFullYear();
  if (m < 1000 || m > 9999) {alert("\u5E74\u4EFD\u503C\u4E0D\u5728 1000 \u5230 9999 \u4E4B\u95F4\uFF01");return;}
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
       {selectInnerHTML += "<option value='" + i + "' selected>" + i + "\u5E74" + "</option>\r\n";}
    else {selectInnerHTML += "<option value='" + i + "'>" + i + "\u5E74" + "</option>\r\n";}
  }
  selectInnerHTML += "</select>";
  document.all.tmpSelectYearLayer.style.display="";
  document.all.tmpSelectYearLayer.innerHTML = selectInnerHTML;
  document.all.tmpSelectYear.focus();
}

function tmpSelectMonthInnerHTML(strMonth) //\u6708\u4EFD\u7684\u4E0B\u62C9\u6846
{
  if (strMonth.match(/\D/)!=null){alert("\u6708\u4EFD\u8F93\u5165\u53C2\u6570\u4E0D\u662F\u6570\u5B57\uFF01");return;}
  document.all.tmpSelectMonth.focus();
}

function closeLayer()               //\u8FD9\u4E2A\u5C42\u7684\u5173\u95ED
  {
    document.all.bltDateLayer.style.display="none";
  }

function document.onkeydown()
  {
    if (window.event.keyCode==27)document.all.bltDateLayer.style.display="none";
  }

function IsPinYear(year)            //\u5224\u65AD\u662F\u5426\u95F0\u5E73\u5E74
  {
    if (0==year%4&&((year%100!=0)||(year%400==0))) return true;else return false;
  }

function GetMonthCount(year,month)  //\u95F0\u5E74\u4E8C\u6708\u4E3A29\u5929
  {
    var c=MonHead[month-1];if((month==2)&&IsPinYear(year)) c++;return c;
  }

function GetDOW(day,month,year)     //\u6C42\u67D0\u5929\u7684\u661F\u671F\u51E0
  {
    var dt=new Date(year,month-1,day).getDay()/7; return dt;
  }

function bltPrevY()  //\u5F80\u524D\u7FFB Year
  {
    if(bltTheYear > 999 && bltTheYear <10000){bltTheYear--;}
    else{alert("\u5E74\u4EFD\u8D85\u51FA\u8303\u56F4\uFF081000-9999\uFF09\uFF01");}
    bltSetDay(bltTheYear,bltTheMonth);
  }
function bltNextY()  //\u5F80\u540E\u7FFB Year
  {
    if(bltTheYear > 999 && bltTheYear <10000){bltTheYear++;}
    else{alert("\u5E74\u4EFD\u8D85\u51FA\u8303\u56F4\uFF081000-9999\uFF09\uFF01");}
    bltSetDay(bltTheYear,bltTheMonth);
  }
function bltToday()  //Today Button
  {
    bltTheYear = new Date().getFullYear();
    bltTheMonth = new Date().getMonth()+1;
    bltSetDay(bltTheYear,bltTheMonth);
  }
function bltPrevM()  //\u5F80\u524D\u7FFB\u6708\u4EFD
  {
    if(bltTheMonth>1){bltTheMonth--}else{bltTheYear--;bltTheMonth=12;}
    bltSetDay(bltTheYear,bltTheMonth);
  }
function bltNextM()  //\u5F80\u540E\u7FFB\u6708\u4EFD
  {
    if(bltTheMonth==12){bltTheYear++;bltTheMonth=1}else{bltTheMonth++}
    bltSetDay(bltTheYear,bltTheMonth);
  }

function bltSetDay(yy,mm)   //\u4E3B\u8981\u7684\u5199\u7A0B\u5E8F**********
{
  bltWriteHead(yy,mm);
  for (var i = 0; i < 37; i++)
  {
  	bltWDay[i]=""
  };  //\u5C06\u663E\u793A\u6846\u7684\u5185\u5BB9\u5168\u90E8\u6E05\u7A7A
  var day1 = 1,firstday = new Date(yy,mm-1,1).getDay();  //\u67D0\u6708\u7B2C\u4E00\u5929\u7684\u661F\u671F\u51E0
  for( var i = firstday; day1 < GetMonthCount(yy,mm)+1; i++)
  {
  	bltWDay[i]=day1;
  	day1++;
  }
  for( var i = 0; i < 37; i++)
  { var da = eval("document.all.bltDay"+i)     //\u4E66\u5199\u65B0\u7684\u4E00\u4E2A\u6708\u7684\u65E5\u671F\u661F\u671F\u6392\u5217
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
	//\u6CE8\uFF1A\u5728\u8FD9\u91CC\u4F60\u53EF\u4EE5\u8F93\u51FA\u6539\u6210\u4F60\u60F3\u8981\u7684\u683C\u5F0F
	if( m_yy != null && m_mm != null && m_dd != null )
	{

		if( f.m_hour.value == '-' || f.m_min.value == '-' )	//\u5982\u679C\u6CA1\u9009\u65F6\u95F4
			outObject.value= m_yy + "-" + m_mm + "-" + m_dd;
		else
  		outObject.value= m_yy + "-" + m_mm + "-" + m_dd + " " + f.m_hour.value + ":" + f.m_min.value + ":00";
  }
  closeLayer();
}
function bltDayClick( n, obj )  //\u70B9\u51FB\u663E\u793A\u6846\u9009\u53D6\u65E5\u671F\uFF0C\u4E3B\u8F93\u5165\u51FD\u6570*************
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
  	alert("\u8F93\u51FA\u6709\u8BEF\uFF01");
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
document.writeln('        onclick="bltPrevM()" title="ååç¿»ä¸æ" Author=blt><b Author=blt>&lt;&lt;</b>');
document.writeln('        </td><td width=100 align=center style="font-size:12px;cursor:default" Author=blt>');
document.writeln('        <span Author=blt id=bltYearHead onclick="tmpSelectYearInnerHTML(this.innerText)"></span>&nbsp;å¹´&nbsp;<span');
document.writeln('         id=bltMonthHead Author=blt onclick="tmpSelectMonthInnerHTML(this.innerText)"></span>&nbsp;æ</td>');
document.writeln('        <td width=20 bgcolor=#808080 align=center style="font-size:12px;cursor: hand;color: #FFD700" ');
document.writeln('         onclick="bltNextM()" title="å¾åç¿»ä¸æ" Author=blt><b Author=blt>&gt;&gt;</b></td></tr>');
document.writeln('    </table></td></tr>');
document.writeln('  <tr><td width=142 height=18 bgcolor=#808080>');
document.writeln('<table border=0 cellspacing=0 cellpadding=0 width=140 height=1 style="cursor:default">');
document.writeln('<tr align=center><td style="font-size:12px;color:#FFFFFF" Author=blt>æ¥</td>');
document.writeln('<td style="font-size:12px;color:#FFFFFF" Author=blt>ä¸</td><td style="font-size:12px;color:#FFFFFF" Author=blt>äº</td>');
document.writeln('<td style="font-size:12px;color:#FFFFFF" Author=blt>ä¸</td><td style="font-size:12px;color:#FFFFFF" Author=blt>å</td>');
document.writeln('<td style="font-size:12px;color:#FFFFFF" Author=blt>äº</td><td style="font-size:12px;color:#FFFFFF" Author=blt>å­</td></tr>');
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
document.writeln('         </select>æ¶</td>');
document.writeln('        <td align=center colspan=4 Author=blt>');
document.writeln('         <select name="m_min" Author=blt>');
for( j = 0; j < 60; j++ )
{
	if( j < 10 )
		document.writeln('         <option value="0' + j + '">' + j + '</option>');
	else
		document.writeln('         <option value="' + j + '">' + j + '</option>');
}
document.writeln('         </select>å');
document.writeln('         </td></tr>');
document.writeln('         <tr>');
document.writeln('        <td colspan=4 align=center Author=blt>');
document.writeln('         <Label onclick=setDayTime()>ç¡® å®</Label>');
document.writeln('        </td>');

document.writeln('        <td colspan=4 align=center Author=blt>');
document.writeln('         <Label onclick=closeLayer()>å æ¶</Label>');
document.writeln('         &nbsp;</td></tr>');
document.writeln('    </table></td></tr><tr><td>');
document.writeln('        <table border=0 cellspacing=1 cellpadding=0 width=100% bgcolor=#FFFFFF>');
document.writeln('          <tr><td Author=blt align=left><Label Author=blt title="ååç¿»ä¸å¹´" onclick="bltPrevY()" ');
document.writeln('             onfocus="this.blur()">< </Label> <Label Author=blt title="ååç¿»ä¸æ" ');
document.writeln('             onclick="bltPrevM()" onfocus="this.blur()"><<</Label></td><td ');
document.writeln('             Author=blt align=center><Label Author=blt onclick="bltToday()" ');
document.writeln('             onfocus="this.blur()" title="ç°å¨çå¹´æ">ä» å¤©</Label></td><td ');
document.writeln('             Author=blt align=right><Label Author=blt onclick="bltNextM()" ');
document.writeln('             onfocus="this.blur()" title="å¾åç¿»ä¸æ">>></Label> <Label ');
document.writeln('             Author=blt title="å¾åç¿»ä¸å¹´" onclick="bltNextY()"');
document.writeln('             onfocus="this.blur()"> ></Label></td>');
document.writeln('</tr></table></td></tr></table></div>');
bltSetDay(bltTheYear,bltTheMonth);



</SCRIPT>





	<SCRIPT language=javascript>
var outObject;
var old_dd = null;
function setday(tt,obj) //\u4E3B\u8C03\u51FD\u6570
{
  if (arguments.length >  2){alert("\u5BF9\u4E0D\u8D77\uFF01\u4F20\u5165\u672C\u63A7\u4EF6\u7684\u53C2\u6570\u592A\u591A\uFF01");return;}
  if (arguments.length == 0){alert("\u5BF9\u4E0D\u8D77\uFF01\u60A8\u6CA1\u6709\u4F20\u56DE\u672C\u63A7\u4EF6\u4EFB\u4F55\u53C2\u6570\uFF01");return;}
  var dads  = document.all.bltDateLayer.style;var th = tt;
  var ttop  = tt.offsetTop;     //TT\u63A7\u4EF6\u7684\u5B9A\u4F4D\u70B9\u9AD8
  var thei  = tt.clientHeight;  //TT\u63A7\u4EF6\u672C\u8EAB\u7684\u9AD8
  var tleft = tt.offsetLeft;    //TT\u63A7\u4EF6\u7684\u5B9A\u4F4D\u70B9\u5BBD
  var ttyp  = tt.type;          //TT\u63A7\u4EF6\u7684\u7C7B\u578B
  while (tt = tt.offsetParent){ttop+=tt.offsetTop; tleft+=tt.offsetLeft;}
  dads.top  = (ttyp=="image")? ttop+thei : ttop+thei+6;
  dads.left = tleft;
  outObject = (arguments.length == 1) ? th : obj;
  dads.display = '';
  event.returnValue=false;
}

var MonHead = new Array(12);    		   //\u5B9A\u4E49\u9633\u5386\u4E2D\u6BCF\u4E2A\u6708\u7684\u6700\u5927\u5929\u6570
    MonHead[0] = 31; MonHead[1] = 28; MonHead[2] = 31; MonHead[3] = 30; MonHead[4]  = 31; MonHead[5]  = 30;
    MonHead[6] = 31; MonHead[7] = 31; MonHead[8] = 30; MonHead[9] = 31; MonHead[10] = 30; MonHead[11] = 31;

var bltTheYear=new Date().getFullYear(); //\u5B9A\u4E49\u5E74\u7684\u53D8\u91CF\u7684\u521D\u59CB\u503C
var bltTheMonth=new Date().getMonth()+1; //\u5B9A\u4E49\u6708\u7684\u53D8\u91CF\u7684\u521D\u59CB\u503C
var bltWDay=new Array(37);               //\u5B9A\u4E49\u5199\u65E5\u671F\u7684\u6570\u7EC4
var m_yy, m_mm, m_dd;
function document.onclick() //\u4EFB\u610F\u70B9\u51FB\u65F6\u5173\u95ED\u8BE5\u63A7\u4EF6
{
return;
  with(window.event.srcElement)
  { if (tagName != "INPUT" && getAttribute("Author")==null)
    document.all.bltDateLayer.style.display="none";
  }
}

function bltWriteHead(yy,mm)  //\u5F80 head \u4E2D\u5199\u5165\u5F53\u524D\u7684\u5E74\u4E0E\u6708
  { document.all.bltYearHead.innerText  = yy;
    document.all.bltMonthHead.innerText = mm;
  }

function tmpSelectYearInnerHTML(strYear) //\u5E74\u4EFD\u7684\u4E0B\u62C9\u6846
{
  if (strYear.match(/\D/)!=null){alert("\u5E74\u4EFD\u8F93\u5165\u53C2\u6570\u4E0D\u662F\u6570\u5B57\uFF01");return;}
  var m = (strYear) ? strYear : new Date().getFullYear();
  if (m < 1000 || m > 9999) {alert("\u5E74\u4EFD\u503C\u4E0D\u5728 1000 \u5230 9999 \u4E4B\u95F4\uFF01");return;}
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
       {selectInnerHTML += "<option value='" + i + "' selected>" + i + "\u5E74" + "</option>\r\n";}
    else {selectInnerHTML += "<option value='" + i + "'>" + i + "\u5E74" + "</option>\r\n";}
  }
  selectInnerHTML += "</select>";
  document.all.tmpSelectYearLayer.style.display="";
  document.all.tmpSelectYearLayer.innerHTML = selectInnerHTML;
  document.all.tmpSelectYear.focus();
}

function tmpSelectMonthInnerHTML(strMonth) //\u6708\u4EFD\u7684\u4E0B\u62C9\u6846
{
  if (strMonth.match(/\D/)!=null){alert("\u6708\u4EFD\u8F93\u5165\u53C2\u6570\u4E0D\u662F\u6570\u5B57\uFF01");return;}
  var m = (strMonth) ? strMonth : new Date().getMonth() + 1;
  var s = "<select Author=blt name=tmpSelectMonth style='font-size: 12px' "
     s += "onblur='document.all.tmpSelectMonthLayer.style.display=\"none\"' "
     s += "onchange='document.all.tmpSelectMonthLayer.style.display=\"none\";"
     s += "bltTheMonth = this.value; bltSetDay(bltTheYear,bltTheMonth)'>\r\n";
  var selectInnerHTML = s;
  for (var i = 1; i < 13; i++)
  {
    if (i == m)
       {selectInnerHTML += "<option value='"+i+"' selected>"+i+"\u6708"+"</option>\r\n";}
    else {selectInnerHTML += "<option value='"+i+"'>"+i+"\u6708"+"</option>\r\n";}
  }
  selectInnerHTML += "</select>";
  document.all.tmpSelectMonthLayer.style.display="";
  document.all.tmpSelectMonthLayer.innerHTML = selectInnerHTML;
  document.all.tmpSelectMonth.focus();
}

function closeLayer()               //\u8FD9\u4E2A\u5C42\u7684\u5173\u95ED
  {
    document.all.bltDateLayer.style.display="none";
  }

function document.onkeydown()
  {
    if (window.event.keyCode==27)document.all.bltDateLayer.style.display="none";
  }

function IsPinYear(year)            //\u5224\u65AD\u662F\u5426\u95F0\u5E73\u5E74
  {
    if (0==year%4&&((year%100!=0)||(year%400==0))) return true;else return false;
  }

function GetMonthCount(year,month)  //\u95F0\u5E74\u4E8C\u6708\u4E3A29\u5929
  {
    var c=MonHead[month-1];if((month==2)&&IsPinYear(year)) c++;return c;
  }

function GetDOW(day,month,year)     //\u6C42\u67D0\u5929\u7684\u661F\u671F\u51E0
  {
    var dt=new Date(year,month-1,day).getDay()/7; return dt;
  }

function bltPrevY()  //\u5F80\u524D\u7FFB Year
  {
    if(bltTheYear > 999 && bltTheYear <10000){bltTheYear--;}
    else{alert("\u5E74\u4EFD\u8D85\u51FA\u8303\u56F4\uFF081000-9999\uFF09\uFF01");}
    bltSetDay(bltTheYear,bltTheMonth);
  }
function bltNextY()  //\u5F80\u540E\u7FFB Year
  {
    if(bltTheYear > 999 && bltTheYear <10000){bltTheYear++;}
    else{alert("\u5E74\u4EFD\u8D85\u51FA\u8303\u56F4\uFF081000-9999\uFF09\uFF01");}
    bltSetDay(bltTheYear,bltTheMonth);
  }
function bltToday()  //Today Button
  {
    bltTheYear = new Date().getFullYear();
    bltTheMonth = new Date().getMonth()+1;
    bltSetDay(bltTheYear,bltTheMonth);
  }
function bltPrevM()  //\u5F80\u524D\u7FFB\u6708\u4EFD
  {
    if(bltTheMonth>1){bltTheMonth--}else{bltTheYear--;bltTheMonth=12;}
    bltSetDay(bltTheYear,bltTheMonth);
  }
function bltNextM()  //\u5F80\u540E\u7FFB\u6708\u4EFD
  {
    if(bltTheMonth==12){bltTheYear++;bltTheMonth=1}else{bltTheMonth++}
    bltSetDay(bltTheYear,bltTheMonth);
  }

function bltSetDay(yy,mm)   //\u4E3B\u8981\u7684\u5199\u7A0B\u5E8F**********
{
  bltWriteHead(yy,mm);
  for (var i = 0; i < 37; i++)
  {
  	bltWDay[i]=""
  };  //\u5C06\u663E\u793A\u6846\u7684\u5185\u5BB9\u5168\u90E8\u6E05\u7A7A
  var day1 = 1,firstday = new Date(yy,mm-1,1).getDay();  //\u67D0\u6708\u7B2C\u4E00\u5929\u7684\u661F\u671F\u51E0
  for( var i = firstday; day1 < GetMonthCount(yy,mm)+1; i++)
  {
  	bltWDay[i]=day1;
  	day1++;
  }
  for( var i = 0; i < 37; i++)
  { var da = eval("document.all.bltDay"+i)     //\u4E66\u5199\u65B0\u7684\u4E00\u4E2A\u6708\u7684\u65E5\u671F\u661F\u671F\u6392\u5217
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
	//\u6CE8\uFF1A\u5728\u8FD9\u91CC\u4F60\u53EF\u4EE5\u8F93\u51FA\u6539\u6210\u4F60\u60F3\u8981\u7684\u683C\u5F0F
	if( m_yy != null && m_mm != null && m_dd != null )
	{

		if( f.m_hour.value == '-' || f.m_min.value == '-' )	//\u5982\u679C\u6CA1\u9009\u65F6\u95F4
			outObject.value= m_yy + "-" + m_mm + "-" + m_dd;
		else
  		outObject.value= m_yy + "-" + m_mm + "-" + m_dd + " " + f.m_hour.value + ":" + f.m_min.value + ":00";
  }
  closeLayer();
}
function bltDayClick( n, obj )  //\u70B9\u51FB\u663E\u793A\u6846\u9009\u53D6\u65E5\u671F\uFF0C\u4E3B\u8F93\u5165\u51FD\u6570*************
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
  	alert("\u8F93\u51FA\u6709\u8BEF\uFF01");
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
	<script language="JavaScript">
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
	<center>
		<table cellSpacing="0" cellPadding="0" width="85%" border="0">
			<html:hidden property="strutsAction" />
			<html:hidden property="dealer" />
			<html:hidden property="cardid" />
			<html:hidden property="oldid" />
			<html:hidden property="id" />
			<tr>
				<td align="right" width="100%">
					<div align="center">
						<table cellSpacing="0" cellPadding="2" width="100%"
							borderColorLight="#808080" border="1">
							<tr>
								<td colspan="4" valign="middle" class="table_title" align="center">
									<b> ${eoms:a2u("æµ è¯ å¡ å ç¨ è®° å½ ä¿® æ¹")}&nbsp;&nbsp; </b>
								</td>
							</tr>
							<tr>
								<td noWrap width="100" class="clsfth">
									å¡å·
								</td>
								<td width="380">
									<bean:write name="tawTestcardManagerForm" property="cardid" />
									&nbsp
								</td>
								<td noWrap width="100" class="clsfth">
									æ§ç³»ç»ç¼å·
								</td>
								<td width="380">
									<bean:write name="tawTestcardManagerForm" property="oldid" />
									&nbsp
								</td>

							</tr>
							<tr>
								<td noWrap width="100" class="clsfth">
									ç»æäºº
								</td>
								<td width="380">
									<bean:write name="tawTestcardManagerForm" property="dealer" />
									&nbsp
								</td>
								<td noWrap width="100" class="clsfth">
									åç¨é¨é¨
								</td>
								<td width="380">
									<html:text styleClass="clstext" property="lenddept" size="20" />
								</td>

							</tr>
							<tr>
								<td noWrap width="100" class="clsfth">
									åç¨äºº
								</td>
								<td width="380">
									<html:text styleClass="clstext" property="lender" size="20" />
								</td>
								<td noWrap width="100" class="clsfth">
									èç³»æ¹å¼
								</td>
								<td colspan=3 width="380">
									<html:text styleClass="clstext" property="contect" size="20" />
								</td>
							</tr>
							<tr>
								<td noWrap width="100" class="clsfth">
									åç¨æ¶é´
								</td>
								<td width="380">
									<html:text property="leantime" styleClass="clstext"
										onfocus="setday(this)" readonly="true" />
								</td>
								<td noWrap width="100" class="clsfth">
									åºå½è¿æ¶é´
								</td>
								<td width="380">
									<html:text property="belongtime" styleClass="clstext"
										onfocus="setday(this)" readonly="true" />
								</td>
							</tr>
							<tr>
								<td noWrap width="100" class="clsfth">
									å½è¿æ¶é´
								</td>
								<td width="380">
									<html:text property="returntime" styleClass="clstext"
										onfocus="setday(this)" readonly="true" />
								</td>
								<td noWrap width="100" class="clsfth">
									ç»­åæé
								</td>
								<td width="380">
									<html:text property="renewlimit" styleClass="clstext" size="20" />
								</td>
							</tr>
							<tr>
								<td noWrap width="100" class="clsfth">
									ç¨é
								</td>
								<td width="380" colspan="3">
									<html:textarea property="reason" rows="4" cols="65" />
								</td>
							</tr>
							<tr>
							<td colspan=4>
								<table bgcolor="#f2f2f2" height="30" cellpadding="0"
									cellspacing="0" border="0" width="100%">
									<tr align="right" valign="middle">
										<td>


											<html:submit styleClass="clsbtn2">
												<bean:message key="label.save" />
											</html:submit>
											<html:reset styleClass="clsbtn2">
												<bean:message key="label.reset" />
											</html:reset>
											&nbsp;&nbsp;
										</td>
									</tr>


								</table>
							</td>
							</tr>
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

						</html:html>