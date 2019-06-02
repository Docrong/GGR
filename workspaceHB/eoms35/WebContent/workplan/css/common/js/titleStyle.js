/*
------------------------------------------------------------------------------------
Copyright (c) 2002-2005,亿阳信通网络事业部IP网管
All rights reserved.
Filename ：style.js
Abstract ：替代IE默认Alt 或 Title标签显示方式
Version　：1.2
Author   ：Liu Guoyuan
Finished Date ：2002-12-24
Last Modified ：2004-03-22

使用方法：
<tr onmouseover=ShowPop() onmouseout=HidePop() title="test<br>test">
  <td>test</td>
</tr>
-------------------------------------------------------------------------------------
*/

//默认设置定义.
pop_tPopWait=100;//停留pop_tPopWait毫秒后显示提示。
pop_tPopShow=10000;//显示pop_tPopShow毫秒后关闭提示
pop_showPopStep=100;
pop_Opacity=99;

//内部变量定义
pop_sPop=null;
pop_tFadeOut=null;
pop_tFadeIn=null;
pop_tFadeWaiting=null;

document.write("<style type='text/css' id='defaultPopStyle'>");
document.write(".cPopText {  background-color: #F8F8F5;color:#000000; border: 1px #000000 solid;font-color: font-size: 12px; padding-right: 4px; padding-left: 4px; height: 20px; padding-top: 2px; padding-bottom: 2px; filter: Alpha(Opacity=0)}");
document.write("</style>");
document.write("<div id='dypopLayer' style='position:absolute;z-index:1000;' class='cPopText'></div>");

function ShowPop(){
	var o=event.srcElement;
	var pElement=o.parentElement;
	if ((pElement.title!=null && pElement.title!="")||(pElement.dypop!=null && pElement.dypop!="")) o=pElement;
	MouseX=event.x;
	MouseY=event.y;
 	if(o.title!=null && o.title!=""){o.dypop=o.title;o.title=""};
	pop_sPop=o.dypop;
	clearTimeout(pop_tFadeOut);
	clearTimeout(pop_tFadeIn);
	clearTimeout(pop_tFadeWaiting);	
	if(pop_sPop==null || pop_sPop=="") {
		dypopLayer.innerHTML="";
		dypopLayer.style.filter="Alpha()";
		dypopLayer.filters.Alpha.opacity=0;	
	}
	else {
		if(o.dyclass!=null) popStyle=o.dyclass 
		else popStyle="cPopText";
		ShowIt();
	}		
}

function ShowIt(){
		dypopLayer.className=popStyle;
		dypopLayer.innerHTML=pop_sPop;

		popWidth=dypopLayer.clientWidth;
		popHeight=dypopLayer.clientHeight;

		if(MouseX+12+popWidth>document.body.clientWidth) popLeftAdjust=-popWidth-12
			else popLeftAdjust=0;
		if(MouseY+12+popHeight>document.body.clientHeight) popTopAdjust=-popHeight-12
			else popTopAdjust=0;
		dypopLayer.style.left=MouseX+12+document.body.scrollLeft+popLeftAdjust;
		dypopLayer.style.top=MouseY+12+document.body.scrollTop+popTopAdjust;
		if (parseInt(dypopLayer.style.left)<0) dypopLayer.style.left=0
		if (parseInt(dypopLayer.style.top)<0) dypopLayer.style.top=0		

		dypopLayer.style.filter="Alpha(Opacity=0)";
		dypopLayer.style.display="";
		FadeOut();
}

function FadeOut(){
	if(dypopLayer.filters.Alpha.opacity<pop_Opacity) {
		dypopLayer.filters.Alpha.opacity+=pop_showPopStep;
		pop_tFadeOut=setTimeout("FadeOut()",1);
	}
	else {
		dypopLayer.filters.Alpha.opacity=pop_Opacity;
		pop_tFadeWaiting=setTimeout("HidePop()",pop_tPopShow);
	}
}
function HidePop(){
	clearTimeout(pop_tFadeOut);
	clearTimeout(pop_tFadeIn);
	clearTimeout(pop_tFadeWaiting);	
	//dypopLayer.style.display="none";
	dypopLayer.filters.Alpha.opacity=0;
	dypopLayer.width="";
	dypopLayer.style.left=-1000;
	dypopLayer.style.top=-1000;
}