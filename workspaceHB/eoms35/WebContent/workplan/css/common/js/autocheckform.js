/********************************************************************************
 Copyright (c) 2002,������ͨ������ҵ��IP����
 All rights reserved.
 Filename ��checkform.js
 Abstract ���Զ������ģ��
 Version����1.0
 Author   ��Liu Guoyuan
 Finished Date ��2003-02-25
 Last Modified ��2003-02-25

 ���ܣ�
 �����������Զ�����
 <form onSubmit="AutoCheckForm(this)">
 ********************************************************************************/

function AutoCheckForm(formObject) {//�Զ�����
    var formObjectCount = formObject.length; //��Ԫ������
    var str = "";
    var conditionStr;   //����֤����
    var conditionItem, conditionValue;
    for (i = 0; i < formObjectCount; i++) {
        with (formObject[i]) {
            conditionStr = getAttribute("condition")
            objCnName = getAttribute("cnname")
            if (conditionStr != null) {
                conditionStr = conditionStr.split(";")
                if (conditionStr.length > 1) //����
                {

                } else {
                    conditionItem = conditionStr[0].split("=")[0];
                    conditionValue = conditionStr[0].split("=")[1];
                    //if (conditionStr.length<=1) {return 2;}//��������
                    if (!CheckFormItem(formObject[i].name, objCnName, conditionItem, conditionValue)) {
                        return false
                    }
                    ;
                }
            }
        }
    }
    return true;
}

function CheckFormItem(objName, objCnName, conditionItem, conditionValue) {
    switch (conditionItem) {
        case "null":
            if (conditionValue == "false")
                return (CheckIsNull
            (objName);
        else
            return true;
            break;
        case "email":
            return CheckIsEmail(objName, conditionValue);
            break;
    }
}