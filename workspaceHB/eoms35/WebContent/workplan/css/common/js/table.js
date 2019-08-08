/********************************************************************************
 Copyright (c) 2004-2006, ������ͨ������ҵ��IP����
 All rights reserved.
 Filename ��table.js
 Abstract �������ؿ��Ʒ���
 Version����2.0
 Author   ��Liu Guoyuan
 Finished Date ��2004-03-30
 Last Modified ��2004-08-12

 2.1
 2004-08-12 �޸�_loadJS�����Ŀ¼���д�дʱ�Ҳ���·��������(����)
 ��װ��pingying.js,bihua.js�ĺ�����Ϊ_loadJS

 2.0
 2004-05-19 �޸� sortTable() ��������ǿ�ݴ�����
 2004-04-28 ��� sortTable(tbl,colIndex,dataType,By) ������򷽷�

 1.11
 2004-04-28 ��� fixTableWidth(srcTable,fixTable) �������������Ϊ��ȡ�
 2004-04-17 ��� switchRow2Col(objTable) ������н���

 1.1
 �޸� getFilterSelect() �߼���1.0Ϊҳ��������ɺ�һ�ζ�ȡȫ���������ݣ��������ݽ϶�ʱ��Ӱ��ҳ�������ٶȣ�
 ��Ϊ���SELECT�б�ʱ��ȡ�����ݣ�����loadFilterListOnDemand����������ָ�������ݣ�

 1.0
 getFilterSelect(tableName,colIndex)  �ӱ��ָ�����еõ������б�Select��
 filterTable(table,colIndex,value)  ���˱����ʾ����   ���������������������������ʾ������
 ********************************************************************************/

//��ȡ������
function _loadJS(src) {
    var script = document.getElementsByTagName("SCRIPT");
    for (var i = 0; i < script.length; i++) {
        var s = script[i].src;
        if (s.indexOf(src) >= 0) return;
        if (s.indexOf("/common/js/table.js") != -1) {
            jsPath = s.replace("table.js", "")
        }
    }
    var oScript = document.createElement("<SCRIPT>");
    oScript.src = jsPath + src;
    script[0].insertAdjacentElement("afterEnd", oScript);
}

_loadJS("lib.js");

/*********************************************
 �ӱ��ָ�����еõ������б�Select��
 �������������������
 *********************************************/
function getFilterSelect(tableName, colIndex) {
    var table = getObject(tableName);
    if (!checkTable(tableName, colIndex)) return "";
    try {
        var colWidth = table.rows[0].cells[colIndex].offsetWidth;
    } catch (E) {
        return "";
    }
    returnStr = "<select style=\"width:" + colWidth + "\" isload=\"false\" " +
        " onclick=\"loadFilterListOnDemand('" + tableName + "'," + colIndex + ");\"" +
        ">";
    returnStr += "<option value=\"\">ȫ��</option>";
    returnStr += "</select>";
    return returnStr;
}

/******************************************
 ��ȡָ����������Select�б�
 ******************************************/
function loadFilterListOnDemand(tableName, colIndex) {
    var oSelect = event.srcElement;
    if (oSelect.isload == true) { //�����Ѷ���
        return;
    } else {
        oSelect.isload = true;
    }
    var table = eval(tableName);
    var tr = table.rows;
    var td;
    var trCount = tr.length;
    var aryAllData = new Array(trCount);
    var arySize = 0;  //����ʵ�ʴ�С
    for (var i = 0; i < trCount; i++) {
        if (tr[i].parentElement.tagName == "THEAD" || tr[i].className.toLowerCase().indexOf("tabletitle") >= 0) continue;
        td = tr[i].cells[colIndex];
        if (td != null) { //��Ԫ������
            if (td.innerText != "") {
                aryAllData[arySize] = td.innerText;
                arySize++;
            }
        }
        ;
    }
    if (arySize < trCount) aryAllData = aryAllData.left(arySize);
    aryAllData = aryAllData.distinct();

    for (var i = 0; i < aryAllData.length; i++) {
        oSelect.add(new Option(aryAllData[i], aryAllData[i]));
    }
    oSelect.onchange = function () {
        /*** �����������б���Ϊѡ��ȫ�� ***/
        var oAllFilterSelect = document.getElementsByTagName("SELECT");
        for (var i = 0; i < oAllFilterSelect.length; i++) {
            if (oAllFilterSelect[i].isload && oAllFilterSelect[i].sourceIndex != oSelect.sourceIndex) {
                oAllFilterSelect[i].options[0].selected = true;
            }
        }
        /*** ����ָ���� ***/
        filterTable(tableName, colIndex, this.value);
    }
}


