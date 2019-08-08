var Quote = 0;
var Bold = 0;
var Italic = 0;
var Underline = 0;
var Code = 0;
var Center = 0;
var Strike = 0;
var Sound = 0;
var Swf = 0;
var Ra = 0;
var Rm = 0;
var Marquee = 0;
var Fly = 0;
var fanzi = 0;
var text_enter_url = "������������ַ";
var text_enter_txt = "����������˵��";
var text_enter_image = "������ͼƬ�ڸ����е��ļ���,����ö��Ÿ���";
var text_enter_sound = "�����������ļ���ַ";
var text_enter_swf = "������FLASH������ַ";
var text_enter_ra = "������Real������ַ";
var text_enter_rm = "������RealӰƬ��ַ";
var text_enter_wmv = "������MediaӰƬ��ַ";
var text_enter_wma = "������Media������ַ";
var text_enter_mov = "������QuickTime������ַ";
var text_enter_sw = "������shockwave������ַ";
var text_enter_email = "�������ʼ���ַ";
var error_no_url = "������������ַ";
var error_no_txt = "����������˵��";
var error_no_title = "������������ҳ����";
var error_no_email = "�����������ʼ���ַ";
var error_no_gset = "������ȷ���ո�ʽ���룡";
var error_no_gtxt = "�����������֣�";
var text_enter_guang1 = "���ֵĳ��ȡ���ɫ�ͱ߽��С";
var text_enter_guang2 = "Ҫ����Ч�������֣�";
var text_enter_points = "��������ֵ,�磺1000 (�����ƻ�����1000�����µ��û��������������!)";
var error_no_points = "������������ֵ,�磺1000";
var text_enter_money = "��������ֵ,�磺1000 (�����ƽ�Ǯ��1000�����µ��û��������������!)";
var error_no_moeny = "������������ֵ,�磺1000";
var text_enter_power = "��������ֵ,�磺1000 (������������1000�����µ��û��������������!)";
var error_no_power = "������������ֵ,�磺1000";
var text_enter_post = "��������ֵ,�磺1000 (�����Ʒ���������1000�����µ��û��������������!)";
var error_no_post = "������������ֵ,�磺1000";
var text_enter_usercp = "��������ֵ,�磺1000 (����������ֵ��1000�����µ��û��������������!)";
var error_no_usercp = "������������ֵ,�磺1000";
var text_enter_usemoney = "��������ֵ,�磺1000 (��ֻ���û�֧����1000���Ǯ�������������!)";
var error_no_usemoney = "������������ֵ,�磺1000";

function commentWrite(NewCode) {
    document.tawInformationForm.body.value += NewCode;
    document.tawInformationForm.body.focus();
    return;
}

function storeCaret(text) {
    if (text.createTextRange) {
        text.caretPos = document.selection.createRange().duplicate();
    }
    if (event.ctrlKey && window.event.keyCode == 13) {
        i++;
        if (i > 1) {
            alert('�������ڷ����������ĵȴ���');
            return false;
        }
        this.document.form.submit();
    }
}

function AddText(text) {
    if (document.tawInformationForm.body.createTextRange && document.tawInformationForm.body.caretPos) {
        var caretPos = document.tawInformationForm.body.caretPos;
        caretPos.text = caretPos.text.charAt(caretPos.text.length - 1) == ' ' ?
            text + ' ' : text;
    } else document.tawInformationForm.body.value += text;
    document.tawInformationForm.body.focus(caretPos);
}

function inputs(str) {
    AddText(str);
}

function Curl() {
    var FoundErrors = '';
    var enterURL = prompt(text_enter_url, "http://");
    var enterTxT = prompt(text_enter_txt, enterURL);
    if (!enterURL) {
        FoundErrors += "\n" + error_no_url;
    }
    if (!enterTxT) {
        FoundErrors += "\n" + error_no_txt;
    }
    if (FoundErrors) {
        alert("����" + FoundErrors);
        return;
    }
    var ToAdd = "[URL=" + enterURL + "]" + enterTxT + "[/URL]";
    document.tawInformationForm.body.value += ToAdd;
    document.tawInformationForm.body.focus();
}

