<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Line Chart Sample - Anychart Flash Chart Component</title>
<link REL=STYLESHEET type="text/css" HREF="<%=request.getContextPath()%>/reportSwfs/theme/flashReport.css"/>
</head>
<body style="margin:0px;">
<div id="Layer1"></div>

<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,0,0" width="100%" height="100%" align="middle"><param name="allowScriptAccess" value="sameDomain" id="AnyChart"/>

<param name="movie" value="<%=request.getContextPath()%>/reportSwfs/swfs/3DPie.swf" />

<param name="FlashVars" value="XMLFile=<%=request.getContextPath()%>/reportSwfs/report/supplierkpi.xml">

<param name="quality" value="high" />

<param name="bgcolor" value="#ffffff" />

<param name="wmode" value="transparent"/>

</object>
</body>
</html>