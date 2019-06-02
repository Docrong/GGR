<%@ include file="/common/taglibs.jsp"%>
<html>
<head>
<title>静态图</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<style type="text/css">
body {
	cursor: move;
}
</style>
<SCRIPT language=JavaScript>
drag = 0
move = 0
function init() {
    window.document.onmousemove = mouseMove
    window.document.onmousedown = mouseDown
    window.document.onmouseup = mouseUp
    window.document.ondragstart = mouseStop
}
function mouseDown() {
    if (drag) {
        clickleft = window.event.x - parseInt(dragObj.style.left)
        clicktop = window.event.y - parseInt(dragObj.style.top)
        dragObj.style.zIndex += 1
        move = 1
    }
}
function mouseStop() {
    window.event.returnValue = false
}
function mouseMove() {
    if (move) {
        dragObj.style.left = window.event.x - clickleft
        dragObj.style.top = window.event.y - clicktop
    }
}
function mouseUp() {
    move = 0
}
</SCRIPT>
<script language="JavaScript" type="text/JavaScript">
<!--
function MM_reloadPage(init) {  //reloads the window if Nav4 resized
  if (init==true) with (navigator) {if ((appName=="Netscape")&&(parseInt(appVersion)==4)) {
    document.MM_pgW=innerWidth; document.MM_pgH=innerHeight; onresize=MM_reloadPage; }}
  else if (innerWidth!=document.MM_pgW || innerHeight!=document.MM_pgH) location.reload();
}
MM_reloadPage(true);
//-->
</script>
</head>
<body bgcolor="#ffffff" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="init()" oncontextmenu="return true;" ondragstart="return false" onselectstart ="return false" onselect="document.selection.empty()" oncopy="document.selection.empty()" onbeforecopy="return false"onmouseup="document.selection.empty()">
<noscript><iframe frameborder="0" src=*></iframe>
</noscript>
<div id='hiddenPic' style='position:absolute; left:433px; top:258px; width:77px; height:91px; z-index:1; visibility: hidden;'>
	<img name='images2' src='${app}/images/nop.gif' border='0'>
</div>
<div id='block1' onmouseout="javascript:drag=0;" onmouseover='javascript:dragObj=block1; drag=1;' 
		style='z-index:10; height: 60; left: 0; position: absolute; top: 0; width: 120'>
	<dd><img name='images1' src='${app}/images/nop.gif' border='0'></dd>
</div>

</body>
</html>
