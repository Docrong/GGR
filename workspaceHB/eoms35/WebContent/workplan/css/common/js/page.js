/********************************************************************************
 Copyright (c) 2004-2006, ������ͨ������ҵ��IP����
 All rights reserved.
 Filename ��page.js
 Abstract ��������ʾҳ����صĲ�����������ʽ����
 Version����1.5
 Author   ��Liu Guoyuan
 Finished Date ��2004-03-13
 Last Modified ��2004-08-12

 1.6
 2004-08-12 �޸�_loadJS�����Ŀ¼���д�дʱ�Ҳ���·��������(����)
 �޸�doModify(formName,sURL),doDel(formName,sURL)����һ������sURL(����)

 1.5
 2004-05-02 �޸� setTableStyle�������needMouseStyle����=trueʱ������ʱIE��ջ�������
 2004-04-29 setTableStyle ��ӵ�����������ܣ���������class����Ϊ"SortTableTitle"���ɣ��ù�����Ҫ����table.js���Զ����ã�

 1.3
 2004-03-23
 setTableStyle ��� checkbox.onpropertychange�¼�������checkboxȫ��ѡ��ʱ��ȫѡ��ͬʱѡ�У�����ȫѡ��ѡ��
 setTableStyle ������Ԫ��ԭ���¼�����

 1.2
 ���ñ����ʽ: setTableStyle(table,needMouseStyle)��ʵ��onmouseover��onmouseout��onclick�¼�

 1.0
 ȫѡcheckbox: selectCheckBox(formName)
 ��ѡcheckbox: switchCheckBox(formName)
 �Լ���ز����磺
 �޸�: doModify(formName)
 ɾ��: doDel(formName)
 ��������: doOtherOpera(formName,actionFileName,enableMulSelect,message){
 ********************************************************************************/

//��ȡ������
function _loadJS(src) {
    var script = document.getElementsByTagName("SCRIPT");
    for (var i = 0; i < script.length; i++) {
        var s = script[i].src;
        if (s.indexOf(src) >= 0) return;
        if (s.indexOf("/common/js/page.js") != -1) {
            jsPath = s.replace("page.js", "")
        }
    }
    var oScript = document.createElement("<SCRIPT>");
    oScript.src = jsPath + src;
//	alert(oScript.src);
    script[0].insertAdjacentElement("afterEnd", oScript);
}

_loadJS("lib.js");

//ѡ��ȫ����ѡ��ҳ���б����������Ϊ"chkSelectAll"�ĸ�ѡ�����Ա�ʶȫѡ״̬
function selectCheckBox(formName) {
    var oForm = eval(formName);
    var iptObjects = oForm.getElementsByTagName("INPUT");
    var iptCount = iptObjects.length;
    var chkAllObjects = document.getElementsByName("chkSelectAll");
    if (chkAllObjects.length == 0) return; //������ȫѡ�򣬷���
    for (var i = 0; i < iptCount; i++) {
        if (iptObjects[i].type == "checkbox" && iptObjects[i].id != "chkSelectAll") {
            iptObjects[i].checked = chkAllObjects[0].checked;
        }
    }
    for (var i = 1; i < chkAllObjects.length; i++) {
        chkAllObjects[i].checked = chkAllObjects[0].checked;
    }
}

//������ѡ��
function switchCheckBox(formName) {
    var oForm = eval(formName);
    var iptObjects = oForm.getElementsByTagName("INPUT");
    var iptCount = iptObjects.length;
    for (var i = 0; i < iptCount; i++) {
        if (iptObjects[i].type == "checkbox" && iptObjects[i].id != "chkSelectAll") {
            iptObjects[i].click();
        }
    }
}

//�޸�ѡ�м�¼
function doModify(formName, sURL) {
    var oForm = eval(formName);
    var selectCount = getSelectCount(formName);
    if (selectCount == 0) {
        alert("��ѡ��һ��Ҫ�޸ĵļ�¼��");
        return;
    } else if (selectCount > 1) {
        alert("һ��ֻ���޸�һ����¼��");
        return;
    } else {
//	  var sURL = getFileName("modify");
        oForm.action = sURL;
        oForm.submit();
    }
}

