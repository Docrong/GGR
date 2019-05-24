/*-----------------------------------------
Any problem may contact wenever@netease.com
------------------------------------------*/
var msg='操作已发送,请稍候\n如果长时间没有响应,\n请刷新页面再试一次.'
var refreshflag=false	//是否刷新父页面标记
var clickedflag=false	//点请求是否发送击标记
var unlockflag=true	//解锁标记(默记点流览器X时亦解锁)

//刷新父窗口
function refreshfather(){
if(refreshflag){
  if (window.opener){
   if(window.opener.parent.view)
    {
    window.opener.parent.view.location.reload()
    }
   else if(window.opener.parent.mainframe)
    {
     if(window.opener.parent.mainframe.view)
      window.opener.parent.mainframe.view.location.reload()
    }
   else if(window.opener.document.title=="值班日志")
    {
      window.opener.location.reload()
    }
  }
  else if(parent.view)
  {parent.view.location.reload()}
 }
}

//操作按钮背景色
topbar_color="#cccccc"
topbar_color_border="#cccccc"
lastselecteddocument=null
lastselecteddocumentclass=null

//操作按钮凹凸效果
function mOver_button(src) {
    src.style.cursor = 'hand';
    src.className="inner"
}

function mOut_button(src) {
    src.className=""
  }
  
function mClk_button(src,Obj) {
    src.className="outer";
    Obj.click();
//    window.location=url;
  }

//  视图表格颜色交错
function setTableColor(tablename,beginline){ 
  var len=tablename.children[0].children.length;

  var k=1;
  for (i=beginline;i<len;i++)
  {
  trObject=tablename.children[0].children[i];
  if (trObject.style.display!="none")
    {
    if (k%2==1) {trObject.className="color1";k++;} else {trObject.className="color2";k++}
    }
  }
}

//表格重定义
function formatTableBorder(tablename,border,cellpadding,cellspacing){  
  tablename.borderColorLight="#000000";
  tablename.borderColorDark="#ffffff";
  if (!border) {border=0};
  tablename.border=border;
  if (!cellspacing) {cellspacing=0};
  tablename.cellSpacing=cellspacing;
  if (!cellpadding) {cellpadding=2};
  tablename.cellPadding=cellpadding;
}

//新增文档
function creatdocument(form,tar,width,height,top,left)
{
if (!width){width=screen.width-9}
if (!height){height=screen.availheight-28}
if (!top){top=0}
if (!left){left=0}

var pathname = window.location.pathname; 
var newurl = pathname.substring(0,(pathname.lastIndexOf('.nsf')+4))+'/'+ form + '?openForm&random='+Math.random();
if (tar) 
{win1=window.open(newurl,tar,"menubar=yes,scrollbars=no,resizable=yes,width="+width+",height="+height+",top=0,left=0");win1.focus()}
else {win1=window.open(newurl,"creatwindow","menubar=yes,scrollbars=no,resizable=yes,width="+width+",height="+height+",top=0,left=0");win1.focus()};

//win1=window.open(newurl,"creatwindow","menubar=no,scrollbars=no,resizable=yes,width="+width+",height="+height+",top="+top+",left="+left );

//win=window.open(newurl,"","menubar=no")//," fullscreen");
//win.moveTo(0,0);
//win.resizeTo(screen.width,screen.availheight)
// parent.location.href = newurl;
}

//在视图上打开文档
function opendocument(url,tar,width,height)
{
if (!width){width=screen.width-9}
if (!height){height=screen.availheight-28}
if (tar) 
{win1=window.open(url,tar,"menubar=yes,scrollbars=no,resizable=yes,width="+width+",height="+height+",top=0,left=0");win1.focus()}
else {win1=window.open(url,"creatwindow","menubar=yes,scrollbars=no,resizable=yes,width="+width+",height="+height+",top=0,left=0");win1.focus()};
}