function Cimage() {
    var FoundErrors = '';
    var enterURL = prompt(text_enter_image, IFrame1.tawFileUploadForm.fileText.value);
    if (!enterURL) {
        FoundErrors += "\n" + error_no_url;
    }

    if (IFrame1.tawFileUploadForm.fileValue.value == "") {
        alert("��ͨ���ϴ������ϴ���Ҫ����ͼƬ");
    } else {
        var fileValueArr = IFrame1.tawFileUploadForm.fileValue.value.split(",");
        var fileTextArr = IFrame1.tawFileUploadForm.fileText.value.split(",");
        var fValue = "";
        var fText = "";
        var urlArr = enterURL.split(",");
        enterURL = "";
        for (i = 0; i < fileTextArr.length; i++) {
            flag = "";
            for (j = 0; j < urlArr.length; j++) {
                if (fileTextArr[i] == urlArr[j]) {
                    if (enterURL == "")
                        enterURL = fileValueArr[i];
                    else
                        enterURL = enterURL + "," + fileValueArr[i];
                    flag = "$";
                    break;
                }
            }
            if (fValue == "") {
                fValue = fileValueArr[i] + flag;
                fText = fileTextArr[i]
            } else {
                fText = fText + "," + fileTextArr[i];
                fValue = fValue + "," + fileValueArr[i] + flag;
            }
        }
        var urlArr1 = enterURL.split(",");
        if (urlArr1.length != urlArr.length) {
            alert("��ͼ�ļ������ʽ�������󣬺˶Ժ���������");
            return;
        }
        var ToAdd = "";
        for (i = 0; i < urlArr1.length; i++)
            ToAdd = ToAdd + "[IMG]" + urlArr1[i] + "[/IMG]";
        IFrame1.tawFileUploadForm.fileValue.value = fValue;
        IFrame1.tawFileUploadForm.fileText.value = fText;
        document.tawInformationForm.body.value += ToAdd;
        document.tawInformationForm.body.focus();
    }
}

function Cemail() {
    var emailAddress = prompt(text_enter_email, "");
    if (!emailAddress) {
        alert(error_no_email);
        return;
    }
    var ToAdd = "[EMAIL]" + emailAddress + "[/EMAIL]";
    commentWrite(ToAdd);
}

function Ccode() {
    if (Code == 0) {
        ToAdd = "[CODE]";
        document.form.code.value = " ����*";
        Code = 1;
    } else {
        ToAdd = "[/CODE]";
        document.form.code.value = " ���� ";
        Code = 0;
    }
    commentWrite(ToAdd);
}

function Cquote() {
    fontbegin = "[QUOTE]";
    fontend = "[/QUOTE]";
    fontchuli();
}

function Cbold() {
    fontbegin = "[B]";
    fontend = "[/B]";
    fontchuli();
}

function Citalic() {
    fontbegin = "[I]";
    fontend = "[/I]";
    fontchuli();
}

function Cunder() {
    fontbegin = "[U]";
    fontend = "[/U]";
    fontchuli();
}

function Ccenter() {
    fontbegin = "[align=center]";
    fontend = "[/align]";
    fontchuli();
}

function Cstrike() {
    fontbegin = "[strike]";
    fontend = "[/strike]";
    fontchuli();
}

function point() {
    var FoundErrors = '';
    var enterpoints = prompt(text_enter_points, "1000");
    if (!enterpoints) {
        FoundErrors += "\n" + error_no_points;
    }
    if (FoundErrors) {
        alert("����" + FoundErrors);
        return;
    }
    var ToAdd = "[Point=" + enterpoints + "][/Point]";
    document.tawInformationForm.body.value += ToAdd;
    document.tawInformationForm.body.focus();
}

function money() {
    var FoundErrors = '';
    var entermoney = prompt(text_enter_money, "1000");
    if (!entermoney) {
        FoundErrors += "\n" + error_no_money;
    }
    if (FoundErrors) {
        alert("����" + FoundErrors);
        return;
    }
    var ToAdd = "[Money=" + entermoney + "][/Money]";
    document.tawInformationForm.body.value += ToAdd;
    document.tawInformationForm.body.focus();
}

