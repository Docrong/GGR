/********************************************************************************
 Copyright (c) 2002-2005,������ͨ������ҵ��IP����
 All rights reserved.
 Filename ��checkform.js
 Abstract �����ñ���⺯����
 Version����2.0
 Author   ��Liu Guoyuan
 Finished Date ��2002-09-20
 Last Modified ��2004-08-12

 Ŀǰ������

 CheckIsNull(objName[,text])   //�ж��ı����Ƿ�Ϊ��,��SELECT�˵��Ƿ�ѡ�У�������(������[��������ʾ])
 CheckIsEmail(objName[,text])
 CheckIsString(objName,[objCnName])
 CheckIsIP(objName[,text])
 CheckIsNum(objName[,objCnName][,minNum,maxNum])  //�Ƿ����֣���������Ԫ����,[����������],[��Сֵ�����ֵ]
 CheckIsErrLength(objName,maxLength[,objCnName])  //�ַ��������Ƿ���ȷ(������Ӣ��),������(����������󳤶�[������������])
 CheckIsDateTime(objName[,objCnName])
 ע��������������������ѡ�������򲻻ᵯ��������ʾ��
 ********************************************************************************/

//��ȡ������
function _loadJS(src) {
    var script = document.getElementsByTagName("SCRIPT");
    for (var i = 0; i < script.length; i++) {
        var s = script[i].src;
        if (s.indexOf(src) >= 0) return;
        if (s.indexOf("/common/js/checkform.js") != -1) {
            jsPath = s.replace("checkform.js", "")
        }
    }
    var oScript = document.createElement("<SCRIPT>");
    oScript.src = jsPath + src;
    script[0].insertAdjacentElement("afterEnd", oScript);
}

_loadJS("lib.js");

function CheckIsNull(objName, text) {
    /*�ж��ı����Ƿ�Ϊ��,��SELECT�˵��Ƿ�ѡ��
     ������(��������������ʾ)
     ����: true ��ʾ�д���
    */
    var Object = getObject(objName);
    var bError = false;
    if (typeof (Object) == "object" && Object != null) {
        switch (Object.tagName) {
            case "SELECT":   //ѡ��˵�
                if (Object.options[0].selected && (Object.options[0].value == "-1" || Object.options[0].value == "")) bError = true;
                break;
            case "INPUT":    //�ı���
                if (Object.value == "") bError = true;
                break;
            case "TEXTAREA":    //�����ı���
                if (Object.value == "") bError = true;
                break;
            default :
                alert("�����������󣺣�" + Object.tagName + "��");
                return true;
        }
        if (bError) {
            if (arguments.length > 1) alert(text); //����������ʾ
            Object.focus();
            return bError;
        }
        return bError;
    } else {
        alert("CheckIsNull:�������󣨶���\"" + objName + "\"�����ڣ�!")
        return true;
    }
}

function CheckIsErrLength(objName, maxLength, text) {
//�ж��ַ�����ʵ���ȣ����ĳ�Ϊ2��Ӣ�ĳ�1
//��������Ԫ����,��󳤶�,[��������]
    var Object = getObject(objName);
    valueLength = Object.value.realLength();
    if (valueLength > maxLength) {
        if (arguments.length > 2) alert(text + "���ȱ����� " + maxLength + " ����!\n��ǰ����Ϊ��" + valueLength + "\n(���ĳ���Ϊ����Ӣ��Ϊ��)"); //����������ʾ
        Object.focus();
        return true;
    }
    return false;
}

function CheckIsDateTime(objName, objCnName) {//���ڸ�ʽ�Ƿ���ȷ����������Ԫ����,[Ԫ��������]
    var Object = getObject(objName);
    if (Date_istrue(Object.value)) {
        return true;
    } else {
        if (arguments.length > 1) {
            if (Object.value.indexOf(" ") > 0)
                alert(objCnName + "��ʽ����ȷ.\nYYYY-MM-DD HH:MI:SS(��-��-�� ʱ:��:��)"); //����������ʾ
            else
                alert(objCnName + "��ʽ����ȷ.\nYYYY-MM-DD(��-��-��)"); //����������ʾ
        }
        Object.focus();
        Object.select();
        return false;
    }
}

function CheckDateTime(asDate) {
    return Date_istrue(asDate);
}

function Date_istrue(asDate) {
    return asDate.isDate();
}

function CheckIsString(objName, objCnName) {
//�Ƿ����֣���ĸ��
//��������Ԫ����,[��ʾ��Ϣ]
    var Object = getObject(objName);
    if (CheckString(Object.value)) {
        return true;
    } else {
        if (arguments.length > 1) alert(objCnName + "��ʽ����(����Ϊ��Сд��ĸ+����+�»���)"); //����������ʾ
        Object.focus();
        return false;
    }
}

function CheckString(str) {
//�Ƿ�ֻ�������֣���ĸ���»���
    var re = /[^A-Za-z0-9_]/;
    if (str.search(re) == -1) return true;
    else return false;
}

function CheckIsNum(objName, objCnName, minNum, maxNum) {//�Ƿ����֣���������Ԫ����,[����������],[��Сֵ�����ֵ]
    var Object = getObject(objName);
    if (arguments.length > 3) { //����Сֵ�����ֵ����
        if (CheckNumber(Object.value, minNum, maxNum)) return true;
    } else {
        if (CheckNumber(Object.value)) return true;
    }
    if (objCnName != "") {
        errMsg = objCnName + "��������.";
        if (arguments.length > 3) errMsg += "\n������(" + minNum + "-" + maxNum + ")֮��!"
        alert(errMsg);
    }
    Object.focus();
    Object.select();
    return false;
}

