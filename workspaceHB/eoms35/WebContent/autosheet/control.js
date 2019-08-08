/*ȷ��ɾ������**************************************************************************/
function ConfirmDel() {
    if (confirm("��ȷ���Ƿ�ɾ��,�ò�����ɾ����Ӧ���µ������������,���������!")) {
        return true;
    } else {
        return false;
    }
}

/*ȷ���޸Ĳ���**************************************************************************/
function ConfirmUpdate() {
    if (confirm("��ȷ���Ƿ��޸�,�ò�����ʹ�޸���Ϣ��Ч���п���Ӱ����ز���,���������!")) {
        return true;
    } else {
        return false;
    }
}

/*ȷ�ϲ�ѯʱ���ʽ**********************************************************************/
function isDate(Year1, Month1, Day1, Year2, Month2, Day2) {
    var Year1, Year2, Month1, Month2, Day1, Day2;
    Year1 = parseFloat(Year1);
    Year2 = parseFloat(Year2);
    Month1 = parseFloat(Month1);
    Month2 = parseFloat(Month2);
    Day1 = parseFloat(Day1);
    Day2 = parseFloat(Day2);

    switch (Month1) {
        case 4:
        case 6:
        case 9:
        case 11:
            if (Day1 > 30) {
                alert("��ʼ������" + Year1 + "-" + Month1 + "-" + Day1 + "������");
                return false;
            } else break;
        case 2:
            if (((Year1 % 4 == 0 && Year1 % 100 != 0) || Year1 % 400 == 0) && Day1 > 29) {
                alert("��ʼ������" + Year1 + "-" + Month1 + "-" + Day1 + "������");
                return false;
            } else if ((Year1 % 4 != 0 || (Year1 % 100 == 0 && Year1 % 400 != 0)) && Day1 > 28) {
                alert("��ʼ������" + Year1 + "-" + Month1 + "-" + Day1 + "������");
                return false;
            }
        default:
    }
    switch (Month2) {
        case 4:
        case 6:
        case 9:
        case 11:
            if (Day2 > 30) {
                alert("����������" + Year1 + "-" + Month1 + "-" + Day1 + "������");
                return false;
            } else break;
        case 2:
            if (((Year2 % 4 == 0 && Year2 % 100 != 0) || Year2 % 400 == 0) && Day2 > 29) {
                alert("����������" + Year1 + "-" + Month1 + "-" + Day1 + "������");
                return false;
            } else if ((Year2 % 4 != 0 || (Year2 % 100 == 0 && Year2 % 400 != 0)) && Day2 > 28) {
                alert("����������" + Year1 + "-" + Month1 + "-" + Day1 + "������");
                return false;
            } else break;
        default:
    }
    if (Year1 > Year2) {
        alert("��ѯ��ʼ���:" + Year1 + "-" + Month1 + "-" + Day1 + "���ڽ������:" + Year2 + "-" + Month2 + "-" + Day2);
        return false;
    } else if (Month1 > Month2) {
        alert("��ѯ��ʼ�·�:" + Year1 + "-" + Month1 + "-" + Day1 + "���ڽ����·�:" + Year2 + "-" + Month2 + "-" + Day2);
        return false;
    } else if (Day1 > Day2) {
        alert("��ѯ��ʼ����:" + Year1 + "-" + Month1 + "-" + Day1 + "���ڽ�������:" + Year2 + "-" + Month2 + "-" + Day2);
        return false;
    }
    return true;
}

/*ȷ��������¼��ʱ���ʽ**********************************************************************/
function isDateInput(Year1, Month1, Day1) {
    var Year1, Month1, Day1;
    Year1 = parseFloat(Year1);
    Month1 = parseFloat(Month1);
    Day1 = parseFloat(Day1);

    switch (Month1) {
        case 4:
        case 6:
        case 9:
        case 11:
            if (Day1 > 30) {
                alert("������" + Year1 + "-" + Month1 + "-" + Day1 + "������");
                return false;

            } else break;
        case 2:
            if (((Year1 % 4 == 0 && Year1 % 100 != 0) || Year1 % 400 == 0) && Day1 > 29) {
                alert("������" + Year1 + "-" + Month1 + "-" + Day1 + "������");
                return false;
            } else if ((Year1 % 4 != 0 || (Year1 % 100 == 0 && Year1 % 400 != 0)) && Day1 > 28) {
                alert("������" + Year1 + "-" + Month1 + "-" + Day1 + "������");
                return false;
            }
        default:
    }
    return true;
}

