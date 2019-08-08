/*******************************************************************************
 Copyright (c) 2004-2006, ������ͨ������ҵ��IP����
 All rights reserved.
 Filename ��lib.js
 Abstract ��JavaScript ��ǿ���
 Version����1.1
 Author   ��Liu Guoyuan
 Finished Date ��2004-05-03
 Last Modified ��2004-05-14
 ********************************************************************************/

/***************************************************************
 ȫ��
 ****************************************************************/
function isNull(unknown) {
    switch (typeof (unknown)) {
        case "undefined":
            return true;
        case "string":
            return unknown == "";
        case "number":
            return unknown == 0;
        case "object":
            return unknown == null;
        case "function":
            return false;
        default:
            return unknown ? false : true;
    }
}

function getObject(objName) {
    var obj;
    if (typeof (objName) == "object") obj = objName; else if (objName.search(/\./g) < 0) {
        obj = document.getElementsByName(objName)[0];
        if (obj == null) {
            obj = document.getElementById(objName);
        }
    } else {
        obj = eval(objName);
    }
    return obj;
}

function getParent(el, pTagName) {
    if (el == null) return null; else if (el.nodeType == 1 && el.tagName.toLowerCase() == pTagName.toLowerCase()) return el; else return getParent(el.parentNode, pTagName);
}

//���Դ���
//var z;alert (isNull("") + "  " + isNull(z));

/****************************************************************
 String ���
 ****************************************************************/