function usemoney() {
    var FoundErrors = '';
    var entermoney = prompt(text_enter_usemoney, "1000");
    if (!entermoney) {
        FoundErrors += "\n" + error_no_usemoney;
    }
    if (FoundErrors) {
        alert("����" + FoundErrors);
        return;
    }
    var ToAdd = "[UseMoney=" + entermoney + "][/UseMoney]";
    document.tawInformationForm.body.value += ToAdd;
    document.tawInformationForm.body.focus();
}

function usercp() {
    var FoundErrors = '';
    var enterusercp = prompt(text_enter_usercp, "1000");
    if (!enterusercp) {
        FoundErrors += "\n" + error_no_usercp;
    }
    if (FoundErrors) {
        alert("����" + FoundErrors);
        return;
    }
    var ToAdd = "[UserCP=" + enterusercp + "][/UserCP]";
    document.tawInformationForm.body.value += ToAdd;
    document.tawInformationForm.body.focus();
}

function power() {
    var FoundErrors = '';
    var enterpower = prompt(text_enter_power, "1000");
    if (!enterpower) {
        FoundErrors += "\n" + error_no_power;
    }
    if (FoundErrors) {
        alert("����" + FoundErrors);
        return;
    }
    var ToAdd = "[Power=" + enterpower + "][/Power]";
    document.tawInformationForm.body.value += ToAdd;
    document.tawInformationForm.body.focus();
}

function article() {
    var FoundErrors = '';
    var enterpost = prompt(text_enter_post, "1000");
    if (!enterpost) {
        FoundErrors += "\n" + error_no_post;
    }
    if (FoundErrors) {
        alert("����" + FoundErrors);
        return;
    }
    var ToAdd = "[Post=" + enterpost + "][/Post]";
    document.tawInformationForm.body.value += ToAdd;
    document.tawInformationForm.body.focus();
}

function replyview() {
    var ToAdd = "[replyview][/replyview]";
    document.tawInformationForm.body.value += ToAdd;
    document.tawInformationForm.body.focus();
}

function Csound() {
    var FoundErrors = '';
    var enterURL = prompt(text_enter_sound, "http://");
    if (!enterURL) {
        FoundErrors += "\n" + error_no_url;
    }
    if (FoundErrors) {
        alert("����" + FoundErrors);
        return;
    }
    var ToAdd = "[SOUND]" + enterURL + "[/SOUND]";
    document.tawInformationForm.body.value += ToAdd;
    document.tawInformationForm.body.focus();
}

helpstat = false;
stprompt = true;
basic = false;

function thelp(swtch) {
    if (swtch == 1) {
        basic = false;
        stprompt = false;
        helpstat = true;
    } else if (swtch == 0) {
        helpstat = false;
        stprompt = false;
        basic = true;
    } else if (swtch == 2) {
        helpstat = false;
        basic = false;
        stprompt = true;
    }
}

function Cswf() {
    if (helpstat) {
        alert("Flash\nFlash ����.\n�÷�: [flash=���, �߶�]Flash �ļ��ĵ�ַ[/flash]");
    } else if (basic) {
        AddTxt = "[flash=500,350][/flash]";
        AddText(AddTxt);
    } else {
        txt2 = prompt("flash��ȣ��߶�", "500,350");
        if (txt2 != null) {
            txt = prompt("Flash �ļ��ĵ�ַ", "http://");
            if (txt != null) {
                if (txt2 == "") {
                    AddTxt = "[flash=500,350]" + txt;
                    AddText(AddTxt);
                    AddTxt = "[/flash]";
                    AddText(AddTxt);
                } else {
                    AddTxt = "[flash=" + txt2 + "]" + txt;
                    AddText(AddTxt);
                    AddTxt = "[/flash]";
                    AddText(AddTxt);
                }
            }
        }
    }
}

