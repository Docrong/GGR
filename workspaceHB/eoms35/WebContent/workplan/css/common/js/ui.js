/********************************************************************************
 Copyright (c) 2004-2005,������ͨ������ҵ��IP����
 All rights reserved.
 Filename ��ui.js
 Abstract ��WEB������ز���������
 Version����1.1
 Author   ��Liu Guoyuan
 Finished Date ��2004-04-08
 Last Modified ��2004-08-12

 ���¼�¼��
 2004-04-22 v1.1
 setLabelAction() �޸ģ����ñ�ǩ(LABEL)Ĭ�϶���������ǩ��ñ�ǩ��һ���Ӷ���󶨣�ʹ�÷���������
 ���������������� �������˷���������body.onload�¼�������ִ�У��������ⲿָ����LABEL�е�һ���Ӷ�����¼���ʧЧ
 ��� getItemText() ����,  ��ȡָ��Ԫ���ı�

 2004-04-20 v1.1
 �޸� getItemValue() �������isChar(�Ƿ��ַ�)������Ϊtrue��valueǰ�����'value'

 2004-04-17 v1.1
 ��� getParent() ����

 2004-04-16 v1.1
 ��� setSelectItem() ����,  ���ö���(�˵�����ѡ�򡢵�ѡ��)��Ĭ��ѡ��״̬��
 ��� getItemValue() ����,   ��ȡָ������(�˵�����ѡ�򡢵�ѡ��)ֵ
 ��� setComboBox() ����,    ���ı�������ѡ��˵���ΪComboBox�˵����ɱ༭�˵���

 2004-04-08 v1.0 ����
 addItem()
 removeItem()
 addAllItem()
 removeAllItem()
 selectAllItem()
 selectAllCheckBox(() ����checkboxѡ��״̬
 setCheckAllAction()  ��ȫѡ����Ҫ��صĸ�ѡ��(�˵�)
 setLabelAction()     ���ñ�ǩ(LABEL)Ĭ�϶����������ǩʱ���øñ�ǩ��һ���Ӷ����click�¼�

 ********************************************************************************/

//��ȡ������
function _loadJS(src) {
    var script = document.getElementsByTagName("SCRIPT");
    for (var i = 0; i < script.length; i++) {
        var s = script[i].src;
        if (s.indexOf(src) >= 0) return;
        if (s.indexOf("/common/js/ui.js") != -1) {
            jsPath = s.replace("ui.js", "")
        }
    }
    var oScript = document.createElement("<SCRIPT>");
    oScript.src = jsPath + src;
    script[0].insertAdjacentElement("afterEnd", oScript);
}

_loadJS("lib.js");

/*****************************
 ���Ӳ˵������(Դselect���ƣ�Ŀ��select����)
 ******************************/
function addItem(source, dest) {
    source = getObject(source);
    dest = getObject(dest);
    var i = 0;
    var soulen = source.options.length - 1;
    var j = 0;
    var deslen = dest.options.length - 1;

    if (deslen == 0) {
        if (dest.options[0].value == "nothing") {
            dest.options[0] = null;
            deslen -= 1;
        }
    }
    while (i <= soulen) {
        bflag = true;
        addvalue = source.options[i].value;
        if (source.options[i].selected && addvalue != "") {
            for (j = 0; j <= deslen; j++) {
                if (dest.options[j].value == addvalue) {
                    bflag = false;
                    break;
                }
            }
            if (bflag) {
                var sText = source.options[i].text + "";
                sText = sText.replace(" ��", "");
                var test1 = new Option(sText, addvalue);
                dest.options[++deslen] = test1;
            }
        }
        i++;
    }
}

//ɾ��ָ��ѡ��˵�����ѡ��ѡ��
function removeItem(dest) {
    dest = getObject(dest);
    var j = 0;
    var deslen = dest.options.length - 1;
    while (j <= deslen) {
        if (dest.options[j].selected) {
            dest.options[j] = null;
            j--;
            deslen--;
        }
        j++;
    }
}

