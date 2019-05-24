var pic1="/images/icons/mod.gif";
var pic2="/images/icons/move.gif";

//视图中文档层的展开与关闭(对tr的display属性控制)
function showhide(trname)
{
current_pic=eval("document.forms[0]."+(trname.replace(/t/g,"a")));
var showtr=true;
var len=table1.children[0].children.length;
var curlevel=getlevel(trname,"t");
var CurObject=document.getElementById(trname);
trObject=CurObject.nextSibling
//alert (trObject.id);
if ((trObject.id.indexOf(trname)!=0 ) | (trObject.id.substring(trname.length,trname.length+1)!="t")) return;
while(trObject)
  {
  if (trObject.style.display=="") {showtr=false} //子层已打开
  if (showtr==false)
    {
    trObject.style.display="none";
    temppic=document.getElementById(trObject.id.replace(/t/g,"a"));
    if (temppic) {temppic.src=pic1}
    }
  else
    {
    if (getlevel(trObject.id,"t")-1==curlevel) {trObject.style.display = "";}
    }
  trObject=trObject.nextSibling
  if (trObject) {if(trObject.id.indexOf(trname)!=0) break}
  }
if (showtr==false) {current_pic.src=pic1} else {current_pic.src=pic2};
}


//文档列表的全部展开和折叠
//展开和折叠
function openreletive()
{
trlength=document.images.length;
for (k=0;k<trlength;k++)
  {
  TRer=document.images[k];
  if (TRer.id.indexOf("a")==0 & TRer.style.cursor=="hand") {TRer.click()}
  }
}
//展开全部
function openall()
{
imglength=document.images.length;
for (k=0;k<imglength;k++)
  {
  TRer=document.images[k];
  if (TRer.id.indexOf("a")==0) {TRer.src=pic2}
  }
var len=table1.children[0].children.length;
for (i=0;i<len;i++)
  {
  table1.children[0].children[i].style.display = ""
  }
setTableColor(table1,1);
}
//折叠全部
function closeall()
{
imglength=document.images.length;
for (k=0;k<imglength;k++)
  {
  TRer=document.images[k];
  if (TRer.id.indexOf("a")==0) {TRer.src=pic1}
  }
var len=table1.children[0].children.length;
var firstlevel=getlevel(table1.children[0].children[1].id,"t")
for (i=1;i<len;i++)
  {
  trObject=table1.children[0].children[i];
  if (getlevel(trObject.id,"t")>firstlevel) {trObject.style.display = "none"}
  }
setTableColor(table1,1);
}
//展开下一个
function opennext()
{
var firstopened=999999;
var FirstOne=null;
imglength=document.images.length;
for (k=0;k<imglength;k++)
  {
  TRer=document.images[k];
  if (TRer.id.indexOf("a")==0 & TRer.style.cursor=="hand")
    {
     if (TRer.src.indexOf(pic2)!=-1) {firstopened=k} else
     if (TRer.src.indexOf(pic1)!=-1)
      {
      	if(!FirstOne) {FirstOne=TRer}
     	if(k>firstopened){TRer.click();return}
      }
    }
  }
if (FirstOne!=null) {FirstOne.click();return;}
}
//展开一个
function openone()
{
imglength=document.images.length;
for (k=0;k<imglength;k++)
  {
  TRer=document.images[k];
  if (TRer.id.indexOf("a")==0 & TRer.style.cursor=="hand" & TRer.src.indexOf(pic1)!=-1) {TRer.click();return;}
  }
}
//折叠一个
function closenext()
{
imglength=document.images.length;
for (k=imglength-1;k>=0;k--)
  {
  TRer=document.images[k];
  if (TRer.id.indexOf("a")==0 & TRer.style.cursor=="hand" & TRer.src.indexOf(pic2)!=-1) {TRer.click();return;}
  }
}

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



///////////////适用于代理生成///////////////////////
function showhideagent(trname)
{

current_pic=eval("document.forms[0]."+(trname.replace(/t/g,"a")));
var showtr=true;
var len=table1.children[0].children.length;
var curlevel=getlevel(trname,"t");
for (i=0;i<len;i++)
  {
  trObject=table1.children[0].children[i];
  if ((trObject.id.indexOf(trname)==0 ) & (trObject.id.substring(trname.length,trname.length+1)=="t"))
    {
    //alert (trObject.id+"  "+trObject.id.substring(trname.length,trname.length+1));
     if (trObject.style.display=="") {showtr=false} //子层已打开
     if (showtr==false)
       {
       trObject.style.display="none";
       temp=trObject.id.replace(/t/g,"a");
       for (j=0;j<document.images.length;j++)
         {
         if (document.images[j].id==temp) {document.images[j].src=pic1}
         }       
       }
     else
       {
       if (getlevel(trObject.id,"t")-1==curlevel) {trObject.style.display = "";}
       }
    }
  }
if (showtr==false) {current_pic.src=pic1} else {current_pic.src=pic2};

}