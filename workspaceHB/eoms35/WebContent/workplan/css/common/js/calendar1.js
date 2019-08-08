var Calendar_outObject;
var Calendar_old_dd = null;

function setday(tt, obj) //��������
{
    if (arguments.length > 2) {
        alert("�Բ��𣡴��뱾�ؼ��Ĳ���̫�࣡");
        return;
    }
    if (arguments.length == 0) {
        alert("�Բ�����û�д��ر��ؼ��κβ�����");
        return;
    }
    var dads = document.all.bltDateLayer.style;
    var th = tt;
    var ttop = tt.offsetTop;     //TT�ؼ��Ķ�λ���
    var thei = tt.clientHeight;  //TT�ؼ�����ĸ�
    var tleft = tt.offsetLeft;    //TT�ؼ��Ķ�λ���
    var ttyp = tt.type;          //TT�ؼ�������
    while (tt = tt.offsetParent) {
        ttop += tt.offsetTop;
        tleft += tt.offsetLeft;
    }
    dads.top = (ttyp == "image") ? ttop + thei : ttop + thei + 6;
    dads.left = tleft;
    Calendar_outObject = (arguments.length == 1) ? th : obj;
    dads.display = '';
    event.returnValue = false;
}

var MonHead = new Array(12);    		   //����������ÿ���µ��������
MonHead[0] = 31;
MonHead[1] = 28;
MonHead[2] = 31;
MonHead[3] = 30;
MonHead[4] = 31;
MonHead[5] = 30;
MonHead[6] = 31;
MonHead[7] = 31;
MonHead[8] = 30;
MonHead[9] = 31;
MonHead[10] = 30;
MonHead[11] = 31;

var bltTheYear = new Date().getFullYear(); //������ı����ĳ�ʼֵ
var bltTheMonth = new Date().getMonth() + 1; //�����µı����ĳ�ʼֵ
var bltWDay = new Array(37);               //����д���ڵ�����
var m_yy, m_mm, m_dd;

function document

.
onclick() //������ʱ�رոÿؼ�
{
    with (window.event.srcElement) {
        if (tagName != "INPUT" && getAttribute("Author") == null)
            document.all.bltDateLayer.style.display = "none";
    }
}

function bltWriteHead(yy, mm)  //�� head ��д�뵱ǰ��������
{
    document.all.bltYearHead.innerText = yy;
    document.all.bltMonthHead.innerText = mm;
}

function tmpSelectYearInnerHTML(strYear) //��ݵ�������
{
    if (strYear.match(/\D/) != null) {
        alert("�����������������֣�");
        return;
    }
    var m = (strYear) ? strYear : new Date().getFullYear();
    if (m < 1000 || m > 9999) {
        alert("���ֵ���� 1000 �� 9999 ֮�䣡");
        return;
    }
    var n = m - 10;
    if (n < 1000) n = 1000;
    if (n + 26 > 9999) n = 9974;
    var s = "<select Author=blt name=tmpSelectYear style='font-size: 12px' "
    s += "onblur='document.all.tmpSelectYearLayer.style.display=\"none\"' "
    s += "onchange='document.all.tmpSelectYearLayer.style.display=\"none\";"
    s += "bltTheYear = this.value; bltSetDay(bltTheYear,bltTheMonth)'>\r\n";
    var selectInnerHTML = s;
    for (var i = n; i < n + 26; i++) {
        if (i == m) {
            selectInnerHTML += "<option value='" + i + "' selected>" + i + "��" + "</option>\r\n";
        } else {
            selectInnerHTML += "<option value='" + i + "'>" + i + "��" + "</option>\r\n";
        }
    }
    selectInnerHTML += "</select>";
    document.all.tmpSelectYearLayer.style.display = "";
    document.all.tmpSelectYearLayer.innerHTML = selectInnerHTML;
    document.all.tmpSelectYear.focus();
}