//ɾ������ѡ�м�¼
function doDel(formName, sURL) {
    var oForm = eval(formName);
    var selectCount = getSelectCount(formName);
    if (selectCount == 0) {
        alert("��ѡ��Ҫɾ���ļ�¼��");
        return;
    }
    if (confirm("��ȷ��Ҫɾ��ѡ���ļ�¼��")) {
//		sURL=getFileName("delete")
        oForm.action = sURL;
        oForm.submit();
    }
}

/*********************************
 �ύ����ָ��ҳ��
 ����������ҳ������[[�Ƿ������ѡ(Ĭ������)],��ʾ���]
 *********************************/
function doOtherOpera(formName, actionFileName, enableMulSelect, message) {
    if (arguments.length < 3) enableMulSelect = true;
    var oForm = eval(formName);
    var selectCount = getSelectCount(formName);
    var showText = enableMulSelect ? "" : "һ��";
    if (selectCount == 0) {
        alert("��ѡ��" + showText + "Ҫ�����ļ�¼��");
        return;
    }
    if (!enableMulSelect)  //������ѡ�������¼
        if (selectCount > 1) {
            alert("һ��ֻ��ѡ��һ����¼��");
            return;
        }
    if (arguments.length == 4 && message != "") { //����ʾ��Ϣʱ
        if (!confirm(message)) return;
    }
    var sURL = getFileName(actionFileName)
    oForm.action = sURL;
    oForm.submit();
}

/*********************************
 �ӵ�ǰURL�õ�ƥ��Ĳ����ļ�������ɾ�����޸ġ����,������
 *********************************/