String.prototype.realLength = function () {
    var arr = this.match(/[^\x00-\xff]/ig);
    return this.length + (arr == null ? 0 : arr.length);
}
String.prototype.left = function (num, mode) {
    if (!/\d+/.test(num)) return (this);
    var str = this.substr(0, num);
    if (!mode) return str;
    var n = str.Tlength() - str.length;
    num = num - parseInt(n / 2);
    return this.substr(0, num);
}
String.prototype.right = function (num, mode) {
    if (!/\d+/.test(num)) return (this);
    var str = this.substr(this.length - num);
    if (!mode) return str;
    var n = str.Tlength() - str.length;
    num = num - parseInt(n / 2);
    return this.substr(this.length - num);
}
String.prototype.getCount = function (str, mode) {
    return eval("this.match(/(" + str + ")/g" + (mode ? "i" : "") + ").length");
}
String.prototype.trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
String.prototype.ltrim = function () {
    return this.replace(/(^\s*)/g, "");
}
String.prototype.rtrim = function () {
    return this.replace(/(\s*$)/g, "");
}
String.prototype.merge = function (mergeStr) {
    var str = this;
    while (str.indexOf(mergeStr + mergeStr) >= 0) {
        str = str.replace(mergeStr + mergeStr, mergeStr);
    }
    return str;
}//�ϲ��ظ��ַ�
String.prototype.mergeSpace = function () {
    return this.merge(" ");
}//�ϲ��ظ��ո�
String.prototype.toStringByZero = function (count) {
    var str = this;
    while (str.length < count) str = "0" + str;
    return str;
} //��0
String.prototype.isString = function () {
    return (this.search(/[^A-Za-z0-9_]/) == -1)
}
String.prototype.isNumeric = function () {
    if ((parseFloat(this.replace(/,/g, "")) + "").length == this.replace(/,/g, "").length) return true; else return (this.replace(/,/g, "").search(/^(-?\+)?\d+(\.\d+)?$/) != -1);
}
String.prototype.isInteger = function () {
    return this.replace(/,/g, "").search(/^(-?\+)?\d+$/) != -1;
}
String.prototype.isChinese = function () {
    return (this.search(/[^\u4E00-\u9FA5]/) == -1)
}
String.prototype.isEmail = function () {
    return this.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) != -1
}
String.prototype.isIP = function () {
    var SearchStr = /^(\d{1,3})[.](\d{1,3})[.](\d{1,3})[.](\d{1,3})$/;
    if (this.search(SearchStr)) return false; else if (RegExp.$1 > 255 || RegExp.$2 > 255 || RegExp.$3 > 255 || RegExp.$4 > 255) return false;
    return true;
}
String.prototype.isDate = function () {
    if (this.indexOf(" ") >= 0) var reg = /^(\d{1,4})[-\/](\d{1,2})[-\/](\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/; else var reg = /^(\d{1,4})[-\/](\d{1,2})[-\/](\d{1,2})$/;
    var r = this.match(reg);
    if (r == null) return false;
    r[2] = r[2] - 1;
    if (this.indexOf(" ") >= 0) var d = new Date(r[1], r[2], r[3], r[4], r[5], r[6]); else var d = new Date(r[1], r[2], r[3]);
    if (d.getFullYear() != r[1] || d.getMonth() != r[2] || d.getDate() != r[3]) return false;
    if (this.indexOf(" ") >= 0) if (d.getHours() != r[4] || d.getMinutes() != r[5] || d.getSeconds() != r[6]) return false;
    return true;
}
String.prototype.toDate = function () {
    if (this.indexOf(" ") >= 0) var reg = /^(\d{1,4})[-\/](\d{1,2})[-\/](\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/; else var reg = /^(\d{1,4})[-\/](\d{1,2})[-\/](\d{1,2})$/;
    var r = this.match(reg);
    if (r == null) return null;
    r[2] = r[2] - 1;
    if (this.indexOf(" ") >= 0) var d = new Date(r[1], r[2], r[3], r[4], r[5], r[6]); else var d = new Date(r[1], r[2], r[3]);
    if (d.getFullYear() != r[1] || d.getMonth() != r[2] || d.getDate() != r[3]) return null;
    if (this.indexOf(" ") >= 0) if (d.getHours() != r[4] || d.getMinutes() != r[5] || d.getSeconds() != r[6]) return null;
    return d;
}
//���Դ���
//alert ("abcdef:"+"abcdef".tLength() + " �Ұ���abc:"+"�Ұ���abc".tLength())
//alert ("abbbcc    ee".mergeSpace().merge("b").merge("c").merge("e"));
//alert ("abc_123".isString() +"\n"+ "abc+123".isString());
//alert ("1.23".isNumeric() +"\n" + "1.23".isInteger());
//alert ("�л����񹲺͹�".isChinese() + "\n" + "��a�����񹲺͹�".isChinese());
//alert ("a@b.com".isEmail());
//alert ("1.1.1.255".isIP() + "\n" + "1.1.1.256".isIP());
//alert ("2003-2-28 01:03:02".isDate() + "\n" + "2003/2/28".isDate() + "\n" + "2003-2-30".isDate());
//alert ("2002-2-28 12:00:32".toDate() + "\n" + "2002-2-29 12:00:32".toDate());

/****************************************************************
 �������
 ****************************************************************/
Array.prototype.left = function (length) {
    return this.slice(0, length);
}
Array.prototype.mid = function (start, length) {
    return this.slice(start, start + length);
}
Array.prototype.right = function (length) {
    if (length >= this.length) return this;
    return this.slice(this.length - length, this.length);
}
Array.prototype.max = function () {
    var max = this[0];
    for (var i = 0; i < this.length; i++) if (max < this[i]) max = this[i];
    return max;
}
Array.prototype.min = function () {
    var min = this[0];
    for (var i = 0; i < this.length; i++) if (min > this[i]) min = this[i];
    return min;
}
Array.prototype.distinct = function () {
    var tempAry = new Array(this.length);
    var bFind;
    var arySize = 0;
    for (var i = 0; i < this.length; i++) {
        bFind = false;
        for (var j = 0; j < tempAry.length; j++) {
            if (this[i] == tempAry[j]) bFind = true;
        }
        if (!bFind) {
            tempAry[arySize] = this[i];
            arySize++;
        }
    }
    if (arySize < this.length) tempAry = tempAry.left(arySize);
    return tempAry;
}

/****************************************************************
 �������
 ****************************************************************/
Number.prototype.round = function (n) {
    if (!/\d/.test(n)) return this;
    var num = this * Math.pow(10, n);
    return Math.round(num) / Math.pow(10, n);
}
Number.prototype.fact = function () {
    var num = Math.floor(this);
    if (num < 0) return NaN;
    if (num == 0 || num == 1) return 1; else return (num * (num - 1).fact());
}
Number.prototype.fixed = function (num) {
    with (Math) {
        var m = pow(10, Number(num));
        return round(this * m) / m;
    }
}
Number.prototype.toStringByZero = function (count) {
    var str = "" + this + "";
    return str.toStringByZero(count);
} //��0
//���Դ���
//alert((1.237802456).round(3))//��������
//alert((2/3).fixed(3))

/****************************************************************
 �������
 ****************************************************************/
//mode(��λ)������ Y:��,M:��,W:��,(Ĭ��)D:��,H:ʱ,MI:��,S:��
Date.prototype.add = function (num, mode) {
    if (!/[\-]?[\d]+/g.test(num)) return this;
    var base = 60 * 60 * 24 * 1000;
    var result = this.getTime();
    if (mode == null) mode = "D";
    switch (mode.toUpperCase()) {
        case "Y":
            result += base * 365 * num;
            break;
        case "M":
            result += base * 30 * num;
            break;
        case "W":
            result += base * 7 * num;
            break;
        case "D":
            result += base * num;
            break;
        case "H":
            result += base * num / 24;
            break;
        case "MI":
            result += base * num / 24 / 60;
            break;
        case "S":
            result += base * num / 24 / 60;
            break;
    }
    return (new Date(result));
}
Date.prototype.diff = function (cDate, mode) {
    try {
        cDate.getYear();
    } catch (e) {
        return (0);
    }
    var base = 60 * 60 * 24 * 1000;
    var result = this - cDate;
    if (mode == null) mode = "D";
    switch (mode.toUpperCase()) {
        case "Y":
            result /= base * 365;
            break;
        case "M":
            result /= base * 365 / 12;
            break;
        case "W":
            result /= base * 7;
            break;
        case "D":
            result /= base;
            break;
        case "H":
            result /= base / 24;
            break;
        case "MI":
            result /= base / 24 / 60;
            break;
        case "S":
            result /= base / 24 / 60 / 60;
            break;
    }
    return (result);
}
Date.prototype.toCNString = function () {
    var d = this;
    return d.toCNDateString() + " " + d.toCNTimeString()
}
Date.prototype.toCNDateString = function () {
    var d = this;
    return d.getFullYear().toStringByZero(4) + "��" + (d.getMonth() + 1).toStringByZero(2) + "��" + d.getDate().toStringByZero(2)
}
Date.prototype.toCNTimeString = function () {
    var d = this;
    return d.getHours().toStringByZero(2) + "ʱ" + d.getMinutes().toStringByZero(2) + "��" + d.getSeconds().toStringByZero(2) + "��";
}
Date.prototype.toString = function () {
    var d = this;
    return d.toDateString() + " " + d.toTimeString()
}
Date.prototype.toDateString = function () {
    var d = this;
    return d.getFullYear().toStringByZero(4) + "-" + (d.getMonth() + 1).toStringByZero(2) + "-" + d.getDate().toStringByZero(2)
}
Date.prototype.toTimeString = function () {
    var d = this;
    return d.getHours().toStringByZero(2) + ":" + d.getMinutes().toStringByZero(2) + ":" + d.getSeconds().toStringByZero(2);
}

//���Դ���
//alert("2002-1-23 00:00:00".toDate().diff("2002-1-23 00:10:2".toDate(),"MI"))  //ʱ���ֵ,mode(��λ)�� Y:��,M:��,W:��,D:��,H:ʱ,MI:��,S:��
//alert("2002-1-23".toDate().add(1,"w")) //����ʱ��,mode(��λ)�� Y:��,M:��,W:��,D:��,H:ʱ,MI:��,S:��
//alert ("2002-1-23 12:00:32".toDate().toCNString())
//alert ("��ǰ���ڣ�" + new Date().toDateString() + "  ʱ�䣺" + new Date().toTimeString())
//alert (new Date())

