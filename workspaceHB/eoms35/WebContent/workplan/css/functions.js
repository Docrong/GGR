/***************************
// ����ҳ�湫�ù���
/***************************/

//����ģ̬����
function popupModal(sURL,params){
	if (params)
	{
		dWin = showModalDialog(sURL, window, params);
	}
	else{
		dWin = showModalDialog(sURL, window, "status:no;help:no;scroll:auto;resizable:yes;dialogHeight:450px;dialogWidth:480px;");
	}
}

function JustifyNull1(field)
{
  var Ret = true
  var str = "" + field.value
  if(str.length)
  {
    for(var i=0;i<str.length;i++)
    if(str.charAt(i)!=" ")
      break
    if(i>=str.length)
      field.value = ""
  }
  if (field.value.length==0)
    Ret = false
  return (Ret)
}// �ж������ֶ��Ƿ�Ϊ��

function  compareLength( field,minLength,maxLength,msg )
{
  var totallength=0;

  for (var i=0;i<field.value.length;i++)
  {
    var str = field.value;
    var intCode=str.charCodeAt(i);

    if (intCode>=0&&intCode<=128)
    {
      totallength=totallength+1;	//�����ĵ����ַ����ȼ� 1
    }
    else
    {
      totallength=totallength+2;	//�����ַ�������� 2
    }
    if ( totallength<minLength||totallength>maxLength )
    {
      alert( msg );
      field.focus();
      return false;
    }
  } //end for
  return true;
} //Ӣ���ַ��������ַ����ȵıȽ�

