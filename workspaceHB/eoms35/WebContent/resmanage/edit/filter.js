function isSelectCity(formName, selectName)//�Ƿ�ѡ���˳���
{
    if (formName.fi_city.value == "0") {
        alert("��û��ѡ�����");
        formName.fi_city.options[0].selected = true;
        formName.fi_city.focus();
        return;
    }
}
