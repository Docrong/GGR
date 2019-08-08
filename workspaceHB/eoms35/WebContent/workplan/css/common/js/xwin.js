/*
------------------------------------------------------------------------------------
Copyright (c) 2004,������ͨ������ҵ��IP����
All rights reserved.
Filename ��xwin.js
Abstract �����ƶ��Ĳ㣬һ������ҳ�浼��
Version����1.0
Author   ��Liu Guoyuan
Finished Date ��2004-02-20
Last Modified ��2004-02-20
------------------------------------------------------------------------------------
����˵����
1.0�����ɿ��ƶ���ͼ�㣬��չ��/�رչ��ܣ�������������Ļ�ƶ�
------------------------------------------------------------------------------------
ʹ��˵����

�������� xWin

������
1 id��       ���ɵ�ͼ��ID  (�Զ�����ǰꡡ�xWin_")
2 width��    ��
3 height��   ��С�߶ȡ������߶ȸ���ʵ�����ݵ�����
4 left��     ��߶�λ
5 top��      ������λ
6 title��    ����
7 msg��      ����
8 isFollow�� �Ƿ������Ļ�ƶ�����true|false ,
������������ ע������������ƶ�ʱ��xWin����ʵ��������Ϊ"oXWin",��ֻ����һ��ͼ������ƶ�

������
build()������ͼ��

ʹ��ʾ����
<!-- ���� -->
<script language="javascript" src="../xwin.js"></script>
<script>
var xWin_content = 
"<table width='100%'  border='0' cellpadding='2' cellspacing='1' id='layNavigation'>"+
"  <tr><td><a href='#'>�����ض���</a></td></tr>"+
"  <tr><td><a href='javascript:search();'>�ѡ�����</a></td></tr>"+
"  <tr><td><a href='javascript:window.close()'>���رմ���</a></td></tr>"+
"</table>";
//����xWin����ʵ��
var oXWin = new xWin("1",120,200,document.body.clientWidth-150,50,"���ٵ���",xWin_content,true);
//��ʾxWin����
oXWin.bulid();
</script>
<!-- End ���� -->
-------------------------------------------------------------------------------------
*/

<
!--
//self.onError=null;
var xWin_x0 = 0, xWin_y0 = 0, xWin_x1 = 0, xWin_y1 = 0;
var xWin_offx = 6, xWin_offxy = 6;
var moveable = false;
var hover = 'orange', normal = 'slategray'; //color;
var xWin_index = 10000;//z-index;
//��ʼ�϶�;
function xWin_startDrag(obj) {
    //����������;
    obj.setCapture();
    //�������;
    var win = obj.parentNode;
    var sha = win.nextSibling;
    //��¼���Ͳ�λ��;
    xWin_x0 = event.clientX;
    xWin_y0 = event.clientY;
    xWin_x1 = parseInt(win.style.left);
    xWin_y1 = parseInt(win.style.top);
    //��¼��ɫ;
    normal = obj.style.backgroundColor;
    //�ı���;
    obj.style.backgroundColor = hover;
    win.style.borderColor = hover;
    obj.nextSibling.style.color = hover;
    sha.style.height = win.clientHeight + 2 * 2;
    sha.style.width = win.clientWidth + 2 * 2;
    sha.style.left = xWin_x1 + xWin_offx;
    sha.style.top = xWin_y1 + xWin_offxy;
    sha.style.display = "block";
    moveable = true;
}

//�϶�;
function xWin_drag(obj) {
    var win = obj.parentNode;
    var sha = win.nextSibling;
    if (moveable) {
        win.style.left = xWin_x1 + event.clientX - xWin_x0;
        win.style.top = xWin_y1 + event.clientY - xWin_y0;
        sha.style.left = parseInt(win.style.left) + xWin_offx;
        sha.style.top = parseInt(win.style.top) + xWin_offxy;
    }
}

//ֹͣ�϶�;
function xWin_stopDrag(obj) {
    var win = obj.parentNode;
    var sha = win.nextSibling;
    win.style.borderColor = normal;
    obj.style.backgroundColor = normal;
    obj.nextSibling.style.color = normal;
    sha.style.display = "none";
    sha.style.left = obj.parentNode.style.left;
    sha.style.top = obj.parentNode.style.top;
    //�ſ�������;
    obj.releaseCapture();
    moveable = false;
}

//��ý���;
function xWin_getFocus(obj) {
    xWin_index = xWin_index + 2;
    var idx = xWin_index;
    obj.style.zIndex = idx;
    obj.nextSibling.style.zIndex = idx - 1;
}

