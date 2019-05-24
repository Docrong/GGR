<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<%@ include file="../../../duty/taglibs.jsp"%>
<%@ include file="../../../common/header_eoms_form.jsp"%>
<%@ page import="java.util.*"%>  
<HTML>
<HEAD>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 
<!--
body {
	background-image: url(bg2.gif);
}
-->
<script language="JavaScript">

function checkForm() {
	if (document.chatForm.usermessage.value == "") {
		alert("发言不能为空!");
		document.chatForm.usermessage.focus();
		return false;
	}
	else {
		document.chatForm.usermessage.focus();
		return true;
	}
}
function fun_refresh()
{
	var _sHeight = 200;
	var _sWidth = 320;
	var sTop=(window.screen.availHeight-_sHeight)/2;
	var sLeft=(window.screen.availWidth-_sWidth)/2;
	var sFeatures="dialogHeight: "+_sHeight+"px; dialogWidth: "+_sWidth+"px; dialogTop: "+sTop+"; dialogLeft: "+sLeft+"; center: Yes; scroll:Yes;help: No; resizable: No; status: No;";
	window.showModalDialog('submit.jsp',window,sFeatures);
 
}

function userLogout() {
	top.window.location="logout.jsp?logout=yes";
}

function openWindow(url) {
	var newWin = window.open(url,"","toolbar=no,status=no,scrollbars=yes,menubar=no,width=450,height=320");
	return false;
}
</script>

</HEAD>
<BODY  topmargin="10" onload="document.chatForm.usermessage.focus();">
<FORM name="chatForm"  method="post" >
<table border="0" width="100%" align="center" cellspacing="0" cellpadding="0" class="formTable">
<tr valign="top"><td>
<%
 
String first = (String)request.getParameter("first");
String talkMessage=(String)request.getParameter("usermessage");
String action = (String)request.getParameter("action");
String fontcolor = (String) request.getParameter("fontcolor");
String talkwith =(String)request.getParameter("talkwith");
 
String systemSpeak=(String) request.getParameter("systemSpeak");
GregorianCalendar calendar =new GregorianCalendar();
String time=calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE);


String Name=(String)session.getAttribute("UserName");
out.println("【<font color='#0000ff'>"+Name+"</font>】"+"对");


if (first == null) {
	talkMessage="系统公告：<font color = blue>"+Name+"</font>进入了聊天室，大家欢迎！";
	action = "no";
	fontcolor = "#000000";
	systemSpeak = "yes";
}

if(talkwith==null) {
	 talkwith = "所有人";
}
%>

        <input type="text" name="talkwith" value= <%= talkwith %> size="8" style="font-size:9pt">
        </td>
        <td>
        动作
        <select name="action" size="1" style="font-size:9pt">
          <option value="no" checked>说话</option>
          <option value="友好地拉着B的手说[<%=time%>]：">问好</option>
          <option value="向B会意地点点头,说[<%=time%>]：">点头</option>
          <option value="对B妩媚地甜甜一笑，说[<%=time%>]：">妩媚</option>
          <option value="万事不萦于心地呵呵笑着对B说[<%=time%>]：">开朗</option>
          <option value="一脸的坏笑，不怀好意地打量着B，说[<%=time%>]：">坏笑</option>
          <option value="忍住泪水，强挤出一丝笑容对B说[<%=time%>]：">苦笑</option>
          <option value="白了B一眼，十分不屑地说[<%=time%>]：">白眼</option>
          <option value="轻轻地在B的额头上吻了一下，温柔地说[<%=time%>]：">轻吻</option>
          <option value="热烈地拥抱着B，几乎透不过气来，说[<%=time%>]：">拥抱</option>
          <option value="含情脉脉地凝视着B说[<%=time%>]：">深情</option>
          <option value="泪珠儿在眼眶里打转，无限伤心地对B说[<%=time%>]：">伤心</option>
          <option value="羞红了脸，拧过身子背对着B说[<%=time%>]：">害羞</option>
          <option value="气得全身发抖，两眼喷火瞪着B，说[<%=time%>]：">愤怒</option>
          <option value="向B招招手，中间夹了个飞吻，说[<%=time%>]：">招手</option>
          <option value="嘟着嘴哼哼哧哧地对B说[<%=time%>]：">不满</option>
          <option value="兴奋得不得了，对着B翩翩起舞，说[<%=time%>]：">兴奋</option>
          <option value="情不自禁地搂着B热吻起来，还叽哩咕噜地说[<%=time%>]：">狂吻</option>
          <option value="泪光闪闪看着B，脸上写满了委屈的说[<%=time%>]：">委屈</option>
          <option value="急得直跺脚，气急败坏地对B说[<%=time%>]：">着急</option>
          <option value="泪如泉涌，对着B哗啦哗啦哭了起来，说[<%=time%>]：">嚎啕</option>
          <option value="皱起眉头别转脸，却又偷看B一眼，故作生气说[<%=time%>]：">撒娇</option>
          <option value="眯起斗鸡眼看着B一阵奸笑说[<%=time%>]：">奸笑</option>
          <option value="突然跃起飞脚猛踢B屁股，说[<%=time%>]：">飞脚</option>
          <option value="左右开弓抽得B满地找牙，说[<%=time%>]：">抽掴</option>
          <option value="冲B皮笑肉不笑地说[<%=time%>]：">假笑</option>
        </select> 
        
        <input type="hidden" name="first" value="first">
        <input type="hidden" name="systemSpeak" value="no">
        </td>
        <td>
