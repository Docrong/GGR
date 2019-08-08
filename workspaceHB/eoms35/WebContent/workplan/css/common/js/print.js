/*
------------------------------------------------------------------------------------
Copyright (c) 2002,������ͨ������ҵ��IP����
All rights reserved.
Filename ��print.js
Abstract �����ô�ӡ����
Version����1.0
Author   ��Liu Guoyuan
Finished Date ��2003-03-19
Last Modified ��2003-03-20
-------------------------------------------------------------------------------------
*/

with (document) {//�����ʽ��IE��ӡ�ؼ�
    write("<style type=\"text/css\" media=\"print\">");
    write("  .noPrint{visibility:hidden}");
    write("</style>");
    write("<OBJECT classid=CLSID:8856F961-340A-11D0-A96B-00C04FD705A2 height=0 id=WB width=0></object>");
}

function doPrintSetup() {//��ӡ����
    WB.ExecWB(8, 1);
}

function doPrintPreview() {//��ӡԤ��
    WB.ExecWB(7, 1);
}

function doPrint() {
    window.print();
}

function showPrintBar() {
    with (document) {
        write("<div align=\"center\" class=\"noprint\">");
        write("  <input type=\"button\" name=\"doBack\" value=\" &lt;&lt;����  \" onClick=\"history.go(-1)\">");
        write("  <input type=\"button\" name=\"doPrintPreview\" onClick=\"WB.ExecWB(8,1)\" value=\"��ӡ����\">");
        write("  <input type=\"button\" name=\"doPrint\" value=\"  ��ӡ&gt;&gt; \" onClick=\"doPrint()\">");
        write("</div>")
    }
}