function CheckNumber(num, minNum, maxNum) {//�Ƿ�����,������(���֣�[��Сֵ�����ֵ])
    if (num == "") return false;
    var strNum = num + "";
    if (strNum.search(/\D/) >= 0) {
        return false;
    }
    num = parseInt(num);
    var minNum = parseInt(minNum);
    var maxNum = parseInt(maxNum);
    if (isNaN(num) || (num < minNum || num > maxNum))
        return false;
    else
        return true;
}

function isNumeric(strNumber) { //����
    if ((parseFloat(strNumber) + "").length == strNumber.length) return true;
    return (strNumber.search(/^(-?\+)?\d+(\.\d+)?$/) != -1);
}

function CheckIsIP(objName, text) {
//�ж�IP��ַ�Ƿ���ȷ
//��������Ԫ����,[��ʾ��Ϣ]
    var Object = getObject(objName);
    if (CheckIPAddress(Object.value)) {
        return true;
    } else {
        if (arguments.length > 1) alert(text); //����������ʾ
        Object.focus();
        return false;
    }
}

function CheckIPAddress(ipAddressString) {
    return ipAddressString.isIP();
}

function CheckIsEmail(objName, text) {
//�ж�Email��ַ�Ƿ���ȷ
//��������Ԫ����,[��ʾ��Ϣ]
    var Object = getObject(objName);
    if (CheckEmail(Object.value)) {
        return true;
    } else {
        if (arguments.length > 1) alert(text); //����������ʾ
        Object.focus();
        return false;
    }
}

function CheckEmail(emailStr) {
    return emailStr.isEmail();
}

function IsBeginEndIP(startIP, endIP) {
    var iTotalDots = 0;
    var iFlag = 0;
    var strMedia = '';
    var startIP1 = 0;
    var startIP2 = 0;
    var startIP3 = 0;
    var startIP4 = 0;
    var endIP1 = 0;
    var endIP2 = 0;
    var endIP3 = 0;
    var endIP4 = 0;
    for (var i = 0; i < startIP.length; i++) {
        var chMedia = startIP.substring(i, i + 1);
        if (chMedia >= '0' && chMedia <= '9')
            strMedia = strMedia + chMedia;
        if (chMedia < '0' || chMedia > '9') {
            if (chMedia == '.') {
                if (strMedia > 255)
                    return false;
                if (iTotalDots == 0) startIP1 = parseInt(strMedia);
                else if (iTotalDots == 1) startIP2 = parseInt(strMedia);
                else if (iTotalDots == 2) startIP3 = parseInt(strMedia);
                strMedia = '';
                if (iFlag == 1)
                    return false;
                iTotalDots++;
                iFlag = 1;
                continue;
            }
            return false;
        }
        iFlag = 0;
    }
    if (strMedia > 255)
        return false;
    startIP4 = parseInt(strMedia);
    if (iTotalDots != 3)
        return false;
    if (startIP.substring(startIP.length - 1, startIP.length) == '.')
        return false;

    strMedia = '';
    iTotalDots = 0;
    iFlag = 0;
    for (var i = 0; i < endIP.length; i++) {
        var chMedia = endIP.substring(i, i + 1);
        if (chMedia >= '0' && chMedia <= '9')
            strMedia = strMedia + chMedia;
        if (chMedia < '0' || chMedia > '9') {
            if (chMedia == '.') {
                if (strMedia > 255)
                    return false;
                if (iTotalDots == 0) endIP1 = parseInt(strMedia);
                else if (iTotalDots == 1) endIP2 = parseInt(strMedia);
                else if (iTotalDots == 2) endIP3 = parseInt(strMedia);
                strMedia = '';
                if (iFlag == 1)
                    return false;
                iTotalDots++;
                iFlag = 1;
                continue;
            }
            return false;
        }
        iFlag = 0;
    }
    if (strMedia > 255)
        return false;
    endIP4 = parseInt(strMedia);
    if (iTotalDots != 3)
        return false;
    if (endIP.substring(endIP.length - 1, endIP.length) == '.')
        return false;
    if (parseInt(startIP1) > parseInt(endIP1)) {
        return false;
    } else if ((parseInt(startIP1) == parseInt(endIP1)) && (parseInt(startIP2) > parseInt(endIP2))) {
        return false;
    } else if ((parseInt(startIP1) == parseInt(endIP1)) && (parseInt(startIP2) == parseInt(endIP2)) && (parseInt(startIP3) > parseInt(endIP3))) {
        return false;
    } else if ((parseInt(startIP1) == parseInt(endIP1)) && (parseInt(startIP2) == parseInt(endIP2)) && (parseInt(startIP3) == parseInt(endIP3)) && (parseInt(startIP4) >= parseInt(endIP4))) {
        return false;
    }
    return true;
}

function IsValidMAC(strMAC) {
    if (strMAC.length != 17) return false;
    for (var i = 0; i < strMAC.length; i++) {
        var chTemp = strMAC.substring(i, i + 1);
        if (i == 2 || i == 5 || i == 8 || i == 11 || i == 14) {
            if (chTemp != ':') return false;
        } else {
            if (!((chTemp >= '0' && chTemp <= '9') || (chTemp >= 'a' && chTemp <= 'f') || (chTemp >= 'A' && chTemp <= 'F'))) {
                return false;
            }
        }
    }
    return true;
}		