/*ȷ���ı���¼��ʱ���ʽ**********************************************************************/
function textInputIsDate(datestr) {
    var Year1, Month1, Day1;
    //����������ʽ��֤ʱ���ʽ
    re = /^\d{4}-{1}\d{2}-{1}\d{2}$/;
    if (datestr.match(re) == null) {
        alert("ʱ�������ʽ���ԣ���ȷ��ʽΪ:YYYY-MM-DD");
        return false;
    } else {

        Year1 = parseFloat(datestr.substring(0, 3));
        Month1 = parseFloat(datestr.substring(5, 6));
        Day1 = parseFloat(datestr.substring(8, 9));

        switch (Month1) {
            case 4:
            case 6:
            case 9:
            case 11:
                if (Day1 > 30) {
                    alert("������" + Year1 + "-" + Month1 + "-" + Day1 + "������");
                    return false;

                } else break;
            case 2:
                if (((Year1 % 4 == 0 && Year1 % 100 != 0) || Year1 % 400 == 0) && Day1 > 29) {
                    alert("������" + Year1 + "-" + Month1 + "-" + Day1 + "������");
                    return false;
                } else if ((Year1 % 4 != 0 || (Year1 % 100 == 0 && Year1 % 400 != 0)) && Day1 > 28) {
                    alert("������" + Year1 + "-" + Month1 + "-" + Day1 + "������");
                    return false;
                }
            default:
        }
    }
    return true;
}

/*ȷ��¼��ʱ���ʽ**********************************************************************/
function isDateInput2(Year1, Month1, Day1) {
    var Year1, Month1, Day1;
    Year1 = parseFloat(Year1.value);
    Month1 = parseFloat(Month1.value);
    Day1 = parseFloat(Day1.value);

    switch (Month1) {
        case 4:
        case 6:
        case 9:
        case 11:
            if (Day1 > 30) {
                alert("������" + Year1 + "-" + Month1 + "-" + Day1 + "������");
                return false;

            } else break;
        case 2:
            if (((Year1 % 4 == 0 && Year1 % 100 != 0) || Year1 % 400 == 0) && Day1 > 29) {
                alert("������" + Year1 + "-" + Month1 + "-" + Day1 + "������");
                return false;
            } else if ((Year1 % 4 != 0 || (Year1 % 100 == 0 && Year1 % 400 != 0)) && Day1 > 28) {
                alert("������" + Year1 + "-" + Month1 + "-" + Day1 + "������");
                return false;
            }
        default:
    }
    return true;
}


/*�ж��Ƿ�Ϊ��ֵ��****************************************************************/
function trim(str) {
    if (str == null) {
        return "";
    }
    for (i = 0; i < str.length; i++) {
        if (str.charAt(0) == ' ') {
            str = str.substr(1);
        } else {
            break;
        }
    }
    return str;
}

/*�滻�ַ���**********************************************************************/
function replacechar(str, searchchar, replacechar) {
    for (i = 0; i < str.length; i++) {
        if (str.charAt(0) == ' ') {
            str = str.substr(1);
        } else {
            break;
        }
    }
    while (str.length > 0) {
        if (str.charAt(str.length - 1) == ' ') {
            str = str.substr(0, str.length - 1);
        } else {
            break;
        }
    }
    return str;
}

/*�ж��Ƿ�Ϊ��ֵ��****************************************************************/
function isNumber(inputVal) {

    inputStr = inputVal;
    if (inputStr.length == 1) {
        if ((inputStr.charAt(0) < '0') || (inputStr.charAt(0) > '9')) {
            alert("������:" + inputStr + "����Ϊ��ֵ��");
            return false;
        }
    } else if (inputStr.length > 1) {
        if ((inputStr.charAt(0) < '0') || (inputStr.charAt(0) > '9')) {
            alert("������:" + inputStr + "����Ϊ��ֵ��");
            return false;
        }
        for (n = 1; n < inputStr.length; n++) {
            if ((inputStr.charAt(n) != '.')) {
                if ((inputStr.charAt(n) > '9') || (inputStr.charAt(n) < '0')) {
                    alert("������:" + inputStr + "����Ϊ��ֵ��");
                    return false;
                }
            }
        }

    }
    return true;
}

/*�ж���ֵ���Ƿ񳬳��������*********************************************************/
function isOutRange(inputVal) {
    var inputInt = parseInt(inputVal);

    var index = document.addEForm.vStoreType.options[document.addEForm.vStoreType.selectedIndex].text;
    if (index == "String") {
        var inputInt1 = parseInt("255");
    } else if (index == "LongString") {
        var inputInt1 = parseInt("4000");
    }
    if (inputInt > inputInt1) {
        alert("������:" + inputVal + "�������ֵ:" + inputInt1);
        return false;
    }
    return true;
}