<%
String aloneTalk=request.getParameter("alonetalk");
if(aloneTalk==null)
aloneTalk="no";
if(aloneTalk.equals("no"))
out.print("<input type=\"checkbox\" name=\"alonetalk\" value=\"yes\" > 悄悄话 ");
else
out.print("<input type=\"checkbox\" name=\"alonetalk\" value=\"yes\" checked > 悄悄话");
%>
 <select name="fontcolor" size="1" style="font-size:9pt">
          <option style="COLOR: #000000" value="#000000" <% if(fontcolor.equals("#000000")) out.print("selected"); %>>黑色
          <option style="COLOR: #7ec0ee" value="#7ec0ee" <% if(fontcolor.equals("#7ec0ee")) out.print("selected"); %>>淡蓝
          <option style="COLOR: #0088ff" value="#0088ff" <% if(fontcolor.equals("#0088ff")) out.print("selected"); %>>海蓝
          <option style="COLOR: #0000ff" value="#0000ff" <% if(fontcolor.equals("#0000ff")) out.print("selected"); %>>草蓝
          <option style="COLOR: #000088" value="#000088" <% if(fontcolor.equals("#000088")) out.print("selected"); %>>深蓝
          <option style="COLOR: #8800ff" value="#8800ff" <% if(fontcolor.equals("#8800ff")) out.print("selected"); %>>蓝紫
          <option style="COLOR: #ab82ff" value="#AB82FF" <% if(fontcolor.equals("#AB82FF")) out.print("selected"); %>>紫色
          <option style="COLOR: #ff88ff" value="#ff88ff" <% if(fontcolor.equals("#ff88ff")) out.print("selected"); %>>紫金
          <option style="COLOR: #ff00ff" value="#ff00ff" <% if(fontcolor.equals("#ff00ff")) out.print("selected"); %>>红紫
          <option style="COLOR: #ff0088" value="#ff0088" <% if(fontcolor.equals("#ff0088")) out.print("selected"); %>>玫红
          <option style="COLOR: #ff0000" value="#ff0000" <% if(fontcolor.equals("#ff0000")) out.print("selected"); %>>大红
          <option style="COLOR: #f4a460" value="#f4a460" <% if(fontcolor.equals("#f4a460")) out.print("selected"); %>>棕色
          <option style="COLOR: #888800" value="#888800" <% if(fontcolor.equals("#888800")) out.print("selected"); %>>卡其
          <option style="COLOR: #888888" value="#888888" <% if(fontcolor.equals("#888888")) out.print("selected"); %>>铁灰
          <option style="COLOR: #90e090" value="#90E090" <% if(fontcolor.equals("#90E090")) out.print("selected"); %>>绿色
          <option style="COLOR: #008800" value="#008800" <% if(fontcolor.equals("#008800")) out.print("selected"); %>>橄榄
          <option style="COLOR: #008888" value="#008888" <% if(fontcolor.equals("#008888")) out.print("selected"); %>>灰蓝
        </select>
        <input type="text"   name="usermessage" size="40"  >
        <input type="submit" name="Submit"  class="button" value="发送" style="" onclick="return checkForm();">
        <input type="button" name="logout" class="button" value="离开" style="" onclick="return userLogout();">
  </tr>
  </table>
<%
String action2 = "";
int i = action.indexOf("B");
if (i != -1) {
	action = action.substring(0,i) + "<font color='blue'>" + talkwith + "</font>" + action.substring(i+1);
}

if(!action.equals("no")) {
	talkMessage = action + "<font color=" + fontcolor + ">" + talkMessage + "</font>";
	action2 = "yes";
}

else if (first != null) {
	talkMessage = "<font color = " + fontcolor + ">" + talkMessage + "</font>";
	action2 = "no";
}
String online = (String) session.getAttribute("online");
if (online == null) {
	online = "";
}
if (online.equals("no")) {
	out.print("<script>alert(\"您已经在线了！不能重覆登陆！\");</script>");
	return;
}

if (Name == null) {
	return;
}

Vector  Message = null;
synchronized (application) {
	Message= (Vector)application.getAttribute("Message");

	if(Message==null) {
		Message= new Vector(30,10);
	}
	if(Message.size()>200) {
		Message.removeAllElements();
	}

	Message.addElement(aloneTalk);
	Message.addElement(Name);
	Message.addElement(talkwith);
	Message.addElement(talkMessage);
	Message.addElement(systemSpeak);
	Message.addElement(action2);
	application.setAttribute("Message", Message);
}
%>
</FORM>
</BODY>
</HTML>