<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms_ext.jsp"%>
<link rel="stylesheet" type="text/css" href="${app}/styles/default/lunar-calendar.css"></link>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/scripts/widgets/test/test-workday-setter.css"></link>

<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/util/Lunar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/widgets/LunarCalendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/scripts/widgets/WorkDaySetter.js"></script>

<div class="app-border">
<div class="wds-header">
<h1>${eoms:a2u("工作日设置工具")}</h1></div>

<div id="wds-tpl">
 <form  name="tawWorkdaySetForm" action="${app}/workdayset/TawWorkdaySetAction.do?method=x_save" method="post" styleId="tawWorkdaySetForm">
    <input type="hidden" value="" id="data" name="data" />
    <input type="hidden" value="${areaid}" id="template" name="template" />
	${areaname}
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
		<br/><br/><input type="submit" value="${eoms:a2u('保存')}"/></td>
	</tr>
</table>
</div>
</div>

<div class="clear"></div>
<br/>

</div>
  </form>
<script type="text/javascript">
	var tplId = $('template').value;
	var config = {
	  tplData : ${data}==""?"[]":${data}, 	//String
	  tplId : tplId
	};	
    var wds = new eoms.WorkDaySetter("dateSetter",config);
</script>
<%@ include file="/common/footer_eoms.jsp"%>