//在视图上打开文档1
function opendocument2(url,tar)
{
if (tar) {win1=window.open(url,tar,"menubar=no,scrollbars=no,resizable=yes,width="+(screen.width-9)+",height="+(screen.availheight-28)+",top=0,left=0");
win1.focus()}
else {win1=window.open(url,"creatwindow1","menubar=no,scrollbars=no,resizable=no,width="+(1)+",height="+(1)+",top=0,left=0");
win1.focus();
};
}

//在统计结果
function opendocument1(url,tar)
{

if (tar) {win1=window.open(url,tar,"menubar=yes,scrollbars=yes,resizable=yes,width="+(screen.width-9)+",height="+(screen.availheight-78)+",top=0,left=0");
win1.focus()}
else {win1=window.open(url,"creatwindow","menubar=yes,scrollbars=yes,resizable=yes,width="+(screen.width-9)+",height="+(screen.availheight-78)+",top=0,left=0");
win1.focus()};
}

//预览文档
function previewdocument(url,obj)
{
selectdocument(obj);
if (parent.MenuFrame.document.all.previewmode.value!="false") {parent.preview.location=url}
}
//预览文档开关
function previewswitch(){
if (parent.document.all.tags("frameset")[2].rows!="1*,60%")
  {parent.document.all.tags("frameset")[2].rows="1*,60%";
   document.all.previewicon.src="/images/icons/previewoff.gif";
   parent.daohang.document.all.previewmode.value="true";
  }
else 
 {parent.document.all.tags("frameset")[2].rows="1*,0";
   document.all.previewicon.src="/images/icons/previewon.gif";
   parent.daohang.document.all.previewmode.value="false";
  }
}

//在视图上选择文档
function selectdocument(obj)
{
if (!obj) {obj=event.srcElement};
if (obj.parentElement.tagName=="TR" ) {obj=obj.parentElement};
if (obj.tagName!="TR") return;
if (lastselecteddocument) {lastselecteddocument.className=lastselecteddocumentclass}
lastselecteddocument=obj;
lastselecteddocumentclass=obj.className
obj.className="selecteddocument";
}

//全选,钩上当前面所有复选框
function selectall(obj)
{
  var checkit=true;
  for (var i=0;i<document.all.tags("INPUT").length;i++)
   {
    var e = document.all.tags("INPUT")[i];
    //if (e.type=="checkbox") e.checked=obj.checked;
    if (e.type=="checkbox" & checkit==true & e.checked==false) {e.checked=true;}
     else {checkit=false;e.checked=false}
   }
}
//主页导航连接
function homepage()
{
var protocol = parent.location.protocol;
var host = parent.location.host;
var url = protocol + '//' + host;
top.location.replace(url);
}

//导航连接
function navigate(url,tar){
var pathname = window.location.pathname; 
var tempurl = pathname.substring(0,(pathname.lastIndexOf('.nsf')+4));
var newurl = tempurl.substring(0,(tempurl.lastIndexOf('/')))+url;
if (tar) {
tar.location.href = newurl+'&random='+Math.random();
}
else {
parent.location.href = newurl+'&random='+Math.random();
   }
}

//密码修改
function passwordmodify(url)
{
var pathname = window.location.pathname; 
var tempurl = pathname.substring(0,(pathname.lastIndexOf('.nsf')+4));
var newurl = tempurl.substring(0,(tempurl.lastIndexOf('/')))+url;
// parent.location.href = newurl;
//win=window.open(newurl,'','toolbar=no,location=no,status=no,resizable=no,menubar=no,width=490,height=250')
//win=window.open(newurl);
win=window.open(newurl,'密码修改','status=no,resizable=no,scrollbars=no,left=260,top=100,width=334,height=200');
//win.moveTo(150,150);
}

//打开个人设置窗口
function settings(url)
{
var pathname = window.location.pathname; 
var tempurl = pathname.substring(0,(pathname.lastIndexOf('.nsf')+4));
var newurl = tempurl.substring(0,(tempurl.lastIndexOf('/')))+url;
win=window.open(newurl,'个人设置','status=no,resizable=yes,scrollbars=no,left=170,top=100,width=230,height=180');
//win.moveTo(150,150);
}