/*�ж��Ƿ�Ϊ��ֵ��****************************************************************/
function isNumber2(inputVal) {

    inputStr = inputVal.value;
    if (inputStr.length == 1) {
        if ((inputStr.charAt(0) < '0') || (inputStr.charAt(0) > '9')) {
            alert("������:" + inputVal.name + "��ֵ" + inputStr + "������.");
            return false;
        }
    } else if (inputStr.length > 1) {
        if ((inputStr.charAt(0) < '1') || (inputStr.charAt(0) > '9')) {
            alert("������:" + inputVal.name + "��ֵ" + inputStr + "������.");
            return false;
        }
        for (n = 1; n < inputStr.length; n++) {
            if ((inputStr.charAt(n) > '9') || (inputStr.charAt(n) < '0')) {
                alert("������:" + inputVal.name + "��ֵ" + inputStr + "������.");
                return false;
            }
        }

    }
    return true;
}

/*�ж���ɫ��ʽ********************************************************************/
function isColor(varColor) {
    /*var varcolor=varColor;
    if(varcolor!=""){
      if(varcolor.length!=7){
        alert("��ʾ��ɫ��ʽ����,��ȷ��ʽ��:#000000��#FFFFFF.");
        return false;
      }else if(varcolor.length==7){
        if(varcolor.charAt(0)!="#"){
          alert("��ʾ��ɫ��ʽ����,��ȷ��ʽ��:#000000��#FFFFFF.");
          return false;
        }else{
          for(i=1;i<7;i++){
            if((varcolor.charAt(i)<="0"||varcolor.charAt(i)>="9")&&(varcolor.charAt(i)<="a"||varcolor.charAt(i)>="f")&&(varcolor.charAt(i)<="A"||varcolor.charAt(i)>="F")){
              alert("��ʾ��ɫ��ʽ����,��ȷ��ʽ��:#000000��#FFFFFF.");
              return false;
            }
          }
        }
      }
    }*/
    return true;
}

/***�����������ȽϽ��,�����ߵ��������ұߵ���,���ؼ�,��֮��**********************/
function compareTwoNum(varValue1, varValue2) {
    var var1 = parseFloat(varValue1);
    var var2 = parseFloat(varValue2);
    if (var1 > var2) {
        alert("��ߵ������ܴ����ұߵ���");
        return false;
    } else
        return true;
}

/***Ȩ�޸澯*********************************************************************/
function powerAlert(varValue) {
    var varvalue = parseFloat(varValue);
    if (varvalue == 1) {
        alert("�Բ���,��û��Ȩ�޽��д������!");
        return false;
    } else
        return true;
}

/***�ж��Ƿ�Ϊ��******************************************************************/
function isNull(varStr) {
    var varstr = varStr;
    if (varstr == "" || varstr == null) {
        alert("���Ǻŵ���" + varStr + "����Ϊ��.");
        return false;
    }
    return true;
}

/***�ж��Ƿ�Ϊ��******************************************************************/
function isNull2(varStr) {
    var varstr = varStr.value;
    if (varstr == "" || varstr == null) {
        alert("���Ǻŵ���" + varStr.name + "����Ϊ��.");
        return false;
    }
    return true;
}

/***�ж�ѡ����Ƿ�ѡ��******************************************************************/
function isOption(selectAttr) {
    var j = 0;
    var SelectedIndex = selectAttr.options.selectedIndex;
    if (SelectedIndex == -1) {
        alert("��ѡ��һ������");
        return false;
    } else {
        return true;
    }
}

/***�ж�******************************************************************/
function isPowerUser(poweruser) {

    if (poweruser != "") {
        alert("����:" + poweruser);
        return false;
    } else {
        return true;
    }


}

function vStoreTypeChange() {

    var index = document.addEForm.vStoreType.options[document.addEForm.vStoreType.selectedIndex].text;
    var index1 = document.addEForm.showtype.options[document.addEForm.showtype.selectedIndex].value;
    if (index == "Date" || index == "DateTime") {
        //document.addEForm.showtype.selectedIndex=3;
        document.addEForm.showtype.disabled = true;
        document.addEForm.formWidth.disabled = true;
        document.addEForm.formHeight.disabled = true;
        document.addEForm.typeID.disabled = true;
    } else if (index == "Integer" || index == "Float") {
        document.addEForm.showtype.selectedIndex = 0;
        document.addEForm.showtype.disabled = true;
        document.addEForm.typeID.selectedIndex = 0;
        document.addEForm.typeID.disabled = true;
        if (index1 == 0 || index1 == 5) {
            document.addEForm.formWidth.disabled = false;
            document.addEForm.formHeight.disabled = false;
        }
        str.innerHTML = "���봮���";
    } else if (index == "String" || index == "LongString") {
        document.addEForm.showtype.disabled = false;
        document.addEForm.typeID.selectedIndex = 0;
        document.addEForm.typeID.disabled = true;
        if (index1 == 0 || index1 == 5) {
            document.addEForm.formWidth.disabled = false;
            document.addEForm.formHeight.disabled = false;
        }
    } else {
        //document.addEForm.showtype.selectedIndex=0;
        document.addEForm.showtype.disabled = false;
        document.addEForm.formWidth.disabled = false;
        document.addEForm.formHeight.disabled = false;
        document.addEForm.typeID.disabled = false;

    }
}

