<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/header_eoms.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<style type="text/css">
body {
	cursor: move;
}
</style>
<script type="text/javascript">
drag = 0
move = 0
function init() {
	window.document.onmousemove = mouseMove;
	window.document.onmousedown = mouseDown;
	window.document.onmouseup = mouseUp;
	window.document.ondragstart = mouseStop;
}
function mouseDown(e) {
	var ev = window.event||e;
	var mousePos = mouseCoords(ev);
    if (drag) {
        clickleft = mousePos.x - parseInt(dragObj.style.left);
        clicktop = mousePos.y - parseInt(dragObj.style.top);
        dragObj.style.zIndex += 1;
        move = 1;
    }
	return false;
}
function mouseStop(e) {
	var ev = window.event||e;
    ev.returnValue = false
}
function mouseMove(e) {
	var ev = window.event||e;
	var mousePos = mouseCoords(ev);
    if (move) {
        dragObj.style.left = mousePos.x - clickleft;
        dragObj.style.top = mousePos.y - clicktop;
    }
	return false;
}
function mouseUp(e) {
    move = 0
}
function mouseCoords(ev){
	if(ev.pageX || ev.pageY){
		return {x:ev.pageX, y:ev.pageY};
	}
	return {
		x:ev.clientX + document.body.scrollLeft - document.body.clientLeft,
		y:ev.clientY + document.body.scrollTop - document.body.clientTop
	};
}
</script>
</head>
<body bgcolor="#ffffff" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="init()" oncontextmenu="return true;" ondragstart="return false" onselectstart ="return false" onbeforecopy="return false">
<div id='hiddenPic' style='position:absolute; left:433px; top:258px; width:77px; height:91px; z-index:1; visibility: hidden;'>
	<img id='images2' name='images2' src='${app}/images/${module}.gif' border='0'>
</div>
<div id='block1' onmouseout="javascript:drag=0;" onmouseover='javascript:dragObj=block1; drag=1;' 
		style='z-index:10; height: 60; left: 0; position: absolute; top: 0; width: 120'>
	<dd><img id = 'images1' name='images1' src='${app}/images/${module}.gif' border='0'></dd>
</div>
<%@ include file="/common/footer_eoms.jsp"%>