function xWin_min(obj) {
    var win = obj.parentNode.parentNode;
    var sha = win.nextSibling;
    var tit = obj.parentNode;
    var msg = tit.nextSibling;
    var flg = msg.style.display == "none";
    if (flg) {
        win.style.height = parseInt(msg.clientHeight) + parseInt(tit.clientHeight) + 2 * 2;
        sha.style.height = win.style.height;
        msg.style.display = "block";
        obj.innerHTML = "0";
    } else {
        win.style.height = parseInt(tit.clientHeight) + 2 * 2;
        sha.style.height = win.style.height;
        obj.innerHTML = "2";
        msg.style.display = "none";
    }
}

function xWin_cls(obj) {
    var win = obj.parentNode.parentNode;
    var sha = win.nextSibling;
    win.style.visibility = "hidden";
    sha.style.visibility = "hidden";
}

//����һ������;
function xWin(id, w, h, l, t, tit, msg, isFollow) {
    xWin_index = xWin_index + 2;
    this.id = "xWin_" + id;
    this.width = w;
    this.height = h;
    this.left = l;
    this.top = t;
    this.zIndex = xWin_index;
    this.title = tit;
    this.isFollow = isFollow;
    this.message = msg;
    this.obj = null;
    this.bulid = xWin_bulid;
}

//��ʼ��;
function xWin_bulid() {
    var str = ""
        + "<div id=" + this.id + " "
        + "style='"
        + "z-index:" + this.zIndex + ";"
        + "width:" + this.width + ";"
        + "height:" + this.height + ";"
        + "left:" + this.left + ";"
        + "top:" + this.top + ";"
        + "background-color:" + normal + ";"
        + "color:" + normal + ";"
        + "font-size:12px;"
        + "font-family:����;"
        + "position:absolute;"
        + "cursor:default;"
        + "border:2px solid " + normal + ";"
        + "' "
        + "onmousedown='xWin_getFocus(this)'>"
        + "<div "
        + "style='"
        + "background-color:" + normal + ";"
        + "width:" + (this.width) + ";"
        + "height:20;"
        + "color:white;"
        + "cursor:move;"
        + "' "
        + "onmousedown='xWin_startDrag(this)' "
        + "onmouseup='xWin_stopDrag(this)' "
        + "onmousemove='xWin_drag(this)' "
        + ">"
        + "<span style='width:" + (this.width - 2 * 12 - 4) + ";padding-left:3px;font_size=12px;'>" + this.title + "</span>"
        + "<span style='cursor:hand;width:12;border-width:0px;color:white;font-family:webdings;' onclick='xWin_min(this)'>0</span>"
        + "<span style='cursor:hand;width:12;border-width:0px;color:white;font-family:webdings;' onclick='xWin_cls(this)'>r</span>"
        + "</div>"
        + "<div style='"
        + "width:100%;"
        + "height:" + (this.height - 20 - 4) + ";"
        + "background-color:white;"
        + "line-height:14px;"
        + "word-break:break-all;"
        + "padding:3px;"
        + "'>" + this.message + "</div>"
        + "</div>"
        + "<div style='"
        + "width:" + this.width + ";"
        + "height:" + this.height + ";"
        + "top:" + this.top + ";"
        + "left:" + this.left + ";"
        + "z-index:" + (this.zIndex - 1) + ";"
        + "position:absolute;"
        + "background-color:black;"
        + "display:none;"
        + "filter:alpha(opacity=40);"
        + "'></div></div>";
    document.write(str);
    if (this.isFollow) {
        document.onload = window.setInterval("xWin_heartBeat(oXWin)", 80);
    }
    //document.body.insertAdjacentHTML("beforeEnd",str);
}

//������Ļ�ƶ�ͼ��
xWin_lastScrollX = 0;
xWin_lastScrollY = 0;

function xWin_heartBeat(xWinObj_Name) {
    var obj = eval("document.all." + xWinObj_Name.id);
    diffY = document.body.scrollTop;
    diffX = document.body.scrollLeft;
    if (diffY != xWin_lastScrollY) {
        percent = .300 * (diffY - xWin_lastScrollY);
        if (percent > 0) percent = Math.ceil(percent);
        else percent = Math.floor(percent);
        obj.style.pixelTop += percent;
        xWin_lastScrollY = xWin_lastScrollY + percent;
    }
    if (diffX != xWin_lastScrollX) {
        percent = .300 * (diffX - xWin_lastScrollX);
        if (percent > 0) percent = Math.ceil(percent);
        else percent = Math.floor(percent);
        obj.style.pixelLeft += percent;
        xWin_lastScrollX = xWin_lastScrollX + percent;
    }
}

//�����������ʽ��
document.write("<style type='text/css'>");
document.write("  #layNavigation{font-size: 12px;");
document.write("  #layNavigation table{background-color: #003366;}");
document.write("  #layNavigation td{line-height: 100%;text-align: center;color: #000000;}");
document.write("  #layNavigation a{color: #000000;}");
document.write("</style>");
//-->