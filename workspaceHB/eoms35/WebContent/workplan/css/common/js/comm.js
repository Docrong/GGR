/********************************************************************************
 Copyright (c) 2002,������ͨ������ҵ��IP����
 All rights reserved.
 Filename ��comm.js
 Abstract ������JavaScript������
 Version����2.0
 Author   ��Liu Guoyuan
 Finished Date ��2002-08-30
 Last Modified ��2004-08-12

 �޸ļ�¼��
 2004-04-25
 ��� showMsg(msg,hidePage) ���� ,��ҳ����ʾ��ʾ��Ϣ
 ********************************************************************************/
var sepi = 0;
var box = 0;

function tableTR() {
    sepi = 1 - sepi;
    if (sepi == 1) {
        S = "<tr bgcolor='#EDF1F8' align='center' class=\"TableBodyOut\" onMouseOver=\"this.className='TableBodyOver'\" onMouseOut=\"this.className='TableBodyOut';\" >";
    } else {
        S = "<tr bgcolor='#D5E0F7' align='center' class=\"TableBodyOut\" onMouseOver=\"this.className='TableBodyOver'\" onMouseOut=\"this.className='TableBodyOut';\">";
    }
    document.write(S);
}

function tableTD(c) {
    box = 1 - box;
    if (box == 1) {
        C = "<input name=\"mid\" type=\"checkbox\" style='backcolor:#EDF1F8;border: 1.0px #9CB9F5;' value='" + c + "' onclick=\"TableBgLock(this)\">";
    } else {
        C = "<input name=\"mid\" type=\"checkbox\" style='backcolor:#D5E0F7;border: 1.0px #9CB9F5;' value='" + c + "' onclick=\"TableBgLock(this)\">";
    }
    document.write(C);
}

function TableBgLock(Obj) {
    if (!Obj.checked) {
        Obj.style.backgroundColor = '';
    } else {
        Obj.style.backgroundColor = '#ABCBE2';
    }
}

