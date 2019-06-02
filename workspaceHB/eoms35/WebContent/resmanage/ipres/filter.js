function isSelectCity(formName,selectName)//是否选择了城市
{
	if (formName.fi_city.value=="0")
	{
		alert("你没有选择城市");
		formName.fi_city.options[0].selected=true;
		formName.fi_city.focus();
		return;
	}
}