//打开帮助窗口
function openhelpwindow(url)
{
var pathname = window.location.pathname; 
var tempurl = pathname.substring(0,(pathname.lastIndexOf('.nsf')+4));
var newurl = tempurl.substring(0,(tempurl.lastIndexOf('/')))+url;
win=window.open(newurl,'helpwindow','status=no,resizable=yes,scrollbars=no,left=120,top=30,width=650,height=490');
}

//打开附件
function openAttach(){
var pathname = window.location.pathname; 
var attDB = pathname.substring(0,(pathname.lastIndexOf('.nsf')+4));

	docID = document.forms[0].AttDocID.value;
	if (docID !="") 
		var attWnd=window.open(attDB + "/All/" + docID + "?EditDocument" ,"att","width=400,height=600,scrollbars=no");
	else
		var attWnd=window.open(attDB + "/attach?OpenForm" ,"att","width=500,height=500,scrollbars=yes");

	attWnd.focus();
}

function openFaultAttach(){
var pathname = window.location.pathname; 
var attDB = pathname.substring(0,(pathname.lastIndexOf('.nsf')+4));

	
		var attWnd=window.open(attDB + "/faultAttach?OpenForm" ,"att","width=500,height=500,scrollbars=yes");
                attWnd.focus();
	
}

//显示时钟
function showhour(){if(!document.layers&&!document.all)
                   return
var Digital=new Date()
var hours=Digital.getHours()
var minutes=Digital.getMinutes()
var seconds=Digital.getSeconds()
               if(minutes<=9)
                           minutes="0"+minutes
               if(seconds<=9)
                           seconds="0"+seconds
//change font size here to your desire
myclock= hours+":"+minutes+":"+seconds
              if(document.layers){document.layers.liveclock.document.write(myclock)
                                 document.layers.liveclock.document.close()
                                 }else if(document.all)
                                 liveclock.innerHTML=myclock
              setTimeout("showhour()",1000)
}
//显示时钟12小时制
function showhour_org(){if(!document.layers&&!document.all)
                   return
var Digital=new Date()
var hours=Digital.getHours()
var minutes=Digital.getMinutes()
var seconds=Digital.getSeconds()
var dn="上午"
               if(hours>12){dn="下午"
                            hours=hours-12
                           }
               if(hours==0)
                            hours=12
               if(minutes<=9)
                           minutes="0"+minutes
               if(seconds<=9)
                           seconds="0"+seconds
//change font size here to your desire
myclock="<font style='font-size:9pt;color=white'><b>" + hours+":"+minutes+":"
+seconds+" "+dn+"</font>"
              if(document.layers){document.layers.liveclock.document.write(myclock)
                                 document.layers.liveclock.document.close()
                                 }else if(document.all)
                                 liveclock.innerHTML=myclock
              setTimeout("showhour()",1000)
}

//清除没找到文档字样
function clearnodocumenttext() {
if (document.all.tags("H2").length!=0){document.all.tags("H2")[0].innerText=""}
}
//上滚动
function scrolldown()
{
/*Layer1.scrollTop=30;
alert (Layer1.scrollTop);
alert (Layer1.scrollHeight);
alert (Layer1.scrollTop);*/
if (Layer1.scrollHeight>Layer1.clientHeight+Layer1.scrollTop) {Layer1.scrollTop=Layer1.scrollTop+Layer1.clientHeight}
}
//下滚动
function scrollup()
{
if (Layer1.scrollTop>0)
{Layer1.scrollTop=Layer1.scrollTop-Layer1.clientHeight}
}
//注销当前用户
function logout(){
var pathName = (window.location.href);
getUrl = pathName.substring(0,(pathName.lastIndexOf('.nsf')+4))
top.location = getUrl + "?logout"
}
//删除所选文档
function deleteselected(docid){
var form=window.document.forms[0]
var tempName=''
var DocCount=0;
for (var i=0;i<form.elements.length;i++)
{
	if(form.elements[i].type=='checkbox')
	   if(form.elements[i].checked & form.elements[i].value!="on")
	  {
             DocCount = DocCount+1
             if(tempName=='')  {tempName = form.elements[i].value}
             else
               	 {  tempName=tempName+','+form.elements[i].value}
       	  }
}
if (DocCount>150)
   {alert("您每次不能选取多于150的文档进行删除！")
    
   }
else if (DocCount==0)
   {alert("请选择要删除的文档！")
    
   }
else
   //删除选定的文档
  {
   var pathname = window.location.pathname; 
   var newurl = pathname.substring(0,(pathname.lastIndexOf('.nsf')+4))+'/deleteSelected?openAgent&select='+tempName+'&page='+docid; 
   alert (newurl);
   if (confirm("确定删除"+DocCount+"个文档?")) {
     self.location=newurl
   }
  }
}

//删除所选文档
function deleteselected(docid){
var form=window.document.forms[0]
var tempName=''
var DocCount=0;
for (var i=0;i<form.elements.length;i++)
{
	if(form.elements[i].type=='checkbox')
	   if(form.elements[i].checked & form.elements[i].value!="on")
	  {
             DocCount = DocCount+1
             if(tempName=='')  {tempName = form.elements[i].value}
             else
               	 {  tempName=tempName+','+form.elements[i].value}
       	  }
}
if (DocCount>25)
   {alert("您选择了"+DocCount+"个文档，每次不能选取多于25的文档进行删除！")
    
   }
else if (DocCount==0)
   {alert("请选择要删除的文档！")
    
   }
else
   //删除选定的文档
  {
   var pathname = window.location.pathname; 
   var newurl = pathname.substring(0,(pathname.lastIndexOf('.nsf')+4))+'/deleteSelected?openAgent&select='+tempName+'&page='+docid; 
  //alert (newurl);
   if (confirm("确定删除"+DocCount+"个文档?")) {
     self.location=newurl
   }
  }
}

//删除所选文档
function deleteit(){
var form=window.document.forms[0]
var tempName=''
var DocCount=0;
for (var i=0;i<form.elements.length;i++)
{
	if(form.elements[i].type=='checkbox')
	   if(form.elements[i].checked & form.elements[i].value!="on")
	  {
             DocCount = DocCount+1
             if(tempName=='')  {tempName = form.elements[i].value}
             else
               	 {  tempName=tempName+','+form.elements[i].value}
       	  }
}
if (DocCount>150)
   {alert("您每次不能选取多于150的文档进行删除！")
    
   }
else if (DocCount==0)
   {alert("请选择要删除的文档！")
    
   }
else
   //删除选定的文档
  {
   var pathname = window.location.pathname; 
   document.all.deletecontent.value=tempName;
   //alert (newurl);
   if (confirm("确定删除"+DocCount+"个文档?")) {
     alert (document.all.deletebutton.outerHTML)
     document.all.deletebutton.click()
   }
  }
}
//通用工单
function PrintSheet(){
var tempbg=document.body.background
document.body.background=""
SheetBar.style.display="none";
Layer1.style.overflow="";
self.print();
SheetBar.style.display="";Layer1.style.overflow="auto";
document.body.background=tempbg
}

//通用打印
function printit(){
var tempbg=document.body.background
document.body.background=""
topbar1.style.display="none";topbar2.style.display="none";;Layer1.style.overflow="";
self.print();
topbar1.style.display="";topbar2.style.display="";Layer1.style.overflow="auto";
document.body.background=tempbg
}
function showtime(){
ObjID=GetObjID(window.event.srcElement.name);

var newurl = "/js/calendar/seltime1.htm"
//var newurl = "/js/calendar/aa.htm"
//win=window.showModalDialog(newurl,window,"dialogWidth:310px;dialogHeight:200px;status:no;scrollBars:no;Resizeable:yes");
win=window.showModalDialog(newurl,window,"dialogWidth:310px;dialogHeight:260px;status:no;scrollBars:no;resizeable:yes");
if(win)
	window.document.forms[0].elements[ObjID].value=win;

}

function GetObjID(ObjName)
{
  for (var ObjID=0; ObjID < window.document.forms[0].elements.length; ObjID++)
    if ( window.document.forms[0].elements[ObjID].name == ObjName )
    {  return(ObjID);
       break;
    }
  return(-1);
}