function tmpSelectMonthInnerHTML(strMonth) //�·ݵ�������
{
    if (strMonth.match(/\D/) != null) {
        alert("�·���������������֣�");
        return;
    }
    var m = (strMonth) ? strMonth : new Date().getMonth() + 1;
    var s = "<select Author=blt name=tmpSelectMonth style='font-size: 12px' "
    s += "onblur='document.all.tmpSelectMonthLayer.style.display=\"none\"' "
    s += "onchange='document.all.tmpSelectMonthLayer.style.display=\"none\";"
    s += "bltTheMonth = this.value; bltSetDay(bltTheYear,bltTheMonth)'>\r\n";
    var selectInnerHTML = s;
    for (var i = 1; i < 13; i++) {
        if (i == m) {
            selectInnerHTML += "<option value='" + i + "' selected>" + i + "��" + "</option>\r\n";
        } else {
            selectInnerHTML += "<option value='" + i + "'>" + i + "��" + "</option>\r\n";
        }
    }
    selectInnerHTML += "</select>";
    document.all.tmpSelectMonthLayer.style.display = "";
    document.all.tmpSelectMonthLayer.innerHTML = selectInnerHTML;
    document.all.tmpSelectMonth.focus();
}

function closeLayer()               //�����Ĺر�
{
    document.all.bltDateLayer.style.display = "none";
}

function document

.
onkeydown()
{
    if (window.event.keyCode == 27) document.all.bltDateLayer.style.display = "none";
}

function IsPinYear(year)            //�ж��Ƿ���ƽ��
{
    if (0 == year % 4 && ((year % 100 != 0) || (year % 400 == 0))) return true; else return false;
}

function GetMonthCount(year, month)  //�������Ϊ29��
{
    var c = MonHead[month - 1];
    if ((month == 2) && IsPinYear(year)) c++;
    return c;
}

function GetDOW(day, month, year)     //��ĳ������ڼ�
{
    var dt = new Date(year, month - 1, day).getDay() / 7;
    return dt;
}

function bltPrevY()  //��ǰ�� Year
{
    if (bltTheYear > 999 && bltTheYear < 10000) {
        bltTheYear--;
    } else {
        alert("��ݳ�����Χ��1000-9999����");
    }
    bltSetDay(bltTheYear, bltTheMonth);
}

function bltNextY()  //���� Year
{
    if (bltTheYear > 999 && bltTheYear < 10000) {
        bltTheYear++;
    } else {
        alert("��ݳ�����Χ��1000-9999����");
    }
    bltSetDay(bltTheYear, bltTheMonth);
}

function bltToday()  //Today Button
{
    bltTheYear = new Date().getFullYear();
    bltTheMonth = new Date().getMonth() + 1;
    bltSetDay(bltTheYear, bltTheMonth);
}

function bltPrevM()  //��ǰ���·�
{
    if (bltTheMonth > 1) {
        bltTheMonth--
    } else {
        bltTheYear--;
        bltTheMonth = 12;
    }
    bltSetDay(bltTheYear, bltTheMonth);
}

function bltNextM()  //�����·�
{
    if (bltTheMonth == 12) {
        bltTheYear++;
        bltTheMonth = 1
    } else {
        bltTheMonth++
    }
    bltSetDay(bltTheYear, bltTheMonth);
}

function bltSetDay(yy, mm)   //��Ҫ��д����**********
{
    bltWriteHead(yy, mm);
    for (var i = 0; i < 37; i++) {
        bltWDay[i] = ""
    }
    ;  //����ʾ�������ȫ�����
    var day1 = 1, firstday = new Date(yy, mm - 1, 1).getDay();  //ĳ�µ�һ������ڼ�
    for (var i = firstday; day1 < GetMonthCount(yy, mm) + 1; i++) {
        bltWDay[i] = day1;
        day1++;
    }
    for (var i = 0; i < 37; i++) {
        var da = eval("document.all.bltDay" + i)     //��д�µ�һ���µ�������������
        if (bltWDay[i] != "") {
            da.innerHTML = "<b>" + bltWDay[i] + "</b>";
            da.style.backgroundColor = (yy == new Date().getFullYear() &&
                //      mm == new Date().getMonth()+1 && bltWDay[i] == new Date().getDate()) ? "#FFD700" : "#ADD8E6";
                mm == new Date().getMonth() + 1 && bltWDay[i] == new Date().getDate()) ? "#ADD8E6" : "#ADD8E6";
            da.style.cursor = "hand"
        } else {
            da.innerHTML = "";
            da.style.backgroundColor = "";
            da.style.cursor = "default";
        }
    }
}