function Crm() {
    if (helpstat) {
        alert("realplay\n����realplay�ļ�.\n�÷�: [rm=���, �߶�]�ļ���ַ[/rm]");
    } else if (basic) {
        AddTxt = "[rm=500,350][/rm]";
        AddText(AddTxt);
    } else {
        txt2 = prompt("��Ƶ�Ŀ�ȣ��߶�", "500,350");
        if (txt2 != null) {
            txt = prompt("��Ƶ�ļ��ĵ�ַ", "������");
            if (txt != null) {
                if (txt2 == "") {
                    AddTxt = "[rm=500,350]" + txt;
                    AddText(AddTxt);
                    AddTxt = "[/rm]";
                    AddText(AddTxt);
                } else {
                    AddTxt = "[rm=" + txt2 + "]" + txt;
                    AddText(AddTxt);
                    AddTxt = "[/rm]";
                    AddText(AddTxt);
                }
            }
        }
    }
}

function Cwmv() {
    if (helpstat) {
        alert("Media Player\n����Media Player�ļ�.\n�÷�: [mp=���, �߶�]�ļ���ַ[/mp]");
    } else if (basic) {
        AddTxt = "[mp=500,350][/mp]";
        AddText(AddTxt);
    } else {
        txt2 = prompt("��Ƶ�Ŀ�ȣ��߶�", "500,350");
        if (txt2 != null) {
            txt = prompt("��Ƶ�ļ��ĵ�ַ", "������");
            if (txt != null) {
                if (txt2 == "") {
                    AddTxt = "[mp=500,350]" + txt;
                    AddText(AddTxt);
                    AddTxt = "[/mp]";
                    AddText(AddTxt);
                } else {
                    AddTxt = "[mp=" + txt2 + "]" + txt;
                    AddText(AddTxt);
                    AddTxt = "[/mp]";
                    AddText(AddTxt);
                }
            }
        }
    }
}

function Cmov() {
    if (helpstat) {
        alert("QuickTime\n����QuickTime�ļ�.\n�÷�: [qt=���, �߶�]�ļ���ַ[/qt]");
    } else if (basic) {
        AddTxt = "[qt=500,350][/qt]";
        AddText(AddTxt);
    } else {
        txt2 = prompt("��Ƶ�Ŀ�ȣ��߶�", "500,350");
        if (txt2 != null) {
            txt = prompt("��Ƶ�ļ��ĵ�ַ", "������");
            if (txt != null) {
                if (txt2 == "") {
                    AddTxt = "[qt=500,350]" + txt;
                    AddText(AddTxt);
                    AddTxt = "[/qt]";
                    AddText(AddTxt);
                } else {
                    AddTxt = "[qt=" + txt2 + "]" + txt;
                    AddText(AddTxt);
                    AddTxt = "[/qt]";
                    AddText(AddTxt);
                }
            }
        }
    }
}

function Cdir() {
    if (helpstat) {
        alert("Shockwave\n����Shockwave�ļ�.\n�÷�: [dir=���, �߶�]�ļ���ַ[/dir]");
    } else if (basic) {
        AddTxt = "[dir=500,350][/dir]";
        AddText(AddTxt);
    } else {
        txt2 = prompt("Shockwave�ļ��Ŀ�ȣ��߶�", "500,350");
        if (txt2 != null) {
            txt = prompt("Shockwave�ļ��ĵ�ַ", "�������ַ");
            if (txt != null) {
                if (txt2 == "") {
                    AddTxt = "[dir=500,350]" + txt;
                    AddText(AddTxt);
                    AddTxt = "[/dir]";
                    AddText(AddTxt);
                } else {
                    AddTxt = "[dir=" + txt2 + "]" + txt;
                    AddText(AddTxt);
                    AddTxt = "[/dir]";
                    AddText(AddTxt);
                }
            }
        }
    }
}


function Cra() {
    var FoundErrors = '';
    var enterURL = prompt(text_enter_ra, "http://");
    if (!enterURL) {
        FoundErrors += "\n" + error_no_url;
    }
    if (FoundErrors) {
        alert("����" + FoundErrors);
        return;
    }
    var ToAdd = "[RA]" + enterURL + "[/RA]";
    document.tawInformationForm.body.value += ToAdd;
    document.tawInformationForm.body.focus();
}


function Cfanzi() {
    fontbegin = "[xray]";
    fontend = "[/xray]";
    fontchuli();
}

