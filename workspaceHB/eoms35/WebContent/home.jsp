<%@ page language="java" pageEncoding="UTF-8" %>
<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title><fmt:message key="webapp.name" /></title>
<%@ include file="/common/meta.jsp"%> 
<script type="text/javascript" src="${app}/scripts/local/zh_CN.js"></script>
<script type="text/javascript" src="${app}/scripts/base/eoms.js"></script>
<script type="text/javascript">eoms.appPath = "${app}";</script>
<link rel="stylesheet" type="text/css" href="${app}/styles/${theme}/theme.css" />
<style type="text/css">
td.left {width: 207px;background:transparent url(${app}/images/home-shadow.gif) repeat-y scroll 100% 0;}
td.middle {padding-right:7px;background:transparent url(${app}/images/home-shadow.gif) repeat-y scroll 100% 0;}
div.footer {position:absolute;width:100%;padding:0;font-size:12px;height:40px;clear: both;background:transparent url(${app}/images/home-footbg.gif) repeat-x;border-top:1px solid #d0d0d0;}
div.block {padding:25px 0 0;}
div.tools{margin-top:10px;background:url(${app}/images/portal-tools.gif) no-repeat;}
div.forum{background:url(${app}/images/home-forum.gif) no-repeat;}
div.links{background:url(${app}/images/home-links.gif) no-repeat;}
div.myfocus{margin-top:10px;background:url(${app}/images/home-myfocus.gif) no-repeat;}
div.msg{background:url(${app}/images/home-msg.gif) no-repeat;}
div.msg form {margin:0;padding:5px;}
div.msg textarea{width:175px;}
div.line{height:5px;background:url(${app}/images/home-line.gif) repeat-x;}
div.header{padding-top:20px;}
ul.list{}
ul.list li{margin:5px;list-style-type:square;color:#CC9999;}
tr.home-even{background-color:#fcf9e1}
td.middle table{width:auto;}
td.middle td{padding:5px;}
td.middle td.dot{width:20px;background:url(${app}/images/home-dot.gif) no-repeat;}
</style>
</head>
<body>
<table width="100%" height="90%" cellpadding="0" cellspacing="0">
<tr>
  <td valign="top" class="left">
	<div class="block tools">
	  <ul class="list">
	    <li><a href="#">日常安排 »</a></li>
	    <li><a href="#">信息中心 »</a></li>
	    <li><a href="#">在线值班 »</a></li>
	    <li><a href="#">作业计划 »</a></li>
	    <li><a href="#">发送便笺 »</a></li>
	    <li><a href="#">个人信息修改 »</a></li>
	  </ul>	          
	</div>
	<div class="block forum">
	  <ul class="list">
	    <li><a href="#">欢迎使用电子运维系统 »</a></li>
	    <li><a href="#">欢迎使用电子运维系统 »</a></li>
	    <li><a href="#">欢迎使用电子运维系统 »</a></li>
	  </ul>	          
	</div>
	<div class="block links">
	  <ul class="list">
	    <li><a href="#">分组网系统 »</a></li>
	    <li><a href="#">GSM报表管理系统 »</a></li>
	    <li><a href="#">CDMA报表管理系统 »</a></li>
	  </ul>	          
	</div>
  </td>

  <td valign="top" class="middle">
  <div class="header">
  <img src="${app}/images/home-works.gif"/>
  </div>
  <div class="line"></div>
  <div>
  <iframe width="100%" src="test/test.jsp"></iframe>
  </div>
  <div class="header">
  <img src="${app}/images/home-board.gif"/>
  </div>
  <div class="line"></div>
  <table width="100%">
    <tr>
      <td class="dot"></td>
      <td><a href="#">128-090106-000-00001</a></td>
      <td>关于话务调整的通知和方案</td>
      <td>2009-01-06 08:19:40</td>
      <td>待审阅</td>
    </tr>
    <tr class="home-even">
      <td class="dot"></td>
      <td><a href="#">128-090106-000-00001</a></td>
      <td>关于话务调整的通知和方案</td>
      <td>2009-01-06 08:19:40</td>
      <td>待审阅</td>
    </tr>
    <tr>
      <td class="dot"></td>
      <td><a href="#">128-090106-000-00001</a></td>
      <td>关于话务调整的通知和方案</td>
      <td>2009-01-06 08:19:40</td>
      <td>待审阅</td>
    </tr>
    <tr class="home-even">
      <td class="dot"></td>
      <td><a href="#">128-090106-000-00001</a></td>
      <td>关于话务调整的通知和方案</td>
      <td>2009-01-06 08:19:40</td>
      <td>待审阅</td>
    </tr>
    <tr>
      <td class="dot"></td>
      <td><a href="#">128-090106-000-00001</a></td>
      <td>关于话务调整的通知和方案</td>
      <td>2009-01-06 08:19:40</td>
      <td>待审阅</td>
    </tr>
    <tr class="home-even">
      <td class="dot"></td>
      <td><a href="#">128-090106-000-00001</a></td>
      <td>关于话务调整的通知和方案</td>
      <td>2009-01-06 08:19:40</td>
      <td>待审阅</td>
    </tr>
  </table>
  </td>
  
  <td width="180" valign="top">
  
	<div class="block myfocus">
	  <ul class="list">
	    <li><a href="#">关于网管竞赛办法疑问的回复 »</a></li>
	    <li><a href="#">关于配置数据更新申请的上报问题 »</a></li>
	  </ul>	          
	</div>
	<div class="block msg">
	  <form>
	    <input id="msg-code" type="text" class="text" size="11" style="width:175px"/> 
	    <textarea id="msg-content" rows="8" class="textarea"></textarea>
	    <input type="button" value="发送短信" class="button">	    
	  </form>          
	</div>
  </td>
</tr>
</table>

<div class="footer"><img src="${app}/images/home-footlogo.gif" valign="absmiddle"/></div>
<script type="text/javascript">
function initField(id,text){
	var el = $(id);
	el.value = text;
	el.on('focus', function(){if(el.value==text)el.value="";});
	el.on('blur', function(){if(el.value=="")el.value=text;});	
}
initField("msg-code","输入手机号");
initField("msg-content","输入短信内容");
</script>
</body>
</html>
