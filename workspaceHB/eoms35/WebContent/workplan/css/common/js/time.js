var enabled = 0; today = new Date();
var day; var date;
if(today.getDay()==0) day = "星期日"
if(today.getDay()==1) day = "星期一"
if(today.getDay()==2) day = "星期二"
if(today.getDay()==3) day = "星期三"
if(today.getDay()==4) day = "星期四"
if(today.getDay()==5) day = "星期五"
if(today.getDay()==6) day = "星期六"
document.fgColor = "000000";
date = (today.getYear()) + "年" + (today.getMonth() + 1 ) + "月" + today.getDate() + "日&nbsp;&nbsp;<br> " + day +"";

var timerID = null;
var timerRunning = false;
function stopclock (){
if(timerRunning)
clearTimeout(timerID);
timerRunning = false;}
function startclock () {
stopclock();
showtime();}
function showtime () {
var now = new Date();
var hours = now.getHours();
var minutes = now.getMinutes();
var seconds = now.getSeconds()
var timeValue = "" +((hours >= 12) ? "下午 " : "上午 " )
timeValue += ((hours >12) ? hours -12 :hours)
timeValue += ((minutes < 10) ? ":0" : ":") + minutes
timeValue += ((seconds < 10) ? ":0" : ":") + seconds
document.all.clock.innerText = timeValue;
timerID = setTimeout("showtime()",1000);
timerRunning = true;
}

document.write(date);