function SelectItem(strValue, ObjName, selMode) {
    /*�������ܣ�ѡ�в˵�(��ѡ�򡢵�ѡ��)��¼
      ����˵����
      strValue:�����ֻ��ַ�
         ֵ��������˵�ֵƥ�䣬������һ���������ݿ��У�����˵�Ϊ��ѡ������ֵ֮���á�,��(����)�ָ�����"24,33,25,23"
      ObjName:
         �˵�����,����ȫ��,�磺"document.form1.selMenu",Ҳ������д����"selMenu"
      selMode:
         ѡ��ģʽ��Ĭ��0��ѡ��1��ѡ��
    */
    var numargs = arguments.length; //���صĲ�������
    var sourceObject;               //����ƥ��Ĳ˵�����
    var isFinded = false;           //�Ƿ��ҵ�ƥ������

    if (numargs > 1) {
        if (ObjName.search(/\./g) < 0)         //�������Ʋ�����"."
            sourceObject = document.getElementsByName(ObjName)[0];
        else
            sourceObject = eval(ObjName);
    } else {
        sourceObject = eval("document.all.selectTree"); //����ȱʡ����
    }

    if (sourceObject.type == "radio") {//��ѡ��
        var aoRadio = document.getElementsByName(ObjName);
        for (var i = 0; i < aoRadio.length; i++) {
            if (aoRadio[i].value == strValue) aoRadio[i].checked = true;
        }
    } else {//�˵�
        if (("" + strValue) == "") {
            sourceObject.options[0].selected = true;
        } else {
            if (selMode == 1) { //��ѡ�˵�
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
            } else {//��ѡ�˵�
                var count = sourceObject.options.length;
                for (var i = 0; i < count; i++) {
                    if (sourceObject.options[i].value == ("" + strValue)) {
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
}

function OpenWin(theURL, winName, features) {
    newWindow = window.open(theURL, winName, features);
    newWindow.focus();
}

function OpenCenterWindow(theURL, winName, width, height, features) {
//����Զ���еĴ���
    var window_width = width;
    var window_height = height;
    var newfeatures = features;
    var window_top = (screen.height - window_height) / 2;
    var window_left = (screen.width - window_width) / 2;
    newWindow = window.open('' + theURL + '', '' + winName + '', 'width=' + window_width + ',height=' + window_height + ',top=' + window_top + ',left=' + window_left + ',' + features + '');
    newWindow.focus();
}

function OpenActionWindow(theURL, vArguments, width, height, features) {
//����Զ���е�ģʽ����
    var window_width = width;
    var window_height = height;
    var newfeatures = features;
    var return_value = window.showModalDialog("" + theURL + "", "" + vArguments + "", "dialogWidth:" + window_width + "px;dialogHeight:" + window_height + "px;scroll:no;status:no;help:no");
    return return_value;
}

//ȫ��ѡ�ж�ѡ��ť
var checkAllFlag = 0;

function checkAll(obj) {
    if (checkAllFlag == 0) {
        if (obj.length > 1) {
            for (var i = 0; i < obj.length; i++)
                obj[i].checked = true;
            document.all.selectall.checked = true;
        } else {
            document.all.selectall.checked = true;
            obj.checked = true;
        }
        checkAllFlag = 1;
    } else {
        if (obj.length > 1) {
            for (var i = 0; i < obj.length; i++)
                obj[i].checked = false;
            document.all.selectall.checked = false;
        } else {
            document.all.selectall.checked = false;
            obj.checked = false;
        }
        checkAllFlag = 0;
    }
}

function SwitchAll(form) {
//������ѡ��ť
    var form = eval(form);
    count = parseInt(form.elements.length);
    if (count == 0) return false;
    if (count == 1) {
        form.mid.checked = !form.mid.checked;
        return true;
    }
    for (var i = 0; i < count; i++) {
        var e = form.mid[i];
        e.checked = !e.checked;
    }
    return true;
}

function modify(form) {
//�޸�ѡ�м�¼
    var form = eval(form);
    var e, count, eTest
    var selectFlag = "FALSE";
    var selectCount = 0;
    var firstSelected;
    count = parseInt(form.elements.length);
    if (count == 0) return;
    if (count == 1) {
        //if (form.mid.checked)
        //{
        selectFlag = "TRUE";
        e = form.mid
        //}
    } else {
        for (var i = 0; i < count; i++) {
            var eTest = form.mid[i];
            if (eTest.checked) {
                e = eTest;
                selectFlag = "TRUE";
                selectCount++;
                if (selectCount > 1) {
                    alert("���ѡ��һ����¼��");
                    return;
                }
            }
        }
    }
    if (selectFlag == "FALSE") {
        alert("��ѡ���¼��");
        return;
    } else {
        URL = GetFileName("modify") + "?id=" + e.value;
        window.location = URL;
    }
}

function del(object) {
//ɾ������ѡ�м�¼
    var form = eval(object);
    var selectFlag = "FALSE"
    count = parseInt(form.elements.length);
    if (count == 0) return;
    if (count == 1) {
        if (form.mid.checked) selectFlag = "TRUE";
    } else {
        for (var i = 0; i < count; i++) {
            var e = form.mid[i];
            if (e.checked) {
                selectFlag = "TRUE";
                break;
            }
        }
    }
    if (selectFlag == "FALSE") {
        alert("��ѡ���¼��");
        return;
    }
    if (confirm("��ȷ��Ҫɾ��ѡ���ļ�¼��")) {
        var form = eval(form);
        URL = GetFileName("delete")
        form.action = URL;
        form.submit();
    }
}

function del2(object) {
//ɾ������ѡ�м�¼
    var form = eval(object);
    var selectFlag = "FALSE"
    count = parseInt(form.elements.length);
    if (count == 0) return;
    if (count == 1) {
        if (form.mid.checked) selectFlag = "TRUE";
    } else {
        for (var i = 0; i < count; i++) {
            var e = form.mid[i];
            if (e.checked) {
                selectFlag = "TRUE";
                break;
            }
        }
    }
    if (selectFlag == "FALSE") {
        alert("��ѡ���¼��");
        return;
    }
    if (confirm("��ȷ��Ҫ�����ջظ��û������ڳ��ĸ�Ȩ����")) {
        var form = eval(form);
        URL = "user_privs_delete2.jsp" //GetFileName("delete")
        form.action = URL;
        form.submit();
    }
}

function del3(object) {
//ɾ������ѡ�м�¼
    var form = eval(object);
    var selectFlag = "FALSE"
    count = parseInt(form.elements.length);
    if (count == 0) return;
    if (count == 1) {
        if (form.mid.checked) selectFlag = "TRUE";
    } else {
        for (var i = 0; i < count; i++) {
            var e = form.mid[i];
            if (e.checked) {
                selectFlag = "TRUE";
                break;
            }
        }
    }
    if (selectFlag == "FALSE") {
        alert("��ѡ���¼��");
        return;
    }
    if (confirm("��ȷ��Ҫ����ɾ�����û����д����Ķ�����")) {
        var form = eval(form);
        URL = "user_delete3.jsp" //GetFileName("delete")
        form.action = URL;
        form.submit();
    }
}


function GetFileName(actionFileName) {
    //loadJS("page.js");
    //return getFileName(action);
    var arrayURL = new Array();
    var tempURL = new String();
    var sURL = new String();
    tempURL = window.location + "";
    arrayURL = tempURL.split("/");
    tempURL = arrayURL[arrayURL.length - 1];
    var fileExtName = tempURL.substr(tempURL.indexOf("."), 4); //�ļ���չ��
    tempURL = tempURL.substr(0, tempURL.indexOf(fileExtName));
    if (tempURL.indexOf("_manage") >= 0) {
        sURL = tempURL.substr(0, tempURL.indexOf("_manage"));
    } else {
        if (tempURL.indexOf("_init") >= 0) {
            sURL = tempURL.substr(0, tempURL.indexOf("_init"));
        } else {
            if (tempURL.indexOf("_list") >= 0) {
                sURL = tempURL.substr(0, tempURL.indexOf("_list"));
            } else {
                sURL = tempURL;
            }
        }
    }
    sURL += "_";
    switch (actionFileName) {
        case "add":
            sURL += "add" + fileExtName;
            break;
        case "modify":
            sURL += "modify" + fileExtName;
            break;
        case "delete":
            sURL += "delete" + fileExtName;
            break;
        default :
            sURL += actionFileName + fileExtName;
    }
    return sURL;
}

function ClearInput(object, text) {
//���������
    var object = eval(object);
    var sValue = object.value;
    if (object.value == text) object.value = ""
}

function RestoreInput(object, text) {
//�ָ�������
    var object = eval(object);
    var text = text;
    if (object.value == "") object.value = text;
}

//�������Ͳ˵�
//���ݸ�ʽ��ID��CLASS��NAME;  ��ӦIDֵ����������
//�� 1,1,ȫ��;2,2,����;
function BuilderSelectTree(sourceObject, targetObject) {
    var numargs = arguments.length; //���صĲ�������
    if (numargs > 0) {
        var sObject = eval(sourceObject);
        var tObject = eval(targetObject);
    } else {
        var sObject = eval("document.form1.treeCode");       //Դ����Դ�������ڶ���,Դ���ݸ�ʽΪ "id,class,name;"����"1000,1,ȫ��;1001,2,����;"
        var tObject = eval("document.form1.selectTree");    //Ŀ�����
    }
    var name, count;
    var treeCode = new Array();
    treeCode = sObject.value.split(";");
    count = treeCode.length - 1;
    j = 0;
    for (i = 0; i < count; i++) {
        var treeCodeItem = new Array(3);
        treeCodeItem = treeCode[i].split(",");
        nodeId = treeCodeItem[0];        //�ڵ�ID
        nodeClass = treeCodeItem[1];     //�ڵ㼶��
        nodeName = treeCodeItem[2];      //�ڵ�����
        name = nodeClass + "��";
        for (k = 1; k <= nodeClass; k++) {
            name = name + "...";
        }
        tObject.options.length = j + 1;
        if (nodeClass == 0) {
            tObject.options[j].className = "OptionRedColor";
            nodeName = "������" + nodeName + "������";
        } else {
            nodeName = name + "|" + nodeName;
        }
        tObject.options[j].text = nodeName;
        tObject.options[j].value = nodeId;
        tObject.options[j].classvalue = nodeClass;
        j++;
    }
    tObject.options[0].selected = true;
}

/***************************
 ��ҳ����ʾ��ʾ��Ϣ����
 ������
 msg :      ��ʾ��Ϣ���޲���ʱ������ʾ����
 hidePage : �Ƿ�����ҳ����ʾ,Ĭ��false,��ԭҳ������ʾ��ʾ��
 ***************************/
function showMsg(msg, hidePage) {
    if (hidePage) {
        document.body.innerHTML = "";
    }
    if (typeof (divMsgWin00001) == "object") {//��ʾ�����Ѿ�����
        if (msg == null) {
            divMsgWin00001.style.display = "none";
        } else {
            divMsgWin00001.style.display = "block";
            divMsgWinText00001.innerText = msg;
        }
    } else {
        if (msg == null) {
            alert("showMsg():��������ʾ��Ϣ������");
            return;
        }
        var msg = "<DIV " +
            " id=\"divMsgWin00001\"" +
            " style=\"Z-INDEX: 300;" +
            " padding:0;WIDTH: 308px;" +
            " position: absolute; " +
            " font-size:14px;" +
            " border:1 #99ccff solid;" +
            " background-color:#ffffff;" +
            " left:expression((document.body.clientWidth+document.body.scrollLeft-308)/2);" +
            " top:expression((document.body.clientHeight+document.body.scrollTop-120)/2)\"" +
            ">" +
            "<table width=100%  border=0 cellspacing=2 cellpadding=2>" +
            "  <tr>" +
            "    <td rowspan=2><IMG src=" + getPath() + "/images/background/msgbg.gif border=0></td>" +
            "    <td valign=bottom id=divMsgWinText00001 style=\"font-size:14px;\">��" + msg + "</td>" +
            "  </tr>" +
            "  <tr height=16>" +
            "    <td valign=bottom><IMG src=" + getPath() + "/images/background/processbar.gif border=0></td>" +
            "  </tr>" +
            "</table>" +
            "<table>" +
            "</DIV>";
        var oDIV = document.createElement("DIV");
        document.body.insertAdjacentElement("beforeEnd", oDIV);
        oDIV.outerHTML = msg;
    }
}

//����ͬĿ¼��ȡJS�ļ�
function loadJS(src) {
    var script = document.getElementsByTagName("SCRIPT");
    for (var i = 0; i < script.length; i++) {
        var s = script[i].src;
        if (s.indexOf(src) >= 0) return;
        if (s.indexOf("/common/js/comm.js") != -1) {
            jsPath = s.replace("comm.js", "")
        }
    }
    var oScript = document.createElement("<SCRIPT>");
    oScript.src = jsPath + src;
    script[0].insertAdjacentElement("afterEnd", oScript);
}

//��ȡ��ǰ��վ���·��
function getPath() {
    var script = document.getElementsByTagName("SCRIPT");
    for (var i = 0; i < script.length; i++) {
        var s = script[i].src.toLowerCase()
        if (s.indexOf("/common/js/comm.js") != -1) {
            jsPath = s.replace("/common/js/comm.js", "")
        }
    }
    return jsPath;
}