//���ָ��ѡ��˵���ȫ��ѡ�Ŀ��˵�
function addAllItem(source, dest) {
    source = getObject(source);
    dest = getObject(dest);
    var i = 0;
    var soulen = source.options.length - 1;
    var j = 0;
    var deslen = dest.options.length - 1;
    if (deslen == 0) {
        if (dest.options[0].value == "nothing") {
            dest.options[0] = null;
            deslen -= 1;
        }
    }
    for (var i = 0; i <= soulen; i++) {
        bflag = true;
        addvalue = source.options[i].value;
        if (addvalue == "-1" || addvalue == "") {
            continue;
        }

        for (var j = 0; j <= deslen; j++) {
            if (dest.options[j].value == addvalue) {
                bflag = false;
                break;
            }
        }

        if (bflag) {
            var sText = source.options[i].text + "";
            sText = sText.replace(" ��", "");
            var test1 = new Option(sText, addvalue);
            dest.options[++deslen] = test1;
        }
    }
}

//ɾ��ѡ��˵��е�ȫ��ѡ��
function removeAllItem(dest) {
    delAllItem(dest);
}

function delAllItem(dest) {
    dest = getObject(dest);
    while (dest.options.length > 0) dest.options[0] = null;
}

//���ò˵���Ŀ��ѡ��״̬
function selectAllItem(objName, flag) {
    var obj = getObject(objName);
    count = obj.options.length
    if (count == 0) return;
    if (!obj.multiple) {//��ѡ
        obj.options[0].selected = flag;
        return;
    }
    for (i = 0; i < count; i++) {
        obj.options[i].selected = flag;
    }
}

/**********************************************
 �������ܣ�ѡ�в˵�(��ѡ�򡢵�ѡ��)��¼
 ����˵����
 objName:
 �˵�����,����ȫ��,�磺"document.form1.selMenu",Ҳ������д����"selMenu"
 strValue:�����ֻ��ַ�
 ֵ��������˵�ֵƥ�䣬������һ���������ݿ��У�����˵�Ϊ��ѡ������ֵ֮���á�,��(����)�ָ�����"24,33,25,23"
 **********************************************/
function setSelectItem(objName, strValue) {
    var numargs = arguments.length; //���صĲ�������
    var sourceObject;               //����ƥ��Ĳ˵�����
    var isFinded = false;           //�Ƿ��ҵ�ƥ������
    strValue = "" + strValue + "";
    sourceObject = getObject(objName);

    //��ѡ��
    if (sourceObject.type == "radio") {
        var aoRadio = document.getElementsByName(objName);
        for (var i = 0; i < aoRadio.length; i++) {
            if (aoRadio[i].value == strValue) {
                aoRadio[i].checked = true;
                break;
            }
        }
    }
    //��ѡ��
    else if (sourceObject.type == "checkbox") {
        var aoCheckBox = document.getElementsByName(objName);
        var asValue = strValue.split(",");
        for (var i = 0; i < aoCheckBox.length; i++) {
            for (var j = 0; j < asValue.length; j++) {
                if (aoCheckBox[i].value == asValue[j]) {
                    aoCheckBox[i].checked = true;
                    break;
                }
            }
        }
    }
    //�˵�
    else {
        if ((strValue) == "") {
            sourceObject.options[0].selected = true;
            return;
        }
        for (i = 0; i < sourceObject.options.length; i++) {
            sourceObject.options[i].selected = false;  //���ԭ��ѡ��
        }
        var aryID = strValue.split(",");
        if (aryID.length == 0) return;
        for (var j = 0; j < aryID.length; j++) {
            for (var i = 0; i < sourceObject.options.length; i++) {
                if (sourceObject.options[i].value == aryID[j]) {
                    sourceObject.options[i].selected = true;
                    isFinded = true;
                    break;
                }
            }
        }
        if (!isFinded) sourceObject.options[0].selected = true;
        ;
    }
}

/*****************************
 ��ָ�������л�ȡֵ
 ������
 ��objName: ��������һ��Ϊ��ѡ�򡢵�ѡ��Ͳ˵�
 isChar:  �Ƿ��ַ���Ϊtrue��valueǰ�����''��
 ���أ�
 ѡ��ֵ�б�������11,24,12,56 �� '11','24','12','56'
 *****************************/