function setDayTime() {
    //ע�����������������ĳ�����Ҫ�ĸ�ʽ
    if (m_yy != null && m_mm != null && m_dd != null) {
        if (document.all("m_hour").value == '-' || document.all("m_min").value == '-')	//���ûѡʱ��
            Calendar_outObject.value = m_yy + "-" + m_mm + "-" + m_dd;
        else
            Calendar_outObject.value = m_yy + "-" + m_mm + "-" + m_dd + " " + document.all("m_hour").value + ":" + document.all("m_min").value + ":00";
    }
    closeLayer();
}

function bltDayClick(n, obj)  //�����ʾ��ѡȡ���ڣ������뺯��*************
{
    var yy = bltTheYear;
    var mm = bltTheMonth;
    m_yy = yy;
    if (mm < 10) {
        m_mm = "0" + mm;
    } else {
        m_mm = "" + mm;
    }
    if (Calendar_outObject) {
        if (!n) {
            Calendar_outObject.value = "";
            return;
        }
        if (n < 10) {
            m_dd = "0" + n;
        } else {
            m_dd = "" + n;
        }
        if (Calendar_old_dd != null)
            Calendar_old_dd.style.backgroundColor = "#ADD8E6";
        Calendar_old_dd = obj;
        obj.style.backgroundColor = "#FFD700";
    } else {
        closeLayer();
        alert("�������");
    }
    setDayTime();
}

