<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<link rel="stylesheet" type="text/css" href="${app}/styles/default/lunar-calendar.css"></link>
<link rel="stylesheet" type="text/css" href="test-workday-setter.css"></link>

<script type="text/javascript" src="../../util/Lunar.js"></script>
<script type="text/javascript" src="../LunarCalendar.js"></script>
<script type="text/javascript" src="../WorkDaySetter.js"></script>

<div class="app-border">
<div class="wds-header"><h1>${eoms:a2u("工作日设置工具")}</h1></div>

<div id="wds-tpl">
  <form method="post" name="theForm">
    <input type="hidden" value="" id="data" name="data" />
	${eoms:a2u("模板选择:") }
	<select name="template" id="template">
	  <option value="d" selected>24 hour</option>
	  <option value="a">asd0610</option>
	  <option value="b">night shift</option>
    </select>
  </form>
</div>

<div>
  <div id="wds-sample">
    <div>${eoms:a2u("工作日:")}</div>
    <div class="wds-box"> </div>
    
    <div>${eoms:a2u("非工作日:")}</div>
    <div class="wds-box" id="snwd"> </div>
    
    <div>${eoms:a2u("有变化的日期:")}</div>
    <div class="wds-box" id="scd"><em>31</em></div>
    
    <div>${eoms:a2u("今天:")}</div>
    <div class="wds-box" id="std"><em>31</em></div>
  </div>

  <div id="dateSetter" style="float:left;width:370px"></div>
  
  <div id="wds-config">

<table border="0" cellspacing="0" cellpadding="5">
	<tr><td colspan="2">${eoms:a2u("将所选日期设置为:")}</td></tr>
	<tr>
		<td colspan="2"><input class="radio" type="radio" name="dateConfig" value="0"
			onchange="wds.onEditConfig(this.value)" />${eoms:a2u("使用默认设置")}</td>
		<td></td>
	</tr>
	<tr>
		<td colspan="2"><input class="radio" type="radio" name="dateConfig" value="1"
			onchange="wds.onEditConfig(this.value)" />${eoms:a2u("非工作日")}</td>
	</tr>
	<tr>
		<td colspan="2"><input class="radio" type="radio" name="dateConfig" value="2"
			onchange="wds.onEditConfig(this.value)" />${eoms:a2u("非默认工作时间")}</td>
	</tr>
	<tr>
		<td>${eoms:a2u("从")}</td>
		<td>${eoms:a2u("到")}</td>
	</tr>
	<tr>
		<td><input type="text" name="" maxlength="5" id="from_0"
			value="" class="text" style="width:80px" onblur="wds.onEditTimes()" /></td>
		<td><input type="text" name="" maxlength="5" id="to_0"
			value="" class="text" style="width:80px" onblur="wds.onEditTimes()" /></td>
	</tr>
	<tr>
		<td><input type="text" name="" maxlength="5" id="from_1"
			value="" class="text" style="width:80px" onblur="wds.onEditTimes()" /></td>
		<td><input type="text" name="" maxlength="5" id="to_1"
			value="" class="text" style="width:80px" onblur="wds.onEditTimes()" /></td>
	</tr>
	<tr>
		<td><input type="text" name="" maxlength="5" id="from_2"
			class="text" style="width:80px" onblur="wds.onEditTimes()" /></td>
		<td><input type="text" name="" maxlength="5" id="to_2"
			class="text" style="width:80px" onblur="wds.onEditTimes()" /></td>
	</tr>
	<tr>
		<td colspan="2">
		<input type="checkbox" name="overwrite" id="overwrite" class="checkbox" onblur="wds.onEditOptions()"/>
		${eoms:a2u('覆盖下级地域的本日设置')}
		<br/><br/><input type="button" value="${eoms:a2u('保存')}" onclick="wds.log();" /></td>
	</tr>
</table>
</div>
</div>

<div class="clear"></div>
<br/>

</div>
<script type="text/javascript">

	var DateString = "{" +
		"'2008-06-15':{config:1,time:['8:00-13:00','14:00-18:00'],overwrite:true}," +
		"'2008-06-01':{config:0,time:['9:30-12:30','15:30-17:30'],overwrite:true}," +
		"'2008-06-24':{config:2,time:['9:30-12:30','15:30-17:30'],overwrite:true}" +
		"}";
	var tplId = $('template').value;
	var config = {
	  tplData : DateString, 	//String 已编辑日期数据
	  tplId : tplId
	};
	
    var wds = new eoms.WorkDaySetter("dateSetter",config);

</script>
<%@ include file="/common/footer_eoms.jsp"%>