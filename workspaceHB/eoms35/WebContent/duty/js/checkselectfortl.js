/*
"��ι�ϵ��ѡ"�����������:��־��,wenever@netease.com,2001-1-14
���´��벻�����κ�Ȩ��,֧��javascript���빫��������
*/

var identifyletter="L";
function getlevel(str1,str2)   //�����������õ�str2��str1�г��ִ������õ��������
{
var indexvalue=0;
var s = str1.indexOf(str2);
var len =str1.length;
var count=0;
while (indexvalue<=len)
  {
   checktemp=str1.indexOf(str2,indexvalue);
   if (checktemp!=-1) {count++; indexvalue=checktemp+1}
   else {return(count)}
  }
}

function downlevel_allselected(box)   //�����������²�ȫѡflag����true,����false
{
    var flag=true;	     //falgΪtrueʱ���������²�ѡ��
    for (var i=0;i<document.all.tags("INPUT").length;i++)  //�ж��²��Ƿ�ȫѡ
   {
    var e = document.all.tags("INPUT")[i];
    if ((e.name.indexOf(box) == 0 ) & (e.name!=box) & (!e.checked)) {flag=false;break;}  //�²㲻��ȫѡ,flag=fales
   }
   return(flag);
}
function downlevel_allunselected(box)   //�����������²�ȫ��ѡflag����true,����false
{
    var flag=true;	     //falgΪtrueʱ���������²�ȫ��ѡ��
    for (var i=0;i<document.all.tags("INPUT").length;i++)  //�ж��²��Ƿ�ȫѡ
   {
    var e = document.all.tags("INPUT")[i];
    if ((e.name.indexOf(box) == 0 ) & (e.name!=box) & (e.checked)) {flag=false;break;}  //�²�����һ����ѡ,flag=fales
   }
   return(flag);
}
function checkclick(box){    //��ѡʱ���ж�������ѡ������ѡ��box�Ĺ�ϵ�������Ϲ���ȥ����
  for (var i=0;i<document.all.tags("INPUT").length;i++)
   {
    var e = document.all.tags("INPUT")[i];
    if ((e.name.indexOf(box) == 0) & (e.name.substring(box.length,box.length+1)==identifyletter)) {e.checked=true}  //�²㹴ѡ
   }
   /*
   checktemp=box;
   var indexvalue=checktemp.length;
   while (checktemp.length>1)
     {
     var indexvalue = checktemp.lastIndexOf(identifyletter);
     checktemp=checktemp.substr(0,indexvalue);
     if (checktemp!="")
       {
       //alert (checktemp);alert(checktemp.length);
       e=eval("document.all."+checktemp); //e���ϲ�
       if (downlevel_allselected(e.name))  //e���²�ȫѡ
        {e.checked=true;e.indeterminate=false;}
       else
        {e.checked=false;e.indeterminate=true;}
       }
     }
    */
}
function findfather(box){
checktemp=box;
var indexvalue=checktemp.length;
while (checktemp.length>1)
  {
  var indexvalue = checktemp.lastIndexOf(identifyletter);
  checktemp=checktemp.substr(0,indexvalue);
  //if (checktemp!="") {alert (checktemp);}
  }
}
function checkunclk(box){    //ȥ����ѡʱ���ж�������ѡ������ѡ��box�Ĺ�ϵ�������Ϲ���ȥ����
/*
    for (var i=document.all.tags("INPUT").length-1;i>=0;i--)   //�ϲ�ȥ��
   {
    var e = document.all.tags("INPUT")[i];
    alert (e.name.lastIndexOf(identifyletter));
    if ((box.indexOf(e.name) == 0) & (e.name!=box))    
     {
     if (downlevel_allunselected(e.name)) {e.indeterminate=false;e.checked=false} else {e.indeterminate=true;e.checked=false}
     }
   }
*/
//    if (downlevel_allselected(box))    //�²�ȫѡʱ���²�ȥ����ѡ
//    {
    for (var i=0;i<document.all.tags("INPUT").length;i++)
      {
	var e = document.all.tags("INPUT")[i];
	if ((e.name.indexOf(box) == 0) & (e.name.substring(box.length,box.length+1)==identifyletter) ) {e.indeterminate=false;e.checked=false}
      }
//    }
//�ϲ�ȥ����ѡ��ѡģ�������
   checktemp=box;
   var indexvalue=checktemp.length;
   while (checktemp.length>1)
     {
     var indexvalue = checktemp.lastIndexOf(identifyletter);
     checktemp=checktemp.substr(0,indexvalue);
     if (checktemp!="")
       {
       e=eval("document.all."+checktemp); //e���ϲ�
       if (downlevel_allunselected(e.name))
        {e.checked=false;e.indeterminate=false;}
       else
        {e.checked=false;e.indeterminate=true;}
       }
     }
}

function show(chkbox)
{
 if (chkbox.checked) {checkclick(chkbox.name)}   // �򹴵����
  else {checkunclk(chkbox.name)}  //ȥ���������
}