function calendarOutput() {
    document.writeln('<style>Label{font-size:12px;background-color:#48AFFE;cursor:hand;border:1px solid #485555;height:18px;padding:2 2 2 2;}</style>');
    document.writeln('<div id=bltDateLayer style="position: absolute; width: 142; height: 166; z-index: 9998; display: none">');
    document.writeln('<span id=tmpSelectYearLayer  style="z-index: 9999;position: absolute;top: 2; left: 18;display: none"></span>');
    document.writeln('<span id=tmpSelectMonthLayer style="z-index: 9999;position: absolute;top: 2; left: 75;display: none"></span>');
    document.writeln('<table border=0 cellspacing=1 cellpadding=0 width=142 height=160 bgcolor=#808080 onselectstart="return false">');
    document.writeln('  <tr><td width=142 height=23 bgcolor=#FFFFFF><table border=0 cellspacing=1 cellpadding=0 width=140 height=23>');
    document.writeln('      <tr align=center><td width=20 align=center bgcolor=#808080 style="font-size:12px;cursor: hand;color: #FFD700" ');
    document.writeln('        onclick="bltPrevM()" title="��ǰ��һ��" Author=blt><b Author=blt>&lt;&lt;</b>');
    document.writeln('        </td><td width=100 align=center style="font-size:12px;cursor:default" Author=blt>');
    document.writeln('        <span Author=blt id=bltYearHead onclick="tmpSelectYearInnerHTML(this.innerText)"></span>&nbsp;��&nbsp;<span');
    document.writeln('         id=bltMonthHead Author=blt onclick="tmpSelectMonthInnerHTML(this.innerText)"></span>&nbsp;��</td>');
    document.writeln('        <td width=20 bgcolor=#808080 align=center style="font-size:12px;cursor: hand;color: #FFD700" ');
    document.writeln('         onclick="bltNextM()" title="����һ��" Author=blt><b Author=blt>&gt;&gt;</b></td></tr>');
    document.writeln('    </table></td></tr>');
    document.writeln('  <tr><td width=142 height=18 bgcolor=#808080>');
    document.writeln('<table border=0 cellspacing=0 cellpadding=0 width=140 height=1 style="cursor:default">');
    document.writeln('<tr align=center><td style="font-size:12px;color:#FFFFFF" Author=blt>��</td>');
    document.writeln('<td style="font-size:12px;color:#FFFFFF" Author=blt>һ</td><td style="font-size:12px;color:#FFFFFF" Author=blt>��</td>');
    document.writeln('<td style="font-size:12px;color:#FFFFFF" Author=blt>��</td><td style="font-size:12px;color:#FFFFFF" Author=blt>��</td>');
    document.writeln('<td style="font-size:12px;color:#FFFFFF" Author=blt>��</td><td style="font-size:12px;color:#FFFFFF" Author=blt>��</td></tr>');
    document.writeln('</table></td></tr>');
    document.writeln('  <tr><td width=142 height=120>');
    document.writeln('    <table border=0 cellspacing=1 cellpadding=0 width=140 height=120 bgcolor=#FFFFFF Author=blt style=font-size:12px> ');
    var n = 0;
    for (j = 0; j < 6; j++) {
        document.writeln(' <tr align=center>');
        for (i = 0; i < 7; i++) {
            document.writeln('<td width=20 height=20 id=bltDay' + n + ' style="font-size:12px" Author=blt onclick=bltDayClick(this.innerText,this)></td>');
            n++;
        }
        document.writeln('</tr>');
    }
    document.writeln('      <tr align=center>');
    document.writeln('        <td align=center colspan=4 Author=blt >');
    document.writeln('         <select name="m_hour" Author=blt style=font-size:12px>');
    for (j = 0; j < 24; j++) {
        if (j < 10)
            document.writeln('         <option value="0' + j + '">' + j + '</option>');
        else
            document.writeln('         <option value="' + j + '">' + j + '</option>');
    }
    document.writeln('         </select>ʱ</td>');
    document.writeln('        <td align=center colspan=4 Author=blt>');
    document.writeln('         <select name="m_min" Author=blt style=font-size:12px>');
    for (j = 0; j < 60; j++) {
        if (j < 10)
            document.writeln('         <option value="0' + j + '">' + j + '</option>');
        else
            document.writeln('         <option value="' + j + '">' + j + '</option>');
    }
    document.writeln('         </select>��');
    document.writeln('         </td></tr>');
    document.writeln('         <tr>');
    document.writeln('        <td colspan=4 align=center Author=blt>');
    document.writeln('         <Label onclick=setDayTime()>ȷ ��</Label>');
    document.writeln('        </td>');

    document.writeln('        <td colspan=4 align=center Author=blt>');
    document.writeln('         <Label onclick=closeLayer()>ȡ ��</Label>');
    document.writeln('         &nbsp;</td></tr>');
    document.writeln('    </table></td></tr><tr><td>');
    document.writeln('        <table border=0 cellspacing=1 cellpadding=0 width=100% bgcolor=#FFFFFF>');
    document.writeln('          <tr><td Author=blt align=left><Label Author=blt title="��ǰ��һ��" onclick="bltPrevY()" ');
    document.writeln('             onfocus="this.blur()">< </Label> <Label Author=blt title="��ǰ��һ��" ');
    document.writeln('             onclick="bltPrevM()" onfocus="this.blur()"><<</Label></td><td ');
    document.writeln('             Author=blt align=center><Label Author=blt onclick="bltToday()" ');
    document.writeln('             onfocus="this.blur()" title="���ڵ�����">�� ��</Label></td><td ');
    document.writeln('             Author=blt align=right><Label Author=blt onclick="bltNextM()" ');
    document.writeln('             onfocus="this.blur()" title="����һ��">>></Label> <Label ');
    document.writeln('             Author=blt title="����һ��" onclick="bltNextY()"');
    document.writeln('             onfocus="this.blur()"> ></Label></td>');
    document.writeln('</tr></table></td></tr></table></div>');
    bltSetDay(bltTheYear, bltTheMonth);
}

document.onload = calendarOutput();