function getFileName(actionFileName) {
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

/*********************************
 �õ���ѡ�е�����
 *********************************/
function getSelectCount(formName) {
    var oForm = eval(formName);
    var selectCount = 0; //ѡ������
    var iptObjects = oForm.getElementsByTagName("INPUT");
    var iptCount = iptObjects.length;
    for (var i = 0; i < iptCount; i++) {
        if (iptObjects[i].type == "checkbox" && iptObjects[i].id != "chkSelectAll") {
            if (iptObjects[i].checked) selectCount++;
        }
    }
    return selectCount;
}

/*********************************
 ���ñ����ʽ������ҳ���ʼ��ʱ���� document.onload = setTableStyle(table)
 ���ܣ�1����Ԫ�񱳾���ɫ����
 ������2�������Ԫ��ʱѡ����ӦCheckBox��Ŧ�����̶���Ԫ�񱳾�ɫ
 ������ 3�������е���������� class = "SortTableTitle"���˹�����Ҫ����table.js���Զ����ã�,�ұ����б�����thead��,�����б�����tbody��
 *********************************/
function setTableStyle(table, needMouseStyle) {
    InitStyle();

    //��ʼ�������ʽ
    function InitStyle() {
        //*** ����ñ���Ѵ�����ʽ��Ϣ ***
        table.isSetStyle = true;
        table.needMouseStyle = needMouseStyle;

        //*** ����¼����� ***
        if (needMouseStyle != false) {
            table.onmouseover = TR_onMouseOver;
            table.onmouseout = TR_onMouseOut;
            table.onclick = TR_onClick;
        }

        //*** �����ʽ���� ***
        if (table.className == "") table.className = "TableContent";
        if (table.cellSpacing == "") table.cellSpacing = 1;
        if (table.cellPadding == "") table.cellPadding = 1;

        //*** ���������ʽ���� ***
        var tr = table.rows;
        var curRow = 0;
        for (var i = 0; i < tr.length; i++) {
            var oTR = tr[i];
            //��ǰ���Ǳ���
            if (oTR.parentNode.tagName == "THEAD" || oTR.parentNode.className.toLowerCase() == "tabletitle") {
                //*** ����������� ***
                if (oTR.className == "SortTableTitle") {
                    if (i <= 1) {
                        _loadJS("table.js");//��ȡ������������
                    }
                    for (var j = 0; j < oTR.cells.length; j++) {
                        if (oTR.cells[j].rowSpan == 1 && oTR.cells[j].colSpan == 1 && oTR.cells[j].innerText.length > 1) {
                            //�������򣺵�ǰ��Ϊ���е��У������ݴ���1����
                            oTR.cells[j].title = "�������";
                            oTR.cells[j].onmousedown = function () {
                                this.style.borderStyle = "inset";
                            }
                            oTR.cells[j].onmouseup = function () {
                                this.style.borderStyle = "outset";
                            }
                            oTR.cells[j].onclick = TD_SortTable;
                        } else {
                            oTR.cells[j].style.cursor = "default";
                            oTR.cells[j].style.textDecoration = "none";
                        }
                    }
                } else {
                    if (oTR.className == "") oTR.className = "TableTitle";
                }
            }
            //��ǰ�����Ǳ������
            else {
                //*** ����������״̬�� ***
                if (oTR.style.display == "none") continue;

                oTR.curRowIndex = curRow; //���浱ǰ��ʾ����,��������ɫ����,��Ϊtr.sectionRowIndex���Ի�������ص��С�
                oTR.className = "TableBodyOut" + (curRow++ % 2 + 1);

                //*** �������¼� ***
                if (needMouseStyle != false) { //��Ҫ��ʽ�л�
                    var td = oTR.cells[0];
                    var chkObj = td.firstChild;
                    if (chkObj != null && chkObj.type == "checkbox") { //��һ����Ԫ����Ԫ��Ϊcheckboxʱ�����õ�Ԫ����ʽ
                        if (i < 3 && tr[0].parentNode.rows.length == 1) {//����ͷֻ��һ��ʱ�����õ�һ�е�һ�б���Ϊ��ɫ
                            tr[0].cells[0].style.backgroundColor = "#FFFFFF";
                        }
                        td.width = 22;
                        td.style.textAlign = "center";
                        td.style.paddingLeft = 0;
                        td.style.paddingRight = 0;
                        chkObj.onpropertychange = CheckBox_Change;
                    } // if checkbox
                } // if needMouseStyle
            } //if else
        } //for
    }

    /*** �������Ч�� ***/
    function TR_onMouseOver() {
        var obj = event.srcElement;
        var objTR = getTableParent(obj, "TR");
        if (objTR == null || objTR.parentNode.tagName == "THEAD" || objTR.parentNode.className.toLowerCase == "tabletitle") return; //��ǰ���Ǳ���
        if (objTR.className != "TableBodyClicked") {
            objTR.className = "TableBodyOver";
        }
    }

    /*** ����Ƴ�Ч�� ***/
    function TR_onMouseOut() {
        var obj = event.srcElement;
        var objTR = getTableParent(obj, "TR");
        if (objTR == null || objTR.parentNode.tagName == "THEAD" || objTR.parentNode.className.toLowerCase == "tabletitle") return; //��ǰ���Ǳ���
        if (objTR.className != "TableBodyClicked") {
            objTR.className = "TableBodyOut" + (objTR.curRowIndex % 2 + 1);
        }
    }

    /*** �����Ч�� ***/
    function TR_onClick() {
        var obj = event.srcElement;
        var objTR = getTableParent(obj, "TR");
        if (objTR == null || obj.tagName == "INPUT" || obj.tagName == "A" || obj.type == "checkbox") return;
        var chkObj = objTR.cells[0].firstChild;
        if (chkObj != null && chkObj.type == "checkbox") {
            chkObj.click();
        }
        //this.onClickOrigin();
    }

    /*** ��ѡ�����Ըı��¼� ***/
    function CheckBox_Change() {
        try {
            var chkAllObj = document.getElementsByName("chkSelectAll")
            var objTR = getTableParent(this, "TR");
            //δѡ��ʱ����ȫѡ����Ϊfalse
            if (!this.checked) {
                objTR.className = "TableBodyOut" + (objTR.sectionRowIndex % 2 + 1);
                for (var i = 0; i < chkAllObj.length; i++) {
                    chkAllObj[i].checked = false;
                }
            } else {
                objTR.className = "TableBodyClicked";
                if (chkAllObj[0].checked) return; //ȫѡ����ѡ��ʱ������
                formObj = getParent(this, "FORM");
                var selectCount = 0; //ѡ������
                var chkCount = 0;    //ȫ����ѡ������
                var iptObjects = formObj.getElementsByTagName("INPUT");
                var iptCount = iptObjects.length;
                for (var i = 0; i < iptCount; i++) {
                    if (iptObjects[i].type == "checkbox" && iptObjects[i].id != "chkSelectAll") {
                        chkCount++;
                        if (iptObjects[i].checked) selectCount++;
                    }
                }
                if (chkCount == selectCount) {
                    for (var i = 0; i < chkAllObj.length; i++) chkAllObj[i].checked = true;
                }
            }
        }//try
        catch (e) {
        }
    }

    /*** ��������¼� ***/
    function TD_SortTable() {
        if (typeof (sortTable) == "function") {
            sortTable(getParent(this, "TABLE")); //sortTable()������table.js��
        }
    }

    function getTableParent(el, pTagName) {
        if (el == null) return null;
        else if (el.nodeType == 1 && el.tagName.toLowerCase() == pTagName.toLowerCase())
            return el;
        else {
            if (el.tagName.toLowerCase() == "tbody" || el.tagName.toLowerCase() == "thead" || el.tagName.toLowerCase() == "table") return null;
            return getTableParent(el.parentNode, pTagName);
        }
    }
}

/*********************************
 �����ʽ��
 =============================
 �����ʽ:             .TableContent
 ��������ʽ:         .TableTitle
 �����������ʽ:       .SortTableTitle
 �����б�����ʽ:       .TableFilterList
 ������ĵ�i����ʽ:    .TableBodyOut1
 ������ĵ�i+1����ʽ:  .TableBodyOut2
 ������������ʽ:     .TableBodyOver
 ���ѡ����ʽ:       .TableBodyClicked
 ���߰�ť��ʽ:         .Page_Tools
 ��ҳ������ʽ:         .Page_List
 *********************************/
with (document) {
    write("<style type=\"text/css\">")
    write("<!--")
    write("  a{color:#006699;text-decoration: none;}")
    write("  .TableContent {font-size: 12px;background-color:#9CB8F4;}")
    write("  .TableContent td{padding-left:4px;padding-right:4px;line-height: 150%; word-break:keep-all;	word-wrap:normal;}")
    write("  .TableTitle {background-color:#0080C0;color: #ffffff;font-weight:bold;text-align:center;}")
    write("  .SortTableTitle {background-color:#0080C0;color: #ffffff;font-weight:bold;text-align:center;}")
    write("  .SortTableTitle td{cursor: hand;border-left:0px;border-top:0px;border-right: 1px outset;border-bottom: 1px outset;white-space: nowrap;text-decoration: none;overflow: hidden;}")
    write("  .TableFilterList {line-height: 100%}")
    write("  .TableFilterList td{padding-left:0px;padding-right:0px;}")
    write("  .TableBodyOut1 {background-color: #EDF1F8;cursor:default;}")
    write("  .TableBodyOut2 {background-color: #D5E0F7;cursor:default;}")
    write("  .TableBodyOver {background-color: #ABCBE2;cursor:default;}")
    write("  .TableBodyClicked {background-color: #C1CDD8;}")
    write("  .Page_Tools a{font-size: 12px;color: #000000;text-decoration: none;border: 1px solid #0066CC;line-height: 120%;letter-spacing: 4px;padding: 2px 0px 0px 4px;}")
    write("  .Page_Tools a:link,.Page_Tools a:visited{background-color: #FFFFFF;}")
    write("  .Page_Tools a:hover{color: red;}")
    write("  .Page_List {font-size: 12px;color: #000000;}")
    write("  .Page_List a{color: #000000;text-decoration: none;border: 1px solid #0066CC;line-height: 120%;letter-spacing: 1px;padding: 2px 0px 0px 2px;}")
    write("  .Page_List a:link,.Page_Tools a:visited{background-color: #FFFFFF;}")
    write("  .Page_List a:hover{background-color: #006699;color: #FFFFFF;}")
    write("  .Page_List_input{border: 1px solid #0066CC;}")
    write("  .Page_List_button{height: 20px;}")
    write("-->")
    write("</style>")
}