function showtypeChange() {
    var index = document.addEForm.showtype.options[document.addEForm.showtype.selectedIndex].value;
    if (index == 0 || index == 5) {
        document.addEForm.typeID.disabled = true;
        document.addEForm.formWidth.disabled = false;
        document.addEForm.formHeight.disabled = false;
        document.addEForm.vStoreType.disabled = false;
        if (index == 5) {
            str.innerHTML = "�ı��༭���<font color=\"red\">(*)</font>";
            document.addEForm.height.disabled = true;
            if (document.addEForm.isedit.value == "0")
                document.addEForm.formHeight.value = 3;
        } else {
            str.innerHTML = "���봮���<font color=\"red\">(*)</font>";
            if (document.addEForm.isedit.value == "0")
                document.addEForm.formHeight.value = 20;
        }
    } else {
        document.addEForm.typeID.disabled = false;
        document.addEForm.formWidth.disabled = true;
        document.addEForm.formHeight.disabled = true;
        document.addEForm.vStoreType.selectedIndex = 0;
        document.addEForm.vStoreType.disabled = true;
        str.innerHTML = "���봮���<font color=\"red\">(*)</font>";

    }
}

function openColorWin() {
//	if (window.secondwindow&&window.secondwindow.closed==false){
//		secondwindow.document.bgColor='red';secondwindow.focus();
//	}else
    secondwindow = window.showModalDialog("color.jsp", window, "dialogHeight:235px;dialogWidth:230px;center:YES;help:NO;resizable:NO;status:NO;");
    //alert(secondwindow);
    document.addEForm.color.value = secondwindow;
    return true;
}


function checkValueInput() {
    var vName = document.addEForm.vName.value;
    var index = document.addEForm.index.value;
    var color = document.addEForm.color.value;
    var colspan = document.addEForm.colspan.value;
    var rowspan = document.addEForm.rowspan.value;
    var width = document.addEForm.width.value;
    var height = document.addEForm.height.value;
    var formWidth = document.addEForm.formWidth.value;
    var formHeight = document.addEForm.formHeight.value;
    var formWidth2 = document.addEForm.formWidth;

    if (isOutRange(formHeight) && isNull(vName) && isNull(index) && isNull(colspan) && isNull(rowspan) &&
        isNull(formWidth) && isNull(formHeight) && isNumber(colspan) && isNumber(rowspan) &&
        isNumber(width) && isNumber(index) && isNumber(height) && isNumber(formWidth) && isNumber(formHeight) && isVariable(document.addEForm.vName)) {
        return true;
    } else {
        return false;
    }
}

function isVariable(variable) {
    var literal = 'abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
    var number = '0123456789_';
    if (variable.value == null)
        alert('�����Ϊ��');
    var value = trim(variable.value);
    for (i = 0; i < value.length; i++) {
        if (i == 0) {
            if (literal.indexOf(value.charAt(i)) < 0) {
                alert('����ĸ������Ӣ����ĸ');
                return variable.focus();

            }
        } else {
            if (literal.indexOf(value.charAt(i)) < 0 && number.indexOf(value.charAt(i)) < 0) {
                alert('������Ӣ�Ļ����ֻ��»���"_"���');
                return variable.focus();

            }
        }

    }
    return true;
}

function initEditValue() {
    var index = document.addEForm.showtype.options[document.addEForm.showtype.selectedIndex].value;
    var index1 = document.addEForm.vStoreType.options[document.addEForm.vStoreType.selectedIndex].text;

    if (index == 5) {
        str.innerHTML = "�ı��༭���";
        document.addEForm.height.disabled = true;
    } else {
        str.innerHTML = "���봮���";
    }
    if (index == 0 || index == 5)
        document.addEForm.typeID.disabled = true;

    if (index == "Integer" || index == "Float") {
        str.innerHTML = "���봮���";
    }
}
