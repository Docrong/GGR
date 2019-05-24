/*
"层次关系勾选"程序代码作者:翟志荣,wenever@netease.com,2001-1-14
以下代码不保留任何权利,支持javascript代码公开可重用
*/

var identifyletter="L";
function getlevel(str1,str2)   //辅助函数，得到str2在str1中出现次数（得到层次数）
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

function downlevel_allselected(box)   //辅助函数，下层全选flag返回true,否则false
{
    var flag=true;	     //falg为true时代表所有下层选中
    for (var i=0;i<document.all.tags("INPUT").length;i++)  //判断下层是否全选
   {
    var e = document.all.tags("INPUT")[i];
    if ((e.name.indexOf(box) == 0 ) & (e.name!=box) & (!e.checked)) {flag=false;break;}  //下层不是全选,flag=fales
   }
   return(flag);
}
function downlevel_allunselected(box)   //辅助函数，下层全不选flag返回true,否则false
{
    var flag=true;	     //falg为true时代表所有下层全不选中
    for (var i=0;i<document.all.tags("INPUT").length;i++)  //判断下层是否全选
   {
    var e = document.all.tags("INPUT")[i];
    if ((e.name.indexOf(box) == 0 ) & (e.name!=box) & (e.checked)) {flag=false;break;}  //下层期中一个被选,flag=fales
   }
   return(flag);
}
function checkclick(box){    //勾选时，判断其它可选框与所选的box的关系，并打上勾或去除勾
  for (var i=0;i<document.all.tags("INPUT").length;i++)
   {
    var e = document.all.tags("INPUT")[i];
    if ((e.name.indexOf(box) == 0) & (e.name.substring(box.length,box.length+1)==identifyletter)) {e.checked=true}  //下层勾选
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
       e=eval("document.all."+checktemp); //e是上层
       if (downlevel_allselected(e.name))  //e的下层全选
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
function checkunclk(box){    //去除勾选时，判断其它可选框与所选的box的关系，并打上勾或去除勾
/*
    for (var i=document.all.tags("INPUT").length-1;i>=0;i--)   //上层去除
   {
    var e = document.all.tags("INPUT")[i];
    alert (e.name.lastIndexOf(identifyletter));
    if ((box.indexOf(e.name) == 0) & (e.name!=box))    
     {
     if (downlevel_allunselected(e.name)) {e.indeterminate=false;e.checked=false} else {e.indeterminate=true;e.checked=false}
     }
   }
*/
//    if (downlevel_allselected(box))    //下层全选时，下层去除勾选
//    {
    for (var i=0;i<document.all.tags("INPUT").length;i++)
      {
	var e = document.all.tags("INPUT")[i];
	if ((e.name.indexOf(box) == 0) & (e.name.substring(box.length,box.length+1)==identifyletter) ) {e.indeterminate=false;e.checked=false}
      }
//    }
//上层去除勾选或勾选模糊的情况
   checktemp=box;
   var indexvalue=checktemp.length;
   while (checktemp.length>1)
     {
     var indexvalue = checktemp.lastIndexOf(identifyletter);
     checktemp=checktemp.substr(0,indexvalue);
     if (checktemp!="")
       {
       e=eval("document.all."+checktemp); //e是上层
       if (downlevel_allunselected(e.name))
        {e.checked=false;e.indeterminate=false;}
       else
        {e.checked=false;e.indeterminate=true;}
       }
     }
}

function show(chkbox)
{
 if (chkbox.checked) {checkclick(chkbox.name)}   // 打勾的情况
  else {checkunclk(chkbox.name)}  //去除勾的情况
}