//在视图上不同窗口打开不同文档
function opendifdoc(url,id,tar,width,height)
{
//window.opener.location.reload();
window.close(id)
if (!width){width=screen.width-9}
if (!height){height=screen.availheight-28}
if (tar) 
{win1=window.open(url,tar,"menubar=yes,scrollbars=no,resizable=yes,width="+width+",height="+height+",top=0,left=0");win1.focus()}
else  {win1=window.open(url,id,"menubar=yes,scrollbars=no,resizable=yes,width="+width+",height="+height+",top=0,left=0");win1.focus()};

}
//删除选中文档（在业务论坛调用）
function deleteselected2(docid){
var form=window.document.forms[0]
var tempName=''
var DocCount=0;
for (var i=0;i<form.elements.length;i++)
{
	if(form.elements[i].type=='checkbox')
	   if(form.elements[i].checked & form.elements[i].value!="on")
	  {
             DocCount = DocCount+1
             if(tempName=='')  {tempName = form.elements[i].value}
             else
               	 {  tempName=tempName+','+form.elements[i].value}
       	  }
}
if (DocCount>25)
   {alert("您选择了"+DocCount+"个文档，每次不能选取多于25的文档进行删除！")
    
   }
else if (DocCount==0)
   {alert("请选择要删除的文档！")
    
   }
else
   //删除选定的文档
  {
   var pathname = window.location.pathname; 
   var newurl = pathname.substring(0,(pathname.lastIndexOf('.nsf')+4))+'/deleteSelected2?openAgent&select='+tempName+'&page='+docid; 
   //alert (newurl);
   if (confirm("确定删除"+DocCount+"个文档?")) {
     self.location=newurl
   }
  }
}

function deletetask(ComName, DeptName){
var form=window.document.forms[0]
var tempName=''
var DocCount=0;
for (var i=0;i<form.elements.length;i++)
{
	if(form.elements[i].type=='checkbox')
	   if(form.elements[i].checked & form.elements[i].value!="on")
	  {
             DocCount = DocCount+1
             if(tempName=='')  {tempName = form.elements[i].value}
             else
               	 {  tempName=tempName+','+form.elements[i].value}
       	  }
}
if (DocCount>5)
   {alert("您选择了"+DocCount+"个作业，每次不能选取多于5个作业进行删除！")
    
   }
else if (DocCount==0)
   {alert("请选择要删除的作业！")
    
   }
else
   //删除选定的文档
  {
   var pathname = window.location.pathname; 
   var newurl = pathname.substring(0,(pathname.lastIndexOf('.nsf')+4))+'/DeleteDutyTask?openAgent&ComName='+ ComName + '&DeptName=' + DeptName + '&DocID=' + tempName; 
   //alert (newurl);
   if (confirm("确定删除"+DocCount+"个作业?")) {
     self.location=newurl
   }
  }
}

//删除已办文档
function DeleteNoTreated(docid){
var form=window.document.forms[0]
var tempName=''
var DocCount=0;
for (var i=0;i<form.elements.length;i++)
{
	if(form.elements[i].type=='checkbox')
	   if(form.elements[i].checked & form.elements[i].value!="on")
	  {
             DocCount = DocCount+1
             if(tempName=='')  {tempName = form.elements[i].value}
             else
               	 {  tempName=tempName+','+form.elements[i].value}
       	  }
}
if (DocCount>25)
   {alert("您选择了"+DocCount+"个文档，每次不能选取多于25的文档进行删除！")
    
   }
else if (DocCount==0)
   {alert("请选择要删除的文档！")
    
   }
else
   //删除选定的文档
  {
   var pathname = window.location.pathname; 
   var newurl = pathname.substring(0,(pathname.lastIndexOf('.nsf')+4))+'/DeleteNoTreated?openAgent&select='+tempName+'&page='+docid; 
   //alert (newurl);
   if (confirm("确定删除"+DocCount+"个文档?")) {
     self.location=newurl
   }
  }
}