function Cwma() {
    var FoundErrors = '';
    var enterURL = prompt(text_enter_wma, "http://");
    if (!enterURL) {
        FoundErrors += "\n" + error_no_url;
    }
    if (FoundErrors) {
        alert("����" + FoundErrors);
        return;
    }
    var ToAdd = "[wma]" + enterURL + "[/wma]";
    document.tawInformationForm.body.value += ToAdd;
    document.tawInformationForm.body.focus();
}


function Cmarquee() {
    fontbegin = "[move]";
    fontend = "[/move]";
    fontchuli();
}

function Cfly() {
    fontbegin = "[fly]";
    fontend = "[/fly]";
    fontchuli();
}

function paste(text) {
    if (opener.document.tawInformationForm.body.createTextRange && opener.document.tawInformationForm.body.caretPos) {
        var caretPos = opener.document.tawInformationForm.body.caretPos;
        caretPos.text = caretPos.text.charAt(caretPos.text.length - 1) == ' ' ?
            text + ' ' : text;
    } else opener.document.tawInformationForm.body.value += text;
    opener.document.tawInformationForm.body.focus(caretPos);
}

function showsize(size) {
    fontbegin = "[size=" + size + "]";
    fontend = "[/size]";
    fontchuli();
}

function showfont(font) {
    fontbegin = "[font=" + font + "]";
    fontend = "[/font]";
    fontchuli();
}

function showcolor(color) {
    fontbegin = "[color=" + color + "]";
    fontend = "[/color]";
    fontchuli();
}

function fontchuli() {
    if ((document.selection) && (document.selection.type == "Text")) {
        var range = document.selection.createRange();
        var ch_text = range.text;
        range.text = fontbegin + ch_text + fontend;
    } else {
        document.tawInformationForm.body.value = fontbegin + document.tawInformationForm.body.value + fontend;
        document.tawInformationForm.body.focus();
    }
}

function Cguang() {
    var FoundErrors = '';
    var enterSET = prompt(text_enter_guang1, "255,red,2");
    var enterTxT = prompt(text_enter_guang2, "����");
    if (!enterSET) {
        FoundErrors += "\n" + error_no_gset;
    }
    if (!enterTxT) {
        FoundErrors += "\n" + error_no_gtxt;
    }
    if (FoundErrors) {
        alert("����" + FoundErrors);
        return;
    }
    var ToAdd = "[glow=" + enterSET + "]" + enterTxT + "[/glow]";
    document.tawInformationForm.body.value += ToAdd;
    document.tawInformationForm.body.focus();
}

function Cying() {
    var FoundErrors = '';
    var enterSET = prompt(text_enter_guang1, "255,blue,1");
    var enterTxT = prompt(text_enter_guang2, "����");
    if (!enterSET) {
        FoundErrors += "\n" + error_no_gset;
    }
    if (!enterTxT) {
        FoundErrors += "\n" + error_no_gtxt;
    }
    if (FoundErrors) {
        alert("����" + FoundErrors);
        return;
    }
    var ToAdd = "[SHADOW=" + enterSET + "]" + enterTxT + "[/SHADOW]";
    document.tawInformationForm.body.value += ToAdd;
    document.tawInformationForm.body.focus();
}

ie = (document.all) ? true : false
if (ie) {
    function ctlent(eventobject) {
        if (event.ctrlKey && window.event.keyCode == 13) {
            this.document.tawInformationForm.submit();
        }
    }
}

function DoTitle(addTitle) {
    var revisedTitle;
    var currentTitle = document.tawInformationForm.subject.value;
    revisedTitle = currentTitle + addTitle;
    document.tawInformationForm.subject.value = revisedTitle;
    document.tawInformationForm.subject.focus();
    return;
}

function insertsmilie(smilieface) {

    document.tawInformationForm.body.value += smilieface;
}

function gopreview() {
    document.forms[1].title.value = document.forms[0].subject.value;
    document.forms[1].body.value = document.forms[0].body.value;
    var popupWin = window.open('preview.jsp', 'preview_page', 'scrollbars=yes,width=750,height=450');
    document.forms[1].submit()
}

function openScript(url, width, height) {
    var Win = window.open(url, "openScript", 'width=' + width + ',height=' + height + ',resizable=1,scrollbars=yes,menubar=no,status=yes');
}