function getItemValue(objName, isChar) {
    var sValue = "";
    var pos = "";
    if (isChar != null && isChar == true) {
        pos = "'";
    }
    var aObj = document.getElementsByName(objName);
    //��ѡ��
    if (aObj[0].type == "radio") {
        for (var i = 0; i < aObj.length; i++) {
            if (aObj[i].checked) {
                sValue += pos + aObj[i].value + pos + ",";
            }
        }
    }
    //��ѡ��
    else if (aObj[0].type == "checkbox") {
        for (var i = 0; i < aObj.length; i++) {
            if (aObj[i].checked) {
                sValue += pos + aObj[i].value + pos + ",";
            }
        }
    }
    //�˵�
    else {
        var count = aObj[0].options.length;
        for (var i = 0; i < count; i++) {
            if (aObj[0].options[i].value != "nothing" && aObj[0].options[i].value != "") {
                sValue += pos + aObj[0].options[i].value.replace("\\", "\\\\") + pos + ",";
            }
        }
    }
    if (sValue != "") sValue = sValue.substring(0, sValue.length - 1); //ȥ�����һ��","
    return sValue;
}

/*****************************
 ��ָ�������л�ȡ�ı�
 ������
 ��objName: ��������һ��Ϊ��ѡ�򡢵�ѡ��Ͳ˵�
 ���أ�
 ѡ���ı��б�����  ����,�Ϻ�,���
 *****************************/
function getItemText(objName) {
    var sValue = "";
    var aObj = document.getElementsByName(objName);
    //��ѡ��
    if (aObj[0].type == "radio") {
        for (var i = 0; i < aObj.length; i++) {
            if (aObj[i].checked) {
                sValue += aObj[i].parentNode.innerText + ",";
            }
        }
    }
    //��ѡ��
    else if (aObj[0].type == "checkbox") {
        for (var i = 0; i < aObj.length; i++) {
            if (aObj[i].checked) {
                sValue += aObj[i].parentNode.innerText + ",";
            }
        }
    }
    //�˵�
    else {
        var count = aObj[0].options.length;
        for (var i = 0; i < count; i++) {
            if (aObj[0].options[i].value != "nothing" && aObj[0].options[i].value != "") {
                sValue += aObj[0].options[i].text.replace(" ��", "") + ",";
            }
        }
    }
    if (sValue != "") sValue = sValue.substring(0, sValue.length - 1); //ȥ�����һ��","
    return sValue;
}

/*****************************
 ���ø�ѡ��״̬
 ������
 objName: ��ѡ������
 flag: ����״̬ true|false
 *****************************/
function selectAllCheckBox(objName, flag) {
    var aoCheckBox = document.getElementsByName(objName);
    for (var i = 0; i < aoCheckBox.length; i++) {
        aoCheckBox[i].checked = flag;
    }
}

/******************************
 ��ȫѡ����Ҫ��صĸ�ѡ��(�˵�)
 ���ܣ�
 ����ȫѡ��ѡ��ʱ������صĸ�ѡ�򣨲˵�ѡ�ȫ��ѡ�У�������Ϊδѡ��״̬��
 ����֮��Ȼ��
 ������
 chkAllObj: ����ȫѡ�ĸ�ѡ������
 chkOtherObj: ��صĸ�ѡ��(�˵�)����
 ******************************/
