/********************************************************************************
 Copyright (c) 2002,������ͨ������ҵ��IP����
 All rights reserved.
 Filename ��comm.js
 Abstract ���������ͽṹ���ݲ�����
 Version����2.0
 Author   ��Liu Guoyuan
 Finished Date ��2002-08-30
 Last Modified ��2003-01-16

 ********************************************************************************/

function hasSubNode(obj) {
//�ж���ͼ�˵���ѡ�����Ƿ����ӽ��
    var nodeIndex = obj.selectedIndex;
    var strNodeName = obj.options[nodeIndex].text;
    var curNodeClass = parseInt(strNodeName.substring(0, 1));//ȡ��ǰ��㼶��
    if (nodeIndex == obj.length - 1) { //���һ���ڵ�ʱ�������ж�
        return false;
    } else {
        var nextNodeClass = parseInt(obj.options[nodeIndex + 1].text.substring(0, 1));//ȡ��һ����㼶��
        if (nextNodeClass > curNodeClass) {//���ӽ��
            return true;
        } else {
            return false;
        }
    }
}

function builderTree(sourceObject, targetObject) {
//�������Ͳ˵�
//���ݸ�ʽ��ID��CLASS��NAME;  ��ӦIDֵ����������
//�� 1,1,ȫ��;2,2,����;
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