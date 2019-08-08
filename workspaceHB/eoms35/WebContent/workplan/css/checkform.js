String.prototype.Trim = function () {	//ȥ����β�Ŀո�
    return this.replace(/(^\s*)|(\s*$)/g, "");
}

function initArray() {
    this.length = initArray.arguments.length;
    for (var i = 0; i < this.length; i++)
        this[i + 1] = initArray.arguments[i]
}

function checkLength(item, nMin, nMax)	// confirm not null
{
    var str = item.value.Trim();
    if (0 != nMin && 0 == str.length) {
        alert("���������� [" + item.title + "] ");
        item.focus();
        return false;
    } else if (nMin > str.length || nMax < str.length) {
        alert("[" + item.title + "] �ĳ��ȱ����� " + nMin + " ~ " + nMax + " ֮�䣡");
        item.focus();
        return false;
    } else
        return true;
}

function checkPwd(item1, item2, nMin) {
    var pwd1 = item1.value.Trim();
    var pwd2 = item2.value.Trim();
    if (pwd1 == "") {
        alert("���������룬����" + nMin + "λ");
        item1.focus();
        return false;
    }
    if (-1 != pwd1.indexOf("'")) {
        alert("�����в��ɺ��е����ţ��������������");
        item1.value = "";
        item1.focus();
        return false;
    } else if (nMin > pwd1.length) {
        alert("���볤��̫�̣����� " + nMin + " λ");
        item1.value = "";
        item1.focus();
        return false;
    } else if (pwd1 != pwd2) {
        alert("������������벻���ϣ�������ȷ����������");
        item2.value = ""
        item2.focus();
        return false;
    } else
        return true;
}

function checkEmail(item) {
    var email = item.value.Trim();
    if (0 == email.length) {
        alert("���������� [����] ");
        item.focus();
        return false;
    }
    re = /(\w+)@(\w+)\.(com|net|org|edu)\.*(\w*)/i;
    re.exec(email);
    if (RegExp.$4 != "") strr = RegExp.$1 + "@" + RegExp.$2 + "." + RegExp.$3 + "." + RegExp.$4
    else strr = RegExp.$1 + "@" + RegExp.$2 + "." + RegExp.$3
    if (strr != email) {
        alert("�����ʼ���ַ��ʽ���ԣ�");
        item.focus();
        return false;
    }
    return true;
}

function checkSpecChar(item, specStr) {
    if (!checkLength(item)) return false;

    var re;
    var str = item.value.Trim();

    if (specStr == null) {//���specStrΪȱʡ,��ֻ���"��'
        re = /[\"\']/;
        if (str.search(re) != -1) {
            alert("�벻Ҫ���������ַ�\n��Ӣ��˫����(\")��Ӣ�ĵ�����(\')��");
            item.focus();
            return false;
        }
    } else {
        for (j = 0; j < specStr.length; j++) {
            re = specStr.charAt(j);
            if (str.search(re) != -1) {
                alert("�벻Ҫ����(" + re + ")�ַ�,лл");
                item.focus();
                return false;
            }
        }
    }
    return true;
}

function checkSpecCharHas(item, specStr) {
    if (!checkLength(item)) return false;

    var re;
    var str = item.value.Trim();

    if (specStr == null) {//���specStrΪȱʡ,��ֻ���"��'
        re = /[\"\']/;
        if (str.search(re) == -1) {
            alert("������(\")��(\')���ַ�");
            item.focus();
            return false;
        }
    } else {
        for (j = 0; j < specStr.length; j++) {
            re = specStr.charAt(j);
            if (str.search(re) == -1) {
                alert("������(" + re + ")�ַ�,лл");
                item.focus();
                return false;
            }
        }
    }
    return true;
}

//**************************************
//���ڲ�ѯ�����Ŀ�������
//**************************************
function checkboxSwitch_input(checkboxObj, inputObj, defaultInputText) {
    if (checkboxObj.checked) {
        inputObj.disabled = false;
        inputObj.value = "";
    } else {
        inputObj.disabled = true;
        inputObj.value = defaultInputText;
    }
}

function checkboxSwitch_multi(checkboxObj, selectObj, inputObj, hiddenObj, defaultSelectText) {
//	alert(checkboxObj+","+selectObj+","+inputObj+","+hiddenObj);
    if (checkboxObj.checked) {
        selectObj.disabled = false;
        inputObj.disabled = false;
        hiddenObj.disabled = false;
    } else {
        selectObj.disabled = true;
        selectObj.value = -1;
        inputObj.disabled = true;
        hiddenObj.disabled = true;
    }
    inputObj.value = defaultSelectText;
    hiddenObj.value = "";
}

function addOptionToInput(selectObj, inputObj, hiddenObj, defaultInputText) {
    var selectedOpt = selectObj.options[selectObj.selectedIndex];

    if (selectedOpt.value != "-1") {
        if (inputObj.value.indexOf(selectedOpt.text) == -1) {
            if (inputObj.value == "" || inputObj.value == defaultInputText) {
                inputObj.value = selectedOpt.text
                hiddenObj.value = selectObj.value;

            } else {
                doadd(selectedOpt.text, inputObj);
                doadd(selectObj.value, hiddenObj);
            }
        }
    } else {
        inputObj.value = defaultInputText;
        hiddenObj = "";
    }

//	alert(hiddenObj.value);
    function doadd(obj, addtoObj) {
        var tempArray = addtoObj.value.split(",");
        tempArray.push(obj);
        addtoObj.value = tempArray.join(",");
    }
}

function GoBack() {
    window.history.back();
}