function setCheckAllAction(chkAllObjName, chkOtherObjName) {
    var aoCheckBox = document.getElementsByName(chkOtherObjName);
    for (var i = 0; i < aoCheckBox.length; i++) {
        /*** ��������Ǹ�ѡ�� ***/
        if (aoCheckBox[i].type == "checkbox") {
            if (i == 0) {//ȫѡ���¼�����
                document.all[chkAllObjName].onclick = function () {
                    selectAllCheckBox(chkOtherObjName, this.checked);
                }
            }
            //��صĸ�ѡ�����Ըı��¼�
            aoCheckBox[i].onpropertychange = function () {
                if (!this.checked) {
                    document.all[chkAllObjName].checked = false;
                } else {
                    //���Ҽ�صĸ�ѡ���Ƿ�ȫ��ѡ�У���ȫѡ��״̬������Ӧ״̬
                    var aoCheckBox = document.getElementsByName(chkOtherObjName);
                    var bAllChk = true;
                    for (var i = 0; i < aoCheckBox.length; i++) {
                        if (!aoCheckBox[i].checked) {
                            bAllChk = false;
                            break;
                        }
                    }
                    document.all[chkAllObjName].checked = bAllChk;
                }
            }
        }// if checkbox
        /*** ���������ѡ��˵� ***/
        else {
            if (i == 0) {//ȫѡ���¼�����
                document.all[chkAllObjName].onclick = function () {
                    selectAllItem(chkOtherObjName, this.checked);
                }
            }
            //��ص�ѡ��˵��¼�����
            document.all[chkOtherObjName].onchange = function () {
                if (this.multiple) {//�����ѡ������Ŀȫ��ѡ��ʱ��ȫѡ����Ϊѡ��״̬
                    var count = this.options.length;
                    var selCount = 0;
                    for (var i = 0; i < count; i++) {
                        if (this.options[i].selected) selCount++;
                    }
                    document.all[chkAllObjName].checked = selCount == count;
                } else {
                    document.all[chkAllObjName].checked = this.value == "" || this.value == "nothing";
                }
            }
        }
    }
}

/******************************
 ���ñ�ǩ(LABEL)Ĭ�϶���������ǩ��ñ�ǩ��һ���Ӷ����
 ���˷���������body.onload�¼�������ִ�У��������ⲿָ����LABEL�е�һ���Ӷ�����¼���ʧЧ
 ******************************/
function setLabelAction() {
    try {
        var aoLabel = document.getElementsByTagName("LABEL");
        var tmpStr;
        var objID;
        for (var i = 0; i < aoLabel.length; i++) {
            if (!aoLabel[i].htmlFor) {//û�а�Ԫ��
                if (aoLabel[i].children[0].id) {//��һ����Ԫ����ID������for
                    aoLabel[i].htmlFor = aoLabel[i].children[0].id;
                } else {//��һ����Ԫ��û��ID�����ID
                    objID = aoLabel[i].children[0].name + i;
                    tmpStr = aoLabel[i].children[0].outerHTML;
                    tmpStr = tmpStr.substring(0, tmpStr.length - 1);
                    tmpStr = tmpStr + " id=\"" + objID + "\">";
                    aoLabel[i].children[0].outerHTML = tmpStr;
                    aoLabel[i].htmlFor = objID;
                }
            }
            if (!aoLabel[i].title) { //û����ʾ����
                aoLabel[i].title = aoLabel[i].innerText;
            }
        }
    } catch (e) {
    }
}

/***************************
 ���ı�������ѡ��˵���ΪComboBox�˵����ɱ༭�˵���
 ������txtObj���ı���������ƣ�selectObj��ѡ��˵�����
 ***************************/
function setComboBox(txtObj, selectObj) {
    txtObj = getObject(txtObj);
    selectObj = getObject(selectObj);

    var downList = document.createElement("SPAN")
    with (downList) {
        innerHTML = "<font face=webdings>6</font>";
        style.cursor = "hand";
        style.textAlign = "center";
        style.color = "#4D6185";
        style.backgroundColor = "#D5E0F6";
        style.lineHeight = "14px";
        style.width = "16px";
        style.border = "1px solid #7BAAD6";
        onclick = function () {
            txtObj.style.display = 'none';
            style.display = "none";
            selectObj.style.display = '';
            selectObj.focus();
        }
    }
    txtObj.insertAdjacentElement("afterEnd", downList);
    txtObj.onkeydown = function () {
        if (event.keyCode == 40 || event.keyCode == 38) {
            downList.click();
            return false;
        }
    }
    selectObj.style.display = "none";
    if (selectObj.clientWidth < (txtObj.offsetWidth + 16)) {
        selectObj.style.width = txtObj.offsetWidth + 16;
    }
    selectObj.onclick = function () {
        txtObj.value = selectObj.value;
    }
    selectObj.onblur = function () {
        txtObj.style.display = "";
        txtObj.focus();
        downList.style.display = "";
        selectObj.style.display = "none";
    }
    selectObj.onkeydown = function () {
        if (event.keyCode == 27) {
            selectObj.blur();
            return false;
        } else if (event.keyCode == 13) {
            txtObj.value = selectObj.value;
            selectObj.blur();
            return false;
        }
    }
}