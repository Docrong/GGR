<%@ page language="java"  pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%
	String obj_name = request.getParameter("obj_name");
%>
<html><head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>时间选择</title>
<style>
	.MinuteComponent
		{
			behavior: url("../../behavior/MinuteComponent.htc");
		}
</style>
</head>
<body bgcolor="c0c0c0" style="font-size:9pt" onload="" onkeypress="keyPress()">
<center><br>
<DIV id="dateHidDiv" name="dateHidDiv" width="100%"><NOBR>
	<select id="yearSle" name="yearSle" style="width:80px;position:relative" onchange="window.event.cancelBubble=true;showDay();return true;"  size=10>
		<script language="vbscript">
			Now_Y   = Year(Now())
			document.write "<option value='"&Now_Y+5&"'>"&Now_Y+5&"年</option>"
			document.write "<option value='"&Now_Y+4&"'>"&Now_Y+4&"年</option>"
			document.write "<option value='"&Now_Y+3&"'>"&Now_Y+3&"年</option>"
			document.write "<option value='"&Now_Y+2&"'>"&Now_Y+2&"年</option>"
			document.write "<option value='"&Now_Y+1&"'>"&Now_Y+1&"年</option>"
			document.write "<option value='"&Now_Y&"' selected>"&Now_Y&"年</option>"
			for TimeSign = 1 to 2
				Now_Y = Now_Y-1
				document.Write "<option value='"&Now_Y&"'>"&Now_Y&"年</option>"
			Next
		</script>
	</select>
	<select id="monthSle" name="monthSle" style="width:80px;position:relative" onchange="window.event.cancelBubble=true;showDay();return true;"  size=10>
		<option value="01">1月</option>
		<option value="02">2月</option>
		<option value="03">3月</option>
		<option value="04">4月</option>
		<option value="05">5月</option>
		<option value="06">6月</option>
		<option value="07">7月</option>
		<option value="08">8月</option>
		<option value="09">9月</option>
		<option value="10">10月</option>
		<option value="11">11月</option>
		<option value="12">12月</option>
	</select>
	<select id="daySle" name="daySle" style="width:80px;position:relative" onclick="window.event.cancelBubble=true;return true;" size=10>
		<option value="01" selected>1日</option>
		<option value="02">2日</option>
		<option value="03">3日</option>
		<option value="04">4日</option>
		<option value="05">5日</option>
		<option value="06">6日</option>
		<option value="07">7日</option>
		<option value="08">8日</option>
		<option value="09">9日</option>
		<option value="10">10日</option>
		<option value="11">11日</option>
		<option value="12">12日</option>
		<option value="13">13日</option>
		<option value="14">14日</option>
		<option value="15">15日</option>
		<option value="16">16日</option>
		<option value="17">17日</option>
		<option value="18">18日</option>
		<option value="19">19日</option>
		<option value="20">20日</option>
		<option value="21">21日</option>
		<option value="22">22日</option>
		<option value="23">23日</option>
		<option value="24">24日</option>
		<option value="25">25日</option>
		<option value="26">26日</option>
		<option value="27">27日</option>
		<option value="28">28日</option>
	</select>
	<CENTER><br/><table>
		<tr><td width="50%" nowrap>
			<SCRIPT language="vbscript">
				document.writeln "时间<INPUT id='hourInput' name='hourInput' class='MinuteComponent' inputValue=" & FormatDateTime(Time(),4) & " maskStyle='Normal' style='position:relative;font-size:9pt;width:50px'>"
			</SCRIPT>
		</td><td width="25%" nowrap>
			<input type="button" value="确&nbsp;&nbsp;定" style="font-size:9pt;border:1px outset;margin-bottom:0px;padding:0px" onmouseover="style.cursor='hand';" onclick="javascript:passValue()"/>
		</td><td width="25%" nowrap>
			<input type="button" value="取&nbsp;&nbsp;消" style="font-size:9pt;border:1px outset;margin-bottom:0px;padding:0px" onmouseover="style.cursor='hand';" onclick="javascript:window.close();"/>
		</td></tr>
	</CENTER>
</DIV>
</center>
</body>
<script language='vbscript'>
	Now_M = Month(Now())-1
	If Not(document.all.monthSle.options(Now_M) is Nothing) Then
		document.all.monthSle.options(Now_M).selected = true
    	End If
</script>
<script language="javascript">
showDay();
function fnPassCdn(){
		logTimeA.innerText = param;
	}

function passValue(){
	var year = yearSle.value;
	var month= monthSle.value;
	var day  = daySle.value;
	var hour = hourInput.value;
	var param= year+"-"+month+"-"+day+" "+hour+":00";
	var sData = dialogArguments;
	if(sData.document.forms[0])	sData.document.forms[0].<%=obj_name%>.value = param;
	else sData.<%=obj_name%>.value = param;
	window.close();
}

function showDay(){
		 var dayLoop;
	if (monthSle.value=="02"){

		if ((yearSle.value == "2000")||(yearSle.value == "2004")||(yearSle.value == "2008")){
		 	if (daySle.options.length < 29){
			    	var Day29= new Option("29日","29");
			    	daySle.options.add(Day29);
		   	}else{
		    		for (dayLoop=daySle.options.length-1;dayLoop>28;dayLoop--){
			     		daySle.options.remove(dayLoop);
			    	}
			   	}
		}else{
			for (dayLoop=daySle.options.length-1;dayLoop>27;dayLoop--){
			   		daySle.options.remove(dayLoop);
			   	}
			}
	}else if ((monthSle.value=="04")||(monthSle.value=="06")||(monthSle.value=="09")||(monthSle.value=="11")){
		if (daySle.options.length < 30){
				var DayOption,dayTag;
			for (dayLoop=daySle.options.length;dayLoop<30;dayLoop++){
			    	dayTag =  dayLoop+1;
			    	DayOption = new Option(dayTag+"日",dayTag);
			    	daySle.options.add(DayOption);
			   	}
		}else if (daySle.options.length > 30){
			for (dayLoop=daySle.options.length-1;dayLoop>29;dayLoop--){
			    	daySle.options.remove(dayLoop);
			   	}
			}
	}else{
		if (daySle.options.length < 31){
				var DayOption,dayTag;
			for (dayLoop=daySle.options.length;dayLoop<31;dayLoop++){
			    	dayTag =  dayLoop+1;
			    	DayOption = new Option(dayTag+"日",dayTag);
			    	daySle.options.add(DayOption);
			   	}
			}
		}
	}
	function keyPress(){
		if (event.keyCode==13) {
			passValue();
		}else if (event.keyCode==16) {
			alert();
		}
	}
</script>
<script language='vbscript'>
	Now_D = Day(Now())-1
    If Not(document.all.daySle.options(Now_D) is Nothing) Then
	daySle.options(Now_D).selected = true
    End If
</script>
</html>