/*********************************************
 ���˱����ʾ����
 ��������������������������ʾ������
 *********************************************/
function filterTable(tableName, colIndex, showValue) {
    var table = getObject(tableName);
    if (!checkTable(tableName, colIndex)) return;
    var tr = table.rows;
    var trCount = tr.length;
    var td;
    var showFlag;
    for (var i = 0; i < trCount; i++) {
        if (tr[i].parentElement.tagName == "THEAD" || tr[i].className.toLowerCase().indexOf("tabletitle") >= 0) continue;
        td = tr[i].cells[colIndex];
        if (td == null) { //��Ԫ������
            showFlag = false;
        } else {
            showFlag = (showValue == "" || showValue == "nothing" || td.innerText == showValue);
        }
        if (showFlag) {//��ʾ
            tr[i].style.display = "block";
        } else {
            tr[i].style.display = "none";
        }
    }
    //������ʽ�������ʹ�ù�page.js�е�setTableStyle���������˺��ʹ��Ԫ����ɫ���ң���Ҫ���µ��ø÷���
    if (table.isSetStyle) {
        setTableStyle(table, table.needMouseStyle);
    }
}

/*********************************************
 ������н���
 ������������
 *********************************************/
function switchRow2Col(objTab) {
    var tabArray = new Array();
    for (var i = 0; i < objTab.rows[0].cells.length; i++) {
        var tmpArray = new Array()
        for (var j = 0; j < objTab.rows.length; j++) {
            tmpArray[tmpArray.length] = objTab.rows[j].cells[i].outerHTML
        }
        tabArray[tabArray.length] = tmpArray;
    }
    var str = "";
    for (var i = 0; i < tabArray.length; i++) {
        str += "<tr>" + tabArray[i].join("") + "</tr>"
    }
    str = "<table width=\"" + objTab.width +
        "\" cellPadding=\"" + objTab.cellPadding +
        "\" cellSpacing=\"" + objTab.cellSpacing +
        "\" id=\"" + objTab.id +
        "\">" + str + "</table>";
    objTab.outerHTML = str
}

/*********************************************
 �������������Ϊ��ȡ�
 ������Դ��������Ҫ�޸Ŀ�ȵı�����
 *********************************************/
function fixTableWidth(srcTable, fixTable) {
    var srcCellCount = srcTable.rows[0].cells.length;
    if (srcCellCount != fixTable.rows[0].cells.length) return;
    for (var i = 0; i < srcCellCount; i++) {
        var srcWidth = srcTable.rows[0].cells[i].offsetWidth;
        var fixTD = fixTable.rows[0].cells[i];
        if (fixTD.firstChild.tagName == "SELECT") {
            fixTD.firstChild.style.width = srcWidth;
        }
        fixTD.style.width = srcWidth;
    }
}

/*********************************************
 ������
 ������
 tbl       - TABLE, TBODY, THEAD or TFOOT Ԫ��
 colIndex  - ������,��Ϊ�����������
 dataType  - ��������: �գ��Զ��жϣ���CHAR(�ַ�),NUM(����),DATE(����),CNPY(����ƴ��),CNBH(���ıʻ�) ,
 By        - Ĭ�������򣬿գ�ASC:reverse�� DESC:descending
 *********************************************/
