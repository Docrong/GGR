/********************************************************************************
 Copyright (c) 2004-2006,������ͨ������ҵ��IP����
 All rights reserved.
 Filename ��date.js
 Abstract ��������غ�����
 Version����1.0
 Author   ��Liu Guoyuan
 Finished Date ��2004-04-17
 Last Modified ��2004-08-12
 ********************************************************************************/

//��ȡ������
if (typeof (loadJS) == "function") {
    _loadJS("lib.js");
    _loadJS("checkform.js");
} else {
    alert("date.js: �����comm.js!");
}

/*********************************
 ��ʱ��ؼ��л�ȡ����ʱ��
 �ؼ���������
 ��ʼ�����տؼ�����start_time
 ���������տؼ�����end_time
 ��ʼСʱ�ؼ�����  bHour
 ����Сʱ�ؼ�����  eHour
 ��ʼ���ӿؼ�����  bMin
 �������ӿؼ�����  eMin
 ������
 timeType:
 "start" ���ؿ�ʼʱ��,�д���ʱ����false
 "end" ���ؽ���ʱ��,�д���ʱ����false
 �޲���ʱ������true|false����������ʽ��֤
 objIndex:
 �������������������ҳ����ڶ������ѡ���ʱ����Ҫ�˲�����ֵ��0��ʼ
 *********************************/
function getDateStrFromObj(timeType, objIndex) {
    if (objIndex == null) objIndex = 0;
    if (!CheckIsDateTime(document.getElementsByName("start_time")[objIndex], "��ʼ����")) return false;
    //û�н���ʱ��,���ؿ�ʼʱ��
    if (typeof (document.getElementsByName("end_time")[objIndex]) != "object") {
        return document.getElementsByName("start_time")[objIndex].value + " " +
            document.getElementsByName("bHour")[objIndex].value + ":" +
            document.getElementsByName("bMin")[objIndex].value + ":00";
    }

    //�н���ʱ�䣬�жϽ���ʱ���Ƿ���ڿ�ʼʱ��
    if (!CheckIsDateTime(document.getElementsByName("end_time")[objIndex], "��������")) return false;

    var start_time = document.getElementsByName("start_time")[objIndex].value;
    var end_time = document.getElementsByName("end_time")[objIndex].value
    var bHour = parseInt(document.getElementsByName("bHour")[objIndex].value);
    var eHour = parseInt(document.getElementsByName("eHour")[objIndex].value);
    var bMin = parseInt(document.getElementsByName("bMin")[objIndex].value);
    var eMin = parseInt(document.getElementsByName("eMin")[objIndex].value);

    var dateOffsetValue = end_time.toDate().diff(start_time.toDate(), "d"); //ʱ���ֵ
    if (dateOffsetValue < 0) {
        alert("�������ڱ�����ڿ�ʼ���ڣ�");
        document.getElementsByName("end_time")[objIndex].focus();
        return false;
    } else if (dateOffsetValue == 0) {
        if (bHour > eHour) {
            alert("����ʱ�������ڿ�ʼʱ�䣡");
            document.getElementsByName("eHour")[objIndex].focus();
            return false;
        } else {
            if (bHour == eHour && bMin >= eMin) {
                alert("����ʱ�������ڿ�ʼʱ�䣡");
                document.getElementsByName("eMin")[objIndex].focus();
                return false;
            }
        }
    }
    if (timeType == "start") { //���ؿ�ʼʱ��
        return document.getElementsByName("start_time")[objIndex].value + " " + bHour + ":" + bMin + ":00";
    } else if (timeType == "end") {//���ؽ���ʱ��
        return document.getElementsByName("end_time")[objIndex].value + " " + eHour + ":" + eMin + ":00";
    } else {
        return true;
    }
}

/*********************************
 �õ�ʱ������ؼ���
 �ؼ�������
 ��ʼ�����տؼ�����start_time
 ���������տؼ�����end_time
 ��ʼСʱ�ؼ�����  bHour
 ����Сʱ�ؼ�����  eHour
 ��ʼ���ӿؼ�����  bMin
 �������ӿؼ�����  eMin
 ������
 timeType: ʱ������
 Ĭ�ϣ�"start" ���ؿ�ʼʱ������ؼ���
 "end" ���ؽ���ʱ������ؼ���,�д���ʱ����false
 defaultDate: Ĭ��ʱ�䣬Ϊ��ʱʱ��Ϊ����
 minStep: ���Ӳ�����Ĭ��Ϊ1

 ע���˷���Ӧ��getDateStrFromObj()�������ʹ�ã��Ի�ȡʱ�䡣
 *********************************/
function getDateInputObj(timeType, defaultDate, minStep) {
    var asTimeObjName;
    if (timeType != null && timeType.toLowerCase() == "end") {
        asTimeObjName = new Array("end_time", "eHour", "eMin");  //�ؼ���
    } else {
        asTimeObjName = new Array("start_time", "bHour", "bMin");  //�ؼ���
    }

    var defDate, defHour, defMin; //Ĭ��ʱ��
    if (defaultDate == null || defaultDate == "") {//û��Ĭ��ʱ�䣬��ʼʱ��Ϊ����
        defDate = new Date().toDateString();
        defHour = 0;
        defMin = 0;
    } else {
        defDate = defaultDate.split(" ")[0];
        defHour = defaultDate.split(" ")[1].split(":")[0];
        defMin = defaultDate.split(" ")[1].split(":")[1];
    }
    if (minStep == null || minStep == "") { //���Ӳ���
        minStep = 1;
    }

    //Сʱ�б�
    var strHourMenu = "";
    for (var i = 0; i < 24; i++) {
        strHourMenu += "<option value='" + i + "'";
        if (i == defHour) strHourMenu += " selected";
        strHourMenu += ">";
        if (i < 10) strHourMenu += "&nbsp;";
        strHourMenu += i + "ʱ</option>";
    }
    //�����б�
    var strMinMenu = ""
    for (var i = 0; i < 60; i += minStep) {
        strMinMenu += "<option value='" + i + "'";
        if (i == defMin) strMinMenu += " selected";
        strMinMenu += ">";
        if (i < 10) strMinMenu += "&nbsp;";
        strMinMenu += +i + "��</option>";
    }
    var strResult = "<INPUT style='width:66;font-size:12px' type='text' name='" + asTimeObjName[0] + "' value='" + defDate + "' size='10' maxlength='10'";
    if (typeof (calendar) == "function") {//�����������ؼ�
        strResult += " onfocus='calendar()'";
    }
    strResult += ">" +
        "<SELECT style='width:47;font-size:12px' name='" + asTimeObjName[1] + "'>" + strHourMenu + "</SELECT>" +
        "<SELECT style='width:47;font-size:12px' name='" + asTimeObjName[2] + "'>" + strMinMenu + "</SELECT>";
    return strResult;
}