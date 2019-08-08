var enabled = 0;
today = new Date();
var day;
var date;
if (today.getDay() == 0) day = "������"
if (today.getDay() == 1) day = "����һ"
if (today.getDay() == 2) day = "���ڶ�"
if (today.getDay() == 3) day = "������"
if (today.getDay() == 4) day = "������"
if (today.getDay() == 5) day = "������"
if (today.getDay() == 6) day = "������"
document.fgColor = "000000";
date = (today.getYear()) + "��" + (today.getMonth() + 1) + "��" + today.getDate() + "��&nbsp;&nbsp;<br> " + day + "";

var timerID = null;
var timerRunning = false;

function stopclock() {
    if (timerRunning)
        clearTimeout(timerID);
    timerRunning = false;
}

function startclock() {
    stopclock();
    showtime();
}

function showtime() {
    var now = new Date();
    var hours = now.getHours();
    var minutes = now.getMinutes();
    var seconds = now.getSeconds()
    var timeValue = "" + ((hours >= 12) ? "���� " : "���� ")
    timeValue += ((hours > 12) ? hours - 12 : hours)
    timeValue += ((minutes < 10) ? ":0" : ":") + minutes
    timeValue += ((seconds < 10) ? ":0" : ":") + seconds
    document.all.clock.innerText = timeValue;
    timerID = setTimeout("showtime()", 1000);
    timerRunning = true;
}

document.write(date);