function sortTable(tbl, colIndex, dataType, By) {
    if (!checkTable(tbl, colIndex)) return;

    //������������ñ���������Ϊ��tbody
    if (tbl.tagName == "TABLE") {
        tbl = tbl.tBodies[0];
    }
    table = tbl.parentNode; //������

    var obj = event.srcElement;    //����Ķ������ڱ���������
    var objTD;                     //�����еĵ�һ��,�������������ͷ

    //����������
    if (colIndex == null) {//û��ָ����������Ϊ��ǰ�����������
        if (obj.tagName != "TD") obj = objTD.parentNode;
        if (obj.tagName == "TD") {
            colIndex = obj.cellIndex;
            objTD = obj;
        } else {
            return;
        }
    } else if (colIndex >= 0) {//ָ����������
        var objTD = table.rows[0].cells[colIndex];
    } else {
        alert("������Ҫ������У�");
        return;
    }

    //�������������õ���Ķ��󱣴浱ǰ��������
    if (obj.sortTableBy == null) {//û�����򣬱���ָ��������
        obj.sortTableBy = By == null ? "ASC" : By;
    } else {
        obj.sortTableBy = obj.sortTableBy == "ASC" ? "DESC" : "ASC";
    }
    By = obj.sortTableBy;

    //���������ͷ
    if (objTD.tagName == "TD" && objTD.parentNode.parentNode.tagName == "THEAD") { //����Ķ���Ϊ��Ԫ���Լ�Ϊ������
        for (var i = 0; i < objTD.parentNode.cells.length; i++) {
            objTD.parentNode.cells[i].innerHTML = objTD.parentNode.cells[i].innerHTML.replace(/��|��/, "");
        }
        objTD.innerHTML += obj.sortTableBy == "DESC" ? "��" : "��";
    }

    if (!checkTable(tbl, colIndex)) return;
    //�Զ��ж���������
    if (dataType == null) {
        checkText = tbl.rows[0].cells[colIndex].innerText;
        if (checkText == "" || checkText == null) {
            dataType = "CHAR";
        } else if (checkText.isDate()) {
            dataType = "DATE";
        } else if (checkText.isChinese()) {
            dataType = "CNPY";
        } else if (checkText.isNumeric()) {
            dataType = "NUM";
        } else {
            dataType = "CHAR";
        }
    }
    if (dataType.toUpperCase() == "CNPY")
        _loadJS("pingying.js");
    else if (dataType.toUpperCase() == "CNBH")
        _loadJS("bihua.js");
    //�˴������������ʽ����
    var extConvert = function (Data, Type) {
        switch (Type.toLowerCase()) {
            case "NUM2":  //�Զ���������
                return parseFloat(Data.replace(/,/g, ""));
                break;
            default:
                return Data
        }
    }
    var convert = function (Data, Type) {
        var Rst = Data;
        var cn_code = function (idx) {
            if (idx < 100) {
                return String.fromCharCode(0, idx);
            } else {
                var s = idx.toString();
                return String.fromCharCode(parseInt(s.substring(0, s.length - 2)), idx % 100);
            }
        }
        switch (Type.toUpperCase()) {
            case "CHAR":
                Rst = Data;
                break;
            case "NUM":
                Rst = parseFloat(Data.replace(/,/g, ""));
                if (isNaN(Rst)) Rst = Data;
                break;
            case "DATE":
                Rst = Date.parse(Data.replace(/\-/g, '/'));
                if (isNaN(Rst)) Rst = Data;
                break;
            case "CNPY":
                if (typeof (strChinesePingYing) == "undefined") {
                    return Data;
                }
                Rst = "";
                for (var i = 0; i < Data.length; i++) {
                    var idx = strChinesePingYing.indexOf(Data.charAt(i));
                    Rst += idx != -1 ? cn_code(idx) : Data.charAt(i);
                }
                break;
            case "CNBH":
                if (typeof (strChineseBiHua) == "undefined") {
                    return Data;
                }
                Rst = "";
                for (var i = 0; i < Data.length; i++) {
                    var idx = strChineseBiHua.indexOf(Data.charAt(i));
                    Rst += idx != -1 ? cn_code(idx) : Data.charAt(i);
                }
                break;
            default :
                Rst = extConvert(Data, Type);
                if (Rst == null) {
                    Rst = Data
                }
                ;
        }
        return Rst;
    }
    var ByAsc = (By == "ASC");
    var arySort = [];
    for (var i = 0; i < tbl.rows.length; i++) {
        var Data = (tbl.rows[i].cells[colIndex]) ? (tbl.rows[i].cells[colIndex].innerText.toLowerCase()) : null;
        Data = convert(Data, dataType);
        arySort[i] = new Array(Data, tbl.rows[i]);
    }
    arySort.sort(function () {
        var a = arguments;
        return ByAsc ? (a[0][0] > a[1][0] ? 1 : (a[0][0] < a[1][0] ? -1 : 0)) : (a[0][0] < a[1][0] ? 1 : (a[0][0] > a[1][0] ? -1 : 0));
    })
    for (i = 0; i < arySort.length; i++) {
        tbl.appendChild(arySort[i][1]);
    }
    //������ʽ�������ʹ�ù�page.js�е�setTableStyle������������ʹ��Ԫ����ɫ���ң���Ҫ���µ��ø÷���
    if (table.isSetStyle) {
        setTableStyle(table, table.needMouseStyle);
    }
}

/*********************************************
 ��֤��������������Ƿ���ȷ
 ������������������
 *********************************************/
function checkTable(table, colIndex) {
    var table = getObject(table);
    if (table == null) return false;
    var tr = table.rows;
    if (tr.length < 1 || tr[0].cells == null) {
        return false;
    } else if (isNull(colIndex)) {
        if (tr[0].cells.length < colIndex) return false;
    }
    return true;
}